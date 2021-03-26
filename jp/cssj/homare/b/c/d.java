/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.b.n;
/*    */ import jp.cssj.homare.b.a.c.g;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ public class d
/*    */   extends b {
/*    */   protected final g a;
/*    */   protected final double b;
/*    */   protected final double c;
/*    */   
/*    */   public d(Shape clip, n pageBox, float opacity, AffineTransform transform, g background, double width, double height) {
/* 17 */     super(pageBox, clip, opacity, transform);
/* 18 */     this.a = background;
/* 19 */     this.b = width;
/* 20 */     this.c = height;
/*    */   }
/*    */   
/*    */   public void a(b gc, double x, double y) throws c {
/* 24 */     this.a.a(gc, x, y, this.b, this.c, 0.0D, 0.0D, 0.0D, 0.0D, null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */