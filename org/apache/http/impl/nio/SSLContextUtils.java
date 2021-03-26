/*    */ package org.apache.http.impl.nio;
/*    */ 
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import javax.net.ssl.SSLContext;
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
/*    */ class SSLContextUtils
/*    */ {
/*    */   static SSLContext getDefault() {
/*    */     SSLContext sSLContext;
/*    */     try {
/*    */       try {
/* 39 */         sSLContext = SSLContext.getInstance("Default");
/* 40 */       } catch (NoSuchAlgorithmException ex) {
/* 41 */         sSLContext = SSLContext.getInstance("TLS");
/*    */       } 
/* 43 */       sSLContext.init(null, null, null);
/* 44 */     } catch (Exception ex) {
/* 45 */       throw new IllegalStateException("Failure initializing default SSL context", ex);
/*    */     } 
/* 47 */     return sSLContext;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SSLContextUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */