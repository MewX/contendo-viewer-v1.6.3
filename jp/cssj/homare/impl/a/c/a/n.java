/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.aa;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.r;
/*    */ import jp.cssj.homare.impl.a.c.u;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class n
/*    */   extends b
/*    */ {
/* 23 */   public static final j a = (j)new n();
/*    */   
/*    */   public static b c(c style) {
/* 26 */     ad value = style.a(a);
/* 27 */     if (value.a() == 1009) {
/* 28 */       return null;
/*    */     }
/* 30 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected n() {
/* 34 */     super("-cssj-column-rule-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 38 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 46 */     if (value == r.a) {
/* 47 */       value = style.a(u.a);
/*    */     }
/* 49 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     if (c.a(lu)) {
/* 54 */       return (ad)aa.a;
/*    */     }
/* 56 */     jp.cssj.homare.css.f.n n1 = c.a(ua, lu);
/* 57 */     if (n1 == null) {
/* 58 */       throw new l();
/*    */     }
/* 60 */     return (ad)n1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */