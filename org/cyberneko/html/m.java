/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class m
/*     */ {
/*     */   private static final Object a;
/*     */   
/*     */   static {
/*  41 */     m ss = null;
/*     */     try {
/*  43 */       Class c = Class.forName("java.security.AccessController");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  61 */       ss = new n();
/*  62 */     } catch (Exception exception) {
/*     */     
/*     */     } finally {
/*  65 */       if (ss == null)
/*  66 */         ss = new m(); 
/*  67 */       a = ss;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static m a() {
/*  76 */     return (m)a;
/*     */   }
/*     */   
/*     */   ClassLoader b() {
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   ClassLoader c() {
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   ClassLoader a(ClassLoader cl) {
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   String a(String propName) {
/*  92 */     return System.getProperty(propName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   FileInputStream a(File file) throws FileNotFoundException {
/*  98 */     return new FileInputStream(file);
/*     */   }
/*     */   
/*     */   InputStream a(ClassLoader cl, String name) {
/*     */     InputStream ris;
/* 103 */     if (cl == null) {
/* 104 */       ris = ClassLoader.getSystemResourceAsStream(name);
/*     */     } else {
/* 106 */       ris = cl.getResourceAsStream(name);
/*     */     } 
/* 108 */     return ris;
/*     */   }
/*     */   
/*     */   boolean b(File f) {
/* 112 */     return f.exists();
/*     */   }
/*     */   
/*     */   long c(File f) {
/* 116 */     return f.lastModified();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/m.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */