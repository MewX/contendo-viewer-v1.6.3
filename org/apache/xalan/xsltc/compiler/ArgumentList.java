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
/*    */ final class ArgumentList
/*    */ {
/*    */   private final Expression _arg;
/*    */   private final ArgumentList _rest;
/*    */   
/*    */   public ArgumentList(Expression arg, ArgumentList rest) {
/* 31 */     this._arg = arg;
/* 32 */     this._rest = rest;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     return (this._rest == null) ? this._arg.toString() : (this._arg.toString() + ", " + this._rest.toString());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ArgumentList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */