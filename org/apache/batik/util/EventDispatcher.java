/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.List;
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
/*     */ public class EventDispatcher
/*     */ {
/*     */   public static void fireEvent(final Dispatcher dispatcher, final List listeners, final Object evt, final boolean useEventQueue) {
/*  43 */     if (useEventQueue && !EventQueue.isDispatchThread()) {
/*  44 */       Runnable r = new Runnable() {
/*     */           public void run() {
/*  46 */             EventDispatcher.fireEvent(dispatcher, listeners, evt, useEventQueue);
/*     */           }
/*     */         };
/*     */       try {
/*  50 */         EventQueue.invokeAndWait(r);
/*  51 */       } catch (InvocationTargetException e) {
/*  52 */         e.printStackTrace();
/*  53 */       } catch (InterruptedException interruptedException) {
/*     */ 
/*     */       
/*  56 */       } catch (ThreadDeath td) {
/*  57 */         throw td;
/*  58 */       } catch (Throwable t) {
/*  59 */         t.printStackTrace();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  64 */     Object[] ll = null;
/*  65 */     Throwable err = null;
/*  66 */     int retryCount = 10;
/*  67 */     while (--retryCount != 0) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  72 */         synchronized (listeners) {
/*  73 */           if (listeners.size() == 0)
/*     */             return; 
/*  75 */           ll = listeners.toArray();
/*     */         } 
/*     */         break;
/*  78 */       } catch (Throwable t) {
/*  79 */         err = t;
/*     */       } 
/*     */     } 
/*  82 */     if (ll == null) {
/*  83 */       if (err != null)
/*  84 */         err.printStackTrace(); 
/*     */       return;
/*     */     } 
/*  87 */     dispatchEvent(dispatcher, ll, evt);
/*     */   }
/*     */   public static interface Dispatcher {
/*     */     void dispatch(Object param1Object1, Object param1Object2); }
/*     */   
/*     */   protected static void dispatchEvent(Dispatcher dispatcher, Object[] ll, Object evt) {
/*  93 */     ThreadDeath td = null;
/*     */     try {
/*  95 */       for (int i = 0; i < ll.length; i++) {
/*     */         
/*     */         try {
/*  98 */           synchronized (ll)
/*  99 */           { Object l = ll[i];
/* 100 */             if (l == null) {  }
/* 101 */             else { ll[i] = null;
/*     */               
/* 103 */               dispatcher.dispatch(l, evt); }  } 
/* 104 */         } catch (ThreadDeath t) {
/*     */           
/* 106 */           td = t;
/* 107 */         } catch (Throwable t) {
/* 108 */           t.printStackTrace();
/*     */         } 
/*     */       } 
/* 111 */     } catch (ThreadDeath t) {
/*     */       
/* 113 */       td = t;
/* 114 */     } catch (Throwable t) {
/* 115 */       if (ll[ll.length - 1] != null)
/* 116 */         dispatchEvent(dispatcher, ll, evt); 
/* 117 */       t.printStackTrace();
/*     */     } 
/* 119 */     if (td != null) throw td; 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/EventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */