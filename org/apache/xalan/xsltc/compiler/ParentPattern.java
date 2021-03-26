/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
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
/*     */ final class ParentPattern
/*     */   extends RelativePathPattern
/*     */ {
/*     */   private final Pattern _left;
/*     */   private final RelativePathPattern _right;
/*     */   
/*     */   public ParentPattern(Pattern left, RelativePathPattern right) {
/*  43 */     (this._left = left).setParent(this);
/*  44 */     (this._right = right).setParent(this);
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  48 */     super.setParser(parser);
/*  49 */     this._left.setParser(parser);
/*  50 */     this._right.setParser(parser);
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public StepPattern getKernelPattern() {
/*  58 */     return this._right.getKernelPattern();
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {
/*  62 */     this._right.reduceKernelPattern();
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  66 */     this._left.typeCheck(stable);
/*  67 */     return this._right.typeCheck(stable);
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  71 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  72 */     InstructionList il = methodGen.getInstructionList();
/*  73 */     LocalVariableGen local = methodGen.addLocalVariable2("ppt", Util.getJCRefType("I"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     ILOAD iLOAD = new ILOAD(local.getIndex());
/*     */     
/*  80 */     ISTORE iSTORE = new ISTORE(local.getIndex());
/*     */ 
/*     */     
/*  83 */     if (this._right.isWildcard()) {
/*  84 */       il.append(methodGen.loadDOM());
/*  85 */       il.append((Instruction)InstructionConstants.SWAP);
/*     */     }
/*  87 */     else if (this._right instanceof StepPattern) {
/*  88 */       il.append((Instruction)InstructionConstants.DUP);
/*  89 */       il.append((Instruction)iSTORE);
/*     */       
/*  91 */       this._right.translate(classGen, methodGen);
/*     */       
/*  93 */       il.append(methodGen.loadDOM());
/*  94 */       local.setEnd(il.append((Instruction)iLOAD));
/*     */     } else {
/*     */       
/*  97 */       this._right.translate(classGen, methodGen);
/*     */       
/*  99 */       if (this._right instanceof AncestorPattern) {
/* 100 */         il.append(methodGen.loadDOM());
/* 101 */         il.append((Instruction)InstructionConstants.SWAP);
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
/*     */ 
/*     */     
/* 108 */     il.append((Instruction)new INVOKEINTERFACE(getParent, 2));
/*     */     
/* 110 */     SyntaxTreeNode p = getParent();
/* 111 */     if (p == null || p instanceof Instruction || p instanceof TopLevelElement) {
/*     */ 
/*     */       
/* 114 */       this._left.translate(classGen, methodGen);
/*     */     } else {
/*     */       
/* 117 */       il.append((Instruction)InstructionConstants.DUP);
/* 118 */       il.append((Instruction)iSTORE);
/*     */       
/* 120 */       this._left.translate(classGen, methodGen);
/*     */       
/* 122 */       il.append(methodGen.loadDOM());
/* 123 */       local.setEnd(il.append((Instruction)iLOAD));
/*     */     } 
/*     */     
/* 126 */     methodGen.removeLocalVariable(local);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     if (this._right instanceof AncestorPattern) {
/* 133 */       AncestorPattern ancestor = (AncestorPattern)this._right;
/* 134 */       this._left.backPatchFalseList(ancestor.getLoopHandle());
/*     */     } 
/*     */     
/* 137 */     this._trueList.append(this._right._trueList.append(this._left._trueList));
/* 138 */     this._falseList.append(this._right._falseList.append(this._left._falseList));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 142 */     return "Parent(" + this._left + ", " + this._right + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ParentPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */