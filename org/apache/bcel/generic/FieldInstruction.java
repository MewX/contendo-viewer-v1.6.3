/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.ConstantPool;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FieldInstruction
/*     */   extends FieldOrMethod
/*     */   implements TypedInstruction
/*     */ {
/*     */   FieldInstruction() {}
/*     */   
/*     */   protected FieldInstruction(short opcode, int index) {
/*  81 */     super(opcode, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/*  88 */     return Constants.OPCODE_NAMES[this.opcode] + " " + cp.constantToString(this.index, (byte)9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getFieldSize(ConstantPoolGen cpg) {
/*  95 */     return getType(cpg).getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cpg) {
/* 101 */     return getFieldType(cpg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getFieldType(ConstantPoolGen cpg) {
/* 107 */     return Type.getType(getSignature(cpg));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldName(ConstantPoolGen cpg) {
/* 113 */     return getName(cpg);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/FieldInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */