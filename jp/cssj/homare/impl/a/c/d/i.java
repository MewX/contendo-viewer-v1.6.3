/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.h;
/*    */ import jp.cssj.homare.impl.a.c.l;
/*    */ import jp.cssj.homare.impl.a.c.o;
/*    */ import jp.cssj.homare.impl.a.c.s;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   extends d
/*    */ {
/* 23 */   public static final o a = (o)new i();
/*    */   
/*    */   protected i() {
/* 26 */     super("border-width");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/* 30 */     E e1 = a.a(ua, lu);
/* 31 */     if (e1 == null) {
/* 32 */       throw new l();
/*    */     }
/* 34 */     if (e1.a() == 1003) {
/* 35 */       primitives.a(l.a, (ad)C.a);
/* 36 */       primitives.a(s.a, (ad)C.a);
/* 37 */       primitives.a(o.a, (ad)C.a);
/* 38 */       primitives.a(h.a, (ad)C.a);
/*    */       return;
/*    */     } 
/* 41 */     lu = lu.getNextLexicalUnit();
/* 42 */     if (lu == null) {
/* 43 */       primitives.a(l.a, (ad)e1);
/* 44 */       primitives.a(s.a, (ad)e1);
/* 45 */       primitives.a(o.a, (ad)e1);
/* 46 */       primitives.a(h.a, (ad)e1);
/*    */       return;
/*    */     } 
/* 49 */     E e2 = a.a(ua, lu);
/* 50 */     if (e2 == null) {
/* 51 */       throw new l();
/*    */     }
/* 53 */     lu = lu.getNextLexicalUnit();
/* 54 */     if (lu == null) {
/* 55 */       primitives.a(l.a, (ad)e2);
/* 56 */       primitives.a(s.a, (ad)e1);
/* 57 */       primitives.a(o.a, (ad)e2);
/* 58 */       primitives.a(h.a, (ad)e1);
/*    */       return;
/*    */     } 
/* 61 */     E e3 = a.a(ua, lu);
/* 62 */     if (e3 == null) {
/* 63 */       throw new l();
/*    */     }
/* 65 */     lu = lu.getNextLexicalUnit();
/* 66 */     if (lu == null) {
/* 67 */       primitives.a(l.a, (ad)e2);
/* 68 */       primitives.a(s.a, (ad)e1);
/* 69 */       primitives.a(o.a, (ad)e2);
/* 70 */       primitives.a(h.a, (ad)e3);
/*    */       return;
/*    */     } 
/* 73 */     E e4 = a.a(ua, lu);
/* 74 */     if (e4 == null) {
/* 75 */       throw new l();
/*    */     }
/* 77 */     primitives.a(l.a, (ad)e4);
/* 78 */     primitives.a(s.a, (ad)e1);
/* 79 */     primitives.a(o.a, (ad)e2);
/* 80 */     primitives.a(h.a, (ad)e3);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */