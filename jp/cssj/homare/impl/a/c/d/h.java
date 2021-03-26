/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
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
/*    */ import jp.cssj.homare.impl.a.c.q;
/*    */ import jp.cssj.homare.impl.a.c.r;
/*    */ import jp.cssj.homare.impl.a.c.s;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   extends d
/*    */ {
/* 27 */   public static final o a = (o)new h();
/*    */   
/*    */   protected h() {
/* 30 */     super("border-top"); } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     E e;
/*    */     i i;
/*    */     n n;
/* 34 */     if (lu.getLexicalUnitType() == 12) {
/* 35 */       primitives.a(s.a, (ad)C.a);
/* 36 */       primitives.a(r.a, (ad)C.a);
/* 37 */       primitives.a(q.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     ad width = null;
/* 42 */     ad styleValue = null;
/* 43 */     ad color = null;
/* 44 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 45 */       if (width == null) {
/* 46 */         e = a.a(ua, lu);
/* 47 */         if (e != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 51 */       if (styleValue == null) {
/* 52 */         i = a.a(lu);
/* 53 */         if (i != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 57 */       if (color == null) {
/* 58 */         if (c.a(lu)) {
/* 59 */           aa aa = aa.a;
/*    */         } else {
/* 61 */           n = c.a(ua, lu);
/*    */         } 
/* 63 */         if (n != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 67 */       throw new l();
/*    */     } 
/*    */     
/* 70 */     primitives.a(s.a, (ad)e);
/* 71 */     primitives.a(r.a, (ad)i);
/* 72 */     primitives.a(q.a, (ad)n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */