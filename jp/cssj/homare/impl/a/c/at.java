/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.ag;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class at
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new at();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     ag value = (ag)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   protected at() {
/* 27 */     super("visibility");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)ag.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 43 */     short luType = lu.getLexicalUnitType();
/* 44 */     switch (luType) {
/*    */       case 35:
/* 46 */         ident = lu.getStringValue().toLowerCase();
/* 47 */         if (ident.equals("visible"))
/* 48 */           return (ad)ag.d; 
/* 49 */         if (ident.equals("hidden"))
/* 50 */           return (ad)ag.e; 
/* 51 */         if (ident.equals("collapse")) {
/* 52 */           return (ad)ag.f;
/*    */         }
/*    */         break;
/*    */     } 
/* 56 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/at.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */