/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.g;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.c.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   implements b
/*    */ {
/*    */   protected static final double a = 40.0D;
/*    */   protected static final double b = 40.0D;
/*    */   protected final String c;
/*    */   protected final m d;
/*    */   
/*    */   public a(m ua, String alt) {
/* 25 */     this.d = ua;
/* 26 */     this.c = alt;
/*    */   }
/*    */   
/*    */   public double a() {
/* 30 */     return 40.0D;
/*    */   }
/*    */   
/*    */   public double b() {
/* 34 */     return 40.0D;
/*    */   }
/*    */   
/*    */   public String c() {
/* 38 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 42 */     GeneralPath path = new GeneralPath();
/* 43 */     gc.d();
/*    */     
/* 45 */     float x = 1.0F;
/* 46 */     float y = 1.0F;
/* 47 */     float w = 38.0F;
/* 48 */     float h = 38.0F;
/*    */     
/* 50 */     gc.a(2.0D);
/* 51 */     gc.a(b.t);
/* 52 */     gc.a(c.b);
/* 53 */     path.moveTo(x + w, y);
/* 54 */     path.lineTo(x, y);
/* 55 */     path.lineTo(x, y + h);
/* 56 */     gc.c(path);
/*    */     
/* 58 */     gc.a(c.a(0.82F));
/* 59 */     path.reset();
/* 60 */     path.moveTo(x, y + h);
/* 61 */     path.lineTo(x + w, y + h);
/* 62 */     path.lineTo(x + w, y);
/* 63 */     gc.c(path);
/*    */     
/* 65 */     gc.a(3.0D);
/* 66 */     gc.a(b.t);
/* 67 */     gc.a(c.l);
/*    */     
/* 69 */     path.reset();
/* 70 */     path.moveTo(x + 5.0F, y + 5.0F);
/* 71 */     path.lineTo(x + 15.0F, y + 15.0F);
/* 72 */     gc.c(path);
/*    */     
/* 74 */     path.reset();
/* 75 */     path.moveTo(x + 15.0F, y + 5.0F);
/* 76 */     path.lineTo(x + 5.0F, y + 15.0F);
/* 77 */     gc.c(path);
/*    */     
/* 79 */     if (this.c != null) {
/* 80 */       e.a(gc, (g)this.d.j(), 5.0D, this.c, 3.0D, 3.0D, 34.0D);
/*    */     }
/*    */     
/* 83 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */