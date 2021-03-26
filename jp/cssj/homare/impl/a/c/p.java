/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.a;
/*    */ import jp.cssj.homare.css.c.e;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class p
/*    */   extends a
/*    */ {
/* 23 */   public static final j a = (j)new p();
/* 24 */   public static final j b = (j)new p();
/*    */   
/* 26 */   private static final j[] c = new j[] { a, b };
/*    */   
/*    */   public static double c(c style) {
/* 29 */     a h = (a)style.a(a);
/* 30 */     return h.c();
/*    */   }
/*    */   
/*    */   public static double d(c style) {
/* 34 */     a v = (a)style.a(b);
/* 35 */     return v.c();
/*    */   }
/*    */   
/*    */   protected p() {
/* 39 */     super("border-spacing");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 43 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 47 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   protected j[] a() {
/* 55 */     return c;
/*    */   }
/*    */   protected e.a[] a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     E v;
/* 59 */     if (lu.getLexicalUnitType() == 12) {
/* 60 */       return new e.a[] { new e.a(a, (ad)C.a), new e.a(b, (ad)C.a) };
/*    */     }
/*    */     
/* 63 */     E h = l.a(ua, lu);
/* 64 */     lu = lu.getNextLexicalUnit();
/*    */     
/* 66 */     if (lu == null) {
/* 67 */       v = h;
/*    */     } else {
/* 69 */       v = l.a(ua, lu);
/*    */     } 
/* 71 */     if (h != null && v != null) {
/* 72 */       return new e.a[] { new e.a(a, (ad)h), new e.a(b, (ad)v) };
/*    */     }
/* 74 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */