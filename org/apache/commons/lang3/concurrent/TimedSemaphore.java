/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.apache.commons.lang3.Validate;
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
/*     */ public class TimedSemaphore
/*     */ {
/*     */   public static final int NO_LIMIT = 0;
/*     */   private static final int THREAD_POOL_SIZE = 1;
/*     */   private final ScheduledExecutorService executorService;
/*     */   private final long period;
/*     */   private final TimeUnit unit;
/*     */   private final boolean ownExecutor;
/*     */   private ScheduledFuture<?> task;
/*     */   private long totalAcquireCount;
/*     */   private long periodCount;
/*     */   private int limit;
/*     */   private int acquireCount;
/*     */   private int lastCallsPerPeriod;
/*     */   private boolean shutdown;
/*     */   
/*     */   public TimedSemaphore(long timePeriod, TimeUnit timeUnit, int limit) {
/* 197 */     this(null, timePeriod, timeUnit, limit);
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
/*     */   public TimedSemaphore(ScheduledExecutorService service, long timePeriod, TimeUnit timeUnit, int limit) {
/* 214 */     Validate.inclusiveBetween(1L, Long.MAX_VALUE, timePeriod, "Time period must be greater than 0!");
/*     */     
/* 216 */     this.period = timePeriod;
/* 217 */     this.unit = timeUnit;
/*     */     
/* 219 */     if (service != null) {
/* 220 */       this.executorService = service;
/* 221 */       this.ownExecutor = false;
/*     */     } else {
/* 223 */       ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
/*     */       
/* 225 */       s.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
/* 226 */       s.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
/* 227 */       this.executorService = s;
/* 228 */       this.ownExecutor = true;
/*     */     } 
/*     */     
/* 231 */     setLimit(limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized int getLimit() {
/* 242 */     return this.limit;
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
/*     */   public final synchronized void setLimit(int limit) {
/* 256 */     this.limit = limit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void shutdown() {
/* 265 */     if (!this.shutdown) {
/*     */       
/* 267 */       if (this.ownExecutor)
/*     */       {
/*     */         
/* 270 */         getExecutorService().shutdownNow();
/*     */       }
/* 272 */       if (this.task != null) {
/* 273 */         this.task.cancel(false);
/*     */       }
/*     */       
/* 276 */       this.shutdown = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isShutdown() {
/* 288 */     return this.shutdown;
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
/*     */   public synchronized void acquire() throws InterruptedException {
/*     */     boolean canPass;
/* 303 */     prepareAcquire();
/*     */ 
/*     */     
/*     */     do {
/* 307 */       canPass = acquirePermit();
/* 308 */       if (canPass)
/* 309 */         continue;  wait();
/*     */     }
/* 311 */     while (!canPass);
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
/*     */   public synchronized boolean tryAcquire() {
/* 326 */     prepareAcquire();
/* 327 */     return acquirePermit();
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
/*     */   public synchronized int getLastAcquiresPerPeriod() {
/* 341 */     return this.lastCallsPerPeriod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getAcquireCount() {
/* 351 */     return this.acquireCount;
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
/*     */   public synchronized int getAvailablePermits() {
/* 366 */     return getLimit() - getAcquireCount();
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
/*     */   public synchronized double getAverageCallsPerPeriod() {
/* 379 */     return (this.periodCount == 0L) ? 0.0D : (
/* 380 */       this.totalAcquireCount / this.periodCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPeriod() {
/* 391 */     return this.period;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeUnit getUnit() {
/* 400 */     return this.unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScheduledExecutorService getExecutorService() {
/* 409 */     return this.executorService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScheduledFuture<?> startTimer() {
/* 420 */     return getExecutorService().scheduleAtFixedRate(new Runnable()
/*     */         {
/*     */           public void run() {
/* 423 */             TimedSemaphore.this.endOfPeriod();
/*     */           }
/* 425 */         },  getPeriod(), getPeriod(), getUnit());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void endOfPeriod() {
/* 434 */     this.lastCallsPerPeriod = this.acquireCount;
/* 435 */     this.totalAcquireCount += this.acquireCount;
/* 436 */     this.periodCount++;
/* 437 */     this.acquireCount = 0;
/* 438 */     notifyAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void prepareAcquire() {
/* 446 */     if (isShutdown()) {
/* 447 */       throw new IllegalStateException("TimedSemaphore is shut down!");
/*     */     }
/*     */     
/* 450 */     if (this.task == null) {
/* 451 */       this.task = startTimer();
/*     */     }
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
/*     */   private boolean acquirePermit() {
/* 464 */     if (getLimit() <= 0 || this.acquireCount < getLimit()) {
/* 465 */       this.acquireCount++;
/* 466 */       return true;
/*     */     } 
/* 468 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/concurrent/TimedSemaphore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */