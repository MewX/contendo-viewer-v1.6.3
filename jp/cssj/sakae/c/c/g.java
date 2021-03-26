/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ public class g
/*    */   extends h {
/*    */   public static final int a = 3;
/*    */   protected final float b;
/*    */   
/*    */   public static h b(float red, float green, float blue, float alpha) {
/*  9 */     if (alpha == 1.0F) {
/* 10 */       return h.b(red, green, blue);
/*    */     }
/* 12 */     return new g(red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   protected g(float red, float green, float blue, float alpha) {
/* 16 */     super(red, green, blue);
/* 17 */     this.b = alpha;
/*    */   }
/*    */   
/*    */   public short c() {
/* 21 */     return 4;
/*    */   }
/*    */   
/*    */   public float a(int i) {
/* 25 */     switch (i) {
/*    */       case 0:
/* 27 */         return this.h;
/*    */       case 1:
/* 29 */         return this.q;
/*    */       case 2:
/* 31 */         return this.r;
/*    */       case 3:
/* 33 */         return this.b;
/*    */     } 
/* 35 */     throw new IllegalArgumentException();
/*    */   }
/*    */   
/*    */   public float g() {
/* 39 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 43 */     if (o instanceof g) {
/* 44 */       g color = (g)o;
/* 45 */       return (this.h == color.h && this.q == color.q && this.r == color.r && this.b == color.b);
/*    */     } 
/*    */     
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 52 */     return "rgba(" + this.h + "," + this.q + "," + this.r + "," + this.b + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */