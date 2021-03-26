/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.d;
/*    */ import jp.cssj.sakae.c.a.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class w
/*    */   implements E
/*    */ {
/* 13 */   private static final w a = new w(0.0D);
/*    */   
/*    */   private final double i;
/*    */   
/*    */   public static w a(double value) {
/* 18 */     if (value == 0.0D) {
/* 19 */       return a;
/*    */     }
/* 21 */     return new w(value);
/*    */   }
/*    */   
/*    */   private w(double value) {
/* 25 */     this.i = value;
/*    */   }
/*    */   
/*    */   public a a(c style) {
/* 29 */     m ua = style.b();
/* 30 */     h fontStyle = style.i();
/* 31 */     d flm = ua.q().a(fontStyle);
/* 32 */     double xheight = flm.e();
/* 33 */     return a.a(ua, xheight * this.i);
/*    */   }
/*    */   
/*    */   public short a() {
/* 37 */     return 1002;
/*    */   }
/*    */   
/*    */   public short b() {
/* 41 */     return 15;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 45 */     return (this.i < 0.0D);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 49 */     return (this.i == 0.0D);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     return this.i + "ex";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */