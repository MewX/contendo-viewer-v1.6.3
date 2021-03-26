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
/*     */ public final class PMGClass
/*     */   extends Attribute
/*     */ {
/*     */   private int pmg_class_index;
/*     */   private int pmg_index;
/*     */   
/*     */   public PMGClass(PMGClass c) {
/*  77 */     this(c.getNameIndex(), c.getLength(), c.getPMGIndex(), c.getPMGClassIndex(), c.getConstantPool());
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
/*     */   PMGClass(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  92 */     this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
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
/*     */   public PMGClass(int name_index, int length, int pmg_index, int pmg_class_index, ConstantPool constant_pool) {
/* 105 */     super((byte)9, name_index, length, constant_pool);
/* 106 */     this.pmg_index = pmg_index;
/* 107 */     this.pmg_class_index = pmg_class_index;
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
/* 118 */     System.err.println("Visiting non-standard PMGClass object");
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
/* 129 */     super.dump(file);
/* 130 */     file.writeShort(this.pmg_index);
/* 131 */     file.writeShort(this.pmg_class_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPMGClassIndex() {
/* 137 */     return this.pmg_class_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setPMGClassIndex(int pmg_class_index) {
/* 143 */     this.pmg_class_index = pmg_class_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPMGIndex() {
/* 149 */     return this.pmg_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setPMGIndex(int pmg_index) {
/* 155 */     this.pmg_index = pmg_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getPMGName() {
/* 162 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.pmg_index, (byte)1);
/*     */     
/* 164 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getPMGClassName() {
/* 171 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.pmg_class_index, (byte)1);
/*     */     
/* 173 */     return c.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 180 */     return "PMGClass(" + getPMGName() + ", " + getPMGClassName() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 187 */     return (PMGClass)clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/PMGClass.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */