/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ import jp.cssj.sakae.c.c.h;
/*    */ 
/*    */ 
/*    */ public class R
/*    */   extends h
/*    */   implements n
/*    */ {
/* 10 */   private static final R a = new R(0.0F, 0.0F, 0.0F);
/* 11 */   private static final R b = new R(255.0F, 255.0F, 255.0F);
/*    */   
/*    */   public static R a(float red, float green, float blue) {
/* 14 */     if (red == 0.0F && green == 0.0F && blue == 0.0F) {
/* 15 */       return a;
/*    */     }
/* 17 */     if (red == 1.0F && green == 1.0F && blue == 1.0F) {
/* 18 */       return b;
/*    */     }
/* 20 */     return new R(red, green, blue);
/*    */   }
/*    */   
/*    */   private R(float red, float green, float blue) {
/* 24 */     super(red, green, blue);
/*    */   }
/*    */   
/*    */   public short a() {
/* 28 */     return 27;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/R.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */