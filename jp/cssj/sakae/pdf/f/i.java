/*    */ package jp.cssj.sakae.pdf.f;
/*    */ 
/*    */ public class i
/*    */   extends a {
/*    */   public static final short d = 1;
/*    */   public static final short e = 2;
/*  7 */   private final f f = new f();
/*    */   
/*  9 */   private int g = 128;
/*    */   
/* 11 */   private short h = 1;
/*    */   
/*    */   private boolean i = true;
/*    */   
/*    */   public short a() {
/* 16 */     return 4;
/*    */   }
/*    */   
/*    */   public f d() {
/* 20 */     return this.f;
/*    */   }
/*    */   
/*    */   public int e() {
/* 24 */     return this.g;
/*    */   }
/*    */   
/*    */   public void a(int length) throws IllegalArgumentException {
/* 28 */     if (length < 40 || length > 128 || length % 8 != 0) {
/* 29 */       throw new IllegalArgumentException("V2暗号化の長さは40-128ビットの範囲で8刻みです。: " + length);
/*    */     }
/* 31 */     this.g = length;
/*    */   }
/*    */   
/*    */   public short f() {
/* 35 */     return this.h;
/*    */   }
/*    */   
/*    */   public void a(short cfm) {
/* 39 */     if (cfm != 1 && cfm != 2) {
/* 40 */       throw new IllegalArgumentException("CFMはCFM_V2またはCFM_AESV2である必要があります。: " + cfm);
/*    */     }
/* 42 */     this.h = cfm;
/*    */   }
/*    */   
/*    */   public boolean g() {
/* 46 */     return this.i;
/*    */   }
/*    */   
/*    */   public void a(boolean enctyptMetadata) {
/* 50 */     this.i = enctyptMetadata;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */