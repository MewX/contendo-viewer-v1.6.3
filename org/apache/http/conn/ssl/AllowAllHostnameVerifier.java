/*    */ package org.apache.http.conn.ssl;
/*    */ 
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
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
/*    */ @Contract(threading = ThreadingBehavior.IMMUTABLE)
/*    */ public class AllowAllHostnameVerifier
/*    */   extends AbstractVerifier
/*    */ {
/* 46 */   public static final AllowAllHostnameVerifier INSTANCE = new AllowAllHostnameVerifier();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void verify(String host, String[] cns, String[] subjectAlts) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String toString() {
/* 58 */     return "ALLOW_ALL";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/ssl/AllowAllHostnameVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */