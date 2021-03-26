/*     */ package org.apache.http.impl.nio.pool;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.pool.AbstractNIOConnPool;
/*     */ import org.apache.http.nio.pool.NIOConnFactory;
/*     */ import org.apache.http.nio.pool.SocketAddressResolver;
/*     */ import org.apache.http.nio.reactor.ConnectingIOReactor;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.pool.PoolEntry;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE)
/*     */ public class BasicNIOConnPool
/*     */   extends AbstractNIOConnPool<HttpHost, NHttpClientConnection, BasicNIOPoolEntry>
/*     */ {
/*  63 */   private static final AtomicLong COUNTER = new AtomicLong();
/*     */   
/*     */   private final int connectTimeout;
/*     */   
/*     */   static class BasicAddressResolver
/*     */     implements SocketAddressResolver<HttpHost>
/*     */   {
/*     */     public SocketAddress resolveLocalAddress(HttpHost host) {
/*  71 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public SocketAddress resolveRemoteAddress(HttpHost host) {
/*  76 */       String hostname = host.getHostName();
/*  77 */       int port = host.getPort();
/*  78 */       if (port == -1) {
/*  79 */         if (host.getSchemeName().equalsIgnoreCase("http")) {
/*  80 */           port = 80;
/*  81 */         } else if (host.getSchemeName().equalsIgnoreCase("https")) {
/*  82 */           port = 443;
/*     */         } 
/*     */       }
/*  85 */       return new InetSocketAddress(hostname, port);
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
/*     */   @Deprecated
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor, NIOConnFactory<HttpHost, NHttpClientConnection> connFactory, HttpParams params) {
/*  98 */     super(ioReactor, connFactory, 2, 20);
/*  99 */     Args.notNull(params, "HTTP parameters");
/* 100 */     this.connectTimeout = params.getIntParameter("http.connection.timeout", 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor, HttpParams params) {
/* 110 */     this(ioReactor, new BasicNIOConnFactory(params), params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor, NIOConnFactory<HttpHost, NHttpClientConnection> connFactory, int connectTimeout) {
/* 120 */     super(ioReactor, connFactory, new BasicAddressResolver(), 2, 20);
/* 121 */     this.connectTimeout = connectTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor, int connectTimeout, ConnectionConfig config) {
/* 131 */     this(ioReactor, new BasicNIOConnFactory(config), connectTimeout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor, ConnectionConfig config) {
/* 140 */     this(ioReactor, new BasicNIOConnFactory(config), 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicNIOConnPool(ConnectingIOReactor ioReactor) {
/* 147 */     this(ioReactor, new BasicNIOConnFactory(ConnectionConfig.DEFAULT), 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected SocketAddress resolveRemoteAddress(HttpHost host) {
/* 156 */     return new InetSocketAddress(host.getHostName(), host.getPort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected SocketAddress resolveLocalAddress(HttpHost host) {
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BasicNIOPoolEntry createEntry(HttpHost host, NHttpClientConnection conn) {
/* 170 */     BasicNIOPoolEntry entry = new BasicNIOPoolEntry(Long.toString(COUNTER.getAndIncrement()), host, conn);
/*     */     
/* 172 */     entry.setSocketTimeout(conn.getSocketTimeout());
/* 173 */     return entry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Future<BasicNIOPoolEntry> lease(HttpHost route, Object state, FutureCallback<BasicNIOPoolEntry> callback) {
/* 181 */     return lease(route, state, this.connectTimeout, TimeUnit.MILLISECONDS, callback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Future<BasicNIOPoolEntry> lease(HttpHost route, Object state) {
/* 189 */     return lease(route, state, this.connectTimeout, TimeUnit.MILLISECONDS, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onLease(BasicNIOPoolEntry entry) {
/* 195 */     NHttpClientConnection conn = (NHttpClientConnection)entry.getConnection();
/* 196 */     conn.setSocketTimeout(entry.getSocketTimeout());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onRelease(BasicNIOPoolEntry entry) {
/* 201 */     NHttpClientConnection conn = (NHttpClientConnection)entry.getConnection();
/* 202 */     entry.setSocketTimeout(conn.getSocketTimeout());
/* 203 */     conn.setSocketTimeout(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/pool/BasicNIOConnPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */