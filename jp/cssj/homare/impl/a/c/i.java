/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.h;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new i();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     h value = (h)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   protected i() {
/* 28 */     super("border-collapse");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 32 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)h.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 44 */     ad value = jp.cssj.homare.css.e.i.c(lu);
/* 45 */     if (value == null) {
/* 46 */       throw new l();
/*    */     }
/* 48 */     return value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */