/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
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
/*     */ final class AbsoluteLocationPath
/*     */   extends Expression
/*     */ {
/*     */   private Expression _path;
/*     */   
/*     */   public AbsoluteLocationPath() {
/*  41 */     this._path = null;
/*     */   }
/*     */   
/*     */   public AbsoluteLocationPath(Expression path) {
/*  45 */     this._path = path;
/*  46 */     if (path != null) {
/*  47 */       this._path.setParent(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  52 */     super.setParser(parser);
/*  53 */     if (this._path != null) {
/*  54 */       this._path.setParser(parser);
/*     */     }
/*     */   }
/*     */   
/*     */   public Expression getPath() {
/*  59 */     return this._path;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  63 */     return "AbsoluteLocationPath(" + ((this._path != null) ? this._path.toString() : "null") + ')';
/*     */   }
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  68 */     if (this._path != null) {
/*  69 */       Type ptype = this._path.typeCheck(stable);
/*  70 */       if (ptype instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/*  71 */         this._path = new CastExpr(this._path, Type.NodeSet);
/*     */       }
/*     */     } 
/*  74 */     return this._type = Type.NodeSet;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  78 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  79 */     InstructionList il = methodGen.getInstructionList();
/*  80 */     if (this._path != null) {
/*  81 */       int initAI = cpg.addMethodref("org.apache.xalan.xsltc.dom.AbsoluteIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.AbsoluteIterator")));
/*  88 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/*  91 */       this._path.translate(classGen, methodGen);
/*     */ 
/*     */       
/*  94 */       il.append((Instruction)new INVOKESPECIAL(initAI));
/*     */     } else {
/*     */       
/*  97 */       int gitr = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/* 100 */       il.append(methodGen.loadDOM());
/* 101 */       il.append((Instruction)new INVOKEINTERFACE(gitr, 1));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AbsoluteLocationPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */