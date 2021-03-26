/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLException;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSession;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSessionHandler;
/*     */ import org.apache.http.impl.nio.reactor.SSLMode;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.NHttpClientIOTarget;
/*     */ import org.apache.http.nio.reactor.IOEventDispatch;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.util.Args;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class SSLClientIOEventDispatch
/*     */   implements IOEventDispatch
/*     */ {
/*     */   private static final String SSL_SESSION = "SSL_SESSION";
/*     */   protected final NHttpClientHandler handler;
/*     */   protected final SSLContext sslcontext;
/*     */   protected final SSLIOSessionHandler sslHandler;
/*     */   protected final HttpParams params;
/*     */   
/*     */   public SSLClientIOEventDispatch(NHttpClientHandler handler, SSLContext sslContext, SSLIOSessionHandler sslHandler, HttpParams params) {
/*  85 */     Args.notNull(handler, "HTTP client handler");
/*  86 */     Args.notNull(sslContext, "SSL context");
/*  87 */     Args.notNull(params, "HTTP parameters");
/*  88 */     this.handler = handler;
/*  89 */     this.params = params;
/*  90 */     this.sslcontext = sslContext;
/*  91 */     this.sslHandler = sslHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLClientIOEventDispatch(NHttpClientHandler handler, SSLContext sslContext, HttpParams params) {
/* 108 */     this(handler, sslContext, null, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ByteBufferAllocator createByteBufferAllocator() {
/* 121 */     return (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HttpResponseFactory createHttpResponseFactory() {
/* 135 */     return (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected NHttpClientIOTarget createConnection(IOSession session) {
/* 150 */     return new DefaultNHttpClientConnection(session, createHttpResponseFactory(), createByteBufferAllocator(), this.params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SSLIOSession createSSLIOSession(IOSession session, SSLContext sslContext, SSLIOSessionHandler sslHandler) {
/* 173 */     return new SSLIOSession(session, sslContext, sslHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void connected(IOSession session) {
/* 179 */     SSLIOSession sslSession = createSSLIOSession(session, this.sslcontext, this.sslHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     NHttpClientIOTarget conn = createConnection((IOSession)sslSession);
/*     */ 
/*     */     
/* 187 */     session.setAttribute("http.connection", conn);
/* 188 */     session.setAttribute("SSL_SESSION", sslSession);
/*     */     
/* 190 */     Object attachment = session.getAttribute("http.session.attachment");
/* 191 */     this.handler.connected((NHttpClientConnection)conn, attachment);
/*     */     
/*     */     try {
/* 194 */       sslSession.bind(SSLMode.CLIENT, this.params);
/* 195 */     } catch (SSLException ex) {
/* 196 */       this.handler.exception((NHttpClientConnection)conn, ex);
/* 197 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void disconnected(IOSession session) {
/* 203 */     NHttpClientIOTarget conn = (NHttpClientIOTarget)session.getAttribute("http.connection");
/*     */     
/* 205 */     if (conn != null) {
/* 206 */       this.handler.closed((NHttpClientConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(IOSession session) {
/* 212 */     NHttpClientIOTarget conn = (NHttpClientIOTarget)session.getAttribute("http.connection");
/*     */     
/* 214 */     SSLIOSession sslSession = (SSLIOSession)session.getAttribute("SSL_SESSION");
/*     */ 
/*     */     
/*     */     try {
/* 218 */       if (sslSession.isAppInputReady()) {
/* 219 */         conn.consumeInput(this.handler);
/*     */       }
/* 221 */       sslSession.inboundTransport();
/* 222 */     } catch (IOException ex) {
/* 223 */       this.handler.exception((NHttpClientConnection)conn, ex);
/* 224 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(IOSession session) {
/* 230 */     NHttpClientIOTarget conn = (NHttpClientIOTarget)session.getAttribute("http.connection");
/*     */     
/* 232 */     SSLIOSession sslSession = (SSLIOSession)session.getAttribute("SSL_SESSION");
/*     */ 
/*     */     
/*     */     try {
/* 236 */       if (sslSession.isAppOutputReady()) {
/* 237 */         conn.produceOutput(this.handler);
/*     */       }
/* 239 */       sslSession.outboundTransport();
/* 240 */     } catch (IOException ex) {
/* 241 */       this.handler.exception((NHttpClientConnection)conn, ex);
/* 242 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(IOSession session) {
/* 248 */     NHttpClientIOTarget conn = (NHttpClientIOTarget)session.getAttribute("http.connection");
/*     */     
/* 250 */     SSLIOSession sslSession = (SSLIOSession)session.getAttribute("SSL_SESSION");
/*     */ 
/*     */     
/* 253 */     this.handler.timeout((NHttpClientConnection)conn);
/* 254 */     synchronized (sslSession) {
/* 255 */       if (sslSession.isOutboundDone() && !sslSession.isInboundDone())
/*     */       {
/* 257 */         sslSession.shutdown();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SSLClientIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */