/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.bcel.classfile.AccessFlags;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.classfile.Method;
/*     */ import org.apache.bcel.classfile.SourceFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassGen
/*     */   extends AccessFlags
/*     */   implements Cloneable
/*     */ {
/*     */   private String class_name;
/*     */   private String super_class_name;
/*     */   private String file_name;
/*  74 */   private int class_name_index = -1, superclass_name_index = -1;
/*  75 */   private int major = 45; private int minor = 3;
/*     */ 
/*     */   
/*     */   private ConstantPoolGen cp;
/*     */   
/*  80 */   private ArrayList field_vec = new ArrayList();
/*  81 */   private ArrayList method_vec = new ArrayList();
/*  82 */   private ArrayList attribute_vec = new ArrayList();
/*  83 */   private ArrayList interface_vec = new ArrayList();
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
/*     */   public ClassGen(String class_name, String super_class_name, String file_name, int access_flags, String[] interfaces) {
/*  95 */     this.class_name = class_name;
/*  96 */     this.super_class_name = super_class_name;
/*  97 */     this.file_name = file_name;
/*  98 */     this.access_flags = access_flags;
/*  99 */     this.cp = new ConstantPoolGen();
/*     */ 
/*     */     
/* 102 */     addAttribute((Attribute)new SourceFile(this.cp.addUtf8("SourceFile"), 2, this.cp.addUtf8(file_name), this.cp.getConstantPool()));
/*     */     
/* 104 */     this.class_name_index = this.cp.addClass(class_name);
/* 105 */     this.superclass_name_index = this.cp.addClass(super_class_name);
/*     */     
/* 107 */     if (interfaces != null) {
/* 108 */       for (int i = 0; i < interfaces.length; i++) {
/* 109 */         addInterface(interfaces[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassGen(JavaClass clazz) {
/* 117 */     this.class_name_index = clazz.getClassNameIndex();
/* 118 */     this.superclass_name_index = clazz.getSuperclassNameIndex();
/* 119 */     this.class_name = clazz.getClassName();
/* 120 */     this.super_class_name = clazz.getSuperclassName();
/* 121 */     this.file_name = clazz.getSourceFileName();
/* 122 */     this.access_flags = clazz.getAccessFlags();
/* 123 */     this.cp = new ConstantPoolGen(clazz.getConstantPool());
/* 124 */     this.major = clazz.getMajor();
/* 125 */     this.minor = clazz.getMinor();
/*     */     
/* 127 */     Attribute[] attributes = clazz.getAttributes();
/* 128 */     Method[] methods = clazz.getMethods();
/* 129 */     Field[] fields = clazz.getFields();
/* 130 */     String[] interfaces = clazz.getInterfaceNames();
/*     */     
/* 132 */     for (int i = 0; i < interfaces.length; i++) {
/* 133 */       addInterface(interfaces[i]);
/*     */     }
/* 135 */     for (int j = 0; j < attributes.length; j++) {
/* 136 */       addAttribute(attributes[j]);
/*     */     }
/* 138 */     for (int k = 0; k < methods.length; k++) {
/* 139 */       addMethod(methods[k]);
/*     */     }
/* 141 */     for (int m = 0; m < fields.length; m++) {
/* 142 */       addField(fields[m]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaClass getJavaClass() {
/* 149 */     int[] interfaces = getInterfaces();
/* 150 */     Field[] fields = getFields();
/* 151 */     Method[] methods = getMethods();
/* 152 */     Attribute[] attributes = getAttributes();
/*     */ 
/*     */     
/* 155 */     ConstantPool cp = this.cp.getFinalConstantPool();
/*     */     
/* 157 */     return new JavaClass(this.class_name_index, this.superclass_name_index, this.file_name, this.major, this.minor, this.access_flags, cp, interfaces, fields, methods, attributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInterface(String name) {
/* 167 */     this.interface_vec.add(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeInterface(String name) {
/* 175 */     this.interface_vec.remove(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMajor() {
/* 181 */     return this.major;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMajor(int major) {
/* 187 */     this.major = major;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinor(int minor) {
/* 194 */     this.minor = minor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinor() {
/* 200 */     return this.minor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(Attribute a) {
/* 206 */     this.attribute_vec.add(a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMethod(Method m) {
/* 212 */     this.method_vec.add(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEmptyConstructor(int access_flags) {
/* 221 */     InstructionList il = new InstructionList();
/* 222 */     il.append(InstructionConstants.THIS);
/* 223 */     il.append(new INVOKESPECIAL(this.cp.addMethodref(this.super_class_name, "<init>", "()V")));
/*     */     
/* 225 */     il.append(InstructionConstants.RETURN);
/*     */     
/* 227 */     MethodGen mg = new MethodGen(access_flags, Type.VOID, Type.NO_ARGS, null, "<init>", this.class_name, il, this.cp);
/*     */     
/* 229 */     mg.setMaxStack(1);
/* 230 */     addMethod(mg.getMethod());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addField(Field f) {
/* 237 */     this.field_vec.add(f);
/*     */   } public boolean containsField(Field f) {
/* 239 */     return this.field_vec.contains(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public Field containsField(String name) {
/* 244 */     for (Iterator e = this.field_vec.iterator(); e.hasNext(); ) {
/* 245 */       Field f = e.next();
/* 246 */       if (f.getName().equals(name)) {
/* 247 */         return f;
/*     */       }
/*     */     } 
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Method containsMethod(String name, String signature) {
/* 256 */     for (Iterator e = this.method_vec.iterator(); e.hasNext(); ) {
/* 257 */       Method m = e.next();
/* 258 */       if (m.getName().equals(name) && m.getSignature().equals(signature)) {
/* 259 */         return m;
/*     */       }
/*     */     } 
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAttribute(Attribute a) {
/* 269 */     this.attribute_vec.remove(a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeMethod(Method m) {
/* 275 */     this.method_vec.remove(m);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceMethod(Method old, Method new_) {
/* 281 */     if (new_ == null) {
/* 282 */       throw new ClassGenException("Replacement method must not be null");
/*     */     }
/* 284 */     int i = this.method_vec.indexOf(old);
/*     */     
/* 286 */     if (i < 0) {
/* 287 */       this.method_vec.add(new_);
/*     */     } else {
/* 289 */       this.method_vec.set(i, new_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceField(Field old, Field new_) {
/* 296 */     if (new_ == null) {
/* 297 */       throw new ClassGenException("Replacement method must not be null");
/*     */     }
/* 299 */     int i = this.field_vec.indexOf(old);
/*     */     
/* 301 */     if (i < 0) {
/* 302 */       this.field_vec.add(new_);
/*     */     } else {
/* 304 */       this.field_vec.set(i, new_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeField(Field f) {
/* 311 */     this.field_vec.remove(f);
/*     */   }
/* 313 */   public String getClassName() { return this.class_name; }
/* 314 */   public String getSuperclassName() { return this.super_class_name; } public String getFileName() {
/* 315 */     return this.file_name;
/*     */   }
/*     */   public void setClassName(String name) {
/* 318 */     this.class_name = name.replace('/', '.');
/* 319 */     this.class_name_index = this.cp.addClass(name);
/*     */   }
/*     */   
/*     */   public void setSuperclassName(String name) {
/* 323 */     this.super_class_name = name.replace('/', '.');
/* 324 */     this.superclass_name_index = this.cp.addClass(name);
/*     */   }
/*     */   
/*     */   public Method[] getMethods() {
/* 328 */     Method[] methods = new Method[this.method_vec.size()];
/* 329 */     this.method_vec.toArray((Object[])methods);
/* 330 */     return methods;
/*     */   }
/*     */   
/*     */   public void setMethods(Method[] methods) {
/* 334 */     this.method_vec.clear();
/* 335 */     for (int m = 0; m < methods.length; m++)
/* 336 */       addMethod(methods[m]); 
/*     */   }
/*     */   
/*     */   public void setMethodAt(Method method, int pos) {
/* 340 */     this.method_vec.set(pos, method);
/*     */   }
/*     */   
/*     */   public Method getMethodAt(int pos) {
/* 344 */     return this.method_vec.get(pos);
/*     */   }
/*     */   
/*     */   public String[] getInterfaceNames() {
/* 348 */     int size = this.interface_vec.size();
/* 349 */     String[] interfaces = new String[size];
/*     */     
/* 351 */     this.interface_vec.toArray((Object[])interfaces);
/* 352 */     return interfaces;
/*     */   }
/*     */   
/*     */   public int[] getInterfaces() {
/* 356 */     int size = this.interface_vec.size();
/* 357 */     int[] interfaces = new int[size];
/*     */     
/* 359 */     for (int i = 0; i < size; i++) {
/* 360 */       interfaces[i] = this.cp.addClass((String)this.interface_vec.get(i));
/*     */     }
/* 362 */     return interfaces;
/*     */   }
/*     */   
/*     */   public Field[] getFields() {
/* 366 */     Field[] fields = new Field[this.field_vec.size()];
/* 367 */     this.field_vec.toArray((Object[])fields);
/* 368 */     return fields;
/*     */   }
/*     */   
/*     */   public Attribute[] getAttributes() {
/* 372 */     Attribute[] attributes = new Attribute[this.attribute_vec.size()];
/* 373 */     this.attribute_vec.toArray((Object[])attributes);
/* 374 */     return attributes;
/*     */   }
/*     */   public ConstantPoolGen getConstantPool() {
/* 377 */     return this.cp;
/*     */   } public void setConstantPool(ConstantPoolGen constant_pool) {
/* 379 */     this.cp = constant_pool;
/*     */   }
/*     */   
/*     */   public void setClassNameIndex(int class_name_index) {
/* 383 */     this.class_name_index = class_name_index;
/* 384 */     this.class_name = this.cp.getConstantPool().getConstantString(class_name_index, (byte)7).replace('/', '.');
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuperclassNameIndex(int superclass_name_index) {
/* 389 */     this.superclass_name_index = superclass_name_index;
/* 390 */     this.super_class_name = this.cp.getConstantPool().getConstantString(superclass_name_index, (byte)7).replace('/', '.');
/*     */   }
/*     */   
/*     */   public int getSuperclassNameIndex() {
/* 394 */     return this.superclass_name_index;
/*     */   } public int getClassNameIndex() {
/* 396 */     return this.class_name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObserver(ClassObserver o) {
/* 403 */     if (this.observers == null) {
/* 404 */       this.observers = new ArrayList();
/*     */     }
/* 406 */     this.observers.add(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeObserver(ClassObserver o) {
/* 412 */     if (this.observers != null) {
/* 413 */       this.observers.remove(o);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 421 */     if (this.observers != null)
/* 422 */       for (Iterator e = this.observers.iterator(); e.hasNext();)
/* 423 */         ((ClassObserver)e.next()).notify(this);  
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 428 */       return super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 430 */       System.err.println(e);
/* 431 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ClassGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */