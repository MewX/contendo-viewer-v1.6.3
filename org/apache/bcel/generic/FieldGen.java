/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantObject;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantValue;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.classfile.Utility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldGen
/*     */   extends FieldGenOrMethodGen
/*     */ {
/*  72 */   private Object value = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList observers;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldGen(int access_flags, Type type, String name, ConstantPoolGen cp) {
/*  85 */     setAccessFlags(access_flags);
/*  86 */     setType(type);
/*  87 */     setName(name);
/*  88 */     setConstantPool(cp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldGen(Field field, ConstantPoolGen cp) {
/*  98 */     this(field.getAccessFlags(), Type.getType(field.getSignature()), field.getName(), cp);
/*     */     
/* 100 */     Attribute[] attrs = field.getAttributes();
/*     */     
/* 102 */     for (int i = 0; i < attrs.length; i++) {
/* 103 */       if (attrs[i] instanceof ConstantValue) {
/* 104 */         setValue(((ConstantValue)attrs[i]).getConstantValueIndex());
/*     */       } else {
/* 106 */         addAttribute(attrs[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void setValue(int index) {
/* 111 */     ConstantPool cp = this.cp.getConstantPool();
/* 112 */     Constant c = cp.getConstant(index);
/* 113 */     this.value = ((ConstantObject)c).getConstantValue(cp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitValue(String str) {
/* 121 */     checkType(new ObjectType("java.lang.String"));
/*     */     
/* 123 */     if (str != null)
/* 124 */       this.value = str; 
/*     */   }
/*     */   
/*     */   public void setInitValue(long l) {
/* 128 */     checkType(Type.LONG);
/*     */     
/* 130 */     if (l != 0L)
/* 131 */       this.value = new Long(l); 
/*     */   }
/*     */   
/*     */   public void setInitValue(int i) {
/* 135 */     checkType(Type.INT);
/*     */     
/* 137 */     if (i != 0)
/* 138 */       this.value = new Integer(i); 
/*     */   }
/*     */   
/*     */   public void setInitValue(short s) {
/* 142 */     checkType(Type.SHORT);
/*     */     
/* 144 */     if (s != 0)
/* 145 */       this.value = new Integer(s); 
/*     */   }
/*     */   
/*     */   public void setInitValue(char c) {
/* 149 */     checkType(Type.CHAR);
/*     */     
/* 151 */     if (c != '\000')
/* 152 */       this.value = new Integer(c); 
/*     */   }
/*     */   
/*     */   public void setInitValue(byte b) {
/* 156 */     checkType(Type.BYTE);
/*     */     
/* 158 */     if (b != 0)
/* 159 */       this.value = new Integer(b); 
/*     */   }
/*     */   
/*     */   public void setInitValue(boolean b) {
/* 163 */     checkType(Type.BOOLEAN);
/*     */     
/* 165 */     if (b)
/* 166 */       this.value = new Integer(1); 
/*     */   }
/*     */   
/*     */   public void setInitValue(float f) {
/* 170 */     checkType(Type.FLOAT);
/*     */     
/* 172 */     if (f != 0.0D)
/* 173 */       this.value = new Float(f); 
/*     */   }
/*     */   
/*     */   public void setInitValue(double d) {
/* 177 */     checkType(Type.DOUBLE);
/*     */     
/* 179 */     if (d != 0.0D) {
/* 180 */       this.value = new Double(d);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelInitValue() {
/* 186 */     this.value = null;
/*     */   }
/*     */   
/*     */   private void checkType(Type atype) {
/* 190 */     if (this.type == null) {
/* 191 */       throw new ClassGenException("You haven't defined the type of the field yet");
/*     */     }
/* 193 */     if (!isFinal()) {
/* 194 */       throw new ClassGenException("Only final fields may have an initial value!");
/*     */     }
/* 196 */     if (!this.type.equals(atype)) {
/* 197 */       throw new ClassGenException("Types are not compatible: " + this.type + " vs. " + atype);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Field getField() {
/* 204 */     String signature = getSignature();
/* 205 */     int name_index = this.cp.addUtf8(this.name);
/* 206 */     int signature_index = this.cp.addUtf8(signature);
/*     */     
/* 208 */     if (this.value != null) {
/* 209 */       checkType(this.type);
/* 210 */       int index = addConstant();
/* 211 */       addAttribute((Attribute)new ConstantValue(this.cp.addUtf8("ConstantValue"), 2, index, this.cp.getConstantPool()));
/*     */     } 
/*     */ 
/*     */     
/* 215 */     return new Field(this.access_flags, name_index, signature_index, getAttributes(), this.cp.getConstantPool());
/*     */   }
/*     */ 
/*     */   
/*     */   private int addConstant() {
/* 220 */     switch (this.type.getType()) { case 4: case 5: case 8:
/*     */       case 9:
/*     */       case 10:
/* 223 */         return this.cp.addInteger(((Integer)this.value).intValue());
/*     */       
/*     */       case 6:
/* 226 */         return this.cp.addFloat(((Float)this.value).floatValue());
/*     */       
/*     */       case 7:
/* 229 */         return this.cp.addDouble(((Double)this.value).doubleValue());
/*     */       
/*     */       case 11:
/* 232 */         return this.cp.addLong(((Long)this.value).longValue());
/*     */       
/*     */       case 14:
/* 235 */         return this.cp.addString((String)this.value); }
/*     */ 
/*     */     
/* 238 */     throw new RuntimeException("Oops: Unhandled : " + this.type.getType());
/*     */   }
/*     */   
/*     */   public String getSignature() {
/* 242 */     return this.type.getSignature();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObserver(FieldObserver o) {
/* 249 */     if (this.observers == null) {
/* 250 */       this.observers = new ArrayList();
/*     */     }
/* 252 */     this.observers.add(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeObserver(FieldObserver o) {
/* 258 */     if (this.observers != null) {
/* 259 */       this.observers.remove(o);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 267 */     if (this.observers != null)
/* 268 */       for (Iterator e = this.observers.iterator(); e.hasNext();)
/* 269 */         ((FieldObserver)e.next()).notify(this);  
/*     */   }
/*     */   
/*     */   public String getInitValue() {
/* 273 */     if (this.value != null) {
/* 274 */       return this.value.toString();
/*     */     }
/* 276 */     return null;
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
/*     */   public final String toString() {
/* 288 */     String access = Utility.accessToString(this.access_flags);
/* 289 */     access = access.equals("") ? "" : (access + " ");
/* 290 */     String signature = this.type.toString();
/* 291 */     String name = getName();
/*     */     
/* 293 */     StringBuffer buf = new StringBuffer(access + signature + " " + name);
/* 294 */     String value = getInitValue();
/*     */     
/* 296 */     if (value != null) {
/* 297 */       buf.append(" = " + value);
/*     */     }
/* 299 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldGen copy(ConstantPoolGen cp) {
/* 305 */     FieldGen fg = (FieldGen)clone();
/*     */     
/* 307 */     fg.setConstantPool(cp);
/* 308 */     return fg;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/FieldGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */