/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import jp.cssj.f.a;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ 
/*    */ class f extends b {
/*  7 */   private int c = -1;
/*    */   
/*    */   private int d;
/*    */   
/*    */   public f(int objectNum) {
/* 12 */     super(objectNum, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(int id, int position) {
/* 17 */     if (this.c != -1) {
/* 18 */       throw new IllegalStateException("同じリファレンスで2度オブジェクトを作成しようとしました。");
/*    */     }
/* 20 */     this.d = id;
/* 21 */     this.c = position;
/*    */   }
/*    */   
/*    */   public long a(a.a info) {
/* 25 */     return info.a(this.d) + this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     return "R " + this.a + " " + this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */