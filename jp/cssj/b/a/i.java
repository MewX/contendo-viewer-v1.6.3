/*    */ package jp.cssj.b.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.servlet.ServletResponse;
/*    */ import jp.cssj.b.d.c;
/*    */ import jp.cssj.e.a;
/*    */ import jp.cssj.f.a;
/*    */ import jp.cssj.f.a.a;
/*    */ import jp.cssj.f.b.e;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   implements c
/*    */ {
/*    */   protected final ServletResponse a;
/* 21 */   protected a b = null;
/*    */   
/*    */   public i(ServletResponse response) {
/* 24 */     this.a = response;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 28 */     return (this.b == null);
/*    */   }
/*    */   
/*    */   public a a(a metaSource) throws IOException {
/* 32 */     if (this.b != null) {
/* 33 */       throw new IllegalStateException();
/*    */     }
/*    */     
/* 36 */     String mimeType = metaSource.c();
/* 37 */     long length = metaSource.b();
/* 38 */     if (mimeType != null) {
/* 39 */       this.a.setContentType(h.a(metaSource));
/*    */     }
/* 41 */     if (length != -1L) {
/* 42 */       this.a.setContentLength((int)length);
/*    */     }
/* 44 */     e e = new e((OutputStream)this.a.getOutputStream());
/* 45 */     this.b = new a(this, (a)e) {
/*    */         public void a() throws IOException {
/* 47 */           this.a.b();
/* 48 */           super.a();
/*    */         }
/*    */       };
/* 51 */     return (a)this.b;
/*    */   }
/*    */   
/*    */   protected void b() {
/* 55 */     long length = this.b.f();
/* 56 */     this.a.setContentLength((int)length);
/*    */   }
/*    */   
/*    */   public void c() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */