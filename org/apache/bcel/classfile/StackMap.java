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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StackMap
/*     */   extends Attribute
/*     */   implements Node
/*     */ {
/*     */   private int map_length;
/*     */   private StackMapEntry[] map;
/*     */   
/*     */   public StackMap(int name_index, int length, StackMapEntry[] map, ConstantPool constant_pool) {
/*  88 */     super((byte)11, name_index, length, constant_pool);
/*     */     
/*  90 */     setStackMap(map);
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
/*     */   StackMap(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 104 */     this(name_index, length, (StackMapEntry[])null, constant_pool);
/*     */     
/* 106 */     this.map_length = file.readUnsignedShort();
/* 107 */     this.map = new StackMapEntry[this.map_length];
/*     */     
/* 109 */     for (int i = 0; i < this.map_length; i++) {
/* 110 */       this.map[i] = new StackMapEntry(file, constant_pool);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 121 */     super.dump(file);
/* 122 */     file.writeShort(this.map_length);
/* 123 */     for (int i = 0; i < this.map_length; i++) {
/* 124 */       this.map[i].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final StackMapEntry[] getStackMap() {
/* 130 */     return this.map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStackMap(StackMapEntry[] map) {
/* 136 */     this.map = map;
/*     */     
/* 138 */     this.map_length = (map == null) ? 0 : map.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 145 */     StringBuffer buf = new StringBuffer("StackMap(");
/*     */     
/* 147 */     for (int i = 0; i < this.map_length; i++) {
/* 148 */       buf.append(this.map[i].toString());
/*     */       
/* 150 */       if (i < this.map_length - 1) {
/* 151 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 154 */     buf.append(')');
/*     */     
/* 156 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 163 */     StackMap c = (StackMap)clone();
/*     */     
/* 165 */     c.map = new StackMapEntry[this.map_length];
/* 166 */     for (int i = 0; i < this.map_length; i++) {
/* 167 */       c.map[i] = this.map[i].copy();
/*     */     }
/* 169 */     c.constant_pool = constant_pool;
/* 170 */     return c;
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
/* 181 */     v.visitStackMap(this);
/*     */   }
/*     */   public final int getMapLength() {
/* 184 */     return this.map_length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/StackMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */