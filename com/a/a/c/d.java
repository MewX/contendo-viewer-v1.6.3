/*     */ package com.a.a.c;
/*     */ 
/*     */ import com.a.a.i.b;
/*     */ import com.a.a.i.g;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends c
/*     */ {
/*     */   static final String a = "org.slf4j.LoggerFactory";
/*     */   public static boolean b = false;
/*     */   static Class<?> c;
/*     */   static Object d;
/*     */   static Method e;
/*     */   static Method f;
/*     */   final Object g;
/*     */   final Method h;
/*     */   final Method i;
/*     */   final Method j;
/*     */   final Method k;
/*     */   final Method l;
/*     */   final Method m;
/*     */   final Method n;
/*     */   final Method o;
/*     */   
/*     */   static {
/*  29 */     b = ((
/*  30 */       c = b.a("org.slf4j.LoggerFactory", new String[0])) != null && (
/*  31 */       d = g.a(g.a(c, "getILoggerFactory", new Class[0]), null, new Object[0])) != null && (
/*  32 */       e = g.a(d.getClass(), "getLogger", new Class[] { Class.class })) != null && (
/*  33 */       f = g.a(d.getClass(), "getLogger", new Class[] { String.class })) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected d(Class<?> clazz) {
/*  38 */     super(clazz);
/*     */     
/*  40 */     this.g = g.a(e, d, new Object[] { clazz });
/*  41 */     this.h = g.a(this.g.getClass(), "info", new Class[] { String.class });
/*  42 */     this.i = g.a(this.g.getClass(), "info", new Class[] { String.class, Throwable.class });
/*  43 */     this.j = g.a(this.g.getClass(), "warning", new Class[] { String.class });
/*  44 */     this.k = g.a(this.g.getClass(), "warning", new Class[] { String.class, Throwable.class });
/*  45 */     this.l = g.a(this.g.getClass(), "error", new Class[] { String.class });
/*  46 */     this.m = g.a(this.g.getClass(), "error", new Class[] { String.class, Throwable.class });
/*  47 */     this.n = g.a(this.g.getClass(), "debug", new Class[] { String.class });
/*  48 */     this.o = g.a(this.g.getClass(), "debug", new Class[] { String.class, Throwable.class });
/*     */   }
/*     */   
/*     */   protected d(String name) {
/*  52 */     super(name);
/*     */     
/*  54 */     this.g = g.a(f, d, new Object[] { name });
/*  55 */     this.h = g.a(this.g.getClass(), "info", new Class[] { String.class });
/*  56 */     this.i = g.a(this.g.getClass(), "info", new Class[] { String.class, Throwable.class });
/*  57 */     this.j = g.a(this.g.getClass(), "warning", new Class[] { String.class });
/*  58 */     this.k = g.a(this.g.getClass(), "warning", new Class[] { String.class, Throwable.class });
/*  59 */     this.l = g.a(this.g.getClass(), "error", new Class[] { String.class });
/*  60 */     this.m = g.a(this.g.getClass(), "error", new Class[] { String.class, Throwable.class });
/*  61 */     this.n = g.a(this.g.getClass(), "debug", new Class[] { String.class });
/*  62 */     this.o = g.a(this.g.getClass(), "debug", new Class[] { String.class, Throwable.class });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String msg) {
/*  70 */     g.a(this.h, this.g, new Object[] { msg });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String msg) {
/*  78 */     g.a(this.n, this.g, new Object[] { msg });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String msg, Throwable th) {
/*  86 */     g.a(this.o, this.g, new Object[] { msg, th });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String msg) {
/*  94 */     g.a(this.j, this.g, new Object[] { msg });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String msg, Throwable th) {
/* 102 */     g.a(this.k, this.g, new Object[] { msg, th });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(String msg) {
/* 110 */     g.a(this.l, this.g, new Object[] { msg });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String msg, Throwable th) {
/* 118 */     g.a(this.m, this.g, new Object[] { msg, th });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/c/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */