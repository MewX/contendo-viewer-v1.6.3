/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.u;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new m();
/*    */   
/*    */   public static double c(c style) {
/* 29 */     return ((a)style.a(a)).c();
/*    */   }
/*    */   
/*    */   protected m() {
/* 33 */     super("-cssj-column-gap");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 37 */     return (ad)u.a(1.0D).a(style);
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 45 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, jp.cssj.homare.ua.m ua, URI uri) throws l {
/* 49 */     if (l.c(lu)) {
/* 50 */       return (ad)u.a(1.0D);
/*    */     }
/* 52 */     E value = a.a(ua, lu);
/* 53 */     if (value == null) {
/* 54 */       throw new l();
/*    */     }
/* 56 */     return (ad)value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */