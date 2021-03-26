/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.net.Socket;
/*     */ import java.nio.channels.Channel;
/*     */ import java.nio.channels.ClosedChannelException;
/*     */ import java.nio.channels.ClosedSelectorException;
/*     */ import java.nio.channels.SelectableChannel;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.Selector;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.apache.http.nio.reactor.IOEventDispatch;
/*     */ import org.apache.http.nio.reactor.IOReactor;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorExceptionHandler;
/*     */ import org.apache.http.nio.reactor.IOReactorStatus;
/*     */ import org.apache.http.params.BasicHttpParams;
/*     */ import org.apache.http.params.HttpParams;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMultiworkerIOReactor
/*     */   implements IOReactor
/*     */ {
/*     */   protected volatile IOReactorStatus status;
/*     */   @Deprecated
/*     */   protected final HttpParams params;
/*     */   protected final IOReactorConfig config;
/*     */   protected final Selector selector;
/*     */   protected final long selectTimeout;
/*     */   protected final boolean interestOpsQueueing;
/*     */   private final int workerCount;
/*     */   private final ThreadFactory threadFactory;
/*     */   private final BaseIOReactor[] dispatchers;
/*     */   private final Worker[] workers;
/*     */   private final Thread[] threads;
/*     */   private final Object statusLock;
/*     */   protected IOReactorExceptionHandler exceptionHandler;
/*     */   protected List<ExceptionEvent> auditLog;
/* 123 */   private int currentWorker = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMultiworkerIOReactor(IOReactorConfig config, ThreadFactory threadFactory) throws IOReactorException {
/* 139 */     this.config = (config != null) ? config : IOReactorConfig.DEFAULT;
/* 140 */     this.params = (HttpParams)new BasicHttpParams();
/*     */     try {
/* 142 */       this.selector = Selector.open();
/* 143 */     } catch (IOException ex) {
/* 144 */       throw new IOReactorException("Failure opening selector", ex);
/*     */     } 
/* 146 */     this.selectTimeout = this.config.getSelectInterval();
/* 147 */     this.interestOpsQueueing = this.config.isInterestOpQueued();
/* 148 */     this.statusLock = new Object();
/* 149 */     if (threadFactory != null) {
/* 150 */       this.threadFactory = threadFactory;
/*     */     } else {
/* 152 */       this.threadFactory = new DefaultThreadFactory();
/*     */     } 
/* 154 */     this.auditLog = new ArrayList<ExceptionEvent>();
/* 155 */     this.workerCount = this.config.getIoThreadCount();
/* 156 */     this.dispatchers = new BaseIOReactor[this.workerCount];
/* 157 */     this.workers = new Worker[this.workerCount];
/* 158 */     this.threads = new Thread[this.workerCount];
/* 159 */     this.status = IOReactorStatus.INACTIVE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMultiworkerIOReactor() throws IOReactorException {
/* 170 */     this(null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   static IOReactorConfig convert(int workerCount, HttpParams params) {
/* 178 */     Args.notNull(params, "HTTP parameters");
/* 179 */     return IOReactorConfig.custom().setSelectInterval(params.getLongParameter("http.nio.select-interval", 1000L)).setShutdownGracePeriod(params.getLongParameter("http.nio.grace-period", 500L)).setInterestOpQueued(params.getBooleanParameter("http.nio.select-interval", false)).setIoThreadCount(workerCount).setSoTimeout(params.getIntParameter("http.socket.timeout", 0)).setConnectTimeout(params.getIntParameter("http.connection.timeout", 0)).setSoTimeout(params.getIntParameter("http.socket.timeout", 0)).setSoReuseAddress(params.getBooleanParameter("http.socket.reuseaddr", false)).setSoKeepAlive(params.getBooleanParameter("http.socket.keepalive", false)).setSoLinger(params.getIntParameter("http.socket.linger", -1)).setTcpNoDelay(params.getBooleanParameter("http.tcp.nodelay", true)).build();
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
/*     */   @Deprecated
/*     */   public AbstractMultiworkerIOReactor(int workerCount, ThreadFactory threadFactory, HttpParams params) throws IOReactorException {
/* 210 */     this(convert(workerCount, params), threadFactory);
/*     */   }
/*     */ 
/*     */   
/*     */   public IOReactorStatus getStatus() {
/* 215 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ExceptionEvent> getAuditLog() {
/* 225 */     synchronized (this.auditLog) {
/* 226 */       return new ArrayList<ExceptionEvent>(this.auditLog);
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
/*     */   protected synchronized void addExceptionEvent(Throwable ex, Date timestamp) {
/* 239 */     if (ex == null) {
/*     */       return;
/*     */     }
/* 242 */     synchronized (this.auditLog) {
/* 243 */       this.auditLog.add(new ExceptionEvent(ex, (timestamp != null) ? timestamp : new Date()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addExceptionEvent(Throwable ex) {
/* 253 */     addExceptionEvent(ex, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExceptionHandler(IOReactorExceptionHandler exceptionHandler) {
/* 262 */     this.exceptionHandler = exceptionHandler;
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
/*     */   protected abstract void processEvents(int paramInt) throws IOReactorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void cancelRequests() throws IOReactorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(IOEventDispatch eventDispatch) throws InterruptedIOException, IOReactorException {
/* 309 */     Args.notNull(eventDispatch, "Event dispatcher");
/* 310 */     synchronized (this.statusLock) {
/* 311 */       if (this.status.compareTo((Enum)IOReactorStatus.SHUTDOWN_REQUEST) >= 0) {
/* 312 */         this.status = IOReactorStatus.SHUT_DOWN;
/* 313 */         this.statusLock.notifyAll();
/*     */         return;
/*     */       } 
/* 316 */       Asserts.check((this.status.compareTo((Enum)IOReactorStatus.INACTIVE) == 0), "Illegal state %s", this.status);
/*     */       
/* 318 */       this.status = IOReactorStatus.ACTIVE;
/*     */       int i;
/* 320 */       for (i = 0; i < this.dispatchers.length; i++) {
/* 321 */         BaseIOReactor dispatcher = new BaseIOReactor(this.selectTimeout, this.interestOpsQueueing);
/* 322 */         dispatcher.setExceptionHandler(this.exceptionHandler);
/* 323 */         this.dispatchers[i] = dispatcher;
/*     */       } 
/* 325 */       for (i = 0; i < this.workerCount; i++) {
/* 326 */         BaseIOReactor dispatcher = this.dispatchers[i];
/* 327 */         this.workers[i] = new Worker(dispatcher, eventDispatch);
/* 328 */         this.threads[i] = this.threadFactory.newThread(this.workers[i]);
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 333 */       for (int i = 0; i < this.workerCount; i++) {
/* 334 */         if (this.status != IOReactorStatus.ACTIVE) {
/*     */           return;
/*     */         }
/* 337 */         this.threads[i].start();
/*     */       } 
/*     */       
/*     */       do {
/*     */         int readyCount;
/*     */         try {
/* 343 */           readyCount = this.selector.select(this.selectTimeout);
/* 344 */         } catch (InterruptedIOException ex) {
/* 345 */           throw ex;
/* 346 */         } catch (IOException ex) {
/* 347 */           throw new IOReactorException("Unexpected selector failure", ex);
/*     */         } 
/*     */         
/* 350 */         if (this.status.compareTo((Enum)IOReactorStatus.ACTIVE) == 0) {
/* 351 */           processEvents(readyCount);
/*     */         }
/*     */ 
/*     */         
/* 355 */         for (int j = 0; j < this.workerCount; j++) {
/* 356 */           Worker worker = this.workers[j];
/* 357 */           Throwable ex = worker.getThrowable();
/* 358 */           if (ex != null) {
/* 359 */             throw new IOReactorException("I/O dispatch worker terminated abnormally", ex);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 364 */       while (this.status.compareTo((Enum)IOReactorStatus.ACTIVE) <= 0);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 369 */     catch (ClosedSelectorException ex) {
/* 370 */       addExceptionEvent(ex);
/* 371 */     } catch (IOReactorException ex) {
/* 372 */       if (ex.getCause() != null) {
/* 373 */         addExceptionEvent(ex.getCause());
/*     */       }
/* 375 */       throw ex;
/*     */     } finally {
/* 377 */       doShutdown();
/* 378 */       synchronized (this.statusLock) {
/* 379 */         this.status = IOReactorStatus.SHUT_DOWN;
/* 380 */         this.statusLock.notifyAll();
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
/*     */   protected void doShutdown() throws InterruptedIOException {
/* 396 */     synchronized (this.statusLock) {
/* 397 */       if (this.status.compareTo((Enum)IOReactorStatus.SHUTTING_DOWN) >= 0) {
/*     */         return;
/*     */       }
/* 400 */       this.status = IOReactorStatus.SHUTTING_DOWN;
/*     */     } 
/*     */     try {
/* 403 */       cancelRequests();
/* 404 */     } catch (IOReactorException ex) {
/* 405 */       if (ex.getCause() != null) {
/* 406 */         addExceptionEvent(ex.getCause());
/*     */       }
/*     */     } 
/* 409 */     this.selector.wakeup();
/*     */ 
/*     */     
/* 412 */     if (this.selector.isOpen()) {
/* 413 */       for (SelectionKey key : this.selector.keys()) {
/*     */         try {
/* 415 */           Channel channel = key.channel();
/* 416 */           if (channel != null) {
/* 417 */             channel.close();
/*     */           }
/* 419 */         } catch (IOException ex) {
/* 420 */           addExceptionEvent(ex);
/*     */         } 
/*     */       } 
/*     */       
/*     */       try {
/* 425 */         this.selector.close();
/* 426 */       } catch (IOException ex) {
/* 427 */         addExceptionEvent(ex);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 432 */     for (int i = 0; i < this.workerCount; i++) {
/* 433 */       BaseIOReactor dispatcher = this.dispatchers[i];
/* 434 */       dispatcher.gracefulShutdown();
/*     */     } 
/*     */     
/* 437 */     long gracePeriod = this.config.getShutdownGracePeriod();
/*     */     
/*     */     try {
/*     */       int j;
/*     */       
/* 442 */       for (j = 0; j < this.workerCount; j++) {
/* 443 */         BaseIOReactor dispatcher = this.dispatchers[j];
/* 444 */         if (dispatcher.getStatus() != IOReactorStatus.INACTIVE) {
/* 445 */           dispatcher.awaitShutdown(gracePeriod);
/*     */         }
/* 447 */         if (dispatcher.getStatus() != IOReactorStatus.SHUT_DOWN) {
/*     */           try {
/* 449 */             dispatcher.hardShutdown();
/* 450 */           } catch (IOReactorException ex) {
/* 451 */             if (ex.getCause() != null) {
/* 452 */               addExceptionEvent(ex.getCause());
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 458 */       for (j = 0; j < this.workerCount; j++) {
/* 459 */         Thread t = this.threads[j];
/* 460 */         if (t != null) {
/* 461 */           t.join(gracePeriod);
/*     */         }
/*     */       } 
/* 464 */     } catch (InterruptedException ex) {
/* 465 */       throw new InterruptedIOException(ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addChannel(ChannelEntry entry) {
/* 476 */     int i = Math.abs(this.currentWorker++ % this.workerCount);
/* 477 */     this.dispatchers[i].addChannel(entry);
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
/*     */   protected SelectionKey registerChannel(SelectableChannel channel, int ops) throws ClosedChannelException {
/* 490 */     return channel.register(this.selector, ops);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareSocket(Socket socket) throws IOException {
/* 500 */     socket.setTcpNoDelay(this.config.isTcpNoDelay());
/* 501 */     socket.setKeepAlive(this.config.isSoKeepalive());
/* 502 */     if (this.config.getSoTimeout() > 0) {
/* 503 */       socket.setSoTimeout(this.config.getSoTimeout());
/*     */     }
/* 505 */     if (this.config.getSndBufSize() > 0) {
/* 506 */       socket.setSendBufferSize(this.config.getSndBufSize());
/*     */     }
/* 508 */     if (this.config.getRcvBufSize() > 0) {
/* 509 */       socket.setReceiveBufferSize(this.config.getRcvBufSize());
/*     */     }
/* 511 */     int linger = this.config.getSoLinger();
/* 512 */     if (linger >= 0) {
/* 513 */       socket.setSoLinger(true, linger);
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
/*     */   protected void awaitShutdown(long timeout) throws InterruptedException {
/* 527 */     synchronized (this.statusLock) {
/* 528 */       long deadline = System.currentTimeMillis() + timeout;
/* 529 */       long remaining = timeout;
/* 530 */       while (this.status != IOReactorStatus.SHUT_DOWN) {
/* 531 */         this.statusLock.wait(remaining);
/* 532 */         if (timeout > 0L) {
/* 533 */           remaining = deadline - System.currentTimeMillis();
/* 534 */           if (remaining <= 0L) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() throws IOException {
/* 544 */     shutdown(2000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown(long waitMs) throws IOException {
/* 549 */     synchronized (this.statusLock) {
/* 550 */       if (this.status.compareTo((Enum)IOReactorStatus.ACTIVE) > 0) {
/*     */         return;
/*     */       }
/* 553 */       if (this.status.compareTo((Enum)IOReactorStatus.INACTIVE) == 0) {
/* 554 */         this.status = IOReactorStatus.SHUT_DOWN;
/* 555 */         cancelRequests();
/* 556 */         this.selector.close();
/*     */         return;
/*     */       } 
/* 559 */       this.status = IOReactorStatus.SHUTDOWN_REQUEST;
/*     */     } 
/* 561 */     this.selector.wakeup();
/*     */     try {
/* 563 */       awaitShutdown(waitMs);
/* 564 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   static void closeChannel(Channel channel) {
/*     */     try {
/* 570 */       channel.close();
/* 571 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   static class Worker
/*     */     implements Runnable
/*     */   {
/*     */     final BaseIOReactor dispatcher;
/*     */     
/*     */     final IOEventDispatch eventDispatch;
/*     */     private volatile Throwable exception;
/*     */     
/*     */     public Worker(BaseIOReactor dispatcher, IOEventDispatch eventDispatch) {
/* 584 */       this.dispatcher = dispatcher;
/* 585 */       this.eventDispatch = eventDispatch;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 591 */         this.dispatcher.execute(this.eventDispatch);
/* 592 */       } catch (Error ex) {
/* 593 */         this.exception = ex;
/* 594 */         throw ex;
/* 595 */       } catch (Exception ex) {
/* 596 */         this.exception = ex;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Throwable getThrowable() {
/* 601 */       return this.exception;
/*     */     }
/*     */   }
/*     */   
/*     */   static class DefaultThreadFactory
/*     */     implements ThreadFactory
/*     */   {
/* 608 */     private static final AtomicLong COUNT = new AtomicLong(1L);
/*     */ 
/*     */     
/*     */     public Thread newThread(Runnable r) {
/* 612 */       return new Thread(r, "I/O dispatcher " + COUNT.getAndIncrement());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/AbstractMultiworkerIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */