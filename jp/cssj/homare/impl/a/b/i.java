/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.IOException;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.pdf.b.a;
/*    */ import jp.cssj.sakae.pdf.b.c;
/*    */ import jp.cssj.sakae.pdf.d;
/*    */ import jp.cssj.sakae.pdf.d.a;
/*    */ import jp.cssj.sakae.pdf.d.b;
/*    */ import jp.cssj.sakae.pdf.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   extends a
/*    */ {
/*    */   public i(m ua, String alt) {
/* 24 */     super(ua, alt);
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 28 */     if (!(gc instanceof a)) {
/*    */       return;
/*    */     }
/* 31 */     Rectangle2D rect = new Rectangle2D.Double(0.0D, 0.0D, 40.0D, 40.0D);
/* 32 */     AffineTransform at = gc.o();
/* 33 */     if (at != null) {
/* 34 */       rect = at.createTransformedShape(rect).getBounds2D();
/*    */     }
/* 36 */     jp.cssj.sakae.pdf.i out = (jp.cssj.sakae.pdf.i)((a)gc).b();
/* 37 */     c annot = new c(this, at) {
/*    */         public void a(h out, jp.cssj.sakae.pdf.i pageOut) throws IOException {
/* 39 */           super.a(out, pageOut);
/*    */           
/* 41 */           Rectangle2D rect = a().getBounds2D();
/* 42 */           b group = pageOut.d().a(rect.getWidth(), rect.getHeight());
/* 43 */           a gc = new a((d)group);
/* 44 */           if (this.a != null) {
/* 45 */             AffineTransform atd = new AffineTransform();
/* 46 */             atd.scale(this.a.getScaleX(), this.a.getScaleY());
/* 47 */             gc.a(atd);
/*    */           } 
/* 49 */           i.a(this.b, (b)gc);
/* 50 */           group.close();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 57 */           out.a("AP");
/* 58 */           out.g();
/* 59 */           out.a("N");
/* 60 */           out.b(group.n());
/* 61 */           out.h();
/* 62 */           out.m();
/*    */         }
/*    */       };
/* 65 */     annot.a(rect);
/* 66 */     annot.a(this.c);
/*    */     try {
/* 68 */       out.a((a)annot);
/* 69 */     } catch (IOException e) {
/* 70 */       throw new c(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */