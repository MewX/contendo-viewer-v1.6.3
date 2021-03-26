/*     */ package org.apache.http.impl.nio.pool;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.nio.DefaultNHttpClientConnectionFactory;
/*     */ import org.apache.http.impl.nio.SSLNHttpClientConnectionFactory;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
/*     */ import org.apache.http.nio.NHttpMessageParserFactory;
/*     */ import org.apache.http.nio.NHttpMessageWriterFactory;
/*     */ import org.apache.http.nio.pool.NIOConnFactory;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class BasicNIOConnFactory
/*     */   implements NIOConnFactory<HttpHost, NHttpClientConnection>
/*     */ {
/*     */   private final NHttpConnectionFactory<? extends NHttpClientConnection> plainFactory;
/*     */   private final NHttpConnectionFactory<? extends NHttpClientConnection> sslFactory;
/*     */   
/*     */   public BasicNIOConnFactory(NHttpConnectionFactory<? extends NHttpClientConnection> plainFactory, NHttpConnectionFactory<? extends NHttpClientConnection> sslFactory) {
/*  73 */     Args.notNull(plainFactory, "Plain HTTP client connection factory");
/*  74 */     this.plainFactory = plainFactory;
/*  75 */     this.sslFactory = sslFactory;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicNIOConnFactory(NHttpConnectionFactory<? extends NHttpClientConnection> plainFactory) {
/*  80 */     this(plainFactory, null);
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
/*     */   @Deprecated
/*     */   public BasicNIOConnFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpResponseFactory responseFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  95 */     this((NHttpConnectionFactory<? extends NHttpClientConnection>)new DefaultNHttpClientConnectionFactory(responseFactory, allocator, params), (NHttpConnectionFactory<? extends NHttpClientConnection>)new SSLNHttpClientConnectionFactory(sslContext, sslHandler, responseFactory, allocator, params));
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
/*     */   @Deprecated
/*     */   public BasicNIOConnFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/* 110 */     this(sslContext, sslHandler, (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BasicNIOConnFactory(HttpParams params) {
/* 119 */     this((SSLContext)null, (SSLSetupHandler)null, params);
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
/*     */   public BasicNIOConnFactory(SSLContext sslContext, SSLSetupHandler sslHandler, NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ByteBufferAllocator allocator, ConnectionConfig config) {
/* 132 */     this((NHttpConnectionFactory<? extends NHttpClientConnection>)new DefaultNHttpClientConnectionFactory(responseParserFactory, requestWriterFactory, allocator, config), (NHttpConnectionFactory<? extends NHttpClientConnection>)new SSLNHttpClientConnectionFactory(sslContext, sslHandler, responseParserFactory, requestWriterFactory, allocator, config));
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
/*     */   public BasicNIOConnFactory(SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 146 */     this(sslContext, sslHandler, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicNIOConnFactory(ConnectionConfig config) {
/* 153 */     this((NHttpConnectionFactory<? extends NHttpClientConnection>)new DefaultNHttpClientConnectionFactory(config), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public NHttpClientConnection create(HttpHost route, IOSession session) throws IOException {
/*     */     NHttpClientConnection conn;
/* 159 */     if (route.getSchemeName().equalsIgnoreCase("https")) {
/* 160 */       if (this.sslFactory == null) {
/* 161 */         throw new IOException("SSL not supported");
/*     */       }
/* 163 */       conn = (NHttpClientConnection)this.sslFactory.createConnection(session);
/*     */     } else {
/* 165 */       conn = (NHttpClientConnection)this.plainFactory.createConnection(session);
/*     */     } 
/* 167 */     session.setAttribute("http.connection", conn);
/* 168 */     return conn;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/pool/BasicNIOConnFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */