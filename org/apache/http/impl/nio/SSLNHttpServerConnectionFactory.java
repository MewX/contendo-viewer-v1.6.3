/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpRequestFactory;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.impl.ConnSupport;
/*     */ import org.apache.http.impl.DefaultHttpRequestFactory;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestParserFactory;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
/*     */ import org.apache.http.nio.NHttpMessageParserFactory;
/*     */ import org.apache.http.nio.NHttpMessageWriterFactory;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.ssl.SSLIOSession;
/*     */ import org.apache.http.nio.reactor.ssl.SSLMode;
/*     */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParamConfig;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.ssl.SSLContexts;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class SSLNHttpServerConnectionFactory
/*     */   implements NHttpConnectionFactory<DefaultNHttpServerConnection>
/*     */ {
/*     */   private final SSLContext sslContext;
/*     */   private final SSLSetupHandler sslHandler;
/*     */   private final ContentLengthStrategy incomingContentStrategy;
/*     */   private final ContentLengthStrategy outgoingContentStrategy;
/*     */   private final NHttpMessageParserFactory<HttpRequest> requestParserFactory;
/*     */   private final NHttpMessageWriterFactory<HttpResponse> responseWriterFactory;
/*     */   private final ByteBufferAllocator allocator;
/*     */   private final ConnectionConfig cconfig;
/*     */   
/*     */   @Deprecated
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpRequestFactory requestFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  89 */     Args.notNull(requestFactory, "HTTP request factory");
/*  90 */     Args.notNull(allocator, "Byte buffer allocator");
/*  91 */     Args.notNull(params, "HTTP parameters");
/*  92 */     this.sslContext = (sslContext != null) ? sslContext : SSLContexts.createSystemDefault();
/*  93 */     this.sslHandler = sslHandler;
/*  94 */     this.incomingContentStrategy = null;
/*  95 */     this.outgoingContentStrategy = null;
/*  96 */     this.requestParserFactory = (NHttpMessageParserFactory<HttpRequest>)new DefaultHttpRequestParserFactory(null, requestFactory);
/*  97 */     this.responseWriterFactory = null;
/*  98 */     this.allocator = allocator;
/*  99 */     this.cconfig = HttpParamConfig.getConnectionConfig(params);
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
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/* 112 */     this(sslContext, sslHandler, (HttpRequestFactory)DefaultHttpRequestFactory.INSTANCE, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public SSLNHttpServerConnectionFactory(HttpParams params) {
/* 122 */     this((SSLContext)null, (SSLSetupHandler)null, params);
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
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 138 */     this.sslContext = (sslContext != null) ? sslContext : SSLContexts.createSystemDefault();
/* 139 */     this.sslHandler = sslHandler;
/* 140 */     this.incomingContentStrategy = incomingContentStrategy;
/* 141 */     this.outgoingContentStrategy = outgoingContentStrategy;
/* 142 */     this.requestParserFactory = requestParserFactory;
/* 143 */     this.responseWriterFactory = responseWriterFactory;
/* 144 */     this.allocator = allocator;
/* 145 */     this.cconfig = (cconfig != null) ? cconfig : ConnectionConfig.DEFAULT;
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
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 158 */     this(sslContext, sslHandler, null, null, requestParserFactory, responseWriterFactory, allocator, cconfig);
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
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory, ConnectionConfig cconfig) {
/* 171 */     this(sslContext, sslHandler, null, null, requestParserFactory, responseWriterFactory, null, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpServerConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 182 */     this(sslContext, sslHandler, null, null, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpServerConnectionFactory(ConnectionConfig config) {
/* 189 */     this(null, null, null, null, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpServerConnectionFactory() {
/* 196 */     this(null, null, null, null, null, null, null, null);
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
/*     */   protected DefaultNHttpServerConnection createConnection(IOSession session, HttpRequestFactory requestFactory, ByteBufferAllocator allocator, HttpParams params) {
/* 208 */     return new DefaultNHttpServerConnection(session, requestFactory, allocator, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SSLIOSession createSSLIOSession(IOSession ioSession, SSLContext sslContext, SSLSetupHandler sslHandler) {
/* 218 */     SSLIOSession sslioSession = new SSLIOSession(ioSession, SSLMode.SERVER, sslContext, sslHandler);
/*     */     
/* 220 */     return sslioSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnection createConnection(IOSession ioSession) {
/* 225 */     SSLIOSession sslioSession = createSSLIOSession(ioSession, this.sslContext, this.sslHandler);
/* 226 */     ioSession.setAttribute("http.session.ssl", sslioSession);
/* 227 */     return new DefaultNHttpServerConnection((IOSession)sslioSession, this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), this.allocator, ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestParserFactory, this.responseWriterFactory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SSLNHttpServerConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */