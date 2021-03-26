/*    */ package jp.cssj.homare.impl.a.a;
/*    */ 
/*    */ import jp.cssj.sakae.c.d.a.a.c;
/*    */ 
/*    */ public class d
/*    */   extends c
/*    */ {
/*    */   public boolean a(char c1, char c2) {
/*  9 */     if (c(c1) && c(c2) && Character.UnicodeBlock.of(c1) != Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
/* 10 */       return true;
/*    */     }
/* 12 */     return super.a(c1, c2);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */