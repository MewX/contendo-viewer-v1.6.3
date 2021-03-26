/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFNONNULL;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.FlowList;
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
/*     */ public class StringType
/*     */   extends Type
/*     */ {
/*     */   public String toString() {
/*  45 */     return "string";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  49 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  53 */     return "Ljava/lang/String;";
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  61 */     return (Type)Type.STRING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/*  73 */     if (type == Type.Boolean) {
/*  74 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  76 */     else if (type == Type.Real) {
/*  77 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  79 */     else if (type == Type.Reference) {
/*  80 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     }
/*  82 */     else if (type != Type.ObjectString) {
/*     */ 
/*     */ 
/*     */       
/*  86 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/*  88 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/*  99 */     InstructionList il = methodGen.getInstructionList();
/* 100 */     FlowList falsel = translateToDesynthesized(classGen, methodGen, type);
/* 101 */     il.append(InstructionConstants.ICONST_1);
/* 102 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 103 */     falsel.backPatch(il.append(InstructionConstants.ICONST_0));
/* 104 */     truec.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, RealType type) {
/* 115 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 116 */     InstructionList il = methodGen.getInstructionList();
/* 117 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "stringToReal", "(Ljava/lang/String;)D")));
/*     */   }
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
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 132 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 133 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 135 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "length", "()I")));
/*     */     
/* 137 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ReferenceType type) {
/* 148 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 160 */     if (clazz.isAssignableFrom(String.class)) {
/* 161 */       methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */     } else {
/*     */       
/* 164 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 166 */       classGen.getParser().reportError(2, err);
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
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 178 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 179 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 181 */     if (clazz.getName().equals("java.lang.String")) {
/*     */       
/* 183 */       il.append((Instruction)InstructionConstants.DUP);
/* 184 */       BranchHandle ifNonNull = il.append((BranchInstruction)new IFNONNULL(null));
/* 185 */       il.append((Instruction)InstructionConstants.POP);
/* 186 */       il.append((CompoundInstruction)new PUSH(cpg, ""));
/* 187 */       ifNonNull.setTarget(il.append(InstructionConstants.NOP));
/*     */     } else {
/*     */       
/* 190 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 192 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 201 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 209 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 216 */     return "java.lang.String";
/*     */   }
/*     */ 
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 221 */     return (Instruction)new ALOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 225 */     return (Instruction)new ASTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/StringType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */