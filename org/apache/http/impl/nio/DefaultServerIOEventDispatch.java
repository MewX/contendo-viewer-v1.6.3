/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpRequestFactory;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.impl.DefaultHttpRequestFactory;
/*     */ import org.apache.http.impl.nio.reactor.AbstractIODispatch;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServerIOTarget;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ import org.apache.http.nio.reactor.IOSession;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpConnectionParams;
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
/*     */ @Deprecated
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class DefaultServerIOEventDispatch
/*     */   extends AbstractIODispatch<NHttpServerIOTarget>
/*     */ {
/*     */   protected final ByteBufferAllocator allocator;
/*     */   protected final NHttpServiceHandler handler;
/*     */   protected final HttpParams params;
/*     */   
/*     */   public DefaultServerIOEventDispatch(NHttpServiceHandler handler, HttpParams params) {
/*  73 */     Args.notNull(handler, "HTTP service handler");
/*  74 */     Args.notNull(params, "HTTP parameters");
/*  75 */     this.allocator = createByteBufferAllocator();
/*  76 */     this.handler = handler;
/*  77 */     this.params = params;
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
/*     */   protected ByteBufferAllocator createByteBufferAllocator() {
/*  90 */     return (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE;
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
/*     */   protected HttpRequestFactory createHttpRequestFactory() {
/* 104 */     return (HttpRequestFactory)DefaultHttpRequestFactory.INSTANCE;
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
/*     */   protected NHttpServerIOTarget createConnection(IOSession session) {
/* 120 */     return new DefaultNHttpServerConnection(session, createHttpRequestFactory(), this.allocator, this.params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onConnected(NHttpServerIOTarget conn) {
/* 129 */     int timeout = HttpConnectionParams.getSoTimeout(this.params);
/* 130 */     conn.setSocketTimeout(timeout);
/* 131 */     this.handler.connected((NHttpServerConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClosed(NHttpServerIOTarget conn) {
/* 136 */     this.handler.closed((NHttpServerConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onException(NHttpServerIOTarget conn, IOException ex) {
/* 141 */     this.handler.exception((NHttpServerConnection)conn, ex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onInputReady(NHttpServerIOTarget conn) {
/* 146 */     conn.consumeInput(this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onOutputReady(NHttpServerIOTarget conn) {
/* 151 */     conn.produceOutput(this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTimeout(NHttpServerIOTarget conn) {
/* 156 */     this.handler.timeout((NHttpServerConnection)conn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultServerIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */