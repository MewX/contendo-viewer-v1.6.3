/*    */ package org.apache.xalan.xsltc.compiler;
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
/*    */ public final class CompilerException
/*    */   extends Exception
/*    */ {
/*    */   private String _msg;
/*    */   
/*    */   public CompilerException() {}
/*    */   
/*    */   public CompilerException(Exception e) {
/* 34 */     super(e.toString());
/* 35 */     this._msg = e.toString();
/*    */   }
/*    */   
/*    */   public CompilerException(String message) {
/* 39 */     super(message);
/* 40 */     this._msg = message;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 44 */     int col = this._msg.indexOf(':');
/*    */     
/* 46 */     if (col > -1) {
/* 47 */       return this._msg.substring(col);
/*    */     }
/* 49 */     return this._msg;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CompilerException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */