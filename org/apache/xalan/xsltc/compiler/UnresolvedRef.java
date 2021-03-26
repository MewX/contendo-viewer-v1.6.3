/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*    */ final class UnresolvedRef
/*    */   extends VariableRefBase
/*    */ {
/* 33 */   private QName _variableName = null;
/* 34 */   private VariableRefBase _ref = null;
/* 35 */   private VariableBase _var = null;
/* 36 */   private Stylesheet _sheet = null;
/*    */ 
/*    */   
/*    */   public UnresolvedRef(QName name) {
/* 40 */     this._variableName = name;
/* 41 */     this._sheet = getStylesheet();
/*    */   }
/*    */   
/*    */   public QName getName() {
/* 45 */     return this._variableName;
/*    */   }
/*    */   
/*    */   private ErrorMsg reportError() {
/* 49 */     ErrorMsg err = new ErrorMsg("VARIABLE_UNDEF_ERR", this._variableName, this);
/*    */     
/* 51 */     getParser().reportError(3, err);
/* 52 */     return err;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private VariableRefBase resolve(Parser parser, SymbolTable stable) {
/* 58 */     VariableBase ref = parser.lookupVariable(this._variableName);
/* 59 */     if (ref == null) ref = (VariableBase)stable.lookupName(this._variableName); 
/* 60 */     if (ref == null) {
/* 61 */       reportError();
/* 62 */       return null;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 67 */     if ((this._var = findParentVariable()) != null) this._var.addDependency(ref);
/*    */ 
/*    */     
/* 70 */     if (ref instanceof Variable)
/* 71 */       return new VariableRef((Variable)ref); 
/* 72 */     if (ref instanceof Param) {
/* 73 */       return new ParameterRef((Param)ref);
/*    */     }
/* 75 */     return null;
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 79 */     if (this._ref != null) {
/* 80 */       String name = this._variableName.toString();
/* 81 */       ErrorMsg errorMsg = new ErrorMsg("CIRCULAR_VARIABLE_ERR", name, this);
/*    */     } 
/*    */     
/* 84 */     if ((this._ref = resolve(getParser(), stable)) != null) {
/* 85 */       return this._type = this._ref.typeCheck(stable);
/*    */     }
/* 87 */     throw new TypeCheckError(reportError());
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 91 */     if (this._ref != null) {
/* 92 */       this._ref.translate(classGen, methodGen);
/*    */     } else {
/* 94 */       reportError();
/*    */     } 
/*    */   }
/*    */   public String toString() {
/* 98 */     return "unresolved-ref()";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UnresolvedRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */