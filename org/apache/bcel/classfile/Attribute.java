/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Attribute
/*     */   implements Cloneable, Node
/*     */ {
/*     */   protected int name_index;
/*     */   protected int length;
/*     */   protected byte tag;
/*     */   protected ConstantPool constant_pool;
/*     */   
/*     */   Attribute(byte tag, int name_index, int length, ConstantPool constant_pool) {
/*  89 */     this.tag = tag;
/*  90 */     this.name_index = name_index;
/*  91 */     this.length = length;
/*  92 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void accept(Visitor paramVisitor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream file) throws IOException {
/* 112 */     file.writeShort(this.name_index);
/* 113 */     file.writeInt(this.length);
/*     */   }
/*     */   
/* 116 */   private static HashMap readers = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addAttributeReader(String name, AttributeReader r) {
/* 126 */     readers.put(name, r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeAttributeReader(String name) {
/* 134 */     readers.remove(name);
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
/*     */ 
/*     */ 
/*     */   
/*     */   static final Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatError, InternalError {
/*     */     AttributeReader r;
/* 158 */     byte tag = -1;
/*     */ 
/*     */     
/* 161 */     int name_index = file.readUnsignedShort();
/* 162 */     ConstantUtf8 c = (ConstantUtf8)constant_pool.getConstant(name_index, (byte)1);
/*     */     
/* 164 */     String name = c.getBytes();
/*     */ 
/*     */     
/* 167 */     int length = file.readInt();
/*     */ 
/*     */     
/* 170 */     for (byte i = 0; i < 12; i = (byte)(i + 1)) {
/* 171 */       if (name.equals(Constants.ATTRIBUTE_NAMES[i])) {
/* 172 */         tag = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     switch (tag) {
/*     */       case -1:
/* 180 */         r = (AttributeReader)readers.get(name);
/*     */         
/* 182 */         if (r != null) {
/* 183 */           return r.createAttribute(name_index, length, file, constant_pool);
/*     */         }
/* 185 */         return new Unknown(name_index, length, file, constant_pool);
/*     */       
/*     */       case 1:
/* 188 */         return new ConstantValue(name_index, length, file, constant_pool);
/*     */       
/*     */       case 0:
/* 191 */         return new SourceFile(name_index, length, file, constant_pool);
/*     */       
/*     */       case 2:
/* 194 */         return new Code(name_index, length, file, constant_pool);
/*     */       
/*     */       case 3:
/* 197 */         return new ExceptionTable(name_index, length, file, constant_pool);
/*     */       
/*     */       case 4:
/* 200 */         return new LineNumberTable(name_index, length, file, constant_pool);
/*     */       
/*     */       case 5:
/* 203 */         return new LocalVariableTable(name_index, length, file, constant_pool);
/*     */       
/*     */       case 6:
/* 206 */         return new InnerClasses(name_index, length, file, constant_pool);
/*     */       
/*     */       case 7:
/* 209 */         return new Synthetic(name_index, length, file, constant_pool);
/*     */       
/*     */       case 8:
/* 212 */         return new Deprecated(name_index, length, file, constant_pool);
/*     */       
/*     */       case 9:
/* 215 */         return new PMGClass(name_index, length, file, constant_pool);
/*     */       
/*     */       case 10:
/* 218 */         return new Signature(name_index, length, file, constant_pool);
/*     */       
/*     */       case 11:
/* 221 */         return new StackMap(name_index, length, file, constant_pool);
/*     */     } 
/*     */     
/* 224 */     throw new InternalError("Ooops! default case reached.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLength() {
/* 231 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLength(int length) {
/* 237 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNameIndex(int name_index) {
/* 244 */     this.name_index = name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNameIndex() {
/* 250 */     return this.name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getTag() {
/* 256 */     return this.tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConstantPool getConstantPool() {
/* 262 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setConstantPool(ConstantPool constant_pool) {
/* 269 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 279 */     Object o = null;
/*     */     
/*     */     try {
/* 282 */       o = super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 284 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 287 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Attribute copy(ConstantPool paramConstantPool);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 299 */     return Constants.ATTRIBUTE_NAMES[this.tag];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Attribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */