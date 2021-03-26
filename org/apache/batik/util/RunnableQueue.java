/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunnableQueue
/*     */   implements Runnable
/*     */ {
/*     */   public static final class RunnableQueueState
/*     */   {
/*     */     private final String value;
/*     */     
/*     */     private RunnableQueueState(String value) {
/*  41 */       this.value = value;
/*     */     } public String getValue() {
/*  43 */       return this.value;
/*     */     }
/*     */     public String toString() {
/*  46 */       return "[RunnableQueueState: " + this.value + ']';
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static final RunnableQueueState RUNNING = new RunnableQueueState("Running");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final RunnableQueueState SUSPENDING = new RunnableQueueState("Suspending");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final RunnableQueueState SUSPENDED = new RunnableQueueState("Suspended");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile RunnableQueueState state;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected final Object stateLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean wasResumed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private final DoublyLinkedList list = new DoublyLinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int preemptCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RunHandler runHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile HaltingThread runnableQueueThread;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IdleRunnable idleRunnable;
/*     */ 
/*     */ 
/*     */   
/*     */   private long idleRunnableWaitTime;
/*     */ 
/*     */ 
/*     */   
/*     */   private static volatile int threadCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RunnableQueue createRunnableQueue() {
/* 124 */     RunnableQueue result = new RunnableQueue();
/* 125 */     synchronized (result) {
/*     */ 
/*     */ 
/*     */       
/* 129 */       HaltingThread ht = new HaltingThread(result, "RunnableQueue-" + threadCount++);
/*     */       
/* 131 */       ht.setDaemon(true);
/* 132 */       ht.start();
/* 133 */       while (result.getThread() == null) {
/*     */         try {
/* 135 */           result.wait();
/* 136 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 149 */     synchronized (this) {
/* 150 */       this.runnableQueueThread = (HaltingThread)Thread.currentThread();
/*     */ 
/*     */       
/* 153 */       notify();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 159 */       while (!HaltingThread.hasBeenHalted()) {
/* 160 */         Link l; Runnable rable; boolean callSuspended = false;
/* 161 */         boolean callResumed = false;
/*     */         
/* 163 */         synchronized (this.stateLock) {
/* 164 */           if (this.state != RUNNING) {
/* 165 */             this.state = SUSPENDED;
/* 166 */             callSuspended = true;
/*     */           } 
/*     */         } 
/* 169 */         if (callSuspended) {
/* 170 */           executionSuspended();
/*     */         }
/* 172 */         synchronized (this.stateLock) {
/* 173 */           while (this.state != RUNNING) {
/* 174 */             this.state = SUSPENDED;
/*     */ 
/*     */ 
/*     */             
/* 178 */             this.stateLock.notifyAll();
/*     */ 
/*     */             
/*     */             try {
/* 182 */               this.stateLock.wait();
/* 183 */             } catch (InterruptedException interruptedException) {}
/*     */           } 
/*     */           
/* 186 */           if (this.wasResumed) {
/* 187 */             this.wasResumed = false;
/* 188 */             callResumed = true;
/*     */           } 
/*     */         } 
/*     */         
/* 192 */         if (callResumed) {
/* 193 */           executionResumed();
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         synchronized (this.list) {
/* 203 */           if (this.state == SUSPENDING)
/*     */             continue; 
/* 205 */           l = (Link)this.list.pop();
/* 206 */           if (this.preemptCount != 0) this.preemptCount--; 
/* 207 */           if (l == null) {
/*     */ 
/*     */             
/* 210 */             if (this.idleRunnable != null && (this.idleRunnableWaitTime = this.idleRunnable.getWaitTime()) < System.currentTimeMillis()) {
/*     */ 
/*     */               
/* 213 */               rable = this.idleRunnable;
/*     */             } else {
/*     */               
/*     */               try {
/* 217 */                 if (this.idleRunnable != null && this.idleRunnableWaitTime != Long.MAX_VALUE) {
/*     */                   
/* 219 */                   long t = this.idleRunnableWaitTime - System.currentTimeMillis();
/*     */                   
/* 221 */                   if (t > 0L) {
/* 222 */                     this.list.wait(t);
/*     */                   }
/*     */                 } else {
/* 225 */                   this.list.wait();
/*     */                 } 
/* 227 */               } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */           } else {
/* 233 */             rable = l.runnable;
/*     */           } 
/*     */         } 
/*     */         
/*     */         try {
/* 238 */           runnableStart(rable);
/*     */           
/* 240 */           rable.run();
/* 241 */         } catch (ThreadDeath td) {
/*     */           
/* 243 */           throw td;
/* 244 */         } catch (Throwable t) {
/*     */ 
/*     */           
/* 247 */           t.printStackTrace();
/*     */         } 
/*     */ 
/*     */         
/* 251 */         if (l != null) {
/* 252 */           l.unlock();
/*     */         }
/*     */         
/*     */         try {
/* 256 */           runnableInvoked(rable);
/* 257 */         } catch (ThreadDeath td) {
/*     */           
/* 259 */           throw td;
/* 260 */         } catch (Throwable t) {
/*     */ 
/*     */           
/* 263 */           t.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } finally {
/*     */       while (true) {
/*     */         Link link;
/*     */ 
/*     */ 
/*     */         
/* 272 */         synchronized (this.list) {
/* 273 */           link = (Link)this.list.pop();
/*     */         } 
/* 275 */         if (link == null)
/* 276 */           break;  link.unlock();
/*     */       } 
/*     */       
/* 279 */       synchronized (this) {
/* 280 */         this.runnableQueueThread = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HaltingThread getThread() {
/* 291 */     return this.runnableQueueThread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invokeLater(Runnable r) {
/* 301 */     if (this.runnableQueueThread == null) {
/* 302 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */     
/* 305 */     synchronized (this.list) {
/* 306 */       this.list.push(new Link(r));
/* 307 */       this.list.notify();
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
/*     */   public void invokeAndWait(Runnable r) throws InterruptedException {
/* 320 */     if (this.runnableQueueThread == null) {
/* 321 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */     
/* 324 */     if (this.runnableQueueThread == Thread.currentThread()) {
/* 325 */       throw new IllegalStateException("Cannot be called from the RunnableQueue thread");
/*     */     }
/*     */ 
/*     */     
/* 329 */     LockableLink l = new LockableLink(r);
/* 330 */     synchronized (this.list) {
/* 331 */       this.list.push(l);
/* 332 */       this.list.notify();
/*     */     } 
/* 334 */     l.lock();
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
/*     */   public void preemptLater(Runnable r) {
/* 347 */     if (this.runnableQueueThread == null) {
/* 348 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */     
/* 351 */     synchronized (this.list) {
/* 352 */       this.list.add(this.preemptCount, new Link(r));
/* 353 */       this.preemptCount++;
/* 354 */       this.list.notify();
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
/*     */ 
/*     */   
/*     */   public void preemptAndWait(Runnable r) throws InterruptedException {
/* 369 */     if (this.runnableQueueThread == null) {
/* 370 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */     
/* 373 */     if (this.runnableQueueThread == Thread.currentThread()) {
/* 374 */       throw new IllegalStateException("Cannot be called from the RunnableQueue thread");
/*     */     }
/*     */ 
/*     */     
/* 378 */     LockableLink l = new LockableLink(r);
/* 379 */     synchronized (this.list) {
/* 380 */       this.list.add(this.preemptCount, l);
/* 381 */       this.preemptCount++;
/* 382 */       this.list.notify();
/*     */     } 
/* 384 */     l.lock();
/*     */   }
/*     */   
/*     */   public RunnableQueueState getQueueState() {
/* 388 */     synchronized (this.stateLock) {
/* 389 */       return this.state;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void suspendExecution(boolean waitTillSuspended) {
/* 405 */     if (this.runnableQueueThread == null) {
/* 406 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */ 
/*     */     
/* 410 */     synchronized (this.stateLock) {
/* 411 */       this.wasResumed = false;
/*     */       
/* 413 */       if (this.state == SUSPENDED) {
/*     */ 
/*     */         
/* 416 */         this.stateLock.notifyAll();
/*     */         
/*     */         return;
/*     */       } 
/* 420 */       if (this.state == RUNNING) {
/* 421 */         this.state = SUSPENDING;
/* 422 */         synchronized (this.list) {
/*     */ 
/*     */ 
/*     */           
/* 426 */           this.list.notify();
/*     */         } 
/*     */       } 
/*     */       
/* 430 */       if (waitTillSuspended) {
/* 431 */         while (this.state == SUSPENDING) {
/*     */           try {
/* 433 */             this.stateLock.wait();
/* 434 */           } catch (InterruptedException interruptedException) {}
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumeExecution() {
/* 446 */     if (this.runnableQueueThread == null) {
/* 447 */       throw new IllegalStateException("RunnableQueue not started or has exited");
/*     */     }
/*     */ 
/*     */     
/* 451 */     synchronized (this.stateLock) {
/* 452 */       this.wasResumed = true;
/*     */       
/* 454 */       if (this.state != RUNNING) {
/* 455 */         this.state = RUNNING;
/* 456 */         this.stateLock.notifyAll();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getIteratorLock() {
/* 466 */     return this.list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 473 */     return new Iterator() {
/* 474 */         RunnableQueue.Link head = (RunnableQueue.Link)RunnableQueue.this.list.getHead(); RunnableQueue.Link link;
/*     */         
/*     */         public boolean hasNext() {
/* 477 */           if (this.head == null) {
/* 478 */             return false;
/*     */           }
/* 480 */           if (this.link == null) {
/* 481 */             return true;
/*     */           }
/* 483 */           return (this.link != this.head);
/*     */         }
/*     */         public Object next() {
/* 486 */           if (this.head == null || this.head == this.link) {
/* 487 */             throw new NoSuchElementException();
/*     */           }
/* 489 */           if (this.link == null) {
/* 490 */             this.link = (RunnableQueue.Link)this.head.getNext();
/* 491 */             return this.head.runnable;
/*     */           } 
/* 493 */           Object result = this.link.runnable;
/* 494 */           this.link = (RunnableQueue.Link)this.link.getNext();
/* 495 */           return result;
/*     */         }
/*     */         public void remove() {
/* 498 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setRunHandler(RunHandler rh) {
/* 507 */     this.runHandler = rh;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized RunHandler getRunHandler() {
/* 514 */     return this.runHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIdleRunnable(IdleRunnable r) {
/* 521 */     synchronized (this.list) {
/* 522 */       this.idleRunnable = r;
/* 523 */       this.idleRunnableWaitTime = 0L;
/* 524 */       this.list.notify();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void executionSuspended() {
/* 534 */     if (this.runHandler != null) {
/* 535 */       this.runHandler.executionSuspended(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void executionResumed() {
/* 545 */     if (this.runHandler != null) {
/* 546 */       this.runHandler.executionResumed(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void runnableStart(Runnable rable) {
/* 556 */     if (this.runHandler != null) {
/* 557 */       this.runHandler.runnableStart(this, rable);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void runnableInvoked(Runnable rable) {
/* 567 */     if (this.runHandler != null) {
/* 568 */       this.runHandler.runnableInvoked(this, rable);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class RunHandlerAdapter
/*     */     implements RunHandler
/*     */   {
/*     */     public void runnableStart(RunnableQueue rq, Runnable r) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void runnableInvoked(RunnableQueue rq, Runnable r) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void executionSuspended(RunnableQueue rq) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void executionResumed(RunnableQueue rq) {}
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
/*     */   protected static class Link
/*     */     extends DoublyLinkedList.Node
/*     */   {
/*     */     private final Runnable runnable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Link(Runnable r) {
/* 659 */       this.runnable = r;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void unlock() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class LockableLink
/*     */     extends Link
/*     */   {
/*     */     private volatile boolean locked;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LockableLink(Runnable r) {
/* 683 */       super(r);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isLocked() {
/* 690 */       return this.locked;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void lock() throws InterruptedException {
/* 697 */       this.locked = true;
/* 698 */       notify();
/* 699 */       wait();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void unlock() {
/* 706 */       while (!this.locked) {
/*     */         try {
/* 708 */           wait();
/* 709 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */ 
/*     */       
/* 713 */       this.locked = false;
/*     */       
/* 715 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface RunHandler {
/*     */     void runnableStart(RunnableQueue param1RunnableQueue, Runnable param1Runnable);
/*     */     
/*     */     void runnableInvoked(RunnableQueue param1RunnableQueue, Runnable param1Runnable);
/*     */     
/*     */     void executionSuspended(RunnableQueue param1RunnableQueue);
/*     */     
/*     */     void executionResumed(RunnableQueue param1RunnableQueue);
/*     */   }
/*     */   
/*     */   public static interface IdleRunnable extends Runnable {
/*     */     long getWaitTime();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/RunnableQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */