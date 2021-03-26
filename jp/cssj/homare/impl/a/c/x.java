/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.d;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.y;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class x
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new x();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     return ((y)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected x() {
/* 27 */     super("font-style");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)y.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 43 */     y fontStyle = d.a(lu);
/* 44 */     if (fontStyle == null) {
/* 45 */       throw new l();
/*    */     }
/* 47 */     return (ad)fontStyle;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */