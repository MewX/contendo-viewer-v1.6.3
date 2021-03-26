/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ClassParser
/*     */ {
/*     */   private DataInputStream file;
/*     */   private ZipFile zip;
/*     */   private String file_name;
/*     */   private int class_name_index;
/*     */   private int superclass_name_index;
/*     */   private int major;
/*     */   private int minor;
/*     */   private int access_flags;
/*     */   private int[] interfaces;
/*     */   private ConstantPool constant_pool;
/*     */   private Field[] fields;
/*     */   private Method[] methods;
/*     */   private Attribute[] attributes;
/*     */   private boolean is_zip;
/*     */   private static final int BUFSIZE = 8192;
/*     */   
/*     */   public ClassParser(InputStream file, String file_name) {
/* 100 */     this.file_name = file_name;
/*     */     
/* 102 */     String clazz = file.getClass().getName();
/* 103 */     this.is_zip = (clazz.startsWith("java.util.zip.") || clazz.startsWith("java.util.jar."));
/*     */     
/* 105 */     if (file instanceof DataInputStream) {
/* 106 */       this.file = (DataInputStream)file;
/*     */     } else {
/* 108 */       this.file = new DataInputStream(new BufferedInputStream(file, 8192));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassParser(String file_name) throws IOException {
/* 118 */     this.is_zip = false;
/* 119 */     this.file_name = file_name;
/* 120 */     this.file = new DataInputStream(new BufferedInputStream(new FileInputStream(file_name), 8192));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassParser(String zip_file, String file_name) throws IOException {
/* 131 */     this.is_zip = true;
/* 132 */     this.zip = new ZipFile(zip_file);
/* 133 */     ZipEntry entry = this.zip.getEntry(file_name);
/*     */     
/* 135 */     this.file_name = file_name;
/*     */     
/* 137 */     this.file = new DataInputStream(new BufferedInputStream(this.zip.getInputStream(entry), 8192));
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
/*     */   public JavaClass parse() throws IOException, ClassFormatError {
/* 156 */     readID();
/*     */ 
/*     */     
/* 159 */     readVersion();
/*     */ 
/*     */ 
/*     */     
/* 163 */     readConstantPool();
/*     */ 
/*     */     
/* 166 */     readClassInfo();
/*     */ 
/*     */     
/* 169 */     readInterfaces();
/*     */ 
/*     */ 
/*     */     
/* 173 */     readFields();
/*     */ 
/*     */     
/* 176 */     readMethods();
/*     */ 
/*     */     
/* 179 */     readAttributes();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     this.file.close();
/* 200 */     if (this.zip != null) {
/* 201 */       this.zip.close();
/*     */     }
/*     */     
/* 204 */     return new JavaClass(this.class_name_index, this.superclass_name_index, this.file_name, this.major, this.minor, this.access_flags, this.constant_pool, this.interfaces, this.fields, this.methods, this.attributes, this.is_zip ? 3 : 2);
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
/*     */   private final void readAttributes() throws IOException, ClassFormatError {
/* 219 */     int attributes_count = this.file.readUnsignedShort();
/* 220 */     this.attributes = new Attribute[attributes_count];
/*     */     
/* 222 */     for (int i = 0; i < attributes_count; i++) {
/* 223 */       this.attributes[i] = Attribute.readAttribute(this.file, this.constant_pool);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readClassInfo() throws IOException, ClassFormatError {
/* 233 */     this.access_flags = this.file.readUnsignedShort();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     if ((this.access_flags & 0x200) != 0) {
/* 239 */       this.access_flags |= 0x400;
/*     */     }
/* 241 */     if ((this.access_flags & 0x400) != 0 && (this.access_flags & 0x10) != 0)
/*     */     {
/* 243 */       throw new ClassFormatError("Class can't be both final and abstract");
/*     */     }
/* 245 */     this.class_name_index = this.file.readUnsignedShort();
/* 246 */     this.superclass_name_index = this.file.readUnsignedShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readConstantPool() throws IOException, ClassFormatError {
/* 255 */     this.constant_pool = new ConstantPool(this.file);
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
/*     */   private final void readFields() throws IOException, ClassFormatError {
/* 267 */     int fields_count = this.file.readUnsignedShort();
/* 268 */     this.fields = new Field[fields_count];
/*     */     
/* 270 */     for (int i = 0; i < fields_count; i++) {
/* 271 */       this.fields[i] = new Field(this.file, this.constant_pool);
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
/*     */   private final void readID() throws IOException, ClassFormatError {
/* 284 */     int magic = -889275714;
/*     */     
/* 286 */     if (this.file.readInt() != magic) {
/* 287 */       throw new ClassFormatError(this.file_name + " is not a Java .class file");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readInterfaces() throws IOException, ClassFormatError {
/* 298 */     int interfaces_count = this.file.readUnsignedShort();
/* 299 */     this.interfaces = new int[interfaces_count];
/*     */     
/* 301 */     for (int i = 0; i < interfaces_count; i++) {
/* 302 */       this.interfaces[i] = this.file.readUnsignedShort();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readMethods() throws IOException, ClassFormatError {
/* 313 */     int methods_count = this.file.readUnsignedShort();
/* 314 */     this.methods = new Method[methods_count];
/*     */     
/* 316 */     for (int i = 0; i < methods_count; i++) {
/* 317 */       this.methods[i] = new Method(this.file, this.constant_pool);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readVersion() throws IOException, ClassFormatError {
/* 326 */     this.minor = this.file.readUnsignedShort();
/* 327 */     this.major = this.file.readUnsignedShort();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/ClassParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */