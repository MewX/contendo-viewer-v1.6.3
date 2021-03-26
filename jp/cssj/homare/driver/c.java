/*     */ package jp.cssj.homare.driver;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.f.b;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpRequestInterceptor;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.conn.HttpClientConnectionManager;
/*     */ import org.apache.http.impl.client.BasicCookieStore;
/*     */ import org.apache.http.impl.client.BasicCredentialsProvider;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClientBuilder;
/*     */ import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/*     */ import org.apache.http.message.BasicHeader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class c
/*     */   extends b
/*     */ {
/* 257 */   protected RequestConfig.Builder a = RequestConfig.custom();
/* 258 */   protected CredentialsProvider b = (CredentialsProvider)new BasicCredentialsProvider();
/* 259 */   protected CookieStore c = (CookieStore)new BasicCookieStore();
/* 260 */   protected HttpRequestInterceptor d = null;
/* 261 */   protected URI e = null;
/*     */   
/* 263 */   protected List<Header> f = null;
/*     */   
/*     */   public void a(URI refURI) {
/* 266 */     this.e = refURI;
/*     */   }
/*     */   
/*     */   public void a(String name, String value) {
/* 270 */     if (this.f == null) {
/* 271 */       this.f = new ArrayList<>();
/*     */     }
/* 273 */     this.f.add(new BasicHeader(name, value));
/*     */   }
/*     */   
/*     */   protected CloseableHttpClient a() {
/* 277 */     HttpClientBuilder builder = HttpClientBuilder.create();
/* 278 */     PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
/* 279 */     builder.setConnectionManager((HttpClientConnectionManager)cm);
/* 280 */     builder.setDefaultRequestConfig(this.a.build());
/* 281 */     builder.setDefaultCredentialsProvider(this.b);
/* 282 */     builder.setDefaultCookieStore(this.c);
/* 283 */     if (this.d != null) {
/* 284 */       builder.addInterceptorFirst(this.d);
/*     */     }
/* 286 */     CloseableHttpClient client = builder.build();
/* 287 */     return client;
/*     */   }
/*     */   
/*     */   public b b(URI uri) throws IOException {
/* 291 */     CloseableHttpClient client = a();
/* 292 */     a source = new a(this, uri, client);
/* 293 */     return (b)source;
/*     */   }
/*     */   
/*     */   class a extends jp.cssj.e.f.a {
/*     */     public a(c this$0, URI uri, CloseableHttpClient httpClient) {
/* 298 */       super(uri, httpClient);
/*     */     }
/*     */     
/*     */     protected HttpUriRequest e() {
/* 302 */       HttpUriRequest req = super.e();
/* 303 */       if (this.a.e != null && !this.a.e.equals(this.b)) {
/* 304 */         req.addHeader("Referer", this.a.e.toASCIIString());
/*     */       }
/* 306 */       if (this.a.f != null) {
/* 307 */         for (int i = 0; i < this.a.f.size(); i++) {
/* 308 */           Header header = this.a.f.get(i);
/* 309 */           req.addHeader(header);
/*     */         } 
/*     */       }
/* 312 */       return req;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */