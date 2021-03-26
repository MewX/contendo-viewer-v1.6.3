/*     */ package org.apache.xml.utils;
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
/*     */ public class ThreadControllerWrapper
/*     */ {
/*  29 */   static ThreadController m_tpool = new ThreadController();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setThreadController(ThreadController tpool) {
/*  39 */     m_tpool = tpool;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Thread runThread(Runnable runnable, int priority) {
/*  44 */     return m_tpool.run(runnable, priority);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void waitThread(Thread worker, Runnable task) throws InterruptedException {
/*  50 */     m_tpool.waitThread(worker, task);
/*     */   }
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
/*     */   public static class ThreadController
/*     */   {
/*     */     public Thread run(Runnable task, int priority) {
/*  80 */       Thread t = new Thread(task);
/*     */       
/*  82 */       t.start();
/*     */ 
/*     */ 
/*     */       
/*  86 */       return t;
/*     */     }
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
/*     */     public void waitThread(Thread worker, Runnable task) throws InterruptedException {
/* 103 */       worker.join();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/ThreadControllerWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */