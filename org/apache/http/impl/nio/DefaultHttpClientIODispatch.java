/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.impl.nio.reactor.AbstractIODispatch;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientEventHandler;
/*     */ import org.apache.http.nio.NHttpConnectionFactory;
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
/*     */ public class DefaultHttpClientIODispatch<H extends NHttpClientEventHandler>
/*     */   extends AbstractIODispatch<DefaultNHttpClientConnection>
/*     */ {
/*     */   private final H handler;
/*     */   private final NHttpConnectionFactory<? extends DefaultNHttpClientConnection> connectionFactory;
/*     */   
/*     */   public static <T extends NHttpClientEventHandler> DefaultHttpClientIODispatch<T> create(T handler, SSLContext sslContext, ConnectionConfig config) {
/*  71 */     return (sslContext == null) ? new DefaultHttpClientIODispatch<T>(handler, config) : new DefaultHttpClientIODispatch<T>(handler, sslContext, config);
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
/*     */   public static <T extends NHttpClientEventHandler> DefaultHttpClientIODispatch<T> create(T handler, SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/*  90 */     return (sslContext == null) ? new DefaultHttpClientIODispatch<T>(handler, config) : new DefaultHttpClientIODispatch<T>(handler, sslContext, sslHandler, config);
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
/*     */   public DefaultHttpClientIODispatch(H handler, NHttpConnectionFactory<? extends DefaultNHttpClientConnection> connFactory) {
/* 108 */     this.handler = (H)Args.notNull(handler, "HTTP client handler");
/* 109 */     this.connectionFactory = (NHttpConnectionFactory<? extends DefaultNHttpClientConnection>)Args.notNull(connFactory, "HTTP client connection factory");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DefaultHttpClientIODispatch(H handler, HttpParams params) {
/* 120 */     this(handler, new DefaultNHttpClientConnectionFactory(params));
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
/*     */   public DefaultHttpClientIODispatch(H handler, SSLContext sslContext, SSLSetupHandler sslHandler, HttpParams params) {
/* 133 */     this(handler, new SSLNHttpClientConnectionFactory(sslContext, sslHandler, params));
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
/*     */   public DefaultHttpClientIODispatch(H handler, SSLContext sslContext, HttpParams params) {
/* 145 */     this(handler, sslContext, (SSLSetupHandler)null, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpClientIODispatch(H handler, ConnectionConfig config) {
/* 152 */     this(handler, new DefaultNHttpClientConnectionFactory(config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpClientIODispatch(H handler, SSLContext sslContext, SSLSetupHandler sslHandler, ConnectionConfig config) {
/* 163 */     this(handler, new SSLNHttpClientConnectionFactory(sslContext, sslHandler, config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHttpClientIODispatch(H handler, SSLContext sslContext, ConnectionConfig config) {
/* 173 */     this(handler, new SSLNHttpClientConnectionFactory(sslContext, null, config));
/*     */   }
/*     */ 
/*     */   
/*     */   protected DefaultNHttpClientConnection createConnection(IOSession session) {
/* 178 */     return (DefaultNHttpClientConnection)this.connectionFactory.createConnection(session);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NHttpConnectionFactory<? extends DefaultNHttpClientConnection> getConnectionFactory() {
/* 188 */     return this.connectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public H getHandler() {
/* 198 */     return this.handler;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onConnected(DefaultNHttpClientConnection conn) {
/* 203 */     Object attachment = conn.getContext().getAttribute("http.session.attachment");
/*     */     try {
/* 205 */       this.handler.connected((NHttpClientConnection)conn, attachment);
/* 206 */     } catch (Exception ex) {
/* 207 */       this.handler.exception((NHttpClientConnection)conn, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClosed(DefaultNHttpClientConnection conn) {
/* 213 */     this.handler.closed((NHttpClientConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onException(DefaultNHttpClientConnection conn, IOException ex) {
/* 218 */     this.handler.exception((NHttpClientConnection)conn, ex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onInputReady(DefaultNHttpClientConnection conn) {
/* 223 */     conn.consumeInput((NHttpClientEventHandler)this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onOutputReady(DefaultNHttpClientConnection conn) {
/* 228 */     conn.produceOutput((NHttpClientEventHandler)this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTimeout(DefaultNHttpClientConnection conn) {
/*     */     try {
/* 234 */       this.handler.timeout((NHttpClientConnection)conn);
/* 235 */     } catch (Exception ex) {
/* 236 */       this.handler.exception((NHttpClientConnection)conn, ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultHttpClientIODispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */