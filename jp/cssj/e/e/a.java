/*    */ package jp.cssj.e.e;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.e.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */   implements Closeable, b
/*    */ {
/*    */   protected final URI b;
/*    */   
/*    */   public a(URI uri) {
/* 19 */     this.b = uri;
/*    */   }
/*    */   
/*    */   public URI d() {
/* 23 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean g() throws IOException {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   public boolean k() throws IOException {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public boolean i() throws IOException {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/e/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */