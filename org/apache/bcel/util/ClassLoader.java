/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.ClassParser;
/*     */ import org.apache.bcel.classfile.ConstantClass;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
/*     */ import org.apache.bcel.classfile.JavaClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassLoader
/*     */   extends java.lang.ClassLoader
/*     */ {
/*  88 */   private Hashtable classes = new Hashtable();
/*  89 */   private String[] ignored_packages = new String[] { "java.", "javax.", "sun." };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassLoader() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassLoader(String[] ignored_packages) {
/* 100 */     String[] new_p = new String[ignored_packages.length + this.ignored_packages.length];
/*     */     
/* 102 */     System.arraycopy(this.ignored_packages, 0, new_p, 0, this.ignored_packages.length);
/* 103 */     System.arraycopy(ignored_packages, 0, new_p, this.ignored_packages.length, ignored_packages.length);
/*     */ 
/*     */     
/* 106 */     this.ignored_packages = new_p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Class loadClass(String class_name, boolean resolve) throws ClassNotFoundException {
/* 112 */     Class cl = null;
/*     */ 
/*     */ 
/*     */     
/* 116 */     if ((cl = (Class)this.classes.get(class_name)) == null) {
/*     */ 
/*     */ 
/*     */       
/* 120 */       for (int i = 0; i < this.ignored_packages.length; i++) {
/* 121 */         if (class_name.startsWith(this.ignored_packages[i])) {
/* 122 */           cl = Class.forName(class_name);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 127 */       if (cl == null) {
/* 128 */         JavaClass clazz = null;
/*     */ 
/*     */ 
/*     */         
/* 132 */         if (class_name.indexOf("$$BCEL$$") >= 0) {
/* 133 */           clazz = createClass(class_name);
/*     */         }
/* 135 */         else if ((clazz = Repository.lookupClass(class_name)) != null) {
/* 136 */           clazz = modifyClass(clazz);
/*     */         } else {
/* 138 */           throw new ClassNotFoundException(class_name);
/*     */         } 
/*     */         
/* 141 */         if (clazz != null) {
/* 142 */           byte[] bytes = clazz.getBytes();
/* 143 */           cl = defineClass(class_name, bytes, 0, bytes.length);
/*     */         } else {
/* 145 */           cl = Class.forName(class_name);
/*     */         } 
/*     */       } 
/* 148 */       if (resolve) {
/* 149 */         resolveClass(cl);
/*     */       }
/*     */     } 
/* 152 */     this.classes.put(class_name, cl);
/*     */     
/* 154 */     return cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JavaClass modifyClass(JavaClass clazz) {
/* 161 */     return clazz;
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
/*     */   protected JavaClass createClass(String class_name) {
/* 179 */     int index = class_name.indexOf("$$BCEL$$");
/* 180 */     String real_name = class_name.substring(index + 8);
/*     */     
/* 182 */     JavaClass clazz = null;
/*     */     try {
/* 184 */       byte[] bytes = Utility.decode(real_name, true);
/* 185 */       ClassParser parser = new ClassParser(new ByteArrayInputStream(bytes), "foo");
/*     */       
/* 187 */       clazz = parser.parse();
/*     */     } catch (Throwable e) {
/* 189 */       e.printStackTrace();
/* 190 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 194 */     ConstantPool cp = clazz.getConstantPool();
/*     */     
/* 196 */     ConstantClass cl = (ConstantClass)cp.getConstant(clazz.getClassNameIndex(), (byte)7);
/*     */     
/* 198 */     ConstantUtf8 name = (ConstantUtf8)cp.getConstant(cl.getNameIndex(), (byte)1);
/*     */     
/* 200 */     name.setBytes(class_name.replace('.', '/'));
/*     */     
/* 202 */     return clazz;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ClassLoader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */