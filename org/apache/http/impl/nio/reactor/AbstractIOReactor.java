/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.ClosedChannelException;
/*     */ import java.nio.channels.ClosedSelectorException;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.Selector;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import org.apache.http.nio.reactor.IOReactor;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorStatus;
/*     */ import org.apache.http.nio.reactor.IOSession;
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
/*     */ public abstract class AbstractIOReactor
/*     */   implements IOReactor
/*     */ {
/*     */   private volatile IOReactorStatus status;
/*     */   private final Object statusMutex;
/*     */   private final long selectTimeout;
/*     */   private final boolean interestOpsQueueing;
/*     */   private final Selector selector;
/*     */   private final Set<IOSession> sessions;
/*     */   private final Queue<InterestOpEntry> interestOpsQueue;
/*     */   private final Queue<IOSession> closedSessions;
/*     */   private final Queue<ChannelEntry> newChannels;
/*     */   
/*     */   public AbstractIOReactor(long selectTimeout) throws IOReactorException {
/*  78 */     this(selectTimeout, false);
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
/*     */   public AbstractIOReactor(long selectTimeout, boolean interestOpsQueueing) throws IOReactorException {
/*  93 */     Args.positive(selectTimeout, "Select timeout");
/*  94 */     this.selectTimeout = selectTimeout;
/*  95 */     this.interestOpsQueueing = interestOpsQueueing;
/*  96 */     this.sessions = Collections.synchronizedSet(new HashSet<IOSession>());
/*  97 */     this.interestOpsQueue = new ConcurrentLinkedQueue<InterestOpEntry>();
/*  98 */     this.closedSessions = new ConcurrentLinkedQueue<IOSession>();
/*  99 */     this.newChannels = new ConcurrentLinkedQueue<ChannelEntry>();
/*     */     try {
/* 101 */       this.selector = Selector.open();
/* 102 */     } catch (IOException ex) {
/* 103 */       throw new IOReactorException("Failure opening selector", ex);
/*     */     } 
/* 105 */     this.statusMutex = new Object();
/* 106 */     this.status = IOReactorStatus.INACTIVE;
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
/*     */   protected void sessionCreated(SelectionKey key, IOSession session) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sessionClosed(IOSession session) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sessionTimedOut(IOSession session) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IOSession getSession(SelectionKey key) {
/* 196 */     return (IOSession)key.attachment();
/*     */   }
/*     */ 
/*     */   
/*     */   public IOReactorStatus getStatus() {
/* 201 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInterestOpsQueueing() {
/* 210 */     return this.interestOpsQueueing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChannel(ChannelEntry channelEntry) {
/* 220 */     Args.notNull(channelEntry, "Channel entry");
/* 221 */     this.newChannels.add(channelEntry);
/* 222 */     this.selector.wakeup();
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
/*     */   protected void execute() throws InterruptedIOException, IOReactorException {
/* 248 */     this.status = IOReactorStatus.ACTIVE;
/*     */ 
/*     */     
/*     */     try { while (true) {
/*     */         int readyCount;
/*     */         
/*     */         try {
/* 255 */           readyCount = this.selector.select(this.selectTimeout);
/* 256 */         } catch (InterruptedIOException ex) {
/* 257 */           throw ex;
/* 258 */         } catch (IOException ex) {
/* 259 */           throw new IOReactorException("Unexpected selector failure", ex);
/*     */         } 
/*     */         
/* 262 */         if (this.status == IOReactorStatus.SHUT_DOWN) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 267 */         if (this.status == IOReactorStatus.SHUTTING_DOWN) {
/*     */ 
/*     */           
/* 270 */           closeSessions();
/* 271 */           closeNewChannels();
/*     */         } 
/*     */ 
/*     */         
/* 275 */         if (readyCount > 0) {
/* 276 */           processEvents(this.selector.selectedKeys());
/*     */         }
/*     */ 
/*     */         
/* 280 */         validate(this.selector.keys());
/*     */ 
/*     */         
/* 283 */         processClosedSessions();
/*     */ 
/*     */         
/* 286 */         if (this.status == IOReactorStatus.ACTIVE) {
/* 287 */           processNewChannels();
/*     */         }
/*     */ 
/*     */         
/* 291 */         if (this.status.compareTo((Enum)IOReactorStatus.ACTIVE) > 0 && this.sessions.isEmpty()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 296 */         if (this.interestOpsQueueing)
/*     */         {
/* 298 */           processPendingInterestOps();
/*     */         }
/*     */       }
/*     */        }
/*     */     
/* 303 */     catch (ClosedSelectorException closedSelectorException) {  }
/*     */     finally
/* 305 */     { hardShutdown();
/* 306 */       synchronized (this.statusMutex) {
/* 307 */         this.statusMutex.notifyAll();
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   private void processEvents(Set<SelectionKey> selectedKeys) {
/* 313 */     for (SelectionKey key : selectedKeys)
/*     */     {
/* 315 */       processEvent(key);
/*     */     }
/*     */     
/* 318 */     selectedKeys.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processEvent(SelectionKey key) {
/* 327 */     IOSessionImpl session = (IOSessionImpl)key.attachment();
/*     */     try {
/* 329 */       if (key.isAcceptable()) {
/* 330 */         acceptable(key);
/*     */       }
/* 332 */       if (key.isConnectable()) {
/* 333 */         connectable(key);
/*     */       }
/* 335 */       if (key.isReadable()) {
/* 336 */         session.resetLastRead();
/* 337 */         readable(key);
/*     */       } 
/* 339 */       if (key.isWritable()) {
/* 340 */         session.resetLastWrite();
/* 341 */         writable(key);
/*     */       } 
/* 343 */     } catch (CancelledKeyException ex) {
/* 344 */       queueClosedSession(session);
/* 345 */       key.attach(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void queueClosedSession(IOSession session) {
/* 355 */     if (session != null) {
/* 356 */       this.closedSessions.add(session);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processNewChannels() throws IOReactorException {
/*     */     ChannelEntry entry;
/* 362 */     while ((entry = this.newChannels.poll()) != null) {
/*     */       SocketChannel channel;
/*     */       SelectionKey key;
/*     */       IOSession session;
/*     */       try {
/* 367 */         channel = entry.getChannel();
/* 368 */         channel.configureBlocking(false);
/* 369 */         key = channel.register(this.selector, 1);
/* 370 */       } catch (ClosedChannelException ex) {
/* 371 */         SessionRequestImpl sessionRequest = entry.getSessionRequest();
/* 372 */         if (sessionRequest != null) {
/* 373 */           sessionRequest.failed(ex);
/*     */         }
/*     */         
/*     */         return;
/* 377 */       } catch (IOException ex) {
/* 378 */         throw new IOReactorException("Failure registering channel with the selector", ex);
/*     */       } 
/*     */ 
/*     */       
/* 382 */       SessionClosedCallback sessionClosedCallback = new SessionClosedCallback()
/*     */         {
/*     */           public void sessionClosed(IOSession session)
/*     */           {
/* 386 */             AbstractIOReactor.this.queueClosedSession(session);
/*     */           }
/*     */         };
/*     */ 
/*     */       
/* 391 */       InterestOpsCallback interestOpsCallback = null;
/* 392 */       if (this.interestOpsQueueing) {
/* 393 */         interestOpsCallback = new InterestOpsCallback()
/*     */           {
/*     */             public void addInterestOps(InterestOpEntry entry)
/*     */             {
/* 397 */               AbstractIOReactor.this.queueInterestOps(entry);
/*     */             }
/*     */           };
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 405 */         session = new IOSessionImpl(key, interestOpsCallback, sessionClosedCallback);
/* 406 */         int timeout = 0;
/*     */         try {
/* 408 */           timeout = channel.socket().getSoTimeout();
/* 409 */         } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 415 */         session.setAttribute("http.session.attachment", entry.getAttachment());
/* 416 */         session.setSocketTimeout(timeout);
/* 417 */       } catch (CancelledKeyException ex) {
/*     */         continue;
/*     */       } 
/*     */       try {
/* 421 */         this.sessions.add(session);
/* 422 */         SessionRequestImpl sessionRequest = entry.getSessionRequest();
/* 423 */         if (sessionRequest != null) {
/* 424 */           sessionRequest.completed(session);
/*     */         }
/* 426 */         key.attach(session);
/* 427 */         sessionCreated(key, session);
/* 428 */       } catch (CancelledKeyException ex) {
/* 429 */         queueClosedSession(session);
/* 430 */         key.attach(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processClosedSessions() {
/*     */     IOSession session;
/* 437 */     while ((session = this.closedSessions.poll()) != null) {
/* 438 */       if (this.sessions.remove(session)) {
/*     */         try {
/* 440 */           sessionClosed(session);
/* 441 */         } catch (CancelledKeyException cancelledKeyException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processPendingInterestOps() {
/* 450 */     if (!this.interestOpsQueueing) {
/*     */       return;
/*     */     }
/*     */     InterestOpEntry entry;
/* 454 */     while ((entry = this.interestOpsQueue.poll()) != null) {
/*     */       
/* 456 */       SelectionKey key = entry.getSelectionKey();
/* 457 */       int eventMask = entry.getEventMask();
/* 458 */       if (key.isValid()) {
/* 459 */         key.interestOps(eventMask);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean queueInterestOps(InterestOpEntry entry) {
/* 466 */     Asserts.check(this.interestOpsQueueing, "Interest ops queueing not enabled");
/* 467 */     if (entry == null) {
/* 468 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 472 */     this.interestOpsQueue.add(entry);
/*     */     
/* 474 */     return true;
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
/*     */   protected void timeoutCheck(SelectionKey key, long now) {
/* 487 */     IOSessionImpl session = (IOSessionImpl)key.attachment();
/* 488 */     if (session != null) {
/* 489 */       int timeout = session.getSocketTimeout();
/* 490 */       if (timeout > 0 && 
/* 491 */         session.getLastAccessTime() + timeout < now) {
/* 492 */         sessionTimedOut(session);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeSessions() {
/* 502 */     synchronized (this.sessions) {
/* 503 */       for (IOSession session : this.sessions) {
/* 504 */         session.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeNewChannels() throws IOReactorException {
/*     */     ChannelEntry entry;
/* 516 */     while ((entry = this.newChannels.poll()) != null) {
/* 517 */       SessionRequestImpl sessionRequest = entry.getSessionRequest();
/* 518 */       if (sessionRequest != null) {
/* 519 */         sessionRequest.cancel();
/*     */       }
/* 521 */       SocketChannel channel = entry.getChannel();
/*     */       try {
/* 523 */         channel.close();
/* 524 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeActiveChannels() throws IOReactorException {
/*     */     try {
/* 536 */       Set<SelectionKey> keys = this.selector.keys();
/* 537 */       for (SelectionKey key : keys) {
/* 538 */         IOSession session = getSession(key);
/* 539 */         if (session != null) {
/* 540 */           session.close();
/*     */         }
/*     */       } 
/* 543 */       this.selector.close();
/* 544 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void gracefulShutdown() {
/* 552 */     synchronized (this.statusMutex) {
/* 553 */       if (this.status != IOReactorStatus.ACTIVE) {
/*     */         return;
/*     */       }
/*     */       
/* 557 */       this.status = IOReactorStatus.SHUTTING_DOWN;
/*     */     } 
/* 559 */     this.selector.wakeup();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hardShutdown() throws IOReactorException {
/* 566 */     synchronized (this.statusMutex) {
/* 567 */       if (this.status == IOReactorStatus.SHUT_DOWN) {
/*     */         return;
/*     */       }
/*     */       
/* 571 */       this.status = IOReactorStatus.SHUT_DOWN;
/*     */     } 
/*     */     
/* 574 */     closeNewChannels();
/* 575 */     closeActiveChannels();
/* 576 */     processClosedSessions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void awaitShutdown(long timeout) throws InterruptedException {
/* 587 */     synchronized (this.statusMutex) {
/* 588 */       long deadline = System.currentTimeMillis() + timeout;
/* 589 */       long remaining = timeout;
/* 590 */       while (this.status != IOReactorStatus.SHUT_DOWN) {
/* 591 */         this.statusMutex.wait(remaining);
/* 592 */         if (timeout > 0L) {
/* 593 */           remaining = deadline - System.currentTimeMillis();
/* 594 */           if (remaining <= 0L) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown(long gracePeriod) throws IOReactorException {
/* 604 */     if (this.status != IOReactorStatus.INACTIVE) {
/* 605 */       gracefulShutdown();
/*     */       try {
/* 607 */         awaitShutdown(gracePeriod);
/* 608 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */     
/* 611 */     if (this.status != IOReactorStatus.SHUT_DOWN) {
/* 612 */       hardShutdown();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() throws IOReactorException {
/* 618 */     shutdown(1000L);
/*     */   }
/*     */   
/*     */   protected abstract void acceptable(SelectionKey paramSelectionKey);
/*     */   
/*     */   protected abstract void connectable(SelectionKey paramSelectionKey);
/*     */   
/*     */   protected abstract void readable(SelectionKey paramSelectionKey);
/*     */   
/*     */   protected abstract void writable(SelectionKey paramSelectionKey);
/*     */   
/*     */   protected abstract void validate(Set<SelectionKey> paramSet);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/AbstractIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */