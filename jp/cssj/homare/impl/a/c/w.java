/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.d;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.x;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.c;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class w
/*    */   extends b
/*    */ {
/* 20 */   public static final b a = new w();
/*    */   
/*    */   public static c c(c style) {
/* 23 */     return (c)style.a((j)a);
/*    */   }
/*    */   
/*    */   protected w() {
/* 27 */     super("font-family");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)style.b().i();
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
/* 43 */     x fontFamily = d.a(ua, lu);
/* 44 */     if (fontFamily == null) {
/* 45 */       throw new l();
/*    */     }
/* 47 */     return (ad)fontFamily;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */