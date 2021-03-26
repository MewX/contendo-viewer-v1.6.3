/*    */ package jp.cssj.sakae.pdf.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.pdf.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends a
/*    */ {
/*    */   protected final String b;
/*    */   
/*    */   public b(String script) {
/* 18 */     this.b = script;
/*    */   }
/*    */   
/*    */   public String b() {
/* 22 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(h out) throws IOException {
/* 26 */     super.a(out);
/* 27 */     if (this.a.h() < 1300) {
/* 28 */       throw new UnsupportedOperationException("JavaScript Actionは PDF 1.3 以降で使用できます。");
/*    */     }
/* 30 */     out.a("S");
/* 31 */     out.a("JavaScript");
/* 32 */     out.k();
/*    */     
/* 34 */     out.a("JS");
/* 35 */     out.e(this.b);
/* 36 */     out.k();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 40 */     return "JavaScript: " + this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */