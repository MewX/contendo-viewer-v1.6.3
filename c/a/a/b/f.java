/*     */ package c.a.a.b;
/*     */ 
/*     */ import c.a.i.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class f
/*     */ {
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected int c;
/*     */   protected int[][] d;
/*     */   protected int[][] e;
/*     */   protected int[][] f;
/*     */   protected int[][] g;
/*     */   protected boolean h;
/*     */   
/*     */   public f(int h, int w) {
/* 135 */     if (w < 0 || h < 0) {
/* 136 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 139 */     a(w, h);
/*     */     
/* 141 */     for (int k = this.d.length - 1; k >= 0; k--) {
/* 142 */       a.a(this.d[k], 2147483647);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public f(int h, int w, int[] val) {
/* 168 */     if (w < 0 || h < 0 || val.length < w * h) {
/* 169 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 172 */     a(w, h);
/*     */     
/* 174 */     for (int k = w * h - 1; k >= 0; k--) {
/* 175 */       this.d[0][k] = val[k];
/*     */     }
/*     */     
/* 178 */     f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int a() {
/* 189 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int b() {
/* 200 */     return this.b;
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
/*     */   private void a(int w, int h) {
/* 218 */     this.a = w;
/* 219 */     this.b = h;
/*     */     
/* 221 */     if (w == 0 || h == 0) {
/* 222 */       this.c = 0;
/*     */     } else {
/*     */       
/* 225 */       this.c = 1;
/* 226 */       while (h != 1 || w != 1) {
/* 227 */         w = w + 1 >> 1;
/* 228 */         h = h + 1 >> 1;
/* 229 */         this.c++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 234 */     this.d = new int[this.c][];
/* 235 */     this.e = new int[this.c][];
/* 236 */     w = this.a;
/* 237 */     h = this.b;
/* 238 */     for (int i = 0; i < this.c; i++) {
/* 239 */       this.d[i] = new int[h * w];
/* 240 */       this.e[i] = new int[h * w];
/* 241 */       w = w + 1 >> 1;
/* 242 */       h = h + 1 >> 1;
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
/*     */   
/*     */   private void f() {
/* 255 */     for (int k = 0; k < this.c - 1; k++) {
/*     */       
/* 257 */       int lw = this.a + (1 << k) - 1 >> k;
/* 258 */       int lh = this.b + (1 << k) - 1 >> k; int m;
/* 259 */       for (m = (lh >> 1 << 1) - 2; m >= 0; m -= 2) {
/* 260 */         int n; for (n = (lw >> 1 << 1) - 2; n >= 0; n -= 2) {
/*     */ 
/*     */           
/* 263 */           int bi = m * lw + n;
/* 264 */           int tm1 = (this.d[k][bi] < this.d[k][bi + 1]) ? this.d[k][bi] : this.d[k][bi + 1];
/*     */           
/* 266 */           int tm2 = (this.d[k][bi + lw] < this.d[k][bi + lw + 1]) ? this.d[k][bi + lw] : this.d[k][bi + lw + 1];
/*     */           
/* 268 */           this.d[k + 1][(m >> 1) * (lw + 1 >> 1) + (n >> 1)] = (tm1 < tm2) ? tm1 : tm2;
/*     */         } 
/*     */ 
/*     */         
/* 272 */         if (lw % 2 != 0) {
/* 273 */           n = lw >> 1 << 1;
/*     */ 
/*     */           
/* 276 */           int bi = m * lw + n;
/* 277 */           this.d[k + 1][(m >> 1) * (lw + 1 >> 1) + (n >> 1)] = (this.d[k][bi] < this.d[k][bi + lw]) ? this.d[k][bi] : this.d[k][bi + lw];
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 283 */       if (lh % 2 != 0) {
/* 284 */         m = lh >> 1 << 1; int n;
/* 285 */         for (n = (lw >> 1 << 1) - 2; n >= 0; n -= 2) {
/*     */ 
/*     */           
/* 288 */           int bi = m * lw + n;
/* 289 */           this.d[k + 1][(m >> 1) * (lw + 1 >> 1) + (n >> 1)] = (this.d[k][bi] < this.d[k][bi + 1]) ? this.d[k][bi] : this.d[k][bi + 1];
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 294 */         if (lw % 2 != 0) {
/*     */           
/* 296 */           n = lw >> 1 << 1;
/* 297 */           this.d[k + 1][(m >> 1) * (lw + 1 >> 1) + (n >> 1)] = this.d[k][m * lw + n];
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int m, int n, int v) {
/* 320 */     if (this.c == 0 || n < 0 || n >= this.a || v < this.e[this.c - 1][0] || this.d[0][m * this.a + n] < this.e[this.c - 1][0])
/*     */     {
/* 322 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 325 */     this.d[0][m * this.a + n] = v;
/*     */     
/* 327 */     for (int k = 1; k < this.c; ) {
/* 328 */       int idx = (m >> k) * (this.a + (1 << k) - 1 >> k) + (n >> k);
/* 329 */       if (v < this.d[k][idx]) {
/*     */ 
/*     */         
/* 332 */         this.d[k][idx] = v;
/*     */         k++;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int[] val) {
/* 364 */     if (this.c == 0) {
/* 365 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 368 */     int maxt = this.e[this.c - 1][0];
/* 369 */     for (int i = this.a * this.b - 1; i >= 0; i--) {
/* 370 */       if ((this.d[0][i] < maxt || val[i] < maxt) && this.d[0][i] != val[i])
/*     */       {
/* 372 */         throw new IllegalArgumentException();
/*     */       }
/*     */       
/* 375 */       this.d[0][i] = val[i];
/*     */     } 
/*     */     
/* 378 */     f();
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
/*     */   public void a(int m, int n, int t, a out) {
/* 401 */     if (m >= this.b || n >= this.a || t < 0) {
/* 402 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 406 */     int k = this.c - 1;
/* 407 */     int tmin = this.e[k][0];
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 412 */       int idx = (m >> k) * (this.a + (1 << k) - 1 >> k) + (n >> k);
/*     */       
/* 414 */       int ts = this.e[k][idx];
/* 415 */       if (ts < tmin) {
/* 416 */         ts = tmin;
/*     */       }
/* 418 */       while (t > ts) {
/* 419 */         if (this.d[k][idx] > ts) {
/* 420 */           out.a(0);
/*     */         }
/* 422 */         else if (this.d[k][idx] == ts) {
/* 423 */           out.a(1);
/*     */         } else {
/*     */           
/* 426 */           ts = t;
/*     */           
/*     */           break;
/*     */         } 
/* 430 */         ts++;
/*     */       } 
/*     */       
/* 433 */       this.e[k][idx] = ts;
/*     */       
/* 435 */       if (k > 0) {
/* 436 */         tmin = (ts < this.d[k][idx]) ? ts : this.d[k][idx];
/* 437 */         k--;
/*     */         continue;
/*     */       } 
/*     */       break;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/* 457 */     if (this.f == null) {
/*     */ 
/*     */       
/* 460 */       this.f = new int[this.c][];
/* 461 */       this.g = new int[this.c][];
/* 462 */       for (int i = this.c - 1; i >= 0; i--) {
/* 463 */         this.f[i] = new int[(this.d[i]).length];
/* 464 */         this.g[i] = new int[(this.d[i]).length];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 469 */     for (int k = this.d.length - 1; k >= 0; k--) {
/* 470 */       System.arraycopy(this.d[k], 0, this.f[k], 0, (this.d[k]).length);
/* 471 */       System.arraycopy(this.e[k], 0, this.g[k], 0, (this.e[k]).length);
/*     */     } 
/*     */ 
/*     */     
/* 475 */     this.h = true;
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
/*     */   public void d() {
/* 490 */     if (!this.h) {
/* 491 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 495 */     for (int k = this.c - 1; k >= 0; k--) {
/* 496 */       System.arraycopy(this.f[k], 0, this.d[k], 0, (this.d[k]).length);
/* 497 */       System.arraycopy(this.g[k], 0, this.e[k], 0, (this.e[k]).length);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void e() {
/* 512 */     for (int k = this.c - 1; k >= 0; k--) {
/* 513 */       a.a(this.d[k], 2147483647);
/* 514 */       a.a(this.e[k], 0);
/*     */     } 
/*     */     
/* 517 */     this.h = false;
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
/*     */   public void b(int[] val) {
/*     */     int k;
/* 531 */     for (k = this.a * this.b - 1; k >= 0; k--) {
/* 532 */       this.d[0][k] = val[k];
/*     */     }
/*     */     
/* 535 */     f();
/*     */     
/* 537 */     for (k = this.c - 1; k >= 0; k--) {
/* 538 */       a.a(this.e[k], 0);
/*     */     }
/*     */     
/* 541 */     this.h = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/b/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */