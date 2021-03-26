/*     */ package org.apache.http.nio.reactor.ssl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.ByteChannel;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.ClosedChannelException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLEngine;
/*     */ import javax.net.ssl.SSLEngineResult;
/*     */ import javax.net.ssl.SSLException;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionBufferStatus;
/*     */ import org.apache.http.nio.reactor.SocketAccessor;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class SSLIOSession
/*     */   implements IOSession, SessionBufferStatus, SocketAccessor
/*     */ {
/*     */   public static final String SESSION_KEY = "http.session.ssl";
/*  83 */   private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
/*     */ 
/*     */   
/*     */   private final IOSession session;
/*     */ 
/*     */   
/*     */   private final SSLEngine sslEngine;
/*     */ 
/*     */   
/*     */   private final SSLBuffer inEncrypted;
/*     */ 
/*     */   
/*     */   private final SSLBuffer outEncrypted;
/*     */ 
/*     */   
/*     */   private final SSLBuffer inPlain;
/*     */ 
/*     */   
/*     */   private final InternalByteChannel channel;
/*     */   
/*     */   private final SSLSetupHandler handler;
/*     */   
/*     */   private int appEventMask;
/*     */   
/*     */   private SessionBufferStatus appBufferStatus;
/*     */   
/*     */   private boolean endOfStream;
/*     */   
/*     */   private volatile SSLMode sslMode;
/*     */   
/*     */   private volatile int status;
/*     */   
/*     */   private volatile boolean initialized;
/*     */ 
/*     */   
/*     */   public SSLIOSession(IOSession session, SSLMode sslMode, HttpHost host, SSLContext sslContext, SSLSetupHandler handler) {
/* 119 */     this(session, sslMode, host, sslContext, handler, new PermanentSSLBufferManagementStrategy());
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
/*     */   public SSLIOSession(IOSession session, SSLMode sslMode, HttpHost host, SSLContext sslContext, SSLSetupHandler handler, SSLBufferManagementStrategy bufferManagementStrategy) {
/* 140 */     Args.notNull(session, "IO session");
/* 141 */     Args.notNull(sslContext, "SSL context");
/* 142 */     Args.notNull(bufferManagementStrategy, "Buffer management strategy");
/* 143 */     this.session = session;
/* 144 */     this.sslMode = sslMode;
/* 145 */     this.appEventMask = session.getEventMask();
/* 146 */     this.channel = new InternalByteChannel();
/* 147 */     this.handler = handler;
/*     */ 
/*     */     
/* 150 */     this.session.setBufferStatus(this);
/*     */     
/* 152 */     if (this.sslMode == SSLMode.CLIENT && host != null) {
/* 153 */       this.sslEngine = sslContext.createSSLEngine(host.getHostName(), host.getPort());
/*     */     } else {
/* 155 */       this.sslEngine = sslContext.createSSLEngine();
/*     */     } 
/*     */ 
/*     */     
/* 159 */     int netBuffersize = this.sslEngine.getSession().getPacketBufferSize();
/* 160 */     this.inEncrypted = bufferManagementStrategy.constructBuffer(netBuffersize);
/* 161 */     this.outEncrypted = bufferManagementStrategy.constructBuffer(netBuffersize);
/*     */ 
/*     */     
/* 164 */     int appBuffersize = this.sslEngine.getSession().getApplicationBufferSize();
/* 165 */     this.inPlain = bufferManagementStrategy.constructBuffer(appBuffersize);
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
/*     */   public SSLIOSession(IOSession session, SSLMode sslMode, SSLContext sslContext, SSLSetupHandler handler) {
/* 181 */     this(session, sslMode, null, sslContext, handler);
/*     */   }
/*     */   
/*     */   protected SSLSetupHandler getSSLSetupHandler() {
/* 185 */     return this.handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 193 */     return this.initialized;
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
/*     */   @Deprecated
/*     */   public synchronized void initialize(SSLMode sslMode) throws SSLException {
/* 206 */     this.sslMode = sslMode;
/* 207 */     initialize();
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
/*     */   public synchronized void initialize() throws SSLException {
/* 219 */     Asserts.check(!this.initialized, "SSL I/O session already initialized");
/* 220 */     if (this.status >= 1) {
/*     */       return;
/*     */     }
/* 223 */     switch (this.sslMode) {
/*     */       case NEED_WRAP:
/* 225 */         this.sslEngine.setUseClientMode(true);
/*     */         break;
/*     */       case NEED_UNWRAP:
/* 228 */         this.sslEngine.setUseClientMode(false);
/*     */         break;
/*     */     } 
/* 231 */     if (this.handler != null) {
/* 232 */       this.handler.initalize(this.sslEngine);
/*     */     }
/* 234 */     this.initialized = true;
/* 235 */     this.sslEngine.beginHandshake();
/*     */     
/* 237 */     this.inEncrypted.release();
/* 238 */     this.outEncrypted.release();
/* 239 */     this.inPlain.release();
/*     */     
/* 241 */     doHandshake();
/*     */   }
/*     */   
/*     */   public synchronized SSLSession getSSLSession() {
/* 245 */     return this.sslEngine.getSession();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SSLException convert(RuntimeException ex) {
/* 254 */     Throwable cause = ex.getCause();
/* 255 */     if (cause == null) {
/* 256 */       cause = ex;
/*     */     }
/* 258 */     return new SSLException(cause);
/*     */   }
/*     */   
/*     */   private SSLEngineResult doWrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
/*     */     try {
/* 263 */       return this.sslEngine.wrap(src, dst);
/* 264 */     } catch (RuntimeException ex) {
/* 265 */       throw convert(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private SSLEngineResult doUnwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
/*     */     try {
/* 271 */       return this.sslEngine.unwrap(src, dst);
/* 272 */     } catch (RuntimeException ex) {
/* 273 */       throw convert(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doRunTask() throws SSLException {
/*     */     try {
/* 279 */       Runnable r = this.sslEngine.getDelegatedTask();
/* 280 */       if (r != null) {
/* 281 */         r.run();
/*     */       }
/* 283 */     } catch (RuntimeException ex) {
/* 284 */       throw convert(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doHandshake() throws SSLException {
/* 289 */     boolean handshaking = true;
/*     */     
/* 291 */     SSLEngineResult result = null;
/* 292 */     while (handshaking) {
/* 293 */       ByteBuffer outEncryptedBuf; ByteBuffer inEncryptedBuf; ByteBuffer inPlainBuf; switch (this.sslEngine.getHandshakeStatus()) {
/*     */ 
/*     */ 
/*     */         
/*     */         case NEED_WRAP:
/* 298 */           outEncryptedBuf = this.outEncrypted.acquire();
/*     */ 
/*     */           
/* 301 */           result = doWrap(ByteBuffer.allocate(0), outEncryptedBuf);
/*     */           
/* 303 */           if (result.getStatus() != SSLEngineResult.Status.OK || result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
/* 304 */             handshaking = false;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case NEED_UNWRAP:
/* 311 */           inEncryptedBuf = this.inEncrypted.acquire();
/* 312 */           inPlainBuf = this.inPlain.acquire();
/*     */ 
/*     */           
/* 315 */           inEncryptedBuf.flip();
/* 316 */           result = doUnwrap(inEncryptedBuf, inPlainBuf);
/* 317 */           inEncryptedBuf.compact();
/*     */ 
/*     */           
/*     */           try {
/* 321 */             if (!inEncryptedBuf.hasRemaining() && result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
/* 322 */               throw new SSLException("Input buffer is full");
/*     */             }
/*     */           } finally {
/*     */             
/* 326 */             if (inEncryptedBuf.position() == 0) {
/* 327 */               this.inEncrypted.release();
/*     */             }
/*     */           } 
/*     */           
/* 331 */           if (this.status >= 1) {
/* 332 */             this.inPlain.release();
/*     */           }
/* 334 */           if (result.getStatus() != SSLEngineResult.Status.OK) {
/* 335 */             handshaking = false;
/*     */           }
/*     */         
/*     */         case NEED_TASK:
/* 339 */           doRunTask();
/*     */         
/*     */         case NOT_HANDSHAKING:
/* 342 */           handshaking = false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 352 */     if (result != null && result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED && 
/* 353 */       this.handler != null) {
/* 354 */       this.handler.verify(this.session, this.sslEngine.getSession());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateEventMask() {
/* 361 */     if (this.status == 0 && (this.endOfStream || this.sslEngine.isInboundDone()))
/*     */     {
/* 363 */       this.status = 1;
/*     */     }
/* 365 */     if (this.status == 1 && !this.outEncrypted.hasData()) {
/* 366 */       this.sslEngine.closeOutbound();
/*     */     }
/* 368 */     if (this.status == 1 && this.sslEngine.isOutboundDone() && (this.endOfStream || this.sslEngine.isInboundDone()) && !this.inPlain.hasData() && this.appBufferStatus != null && !this.appBufferStatus.hasBufferedInput())
/*     */     {
/*     */ 
/*     */       
/* 372 */       this.status = Integer.MAX_VALUE;
/*     */     }
/*     */     
/* 375 */     if (this.status <= 1 && this.endOfStream && this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP)
/*     */     {
/* 377 */       this.status = Integer.MAX_VALUE;
/*     */     }
/* 379 */     if (this.status == Integer.MAX_VALUE) {
/* 380 */       this.session.close();
/*     */       
/*     */       return;
/*     */     } 
/* 384 */     int oldMask = this.session.getEventMask();
/* 385 */     int newMask = oldMask;
/* 386 */     switch (this.sslEngine.getHandshakeStatus()) {
/*     */       case NEED_WRAP:
/* 388 */         newMask = 5;
/*     */         break;
/*     */       case NEED_UNWRAP:
/* 391 */         newMask = 1;
/*     */         break;
/*     */       case NOT_HANDSHAKING:
/* 394 */         newMask = this.appEventMask;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 402 */     if (this.endOfStream && (this.appBufferStatus == null || !this.appBufferStatus.hasBufferedInput())) {
/* 403 */       newMask &= 0xFFFFFFFE;
/*     */     }
/*     */ 
/*     */     
/* 407 */     if (this.outEncrypted.hasData()) {
/* 408 */       newMask |= 0x4;
/*     */     }
/*     */ 
/*     */     
/* 412 */     if (oldMask != newMask) {
/* 413 */       this.session.setEventMask(newMask);
/*     */     }
/*     */   }
/*     */   
/*     */   private int sendEncryptedData() throws IOException {
/* 418 */     if (!this.outEncrypted.hasData())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 423 */       return this.session.channel().write(EMPTY_BUFFER);
/*     */     }
/*     */ 
/*     */     
/* 427 */     ByteBuffer outEncryptedBuf = this.outEncrypted.acquire();
/*     */ 
/*     */     
/* 430 */     outEncryptedBuf.flip();
/* 431 */     int bytesWritten = this.session.channel().write(outEncryptedBuf);
/* 432 */     outEncryptedBuf.compact();
/*     */ 
/*     */     
/* 435 */     if (outEncryptedBuf.position() == 0) {
/* 436 */       this.outEncrypted.release();
/*     */     }
/* 438 */     return bytesWritten;
/*     */   }
/*     */   
/*     */   private int receiveEncryptedData() throws IOException {
/* 442 */     if (this.endOfStream) {
/* 443 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 447 */     ByteBuffer inEncryptedBuf = this.inEncrypted.acquire();
/*     */ 
/*     */     
/* 450 */     int bytesRead = this.session.channel().read(inEncryptedBuf);
/*     */ 
/*     */     
/* 453 */     if (inEncryptedBuf.position() == 0) {
/* 454 */       this.inEncrypted.release();
/*     */     }
/* 456 */     if (bytesRead == -1) {
/* 457 */       this.endOfStream = true;
/*     */     }
/* 459 */     return bytesRead;
/*     */   }
/*     */   
/*     */   private boolean decryptData() throws SSLException {
/* 463 */     boolean decrypted = false;
/* 464 */     while (this.inEncrypted.hasData()) {
/*     */       
/* 466 */       ByteBuffer inEncryptedBuf = this.inEncrypted.acquire();
/* 467 */       ByteBuffer inPlainBuf = this.inPlain.acquire();
/*     */ 
/*     */       
/* 470 */       inEncryptedBuf.flip();
/* 471 */       SSLEngineResult result = doUnwrap(inEncryptedBuf, inPlainBuf);
/* 472 */       inEncryptedBuf.compact();
/*     */ 
/*     */       
/* 475 */       try { if (!inEncryptedBuf.hasRemaining() && result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
/* 476 */           throw new SSLException("Unable to complete SSL handshake");
/*     */         }
/* 478 */         SSLEngineResult.Status status = result.getStatus();
/* 479 */         if (status == SSLEngineResult.Status.OK)
/* 480 */         { decrypted = true; }
/*     */         else
/* 482 */         { if (status == SSLEngineResult.Status.BUFFER_UNDERFLOW && this.endOfStream) {
/* 483 */             throw new SSLException("Unable to decrypt incoming data due to unexpected end of stream");
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 489 */           if (this.inEncrypted.acquire().position() == 0)
/* 490 */             this.inEncrypted.release();  break; }  } finally { if (this.inEncrypted.acquire().position() == 0) this.inEncrypted.release();
/*     */          }
/*     */     
/*     */     } 
/* 494 */     if (this.sslEngine.isInboundDone()) {
/* 495 */       this.endOfStream = true;
/*     */     }
/* 497 */     return decrypted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isAppInputReady() throws IOException {
/*     */     do {
/* 508 */       receiveEncryptedData();
/* 509 */       doHandshake();
/* 510 */       SSLEngineResult.HandshakeStatus status = this.sslEngine.getHandshakeStatus();
/* 511 */       if (status != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING && status != SSLEngineResult.HandshakeStatus.FINISHED)
/* 512 */         continue;  decryptData();
/*     */     }
/* 514 */     while (this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_TASK);
/*     */     
/* 516 */     return ((this.appEventMask & 0x1) > 0 && (this.inPlain.hasData() || (this.appBufferStatus != null && this.appBufferStatus.hasBufferedInput()) || (this.endOfStream && this.status == 0)));
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
/*     */   public synchronized boolean isAppOutputReady() throws IOException {
/* 529 */     return ((this.appEventMask & 0x4) > 0 && this.status == 0 && this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void inboundTransport() throws IOException {
/* 540 */     updateEventMask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void outboundTransport() throws IOException {
/* 549 */     sendEncryptedData();
/* 550 */     doHandshake();
/* 551 */     updateEventMask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isInboundDone() {
/* 558 */     return this.sslEngine.isInboundDone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isOutboundDone() {
/* 565 */     return this.sslEngine.isOutboundDone();
/*     */   }
/*     */   
/*     */   private synchronized int writePlain(ByteBuffer src) throws IOException {
/* 569 */     Args.notNull(src, "Byte buffer");
/* 570 */     if (this.status != 0) {
/* 571 */       throw new ClosedChannelException();
/*     */     }
/* 573 */     ByteBuffer outEncryptedBuf = this.outEncrypted.acquire();
/* 574 */     SSLEngineResult result = doWrap(src, outEncryptedBuf);
/* 575 */     if (result.getStatus() == SSLEngineResult.Status.CLOSED) {
/* 576 */       this.status = Integer.MAX_VALUE;
/*     */     }
/* 578 */     return result.bytesConsumed();
/*     */   }
/*     */   
/*     */   private synchronized int readPlain(ByteBuffer dst) {
/* 582 */     Args.notNull(dst, "Byte buffer");
/* 583 */     if (this.inPlain.hasData()) {
/*     */       
/* 585 */       ByteBuffer inPlainBuf = this.inPlain.acquire();
/*     */ 
/*     */       
/* 588 */       inPlainBuf.flip();
/* 589 */       int n = Math.min(inPlainBuf.remaining(), dst.remaining());
/* 590 */       for (int i = 0; i < n; i++) {
/* 591 */         dst.put(inPlainBuf.get());
/*     */       }
/* 593 */       inPlainBuf.compact();
/*     */ 
/*     */       
/* 596 */       if (inPlainBuf.position() == 0) {
/* 597 */         this.inPlain.release();
/*     */       }
/* 599 */       return n;
/*     */     } 
/* 601 */     return this.endOfStream ? -1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void close() {
/* 606 */     if (this.status >= 1) {
/*     */       return;
/*     */     }
/* 609 */     this.status = 1;
/* 610 */     if (this.session.getSocketTimeout() == 0) {
/* 611 */       this.session.setSocketTimeout(1000);
/*     */     }
/* 613 */     this.sslEngine.closeOutbound();
/*     */     try {
/* 615 */       updateEventMask();
/* 616 */     } catch (CancelledKeyException ex) {
/* 617 */       shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void shutdown() {
/* 623 */     if (this.status == Integer.MAX_VALUE) {
/*     */       return;
/*     */     }
/* 626 */     this.status = Integer.MAX_VALUE;
/* 627 */     this.session.shutdown();
/*     */     
/* 629 */     this.inEncrypted.release();
/* 630 */     this.outEncrypted.release();
/* 631 */     this.inPlain.release();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 637 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 642 */     return (this.status >= 1 || this.session.isClosed());
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteChannel channel() {
/* 647 */     return this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getLocalAddress() {
/* 652 */     return this.session.getLocalAddress();
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getRemoteAddress() {
/* 657 */     return this.session.getRemoteAddress();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int getEventMask() {
/* 662 */     return this.appEventMask;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setEventMask(int ops) {
/* 667 */     this.appEventMask = ops;
/* 668 */     updateEventMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setEvent(int op) {
/* 673 */     this.appEventMask |= op;
/* 674 */     updateEventMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clearEvent(int op) {
/* 679 */     this.appEventMask &= op ^ 0xFFFFFFFF;
/* 680 */     updateEventMask();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSocketTimeout() {
/* 685 */     return this.session.getSocketTimeout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSocketTimeout(int timeout) {
/* 690 */     this.session.setSocketTimeout(timeout);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean hasBufferedInput() {
/* 695 */     return ((this.appBufferStatus != null && this.appBufferStatus.hasBufferedInput()) || this.inEncrypted.hasData() || this.inPlain.hasData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean hasBufferedOutput() {
/* 702 */     return ((this.appBufferStatus != null && this.appBufferStatus.hasBufferedOutput()) || this.outEncrypted.hasData());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setBufferStatus(SessionBufferStatus status) {
/* 708 */     this.appBufferStatus = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getAttribute(String name) {
/* 713 */     return this.session.getAttribute(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object removeAttribute(String name) {
/* 718 */     return this.session.removeAttribute(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttribute(String name, Object obj) {
/* 723 */     this.session.setAttribute(name, obj);
/*     */   }
/*     */   
/*     */   private static void formatOps(StringBuilder buffer, int ops) {
/* 727 */     if ((ops & 0x1) > 0) {
/* 728 */       buffer.append('r');
/*     */     }
/* 730 */     if ((ops & 0x4) > 0) {
/* 731 */       buffer.append('w');
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 737 */     StringBuilder buffer = new StringBuilder();
/* 738 */     buffer.append(this.session);
/* 739 */     buffer.append("[");
/* 740 */     switch (this.status) {
/*     */       case 0:
/* 742 */         buffer.append("ACTIVE");
/*     */         break;
/*     */       case 1:
/* 745 */         buffer.append("CLOSING");
/*     */         break;
/*     */       case 2147483647:
/* 748 */         buffer.append("CLOSED");
/*     */         break;
/*     */     } 
/* 751 */     buffer.append("][");
/* 752 */     formatOps(buffer, this.appEventMask);
/* 753 */     buffer.append("][");
/* 754 */     buffer.append(this.sslEngine.getHandshakeStatus());
/* 755 */     if (this.sslEngine.isInboundDone()) {
/* 756 */       buffer.append("][inbound done][");
/*     */     }
/* 758 */     if (this.sslEngine.isOutboundDone()) {
/* 759 */       buffer.append("][outbound done][");
/*     */     }
/* 761 */     if (this.endOfStream) {
/* 762 */       buffer.append("][EOF][");
/*     */     }
/* 764 */     buffer.append("][");
/* 765 */     buffer.append(!this.inEncrypted.hasData() ? 0 : this.inEncrypted.acquire().position());
/* 766 */     buffer.append("][");
/* 767 */     buffer.append(!this.inPlain.hasData() ? 0 : this.inPlain.acquire().position());
/* 768 */     buffer.append("][");
/* 769 */     buffer.append(!this.outEncrypted.hasData() ? 0 : this.outEncrypted.acquire().position());
/* 770 */     buffer.append("]");
/* 771 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket getSocket() {
/* 776 */     return (this.session instanceof SocketAccessor) ? ((SocketAccessor)this.session).getSocket() : null;
/*     */   }
/*     */   
/*     */   private class InternalByteChannel implements ByteChannel {
/*     */     private InternalByteChannel() {}
/*     */     
/*     */     public int write(ByteBuffer src) throws IOException {
/* 783 */       return SSLIOSession.this.writePlain(src);
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(ByteBuffer dst) throws IOException {
/* 788 */       return SSLIOSession.this.readPlain(dst);
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 793 */       SSLIOSession.this.close();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isOpen() {
/* 798 */       return !SSLIOSession.this.isClosed();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ssl/SSLIOSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */