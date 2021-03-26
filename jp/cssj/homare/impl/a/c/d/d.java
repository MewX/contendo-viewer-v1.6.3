/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.aa;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.i;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.impl.a.c.j;
/*    */ import jp.cssj.homare.impl.a.c.k;
/*    */ import jp.cssj.homare.impl.a.c.l;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ public class d
/*    */   extends d {
/* 24 */   public static final o a = (o)new d();
/*    */   
/*    */   protected d() {
/* 27 */     super("border-left"); } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     E e;
/*    */     i i;
/*    */     n n;
/* 31 */     if (lu.getLexicalUnitType() == 12) {
/* 32 */       primitives.a(l.a, (ad)C.a);
/* 33 */       primitives.a((j)k.a, (ad)C.a);
/* 34 */       primitives.a(j.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     ad width = null;
/* 39 */     ad styleValue = null;
/* 40 */     ad color = null;
/* 41 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 42 */       if (width == null) {
/* 43 */         e = a.a(ua, lu);
/* 44 */         if (e != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 48 */       if (styleValue == null) {
/* 49 */         i = a.a(lu);
/* 50 */         if (i != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 54 */       if (color == null) {
/* 55 */         if (c.a(lu)) {
/* 56 */           aa aa = aa.a;
/*    */         } else {
/* 58 */           n = c.a(ua, lu);
/*    */         } 
/* 60 */         if (n != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 64 */       throw new l();
/*    */     } 
/*    */     
/* 67 */     primitives.a(l.a, (ad)e);
/* 68 */     primitives.a((j)k.a, (ad)i);
/* 69 */     primitives.a(j.a, (ad)n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */