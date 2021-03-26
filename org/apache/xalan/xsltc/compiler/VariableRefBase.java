/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class VariableRefBase
/*     */   extends Expression
/*     */ {
/*     */   protected final VariableBase _variable;
/*  40 */   protected Closure _closure = null;
/*     */   
/*     */   public VariableRefBase(VariableBase variable) {
/*  43 */     this._variable = variable;
/*  44 */     variable.addReference(this);
/*     */   }
/*     */   
/*     */   public VariableRefBase() {
/*  48 */     this._variable = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableBase getVariable() {
/*  55 */     return this._variable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableBase findParentVariable() {
/*  62 */     SyntaxTreeNode node = this;
/*  63 */     while (node != null && !(node instanceof VariableBase)) {
/*  64 */       node = node.getParent();
/*     */     }
/*  66 */     return (VariableBase)node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*     */     
/*  75 */     try { return (this._variable == ((VariableRefBase)obj)._variable); } catch (ClassCastException e)
/*     */     
/*     */     { 
/*  78 */       return false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     return "variable-ref(" + this._variable.getName() + '/' + this._variable.getType() + ')';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  95 */     if (this._type != null) return this._type;
/*     */ 
/*     */     
/*  98 */     if (this._variable.isLocal()) {
/*  99 */       SyntaxTreeNode node = getParent();
/*     */       do {
/* 101 */         if (node instanceof Closure) {
/* 102 */           this._closure = (Closure)node;
/*     */           break;
/*     */         } 
/* 105 */         if (node instanceof TopLevelElement) {
/*     */           break;
/*     */         }
/* 108 */         node = node.getParent();
/* 109 */       } while (node != null);
/*     */       
/* 111 */       if (this._closure != null) {
/* 112 */         this._closure.addVariable(this);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 117 */     VariableBase parent = findParentVariable();
/* 118 */     if (parent != null) {
/* 119 */       VariableBase var = this._variable;
/* 120 */       if (this._variable._ignore) {
/* 121 */         if (this._variable instanceof Variable) {
/* 122 */           var = parent.getSymbolTable().lookupVariable(this._variable._name);
/*     */         }
/* 124 */         else if (this._variable instanceof Param) {
/* 125 */           var = parent.getSymbolTable().lookupParam(this._variable._name);
/*     */         } 
/*     */       }
/* 128 */       parent.addDependency(var);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     this._type = this._variable.getType();
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (this._type == null) {
/* 137 */       this._variable.typeCheck(stable);
/* 138 */       this._type = this._variable.getType();
/*     */     } 
/*     */ 
/*     */     
/* 142 */     return this._type;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/VariableRefBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */