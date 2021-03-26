/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class S
/*    */   implements O, ad
/*    */ {
/*    */   private final double c;
/* 10 */   public static final S a = new S(0.0D);
/* 11 */   public static final S b = new S(1.0D);
/*    */   
/*    */   public static S a(double real) {
/* 14 */     if (real == 0.0D) {
/* 15 */       return a;
/*    */     }
/* 17 */     if (real == 1.0D) {
/* 18 */       return b;
/*    */     }
/* 20 */     return new S(real);
/*    */   }
/*    */   
/*    */   private S(double real) {
/* 24 */     this.c = real;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 28 */     return (this.c < 0.0D);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 32 */     return (this.c == 0.0D);
/*    */   }
/*    */   
/*    */   public double b() {
/* 36 */     return this.c;
/*    */   }
/*    */   
/*    */   public short a() {
/* 40 */     return 1017;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return String.valueOf(this.c);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/S.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */