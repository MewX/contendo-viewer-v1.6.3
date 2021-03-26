/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.protocol.UriPatternMatcher;
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
/*     */ @Contract(threading = ThreadingBehavior.SAFE)
/*     */ public class UriHttpAsyncRequestHandlerMapper
/*     */   implements HttpAsyncRequestHandlerMapper
/*     */ {
/*     */   private final UriPatternMatcher<HttpAsyncRequestHandler<?>> matcher;
/*     */   
/*     */   protected UriHttpAsyncRequestHandlerMapper(UriPatternMatcher<HttpAsyncRequestHandler<?>> matcher) {
/*  59 */     this.matcher = (UriPatternMatcher<HttpAsyncRequestHandler<?>>)Args.notNull(matcher, "Pattern matcher");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UriPatternMatcher<HttpAsyncRequestHandler<?>> getUriPatternMatcher() {
/*  69 */     return this.matcher;
/*     */   }
/*     */   
/*     */   public UriHttpAsyncRequestHandlerMapper() {
/*  73 */     this(new UriPatternMatcher());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(String pattern, HttpAsyncRequestHandler<?> handler) {
/*  84 */     this.matcher.register(pattern, handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister(String pattern) {
/*  93 */     this.matcher.unregister(pattern);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getRequestPath(HttpRequest request) {
/* 100 */     String uriPath = request.getRequestLine().getUri();
/* 101 */     int index = uriPath.indexOf('?');
/* 102 */     if (index != -1) {
/* 103 */       uriPath = uriPath.substring(0, index);
/*     */     } else {
/* 105 */       index = uriPath.indexOf('#');
/* 106 */       if (index != -1) {
/* 107 */         uriPath = uriPath.substring(0, index);
/*     */       }
/*     */     } 
/* 110 */     return uriPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return getClass().getName() + " [matcher=" + this.matcher + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpAsyncRequestHandler<?> lookup(HttpRequest request) {
/* 126 */     return (HttpAsyncRequestHandler)this.matcher.lookup(getRequestPath(request));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/UriHttpAsyncRequestHandlerMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */