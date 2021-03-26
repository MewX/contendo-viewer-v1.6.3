/*     */ package com.a.a.b.b;
/*     */ 
/*     */ import com.a.a.i.c;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*  18 */   private static final LinkedList<String> a = new LinkedList<String>();
/*     */ 
/*     */   
/*  21 */   private static a b = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static class a
/*     */     implements Runnable
/*     */   {
/*  28 */     private final HashSet<String> a = new HashSet<String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*  35 */       synchronized (a.b()) {
/*  36 */         if (a.b().isEmpty()) {
/*  37 */           a.a((a)null);
/*     */         } else {
/*  39 */           String name = a.b().removeFirst();
/*  40 */           if (a.a(name)) {
/*  41 */             a.b().remove(name);
/*     */           } else {
/*  43 */             this.a.add(name);
/*  44 */             a.b().addLast(name);
/*     */           } 
/*  46 */           if (a.b().isEmpty()) {
/*  47 */             a.a((a)null);
/*     */           } else {
/*  49 */             name = a.b().getFirst();
/*  50 */             long latency = 0L;
/*  51 */             if (this.a.contains(name)) {
/*  52 */               latency = 3000L;
/*     */             }
/*  54 */             c.a(this, latency);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(File file) {
/*  67 */     boolean rtn = true;
/*  68 */     if (file.exists()) {
/*  69 */       if (file.isDirectory()) {
/*  70 */         File[] files = file.listFiles(); byte b; int i; File[] arrayOfFile1;
/*  71 */         for (i = (arrayOfFile1 = files).length, b = 0; b < i; ) { File f = arrayOfFile1[b];
/*  72 */           a(f); b++; }
/*     */       
/*     */       } 
/*  75 */       rtn = file.delete();
/*     */     } 
/*  77 */     return rtn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(File[] files) {
/*  86 */     boolean rtn = true;
/*  87 */     if (files != null) {
/*  88 */       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File f = arrayOfFile[b]; if (!a(f)) rtn = false;  b++; }
/*     */     
/*  90 */     }  return rtn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(String name, String basePath) {
/* 100 */     return a(new File(basePath, name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(String name) {
/* 109 */     return a(new File(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(File file) {
/* 118 */     b(file.getAbsolutePath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(String name) {
/* 127 */     if (!a(name)) {
/* 128 */       synchronized (a) {
/* 129 */         a.add(name);
/* 130 */         if (b == null) {
/* 131 */           b = new a();
/* 132 */           c.a(b);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] a() {
/*     */     String[] list;
/* 143 */     synchronized (a) {
/* 144 */       list = (String[])a.toArray();
/*     */     } 
/* 146 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void c(File f) {
/* 153 */     c(f.getAbsolutePath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void c(String name) {
/* 160 */     synchronized (a) {
/* 161 */       a.remove(name);
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
/*     */   public static long a(File src, File dest) throws IOException {
/* 173 */     long size = 0L;
/* 174 */     FileInputStream fis = new FileInputStream(src);
/* 175 */     FileOutputStream fos = null;
/*     */     
/* 177 */     try { fos = new FileOutputStream(dest);
/*     */       try {
/* 179 */         size = b.a(fis, fos);
/*     */       } finally {
/* 181 */         fos.close();
/*     */       }  }
/*     */     finally { 
/* 184 */       try { fis.close(); } catch (Exception exception) {} }
/*     */     
/* 186 */     return size;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */