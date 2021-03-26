/*     */ package org.apache.http.nio.pool;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.nio.reactor.ConnectingIOReactor;
/*     */ import org.apache.http.nio.reactor.IOReactorStatus;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionRequest;
/*     */ import org.apache.http.nio.reactor.SessionRequestCallback;
/*     */ import org.apache.http.pool.ConnPool;
/*     */ import org.apache.http.pool.ConnPoolControl;
/*     */ import org.apache.http.pool.PoolEntry;
/*     */ import org.apache.http.pool.PoolEntryCallback;
/*     */ import org.apache.http.pool.PoolStats;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ import org.apache.http.util.LangUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public abstract class AbstractNIOConnPool<T, C, E extends PoolEntry<T, C>>
/*     */   implements ConnPool<T, E>, ConnPoolControl<T>
/*     */ {
/*     */   private final ConnectingIOReactor ioReactor;
/*     */   private final NIOConnFactory<T, C> connFactory;
/*     */   private final SocketAddressResolver<T> addressResolver;
/*     */   private final SessionRequestCallback sessionRequestCallback;
/*     */   private final Map<T, RouteSpecificPool<T, C, E>> routeToPool;
/*     */   private final LinkedList<LeaseRequest<T, C, E>> leasingRequests;
/*     */   private final Set<SessionRequest> pending;
/*     */   private final Set<E> leased;
/*     */   private final LinkedList<E> available;
/*     */   private final ConcurrentLinkedQueue<LeaseRequest<T, C, E>> completedRequests;
/*     */   private final Map<T, Integer> maxPerRoute;
/*     */   private final Lock lock;
/*     */   private final AtomicBoolean isShutDown;
/*     */   private volatile int defaultMaxPerRoute;
/*     */   private volatile int maxTotal;
/*     */   
/*     */   @Deprecated
/*     */   public AbstractNIOConnPool(ConnectingIOReactor ioReactor, NIOConnFactory<T, C> connFactory, int defaultMaxPerRoute, int maxTotal) {
/* 106 */     Args.notNull(ioReactor, "I/O reactor");
/* 107 */     Args.notNull(connFactory, "Connection factory");
/* 108 */     Args.positive(defaultMaxPerRoute, "Max per route value");
/* 109 */     Args.positive(maxTotal, "Max total value");
/* 110 */     this.ioReactor = ioReactor;
/* 111 */     this.connFactory = connFactory;
/* 112 */     this.addressResolver = new SocketAddressResolver<T>()
/*     */       {
/*     */         public SocketAddress resolveLocalAddress(T route) throws IOException
/*     */         {
/* 116 */           return AbstractNIOConnPool.this.resolveLocalAddress(route);
/*     */         }
/*     */ 
/*     */         
/*     */         public SocketAddress resolveRemoteAddress(T route) throws IOException {
/* 121 */           return AbstractNIOConnPool.this.resolveRemoteAddress(route);
/*     */         }
/*     */       };
/*     */     
/* 125 */     this.sessionRequestCallback = new InternalSessionRequestCallback();
/* 126 */     this.routeToPool = new HashMap<T, RouteSpecificPool<T, C, E>>();
/* 127 */     this.leasingRequests = new LinkedList<LeaseRequest<T, C, E>>();
/* 128 */     this.pending = new HashSet<SessionRequest>();
/* 129 */     this.leased = new HashSet<E>();
/* 130 */     this.available = new LinkedList<E>();
/* 131 */     this.maxPerRoute = new HashMap<T, Integer>();
/* 132 */     this.completedRequests = new ConcurrentLinkedQueue<LeaseRequest<T, C, E>>();
/* 133 */     this.lock = new ReentrantLock();
/* 134 */     this.isShutDown = new AtomicBoolean(false);
/* 135 */     this.defaultMaxPerRoute = defaultMaxPerRoute;
/* 136 */     this.maxTotal = maxTotal;
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
/*     */   public AbstractNIOConnPool(ConnectingIOReactor ioReactor, NIOConnFactory<T, C> connFactory, SocketAddressResolver<T> addressResolver, int defaultMaxPerRoute, int maxTotal) {
/* 149 */     Args.notNull(ioReactor, "I/O reactor");
/* 150 */     Args.notNull(connFactory, "Connection factory");
/* 151 */     Args.notNull(addressResolver, "Address resolver");
/* 152 */     Args.positive(defaultMaxPerRoute, "Max per route value");
/* 153 */     Args.positive(maxTotal, "Max total value");
/* 154 */     this.ioReactor = ioReactor;
/* 155 */     this.connFactory = connFactory;
/* 156 */     this.addressResolver = addressResolver;
/* 157 */     this.sessionRequestCallback = new InternalSessionRequestCallback();
/* 158 */     this.routeToPool = new HashMap<T, RouteSpecificPool<T, C, E>>();
/* 159 */     this.leasingRequests = new LinkedList<LeaseRequest<T, C, E>>();
/* 160 */     this.pending = new HashSet<SessionRequest>();
/* 161 */     this.leased = new HashSet<E>();
/* 162 */     this.available = new LinkedList<E>();
/* 163 */     this.completedRequests = new ConcurrentLinkedQueue<LeaseRequest<T, C, E>>();
/* 164 */     this.maxPerRoute = new HashMap<T, Integer>();
/* 165 */     this.lock = new ReentrantLock();
/* 166 */     this.isShutDown = new AtomicBoolean(false);
/* 167 */     this.defaultMaxPerRoute = defaultMaxPerRoute;
/* 168 */     this.maxTotal = maxTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected SocketAddress resolveRemoteAddress(T route) {
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected SocketAddress resolveLocalAddress(T route) {
/* 184 */     return null;
/*     */   }
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
/*     */   protected void onRelease(E entry) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onReuse(E entry) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShutdown() {
/* 208 */     return this.isShutDown.get();
/*     */   }
/*     */   
/*     */   public void shutdown(long waitMs) throws IOException {
/* 212 */     if (this.isShutDown.compareAndSet(false, true)) {
/* 213 */       fireCallbacks();
/* 214 */       this.lock.lock();
/*     */       try {
/* 216 */         for (SessionRequest sessionRequest : this.pending) {
/* 217 */           sessionRequest.cancel();
/*     */         }
/* 219 */         for (PoolEntry poolEntry : this.available) {
/* 220 */           poolEntry.close();
/*     */         }
/* 222 */         for (PoolEntry poolEntry : this.leased) {
/* 223 */           poolEntry.close();
/*     */         }
/* 225 */         for (RouteSpecificPool<T, C, E> pool : this.routeToPool.values()) {
/* 226 */           pool.shutdown();
/*     */         }
/* 228 */         this.routeToPool.clear();
/* 229 */         this.leased.clear();
/* 230 */         this.pending.clear();
/* 231 */         this.available.clear();
/* 232 */         this.leasingRequests.clear();
/* 233 */         this.ioReactor.shutdown(waitMs);
/*     */       } finally {
/* 235 */         this.lock.unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private RouteSpecificPool<T, C, E> getPool(T route) {
/* 241 */     RouteSpecificPool<T, C, E> pool = this.routeToPool.get(route);
/* 242 */     if (pool == null) {
/* 243 */       pool = new RouteSpecificPool<T, C, E>(route)
/*     */         {
/*     */           protected E createEntry(T route, C conn)
/*     */           {
/* 247 */             return AbstractNIOConnPool.this.createEntry(route, conn);
/*     */           }
/*     */         };
/*     */       
/* 251 */       this.routeToPool.put(route, pool);
/*     */     } 
/* 253 */     return pool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Future<E> lease(T route, Object state, long connectTimeout, TimeUnit timeUnit, FutureCallback<E> callback) {
/* 260 */     return lease(route, state, connectTimeout, connectTimeout, timeUnit, callback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Future<E> lease(T route, Object state, long connectTimeout, long leaseTimeout, TimeUnit timeUnit, FutureCallback<E> callback) {
/* 270 */     Args.notNull(route, "Route");
/* 271 */     Args.notNull(timeUnit, "Time unit");
/* 272 */     Asserts.check(!this.isShutDown.get(), "Connection pool shut down");
/* 273 */     final BasicFuture<E> future = new BasicFuture(callback);
/* 274 */     final LeaseRequest<T, C, E> leaseRequest = new LeaseRequest<T, C, E>(route, state, (connectTimeout >= 0L) ? timeUnit.toMillis(connectTimeout) : -1L, (leaseTimeout > 0L) ? timeUnit.toMillis(leaseTimeout) : 0L, future);
/*     */ 
/*     */ 
/*     */     
/* 278 */     this.lock.lock();
/*     */     try {
/* 280 */       boolean completed = processPendingRequest(leaseRequest);
/* 281 */       if (!leaseRequest.isDone() && !completed) {
/* 282 */         this.leasingRequests.add(leaseRequest);
/*     */       }
/* 284 */       if (leaseRequest.isDone()) {
/* 285 */         this.completedRequests.add(leaseRequest);
/*     */       }
/*     */     } finally {
/* 288 */       this.lock.unlock();
/*     */     } 
/* 290 */     fireCallbacks();
/* 291 */     return new Future<E>()
/*     */       {
/*     */         public E get() throws InterruptedException, ExecutionException
/*     */         {
/* 295 */           return (E)future.get();
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public E get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
/* 302 */           return (E)future.get(timeout, unit);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public boolean cancel(boolean mayInterruptIfRunning) {
/*     */           
/* 310 */           try { return future.cancel(mayInterruptIfRunning); } finally { Exception exception = null; }
/*     */         
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isCancelled() {
/* 316 */           return future.isCancelled();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isDone() {
/* 321 */           return future.isDone();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Future<E> lease(T route, Object state, FutureCallback<E> callback) {
/* 329 */     return lease(route, state, -1L, TimeUnit.MICROSECONDS, callback);
/*     */   }
/*     */   
/*     */   public Future<E> lease(T route, Object state) {
/* 333 */     return lease(route, state, -1L, TimeUnit.MICROSECONDS, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void release(E entry, boolean reusable) {
/* 338 */     if (entry == null) {
/*     */       return;
/*     */     }
/* 341 */     if (this.isShutDown.get()) {
/*     */       return;
/*     */     }
/* 344 */     this.lock.lock();
/*     */     try {
/* 346 */       if (this.leased.remove(entry)) {
/* 347 */         RouteSpecificPool<T, C, E> pool = getPool((T)entry.getRoute());
/* 348 */         pool.free(entry, reusable);
/* 349 */         if (reusable) {
/* 350 */           this.available.addFirst(entry);
/* 351 */           onRelease(entry);
/*     */         } else {
/* 353 */           entry.close();
/*     */         } 
/* 355 */         processNextPendingRequest();
/*     */       } 
/*     */     } finally {
/* 358 */       this.lock.unlock();
/*     */     } 
/* 360 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   private void processPendingRequests() {
/* 364 */     ListIterator<LeaseRequest<T, C, E>> it = this.leasingRequests.listIterator();
/* 365 */     while (it.hasNext()) {
/* 366 */       LeaseRequest<T, C, E> request = it.next();
/* 367 */       BasicFuture<E> future = request.getFuture();
/* 368 */       if (future.isCancelled()) {
/* 369 */         it.remove();
/*     */         continue;
/*     */       } 
/* 372 */       boolean completed = processPendingRequest(request);
/* 373 */       if (request.isDone() || completed) {
/* 374 */         it.remove();
/*     */       }
/* 376 */       if (request.isDone()) {
/* 377 */         this.completedRequests.add(request);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processNextPendingRequest() {
/* 383 */     ListIterator<LeaseRequest<T, C, E>> it = this.leasingRequests.listIterator();
/* 384 */     while (it.hasNext()) {
/* 385 */       LeaseRequest<T, C, E> request = it.next();
/* 386 */       BasicFuture<E> future = request.getFuture();
/* 387 */       if (future.isCancelled()) {
/* 388 */         it.remove();
/*     */         continue;
/*     */       } 
/* 391 */       boolean completed = processPendingRequest(request);
/* 392 */       if (request.isDone() || completed) {
/* 393 */         it.remove();
/*     */       }
/* 395 */       if (request.isDone()) {
/* 396 */         this.completedRequests.add(request);
/*     */       }
/* 398 */       if (completed)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean processPendingRequest(LeaseRequest<T, C, E> request) {
/*     */     E entry;
/* 405 */     T route = request.getRoute();
/* 406 */     Object state = request.getState();
/* 407 */     long deadline = request.getDeadline();
/*     */     
/* 409 */     long now = System.currentTimeMillis();
/* 410 */     if (now > deadline) {
/* 411 */       request.failed(new TimeoutException("Connection lease request time out"));
/* 412 */       return false;
/*     */     } 
/*     */     
/* 415 */     RouteSpecificPool<T, C, E> pool = getPool(route);
/*     */     
/*     */     while (true) {
/* 418 */       entry = pool.getFree(state);
/* 419 */       if (entry == null) {
/*     */         break;
/*     */       }
/* 422 */       if (entry.isClosed() || entry.isExpired(System.currentTimeMillis())) {
/* 423 */         entry.close();
/* 424 */         this.available.remove(entry);
/* 425 */         pool.free(entry, false);
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 430 */     if (entry != null) {
/* 431 */       this.available.remove(entry);
/* 432 */       this.leased.add(entry);
/* 433 */       request.completed(entry);
/* 434 */       onReuse(entry);
/* 435 */       onLease(entry);
/* 436 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 440 */     int maxPerRoute = getMax(route);
/*     */     
/* 442 */     int excess = Math.max(0, pool.getAllocatedCount() + 1 - maxPerRoute);
/* 443 */     if (excess > 0) {
/* 444 */       for (int i = 0; i < excess; i++) {
/* 445 */         E lastUsed = pool.getLastUsed();
/* 446 */         if (lastUsed == null) {
/*     */           break;
/*     */         }
/* 449 */         lastUsed.close();
/* 450 */         this.available.remove(lastUsed);
/* 451 */         pool.remove(lastUsed);
/*     */       } 
/*     */     }
/*     */     
/* 455 */     if (pool.getAllocatedCount() < maxPerRoute) {
/* 456 */       SocketAddress localAddress, remoteAddress; int totalUsed = this.pending.size() + this.leased.size();
/* 457 */       int freeCapacity = Math.max(this.maxTotal - totalUsed, 0);
/* 458 */       if (freeCapacity == 0) {
/* 459 */         return false;
/*     */       }
/* 461 */       int totalAvailable = this.available.size();
/* 462 */       if (totalAvailable > freeCapacity - 1 && 
/* 463 */         !this.available.isEmpty()) {
/* 464 */         PoolEntry poolEntry = (PoolEntry)this.available.removeLast();
/* 465 */         poolEntry.close();
/* 466 */         RouteSpecificPool<T, C, E> otherpool = getPool((T)poolEntry.getRoute());
/* 467 */         otherpool.remove((E)poolEntry);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 474 */         remoteAddress = this.addressResolver.resolveRemoteAddress(route);
/* 475 */         localAddress = this.addressResolver.resolveLocalAddress(route);
/* 476 */       } catch (IOException ex) {
/* 477 */         request.failed(ex);
/* 478 */         return false;
/*     */       } 
/*     */       
/* 481 */       SessionRequest sessionRequest = this.ioReactor.connect(remoteAddress, localAddress, route, this.sessionRequestCallback);
/*     */       
/* 483 */       request.attachSessionRequest(sessionRequest);
/* 484 */       long connectTimeout = request.getConnectTimeout();
/* 485 */       if (connectTimeout >= 0L) {
/* 486 */         sessionRequest.setConnectTimeout((connectTimeout < 2147483647L) ? (int)connectTimeout : Integer.MAX_VALUE);
/*     */       }
/* 488 */       this.pending.add(sessionRequest);
/* 489 */       pool.addPending(sessionRequest, request.getFuture());
/* 490 */       return true;
/*     */     } 
/* 492 */     return false;
/*     */   }
/*     */   
/*     */   private void fireCallbacks() {
/*     */     LeaseRequest<T, C, E> request;
/* 497 */     while ((request = (LeaseRequest<T, C, E>)this.completedRequests.poll()) != null) {
/* 498 */       BasicFuture<E> future = request.getFuture();
/* 499 */       Exception ex = request.getException();
/* 500 */       E result = request.getResult();
/* 501 */       boolean successfullyCompleted = false;
/* 502 */       if (ex != null) {
/* 503 */         future.failed(ex);
/* 504 */       } else if (result != null) {
/* 505 */         if (future.completed(result)) {
/* 506 */           successfullyCompleted = true;
/*     */         }
/*     */       } else {
/* 509 */         future.cancel();
/*     */       } 
/* 511 */       if (!successfullyCompleted) {
/* 512 */         release(result, true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validatePendingRequests() {
/* 518 */     this.lock.lock();
/*     */     try {
/* 520 */       long now = System.currentTimeMillis();
/* 521 */       ListIterator<LeaseRequest<T, C, E>> it = this.leasingRequests.listIterator();
/* 522 */       while (it.hasNext()) {
/* 523 */         LeaseRequest<T, C, E> request = it.next();
/* 524 */         BasicFuture<E> future = request.getFuture();
/* 525 */         if (future.isCancelled() && !request.isDone()) {
/* 526 */           it.remove(); continue;
/*     */         } 
/* 528 */         long deadline = request.getDeadline();
/* 529 */         if (now > deadline) {
/* 530 */           request.failed(new TimeoutException("Connection lease request time out"));
/*     */         }
/* 532 */         if (request.isDone()) {
/* 533 */           it.remove();
/* 534 */           this.completedRequests.add(request);
/*     */         } 
/*     */       } 
/*     */     } finally {
/*     */       
/* 539 */       this.lock.unlock();
/*     */     } 
/* 541 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   protected void requestCompleted(SessionRequest request) {
/* 545 */     if (this.isShutDown.get()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 550 */     T route = (T)request.getAttachment();
/* 551 */     this.lock.lock();
/*     */     try {
/* 553 */       this.pending.remove(request);
/* 554 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 555 */       IOSession session = request.getSession();
/*     */       try {
/* 557 */         C conn = this.connFactory.create(route, session);
/* 558 */         E entry = pool.createEntry(request, conn);
/* 559 */         if (pool.completed(request, entry)) {
/* 560 */           this.leased.add(entry);
/* 561 */           onLease(entry);
/*     */         } else {
/* 563 */           this.available.add(entry);
/* 564 */           if (this.ioReactor.getStatus().compareTo((Enum)IOReactorStatus.ACTIVE) <= 0) {
/* 565 */             processNextPendingRequest();
/*     */           }
/*     */         } 
/* 568 */       } catch (IOException ex) {
/* 569 */         pool.failed(request, ex);
/*     */       } 
/*     */     } finally {
/* 572 */       this.lock.unlock();
/*     */     } 
/* 574 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   protected void requestCancelled(SessionRequest request) {
/* 578 */     if (this.isShutDown.get()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 583 */     T route = (T)request.getAttachment();
/* 584 */     this.lock.lock();
/*     */     try {
/* 586 */       this.pending.remove(request);
/* 587 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 588 */       pool.cancelled(request);
/* 589 */       if (this.ioReactor.getStatus().compareTo((Enum)IOReactorStatus.ACTIVE) <= 0) {
/* 590 */         processNextPendingRequest();
/*     */       }
/*     */     } finally {
/* 593 */       this.lock.unlock();
/*     */     } 
/* 595 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   protected void requestFailed(SessionRequest request) {
/* 599 */     if (this.isShutDown.get()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 604 */     T route = (T)request.getAttachment();
/* 605 */     this.lock.lock();
/*     */     try {
/* 607 */       this.pending.remove(request);
/* 608 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 609 */       pool.failed(request, request.getException());
/* 610 */       processNextPendingRequest();
/*     */     } finally {
/* 612 */       this.lock.unlock();
/*     */     } 
/* 614 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   protected void requestTimeout(SessionRequest request) {
/* 618 */     if (this.isShutDown.get()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 623 */     T route = (T)request.getAttachment();
/* 624 */     this.lock.lock();
/*     */     try {
/* 626 */       this.pending.remove(request);
/* 627 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 628 */       pool.timeout(request);
/* 629 */       processNextPendingRequest();
/*     */     } finally {
/* 631 */       this.lock.unlock();
/*     */     } 
/* 633 */     fireCallbacks();
/*     */   }
/*     */   
/*     */   private int getMax(T route) {
/* 637 */     Integer v = this.maxPerRoute.get(route);
/* 638 */     return (v != null) ? v.intValue() : this.defaultMaxPerRoute;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxTotal(int max) {
/* 643 */     Args.positive(max, "Max value");
/* 644 */     this.lock.lock();
/*     */     try {
/* 646 */       this.maxTotal = max;
/*     */     } finally {
/* 648 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxTotal() {
/* 654 */     this.lock.lock();
/*     */     try {
/* 656 */       return this.maxTotal;
/*     */     } finally {
/* 658 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMaxPerRoute(int max) {
/* 664 */     Args.positive(max, "Max value");
/* 665 */     this.lock.lock();
/*     */     try {
/* 667 */       this.defaultMaxPerRoute = max;
/*     */     } finally {
/* 669 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultMaxPerRoute() {
/* 675 */     this.lock.lock();
/*     */     try {
/* 677 */       return this.defaultMaxPerRoute;
/*     */     } finally {
/* 679 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxPerRoute(T route, int max) {
/* 685 */     Args.notNull(route, "Route");
/* 686 */     this.lock.lock();
/*     */     try {
/* 688 */       if (max > -1) {
/* 689 */         this.maxPerRoute.put(route, Integer.valueOf(max));
/*     */       } else {
/* 691 */         this.maxPerRoute.remove(route);
/*     */       } 
/*     */     } finally {
/* 694 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxPerRoute(T route) {
/* 700 */     Args.notNull(route, "Route");
/* 701 */     this.lock.lock();
/*     */     try {
/* 703 */       return getMax(route);
/*     */     } finally {
/* 705 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getTotalStats() {
/* 711 */     this.lock.lock();
/*     */     try {
/* 713 */       return new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 719 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getStats(T route) {
/* 725 */     Args.notNull(route, "Route");
/* 726 */     this.lock.lock();
/*     */     try {
/* 728 */       RouteSpecificPool<T, C, E> pool = getPool(route);
/* 729 */       int pendingCount = 0;
/* 730 */       for (LeaseRequest<T, C, E> request : this.leasingRequests) {
/* 731 */         if (LangUtils.equals(route, request.getRoute())) {
/* 732 */           pendingCount++;
/*     */         }
/*     */       } 
/* 735 */       return new PoolStats(pool.getLeasedCount(), pendingCount + pool.getPendingCount(), pool.getAvailableCount(), getMax(route));
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 741 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<T> getRoutes() {
/* 751 */     this.lock.lock();
/*     */     try {
/* 753 */       return new HashSet(this.routeToPool.keySet());
/*     */     } finally {
/* 755 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enumAvailable(PoolEntryCallback<T, C> callback) {
/* 765 */     this.lock.lock();
/*     */     try {
/* 767 */       Iterator<E> it = this.available.iterator();
/* 768 */       while (it.hasNext()) {
/* 769 */         PoolEntry poolEntry = (PoolEntry)it.next();
/* 770 */         callback.process(poolEntry);
/* 771 */         if (poolEntry.isClosed()) {
/* 772 */           RouteSpecificPool<T, C, E> pool = getPool((T)poolEntry.getRoute());
/* 773 */           pool.remove((E)poolEntry);
/* 774 */           it.remove();
/*     */         } 
/*     */       } 
/* 777 */       processPendingRequests();
/* 778 */       purgePoolMap();
/*     */     } finally {
/* 780 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enumLeased(PoolEntryCallback<T, C> callback) {
/* 790 */     this.lock.lock();
/*     */     try {
/* 792 */       Iterator<E> it = this.leased.iterator();
/* 793 */       while (it.hasNext()) {
/* 794 */         PoolEntry poolEntry = (PoolEntry)it.next();
/* 795 */         callback.process(poolEntry);
/*     */       } 
/* 797 */       processPendingRequests();
/*     */     } finally {
/* 799 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void enumEntries(Iterator<E> it, PoolEntryCallback<T, C> callback) {
/* 811 */     while (it.hasNext()) {
/* 812 */       PoolEntry poolEntry = (PoolEntry)it.next();
/* 813 */       callback.process(poolEntry);
/*     */     } 
/* 815 */     processPendingRequests();
/*     */   }
/*     */   
/*     */   private void purgePoolMap() {
/* 819 */     Iterator<Map.Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
/* 820 */     while (it.hasNext()) {
/* 821 */       Map.Entry<T, RouteSpecificPool<T, C, E>> entry = it.next();
/* 822 */       RouteSpecificPool<T, C, E> pool = entry.getValue();
/* 823 */       if (pool.getAllocatedCount() == 0) {
/* 824 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void closeIdle(long idletime, TimeUnit timeUnit) {
/* 830 */     Args.notNull(timeUnit, "Time unit");
/* 831 */     long time = timeUnit.toMillis(idletime);
/* 832 */     if (time < 0L) {
/* 833 */       time = 0L;
/*     */     }
/* 835 */     final long deadline = System.currentTimeMillis() - time;
/* 836 */     enumAvailable(new PoolEntryCallback<T, C>()
/*     */         {
/*     */           public void process(PoolEntry<T, C> entry)
/*     */           {
/* 840 */             if (entry.getUpdated() <= deadline) {
/* 841 */               entry.close();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeExpired() {
/* 849 */     final long now = System.currentTimeMillis();
/* 850 */     enumAvailable(new PoolEntryCallback<T, C>()
/*     */         {
/*     */           public void process(PoolEntry<T, C> entry)
/*     */           {
/* 854 */             if (entry.isExpired(now)) {
/* 855 */               entry.close();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 864 */     StringBuilder buffer = new StringBuilder();
/* 865 */     buffer.append("[leased: ");
/* 866 */     buffer.append(this.leased);
/* 867 */     buffer.append("][available: ");
/* 868 */     buffer.append(this.available);
/* 869 */     buffer.append("][pending: ");
/* 870 */     buffer.append(this.pending);
/* 871 */     buffer.append("]");
/* 872 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   protected abstract E createEntry(T paramT, C paramC);
/*     */   
/*     */   class InternalSessionRequestCallback implements SessionRequestCallback {
/*     */     public void completed(SessionRequest request) {
/* 879 */       AbstractNIOConnPool.this.requestCompleted(request);
/*     */     }
/*     */ 
/*     */     
/*     */     public void cancelled(SessionRequest request) {
/* 884 */       AbstractNIOConnPool.this.requestCancelled(request);
/*     */     }
/*     */ 
/*     */     
/*     */     public void failed(SessionRequest request) {
/* 889 */       AbstractNIOConnPool.this.requestFailed(request);
/*     */     }
/*     */ 
/*     */     
/*     */     public void timeout(SessionRequest request) {
/* 894 */       AbstractNIOConnPool.this.requestTimeout(request);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/pool/AbstractNIOConnPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */