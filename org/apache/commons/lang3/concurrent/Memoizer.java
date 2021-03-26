/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CancellationException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.FutureTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Memoizer<I, O>
/*     */   implements Computable<I, O>
/*     */ {
/*  56 */   private final ConcurrentMap<I, Future<O>> cache = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Computable<I, O> computable;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean recalculate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Memoizer(Computable<I, O> computable) {
/*  74 */     this(computable, false);
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
/*     */   public Memoizer(Computable<I, O> computable, boolean recalculate) {
/*  91 */     this.computable = computable;
/*  92 */     this.recalculate = recalculate;
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
/*     */   public O compute(final I arg) throws InterruptedException {
/*     */     while (true) {
/* 116 */       Future<O> future = this.cache.get(arg);
/* 117 */       if (future == null) {
/* 118 */         Callable<O> eval = new Callable<O>()
/*     */           {
/*     */             public O call() throws InterruptedException
/*     */             {
/* 122 */               return (O)Memoizer.this.computable.compute(arg);
/*     */             }
/*     */           };
/* 125 */         FutureTask<O> futureTask = new FutureTask<>(eval);
/* 126 */         future = this.cache.putIfAbsent(arg, futureTask);
/* 127 */         if (future == null) {
/* 128 */           future = futureTask;
/* 129 */           futureTask.run();
/*     */         } 
/*     */       } 
/*     */       try {
/* 133 */         return future.get();
/* 134 */       } catch (CancellationException e) {
/* 135 */         this.cache.remove(arg, future);
/* 136 */       } catch (ExecutionException e) {
/* 137 */         if (this.recalculate) {
/* 138 */           this.cache.remove(arg, future);
/*     */         }
/*     */         
/* 141 */         throw launderException(e.getCause());
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
/*     */ 
/*     */ 
/*     */   
/*     */   private RuntimeException launderException(Throwable throwable) {
/* 157 */     if (throwable instanceof RuntimeException)
/* 158 */       return (RuntimeException)throwable; 
/* 159 */     if (throwable instanceof Error) {
/* 160 */       throw (Error)throwable;
/*     */     }
/* 162 */     throw new IllegalStateException("Unchecked exception", throwable);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/concurrent/Memoizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */