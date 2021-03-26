/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements b
/*    */ {
/*    */   private final boolean a;
/*    */   private final boolean b;
/*    */   
/*    */   public b(boolean checked, boolean disabled) {
/* 20 */     this.a = checked;
/* 21 */     this.b = disabled;
/*    */   }
/*    */   
/*    */   public double a() {
/* 25 */     return 12.0D;
/*    */   }
/*    */   
/*    */   public double b() {
/* 29 */     return 12.0D;
/*    */   }
/*    */   
/*    */   public String c() {
/* 33 */     return "○";
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.sakae.c.b gc) throws c {
/* 37 */     gc.d();
/*    */     
/* 39 */     Shape frame = new Rectangle2D.Double(2.0D, 2.0D, 8.0D, 8.0D);
/* 40 */     GeneralPath path = new GeneralPath();
/* 41 */     path.moveTo(3.0F, 5.0F);
/* 42 */     path.lineTo(6.0F, 8.0F);
/* 43 */     path.lineTo(12.0F, 2.0F);
/* 44 */     Shape check = path;
/*    */     
/* 46 */     gc.b(this.b ? c.aD : c.o);
/* 47 */     gc.a(this.b ? c.ad : c.b);
/* 48 */     gc.a(1.0D);
/* 49 */     gc.a(jp.cssj.sakae.c.b.t);
/* 50 */     gc.d(frame);
/*    */     
/* 52 */     if (this.a) {
/* 53 */       gc.a(2.0D);
/* 54 */       gc.a(jp.cssj.sakae.c.b.t);
/* 55 */       gc.c(check);
/*    */     } 
/*    */     
/* 58 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */