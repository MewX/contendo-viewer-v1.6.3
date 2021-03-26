/*     */ package org.apache.http.impl.nio.ssl;
/*     */ 
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLException;
/*     */ import org.apache.http.impl.nio.DefaultServerIOEventDispatch;
/*     */ import org.apache.http.impl.nio.reactor.SSLIOSession;
/*     */ import org.apache.http.impl.nio.reactor.SSLSetupHandler;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerIOTarget;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
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
/*     */ public class SSLServerIOEventDispatch
/*     */   extends DefaultServerIOEventDispatch
/*     */ {
/*     */   private final SSLContext sslContext;
/*     */   private final SSLSetupHandler sslHandler;
/*     */   
/*     */   public SSLServerIOEventDispatch(NHttpServiceHandler handler, SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
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
/*     */   public SSLServerIOEventDispatch(NHttpServiceHandler handler, SSLContext sslContext, HttpParams params) {
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
/*     */   protected NHttpServerIOTarget createSSLConnection(SSLIOSession sslioSession) {
/* 117 */     return super.createConnection((IOSession)sslioSession);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NHttpServerIOTarget createConnection(IOSession session) {
/* 122 */     SSLIOSession sslioSession = createSSLIOSession(session, this.sslContext, this.sslHandler);
/* 123 */     session.setAttribute("http.session.ssl", sslioSession);
/* 124 */     NHttpServerIOTarget conn = createSSLConnection(sslioSession);
/*     */     try {
/* 126 */       sslioSession.initialize();
/* 127 */     } catch (SSLException ex) {
/* 128 */       this.handler.exception((NHttpServerConnection)conn, ex);
/* 129 */       sslioSession.shutdown();
/*     */     } 
/* 131 */     return conn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onConnected(NHttpServerIOTarget conn) {
/* 136 */     int timeout = HttpConnectionParams.getSoTimeout(this.params);
/* 137 */     conn.setSocketTimeout(timeout);
/* 138 */     this.handler.connected((NHttpServerConnection)conn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/ssl/SSLServerIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */