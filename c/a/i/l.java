/*     */ package c.a.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class l
/*     */ {
/*     */   public static final String a = "jj2000.j2k.util.ThreadPool.concurrency";
/*     */   private a[] b;
/*     */   private int c;
/*     */   private String d;
/*     */   private int e;
/*     */   private volatile Error f;
/*     */   private volatile RuntimeException g;
/*     */   
/*     */   class a
/*     */     extends Thread
/*     */   {
/*     */     private Runnable b;
/*     */     private Object c;
/*     */     private boolean d;
/*     */     
/*     */     public a(l this$0, int idx, String name) {
/* 172 */       super(name);
/* 173 */       setDaemon(true);
/* 174 */       setPriority(l.a(this$0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 199 */       l.a(this.a, this);
/*     */ 
/*     */ 
/*     */       
/* 203 */       synchronized (this) {
/*     */         
/*     */         while (true) {
/* 206 */           while (this.b == null) {
/*     */             try {
/* 208 */               wait();
/* 209 */             } catch (InterruptedException interruptedException) {}
/*     */           } 
/*     */ 
/*     */           
/*     */           try {
/* 214 */             this.b.run();
/* 215 */           } catch (ThreadDeath td) {
/*     */ 
/*     */ 
/*     */             
/* 219 */             c.b()
/* 220 */               .printmsg(2, "Thread.stop() called on a ThreadPool thread or ThreadDeath thrown. This is deprecated. Lock-up might occur.");
/*     */ 
/*     */ 
/*     */             
/* 224 */             throw td;
/* 225 */           } catch (Error e) {
/* 226 */             l.a(this.a, e);
/* 227 */           } catch (RuntimeException re) {
/* 228 */             l.a(this.a, re);
/* 229 */           } catch (Throwable ue) {
/*     */ 
/*     */ 
/*     */             
/* 233 */             l.a(this.a, new RuntimeException("Unchecked exception thrown by target's run() method in pool " + 
/*     */ 
/*     */                   
/* 236 */                   l.b(this.a) + "."));
/*     */           } 
/*     */           
/* 239 */           l.a(this.a, this);
/*     */           
/* 241 */           this.b = null;
/* 242 */           if (this.c != null) {
/* 243 */             synchronized (this.c) {
/* 244 */               if (this.d) {
/* 245 */                 this.c.notifyAll();
/*     */               } else {
/*     */                 
/* 248 */                 this.c.notify();
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     synchronized void a(Runnable target, Object lock, boolean notifyAll) {
/* 275 */       this.b = target;
/* 276 */       this.c = lock;
/* 277 */       this.d = notifyAll;
/*     */       
/* 279 */       notify();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public l(int size, int priority, String name) {
/* 314 */     if (size <= 0) {
/* 315 */       throw new IllegalArgumentException("Pool must be of positive size");
/*     */     }
/* 317 */     if (priority < 1) {
/* 318 */       this.e = Thread.currentThread().getPriority();
/*     */     } else {
/*     */       
/* 321 */       this.e = (priority < 10) ? priority : 10;
/*     */     } 
/*     */     
/* 324 */     if (name == null) {
/* 325 */       this.d = "Anonymous ThreadPool";
/*     */     } else {
/*     */       
/* 328 */       this.d = name;
/*     */     } 
/*     */ 
/*     */     
/* 332 */     String prop = null;
/*     */     try {
/* 334 */       prop = System.getProperty("jj2000.j2k.util.ThreadPool.concurrency");
/* 335 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */     
/* 338 */     if (prop != null) {
/*     */       int clevel;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 344 */         clevel = Integer.parseInt(prop);
/* 345 */         if (clevel < 0) throw new NumberFormatException(); 
/* 346 */       } catch (NumberFormatException e) {
/* 347 */         throw new IllegalArgumentException("Invalid concurrency level in property jj2000.j2k.util.ThreadPool.concurrency");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 352 */       if (h.b()) {
/*     */         
/* 354 */         c.b()
/* 355 */           .printmsg(1, "Changing thread concurrency level from " + 
/*     */             
/* 357 */             h.a() + " to " + clevel + ".");
/*     */         
/* 359 */         h.a(clevel);
/*     */       }
/*     */       else {
/*     */         
/* 363 */         c.b()
/* 364 */           .printmsg(2, "Native library to set thread concurrency level as specified by the jj2000.j2k.util.ThreadPool.concurrency property not found. Thread concurrency unchanged.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     this.b = new a[size];
/* 373 */     this.c = 0;
/*     */ 
/*     */     
/* 376 */     for (int i = 0; i < size; i++) {
/* 377 */       a t = new a(this, i, this.d + "-" + i);
/* 378 */       t.start();
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
/*     */   public int a() {
/* 390 */     return this.b.length;
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
/*     */   public boolean a(Runnable t, Object object) {
/* 415 */     return a(t, object, false, false);
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
/*     */   
/*     */   public boolean a(Runnable t, Object object, boolean async) {
/* 444 */     return a(t, object, async, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Runnable t, Object object, boolean async, boolean notifyAll) {
/* 479 */     a runner = a(async);
/*     */     
/* 481 */     if (runner == null) return false;
/*     */     
/* 483 */     runner.a(t, object, notifyAll);
/* 484 */     return true;
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
/*     */   public void b() {
/* 500 */     if (this.f != null) throw this.f;
/*     */     
/* 502 */     if (this.g != null) throw this.g;
/*     */   
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
/*     */   public void c() {
/* 520 */     this.f = null;
/* 521 */     this.g = null;
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
/*     */   private void a(a t) {
/* 542 */     synchronized (this.b) {
/* 543 */       this.b[this.c] = t;
/* 544 */       this.c++;
/*     */       
/* 546 */       if (this.c == 1) this.b.notify();
/*     */     
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
/*     */   private a a(boolean async) {
/* 569 */     synchronized (this.b) {
/* 570 */       if (async) {
/*     */         
/* 572 */         if (this.c == 0) return null;
/*     */       
/*     */       } else {
/*     */         
/* 576 */         while (this.c == 0) {
/*     */           try {
/* 578 */             this.b.wait();
/* 579 */           } catch (InterruptedException e) {
/*     */             
/* 581 */             return null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 586 */       this.c--;
/* 587 */       return this.b[this.c];
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/l.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */