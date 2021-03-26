/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO_W;
/*     */ import org.apache.bcel.generic.IF_ICMPEQ;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.PUSH;
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
/*     */ final class AbsolutePathPattern
/*     */   extends LocationPathPattern
/*     */ {
/*     */   private final RelativePathPattern _left;
/*     */   
/*     */   public AbsolutePathPattern(RelativePathPattern left) {
/*  48 */     this._left = left;
/*  49 */     if (left != null) {
/*  50 */       left.setParent(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  55 */     super.setParser(parser);
/*  56 */     if (this._left != null)
/*  57 */       this._left.setParser(parser); 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  61 */     return (this._left == null) ? Type.Root : this._left.typeCheck(stable);
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public StepPattern getKernelPattern() {
/*  69 */     return (this._left != null) ? this._left.getKernelPattern() : null;
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {
/*  73 */     this._left.reduceKernelPattern();
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  77 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  78 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/*  80 */     if (this._left != null) {
/*  81 */       if (this._left instanceof StepPattern) {
/*  82 */         LocalVariableGen local = methodGen.addLocalVariable2("apptmp", Util.getJCRefType("I"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  87 */         il.append((Instruction)InstructionConstants.DUP);
/*  88 */         il.append((Instruction)new ISTORE(local.getIndex()));
/*  89 */         this._left.translate(classGen, methodGen);
/*  90 */         il.append(methodGen.loadDOM());
/*  91 */         local.setEnd(il.append((Instruction)new ILOAD(local.getIndex())));
/*  92 */         methodGen.removeLocalVariable(local);
/*     */       } else {
/*     */         
/*  95 */         this._left.translate(classGen, methodGen);
/*     */       } 
/*     */     }
/*     */     
/*  99 */     int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
/*     */ 
/*     */     
/* 102 */     int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*     */ 
/*     */ 
/*     */     
/* 106 */     InstructionHandle begin = il.append(methodGen.loadDOM());
/* 107 */     il.append((Instruction)InstructionConstants.SWAP);
/* 108 */     il.append((Instruction)new INVOKEINTERFACE(getParent, 2));
/* 109 */     if (this._left instanceof AncestorPattern) {
/* 110 */       il.append(methodGen.loadDOM());
/* 111 */       il.append((Instruction)InstructionConstants.SWAP);
/*     */     } 
/* 113 */     il.append((Instruction)new INVOKEINTERFACE(getType, 2));
/* 114 */     il.append((CompoundInstruction)new PUSH(cpg, 9));
/*     */     
/* 116 */     BranchHandle skip = il.append((BranchInstruction)new IF_ICMPEQ(null));
/* 117 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO_W(null)));
/* 118 */     skip.setTarget(il.append(InstructionConstants.NOP));
/*     */     
/* 120 */     if (this._left != null) {
/* 121 */       this._left.backPatchTrueList(begin);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       if (this._left instanceof AncestorPattern) {
/* 128 */         AncestorPattern ancestor = (AncestorPattern)this._left;
/* 129 */         this._falseList.backPatch(ancestor.getLoopHandle());
/*     */       } 
/* 131 */       this._falseList.append(this._left._falseList);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 136 */     return "absolutePathPattern(" + ((this._left != null) ? this._left.toString() : ")");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AbsolutePathPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */