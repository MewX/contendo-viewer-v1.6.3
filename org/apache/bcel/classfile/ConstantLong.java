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
/*     */ public final class ConstantLong
/*     */   extends Constant
/*     */   implements ConstantObject
/*     */ {
/*     */   private long bytes;
/*     */   
/*     */   public ConstantLong(long bytes) {
/*  77 */     super((byte)5);
/*  78 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantLong(ConstantLong c) {
/*  84 */     this(c.getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConstantLong(DataInputStream file) throws IOException {
/*  94 */     this(file.readLong());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 104 */     v.visitConstantLong(this);
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
/* 115 */     file.writeLong(this.bytes);
/*     */   }
/*     */ 
/*     */   
/*     */   public final long getBytes() {
/* 120 */     return this.bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setBytes(long bytes) {
/* 125 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 131 */     return super.toString() + "(bytes = " + this.bytes + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConstantValue(ConstantPool cp) {
/* 137 */     return new Long(this.bytes);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantLong.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */