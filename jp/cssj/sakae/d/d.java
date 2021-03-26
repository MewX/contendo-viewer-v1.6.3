/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Paint;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.sakae.b.a.a;
/*    */ import jp.cssj.sakae.b.c.a;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.c.c.e;
/*    */ import jp.cssj.sakae.c.c.f;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.apache.batik.gvt.PatternPaint;
/*    */ 
/*    */ public class d extends a {
/*    */   public d(b gc) throws c {
/* 19 */     super(gc, e.a);
/*    */   }
/*    */   public void setPaint(Paint paint) {
/*    */     e spaint;
/* 23 */     if (paint == null) {
/*    */       return;
/*    */     }
/* 26 */     this.g = paint;
/* 27 */     if (paint instanceof Color) {
/* 28 */       this.h = (Color)paint;
/*    */     }
/*    */     
/* 31 */     if (paint instanceof PatternPaint) {
/* 32 */       PatternPaint pattern = (PatternPaint)paint;
/* 33 */       GraphicsNode node = pattern.getGraphicsNode();
/*    */       
/* 35 */       Rectangle2D rect = pattern.getPatternRect();
/* 36 */       AffineTransform nat = node.getTransform();
/* 37 */       nat.translate(-rect.getX(), -rect.getY());
/* 38 */       node.setTransform(nat);
/* 39 */       b image = new g(node, rect.getWidth(), rect.getHeight());
/*    */       
/* 41 */       AffineTransform at = new AffineTransform(pattern.getPatternTransform());
/* 42 */       at.translate(rect.getX(), rect.getY());
/* 43 */       f f = new f(image, at);
/*    */     } else {
/* 45 */       spaint = a.a(paint);
/*    */     } 
/* 47 */     if (spaint != null) {
/* 48 */       this.a.a(spaint);
/* 49 */       this.a.b(spaint);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */