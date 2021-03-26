/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import javax.net.ssl.SSLEngine;
/*    */ import javax.net.ssl.SSLException;
/*    */ import javax.net.ssl.SSLSession;
/*    */ import org.apache.http.nio.reactor.IOSession;
/*    */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
/*    */ import org.apache.http.params.BasicHttpParams;
/*    */ import org.apache.http.params.HttpParams;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ class SSLIOSessionHandlerAdaptor
/*    */   implements SSLSetupHandler
/*    */ {
/*    */   private final SSLIOSessionHandler handler;
/*    */   private HttpParams params;
/*    */   
/*    */   public SSLIOSessionHandlerAdaptor(SSLIOSessionHandler handler) {
/* 50 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initalize(SSLEngine sslengine) throws SSLException {
/* 55 */     this.handler.initalize(sslengine, (this.params != null) ? this.params : (HttpParams)new BasicHttpParams());
/*    */   }
/*    */ 
/*    */   
/*    */   public void verify(IOSession ioSession, SSLSession sslsession) throws SSLException {
/* 60 */     this.handler.verify(ioSession.getRemoteAddress(), sslsession);
/*    */   }
/*    */   
/*    */   public void setParams(HttpParams params) {
/* 64 */     this.params = params;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SSLIOSessionHandlerAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */