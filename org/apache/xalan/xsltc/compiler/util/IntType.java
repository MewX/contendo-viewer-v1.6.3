/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFGE;
/*     */ import org.apache.bcel.generic.IFGT;
/*     */ import org.apache.bcel.generic.IFLE;
/*     */ import org.apache.bcel.generic.IFLT;
/*     */ import org.apache.bcel.generic.IF_ICMPGE;
/*     */ import org.apache.bcel.generic.IF_ICMPGT;
/*     */ import org.apache.bcel.generic.IF_ICMPLE;
/*     */ import org.apache.bcel.generic.IF_ICMPLT;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IntType
/*     */   extends NumberType
/*     */ {
/*     */   public String toString() {
/*  56 */     return "int";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  60 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  64 */     return "I";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  68 */     return (Type)Type.INT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int distanceTo(Type type) {
/*  75 */     if (type == this) {
/*  76 */       return 0;
/*     */     }
/*  78 */     if (type == Type.Real) {
/*  79 */       return 1;
/*     */     }
/*     */     
/*  82 */     return Integer.MAX_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/*  92 */     if (type == Type.Real) {
/*  93 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  95 */     else if (type == Type.String) {
/*  96 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  98 */     else if (type == Type.Boolean) {
/*  99 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/* 101 */     else if (type == Type.Reference) {
/* 102 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     } else {
/*     */       
/* 105 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/* 107 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, RealType type) {
/* 118 */     methodGen.getInstructionList().append((Instruction)InstructionConstants.I2D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 129 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 130 */     InstructionList il = methodGen.getInstructionList();
/* 131 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("java.lang.Integer", "toString", "(I)Ljava/lang/String;")));
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 144 */     InstructionList il = methodGen.getInstructionList();
/* 145 */     BranchHandle falsec = il.append((BranchInstruction)new IFEQ(null));
/* 146 */     il.append(InstructionConstants.ICONST_1);
/* 147 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 148 */     falsec.setTarget(il.append(InstructionConstants.ICONST_0));
/* 149 */     truec.setTarget(il.append(InstructionConstants.NOP));
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
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 162 */     InstructionList il = methodGen.getInstructionList();
/* 163 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ReferenceType type) {
/* 175 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 176 */     InstructionList il = methodGen.getInstructionList();
/* 177 */     il.append((Instruction)new NEW(cpg.addClass("java.lang.Integer")));
/* 178 */     il.append((Instruction)InstructionConstants.DUP_X1);
/* 179 */     il.append((Instruction)InstructionConstants.SWAP);
/* 180 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("java.lang.Integer", "<init>", "(I)V")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 191 */     InstructionList il = methodGen.getInstructionList();
/* 192 */     if (clazz == char.class) {
/* 193 */       il.append((Instruction)InstructionConstants.I2C);
/*     */     }
/* 195 */     else if (clazz == byte.class) {
/* 196 */       il.append((Instruction)InstructionConstants.I2B);
/*     */     }
/* 198 */     else if (clazz == short.class) {
/* 199 */       il.append((Instruction)InstructionConstants.I2S);
/*     */     }
/* 201 */     else if (clazz == int.class) {
/* 202 */       il.append(InstructionConstants.NOP);
/*     */     }
/* 204 */     else if (clazz == long.class) {
/* 205 */       il.append((Instruction)InstructionConstants.I2L);
/*     */     }
/* 207 */     else if (clazz == float.class) {
/* 208 */       il.append((Instruction)InstructionConstants.I2F);
/*     */     }
/* 210 */     else if (clazz == double.class) {
/* 211 */       il.append((Instruction)InstructionConstants.I2D);
/*     */     
/*     */     }
/* 214 */     else if (clazz.isAssignableFrom(Double.class)) {
/* 215 */       il.append((Instruction)InstructionConstants.I2D);
/* 216 */       Type.Real.translateTo(classGen, methodGen, Type.Reference);
/*     */     } else {
/*     */       
/* 219 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 221 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 230 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 238 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 239 */     InstructionList il = methodGen.getInstructionList();
/* 240 */     il.append((Instruction)new CHECKCAST(cpg.addClass("java.lang.Integer")));
/* 241 */     int index = cpg.addMethodref("java.lang.Integer", "intValue", "()I");
/*     */ 
/*     */     
/* 244 */     il.append((Instruction)new INVOKEVIRTUAL(index));
/*     */   }
/*     */   
/*     */   public Instruction ADD() {
/* 248 */     return (Instruction)InstructionConstants.IADD;
/*     */   }
/*     */   
/*     */   public Instruction SUB() {
/* 252 */     return (Instruction)InstructionConstants.ISUB;
/*     */   }
/*     */   
/*     */   public Instruction MUL() {
/* 256 */     return (Instruction)InstructionConstants.IMUL;
/*     */   }
/*     */   
/*     */   public Instruction DIV() {
/* 260 */     return (Instruction)InstructionConstants.IDIV;
/*     */   }
/*     */   
/*     */   public Instruction REM() {
/* 264 */     return (Instruction)InstructionConstants.IREM;
/*     */   }
/*     */   
/*     */   public Instruction NEG() {
/* 268 */     return (Instruction)InstructionConstants.INEG;
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 272 */     return (Instruction)new ILOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 276 */     return (Instruction)new ISTORE(slot);
/*     */   }
/*     */   
/*     */   public BranchInstruction GT(boolean tozero) {
/* 280 */     return tozero ? (BranchInstruction)new IFGT(null) : (BranchInstruction)new IF_ICMPGT(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction GE(boolean tozero) {
/* 285 */     return tozero ? (BranchInstruction)new IFGE(null) : (BranchInstruction)new IF_ICMPGE(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction LT(boolean tozero) {
/* 290 */     return tozero ? (BranchInstruction)new IFLT(null) : (BranchInstruction)new IF_ICMPLT(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction LE(boolean tozero) {
/* 295 */     return tozero ? (BranchInstruction)new IFLE(null) : (BranchInstruction)new IF_ICMPLE(null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/IntType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */