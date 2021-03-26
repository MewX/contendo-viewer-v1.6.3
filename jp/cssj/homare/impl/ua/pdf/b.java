/*    */ package jp.cssj.homare.impl.ua.pdf;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.a;
/*    */ import jp.cssj.homare.impl.ua.b;
/*    */ import jp.cssj.homare.ua.a.B;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.pdf.b.a;
/*    */ import jp.cssj.sakae.pdf.d.a;
/*    */ import jp.cssj.sakae.pdf.i;
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends b
/*    */ {
/*    */   private a b;
/*    */   
/*    */   protected b(m ua) {
/* 24 */     super(ua);
/* 25 */     boolean links = B.as.a(this.a);
/* 26 */     if (links && B.ae.a(this.a) == 8) {
/* 27 */       this.a.a((short)10258, B.as.a, 
/* 28 */           String.valueOf(true), "PDF/X-1a");
/* 29 */       links = false;
/*    */     } 
/* 31 */     c(links);
/* 32 */     b(B.av.a(this.a));
/* 33 */     a(B.ar.a(this.a));
/*    */   }
/*    */   
/*    */   protected void a(Shape s, URI uri, a ce) {
/* 37 */     i pdfOut = (i)this.b.b();
/* 38 */     AffineTransform at = this.b.o();
/* 39 */     if (at != null) {
/* 40 */       s = at.createTransformedShape(s);
/*    */     }
/*    */     
/* 43 */     jp.cssj.sakae.pdf.b.b link = new jp.cssj.sakae.pdf.b.b();
/* 44 */     link.a(s);
/* 45 */     link.a(uri);
/*    */     try {
/* 47 */       pdfOut.a((a)link);
/* 48 */     } catch (IOException e) {
/* 49 */       throw new c(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void a(String id, Point2D location) {
/* 54 */     i pdfOut = (i)this.b.b();
/* 55 */     AffineTransform at = this.b.o();
/* 56 */     if (at != null) {
/* 57 */       at.transform(location, location);
/*    */     }
/*    */     try {
/* 60 */       pdfOut.a(id, location);
/* 61 */     } catch (IOException e) {
/* 62 */       throw new c(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void b(String title, Point2D location) {
/* 67 */     i pdfOut = (i)this.b.b();
/* 68 */     AffineTransform at = this.b.o();
/* 69 */     if (at != null) {
/* 70 */       at.transform(location, location);
/*    */     }
/*    */     try {
/* 73 */       pdfOut.b(title, location);
/* 74 */     } catch (IOException e) {
/* 75 */       throw new c(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void a() {
/* 80 */     i pdfOut = (i)this.b.b();
/*    */     try {
/* 82 */       pdfOut.c();
/* 83 */     } catch (IOException e) {
/* 84 */       throw new c(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(a gc) {
/* 89 */     f();
/* 90 */     this.b = gc;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/pdf/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */