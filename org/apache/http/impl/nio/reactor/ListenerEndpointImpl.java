/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.Channel;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.reactor.ListenerEndpoint;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class ListenerEndpointImpl
/*     */   implements ListenerEndpoint
/*     */ {
/*     */   private volatile boolean completed;
/*     */   private volatile boolean closed;
/*     */   private volatile SelectionKey key;
/*     */   private volatile SocketAddress address;
/*     */   private volatile IOException exception;
/*     */   private final ListenerEndpointClosedCallback callback;
/*     */   
/*     */   public ListenerEndpointImpl(SocketAddress address, ListenerEndpointClosedCallback callback) {
/*  60 */     Args.notNull(address, "Address");
/*  61 */     this.address = address;
/*  62 */     this.callback = callback;
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getAddress() {
/*  67 */     return this.address;
/*     */   }
/*     */   
/*     */   public boolean isCompleted() {
/*  71 */     return this.completed;
/*     */   }
/*     */ 
/*     */   
/*     */   public IOException getException() {
/*  76 */     return this.exception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void waitFor() throws InterruptedException {
/*  81 */     if (this.completed) {
/*     */       return;
/*     */     }
/*  84 */     synchronized (this) {
/*  85 */       while (!this.completed) {
/*  86 */         wait();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void completed(SocketAddress address) {
/*  92 */     Args.notNull(address, "Address");
/*  93 */     if (this.completed) {
/*     */       return;
/*     */     }
/*  96 */     this.completed = true;
/*  97 */     synchronized (this) {
/*  98 */       this.address = address;
/*  99 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void failed(IOException exception) {
/* 104 */     if (exception == null) {
/*     */       return;
/*     */     }
/* 107 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 110 */     this.completed = true;
/* 111 */     synchronized (this) {
/* 112 */       this.exception = exception;
/* 113 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cancel() {
/* 118 */     if (this.completed) {
/*     */       return;
/*     */     }
/* 121 */     this.completed = true;
/* 122 */     this.closed = true;
/* 123 */     synchronized (this) {
/* 124 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setKey(SelectionKey key) {
/* 129 */     this.key = key;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 134 */     return (this.closed || (this.key != null && !this.key.isValid()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 139 */     if (this.closed) {
/*     */       return;
/*     */     }
/* 142 */     this.completed = true;
/* 143 */     this.closed = true;
/* 144 */     if (this.key != null) {
/* 145 */       this.key.cancel();
/* 146 */       Channel channel = this.key.channel();
/*     */       try {
/* 148 */         channel.close();
/* 149 */       } catch (IOException iOException) {}
/*     */     } 
/* 151 */     if (this.callback != null) {
/* 152 */       this.callback.endpointClosed(this);
/*     */     }
/* 154 */     synchronized (this) {
/* 155 */       notifyAll();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     return "[address=" + this.address + ", key=" + this.key + ", closed=" + this.closed + ", completed=" + this.completed + ", exception=" + this.exception + ", callback=" + this.callback + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/ListenerEndpointImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */