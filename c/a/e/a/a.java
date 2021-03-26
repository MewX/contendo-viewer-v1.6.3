/*     */ package c.a.e.a;
/*     */ 
/*     */ import c.a.e.a;
/*     */ import c.a.e.b;
/*     */ import c.a.e.c;
/*     */ import c.a.e.d;
/*     */ import c.a.e.e;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import c.a.i.e;
/*     */ import c.a.j.a.f;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static final int b = 1;
/*     */   public static final int c = 2;
/*     */   private a e;
/*     */   private b f;
/*     */   private f g;
/*  93 */   private int h = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] i;
/*     */ 
/*     */ 
/*     */   
/*     */   private c j;
/*     */ 
/*     */ 
/*     */   
/*     */   private e m;
/*     */ 
/*     */ 
/*     */   
/*     */   private e n;
/*     */ 
/*     */ 
/*     */   
/*     */   private e o;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final char d = 'M';
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(a imgSrc, J2KImageWriteParamJava wp) {
/* 123 */     super((f)imgSrc);
/* 124 */     this.f = wp.getComponentTransformation();
/* 125 */     this.g = wp.getFilters();
/* 126 */     this.e = imgSrc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   private static final String[][] p = new String[][] { { "Mct", "[<tile index>] [true|false] ...", "Specifies to use component transformation with some tiles.  If the wavelet transform is reversible (w5x3 filter), the Reversible Component Transformation (RCT) is applied. If not (w9x7 filter), the Irreversible Component Transformation (ICT) is used.", null } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 161 */     return this.e.getFixedPoint(i);
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
/* 178 */     return p;
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
/*     */   public static int[] a(int[] ntdepth, int ttype, int[] tdepth) {
/* 198 */     if (ntdepth.length < 3 && ttype != 0) {
/* 199 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 202 */     if (tdepth == null) {
/* 203 */       tdepth = new int[ntdepth.length];
/*     */     }
/*     */     
/* 206 */     switch (ttype) {
/*     */       case 0:
/* 208 */         System.arraycopy(ntdepth, 0, tdepth, 0, ntdepth.length);
/*     */         break;
/*     */       case 1:
/* 211 */         if (ntdepth.length > 3) {
/* 212 */           System.arraycopy(ntdepth, 3, tdepth, 3, ntdepth.length - 3);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 222 */         tdepth[0] = e.a((1 << ntdepth[0]) + (2 << ntdepth[1]) + (1 << ntdepth[2]) - 1) - 2 + 1;
/*     */         
/* 224 */         tdepth[1] = e.a((1 << ntdepth[2]) + (1 << ntdepth[1]) - 1) + 1;
/* 225 */         tdepth[2] = e.a((1 << ntdepth[0]) + (1 << ntdepth[1]) - 1) + 1;
/*     */         break;
/*     */       case 2:
/* 228 */         if (ntdepth.length > 3) {
/* 229 */           System.arraycopy(ntdepth, 3, tdepth, 3, ntdepth.length - 3);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 234 */         tdepth[0] = 
/* 235 */           e.a((int)Math.floor((1 << ntdepth[0]) * 0.299072D + (1 << ntdepth[1]) * 0.586914D + (1 << ntdepth[2]) * 0.114014D) - 1) + 1;
/*     */ 
/*     */         
/* 238 */         tdepth[1] = 
/* 239 */           e.a((int)Math.floor((1 << ntdepth[0]) * 0.168701D + (1 << ntdepth[1]) * 0.331299D + (1 << ntdepth[2]) * 0.5D) - 1) + 1;
/*     */ 
/*     */         
/* 242 */         tdepth[2] = 
/* 243 */           e.a((int)Math.floor((1 << ntdepth[0]) * 0.5D + (1 << ntdepth[1]) * 0.418701D + (1 << ntdepth[2]) * 0.081299D) - 1) + 1;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 249 */     return tdepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void c() {
/* 258 */     int tIdx = getTileIdx();
/*     */     
/* 260 */     if (this.e.getNumComps() < 3) {
/* 261 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 264 */     if (this.e.getTileCompWidth(tIdx, 0) != this.e.getTileCompWidth(tIdx, 1) || this.e
/* 265 */       .getTileCompWidth(tIdx, 0) != this.e.getTileCompWidth(tIdx, 2) || this.e
/* 266 */       .getTileCompHeight(tIdx, 0) != this.e.getTileCompHeight(tIdx, 1) || this.e
/* 267 */       .getTileCompHeight(tIdx, 0) != this.e.getTileCompHeight(tIdx, 2)) {
/* 268 */       throw new IllegalArgumentException("Can not use RCT on components with different dimensions");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     int[] utd = new int[this.e.getNumComps()];
/* 275 */     for (int i = utd.length - 1; i >= 0; i--) {
/* 276 */       utd[i] = this.e.getNomRangeBits(i);
/*     */     }
/* 278 */     this.i = a(utd, 1, (int[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void d() {
/* 287 */     int tIdx = getTileIdx();
/*     */     
/* 289 */     if (this.e.getNumComps() < 3) {
/* 290 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 293 */     if (this.e.getTileCompWidth(tIdx, 0) != this.e.getTileCompWidth(tIdx, 1) || this.e
/* 294 */       .getTileCompWidth(tIdx, 0) != this.e.getTileCompWidth(tIdx, 2) || this.e
/* 295 */       .getTileCompHeight(tIdx, 0) != this.e.getTileCompHeight(tIdx, 1) || this.e
/* 296 */       .getTileCompHeight(tIdx, 0) != this.e.getTileCompHeight(tIdx, 2)) {
/* 297 */       throw new IllegalArgumentException("Can not use ICT on components with different dimensions");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     int[] utd = new int[this.e.getNumComps()];
/* 304 */     for (int i = utd.length - 1; i >= 0; i--) {
/* 305 */       utd[i] = this.e.getNomRangeBits(i);
/*     */     }
/* 307 */     this.i = a(utd, 2, (int[])null);
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
/* 318 */     switch (this.h) {
/*     */       case 1:
/* 320 */         return "Forward RCT";
/*     */       case 2:
/* 322 */         return "Forward ICT";
/*     */       case 0:
/* 324 */         return "No component transformation";
/*     */     } 
/* 326 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   public int getNomRangeBits(int i) {
/* 344 */     switch (this.h) {
/*     */       case 1:
/*     */       case 2:
/* 347 */         return this.i[i];
/*     */       case 0:
/* 349 */         return this.e.getNomRangeBits(i);
/*     */     } 
/* 351 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   public boolean b() {
/* 365 */     switch (this.h) {
/*     */       case 0:
/*     */       case 1:
/* 368 */         return true;
/*     */       case 2:
/* 370 */         return false;
/*     */     } 
/* 372 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
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
/*     */   public c getCompData(c blk, int i) {
/* 399 */     if (i >= 3 || this.h == 0) {
/* 400 */       return this.e.getCompData(blk, i);
/*     */     }
/*     */     
/* 403 */     return getInternCompData(blk, i);
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
/*     */   public c getInternCompData(c blk, int i) {
/* 423 */     switch (this.h) {
/*     */       case 0:
/* 425 */         return this.e.getInternCompData(blk, i);
/*     */       case 1:
/* 427 */         return a(blk, i);
/*     */       case 2:
/* 429 */         return b(blk, i);
/*     */     } 
/* 431 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation for tile: " + this.k);
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
/*     */   private c a(c blk, int i) {
/* 450 */     int w = blk.g;
/* 451 */     int h = blk.h;
/*     */ 
/*     */ 
/*     */     
/* 455 */     if (i >= 0 && i <= 2) {
/*     */       int j;
/* 457 */       if (blk.a() != 3) {
/* 458 */         if (this.j == null || this.j.a() != 3) {
/* 459 */           this.j = (c)new e();
/*     */         }
/* 461 */         this.j.g = w;
/* 462 */         this.j.h = h;
/* 463 */         this.j.e = blk.e;
/* 464 */         this.j.f = blk.f;
/* 465 */         blk = this.j;
/*     */       } 
/*     */ 
/*     */       
/* 469 */       int[] outdata = (int[])blk.b();
/*     */ 
/*     */       
/* 472 */       if (outdata == null || outdata.length < h * w) {
/* 473 */         outdata = new int[h * w];
/* 474 */         blk.a(outdata);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 480 */       if (this.m == null)
/* 481 */         this.m = new e(); 
/* 482 */       if (this.n == null)
/* 483 */         this.n = new e(); 
/* 484 */       if (this.o == null)
/* 485 */         this.o = new e(); 
/* 486 */       this.o.g = blk.g;
/* 487 */       this.o.h = blk.h;
/* 488 */       this.o.e = blk.e;
/* 489 */       this.o.f = blk.f;
/*     */ 
/*     */ 
/*     */       
/* 493 */       this.m = (e)this.e.getInternCompData((c)this.m, 0);
/* 494 */       int[] data0 = (int[])this.m.b();
/* 495 */       this.n = (e)this.e.getInternCompData((c)this.n, 1);
/* 496 */       int[] data1 = (int[])this.n.b();
/* 497 */       this.o = (e)this.e.getInternCompData((c)this.o, 2);
/* 498 */       int[] bdata = (int[])this.o.b();
/*     */ 
/*     */       
/* 501 */       blk.k = (this.m.k || this.n.k || this.o.k);
/*     */       
/* 503 */       blk.i = 0;
/* 504 */       blk.j = w;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 509 */       int k = w * h - 1;
/* 510 */       int k0 = this.m.i + (h - 1) * this.m.j + w - 1;
/* 511 */       int k1 = this.n.i + (h - 1) * this.n.j + w - 1;
/* 512 */       int k2 = this.o.i + (h - 1) * this.o.j + w - 1;
/*     */       
/* 514 */       switch (i) {
/*     */         case 0:
/* 516 */           for (j = h - 1; j >= 0; j--) {
/* 517 */             for (int mink = k - w; k > mink; k--, k0--, k1--, k2--)
/*     */             {
/*     */               
/* 520 */               outdata[k] = data0[k] + 2 * data1[k] + bdata[k] >> 2;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 525 */             k0 -= this.m.j - w;
/* 526 */             k1 -= this.n.j - w;
/* 527 */             k2 -= this.o.j - w;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 1:
/* 532 */           for (j = h - 1; j >= 0; j--) {
/* 533 */             for (int mink = k - w; k > mink; k--, k1--, k2--)
/*     */             {
/*     */               
/* 536 */               outdata[k] = bdata[k2] - data1[k1];
/*     */             }
/*     */             
/* 539 */             k1 -= this.n.j - w;
/* 540 */             k2 -= this.o.j - w;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 2:
/* 545 */           for (j = h - 1; j >= 0; j--) {
/* 546 */             for (int mink = k - w; k > mink; k--, k0--, k1--)
/*     */             {
/*     */               
/* 549 */               outdata[k] = data0[k0] - data1[k1];
/*     */             }
/*     */             
/* 552 */             k0 -= this.m.j - w;
/* 553 */             k1 -= this.n.j - w;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 559 */       if (i >= 3)
/*     */       {
/*     */         
/* 562 */         return this.e.getInternCompData(blk, i);
/*     */       }
/*     */ 
/*     */       
/* 566 */       throw new IllegalArgumentException();
/*     */     } 
/* 568 */     return blk;
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
/*     */   private c b(c blk, int i) {
/* 585 */     int w = blk.g;
/* 586 */     int h = blk.h;
/*     */ 
/*     */     
/* 589 */     if (blk.a() != 4) {
/* 590 */       if (this.j == null || this.j.a() != 4) {
/* 591 */         this.j = (c)new d();
/*     */       }
/* 593 */       this.j.g = w;
/* 594 */       this.j.h = h;
/* 595 */       this.j.e = blk.e;
/* 596 */       this.j.f = blk.f;
/* 597 */       blk = this.j;
/*     */     } 
/*     */ 
/*     */     
/* 601 */     float[] outdata = (float[])blk.b();
/*     */ 
/*     */     
/* 604 */     if (outdata == null || outdata.length < w * h) {
/* 605 */       outdata = new float[h * w];
/* 606 */       blk.a(outdata);
/*     */     } 
/*     */ 
/*     */     
/* 610 */     if (i >= 0 && i <= 2) {
/*     */       int j;
/*     */ 
/*     */       
/* 614 */       if (this.m == null)
/* 615 */         this.m = new e(); 
/* 616 */       if (this.n == null)
/* 617 */         this.n = new e(); 
/* 618 */       if (this.o == null)
/* 619 */         this.o = new e(); 
/* 620 */       this.o.g = blk.g;
/* 621 */       this.o.h = blk.h;
/* 622 */       this.o.e = blk.e;
/* 623 */       this.o.f = blk.f;
/*     */ 
/*     */       
/* 626 */       this.m = (e)this.e.getInternCompData((c)this.m, 0);
/* 627 */       int[] data0 = (int[])this.m.b();
/* 628 */       this.n = (e)this.e.getInternCompData((c)this.n, 1);
/* 629 */       int[] data1 = (int[])this.n.b();
/* 630 */       this.o = (e)this.e.getInternCompData((c)this.o, 2);
/* 631 */       int[] data2 = (int[])this.o.b();
/*     */ 
/*     */       
/* 634 */       blk.k = (this.m.k || this.n.k || this.o.k);
/*     */       
/* 636 */       blk.i = 0;
/* 637 */       blk.j = w;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 642 */       int k = w * h - 1;
/* 643 */       int k0 = this.m.i + (h - 1) * this.m.j + w - 1;
/* 644 */       int k1 = this.n.i + (h - 1) * this.n.j + w - 1;
/* 645 */       int k2 = this.o.i + (h - 1) * this.o.j + w - 1;
/*     */       
/* 647 */       switch (i) {
/*     */         
/*     */         case 0:
/* 650 */           for (j = h - 1; j >= 0; j--) {
/* 651 */             for (int mink = k - w; k > mink; k--, k0--, k1--, k2--) {
/* 652 */               outdata[k] = 0.299F * data0[k0] + 0.587F * data1[k1] + 0.114F * data2[k2];
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 658 */             k0 -= this.m.j - w;
/* 659 */             k1 -= this.n.j - w;
/* 660 */             k2 -= this.o.j - w;
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 666 */           for (j = h - 1; j >= 0; j--) {
/* 667 */             for (int mink = k - w; k > mink; k--, k0--, k1--, k2--) {
/* 668 */               outdata[k] = -0.16875F * data0[k0] - 0.33126F * data1[k1] + 0.5F * data2[k2];
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 674 */             k0 -= this.m.j - w;
/* 675 */             k1 -= this.n.j - w;
/* 676 */             k2 -= this.o.j - w;
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 682 */           for (j = h - 1; j >= 0; j--) {
/* 683 */             for (int mink = k - w; k > mink; k--, k0--, k1--, k2--) {
/* 684 */               outdata[k] = 0.5F * data0[k0] - 0.41869F * data1[k1] - 0.08131F * data2[k2];
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 690 */             k0 -= this.m.j - w;
/* 691 */             k1 -= this.n.j - w;
/* 692 */             k2 -= this.o.j - w;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } else {
/* 697 */       if (i >= 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 702 */         e indb = new e(blk.e, blk.f, w, h);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 707 */         this.e.getInternCompData((c)indb, i);
/* 708 */         int[] indata = (int[])indb.b();
/*     */ 
/*     */         
/* 711 */         int k = w * h - 1;
/* 712 */         int k0 = indb.i + (h - 1) * indb.j + w - 1;
/* 713 */         for (int j = h - 1; j >= 0; j--) {
/* 714 */           for (int mink = k - w; k > mink; k--, k0--) {
/* 715 */             outdata[k] = indata[k0];
/*     */           }
/*     */           
/* 718 */           k0 += indb.g - w;
/*     */         } 
/*     */ 
/*     */         
/* 722 */         blk.k = indb.k;
/* 723 */         blk.i = 0;
/* 724 */         blk.j = w;
/* 725 */         return blk;
/*     */       } 
/*     */ 
/*     */       
/* 729 */       throw new IllegalArgumentException();
/*     */     } 
/* 731 */     return blk;
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
/* 748 */     this.e.setTile(x, y);
/* 749 */     this.k = getTileIdx();
/*     */ 
/*     */     
/* 752 */     String str = (String)this.f.f(this.k);
/* 753 */     if (str.equals("none")) {
/* 754 */       this.h = 0;
/*     */     }
/* 756 */     else if (str.equals("rct")) {
/* 757 */       this.h = 1;
/* 758 */       c();
/*     */     }
/* 760 */     else if (str.equals("ict")) {
/* 761 */       this.h = 2;
/* 762 */       d();
/*     */     } else {
/*     */       
/* 765 */       throw new IllegalArgumentException("Component transformation not recognized");
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
/*     */   public void nextTile() {
/* 779 */     this.e.nextTile();
/* 780 */     this.k = getTileIdx();
/*     */ 
/*     */     
/* 783 */     String str = (String)this.f.f(this.k);
/* 784 */     if (str.equals("none")) {
/* 785 */       this.h = 0;
/*     */     }
/* 787 */     else if (str.equals("rct")) {
/* 788 */       this.h = 1;
/* 789 */       c();
/*     */     }
/* 791 */     else if (str.equals("ict")) {
/* 792 */       this.h = 2;
/* 793 */       d();
/*     */     } else {
/*     */       
/* 796 */       throw new IllegalArgumentException("Component transformation not recognized");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */