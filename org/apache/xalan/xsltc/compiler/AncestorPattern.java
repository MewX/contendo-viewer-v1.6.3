/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFLT;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
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
/*     */ 
/*     */ final class AncestorPattern
/*     */   extends RelativePathPattern
/*     */ {
/*     */   private final Pattern _left;
/*     */   private final RelativePathPattern _right;
/*     */   private InstructionHandle _loop;
/*     */   
/*     */   public AncestorPattern(RelativePathPattern right) {
/*  50 */     this(null, right);
/*     */   }
/*     */   
/*     */   public AncestorPattern(Pattern left, RelativePathPattern right) {
/*  54 */     this._left = left;
/*  55 */     (this._right = right).setParent(this);
/*  56 */     if (left != null) {
/*  57 */       left.setParent(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public InstructionHandle getLoopHandle() {
/*  62 */     return this._loop;
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  66 */     super.setParser(parser);
/*  67 */     if (this._left != null) {
/*  68 */       this._left.setParser(parser);
/*     */     }
/*  70 */     this._right.setParser(parser);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWildcard() {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public StepPattern getKernelPattern() {
/*  79 */     return this._right.getKernelPattern();
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {
/*  83 */     this._right.reduceKernelPattern();
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  87 */     if (this._left != null) {
/*  88 */       this._left.typeCheck(stable);
/*     */     }
/*  90 */     return this._right.typeCheck(stable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  95 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  96 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     LocalVariableGen local = methodGen.addLocalVariable2("app", Util.getJCRefType("I"), il.getEnd());
/*     */ 
/*     */ 
/*     */     
/* 106 */     ILOAD iLOAD = new ILOAD(local.getIndex());
/*     */     
/* 108 */     ISTORE iSTORE = new ISTORE(local.getIndex());
/*     */ 
/*     */     
/* 111 */     if (this._right instanceof StepPattern) {
/* 112 */       il.append((Instruction)InstructionConstants.DUP);
/* 113 */       il.append((Instruction)iSTORE);
/* 114 */       this._right.translate(classGen, methodGen);
/* 115 */       il.append(methodGen.loadDOM());
/* 116 */       il.append((Instruction)iLOAD);
/*     */     } else {
/*     */       
/* 119 */       this._right.translate(classGen, methodGen);
/*     */       
/* 121 */       if (this._right instanceof AncestorPattern) {
/* 122 */         il.append(methodGen.loadDOM());
/* 123 */         il.append((Instruction)InstructionConstants.SWAP);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     if (this._left != null) {
/* 128 */       int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
/*     */ 
/*     */       
/* 131 */       InstructionHandle parent = il.append((Instruction)new INVOKEINTERFACE(getParent, 2));
/*     */       
/* 133 */       il.append((Instruction)InstructionConstants.DUP);
/* 134 */       il.append((Instruction)iSTORE);
/* 135 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFLT(null)));
/* 136 */       il.append((Instruction)iLOAD);
/*     */       
/* 138 */       this._left.translate(classGen, methodGen);
/*     */       
/* 140 */       SyntaxTreeNode p = getParent();
/* 141 */       if (p != null && !(p instanceof Instruction) && !(p instanceof TopLevelElement))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 147 */         il.append((Instruction)iLOAD);
/*     */       }
/*     */       
/* 150 */       BranchHandle exit = il.append((BranchInstruction)new GOTO(null));
/* 151 */       this._loop = il.append(methodGen.loadDOM());
/* 152 */       il.append((Instruction)iLOAD);
/* 153 */       local.setEnd(this._loop);
/* 154 */       il.append((BranchInstruction)new GOTO(parent));
/* 155 */       exit.setTarget(il.append(InstructionConstants.NOP));
/* 156 */       this._left.backPatchFalseList(this._loop);
/*     */       
/* 158 */       this._trueList.append(this._left._trueList);
/*     */     } else {
/*     */       
/* 161 */       il.append((Instruction)InstructionConstants.POP2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (this._right instanceof AncestorPattern) {
/* 169 */       AncestorPattern ancestor = (AncestorPattern)this._right;
/* 170 */       this._falseList.backPatch(ancestor.getLoopHandle());
/*     */     } 
/*     */     
/* 173 */     this._trueList.append(this._right._trueList);
/* 174 */     this._falseList.append(this._right._falseList);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     return "AncestorPattern(" + this._left + ", " + this._right + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AncestorPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */