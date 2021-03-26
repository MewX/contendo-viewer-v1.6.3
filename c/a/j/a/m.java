/*      */ package c.a.j.a;
/*      */ 
/*      */ import c.a.a;
/*      */ import c.a.c.a;
/*      */ import c.a.c.c;
/*      */ import c.a.e.a;
/*      */ import c.a.e.c;
/*      */ import c.a.e.d;
/*      */ import c.a.e.e;
/*      */ import c.a.e.f;
/*      */ import c.a.i.e;
/*      */ import c.a.j.b;
/*      */ import c.a.j.e;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*      */ import java.awt.Point;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class m
/*      */   extends n
/*      */ {
/*      */   private boolean g;
/*      */   private o[][] h;
/*      */   private a i;
/*      */   private int j;
/*      */   private int m;
/*      */   private a n;
/*      */   private f o;
/*      */   private a p;
/*      */   private c q;
/*      */   private c[] r;
/*      */   private int[] s;
/*      */   private int[] t;
/*      */   o[] c;
/*      */   Point d;
/*      */   
/*      */   public m(a src, J2KImageWriteParamJava wp, int pox, int poy) {
/*  150 */     super((f)src);
/*  151 */     this.i = src;
/*      */     
/*  153 */     this.j = this.j;
/*  154 */     this.m = this.m;
/*  155 */     this.n = wp.getDecompositionLevel();
/*  156 */     this.o = wp.getFilters();
/*  157 */     this.p = wp.getCodeBlockSize();
/*  158 */     this.q = wp.getPrecinctPartition();
/*      */     
/*  160 */     int ncomp = src.getNumComps();
/*  161 */     int ntiles = src.getNumTiles();
/*      */     
/*  163 */     this.c = new o[ncomp];
/*  164 */     this.r = new c[ncomp];
/*  165 */     this.h = new o[ntiles][ncomp];
/*  166 */     this.s = new int[ncomp];
/*  167 */     this.t = new int[ncomp];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int a(int i) {
/*  179 */     return 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int f(int t, int i) {
/*  195 */     return ((Integer)this.n.a(t, i)).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int g(int t, int i) {
/*  209 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a[] b(int t, int i) {
/*  234 */     return this.o.f(t, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a[] c(int t, int i) {
/*  259 */     return this.o.g(t, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean d(int t, int i) {
/*  274 */     return this.o.h(t, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int a() {
/*  282 */     return this.j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int b() {
/*  290 */     return this.m;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int b(int i) {
/*  308 */     return this.i.getFixedPoint(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g b(int i, g cblk) {
/*  349 */     this.g = (this.o.e(this.k, i) == 3);
/*      */ 
/*      */     
/*  352 */     if (this.r[i] == null) {
/*      */       d d;
/*      */ 
/*      */ 
/*      */       
/*  357 */       int w = getTileCompWidth(this.k, i);
/*  358 */       int h = getTileCompHeight(this.k, i);
/*      */ 
/*      */       
/*  361 */       if (this.g) {
/*  362 */         this.r[i] = (c)new e(0, 0, w, h);
/*  363 */         e e = new e();
/*      */       } else {
/*  365 */         this.r[i] = (c)new d(0, 0, w, h);
/*  366 */         d = new d();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  371 */       Object dst_data = this.r[i].b();
/*  372 */       int lstart = getCompULX(i);
/*  373 */       ((c)d).e = lstart;
/*  374 */       ((c)d).g = w;
/*  375 */       ((c)d).h = 1;
/*  376 */       int kk = getCompULY(i);
/*  377 */       for (int k = 0; k < h; k++, kk++) {
/*  378 */         ((c)d).f = kk;
/*  379 */         ((c)d).e = lstart;
/*  380 */         c c1 = this.i.getInternCompData((c)d, i);
/*  381 */         System.arraycopy(c1.b(), c1.i, dst_data, k * w, w);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  386 */       a(this.r[i], 
/*  387 */           e(this.k, i), i);
/*      */ 
/*      */       
/*  390 */       this.c[i] = c(i);
/*      */       
/*  392 */       this.s[i] = -1;
/*  393 */       this.t[i] = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/*  399 */       this.d = (this.c[i]).i;
/*      */       
/*  401 */       this.s[i] = this.s[i] + 1;
/*  402 */       if (this.s[i] == this.d.x) {
/*      */         
/*  404 */         this.s[i] = 0;
/*  405 */         this.t[i] = this.t[i] + 1;
/*      */       } 
/*  407 */       if (this.t[i] < this.d.y) {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  414 */       this.c[i] = c(i);
/*  415 */       this.s[i] = -1;
/*  416 */       this.t[i] = 0;
/*  417 */       if (this.c[i] == null) {
/*      */         
/*  419 */         this.r[i] = null;
/*      */ 
/*      */ 
/*      */         
/*  423 */         return null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  433 */     int acb0x = this.j;
/*  434 */     int acb0y = this.m;
/*  435 */     switch ((this.c[i]).k) {
/*      */       case 0:
/*      */         break;
/*      */       
/*      */       case 1:
/*  440 */         acb0x = 0;
/*      */         break;
/*      */       case 2:
/*  443 */         acb0y = 0;
/*      */         break;
/*      */       case 3:
/*  446 */         acb0x = 0;
/*  447 */         acb0y = 0;
/*      */         break;
/*      */       default:
/*  450 */         throw new Error("Internal JJ2000 error");
/*      */     } 
/*      */     
/*  453 */     if (cblk == null) {
/*  454 */       if (this.g) {
/*  455 */         cblk = new i();
/*      */       } else {
/*  457 */         cblk = new h();
/*      */       } 
/*      */     }
/*  460 */     int cbn = this.s[i];
/*  461 */     int cbm = this.t[i];
/*  462 */     o sb = this.c[i];
/*  463 */     cblk.c = cbn;
/*  464 */     cblk.d = cbm;
/*  465 */     cblk.e = sb;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  472 */     int cn = (sb.l - acb0x + sb.r) / sb.r - 1;
/*  473 */     int cm = (sb.m - acb0y + sb.s) / sb.s - 1;
/*  474 */     if (cbn == 0) {
/*  475 */       cblk.a = sb.n;
/*      */     } else {
/*      */       
/*  478 */       cblk.a = (cn + cbn) * sb.r - sb.l - acb0x + sb.n;
/*      */     } 
/*  480 */     if (cbm == 0) {
/*  481 */       cblk.b = sb.o;
/*      */     } else {
/*  483 */       cblk.b = (cm + cbm) * sb.s - sb.m - acb0y + sb.o;
/*      */     } 
/*  485 */     if (cbn < this.d.x - 1) {
/*      */       
/*  487 */       cblk.f = (cn + cbn + 1) * sb.r - sb.l - acb0x + sb.n - cblk.a;
/*      */     } else {
/*      */       
/*  490 */       cblk.f = sb.n + sb.p - cblk.a;
/*      */     } 
/*  492 */     if (cbm < this.d.y - 1) {
/*      */       
/*  494 */       cblk.g = (cm + cbm + 1) * sb.s - sb.m - acb0y + sb.o - cblk.b;
/*      */     } else {
/*      */       
/*  497 */       cblk.g = sb.o + sb.q - cblk.b;
/*      */     } 
/*  499 */     cblk.k = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  504 */     cblk.h = cblk.b * (this.r[i]).g + cblk.a;
/*  505 */     cblk.i = (this.r[i]).g;
/*      */ 
/*      */     
/*  508 */     cblk.a(this.r[i].b());
/*      */     
/*  510 */     return cblk;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public g a(int i, g cblk) {
/*  560 */     this.g = (this.o.e(this.k, i) == 3);
/*      */     
/*  562 */     Object dst_data = null;
/*      */ 
/*      */     
/*  565 */     if (cblk != null) {
/*  566 */       dst_data = cblk.b();
/*      */     }
/*      */ 
/*      */     
/*  570 */     cblk = b(i, cblk);
/*      */     
/*  572 */     if (cblk == null) {
/*  573 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  578 */     if (this.g) {
/*  579 */       int[] dst_data_int = (int[])dst_data;
/*  580 */       if (dst_data_int == null || dst_data_int.length < cblk.f * cblk.g) {
/*  581 */         dst_data = new int[cblk.f * cblk.g];
/*      */       }
/*      */     } else {
/*      */       
/*  585 */       float[] dst_data_float = (float[])dst_data;
/*  586 */       if (dst_data_float == null || dst_data_float.length < cblk.f * cblk.g)
/*      */       {
/*  588 */         dst_data = new float[cblk.f * cblk.g];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  593 */     Object src_data = cblk.b();
/*  594 */     int w = cblk.f;
/*  595 */     int j = w * (cblk.g - 1), k = cblk.h + (cblk.g - 1) * cblk.i;
/*  596 */     for (; j >= 0; j -= w, k -= cblk.i) {
/*  597 */       System.arraycopy(src_data, k, dst_data, j, w);
/*      */     }
/*  599 */     cblk.a(dst_data);
/*  600 */     cblk.h = 0;
/*  601 */     cblk.i = w;
/*      */     
/*  603 */     return cblk;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int a(int t, int i) {
/*  618 */     return this.o.e(t, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private o c(int i) {
/*  631 */     int down = 1;
/*  632 */     int up = 0;
/*  633 */     int direction = down;
/*      */ 
/*      */     
/*  636 */     o nextsb = this.c[i];
/*      */     
/*  638 */     if (nextsb == null) {
/*  639 */       nextsb = e(this.k, i);
/*      */       
/*  641 */       if (!nextsb.e) {
/*  642 */         return nextsb;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  649 */     while (nextsb != null)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       if (!nextsb.e) {
/*  657 */         switch (nextsb.f) {
/*      */           case 3:
/*  659 */             nextsb = (o)nextsb.a().d();
/*  660 */             direction = down;
/*      */             break;
/*      */           case 2:
/*  663 */             nextsb = (o)nextsb.a().c();
/*  664 */             direction = down;
/*      */             break;
/*      */           case 1:
/*  667 */             nextsb = (o)nextsb.a().b();
/*  668 */             direction = down;
/*      */             break;
/*      */           case 0:
/*  671 */             nextsb = (o)nextsb.a();
/*  672 */             direction = up;
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*  678 */       } else if (nextsb.e) {
/*      */ 
/*      */         
/*  681 */         if (direction == down) {
/*  682 */           nextsb = (o)nextsb.e();
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  687 */         else if (direction == up) {
/*  688 */           switch (nextsb.f) {
/*      */             case 3:
/*  690 */               nextsb = (o)nextsb.a().d();
/*  691 */               direction = down;
/*      */               break;
/*      */             case 2:
/*  694 */               nextsb = (o)nextsb.a().c();
/*  695 */               direction = down;
/*      */               break;
/*      */             case 1:
/*  698 */               nextsb = (o)nextsb.a().b();
/*  699 */               direction = down;
/*      */               break;
/*      */             case 0:
/*  702 */               nextsb = (o)nextsb.a();
/*  703 */               direction = up;
/*      */               break;
/*      */           } 
/*      */         
/*      */         } 
/*      */       } 
/*  709 */       if (nextsb == null) {
/*      */         break;
/*      */       }
/*  712 */       if (!nextsb.e)
/*  713 */         break;  }  return nextsb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(c band, o subband, int i) {
/*  732 */     if (!subband.e) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  737 */     b(band, subband, i);
/*      */ 
/*      */     
/*  740 */     a(band, (o)subband.e(), i);
/*  741 */     a(band, (o)subband.d(), i);
/*  742 */     a(band, (o)subband.c(), i);
/*  743 */     a(band, (o)subband.b(), i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void b(c band, o subband, int i) {
/*  767 */     if (subband.p == 0 || subband.q == 0) {
/*      */       return;
/*      */     }
/*      */     
/*  771 */     int ulx = subband.n;
/*  772 */     int uly = subband.o;
/*  773 */     int w = subband.p;
/*  774 */     int h = subband.q;
/*  775 */     int band_w = getTileCompWidth(this.k, i);
/*  776 */     int band_h = getTileCompHeight(this.k, i);
/*      */     
/*  778 */     if (this.g) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  783 */       int[] tmpVector = new int[Math.max(w, h)];
/*      */       
/*  785 */       int[] data = ((e)band).c();
/*      */ 
/*      */       
/*  788 */       if (subband.m % 2 == 0) {
/*  789 */         for (int j = 0; j < w; j++) {
/*  790 */           int offset = uly * band_w + ulx + j;
/*  791 */           for (int k = 0; k < h; k++)
/*  792 */             tmpVector[k] = data[offset + k * band_w]; 
/*  793 */           subband.z.a(tmpVector, 0, h, 1, data, offset, band_w, data, offset + (h + 1) / 2 * band_w, band_w);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  800 */         for (int j = 0; j < w; j++) {
/*  801 */           int offset = uly * band_w + ulx + j;
/*  802 */           for (int k = 0; k < h; k++)
/*  803 */             tmpVector[k] = data[offset + k * band_w]; 
/*  804 */           subband.z.b(tmpVector, 0, h, 1, data, offset, band_w, data, offset + h / 2 * band_w, band_w);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  812 */       if (subband.l % 2 == 0) {
/*  813 */         for (int j = 0; j < h; j++) {
/*  814 */           int offset = (uly + j) * band_w + ulx;
/*  815 */           for (byte b = 0; b < w; b++)
/*  816 */             tmpVector[b] = data[offset + b]; 
/*  817 */           subband.y.a(tmpVector, 0, w, 1, data, offset, 1, data, offset + (w + 1) / 2, 1);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  823 */         for (int j = 0; j < h; j++) {
/*  824 */           int offset = (uly + j) * band_w + ulx;
/*  825 */           for (byte b = 0; b < w; b++)
/*  826 */             tmpVector[b] = data[offset + b]; 
/*  827 */           subband.y.b(tmpVector, 0, w, 1, data, offset, 1, data, offset + w / 2, 1);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  838 */       float[] tmpVector = new float[Math.max(w, h)];
/*  839 */       float[] data = ((d)band).c();
/*      */ 
/*      */       
/*  842 */       if (subband.m % 2 == 0) {
/*  843 */         for (int j = 0; j < w; j++) {
/*  844 */           int offset = uly * band_w + ulx + j;
/*  845 */           for (int k = 0; k < h; k++)
/*  846 */             tmpVector[k] = data[offset + k * band_w]; 
/*  847 */           subband.z.a(tmpVector, 0, h, 1, data, offset, band_w, data, offset + (h + 1) / 2 * band_w, band_w);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  854 */         for (int j = 0; j < w; j++) {
/*  855 */           int offset = uly * band_w + ulx + j;
/*  856 */           for (int k = 0; k < h; k++)
/*  857 */             tmpVector[k] = data[offset + k * band_w]; 
/*  858 */           subband.z.b(tmpVector, 0, h, 1, data, offset, band_w, data, offset + h / 2 * band_w, band_w);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  865 */       if (subband.l % 2 == 0) {
/*  866 */         for (int j = 0; j < h; j++) {
/*  867 */           int offset = (uly + j) * band_w + ulx;
/*  868 */           for (byte b = 0; b < w; b++)
/*  869 */             tmpVector[b] = data[offset + b]; 
/*  870 */           subband.y.a(tmpVector, 0, w, 1, data, offset, 1, data, offset + (w + 1) / 2, 1);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  876 */         for (int j = 0; j < h; j++) {
/*  877 */           int offset = (uly + j) * band_w + ulx;
/*  878 */           for (byte b = 0; b < w; b++)
/*  879 */             tmpVector[b] = data[offset + b]; 
/*  880 */           subband.y.b(tmpVector, 0, w, 1, data, offset, 1, data, offset + w / 2, 1);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTile(int x, int y) {
/*  903 */     super.setTile(x, y);
/*      */ 
/*      */     
/*  906 */     if (this.r != null) {
/*  907 */       for (int i = this.r.length - 1; i >= 0; i--) {
/*  908 */         this.r[i] = null;
/*  909 */         this.c[i] = null;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextTile() {
/*  928 */     super.nextTile();
/*      */     
/*  930 */     if (this.r != null) {
/*  931 */       for (int i = this.r.length - 1; i >= 0; i--) {
/*  932 */         this.r[i] = null;
/*  933 */         this.c[i] = null;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public o e(int t, int i) {
/*  953 */     if (this.h[t][i] == null) {
/*  954 */       this.h[t][i] = new o(
/*  955 */           getTileCompWidth(this.k, i), getTileCompHeight(this.k, i), 
/*  956 */           getCompULX(i), getCompULY(i), 
/*  957 */           f(t, i), (e[])
/*  958 */           b(t, i), (e[])
/*  959 */           c(t, i));
/*  960 */       a(t, i, this.h[t][i]);
/*      */     } 
/*  962 */     return this.h[t][i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(int t, int i, b sb) {
/*  977 */     int cbw = this.p.a((byte)3, t, i);
/*  978 */     int cbh = this.p.b((byte)3, t, i);
/*      */     
/*  980 */     if (!sb.e) {
/*      */ 
/*      */ 
/*      */       
/*  984 */       int ppx = this.q.a(t, i, sb.h);
/*  985 */       int ppy = this.q.b(t, i, sb.h);
/*      */       
/*  987 */       if (ppx != 65535 || ppy != 65535) {
/*      */ 
/*      */         
/*  990 */         int ppxExp = e.a(ppx);
/*  991 */         int ppyExp = e.a(ppy);
/*  992 */         int cbwExp = e.a(cbw);
/*  993 */         int cbhExp = e.a(cbh);
/*      */ 
/*      */         
/*  996 */         switch (sb.h) {
/*      */           case 0:
/*  998 */             sb.r = (cbwExp < ppxExp) ? (1 << cbwExp) : (1 << ppxExp);
/*      */             
/* 1000 */             sb.s = (cbhExp < ppyExp) ? (1 << cbhExp) : (1 << ppyExp);
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 1005 */             sb.r = (cbwExp < ppxExp - 1) ? (1 << cbwExp) : (1 << ppxExp - 1);
/*      */             
/* 1007 */             sb.s = (cbhExp < ppyExp - 1) ? (1 << cbhExp) : (1 << ppyExp - 1);
/*      */             break;
/*      */         } 
/*      */       
/*      */       } else {
/* 1012 */         sb.r = cbw;
/* 1013 */         sb.s = cbh;
/*      */       } 
/*      */ 
/*      */       
/* 1017 */       if (sb.i == null) sb.i = new Point(); 
/* 1018 */       if (sb.p != 0 && sb.q != 0) {
/* 1019 */         int acb0x = this.j;
/* 1020 */         int acb0y = this.m;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1028 */         switch (sb.k) {
/*      */           case 0:
/*      */             break;
/*      */           
/*      */           case 1:
/* 1033 */             acb0x = 0;
/*      */             break;
/*      */           case 2:
/* 1036 */             acb0y = 0;
/*      */             break;
/*      */           case 3:
/* 1039 */             acb0x = 0;
/* 1040 */             acb0y = 0;
/*      */             break;
/*      */           default:
/* 1043 */             throw new Error("Internal JJ2000 error");
/*      */         } 
/* 1045 */         if (sb.l - acb0x < 0 || sb.m - acb0y < 0) {
/* 1046 */           throw new IllegalArgumentException("Invalid code-blocks partition origin or image offset in the reference grid.");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1055 */         int tmp = sb.l - acb0x + sb.r;
/* 1056 */         sb.i.x = (tmp + sb.p - 1) / sb.r - tmp / sb.r - 1;
/* 1057 */         tmp = sb.m - acb0y + sb.s;
/* 1058 */         sb.i.y = (tmp + sb.q - 1) / sb.s - tmp / sb.s - 1;
/*      */       } else {
/* 1060 */         sb.i.x = sb.i.y = 0;
/*      */       } 
/*      */     } else {
/* 1063 */       a(t, i, sb.b());
/* 1064 */       a(t, i, sb.c());
/* 1065 */       a(t, i, sb.d());
/* 1066 */       a(t, i, sb.e());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/m.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */