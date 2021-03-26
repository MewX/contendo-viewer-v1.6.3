/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.DLOAD;
/*     */ import org.apache.bcel.generic.DSTORE;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFNE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
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
/*     */ public final class RealType
/*     */   extends NumberType
/*     */ {
/*     */   public String toString() {
/*  49 */     return "real";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  53 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  57 */     return "D";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  61 */     return (Type)Type.DOUBLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int distanceTo(Type type) {
/*  68 */     if (type == this) {
/*  69 */       return 0;
/*     */     }
/*  71 */     if (type == Type.Int) {
/*  72 */       return 1;
/*     */     }
/*     */     
/*  75 */     return Integer.MAX_VALUE;
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
/*  87 */     if (type == Type.String) {
/*  88 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  90 */     else if (type == Type.Boolean) {
/*  91 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  93 */     else if (type == Type.Reference) {
/*  94 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     }
/*  96 */     else if (type == Type.Int) {
/*  97 */       translateTo(classGen, methodGen, (IntType)type);
/*     */     } else {
/*     */       
/* 100 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/* 102 */       classGen.getParser().reportError(2, err);
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 114 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 115 */     InstructionList il = methodGen.getInstructionList();
/* 116 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "realToString", "(D)Ljava/lang/String;")));
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
/* 129 */     InstructionList il = methodGen.getInstructionList();
/* 130 */     FlowList falsel = translateToDesynthesized(classGen, methodGen, type);
/* 131 */     il.append(InstructionConstants.ICONST_1);
/* 132 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 133 */     falsel.backPatch(il.append(InstructionConstants.ICONST_0));
/* 134 */     truec.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, IntType type) {
/* 144 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 145 */     InstructionList il = methodGen.getInstructionList();
/* 146 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "realToInt", "(D)I")));
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
/* 161 */     FlowList flowlist = new FlowList();
/* 162 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 163 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 166 */     il.append((Instruction)InstructionConstants.DUP2);
/* 167 */     LocalVariableGen local = methodGen.addLocalVariable("real_to_boolean_tmp", (Type)Type.DOUBLE, il.getEnd(), null);
/*     */ 
/*     */     
/* 170 */     il.append((Instruction)new DSTORE(local.getIndex()));
/*     */ 
/*     */     
/* 173 */     il.append(InstructionConstants.DCONST_0);
/* 174 */     il.append(InstructionConstants.DCMPG);
/* 175 */     flowlist.add((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*     */ 
/*     */ 
/*     */     
/* 179 */     il.append((Instruction)new DLOAD(local.getIndex()));
/* 180 */     il.append((Instruction)new DLOAD(local.getIndex()));
/* 181 */     il.append(InstructionConstants.DCMPG);
/* 182 */     flowlist.add((InstructionHandle)il.append((BranchInstruction)new IFNE(null)));
/* 183 */     return flowlist;
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
/* 194 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 195 */     InstructionList il = methodGen.getInstructionList();
/* 196 */     il.append((Instruction)new NEW(cpg.addClass("java.lang.Double")));
/* 197 */     il.append((Instruction)InstructionConstants.DUP_X2);
/* 198 */     il.append((Instruction)InstructionConstants.DUP_X2);
/* 199 */     il.append((Instruction)InstructionConstants.POP);
/* 200 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("java.lang.Double", "<init>", "(D)V")));
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
/* 211 */     InstructionList il = methodGen.getInstructionList();
/* 212 */     if (clazz == char.class) {
/* 213 */       il.append((Instruction)InstructionConstants.D2I);
/* 214 */       il.append((Instruction)InstructionConstants.I2C);
/*     */     }
/* 216 */     else if (clazz == byte.class) {
/* 217 */       il.append((Instruction)InstructionConstants.D2I);
/* 218 */       il.append((Instruction)InstructionConstants.I2B);
/*     */     }
/* 220 */     else if (clazz == short.class) {
/* 221 */       il.append((Instruction)InstructionConstants.D2I);
/* 222 */       il.append((Instruction)InstructionConstants.I2S);
/*     */     }
/* 224 */     else if (clazz == int.class) {
/* 225 */       il.append((Instruction)InstructionConstants.D2I);
/*     */     }
/* 227 */     else if (clazz == long.class) {
/* 228 */       il.append((Instruction)InstructionConstants.D2L);
/*     */     }
/* 230 */     else if (clazz == float.class) {
/* 231 */       il.append((Instruction)InstructionConstants.D2F);
/*     */     }
/* 233 */     else if (clazz == double.class) {
/* 234 */       il.append(InstructionConstants.NOP);
/*     */     
/*     */     }
/* 237 */     else if (clazz.isAssignableFrom(Double.class)) {
/* 238 */       translateTo(classGen, methodGen, Type.Reference);
/*     */     } else {
/*     */       
/* 241 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 243 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 253 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 255 */     if (clazz == char.class || clazz == byte.class || clazz == short.class || clazz == int.class) {
/*     */       
/* 257 */       il.append((Instruction)InstructionConstants.I2D);
/*     */     }
/* 259 */     else if (clazz == long.class) {
/* 260 */       il.append((Instruction)InstructionConstants.L2D);
/*     */     }
/* 262 */     else if (clazz == float.class) {
/* 263 */       il.append((Instruction)InstructionConstants.F2D);
/*     */     }
/* 265 */     else if (clazz == double.class) {
/* 266 */       il.append(InstructionConstants.NOP);
/*     */     } else {
/*     */       
/* 269 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 271 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 280 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 288 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 289 */     InstructionList il = methodGen.getInstructionList();
/* 290 */     il.append((Instruction)new CHECKCAST(cpg.addClass("java.lang.Double")));
/* 291 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.Double", "doubleValue", "()D")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction ADD() {
/* 297 */     return (Instruction)InstructionConstants.DADD;
/*     */   }
/*     */   
/*     */   public Instruction SUB() {
/* 301 */     return (Instruction)InstructionConstants.DSUB;
/*     */   }
/*     */   
/*     */   public Instruction MUL() {
/* 305 */     return (Instruction)InstructionConstants.DMUL;
/*     */   }
/*     */   
/*     */   public Instruction DIV() {
/* 309 */     return (Instruction)InstructionConstants.DDIV;
/*     */   }
/*     */   
/*     */   public Instruction REM() {
/* 313 */     return (Instruction)InstructionConstants.DREM;
/*     */   }
/*     */   
/*     */   public Instruction NEG() {
/* 317 */     return (Instruction)InstructionConstants.DNEG;
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 321 */     return (Instruction)new DLOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 325 */     return (Instruction)new DSTORE(slot);
/*     */   }
/*     */   
/*     */   public Instruction POP() {
/* 329 */     return (Instruction)InstructionConstants.POP2;
/*     */   }
/*     */   
/*     */   public Instruction CMP(boolean less) {
/* 333 */     return less ? InstructionConstants.DCMPG : InstructionConstants.DCMPL;
/*     */   }
/*     */   
/*     */   public Instruction DUP() {
/* 337 */     return (Instruction)InstructionConstants.DUP2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/RealType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */