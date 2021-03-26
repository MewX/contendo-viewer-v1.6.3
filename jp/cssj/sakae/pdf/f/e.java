/*    */ package jp.cssj.sakae.pdf.f;
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends d
/*    */ {
/*    */   public static final int i = 256;
/*    */   public static final int j = 512;
/*    */   public static final int k = 1024;
/*    */   public static final int l = 2048;
/*    */   
/*    */   public short a() {
/* 13 */     return 3;
/*    */   }
/*    */   
/*    */   public int b() {
/* 17 */     return this.d;
/*    */   }
/*    */   
/*    */   public boolean g() {
/* 21 */     return ((this.d & 0x400) != 0);
/*    */   }
/*    */   
/*    */   public void e(boolean assemble) {
/* 25 */     if (assemble) {
/* 26 */       this.d |= 0x400;
/*    */     } else {
/* 28 */       this.d &= 0xFFFFFBFF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean h() {
/* 33 */     return ((this.d & 0x200) != 0);
/*    */   }
/*    */   
/*    */   public void f(boolean extract) {
/* 37 */     if (extract) {
/* 38 */       this.d |= 0x200;
/*    */     } else {
/* 40 */       this.d &= 0xFFFFFDFF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean i() {
/* 45 */     return ((this.d & 0x100) != 0);
/*    */   }
/*    */   
/*    */   public void g(boolean fill) {
/* 49 */     if (fill) {
/* 50 */       this.d |= 0x100;
/*    */     } else {
/* 52 */       this.d &= 0xFFFFFEFF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean j() {
/* 57 */     return ((this.d & 0x800) != 0);
/*    */   }
/*    */   
/*    */   public void h(boolean printHigh) {
/* 61 */     if (printHigh) {
/* 62 */       this.d |= 0x800;
/*    */     } else {
/* 64 */       this.d &= 0xFFFFF7FF;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */