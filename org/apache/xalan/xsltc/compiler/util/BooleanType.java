/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
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
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
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
/*     */ public final class BooleanType
/*     */   extends Type
/*     */ {
/*     */   public String toString() {
/*  54 */     return "boolean";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  58 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  62 */     return "Z";
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/*  66 */     return true;
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  70 */     return (Type)Type.BOOLEAN;
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
/*  82 */     if (type == Type.String) {
/*  83 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  85 */     else if (type == Type.Real) {
/*  86 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  88 */     else if (type == Type.Reference) {
/*  89 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     } else {
/*     */       
/*  92 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/*  94 */       classGen.getParser().reportError(2, err);
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
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 107 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 108 */     InstructionList il = methodGen.getInstructionList();
/* 109 */     BranchHandle falsec = il.append((BranchInstruction)new IFEQ(null));
/* 110 */     il.append((CompoundInstruction)new PUSH(cpg, "true"));
/* 111 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 112 */     falsec.setTarget(il.append((CompoundInstruction)new PUSH(cpg, "false")));
/* 113 */     truec.setTarget(il.append(InstructionConstants.NOP));
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
/* 124 */     methodGen.getInstructionList().append((Instruction)InstructionConstants.I2D);
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
/* 136 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 137 */     InstructionList il = methodGen.getInstructionList();
/* 138 */     il.append((Instruction)new NEW(cpg.addClass("java.lang.Boolean")));
/* 139 */     il.append((Instruction)InstructionConstants.DUP_X1);
/* 140 */     il.append((Instruction)InstructionConstants.SWAP);
/* 141 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("java.lang.Boolean", "<init>", "(Z)V")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 151 */     if (clazz == boolean.class) {
/* 152 */       methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */     
/*     */     }
/* 155 */     else if (clazz.isAssignableFrom(Boolean.class)) {
/* 156 */       translateTo(classGen, methodGen, Type.Reference);
/*     */     } else {
/*     */       
/* 159 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 161 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 170 */     translateTo(classGen, methodGen, clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 178 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 186 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 187 */     InstructionList il = methodGen.getInstructionList();
/* 188 */     il.append((Instruction)new CHECKCAST(cpg.addClass("java.lang.Boolean")));
/* 189 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.Boolean", "booleanValue", "()Z")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 195 */     return (Instruction)new ILOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 199 */     return (Instruction)new ISTORE(slot);
/*     */   }
/*     */   
/*     */   public BranchInstruction GT(boolean tozero) {
/* 203 */     return tozero ? (BranchInstruction)new IFGT(null) : (BranchInstruction)new IF_ICMPGT(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction GE(boolean tozero) {
/* 208 */     return tozero ? (BranchInstruction)new IFGE(null) : (BranchInstruction)new IF_ICMPGE(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction LT(boolean tozero) {
/* 213 */     return tozero ? (BranchInstruction)new IFLT(null) : (BranchInstruction)new IF_ICMPLT(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BranchInstruction LE(boolean tozero) {
/* 218 */     return tozero ? (BranchInstruction)new IFLE(null) : (BranchInstruction)new IF_ICMPLE(null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/BooleanType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */