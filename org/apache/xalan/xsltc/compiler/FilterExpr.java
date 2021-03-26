/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class FilterExpr
/*     */   extends Expression
/*     */ {
/*     */   private Expression _primary;
/*     */   private final Vector _predicates;
/*     */   
/*     */   public FilterExpr(Expression primary, Vector predicates) {
/*  55 */     this._primary = primary;
/*  56 */     this._predicates = predicates;
/*  57 */     primary.setParent(this);
/*     */   }
/*     */   
/*     */   protected Expression getExpr() {
/*  61 */     if (this._primary instanceof CastExpr) {
/*  62 */       return ((CastExpr)this._primary).getExpr();
/*     */     }
/*  64 */     return this._primary;
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  68 */     super.setParser(parser);
/*  69 */     this._primary.setParser(parser);
/*  70 */     if (this._predicates != null) {
/*  71 */       int n = this._predicates.size();
/*  72 */       for (int i = 0; i < n; i++) {
/*  73 */         Expression exp = this._predicates.elementAt(i);
/*  74 */         exp.setParser(parser);
/*  75 */         exp.setParent(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  81 */     return "filter-expr(" + this._primary + ", " + this._predicates + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  92 */     Type ptype = this._primary.typeCheck(stable);
/*     */     
/*  94 */     if (!(ptype instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType)) {
/*  95 */       if (ptype instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/*  96 */         this._primary = new CastExpr(this._primary, Type.NodeSet);
/*     */       } else {
/*     */         
/*  99 */         throw new TypeCheckError(this);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 104 */     int n = this._predicates.size();
/* 105 */     for (int i = 0; i < n; i++) {
/* 106 */       Predicate pred = this._predicates.elementAt(i);
/* 107 */       pred.dontOptimize();
/* 108 */       pred.typeCheck(stable);
/*     */     } 
/* 110 */     return this._type = Type.NodeSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 118 */     if (this._predicates.size() > 0) {
/* 119 */       translatePredicates(classGen, methodGen);
/*     */     } else {
/*     */       
/* 122 */       this._primary.translate(classGen, methodGen);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translatePredicates(ClassGenerator classGen, MethodGenerator methodGen) {
/* 134 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 135 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 138 */     if (this._predicates.size() == 0) {
/* 139 */       translate(classGen, methodGen);
/*     */     }
/*     */     else {
/*     */       
/* 143 */       int initCNLI = cpg.addMethodref("org.apache.xalan.xsltc.dom.CurrentNodeListIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;ZLorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;ILorg/apache/xalan/xsltc/runtime/AbstractTranslet;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       Predicate predicate = this._predicates.lastElement();
/* 150 */       this._predicates.remove(predicate);
/*     */ 
/*     */       
/* 153 */       il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CurrentNodeListIterator")));
/* 154 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 157 */       translatePredicates(classGen, methodGen);
/*     */ 
/*     */       
/* 160 */       il.append(InstructionConstants.ICONST_1);
/* 161 */       predicate.translate(classGen, methodGen);
/* 162 */       il.append(methodGen.loadCurrentNode());
/* 163 */       il.append(classGen.loadTranslet());
/* 164 */       il.append((Instruction)new INVOKESPECIAL(initCNLI));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FilterExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */