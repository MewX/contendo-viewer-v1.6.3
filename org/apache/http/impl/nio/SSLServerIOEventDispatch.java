/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLException;
/*     */ import org.apache.http.HttpRequestFactory;
/*     */ import org.apache.http.impl.DefaultHttpRequestFactory;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSession;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSessionHandler;
/*     */ import org.apache.http.impl.nio.reactor.SSLMode;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerIOTarget;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
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
/*     */ public class SSLServerIOEventDispatch
/*     */   implements IOEventDispatch
/*     */ {
/*     */   private static final String SSL_SESSION = "SSL_SESSION";
/*     */   protected final NHttpServiceHandler handler;
/*     */   protected final SSLContext sslcontext;
/*     */   protected final SSLIOSessionHandler sslHandler;
/*     */   protected final HttpParams params;
/*     */   
/*     */   public SSLServerIOEventDispatch(NHttpServiceHandler handler, SSLContext sslContext, SSLIOSessionHandler sslHandler, HttpParams params) {
/*  85 */     Args.notNull(handler, "HTTP service handler");
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
/*     */   public SSLServerIOEventDispatch(NHttpServiceHandler handler, SSLContext sslContext, HttpParams params) {
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
/*     */   protected HttpRequestFactory createHttpRequestFactory() {
/* 135 */     return (HttpRequestFactory)DefaultHttpRequestFactory.INSTANCE;
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
/*     */   protected NHttpServerIOTarget createConnection(IOSession session) {
/* 150 */     return new DefaultNHttpServerConnection(session, createHttpRequestFactory(), createByteBufferAllocator(), this.params);
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
/* 184 */     NHttpServerIOTarget conn = createConnection((IOSession)sslSession);
/*     */ 
/*     */     
/* 187 */     session.setAttribute("http.connection", conn);
/* 188 */     session.setAttribute("SSL_SESSION", sslSession);
/*     */     
/* 190 */     this.handler.connected((NHttpServerConnection)conn);
/*     */     
/*     */     try {
/* 193 */       sslSession.bind(SSLMode.SERVER, this.params);
/* 194 */     } catch (SSLException ex) {
/* 195 */       this.handler.exception((NHttpServerConnection)conn, ex);
/* 196 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void disconnected(IOSession session) {
/* 202 */     NHttpServerIOTarget conn = (NHttpServerIOTarget)session.getAttribute("http.connection");
/*     */ 
/*     */     
/* 205 */     if (conn != null) {
/* 206 */       this.handler.closed((NHttpServerConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(IOSession session) {
/* 212 */     NHttpServerIOTarget conn = (NHttpServerIOTarget)session.getAttribute("http.connection");
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
/* 223 */       this.handler.exception((NHttpServerConnection)conn, ex);
/* 224 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(IOSession session) {
/* 230 */     NHttpServerIOTarget conn = (NHttpServerIOTarget)session.getAttribute("http.connection");
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
/* 241 */       this.handler.exception((NHttpServerConnection)conn, ex);
/* 242 */       sslSession.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(IOSession session) {
/* 248 */     NHttpServerIOTarget conn = (NHttpServerIOTarget)session.getAttribute("http.connection");
/*     */     
/* 250 */     SSLIOSession sslSession = (SSLIOSession)session.getAttribute("SSL_SESSION");
/*     */ 
/*     */     
/* 253 */     this.handler.timeout((NHttpServerConnection)conn);
/* 254 */     synchronized (sslSession) {
/* 255 */       if (sslSession.isOutboundDone() && !sslSession.isInboundDone())
/*     */       {
/* 257 */         sslSession.shutdown();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SSLServerIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */