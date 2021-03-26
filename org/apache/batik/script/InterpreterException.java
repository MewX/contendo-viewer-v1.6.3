/*    */ package org.apache.batik.script;
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
/*    */ public class InterpreterException
/*    */   extends RuntimeException
/*    */ {
/* 29 */   private int line = -1;
/* 30 */   private int column = -1;
/* 31 */   private Exception embedded = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InterpreterException(String message, int lineno, int columnno) {
/* 40 */     super(message);
/* 41 */     this.line = lineno;
/* 42 */     this.column = columnno;
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
/*    */   public InterpreterException(Exception exception, String message, int lineno, int columnno) {
/* 54 */     this(message, lineno, columnno);
/* 55 */     this.embedded = exception;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLineNumber() {
/* 63 */     return this.line;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColumnNumber() {
/* 71 */     return this.column;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Exception getException() {
/* 79 */     return this.embedded;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 88 */     String msg = super.getMessage();
/* 89 */     if (msg != null)
/* 90 */       return msg; 
/* 91 */     if (this.embedded != null) {
/* 92 */       return this.embedded.getMessage();
/*    */     }
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/InterpreterException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */