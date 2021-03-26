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
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class q
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new q();
/*    */   
/*    */   public static double c(c style) {
/* 28 */     return ((a)style.a(a)).c();
/*    */   }
/*    */   
/*    */   protected q() {
/* 32 */     super("-cssj-column-rule-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)style.b().c((byte)2);
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 44 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 48 */     E value = a.a(ua, lu);
/* 49 */     if (value == null) {
/* 50 */       throw new l();
/*    */     }
/* 52 */     return (ad)value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */