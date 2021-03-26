/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import org.apache.http.nio.reactor.ConnectingIOReactor;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorStatus;
/*     */ import org.apache.http.nio.reactor.SessionRequest;
/*     */ import org.apache.http.nio.reactor.SessionRequestCallback;
/*     */ import org.apache.http.params.HttpParams;
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
/*     */ public class DefaultConnectingIOReactor
/*     */   extends AbstractMultiworkerIOReactor
/*     */   implements ConnectingIOReactor
/*     */ {
/*     */   private final Queue<SessionRequestImpl> requestQueue;
/*     */   private long lastTimeoutCheck;
/*     */   
/*     */   public DefaultConnectingIOReactor(IOReactorConfig config, ThreadFactory threadFactory) throws IOReactorException {
/*  79 */     super(config, threadFactory);
/*  80 */     this.requestQueue = new ConcurrentLinkedQueue<SessionRequestImpl>();
/*  81 */     this.lastTimeoutCheck = System.currentTimeMillis();
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
/*     */   public DefaultConnectingIOReactor(IOReactorConfig config) throws IOReactorException {
/*  94 */     this(config, (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultConnectingIOReactor() throws IOReactorException {
/* 105 */     this((IOReactorConfig)null, (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultConnectingIOReactor(int workerCount, ThreadFactory threadFactory, HttpParams params) throws IOReactorException {
/* 116 */     this(convert(workerCount, params), threadFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultConnectingIOReactor(int workerCount, HttpParams params) throws IOReactorException {
/* 126 */     this(convert(workerCount, params), (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void cancelRequests() throws IOReactorException {
/*     */     SessionRequestImpl request;
/* 132 */     while ((request = this.requestQueue.poll()) != null) {
/* 133 */       request.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processEvents(int readyCount) throws IOReactorException {
/* 139 */     processSessionRequests();
/*     */     
/* 141 */     if (readyCount > 0) {
/* 142 */       Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
/* 143 */       for (SelectionKey key : selectedKeys)
/*     */       {
/* 145 */         processEvent(key);
/*     */       }
/*     */       
/* 148 */       selectedKeys.clear();
/*     */     } 
/*     */     
/* 151 */     long currentTime = System.currentTimeMillis();
/* 152 */     if (currentTime - this.lastTimeoutCheck >= this.selectTimeout) {
/* 153 */       this.lastTimeoutCheck = currentTime;
/* 154 */       Set<SelectionKey> keys = this.selector.keys();
/* 155 */       processTimeouts(keys);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void processEvent(SelectionKey key) {
/*     */     try {
/* 162 */       if (key.isConnectable())
/*     */       {
/* 164 */         SocketChannel channel = (SocketChannel)key.channel();
/*     */         
/* 166 */         SessionRequestHandle requestHandle = (SessionRequestHandle)key.attachment();
/* 167 */         SessionRequestImpl sessionRequest = requestHandle.getSessionRequest();
/*     */ 
/*     */         
/*     */         try {
/* 171 */           channel.finishConnect();
/* 172 */         } catch (IOException ex) {
/* 173 */           sessionRequest.failed(ex);
/*     */         } 
/* 175 */         key.cancel();
/* 176 */         key.attach(null);
/* 177 */         if (!sessionRequest.isCompleted()) {
/* 178 */           addChannel(new ChannelEntry(channel, sessionRequest));
/*     */         } else {
/*     */           try {
/* 181 */             channel.close();
/* 182 */           } catch (IOException iOException) {}
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 187 */     } catch (CancelledKeyException ex) {
/* 188 */       SessionRequestHandle requestHandle = (SessionRequestHandle)key.attachment();
/* 189 */       key.attach(null);
/* 190 */       if (requestHandle != null) {
/* 191 */         SessionRequestImpl sessionRequest = requestHandle.getSessionRequest();
/* 192 */         if (sessionRequest != null) {
/* 193 */           sessionRequest.cancel();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processTimeouts(Set<SelectionKey> keys) {
/* 200 */     long now = System.currentTimeMillis();
/* 201 */     for (SelectionKey key : keys) {
/* 202 */       Object attachment = key.attachment();
/*     */       
/* 204 */       if (attachment instanceof SessionRequestHandle) {
/* 205 */         SessionRequestHandle handle = (SessionRequestHandle)key.attachment();
/* 206 */         SessionRequestImpl sessionRequest = handle.getSessionRequest();
/* 207 */         int timeout = sessionRequest.getConnectTimeout();
/* 208 */         if (timeout > 0 && 
/* 209 */           handle.getRequestTime() + timeout < now) {
/* 210 */           sessionRequest.timeout();
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
/*     */ 
/*     */   
/*     */   public SessionRequest connect(SocketAddress remoteAddress, SocketAddress localAddress, Object attachment, SessionRequestCallback callback) {
/* 224 */     Asserts.check((this.status.compareTo((Enum)IOReactorStatus.ACTIVE) <= 0), "I/O reactor has been shut down");
/*     */     
/* 226 */     SessionRequestImpl sessionRequest = new SessionRequestImpl(remoteAddress, localAddress, attachment, callback);
/*     */     
/* 228 */     sessionRequest.setConnectTimeout(this.config.getConnectTimeout());
/*     */     
/* 230 */     this.requestQueue.add(sessionRequest);
/* 231 */     this.selector.wakeup();
/*     */     
/* 233 */     return sessionRequest;
/*     */   }
/*     */   
/*     */   private void validateAddress(SocketAddress address) throws UnknownHostException {
/* 237 */     if (address == null) {
/*     */       return;
/*     */     }
/* 240 */     if (address instanceof InetSocketAddress) {
/* 241 */       InetSocketAddress endpoint = (InetSocketAddress)address;
/* 242 */       if (endpoint.isUnresolved()) {
/* 243 */         throw new UnknownHostException(endpoint.getHostName());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processSessionRequests() throws IOReactorException {
/*     */     SessionRequestImpl request;
/* 250 */     while ((request = this.requestQueue.poll()) != null) {
/* 251 */       SocketChannel socketChannel; if (request.isCompleted()) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       try {
/* 256 */         socketChannel = SocketChannel.open();
/* 257 */       } catch (IOException ex) {
/* 258 */         request.failed(ex);
/*     */         return;
/*     */       } 
/*     */       try {
/* 262 */         validateAddress(request.getLocalAddress());
/* 263 */         validateAddress(request.getRemoteAddress());
/*     */         
/* 265 */         socketChannel.configureBlocking(false);
/* 266 */         prepareSocket(socketChannel.socket());
/*     */         
/* 268 */         if (request.getLocalAddress() != null) {
/* 269 */           Socket sock = socketChannel.socket();
/* 270 */           sock.setReuseAddress(this.config.isSoReuseAddress());
/* 271 */           sock.bind(request.getLocalAddress());
/*     */         } 
/* 273 */         boolean connected = socketChannel.connect(request.getRemoteAddress());
/* 274 */         if (connected) {
/* 275 */           ChannelEntry entry = new ChannelEntry(socketChannel, request);
/* 276 */           addChannel(entry);
/*     */           continue;
/*     */         } 
/* 279 */       } catch (IOException ex) {
/* 280 */         closeChannel(socketChannel);
/* 281 */         request.failed(ex);
/*     */         return;
/* 283 */       } catch (SecurityException ex) {
/* 284 */         closeChannel(socketChannel);
/* 285 */         request.failed(new IOException(ex));
/*     */         
/*     */         return;
/*     */       } 
/* 289 */       SessionRequestHandle requestHandle = new SessionRequestHandle(request);
/*     */       try {
/* 291 */         SelectionKey key = socketChannel.register(this.selector, 8, requestHandle);
/*     */         
/* 293 */         request.setKey(key);
/* 294 */       } catch (IOException ex) {
/* 295 */         closeChannel(socketChannel);
/* 296 */         throw new IOReactorException("Failure registering channel with the selector", ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/DefaultConnectingIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */