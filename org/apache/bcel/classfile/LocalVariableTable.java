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
/*     */ public class LocalVariableTable
/*     */   extends Attribute
/*     */ {
/*     */   private int local_variable_table_length;
/*     */   private LocalVariable[] local_variable_table;
/*     */   
/*     */   public LocalVariableTable(LocalVariableTable c) {
/*  78 */     this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
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
/*     */   public LocalVariableTable(int name_index, int length, LocalVariable[] local_variable_table, ConstantPool constant_pool) {
/*  92 */     super((byte)5, name_index, length, constant_pool);
/*  93 */     setLocalVariableTable(local_variable_table);
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
/*     */   LocalVariableTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 107 */     this(name_index, length, (LocalVariable[])null, constant_pool);
/*     */     
/* 109 */     this.local_variable_table_length = file.readUnsignedShort();
/* 110 */     this.local_variable_table = new LocalVariable[this.local_variable_table_length];
/*     */     
/* 112 */     for (int i = 0; i < this.local_variable_table_length; i++) {
/* 113 */       this.local_variable_table[i] = new LocalVariable(file, constant_pool);
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
/* 124 */     v.visitLocalVariableTable(this);
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
/* 135 */     super.dump(file);
/* 136 */     file.writeShort(this.local_variable_table_length);
/* 137 */     for (int i = 0; i < this.local_variable_table_length; i++) {
/* 138 */       this.local_variable_table[i].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final LocalVariable[] getLocalVariableTable() {
/* 145 */     return this.local_variable_table;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final LocalVariable getLocalVariable(int index) {
/* 151 */     for (int i = 0; i < this.local_variable_table_length; i++) {
/* 152 */       if (this.local_variable_table[i].getIndex() == index)
/* 153 */         return this.local_variable_table[i]; 
/*     */     } 
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setLocalVariableTable(LocalVariable[] local_variable_table) {
/* 160 */     this.local_variable_table = local_variable_table;
/* 161 */     this.local_variable_table_length = (local_variable_table == null) ? 0 : local_variable_table.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 169 */     StringBuffer buf = new StringBuffer("");
/*     */     
/* 171 */     for (int i = 0; i < this.local_variable_table_length; i++) {
/* 172 */       buf.append(this.local_variable_table[i].toString());
/*     */       
/* 174 */       if (i < this.local_variable_table_length - 1) {
/* 175 */         buf.append('\n');
/*     */       }
/*     */     } 
/* 178 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 185 */     LocalVariableTable c = (LocalVariableTable)clone();
/*     */     
/* 187 */     c.local_variable_table = new LocalVariable[this.local_variable_table_length];
/* 188 */     for (int i = 0; i < this.local_variable_table_length; i++) {
/* 189 */       c.local_variable_table[i] = this.local_variable_table[i].copy();
/*     */     }
/* 191 */     c.constant_pool = constant_pool;
/* 192 */     return c;
/*     */   }
/*     */   public final int getTableLength() {
/* 195 */     return this.local_variable_table_length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/LocalVariableTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */