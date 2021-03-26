/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UncheckedIOException;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Functions
/*     */ {
/*     */   public static <T extends Throwable> void run(FailableRunnable<T> pRunnable) {
/*     */     try {
/* 135 */       pRunnable.run();
/* 136 */     } catch (Throwable t) {
/* 137 */       throw rethrow(t);
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
/*     */   public static <O, T extends Throwable> O call(FailableCallable<O, T> pCallable) {
/*     */     try {
/* 150 */       return pCallable.call();
/* 151 */     } catch (Throwable t) {
/* 152 */       throw rethrow(t);
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
/*     */   public static <O, T extends Throwable> void accept(FailableConsumer<O, T> pConsumer, O pObject) {
/*     */     try {
/* 165 */       pConsumer.accept(pObject);
/* 166 */     } catch (Throwable t) {
/* 167 */       throw rethrow(t);
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
/*     */   public static <O1, O2, T extends Throwable> void accept(FailableBiConsumer<O1, O2, T> pConsumer, O1 pObject1, O2 pObject2) {
/*     */     try {
/* 182 */       pConsumer.accept(pObject1, pObject2);
/* 183 */     } catch (Throwable t) {
/* 184 */       throw rethrow(t);
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
/*     */   public static <I, O, T extends Throwable> O apply(FailableFunction<I, O, T> pFunction, I pInput) {
/*     */     try {
/* 199 */       return pFunction.apply(pInput);
/* 200 */     } catch (Throwable t) {
/* 201 */       throw rethrow(t);
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
/*     */   public static <I1, I2, O, T extends Throwable> O apply(FailableBiFunction<I1, I2, O, T> pFunction, I1 pInput1, I2 pInput2) {
/*     */     try {
/* 218 */       return pFunction.apply(pInput1, pInput2);
/* 219 */     } catch (Throwable t) {
/* 220 */       throw rethrow(t);
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
/*     */   public static <O, T extends Throwable> boolean test(FailablePredicate<O, T> pPredicate, O pObject) {
/*     */     try {
/* 234 */       return pPredicate.test(pObject);
/* 235 */     } catch (Throwable t) {
/* 236 */       throw rethrow(t);
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
/*     */   public static <O1, O2, T extends Throwable> boolean test(FailableBiPredicate<O1, O2, T> pPredicate, O1 pObject1, O2 pObject2) {
/*     */     try {
/* 252 */       return pPredicate.test(pObject1, pObject2);
/* 253 */     } catch (Throwable t) {
/* 254 */       throw rethrow(t);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SafeVarargs
/*     */   public static void tryWithResources(FailableRunnable<? extends Throwable> pAction, FailableConsumer<Throwable, ? extends Throwable> pErrorHandler, FailableRunnable<? extends Throwable>... pResources) {
/*     */     FailableConsumer<Throwable, ? extends Throwable> errorHandler;
/* 285 */     if (pErrorHandler == null) {
/* 286 */       errorHandler = (t -> rethrow(t));
/*     */     } else {
/* 288 */       errorHandler = pErrorHandler;
/*     */     } 
/* 290 */     if (pResources != null) {
/* 291 */       for (FailableRunnable<? extends Throwable> runnable : pResources) {
/* 292 */         if (runnable == null) {
/* 293 */           throw new NullPointerException("A resource action must not be null.");
/*     */         }
/*     */       } 
/*     */     }
/* 297 */     Throwable th = null;
/*     */     try {
/* 299 */       pAction.run();
/* 300 */     } catch (Throwable t) {
/* 301 */       th = t;
/*     */     } 
/* 303 */     if (pResources != null) {
/* 304 */       for (FailableRunnable<? extends Throwable> failableRunnable : pResources) {
/*     */         try {
/* 306 */           failableRunnable.run();
/* 307 */         } catch (Throwable t) {
/* 308 */           if (th == null) {
/* 309 */             th = t;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 314 */     if (th != null) {
/*     */       try {
/* 316 */         errorHandler.accept(th);
/* 317 */       } catch (Throwable t) {
/* 318 */         throw rethrow(t);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SafeVarargs
/*     */   public static void tryWithResources(FailableRunnable<? extends Throwable> pAction, FailableRunnable<? extends Throwable>... pResources) {
/* 345 */     tryWithResources(pAction, null, pResources);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RuntimeException rethrow(Throwable pThrowable) {
/* 354 */     if (pThrowable == null) {
/* 355 */       throw new NullPointerException("The Throwable must not be null.");
/*     */     }
/* 357 */     if (pThrowable instanceof RuntimeException)
/* 358 */       throw (RuntimeException)pThrowable; 
/* 359 */     if (pThrowable instanceof Error)
/* 360 */       throw (Error)pThrowable; 
/* 361 */     if (pThrowable instanceof IOException) {
/* 362 */       throw new UncheckedIOException((IOException)pThrowable);
/*     */     }
/* 364 */     throw new UndeclaredThrowableException(pThrowable);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableBiPredicate<O1, O2, T extends Throwable> {
/*     */     boolean test(O1 param1O1, O2 param1O2) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailablePredicate<O, T extends Throwable> {
/*     */     boolean test(O param1O) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableBiFunction<I1, I2, O, T extends Throwable> {
/*     */     O apply(I1 param1I1, I2 param1I2) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableFunction<I, O, T extends Throwable> {
/*     */     O apply(I param1I) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableBiConsumer<O1, O2, T extends Throwable> {
/*     */     void accept(O1 param1O1, O2 param1O2) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableConsumer<O, T extends Throwable> {
/*     */     void accept(O param1O) throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableCallable<O, T extends Throwable> {
/*     */     O call() throws T;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FailableRunnable<T extends Throwable> {
/*     */     void run() throws T;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/Functions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */