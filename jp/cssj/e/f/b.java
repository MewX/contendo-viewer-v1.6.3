/*    */ package jp.cssj.e.f;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.e.c;
/*    */ import org.apache.http.conn.HttpClientConnectionManager;
/*    */ import org.apache.http.impl.client.CloseableHttpClient;
/*    */ import org.apache.http.impl.client.HttpClientBuilder;
/*    */ import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements c
/*    */ {
/*    */   protected CloseableHttpClient a() {
/* 21 */     PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
/* 22 */     CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager)cm).build();
/* 23 */     return client;
/*    */   }
/*    */   
/*    */   public jp.cssj.e.b b(URI uri) throws IOException {
/* 27 */     CloseableHttpClient client = a();
/* 28 */     a source = new a(uri, client);
/* 29 */     return (jp.cssj.e.b)source;
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.e.b source) {
/* 33 */     ((a)source).close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/f/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */