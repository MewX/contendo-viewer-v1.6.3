/*     */ package c.a.h.a;
/*     */ 
/*     */ import c.a.e.b.b;
/*     */ import c.a.e.c;
/*     */ import c.a.e.e;
/*     */ import c.a.g.b.b;
/*     */ import c.a.j.b;
/*     */ import c.a.j.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends c
/*     */ {
/*     */   private b e;
/*     */   private int[][] f;
/*     */   private int[] g;
/*     */   private int[] h;
/*     */   private int[] i;
/*     */   private boolean j;
/*     */   
/*     */   public a(b[] rois, int nrc, b src) {
/*  96 */     super(rois, nrc);
/*  97 */     this.f = new int[nrc][];
/*  98 */     this.e = src;
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
/*     */   public boolean a(e db, b sb, int magbits, int i) {
/* 123 */     int x = db.e;
/* 124 */     int y = db.f;
/* 125 */     int w = db.g;
/* 126 */     int h = db.h;
/* 127 */     int tilew = sb.p;
/* 128 */     int tileh = sb.q;
/* 129 */     int[] maskData = (int[])db.b();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (!this.c[i]) {
/* 135 */       a(sb, magbits, i);
/* 136 */       this.c[i] = true;
/*     */     } 
/* 138 */     if (!this.j) {
/* 139 */       return false;
/*     */     }
/* 141 */     int[] mask = this.f[i];
/*     */ 
/*     */     
/* 144 */     int k = (y + h - 1) * tilew + x + w - 1;
/* 145 */     int bi = w * h - 1;
/* 146 */     int wrap = tilew - w;
/* 147 */     for (int j = h; j > 0; j--) {
/* 148 */       for (int m = w; m > 0; m--, k--, bi--) {
/* 149 */         maskData[bi] = mask[k];
/*     */       }
/* 151 */       k -= wrap;
/*     */     } 
/* 153 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     return "Fast rectangular ROI mask generator";
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
/*     */   public void a(b sb, int magbits, int i) {
/*     */     int[] mask;
/* 178 */     b[] rois = this.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     int tileulx = sb.l;
/* 186 */     int tileuly = sb.m;
/* 187 */     int tilew = sb.p;
/* 188 */     int tileh = sb.q;
/* 189 */     int lineLen = (tilew > tileh) ? tilew : tileh;
/*     */ 
/*     */     
/* 192 */     if (this.f[i] == null || (this.f[i]).length < tilew * tileh) {
/* 193 */       this.f[i] = new int[tilew * tileh];
/* 194 */       mask = this.f[i];
/*     */     } else {
/*     */       
/* 197 */       mask = this.f[i];
/* 198 */       for (int j = tilew * tileh - 1; j >= 0; j--) {
/* 199 */         mask[j] = 0;
/*     */       }
/*     */     } 
/*     */     
/* 203 */     if (this.g == null || this.g.length < (lineLen + 1) / 2)
/* 204 */       this.g = new int[(lineLen + 1) / 2]; 
/* 205 */     if (this.h == null || this.h.length < (lineLen + 1) / 2) {
/* 206 */       this.h = new int[(lineLen + 1) / 2];
/*     */     }
/* 208 */     this.j = false;
/*     */     
/* 210 */     for (int r = rois.length - 1; r >= 0; r--) {
/* 211 */       if ((rois[r]).d == i) {
/* 212 */         int curScalVal = magbits;
/*     */         
/* 214 */         if ((rois[r]).b) {
/* 215 */           b maskPGM = (rois[r]).a;
/*     */           
/* 217 */           if (this.e.getImgWidth() != maskPGM.getImgWidth() || this.e
/* 218 */             .getImgHeight() != maskPGM.getImgHeight()) {
/* 219 */             throw new IllegalArgumentException("Input image and ROI mask must have the same size");
/*     */           }
/*     */ 
/*     */           
/* 223 */           int x = this.e.getImgULX();
/* 224 */           int y = this.e.getImgULY();
/* 225 */           int lrx = x + this.e.getImgWidth() - 1;
/* 226 */           int lry = y + this.e.getImgHeight() - 1;
/* 227 */           if (x <= tileulx + tilew && y <= tileuly + tileh && lrx >= tileulx && lry >= tileuly) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 232 */             x -= tileulx;
/* 233 */             lrx -= tileulx;
/* 234 */             y -= tileuly;
/* 235 */             lry -= tileuly;
/*     */             
/* 237 */             int offx = 0;
/* 238 */             int offy = 0;
/* 239 */             if (x < 0) {
/* 240 */               offx = -x;
/* 241 */               x = 0;
/*     */             } 
/* 243 */             if (y < 0) {
/* 244 */               offy = -y;
/* 245 */               y = 0;
/*     */             } 
/* 247 */             int w = (lrx > tilew - 1) ? (tilew - x) : (lrx + 1 - x);
/* 248 */             int h = (lry > tileh - 1) ? (tileh - y) : (lry + 1 - y);
/*     */ 
/*     */ 
/*     */             
/* 252 */             e srcblk = new e();
/* 253 */             int mDcOff = -b.d;
/* 254 */             int nROIcoeff = 0;
/*     */             
/* 256 */             srcblk.e = offx;
/* 257 */             srcblk.g = w;
/* 258 */             srcblk.h = 1;
/*     */             
/* 260 */             int j = (y + h - 1) * tilew + x + w - 1;
/* 261 */             int maxj = w;
/* 262 */             int wrap = tilew - maxj;
/* 263 */             for (int k = h; k > 0; k--) {
/* 264 */               srcblk.f = offy + k - 1;
/*     */               
/* 266 */               srcblk = (e)maskPGM.getInternCompData((c)srcblk, 0);
/* 267 */               int[] src_data = srcblk.c();
/*     */               
/* 269 */               for (int m = maxj; m > 0; m--, j--) {
/* 270 */                 if (src_data[m - 1] != mDcOff) {
/* 271 */                   mask[j] = curScalVal;
/* 272 */                   nROIcoeff++;
/*     */                 } 
/*     */               } 
/* 275 */               j -= wrap;
/*     */             } 
/*     */             
/* 278 */             if (nROIcoeff != 0) {
/* 279 */               this.j = true;
/*     */             }
/*     */           } 
/* 282 */         } else if ((rois[r]).c) {
/* 283 */           int x = (rois[r]).e;
/* 284 */           int y = (rois[r]).f;
/* 285 */           int lrx = (rois[r]).g + x - 1;
/* 286 */           int lry = (rois[r]).h + y - 1;
/*     */           
/* 288 */           if (x <= tileulx + tilew && y <= tileuly + tileh && lrx >= tileulx && lry >= tileuly) {
/*     */ 
/*     */ 
/*     */             
/* 292 */             this.j = true;
/*     */ 
/*     */             
/* 295 */             x -= tileulx;
/* 296 */             lrx -= tileulx;
/* 297 */             y -= tileuly;
/* 298 */             lry -= tileuly;
/*     */             
/* 300 */             x = (x < 0) ? 0 : x;
/* 301 */             y = (y < 0) ? 0 : y;
/* 302 */             int w = (lrx > tilew - 1) ? (tilew - x) : (lrx + 1 - x);
/* 303 */             int h = (lry > tileh - 1) ? (tileh - y) : (lry + 1 - y);
/*     */             
/* 305 */             int j = (y + h - 1) * tilew + x + w - 1;
/* 306 */             int maxj = w;
/* 307 */             int wrap = tilew - maxj;
/* 308 */             for (int k = h; k > 0; k--) {
/* 309 */               for (int m = maxj; m > 0; m--, j--) {
/* 310 */                 mask[j] = curScalVal;
/*     */               }
/* 312 */               j -= wrap;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 316 */           int cx = (rois[r]).i - tileulx;
/* 317 */           int cy = (rois[r]).j - tileuly;
/* 318 */           int rad = (rois[r]).k;
/* 319 */           int j = tileh * tilew - 1;
/* 320 */           for (int k = tileh - 1; k >= 0; k--) {
/* 321 */             for (int m = tilew - 1; m >= 0; m--, j--) {
/* 322 */               if ((m - cx) * (m - cx) + (k - cy) * (k - cy) < rad * rad) {
/* 323 */                 mask[j] = curScalVal;
/* 324 */                 this.j = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 333 */     if (sb.e) {
/*     */ 
/*     */       
/* 336 */       e vFilter = sb.j();
/* 337 */       e hFilter = sb.i();
/*     */       
/* 339 */       int lvsup = vFilter.e() + vFilter.f();
/*     */       
/* 341 */       int hvsup = vFilter.g() + vFilter.h();
/*     */       
/* 343 */       int lhsup = hFilter.e() + hFilter.f();
/*     */       
/* 345 */       int hhsup = hFilter.g() + hFilter.h();
/* 346 */       lvsup = (lvsup > hvsup) ? lvsup : hvsup;
/* 347 */       lhsup = (lhsup > hhsup) ? lhsup : hhsup;
/* 348 */       lvsup = (lvsup > lhsup) ? lvsup : lhsup;
/* 349 */       this.i = new int[lineLen + lvsup];
/*     */       
/* 351 */       if (this.j) {
/* 352 */         a(sb, tilew, tileh, i);
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
/*     */   private void a(b sb, int tilew, int tileh, int i) {
/* 371 */     int hmax, lmax, ulx = sb.n;
/* 372 */     int uly = sb.o;
/* 373 */     int w = sb.p;
/* 374 */     int h = sb.q;
/* 375 */     int maxVal = 0;
/* 376 */     int mi = 0;
/*     */ 
/*     */     
/* 379 */     int[] mask = this.f[i];
/* 380 */     int[] low = this.g;
/* 381 */     int[] high = this.h;
/* 382 */     int[] padLine = this.i;
/* 383 */     int highFirst = 0;
/*     */ 
/*     */ 
/*     */     
/* 387 */     if (!sb.e) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 394 */     e filter = sb.i();
/* 395 */     int lnSup = filter.e();
/* 396 */     int hnSup = filter.g();
/* 397 */     int lpSup = filter.f();
/* 398 */     int hpSup = filter.h();
/* 399 */     int lsup = lnSup + lpSup + 1;
/* 400 */     int hsup = hnSup + hpSup + 1;
/*     */ 
/*     */     
/* 403 */     highFirst = sb.l % 2;
/* 404 */     if (sb.p % 2 == 0) {
/* 405 */       lmax = w / 2 - 1;
/* 406 */       hmax = lmax;
/*     */     
/*     */     }
/* 409 */     else if (highFirst == 0) {
/* 410 */       lmax = (w + 1) / 2 - 1;
/* 411 */       hmax = w / 2 - 1;
/*     */     } else {
/*     */       
/* 414 */       hmax = (w + 1) / 2 - 1;
/* 415 */       lmax = w / 2 - 1;
/*     */     } 
/*     */ 
/*     */     
/* 419 */     int maxnSup = (lnSup > hnSup) ? lnSup : hnSup;
/* 420 */     int maxpSup = (lpSup > hpSup) ? lpSup : hpSup;
/*     */     
/*     */     int pin;
/*     */     
/* 424 */     for (pin = maxnSup - 1; pin >= 0; pin--)
/* 425 */       padLine[pin] = 0; 
/* 426 */     for (pin = maxnSup + w - 1 + maxpSup; pin >= w; pin--) {
/* 427 */       padLine[pin] = 0;
/*     */     }
/*     */     
/* 430 */     int lineoffs = (uly + h) * tilew + ulx + w - 1; int j;
/* 431 */     for (j = h - 1; j >= 0; j--) {
/* 432 */       lineoffs -= tilew;
/*     */       
/* 434 */       mi = lineoffs; int k;
/* 435 */       for (k = w, pin = w - 1 + maxnSup; k > 0; k--, mi--, pin--) {
/* 436 */         padLine[pin] = mask[mi];
/*     */       }
/*     */       
/* 439 */       int lastpin = maxnSup + highFirst + 2 * lmax + lpSup;
/* 440 */       for (k = lmax; k >= 0; k--, lastpin -= 2) {
/* 441 */         pin = lastpin;
/* 442 */         for (int s = lsup; s > 0; s--, pin--) {
/* 443 */           int scalVal = padLine[pin];
/* 444 */           if (scalVal > maxVal)
/* 445 */             maxVal = scalVal; 
/*     */         } 
/* 447 */         low[k] = maxVal;
/* 448 */         maxVal = 0;
/*     */       } 
/* 450 */       lastpin = maxnSup - highFirst + 2 * hmax + 1 + hpSup;
/* 451 */       for (k = hmax; k >= 0; k--, lastpin -= 2) {
/* 452 */         pin = lastpin;
/* 453 */         for (int s = hsup; s > 0; s--, pin--) {
/* 454 */           int scalVal = padLine[pin];
/* 455 */           if (scalVal > maxVal)
/* 456 */             maxVal = scalVal; 
/*     */         } 
/* 458 */         high[k] = maxVal;
/* 459 */         maxVal = 0;
/*     */       } 
/*     */       
/* 462 */       mi = lineoffs;
/* 463 */       for (k = hmax; k >= 0; k--, mi--) {
/* 464 */         mask[mi] = high[k];
/*     */       }
/* 466 */       for (k = lmax; k >= 0; k--, mi--) {
/* 467 */         mask[mi] = low[k];
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 475 */     filter = sb.j();
/* 476 */     lnSup = filter.e();
/* 477 */     hnSup = filter.g();
/* 478 */     lpSup = filter.f();
/* 479 */     hpSup = filter.h();
/* 480 */     lsup = lnSup + lpSup + 1;
/* 481 */     hsup = hnSup + hpSup + 1;
/*     */ 
/*     */     
/* 484 */     highFirst = sb.m % 2;
/* 485 */     if (sb.q % 2 == 0) {
/* 486 */       lmax = h / 2 - 1;
/* 487 */       hmax = lmax;
/*     */     
/*     */     }
/* 490 */     else if (sb.m % 2 == 0) {
/* 491 */       lmax = (h + 1) / 2 - 1;
/* 492 */       hmax = h / 2 - 1;
/*     */     } else {
/*     */       
/* 495 */       hmax = (h + 1) / 2 - 1;
/* 496 */       lmax = h / 2 - 1;
/*     */     } 
/*     */ 
/*     */     
/* 500 */     maxnSup = (lnSup > hnSup) ? lnSup : hnSup;
/* 501 */     maxpSup = (lpSup > hpSup) ? lpSup : hpSup;
/*     */ 
/*     */     
/* 504 */     for (pin = maxnSup - 1; pin >= 0; pin--)
/* 505 */       padLine[pin] = 0; 
/* 506 */     for (pin = maxnSup + h - 1 + maxpSup; pin >= h; pin--) {
/* 507 */       padLine[pin] = 0;
/*     */     }
/*     */     
/* 510 */     lineoffs = (uly + h - 1) * tilew + ulx + w;
/* 511 */     for (j = w - 1; j >= 0; j--) {
/*     */ 
/*     */       
/* 514 */       mi = --lineoffs; int k;
/* 515 */       for (k = h, pin = k - 1 + maxnSup; k > 0; k--, mi -= tilew, pin--) {
/* 516 */         padLine[pin] = mask[mi];
/*     */       }
/* 518 */       int lastpin = maxnSup + highFirst + 2 * lmax + lpSup;
/* 519 */       for (k = lmax; k >= 0; k--, lastpin -= 2) {
/* 520 */         pin = lastpin;
/* 521 */         for (int s = lsup; s > 0; s--, pin--) {
/* 522 */           int scalVal = padLine[pin];
/* 523 */           if (scalVal > maxVal)
/* 524 */             maxVal = scalVal; 
/*     */         } 
/* 526 */         low[k] = maxVal;
/* 527 */         maxVal = 0;
/*     */       } 
/* 529 */       lastpin = maxnSup - highFirst + 2 * hmax + 1 + hpSup;
/* 530 */       for (k = hmax; k >= 0; k--, lastpin -= 2) {
/* 531 */         pin = lastpin;
/* 532 */         for (int s = hsup; s > 0; s--, pin--) {
/* 533 */           int scalVal = padLine[pin];
/* 534 */           if (scalVal > maxVal)
/* 535 */             maxVal = scalVal; 
/*     */         } 
/* 537 */         high[k] = maxVal;
/* 538 */         maxVal = 0;
/*     */       } 
/*     */       
/* 541 */       mi = lineoffs;
/* 542 */       for (k = hmax; k >= 0; k--, mi -= tilew) {
/* 543 */         mask[mi] = high[k];
/*     */       }
/* 545 */       for (k = lmax; k >= 0; k--, mi -= tilew) {
/* 546 */         mask[mi] = low[k];
/*     */       }
/*     */     } 
/*     */     
/* 550 */     if (sb.e) {
/* 551 */       a(sb.e(), tilew, tileh, i);
/* 552 */       a(sb.d(), tilew, tileh, i);
/* 553 */       a(sb.c(), tilew, tileh, i);
/* 554 */       a(sb.b(), tilew, tileh, i);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */