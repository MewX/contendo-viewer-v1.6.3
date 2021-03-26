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
/*     */ public class ConstantPool
/*     */   implements Cloneable, Node
/*     */ {
/*     */   private int constant_pool_count;
/*     */   private Constant[] constant_pool;
/*     */   
/*     */   public ConstantPool(Constant[] constant_pool) {
/*  78 */     setConstantPool(constant_pool);
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
/*     */   ConstantPool(DataInputStream file) throws IOException, ClassFormatError {
/*  92 */     this.constant_pool_count = file.readUnsignedShort();
/*  93 */     this.constant_pool = new Constant[this.constant_pool_count];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     for (int i = 1; i < this.constant_pool_count; i++) {
/*  99 */       this.constant_pool[i] = Constant.readConstant(file);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       byte tag = this.constant_pool[i].getTag();
/* 109 */       if (tag == 6 || tag == 5) {
/* 110 */         i++;
/*     */       }
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
/* 122 */     v.visitConstantPool(this);
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
/*     */   public String constantToString(Constant c) throws ClassFormatError {
/*     */     String str;
/*     */     int i;
/* 136 */     byte tag = c.getTag();
/*     */     
/* 138 */     switch (tag) {
/*     */       case 7:
/* 140 */         i = ((ConstantClass)c).getNameIndex();
/* 141 */         c = getConstant(i, (byte)1);
/* 142 */         str = Utility.compactClassName(((ConstantUtf8)c).getBytes(), false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 176 */         return str;case 8: i = ((ConstantString)c).getStringIndex(); c = getConstant(i, (byte)1); str = "\"" + escape(((ConstantUtf8)c).getBytes()) + "\""; return str;case 1: str = ((ConstantUtf8)c).getBytes(); return str;case 6: str = "" + ((ConstantDouble)c).getBytes(); return str;case 4: str = "" + ((ConstantFloat)c).getBytes(); return str;case 5: str = "" + ((ConstantLong)c).getBytes(); return str;case 3: str = "" + ((ConstantInteger)c).getBytes(); return str;case 12: str = constantToString(((ConstantNameAndType)c).getNameIndex(), (byte)1) + " " + constantToString(((ConstantNameAndType)c).getSignatureIndex(), (byte)1); return str;case 9: case 10: case 11: str = constantToString(((ConstantCP)c).getClassIndex(), (byte)7) + "." + constantToString(((ConstantCP)c).getNameAndTypeIndex(), (byte)12); return str;
/*     */     } 
/*     */     throw new RuntimeException("Unknown constant type " + tag);
/*     */   } private static final String escape(String str) {
/* 180 */     int len = str.length();
/* 181 */     StringBuffer buf = new StringBuffer(len + 5);
/* 182 */     char[] ch = str.toCharArray();
/*     */     
/* 184 */     for (int i = 0; i < len; i++) {
/* 185 */       switch (ch[i]) { case '\n':
/* 186 */           buf.append("\\n"); break;
/* 187 */         case '\r': buf.append("\\r"); break;
/* 188 */         case '\t': buf.append("\\t"); break;
/* 189 */         case '\b': buf.append("\\b"); break;
/* 190 */         case '"': buf.append("\\\""); break;
/* 191 */         default: buf.append(ch[i]);
/*     */           break; }
/*     */     
/*     */     } 
/* 195 */     return buf.toString();
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
/*     */   public String constantToString(int index, byte tag) throws ClassFormatError {
/* 210 */     Constant c = getConstant(index, tag);
/* 211 */     return constantToString(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream file) throws IOException {
/* 222 */     file.writeShort(this.constant_pool_count);
/*     */     
/* 224 */     for (int i = 1; i < this.constant_pool_count; i++) {
/* 225 */       if (this.constant_pool[i] != null) {
/* 226 */         this.constant_pool[i].dump(file);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Constant getConstant(int index) {
/* 237 */     if (index >= this.constant_pool.length || index < 0) {
/* 238 */       throw new ClassFormatError("Invalid constant pool reference: " + index + ". Constant pool size is: " + this.constant_pool.length);
/*     */     }
/*     */     
/* 241 */     return this.constant_pool[index];
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
/*     */   public Constant getConstant(int index, byte tag) throws ClassFormatError {
/* 259 */     Constant c = getConstant(index);
/*     */     
/* 261 */     if (c == null) {
/* 262 */       throw new ClassFormatError("Constant pool at index " + index + " is null.");
/*     */     }
/* 264 */     if (c.getTag() == tag) {
/* 265 */       return c;
/*     */     }
/* 267 */     throw new ClassFormatError("Expected class `" + Constants.CONSTANT_NAMES[tag] + "' at index " + index + " and got " + c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Constant[] getConstantPool() {
/* 275 */     return this.constant_pool;
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
/*     */   public String getConstantString(int index, byte tag) throws ClassFormatError {
/*     */     int i;
/* 296 */     Constant c = getConstant(index, tag);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     switch (tag) { case 7:
/* 306 */         i = ((ConstantClass)c).getNameIndex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 313 */         c = getConstant(i, (byte)1);
/* 314 */         return ((ConstantUtf8)c).getBytes();case 8: i = ((ConstantString)c).getStringIndex(); c = getConstant(i, (byte)1); return ((ConstantUtf8)c).getBytes(); }
/*     */     
/*     */     throw new RuntimeException("getConstantString called with illegal tag " + tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 321 */     return this.constant_pool_count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstant(int index, Constant constant) {
/* 328 */     this.constant_pool[index] = constant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstantPool(Constant[] constant_pool) {
/* 335 */     this.constant_pool = constant_pool;
/* 336 */     this.constant_pool_count = (constant_pool == null) ? 0 : constant_pool.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 342 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 344 */     for (int i = 1; i < this.constant_pool_count; i++) {
/* 345 */       buf.append(i + ")" + this.constant_pool[i] + "\n");
/*     */     }
/* 347 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantPool copy() {
/* 354 */     ConstantPool c = null;
/*     */     
/*     */     try {
/* 357 */       c = (ConstantPool)clone();
/* 358 */     } catch (CloneNotSupportedException e) {}
/*     */     
/* 360 */     c.constant_pool = new Constant[this.constant_pool_count];
/*     */     
/* 362 */     for (int i = 1; i < this.constant_pool_count; i++) {
/* 363 */       if (this.constant_pool[i] != null) {
/* 364 */         c.constant_pool[i] = this.constant_pool[i].copy();
/*     */       }
/*     */     } 
/* 367 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ConstantPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */