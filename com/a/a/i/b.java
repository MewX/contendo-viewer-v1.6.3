/*     */ package com.a.a.i;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.security.CodeSource;
/*     */ import java.security.ProtectionDomain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*     */   public static Class<?> a(Class<?> clazz) {
/*  19 */     if (clazz != null && clazz.isPrimitive()) {
/*  20 */       if (clazz == int.class) {
/*  21 */         clazz = Integer.class;
/*  22 */       } else if (clazz == long.class) {
/*  23 */         clazz = Long.class;
/*  24 */       } else if (clazz == boolean.class) {
/*  25 */         clazz = Boolean.class;
/*  26 */       } else if (clazz == double.class) {
/*  27 */         clazz = Double.class;
/*  28 */       } else if (clazz == float.class) {
/*  29 */         clazz = Float.class;
/*  30 */       } else if (clazz == byte.class) {
/*  31 */         clazz = Byte.class;
/*  32 */       } else if (clazz == char.class) {
/*  33 */         clazz = Character.class;
/*  34 */       } else if (clazz == short.class) {
/*  35 */         clazz = Short.class;
/*     */       } 
/*     */     }
/*  38 */     return clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(Class[] clazz) {
/*  46 */     for (int i = 0, m = clazz.length; i < m; i++) {
/*  47 */       clazz[i] = a(clazz[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static URL b(Class<?> clazz) {
/*  52 */     String clzFileName = String.valueOf(clazz.getSimpleName()) + ".class";
/*  53 */     URL url = clazz.getResource(clzFileName);
/*  54 */     if (url == null) {
/*  55 */       ProtectionDomain domain = clazz.getProtectionDomain();
/*  56 */       CodeSource cs = domain.getCodeSource();
/*  57 */       if (cs != null) {
/*  58 */         url = cs.getLocation();
/*     */       }
/*     */     } 
/*  61 */     return url;
/*     */   }
/*     */   
/*     */   public static File c(Class<?> clazz) throws IOException {
/*  65 */     return a(clazz.getPackage().getName(), b(clazz));
/*     */   }
/*     */   
/*     */   public static File a(String pkgname, URL loc) throws IOException {
/*  69 */     if (loc == null) return null;
/*     */     
/*  71 */     String name = loc.getFile();
/*  72 */     if ("jar".equals(loc.getProtocol())) {
/*  73 */       int n = name.indexOf("!");
/*  74 */       if (n > 0) {
/*  75 */         name = name.substring(0, n);
/*     */       }
/*  77 */       return a((String)null, new URL(name));
/*     */     } 
/*  79 */     File file = new File(URLDecoder.decode(name, System.getProperty("file.encoding")));
/*  80 */     if (!file.isDirectory() && !file.toString().endsWith(".jar")) {
/*  81 */       file = file.getParentFile();
/*  82 */       if (pkgname != null && pkgname.length() > 0) {
/*  83 */         for (int i = 0, s = 0; i >= 0; s = i + 1) {
/*  84 */           file = file.getParentFile();
/*  85 */           i = pkgname.indexOf(".", s);
/*     */         } 
/*     */       }
/*     */     } 
/*  89 */     return file;
/*     */   }
/*     */   
/*     */   public static String d(Class<?> clazz) throws IOException {
/*  93 */     return b(clazz.getPackage().getName(), b(clazz));
/*     */   }
/*     */   
/*     */   public static String b(String pkgname, URL loc) throws IOException {
/*  97 */     File f = a(pkgname, loc);
/*  98 */     if (f != null) {
/*  99 */       return f.getPath();
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public static File a(String name) throws IOException {
/* 105 */     File dir = a((String)null, ClassLoader.getSystemResource(name));
/* 106 */     if (dir == null) throw new FileNotFoundException("file = '" + name + "'"); 
/* 107 */     return new File(dir, name);
/*     */   }
/*     */   
/*     */   public static <T> Object a(String clazzName, Object... param) throws Exception {
/* 111 */     Class<?> clazz = Class.forName(clazzName);
/* 112 */     return a(clazz, param);
/*     */   }
/*     */   
/*     */   public static <T> T a(Class<T> clazz, Object... param) throws Exception {
/* 116 */     if (param == null || param.length == 0) {
/* 117 */       return clazz.newInstance();
/*     */     }
/*     */     
/* 120 */     Class[] types = new Class[param.length];
/* 121 */     for (int i = 0; i < param.length; i++) {
/* 122 */       types[i] = param[i].getClass();
/*     */     }
/* 124 */     Constructor<T> cons = clazz.getConstructor(types);
/* 125 */     return cons.newInstance(param);
/*     */   }
/*     */   
/*     */   public static Class<?> a(String name, String... packages) {
/* 129 */     Class<?> clazz = null;
/*     */     try {
/* 131 */       clazz = Class.forName(name);
/* 132 */     } catch (ClassNotFoundException e) {
/* 133 */       if (packages != null) {
/* 134 */         byte b1; int i; String[] arrayOfString; for (i = (arrayOfString = packages).length, b1 = 0; b1 < i; ) { String pkg = arrayOfString[b1];
/* 135 */           if (!pkg.endsWith(".")) pkg = String.valueOf(pkg) + "."; 
/*     */           try {
/* 137 */             clazz = Class.forName(String.valueOf(pkg) + name);
/*     */             break;
/* 139 */           } catch (ClassNotFoundException classNotFoundException) {}
/*     */           b1++; }
/*     */       
/*     */       } 
/*     */     } 
/* 144 */     return clazz;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */