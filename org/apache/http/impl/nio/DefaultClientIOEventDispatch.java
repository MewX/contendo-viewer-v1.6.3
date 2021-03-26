/*     */ package org.apache.http.impl.nio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.nio.reactor.AbstractIODispatch;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.NHttpClientIOTarget;
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
/*     */ public class DefaultClientIOEventDispatch
/*     */   extends AbstractIODispatch<NHttpClientIOTarget>
/*     */ {
/*     */   protected final NHttpClientHandler handler;
/*     */   protected final ByteBufferAllocator allocator;
/*     */   protected final HttpParams params;
/*     */   
/*     */   public DefaultClientIOEventDispatch(NHttpClientHandler handler, HttpParams params) {
/*  70 */     Args.notNull(handler, "HTTP client handler");
/*  71 */     Args.notNull(params, "HTTP parameters");
/*  72 */     this.allocator = createByteBufferAllocator();
/*  73 */     this.handler = handler;
/*  74 */     this.params = params;
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
/*  87 */     return (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE;
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
/*     */   protected HttpResponseFactory createHttpResponseFactory() {
/* 101 */     return (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE;
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
/*     */   protected NHttpClientIOTarget createConnection(IOSession session) {
/* 117 */     return new DefaultNHttpClientConnection(session, createHttpResponseFactory(), this.allocator, this.params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onConnected(NHttpClientIOTarget conn) {
/* 126 */     int timeout = HttpConnectionParams.getSoTimeout(this.params);
/* 127 */     conn.setSocketTimeout(timeout);
/*     */     
/* 129 */     Object attachment = conn.getContext().getAttribute("http.session.attachment");
/* 130 */     this.handler.connected((NHttpClientConnection)conn, attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClosed(NHttpClientIOTarget conn) {
/* 135 */     this.handler.closed((NHttpClientConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onException(NHttpClientIOTarget conn, IOException ex) {
/* 140 */     this.handler.exception((NHttpClientConnection)conn, ex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onInputReady(NHttpClientIOTarget conn) {
/* 145 */     conn.consumeInput(this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onOutputReady(NHttpClientIOTarget conn) {
/* 150 */     conn.produceOutput(this.handler);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTimeout(NHttpClientIOTarget conn) {
/* 155 */     this.handler.timeout((NHttpClientConnection)conn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/DefaultClientIOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */