/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.g;
/*    */ import jp.cssj.homare.css.f.L;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ag
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new ag();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     L value = (L)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   private ag() {
/* 28 */     super("page-break-after");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 32 */     return (ad)L.n;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 40 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 44 */     L value = g.a(lu);
/* 45 */     if (value != null) {
/* 46 */       return (ad)value;
/*    */     }
/* 48 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */