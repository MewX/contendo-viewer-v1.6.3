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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Code
/*     */   extends Attribute
/*     */ {
/*     */   private int max_stack;
/*     */   private int max_locals;
/*     */   private int code_length;
/*     */   private byte[] code;
/*     */   private int exception_table_length;
/*     */   private CodeException[] exception_table;
/*     */   private int attributes_count;
/*     */   private Attribute[] attributes;
/*     */   
/*     */   public Code(Code c) {
/*  95 */     this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
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
/*     */   Code(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/* 110 */     this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[])null, (CodeException[])null, (Attribute[])null, constant_pool);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     this.code_length = file.readInt();
/* 116 */     this.code = new byte[this.code_length];
/* 117 */     file.readFully(this.code);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     this.exception_table_length = file.readUnsignedShort();
/* 123 */     this.exception_table = new CodeException[this.exception_table_length];
/*     */     
/* 125 */     for (int i = 0; i < this.exception_table_length; i++) {
/* 126 */       this.exception_table[i] = new CodeException(file);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 131 */     this.attributes_count = file.readUnsignedShort();
/* 132 */     this.attributes = new Attribute[this.attributes_count];
/* 133 */     for (int j = 0; j < this.attributes_count; j++) {
/* 134 */       this.attributes[j] = Attribute.readAttribute(file, constant_pool);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     this.length = length;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Code(int name_index, int length, int max_stack, int max_locals, byte[] code, CodeException[] exception_table, Attribute[] attributes, ConstantPool constant_pool) {
/* 160 */     super((byte)2, name_index, length, constant_pool);
/*     */     
/* 162 */     this.max_stack = max_stack;
/* 163 */     this.max_locals = max_locals;
/*     */     
/* 165 */     setCode(code);
/* 166 */     setExceptionTable(exception_table);
/* 167 */     setAttributes(attributes);
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
/* 178 */     v.visitCode(this);
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
/* 189 */     super.dump(file);
/*     */     
/* 191 */     file.writeShort(this.max_stack);
/* 192 */     file.writeShort(this.max_locals);
/* 193 */     file.writeInt(this.code_length);
/* 194 */     file.write(this.code, 0, this.code_length);
/*     */     
/* 196 */     file.writeShort(this.exception_table_length);
/* 197 */     for (int i = 0; i < this.exception_table_length; i++) {
/* 198 */       this.exception_table[i].dump(file);
/*     */     }
/* 200 */     file.writeShort(this.attributes_count);
/* 201 */     for (int j = 0; j < this.attributes_count; j++) {
/* 202 */       this.attributes[j].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Attribute[] getAttributes() {
/* 209 */     return this.attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumberTable getLineNumberTable() {
/* 215 */     for (int i = 0; i < this.attributes_count; i++) {
/* 216 */       if (this.attributes[i] instanceof LineNumberTable)
/* 217 */         return (LineNumberTable)this.attributes[i]; 
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariableTable getLocalVariableTable() {
/* 226 */     for (int i = 0; i < this.attributes_count; i++) {
/* 227 */       if (this.attributes[i] instanceof LocalVariableTable)
/* 228 */         return (LocalVariableTable)this.attributes[i]; 
/*     */     } 
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte[] getCode() {
/* 236 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final CodeException[] getExceptionTable() {
/* 242 */     return this.exception_table;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getMaxLocals() {
/* 247 */     return this.max_locals;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaxStack() {
/* 253 */     return this.max_stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int getInternalLength() {
/* 260 */     return 8 + this.code_length + 2 + 8 * this.exception_table_length + 2;
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
/*     */   private final int calculateLength() {
/* 272 */     int len = 0;
/*     */     
/* 274 */     for (int i = 0; i < this.attributes_count; i++) {
/* 275 */       len += (this.attributes[i]).length + 6;
/*     */     }
/* 277 */     return len + getInternalLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setAttributes(Attribute[] attributes) {
/* 284 */     this.attributes = attributes;
/* 285 */     this.attributes_count = (attributes == null) ? 0 : attributes.length;
/* 286 */     this.length = calculateLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCode(byte[] code) {
/* 293 */     this.code = code;
/* 294 */     this.code_length = (code == null) ? 0 : code.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setExceptionTable(CodeException[] exception_table) {
/* 301 */     this.exception_table = exception_table;
/* 302 */     this.exception_table_length = (exception_table == null) ? 0 : exception_table.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setMaxLocals(int max_locals) {
/* 310 */     this.max_locals = max_locals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setMaxStack(int max_stack) {
/* 317 */     this.max_stack = max_stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString(boolean verbose) {
/* 326 */     StringBuffer buf = new StringBuffer("Code(max_stack = " + this.max_stack + ", max_locals = " + this.max_locals + ", code_length = " + this.code_length + ")\n" + Utility.codeToString(this.code, this.constant_pool, 0, -1, verbose));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 331 */     if (this.exception_table_length > 0) {
/* 332 */       buf.append("\nException handler(s) = \nFrom\tTo\tHandler\tType\n");
/*     */       
/* 334 */       for (int i = 0; i < this.exception_table_length; i++) {
/* 335 */         buf.append(this.exception_table[i].toString(this.constant_pool, verbose) + "\n");
/*     */       }
/*     */     } 
/* 338 */     if (this.attributes_count > 0) {
/* 339 */       buf.append("\nAttribute(s) = \n");
/*     */       
/* 341 */       for (int i = 0; i < this.attributes_count; i++) {
/* 342 */         buf.append(this.attributes[i].toString() + "\n");
/*     */       }
/*     */     } 
/* 345 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 352 */     return toString(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 359 */     Code c = (Code)clone();
/* 360 */     c.code = (byte[])this.code.clone();
/* 361 */     c.constant_pool = constant_pool;
/*     */     
/* 363 */     c.exception_table = new CodeException[this.exception_table_length];
/* 364 */     for (int i = 0; i < this.exception_table_length; i++) {
/* 365 */       c.exception_table[i] = this.exception_table[i].copy();
/*     */     }
/* 367 */     c.attributes = new Attribute[this.attributes_count];
/* 368 */     for (int j = 0; j < this.attributes_count; j++) {
/* 369 */       c.attributes[j] = this.attributes[j].copy(constant_pool);
/*     */     }
/* 371 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Code.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */