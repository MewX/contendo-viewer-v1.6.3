/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ public class h
/*    */   implements b
/*    */ {
/*    */   public static final int c = 0;
/*    */   public static final int d = 1;
/*    */   public static final int e = 2;
/* 10 */   public static final h f = new h(0.0F, 0.0F, 0.0F);
/*    */   
/* 12 */   public static final h g = new h(1.0F, 1.0F, 1.0F);
/*    */   
/*    */   protected final float h;
/*    */   
/*    */   public static h b(float red, float green, float blue) {
/* 17 */     if (red <= 0.0F && green <= 0.0F && blue <= 0.0F) {
/* 18 */       return f;
/*    */     }
/* 20 */     if (red >= 1.0F && green >= 1.0F && blue >= 1.0F) {
/* 21 */       return g;
/*    */     }
/* 23 */     return new h(red, green, blue);
/*    */   }
/*    */   protected final float q; protected final float r;
/*    */   protected h(float red, float green, float blue) {
/* 27 */     this.h = Math.min(1.0F, Math.max(0.0F, red));
/* 28 */     this.q = Math.min(1.0F, Math.max(0.0F, green));
/* 29 */     this.r = Math.min(1.0F, Math.max(0.0F, blue));
/*    */   }
/*    */   
/*    */   public short b() {
/* 33 */     return 1;
/*    */   }
/*    */   
/*    */   public short c() {
/* 37 */     return 1;
/*    */   }
/*    */   
/*    */   public float a(int i) {
/* 41 */     switch (i) {
/*    */       case 0:
/* 43 */         return this.h;
/*    */       case 1:
/* 45 */         return this.q;
/*    */       case 2:
/* 47 */         return this.r;
/*    */     } 
/* 49 */     throw new IllegalArgumentException();
/*    */   }
/*    */   
/*    */   public float d() {
/* 53 */     return this.h;
/*    */   }
/*    */   
/*    */   public float e() {
/* 57 */     return this.q;
/*    */   }
/*    */   
/*    */   public float f() {
/* 61 */     return this.r;
/*    */   }
/*    */   
/*    */   public float g() {
/* 65 */     return 1.0F;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 69 */     if (o instanceof h) {
/* 70 */       h color = (h)o;
/* 71 */       return (this.h == color.h && this.q == color.q && this.r == color.r);
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 77 */     return "rgb(" + this.h + "," + this.q + "," + this.r + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */