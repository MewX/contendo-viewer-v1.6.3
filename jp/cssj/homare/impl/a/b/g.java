/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   implements b
/*    */ {
/*    */   private final boolean a;
/*    */   private final double b;
/*    */   
/*    */   public g(boolean disabled, double height) {
/* 21 */     this.a = disabled;
/* 22 */     this.b = height;
/*    */   }
/*    */   
/*    */   public double a() {
/* 26 */     return 16.0D;
/*    */   }
/*    */   
/*    */   public double b() {
/* 30 */     return this.b;
/*    */   }
/*    */   
/*    */   public String c() {
/* 34 */     return "○";
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 38 */     gc.d();
/*    */     
/* 40 */     gc.a(1.0D);
/* 41 */     gc.a(b.t);
/* 42 */     Shape frame = new Rectangle2D.Double(0.0D, 0.0D, 16.0D, this.b);
/* 43 */     Shape upFrame = new Rectangle2D.Double(0.0D, 0.0D, 16.0D, 16.0D);
/*    */ 
/*    */     
/* 46 */     GeneralPath path = new GeneralPath();
/* 47 */     path.moveTo(8.0F, 4.0F);
/* 48 */     path.lineTo(3.0F, 10.0F);
/* 49 */     path.lineTo(13.0F, 10.0F);
/* 50 */     Shape up = path;
/* 51 */     path = new GeneralPath();
/* 52 */     path.moveTo(16.0F, 0.0F);
/* 53 */     path.lineTo(16.0F, 16.0F);
/* 54 */     path.lineTo(0.0F, 16.0F);
/* 55 */     Shape upShadow = path;
/*    */     
/* 57 */     Shape downFrame = new Rectangle2D.Double(0.0D, this.b - 16.0D, 16.0D, 16.0D);
/*    */ 
/*    */     
/* 60 */     GeneralPath generalPath1 = new GeneralPath();
/* 61 */     generalPath1.moveTo(8.0F, (float)(this.b - 4.0D));
/* 62 */     generalPath1.lineTo(3.0F, (float)(this.b - 10.0D));
/* 63 */     generalPath1.lineTo(13.0F, (float)(this.b - 10.0D));
/* 64 */     Shape down = generalPath1;
/* 65 */     generalPath1 = new GeneralPath();
/* 66 */     generalPath1.moveTo(16.0F, (float)(this.b - 16.0D));
/* 67 */     generalPath1.lineTo(16.0F, (float)this.b);
/* 68 */     generalPath1.lineTo(0.0F, (float)this.b);
/* 69 */     Shape downShadow = generalPath1;
/*    */ 
/*    */     
/* 72 */     gc.b(c.aD);
/* 73 */     gc.b(frame);
/*    */     
/* 75 */     gc.b(upFrame);
/* 76 */     gc.b(this.a ? c.ad : c.b);
/* 77 */     gc.b(up);
/* 78 */     gc.c(upShadow);
/*    */     
/* 80 */     gc.b(c.aD);
/* 81 */     gc.b(downFrame);
/* 82 */     gc.b(this.a ? c.ad : c.b);
/* 83 */     gc.b(down);
/* 84 */     gc.c(downShadow);
/*    */     
/* 86 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */