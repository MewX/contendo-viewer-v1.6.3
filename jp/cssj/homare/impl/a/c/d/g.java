/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.i;
/*    */ import jp.cssj.homare.impl.a.c.k;
/*    */ import jp.cssj.homare.impl.a.c.n;
/*    */ import jp.cssj.homare.impl.a.c.r;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   extends d
/*    */ {
/* 23 */   public static final o a = (o)new g();
/*    */   
/*    */   protected g() {
/* 26 */     super("border-style");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/* 30 */     i i1 = a.a(lu);
/* 31 */     if (i1 == null) {
/* 32 */       throw new l();
/*    */     }
/* 34 */     if (i1.a() == 1003) {
/* 35 */       primitives.a((j)k.a, (ad)C.a);
/* 36 */       primitives.a(r.a, (ad)C.a);
/* 37 */       primitives.a(n.a, (ad)C.a);
/* 38 */       primitives.a((j)jp.cssj.homare.impl.a.c.g.a, (ad)C.a);
/*    */       return;
/*    */     } 
/* 41 */     lu = lu.getNextLexicalUnit();
/* 42 */     if (lu == null) {
/* 43 */       primitives.a((j)k.a, (ad)i1);
/* 44 */       primitives.a(r.a, (ad)i1);
/* 45 */       primitives.a(n.a, (ad)i1);
/* 46 */       primitives.a((j)jp.cssj.homare.impl.a.c.g.a, (ad)i1);
/*    */       return;
/*    */     } 
/* 49 */     i i2 = a.a(lu);
/* 50 */     if (i2 == null) {
/* 51 */       throw new l();
/*    */     }
/* 53 */     lu = lu.getNextLexicalUnit();
/* 54 */     if (lu == null) {
/* 55 */       primitives.a((j)k.a, (ad)i2);
/* 56 */       primitives.a(r.a, (ad)i1);
/* 57 */       primitives.a(n.a, (ad)i2);
/* 58 */       primitives.a((j)jp.cssj.homare.impl.a.c.g.a, (ad)i1);
/*    */       return;
/*    */     } 
/* 61 */     i i3 = a.a(lu);
/* 62 */     if (i3 == null) {
/* 63 */       throw new l();
/*    */     }
/* 65 */     lu = lu.getNextLexicalUnit();
/* 66 */     if (lu == null) {
/* 67 */       primitives.a((j)k.a, (ad)i2);
/* 68 */       primitives.a(r.a, (ad)i1);
/* 69 */       primitives.a(n.a, (ad)i2);
/* 70 */       primitives.a((j)jp.cssj.homare.impl.a.c.g.a, (ad)i3);
/*    */       return;
/*    */     } 
/* 73 */     i i4 = a.a(lu);
/* 74 */     if (i4 == null) {
/* 75 */       throw new l();
/*    */     }
/* 77 */     primitives.a((j)k.a, (ad)i4);
/* 78 */     primitives.a(r.a, (ad)i1);
/* 79 */     primitives.a(n.a, (ad)i2);
/* 80 */     primitives.a((j)jp.cssj.homare.impl.a.c.g.a, (ad)i3);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */