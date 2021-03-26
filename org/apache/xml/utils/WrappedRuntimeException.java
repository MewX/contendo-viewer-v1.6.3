/*    */ package org.apache.xml.utils;
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
/*    */ public class WrappedRuntimeException
/*    */   extends RuntimeException
/*    */ {
/*    */   private Exception m_exception;
/*    */   
/*    */   public WrappedRuntimeException(Exception e) {
/* 42 */     super(e.getMessage());
/*    */     
/* 44 */     this.m_exception = e;
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
/*    */   
/*    */   public WrappedRuntimeException(String msg, Exception e) {
/* 57 */     super(msg);
/*    */     
/* 59 */     this.m_exception = e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Exception getException() {
/* 69 */     return this.m_exception;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/WrappedRuntimeException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */