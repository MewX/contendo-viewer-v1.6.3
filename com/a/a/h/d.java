/*    */ package com.a.a.h;
/*    */ 
/*    */ import java.lang.ref.PhantomReference;
/*    */ import java.lang.ref.Reference;
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d<T>
/*    */ {
/*    */   private class a
/*    */     extends Thread
/*    */   {
/*    */     private a(d this$0) {}
/*    */     
/*    */     public void run() {
/*    */       try {
/* 31 */         while (!isInterrupted() && !this.a.b.isEmpty()) {
/* 32 */           Reference<? extends Object> ref = (Reference)this.a.a.remove();
/* 33 */           if (ref != null) {
/* 34 */             synchronized (this.a.b) {
/* 35 */               T val = (T)this.a.b.remove(ref);
/* 36 */               if (val != null && !this.a.b.containsValue(val)) {
/* 37 */                 this.a.c.a(val);
/*    */               }
/* 39 */               if (this.a.b.isEmpty()) {
/* 40 */                 this.a.d = null;
/*    */                 break;
/*    */               } 
/*    */             } 
/*    */           }
/*    */         } 
/* 46 */       } catch (InterruptedException interruptedException) {}
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   ReferenceQueue<Object> a = new ReferenceQueue();
/*    */   
/*    */   final Map<Reference<Object>, T> b;
/*    */   final b<T> c;
/* 56 */   a d = null;
/*    */   
/*    */   public d(b<T> collector) {
/* 59 */     this(collector, new HashMap<Reference<Object>, T>());
/*    */   }
/*    */   
/*    */   public d(b<T> collector, Map<Reference<Object>, T> map) {
/* 63 */     this.b = map;
/* 64 */     this.c = collector;
/*    */   }
/*    */   
/*    */   public void a(Object key, T value) {
/* 68 */     synchronized (this.b) {
/* 69 */       this.b.put(new PhantomReference(key, this.a), value);
/* 70 */       if (this.d == null) {
/* 71 */         this.d = new a(null);
/* 72 */         this.d.start();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static interface b<T> {
/*    */     void a(T param1T);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */