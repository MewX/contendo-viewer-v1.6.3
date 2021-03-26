/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.ByteChannel;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionBufferStatus;
/*     */ import org.apache.http.nio.reactor.SocketAccessor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.SAFE)
/*     */ public class IOSessionImpl
/*     */   implements IOSession, SocketAccessor
/*     */ {
/*     */   private final SelectionKey key;
/*     */   private final ByteChannel channel;
/*     */   private final Map<String, Object> attributes;
/*     */   private final InterestOpsCallback interestOpsCallback;
/*     */   private final SessionClosedCallback sessionClosedCallback;
/*     */   private volatile int status;
/*     */   private volatile int currentEventMask;
/*     */   private volatile SessionBufferStatus bufferStatus;
/*     */   private volatile int socketTimeout;
/*     */   private final long startedTime;
/*     */   private volatile long lastReadTime;
/*     */   private volatile long lastWriteTime;
/*     */   private volatile long lastAccessTime;
/*     */   
/*     */   public IOSessionImpl(SelectionKey key, InterestOpsCallback interestOpsCallback, SessionClosedCallback sessionClosedCallback) {
/*  87 */     Args.notNull(key, "Selection key");
/*  88 */     this.key = key;
/*  89 */     this.channel = (ByteChannel)this.key.channel();
/*  90 */     this.interestOpsCallback = interestOpsCallback;
/*  91 */     this.sessionClosedCallback = sessionClosedCallback;
/*  92 */     this.attributes = Collections.synchronizedMap(new HashMap<String, Object>());
/*  93 */     this.currentEventMask = key.interestOps();
/*  94 */     this.socketTimeout = 0;
/*  95 */     this.status = 0;
/*  96 */     long now = System.currentTimeMillis();
/*  97 */     this.startedTime = now;
/*  98 */     this.lastReadTime = now;
/*  99 */     this.lastWriteTime = now;
/* 100 */     this.lastAccessTime = now;
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
/*     */   public IOSessionImpl(SelectionKey key, SessionClosedCallback sessionClosedCallback) {
/* 112 */     this(key, null, sessionClosedCallback);
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteChannel channel() {
/* 117 */     return this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getLocalAddress() {
/* 122 */     return (this.channel instanceof SocketChannel) ? ((SocketChannel)this.channel).socket().getLocalSocketAddress() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getRemoteAddress() {
/* 129 */     return (this.channel instanceof SocketChannel) ? ((SocketChannel)this.channel).socket().getRemoteSocketAddress() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEventMask() {
/* 136 */     return (this.interestOpsCallback != null) ? this.currentEventMask : this.key.interestOps();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setEventMask(int ops) {
/* 141 */     if (this.status == Integer.MAX_VALUE) {
/*     */       return;
/*     */     }
/* 144 */     if (this.interestOpsCallback != null) {
/*     */       
/* 146 */       this.currentEventMask = ops;
/*     */ 
/*     */       
/* 149 */       InterestOpEntry entry = new InterestOpEntry(this.key, this.currentEventMask);
/*     */ 
/*     */       
/* 152 */       this.interestOpsCallback.addInterestOps(entry);
/*     */     } else {
/* 154 */       this.key.interestOps(ops);
/*     */     } 
/* 156 */     this.key.selector().wakeup();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setEvent(int op) {
/* 161 */     if (this.status == Integer.MAX_VALUE) {
/*     */       return;
/*     */     }
/* 164 */     if (this.interestOpsCallback != null) {
/*     */       
/* 166 */       this.currentEventMask |= op;
/*     */ 
/*     */       
/* 169 */       InterestOpEntry entry = new InterestOpEntry(this.key, this.currentEventMask);
/*     */ 
/*     */       
/* 172 */       this.interestOpsCallback.addInterestOps(entry);
/*     */     } else {
/* 174 */       int ops = this.key.interestOps();
/* 175 */       this.key.interestOps(ops | op);
/*     */     } 
/* 177 */     this.key.selector().wakeup();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clearEvent(int op) {
/* 182 */     if (this.status == Integer.MAX_VALUE) {
/*     */       return;
/*     */     }
/* 185 */     if (this.interestOpsCallback != null) {
/*     */       
/* 187 */       this.currentEventMask &= op ^ 0xFFFFFFFF;
/*     */ 
/*     */       
/* 190 */       InterestOpEntry entry = new InterestOpEntry(this.key, this.currentEventMask);
/*     */ 
/*     */       
/* 193 */       this.interestOpsCallback.addInterestOps(entry);
/*     */     } else {
/* 195 */       int ops = this.key.interestOps();
/* 196 */       this.key.interestOps(ops & (op ^ 0xFFFFFFFF));
/*     */     } 
/* 198 */     this.key.selector().wakeup();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSocketTimeout() {
/* 203 */     return this.socketTimeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSocketTimeout(int timeout) {
/* 208 */     this.socketTimeout = timeout;
/* 209 */     this.lastAccessTime = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 214 */     synchronized (this) {
/* 215 */       if (this.status == Integer.MAX_VALUE) {
/*     */         return;
/*     */       }
/* 218 */       this.status = Integer.MAX_VALUE;
/* 219 */       this.key.cancel();
/*     */       try {
/* 221 */         this.key.channel().close();
/* 222 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/* 226 */       if (this.sessionClosedCallback != null) {
/* 227 */         this.sessionClosedCallback.sessionClosed(this);
/*     */       }
/* 229 */       if (this.key.selector().isOpen()) {
/* 230 */         this.key.selector().wakeup();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 237 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 242 */     return (this.status == Integer.MAX_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {
/* 249 */     close();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBufferedInput() {
/* 254 */     SessionBufferStatus buffStatus = this.bufferStatus;
/* 255 */     return (buffStatus != null && buffStatus.hasBufferedInput());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBufferedOutput() {
/* 260 */     SessionBufferStatus buffStatus = this.bufferStatus;
/* 261 */     return (buffStatus != null && buffStatus.hasBufferedOutput());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBufferStatus(SessionBufferStatus bufferStatus) {
/* 266 */     this.bufferStatus = bufferStatus;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getAttribute(String name) {
/* 271 */     return this.attributes.get(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object removeAttribute(String name) {
/* 276 */     return this.attributes.remove(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttribute(String name, Object obj) {
/* 281 */     this.attributes.put(name, obj);
/*     */   }
/*     */   
/*     */   public long getStartedTime() {
/* 285 */     return this.startedTime;
/*     */   }
/*     */   
/*     */   public long getLastReadTime() {
/* 289 */     return this.lastReadTime;
/*     */   }
/*     */   
/*     */   public long getLastWriteTime() {
/* 293 */     return this.lastWriteTime;
/*     */   }
/*     */   
/*     */   public long getLastAccessTime() {
/* 297 */     return this.lastAccessTime;
/*     */   }
/*     */   
/*     */   void resetLastRead() {
/* 301 */     long now = System.currentTimeMillis();
/* 302 */     this.lastReadTime = now;
/* 303 */     this.lastAccessTime = now;
/*     */   }
/*     */   
/*     */   void resetLastWrite() {
/* 307 */     long now = System.currentTimeMillis();
/* 308 */     this.lastWriteTime = now;
/* 309 */     this.lastAccessTime = now;
/*     */   }
/*     */   
/*     */   private static void formatOps(StringBuilder buffer, int ops) {
/* 313 */     if ((ops & 0x1) > 0) {
/* 314 */       buffer.append('r');
/*     */     }
/* 316 */     if ((ops & 0x4) > 0) {
/* 317 */       buffer.append('w');
/*     */     }
/* 319 */     if ((ops & 0x10) > 0) {
/* 320 */       buffer.append('a');
/*     */     }
/* 322 */     if ((ops & 0x8) > 0) {
/* 323 */       buffer.append('c');
/*     */     }
/*     */   }
/*     */   
/*     */   private static void formatAddress(StringBuilder buffer, SocketAddress socketAddress) {
/* 328 */     if (socketAddress instanceof InetSocketAddress) {
/* 329 */       InetSocketAddress addr = (InetSocketAddress)socketAddress;
/* 330 */       buffer.append((addr.getAddress() != null) ? addr.getAddress().getHostAddress() : addr.getAddress()).append(':').append(addr.getPort());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 335 */       buffer.append(socketAddress);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 341 */     StringBuilder buffer = new StringBuilder();
/* 342 */     synchronized (this.key) {
/* 343 */       SocketAddress remoteAddress = getRemoteAddress();
/* 344 */       SocketAddress localAddress = getLocalAddress();
/* 345 */       if (remoteAddress != null && localAddress != null) {
/* 346 */         formatAddress(buffer, localAddress);
/* 347 */         buffer.append("<->");
/* 348 */         formatAddress(buffer, remoteAddress);
/*     */       } 
/* 350 */       buffer.append('[');
/* 351 */       switch (this.status) {
/*     */         case 0:
/* 353 */           buffer.append("ACTIVE");
/*     */           break;
/*     */         case 1:
/* 356 */           buffer.append("CLOSING");
/*     */           break;
/*     */         case 2147483647:
/* 359 */           buffer.append("CLOSED");
/*     */           break;
/*     */       } 
/* 362 */       buffer.append("][");
/* 363 */       if (this.key.isValid()) {
/* 364 */         formatOps(buffer, (this.interestOpsCallback != null) ? this.currentEventMask : this.key.interestOps());
/*     */         
/* 366 */         buffer.append(':');
/* 367 */         formatOps(buffer, this.key.readyOps());
/*     */       } 
/*     */     } 
/* 370 */     buffer.append(']');
/* 371 */     return new String(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket getSocket() {
/* 376 */     return (this.channel instanceof SocketChannel) ? ((SocketChannel)this.channel).socket() : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/IOSessionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */