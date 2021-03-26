/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.SyntaxTreeNode;
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
/*    */ public class TypeCheckError
/*    */   extends Exception
/*    */ {
/* 29 */   ErrorMsg _error = null;
/* 30 */   SyntaxTreeNode _node = null;
/*    */ 
/*    */   
/*    */   public TypeCheckError(SyntaxTreeNode node) {
/* 34 */     this._node = node;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeCheckError(ErrorMsg error) {
/* 39 */     this._error = error;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeCheckError(String code, Object param) {
/* 44 */     this._error = new ErrorMsg(code, param);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeCheckError(String code, Object param1, Object param2) {
/* 49 */     this._error = new ErrorMsg(code, param1, param2);
/*    */   }
/*    */   
/*    */   public ErrorMsg getErrorMsg() {
/* 53 */     return this._error;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 57 */     return toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     if (this._error == null) {
/* 64 */       if (this._node != null) {
/* 65 */         this._error = new ErrorMsg("TYPE_CHECK_ERR", this._node.toString());
/*    */       } else {
/*    */         
/* 68 */         this._error = new ErrorMsg("TYPE_CHECK_UNK_LOC_ERR");
/*    */       } 
/*    */     }
/*    */     
/* 72 */     return this._error.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/TypeCheckError.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */