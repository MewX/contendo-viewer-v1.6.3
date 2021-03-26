/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.g;
/*    */ import jp.cssj.homare.css.f.s;
/*    */ import jp.cssj.homare.impl.a.c.F;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class K
/*    */   extends d
/*    */ {
/* 19 */   public static final o a = (o)new K();
/*    */   
/*    */   protected K() {
/* 22 */     super("-cssj-writing-mode");
/*    */   }
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*    */     String ident;
/* 26 */     short luType = lu.getLexicalUnitType();
/* 27 */     switch (luType) {
/*    */       case 35:
/* 29 */         ident = lu.getStringValue().toLowerCase();
/* 30 */         if (ident.equals("lr-tb") || ident.equals("lr") || ident.equals("horizontal-tb")) {
/* 31 */           primitives.a(F.a, (ad)s.a);
/* 32 */           primitives.a(c.a, (ad)g.a);
/* 33 */         } else if (ident.equals("rl-tb") || ident.equals("rl")) {
/* 34 */           primitives.a(F.a, (ad)s.b);
/* 35 */           primitives.a(c.a, (ad)g.a);
/* 36 */         } else if (ident.equals("tb-rl") || ident.equals("tb") || ident.equals("vertical-rl")) {
/* 37 */           primitives.a(F.a, (ad)s.a);
/* 38 */           primitives.a(c.a, (ad)g.b);
/* 39 */         } else if (ident.equals("tb-lr") || ident.equals("vertical-lr")) {
/* 40 */           primitives.a(F.a, (ad)s.b);
/* 41 */           primitives.a(c.a, (ad)g.c);
/*    */         } else {
/* 43 */           throw new l();
/*    */         } 
/*    */         return;
/*    */     } 
/* 47 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/K.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */