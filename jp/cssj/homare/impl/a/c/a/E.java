/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.aa;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class E
/*    */   extends d
/*    */ {
/* 21 */   public static final o a = (o)new E();
/*    */   
/*    */   protected E() {
/* 24 */     super("-cssj-text-stroke");
/*    */   } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     jp.cssj.homare.css.f.E e;
/*    */     n n;
/* 28 */     if (lu.getLexicalUnitType() == 12) {
/* 29 */       primitives.a(F.a, (ad)C.a);
/* 30 */       primitives.a(D.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     ad width = null;
/* 35 */     ad color = null;
/* 36 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 37 */       if (width == null) {
/* 38 */         e = a.a(ua, lu);
/* 39 */         if (e != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 43 */       if (color == null) {
/* 44 */         if (c.a(lu)) {
/* 45 */           aa aa = aa.a;
/*    */         } else {
/* 47 */           n = c.a(ua, lu);
/*    */         } 
/* 49 */         if (n != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 53 */       throw new l();
/*    */     } 
/*    */     
/* 56 */     primitives.a(F.a, (ad)e);
/* 57 */     primitives.a(D.a, (ad)n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/E.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */