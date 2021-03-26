/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.g;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new c();
/*    */   
/*    */   public static byte c(jp.cssj.homare.css.c style) {
/* 22 */     return ((g)style.a(a)).b();
/*    */   }
/*    */   
/*    */   protected c() {
/* 26 */     super("-cssj-block-flow");
/*    */   }
/*    */   
/*    */   public ad b(jp.cssj.homare.css.c style) {
/* 30 */     return (ad)g.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, jp.cssj.homare.css.c style) {
/* 38 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 42 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 44 */         ident = lu.getStringValue().toLowerCase();
/* 45 */         if (ident.equals("tb"))
/* 46 */           return (ad)g.a; 
/* 47 */         if (ident.equals("rl"))
/* 48 */           return (ad)g.b; 
/* 49 */         if (ident.equals("lr")) {
/* 50 */           return (ad)g.c;
/*    */         }
/*    */         break;
/*    */     } 
/* 54 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */