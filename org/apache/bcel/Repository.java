/*     */ package org.apache.bcel;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import org.apache.bcel.classfile.ClassParser;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.util.ClassPath;
/*     */ import org.apache.bcel.util.ClassQueue;
/*     */ import org.apache.bcel.util.ClassVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Repository
/*     */ {
/*  75 */   private static ClassPath class_path = new ClassPath();
/*     */   private static HashMap classes;
/*     */   
/*     */   static {
/*  79 */     clearCache();
/*     */   }
/*     */   private static JavaClass OBJECT;
/*     */   
/*     */   public static JavaClass lookupClass(String class_name) {
/*  84 */     if (class_name == null || class_name.equals("")) {
/*  85 */       throw new RuntimeException("Invalid class name");
/*     */     }
/*  87 */     class_name = class_name.replace('/', '.');
/*     */     
/*  89 */     JavaClass clazz = (JavaClass)classes.get(class_name);
/*     */     
/*  91 */     if (clazz == null) {
/*     */       
/*  93 */       try { InputStream is = class_path.getInputStream(class_name);
/*  94 */         clazz = (new ClassParser(is, class_name)).parse();
/*  95 */         class_name = clazz.getClassName(); }
/*  96 */       catch (IOException e) { return null; }
/*     */       
/*  98 */       classes.put(class_name, clazz);
/*     */     } 
/*     */     
/* 101 */     return clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClassPath.ClassFile lookupClassFile(String class_name) {
/*     */     
/* 108 */     try { return class_path.getClassFile(class_name); }
/* 109 */     catch (IOException e) { return null; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearCache() {
/* 115 */     classes = new HashMap();
/* 116 */     OBJECT = lookupClass("java.lang.Object");
/*     */     
/* 118 */     if (OBJECT == null) {
/* 119 */       System.err.println("Warning: java.lang.Object not found on CLASSPATH!");
/*     */     } else {
/* 121 */       classes.put("java.lang.Object", OBJECT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JavaClass addClass(JavaClass clazz) {
/* 130 */     String name = clazz.getClassName();
/* 131 */     JavaClass cl = (JavaClass)classes.get(name);
/*     */     
/* 133 */     if (cl == null) {
/* 134 */       classes.put(name, cl = clazz);
/*     */     }
/* 136 */     return cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeClass(String clazz) {
/* 143 */     classes.remove(clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeClass(JavaClass clazz) {
/* 150 */     removeClass(clazz.getClassName());
/*     */   }
/*     */ 
/*     */   
/*     */   private static final JavaClass getSuperClass(JavaClass clazz) {
/* 155 */     if (clazz == OBJECT) {
/* 156 */       return null;
/*     */     }
/* 158 */     return lookupClass(clazz.getSuperclassName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JavaClass[] getSuperClasses(JavaClass clazz) {
/* 166 */     ClassVector vec = new ClassVector();
/*     */     
/* 168 */     for (clazz = getSuperClass(clazz); clazz != null; clazz = getSuperClass(clazz)) {
/* 169 */       vec.addElement(clazz);
/*     */     }
/* 171 */     return vec.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JavaClass[] getSuperClasses(String class_name) {
/* 179 */     JavaClass jc = lookupClass(class_name);
/* 180 */     return (jc == null) ? null : getSuperClasses(jc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JavaClass[] getInterfaces(JavaClass clazz) {
/* 188 */     ClassVector vec = new ClassVector();
/* 189 */     ClassQueue queue = new ClassQueue();
/*     */     
/* 191 */     queue.enqueue(clazz);
/*     */     
/* 193 */     while (!queue.empty()) {
/* 194 */       clazz = queue.dequeue();
/*     */       
/* 196 */       String s = clazz.getSuperclassName();
/* 197 */       String[] interfaces = clazz.getInterfaceNames();
/*     */       
/* 199 */       if (clazz.isInterface()) {
/* 200 */         vec.addElement(clazz);
/* 201 */       } else if (!s.equals("java.lang.Object")) {
/* 202 */         queue.enqueue(lookupClass(s));
/*     */       } 
/* 204 */       for (int i = 0; i < interfaces.length; i++) {
/* 205 */         queue.enqueue(lookupClass(interfaces[i]));
/*     */       }
/*     */     } 
/* 208 */     return vec.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JavaClass[] getInterfaces(String class_name) {
/* 216 */     return getInterfaces(lookupClass(class_name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean instanceOf(JavaClass clazz, JavaClass super_class) {
/* 223 */     if (clazz == super_class) {
/* 224 */       return true;
/*     */     }
/* 226 */     JavaClass[] super_classes = getSuperClasses(clazz);
/*     */     
/* 228 */     for (int i = 0; i < super_classes.length; i++) {
/* 229 */       if (super_classes[i] == super_class)
/* 230 */         return true; 
/*     */     } 
/* 232 */     if (super_class.isInterface()) {
/* 233 */       return implementationOf(clazz, super_class);
/*     */     }
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean instanceOf(String clazz, String super_class) {
/* 242 */     return instanceOf(lookupClass(clazz), lookupClass(super_class));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean instanceOf(JavaClass clazz, String super_class) {
/* 249 */     return instanceOf(clazz, lookupClass(super_class));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean instanceOf(String clazz, JavaClass super_class) {
/* 256 */     return instanceOf(lookupClass(clazz), super_class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean implementationOf(JavaClass clazz, JavaClass inter) {
/* 263 */     if (clazz == inter) {
/* 264 */       return true;
/*     */     }
/* 266 */     JavaClass[] super_interfaces = getInterfaces(clazz);
/*     */     
/* 268 */     for (int i = 0; i < super_interfaces.length; i++) {
/* 269 */       if (super_interfaces[i] == inter)
/* 270 */         return true; 
/*     */     } 
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean implementationOf(String clazz, String inter) {
/* 279 */     return implementationOf(lookupClass(clazz), lookupClass(inter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean implementationOf(JavaClass clazz, String inter) {
/* 286 */     return implementationOf(clazz, lookupClass(inter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean implementationOf(String clazz, JavaClass inter) {
/* 293 */     return implementationOf(lookupClass(clazz), inter);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/Repository.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */