/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements b
/*    */ {
/*    */   private final boolean a;
/*    */   private final boolean b;
/*    */   
/*    */   public f(boolean checked, boolean disabled) {
/* 19 */     this.a = checked;
/* 20 */     this.b = disabled;
/*    */   }
/*    */   
/*    */   public double a() {
/* 24 */     return 12.0D;
/*    */   }
/*    */   
/*    */   public double b() {
/* 28 */     return 12.0D;
/*    */   }
/*    */   
/*    */   public String c() {
/* 32 */     return "○";
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 36 */     gc.d();
/*    */     
/* 38 */     Shape frame = new Ellipse2D.Double(2.0D, 2.0D, 8.0D, 8.0D);
/* 39 */     Shape check = new Ellipse2D.Double(4.0D, 4.0D, 4.0D, 4.0D);
/*    */     
/* 41 */     gc.b(this.b ? c.aD : c.o);
/* 42 */     gc.a(this.b ? c.ad : c.b);
/* 43 */     gc.a(1.0D);
/* 44 */     gc.a(b.t);
/* 45 */     gc.d(frame);
/*    */     
/* 47 */     if (this.a) {
/* 48 */       gc.b(this.b ? c.ad : c.b);
/* 49 */       gc.b(check);
/*    */     } 
/*    */     
/* 52 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */