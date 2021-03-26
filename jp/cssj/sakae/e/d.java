/*    */ package jp.cssj.sakae.e;
/*    */ 
/*    */ public final class d {
/*  4 */   private static final Short[] a = new Short[] { new Short((short)0), new Short((short)1), new Short((short)2), new Short((short)3) };
/*    */   
/*  6 */   private static final Integer[] b = new Integer[] { new Integer(0), new Integer(1), new Integer(2), new Integer(3) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Integer a(int a) {
/* 13 */     if (a >= 0 && a < b.length) {
/* 14 */       return b[a];
/*    */     }
/* 16 */     return new Integer(a);
/*    */   }
/*    */   
/*    */   public static Short a(short a) {
/* 20 */     if (a >= 0 && a < d.a.length) {
/* 21 */       return d.a[a];
/*    */     }
/* 23 */     return new Short(a);
/*    */   }
/*    */   
/*    */   public static double a(String str) {
/* 27 */     double a = Double.parseDouble(str);
/* 28 */     if (Double.isNaN(a)) {
/* 29 */       throw new NumberFormatException(str);
/*    */     }
/* 31 */     return a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/e/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */