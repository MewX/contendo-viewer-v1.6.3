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
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.css.f.r;
/*    */ import jp.cssj.homare.impl.a.c.u;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class B
/*    */   extends b
/*    */ {
/* 23 */   public static final j a = (j)new B();
/*    */   
/*    */   public static b c(c style) {
/* 26 */     ad value = style.a(a);
/* 27 */     if (value.a() == 1009) {
/* 28 */       return null;
/*    */     }
/* 30 */     if (value == r.a) {
/* 31 */       return u.c(style);
/*    */     }
/* 33 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected B() {
/* 37 */     super("-cssj-text-fill-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 41 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 49 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     if (lu.getLexicalUnitType() == 35 && lu.getStringValue().equalsIgnoreCase("currentcolor")) {
/* 54 */       return (ad)r.a;
/*    */     }
/* 56 */     if (c.a(lu)) {
/* 57 */       return (ad)aa.a;
/*    */     }
/* 59 */     n n = c.a(ua, lu);
/* 60 */     if (n == null) {
/* 61 */       throw new l();
/*    */     }
/* 63 */     return (ad)n;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */