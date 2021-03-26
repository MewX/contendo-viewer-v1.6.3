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
/*     */ public final class InnerClass
/*     */   implements Cloneable, Node
/*     */ {
/*     */   private int inner_class_index;
/*     */   private int outer_class_index;
/*     */   private int inner_name_index;
/*     */   private int inner_access_flags;
/*     */   
/*     */   public InnerClass(InnerClass c) {
/*  79 */     this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c.getInnerAccessFlags());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   InnerClass(DataInputStream file) throws IOException {
/*  90 */     this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
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
/*     */   public InnerClass(int inner_class_index, int outer_class_index, int inner_name_index, int inner_access_flags) {
/* 103 */     this.inner_class_index = inner_class_index;
/* 104 */     this.outer_class_index = outer_class_index;
/* 105 */     this.inner_name_index = inner_name_index;
/* 106 */     this.inner_access_flags = inner_access_flags;
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
/* 117 */     v.visitInnerClass(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 127 */     file.writeShort(this.inner_class_index);
/* 128 */     file.writeShort(this.outer_class_index);
/* 129 */     file.writeShort(this.inner_name_index);
/* 130 */     file.writeShort(this.inner_access_flags);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getInnerAccessFlags() {
/* 135 */     return this.inner_access_flags;
/*     */   }
/*     */   
/*     */   public final int getInnerClassIndex() {
/* 139 */     return this.inner_class_index;
/*     */   }
/*     */   
/*     */   public final int getInnerNameIndex() {
/* 143 */     return this.inner_name_index;
/*     */   }
/*     */   
/*     */   public final int getOuterClassIndex() {
/* 147 */     return this.outer_class_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setInnerAccessFlags(int inner_access_flags) {
/* 152 */     this.inner_access_flags = inner_access_flags;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setInnerClassIndex(int inner_class_index) {
/* 158 */     this.inner_class_index = inner_class_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setInnerNameIndex(int inner_name_index) {
/* 164 */     this.inner_name_index = inner_name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setOuterClassIndex(int outer_class_index) {
/* 170 */     this.outer_class_index = outer_class_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 176 */     return "InnerClass(" + this.inner_class_index + ", " + this.outer_class_index + ", " + this.inner_name_index + ", " + this.inner_access_flags + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString(ConstantPool constant_pool) {
/* 186 */     String str1, str2, inner_class_name = constant_pool.getConstantString(this.inner_class_index, (byte)7);
/*     */     
/* 188 */     inner_class_name = Utility.compactClassName(inner_class_name);
/*     */     
/* 190 */     if (this.outer_class_index != 0) {
/* 191 */       str1 = constant_pool.getConstantString(this.outer_class_index, (byte)7);
/*     */       
/* 193 */       str1 = Utility.compactClassName(str1);
/*     */     } else {
/*     */       
/* 196 */       str1 = "<not a member>";
/*     */     } 
/* 198 */     if (this.inner_name_index != 0) {
/* 199 */       str2 = ((ConstantUtf8)constant_pool.getConstant(this.inner_name_index, (byte)1)).getBytes();
/*     */     } else {
/*     */       
/* 202 */       str2 = "<anonymous>";
/*     */     } 
/* 204 */     String access = Utility.accessToString(this.inner_access_flags, true);
/* 205 */     access = access.equals("") ? "" : (access + " ");
/*     */     
/* 207 */     return "InnerClass:" + access + inner_class_name + "(\"" + str1 + "\", \"" + str2 + "\")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InnerClass copy() {
/*     */     try {
/* 216 */       return (InnerClass)clone();
/* 217 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 219 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/InnerClass.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */