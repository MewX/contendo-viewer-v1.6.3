/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.i;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class p
/*    */   extends b
/*    */ {
/* 19 */   public static final b a = new p();
/*    */   
/*    */   public static short c(c style) {
/* 22 */     i value = (i)style.a((j)a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   protected p() {
/* 27 */     super("-cssj-column-rule-style");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)i.k;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 43 */     i i = a.a(lu);
/* 44 */     if (i == null) {
/* 45 */       throw new l();
/*    */     }
/* 47 */     return (ad)i;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */