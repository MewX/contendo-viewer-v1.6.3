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
/*     */ public final class ConstantClass
/*     */   extends Constant
/*     */   implements ConstantObject
/*     */ {
/*     */   private int name_index;
/*     */   
/*     */   public ConstantClass(ConstantClass c) {
/*  76 */     this(c.getNameIndex());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConstantClass(DataInputStream file) throws IOException {
/*  87 */     this(file.readUnsignedShort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantClass(int name_index) {
/*  94 */     super((byte)7);
/*  95 */     this.name_index = name_index;
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
/* 106 */     v.visitConstantClass(this);
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
/* 117 */     file.writeByte(this.tag);
/* 118 */     file.writeShort(this.name_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNameIndex() {
/* 124 */     return this.name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameIndex(int name_index) {
/* 130 */     this.name_index = name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConstantValue(ConstantPool cp) {
/* 137 */     Constant c = cp.getConstant(this.name_index, (byte)1);
/* 138 */     return ((ConstantUtf8)c).getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBytes(ConstantPool cp) {
/* 144 */     return (String)getConstantValue(cp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 151 */     return super.toString() + "(name_index = " + this.name_index + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantClass.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */