/*     */ package com.a.a.c;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ public class b
/*     */   implements a
/*     */ {
/*  11 */   private static Map<Object, a> a = Collections.synchronizedMap(new WeakHashMap<Object, a>());
/*     */   
/*     */   private final a b;
/*     */   
/*     */   public static a g() {
/*  16 */     return a(b.class);
/*     */   }
/*     */   
/*     */   public static a a(Class<?> clazz) {
/*  20 */     a logger = a.get(clazz);
/*  21 */     if (logger == null) {
/*  22 */       synchronized (a) {
/*  23 */         logger = a.get(clazz);
/*  24 */         if (logger == null) {
/*  25 */           logger = new b(clazz);
/*     */         }
/*     */       } 
/*     */     }
/*  29 */     return logger;
/*     */   }
/*     */   
/*     */   public static a g(String name) {
/*  33 */     name = name.intern();
/*  34 */     a logger = a.get(name);
/*  35 */     if (logger == null) {
/*  36 */       synchronized (a) {
/*  37 */         logger = a.get(name);
/*  38 */         if (logger == null) {
/*  39 */           logger = new b(name);
/*     */         }
/*     */       } 
/*     */     }
/*  43 */     return logger;
/*     */   }
/*     */   
/*     */   private b(Object obj) {
/*  47 */     a logger = null;
/*  48 */     Class<?> clazz = null;
/*  49 */     String name = null;
/*  50 */     if (obj instanceof Class) {
/*  51 */       clazz = (Class)obj;
/*     */     } else {
/*  53 */       name = obj.toString();
/*     */     } 
/*  55 */     if (logger == null && d.b) {
/*     */       try {
/*  57 */         logger = (clazz != null) ? new d(clazz) : new d(name);
/*  58 */       } catch (Throwable th) {
/*  59 */         d.b = false;
/*     */       } 
/*     */     }
/*  62 */     this.b = logger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  69 */     return this.b.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  76 */     return this.b.b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c() {
/*  83 */     return this.b.c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String msg) {
/*  90 */     this.b.a(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String msg) {
/*  97 */     this.b.b(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String msg, Throwable th) {
/* 104 */     this.b.a(msg, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Properties prop) {
/* 111 */     this.b.a(prop);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String msg) {
/* 118 */     this.b.c(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String msg, Throwable th) {
/* 125 */     this.b.b(msg, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(String msg) {
/* 132 */     this.b.d(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String string, Throwable th) {
/* 139 */     this.b.c(string, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(String msg) {
/* 146 */     this.b.e(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d() {
/* 153 */     this.b.d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(String msg) {
/* 160 */     this.b.f(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e() {
/* 167 */     this.b.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String f() {
/* 174 */     return this.b.f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String clazz, String msg) {
/* 181 */     this.b.a(clazz, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String clazz, String msg, Throwable th) {
/* 188 */     this.b.a(clazz, msg, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String clazz, String msg) {
/* 195 */     this.b.b(clazz, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String clazz, String msg, Throwable th) {
/* 202 */     this.b.b(clazz, msg, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String clazz, String msg) {
/* 209 */     this.b.c(clazz, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String clazz, String msg, Throwable th) {
/* 216 */     this.b.c(clazz, msg, th);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(String clazz, String msg) {
/* 223 */     this.b.d(clazz, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(String clazz, String msg, Throwable th) {
/* 230 */     this.b.d(clazz, msg, th);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */