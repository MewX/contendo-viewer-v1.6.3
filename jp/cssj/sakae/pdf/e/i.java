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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class i
/*    */ {
/*    */   private final n a;
/*    */   private final b b;
/*    */   private final j c;
/*    */   private final j d;
/* 24 */   private int e = 0;
/*    */   
/*    */   public i(n pdfWriter, b rootPageRef) throws IOException {
/* 27 */     this.a = pdfWriter;
/* 28 */     this.b = rootPageRef;
/*    */     
/* 30 */     j mainFlow = pdfWriter.k;
/* 31 */     mainFlow.a(rootPageRef);
/*    */     
/* 33 */     mainFlow.g();
/*    */     
/* 35 */     mainFlow.a("Type");
/* 36 */     mainFlow.a("Pages");
/* 37 */     mainFlow.k();
/*    */     
/* 39 */     mainFlow.a("Kids");
/* 40 */     mainFlow.i();
/* 41 */     this.c = mainFlow.c();
/* 42 */     mainFlow.j();
/* 43 */     mainFlow.k();
/*    */     
/* 45 */     mainFlow.a("Count");
/* 46 */     mainFlow.write(32);
/* 47 */     this.d = mainFlow.c();
/* 48 */     mainFlow.k();
/*    */     
/* 50 */     mainFlow.h();
/* 51 */     mainFlow.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public m a(double width, double height) throws IOException {
/* 56 */     this.e++;
/*    */     
/* 58 */     return new m(this.a, this.b, this.c, width, height);
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 62 */     this.d.a(this.e);
/* 63 */     this.d.close();
/* 64 */     this.c.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */