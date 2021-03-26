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
/*     */ public final class StackMapType
/*     */   implements Cloneable
/*     */ {
/*     */   private byte type;
/*  72 */   private int index = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private ConstantPool constant_pool;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   StackMapType(DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  82 */     this(file.readByte(), -1, constant_pool);
/*     */     
/*  84 */     if (hasIndex()) {
/*  85 */       setIndex(file.readShort());
/*     */     }
/*  87 */     setConstantPool(constant_pool);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StackMapType(byte type, int index, ConstantPool constant_pool) {
/*  95 */     setType(type);
/*  96 */     setIndex(index);
/*  97 */     setConstantPool(constant_pool);
/*     */   }
/*     */   
/*     */   public void setType(byte t) {
/* 101 */     if (t < 0 || t > 8)
/* 102 */       throw new RuntimeException("Illegal type for StackMapType: " + t); 
/* 103 */     this.type = t;
/*     */   }
/*     */   
/* 106 */   public byte getType() { return this.type; } public void setIndex(int t) {
/* 107 */     this.index = t;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndex() {
/* 112 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 122 */     file.writeByte(this.type);
/* 123 */     if (hasIndex()) {
/* 124 */       file.writeShort(getIndex());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean hasIndex() {
/* 130 */     return (this.type == 7 || this.type == 8);
/*     */   }
/*     */ 
/*     */   
/*     */   private String printIndex() {
/* 135 */     if (this.type == 7)
/* 136 */       return ", class=" + this.constant_pool.constantToString(this.index, (byte)7); 
/* 137 */     if (this.type == 8) {
/* 138 */       return ", offset=" + this.index;
/*     */     }
/* 140 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 147 */     return "(type=" + Constants.ITEM_NAMES[this.type] + printIndex() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StackMapType copy() {
/*     */     try {
/* 155 */       return (StackMapType)clone();
/* 156 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 158 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final ConstantPool getConstantPool() {
/* 164 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantPool(ConstantPool constant_pool) {
/* 170 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/StackMapType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */