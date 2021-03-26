/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.impl.ConnSupport;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpResponseParserFactory;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class SSLNHttpClientConnectionFactory
/*     */   implements NHttpConnectionFactory<DefaultNHttpClientConnection>
/*     */ {
/*  67 */   public static final SSLNHttpClientConnectionFactory INSTANCE = new SSLNHttpClientConnectionFactory();
/*     */ 
/*     */   
/*     */   private final ContentLengthStrategy incomingContentStrategy;
/*     */ 
/*     */   
/*     */   private final ContentLengthStrategy outgoingContentStrategy;
/*     */ 
/*     */   
/*     */   private final NHttpMessageParserFactory<HttpResponse> responseParserFactory;
/*     */ 
/*     */   
/*     */   private final NHttpMessageWriterFactory<HttpRequest> requestWriterFactory;
/*     */   
/*     */   private final ByteBufferAllocator allocator;
/*     */   
/*     */   private final SSLContext sslContext;
/*     */   
/*     */   private final SSLSetupHandler sslHandler;
/*     */   
/*     */   private final ConnectionConfig cconfig;
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpResponseFactory responseFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  92 */     Args.notNull(responseFactory, "HTTP response factory");
/*  93 */     Args.notNull(allocator, "Byte buffer allocator");
/*  94 */     Args.notNull(params, "HTTP parameters");
/*  95 */     this.sslContext = (sslContext != null) ? sslContext : SSLContexts.createSystemDefault();
/*  96 */     this.sslHandler = sslHandler;
/*  97 */     this.allocator = allocator;
/*  98 */     this.incomingContentStrategy = null;
/*  99 */     this.outgoingContentStrategy = null;
/* 100 */     this.responseParserFactory = (NHttpMessageParserFactory<HttpResponse>)new DefaultHttpResponseParserFactory(null, responseFactory);
/* 101 */     this.requestWriterFactory = null;
/* 102 */     this.cconfig = HttpParamConfig.getConnectionConfig(params);
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
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/* 115 */     this(sslContext, sslHandler, (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public SSLNHttpClientConnectionFactory(HttpParams params) {
/* 125 */     this((SSLContext)null, (SSLSetupHandler)null, params);
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
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 141 */     this.sslContext = (sslContext != null) ? sslContext : SSLContexts.createSystemDefault();
/* 142 */     this.sslHandler = sslHandler;
/* 143 */     this.incomingContentStrategy = incomingContentStrategy;
/* 144 */     this.outgoingContentStrategy = outgoingContentStrategy;
/* 145 */     this.responseParserFactory = responseParserFactory;
/* 146 */     this.requestWriterFactory = requestWriterFactory;
/* 147 */     this.allocator = allocator;
/* 148 */     this.cconfig = (cconfig != null) ? cconfig : ConnectionConfig.DEFAULT;
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
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 161 */     this(sslContext, sslHandler, null, null, responseParserFactory, requestWriterFactory, allocator, cconfig);
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
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ConnectionConfig cconfig) {
/* 174 */     this(sslContext, sslHandler, null, null, responseParserFactory, requestWriterFactory, null, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpClientConnectionFactory(SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 185 */     this(sslContext, sslHandler, null, null, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpClientConnectionFactory(ConnectionConfig config) {
/* 192 */     this(null, null, null, null, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SSLNHttpClientConnectionFactory() {
/* 199 */     this(null, null, null, null, null, null);
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
/*     */   protected DefaultNHttpClientConnection createConnection(IOSession session, HttpResponseFactory responseFactory, ByteBufferAllocator allocator, HttpParams params) {
/* 211 */     return new DefaultNHttpClientConnection(session, responseFactory, allocator, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SSLIOSession createSSLIOSession(IOSession ioSession, SSLContext sslContext, SSLSetupHandler sslHandler) {
/* 221 */     Object attachment = ioSession.getAttribute("http.session.attachment");
/* 222 */     return new SSLIOSession(ioSession, SSLMode.CLIENT, (attachment instanceof HttpHost) ? (HttpHost)attachment : null, sslContext, sslHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnection createConnection(IOSession ioSession) {
/* 229 */     SSLIOSession sslioSession = createSSLIOSession(ioSession, this.sslContext, this.sslHandler);
/* 230 */     ioSession.setAttribute("http.session.ssl", sslioSession);
/* 231 */     return new DefaultNHttpClientConnection((IOSession)sslioSession, this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), this.allocator, ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestWriterFactory, this.responseParserFactory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SSLNHttpClientConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */