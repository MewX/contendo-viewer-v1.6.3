/*     */ package org.apache.http.nio.pool;
/*     */ 
/*     */ import java.net.ConnectException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.nio.reactor.SessionRequest;
/*     */ import org.apache.http.pool.PoolEntry;
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
/*     */ abstract class RouteSpecificPool<T, C, E extends PoolEntry<T, C>>
/*     */ {
/*     */   private final T route;
/*     */   private final Set<E> leased;
/*     */   private final LinkedList<E> available;
/*     */   private final Map<SessionRequest, BasicFuture<E>> pending;
/*     */   
/*     */   RouteSpecificPool(T route) {
/*  52 */     this.route = route;
/*  53 */     this.leased = new HashSet<E>();
/*  54 */     this.available = new LinkedList<E>();
/*  55 */     this.pending = new HashMap<SessionRequest, BasicFuture<E>>();
/*     */   }
/*     */   
/*     */   public T getRoute() {
/*  59 */     return this.route;
/*     */   }
/*     */   
/*     */   protected abstract E createEntry(T paramT, C paramC);
/*     */   
/*     */   public int getLeasedCount() {
/*  65 */     return this.leased.size();
/*     */   }
/*     */   
/*     */   public int getPendingCount() {
/*  69 */     return this.pending.size();
/*     */   }
/*     */   
/*     */   public int getAvailableCount() {
/*  73 */     return this.available.size();
/*     */   }
/*     */   
/*     */   public int getAllocatedCount() {
/*  77 */     return this.available.size() + this.leased.size() + this.pending.size();
/*     */   }
/*     */   
/*     */   public E getFree(Object state) {
/*  81 */     if (!this.available.isEmpty()) {
/*  82 */       if (state != null) {
/*  83 */         Iterator<E> iterator = this.available.iterator();
/*  84 */         while (iterator.hasNext()) {
/*  85 */           PoolEntry poolEntry = (PoolEntry)iterator.next();
/*  86 */           if (state.equals(poolEntry.getState())) {
/*  87 */             iterator.remove();
/*  88 */             this.leased.add((E)poolEntry);
/*  89 */             return (E)poolEntry;
/*     */           } 
/*     */         } 
/*     */       } 
/*  93 */       Iterator<E> it = this.available.iterator();
/*  94 */       while (it.hasNext()) {
/*  95 */         PoolEntry poolEntry = (PoolEntry)it.next();
/*  96 */         if (poolEntry.getState() == null) {
/*  97 */           it.remove();
/*  98 */           this.leased.add((E)poolEntry);
/*  99 */           return (E)poolEntry;
/*     */         } 
/*     */       } 
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public E getLastUsed() {
/* 107 */     return this.available.isEmpty() ? null : this.available.getLast();
/*     */   }
/*     */   
/*     */   public boolean remove(E entry) {
/* 111 */     Args.notNull(entry, "Pool entry");
/* 112 */     if (!this.available.remove(entry) && 
/* 113 */       !this.leased.remove(entry)) {
/* 114 */       return false;
/*     */     }
/*     */     
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public void free(E entry, boolean reusable) {
/* 121 */     Args.notNull(entry, "Pool entry");
/* 122 */     boolean found = this.leased.remove(entry);
/* 123 */     Asserts.check(found, "Entry %s has not been leased from this pool", entry);
/* 124 */     if (reusable) {
/* 125 */       this.available.addFirst(entry);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addPending(SessionRequest request, BasicFuture<E> future) {
/* 130 */     this.pending.put(request, future);
/*     */   }
/*     */   
/*     */   private BasicFuture<E> removeRequest(SessionRequest request) {
/* 134 */     return this.pending.remove(request);
/*     */   }
/*     */   
/*     */   public E createEntry(SessionRequest request, C conn) {
/* 138 */     E entry = createEntry(this.route, conn);
/* 139 */     this.leased.add(entry);
/* 140 */     return entry;
/*     */   }
/*     */   
/*     */   public boolean completed(SessionRequest request, E entry) {
/* 144 */     BasicFuture<E> future = removeRequest(request);
/* 145 */     if (future != null) {
/* 146 */       return future.completed(entry);
/*     */     }
/* 148 */     request.cancel();
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public void cancelled(SessionRequest request) {
/* 153 */     BasicFuture<E> future = removeRequest(request);
/* 154 */     if (future != null) {
/* 155 */       future.cancel(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void failed(SessionRequest request, Exception ex) {
/* 160 */     BasicFuture<E> future = removeRequest(request);
/* 161 */     if (future != null) {
/* 162 */       future.failed(ex);
/*     */     }
/*     */   }
/*     */   
/*     */   public void timeout(SessionRequest request) {
/* 167 */     BasicFuture<E> future = removeRequest(request);
/* 168 */     if (future != null) {
/* 169 */       future.failed(new ConnectException("Timeout connecting to [" + request.getRemoteAddress() + "]"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 174 */     for (SessionRequest request : this.pending.keySet()) {
/* 175 */       request.cancel();
/*     */     }
/* 177 */     this.pending.clear();
/* 178 */     for (PoolEntry poolEntry : this.available) {
/* 179 */       poolEntry.close();
/*     */     }
/* 181 */     this.available.clear();
/* 182 */     for (PoolEntry poolEntry : this.leased) {
/* 183 */       poolEntry.close();
/*     */     }
/* 185 */     this.leased.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 190 */     StringBuilder buffer = new StringBuilder();
/* 191 */     buffer.append("[route: ");
/* 192 */     buffer.append(this.route);
/* 193 */     buffer.append("][leased: ");
/* 194 */     buffer.append(this.leased.size());
/* 195 */     buffer.append("][available: ");
/* 196 */     buffer.append(this.available.size());
/* 197 */     buffer.append("][pending: ");
/* 198 */     buffer.append(this.pending.size());
/* 199 */     buffer.append("]");
/* 200 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/pool/RouteSpecificPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */