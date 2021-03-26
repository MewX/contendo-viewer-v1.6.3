/*     */ package c.a.e.c;
/*     */ 
/*     */ import c.a.e.a;
/*     */ import c.a.e.b;
/*     */ import c.a.e.c;
/*     */ import c.a.e.d;
/*     */ import c.a.e.e;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import c.a.i.e;
/*     */ import c.a.j.b.o;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends g
/*     */   implements a
/*     */ {
/*     */   public static final int a = 0;
/*     */   public static final char b = 'M';
/*  78 */   private static final String[][] e = (String[][])null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int c = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int d = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   private a f;
/*     */ 
/*     */   
/*     */   private b g;
/*     */ 
/*     */   
/*     */   private o h;
/*     */ 
/*     */   
/*  99 */   private int i = 0;
/*     */ 
/*     */   
/* 102 */   private int[][] j = new int[3][];
/*     */ 
/*     */ 
/*     */   
/*     */   private c m;
/*     */ 
/*     */   
/*     */   private c n;
/*     */ 
/*     */   
/*     */   private c o;
/*     */ 
/*     */   
/* 115 */   private e p = new e();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean r = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(a imgSrc, c.a.b.a decSpec, int[] utdepth) {
/* 137 */     super((f)imgSrc);
/* 138 */     this.g = decSpec.k;
/* 139 */     this.h = decSpec.f;
/* 140 */     this.f = imgSrc;
/* 141 */     this.q = utdepth;
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
/*     */   public static String[][] a() {
/* 158 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 169 */     switch (this.i) {
/*     */       case 1:
/* 171 */         return "Inverse RCT";
/*     */       case 2:
/* 173 */         return "Inverse ICT";
/*     */       case 0:
/* 175 */         return "No component transformation";
/*     */     } 
/* 177 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   public boolean b() {
/* 192 */     switch (this.i) {
/*     */       case 0:
/*     */       case 1:
/* 195 */         return true;
/*     */       case 2:
/* 197 */         return false;
/*     */     } 
/* 199 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   public int getFixedPoint(int i) {
/* 222 */     return this.f.getFixedPoint(i);
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
/*     */   public static int[] a(int[] utdepth, int ttype, int[] tdepth) {
/* 242 */     if (utdepth.length < 3 && ttype != 0) {
/* 243 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 246 */     if (tdepth == null) {
/* 247 */       tdepth = new int[utdepth.length];
/*     */     }
/*     */     
/* 250 */     switch (ttype) {
/*     */       case 0:
/* 252 */         System.arraycopy(utdepth, 0, tdepth, 0, utdepth.length);
/*     */         break;
/*     */       case 1:
/* 255 */         if (utdepth.length > 3) {
/* 256 */           System.arraycopy(utdepth, 3, tdepth, 3, utdepth.length - 3);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 266 */         tdepth[0] = e.a((1 << utdepth[0]) + (2 << utdepth[1]) + (1 << utdepth[2]) - 1) - 2 + 1;
/*     */         
/* 268 */         tdepth[1] = e.a((1 << utdepth[2]) + (1 << utdepth[1]) - 1) + 1;
/* 269 */         tdepth[2] = e.a((1 << utdepth[0]) + (1 << utdepth[1]) - 1) + 1;
/*     */         break;
/*     */       case 2:
/* 272 */         if (utdepth.length > 3) {
/* 273 */           System.arraycopy(utdepth, 3, tdepth, 3, utdepth.length - 3);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 278 */         tdepth[0] = 
/* 279 */           e.a((int)Math.floor((1 << utdepth[0]) * 0.299072D + (1 << utdepth[1]) * 0.586914D + (1 << utdepth[2]) * 0.114014D) - 1) + 1;
/*     */ 
/*     */         
/* 282 */         tdepth[1] = 
/* 283 */           e.a((int)Math.floor((1 << utdepth[0]) * 0.168701D + (1 << utdepth[1]) * 0.331299D + (1 << utdepth[2]) * 0.5D) - 1) + 1;
/*     */ 
/*     */         
/* 286 */         tdepth[2] = 
/* 287 */           e.a((int)Math.floor((1 << utdepth[0]) * 0.5D + (1 << utdepth[1]) * 0.418701D + (1 << utdepth[2]) * 0.081299D) - 1) + 1;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 293 */     return tdepth;
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
/*     */   public int getNomRangeBits(int i) {
/* 308 */     return this.q[i];
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
/*     */   public c getCompData(c blk, int i) {
/* 333 */     if (i >= 3 || this.i == 0) {
/* 334 */       return this.f.getCompData(blk, i);
/*     */     }
/*     */     
/* 337 */     return getInternCompData(blk, i);
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
/*     */   public c getInternCompData(c blk, int i) {
/* 360 */     if (this.r) {
/* 361 */       return this.f.getInternCompData(blk, i);
/*     */     }
/* 363 */     switch (this.i) {
/*     */       case 0:
/* 365 */         return this.f.getInternCompData(blk, i);
/*     */       case 1:
/* 367 */         return a(blk, i);
/*     */       case 2:
/* 369 */         return b(blk, i);
/*     */     } 
/* 371 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   private c a(c blk, int i) {
/* 389 */     if (i >= 3 && i < getNumComps())
/*     */     {
/* 391 */       return this.f.getInternCompData(blk, i);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (this.j[i] == null || this.p.e > blk.e || this.p.f > blk.f || this.p.e + this.p.g < blk.e + blk.g || this.p.f + this.p.h < blk.f + blk.h) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 401 */       int w = blk.g;
/* 402 */       int h = blk.h;
/*     */ 
/*     */       
/* 405 */       this.j[i] = (int[])blk.b();
/*     */ 
/*     */       
/* 408 */       if (this.j[i] == null || (this.j[i]).length != h * w) {
/* 409 */         this.j[i] = new int[h * w];
/* 410 */         blk.a(this.j[i]);
/*     */       } 
/*     */       
/* 413 */       this.j[(i + 1) % 3] = new int[(this.j[i]).length];
/* 414 */       this.j[(i + 2) % 3] = new int[(this.j[i]).length];
/*     */       
/* 416 */       if (this.m == null || this.m.a() != 3)
/* 417 */         this.m = (c)new e(); 
/* 418 */       if (this.n == null || this.n.a() != 3)
/* 419 */         this.n = (c)new e(); 
/* 420 */       if (this.o == null || this.o.a() != 3)
/* 421 */         this.o = (c)new e(); 
/* 422 */       this.o.g = blk.g;
/* 423 */       this.o.h = blk.h;
/* 424 */       this.o.e = blk.e;
/* 425 */       this.o.f = blk.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 431 */       this.m = this.f.getInternCompData(this.m, 0);
/* 432 */       int[] data0 = (int[])this.m.b();
/* 433 */       this.n = this.f.getInternCompData(this.n, 1);
/* 434 */       int[] data1 = (int[])this.n.b();
/* 435 */       this.o = this.f.getInternCompData(this.o, 2);
/* 436 */       int[] data2 = (int[])this.o.b();
/*     */ 
/*     */       
/* 439 */       blk.k = (this.m.k || this.n.k || this.o.k);
/*     */       
/* 441 */       blk.i = 0;
/* 442 */       blk.j = w;
/*     */ 
/*     */       
/* 445 */       this.p.k = blk.k;
/* 446 */       this.p.e = blk.e;
/* 447 */       this.p.f = blk.f;
/* 448 */       this.p.g = blk.g;
/* 449 */       this.p.h = blk.h;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 454 */       int k = w * h - 1;
/* 455 */       int k0 = this.m.i + (h - 1) * this.m.j + w - 1;
/* 456 */       int k1 = this.n.i + (h - 1) * this.n.j + w - 1;
/* 457 */       int k2 = this.o.i + (h - 1) * this.o.j + w - 1;
/*     */       
/* 459 */       for (int j = h - 1; j >= 0; j--) {
/* 460 */         for (int mink = k - w; k > mink; k--, k0--, k1--, k2--) {
/* 461 */           this.j[1][k] = data0[k0] - (data1[k1] + data2[k2] >> 2);
/* 462 */           this.j[0][k] = data2[k2] + this.j[1][k];
/* 463 */           this.j[2][k] = data1[k1] + this.j[1][k];
/*     */         } 
/*     */         
/* 466 */         k0 -= this.m.j - w;
/* 467 */         k1 -= this.n.j - w;
/* 468 */         k2 -= this.o.j - w;
/*     */       } 
/* 470 */       this.j[i] = null;
/*     */     }
/* 472 */     else if (i >= 0 && i <= 3) {
/* 473 */       blk.a(this.j[i]);
/* 474 */       blk.k = this.p.k;
/* 475 */       blk.i = (blk.f - this.p.f) * this.p.g + blk.e - this.p.e;
/* 476 */       blk.j = this.p.g;
/* 477 */       this.j[i] = null;
/*     */     }
/*     */     else {
/*     */       
/* 481 */       throw new IllegalArgumentException();
/*     */     } 
/* 483 */     return blk;
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
/*     */   private c b(c blk, int i) {
/* 498 */     if (i >= 3 && i < getNumComps()) {
/*     */ 
/*     */       
/* 501 */       int w = blk.g;
/* 502 */       int h = blk.h;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 507 */       int[] outdata = (int[])blk.b();
/*     */ 
/*     */       
/* 510 */       if (outdata == null) {
/* 511 */         outdata = new int[h * w];
/* 512 */         blk.a(outdata);
/*     */       } 
/*     */ 
/*     */       
/* 516 */       d indb = new d(blk.e, blk.f, w, h);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 521 */       this.f.getInternCompData((c)indb, i);
/* 522 */       float[] indata = (float[])indb.b();
/*     */ 
/*     */       
/* 525 */       int k = w * h - 1;
/* 526 */       int k0 = indb.i + (h - 1) * indb.j + w - 1;
/* 527 */       for (int j = h - 1; j >= 0; j--) {
/* 528 */         for (int mink = k - w; k > mink; k--, k0--) {
/* 529 */           outdata[k] = (int)indata[k0];
/*     */         }
/*     */         
/* 532 */         k0 -= indb.j - w;
/*     */       } 
/*     */ 
/*     */       
/* 536 */       blk.k = indb.k;
/* 537 */       blk.i = 0;
/* 538 */       blk.j = w;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 543 */     else if (this.j[i] == null || this.p.e > blk.e || this.p.f > blk.f || this.p.e + this.p.g < blk.e + blk.g || this.p.f + this.p.h < blk.f + blk.h) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 548 */       int w = blk.g;
/* 549 */       int h = blk.h;
/*     */ 
/*     */       
/* 552 */       this.j[i] = (int[])blk.b();
/*     */ 
/*     */       
/* 555 */       if (this.j[i] == null || (this.j[i]).length != w * h) {
/* 556 */         this.j[i] = new int[h * w];
/* 557 */         blk.a(this.j[i]);
/*     */       } 
/*     */       
/* 560 */       this.j[(i + 1) % 3] = new int[(this.j[i]).length];
/* 561 */       this.j[(i + 2) % 3] = new int[(this.j[i]).length];
/*     */       
/* 563 */       if (this.m == null || this.m.a() != 4)
/* 564 */         this.m = (c)new d(); 
/* 565 */       if (this.o == null || this.o.a() != 4)
/* 566 */         this.o = (c)new d(); 
/* 567 */       if (this.n == null || this.n.a() != 4)
/* 568 */         this.n = (c)new d(); 
/* 569 */       this.n.g = blk.g;
/* 570 */       this.n.h = blk.h;
/* 571 */       this.n.e = blk.e;
/* 572 */       this.n.f = blk.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 578 */       this.m = this.f.getInternCompData(this.m, 0);
/* 579 */       float[] data0 = (float[])this.m.b();
/* 580 */       this.o = this.f.getInternCompData(this.o, 1);
/* 581 */       float[] data2 = (float[])this.o.b();
/* 582 */       this.n = this.f.getInternCompData(this.n, 2);
/* 583 */       float[] data1 = (float[])this.n.b();
/*     */ 
/*     */       
/* 586 */       blk.k = (this.m.k || this.n.k || this.o.k);
/*     */       
/* 588 */       blk.i = 0;
/* 589 */       blk.j = w;
/*     */ 
/*     */       
/* 592 */       this.p.k = blk.k;
/* 593 */       this.p.e = blk.e;
/* 594 */       this.p.f = blk.f;
/* 595 */       this.p.g = blk.g;
/* 596 */       this.p.h = blk.h;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 601 */       int k = w * h - 1;
/* 602 */       int k0 = this.m.i + (h - 1) * this.m.j + w - 1;
/* 603 */       int k2 = this.o.i + (h - 1) * this.o.j + w - 1;
/* 604 */       int k1 = this.n.i + (h - 1) * this.n.j + w - 1;
/*     */       
/* 606 */       for (int j = h - 1; j >= 0; j--) {
/* 607 */         for (int mink = k - w; k > mink; k--, k0--, k2--, k1--) {
/* 608 */           this.j[0][k] = (int)(data0[k0] + 1.402F * data1[k1] + 0.5F);
/* 609 */           this.j[1][k] = (int)(data0[k0] - 0.34413F * data2[k2] - 0.71414F * data1[k1] + 0.5F);
/*     */ 
/*     */           
/* 612 */           this.j[2][k] = (int)(data0[k0] + 1.772F * data2[k2] + 0.5F);
/*     */         } 
/*     */         
/* 615 */         k0 -= this.m.j - w;
/* 616 */         k2 -= this.o.j - w;
/* 617 */         k1 -= this.n.j - w;
/*     */       } 
/* 619 */       this.j[i] = null;
/*     */     }
/* 621 */     else if (i >= 0 && i <= 3) {
/* 622 */       blk.a(this.j[i]);
/* 623 */       blk.k = this.p.k;
/* 624 */       blk.i = (blk.f - this.p.f) * this.p.g + blk.e - this.p.e;
/* 625 */       blk.j = this.p.g;
/* 626 */       this.j[i] = null;
/*     */     } else {
/*     */       
/* 629 */       throw new IllegalArgumentException();
/*     */     } 
/* 631 */     return blk;
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
/*     */   public void setTile(int x, int y) {
/* 648 */     this.f.setTile(x, y);
/* 649 */     this.k = getTileIdx();
/*     */ 
/*     */     
/* 652 */     if (((Integer)this.g.f(this.k)).intValue() == 0) {
/* 653 */       this.i = 0;
/*     */     } else {
/* 655 */       int nc = (this.f.getNumComps() > 3) ? 3 : this.f.getNumComps();
/* 656 */       int rev = 0;
/* 657 */       for (int i = 0; i < nc; i++)
/* 658 */         rev += this.h.h(this.k, i) ? 1 : 0; 
/* 659 */       if (rev == 3) {
/*     */         
/* 661 */         this.i = 1;
/*     */       }
/* 663 */       else if (rev == 0) {
/*     */         
/* 665 */         this.i = 2;
/*     */       }
/*     */       else {
/*     */         
/* 669 */         throw new IllegalArgumentException("Wavelet transformation and component transformation not coherent in tile" + this.k);
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
/*     */   public void nextTile() {
/* 687 */     this.f.nextTile();
/* 688 */     this.k = getTileIdx();
/*     */ 
/*     */     
/* 691 */     if (((Integer)this.g.f(this.k)).intValue() == 0) {
/* 692 */       this.i = 0;
/*     */     } else {
/* 694 */       int nc = (this.f.getNumComps() > 3) ? 3 : this.f.getNumComps();
/* 695 */       int rev = 0;
/* 696 */       for (int i = 0; i < nc; i++)
/* 697 */         rev += this.h.h(this.k, i) ? 1 : 0; 
/* 698 */       if (rev == 3) {
/*     */         
/* 700 */         this.i = 1;
/*     */       }
/* 702 */       else if (rev == 0) {
/*     */         
/* 704 */         this.i = 2;
/*     */       }
/*     */       else {
/*     */         
/* 708 */         throw new IllegalArgumentException("Wavelet transformation and component transformation not coherent in tile" + this.k);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/c/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */