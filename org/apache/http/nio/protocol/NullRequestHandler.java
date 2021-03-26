/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.HttpRequest;
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.entity.ContentType;
/*    */ import org.apache.http.nio.entity.NStringEntity;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class NullRequestHandler
/*    */   implements HttpAsyncRequestHandler<Object>
/*    */ {
/* 42 */   public static final NullRequestHandler INSTANCE = new NullRequestHandler();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HttpAsyncRequestConsumer<Object> processRequest(HttpRequest request, HttpContext context) {
/* 51 */     return new NullRequestConsumer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handle(Object obj, HttpAsyncExchange httpexchange, HttpContext context) {
/* 59 */     HttpResponse response = httpexchange.getResponse();
/* 60 */     response.setStatusCode(501);
/* 61 */     httpexchange.submitResponse(new ErrorResponseProducer(response, (HttpEntity)new NStringEntity("Service not implemented", ContentType.TEXT_PLAIN), true));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/NullRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */