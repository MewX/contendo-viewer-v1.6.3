/*    */ package jp.cssj.homare.css.e;
/*    */ 
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class h
/*    */ {
/*    */   public static ad a(m ua, LexicalUnit lu) {
/* 24 */     short luType = lu.getLexicalUnitType();
/* 25 */     switch (luType) {
/*    */       case 12:
/*    */       case 15:
/*    */       case 16:
/*    */       case 35:
/* 30 */         return null;
/*    */       
/*    */       case 23:
/* 33 */         return (ad)l.d(lu);
/*    */     } 
/*    */     
/* 36 */     return (ad)l.a(ua, lu);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */