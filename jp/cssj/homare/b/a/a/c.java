/*    */ package jp.cssj.homare.b.a.a;
/*    */ 
/*    */ import jp.cssj.homare.b.a.j;
/*    */ 
/*    */ public abstract class c implements Comparable<c> {
/*    */   public final int a;
/*    */   
/*    */   public c(int serial) {
/*  9 */     this.a = serial;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(c o) {
/* 15 */     c holder = o;
/* 16 */     if (this.a > holder.a) {
/* 17 */       return 1;
/*    */     }
/* 19 */     if (this.a < holder.a) {
/* 20 */       return -1;
/*    */     }
/* 22 */     return 0;
/*    */   }
/*    */   
/*    */   public abstract j a();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */