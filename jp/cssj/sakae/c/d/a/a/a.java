/*    */ package jp.cssj.sakae.c.d.a.a;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ 
/*    */ public class a implements b {
/*  6 */   private final BitSet c = new BitSet();
/*    */   
/*    */   public a(String str) {
/*  9 */     for (int i = 0; i < str.length(); i++) {
/* 10 */       char c = str.charAt(i);
/* 11 */       this.c.set(c);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean a(char c) {
/* 16 */     return this.c.get(c);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */