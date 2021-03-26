/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.impl.a.c.I;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   implements E
/*    */ {
/* 13 */   private static final h a = new h(0.0D);
/*    */   
/*    */   private final double i;
/*    */   
/*    */   public static h a(double value) {
/* 18 */     if (value == 0.0D) {
/* 19 */       return a;
/*    */     }
/* 21 */     return new h(value);
/*    */   }
/*    */   
/*    */   private h(double value) {
/* 25 */     this.i = value;
/*    */   }
/*    */   
/*    */   public a a(c style) {
/* 29 */     double fontSize = I.c(style.d());
/* 30 */     return a.a(style.b(), fontSize * this.i);
/*    */   }
/*    */   
/*    */   public short a() {
/* 34 */     return 3008;
/*    */   }
/*    */   
/*    */   public short b() {
/* 38 */     return 15;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 42 */     return (this.i < 0.0D);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 46 */     return (this.i == 0.0D);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return this.i + "rem";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */