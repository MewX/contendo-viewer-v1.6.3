/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
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
/*     */ public final class ConstantNameAndType
/*     */   extends Constant
/*     */ {
/*     */   private int name_index;
/*     */   private int signature_index;
/*     */   
/*     */   public ConstantNameAndType(ConstantNameAndType c) {
/*  78 */     this(c.getNameIndex(), c.getSignatureIndex());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConstantNameAndType(DataInputStream file) throws IOException {
/*  89 */     this(file.readUnsignedShort(), file.readUnsignedShort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantNameAndType(int name_index, int signature_index) {
/*  99 */     super((byte)12);
/* 100 */     this.name_index = name_index;
/* 101 */     this.signature_index = signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 112 */     v.visitConstantNameAndType(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 123 */     file.writeByte(this.tag);
/* 124 */     file.writeShort(this.name_index);
/* 125 */     file.writeShort(this.signature_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNameIndex() {
/* 131 */     return this.name_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getName(ConstantPool cp) {
/* 136 */     return cp.constantToString(getNameIndex(), (byte)1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getSignatureIndex() {
/* 142 */     return this.signature_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getSignature(ConstantPool cp) {
/* 147 */     return cp.constantToString(getSignatureIndex(), (byte)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameIndex(int name_index) {
/* 154 */     this.name_index = name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSignatureIndex(int signature_index) {
/* 161 */     this.signature_index = signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 168 */     return super.toString() + "(name_index = " + this.name_index + ", signature_index = " + this.signature_index + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantNameAndType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */