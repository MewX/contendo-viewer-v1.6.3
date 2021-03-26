/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.d;
/*    */ import jp.cssj.homare.css.f.A;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class K
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new K();
/*    */   
/*    */   public static short c(c style) {
/* 23 */     return ((A)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected K() {
/* 27 */     super("font-weight");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)A.n;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     A fontWeight = (A)value;
/* 40 */     if (fontWeight == A.p) {
/* 41 */       c parentStyle = style.c();
/* 42 */       if (parentStyle == null) {
/* 43 */         parentStyle = style;
/*    */       }
/* 45 */       fontWeight = ((A)parentStyle.a(a)).c();
/* 46 */     } else if (fontWeight == A.q) {
/* 47 */       c parentStyle = style.c();
/* 48 */       if (parentStyle == null) {
/* 49 */         parentStyle = style;
/*    */       }
/* 51 */       fontWeight = ((A)parentStyle.a(a)).d();
/*    */     } 
/* 53 */     return (ad)fontWeight;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 57 */     A fontWeight = d.d(lu);
/* 58 */     if (fontWeight == null) {
/* 59 */       throw new l();
/*    */     }
/* 61 */     return (ad)fontWeight;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/K.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */