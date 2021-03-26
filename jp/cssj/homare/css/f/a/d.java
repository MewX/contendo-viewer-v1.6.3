/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   implements E
/*    */ {
/* 15 */   private static final d a = new d(0.0D);
/*    */   
/*    */   private final double i;
/*    */   
/*    */   public static d a(double value) {
/* 20 */     if (value == 0.0D) {
/* 21 */       return a;
/*    */     }
/* 23 */     return new d(value);
/*    */   }
/*    */   
/*    */   private d(double value) {
/* 27 */     this.i = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public a a(c style) {
/* 32 */     m ua = style.b();
/* 33 */     h fontStyle = style.i();
/* 34 */     jp.cssj.sakae.c.a.d flm = ua.q().a(fontStyle);
/* 35 */     double xheight = flm.e();
/* 36 */     return a.a(ua, xheight * this.i);
/*    */   }
/*    */   
/*    */   public short a() {
/* 40 */     return 3009;
/*    */   }
/*    */   
/*    */   public short b() {
/* 44 */     return 15;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 48 */     return (this.i < 0.0D);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 52 */     return (this.i == 0.0D);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     return this.i + "ch";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */