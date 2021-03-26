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
/*     */ public final class StackMapEntry
/*     */   implements Cloneable
/*     */ {
/*     */   private int byte_code_offset;
/*     */   private int number_of_locals;
/*     */   private StackMapType[] types_of_locals;
/*     */   private int number_of_stack_items;
/*     */   private StackMapType[] types_of_stack_items;
/*     */   private ConstantPool constant_pool;
/*     */   
/*     */   StackMapEntry(DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  85 */     this(file.readShort(), file.readShort(), null, -1, null, constant_pool);
/*     */     
/*  87 */     this.types_of_locals = new StackMapType[this.number_of_locals];
/*  88 */     for (int i = 0; i < this.number_of_locals; i++) {
/*  89 */       this.types_of_locals[i] = new StackMapType(file, constant_pool);
/*     */     }
/*  91 */     this.number_of_stack_items = file.readShort();
/*  92 */     this.types_of_stack_items = new StackMapType[this.number_of_stack_items];
/*  93 */     for (int j = 0; j < this.number_of_stack_items; j++) {
/*  94 */       this.types_of_stack_items[j] = new StackMapType(file, constant_pool);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StackMapEntry(int byte_code_offset, int number_of_locals, StackMapType[] types_of_locals, int number_of_stack_items, StackMapType[] types_of_stack_items, ConstantPool constant_pool) {
/* 102 */     this.byte_code_offset = byte_code_offset;
/* 103 */     this.number_of_locals = number_of_locals;
/* 104 */     this.types_of_locals = types_of_locals;
/* 105 */     this.number_of_stack_items = number_of_stack_items;
/* 106 */     this.types_of_stack_items = types_of_stack_items;
/* 107 */     this.constant_pool = constant_pool;
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
/* 118 */     file.writeShort(this.byte_code_offset);
/*     */     
/* 120 */     file.writeShort(this.number_of_locals);
/* 121 */     for (int i = 0; i < this.number_of_locals; i++) {
/* 122 */       this.types_of_locals[i].dump(file);
/*     */     }
/* 124 */     file.writeShort(this.number_of_stack_items);
/* 125 */     for (int j = 0; j < this.number_of_stack_items; j++) {
/* 126 */       this.types_of_stack_items[j].dump(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 133 */     StringBuffer buf = new StringBuffer("(offset=" + this.byte_code_offset);
/*     */     
/* 135 */     if (this.number_of_locals > 0) {
/* 136 */       buf.append(", locals={");
/*     */       
/* 138 */       for (int i = 0; i < this.number_of_locals; i++) {
/* 139 */         buf.append(this.types_of_locals[i]);
/* 140 */         if (i < this.number_of_locals - 1) {
/* 141 */           buf.append(", ");
/*     */         }
/*     */       } 
/* 144 */       buf.append("}");
/*     */     } 
/*     */     
/* 147 */     if (this.number_of_stack_items > 0) {
/* 148 */       buf.append(", stack items={");
/*     */       
/* 150 */       for (int i = 0; i < this.number_of_stack_items; i++) {
/* 151 */         buf.append(this.types_of_stack_items[i]);
/* 152 */         if (i < this.number_of_stack_items - 1) {
/* 153 */           buf.append(", ");
/*     */         }
/*     */       } 
/* 156 */       buf.append("}");
/*     */     } 
/*     */     
/* 159 */     buf.append(")");
/*     */     
/* 161 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void setByteCodeOffset(int b) {
/* 165 */     this.byte_code_offset = b;
/* 166 */   } public int getByteCodeOffset() { return this.byte_code_offset; }
/* 167 */   public void setNumberOfLocals(int n) { this.number_of_locals = n; }
/* 168 */   public int getNumberOfLocals() { return this.number_of_locals; }
/* 169 */   public void setTypesOfLocals(StackMapType[] t) { this.types_of_locals = t; }
/* 170 */   public StackMapType[] getTypesOfLocals() { return this.types_of_locals; }
/* 171 */   public void setNumberOfStackItems(int n) { this.number_of_stack_items = n; }
/* 172 */   public int getNumberOfStackItems() { return this.number_of_stack_items; }
/* 173 */   public void setTypesOfStackItems(StackMapType[] t) { this.types_of_stack_items = t; } public StackMapType[] getTypesOfStackItems() {
/* 174 */     return this.types_of_stack_items;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StackMapEntry copy() {
/*     */     try {
/* 181 */       return (StackMapEntry)clone();
/* 182 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 184 */       return null;
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
/* 195 */     v.visitStackMapEntry(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConstantPool getConstantPool() {
/* 201 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantPool(ConstantPool constant_pool) {
/* 207 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/StackMapEntry.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */