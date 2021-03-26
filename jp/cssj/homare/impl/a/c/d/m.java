/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.O;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.ac;
/*    */ import jp.cssj.homare.impl.a.c.ad;
/*    */ import jp.cssj.homare.impl.a.c.ae;
/*    */ import jp.cssj.homare.impl.a.c.af;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   extends d
/*    */ {
/* 23 */   public static final o a = (o)new m();
/*    */   
/*    */   protected m() {
/* 26 */     super("padding");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, jp.cssj.homare.ua.m ua, URI uri, d.a primitives) throws l {
/* 30 */     O o1 = b.c(ua, lu);
/* 31 */     if (o1 == null) {
/* 32 */       throw new l();
/*    */     }
/* 34 */     if (o1.a() == 1003) {
/* 35 */       primitives.a(ad.a, (ad)C.a);
/* 36 */       primitives.a(af.a, (ad)C.a);
/* 37 */       primitives.a(ae.a, (ad)C.a);
/* 38 */       primitives.a(ac.a, (ad)C.a);
/*    */       return;
/*    */     } 
/* 41 */     lu = lu.getNextLexicalUnit();
/* 42 */     if (lu == null) {
/* 43 */       primitives.a(ad.a, (ad)o1);
/* 44 */       primitives.a(af.a, (ad)o1);
/* 45 */       primitives.a(ae.a, (ad)o1);
/* 46 */       primitives.a(ac.a, (ad)o1);
/*    */       return;
/*    */     } 
/* 49 */     O o2 = b.c(ua, lu);
/* 50 */     if (o2 == null) {
/* 51 */       throw new l();
/*    */     }
/* 53 */     lu = lu.getNextLexicalUnit();
/* 54 */     if (lu == null) {
/* 55 */       primitives.a(ad.a, (ad)o2);
/* 56 */       primitives.a(af.a, (ad)o1);
/* 57 */       primitives.a(ae.a, (ad)o2);
/* 58 */       primitives.a(ac.a, (ad)o1);
/*    */       return;
/*    */     } 
/* 61 */     O o3 = b.c(ua, lu);
/* 62 */     if (o3 == null) {
/* 63 */       throw new l();
/*    */     }
/* 65 */     lu = lu.getNextLexicalUnit();
/* 66 */     if (lu == null) {
/* 67 */       primitives.a(ad.a, (ad)o2);
/* 68 */       primitives.a(af.a, (ad)o1);
/* 69 */       primitives.a(ae.a, (ad)o2);
/* 70 */       primitives.a(ac.a, (ad)o3);
/*    */       return;
/*    */     } 
/* 73 */     O o4 = b.c(ua, lu);
/* 74 */     if (o4 == null) {
/* 75 */       throw new l();
/*    */     }
/* 77 */     primitives.a(ad.a, (ad)o4);
/* 78 */     primitives.a(af.a, (ad)o1);
/* 79 */     primitives.a(ae.a, (ad)o2);
/* 80 */     primitives.a(ac.a, (ad)o3);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */