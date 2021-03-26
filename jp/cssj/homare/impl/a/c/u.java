/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class u
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new u();
/*    */   
/*    */   public static b c(c style) {
/* 24 */     ad value = style.a(a);
/* 25 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected u() {
/* 29 */     super("color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 33 */     return (ad)style.b().g();
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 41 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 45 */     n n = c.a(ua, lu);
/* 46 */     if (n != null) {
/* 47 */       return (ad)n;
/*    */     }
/* 49 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */