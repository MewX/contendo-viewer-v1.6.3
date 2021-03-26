/*    */ package jp.cssj.e.j;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.util.zip.ZipFile;
/*    */ import jp.cssj.e.c;
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
/*    */   protected final ZipFile a;
/*    */   
/*    */   public b(ZipFile zip) {
/* 20 */     this.a = zip;
/*    */   }
/*    */   
/*    */   public jp.cssj.e.b b(URI uri) throws IOException {
/* 24 */     a source = new a(this.a, uri);
/* 25 */     return (jp.cssj.e.b)source;
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.e.b source) {
/* 29 */     ((a)source).close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/j/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */