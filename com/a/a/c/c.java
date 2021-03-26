/*     */ package com.a.a.c;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class c
/*     */   implements a
/*     */ {
/*     */   private boolean a = true;
/*     */   private boolean b = true;
/*     */   private boolean c = true;
/*     */   
/*     */   protected c(Class<?> clazz) {}
/*     */   
/*     */   protected c(String name) {}
/*     */   
/*     */   public boolean a() {
/*  23 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  31 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c() {
/*  39 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Properties prop) {
/*  46 */     if (b()) {
/*  47 */       ArrayList<String> list = new ArrayList<String>();
/*  48 */       for (Object obj : prop.keySet()) {
/*  49 */         if (obj instanceof String) list.add((String)obj); 
/*     */       } 
/*  51 */       Collections.sort(list);
/*  52 */       for (String key : list) {
/*  53 */         Object obj = prop.get(key);
/*  54 */         if (obj == null) {
/*  55 */           b("(properties) key=" + key + " val="); continue;
/*     */         } 
/*  57 */         b("(properties) key=" + key + " val=" + obj.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean enbale_info) {
/*  67 */     this.a = enbale_info;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(boolean enbale_debug) {
/*  74 */     this.b = enbale_debug;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(boolean enbale_trace) {
/*  81 */     this.c = enbale_trace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String f() {
/*  89 */     return Thread.currentThread().getStackTrace()[3].getMethodName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d() {
/*  97 */     if (c()) {
/*  98 */       e(String.valueOf(f()) + " START");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e() {
/* 106 */     if (c()) {
/* 107 */       f(String.valueOf(f()) + " END");
/*     */     }
/*     */   }
/*     */   
/*     */   public void e(String msg) {
/* 112 */     if (c()) {
/* 113 */       a(String.valueOf(f()) + " START: " + msg);
/*     */     }
/*     */   }
/*     */   
/*     */   public void f(String msg) {
/* 118 */     if (c()) {
/* 119 */       a(String.valueOf(f()) + " END  : " + msg);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String clazz, String msg) {
/* 127 */     b.g(clazz).a(msg);
/*     */   }
/*     */   
/*     */   public void a(String clazz, String msg, Throwable th) {}
/*     */   
/*     */   public void b(String clazz, String msg) {}
/*     */   
/*     */   public void b(String clazz, String msg, Throwable th) {}
/*     */   
/*     */   public void c(String clazz, String msg) {}
/*     */   
/*     */   public void c(String clazz, String msg, Throwable th) {}
/*     */   
/*     */   public void d(String clazz, String msg) {}
/*     */   
/*     */   public void d(String clazz, String msg, Throwable th) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */