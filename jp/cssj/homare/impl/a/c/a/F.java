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
/*    */ public class F
/*    */   extends b
/*    */ {
/* 22 */   public static final j a = (j)new F();
/*    */   
/*    */   public static double c(c style) {
/* 25 */     return ((a)style.a(a)).c();
/*    */   }
/*    */   
/*    */   protected F() {
/* 29 */     super("-cssj-text-stroke-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 33 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 41 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 45 */     E value = a.a(ua, lu);
/* 46 */     if (value == null) {
/* 47 */       throw new l();
/*    */     }
/* 49 */     return (ad)value;
/*    */   }
/*    */   
/*    */   public int a() {
/* 53 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */