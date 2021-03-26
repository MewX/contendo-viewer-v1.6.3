/*     */ package org.apache.http.pool;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>>
/*     */   implements ConnPool<T, E>, ConnPoolControl<T>
/*     */ {
/*     */   private final Lock lock;
/*     */   private final Condition condition;
/*     */   private final ConnFactory<T, C> connFactory;
/*     */   private final Map<T, RouteSpecificPool<T, C, E>> routeToPool;
/*     */   private final Set<E> leased;
/*     */   private final LinkedList<E> available;
/*     */   private final LinkedList<Future<E>> pending;
/*     */   private final Map<T, Integer> maxPerRoute;
/*     */   private volatile boolean isShutDown;
/*     */   private volatile int defaultMaxPerRoute;
/*     */   private volatile int maxTotal;
/*     */   private volatile int validateAfterInactivity;
/*     */   
/*     */   public AbstractConnPool(ConnFactory<T, C> connFactory, int defaultMaxPerRoute, int maxTotal) {
/*  91 */     this.connFactory = (ConnFactory<T, C>)Args.notNull(connFactory, "Connection factory");
/*  92 */     this.defaultMaxPerRoute = Args.positive(defaultMaxPerRoute, "Max per route value");
/*  93 */     this.maxTotal = Args.positive(maxTotal, "Max total value");
/*  94 */     this.lock = new ReentrantLock();
/*  95 */     this.condition = this.lock.newCondition();
/*  96 */     this.routeToPool = new HashMap<T, RouteSpecificPool<T, C, E>>();
/*  97 */     this.leased = new HashSet<E>();
/*  98 */     this.available = new LinkedList<E>();
/*  99 */     this.pending = new LinkedList<Future<E>>();
/* 100 */     this.maxPerRoute = new HashMap<T, Integer>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onLease(E entry) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onRelease(E entry) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onReuse(E entry) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(E entry) {
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isShutdown() {
/* 134 */     return this.isShutDown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() throws IOException {
/* 141 */     if (this.isShutDown) {
/*     */       return;
/*     */     }
/* 144 */     this.isShutDown = true;
/* 145 */     this.lock.lock();
/*     */     try {
/* 147 */       for (PoolEntry poolEntry : this.available) {
/* 148 */         poolEntry.close();
/*     */       }
/* 150 */       for (PoolEntry poolEntry : this.leased) {
/* 151 */         poolEntry.close();
/*     */       }
/* 153 */       for (RouteSpecificPool<T, C, E> pool : this.routeToPool.values()) {
/* 154 */         pool.shutdown();
/*     */       }
/* 156 */       this.routeToPool.clear();
/* 157 */       this.leased.clear();
/* 158 */       this.available.clear();
/*     */     } finally {
/* 160 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private RouteSpecificPool<T, C, E> getPool(final T route) {
/* 165 */     RouteSpecificPool<T, C, E> pool = this.routeToPool.get(route);
/* 166 */     if (pool == null) {
/* 167 */       pool = new RouteSpecificPool<T, C, E>(route)
/*     */         {
/*     */           protected E createEntry(C conn)
/*     */           {
/* 171 */             return AbstractConnPool.this.createEntry(route, conn);
/*     */           }
/*     */         };
/*     */       
/* 175 */       this.routeToPool.put(route, pool);
/*     */     } 
/* 177 */     return pool;
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
/*     */   public Future<E> lease(final T route, final Object state, final FutureCallback<E> callback) {
/* 190 */     Args.notNull(route, "Route");
/* 191 */     Asserts.check(!this.isShutDown, "Connection pool shut down");
/*     */     
/* 193 */     return new Future<E>()
/*     */       {
/* 195 */         private final AtomicBoolean cancelled = new AtomicBoolean(false);
/* 196 */         private final AtomicBoolean done = new AtomicBoolean(false);
/* 197 */         private final AtomicReference<E> entryRef = new AtomicReference<E>(null);
/*     */ 
/*     */         
/*     */         public boolean cancel(boolean mayInterruptIfRunning) {
/* 201 */           if (this.cancelled.compareAndSet(false, true)) {
/* 202 */             this.done.set(true);
/* 203 */             AbstractConnPool.this.lock.lock();
/*     */             try {
/* 205 */               AbstractConnPool.this.condition.signalAll();
/*     */             } finally {
/* 207 */               AbstractConnPool.this.lock.unlock();
/*     */             } 
/* 209 */             if (callback != null) {
/* 210 */               callback.cancelled();
/*     */             }
/* 212 */             return true;
/*     */           } 
/* 214 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isCancelled() {
/* 219 */           return this.cancelled.get();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isDone() {
/* 224 */           return this.done.get();
/*     */         }
/*     */ 
/*     */         
/*     */         public E get() throws InterruptedException, ExecutionException {
/*     */           try {
/* 230 */             return get(0L, TimeUnit.MILLISECONDS);
/* 231 */           } catch (TimeoutException ex) {
/* 232 */             throw new ExecutionException(ex);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public E get(long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
/* 238 */           PoolEntry poolEntry = (PoolEntry)this.entryRef.get();
/* 239 */           if (poolEntry != null) {
/* 240 */             return (E)poolEntry;
/*     */           }
/* 242 */           synchronized (this) {
/*     */             while (true) {
/*     */               try {
/* 245 */                 PoolEntry poolEntry1 = (PoolEntry)AbstractConnPool.this.getPoolEntryBlocking((T)route, state, timeout, timeUnit, this);
/* 246 */                 if (AbstractConnPool.this.validateAfterInactivity > 0 && 
/* 247 */                   poolEntry1.getUpdated() + AbstractConnPool.this.validateAfterInactivity <= System.currentTimeMillis() && 
/* 248 */                   !AbstractConnPool.this.validate(poolEntry1)) {
/* 249 */                   poolEntry1.close();
/* 250 */                   AbstractConnPool.this.release(poolEntry1, false);
/*     */                   
/*     */                   continue;
/*     */                 } 
/*     */                 
/* 255 */                 this.entryRef.set((E)poolEntry1);
/* 256 */                 this.done.set(true);
/* 257 */                 AbstractConnPool.this.onLease(poolEntry1);
/* 258 */                 if (callback != null) {
/* 259 */                   callback.completed(poolEntry1);
/*     */                 }
/* 261 */                 return (E)poolEntry1;
/*     */               }
/* 263 */               catch (IOException ex) {
/* 264 */                 this.done.set(true);
/* 265 */                 if (callback != null) {
/* 266 */                   callback.failed(ex);
/*     */                 }
/* 268 */                 throw new ExecutionException(ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
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
/*     */   public Future<E> lease(T route, Object state) {
/* 293 */     return lease(route, state, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private E getPoolEntryBlocking(T route, Object state, long timeout, TimeUnit timeUnit, Future<E> future) throws IOException, InterruptedException, TimeoutException {
/* 301 */     Date deadline = null;
/* 302 */     if (timeout > 0L) {
/* 303 */       deadline = new Date(System.currentTimeMillis() + timeUnit.toMillis(timeout));
/*     */     }
/* 305 */     this.lock.lock(); try {
/*     */       boolean success;
/* 307 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/*     */       do {
/*     */         E entry;
/* 310 */         Asserts.check(!this.isShutDown, "Connection pool shut down");
/*     */         while (true) {
/* 312 */           entry = pool.getFree(state);
/* 313 */           if (entry == null) {
/*     */             break;
/*     */           }
/* 316 */           if (entry.isExpired(System.currentTimeMillis())) {
/* 317 */             entry.close();
/*     */           }
/* 319 */           if (entry.isClosed()) {
/* 320 */             this.available.remove(entry);
/* 321 */             pool.free(entry, false);
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/* 326 */         if (entry != null) {
/* 327 */           this.available.remove(entry);
/* 328 */           this.leased.add(entry);
/* 329 */           onReuse(entry);
/* 330 */           return entry;
/*     */         } 
/*     */ 
/*     */         
/* 334 */         int maxPerRoute = getMax(route);
/*     */         
/* 336 */         int excess = Math.max(0, pool.getAllocatedCount() + 1 - maxPerRoute);
/* 337 */         if (excess > 0) {
/* 338 */           for (int i = 0; i < excess; i++) {
/* 339 */             E lastUsed = pool.getLastUsed();
/* 340 */             if (lastUsed == null) {
/*     */               break;
/*     */             }
/* 343 */             lastUsed.close();
/* 344 */             this.available.remove(lastUsed);
/* 345 */             pool.remove(lastUsed);
/*     */           } 
/*     */         }
/*     */         
/* 349 */         if (pool.getAllocatedCount() < maxPerRoute) {
/* 350 */           int totalUsed = this.leased.size();
/* 351 */           int freeCapacity = Math.max(this.maxTotal - totalUsed, 0);
/* 352 */           if (freeCapacity > 0) {
/* 353 */             int totalAvailable = this.available.size();
/* 354 */             if (totalAvailable > freeCapacity - 1 && 
/* 355 */               !this.available.isEmpty()) {
/* 356 */               PoolEntry poolEntry = (PoolEntry)this.available.removeLast();
/* 357 */               poolEntry.close();
/* 358 */               RouteSpecificPool<T, C, E> otherpool = getPool((T)poolEntry.getRoute());
/* 359 */               otherpool.remove((E)poolEntry);
/*     */             } 
/*     */             
/* 362 */             C conn = this.connFactory.create(route);
/* 363 */             entry = pool.add(conn);
/* 364 */             this.leased.add(entry);
/* 365 */             return entry;
/*     */           } 
/*     */         } 
/*     */         
/* 369 */         success = false;
/*     */         try {
/* 371 */           if (future.isCancelled()) {
/* 372 */             throw new InterruptedException("Operation interrupted");
/*     */           }
/* 374 */           pool.queue(future);
/* 375 */           this.pending.add(future);
/* 376 */           if (deadline != null) {
/* 377 */             success = this.condition.awaitUntil(deadline);
/*     */           } else {
/* 379 */             this.condition.await();
/* 380 */             success = true;
/*     */           } 
/* 382 */           if (future.isCancelled()) {
/* 383 */             throw new InterruptedException("Operation interrupted");
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         finally {
/*     */           
/* 390 */           pool.unqueue(future);
/* 391 */           this.pending.remove(future);
/*     */         }
/*     */       
/* 394 */       } while (success || deadline == null || deadline.getTime() > System.currentTimeMillis());
/*     */ 
/*     */ 
/*     */       
/* 398 */       throw new TimeoutException("Timeout waiting for connection");
/*     */     } finally {
/* 400 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void release(E entry, boolean reusable) {
/* 406 */     this.lock.lock();
/*     */     try {
/* 408 */       if (this.leased.remove(entry)) {
/* 409 */         RouteSpecificPool<T, C, E> pool = getPool((T)entry.getRoute());
/* 410 */         pool.free(entry, reusable);
/* 411 */         if (reusable && !this.isShutDown) {
/* 412 */           this.available.addFirst(entry);
/*     */         } else {
/* 414 */           entry.close();
/*     */         } 
/* 416 */         onRelease(entry);
/* 417 */         Future<E> future = pool.nextPending();
/* 418 */         if (future != null) {
/* 419 */           this.pending.remove(future);
/*     */         } else {
/* 421 */           future = this.pending.poll();
/*     */         } 
/* 423 */         if (future != null) {
/* 424 */           this.condition.signalAll();
/*     */         }
/*     */       } 
/*     */     } finally {
/* 428 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getMax(T route) {
/* 433 */     Integer v = this.maxPerRoute.get(route);
/* 434 */     return (v != null) ? v.intValue() : this.defaultMaxPerRoute;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxTotal(int max) {
/* 439 */     Args.positive(max, "Max value");
/* 440 */     this.lock.lock();
/*     */     try {
/* 442 */       this.maxTotal = max;
/*     */     } finally {
/* 444 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxTotal() {
/* 450 */     this.lock.lock();
/*     */     try {
/* 452 */       return this.maxTotal;
/*     */     } finally {
/* 454 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMaxPerRoute(int max) {
/* 460 */     Args.positive(max, "Max per route value");
/* 461 */     this.lock.lock();
/*     */     try {
/* 463 */       this.defaultMaxPerRoute = max;
/*     */     } finally {
/* 465 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultMaxPerRoute() {
/* 471 */     this.lock.lock();
/*     */     try {
/* 473 */       return this.defaultMaxPerRoute;
/*     */     } finally {
/* 475 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxPerRoute(T route, int max) {
/* 481 */     Args.notNull(route, "Route");
/* 482 */     this.lock.lock();
/*     */     try {
/* 484 */       if (max > -1) {
/* 485 */         this.maxPerRoute.put(route, Integer.valueOf(max));
/*     */       } else {
/* 487 */         this.maxPerRoute.remove(route);
/*     */       } 
/*     */     } finally {
/* 490 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxPerRoute(T route) {
/* 496 */     Args.notNull(route, "Route");
/* 497 */     this.lock.lock();
/*     */     try {
/* 499 */       return getMax(route);
/*     */     } finally {
/* 501 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getTotalStats() {
/* 507 */     this.lock.lock();
/*     */     try {
/* 509 */       return new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 515 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getStats(T route) {
/* 521 */     Args.notNull(route, "Route");
/* 522 */     this.lock.lock();
/*     */     try {
/* 524 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 525 */       return new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), getMax(route));
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 531 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<T> getRoutes() {
/* 542 */     this.lock.lock();
/*     */     try {
/* 544 */       return new HashSet(this.routeToPool.keySet());
/*     */     } finally {
/* 546 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enumAvailable(PoolEntryCallback<T, C> callback) {
/* 556 */     this.lock.lock();
/*     */     try {
/* 558 */       Iterator<E> it = this.available.iterator();
/* 559 */       while (it.hasNext()) {
/* 560 */         PoolEntry<T, C> poolEntry = (PoolEntry)it.next();
/* 561 */         callback.process(poolEntry);
/* 562 */         if (poolEntry.isClosed()) {
/* 563 */           RouteSpecificPool<T, C, E> pool = getPool(poolEntry.getRoute());
/* 564 */           pool.remove((E)poolEntry);
/* 565 */           it.remove();
/*     */         } 
/*     */       } 
/* 568 */       purgePoolMap();
/*     */     } finally {
/* 570 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enumLeased(PoolEntryCallback<T, C> callback) {
/* 580 */     this.lock.lock();
/*     */     try {
/* 582 */       Iterator<E> it = this.leased.iterator();
/* 583 */       while (it.hasNext()) {
/* 584 */         PoolEntry<T, C> poolEntry = (PoolEntry)it.next();
/* 585 */         callback.process(poolEntry);
/*     */       } 
/*     */     } finally {
/* 588 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void purgePoolMap() {
/* 593 */     Iterator<Map.Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
/* 594 */     while (it.hasNext()) {
/* 595 */       Map.Entry<T, RouteSpecificPool<T, C, E>> entry = it.next();
/* 596 */       RouteSpecificPool<T, C, E> pool = entry.getValue();
/* 597 */       if (pool.getPendingCount() + pool.getAllocatedCount() == 0) {
/* 598 */         it.remove();
/*     */       }
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
/*     */   public void closeIdle(long idletime, TimeUnit timeUnit) {
/* 611 */     Args.notNull(timeUnit, "Time unit");
/* 612 */     long time = timeUnit.toMillis(idletime);
/* 613 */     if (time < 0L) {
/* 614 */       time = 0L;
/*     */     }
/* 616 */     final long deadline = System.currentTimeMillis() - time;
/* 617 */     enumAvailable(new PoolEntryCallback<T, C>()
/*     */         {
/*     */           public void process(PoolEntry<T, C> entry)
/*     */           {
/* 621 */             if (entry.getUpdated() <= deadline) {
/* 622 */               entry.close();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeExpired() {
/* 633 */     final long now = System.currentTimeMillis();
/* 634 */     enumAvailable(new PoolEntryCallback<T, C>()
/*     */         {
/*     */           public void process(PoolEntry<T, C> entry)
/*     */           {
/* 638 */             if (entry.isExpired(now)) {
/* 639 */               entry.close();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValidateAfterInactivity() {
/* 651 */     return this.validateAfterInactivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValidateAfterInactivity(int ms) {
/* 659 */     this.validateAfterInactivity = ms;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 664 */     this.lock.lock();
/*     */     try {
/* 666 */       StringBuilder buffer = new StringBuilder();
/* 667 */       buffer.append("[leased: ");
/* 668 */       buffer.append(this.leased);
/* 669 */       buffer.append("][available: ");
/* 670 */       buffer.append(this.available);
/* 671 */       buffer.append("][pending: ");
/* 672 */       buffer.append(this.pending);
/* 673 */       buffer.append("]");
/* 674 */       return buffer.toString();
/*     */     } finally {
/* 676 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract E createEntry(T paramT, C paramC);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/pool/AbstractConnPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */