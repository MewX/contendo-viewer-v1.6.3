/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.xalan.xsltc.compiler.Constants;
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
/*     */ public abstract class Type
/*     */   implements Constants
/*     */ {
/*  34 */   public static final Type Int = new IntType();
/*  35 */   public static final Type Real = new RealType();
/*  36 */   public static final Type Boolean = new BooleanType();
/*  37 */   public static final Type NodeSet = new NodeSetType();
/*  38 */   public static final Type String = new StringType();
/*  39 */   public static final Type ResultTree = new ResultTreeType();
/*  40 */   public static final Type Reference = new ReferenceType();
/*  41 */   public static final Type Void = new VoidType();
/*     */   
/*  43 */   public static final Type Object = new ObjectType(Object.class);
/*  44 */   public static final Type ObjectString = new ObjectType(String.class);
/*     */   
/*  46 */   public static final Type Node = new NodeType(-1);
/*  47 */   public static final Type Root = new NodeType(9);
/*  48 */   public static final Type Element = new NodeType(1);
/*  49 */   public static final Type Attribute = new NodeType(2);
/*  50 */   public static final Type Text = new NodeType(3);
/*  51 */   public static final Type Comment = new NodeType(8);
/*  52 */   public static final Type Processing_Instruction = new NodeType(7);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type newObjectType(String javaClassName) {
/*  59 */     if (javaClassName == "java.lang.Object") {
/*  60 */       return Object;
/*     */     }
/*  62 */     if (javaClassName == "java.lang.String") {
/*  63 */       return ObjectString;
/*     */     }
/*     */     
/*  66 */     return new ObjectType(javaClassName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type newObjectType(Class clazz) {
/*  75 */     if (clazz == Object.class) {
/*  76 */       return Object;
/*     */     }
/*  78 */     if (clazz == String.class) {
/*  79 */       return ObjectString;
/*     */     }
/*     */     
/*  82 */     return new ObjectType(clazz);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNumber() {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean implementedAsMethod() {
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSimple() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int distanceTo(Type type) {
/* 127 */     return (type == this) ? 0 : Integer.MAX_VALUE;
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/* 142 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */     
/* 144 */     classGen.getParser().reportError(2, err);
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
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/* 156 */     FlowList fl = null;
/* 157 */     if (type == Boolean) {
/* 158 */       fl = translateToDesynthesized(classGen, methodGen, (BooleanType)type);
/*     */     }
/*     */     else {
/*     */       
/* 162 */       translateTo(classGen, methodGen, type);
/*     */     } 
/* 164 */     return fl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 175 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */     
/* 177 */     classGen.getParser().reportError(2, err);
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 188 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getClass().toString());
/*     */     
/* 190 */     classGen.getParser().reportError(2, err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 200 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", clazz.getClass().toString(), toString());
/*     */     
/* 202 */     classGen.getParser().reportError(2, err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 210 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), "[" + toString() + "]");
/*     */     
/* 212 */     classGen.getParser().reportError(2, err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 220 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", "[" + toString() + "]", toString());
/*     */     
/* 222 */     classGen.getParser().reportError(2, err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 229 */     return "";
/*     */   }
/*     */   
/*     */   public Instruction ADD() {
/* 233 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction SUB() {
/* 237 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction MUL() {
/* 241 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction DIV() {
/* 245 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction REM() {
/* 249 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction NEG() {
/* 253 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 257 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 261 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction POP() {
/* 265 */     return (Instruction)InstructionConstants.POP;
/*     */   }
/*     */   
/*     */   public BranchInstruction GT(boolean tozero) {
/* 269 */     return null;
/*     */   }
/*     */   
/*     */   public BranchInstruction GE(boolean tozero) {
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   public BranchInstruction LT(boolean tozero) {
/* 277 */     return null;
/*     */   }
/*     */   
/*     */   public BranchInstruction LE(boolean tozero) {
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction CMP(boolean less) {
/* 285 */     return null;
/*     */   }
/*     */   
/*     */   public Instruction DUP() {
/* 289 */     return (Instruction)InstructionConstants.DUP;
/*     */   }
/*     */   
/*     */   public abstract String toString();
/*     */   
/*     */   public abstract boolean identicalTo(Type paramType);
/*     */   
/*     */   public abstract org.apache.bcel.generic.Type toJCType();
/*     */   
/*     */   public abstract String toSignature();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/Type.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */