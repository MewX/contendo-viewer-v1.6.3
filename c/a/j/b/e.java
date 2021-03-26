/*     */ package c.a.j.b;
/*     */ 
/*     */ import c.a.b.a;
/*     */ import c.a.e.c;
/*     */ import c.a.e.d;
/*     */ import c.a.i.c;
/*     */ import c.a.i.i;
/*     */ import c.a.j.b;
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
/*     */ public class e
/*     */   extends f
/*     */ {
/*  87 */   private i g = null;
/*     */ 
/*     */   
/*  90 */   private int h = 0;
/*     */ 
/*     */   
/*  93 */   private int i = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int k;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c[] l;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] m;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean[][] n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e(a src, a decSpec) {
/* 128 */     super(src, decSpec);
/* 129 */     this.j = src;
/*     */     
/* 131 */     int nc = src.c();
/* 132 */     this.l = new c[nc];
/* 133 */     this.m = new int[nc];
/* 134 */     this.g = c.a();
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
/*     */   private boolean a(b subband) {
/* 149 */     if (subband.e)
/*     */     {
/*     */       
/* 152 */       return (
/* 153 */         a(subband.b()) && 
/* 154 */         a(subband.c()) && 
/* 155 */         a(subband.d()) && 
/* 156 */         a(subband.e()) && ((i)subband).y
/* 157 */         .k() && ((i)subband).z
/* 158 */         .k());
/*     */     }
/*     */ 
/*     */     
/* 162 */     return true;
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
/*     */   public boolean d(int t, int j) {
/* 179 */     if (this.n[t] == null) {
/*     */       
/* 181 */       this.n[t] = new boolean[getNumComps()];
/* 182 */       for (int k = this.n.length - 1; k >= 0; k--) {
/* 183 */         this.n[t][k] = 
/* 184 */           a(this.j.f(t, k));
/*     */       }
/*     */     } 
/* 187 */     return this.n[t][j];
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
/*     */   public int getNomRangeBits(int j) {
/* 211 */     return this.j.i(j);
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
/*     */   public int getFixedPoint(int j) {
/* 233 */     return this.j.j(j);
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
/*     */   public final c getInternCompData(c blk, int j) {
/*     */     d d;
/* 263 */     int tIdx = getTileIdx();
/* 264 */     if (this.j.f(tIdx, j).i() == null) {
/* 265 */       this.k = 3;
/*     */     } else {
/* 267 */       this
/* 268 */         .k = this.j.f(tIdx, j).i().j();
/*     */     } 
/*     */ 
/*     */     
/* 272 */     if (this.l[j] == null) {
/*     */       
/* 274 */       switch (this.k) {
/*     */         case 4:
/* 276 */           this.l[j] = (c)new d(0, 0, 
/* 277 */               getTileCompWidth(tIdx, j), 
/* 278 */               getTileCompHeight(tIdx, j));
/*     */           break;
/*     */         case 3:
/* 281 */           this.l[j] = (c)new c.a.e.e(0, 0, 
/* 282 */               getTileCompWidth(tIdx, j), 
/* 283 */               getTileCompHeight(tIdx, j));
/*     */           break;
/*     */       } 
/*     */       
/* 287 */       b(this.l[j], this.j
/* 288 */           .f(tIdx, j), j);
/* 289 */       if (this.g != null && j == this.j.c() - 1) {
/* 290 */         this.g.a();
/*     */       }
/*     */     } 
/*     */     
/* 294 */     if (blk.a() != this.k) {
/* 295 */       c.a.e.e e1; if (this.k == 3) {
/* 296 */         e1 = new c.a.e.e(blk.e, blk.f, blk.g, blk.h);
/*     */       } else {
/* 298 */         d = new d(((c)e1).e, ((c)e1).f, ((c)e1).g, ((c)e1).h);
/*     */       } 
/*     */     } 
/*     */     
/* 302 */     d.a(this.l[j].b());
/* 303 */     ((c)d).i = (this.l[j]).g * ((c)d).f + ((c)d).e;
/* 304 */     ((c)d).j = (this.l[j]).g;
/* 305 */     ((c)d).k = false;
/* 306 */     return (c)d;
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
/*     */   public c getCompData(c blk, int j) {
/*     */     int[] dst_data_int;
/*     */     float[] dst_data_float;
/* 349 */     Object dst_data = null;
/*     */ 
/*     */     
/* 352 */     switch (blk.a()) {
/*     */       case 3:
/* 354 */         dst_data_int = (int[])blk.b();
/* 355 */         if (dst_data_int == null || dst_data_int.length < blk.g * blk.h) {
/* 356 */           dst_data_int = new int[blk.g * blk.h];
/*     */         }
/* 358 */         dst_data = dst_data_int;
/*     */         break;
/*     */       case 4:
/* 361 */         dst_data_float = (float[])blk.b();
/* 362 */         if (dst_data_float == null || dst_data_float.length < blk.g * blk.h) {
/* 363 */           dst_data_float = new float[blk.g * blk.h];
/*     */         }
/* 365 */         dst_data = dst_data_float;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 371 */     blk = getInternCompData(blk, j);
/*     */ 
/*     */     
/* 374 */     blk.a(dst_data);
/* 375 */     blk.i = 0;
/* 376 */     blk.j = blk.g;
/* 377 */     return blk;
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
/*     */   private void a(c db, i sb, int j) {
/*     */     int k, data_int[], buf_int[];
/*     */     float[] data_float, buf_float;
/* 399 */     if (sb.p == 0 || sb.q == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 403 */     Object data = db.b();
/*     */     
/* 405 */     int ulx = sb.n;
/* 406 */     int uly = sb.o;
/* 407 */     int w = sb.p;
/* 408 */     int h = sb.q;
/*     */     
/* 410 */     Object buf = null;
/*     */     
/* 412 */     switch (sb.i().j()) {
/*     */       case 3:
/* 414 */         buf = new int[(w >= h) ? w : h];
/*     */         break;
/*     */       case 4:
/* 417 */         buf = new float[(w >= h) ? w : h];
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 422 */     int offset = (uly - db.f) * db.g + ulx - db.e;
/* 423 */     if (sb.l % 2 == 0) {
/* 424 */       for (int m = 0; m < h; m++, offset += db.g) {
/* 425 */         System.arraycopy(data, offset, buf, 0, w);
/* 426 */         sb.y.a(buf, 0, (w + 1) / 2, 1, buf, (w + 1) / 2, w / 2, 1, data, offset, 1);
/*     */       } 
/*     */     } else {
/*     */       
/* 430 */       for (int m = 0; m < h; m++, offset += db.g) {
/* 431 */         System.arraycopy(data, offset, buf, 0, w);
/* 432 */         sb.y.b(buf, 0, w / 2, 1, buf, w / 2, (w + 1) / 2, 1, data, offset, 1);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 438 */     offset = (uly - db.f) * db.g + ulx - db.e;
/* 439 */     switch (sb.j().j()) {
/*     */       
/*     */       case 3:
/* 442 */         data_int = (int[])data;
/* 443 */         buf_int = (int[])buf;
/* 444 */         if (sb.m % 2 == 0) {
/* 445 */           for (int m = 0; m < w; m++, offset++) {
/* 446 */             int i1; for (int n = h - 1; n >= 0; n--, i1 -= db.g)
/* 447 */               buf_int[n] = data_int[i1]; 
/* 448 */             sb.z.a(buf, 0, (h + 1) / 2, 1, buf, (h + 1) / 2, h / 2, 1, data, offset, db.g);
/*     */           } 
/*     */           break;
/*     */         } 
/* 452 */         for (k = 0; k < w; k++, offset++) {
/* 453 */           int n; for (int m = h - 1; m >= 0; m--, n -= db.g)
/* 454 */             buf_int[m] = data_int[n]; 
/* 455 */           sb.z.b(buf, 0, h / 2, 1, buf, h / 2, (h + 1) / 2, 1, data, offset, db.g);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 462 */         data_float = (float[])data;
/* 463 */         buf_float = (float[])buf;
/* 464 */         if (sb.m % 2 == 0) {
/* 465 */           for (k = 0; k < w; k++, offset++) {
/* 466 */             int n; for (int m = h - 1; m >= 0; m--, n -= db.g)
/* 467 */               buf_float[m] = data_float[n]; 
/* 468 */             sb.z.a(buf, 0, (h + 1) / 2, 1, buf, (h + 1) / 2, h / 2, 1, data, offset, db.g);
/*     */           } 
/*     */           break;
/*     */         } 
/* 472 */         for (k = 0; k < w; k++, offset++) {
/* 473 */           int n; for (int m = h - 1; m >= 0; m--, n -= db.g)
/* 474 */             buf_float[m] = data_float[n]; 
/* 475 */           sb.z.b(buf, 0, h / 2, 1, buf, h / 2, (h + 1) / 2, 1, data, offset, db.g);
/*     */         } 
/*     */         break;
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
/*     */   private void b(c img, i sb, int j) {
/* 501 */     if (!sb.e) {
/*     */       d d;
/*     */ 
/*     */ 
/*     */       
/* 506 */       if (sb.p == 0 || sb.q == 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 511 */       if (this.k == 3) {
/* 512 */         c.a.e.e e1 = new c.a.e.e();
/*     */       } else {
/* 514 */         d = new d();
/*     */       } 
/* 516 */       Point ncblks = sb.i;
/* 517 */       Object dst_data = img.b();
/* 518 */       for (int m = 0; m < ncblks.y; m++) {
/* 519 */         for (int n = 0; n < ncblks.x; n++) {
/* 520 */           c c1 = this.j.b(j, m, n, sb, (c)d);
/* 521 */           Object src_data = c1.b();
/* 522 */           if (this.g != null) {
/* 523 */             this.i++;
/* 524 */             this.g.a(this.i, null);
/*     */           } 
/*     */           
/* 527 */           for (int k = c1.h - 1; k >= 0; k--) {
/* 528 */             System.arraycopy(src_data, c1.i + k * c1.j, dst_data, (c1.f + k) * img.g + c1.e, c1.g);
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 536 */     else if (sb.e) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 541 */       b(img, (i)sb.b(), j);
/*     */       
/* 543 */       if (sb.h <= this.e - this.f + this.m[j]) {
/*     */         
/* 545 */         b(img, (i)sb.c(), j);
/* 546 */         b(img, (i)sb.d(), j);
/* 547 */         b(img, (i)sb.e(), j);
/*     */ 
/*     */         
/* 550 */         a(img, sb, j);
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
/*     */   public int a(int j) {
/* 566 */     return 2;
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
/*     */   public void setTile(int x, int y) {
/* 582 */     super.setTile(x, y);
/*     */     
/* 584 */     int nc = this.j.c();
/* 585 */     int tIdx = this.j.e();
/* 586 */     for (int j = 0; j < nc; j++) {
/* 587 */       this.m[j] = (this.j.f(tIdx, j)).h;
/*     */     }
/*     */ 
/*     */     
/* 591 */     if (this.l != null) {
/* 592 */       for (int m = this.l.length - 1; m >= 0; m--) {
/* 593 */         this.l[m] = null;
/*     */       }
/*     */     }
/*     */     
/* 597 */     this.h = 0;
/*     */     
/* 599 */     for (int k = 0; k < nc; k++) {
/* 600 */       i root = this.j.f(tIdx, k);
/* 601 */       for (int r = 0; r <= this.e - this.f + root.h; r++) {
/* 602 */         if (r == 0) {
/* 603 */           i sb = (i)root.a(0, 0);
/* 604 */           if (sb != null) this.h += sb.i.x * sb.i.y; 
/*     */         } else {
/* 606 */           i sb = (i)root.a(r, 1);
/* 607 */           if (sb != null) this.h += sb.i.x * sb.i.y; 
/* 608 */           sb = (i)root.a(r, 2);
/* 609 */           if (sb != null) this.h += sb.i.x * sb.i.y; 
/* 610 */           sb = (i)root.a(r, 3);
/* 611 */           if (sb != null) this.h += sb.i.x * sb.i.y; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 615 */     this.i = 0;
/*     */     
/* 617 */     if (this.g != null) {
/* 618 */       this.g.a(0, this.h, "Decoding tile " + tIdx + "...");
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
/*     */   public void nextTile() {
/* 631 */     super.nextTile();
/*     */     
/* 633 */     int nc = this.j.c();
/* 634 */     int tIdx = this.j.e();
/* 635 */     for (int j = 0; j < nc; j++) {
/* 636 */       this.m[j] = (this.j.f(tIdx, j)).h;
/*     */     }
/*     */ 
/*     */     
/* 640 */     if (this.l != null)
/* 641 */       for (int k = this.l.length - 1; k >= 0; k--)
/* 642 */         this.l[k] = null;  
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */