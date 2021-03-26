/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.f.a.e;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class l
/*    */   extends b
/*    */ {
/* 20 */   public static final j a = (j)new l();
/*    */   
/*    */   public static byte c(c style) {
/* 23 */     e value = (e)style.a(a);
/* 24 */     return value.b();
/*    */   }
/*    */   
/*    */   protected l() {
/* 28 */     super("-cssj-column-fill");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 32 */     return (ad)e.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 40 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws jp.cssj.homare.css.c.l {
/*    */     String ident;
/* 44 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 46 */         ident = lu.getStringValue().toLowerCase();
/* 47 */         if (ident.equals("auto"))
/* 48 */           return (ad)e.c; 
/* 49 */         if (ident.equals("balance")) {
/* 50 */           return (ad)e.d;
/*    */         }
/*    */         break;
/*    */     } 
/* 54 */     throw new jp.cssj.homare.css.c.l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */