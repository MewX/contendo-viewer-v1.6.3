/*     */ package c.a.c.b;
/*     */ 
/*     */ import c.a.c.b;
/*     */ import c.a.j.a.o;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends b
/*     */ {
/*     */   public o e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int[] h;
/*     */   public double[] i;
/*     */   public float[] j;
/*     */   public int[] k;
/*     */   public boolean[] l;
/* 119 */   public int m = 0;
/*     */ 
/*     */   
/* 122 */   public int n = 0;
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
/*     */   public c() {}
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
/*     */   public c(int m, int n, int skipMSBP, byte[] data, int[] rates, double[] dists, boolean[] termp, int np, boolean inclast) {
/* 178 */     super(m, n, skipMSBP, data);
/* 179 */     a(rates, dists, termp, np, inclast);
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
/*     */   public void a(int[] rates, double[] dists, boolean[] termp, int n, boolean inclast) {
/* 229 */     int first_pnt = 0;
/* 230 */     while (first_pnt < n && rates[first_pnt] <= 0) {
/* 231 */       first_pnt++;
/*     */     }
/*     */ 
/*     */     
/* 235 */     int npnt = n - first_pnt;
/* 236 */     float p_slope = 0.0F;
/*     */     
/*     */     label56: while (true) {
/* 239 */       int p = -1;
/* 240 */       for (int m = first_pnt; m < n; m++) {
/* 241 */         if (rates[m] >= 0) {
/*     */           int delta_rate;
/*     */           
/*     */           double delta_dist;
/* 245 */           if (p >= 0) {
/* 246 */             delta_rate = rates[m] - rates[p];
/* 247 */             delta_dist = dists[m] - dists[p];
/*     */           } else {
/*     */             
/* 250 */             delta_rate = rates[m];
/* 251 */             delta_dist = dists[m];
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 256 */           if (delta_dist < 0.0D || (delta_dist == 0.0D && delta_rate > 0)) {
/*     */             
/* 258 */             rates[m] = -rates[m];
/* 259 */             npnt--;
/*     */           } else {
/*     */             
/* 262 */             float k_slope = (float)(delta_dist / delta_rate);
/*     */ 
/*     */ 
/*     */             
/* 266 */             if (p >= 0 && (delta_rate <= 0 || k_slope >= p_slope)) {
/*     */ 
/*     */               
/* 269 */               rates[p] = -rates[p];
/* 270 */               npnt--;
/*     */               
/*     */               continue label56;
/*     */             } 
/* 274 */             p_slope = k_slope;
/* 275 */             p = m;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*     */       break;
/*     */     } 
/*     */     
/* 284 */     if (inclast && n > 0 && rates[n - 1] < 0) {
/* 285 */       rates[n - 1] = -rates[n - 1];
/*     */ 
/*     */ 
/*     */       
/* 289 */       npnt++;
/*     */     } 
/*     */ 
/*     */     
/* 293 */     this.f = n;
/* 294 */     this.g = npnt;
/* 295 */     this.h = new int[n];
/* 296 */     this.i = new double[n];
/* 297 */     this.j = new float[npnt];
/* 298 */     this.k = new int[npnt];
/* 299 */     if (termp != null) {
/* 300 */       this.l = new boolean[n];
/* 301 */       System.arraycopy(termp, 0, this.l, 0, n);
/*     */     } else {
/*     */       
/* 304 */       this.l = null;
/*     */     } 
/* 306 */     System.arraycopy(rates, 0, this.h, 0, n);
/* 307 */     for (int k = first_pnt, j = -1, i = 0; k < n; k++) {
/* 308 */       if (rates[k] > 0) {
/* 309 */         this.i[k] = dists[k];
/* 310 */         if (j < 0) {
/* 311 */           this.j[i] = (float)(dists[k] / rates[k]);
/*     */         } else {
/*     */           
/* 314 */           this.j[i] = (float)((dists[k] - dists[j]) / (rates[k] - rates[j]));
/*     */         } 
/*     */         
/* 317 */         this.k[i] = k;
/* 318 */         i++;
/* 319 */         j = k;
/*     */       } else {
/*     */         
/* 322 */         this.i[k] = -1.0D;
/* 323 */         this.h[k] = -this.h[k];
/*     */       } 
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
/* 335 */     return super.toString() + "\n nVldTrunc = " + this.g + ", nTotTrunc=" + this.f + ", num. ROI" + " coeff=" + this.m + ", num. ROI coding passes=" + this.n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */