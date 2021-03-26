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
/*     */ public final class LineNumberTable
/*     */   extends Attribute
/*     */ {
/*     */   private int line_number_table_length;
/*     */   private LineNumber[] line_number_table;
/*     */   
/*     */   public LineNumberTable(LineNumberTable c) {
/*  79 */     this(c.getNameIndex(), c.getLength(), c.getLineNumberTable(), c.getConstantPool());
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
/*     */   public LineNumberTable(int name_index, int length, LineNumber[] line_number_table, ConstantPool constant_pool) {
/*  93 */     super((byte)4, name_index, length, constant_pool);
/*  94 */     setLineNumberTable(line_number_table);
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
/*     */   LineNumberTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 108 */     this(name_index, length, (LineNumber[])null, constant_pool);
/* 109 */     this.line_number_table_length = file.readUnsignedShort();
/* 110 */     this.line_number_table = new LineNumber[this.line_number_table_length];
/*     */     
/* 112 */     for (int i = 0; i < this.line_number_table_length; i++) {
/* 113 */       this.line_number_table[i] = new LineNumber(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 123 */     v.visitLineNumberTable(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 133 */     super.dump(file);
/* 134 */     file.writeShort(this.line_number_table_length);
/* 135 */     for (int i = 0; i < this.line_number_table_length; i++) {
/* 136 */       this.line_number_table[i].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final LineNumber[] getLineNumberTable() {
/* 142 */     return this.line_number_table;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLineNumberTable(LineNumber[] line_number_table) {
/* 148 */     this.line_number_table = line_number_table;
/*     */     
/* 150 */     this.line_number_table_length = (line_number_table == null) ? 0 : line_number_table.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 158 */     StringBuffer buf = new StringBuffer();
/* 159 */     StringBuffer line = new StringBuffer();
/*     */     
/* 161 */     for (int i = 0; i < this.line_number_table_length; i++) {
/* 162 */       line.append(this.line_number_table[i].toString());
/*     */       
/* 164 */       if (i < this.line_number_table_length - 1) {
/* 165 */         line.append(", ");
/*     */       }
/* 167 */       if (line.length() > 72) {
/* 168 */         line.append('\n');
/* 169 */         buf.append(line);
/* 170 */         line.setLength(0);
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     buf.append(line);
/*     */     
/* 176 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSourceLine(int pos) {
/* 186 */     int l = 0, r = this.line_number_table_length - 1;
/*     */     
/* 188 */     if (r < 0) {
/* 189 */       return -1;
/*     */     }
/* 191 */     int min_index = -1, min = -1;
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 196 */       int i = (l + r) / 2;
/* 197 */       int j = this.line_number_table[i].getStartPC();
/*     */       
/* 199 */       if (j == pos)
/* 200 */         return this.line_number_table[i].getLineNumber(); 
/* 201 */       if (pos < j) {
/* 202 */         r = i - 1;
/*     */       } else {
/* 204 */         l = i + 1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 210 */       if (j >= pos || j <= min)
/* 211 */         continue;  min = j;
/* 212 */       min_index = i;
/*     */     }
/* 214 */     while (l <= r);
/*     */     
/* 216 */     return this.line_number_table[min_index].getLineNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 223 */     LineNumberTable c = (LineNumberTable)clone();
/*     */     
/* 225 */     c.line_number_table = new LineNumber[this.line_number_table_length];
/* 226 */     for (int i = 0; i < this.line_number_table_length; i++) {
/* 227 */       c.line_number_table[i] = this.line_number_table[i].copy();
/*     */     }
/* 229 */     c.constant_pool = constant_pool;
/* 230 */     return c;
/*     */   }
/*     */   public final int getTableLength() {
/* 233 */     return this.line_number_table_length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/LineNumberTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */