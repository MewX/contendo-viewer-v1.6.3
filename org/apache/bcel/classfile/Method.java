/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
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
/*     */ public final class Method
/*     */   extends FieldOrMethod
/*     */ {
/*     */   public Method() {}
/*     */   
/*     */   public Method(Method c) {
/*  79 */     super(c);
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
/*     */   Method(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatError {
/*  91 */     super(file, constant_pool);
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
/*     */   public Method(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
/* 104 */     super(access_flags, name_index, signature_index, attributes, constant_pool);
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
/* 115 */     v.visitMethod(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Code getCode() {
/* 122 */     for (int i = 0; i < this.attributes_count; i++) {
/* 123 */       if (this.attributes[i] instanceof Code)
/* 124 */         return (Code)this.attributes[i]; 
/*     */     } 
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ExceptionTable getExceptionTable() {
/* 134 */     for (int i = 0; i < this.attributes_count; i++) {
/* 135 */       if (this.attributes[i] instanceof ExceptionTable)
/* 136 */         return (ExceptionTable)this.attributes[i]; 
/*     */     } 
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final LocalVariableTable getLocalVariableTable() {
/* 145 */     Code code = getCode();
/*     */     
/* 147 */     if (code != null) {
/* 148 */       return code.getLocalVariableTable();
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final LineNumberTable getLineNumberTable() {
/* 157 */     Code code = getCode();
/*     */     
/* 159 */     if (code != null) {
/* 160 */       return code.getLineNumberTable();
/*     */     }
/* 162 */     return null;
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
/*     */   public final String toString() {
/* 179 */     String access = Utility.accessToString(this.access_flags);
/*     */ 
/*     */     
/* 182 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.signature_index, (byte)1);
/*     */     
/* 184 */     String signature = c.getBytes();
/*     */     
/* 186 */     c = (ConstantUtf8)this.constant_pool.getConstant(this.name_index, (byte)1);
/* 187 */     String name = c.getBytes();
/*     */     
/* 189 */     signature = Utility.methodSignatureToString(signature, name, access, true, getLocalVariableTable());
/*     */     
/* 191 */     StringBuffer buf = new StringBuffer(signature);
/*     */     
/* 193 */     for (int i = 0; i < this.attributes_count; i++) {
/* 194 */       Attribute a = this.attributes[i];
/*     */       
/* 196 */       if (!(a instanceof Code) && !(a instanceof ExceptionTable)) {
/* 197 */         buf.append(" [" + a.toString() + "]");
/*     */       }
/*     */     } 
/* 200 */     ExceptionTable e = getExceptionTable();
/* 201 */     if (e != null) {
/* 202 */       String str = e.toString();
/* 203 */       if (!str.equals("")) {
/* 204 */         buf.append("\n\t\tthrows " + str);
/*     */       }
/*     */     } 
/* 207 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Method copy(ConstantPool constant_pool) {
/* 214 */     return (Method)copy_(constant_pool);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Method.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */