/*    */ package jp.cssj.homare.impl.a.a;
/*    */ 
/*    */ import jp.cssj.homare.css.f.b.a;
/*    */ import jp.cssj.sakae.c.d.a.a.c;
/*    */ 
/*    */ public class b
/*    */   extends c {
/*    */   private final a a;
/*    */   private final a b;
/*    */   
/*    */   public b(a include, a exclude) {
/* 12 */     this.a = include;
/* 13 */     this.b = exclude;
/*    */   }
/*    */   
/*    */   protected jp.cssj.sakae.c.d.a.a.b a(char c1) {
/* 17 */     if (this.a.b().indexOf(c1) != -1) {
/* 18 */       return jp.cssj.sakae.c.d.a.a.b.a;
/*    */     }
/* 20 */     if (this.b.b().indexOf(c1) != -1) {
/* 21 */       return jp.cssj.sakae.c.d.a.a.b.b;
/*    */     }
/* 23 */     return super.a(c1);
/*    */   }
/*    */   
/*    */   protected jp.cssj.sakae.c.d.a.a.b b(char c1) {
/* 27 */     if (this.a.c().indexOf(c1) != -1) {
/* 28 */       return jp.cssj.sakae.c.d.a.a.b.a;
/*    */     }
/* 30 */     if (this.b.c().indexOf(c1) != -1) {
/* 31 */       return jp.cssj.sakae.c.d.a.a.b.b;
/*    */     }
/* 33 */     return super.b(c1);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */