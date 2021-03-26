/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import jp.cssj.homare.b.a.b.n;
/*    */ import jp.cssj.homare.b.e.b;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ public class a
/*    */   extends b {
/*    */   protected final b b;
/*    */   protected final double c;
/*    */   protected final double d;
/*    */   
/*    */   public a(n pageBox, Shape clip, float opacity, AffineTransform transform, b frame, double width, double height) {
/* 17 */     super(pageBox, clip, opacity, transform);
/* 18 */     this.b = frame;
/* 19 */     this.c = width;
/* 20 */     this.d = height;
/*    */   }
/*    */   
/*    */   public void a(b gc, double x, double y) throws c {
/* 24 */     this.b.a(gc, x, y, this.c, this.d);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */