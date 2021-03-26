/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ import org.apache.http.nio.entity.BufferingNHttpEntity;
/*     */ import org.apache.http.nio.entity.ConsumingNHttpEntity;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpExpectationVerifier;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.protocol.HttpRequestHandler;
/*     */ import org.apache.http.protocol.HttpRequestHandlerResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class BufferingHttpServiceHandler
/*     */   implements NHttpServiceHandler
/*     */ {
/*     */   private final AsyncNHttpServiceHandler asyncHandler;
/*     */   private HttpRequestHandlerResolver handlerResolver;
/*     */   
/*     */   public BufferingHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, HttpParams params) {
/*  90 */     this.asyncHandler = new AsyncNHttpServiceHandler(httpProcessor, responseFactory, connStrategy, allocator, params);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.asyncHandler.setHandlerResolver(new RequestHandlerResolverAdaptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferingHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, HttpParams params) {
/* 104 */     this(httpProcessor, responseFactory, connStrategy, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEventListener(EventListener eventListener) {
/* 109 */     this.asyncHandler.setEventListener(eventListener);
/*     */   }
/*     */   
/*     */   public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier) {
/* 113 */     this.asyncHandler.setExpectationVerifier(expectationVerifier);
/*     */   }
/*     */   
/*     */   public void setHandlerResolver(HttpRequestHandlerResolver handlerResolver) {
/* 117 */     this.handlerResolver = handlerResolver;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpServerConnection conn) {
/* 122 */     this.asyncHandler.connected(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpServerConnection conn) {
/* 127 */     this.asyncHandler.closed(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestReceived(NHttpServerConnection conn) {
/* 132 */     this.asyncHandler.requestReceived(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpServerConnection conn, ContentDecoder decoder) {
/* 137 */     this.asyncHandler.inputReady(conn, decoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReady(NHttpServerConnection conn) {
/* 142 */     this.asyncHandler.responseReady(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpServerConnection conn, ContentEncoder encoder) {
/* 147 */     this.asyncHandler.outputReady(conn, encoder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, HttpException httpex) {
/* 152 */     this.asyncHandler.exception(conn, httpex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, IOException ioex) {
/* 157 */     this.asyncHandler.exception(conn, ioex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpServerConnection conn) {
/* 162 */     this.asyncHandler.timeout(conn);
/*     */   }
/*     */   
/*     */   class RequestHandlerResolverAdaptor
/*     */     implements NHttpRequestHandlerResolver
/*     */   {
/*     */     public NHttpRequestHandler lookup(String requestURI) {
/* 169 */       HttpRequestHandler handler = BufferingHttpServiceHandler.this.handlerResolver.lookup(requestURI);
/* 170 */       if (handler != null) {
/* 171 */         return new BufferingHttpServiceHandler.RequestHandlerAdaptor(handler);
/*     */       }
/* 173 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class RequestHandlerAdaptor
/*     */     extends SimpleNHttpRequestHandler
/*     */   {
/*     */     private final HttpRequestHandler requestHandler;
/*     */ 
/*     */     
/*     */     public RequestHandlerAdaptor(HttpRequestHandler requestHandler) {
/* 185 */       this.requestHandler = requestHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConsumingNHttpEntity entityRequest(HttpEntityEnclosingRequest request, HttpContext context) throws HttpException, IOException {
/* 192 */       return (ConsumingNHttpEntity)new BufferingNHttpEntity(request.getEntity(), (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
/* 202 */       this.requestHandler.handle(request, response, context);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BufferingHttpServiceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */