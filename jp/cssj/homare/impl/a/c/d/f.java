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
/*    */ import jp.cssj.homare.impl.a.c.g;
/*    */ import jp.cssj.homare.impl.a.c.h;
/*    */ import jp.cssj.homare.impl.a.c.j;
/*    */ import jp.cssj.homare.impl.a.c.k;
/*    */ import jp.cssj.homare.impl.a.c.l;
/*    */ import jp.cssj.homare.impl.a.c.m;
/*    */ import jp.cssj.homare.impl.a.c.n;
/*    */ import jp.cssj.homare.impl.a.c.o;
/*    */ import jp.cssj.homare.impl.a.c.q;
/*    */ import jp.cssj.homare.impl.a.c.r;
/*    */ import jp.cssj.homare.impl.a.c.s;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ public class f
/*    */   extends d
/*    */ {
/* 33 */   public static final o a = (o)new f();
/*    */   
/*    */   protected f() {
/* 36 */     super("border"); } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     E e;
/*    */     i i;
/*    */     n n;
/* 40 */     if (lu.getLexicalUnitType() == 12) {
/* 41 */       primitives.a(l.a, (ad)C.a);
/* 42 */       primitives.a((j)k.a, (ad)C.a);
/* 43 */       primitives.a(j.a, (ad)C.a);
/* 44 */       primitives.a(s.a, (ad)C.a);
/* 45 */       primitives.a(r.a, (ad)C.a);
/* 46 */       primitives.a(q.a, (ad)C.a);
/* 47 */       primitives.a(o.a, (ad)C.a);
/* 48 */       primitives.a(n.a, (ad)C.a);
/* 49 */       primitives.a(m.a, (ad)C.a);
/* 50 */       primitives.a(h.a, (ad)C.a);
/* 51 */       primitives.a((j)g.a, (ad)C.a);
/* 52 */       primitives.a(jp.cssj.homare.impl.a.c.f.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     ad width = null;
/* 57 */     ad styleValue = null;
/* 58 */     ad color = null;
/* 59 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 60 */       if (width == null) {
/* 61 */         e = a.a(ua, lu);
/* 62 */         if (e != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 66 */       if (styleValue == null) {
/* 67 */         i = a.a(lu);
/* 68 */         if (i != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 72 */       if (color == null) {
/* 73 */         if (c.a(lu)) {
/* 74 */           aa aa = aa.a;
/*    */         } else {
/* 76 */           n = c.a(ua, lu);
/*    */         } 
/* 78 */         if (n != null) {
/*    */           continue;
/*    */         }
/*    */       } 
/* 82 */       throw new l();
/*    */     } 
/*    */     
/* 85 */     primitives.a(l.a, (ad)e);
/* 86 */     primitives.a(s.a, (ad)e);
/* 87 */     primitives.a(o.a, (ad)e);
/* 88 */     primitives.a(h.a, (ad)e);
/* 89 */     primitives.a((j)k.a, (ad)i);
/* 90 */     primitives.a(r.a, (ad)i);
/* 91 */     primitives.a(n.a, (ad)i);
/* 92 */     primitives.a((j)g.a, (ad)i);
/* 93 */     primitives.a(j.a, (ad)n);
/* 94 */     primitives.a(q.a, (ad)n);
/* 95 */     primitives.a(m.a, (ad)n);
/* 96 */     primitives.a(jp.cssj.homare.impl.a.c.f.a, (ad)n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */