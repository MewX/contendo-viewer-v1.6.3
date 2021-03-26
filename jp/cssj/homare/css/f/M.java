/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class M
/*    */   implements O, ad
/*    */ {
/*    */   private final double d;
/* 10 */   public static final M a = new M(0.0D);
/*    */   
/* 12 */   public static final M b = new M(50.0D);
/*    */   
/* 14 */   public static final M c = new M(100.0D);
/*    */   
/*    */   public static M a(double percentage) {
/* 17 */     if (percentage == 0.0D) {
/* 18 */       return a;
/*    */     }
/* 20 */     if (percentage == 50.0D) {
/* 21 */       return b;
/*    */     }
/* 23 */     if (percentage == 100.0D) {
/* 24 */       return c;
/*    */     }
/* 26 */     return new M(percentage);
/*    */   }
/*    */   
/*    */   private M(double percentage) {
/* 30 */     this.d = percentage;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 34 */     return (this.d == 0.0D);
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 38 */     return (this.d < 0.0D);
/*    */   }
/*    */   
/*    */   public double b() {
/* 42 */     return this.d;
/*    */   }
/*    */   
/*    */   public double c() {
/* 46 */     return this.d / 100.0D;
/*    */   }
/*    */   
/*    */   public short a() {
/* 50 */     return 23;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return this.d + "%";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/M.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */