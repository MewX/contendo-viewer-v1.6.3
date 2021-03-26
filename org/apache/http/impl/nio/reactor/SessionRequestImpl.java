/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.Channel;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.SessionRequest;
/*     */ import org.apache.http.nio.reactor.SessionRequestCallback;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class SessionRequestImpl
/*     */   implements SessionRequest
/*     */ {
/*     */   private volatile boolean completed;
/*     */   private volatile SelectionKey key;
/*     */   private final SocketAddress remoteAddress;
/*     */   private final SocketAddress localAddress;
/*     */   private final Object attachment;
/*     */   private final SessionRequestCallback callback;
/*     */   private volatile int connectTimeout;
/*  59 */   private volatile IOSession session = null;
/*  60 */   private volatile IOException exception = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SessionRequestImpl(SocketAddress remoteAddress, SocketAddress localAddress, Object attachment, SessionRequestCallback callback) {
/*  68 */     Args.notNull(remoteAddress, "Remote address");
/*  69 */     this.remoteAddress = remoteAddress;
/*  70 */     this.localAddress = localAddress;
/*  71 */     this.attachment = attachment;
/*  72 */     this.callback = callback;
/*  73 */     this.connectTimeout = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getRemoteAddress() {
/*  78 */     return this.remoteAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getLocalAddress() {
/*  83 */     return this.localAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getAttachment() {
/*  88 */     return this.attachment;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCompleted() {
/*  93 */     return this.completed;
/*     */   }
/*     */   
/*     */   protected void setKey(SelectionKey key) {
/*  97 */     this.key = key;
/*     */   }
/*     */ 
/*     */   
/*     */   public void waitFor() throws InterruptedException {
/* 102 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 105 */     synchronized (this) {
/* 106 */       while (!this.completed) {
/* 107 */         wait();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IOSession getSession() {
/* 114 */     synchronized (this) {
/* 115 */       return this.session;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IOException getException() {
/* 121 */     synchronized (this) {
/* 122 */       return this.exception;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void completed(IOSession session) {
/* 127 */     Args.notNull(session, "Session");
/* 128 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 131 */     this.completed = true;
/* 132 */     synchronized (this) {
/* 133 */       this.session = session;
/* 134 */       if (this.callback != null) {
/* 135 */         this.callback.completed(this);
/*     */       }
/* 137 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void failed(IOException exception) {
/* 142 */     if (exception == null) {
/*     */       return;
/*     */     }
/* 145 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 148 */     this.completed = true;
/* 149 */     SelectionKey key = this.key;
/* 150 */     if (key != null) {
/* 151 */       key.cancel();
/* 152 */       Channel channel = key.channel();
/*     */       try {
/* 154 */         channel.close();
/* 155 */       } catch (IOException iOException) {}
/*     */     } 
/* 157 */     synchronized (this) {
/* 158 */       this.exception = exception;
/* 159 */       if (this.callback != null) {
/* 160 */         this.callback.failed(this);
/*     */       }
/* 162 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void timeout() {
/* 167 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 170 */     this.completed = true;
/* 171 */     SelectionKey key = this.key;
/* 172 */     if (key != null) {
/* 173 */       key.cancel();
/* 174 */       Channel channel = key.channel();
/* 175 */       if (channel.isOpen()) {
/*     */         try {
/* 177 */           channel.close();
/* 178 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/* 181 */     synchronized (this) {
/* 182 */       if (this.callback != null) {
/* 183 */         this.callback.timeout(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConnectTimeout() {
/* 190 */     return this.connectTimeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConnectTimeout(int timeout) {
/* 195 */     if (this.connectTimeout != timeout) {
/* 196 */       this.connectTimeout = timeout;
/* 197 */       SelectionKey key = this.key;
/* 198 */       if (key != null) {
/* 199 */         key.selector().wakeup();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancel() {
/* 206 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 209 */     this.completed = true;
/* 210 */     SelectionKey key = this.key;
/* 211 */     if (key != null) {
/* 212 */       key.cancel();
/* 213 */       Channel channel = key.channel();
/* 214 */       if (channel.isOpen()) {
/*     */         try {
/* 216 */           channel.close();
/* 217 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/* 220 */     synchronized (this) {
/* 221 */       if (this.callback != null) {
/* 222 */         this.callback.cancelled(this);
/*     */       }
/* 224 */       notifyAll();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SessionRequestImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */