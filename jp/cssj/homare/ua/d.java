/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ class d
/*    */ {
/*    */   private static final int a = 512;
/*  6 */   private final e[] b = new e[512];
/*    */   
/*    */   public e a(int level, boolean create) {
/*  9 */     if (level >= 512) {
/* 10 */       level = 511;
/*    */     }
/* 12 */     if (create && this.b[level] == null) {
/* 13 */       this.b[level] = new e();
/*    */     }
/* 15 */     return this.b[level];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */