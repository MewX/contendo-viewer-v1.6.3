/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.Constants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Deprecated
/*     */   extends Attribute
/*     */ {
/*     */   private byte[] bytes;
/*     */   
/*     */   public Deprecated(Deprecated c) {
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
/*     */   public Deprecated(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
/*  90 */     super((byte)8, name_index, length, constant_pool);
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
/*     */   Deprecated(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 105 */     this(name_index, length, (byte[])null, constant_pool);
/*     */     
/* 107 */     if (length > 0) {
/* 108 */       this.bytes = new byte[length];
/* 109 */       file.readFully(this.bytes);
/* 110 */       System.err.println("Deprecated attribute with length > 0");
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
/*     */   public void accept(Visitor v) {
/* 122 */     v.visitDeprecated(this);
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
/* 133 */     super.dump(file);
/*     */     
/* 135 */     if (this.length > 0) {
/* 136 */       file.write(this.bytes, 0, this.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final byte[] getBytes() {
/* 142 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setBytes(byte[] bytes) {
/* 148 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 155 */     return Constants.ATTRIBUTE_NAMES[8];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 162 */     Deprecated c = (Deprecated)clone();
/*     */     
/* 164 */     if (this.bytes != null) {
/* 165 */       c.bytes = (byte[])this.bytes.clone();
/*     */     }
/* 167 */     c.constant_pool = constant_pool;
/* 168 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Deprecated.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */