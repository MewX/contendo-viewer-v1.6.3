/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpRequestFactory;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.impl.nio.codecs.DefaultHttpRequestParserFactory;
/*     */ import org.apache.http.impl.nio.reactor.AbstractIODispatch;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
/*     */ import org.apache.http.nio.NHttpMessageParserFactory;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerEventHandler;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class DefaultHttpServerIODispatch<H extends NHttpServerEventHandler>
/*     */   extends AbstractIODispatch<DefaultNHttpServerConnection>
/*     */ {
/*     */   private final H handler;
/*     */   private final NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connectionFactory;
/*     */   
/*     */   public static <T extends NHttpServerEventHandler> DefaultHttpServerIODispatch<T> create(T handler, SSLContext sslContext, ConnectionConfig config) {
/*  75 */     return (sslContext == null) ? new DefaultHttpServerIODispatch<T>(handler, config) : new DefaultHttpServerIODispatch<T>(handler, sslContext, config);
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
/*     */   
/*     */   public static <T extends NHttpServerEventHandler> DefaultHttpServerIODispatch<T> create(T eventHandler, SSLContext sslContext, ConnectionConfig config, HttpRequestFactory httpRequestFactory) {
/*  92 */     DefaultHttpRequestParserFactory defaultHttpRequestParserFactory = new DefaultHttpRequestParserFactory(null, httpRequestFactory);
/*     */ 
/*     */     
/*  95 */     return (sslContext == null) ? new DefaultHttpServerIODispatch<T>(eventHandler, new DefaultNHttpServerConnectionFactory(null, (NHttpMessageParserFactory<HttpRequest>)defaultHttpRequestParserFactory, null, config)) : new DefaultHttpServerIODispatch<T>(eventHandler, new SSLNHttpServerConnectionFactory(sslContext, null, (NHttpMessageParserFactory<HttpRequest>)defaultHttpRequestParserFactory, null, config));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends NHttpServerEventHandler> DefaultHttpServerIODispatch<T> create(T handler, SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 118 */     return (sslContext == null) ? new DefaultHttpServerIODispatch<T>(handler, config) : new DefaultHttpServerIODispatch<T>(handler, sslContext, sslHandler, config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpServerIODispatch(H handler, NHttpConnectionFactory<? extends DefaultNHttpServerConnection> connFactory) {
/* 129 */     this.handler = (H)Args.notNull(handler, "HTTP server handler");
/* 130 */     this.connectionFactory = (NHttpConnectionFactory<? extends DefaultNHttpServerConnection>)Args.notNull(connFactory, "HTTP server connection factory");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultHttpServerIODispatch(H handler, HttpParams params) {
/* 141 */     this(handler, new DefaultNHttpServerConnectionFactory(params));
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
/*     */   public DefaultHttpServerIODispatch(H handler, SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/* 154 */     this(handler, new SSLNHttpServerConnectionFactory(sslContext, sslHandler, params));
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
/*     */   public DefaultHttpServerIODispatch(H handler, SSLContext sslContext, HttpParams params) {
/* 166 */     this(handler, sslContext, (SSLSetupHandler)null, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpServerIODispatch(H handler, ConnectionConfig config) {
/* 173 */     this(handler, new DefaultNHttpServerConnectionFactory(config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpServerIODispatch(H handler, SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 184 */     this(handler, new SSLNHttpServerConnectionFactory(sslContext, sslHandler, config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpServerIODispatch(H handler, SSLContext sslContext, ConnectionConfig config) {
/* 194 */     this(handler, new SSLNHttpServerConnectionFactory(sslContext, null, config));
/*     */   }
/*     */ 
/*     */   
/*     */   protected DefaultNHttpServerConnection createConnection(IOSession session) {
/* 199 */     return (DefaultNHttpServerConnection)this.connectionFactory.createConnection(session);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NHttpConnectionFactory<? extends DefaultNHttpServerConnection> getConnectionFactory() {
/* 209 */     return this.connectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public H getHandler() {
/* 219 */     return this.handler;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onConnected(DefaultNHttpServerConnection conn) {
/*     */     try {
/* 225 */       this.handler.connected((NHttpServerConnection)conn);
/* 226 */     } catch (Exception ex) {
/* 227 */       this.handler.exception((NHttpServerConnection)conn, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClosed(DefaultNHttpServerConnection conn) {
/* 233 */     this.handler.closed((NHttpServerConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onException(DefaultNHttpServerConnection conn, IOException ex) {
/* 238 */     this.handler.exception((NHttpServerConnection)conn, ex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onInputReady(DefaultNHttpServerConnection conn) {
/* 243 */     conn.consumeInput((NHttpServerEventHandler)this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onOutputReady(DefaultNHttpServerConnection conn) {
/* 248 */     conn.produceOutput((NHttpServerEventHandler)this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTimeout(DefaultNHttpServerConnection conn) {
/*     */     try {
/* 254 */       this.handler.timeout((NHttpServerConnection)conn);
/* 255 */     } catch (Exception ex) {
/* 256 */       this.handler.exception((NHttpServerConnection)conn, ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultHttpServerIODispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */