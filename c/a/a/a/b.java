/*     */ package c.a.a.a;
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
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   public int e;
/*     */   public int[] f;
/*     */   public int[] g;
/*     */   public int[] h;
/*     */   public int i;
/*     */   public int[][] j;
/*     */   public int[] k;
/*     */   
/*     */   public b(int ulx, int uly, int w, int h, int nl) {
/* 112 */     this.a = ulx;
/* 113 */     this.b = uly;
/* 114 */     this.c = w;
/* 115 */     this.d = h;
/* 116 */     this.g = new int[nl];
/* 117 */     this.f = new int[nl];
/* 118 */     this.h = new int[nl];
/* 119 */     this.j = new int[nl][];
/* 120 */     this.k = new int[nl];
/* 121 */     for (int i = nl - 1; i >= 0; i--) {
/* 122 */       this.k[i] = -1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int l, int newtp) {
/* 134 */     this.h[l] = newtp;
/* 135 */     this.i = 0;
/* 136 */     for (int lIdx = 0; lIdx <= l; lIdx++) {
/* 137 */       this.i += this.h[lIdx];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 148 */     String string = "(ulx,uly,w,h)= " + this.a + "," + this.b + "," + this.c + "," + this.d;
/* 149 */     string = string + ", " + this.e + " MSB bit(s) skipped\n";
/* 150 */     if (this.f != null)
/* 151 */       for (int i = 0; i < this.f.length; i++) {
/* 152 */         string = string + "\tl:" + i + ", start:" + this.g[i] + ", len:" + this.f[i] + ", ntp:" + this.h[i] + ", pktIdx=" + this.k[i];
/*     */ 
/*     */         
/* 155 */         if (this.j != null && this.j[i] != null) {
/* 156 */           string = string + " { ";
/* 157 */           for (int j = 0; j < (this.j[i]).length; j++)
/* 158 */             string = string + this.j[i][j] + " "; 
/* 159 */           string = string + "}";
/*     */         } 
/* 161 */         string = string + "\n";
/*     */       }  
/* 163 */     string = string + "\tctp=" + this.i;
/* 164 */     return string;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */