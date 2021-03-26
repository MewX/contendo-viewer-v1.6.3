/*     */ package org.apache.commons.io;
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
/*     */ class ThreadMonitor
/*     */   implements Runnable
/*     */ {
/*     */   private final Thread thread;
/*     */   private final long timeout;
/*     */   
/*     */   public static Thread start(long timeout) {
/*  54 */     return start(Thread.currentThread(), timeout);
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
/*     */   public static Thread start(Thread thread, long timeout) {
/*  67 */     Thread monitor = null;
/*  68 */     if (timeout > 0L) {
/*  69 */       ThreadMonitor timout = new ThreadMonitor(thread, timeout);
/*  70 */       monitor = new Thread(timout, ThreadMonitor.class.getSimpleName());
/*  71 */       monitor.setDaemon(true);
/*  72 */       monitor.start();
/*     */     } 
/*  74 */     return monitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stop(Thread thread) {
/*  83 */     if (thread != null) {
/*  84 */       thread.interrupt();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ThreadMonitor(Thread thread, long timeout) {
/*  95 */     this.thread = thread;
/*  96 */     this.timeout = timeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 108 */       sleep(this.timeout);
/* 109 */       this.thread.interrupt();
/* 110 */     } catch (InterruptedException interruptedException) {}
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
/*     */   private static void sleep(long ms) throws InterruptedException {
/* 125 */     long finishAt = System.currentTimeMillis() + ms;
/* 126 */     long remaining = ms;
/*     */     do {
/* 128 */       Thread.sleep(remaining);
/* 129 */       remaining = finishAt - System.currentTimeMillis();
/* 130 */     } while (remaining > 0L);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/ThreadMonitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */