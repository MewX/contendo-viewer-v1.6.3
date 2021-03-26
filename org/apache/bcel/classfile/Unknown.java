/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Unknown
/*     */   extends Attribute
/*     */ {
/*     */   private byte[] bytes;
/*     */   private String name;
/*  73 */   private static HashMap unknown_attributes = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   static Unknown[] getUnknownAttributes() {
/*  78 */     Unknown[] unknowns = new Unknown[unknown_attributes.size()];
/*  79 */     Iterator entries = unknown_attributes.values().iterator();
/*     */     
/*  81 */     for (int i = 0; entries.hasNext(); i++) {
/*  82 */       unknowns[i] = entries.next();
/*     */     }
/*  84 */     unknown_attributes.clear();
/*  85 */     return unknowns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Unknown(Unknown c) {
/*  93 */     this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
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
/*     */   public Unknown(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
/* 107 */     super((byte)-1, name_index, length, constant_pool);
/* 108 */     this.bytes = bytes;
/*     */     
/* 110 */     this.name = ((ConstantUtf8)constant_pool.getConstant(name_index, (byte)1)).getBytes();
/*     */     
/* 112 */     unknown_attributes.put(this.name, this);
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
/*     */   Unknown(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 127 */     this(name_index, length, (byte[])null, constant_pool);
/*     */     
/* 129 */     if (length > 0) {
/* 130 */       this.bytes = new byte[length];
/* 131 */       file.readFully(this.bytes);
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
/* 143 */     v.visitUnknown(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 153 */     super.dump(file);
/* 154 */     if (this.length > 0) {
/* 155 */       file.write(this.bytes, 0, this.length);
/*     */     }
/*     */   }
/*     */   
/*     */   public final byte[] getBytes() {
/* 160 */     return this.bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getName() {
/* 165 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setBytes(byte[] bytes) {
/* 171 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/*     */     String str;
/* 178 */     if (this.length == 0 || this.bytes == null) {
/* 179 */       return "(Unknown attribute " + this.name + ")";
/*     */     }
/*     */     
/* 182 */     if (this.length > 10) {
/* 183 */       byte[] tmp = new byte[10];
/* 184 */       System.arraycopy(this.bytes, 0, tmp, 0, 10);
/* 185 */       str = Utility.toHexString(tmp) + "... (truncated)";
/*     */     } else {
/*     */       
/* 188 */       str = Utility.toHexString(this.bytes);
/*     */     } 
/* 190 */     return "(Unknown attribute " + this.name + ": " + str + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 197 */     Unknown c = (Unknown)clone();
/*     */     
/* 199 */     if (this.bytes != null) {
/* 200 */       c.bytes = (byte[])this.bytes.clone();
/*     */     }
/* 202 */     c.constant_pool = constant_pool;
/* 203 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Unknown.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */