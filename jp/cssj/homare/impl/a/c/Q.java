/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.e;
/*    */ import jp.cssj.homare.css.f.F;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Q
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new Q();
/*    */   
/*    */   public static short c(c style) {
/* 23 */     F value = (F)style.a(a);
/* 24 */     return (short)value.b();
/*    */   }
/*    */   
/*    */   protected Q() {
/* 28 */     super("list-style-position");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 32 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 36 */     return (ad)F.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 44 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/*    */       case 36:
/*    */         break;
/*    */       default:
/* 49 */         throw new l();
/*    */     } 
/* 51 */     F f = e.b(lu.getStringValue());
/* 52 */     if (f == null) {
/* 53 */       throw new l();
/*    */     }
/* 55 */     return (ad)f;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/Q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */