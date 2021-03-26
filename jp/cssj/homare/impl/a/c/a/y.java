/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
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
/*    */ 
/*    */ public class y
/*    */   extends b
/*    */ {
/* 23 */   public static final j a = (j)new y();
/*    */   
/*    */   public static b c(c style) {
/* 26 */     ad value = style.a(a);
/* 27 */     if (value == r.a) {
/* 28 */       value = style.a(u.a);
/*    */     }
/* 30 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected y() {
/* 34 */     super("-cssj-text-emphasis-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 38 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 46 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 50 */     n n = c.a(ua, lu);
/* 51 */     if (n != null) {
/* 52 */       return (ad)n;
/*    */     }
/* 54 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */