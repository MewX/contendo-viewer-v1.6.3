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
/*     */ public abstract class Constant
/*     */   implements Cloneable, Node
/*     */ {
/*     */   protected byte tag;
/*     */   
/*     */   Constant(byte tag) {
/*  79 */     this.tag = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void accept(Visitor paramVisitor);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void dump(DataOutputStream paramDataOutputStream) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getTag() {
/*  96 */     return this.tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     return Constants.CONSTANT_NAMES[this.tag] + "[" + this.tag + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Constant copy() {
/*     */     try {
/* 110 */       return (Constant)super.clone();
/* 111 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 113 */       return null;
/*     */     } 
/*     */   }
/*     */   public Object clone() throws CloneNotSupportedException {
/* 117 */     return super.clone();
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
/*     */   static final Constant readConstant(DataInputStream file) throws IOException, ClassFormatError {
/* 129 */     byte b = file.readByte();
/*     */     
/* 131 */     switch (b) { case 7:
/* 132 */         return new ConstantClass(file);
/* 133 */       case 9: return new ConstantFieldref(file);
/* 134 */       case 10: return new ConstantMethodref(file);
/* 135 */       case 11: return new ConstantInterfaceMethodref(file);
/*     */       case 8:
/* 137 */         return new ConstantString(file);
/* 138 */       case 3: return new ConstantInteger(file);
/* 139 */       case 4: return new ConstantFloat(file);
/* 140 */       case 5: return new ConstantLong(file);
/* 141 */       case 6: return new ConstantDouble(file);
/* 142 */       case 12: return new ConstantNameAndType(file);
/* 143 */       case 1: return new ConstantUtf8(file); }
/*     */     
/* 145 */     throw new ClassFormatError("Invalid byte tag in constant pool: " + b);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Constant.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */