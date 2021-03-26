/*     */ package org.apache.http.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import org.apache.http.HttpConnection;
/*     */ import org.apache.http.HttpConnectionFactory;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.entity.ContentLengthStrategy;
/*     */ import org.apache.http.io.HttpMessageParserFactory;
/*     */ import org.apache.http.io.HttpMessageWriterFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class DefaultBHttpClientConnectionFactory
/*     */   implements HttpConnectionFactory<DefaultBHttpClientConnection>
/*     */ {
/*  51 */   public static final DefaultBHttpClientConnectionFactory INSTANCE = new DefaultBHttpClientConnectionFactory();
/*     */ 
/*     */   
/*     */   private final ConnectionConfig cconfig;
/*     */   
/*     */   private final ContentLengthStrategy incomingContentStrategy;
/*     */   
/*     */   private final ContentLengthStrategy outgoingContentStrategy;
/*     */   
/*     */   private final HttpMessageWriterFactory<HttpRequest> requestWriterFactory;
/*     */   
/*     */   private final HttpMessageParserFactory<HttpResponse> responseParserFactory;
/*     */ 
/*     */   
/*     */   public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
/*  66 */     this.cconfig = (cconfig != null) ? cconfig : ConnectionConfig.DEFAULT;
/*  67 */     this.incomingContentStrategy = incomingContentStrategy;
/*  68 */     this.outgoingContentStrategy = outgoingContentStrategy;
/*  69 */     this.requestWriterFactory = requestWriterFactory;
/*  70 */     this.responseParserFactory = responseParserFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
/*  77 */     this(cconfig, null, null, requestWriterFactory, responseParserFactory);
/*     */   }
/*     */   
/*     */   public DefaultBHttpClientConnectionFactory(ConnectionConfig cconfig) {
/*  81 */     this(cconfig, null, null, null, null);
/*     */   }
/*     */   
/*     */   public DefaultBHttpClientConnectionFactory() {
/*  85 */     this(null, null, null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultBHttpClientConnection createConnection(Socket socket) throws IOException {
/*  90 */     DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(this.cconfig.getBufferSize(), this.cconfig.getFragmentSizeHint(), ConnSupport.createDecoder(this.cconfig), ConnSupport.createEncoder(this.cconfig), this.cconfig.getMessageConstraints(), this.incomingContentStrategy, this.outgoingContentStrategy, this.requestWriterFactory, this.responseParserFactory);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     conn.bind(socket);
/* 101 */     return conn;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/DefaultBHttpClientConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */