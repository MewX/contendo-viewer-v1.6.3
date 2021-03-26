/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.lang.ref.PhantomReference;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
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
/*     */ public class CleanerThread
/*     */   extends Thread
/*     */ {
/*  37 */   static volatile ReferenceQueue queue = null;
/*  38 */   static CleanerThread thread = null;
/*     */ 
/*     */   
/*     */   public static ReferenceQueue getReferenceQueue() {
/*  42 */     if (queue == null) {
/*  43 */       synchronized (CleanerThread.class) {
/*  44 */         queue = new ReferenceQueue();
/*  45 */         thread = new CleanerThread();
/*     */       } 
/*     */     }
/*  48 */     return queue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ReferenceCleared
/*     */   {
/*     */     void cleared();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class SoftReferenceCleared
/*     */     extends SoftReference
/*     */     implements ReferenceCleared
/*     */   {
/*     */     public SoftReferenceCleared(Object o) {
/*  68 */       super((T)o, CleanerThread.getReferenceQueue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class WeakReferenceCleared
/*     */     extends WeakReference
/*     */     implements ReferenceCleared
/*     */   {
/*     */     public WeakReferenceCleared(Object o) {
/*  79 */       super((T)o, CleanerThread.getReferenceQueue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class PhantomReferenceCleared
/*     */     extends PhantomReference
/*     */     implements ReferenceCleared
/*     */   {
/*     */     public PhantomReferenceCleared(Object o) {
/*  91 */       super((T)o, CleanerThread.getReferenceQueue());
/*     */     }
/*     */   }
/*     */   
/*     */   protected CleanerThread() {
/*  96 */     super("Batik CleanerThread");
/*  97 */     setDaemon(true);
/*  98 */     start();
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*     */         Reference ref;
/*     */         try {
/* 106 */           ref = queue.remove();
/*     */         }
/* 108 */         catch (InterruptedException ie) {
/*     */           continue;
/*     */         } 
/*     */         
/* 112 */         if (ref instanceof ReferenceCleared) {
/* 113 */           ReferenceCleared rc = (ReferenceCleared)ref;
/* 114 */           rc.cleared();
/*     */         } 
/* 116 */       } catch (ThreadDeath td) {
/* 117 */         throw td;
/* 118 */       } catch (Throwable t) {
/* 119 */         t.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/CleanerThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */