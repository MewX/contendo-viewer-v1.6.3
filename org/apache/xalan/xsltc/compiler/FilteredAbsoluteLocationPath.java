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
/*     */ final class FilteredAbsoluteLocationPath
/*     */   extends Expression
/*     */ {
/*     */   private Expression _path;
/*     */   
/*     */   public FilteredAbsoluteLocationPath() {
/*  40 */     this._path = null;
/*     */   }
/*     */   
/*     */   public FilteredAbsoluteLocationPath(Expression path) {
/*  44 */     this._path = path;
/*  45 */     if (path != null) {
/*  46 */       this._path.setParent(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  51 */     super.setParser(parser);
/*  52 */     if (this._path != null) {
/*  53 */       this._path.setParser(parser);
/*     */     }
/*     */   }
/*     */   
/*     */   public Expression getPath() {
/*  58 */     return this._path;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  62 */     return "FilteredAbsoluteLocationPath(" + ((this._path != null) ? this._path.toString() : "null") + ')';
/*     */   }
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  67 */     if (this._path != null) {
/*  68 */       Type ptype = this._path.typeCheck(stable);
/*  69 */       if (ptype instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/*  70 */         this._path = new CastExpr(this._path, Type.NodeSet);
/*     */       }
/*     */     } 
/*  73 */     return this._type = Type.NodeSet;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  77 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  78 */     InstructionList il = methodGen.getInstructionList();
/*  79 */     if (this._path != null) {
/*  80 */       int initDFI = cpg.addMethodref("org.apache.xalan.xsltc.dom.DupFilterIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.DupFilterIterator")));
/*  87 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/*  90 */       this._path.translate(classGen, methodGen);
/*     */ 
/*     */       
/*  93 */       il.append((Instruction)new INVOKESPECIAL(initDFI));
/*     */     } else {
/*     */       
/*  96 */       int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/*  99 */       il.append(methodGen.loadDOM());
/* 100 */       il.append((Instruction)new INVOKEINTERFACE(git, 1));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FilteredAbsoluteLocationPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */