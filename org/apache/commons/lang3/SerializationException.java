/*    */ package org.apache.commons.lang3;
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
/*    */ 
/*    */ 
/*    */ public class SerializationException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 4029025366392702726L;
/*    */   
/*    */   public SerializationException() {}
/*    */   
/*    */   public SerializationException(String msg) {
/* 51 */     super(msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SerializationException(Throwable cause) {
/* 62 */     super(cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SerializationException(String msg, Throwable cause) {
/* 74 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/SerializationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */