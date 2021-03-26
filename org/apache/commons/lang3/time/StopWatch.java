/*     */ package org.apache.commons.lang3.time;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StopWatch
/*     */ {
/*     */   private static final long NANO_2_MILLIS = 1000000L;
/*     */   
/*     */   public static StopWatch createStarted() {
/*  72 */     StopWatch sw = new StopWatch();
/*  73 */     sw.start();
/*  74 */     return sw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private enum State
/*     */   {
/*  82 */     UNSTARTED
/*     */     {
/*     */       boolean isStarted() {
/*  85 */         return false;
/*     */       }
/*     */       
/*     */       boolean isStopped() {
/*  89 */         return true;
/*     */       }
/*     */       
/*     */       boolean isSuspended() {
/*  93 */         return false;
/*     */       }
/*     */     },
/*  96 */     RUNNING
/*     */     {
/*     */       boolean isStarted() {
/*  99 */         return true;
/*     */       }
/*     */       
/*     */       boolean isStopped() {
/* 103 */         return false;
/*     */       }
/*     */       
/*     */       boolean isSuspended() {
/* 107 */         return false;
/*     */       }
/*     */     },
/* 110 */     STOPPED
/*     */     {
/*     */       boolean isStarted() {
/* 113 */         return false;
/*     */       }
/*     */       
/*     */       boolean isStopped() {
/* 117 */         return true;
/*     */       }
/*     */       
/*     */       boolean isSuspended() {
/* 121 */         return false;
/*     */       }
/*     */     },
/* 124 */     SUSPENDED
/*     */     {
/*     */       boolean isStarted() {
/* 127 */         return true;
/*     */       }
/*     */       
/*     */       boolean isStopped() {
/* 131 */         return false;
/*     */       }
/*     */       
/*     */       boolean isSuspended() {
/* 135 */         return true;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract boolean isStarted();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract boolean isStopped();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract boolean isSuspended();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private enum SplitState
/*     */   {
/* 177 */     SPLIT,
/* 178 */     UNSPLIT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 183 */   private State runningState = State.UNSTARTED;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   private SplitState splitState = SplitState.UNSPLIT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long startTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long startTimeMillis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long stopTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 229 */     if (this.runningState == State.STOPPED) {
/* 230 */       throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
/*     */     }
/* 232 */     if (this.runningState != State.UNSTARTED) {
/* 233 */       throw new IllegalStateException("Stopwatch already started. ");
/*     */     }
/* 235 */     this.startTime = System.nanoTime();
/* 236 */     this.startTimeMillis = System.currentTimeMillis();
/* 237 */     this.runningState = State.RUNNING;
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
/*     */   public void stop() {
/* 254 */     if (this.runningState != State.RUNNING && this.runningState != State.SUSPENDED) {
/* 255 */       throw new IllegalStateException("Stopwatch is not running. ");
/*     */     }
/* 257 */     if (this.runningState == State.RUNNING) {
/* 258 */       this.stopTime = System.nanoTime();
/*     */     }
/* 260 */     this.runningState = State.STOPPED;
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
/*     */   public void reset() {
/* 273 */     this.runningState = State.UNSTARTED;
/* 274 */     this.splitState = SplitState.UNSPLIT;
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
/*     */   public void split() {
/* 291 */     if (this.runningState != State.RUNNING) {
/* 292 */       throw new IllegalStateException("Stopwatch is not running. ");
/*     */     }
/* 294 */     this.stopTime = System.nanoTime();
/* 295 */     this.splitState = SplitState.SPLIT;
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
/*     */   public void unsplit() {
/* 312 */     if (this.splitState != SplitState.SPLIT) {
/* 313 */       throw new IllegalStateException("Stopwatch has not been split. ");
/*     */     }
/* 315 */     this.splitState = SplitState.UNSPLIT;
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
/*     */   public void suspend() {
/* 332 */     if (this.runningState != State.RUNNING) {
/* 333 */       throw new IllegalStateException("Stopwatch must be running to suspend. ");
/*     */     }
/* 335 */     this.stopTime = System.nanoTime();
/* 336 */     this.runningState = State.SUSPENDED;
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
/*     */   public void resume() {
/* 353 */     if (this.runningState != State.SUSPENDED) {
/* 354 */       throw new IllegalStateException("Stopwatch must be suspended to resume. ");
/*     */     }
/* 356 */     this.startTime += System.nanoTime() - this.stopTime;
/* 357 */     this.runningState = State.RUNNING;
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
/*     */   public long getTime() {
/* 373 */     return getNanoTime() / 1000000L;
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
/*     */   public long getTime(TimeUnit timeUnit) {
/* 393 */     return timeUnit.convert(getNanoTime(), TimeUnit.NANOSECONDS);
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
/*     */   public long getNanoTime() {
/* 410 */     if (this.runningState == State.STOPPED || this.runningState == State.SUSPENDED)
/* 411 */       return this.stopTime - this.startTime; 
/* 412 */     if (this.runningState == State.UNSTARTED)
/* 413 */       return 0L; 
/* 414 */     if (this.runningState == State.RUNNING) {
/* 415 */       return System.nanoTime() - this.startTime;
/*     */     }
/* 417 */     throw new RuntimeException("Illegal running state has occurred.");
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
/*     */   public long getSplitTime() {
/* 436 */     return getSplitNanoTime() / 1000000L;
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
/*     */   public long getSplitNanoTime() {
/* 454 */     if (this.splitState != SplitState.SPLIT) {
/* 455 */       throw new IllegalStateException("Stopwatch must be split to get the split time. ");
/*     */     }
/* 457 */     return this.stopTime - this.startTime;
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
/*     */   public long getStartTime() {
/* 469 */     if (this.runningState == State.UNSTARTED) {
/* 470 */       throw new IllegalStateException("Stopwatch has not been started");
/*     */     }
/*     */     
/* 473 */     return this.startTimeMillis;
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
/*     */   public String toString() {
/* 489 */     return DurationFormatUtils.formatDurationHMS(getTime());
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
/*     */   public String toSplitString() {
/* 505 */     return DurationFormatUtils.formatDurationHMS(getSplitTime());
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
/*     */   public boolean isStarted() {
/* 519 */     return this.runningState.isStarted();
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
/*     */   public boolean isSuspended() {
/* 532 */     return this.runningState.isSuspended();
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
/*     */   public boolean isStopped() {
/* 547 */     return this.runningState.isStopped();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/time/StopWatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */