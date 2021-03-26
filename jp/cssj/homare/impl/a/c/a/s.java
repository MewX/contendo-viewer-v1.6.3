/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class s
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new s();
/*    */   
/*    */   public static double c(c style) {
/* 29 */     ad value = style.a(a);
/* 30 */     if (value.a() == 1006) {
/* 31 */       return 1.722773839210782E308D;
/*    */     }
/* 33 */     return b.a(value).b();
/*    */   }
/*    */   
/*    */   protected s() {
/* 37 */     super("-cssj-column-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 41 */     return (ad)d.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 49 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     if (l.a(lu)) {
/* 54 */       return (ad)d.a;
/*    */     }
/*    */     
/* 57 */     E value = l.a(ua, lu);
/* 58 */     if (value == null) {
/* 59 */       throw new l();
/*    */     }
/* 61 */     if (value.d()) {
/* 62 */       return null;
/*    */     }
/* 64 */     return (ad)value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/s.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */