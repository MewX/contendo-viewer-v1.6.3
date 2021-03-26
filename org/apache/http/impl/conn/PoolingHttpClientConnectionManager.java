/*     */ package org.apache.http.impl.conn;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CancellationException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.http.HttpClientConnection;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.config.Lookup;
/*     */ import org.apache.http.config.Registry;
/*     */ import org.apache.http.config.RegistryBuilder;
/*     */ import org.apache.http.config.SocketConfig;
/*     */ import org.apache.http.conn.ConnectionPoolTimeoutException;
/*     */ import org.apache.http.conn.ConnectionRequest;
/*     */ import org.apache.http.conn.DnsResolver;
/*     */ import org.apache.http.conn.HttpClientConnectionManager;
/*     */ import org.apache.http.conn.HttpClientConnectionOperator;
/*     */ import org.apache.http.conn.HttpConnectionFactory;
/*     */ import org.apache.http.conn.ManagedHttpClientConnection;
/*     */ import org.apache.http.conn.SchemePortResolver;
/*     */ import org.apache.http.conn.routing.HttpRoute;
/*     */ import org.apache.http.conn.socket.ConnectionSocketFactory;
/*     */ import org.apache.http.conn.socket.PlainConnectionSocketFactory;
/*     */ import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
/*     */ import org.apache.http.pool.ConnFactory;
/*     */ import org.apache.http.pool.ConnPoolControl;
/*     */ import org.apache.http.pool.PoolEntryCallback;
/*     */ import org.apache.http.pool.PoolStats;
/*     */ import org.apache.http.protocol.HttpContext;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class PoolingHttpClientConnectionManager
/*     */   implements Closeable, HttpClientConnectionManager, ConnPoolControl<HttpRoute>
/*     */ {
/* 107 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private final ConfigData configData;
/*     */   private final CPool pool;
/*     */   private final HttpClientConnectionOperator connectionOperator;
/*     */   private final AtomicBoolean isShutDown;
/*     */   
/*     */   private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
/* 115 */     return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager() {
/* 122 */     this(getDefaultRegistry());
/*     */   }
/*     */   
/*     */   public PoolingHttpClientConnectionManager(long timeToLive, TimeUnit timeUnit) {
/* 126 */     this(getDefaultRegistry(), null, null, null, timeToLive, timeUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry) {
/* 131 */     this(socketFactoryRegistry, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, DnsResolver dnsResolver) {
/* 137 */     this(socketFactoryRegistry, null, dnsResolver);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
/* 143 */     this(socketFactoryRegistry, connFactory, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
/* 148 */     this(getDefaultRegistry(), connFactory, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, DnsResolver dnsResolver) {
/* 155 */     this(socketFactoryRegistry, connFactory, null, dnsResolver, -1L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver, long timeToLive, TimeUnit timeUnit) {
/* 164 */     this(new DefaultHttpClientConnectionOperator((Lookup<ConnectionSocketFactory>)socketFactoryRegistry, schemePortResolver, dnsResolver), connFactory, timeToLive, timeUnit);
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
/*     */   public PoolingHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, long timeToLive, TimeUnit timeUnit) {
/* 179 */     this.configData = new ConfigData();
/* 180 */     this.pool = new CPool(new InternalConnectionFactory(this.configData, connFactory), 2, 20, timeToLive, timeUnit);
/*     */     
/* 182 */     this.pool.setValidateAfterInactivity(2000);
/* 183 */     this.connectionOperator = (HttpClientConnectionOperator)Args.notNull(httpClientConnectionOperator, "HttpClientConnectionOperator");
/* 184 */     this.isShutDown = new AtomicBoolean(false);
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
/*     */   PoolingHttpClientConnectionManager(CPool pool, Lookup<ConnectionSocketFactory> socketFactoryRegistry, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
/* 196 */     this.configData = new ConfigData();
/* 197 */     this.pool = pool;
/* 198 */     this.connectionOperator = new DefaultHttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
/*     */     
/* 200 */     this.isShutDown = new AtomicBoolean(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/* 206 */       shutdown();
/*     */     } finally {
/* 208 */       super.finalize();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 214 */     shutdown();
/*     */   }
/*     */   
/*     */   private String format(HttpRoute route, Object state) {
/* 218 */     StringBuilder buf = new StringBuilder();
/* 219 */     buf.append("[route: ").append(route).append("]");
/* 220 */     if (state != null) {
/* 221 */       buf.append("[state: ").append(state).append("]");
/*     */     }
/* 223 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private String formatStats(HttpRoute route) {
/* 227 */     StringBuilder buf = new StringBuilder();
/* 228 */     PoolStats totals = this.pool.getTotalStats();
/* 229 */     PoolStats stats = this.pool.getStats(route);
/* 230 */     buf.append("[total kept alive: ").append(totals.getAvailable()).append("; ");
/* 231 */     buf.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
/* 232 */     buf.append(" of ").append(stats.getMax()).append("; ");
/* 233 */     buf.append("total allocated: ").append(totals.getLeased() + totals.getAvailable());
/* 234 */     buf.append(" of ").append(totals.getMax()).append("]");
/* 235 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private String format(CPoolEntry entry) {
/* 239 */     StringBuilder buf = new StringBuilder();
/* 240 */     buf.append("[id: ").append(entry.getId()).append("]");
/* 241 */     buf.append("[route: ").append(entry.getRoute()).append("]");
/* 242 */     Object state = entry.getState();
/* 243 */     if (state != null) {
/* 244 */       buf.append("[state: ").append(state).append("]");
/*     */     }
/* 246 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private SocketConfig resolveSocketConfig(HttpHost host) {
/* 250 */     SocketConfig socketConfig = this.configData.getSocketConfig(host);
/* 251 */     if (socketConfig == null) {
/* 252 */       socketConfig = this.configData.getDefaultSocketConfig();
/*     */     }
/* 254 */     if (socketConfig == null) {
/* 255 */       socketConfig = SocketConfig.DEFAULT;
/*     */     }
/* 257 */     return socketConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionRequest requestConnection(final HttpRoute route, Object state) {
/* 264 */     Args.notNull(route, "HTTP route");
/* 265 */     if (this.log.isDebugEnabled()) {
/* 266 */       this.log.debug("Connection request: " + format(route, state) + formatStats(route));
/*     */     }
/* 268 */     final Future<CPoolEntry> future = this.pool.lease(route, state, null);
/* 269 */     return new ConnectionRequest()
/*     */       {
/*     */         public boolean cancel()
/*     */         {
/* 273 */           return future.cancel(true);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public HttpClientConnection get(long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
/* 280 */           HttpClientConnection conn = PoolingHttpClientConnectionManager.this.leaseConnection(future, timeout, timeUnit);
/* 281 */           if (conn.isOpen()) {
/*     */             HttpHost host;
/* 283 */             if (route.getProxyHost() != null) {
/* 284 */               host = route.getProxyHost();
/*     */             } else {
/* 286 */               host = route.getTargetHost();
/*     */             } 
/* 288 */             SocketConfig socketConfig = PoolingHttpClientConnectionManager.this.resolveSocketConfig(host);
/* 289 */             conn.setSocketTimeout(socketConfig.getSoTimeout());
/*     */           } 
/* 291 */           return conn;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HttpClientConnection leaseConnection(Future<CPoolEntry> future, long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
/*     */     try {
/* 304 */       CPoolEntry entry = future.get(timeout, timeUnit);
/* 305 */       if (entry == null || future.isCancelled()) {
/* 306 */         throw new ExecutionException(new CancellationException("Operation cancelled"));
/*     */       }
/* 308 */       Asserts.check((entry.getConnection() != null), "Pool entry with no connection");
/* 309 */       if (this.log.isDebugEnabled()) {
/* 310 */         this.log.debug("Connection leased: " + format(entry) + formatStats((HttpRoute)entry.getRoute()));
/*     */       }
/* 312 */       return CPoolProxy.newProxy(entry);
/* 313 */     } catch (TimeoutException ex) {
/* 314 */       throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseConnection(HttpClientConnection managedConn, Object state, long keepalive, TimeUnit timeUnit) {
/* 323 */     Args.notNull(managedConn, "Managed connection");
/* 324 */     synchronized (managedConn) {
/* 325 */       CPoolEntry entry = CPoolProxy.detach(managedConn);
/* 326 */       if (entry == null) {
/*     */         return;
/*     */       }
/* 329 */       ManagedHttpClientConnection conn = (ManagedHttpClientConnection)entry.getConnection();
/*     */       try {
/* 331 */         if (conn.isOpen()) {
/* 332 */           TimeUnit effectiveUnit = (timeUnit != null) ? timeUnit : TimeUnit.MILLISECONDS;
/* 333 */           entry.setState(state);
/* 334 */           entry.updateExpiry(keepalive, effectiveUnit);
/* 335 */           if (this.log.isDebugEnabled()) {
/*     */             String s;
/* 337 */             if (keepalive > 0L) {
/* 338 */               s = "for " + (effectiveUnit.toMillis(keepalive) / 1000.0D) + " seconds";
/*     */             } else {
/* 340 */               s = "indefinitely";
/*     */             } 
/* 342 */             this.log.debug("Connection " + format(entry) + " can be kept alive " + s);
/*     */           } 
/* 344 */           conn.setSocketTimeout(0);
/*     */         } 
/*     */       } finally {
/* 347 */         this.pool.release(entry, (conn.isOpen() && entry.isRouteComplete()));
/* 348 */         if (this.log.isDebugEnabled()) {
/* 349 */           this.log.debug("Connection released: " + format(entry) + formatStats((HttpRoute)entry.getRoute()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void connect(HttpClientConnection managedConn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
/*     */     ManagedHttpClientConnection conn;
/*     */     HttpHost host;
/* 361 */     Args.notNull(managedConn, "Managed Connection");
/* 362 */     Args.notNull(route, "HTTP route");
/*     */     
/* 364 */     synchronized (managedConn) {
/* 365 */       CPoolEntry entry = CPoolProxy.getPoolEntry(managedConn);
/* 366 */       conn = (ManagedHttpClientConnection)entry.getConnection();
/*     */     } 
/*     */     
/* 369 */     if (route.getProxyHost() != null) {
/* 370 */       host = route.getProxyHost();
/*     */     } else {
/* 372 */       host = route.getTargetHost();
/*     */     } 
/* 374 */     this.connectionOperator.connect(conn, host, route.getLocalSocketAddress(), connectTimeout, resolveSocketConfig(host), context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
/*     */     ManagedHttpClientConnection conn;
/* 383 */     Args.notNull(managedConn, "Managed Connection");
/* 384 */     Args.notNull(route, "HTTP route");
/*     */     
/* 386 */     synchronized (managedConn) {
/* 387 */       CPoolEntry entry = CPoolProxy.getPoolEntry(managedConn);
/* 388 */       conn = (ManagedHttpClientConnection)entry.getConnection();
/*     */     } 
/* 390 */     this.connectionOperator.upgrade(conn, route.getTargetHost(), context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void routeComplete(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
/* 398 */     Args.notNull(managedConn, "Managed Connection");
/* 399 */     Args.notNull(route, "HTTP route");
/* 400 */     synchronized (managedConn) {
/* 401 */       CPoolEntry entry = CPoolProxy.getPoolEntry(managedConn);
/* 402 */       entry.markRouteComplete();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() {
/* 408 */     if (this.isShutDown.compareAndSet(false, true)) {
/* 409 */       this.log.debug("Connection manager is shutting down");
/*     */       try {
/* 411 */         this.pool.shutdown();
/* 412 */       } catch (IOException ex) {
/* 413 */         this.log.debug("I/O exception shutting down connection manager", ex);
/*     */       } 
/* 415 */       this.log.debug("Connection manager shut down");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeIdleConnections(long idleTimeout, TimeUnit timeUnit) {
/* 421 */     if (this.log.isDebugEnabled()) {
/* 422 */       this.log.debug("Closing connections idle longer than " + idleTimeout + " " + timeUnit);
/*     */     }
/* 424 */     this.pool.closeIdle(idleTimeout, timeUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeExpiredConnections() {
/* 429 */     this.log.debug("Closing expired connections");
/* 430 */     this.pool.closeExpired();
/*     */   }
/*     */   
/*     */   protected void enumAvailable(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> callback) {
/* 434 */     this.pool.enumAvailable(callback);
/*     */   }
/*     */   
/*     */   protected void enumLeased(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> callback) {
/* 438 */     this.pool.enumLeased(callback);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxTotal() {
/* 443 */     return this.pool.getMaxTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxTotal(int max) {
/* 448 */     this.pool.setMaxTotal(max);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultMaxPerRoute() {
/* 453 */     return this.pool.getDefaultMaxPerRoute();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultMaxPerRoute(int max) {
/* 458 */     this.pool.setDefaultMaxPerRoute(max);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxPerRoute(HttpRoute route) {
/* 463 */     return this.pool.getMaxPerRoute(route);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxPerRoute(HttpRoute route, int max) {
/* 468 */     this.pool.setMaxPerRoute(route, max);
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getTotalStats() {
/* 473 */     return this.pool.getTotalStats();
/*     */   }
/*     */ 
/*     */   
/*     */   public PoolStats getStats(HttpRoute route) {
/* 478 */     return this.pool.getStats(route);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<HttpRoute> getRoutes() {
/* 485 */     return this.pool.getRoutes();
/*     */   }
/*     */   
/*     */   public SocketConfig getDefaultSocketConfig() {
/* 489 */     return this.configData.getDefaultSocketConfig();
/*     */   }
/*     */   
/*     */   public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
/* 493 */     this.configData.setDefaultSocketConfig(defaultSocketConfig);
/*     */   }
/*     */   
/*     */   public ConnectionConfig getDefaultConnectionConfig() {
/* 497 */     return this.configData.getDefaultConnectionConfig();
/*     */   }
/*     */   
/*     */   public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
/* 501 */     this.configData.setDefaultConnectionConfig(defaultConnectionConfig);
/*     */   }
/*     */   
/*     */   public SocketConfig getSocketConfig(HttpHost host) {
/* 505 */     return this.configData.getSocketConfig(host);
/*     */   }
/*     */   
/*     */   public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
/* 509 */     this.configData.setSocketConfig(host, socketConfig);
/*     */   }
/*     */   
/*     */   public ConnectionConfig getConnectionConfig(HttpHost host) {
/* 513 */     return this.configData.getConnectionConfig(host);
/*     */   }
/*     */   
/*     */   public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
/* 517 */     this.configData.setConnectionConfig(host, connectionConfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValidateAfterInactivity() {
/* 526 */     return this.pool.getValidateAfterInactivity();
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
/*     */   public void setValidateAfterInactivity(int ms) {
/* 541 */     this.pool.setValidateAfterInactivity(ms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ConfigData
/*     */   {
/* 553 */     private final Map<HttpHost, SocketConfig> socketConfigMap = new ConcurrentHashMap<HttpHost, SocketConfig>();
/* 554 */     private final Map<HttpHost, ConnectionConfig> connectionConfigMap = new ConcurrentHashMap<HttpHost, ConnectionConfig>();
/*     */     private volatile SocketConfig defaultSocketConfig;
/*     */     
/*     */     public SocketConfig getDefaultSocketConfig() {
/* 558 */       return this.defaultSocketConfig;
/*     */     }
/*     */     private volatile ConnectionConfig defaultConnectionConfig;
/*     */     public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
/* 562 */       this.defaultSocketConfig = defaultSocketConfig;
/*     */     }
/*     */     
/*     */     public ConnectionConfig getDefaultConnectionConfig() {
/* 566 */       return this.defaultConnectionConfig;
/*     */     }
/*     */     
/*     */     public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
/* 570 */       this.defaultConnectionConfig = defaultConnectionConfig;
/*     */     }
/*     */     
/*     */     public SocketConfig getSocketConfig(HttpHost host) {
/* 574 */       return this.socketConfigMap.get(host);
/*     */     }
/*     */     
/*     */     public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
/* 578 */       this.socketConfigMap.put(host, socketConfig);
/*     */     }
/*     */     
/*     */     public ConnectionConfig getConnectionConfig(HttpHost host) {
/* 582 */       return this.connectionConfigMap.get(host);
/*     */     }
/*     */     
/*     */     public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
/* 586 */       this.connectionConfigMap.put(host, connectionConfig);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class InternalConnectionFactory
/*     */     implements ConnFactory<HttpRoute, ManagedHttpClientConnection>
/*     */   {
/*     */     private final PoolingHttpClientConnectionManager.ConfigData configData;
/*     */     
/*     */     private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
/*     */ 
/*     */     
/*     */     InternalConnectionFactory(PoolingHttpClientConnectionManager.ConfigData configData, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
/* 600 */       this.configData = (configData != null) ? configData : new PoolingHttpClientConnectionManager.ConfigData();
/* 601 */       this.connFactory = (connFactory != null) ? connFactory : ManagedHttpClientConnectionFactory.INSTANCE;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ManagedHttpClientConnection create(HttpRoute route) throws IOException {
/* 607 */       ConnectionConfig config = null;
/* 608 */       if (route.getProxyHost() != null) {
/* 609 */         config = this.configData.getConnectionConfig(route.getProxyHost());
/*     */       }
/* 611 */       if (config == null) {
/* 612 */         config = this.configData.getConnectionConfig(route.getTargetHost());
/*     */       }
/* 614 */       if (config == null) {
/* 615 */         config = this.configData.getDefaultConnectionConfig();
/*     */       }
/* 617 */       if (config == null) {
/* 618 */         config = ConnectionConfig.DEFAULT;
/*     */       }
/* 620 */       return (ManagedHttpClientConnection)this.connFactory.create(route, config);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/conn/PoolingHttpClientConnectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */