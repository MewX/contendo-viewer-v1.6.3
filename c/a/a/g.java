/*     */ package c.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   public int e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int h;
/*     */   public int i;
/*     */   public a[][][] j;
/*     */   public int[] k;
/*     */   
/*     */   public g(int r, int ulx, int uly, int w, int h, int rgulx, int rguly, int rgw, int rgh) {
/* 101 */     this.i = r;
/* 102 */     this.e = ulx;
/* 103 */     this.f = uly;
/* 104 */     this.g = w;
/* 105 */     this.h = h;
/* 106 */     this.a = rgulx;
/* 107 */     this.b = rguly;
/* 108 */     this.c = rgw;
/* 109 */     this.d = rgh;
/*     */     
/* 111 */     if (r == 0) {
/* 112 */       this.j = new a[1][][];
/* 113 */       this.k = new int[1];
/*     */     } else {
/* 115 */       this.j = new a[4][][];
/* 116 */       this.k = new int[4];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     return "ulx=" + this.e + ",uly=" + this.f + ",w=" + this.g + ",h=" + this.h + ",rgulx=" + this.a + ",rguly=" + this.b + ",rgw=" + this.c + ",rgh=" + this.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */