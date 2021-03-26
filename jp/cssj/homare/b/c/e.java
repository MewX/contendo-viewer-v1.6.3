/*    */ package jp.cssj.homare.b.c;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ 
/*    */ public class e
/*    */   implements f {
/*    */   protected final double a;
/*    */   protected final double b;
/*    */   protected final b c;
/*    */   
/*    */   public e(double width, double height, b color) {
/* 15 */     this.a = width;
/* 16 */     this.b = height;
/* 17 */     this.c = color;
/*    */   }
/*    */   
/*    */   public void b(b gc, double x, double y) throws c {
/* 21 */     gc.d();
/* 22 */     gc.a(this.c);
/* 23 */     gc.c(new Rectangle2D.Double(x, y, this.a, this.b));
/* 24 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */