/*    */ package jp.cssj.sakae.pdf.f;
/*    */ 
/*    */ 
/*    */ public class d
/*    */   extends c
/*    */ {
/*    */   public static final int e = 4;
/*    */   public static final int f = 8;
/*    */   public static final int g = 16;
/*    */   public static final int h = 32;
/*    */   
/*    */   public short a() {
/* 13 */     return 2;
/*    */   }
/*    */   
/*    */   public int b() {
/* 17 */     return this.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 21 */     return ((this.d & 0x20) != 0);
/*    */   }
/*    */   
/*    */   public void a(boolean add) {
/* 25 */     if (add) {
/* 26 */       this.d |= 0x20;
/*    */     } else {
/* 28 */       this.d &= 0xFFFFFFDF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 33 */     return ((this.d & 0x10) != 0);
/*    */   }
/*    */   
/*    */   public void b(boolean copy) {
/* 37 */     if (copy) {
/* 38 */       this.d |= 0x10;
/*    */     } else {
/* 40 */       this.d &= 0xFFFFFFEF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 45 */     return ((this.d & 0x8) != 0);
/*    */   }
/*    */   
/*    */   public void c(boolean modify) {
/* 49 */     if (modify) {
/* 50 */       this.d |= 0x8;
/*    */     } else {
/* 52 */       this.d &= 0xFFFFFFF7;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean f() {
/* 57 */     return ((this.d & 0x4) != 0);
/*    */   }
/*    */   
/*    */   public void d(boolean print) {
/* 61 */     if (print) {
/* 62 */       this.d |= 0x4;
/*    */     } else {
/* 64 */       this.d &= 0xFFFFFFFB;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */