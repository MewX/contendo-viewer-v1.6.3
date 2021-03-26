/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.d;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.b.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.g;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends b
/*    */ {
/* 21 */   public static final j a = (j)new c();
/*    */   
/*    */   public static g c(jp.cssj.homare.css.c style) {
/* 24 */     return (g)style.a(a);
/*    */   }
/*    */   
/*    */   protected c() {
/* 28 */     super("-cssj-font-policy");
/*    */   }
/*    */   
/*    */   public ad b(jp.cssj.homare.css.c style) {
/* 32 */     return (ad)style.b().j();
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, jp.cssj.homare.css.c style) {
/* 40 */     if (style.b().j() == d.e) {
/* 41 */       return (ad)d.e;
/*    */     }
/* 43 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 49 */     d fontPolicy = d.b(lu);
/* 50 */     if (fontPolicy == null) {
/* 51 */       throw new l();
/*    */     }
/* 53 */     return (ad)fontPolicy;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */