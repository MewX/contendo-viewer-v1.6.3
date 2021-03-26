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
/*     */ public abstract class ConstantCP
/*     */   extends Constant
/*     */ {
/*     */   protected int class_index;
/*     */   protected int name_and_type_index;
/*     */   
/*     */   public ConstantCP(ConstantCP c) {
/*  77 */     this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
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
/*     */   ConstantCP(byte tag, DataInputStream file) throws IOException {
/*  89 */     this(tag, file.readUnsignedShort(), file.readUnsignedShort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ConstantCP(byte tag, int class_index, int name_and_type_index) {
/*  98 */     super(tag);
/*  99 */     this.class_index = class_index;
/* 100 */     this.name_and_type_index = name_and_type_index;
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
/* 111 */     file.writeByte(this.tag);
/* 112 */     file.writeShort(this.class_index);
/* 113 */     file.writeShort(this.name_and_type_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getClassIndex() {
/* 119 */     return this.class_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNameAndTypeIndex() {
/* 124 */     return this.name_and_type_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setClassIndex(int class_index) {
/* 130 */     this.class_index = class_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClass(ConstantPool cp) {
/* 137 */     return cp.constantToString(this.class_index, (byte)7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameAndTypeIndex(int name_and_type_index) {
/* 144 */     this.name_and_type_index = name_and_type_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 151 */     return super.toString() + "(class_index = " + this.class_index + ", name_and_type_index = " + this.name_and_type_index + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantCP.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */