/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.sakae.b.a.a;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import org.apache.batik.gvt.AbstractGraphicsNode;
/*    */ 
/*    */ class d
/*    */   extends AbstractGraphicsNode
/*    */ {
/*    */   protected final b a;
/*    */   
/*    */   public d(b image) {
/* 17 */     this.a = image;
/*    */   }
/*    */   
/*    */   public Shape getOutline() {
/* 21 */     return getPrimitiveBounds();
/*    */   }
/*    */   
/*    */   public Rectangle2D getGeometryBounds() {
/* 25 */     return getPrimitiveBounds();
/*    */   }
/*    */   
/*    */   public Rectangle2D getSensitiveBounds() {
/* 29 */     return getPrimitiveBounds();
/*    */   }
/*    */   
/*    */   public void primitivePaint(Graphics2D g2d) {
/* 33 */     if (g2d instanceof a) {
/* 34 */       b gc = ((a)g2d).a();
/* 35 */       gc.a(this.a);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Rectangle2D getPrimitiveBounds() {
/* 40 */     if (this.a == null) {
/* 41 */       return new Rectangle2D.Double();
/*    */     }
/* 43 */     double imageWidth = this.a.a();
/* 44 */     double imageHeight = this.a.b();
/* 45 */     return new Rectangle2D.Double(0.0D, 0.0D, imageWidth, imageHeight);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */