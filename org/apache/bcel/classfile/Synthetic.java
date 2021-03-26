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
/*     */ public final class Synthetic
/*     */   extends Attribute
/*     */ {
/*     */   private byte[] bytes;
/*     */   
/*     */   public Synthetic(Synthetic c) {
/*  77 */     this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
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
/*     */   public Synthetic(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
/*  90 */     super((byte)7, name_index, length, constant_pool);
/*  91 */     this.bytes = bytes;
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
/*     */   Synthetic(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 105 */     this(name_index, length, (byte[])null, constant_pool);
/*     */     
/* 107 */     if (length > 0) {
/* 108 */       this.bytes = new byte[length];
/* 109 */       file.readFully(this.bytes);
/* 110 */       System.err.println("Synthetic attribute with length > 0");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 121 */     v.visitSynthetic(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 131 */     super.dump(file);
/* 132 */     if (this.length > 0) {
/* 133 */       file.write(this.bytes, 0, this.length);
/*     */     }
/*     */   }
/*     */   
/*     */   public final byte[] getBytes() {
/* 138 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setBytes(byte[] bytes) {
/* 144 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 151 */     StringBuffer buf = new StringBuffer("Synthetic");
/*     */     
/* 153 */     if (this.length > 0) {
/* 154 */       buf.append(" " + Utility.toHexString(this.bytes));
/*     */     }
/* 156 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 163 */     Synthetic c = (Synthetic)clone();
/*     */     
/* 165 */     if (this.bytes != null) {
/* 166 */       c.bytes = (byte[])this.bytes.clone();
/*     */     }
/* 168 */     c.constant_pool = constant_pool;
/* 169 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Synthetic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */