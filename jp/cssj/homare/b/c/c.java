/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.b.n;
/*    */ import jp.cssj.homare.b.a.c.A;
/*    */ import jp.cssj.homare.b.a.c.g;
/*    */ import jp.cssj.sakae.c.b;
/*    */ 
/*    */ public class c
/*    */   extends b
/*    */ {
/*    */   protected final g c;
/*    */   protected final A d;
/*    */   protected final double e;
/*    */   protected final double f;
/*    */   
/*    */   public c(n pageBox, Shape clip, float opacity, AffineTransform transform, g background, A border, double width, double height) {
/* 19 */     super(pageBox, clip, opacity, transform);
/* 20 */     this.c = background;
/* 21 */     this.d = border;
/* 22 */     this.e = width;
/* 23 */     this.f = height;
/*    */   }
/*    */   
/*    */   public void a(b gc, double x, double y) throws jp.cssj.sakae.c.c {
/* 27 */     double pbLeft = (this.d.d()).m;
/* 28 */     double pbTop = (this.d.a()).m;
/* 29 */     double pbRight = (this.d.b()).m;
/* 30 */     double pbBottom = (this.d.c()).m;
/* 31 */     this.c.a(gc, x, y, this.e, this.f, pbLeft, pbTop, pbRight, pbBottom, this.d);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */