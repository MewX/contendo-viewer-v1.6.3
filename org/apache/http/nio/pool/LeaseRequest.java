/*     */ package org.apache.http.nio.pool;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.concurrent.Cancellable;
/*     */ import org.apache.http.nio.reactor.SessionRequest;
/*     */ import org.apache.http.pool.PoolEntry;
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
/*     */ class LeaseRequest<T, C, E extends PoolEntry<T, C>>
/*     */   implements Cancellable
/*     */ {
/*     */   private final T route;
/*     */   private final Object state;
/*     */   private final long connectTimeout;
/*     */   private final long deadline;
/*     */   private final BasicFuture<E> future;
/*     */   private final AtomicReference<SessionRequest> sessionRequestRef;
/*     */   private final AtomicBoolean completed;
/*     */   private volatile E result;
/*     */   private volatile Exception ex;
/*     */   
/*     */   public LeaseRequest(T route, Object state, long connectTimeout, long leaseTimeout, BasicFuture<E> future) {
/*  65 */     this.route = route;
/*  66 */     this.state = state;
/*  67 */     this.connectTimeout = connectTimeout;
/*  68 */     this.deadline = (leaseTimeout > 0L) ? (System.currentTimeMillis() + leaseTimeout) : Long.MAX_VALUE;
/*  69 */     this.future = future;
/*  70 */     this.sessionRequestRef = new AtomicReference<SessionRequest>(null);
/*  71 */     this.completed = new AtomicBoolean(false);
/*     */   }
/*     */   
/*     */   public T getRoute() {
/*  75 */     return this.route;
/*     */   }
/*     */   
/*     */   public Object getState() {
/*  79 */     return this.state;
/*     */   }
/*     */   
/*     */   public long getConnectTimeout() {
/*  83 */     return this.connectTimeout;
/*     */   }
/*     */   
/*     */   public long getDeadline() {
/*  87 */     return this.deadline;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/*  91 */     return this.completed.get();
/*     */   }
/*     */   
/*     */   public void attachSessionRequest(SessionRequest sessionRequest) {
/*  95 */     Asserts.check(this.sessionRequestRef.compareAndSet(null, sessionRequest), "Session request has already been set");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cancel() {
/* 100 */     boolean cancelled = this.completed.compareAndSet(false, true);
/* 101 */     SessionRequest sessionRequest = this.sessionRequestRef.getAndSet(null);
/* 102 */     if (sessionRequest != null) {
/* 103 */       sessionRequest.cancel();
/*     */     }
/* 105 */     return cancelled;
/*     */   }
/*     */   
/*     */   public void failed(Exception ex) {
/* 109 */     if (this.completed.compareAndSet(false, true)) {
/* 110 */       this.ex = ex;
/*     */     }
/*     */   }
/*     */   
/*     */   public void completed(E result) {
/* 115 */     if (this.completed.compareAndSet(false, true)) {
/* 116 */       this.result = result;
/*     */     }
/*     */   }
/*     */   
/*     */   public BasicFuture<E> getFuture() {
/* 121 */     return this.future;
/*     */   }
/*     */   
/*     */   public E getResult() {
/* 125 */     return this.result;
/*     */   }
/*     */   
/*     */   public Exception getException() {
/* 129 */     return this.ex;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     StringBuilder buffer = new StringBuilder();
/* 135 */     buffer.append("[");
/* 136 */     buffer.append(this.route);
/* 137 */     buffer.append("][");
/* 138 */     buffer.append(this.state);
/* 139 */     buffer.append("]");
/* 140 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/pool/LeaseRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */