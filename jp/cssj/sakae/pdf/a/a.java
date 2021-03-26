/*    */ package jp.cssj.sakae.pdf.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.pdf.f.b;
/*    */ import jp.cssj.sakae.pdf.h;
/*    */ 
/*    */ public class a
/*    */ {
/*    */   protected b a;
/* 10 */   private a[] b = null;
/*    */   
/*    */   public void a(b params) {
/* 13 */     this.a = params;
/*    */   }
/*    */   
/*    */   public a[] a() {
/* 17 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(a[] next) {
/* 21 */     this.b = next;
/*    */   }
/*    */   
/*    */   public void a(h out) throws IOException {
/* 25 */     out.a("Type");
/* 26 */     out.a("Action");
/* 27 */     out.k();
/*    */     
/* 29 */     if (this.b != null && this.b.length > 0) {
/* 30 */       out.a("Next");
/* 31 */       if (this.b.length == 1) {
/* 32 */         out.g();
/* 33 */         this.b[0].a(this.a);
/* 34 */         this.b[0].a(out);
/* 35 */         out.h();
/*    */       } else {
/* 37 */         out.i();
/* 38 */         for (int i = 0; i < this.b.length; i++) {
/* 39 */           out.g();
/* 40 */           this.b[i].a(this.a);
/* 41 */           this.b[i].a(out);
/* 42 */           out.h();
/*    */         } 
/* 44 */         out.j();
/*    */       } 
/* 46 */       out.k();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */