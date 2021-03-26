/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.InterruptedIOException;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.http.nio.reactor.IOEventDispatch;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorExceptionHandler;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.util.Args;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseIOReactor
/*     */   extends AbstractIOReactor
/*     */ {
/*     */   private final long timeoutCheckInterval;
/*     */   private final Set<IOSession> bufferingSessions;
/*     */   private long lastTimeoutCheck;
/*  60 */   private IOReactorExceptionHandler exceptionHandler = null;
/*  61 */   private IOEventDispatch eventDispatch = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseIOReactor(long selectTimeout) throws IOReactorException {
/*  70 */     this(selectTimeout, false);
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
/*     */   public BaseIOReactor(long selectTimeout, boolean interestOpsQueueing) throws IOReactorException {
/*  85 */     super(selectTimeout, interestOpsQueueing);
/*  86 */     this.bufferingSessions = new HashSet<IOSession>();
/*  87 */     this.timeoutCheckInterval = selectTimeout;
/*  88 */     this.lastTimeoutCheck = System.currentTimeMillis();
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
/*     */   public void execute(IOEventDispatch eventDispatch) throws InterruptedIOException, IOReactorException {
/* 102 */     Args.notNull(eventDispatch, "Event dispatcher");
/* 103 */     this.eventDispatch = eventDispatch;
/* 104 */     execute();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExceptionHandler(IOReactorExceptionHandler exceptionHandler) {
/* 113 */     this.exceptionHandler = exceptionHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleRuntimeException(RuntimeException ex) {
/* 124 */     if (this.exceptionHandler == null || !this.exceptionHandler.handle(ex)) {
/* 125 */       throw ex;
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
/*     */   protected void acceptable(SelectionKey key) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void connectable(SelectionKey key) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readable(SelectionKey key) {
/* 156 */     IOSession session = getSession(key);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 161 */       for (int i = 0; i < 5; i++) {
/* 162 */         this.eventDispatch.inputReady(session);
/* 163 */         if (!session.hasBufferedInput() || (session.getEventMask() & 0x1) == 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 168 */       if (session.hasBufferedInput()) {
/* 169 */         this.bufferingSessions.add(session);
/*     */       }
/* 171 */     } catch (CancelledKeyException ex) {
/* 172 */       queueClosedSession(session);
/* 173 */       key.attach(null);
/* 174 */     } catch (RuntimeException ex) {
/* 175 */       handleRuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writable(SelectionKey key) {
/* 186 */     IOSession session = getSession(key);
/*     */     try {
/* 188 */       this.eventDispatch.outputReady(session);
/* 189 */     } catch (CancelledKeyException ex) {
/* 190 */       queueClosedSession(session);
/* 191 */       key.attach(null);
/* 192 */     } catch (RuntimeException ex) {
/* 193 */       handleRuntimeException(ex);
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
/*     */   protected void validate(Set<SelectionKey> keys) {
/* 208 */     long currentTime = System.currentTimeMillis();
/* 209 */     if (currentTime - this.lastTimeoutCheck >= this.timeoutCheckInterval) {
/* 210 */       this.lastTimeoutCheck = currentTime;
/* 211 */       if (keys != null) {
/* 212 */         for (SelectionKey key : keys) {
/* 213 */           timeoutCheck(key, currentTime);
/*     */         }
/*     */       }
/*     */     } 
/* 217 */     if (!this.bufferingSessions.isEmpty()) {
/* 218 */       for (Iterator<IOSession> it = this.bufferingSessions.iterator(); it.hasNext(); ) {
/* 219 */         IOSession session = it.next();
/* 220 */         if (!session.hasBufferedInput()) {
/* 221 */           it.remove();
/*     */           continue;
/*     */         } 
/*     */         try {
/* 225 */           if ((session.getEventMask() & 0x1) > 0) {
/* 226 */             this.eventDispatch.inputReady(session);
/* 227 */             if (!session.hasBufferedInput()) {
/* 228 */               it.remove();
/*     */             }
/*     */           } 
/* 231 */         } catch (CancelledKeyException ex) {
/* 232 */           it.remove();
/* 233 */           queueClosedSession(session);
/* 234 */         } catch (RuntimeException ex) {
/* 235 */           handleRuntimeException(ex);
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
/*     */   protected void sessionCreated(SelectionKey key, IOSession session) {
/*     */     try {
/* 248 */       this.eventDispatch.connected(session);
/* 249 */     } catch (CancelledKeyException ex) {
/* 250 */       queueClosedSession(session);
/* 251 */     } catch (RuntimeException ex) {
/* 252 */       handleRuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sessionTimedOut(IOSession session) {
/*     */     try {
/* 263 */       this.eventDispatch.timeout(session);
/* 264 */     } catch (CancelledKeyException ex) {
/* 265 */       queueClosedSession(session);
/* 266 */     } catch (RuntimeException ex) {
/* 267 */       handleRuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sessionClosed(IOSession session) {
/*     */     try {
/* 279 */       this.eventDispatch.disconnected(session);
/* 280 */     } catch (CancelledKeyException cancelledKeyException) {
/*     */     
/* 282 */     } catch (RuntimeException ex) {
/* 283 */       handleRuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/BaseIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */