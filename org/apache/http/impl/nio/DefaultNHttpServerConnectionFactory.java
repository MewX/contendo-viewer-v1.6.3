/*     */ package org.apache.http.impl.nio;
/*     */ 
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
/*     */ 
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
/*     */ public class DefaultNHttpServerConnectionFactory
/*     */   implements NHttpConnectionFactory<DefaultNHttpServerConnection>
/*     */ {
/*     */   private final ContentLengthStrategy incomingContentStrategy;
/*     */   private final ContentLengthStrategy outgoingContentStrategy;
/*     */   private final NHttpMessageParserFactory<HttpRequest> requestParserFactory;
/*     */   private final NHttpMessageWriterFactory<HttpResponse> responseWriterFactory;
/*     */   private final ByteBufferAllocator allocator;
/*     */   private final ConnectionConfig cconfig;
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpServerConnectionFactory(HttpRequestFactory requestFactory, ByteBufferAllocator allocator, HttpParams params) {
/*  79 */     Args.notNull(requestFactory, "HTTP request factory");
/*  80 */     Args.notNull(allocator, "Byte buffer allocator");
/*  81 */     Args.notNull(params, "HTTP parameters");
/*  82 */     this.incomingContentStrategy = null;
/*  83 */     this.outgoingContentStrategy = null;
/*  84 */     this.requestParserFactory = (NHttpMessageParserFactory<HttpRequest>)new DefaultHttpRequestParserFactory(null, requestFactory);
/*  85 */     this.responseWriterFactory = null;
/*  86 */     this.allocator = allocator;
/*  87 */     this.cconfig = HttpParamConfig.getConnectionConfig(params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultNHttpServerConnectionFactory(HttpParams params) {
/*  96 */     this((HttpRequestFactory)DefaultHttpRequestFactory.INSTANCE, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
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
/* 108 */     return new DefaultNHttpServerConnection(session, requestFactory, allocator, params);
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
/*     */   public DefaultNHttpServerConnectionFactory(ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory, ByteBufferAllocator allocator, ConnectionConfig cconfig) {
/* 122 */     this.incomingContentStrategy = incomingContentStrategy;
/* 123 */     this.outgoingContentStrategy = outgoingContentStrategy;
/* 124 */     this.requestParserFactory = requestParserFactory;
/* 125 */     this.responseWriterFactory = responseWriterFactory;
/* 126 */     this.allocator = allocator;
/* 127 */     this.cconfig = (cconfig != null) ? cconfig : ConnectionConfig.DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnectionFactory(ByteBufferAllocator allocator, NHttpMessageParserFactory<HttpRequest> requestParserFactory, NHttpMessageWriterFactory<HttpResponse> responseWriterFactory, ConnectionConfig cconfig) {
/* 138 */     this(null, null, requestParserFactory, responseWriterFactory, allocator, cconfig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnectionFactory(ConnectionConfig config) {
/* 145 */     this(null, null, null, null, null, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnectionFactory() {
/* 152 */     this(null, null, null, null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultNHttpServerConnection createConnection(IOSession session) {
/* 157 */     return new DefaultNHttpServerConnection(session, this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), this.allocator, ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestParserFactory, this.responseWriterFactory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultNHttpServerConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */