/*    */ package org.apache.commons.collections;
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
/*    */ public class BufferOverflowException
/*    */   extends RuntimeException
/*    */ {
/*    */   private final Throwable throwable;
/*    */   
/*    */   public BufferOverflowException() {
/* 41 */     this.throwable = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferOverflowException(String message) {
/* 50 */     this(message, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferOverflowException(String message, Throwable exception) {
/* 60 */     super(message);
/* 61 */     this.throwable = exception;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final Throwable getCause() {
/* 70 */     return this.throwable;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BufferOverflowException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */