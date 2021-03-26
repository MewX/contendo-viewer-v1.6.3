/*    */ package jp.cssj.sakae.pdf.f;
/*    */ 
/*    */ public class h extends a {
/*  4 */   private final e d = new e();
/*    */   
/*  6 */   private int e = 128;
/*    */   
/*    */   public short a() {
/*  9 */     return 2;
/*    */   }
/*    */   
/*    */   public e d() {
/* 13 */     return this.d;
/*    */   }
/*    */   
/*    */   public int e() {
/* 17 */     return this.e;
/*    */   }
/*    */   
/*    */   public void a(int length) throws IllegalArgumentException {
/* 21 */     if (length < 40 || length > 128 || length % 8 != 0) {
/* 22 */       throw new IllegalArgumentException("V2暗号化の長さは40-128ビットの範囲で8刻みです。: " + length);
/*    */     }
/* 24 */     this.e = length;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */