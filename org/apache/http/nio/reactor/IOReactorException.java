/*    */ package org.apache.http.nio.reactor;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class IOReactorException
/*    */   extends IOException
/*    */ {
/*    */   private static final long serialVersionUID = -4248110651729635749L;
/*    */   
/*    */   public IOReactorException(String message, Exception cause) {
/* 43 */     super(message);
/* 44 */     if (cause != null) {
/* 45 */       initCause(cause);
/*    */     }
/*    */   }
/*    */   
/*    */   public IOReactorException(String message, Throwable cause) {
/* 50 */     super(message);
/* 51 */     if (cause != null) {
/* 52 */       initCause(cause);
/*    */     }
/*    */   }
/*    */   
/*    */   public IOReactorException(String message) {
/* 57 */     super(message);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/IOReactorException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */