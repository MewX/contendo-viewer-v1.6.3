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
/*     */ public final class ConstantString
/*     */   extends Constant
/*     */   implements ConstantObject
/*     */ {
/*     */   private int string_index;
/*     */   
/*     */   public ConstantString(ConstantString c) {
/*  76 */     this(c.getStringIndex());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConstantString(DataInputStream file) throws IOException {
/*  86 */     this(file.readUnsignedShort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantString(int string_index) {
/*  93 */     super((byte)8);
/*  94 */     this.string_index = string_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 104 */     v.visitConstantString(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 114 */     file.writeByte(this.tag);
/* 115 */     file.writeShort(this.string_index);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getStringIndex() {
/* 120 */     return this.string_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setStringIndex(int string_index) {
/* 125 */     this.string_index = string_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 132 */     return super.toString() + "(string_index = " + this.string_index + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConstantValue(ConstantPool cp) {
/* 138 */     Constant c = cp.getConstant(this.string_index, (byte)1);
/* 139 */     return ((ConstantUtf8)c).getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBytes(ConstantPool cp) {
/* 145 */     return (String)getConstantValue(cp);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantString.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */