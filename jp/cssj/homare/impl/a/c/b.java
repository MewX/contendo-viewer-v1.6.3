/*    */ package jp.cssj.homare.impl.a.c;
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
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new b();
/*    */   
/*    */   public static n c(c style) {
/* 24 */     ad value = style.a(a);
/* 25 */     if (value.a() == 1009) {
/* 26 */       return null;
/*    */     }
/* 28 */     return (n)value;
/*    */   }
/*    */   
/*    */   protected b() {
/* 32 */     super("background-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)aa.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 44 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 48 */     if (c.a(lu)) {
/* 49 */       return (ad)aa.a;
/*    */     }
/* 51 */     n n = c.a(ua, lu);
/* 52 */     if (n == null) {
/* 53 */       throw new l();
/*    */     }
/* 55 */     return (ad)n;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */