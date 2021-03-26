/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.f.M;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.g;
/*    */ import jp.cssj.homare.css.f.s;
/*    */ import jp.cssj.homare.impl.a.c.F;
/*    */ import jp.cssj.homare.impl.a.c.O;
/*    */ import jp.cssj.homare.impl.a.c.ao;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class x
/*    */   extends d
/*    */ {
/* 23 */   public static final o a = (o)new x();
/*    */   
/*    */   protected x() {
/* 26 */     super("-cssj-text-combine");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/* 30 */     if (lu.getLexicalUnitType() == 35) {
/* 31 */       String ident = lu.getStringValue();
/* 32 */       if (ident.equals("horizontal")) {
/* 33 */         primitives.a(F.a, (ad)s.a);
/* 34 */         primitives.a(c.a, (ad)g.a);
/* 35 */         primitives.a(ao.a, (ad)a.a);
/* 36 */         primitives.a(O.a, (ad)M.c);
/*    */       } else {
/* 38 */         throw new l();
/*    */       } 
/* 40 */       if (lu.getNextLexicalUnit() != null) {
/* 41 */         throw new l();
/*    */       }
/*    */     } else {
/* 44 */       throw new l();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */