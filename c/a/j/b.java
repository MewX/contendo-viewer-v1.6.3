/*     */ package c.a.j;
/*     */ 
/*     */ import java.awt.Point;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class b
/*     */ {
/*     */   public static final int a = 0;
/*     */   public static final int b = 1;
/*     */   public static final int c = 2;
/*     */   public static final int d = 3;
/*     */   public boolean e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int h;
/* 111 */   public Point i = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public int k = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int l;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int o;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int p;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int r;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int s;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract b a();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract b b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract b c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract b d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract b e();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract b a(e parame1, e parame2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void f() {
/* 244 */     b subb_LL = b();
/* 245 */     b subb_HL = c();
/* 246 */     b subb_LH = d();
/* 247 */     b subb_HH = e();
/*     */ 
/*     */     
/* 250 */     this.g++;
/* 251 */     subb_LL.l = this.l + 1 >> 1;
/* 252 */     subb_LL.m = this.m + 1 >> 1;
/* 253 */     subb_LL.n = this.n;
/* 254 */     subb_LL.o = this.o;
/* 255 */     subb_LL.p = (this.l + this.p + 1 >> 1) - subb_LL.l;
/* 256 */     subb_LL.q = (this.m + this.q + 1 >> 1) - subb_LL.m;
/*     */ 
/*     */     
/* 259 */     subb_LL.h = (this.f == 0) ? (this.h - 1) : this.h;
/* 260 */     subb_LL.j = this.j;
/* 261 */     this.k <<= 2;
/*     */     
/* 263 */     subb_HL.f = 1;
/* 264 */     subb_HL.g = subb_LL.g;
/* 265 */     this.l >>= 1;
/* 266 */     subb_HL.m = subb_LL.m;
/* 267 */     this.n += subb_LL.p;
/* 268 */     subb_HL.o = this.o;
/* 269 */     subb_HL.p = (this.l + this.p >> 1) - subb_HL.l;
/* 270 */     subb_HL.q = subb_LL.q;
/* 271 */     subb_HL.h = this.h;
/* 272 */     this.j++;
/* 273 */     subb_HL.k = (this.k << 2) + 1;
/*     */     
/* 275 */     subb_LH.f = 2;
/* 276 */     subb_LH.g = subb_LL.g;
/* 277 */     subb_LH.l = subb_LL.l;
/* 278 */     this.m >>= 1;
/* 279 */     subb_LH.n = this.n;
/* 280 */     this.o += subb_LL.q;
/* 281 */     subb_LH.p = subb_LL.p;
/* 282 */     subb_LH.q = (this.m + this.q >> 1) - subb_LH.m;
/* 283 */     subb_LH.h = this.h;
/* 284 */     this.j++;
/* 285 */     subb_LH.k = (this.k << 2) + 2;
/*     */     
/* 287 */     subb_HH.f = 3;
/* 288 */     subb_HH.g = subb_LL.g;
/* 289 */     subb_HH.l = subb_HL.l;
/* 290 */     subb_HH.m = subb_LH.m;
/* 291 */     subb_HH.n = subb_HL.n;
/* 292 */     subb_HH.o = subb_LH.o;
/* 293 */     subb_HH.p = subb_HL.p;
/* 294 */     subb_HH.q = subb_LH.q;
/* 295 */     subb_HH.h = this.h;
/* 296 */     this.j += 2;
/* 297 */     subb_HH.k = (this.k << 2) + 3;
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
/*     */   public b() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(int w, int h, int ulcx, int ulcy, int lvls, e[] hfilters, e[] vfilters) {
/* 351 */     this.p = w;
/* 352 */     this.q = h;
/* 353 */     this.l = ulcx;
/* 354 */     this.m = ulcy;
/* 355 */     this.h = lvls;
/*     */     
/* 357 */     b cur = this;
/* 358 */     for (int i = 0; i < lvls; i++) {
/* 359 */       int hi = (cur.h <= hfilters.length) ? (cur.h - 1) : (hfilters.length - 1);
/*     */       
/* 361 */       int vi = (cur.h <= vfilters.length) ? (cur.h - 1) : (vfilters.length - 1);
/*     */       
/* 363 */       cur = cur.a(hfilters[hi], vfilters[vi]);
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
/*     */   public b g() {
/*     */     b sb;
/* 379 */     if (this.e) {
/* 380 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 383 */     switch (this.f) {
/*     */       case 0:
/* 385 */         sb = a();
/* 386 */         if (sb == null || sb.h != this.h)
/*     */         {
/* 388 */           return null;
/*     */         }
/*     */         
/* 391 */         return sb.c();
/*     */       
/*     */       case 1:
/* 394 */         return a().d();
/*     */       case 2:
/* 396 */         return a().e();
/*     */       
/*     */       case 3:
/* 399 */         sb = this;
/* 400 */         while (sb.f == 3) {
/* 401 */           sb = sb.a();
/*     */         }
/* 403 */         switch (sb.f) {
/*     */           case 0:
/* 405 */             sb = sb.a();
/* 406 */             if (sb == null || sb.h != this.h)
/*     */             {
/* 408 */               return null;
/*     */             }
/*     */             
/* 411 */             sb = sb.c();
/*     */             break;
/*     */           
/*     */           case 1:
/* 415 */             sb = sb.a().d();
/*     */             break;
/*     */           case 2:
/* 418 */             sb = sb.a().e();
/*     */             break;
/*     */           default:
/* 421 */             throw new Error("You have found a bug in JJ2000");
/*     */         } 
/* 423 */         while (sb.e) {
/* 424 */           sb = sb.b();
/*     */         }
/* 426 */         return sb;
/*     */     } 
/* 428 */     throw new Error("You have found a bug in JJ2000");
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
/*     */   public b h() {
/* 442 */     if (this.g == 0) {
/* 443 */       return null;
/*     */     }
/*     */     
/* 446 */     b sb = this;
/*     */     do {
/* 448 */       sb = sb.a();
/* 449 */       if (sb == null) {
/* 450 */         return null;
/*     */       }
/* 452 */     } while (sb.h == this.h);
/*     */     
/* 454 */     sb = sb.c();
/*     */     
/* 456 */     while (sb.e) {
/* 457 */       sb = sb.b();
/*     */     }
/* 459 */     return sb;
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
/*     */   public b a(int rl, int sbi) {
/* 471 */     b sb = this;
/*     */ 
/*     */     
/* 474 */     if (rl > sb.h || rl < 0) {
/* 475 */       throw new IllegalArgumentException("Resolution level index out of range");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 480 */     if (rl == sb.h && sbi == sb.k) return sb;
/*     */     
/* 482 */     if (sb.k != 0) sb = sb.a();
/*     */     
/* 484 */     for (; sb.h > rl; sb = sb.b());
/* 485 */     for (; sb.h < rl; sb = sb.a());
/*     */     
/* 487 */     switch (sbi)
/*     */     
/*     */     { default:
/* 490 */         return sb;
/*     */       case 1:
/* 492 */         return sb.c();
/*     */       case 2:
/* 494 */         return sb.d();
/*     */       case 3:
/* 496 */         break; }  return sb.e();
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
/*     */   public b b(int x, int y) {
/* 514 */     if (x < this.n || y < this.o || x >= this.n + this.p || y >= this.o + this.q) {
/* 515 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 518 */     b cur = this;
/* 519 */     while (cur.e) {
/* 520 */       b hhs = cur.e();
/*     */       
/* 522 */       if (x < hhs.n) {
/*     */         
/* 524 */         if (y < hhs.o) {
/*     */           
/* 526 */           cur = cur.b();
/*     */           continue;
/*     */         } 
/* 529 */         cur = cur.d();
/*     */         
/*     */         continue;
/*     */       } 
/* 533 */       if (y < hhs.o) {
/*     */         
/* 535 */         cur = cur.c();
/*     */         continue;
/*     */       } 
/* 538 */       cur = cur.e();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 543 */     return cur;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 553 */     String string = "w=" + this.p + ", h=" + this.q + ", ulx=" + this.n + ", uly=" + this.o + ", ulcx= " + this.l + ", ulcy=" + this.m + ", idx=" + this.k + "\norient=" + this.f + ", node=" + this.e + ", level=" + this.g + ", resLvl=" + this.h + ", nomCBlkW=" + this.r + ", nomCBlkH=" + this.s;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     return string;
/*     */   }
/*     */   
/*     */   public abstract e i();
/*     */   
/*     */   public abstract e j();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */