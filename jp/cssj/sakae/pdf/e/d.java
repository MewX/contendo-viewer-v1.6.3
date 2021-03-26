/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class d
/*    */ {
/*    */   private final p a;
/*    */   private final j b;
/*    */   private final j c;
/*    */   private boolean d = false;
/*    */   
/*    */   public d(n pdfWriter) throws IOException {
/* 19 */     this.a = pdfWriter.i;
/*    */     
/* 21 */     j mainFlow = pdfWriter.k;
/* 22 */     this.b = mainFlow.c();
/* 23 */     this.c = pdfWriter.l;
/*    */   }
/*    */   
/*    */   public void a(String key, b ref) throws IOException {
/* 27 */     if (!this.d) {
/* 28 */       b nameTreeRef = this.a.a();
/* 29 */       this.c.a("Names");
/* 30 */       this.c.b(nameTreeRef);
/* 31 */       this.c.k();
/*    */       
/* 33 */       this.b.a(nameTreeRef);
/* 34 */       this.b.g();
/* 35 */       this.d = true;
/*    */     } 
/*    */     
/* 38 */     this.b.a(key);
/* 39 */     this.b.b(ref);
/* 40 */     this.b.k();
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 44 */     if (this.d) {
/* 45 */       this.b.h();
/* 46 */       this.b.a();
/*    */     } 
/* 48 */     this.b.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */