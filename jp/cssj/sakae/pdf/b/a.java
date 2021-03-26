/*    */ package jp.cssj.sakae.pdf.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.pdf.h;
/*    */ import jp.cssj.sakae.pdf.i;
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
/*    */ public abstract class a
/*    */ {
/*    */   protected Shape c;
/*    */   protected String d;
/*    */   
/*    */   public void a(Shape shape) {
/* 28 */     this.c = shape;
/*    */   }
/*    */   
/*    */   public Shape a() {
/* 32 */     return this.c;
/*    */   }
/*    */   
/*    */   public String b() {
/* 36 */     return this.d;
/*    */   }
/*    */   
/*    */   public void a(String contents) {
/* 40 */     this.d = contents;
/*    */   }
/*    */   
/*    */   public void a(h out, i pageOut) throws IOException {
/* 44 */     out.a("Type");
/* 45 */     out.a("Annot");
/* 46 */     out.k();
/*    */ 
/*    */     
/* 49 */     double pageHeight = pageOut.b();
/* 50 */     out.a("Rect");
/* 51 */     out.i();
/* 52 */     Rectangle2D rect = a().getBounds2D();
/* 53 */     double x = rect.getX();
/* 54 */     double y = rect.getY();
/* 55 */     double width = rect.getWidth();
/* 56 */     double height = rect.getHeight();
/* 57 */     out.a(x);
/* 58 */     out.a(pageHeight - y + height);
/* 59 */     out.a(x + width);
/* 60 */     out.a(pageHeight - y);
/* 61 */     out.j();
/* 62 */     out.k();
/*    */ 
/*    */     
/* 65 */     String contents = b();
/* 66 */     if (contents != null) {
/* 67 */       out.a("Contents");
/* 68 */       out.e(contents);
/* 69 */       out.k();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */