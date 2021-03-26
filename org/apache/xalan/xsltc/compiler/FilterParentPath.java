/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
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
/*     */ final class FilterParentPath
/*     */   extends Expression
/*     */ {
/*     */   private Expression _filterExpr;
/*     */   private Expression _path;
/*     */   private boolean _hasDescendantAxis = false;
/*     */   
/*     */   public FilterParentPath(Expression filterExpr, Expression path) {
/*  47 */     (this._path = path).setParent(this);
/*  48 */     (this._filterExpr = filterExpr).setParent(this);
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  52 */     super.setParser(parser);
/*  53 */     this._filterExpr.setParser(parser);
/*  54 */     this._path.setParser(parser);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  58 */     return "FilterParentPath(" + this._filterExpr + ", " + this._path + ')';
/*     */   }
/*     */   
/*     */   public void setDescendantAxis() {
/*  62 */     this._hasDescendantAxis = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  71 */     Type ftype = this._filterExpr.typeCheck(stable);
/*  72 */     if (!(ftype instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType)) {
/*  73 */       if (ftype instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/*  74 */         this._filterExpr = new CastExpr(this._filterExpr, Type.NodeSet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*  81 */       else if (ftype instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/*  82 */         this._filterExpr = new CastExpr(this._filterExpr, Type.NodeSet);
/*     */       } else {
/*     */         
/*  85 */         throw new TypeCheckError(this);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  90 */     Type ptype = this._path.typeCheck(stable);
/*  91 */     if (!(ptype instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType)) {
/*  92 */       this._path = new CastExpr(this._path, Type.NodeSet);
/*     */     }
/*     */     
/*  95 */     return this._type = Type.NodeSet;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  99 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 100 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 102 */     int initSI = cpg.addMethodref("org.apache.xalan.xsltc.dom.StepIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.StepIterator")));
/* 109 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 112 */     this._filterExpr.translate(classGen, methodGen);
/* 113 */     this._path.translate(classGen, methodGen);
/*     */ 
/*     */     
/* 116 */     il.append((Instruction)new INVOKESPECIAL(initSI));
/*     */ 
/*     */     
/* 119 */     if (this._hasDescendantAxis) {
/* 120 */       int incl = cpg.addMethodref("org.apache.xml.dtm.ref.DTMAxisIteratorBase", "includeSelf", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/* 123 */       il.append((Instruction)new INVOKEVIRTUAL(incl));
/*     */     } 
/*     */     
/* 126 */     if (!(getParent() instanceof RelativeLocationPath) && !(getParent() instanceof FilterParentPath)) {
/*     */       
/* 128 */       int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/* 131 */       il.append(methodGen.loadDOM());
/* 132 */       il.append((Instruction)InstructionConstants.SWAP);
/* 133 */       il.append(methodGen.loadContextNode());
/* 134 */       il.append((Instruction)new INVOKEINTERFACE(order, 3));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FilterParentPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */