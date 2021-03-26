/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IF_ICMPEQ;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
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
/*     */ final class ProcessingInstructionPattern
/*     */   extends StepPattern
/*     */ {
/*  44 */   private String _name = null;
/*     */ 
/*     */   
/*     */   private boolean _typeChecked = false;
/*     */ 
/*     */   
/*     */   public ProcessingInstructionPattern(String name) {
/*  51 */     super(3, 7, null);
/*  52 */     this._name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDefaultPriority() {
/*  60 */     return (this._name != null) ? 0.0D : -0.5D;
/*     */   }
/*     */   public String toString() {
/*  63 */     if (this._predicates == null) {
/*  64 */       return "processing-instruction(" + this._name + ")";
/*     */     }
/*  66 */     return "processing-instruction(" + this._name + ")" + this._predicates;
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {
/*  70 */     this._typeChecked = true;
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  78 */     if (hasPredicates()) {
/*     */       
/*  80 */       int n = this._predicates.size();
/*  81 */       for (int i = 0; i < n; i++) {
/*  82 */         Predicate pred = this._predicates.elementAt(i);
/*  83 */         pred.typeCheck(stable);
/*     */       } 
/*     */     } 
/*  86 */     return Type.NodeSet;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  90 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  91 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  94 */     int gname = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeName", "(I)Ljava/lang/String;");
/*     */ 
/*     */     
/*  97 */     int cmp = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
/*     */ 
/*     */ 
/*     */     
/* 101 */     il.append(methodGen.loadCurrentNode());
/* 102 */     il.append((Instruction)InstructionConstants.SWAP);
/*     */ 
/*     */     
/* 105 */     il.append(methodGen.storeCurrentNode());
/*     */ 
/*     */     
/* 108 */     if (!this._typeChecked) {
/* 109 */       il.append(methodGen.loadCurrentNode());
/* 110 */       int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*     */ 
/*     */       
/* 113 */       il.append(methodGen.loadDOM());
/* 114 */       il.append(methodGen.loadCurrentNode());
/* 115 */       il.append((Instruction)new INVOKEINTERFACE(getType, 2));
/* 116 */       il.append((CompoundInstruction)new PUSH(cpg, 7));
/* 117 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IF_ICMPEQ(null)));
/*     */     } 
/*     */ 
/*     */     
/* 121 */     il.append((CompoundInstruction)new PUSH(cpg, this._name));
/*     */     
/* 123 */     il.append(methodGen.loadDOM());
/* 124 */     il.append(methodGen.loadCurrentNode());
/* 125 */     il.append((Instruction)new INVOKEINTERFACE(gname, 2));
/*     */     
/* 127 */     il.append((Instruction)new INVOKEVIRTUAL(cmp));
/* 128 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*     */ 
/*     */     
/* 131 */     if (hasPredicates()) {
/* 132 */       int n = this._predicates.size();
/* 133 */       for (int i = 0; i < n; i++) {
/* 134 */         Predicate pred = this._predicates.elementAt(i);
/* 135 */         Expression exp = pred.getExpr();
/* 136 */         exp.translateDesynthesized(classGen, methodGen);
/* 137 */         this._trueList.append(exp._trueList);
/* 138 */         this._falseList.append(exp._falseList);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 144 */     InstructionHandle restore = il.append(methodGen.storeCurrentNode());
/* 145 */     backPatchTrueList(restore);
/* 146 */     BranchHandle skipFalse = il.append((BranchInstruction)new GOTO(null));
/*     */ 
/*     */     
/* 149 */     restore = il.append(methodGen.storeCurrentNode());
/* 150 */     backPatchFalseList(restore);
/* 151 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO(null)));
/*     */ 
/*     */     
/* 154 */     skipFalse.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ProcessingInstructionPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */