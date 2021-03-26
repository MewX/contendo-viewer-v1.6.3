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
/*     */ public final class ConstantValue
/*     */   extends Attribute
/*     */ {
/*     */   private int constantvalue_index;
/*     */   
/*     */   public ConstantValue(ConstantValue c) {
/*  77 */     this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
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
/*     */   ConstantValue(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  92 */     this(name_index, length, file.readUnsignedShort(), constant_pool);
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
/*     */   public ConstantValue(int name_index, int length, int constantvalue_index, ConstantPool constant_pool) {
/* 105 */     super((byte)1, name_index, length, constant_pool);
/* 106 */     this.constantvalue_index = constantvalue_index;
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
/* 117 */     v.visitConstantValue(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 127 */     super.dump(file);
/* 128 */     file.writeShort(this.constantvalue_index);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getConstantValueIndex() {
/* 133 */     return this.constantvalue_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantValueIndex(int constantvalue_index) {
/* 139 */     this.constantvalue_index = constantvalue_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() throws InternalError {
/*     */     String buf;
/*     */     int i;
/* 147 */     Constant c = this.constant_pool.getConstant(this.constantvalue_index);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     switch (c.getTag()) { case 5:
/* 154 */         buf = "" + ((ConstantLong)c).getBytes();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 166 */         return buf;case 4: buf = "" + ((ConstantFloat)c).getBytes(); return buf;case 6: buf = "" + ((ConstantDouble)c).getBytes(); return buf;case 3: buf = "" + ((ConstantInteger)c).getBytes(); return buf;case 8: i = ((ConstantString)c).getStringIndex(); c = this.constant_pool.getConstant(i, (byte)1); buf = "\"" + convertString(((ConstantUtf8)c).getBytes()) + "\""; return buf; }
/*     */     
/*     */     throw new InternalError("Type of ConstValue invalid: " + c);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String convertString(String label) {
/* 173 */     char[] ch = label.toCharArray();
/* 174 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 176 */     for (int i = 0; i < ch.length; i++) {
/* 177 */       switch (ch[i]) {
/*     */         case '\n':
/* 179 */           buf.append("\\n"); break;
/*     */         case '\r':
/* 181 */           buf.append("\\r"); break;
/*     */         case '"':
/* 183 */           buf.append("\\\""); break;
/*     */         case '\'':
/* 185 */           buf.append("\\'"); break;
/*     */         case '\\':
/* 187 */           buf.append("\\\\"); break;
/*     */         default:
/* 189 */           buf.append(ch[i]);
/*     */           break;
/*     */       } 
/*     */     } 
/* 193 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 200 */     ConstantValue c = (ConstantValue)clone();
/* 201 */     c.constant_pool = constant_pool;
/* 202 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantValue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */