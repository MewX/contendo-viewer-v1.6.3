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
/*     */ public final class ExceptionTable
/*     */   extends Attribute
/*     */ {
/*     */   private int number_of_exceptions;
/*     */   private int[] exception_index_table;
/*     */   
/*     */   public ExceptionTable(ExceptionTable c) {
/*  81 */     this(c.getNameIndex(), c.getLength(), c.getExceptionIndexTable(), c.getConstantPool());
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
/*     */   public ExceptionTable(int name_index, int length, int[] exception_index_table, ConstantPool constant_pool) {
/*  95 */     super((byte)3, name_index, length, constant_pool);
/*  96 */     setExceptionIndexTable(exception_index_table);
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
/*     */   ExceptionTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 110 */     this(name_index, length, (int[])null, constant_pool);
/*     */     
/* 112 */     this.number_of_exceptions = file.readUnsignedShort();
/* 113 */     this.exception_index_table = new int[this.number_of_exceptions];
/*     */     
/* 115 */     for (int i = 0; i < this.number_of_exceptions; i++) {
/* 116 */       this.exception_index_table[i] = file.readUnsignedShort();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 127 */     v.visitExceptionTable(this);
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
/* 138 */     super.dump(file);
/* 139 */     file.writeShort(this.number_of_exceptions);
/* 140 */     for (int i = 0; i < this.number_of_exceptions; i++) {
/* 141 */       file.writeShort(this.exception_index_table[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final int[] getExceptionIndexTable() {
/* 147 */     return this.exception_index_table;
/*     */   }
/*     */   
/*     */   public final int getNumberOfExceptions() {
/* 151 */     return this.number_of_exceptions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String[] getExceptionNames() {
/* 157 */     String[] names = new String[this.number_of_exceptions];
/* 158 */     for (int i = 0; i < this.number_of_exceptions; i++) {
/* 159 */       names[i] = this.constant_pool.getConstantString(this.exception_index_table[i], (byte)7).replace('/', '.');
/*     */     }
/*     */     
/* 162 */     return names;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setExceptionIndexTable(int[] exception_index_table) {
/* 170 */     this.exception_index_table = exception_index_table;
/* 171 */     this.number_of_exceptions = (exception_index_table == null) ? 0 : exception_index_table.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 178 */     StringBuffer buf = new StringBuffer("");
/*     */ 
/*     */     
/* 181 */     for (int i = 0; i < this.number_of_exceptions; i++) {
/* 182 */       String str = this.constant_pool.getConstantString(this.exception_index_table[i], (byte)7);
/*     */       
/* 184 */       buf.append(Utility.compactClassName(str, false));
/*     */       
/* 186 */       if (i < this.number_of_exceptions - 1) {
/* 187 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 190 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 197 */     ExceptionTable c = (ExceptionTable)clone();
/* 198 */     c.exception_index_table = (int[])this.exception_index_table.clone();
/* 199 */     c.constant_pool = constant_pool;
/* 200 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ExceptionTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */