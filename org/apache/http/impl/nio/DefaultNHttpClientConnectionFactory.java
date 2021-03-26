/*     */ package org.apache.http.impl.nio;
/*     */ 
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
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParamConfig;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class DefaultNHttpClientConnectionFactory
/*     */   implements NHttpConnectionFactory<DefaultNHttpClientConnection>
/*     */ {
/*  60 */   public static final DefaultNHttpClientConnectionFactory INSTANCE = new DefaultNHttpClientConnectionFactory();
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
/*     */   private final ConnectionConfig cconfig;
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpClientConnectionFactory(HttpResponseFactory responseFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  81 */     Args.notNull(responseFactory, "HTTP response factory");
/*  82 */     Args.notNull(allocator, "Byte buffer allocator");
/*  83 */     Args.notNull(params, "HTTP parameters");
/*  84 */     this.allocator = allocator;
/*  85 */     this.incomingContentStrategy = null;
/*  86 */     this.outgoingContentStrategy = null;
/*  87 */     this.responseParserFactory = (NHttpMessageParserFactory<HttpResponse>)new DefaultHttpResponseParserFactory(null, responseFactory);
/*  88 */     this.requestWriterFactory = null;
/*  89 */     this.cconfig = HttpParamConfig.getConnectionConfig(params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpClientConnectionFactory(HttpParams params) {
/*  99 */     this((HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
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
/*     */   public DefaultNHttpClientConnectionFactory(ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 113 */     this.incomingContentStrategy = incomingContentStrategy;
/* 114 */     this.outgoingContentStrategy = outgoingContentStrategy;
/* 115 */     this.responseParserFactory = responseParserFactory;
/* 116 */     this.requestWriterFactory = requestWriterFactory;
/* 117 */     this.allocator = allocator;
/* 118 */     this.cconfig = (cconfig != null) ? cconfig : ConnectionConfig.DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnectionFactory(NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 129 */     this(null, null, responseParserFactory, requestWriterFactory, allocator, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnectionFactory(NHttpMessageParserFactory<HttpResponse> responseParserFactory, NHttpMessageWriterFactory<HttpRequest> requestWriterFactory, ConnectionConfig cconfig) {
/* 139 */     this(null, null, responseParserFactory, requestWriterFactory, null, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnectionFactory(ConnectionConfig cconfig) {
/* 146 */     this(null, null, null, null, null, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnectionFactory() {
/* 153 */     this(null, null, null, null, null, null);
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
/* 165 */     return new DefaultNHttpClientConnection(session, responseFactory, allocator, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultNHttpClientConnection createConnection(IOSession session) {
/* 170 */     return new DefaultNHttpClientConnection(session, this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), this.allocator, ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestWriterFactory, this.responseParserFactory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultNHttpClientConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */