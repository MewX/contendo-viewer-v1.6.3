/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantCP;
/*     */ import org.apache.bcel.classfile.ConstantClass;
/*     */ import org.apache.bcel.classfile.ConstantDouble;
/*     */ import org.apache.bcel.classfile.ConstantFieldref;
/*     */ import org.apache.bcel.classfile.ConstantFloat;
/*     */ import org.apache.bcel.classfile.ConstantInteger;
/*     */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*     */ import org.apache.bcel.classfile.ConstantLong;
/*     */ import org.apache.bcel.classfile.ConstantMethodref;
/*     */ import org.apache.bcel.classfile.ConstantNameAndType;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantString;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstantPoolGen
/*     */ {
/*  76 */   protected int size = 1024;
/*  77 */   protected Constant[] constants = new Constant[this.size];
/*  78 */   protected int index = 1; private static final String METHODREF_DELIM = ":"; private static final String IMETHODREF_DELIM = "#"; private static final String FIELDREF_DELIM = "&"; private static final String NAT_DELIM = "%"; private HashMap string_table;
/*     */   private HashMap class_table;
/*     */   private HashMap utf8_table;
/*     */   private HashMap n_a_t_table;
/*     */   private HashMap cp_table;
/*     */   
/*     */   private static class Index { int index;
/*     */     
/*     */     Index(int i) {
/*  87 */       this.index = i;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantPoolGen(ConstantPool cp) {
/* 159 */     this(cp.getConstantPool());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void adjustSize() {
/* 170 */     if (this.index + 3 >= this.size) {
/* 171 */       Constant[] cs = this.constants;
/*     */       
/* 173 */       this.size *= 2;
/* 174 */       this.constants = new Constant[this.size];
/* 175 */       System.arraycopy(cs, 0, this.constants, 0, this.index);
/*     */     } 
/*     */   }
/*     */   
/* 179 */   public ConstantPoolGen(Constant[] cs) { this.string_table = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     this.class_table = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     this.utf8_table = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 474 */     this.n_a_t_table = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     this.cp_table = new HashMap(); if (cs.length > this.size) { this.size = cs.length; this.constants = new Constant[this.size]; }  System.arraycopy(cs, 0, this.constants, 0, cs.length); if (cs.length > 0) this.index = cs.length;  for (int i = 1; i < this.index; i++) { Constant c = this.constants[i]; if (c instanceof ConstantString) { ConstantString s = (ConstantString)c; ConstantUtf8 u8 = (ConstantUtf8)this.constants[s.getStringIndex()]; this.string_table.put(u8.getBytes(), new Index(i)); } else if (c instanceof ConstantClass) { ConstantClass s = (ConstantClass)c; ConstantUtf8 u8 = (ConstantUtf8)this.constants[s.getNameIndex()]; this.class_table.put(u8.getBytes(), new Index(i)); } else if (c instanceof ConstantNameAndType) { ConstantNameAndType n = (ConstantNameAndType)c; ConstantUtf8 u8 = (ConstantUtf8)this.constants[n.getNameIndex()]; ConstantUtf8 u8_2 = (ConstantUtf8)this.constants[n.getSignatureIndex()]; this.n_a_t_table.put(u8.getBytes() + "%" + u8_2.getBytes(), new Index(i)); } else if (c instanceof ConstantUtf8) { ConstantUtf8 u = (ConstantUtf8)c; this.utf8_table.put(u.getBytes(), new Index(i)); } else if (c instanceof ConstantCP) { ConstantCP m = (ConstantCP)c; ConstantClass clazz = (ConstantClass)this.constants[m.getClassIndex()]; ConstantNameAndType n = (ConstantNameAndType)this.constants[m.getNameAndTypeIndex()]; ConstantUtf8 u8 = (ConstantUtf8)this.constants[clazz.getNameIndex()]; String class_name = u8.getBytes().replace('/', '.'); u8 = (ConstantUtf8)this.constants[n.getNameIndex()]; String method_name = u8.getBytes(); u8 = (ConstantUtf8)this.constants[n.getSignatureIndex()]; String signature = u8.getBytes(); String delim = ":"; if (c instanceof ConstantInterfaceMethodref) { delim = "#"; } else if (c instanceof ConstantFieldref) { delim = "&"; }  this.cp_table.put(class_name + delim + method_name + delim + signature, new Index(i)); }  }  } public ConstantPoolGen() { this.string_table = new HashMap(); this.class_table = new HashMap(); this.utf8_table = new HashMap(); this.n_a_t_table = new HashMap(); this.cp_table = new HashMap(); }
/*     */   public int lookupString(String str) { Index index = (Index)this.string_table.get(str); return (index != null) ? index.index : -1; }
/*     */   public int addString(String str) { int ret; if ((ret = lookupString(str)) != -1) return ret;  adjustSize(); ConstantUtf8 u8 = new ConstantUtf8(str); ConstantString s = new ConstantString(this.index); this.constants[this.index++] = (Constant)u8; ret = this.index; this.constants[this.index++] = (Constant)s; this.string_table.put(str, new Index(ret)); return ret; }
/*     */   public int lookupClass(String str) { Index index = (Index)this.class_table.get(str.replace('.', '/')); return (index != null) ? index.index : -1; }
/*     */   private int addClass_(String clazz) { int ret; if ((ret = lookupClass(clazz)) != -1) return ret;  adjustSize(); ConstantClass c = new ConstantClass(addUtf8(clazz)); ret = this.index; this.constants[this.index++] = (Constant)c; this.class_table.put(clazz, new Index(ret)); return ret; }
/*     */   public int addClass(String str) { return addClass_(str.replace('.', '/')); }
/*     */   public int addClass(ObjectType type) { return addClass(type.getClassName()); }
/*     */   public int addArrayClass(ArrayType type) { return addClass_(type.getSignature()); }
/*     */   public int lookupInteger(int n) { for (int i = 1; i < this.index; i++) { if (this.constants[i] instanceof ConstantInteger) { ConstantInteger c = (ConstantInteger)this.constants[i]; if (c.getBytes() == n)
/*     */           return i;  }  }  return -1; }
/*     */   public int addInteger(int n) { int ret; if ((ret = lookupInteger(n)) != -1)
/* 524 */       return ret;  adjustSize(); ret = this.index; this.constants[this.index++] = (Constant)new ConstantInteger(n); return ret; } public int lookupMethodref(String class_name, String method_name, String signature) { Index index = (Index)this.cp_table.get(class_name + ":" + method_name + ":" + signature);
/*     */     
/* 526 */     return (index != null) ? index.index : -1; }
/*     */   public int lookupFloat(float n) { for (int i = 1; i < this.index; i++) { if (this.constants[i] instanceof ConstantFloat) { ConstantFloat c = (ConstantFloat)this.constants[i]; if (c.getBytes() == n) return i;  }  }  return -1; }
/*     */   public int addFloat(float n) { int ret; if ((ret = lookupFloat(n)) != -1) return ret;  adjustSize(); ret = this.index; this.constants[this.index++] = (Constant)new ConstantFloat(n); return ret; }
/*     */   public int lookupUtf8(String n) { Index index = (Index)this.utf8_table.get(n); return (index != null) ? index.index : -1; }
/* 530 */   public int addUtf8(String n) { int ret; if ((ret = lookupUtf8(n)) != -1) return ret;  adjustSize(); ret = this.index; this.constants[this.index++] = (Constant)new ConstantUtf8(n); this.utf8_table.put(n, new Index(ret)); return ret; } public int lookupLong(long n) { for (int i = 1; i < this.index; i++) { if (this.constants[i] instanceof ConstantLong) { ConstantLong c = (ConstantLong)this.constants[i]; if (c.getBytes() == n) return i;  }  }  return -1; } public int addLong(long n) { int ret; if ((ret = lookupLong(n)) != -1) return ret;  adjustSize(); ret = this.index; this.constants[this.index] = (Constant)new ConstantLong(n); this.index += 2; return ret; } public int lookupDouble(double n) { for (int i = 1; i < this.index; i++) { if (this.constants[i] instanceof ConstantDouble) { ConstantDouble c = (ConstantDouble)this.constants[i]; if (c.getBytes() == n) return i;  }  }  return -1; } public int addDouble(double n) { int ret; if ((ret = lookupDouble(n)) != -1) return ret;  adjustSize(); ret = this.index; this.constants[this.index] = (Constant)new ConstantDouble(n); this.index += 2; return ret; } public int lookupNameAndType(String name, String signature) { Index index = (Index)this.n_a_t_table.get(name + "%" + signature); return (index != null) ? index.index : -1; } public int addNameAndType(String name, String signature) { int ret; if ((ret = lookupNameAndType(name, signature)) != -1) return ret;  adjustSize(); int name_index = addUtf8(name); int signature_index = addUtf8(signature); ret = this.index; this.constants[this.index++] = (Constant)new ConstantNameAndType(name_index, signature_index); this.n_a_t_table.put(name + "%" + signature, new Index(ret)); return ret; } public int lookupMethodref(MethodGen method) { return lookupMethodref(method.getClassName(), method.getName(), method.getSignature()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addMethodref(String class_name, String method_name, String signature) {
/*     */     int ret;
/* 544 */     if ((ret = lookupMethodref(class_name, method_name, signature)) != -1) {
/* 545 */       return ret;
/*     */     }
/* 547 */     adjustSize();
/*     */     
/* 549 */     int name_and_type_index = addNameAndType(method_name, signature);
/* 550 */     int class_index = addClass(class_name);
/* 551 */     ret = this.index;
/* 552 */     this.constants[this.index++] = (Constant)new ConstantMethodref(class_index, name_and_type_index);
/*     */     
/* 554 */     this.cp_table.put(class_name + ":" + method_name + ":" + signature, new Index(ret));
/*     */ 
/*     */     
/* 557 */     return ret;
/*     */   }
/*     */   
/*     */   public int addMethodref(MethodGen method) {
/* 561 */     return addMethodref(method.getClassName(), method.getName(), method.getSignature());
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
/*     */   public int lookupInterfaceMethodref(String class_name, String method_name, String signature) {
/* 574 */     Index index = (Index)this.cp_table.get(class_name + "#" + method_name + "#" + signature);
/*     */     
/* 576 */     return (index != null) ? index.index : -1;
/*     */   }
/*     */   
/*     */   public int lookupInterfaceMethodref(MethodGen method) {
/* 580 */     return lookupInterfaceMethodref(method.getClassName(), method.getName(), method.getSignature());
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
/*     */   public int addInterfaceMethodref(String class_name, String method_name, String signature) {
/*     */     int ret;
/* 594 */     if ((ret = lookupInterfaceMethodref(class_name, method_name, signature)) != -1) {
/* 595 */       return ret;
/*     */     }
/* 597 */     adjustSize();
/*     */     
/* 599 */     int class_index = addClass(class_name);
/* 600 */     int name_and_type_index = addNameAndType(method_name, signature);
/* 601 */     ret = this.index;
/* 602 */     this.constants[this.index++] = (Constant)new ConstantInterfaceMethodref(class_index, name_and_type_index);
/*     */     
/* 604 */     this.cp_table.put(class_name + "#" + method_name + "#" + signature, new Index(ret));
/*     */ 
/*     */     
/* 607 */     return ret;
/*     */   }
/*     */   
/*     */   public int addInterfaceMethodref(MethodGen method) {
/* 611 */     return addInterfaceMethodref(method.getClassName(), method.getName(), method.getSignature());
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
/*     */   public int lookupFieldref(String class_name, String field_name, String signature) {
/* 624 */     Index index = (Index)this.cp_table.get(class_name + "&" + field_name + "&" + signature);
/*     */     
/* 626 */     return (index != null) ? index.index : -1;
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
/*     */   public int addFieldref(String class_name, String field_name, String signature) {
/*     */     int ret;
/* 640 */     if ((ret = lookupFieldref(class_name, field_name, signature)) != -1) {
/* 641 */       return ret;
/*     */     }
/* 643 */     adjustSize();
/*     */     
/* 645 */     int class_index = addClass(class_name);
/* 646 */     int name_and_type_index = addNameAndType(field_name, signature);
/* 647 */     ret = this.index;
/* 648 */     this.constants[this.index++] = (Constant)new ConstantFieldref(class_index, name_and_type_index);
/*     */     
/* 650 */     this.cp_table.put(class_name + "&" + field_name + "&" + signature, new Index(ret));
/*     */     
/* 652 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Constant getConstant(int i) {
/* 659 */     return this.constants[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstant(int i, Constant c) {
/* 667 */     this.constants[i] = c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantPool getConstantPool() {
/* 673 */     return new ConstantPool(this.constants);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 680 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantPool getFinalConstantPool() {
/* 687 */     Constant[] cs = new Constant[this.index];
/*     */     
/* 689 */     System.arraycopy(this.constants, 0, cs, 0, this.index);
/*     */     
/* 691 */     return new ConstantPool(cs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString()
/*     */   {
/* 698 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 700 */     for (int i = 1; i < this.index; i++) {
/* 701 */       buf.append(i + ")" + this.constants[i] + "\n");
/*     */     }
/* 703 */     return buf.toString(); } public int addConstant(Constant c, ConstantPoolGen cp) { ConstantString constantString; ConstantClass s; ConstantNameAndType n; ConstantCP m; ConstantUtf8 u8;
/*     */     ConstantClass clazz;
/*     */     ConstantUtf8 u8_2;
/*     */     ConstantNameAndType constantNameAndType1;
/*     */     ConstantUtf8 constantUtf81;
/*     */     String class_name, name, signature;
/* 709 */     Constant[] constants = cp.getConstantPool().getConstantPool();
/*     */     
/* 711 */     switch (c.getTag()) {
/*     */       case 8:
/* 713 */         constantString = (ConstantString)c;
/* 714 */         u8 = (ConstantUtf8)constants[constantString.getStringIndex()];
/*     */         
/* 716 */         return addString(u8.getBytes());
/*     */ 
/*     */       
/*     */       case 7:
/* 720 */         s = (ConstantClass)c;
/* 721 */         u8 = (ConstantUtf8)constants[s.getNameIndex()];
/*     */         
/* 723 */         return addClass(u8.getBytes());
/*     */ 
/*     */       
/*     */       case 12:
/* 727 */         n = (ConstantNameAndType)c;
/* 728 */         u8 = (ConstantUtf8)constants[n.getNameIndex()];
/* 729 */         u8_2 = (ConstantUtf8)constants[n.getSignatureIndex()];
/*     */         
/* 731 */         return addNameAndType(u8.getBytes(), u8_2.getBytes());
/*     */ 
/*     */       
/*     */       case 1:
/* 735 */         return addUtf8(((ConstantUtf8)c).getBytes());
/*     */       
/*     */       case 6:
/* 738 */         return addDouble(((ConstantDouble)c).getBytes());
/*     */       
/*     */       case 4:
/* 741 */         return addFloat(((ConstantFloat)c).getBytes());
/*     */       
/*     */       case 5:
/* 744 */         return addLong(((ConstantLong)c).getBytes());
/*     */       
/*     */       case 3:
/* 747 */         return addInteger(((ConstantInteger)c).getBytes());
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/* 751 */         m = (ConstantCP)c;
/* 752 */         clazz = (ConstantClass)constants[m.getClassIndex()];
/* 753 */         constantNameAndType1 = (ConstantNameAndType)constants[m.getNameAndTypeIndex()];
/* 754 */         constantUtf81 = (ConstantUtf8)constants[clazz.getNameIndex()];
/* 755 */         class_name = constantUtf81.getBytes().replace('/', '.');
/*     */         
/* 757 */         constantUtf81 = (ConstantUtf8)constants[constantNameAndType1.getNameIndex()];
/* 758 */         name = constantUtf81.getBytes();
/*     */         
/* 760 */         constantUtf81 = (ConstantUtf8)constants[constantNameAndType1.getSignatureIndex()];
/* 761 */         signature = constantUtf81.getBytes();
/*     */         
/* 763 */         switch (c.getTag()) {
/*     */           case 11:
/* 765 */             return addInterfaceMethodref(class_name, name, signature);
/*     */           
/*     */           case 10:
/* 768 */             return addMethodref(class_name, name, signature);
/*     */           
/*     */           case 9:
/* 771 */             return addFieldref(class_name, name, signature);
/*     */         } 
/*     */         
/* 774 */         throw new RuntimeException("Unknown constant type " + c);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 779 */     throw new RuntimeException("Unknown constant type " + c); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ConstantPoolGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */