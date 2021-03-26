/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.K;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ai
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new ai();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     K value = (K)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   private ai() {
/* 27 */     super("page-break-inside");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)K.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*    */     String ident;
/* 43 */     switch (lu.getLexicalUnitType()) {
/*    */       case 35:
/* 45 */         ident = lu.getStringValue().toLowerCase();
/* 46 */         if (ident.equals("auto"))
/* 47 */           return (ad)K.a; 
/* 48 */         if (ident.equals("avoid")) {
/* 49 */           return (ad)K.b;
/*    */         }
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ai.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */