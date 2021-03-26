/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.aa;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.i;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ public class o
/*    */   extends d
/*    */ {
/* 21 */   public static final jp.cssj.homare.css.c.o a = (jp.cssj.homare.css.c.o)new o();
/*    */   
/*    */   protected o() {
/* 24 */     super("-cssj-column-rule"); } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     E e;
/*    */     i i;
/*    */     n n;
/* 28 */     if (lu.getLexicalUnitType() == 12) {
/* 29 */       primitives.a(q.a, (ad)C.a);
/* 30 */       primitives.a((j)p.a, (ad)C.a);
/* 31 */       primitives.a(n.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     ad width = null;
/* 36 */     ad styleValue = null;
/* 37 */     ad color = null;
/* 38 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 39 */       if (width == null) {
/* 40 */         e = a.a(ua, lu);
/* 41 */         if (e != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 45 */       if (styleValue == null) {
/* 46 */         i = a.a(lu);
/* 47 */         if (i != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 51 */       if (color == null) {
/* 52 */         if (c.a(lu)) {
/* 53 */           aa aa = aa.a;
/*    */         } else {
/* 55 */           n = c.a(ua, lu);
/*    */         } 
/* 57 */         if (n != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 61 */       throw new l();
/*    */     } 
/*    */     
/* 64 */     primitives.a(q.a, (ad)e);
/* 65 */     primitives.a((j)p.a, (ad)i);
/* 66 */     primitives.a(n.a, (ad)n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/o.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */