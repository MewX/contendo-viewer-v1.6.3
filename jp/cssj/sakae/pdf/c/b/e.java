/*    */ package jp.cssj.sakae.pdf.c.b;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class e
/*    */   implements Serializable
/*    */ {
/*    */   private static final long b = 0L;
/*    */   public final short a;
/*    */   private final int[] c;
/*    */   private final short[] d;
/*    */   
/*    */   public e(short advance, int[] sgidToLigature, short[] sgidToKerning) {
/* 21 */     this.a = advance;
/* 22 */     this.c = sgidToLigature;
/* 23 */     this.d = sgidToKerning;
/*    */   }
/*    */   
/*    */   public short a(int sgid) {
/* 27 */     if (this.d == null || sgid >= this.d.length) {
/* 28 */       return 0;
/*    */     }
/* 30 */     short kerning = this.d[sgid];
/* 31 */     return kerning;
/*    */   }
/*    */   
/*    */   public int b(int sgid) {
/* 35 */     if (this.c == null || sgid >= this.c.length) {
/* 36 */       return -1;
/*    */     }
/* 38 */     int lgid = this.c[sgid];
/* 39 */     return lgid;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */