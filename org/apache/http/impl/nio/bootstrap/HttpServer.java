/*     */ package org.apache.http.impl.nio.bootstrap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import org.apache.http.ExceptionLogger;
/*     */ import org.apache.http.impl.nio.DefaultHttpServerIODispatch;
/*     */ import org.apache.http.impl.nio.DefaultNHttpServerConnection;
/*     */ import org.apache.http.impl.nio.reactor.DefaultListeningIOReactor;
/*     */ import org.apache.http.impl.nio.reactor.IOReactorConfig;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
/*     */ import org.apache.http.nio.NHttpServerEventHandler;
/*     */ import org.apache.http.nio.reactor.IOEventDispatch;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorExceptionHandler;
/*     */ import org.apache.http.nio.reactor.ListenerEndpoint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpServer
/*     */ {
/*     */   private final int port;
/*     */   private final InetAddress ifAddress;
/*     */   private final IOReactorConfig ioReactorConfig;
/*     */   private final NHttpServerEventHandler serverEventHandler;
/*     */   private final NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactory;
/*     */   private final ExceptionLogger exceptionLogger;
/*     */   private final ExecutorService listenerExecutorService;
/*     */   private final ThreadGroup dispatchThreads;
/*     */   private final AtomicReference<Status> status;
/*     */   private final DefaultListeningIOReactor ioReactor;
/*     */   private volatile ListenerEndpoint endpoint;
/*     */   
/*     */   enum Status
/*     */   {
/*  54 */     READY, ACTIVE, STOPPING;
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
/*     */   HttpServer(int port, InetAddress ifAddress, IOReactorConfig ioReactorConfig, NHttpServerEventHandler serverEventHandler, NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactory, final ExceptionLogger exceptionLogger) {
/*  76 */     this.port = port;
/*  77 */     this.ifAddress = ifAddress;
/*  78 */     this.ioReactorConfig = ioReactorConfig;
/*  79 */     this.serverEventHandler = serverEventHandler;
/*  80 */     this.connectionFactory = connectionFactory;
/*  81 */     this.exceptionLogger = exceptionLogger;
/*  82 */     this.listenerExecutorService = Executors.newSingleThreadExecutor(new ThreadFactoryImpl("HTTP-listener-" + this.port));
/*     */     
/*  84 */     this.dispatchThreads = new ThreadGroup("I/O-dispatchers");
/*     */     try {
/*  86 */       this.ioReactor = new DefaultListeningIOReactor(this.ioReactorConfig, new ThreadFactoryImpl("I/O-dispatch", this.dispatchThreads));
/*     */     
/*     */     }
/*  89 */     catch (IOReactorException ex) {
/*  90 */       throw new IllegalStateException(ex);
/*     */     } 
/*  92 */     this.ioReactor.setExceptionHandler(new IOReactorExceptionHandler()
/*     */         {
/*     */           public boolean handle(IOException ex) {
/*  95 */             exceptionLogger.log(ex);
/*  96 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean handle(RuntimeException ex) {
/* 101 */             exceptionLogger.log(ex);
/* 102 */             return false;
/*     */           }
/*     */         });
/* 105 */     this.status = new AtomicReference<Status>(Status.READY);
/*     */   }
/*     */   
/*     */   public ListenerEndpoint getEndpoint() {
/* 109 */     return this.endpoint;
/*     */   }
/*     */   
/*     */   public void start() throws IOException {
/* 113 */     if (this.status.compareAndSet(Status.READY, Status.ACTIVE)) {
/* 114 */       this.endpoint = this.ioReactor.listen(new InetSocketAddress(this.ifAddress, (this.port > 0) ? this.port : 0));
/* 115 */       final DefaultHttpServerIODispatch ioEventDispatch = new DefaultHttpServerIODispatch(this.serverEventHandler, this.connectionFactory);
/*     */       
/* 117 */       this.listenerExecutorService.execute(new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/*     */               try {
/* 122 */                 HttpServer.this.ioReactor.execute(ioEventDispatch);
/* 123 */               } catch (Exception ex) {
/* 124 */                 HttpServer.this.exceptionLogger.log(ex);
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
/* 133 */     this.listenerExecutorService.awaitTermination(timeout, timeUnit);
/*     */   }
/*     */   
/*     */   public void shutdown(long gracePeriod, TimeUnit timeUnit) {
/* 137 */     if (this.status.compareAndSet(Status.ACTIVE, Status.STOPPING)) {
/*     */       try {
/* 139 */         this.ioReactor.shutdown(timeUnit.toMillis(gracePeriod));
/* 140 */       } catch (IOException ex) {
/* 141 */         this.exceptionLogger.log(ex);
/*     */       } 
/* 143 */       this.listenerExecutorService.shutdown();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/bootstrap/HttpServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */