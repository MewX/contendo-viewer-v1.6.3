/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.D;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new k();
/*    */   
/*    */   public static int c(c style) {
/* 23 */     D value = (D)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   private k() {
/* 28 */     super("-cssj-column-count");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 32 */     return (ad)D.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 40 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     int a;
/* 44 */     if (l.a(lu)) {
/* 45 */       return (ad)D.a;
/*    */     }
/* 47 */     switch (lu.getLexicalUnitType()) {
/*    */       case 13:
/* 49 */         a = lu.getIntegerValue();
/* 50 */         if (a >= 1) {
/* 51 */           return (ad)D.a(a);
/*    */         }
/*    */         break;
/*    */     } 
/* 55 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */