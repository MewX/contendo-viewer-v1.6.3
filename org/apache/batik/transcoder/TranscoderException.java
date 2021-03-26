/*    */ package org.apache.batik.transcoder;
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
/*    */ public class TranscoderException
/*    */   extends Exception
/*    */ {
/*    */   protected Exception ex;
/*    */   
/*    */   public TranscoderException(String s) {
/* 37 */     this(s, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TranscoderException(Exception ex) {
/* 45 */     this(null, ex);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TranscoderException(String s, Exception ex) {
/* 54 */     super(s, ex);
/* 55 */     this.ex = ex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 64 */     String msg = super.getMessage();
/* 65 */     if (this.ex != null) {
/* 66 */       msg = msg + "\nEnclosed Exception:\n";
/* 67 */       msg = msg + this.ex.getMessage();
/*    */     } 
/* 69 */     return msg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Exception getException() {
/* 76 */     return this.ex;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/TranscoderException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */