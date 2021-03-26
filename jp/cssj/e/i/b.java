/*    */ package jp.cssj.e.i;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.net.URL;
/*    */ import jp.cssj.e.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements c
/*    */ {
/*    */   public jp.cssj.e.b b(URI uri) throws IOException {
/*    */     try {
/*    */       a source;
/* 20 */       URL url = uri.toURL();
/*    */       
/*    */       try {
/* 23 */         source = new a(url, null, null);
/* 24 */       } catch (URISyntaxException e) {
/* 25 */         IOException ioe = new IOException(e.getMessage());
/* 26 */         ioe.initCause(e);
/* 27 */         throw ioe;
/*    */       } 
/* 29 */       return (jp.cssj.e.b)source;
/* 30 */     } catch (IllegalArgumentException e) {
/* 31 */       IOException ioe = new IOException(e.getMessage());
/* 32 */       ioe.initCause(e);
/* 33 */       throw ioe;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.e.b source) {
/* 38 */     ((a)source).close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/i/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */