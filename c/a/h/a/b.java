/*     */ package c.a.h.a;
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
/*     */ public class b
/*     */ {
/*  57 */   public c.a.e.b.b a = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c;
/*     */ 
/*     */ 
/*     */   
/*     */   public int d;
/*     */ 
/*     */ 
/*     */   
/*     */   public int e;
/*     */ 
/*     */ 
/*     */   
/*     */   public int f;
/*     */ 
/*     */ 
/*     */   
/*     */   public int g;
/*     */ 
/*     */   
/*     */   public int h;
/*     */ 
/*     */   
/*     */   public int i;
/*     */ 
/*     */   
/*     */   public int j;
/*     */ 
/*     */   
/*     */   public int k;
/*     */ 
/*     */ 
/*     */   
/*     */   public b(int comp, c.a.e.b.b maskPGM) {
/*  98 */     this.b = true;
/*  99 */     this.c = false;
/* 100 */     this.d = comp;
/* 101 */     this.a = maskPGM;
/*     */   }
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
/*     */   public b(int comp, int ulx, int uly, int w, int h) {
/* 118 */     this.b = false;
/* 119 */     this.d = comp;
/* 120 */     this.e = ulx;
/* 121 */     this.f = uly;
/* 122 */     this.g = w;
/* 123 */     this.h = h;
/* 124 */     this.c = true;
/*     */   }
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
/*     */   public b(int comp, int x, int y, int rad) {
/* 139 */     this.b = false;
/* 140 */     this.d = comp;
/* 141 */     this.i = x;
/* 142 */     this.j = y;
/* 143 */     this.k = rad;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     if (this.b) {
/* 151 */       return "ROI with arbitrary shape, PGM file= " + this.a;
/*     */     }
/* 153 */     if (this.c) {
/* 154 */       return "Rectangular ROI, comp=" + this.d + " ulx=" + this.e + " uly=" + this.f + " w=" + this.g + " h=" + this.h;
/*     */     }
/*     */     
/* 157 */     return "Circular ROI,  comp=" + this.d + " x=" + this.i + " y=" + this.j + " radius=" + this.k;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */