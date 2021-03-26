/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.classfile.ConstantCP;
/*     */ import org.apache.bcel.classfile.ConstantNameAndType;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
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
/*     */ public abstract class FieldOrMethod
/*     */   extends CPInstruction
/*     */   implements LoadClass
/*     */ {
/*     */   FieldOrMethod() {}
/*     */   
/*     */   protected FieldOrMethod(short opcode, int index) {
/*  76 */     super(opcode, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignature(ConstantPoolGen cpg) {
/*  82 */     ConstantPool cp = cpg.getConstantPool();
/*  83 */     ConstantCP cmr = (ConstantCP)cp.getConstant(this.index);
/*  84 */     ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());
/*     */     
/*  86 */     return ((ConstantUtf8)cp.getConstant(cnat.getSignatureIndex())).getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(ConstantPoolGen cpg) {
/*  92 */     ConstantPool cp = cpg.getConstantPool();
/*  93 */     ConstantCP cmr = (ConstantCP)cp.getConstant(this.index);
/*  94 */     ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());
/*  95 */     return ((ConstantUtf8)cp.getConstant(cnat.getNameIndex())).getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName(ConstantPoolGen cpg) {
/* 101 */     ConstantPool cp = cpg.getConstantPool();
/* 102 */     ConstantCP cmr = (ConstantCP)cp.getConstant(this.index);
/* 103 */     return cp.getConstantString(cmr.getClassIndex(), (byte)7).replace('/', '.');
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectType getClassType(ConstantPoolGen cpg) {
/* 109 */     return new ObjectType(getClassName(cpg));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/* 115 */     return getClassType(cpg);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/FieldOrMethod.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */