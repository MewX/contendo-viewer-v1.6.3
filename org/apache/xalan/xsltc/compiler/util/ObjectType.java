/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFNULL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ public final class ObjectType
/*     */   extends Type
/*     */ {
/*  40 */   private String _javaClassName = "java.lang.Object";
/*  41 */   private Class _clazz = Object.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ObjectType(String javaClassName) {
/*  49 */     this._javaClassName = javaClassName;
/*     */ 
/*     */     
/*  52 */     try { this._clazz = ObjectFactory.findProviderClass(javaClassName, ObjectFactory.findClassLoader(), true); } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/*     */       
/*  56 */       this._clazz = null; }
/*     */   
/*     */   }
/*     */   
/*     */   protected ObjectType(Class clazz) {
/*  61 */     this._clazz = clazz;
/*  62 */     this._javaClassName = clazz.getName();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  66 */     return toString().hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  70 */     return obj instanceof ObjectType;
/*     */   }
/*     */   
/*     */   public String getJavaClassName() {
/*  74 */     return this._javaClassName;
/*     */   }
/*     */   
/*     */   public Class getJavaClass() {
/*  78 */     return this._clazz;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  82 */     return this._javaClassName;
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  86 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  90 */     StringBuffer result = new StringBuffer("L");
/*  91 */     result.append(this._javaClassName.replace('.', '/')).append(';');
/*  92 */     return result.toString();
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  96 */     return Util.getJCRefType(toSignature());
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
/* 108 */     if (type == Type.String) {
/* 109 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     } else {
/*     */       
/* 112 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/* 114 */       classGen.getParser().reportError(2, err);
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
/* 126 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 127 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 129 */     il.append((Instruction)InstructionConstants.DUP);
/* 130 */     BranchHandle ifNull = il.append((BranchInstruction)new IFNULL(null));
/* 131 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref(this._javaClassName, "toString", "()Ljava/lang/String;")));
/*     */ 
/*     */     
/* 134 */     BranchHandle gotobh = il.append((BranchInstruction)new GOTO(null));
/* 135 */     ifNull.setTarget(il.append((Instruction)InstructionConstants.POP));
/* 136 */     il.append((CompoundInstruction)new PUSH(cpg, ""));
/* 137 */     gotobh.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 147 */     if (clazz.isAssignableFrom(this._clazz)) {
/* 148 */       methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */     } else {
/* 150 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getClass().toString());
/*     */       
/* 152 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 161 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 165 */     return (Instruction)new ALOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 169 */     return (Instruction)new ASTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/ObjectType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */