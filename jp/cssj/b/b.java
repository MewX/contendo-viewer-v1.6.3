/*    */ package jp.cssj.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class b
/*    */ {
/*    */   public static a a(URI uri) {
/* 30 */     a driver = (a)jp.cssj.c.b.a().a(a.class, uri);
/* 31 */     if (driver == null) {
/* 32 */       throw new RuntimeException(uri + " に接続するドライバがありません。");
/*    */     }
/* 34 */     return driver;
/*    */   }
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
/*    */   public static c b(URI uri) throws IOException {
/* 50 */     return a(uri, null);
/*    */   }
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
/*    */   public static c a(URI uri, Map<String, String> props) throws IOException {
/* 66 */     a driver = a(uri);
/* 67 */     return driver.getSession(uri, props);
/*    */   }
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
/*    */   public static c a(URI uri, String user, String password) throws IOException {
/* 84 */     a driver = a(uri);
/* 85 */     Map<String, String> props = null;
/* 86 */     if (user != null || password != null) {
/* 87 */       props = new HashMap<>();
/* 88 */       props.put("user", user);
/* 89 */       props.put("password", password);
/*    */     } 
/* 91 */     return driver.getSession(uri, props);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */