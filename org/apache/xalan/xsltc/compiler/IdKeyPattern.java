/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFNE;
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
/*     */ abstract class IdKeyPattern
/*     */   extends LocationPathPattern
/*     */ {
/*  40 */   protected RelativePathPattern _left = null;
/*  41 */   private String _index = null;
/*  42 */   private String _value = null;
/*     */   
/*     */   public IdKeyPattern(String index, String value) {
/*  45 */     this._index = index;
/*  46 */     this._value = value;
/*     */   }
/*     */   
/*     */   public String getIndexName() {
/*  50 */     return this._index;
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  54 */     return Type.NodeSet;
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public void setLeft(RelativePathPattern left) {
/*  62 */     this._left = left;
/*     */   }
/*     */   
/*     */   public StepPattern getKernelPattern() {
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public void reduceKernelPattern() {}
/*     */   
/*     */   public String toString() {
/*  72 */     return "id/keyPattern(" + this._index + ", " + this._value + ')';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  82 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  83 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  86 */     int getKeyIndex = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getKeyIndex", "(Ljava/lang/String;)Lorg/apache/xalan/xsltc/dom/KeyIndex;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     int lookupId = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "containsID", "(ILjava/lang/Object;)I");
/*     */ 
/*     */     
/*  95 */     int lookupKey = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "containsKey", "(ILjava/lang/Object;)I");
/*     */ 
/*     */     
/*  98 */     int getNodeIdent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeIdent", "(I)I");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     il.append(classGen.loadTranslet());
/* 105 */     il.append((CompoundInstruction)new PUSH(cpg, this._index));
/* 106 */     il.append((Instruction)new INVOKEVIRTUAL(getKeyIndex));
/*     */ 
/*     */ 
/*     */     
/* 110 */     il.append((Instruction)InstructionConstants.SWAP);
/* 111 */     il.append((CompoundInstruction)new PUSH(cpg, this._value));
/* 112 */     if (this instanceof IdPattern) {
/*     */       
/* 114 */       il.append((Instruction)InstructionConstants.SWAP);
/* 115 */       il.append(methodGen.loadDOM());
/* 116 */       il.append((Instruction)InstructionConstants.SWAP);
/* 117 */       il.append((Instruction)new INVOKEINTERFACE(getNodeIdent, 2));
/* 118 */       il.append((Instruction)InstructionConstants.SWAP);
/* 119 */       il.append((Instruction)new INVOKEVIRTUAL(lookupId));
/*     */     }
/*     */     else {
/*     */       
/* 123 */       il.append((Instruction)InstructionConstants.SWAP);
/* 124 */       il.append(methodGen.loadDOM());
/* 125 */       il.append((Instruction)InstructionConstants.SWAP);
/* 126 */       il.append((Instruction)new INVOKEINTERFACE(getNodeIdent, 2));
/* 127 */       il.append((Instruction)InstructionConstants.SWAP);
/* 128 */       il.append((Instruction)new INVOKEVIRTUAL(lookupKey));
/*     */     } 
/*     */     
/* 131 */     this._trueList.add((InstructionHandle)il.append((BranchInstruction)new IFNE(null)));
/* 132 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new GOTO(null)));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/IdKeyPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */