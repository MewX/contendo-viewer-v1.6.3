/*    */ package org.apache.commons.lang3.exception;
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
/*    */ public class CloneFailedException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 20091223L;
/*    */   
/*    */   public CloneFailedException(String message) {
/* 38 */     super(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CloneFailedException(Throwable cause) {
/* 47 */     super(cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CloneFailedException(String message, Throwable cause) {
/* 57 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/exception/CloneFailedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */