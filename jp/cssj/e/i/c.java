/*    */ package jp.cssj.e.i;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import jp.cssj.e.d;
/*    */ 
/*    */ 
/*    */ class c
/*    */   implements d
/*    */ {
/*    */   private static final long d = 0L;
/*    */   private final long e;
/*    */   private final URL f;
/*    */   
/*    */   public c(long timestamp, URL url) {
/* 17 */     this.e = timestamp;
/* 18 */     this.f = url;
/*    */   }
/*    */   
/*    */   public int a() {
/* 22 */     return a(this.f);
/*    */   }
/*    */   
/*    */   public int a(d validity) {
/* 26 */     return a(((c)validity).f);
/*    */   }
/*    */   
/*    */   private int a(URL url) {
/*    */     try {
/* 31 */       URLConnection conn = url.openConnection();
/* 32 */       if (conn.getLastModified() != this.e) {
/* 33 */         return -1;
/*    */       }
/* 35 */       return 1;
/* 36 */     } catch (IOException e) {
/* 37 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/i/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */