/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.impl.a.c.I;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class u
/*    */   implements E
/*    */ {
/* 11 */   private static final u a = new u(0.0D);
/*    */   
/*    */   private final double i;
/*    */   
/*    */   public static u a(double value) {
/* 16 */     if (value == 0.0D) {
/* 17 */       return a;
/*    */     }
/* 19 */     return new u(value);
/*    */   }
/*    */   
/*    */   private u(double value) {
/* 23 */     this.i = value;
/*    */   }
/*    */   
/*    */   public a a(c style) {
/* 27 */     double fontSize = I.c(style);
/* 28 */     return a.a(style.b(), fontSize * this.i);
/*    */   }
/*    */   
/*    */   public short a() {
/* 32 */     return 1001;
/*    */   }
/*    */   
/*    */   public short b() {
/* 36 */     return 15;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 40 */     return (this.i < 0.0D);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 44 */     return (this.i == 0.0D);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return this.i + "em";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */