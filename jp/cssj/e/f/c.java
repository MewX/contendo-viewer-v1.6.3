/*    */ package jp.cssj.e.f;
/*    */ 
/*    */ import jp.cssj.e.d;
/*    */ 
/*    */ class c
/*    */   implements d {
/*    */   private static final long d = 0L;
/*    */   private final long e;
/*    */   
/*    */   public c(long lastModified) {
/* 11 */     this.e = lastModified;
/*    */   }
/*    */   
/*    */   public int a() {
/* 15 */     return 0;
/*    */   }
/*    */   
/*    */   public int a(d validity) {
/* 19 */     if (this.e == -1L) {
/* 20 */       return 0;
/*    */     }
/* 22 */     return (this.e == ((c)validity).e) ? 1 : -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/f/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */