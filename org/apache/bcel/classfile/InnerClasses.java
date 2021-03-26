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
/*     */ public final class InnerClasses
/*     */   extends Attribute
/*     */ {
/*     */   private InnerClass[] inner_classes;
/*     */   private int number_of_classes;
/*     */   
/*     */   public InnerClasses(InnerClasses c) {
/*  79 */     this(c.getNameIndex(), c.getLength(), c.getInnerClasses(), c.getConstantPool());
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
/*     */   public InnerClasses(int name_index, int length, InnerClass[] inner_classes, ConstantPool constant_pool) {
/*  94 */     super((byte)6, name_index, length, constant_pool);
/*  95 */     setInnerClasses(inner_classes);
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
/*     */   InnerClasses(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 110 */     this(name_index, length, (InnerClass[])null, constant_pool);
/*     */     
/* 112 */     this.number_of_classes = file.readUnsignedShort();
/* 113 */     this.inner_classes = new InnerClass[this.number_of_classes];
/*     */     
/* 115 */     for (int i = 0; i < this.number_of_classes; i++) {
/* 116 */       this.inner_classes[i] = new InnerClass(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 126 */     v.visitInnerClasses(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 136 */     super.dump(file);
/* 137 */     file.writeShort(this.number_of_classes);
/*     */     
/* 139 */     for (int i = 0; i < this.number_of_classes; i++) {
/* 140 */       this.inner_classes[i].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final InnerClass[] getInnerClasses() {
/* 146 */     return this.inner_classes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setInnerClasses(InnerClass[] inner_classes) {
/* 152 */     this.inner_classes = inner_classes;
/* 153 */     this.number_of_classes = (inner_classes == null) ? 0 : inner_classes.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 160 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 162 */     for (int i = 0; i < this.number_of_classes; i++) {
/* 163 */       buf.append(this.inner_classes[i].toString(this.constant_pool) + "\n");
/*     */     }
/* 165 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 172 */     InnerClasses c = (InnerClasses)clone();
/*     */     
/* 174 */     c.inner_classes = new InnerClass[this.number_of_classes];
/* 175 */     for (int i = 0; i < this.number_of_classes; i++) {
/* 176 */       c.inner_classes[i] = this.inner_classes[i].copy();
/*     */     }
/* 178 */     c.constant_pool = constant_pool;
/* 179 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/InnerClasses.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */