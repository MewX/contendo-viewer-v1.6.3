/*     */ package com.a.a.i;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.Executors;
/*     */ 
/*     */ public class c {
/*     */   private static final int a = 500;
/*  13 */   private static WeakReference<Executor> b = null;
/*  14 */   private static List<b> c = null;
/*  15 */   private static final a d = new a();
/*     */ 
/*     */   
/*     */   static class b
/*     */     implements Comparable<b>
/*     */   {
/*     */     Runnable a;
/*     */     
/*     */     long b;
/*     */     
/*     */     b(Runnable command, long time) {
/*  26 */       this.a = command;
/*  27 */       this.b = time;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int a(b o) {
/*  35 */       return Long.valueOf(this.b).compareTo(Long.valueOf(o.b));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class a
/*     */     implements Runnable
/*     */   {
/*     */     public void run() {
/*  46 */       if (c.b() != null) {
/*  47 */         boolean bSubmit = false;
/*  48 */         synchronized (this) {
/*  49 */           if (c.b() != null) {
/*  50 */             long currentTime = System.currentTimeMillis();
/*  51 */             ListIterator<c.b> it = c.b().listIterator();
/*  52 */             while (it.hasNext()) {
/*  53 */               c.b task = it.next();
/*  54 */               if (task.b <= currentTime) {
/*  55 */                 it.remove();
/*  56 */                 c.a(task.a);
/*  57 */                 bSubmit = true;
/*     */               } 
/*     */             } 
/*  60 */             if (c.b().isEmpty()) {
/*  61 */               c.a((List)null);
/*     */             } else {
/*  63 */               c.a(this);
/*     */             } 
/*     */           } 
/*     */         } 
/*  67 */         if (c.b() != null && !bSubmit) {
/*     */           try {
/*  69 */             Thread.sleep(500L);
/*  70 */           } catch (InterruptedException interruptedException) {}
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Executor a() {
/*  81 */     Executor executor = null;
/*  82 */     if (b == null || (executor = b.get()) == null) {
/*  83 */       synchronized (c.class) {
/*  84 */         if (b == null || (executor = b.get()) == null) {
/*  85 */           executor = Executors.newSingleThreadExecutor();
/*  86 */           b = new WeakReference<Executor>(executor);
/*     */         } 
/*     */       } 
/*     */     }
/*  90 */     return executor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(Runnable command) {
/*  97 */     a().execute(command);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(Runnable command, long latency) {
/* 105 */     if (latency == 0L) {
/* 106 */       a().execute(command);
/*     */     } else {
/* 108 */       synchronized (d) {
/* 109 */         if (c == null) c = new LinkedList<b>(); 
/* 110 */         c.add(new b(command, System.currentTimeMillis() + latency));
/* 111 */         if (c.size() > 1) {
/* 112 */           Collections.sort(c);
/*     */         }
/* 114 */         a().execute(d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */