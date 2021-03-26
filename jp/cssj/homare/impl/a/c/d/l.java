/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.S;
/*    */ import jp.cssj.homare.impl.a.c.T;
/*    */ import jp.cssj.homare.impl.a.c.U;
/*    */ import jp.cssj.homare.impl.a.c.V;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class l
/*    */   extends d
/*    */ {
/* 23 */   public static final o a = (o)new l();
/*    */   
/*    */   protected l() {
/* 26 */     super("margin");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws jp.cssj.homare.css.c.l {
/* 30 */     ad margin1 = b.a(ua, lu);
/* 31 */     if (margin1 == null) {
/* 32 */       throw new jp.cssj.homare.css.c.l();
/*    */     }
/* 34 */     if (margin1.a() == 1003) {
/* 35 */       primitives.a(V.a, (ad)C.a);
/* 36 */       primitives.a(U.a, (ad)C.a);
/* 37 */       primitives.a(S.a, (ad)C.a);
/* 38 */       primitives.a(T.a, (ad)C.a);
/*    */       return;
/*    */     } 
/* 41 */     lu = lu.getNextLexicalUnit();
/* 42 */     if (lu == null) {
/* 43 */       primitives.a(V.a, margin1);
/* 44 */       primitives.a(U.a, margin1);
/* 45 */       primitives.a(S.a, margin1);
/* 46 */       primitives.a(T.a, margin1);
/*    */       return;
/*    */     } 
/* 49 */     ad margin2 = b.a(ua, lu);
/* 50 */     if (margin2 == null) {
/* 51 */       throw new jp.cssj.homare.css.c.l();
/*    */     }
/* 53 */     lu = lu.getNextLexicalUnit();
/* 54 */     if (lu == null) {
/* 55 */       primitives.a(V.a, margin1);
/* 56 */       primitives.a(U.a, margin2);
/* 57 */       primitives.a(S.a, margin1);
/* 58 */       primitives.a(T.a, margin2);
/*    */       return;
/*    */     } 
/* 61 */     ad margin3 = b.a(ua, lu);
/* 62 */     if (margin3 == null) {
/* 63 */       throw new jp.cssj.homare.css.c.l();
/*    */     }
/* 65 */     lu = lu.getNextLexicalUnit();
/* 66 */     if (lu == null) {
/* 67 */       primitives.a(V.a, margin1);
/* 68 */       primitives.a(U.a, margin2);
/* 69 */       primitives.a(S.a, margin3);
/* 70 */       primitives.a(T.a, margin2);
/*    */       return;
/*    */     } 
/* 73 */     ad margin4 = b.a(ua, lu);
/* 74 */     if (margin4 == null) {
/* 75 */       throw new jp.cssj.homare.css.c.l();
/*    */     }
/* 77 */     primitives.a(V.a, margin1);
/* 78 */     primitives.a(U.a, margin2);
/* 79 */     primitives.a(S.a, margin3);
/* 80 */     primitives.a(T.a, margin4);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */