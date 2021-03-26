/*     */ package org.apache.http.impl.nio.ssl;
/*     */ 
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLException;
/*     */ import org.apache.http.impl.nio.DefaultClientIOEventDispatch;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSession;
/*     */ import org.apache.http.impl.nio.reactor.SSLSetupHandler;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.NHttpClientIOTarget;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.params.HttpConnectionParams;
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
/*     */ @Deprecated
/*     */ public class SSLClientIOEventDispatch
/*     */   extends DefaultClientIOEventDispatch
/*     */ {
/*     */   private final SSLContext sslContext;
/*     */   private final SSLSetupHandler sslHandler;
/*     */   
/*     */   public SSLClientIOEventDispatch(NHttpClientHandler handler, SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/*  73 */     super(handler, params);
/*  74 */     Args.notNull(sslContext, "SSL context");
/*  75 */     Args.notNull(params, "HTTP parameters");
/*  76 */     this.sslContext = sslContext;
/*  77 */     this.sslHandler = sslHandler;
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
/*  94 */     this(handler, sslContext, (SSLSetupHandler)null, params);
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
/*     */   protected SSLIOSession createSSLIOSession(IOSession session, SSLContext sslContext, SSLSetupHandler sslHandler) {
/* 113 */     return new SSLIOSession(session, sslContext, sslHandler);
/*     */   }
/*     */   
/*     */   protected NHttpClientIOTarget createSSLConnection(SSLIOSession sslioSession) {
/* 117 */     return super.createConnection((IOSession)sslioSession);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NHttpClientIOTarget createConnection(IOSession session) {
/* 122 */     SSLIOSession sslioSession = createSSLIOSession(session, this.sslContext, this.sslHandler);
/* 123 */     session.setAttribute("http.session.ssl", sslioSession);
/* 124 */     NHttpClientIOTarget conn = createSSLConnection(sslioSession);
/*     */     try {
/* 126 */       sslioSession.initialize();
/* 127 */     } catch (SSLException ex) {
/* 128 */       this.handler.exception((NHttpClientConnection)conn, ex);
/* 129 */       sslioSession.shutdown();
/*     */     } 
/* 131 */     return conn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onConnected(NHttpClientIOTarget conn) {
/* 136 */     int timeout = HttpConnectionParams.getSoTimeout(this.params);
/* 137 */     conn.setSocketTimeout(timeout);
/*     */     
/* 139 */     Object attachment = conn.getContext().getAttribute("http.session.attachment");
/* 140 */     this.handler.connected((NHttpClientConnection)conn, attachment);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/ssl/SSLClientIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */