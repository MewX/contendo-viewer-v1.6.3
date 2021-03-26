/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.channels.CancelledKeyException;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.ServerSocketChannel;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.nio.reactor.IOReactorStatus;
/*     */ import org.apache.http.nio.reactor.ListenerEndpoint;
/*     */ import org.apache.http.nio.reactor.ListeningIOReactor;
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
/*     */ public class DefaultListeningIOReactor
/*     */   extends AbstractMultiworkerIOReactor
/*     */   implements ListeningIOReactor
/*     */ {
/*     */   private final Queue<ListenerEndpointImpl> requestQueue;
/*     */   private final Set<ListenerEndpointImpl> endpoints;
/*     */   private final Set<SocketAddress> pausedEndpoints;
/*     */   private volatile boolean paused;
/*     */   
/*     */   public DefaultListeningIOReactor(IOReactorConfig config, ThreadFactory threadFactory) throws IOReactorException {
/*  82 */     super(config, threadFactory);
/*  83 */     this.requestQueue = new ConcurrentLinkedQueue<ListenerEndpointImpl>();
/*  84 */     this.endpoints = Collections.synchronizedSet(new HashSet<ListenerEndpointImpl>());
/*  85 */     this.pausedEndpoints = new HashSet<SocketAddress>();
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
/*     */   public DefaultListeningIOReactor(IOReactorConfig config) throws IOReactorException {
/*  98 */     this(config, (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultListeningIOReactor() throws IOReactorException {
/* 109 */     this((IOReactorConfig)null, (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultListeningIOReactor(int workerCount, ThreadFactory threadFactory, HttpParams params) throws IOReactorException {
/* 120 */     this(convert(workerCount, params), threadFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultListeningIOReactor(int workerCount, HttpParams params) throws IOReactorException {
/* 130 */     this(convert(workerCount, params), (ThreadFactory)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void cancelRequests() throws IOReactorException {
/*     */     ListenerEndpointImpl request;
/* 136 */     while ((request = this.requestQueue.poll()) != null) {
/* 137 */       request.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processEvents(int readyCount) throws IOReactorException {
/* 143 */     if (!this.paused) {
/* 144 */       processSessionRequests();
/*     */     }
/*     */     
/* 147 */     if (readyCount > 0) {
/* 148 */       Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
/* 149 */       for (SelectionKey key : selectedKeys)
/*     */       {
/* 151 */         processEvent(key);
/*     */       }
/*     */       
/* 154 */       selectedKeys.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void processEvent(SelectionKey key) throws IOReactorException {
/*     */     try {
/* 162 */       if (key.isAcceptable()) {
/*     */         
/* 164 */         ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
/*     */         while (true) {
/* 166 */           SocketChannel socketChannel = null;
/*     */           try {
/* 168 */             socketChannel = serverChannel.accept();
/* 169 */           } catch (IOException ex) {
/* 170 */             if (this.exceptionHandler == null || !this.exceptionHandler.handle(ex))
/*     */             {
/* 172 */               throw new IOReactorException("Failure accepting connection", ex);
/*     */             }
/*     */           } 
/*     */           
/* 176 */           if (socketChannel == null) {
/*     */             break;
/*     */           }
/*     */           try {
/* 180 */             prepareSocket(socketChannel.socket());
/* 181 */           } catch (IOException ex) {
/* 182 */             if (this.exceptionHandler == null || !this.exceptionHandler.handle(ex))
/*     */             {
/* 184 */               throw new IOReactorException("Failure initalizing socket", ex);
/*     */             }
/*     */           } 
/*     */           
/* 188 */           ChannelEntry entry = new ChannelEntry(socketChannel);
/* 189 */           addChannel(entry);
/*     */         }
/*     */       
/*     */       } 
/* 193 */     } catch (CancelledKeyException ex) {
/* 194 */       ListenerEndpoint endpoint = (ListenerEndpoint)key.attachment();
/* 195 */       this.endpoints.remove(endpoint);
/* 196 */       key.attach(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ListenerEndpointImpl createEndpoint(SocketAddress address) {
/* 201 */     return new ListenerEndpointImpl(address, new ListenerEndpointClosedCallback()
/*     */         {
/*     */ 
/*     */           
/*     */           public void endpointClosed(ListenerEndpoint endpoint)
/*     */           {
/* 207 */             DefaultListeningIOReactor.this.endpoints.remove(endpoint);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListenerEndpoint listen(SocketAddress address) {
/* 215 */     Asserts.check((this.status.compareTo((Enum)IOReactorStatus.ACTIVE) <= 0), "I/O reactor has been shut down");
/*     */     
/* 217 */     ListenerEndpointImpl request = createEndpoint(address);
/* 218 */     this.requestQueue.add(request);
/* 219 */     this.selector.wakeup();
/* 220 */     return request;
/*     */   }
/*     */   
/*     */   private void processSessionRequests() throws IOReactorException {
/*     */     ListenerEndpointImpl request;
/* 225 */     while ((request = this.requestQueue.poll()) != null) {
/* 226 */       ServerSocketChannel serverChannel; SocketAddress address = request.getAddress();
/*     */       
/*     */       try {
/* 229 */         serverChannel = ServerSocketChannel.open();
/* 230 */       } catch (IOException ex) {
/* 231 */         throw new IOReactorException("Failure opening server socket", ex);
/*     */       } 
/*     */       try {
/* 234 */         ServerSocket socket = serverChannel.socket();
/* 235 */         socket.setReuseAddress(this.config.isSoReuseAddress());
/* 236 */         if (this.config.getSoTimeout() > 0) {
/* 237 */           socket.setSoTimeout(this.config.getSoTimeout());
/*     */         }
/* 239 */         if (this.config.getRcvBufSize() > 0) {
/* 240 */           socket.setReceiveBufferSize(this.config.getRcvBufSize());
/*     */         }
/* 242 */         serverChannel.configureBlocking(false);
/* 243 */         socket.bind(address, this.config.getBacklogSize());
/* 244 */       } catch (IOException ex) {
/* 245 */         closeChannel(serverChannel);
/* 246 */         request.failed(ex);
/* 247 */         if (this.exceptionHandler == null || !this.exceptionHandler.handle(ex)) {
/* 248 */           throw new IOReactorException("Failure binding socket to address " + address, ex);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       try {
/* 254 */         SelectionKey key = serverChannel.register(this.selector, 16);
/* 255 */         key.attach(request);
/* 256 */         request.setKey(key);
/* 257 */       } catch (IOException ex) {
/* 258 */         closeChannel(serverChannel);
/* 259 */         throw new IOReactorException("Failure registering channel with the selector", ex);
/*     */       } 
/*     */ 
/*     */       
/* 263 */       this.endpoints.add(request);
/* 264 */       request.completed(serverChannel.socket().getLocalSocketAddress());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ListenerEndpoint> getEndpoints() {
/* 270 */     Set<ListenerEndpoint> set = new HashSet<ListenerEndpoint>();
/* 271 */     synchronized (this.endpoints) {
/* 272 */       Iterator<ListenerEndpointImpl> it = this.endpoints.iterator();
/* 273 */       while (it.hasNext()) {
/* 274 */         ListenerEndpoint endpoint = it.next();
/* 275 */         if (!endpoint.isClosed()) {
/* 276 */           set.add(endpoint); continue;
/*     */         } 
/* 278 */         it.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 282 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() throws IOException {
/* 287 */     if (this.paused) {
/*     */       return;
/*     */     }
/* 290 */     this.paused = true;
/* 291 */     synchronized (this.endpoints) {
/* 292 */       for (ListenerEndpointImpl endpoint : this.endpoints) {
/* 293 */         if (!endpoint.isClosed()) {
/* 294 */           endpoint.close();
/* 295 */           this.pausedEndpoints.add(endpoint.getAddress());
/*     */         } 
/*     */       } 
/* 298 */       this.endpoints.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void resume() throws IOException {
/* 304 */     if (!this.paused) {
/*     */       return;
/*     */     }
/* 307 */     this.paused = false;
/* 308 */     for (SocketAddress address : this.pausedEndpoints) {
/* 309 */       ListenerEndpointImpl request = createEndpoint(address);
/* 310 */       this.requestQueue.add(request);
/*     */     } 
/* 312 */     this.pausedEndpoints.clear();
/* 313 */     this.selector.wakeup();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/DefaultListeningIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */