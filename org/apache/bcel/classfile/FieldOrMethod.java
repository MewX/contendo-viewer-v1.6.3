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
/*     */ public abstract class FieldOrMethod
/*     */   extends AccessFlags
/*     */   implements Cloneable, Node
/*     */ {
/*     */   protected int name_index;
/*     */   protected int signature_index;
/*     */   protected int attributes_count;
/*     */   protected Attribute[] attributes;
/*     */   protected ConstantPool constant_pool;
/*     */   
/*     */   FieldOrMethod() {}
/*     */   
/*     */   protected FieldOrMethod(FieldOrMethod c) {
/*  79 */     this(c.getAccessFlags(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool());
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
/*     */   protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatError {
/*  92 */     this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
/*     */ 
/*     */     
/*  95 */     this.attributes_count = file.readUnsignedShort();
/*  96 */     this.attributes = new Attribute[this.attributes_count];
/*  97 */     for (int i = 0; i < this.attributes_count; i++) {
/*  98 */       this.attributes[i] = Attribute.readAttribute(file, constant_pool);
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
/*     */   
/*     */   protected FieldOrMethod(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
/* 111 */     this.access_flags = access_flags;
/* 112 */     this.name_index = name_index;
/* 113 */     this.signature_index = signature_index;
/* 114 */     this.constant_pool = constant_pool;
/*     */     
/* 116 */     setAttributes(attributes);
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
/* 127 */     file.writeShort(this.access_flags);
/* 128 */     file.writeShort(this.name_index);
/* 129 */     file.writeShort(this.signature_index);
/* 130 */     file.writeShort(this.attributes_count);
/*     */     
/* 132 */     for (int i = 0; i < this.attributes_count; i++) {
/* 133 */       this.attributes[i].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final Attribute[] getAttributes() {
/* 139 */     return this.attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setAttributes(Attribute[] attributes) {
/* 145 */     this.attributes = attributes;
/* 146 */     this.attributes_count = (attributes == null) ? 0 : attributes.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConstantPool getConstantPool() {
/* 152 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantPool(ConstantPool constant_pool) {
/* 158 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNameIndex() {
/* 164 */     return this.name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameIndex(int name_index) {
/* 170 */     this.name_index = name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getSignatureIndex() {
/* 176 */     return this.signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSignatureIndex(int signature_index) {
/* 182 */     this.signature_index = signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/* 190 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.name_index, (byte)1);
/*     */     
/* 192 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getSignature() {
/* 200 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.signature_index, (byte)1);
/*     */     
/* 202 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FieldOrMethod copy_(ConstantPool constant_pool) {
/* 209 */     FieldOrMethod c = null;
/*     */     
/*     */     try {
/* 212 */       c = (FieldOrMethod)clone();
/* 213 */     } catch (CloneNotSupportedException e) {}
/*     */     
/* 215 */     c.constant_pool = constant_pool;
/* 216 */     c.attributes = new Attribute[this.attributes_count];
/*     */     
/* 218 */     for (int i = 0; i < this.attributes_count; i++) {
/* 219 */       c.attributes[i] = this.attributes[i].copy(constant_pool);
/*     */     }
/* 221 */     return c;
/*     */   }
/*     */   
/*     */   public abstract void accept(Visitor paramVisitor);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/FieldOrMethod.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */