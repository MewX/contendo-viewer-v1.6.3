/*    */ package jp.cssj.homare.b.a.a;
/*    */ 
/*    */ import jp.cssj.homare.b.a.e;
/*    */ import jp.cssj.homare.b.a.h;
/*    */ 
/*    */ public class j implements m {
/*    */   protected final double a;
/*    */   
/*    */   public j(double ratio) {
/* 10 */     this.a = ratio;
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(h parent, e line, double ascent, double descent, double lineHeight, double lineBase) {
/* 15 */     return this.a * lineHeight;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return (this.a * 100.0D) + "%";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */