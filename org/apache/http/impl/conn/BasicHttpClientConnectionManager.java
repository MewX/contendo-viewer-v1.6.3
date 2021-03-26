/*     */ package org.apache.http.impl.conn;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ import org.apache.http.util.LangUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class BasicHttpClientConnectionManager
/*     */   implements Closeable, HttpClientConnectionManager
/*     */ {
/*  85 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private final HttpClientConnectionOperator connectionOperator;
/*     */   
/*     */   private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
/*     */   
/*     */   private ManagedHttpClientConnection conn;
/*     */   private HttpRoute route;
/*     */   private Object state;
/*     */   private long updated;
/*     */   private long expiry;
/*     */   private boolean leased;
/*     */   private SocketConfig socketConfig;
/*     */   private ConnectionConfig connConfig;
/*     */   private final AtomicBoolean isShutdown;
/*     */   
/*     */   private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
/* 102 */     return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
/* 113 */     this(new DefaultHttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver), connFactory);
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
/*     */   public BasicHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
/* 126 */     this.connectionOperator = (HttpClientConnectionOperator)Args.notNull(httpClientConnectionOperator, "Connection operator");
/* 127 */     this.connFactory = (connFactory != null) ? connFactory : ManagedHttpClientConnectionFactory.INSTANCE;
/* 128 */     this.expiry = Long.MAX_VALUE;
/* 129 */     this.socketConfig = SocketConfig.DEFAULT;
/* 130 */     this.connConfig = ConnectionConfig.DEFAULT;
/* 131 */     this.isShutdown = new AtomicBoolean(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
/* 137 */     this(socketFactoryRegistry, connFactory, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry) {
/* 142 */     this(socketFactoryRegistry, null, null, null);
/*     */   }
/*     */   
/*     */   public BasicHttpClientConnectionManager() {
/* 146 */     this((Lookup<ConnectionSocketFactory>)getDefaultRegistry(), null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/* 152 */       shutdown();
/*     */     } finally {
/* 154 */       super.finalize();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 160 */     if (this.isShutdown.compareAndSet(false, true)) {
/* 161 */       closeConnection();
/*     */     }
/*     */   }
/*     */   
/*     */   HttpRoute getRoute() {
/* 166 */     return this.route;
/*     */   }
/*     */   
/*     */   Object getState() {
/* 170 */     return this.state;
/*     */   }
/*     */   
/*     */   public synchronized SocketConfig getSocketConfig() {
/* 174 */     return this.socketConfig;
/*     */   }
/*     */   
/*     */   public synchronized void setSocketConfig(SocketConfig socketConfig) {
/* 178 */     this.socketConfig = (socketConfig != null) ? socketConfig : SocketConfig.DEFAULT;
/*     */   }
/*     */   
/*     */   public synchronized ConnectionConfig getConnectionConfig() {
/* 182 */     return this.connConfig;
/*     */   }
/*     */   
/*     */   public synchronized void setConnectionConfig(ConnectionConfig connConfig) {
/* 186 */     this.connConfig = (connConfig != null) ? connConfig : ConnectionConfig.DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConnectionRequest requestConnection(final HttpRoute route, final Object state) {
/* 193 */     Args.notNull(route, "Route");
/* 194 */     return new ConnectionRequest()
/*     */       {
/*     */         
/*     */         public boolean cancel()
/*     */         {
/* 199 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         public HttpClientConnection get(long timeout, TimeUnit timeUnit) {
/* 204 */           return BasicHttpClientConnectionManager.this.getConnection(route, state);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void closeConnection() {
/* 212 */     if (this.conn != null) {
/* 213 */       this.log.debug("Closing connection");
/*     */       try {
/* 215 */         this.conn.close();
/* 216 */       } catch (IOException iox) {
/* 217 */         if (this.log.isDebugEnabled()) {
/* 218 */           this.log.debug("I/O exception closing connection", iox);
/*     */         }
/*     */       } 
/* 221 */       this.conn = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkExpiry() {
/* 226 */     if (this.conn != null && System.currentTimeMillis() >= this.expiry) {
/* 227 */       if (this.log.isDebugEnabled()) {
/* 228 */         this.log.debug("Connection expired @ " + new Date(this.expiry));
/*     */       }
/* 230 */       closeConnection();
/*     */     } 
/*     */   }
/*     */   
/*     */   synchronized HttpClientConnection getConnection(HttpRoute route, Object state) {
/* 235 */     Asserts.check(!this.isShutdown.get(), "Connection manager has been shut down");
/* 236 */     if (this.log.isDebugEnabled()) {
/* 237 */       this.log.debug("Get connection for route " + route);
/*     */     }
/* 239 */     Asserts.check(!this.leased, "Connection is still allocated");
/* 240 */     if (!LangUtils.equals(this.route, route) || !LangUtils.equals(this.state, state)) {
/* 241 */       closeConnection();
/*     */     }
/* 243 */     this.route = route;
/* 244 */     this.state = state;
/* 245 */     checkExpiry();
/* 246 */     if (this.conn == null) {
/* 247 */       this.conn = (ManagedHttpClientConnection)this.connFactory.create(route, this.connConfig);
/*     */     }
/* 249 */     this.conn.setSocketTimeout(this.socketConfig.getSoTimeout());
/* 250 */     this.leased = true;
/* 251 */     return (HttpClientConnection)this.conn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void releaseConnection(HttpClientConnection conn, Object state, long keepalive, TimeUnit timeUnit) {
/* 259 */     Args.notNull(conn, "Connection");
/* 260 */     Asserts.check((conn == this.conn), "Connection not obtained from this manager");
/* 261 */     if (this.log.isDebugEnabled()) {
/* 262 */       this.log.debug("Releasing connection " + conn);
/*     */     }
/* 264 */     if (this.isShutdown.get()) {
/*     */       return;
/*     */     }
/*     */     try {
/* 268 */       this.updated = System.currentTimeMillis();
/* 269 */       if (!this.conn.isOpen()) {
/* 270 */         this.conn = null;
/* 271 */         this.route = null;
/* 272 */         this.conn = null;
/* 273 */         this.expiry = Long.MAX_VALUE;
/*     */       } else {
/* 275 */         this.state = state;
/* 276 */         this.conn.setSocketTimeout(0);
/* 277 */         if (this.log.isDebugEnabled()) {
/*     */           String s;
/* 279 */           if (keepalive > 0L) {
/* 280 */             s = "for " + keepalive + " " + timeUnit;
/*     */           } else {
/* 282 */             s = "indefinitely";
/*     */           } 
/* 284 */           this.log.debug("Connection can be kept alive " + s);
/*     */         } 
/* 286 */         if (keepalive > 0L) {
/* 287 */           this.expiry = this.updated + timeUnit.toMillis(keepalive);
/*     */         } else {
/* 289 */           this.expiry = Long.MAX_VALUE;
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 293 */       this.leased = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void connect(HttpClientConnection conn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
/*     */     HttpHost host;
/* 303 */     Args.notNull(conn, "Connection");
/* 304 */     Args.notNull(route, "HTTP route");
/* 305 */     Asserts.check((conn == this.conn), "Connection not obtained from this manager");
/*     */     
/* 307 */     if (route.getProxyHost() != null) {
/* 308 */       host = route.getProxyHost();
/*     */     } else {
/* 310 */       host = route.getTargetHost();
/*     */     } 
/* 312 */     InetSocketAddress localAddress = route.getLocalSocketAddress();
/* 313 */     this.connectionOperator.connect(this.conn, host, localAddress, connectTimeout, this.socketConfig, context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {
/* 322 */     Args.notNull(conn, "Connection");
/* 323 */     Args.notNull(route, "HTTP route");
/* 324 */     Asserts.check((conn == this.conn), "Connection not obtained from this manager");
/* 325 */     this.connectionOperator.upgrade(this.conn, route.getTargetHost(), context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void routeComplete(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void closeExpiredConnections() {
/* 337 */     if (this.isShutdown.get()) {
/*     */       return;
/*     */     }
/* 340 */     if (!this.leased) {
/* 341 */       checkExpiry();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void closeIdleConnections(long idletime, TimeUnit timeUnit) {
/* 347 */     Args.notNull(timeUnit, "Time unit");
/* 348 */     if (this.isShutdown.get()) {
/*     */       return;
/*     */     }
/* 351 */     if (!this.leased) {
/* 352 */       long time = timeUnit.toMillis(idletime);
/* 353 */       if (time < 0L) {
/* 354 */         time = 0L;
/*     */       }
/* 356 */       long deadline = System.currentTimeMillis() - time;
/* 357 */       if (this.updated <= deadline) {
/* 358 */         closeConnection();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() {
/* 365 */     close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/conn/BasicHttpClientConnectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */