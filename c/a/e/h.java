/*     */ package c.a.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class h
/*     */   extends g
/*     */   implements a
/*     */ {
/*  64 */   private c a = new e();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h(a imgSrc, int fp) {
/*  83 */     super(imgSrc);
/*  84 */     this.b = imgSrc;
/*  85 */     this.c = fp;
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
/*     */   public h(a imgSrc) {
/*  97 */     super(imgSrc);
/*  98 */     this.b = imgSrc;
/*  99 */     this.c = 0;
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
/*     */   public int getFixedPoint(int i) {
/* 117 */     return this.c;
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
/*     */   public c getCompData(c blk, int i) {
/* 157 */     return a(blk, i, false);
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
/*     */   public final c getInternCompData(c blk, int i) {
/* 203 */     return a(blk, i, true);
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
/*     */   private c a(c blk, int i, boolean intern) {
/*     */     c reqBlk;
/*     */     float[] farr;
/*     */     int[] srcIArr, iarr;
/*     */     float[] srcFArr;
/* 228 */     int otype = blk.a();
/*     */     
/* 230 */     if (otype == this.a.a()) {
/*     */       
/* 232 */       reqBlk = blk;
/*     */     }
/*     */     else {
/*     */       
/* 236 */       reqBlk = this.a;
/*     */       
/* 238 */       reqBlk.e = blk.e;
/* 239 */       reqBlk.f = blk.f;
/* 240 */       reqBlk.g = blk.g;
/* 241 */       reqBlk.h = blk.h;
/*     */     } 
/*     */ 
/*     */     
/* 245 */     if (intern) {
/*     */       
/* 247 */       this.a = this.b.getInternCompData(reqBlk, i);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 254 */       this.a = this.b.getCompData(reqBlk, i);
/*     */     } 
/*     */ 
/*     */     
/* 258 */     if (this.a.a() == otype) {
/* 259 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     int w = this.a.g;
/* 266 */     int j = this.a.h;
/*     */     
/* 268 */     switch (otype) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 275 */         farr = (float[])blk.b();
/* 276 */         if (farr == null || farr.length < w * j) {
/* 277 */           farr = new float[w * j];
/* 278 */           blk.a(farr);
/*     */         } 
/*     */         
/* 281 */         blk.j = this.a.g;
/* 282 */         blk.i = 0;
/* 283 */         blk.k = this.a.k;
/* 284 */         srcIArr = (int[])this.a.b();
/*     */ 
/*     */         
/* 287 */         this.c = this.b.getFixedPoint(i);
/* 288 */         if (this.c != 0) {
/* 289 */           float mult = 1.0F / (1 << this.c);
/* 290 */           int m = j - 1, k = w * j - 1, kSrc = this.a.i + (j - 1) * this.a.j + w - 1;
/* 291 */           for (; m >= 0; m--) {
/* 292 */             for (int kmin = k - w; k > kmin; k--, kSrc--) {
/* 293 */               farr[k] = srcIArr[kSrc] * mult;
/*     */             }
/*     */             
/* 296 */             kSrc -= this.a.j - w;
/*     */           } 
/*     */         } else {
/*     */           
/* 300 */           int m = j - 1, k = w * j - 1, kSrc = this.a.i + (j - 1) * this.a.j + w - 1;
/* 301 */           for (; m >= 0; m--) {
/* 302 */             for (int kmin = k - w; k > kmin; k--, kSrc--) {
/* 303 */               farr[k] = srcIArr[kSrc];
/*     */             }
/*     */             
/* 306 */             kSrc -= this.a.j - w;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 365 */         return blk;case 3: iarr = (int[])blk.b(); if (iarr == null || iarr.length < w * j) { iarr = new int[w * j]; blk.a(iarr); }  blk.j = this.a.g; blk.i = 0; blk.k = this.a.k; srcFArr = (float[])this.a.b(); if (this.c != 0) { float mult = (1 << this.c); int m = j - 1, k = w * j - 1, kSrc = this.a.i + (j - 1) * this.a.j + w - 1; for (; m >= 0; m--) { for (int kmin = k - w; k > kmin; k--, kSrc--) { if (srcFArr[kSrc] > 0.0F) { iarr[k] = (int)(srcFArr[kSrc] * mult + 0.5F); } else { iarr[k] = (int)(srcFArr[kSrc] * mult - 0.5F); }  }  kSrc -= this.a.j - w; }  } else { int m = j - 1, k = w * j - 1, kSrc = this.a.i + (j - 1) * this.a.j + w - 1; for (; m >= 0; m--) { for (int kmin = k - w; k > kmin; k--, kSrc--) { if (srcFArr[kSrc] > 0.0F) { iarr[k] = (int)(srcFArr[kSrc] + 0.5F); } else { iarr[k] = (int)(srcFArr[kSrc] - 0.5F); }  }  kSrc -= this.a.j - w; }  }  return blk;
/*     */     } 
/*     */     throw new IllegalArgumentException("Only integer and float data are supported by JJ2000");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/h.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */