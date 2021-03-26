/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.bcel.Repository;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JavaClass
/*     */   extends AccessFlags
/*     */   implements Cloneable, Node
/*     */ {
/*     */   private String file_name;
/*     */   private String package_name;
/*  74 */   private String source_file_name = "<Unknown>";
/*     */   
/*     */   private int class_name_index;
/*     */   
/*     */   private int superclass_name_index;
/*     */   
/*     */   private String class_name;
/*     */   
/*     */   private String superclass_name;
/*     */   
/*     */   private int major;
/*     */   private int minor;
/*  86 */   private byte source = 1; private ConstantPool constant_pool; private int[] interfaces; private String[] interface_names; private Field[] fields;
/*     */   private Method[] methods;
/*     */   private Attribute[] attributes;
/*     */   public static final byte HEAP = 1;
/*     */   public static final byte FILE = 2;
/*     */   public static final byte ZIP = 3;
/*     */   static boolean debug = false;
/*  93 */   static char sep = '/';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaClass(int class_name_index, int superclass_name_index, String file_name, int major, int minor, int access_flags, ConstantPool constant_pool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) {
/* 124 */     if (interfaces == null)
/* 125 */       interfaces = new int[0]; 
/* 126 */     if (attributes == null)
/* 127 */       this.attributes = new Attribute[0]; 
/* 128 */     if (fields == null)
/* 129 */       fields = new Field[0]; 
/* 130 */     if (methods == null) {
/* 131 */       methods = new Method[0];
/*     */     }
/* 133 */     this.class_name_index = class_name_index;
/* 134 */     this.superclass_name_index = superclass_name_index;
/* 135 */     this.file_name = file_name;
/* 136 */     this.major = major;
/* 137 */     this.minor = minor;
/* 138 */     this.access_flags = access_flags;
/* 139 */     this.constant_pool = constant_pool;
/* 140 */     this.interfaces = interfaces;
/* 141 */     this.fields = fields;
/* 142 */     this.methods = methods;
/* 143 */     this.attributes = attributes;
/* 144 */     this.source = source;
/*     */ 
/*     */     
/* 147 */     for (int i = 0; i < attributes.length; i++) {
/* 148 */       if (attributes[i] instanceof SourceFile) {
/* 149 */         this.source_file_name = ((SourceFile)attributes[i]).getSourceFileName();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     this.class_name = constant_pool.getConstantString(class_name_index, (byte)7);
/*     */     
/* 163 */     this.class_name = Utility.compactClassName(this.class_name, false);
/*     */     
/* 165 */     int index = this.class_name.lastIndexOf('.');
/* 166 */     if (index < 0) {
/* 167 */       this.package_name = "";
/*     */     } else {
/* 169 */       this.package_name = this.class_name.substring(0, index);
/*     */     } 
/* 171 */     if (superclass_name_index > 0) {
/* 172 */       this.superclass_name = constant_pool.getConstantString(superclass_name_index, (byte)7);
/*     */       
/* 174 */       this.superclass_name = Utility.compactClassName(this.superclass_name, false);
/*     */     } else {
/*     */       
/* 177 */       this.superclass_name = "java.lang.Object";
/*     */     } 
/* 179 */     this.interface_names = new String[interfaces.length];
/* 180 */     for (int j = 0; j < interfaces.length; j++) {
/* 181 */       String str = constant_pool.getConstantString(interfaces[j], (byte)7);
/* 182 */       this.interface_names[j] = Utility.compactClassName(str, false);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaClass(int class_name_index, int superclass_name_index, String file_name, int major, int minor, int access_flags, ConstantPool constant_pool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
/* 212 */     this(class_name_index, superclass_name_index, file_name, major, minor, access_flags, constant_pool, interfaces, fields, methods, attributes, (byte)1);
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
/*     */   public void accept(Visitor v) {
/* 225 */     v.visitJavaClass(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static final void Debug(String str) {
/* 231 */     if (debug) {
/* 232 */       System.out.println(str);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(File file) throws IOException {
/* 243 */     String parent = file.getParent();
/*     */     
/* 245 */     if (parent != null) {
/* 246 */       File dir = new File(parent);
/*     */       
/* 248 */       if (dir != null) {
/* 249 */         dir.mkdirs();
/*     */       }
/*     */     } 
/* 252 */     dump(new DataOutputStream(new FileOutputStream(file)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(String file_name) throws IOException {
/* 263 */     dump(new File(file_name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/* 270 */     ByteArrayOutputStream s = new ByteArrayOutputStream();
/* 271 */     DataOutputStream ds = new DataOutputStream(s);
/*     */ 
/*     */     
/* 274 */     try { dump(ds);
/* 275 */       ds.close(); }
/* 276 */     catch (IOException e) { e.printStackTrace(); }
/*     */     
/* 278 */     return s.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(OutputStream file) throws IOException {
/* 288 */     dump(new DataOutputStream(file));
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
/* 299 */     file.writeInt(-889275714);
/* 300 */     file.writeShort(this.minor);
/* 301 */     file.writeShort(this.major);
/*     */     
/* 303 */     this.constant_pool.dump(file);
/*     */     
/* 305 */     file.writeShort(this.access_flags);
/* 306 */     file.writeShort(this.class_name_index);
/* 307 */     file.writeShort(this.superclass_name_index);
/*     */     
/* 309 */     file.writeShort(this.interfaces.length);
/* 310 */     for (int i = 0; i < this.interfaces.length; i++) {
/* 311 */       file.writeShort(this.interfaces[i]);
/*     */     }
/* 313 */     file.writeShort(this.fields.length);
/* 314 */     for (int j = 0; j < this.fields.length; j++) {
/* 315 */       this.fields[j].dump(file);
/*     */     }
/* 317 */     file.writeShort(this.methods.length);
/* 318 */     for (int k = 0; k < this.methods.length; k++) {
/* 319 */       this.methods[k].dump(file);
/*     */     }
/* 321 */     if (this.attributes != null) {
/* 322 */       file.writeShort(this.attributes.length);
/* 323 */       for (int m = 0; m < this.attributes.length; m++) {
/* 324 */         this.attributes[m].dump(file);
/*     */       }
/*     */     } else {
/* 327 */       file.writeShort(0);
/*     */     } 
/* 329 */     file.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute[] getAttributes() {
/* 335 */     return this.attributes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 340 */     return this.class_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 345 */     return this.package_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getClassNameIndex() {
/* 350 */     return this.class_name_index;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConstantPool getConstantPool() {
/* 355 */     return this.constant_pool;
/*     */   }
/*     */ 
/*     */   
/*     */   public Field[] getFields() {
/* 360 */     return this.fields;
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 364 */     return this.file_name;
/*     */   }
/*     */   
/*     */   public String[] getInterfaceNames() {
/* 368 */     return this.interface_names;
/*     */   }
/*     */   
/*     */   public int[] getInterfaces() {
/* 372 */     return this.interfaces;
/*     */   }
/*     */   
/*     */   public int getMajor() {
/* 376 */     return this.major;
/*     */   }
/*     */   
/*     */   public Method[] getMethods() {
/* 380 */     return this.methods;
/*     */   }
/*     */   
/*     */   public int getMinor() {
/* 384 */     return this.minor;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSourceFileName() {
/* 389 */     return this.source_file_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSuperclassName() {
/* 394 */     return this.superclass_name;
/*     */   }
/*     */   
/*     */   public int getSuperclassNameIndex() {
/* 398 */     return this.superclass_name_index;
/*     */   }
/*     */   
/*     */   static {
/* 402 */     String debug = System.getProperty("JavaClass.debug");
/*     */     
/* 404 */     if (debug != null) {
/* 405 */       JavaClass.debug = (new Boolean(debug)).booleanValue();
/*     */     }
/*     */     
/* 408 */     String sep = System.getProperty("file.separator");
/*     */     
/* 410 */     if (sep != null) {
/*     */       try {
/* 412 */         JavaClass.sep = sep.charAt(0);
/* 413 */       } catch (StringIndexOutOfBoundsException e) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(Attribute[] attributes) {
/* 420 */     this.attributes = attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassName(String class_name) {
/* 426 */     this.class_name = class_name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassNameIndex(int class_name_index) {
/* 432 */     this.class_name_index = class_name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstantPool(ConstantPool constant_pool) {
/* 438 */     this.constant_pool = constant_pool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFields(Field[] fields) {
/* 444 */     this.fields = fields;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileName(String file_name) {
/* 450 */     this.file_name = file_name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterfaceNames(String[] interface_names) {
/* 456 */     this.interface_names = interface_names;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterfaces(int[] interfaces) {
/* 462 */     this.interfaces = interfaces;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMajor(int major) {
/* 468 */     this.major = major;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMethods(Method[] methods) {
/* 474 */     this.methods = methods;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinor(int minor) {
/* 480 */     this.minor = minor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceFileName(String source_file_name) {
/* 486 */     this.source_file_name = source_file_name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuperclassName(String superclass_name) {
/* 492 */     this.superclass_name = superclass_name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuperclassNameIndex(int superclass_name_index) {
/* 498 */     this.superclass_name_index = superclass_name_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 504 */     String access = Utility.accessToString(this.access_flags, true);
/* 505 */     access = access.equals("") ? "" : (access + " ");
/*     */     
/* 507 */     StringBuffer buf = new StringBuffer(access + Utility.classOrInterface(this.access_flags) + " " + this.class_name + " extends " + Utility.compactClassName(this.superclass_name, false) + '\n');
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     int size = this.interfaces.length;
/*     */     
/* 515 */     if (size > 0) {
/* 516 */       buf.append("implements\t\t");
/*     */       
/* 518 */       for (int i = 0; i < size; i++) {
/* 519 */         buf.append(this.interface_names[i]);
/* 520 */         if (i < size - 1) {
/* 521 */           buf.append(", ");
/*     */         }
/*     */       } 
/* 524 */       buf.append('\n');
/*     */     } 
/*     */     
/* 527 */     buf.append("filename\t\t" + this.file_name + '\n');
/* 528 */     buf.append("compiled from\t\t" + this.source_file_name + '\n');
/* 529 */     buf.append("compiler version\t" + this.major + "." + this.minor + '\n');
/* 530 */     buf.append("access flags\t\t" + this.access_flags + '\n');
/* 531 */     buf.append("constant pool\t\t" + this.constant_pool.getLength() + " entries\n");
/* 532 */     buf.append("ACC_SUPER flag\t\t" + isSuper() + "\n");
/*     */     
/* 534 */     if (this.attributes.length > 0) {
/* 535 */       buf.append("\nAttribute(s):\n");
/* 536 */       for (int i = 0; i < this.attributes.length; i++) {
/* 537 */         buf.append(indent(this.attributes[i]));
/*     */       }
/*     */     } 
/* 540 */     if (this.fields.length > 0) {
/* 541 */       buf.append("\n" + this.fields.length + " fields:\n");
/* 542 */       for (int i = 0; i < this.fields.length; i++) {
/* 543 */         buf.append("\t" + this.fields[i] + '\n');
/*     */       }
/*     */     } 
/* 546 */     if (this.methods.length > 0) {
/* 547 */       buf.append("\n" + this.methods.length + " methods:\n");
/* 548 */       for (int i = 0; i < this.methods.length; i++) {
/* 549 */         buf.append("\t" + this.methods[i] + '\n');
/*     */       }
/*     */     } 
/* 552 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static final String indent(Object obj) {
/* 556 */     StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
/* 557 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 559 */     while (tok.hasMoreTokens()) {
/* 560 */       buf.append("\t" + tok.nextToken() + "\n");
/*     */     }
/* 562 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaClass copy() {
/* 569 */     JavaClass c = null;
/*     */     
/*     */     try {
/* 572 */       c = (JavaClass)clone();
/* 573 */     } catch (CloneNotSupportedException e) {}
/*     */     
/* 575 */     c.constant_pool = this.constant_pool.copy();
/* 576 */     c.interfaces = (int[])this.interfaces.clone();
/* 577 */     c.interface_names = (String[])this.interface_names.clone();
/*     */     
/* 579 */     c.fields = new Field[this.fields.length];
/* 580 */     for (int i = 0; i < this.fields.length; i++) {
/* 581 */       c.fields[i] = this.fields[i].copy(c.constant_pool);
/*     */     }
/* 583 */     c.methods = new Method[this.methods.length];
/* 584 */     for (int j = 0; j < this.methods.length; j++) {
/* 585 */       c.methods[j] = this.methods[j].copy(c.constant_pool);
/*     */     }
/* 587 */     c.attributes = new Attribute[this.attributes.length];
/* 588 */     for (int k = 0; k < this.attributes.length; k++) {
/* 589 */       c.attributes[k] = this.attributes[k].copy(c.constant_pool);
/*     */     }
/* 591 */     return c;
/*     */   }
/*     */   
/*     */   public final boolean instanceOf(JavaClass super_class) {
/* 595 */     return Repository.instanceOf(this, super_class);
/*     */   }
/*     */   
/*     */   public final boolean isSuper() {
/* 599 */     return ((this.access_flags & 0x20) != 0);
/*     */   }
/*     */   
/*     */   public final boolean isClass() {
/* 603 */     return ((this.access_flags & 0x200) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getSource() {
/* 609 */     return this.source;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/JavaClass.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */