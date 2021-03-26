/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.entity.BufferingNHttpEntity;
/*     */ import org.apache.http.nio.entity.ConsumingNHttpEntity;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class BufferingHttpClientHandler
/*     */   implements NHttpClientHandler
/*     */ {
/*     */   private final AsyncNHttpClientHandler asyncHandler;
/*     */   
/*     */   public BufferingHttpClientHandler(HttpProcessor httpProcessor, HttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, HttpParams params) {
/*  82 */     this.asyncHandler = new AsyncNHttpClientHandler(httpProcessor, new ExecutionHandlerAdaptor(execHandler), connStrategy, allocator, params);
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
/*     */   public BufferingHttpClientHandler(HttpProcessor httpProcessor, HttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, HttpParams params) {
/*  95 */     this(httpProcessor, execHandler, connStrategy, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventListener(EventListener eventListener) {
/* 100 */     this.asyncHandler.setEventListener(eventListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpClientConnection conn, Object attachment) {
/* 105 */     this.asyncHandler.connected(conn, attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpClientConnection conn) {
/* 110 */     this.asyncHandler.closed(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestReady(NHttpClientConnection conn) {
/* 115 */     this.asyncHandler.requestReady(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpClientConnection conn, ContentDecoder decoder) {
/* 120 */     this.asyncHandler.inputReady(conn, decoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpClientConnection conn, ContentEncoder encoder) {
/* 125 */     this.asyncHandler.outputReady(conn, encoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(NHttpClientConnection conn) {
/* 130 */     this.asyncHandler.responseReceived(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, HttpException httpex) {
/* 135 */     this.asyncHandler.exception(conn, httpex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, IOException ioex) {
/* 140 */     this.asyncHandler.exception(conn, ioex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpClientConnection conn) {
/* 145 */     this.asyncHandler.timeout(conn);
/*     */   }
/*     */   
/*     */   static class ExecutionHandlerAdaptor
/*     */     implements NHttpRequestExecutionHandler
/*     */   {
/*     */     private final HttpRequestExecutionHandler execHandler;
/*     */     
/*     */     public ExecutionHandlerAdaptor(HttpRequestExecutionHandler execHandler) {
/* 154 */       this.execHandler = execHandler;
/*     */     }
/*     */ 
/*     */     
/*     */     public void initalizeContext(HttpContext context, Object attachment) {
/* 159 */       this.execHandler.initalizeContext(context, attachment);
/*     */     }
/*     */     
/*     */     public void finalizeContext(HttpContext context) {
/* 163 */       this.execHandler.finalizeContext(context);
/*     */     }
/*     */ 
/*     */     
/*     */     public HttpRequest submitRequest(HttpContext context) {
/* 168 */       return this.execHandler.submitRequest(context);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConsumingNHttpEntity responseEntity(HttpResponse response, HttpContext context) throws IOException {
/* 175 */       return (ConsumingNHttpEntity)new BufferingNHttpEntity(response.getEntity(), (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleResponse(HttpResponse response, HttpContext context) throws IOException {
/* 184 */       this.execHandler.handleResponse(response, context);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BufferingHttpClientHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */