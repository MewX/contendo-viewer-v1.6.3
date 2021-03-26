/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLException;
/*    */ import org.apache.http.nio.reactor.IOSession;
/*    */ import org.apache.http.nio.reactor.ssl.SSLIOSession;
/*    */ import org.apache.http.nio.reactor.ssl.SSLMode;
/*    */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SSLIOSession
/*    */   extends SSLIOSession
/*    */ {
/*    */   public SSLIOSession(IOSession session, SSLContext sslContext, SSLSetupHandler handler) {
/* 54 */     super(session, SSLMode.CLIENT, sslContext, (handler != null) ? new SSLSetupHandlerAdaptor(handler) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SSLIOSession(IOSession session, SSLContext sslContext, SSLIOSessionHandler handler) {
/* 62 */     super(session, SSLMode.CLIENT, sslContext, (handler != null) ? new SSLIOSessionHandlerAdaptor(handler) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void bind(SSLMode mode, HttpParams params) throws SSLException {
/* 69 */     SSLSetupHandler handler = getSSLSetupHandler();
/* 70 */     if (handler instanceof SSLIOSessionHandlerAdaptor) {
/* 71 */       ((SSLIOSessionHandlerAdaptor)handler).setParams(params);
/* 72 */     } else if (handler instanceof SSLSetupHandlerAdaptor) {
/* 73 */       ((SSLSetupHandlerAdaptor)handler).setParams(params);
/*    */     } 
/* 75 */     initialize(convert(mode));
/*    */   }
/*    */   
/*    */   private SSLMode convert(SSLMode mode) {
/* 79 */     switch (mode) {
/*    */       case CLIENT:
/* 81 */         return SSLMode.CLIENT;
/*    */       case SERVER:
/* 83 */         return SSLMode.SERVER;
/*    */     } 
/* 85 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SSLIOSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */