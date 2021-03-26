/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class j
/*    */   implements c
/*    */ {
/* 13 */   public static final j a = new j(new a[0]);
/*    */   private final a[] b;
/*    */   
/*    */   public static class a
/*    */   {
/*    */     public final E a;
/*    */     public final E b;
/*    */     public final n c;
/*    */     
/*    */     public a(E x, E y, n color) {
/* 23 */       this.a = x;
/* 24 */       this.b = y;
/* 25 */       this.c = color;
/*    */     }
/*    */   }
/*    */   
/*    */   public static final j a(a[] shadows) {
/* 30 */     if (shadows == null || shadows.length == 0) {
/* 31 */       return a;
/*    */     }
/* 33 */     return new j(shadows);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected j(a[] shadows) {
/* 39 */     this.b = shadows;
/*    */   }
/*    */   
/*    */   public a[] b() {
/* 43 */     return this.b;
/*    */   }
/*    */   
/*    */   public short a() {
/* 47 */     return 3007;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */