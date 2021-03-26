/*    */ package org.a.a;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import org.a.a.a.a;
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
/*    */ 
/*    */ class a
/*    */   implements a
/*    */ {
/*    */   private volatile WeakReference<ConcurrentLinkedQueue<char[]>> a;
/*    */   
/*    */   public final char[] a() {
/* 69 */     char[] t = b().poll();
/* 70 */     if (t == null)
/* 71 */       return new char[4096]; 
/* 72 */     return t;
/*    */   }
/*    */   
/*    */   private ConcurrentLinkedQueue<char[]> b() {
/* 76 */     WeakReference<ConcurrentLinkedQueue<char[]>> q = this.a;
/* 77 */     if (q != null) {
/* 78 */       ConcurrentLinkedQueue<char[]> concurrentLinkedQueue = q.get();
/* 79 */       if (concurrentLinkedQueue != null) {
/* 80 */         return concurrentLinkedQueue;
/*    */       }
/*    */     } 
/*    */     
/* 84 */     ConcurrentLinkedQueue<char[]> d = (ConcurrentLinkedQueue)new ConcurrentLinkedQueue<>();
/* 85 */     this.a = new WeakReference<>(d);
/*    */     
/* 87 */     return d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(char[] t) {
/* 95 */     b().offer(t);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */