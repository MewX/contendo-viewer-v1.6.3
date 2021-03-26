/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.D;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class t
/*    */   extends d
/*    */ {
/* 20 */   public static final o a = (o)new t();
/*    */   
/*    */   protected t() {
/* 23 */     super("-cssj-columns");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/* 27 */     LexicalUnit lu2 = lu.getNextLexicalUnit();
/* 28 */     if (lu2 != null && lu2.getNextLexicalUnit() != null) {
/* 29 */       throw new l();
/*    */     }
/* 31 */     if (l.a(lu)) {
/* 32 */       if (lu2 == null || l.a(lu2)) {
/* 33 */         primitives.a(s.a, (ad)d.a);
/* 34 */         primitives.a(k.a, (ad)D.a(1));
/*    */         return;
/*    */       } 
/* 37 */       if (lu2.getLexicalUnitType() == 13) {
/* 38 */         primitives.a(s.a, (ad)d.a);
/* 39 */         primitives.a(k.a, (ad)D.a(lu2.getIntegerValue()));
/*    */         return;
/*    */       } 
/* 42 */       E e = l.a(ua, lu2);
/* 43 */       if (e != null) {
/* 44 */         primitives.a(s.a, (ad)e);
/* 45 */         primitives.a(k.a, (ad)D.a(1));
/*    */         return;
/*    */       } 
/* 48 */       throw new l();
/*    */     } 
/* 50 */     if (lu.getLexicalUnitType() == 13) {
/* 51 */       if (lu2 == null || l.a(lu2)) {
/* 52 */         primitives.a(s.a, (ad)d.a);
/* 53 */         primitives.a(k.a, (ad)D.a(lu.getIntegerValue()));
/*    */         return;
/*    */       } 
/* 56 */       E e = l.a(ua, lu2);
/* 57 */       if (e != null) {
/* 58 */         primitives.a(s.a, (ad)e);
/* 59 */         primitives.a(k.a, (ad)D.a(lu.getIntegerValue()));
/*    */         return;
/*    */       } 
/* 62 */       throw new l();
/*    */     } 
/* 64 */     E value = l.a(ua, lu);
/* 65 */     if (value == null) {
/* 66 */       throw new l();
/*    */     }
/* 68 */     if (lu2 == null || l.a(lu2)) {
/* 69 */       primitives.a(s.a, (ad)value);
/* 70 */       primitives.a(k.a, (ad)D.a(1));
/*    */       return;
/*    */     } 
/* 73 */     if (lu2.getLexicalUnitType() == 13) {
/* 74 */       primitives.a(s.a, (ad)value);
/* 75 */       primitives.a(k.a, (ad)D.a(lu2.getIntegerValue()));
/*    */       return;
/*    */     } 
/* 78 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/t.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */