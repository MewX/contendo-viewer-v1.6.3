/*    */ package org.apache.batik.util;
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
/*    */ public class HaltingThread
/*    */   extends Thread
/*    */ {
/*    */   protected boolean beenHalted = false;
/*    */   
/*    */   public HaltingThread() {}
/*    */   
/*    */   public HaltingThread(Runnable r) {
/* 38 */     super(r);
/*    */   } public HaltingThread(String name) {
/* 40 */     super(name);
/*    */   } public HaltingThread(Runnable r, String name) {
/* 42 */     super(r, name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isHalted() {
/* 48 */     synchronized (this) { return this.beenHalted; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void halt() {
/* 55 */     synchronized (this) { this.beenHalted = true; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void clearHalted() {
/* 62 */     synchronized (this) { this.beenHalted = false; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void haltThread() {
/* 70 */     haltThread(Thread.currentThread());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void haltThread(Thread t) {
/* 78 */     if (t instanceof HaltingThread) {
/* 79 */       ((HaltingThread)t).halt();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasBeenHalted() {
/* 88 */     return hasBeenHalted(Thread.currentThread());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasBeenHalted(Thread t) {
/* 96 */     if (t instanceof HaltingThread)
/* 97 */       return ((HaltingThread)t).isHalted(); 
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/HaltingThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */