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
/*     */ 
/*     */ 
/*     */ public final class LocalVariable
/*     */   implements Cloneable, Constants, Node
/*     */ {
/*     */   private int start_pc;
/*     */   private int length;
/*     */   private int name_index;
/*     */   private int signature_index;
/*     */   private int index;
/*     */   private ConstantPool constant_pool;
/*     */   
/*     */   public LocalVariable(LocalVariable c) {
/*  84 */     this(c.getStartPC(), c.getLength(), c.getNameIndex(), c.getSignatureIndex(), c.getIndex(), c.getConstantPool());
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
/*     */   LocalVariable(DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  96 */     this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
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
/*     */ 
/*     */   
/*     */   public LocalVariable(int start_pc, int length, int name_index, int signature_index, int index, ConstantPool constant_pool) {
/* 113 */     this.start_pc = start_pc;
/* 114 */     this.length = length;
/* 115 */     this.name_index = name_index;
/* 116 */     this.signature_index = signature_index;
/* 117 */     this.index = index;
/* 118 */     this.constant_pool = constant_pool;
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
/* 129 */     v.visitLocalVariable(this);
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
/* 140 */     file.writeShort(this.start_pc);
/* 141 */     file.writeShort(this.length);
/* 142 */     file.writeShort(this.name_index);
/* 143 */     file.writeShort(this.signature_index);
/* 144 */     file.writeShort(this.index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConstantPool getConstantPool() {
/* 150 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getLength() {
/* 155 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/* 163 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.name_index, (byte)1);
/* 164 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNameIndex() {
/* 170 */     return this.name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getSignature() {
/* 177 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.signature_index, (byte)1);
/*     */     
/* 179 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getSignatureIndex() {
/* 185 */     return this.signature_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 190 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getStartPC() {
/* 195 */     return this.start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantPool(ConstantPool constant_pool) {
/* 201 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLength(int length) {
/* 208 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameIndex(int name_index) {
/* 215 */     this.name_index = name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSignatureIndex(int signature_index) {
/* 222 */     this.signature_index = signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndex(int index) {
/* 228 */     this.index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStartPC(int start_pc) {
/* 234 */     this.start_pc = start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 241 */     String name = getName(), signature = Utility.signatureToString(getSignature());
/*     */     
/* 243 */     return "LocalVariable(start_pc = " + this.start_pc + ", length = " + this.length + ", index = " + this.index + ":" + signature + " " + name + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariable copy() {
/*     */     try {
/* 252 */       return (LocalVariable)clone();
/* 253 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 255 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/LocalVariable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */