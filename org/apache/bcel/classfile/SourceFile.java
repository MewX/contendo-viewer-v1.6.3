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
/*     */ public final class SourceFile
/*     */   extends Attribute
/*     */ {
/*     */   private int sourcefile_index;
/*     */   
/*     */   public SourceFile(SourceFile c) {
/*  77 */     this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
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
/*     */   
/*     */   SourceFile(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  92 */     this(name_index, length, file.readUnsignedShort(), constant_pool);
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
/*     */   public SourceFile(int name_index, int length, int sourcefile_index, ConstantPool constant_pool) {
/* 104 */     super((byte)0, name_index, length, constant_pool);
/* 105 */     this.sourcefile_index = sourcefile_index;
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
/* 116 */     v.visitSourceFile(this);
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
/* 127 */     super.dump(file);
/* 128 */     file.writeShort(this.sourcefile_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getSourceFileIndex() {
/* 134 */     return this.sourcefile_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSourceFileIndex(int sourcefile_index) {
/* 140 */     this.sourcefile_index = sourcefile_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getSourceFileName() {
/* 147 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.sourcefile_index, (byte)1);
/*     */     
/* 149 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 156 */     return "SourceFile(" + getSourceFileName() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 163 */     return (SourceFile)clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/SourceFile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */