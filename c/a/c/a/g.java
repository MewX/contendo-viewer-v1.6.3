/*      */ package c.a.c.a;
/*      */ 
/*      */ import c.a.b.a;
/*      */ import c.a.c.f;
/*      */ import c.a.e.c;
/*      */ import c.a.e.e;
/*      */ import c.a.i.a;
/*      */ import c.a.i.c;
/*      */ import c.a.j.b.i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class g
/*      */   extends e
/*      */   implements f
/*      */ {
/*      */   private static final boolean c = false;
/*      */   private long[] d;
/*      */   private b g;
/*      */   private f h;
/*      */   private a i;
/*      */   private int j;
/*      */   private final boolean y;
/*      */   private final boolean z;
/*      */   private static final int A = 8;
/*  121 */   private static final int[] B = new int[256];
/*      */ 
/*      */   
/*  124 */   private static final int[] C = new int[256];
/*      */ 
/*      */   
/*  127 */   private static final int[] D = new int[256];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int E = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private static final int[] F = new int[512];
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int G = 15;
/*      */ 
/*      */   
/*      */   private static final int H = 31;
/*      */ 
/*      */   
/*      */   private static final int I = -2147483648;
/*      */ 
/*      */   
/*      */   private static final int J = 9;
/*      */ 
/*      */   
/*  157 */   private static final int[] K = new int[512];
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int L = 19;
/*      */ 
/*      */   
/*      */   private static final int M = 1;
/*      */ 
/*      */   
/*      */   private static final int N = 0;
/*      */ 
/*      */   
/*  170 */   private static final int[] O = new int[] { 46, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int P = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int[] Q;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int R = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int S = 32768;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int T = 16384;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int U = 8192;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int V = 4096;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int W = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int X = 1024;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int Y = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int Z = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aa = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ab = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ac = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ad = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ae = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int af = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ag = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ah = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ai = -2147483648;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aj = 1073741824;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ak = 536870912;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int al = 268435456;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int am = 134217728;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int an = 67108864;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ao = 33554432;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ap = 16777216;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aq = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ar = 4194304;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int as = 2097152;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int at = 1048576;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int au = 524288;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int av = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aw = 131072;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ax = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ay = -2147450880;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int az = 1073758208;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aA = -536813568;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aB = 255;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aC = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aD = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aE = 511;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aF = 511;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private d aG;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int aH;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  401 */     B[0] = 2;
/*      */     int i;
/*  403 */     for (i = 1; i < 16; i++) {
/*  404 */       B[i] = 4;
/*      */     }
/*  406 */     for (i = 0; i < 4; i++) {
/*  407 */       B[1 << i] = 3;
/*      */     }
/*      */     
/*  410 */     for (i = 0; i < 16; i++) {
/*      */       
/*  412 */       B[0x20 | i] = 5;
/*  413 */       B[0x10 | i] = 5;
/*      */       
/*  415 */       B[0x30 | i] = 6;
/*      */     } 
/*      */     
/*  418 */     B[128] = 7;
/*  419 */     B[64] = 7;
/*      */ 
/*      */     
/*  422 */     for (i = 1; i < 16; i++) {
/*  423 */       B[0x80 | i] = 8;
/*  424 */       B[0x40 | i] = 8;
/*      */     } 
/*      */ 
/*      */     
/*  428 */     for (i = 1; i < 4; i++) {
/*  429 */       for (int k = 0; k < 16; k++) {
/*  430 */         B[0x80 | i << 4 | k] = 9;
/*  431 */         B[0x40 | i << 4 | k] = 9;
/*      */       } 
/*      */     } 
/*      */     
/*  435 */     for (i = 0; i < 64; i++) {
/*  436 */       B[0xC0 | i] = 10;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  442 */     C[0] = 2;
/*      */     
/*  444 */     for (i = 1; i < 16; i++) {
/*  445 */       C[i] = 4;
/*      */     }
/*  447 */     for (i = 0; i < 4; i++) {
/*  448 */       C[1 << i] = 3;
/*      */     }
/*      */     
/*  451 */     for (i = 0; i < 16; i++) {
/*      */       
/*  453 */       C[0x80 | i] = 5;
/*  454 */       C[0x40 | i] = 5;
/*      */       
/*  456 */       C[0xC0 | i] = 6;
/*      */     } 
/*      */     
/*  459 */     C[32] = 7;
/*  460 */     C[16] = 7;
/*      */ 
/*      */     
/*  463 */     for (i = 1; i < 16; i++) {
/*  464 */       C[0x20 | i] = 8;
/*  465 */       C[0x10 | i] = 8;
/*      */     } 
/*      */ 
/*      */     
/*  469 */     for (i = 1; i < 4; i++) {
/*  470 */       for (int k = 0; k < 16; k++) {
/*  471 */         C[i << 6 | 0x20 | k] = 9;
/*  472 */         C[i << 6 | 0x10 | k] = 9;
/*      */       } 
/*      */     } 
/*      */     
/*  476 */     for (i = 0; i < 4; i++) {
/*  477 */       for (int k = 0; k < 16; k++) {
/*  478 */         C[i << 6 | 0x20 | 0x10 | k] = 10;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  483 */     int[] twoBits = { 3, 5, 6, 9, 10, 12 };
/*      */ 
/*      */     
/*  486 */     int[] oneBit = { 1, 2, 4, 8 };
/*      */ 
/*      */     
/*  489 */     int[] twoLeast = { 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15 };
/*      */ 
/*      */ 
/*      */     
/*  493 */     int[] threeLeast = { 7, 11, 13, 14, 15 };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  498 */     D[0] = 2;
/*      */ 
/*      */     
/*  501 */     for (i = 0; i < oneBit.length; i++) {
/*  502 */       D[oneBit[i] << 4] = 3;
/*      */     }
/*      */     
/*  505 */     for (i = 0; i < twoLeast.length; i++) {
/*  506 */       D[twoLeast[i] << 4] = 4;
/*      */     }
/*      */     
/*  509 */     for (i = 0; i < oneBit.length; i++) {
/*  510 */       D[oneBit[i]] = 5;
/*      */     }
/*      */     
/*  513 */     for (i = 0; i < oneBit.length; i++) {
/*  514 */       for (int k = 0; k < oneBit.length; k++) {
/*  515 */         D[oneBit[i] << 4 | oneBit[k]] = 6;
/*      */       }
/*      */     } 
/*  518 */     for (i = 0; i < twoLeast.length; i++) {
/*  519 */       for (int k = 0; k < oneBit.length; k++) {
/*  520 */         D[twoLeast[i] << 4 | oneBit[k]] = 7;
/*      */       }
/*      */     } 
/*  523 */     for (i = 0; i < twoBits.length; i++) {
/*  524 */       D[twoBits[i]] = 8;
/*      */     }
/*      */     int j;
/*  527 */     for (j = 0; j < twoBits.length; j++) {
/*  528 */       for (i = 1; i < 16; i++) {
/*  529 */         D[i << 4 | twoBits[j]] = 9;
/*      */       }
/*      */     } 
/*  532 */     for (i = 0; i < 16; i++) {
/*  533 */       for (j = 0; j < threeLeast.length; j++) {
/*  534 */         D[i << 4 | threeLeast[j]] = 10;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  543 */     int[] inter_sc_lut = new int[36];
/*  544 */     inter_sc_lut[18] = 15;
/*  545 */     inter_sc_lut[17] = 14;
/*  546 */     inter_sc_lut[16] = 13;
/*  547 */     inter_sc_lut[10] = 12;
/*  548 */     inter_sc_lut[9] = 11;
/*  549 */     inter_sc_lut[8] = -2147483636;
/*  550 */     inter_sc_lut[2] = -2147483635;
/*  551 */     inter_sc_lut[1] = -2147483634;
/*  552 */     inter_sc_lut[0] = -2147483633;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     for (i = 0; i < 511; i++) {
/*  562 */       int ds = i & 0x1;
/*  563 */       int us = i >> 1 & 0x1;
/*  564 */       int rs = i >> 2 & 0x1;
/*  565 */       int ls = i >> 3 & 0x1;
/*  566 */       int dsgn = i >> 5 & 0x1;
/*  567 */       int usgn = i >> 6 & 0x1;
/*  568 */       int rsgn = i >> 7 & 0x1;
/*  569 */       int lsgn = i >> 8 & 0x1;
/*      */       
/*  571 */       int h = ls * (1 - 2 * lsgn) + rs * (1 - 2 * rsgn);
/*  572 */       h = (h >= -1) ? h : -1;
/*  573 */       h = (h <= 1) ? h : 1;
/*  574 */       int v = us * (1 - 2 * usgn) + ds * (1 - 2 * dsgn);
/*  575 */       v = (v >= -1) ? v : -1;
/*  576 */       v = (v <= 1) ? v : 1;
/*      */       
/*  578 */       F[i] = inter_sc_lut[h + 1 << 3 | v + 1];
/*      */     } 
/*  580 */     inter_sc_lut = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  585 */     K[0] = 16;
/*      */     
/*  587 */     for (i = 1; i < 256; i++) {
/*  588 */       K[i] = 17;
/*      */     }
/*      */     
/*  591 */     for (; i < 512; i++) {
/*  592 */       K[i] = 18;
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
/*      */   
/*      */   public g(c src, a decSpec, boolean doer, boolean verber, int mQuit) {
/*  614 */     super(src);
/*      */     
/*  616 */     this.i = decSpec;
/*  617 */     this.y = doer;
/*  618 */     this.z = verber;
/*  619 */     this.aH = mQuit;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  629 */     this
/*  630 */       .Q = new int[(decSpec.q.a() + 2) * ((decSpec.q.b() + 1) / 2 + 2)];
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
/*      */   public void finalize() throws Throwable {
/*  654 */     super.finalize();
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
/*      */   public c a(int j, int m, int n, i sb, c cblk) {
/*      */     e e1;
/*      */     int[] zc_lut;
/*  702 */     long stime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  710 */     a in = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  715 */     this.aG = this.b.a(j, m, n, sb, 1, -1, this.aG);
/*      */ 
/*      */ 
/*      */     
/*  719 */     this
/*  720 */       .j = ((Integer)this.i.j.a(this.e, j)).intValue();
/*      */ 
/*      */     
/*  723 */     a.a(this.Q, 0);
/*      */ 
/*      */     
/*  726 */     if (cblk == null) {
/*  727 */       e1 = new e();
/*      */     }
/*  729 */     ((c)e1).k = this.aG.j;
/*  730 */     ((c)e1).e = this.aG.e;
/*  731 */     ((c)e1).f = this.aG.f;
/*  732 */     ((c)e1).g = this.aG.g;
/*  733 */     ((c)e1).h = this.aG.h;
/*  734 */     ((c)e1).i = 0;
/*  735 */     ((c)e1).j = ((c)e1).g;
/*  736 */     int[] out_data = (int[])e1.b();
/*      */     
/*  738 */     if (out_data == null || out_data.length < this.aG.g * this.aG.h) {
/*  739 */       out_data = new int[this.aG.g * this.aG.h];
/*  740 */       e1.a(out_data);
/*      */     } else {
/*      */       
/*  743 */       a.a(out_data, 0);
/*      */     } 
/*      */     
/*  746 */     if (this.aG.k <= 0 || this.aG.m <= 0)
/*      */     {
/*  748 */       return (c)e1;
/*      */     }
/*      */ 
/*      */     
/*  752 */     int tslen = (this.aG.n == null) ? this.aG.i : this.aG.n[0];
/*  753 */     int tsidx = 0;
/*      */     
/*  755 */     int npasses = this.aG.m;
/*  756 */     if (this.h == null) {
/*  757 */       in = new a(this.aG.d, 0, tslen);
/*  758 */       this.h = new f(in, 19, O);
/*      */     }
/*      */     else {
/*      */       
/*  762 */       this.h.a(this.aG.d, 0, tslen);
/*  763 */       this.h.c();
/*      */     } 
/*  765 */     boolean error = false;
/*      */     
/*  767 */     if ((this.j & 0x1) != 0 && 
/*  768 */       this.g == null) {
/*  769 */       if (in == null) in = this.h.d(); 
/*  770 */       this.g = new b(in);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  775 */     switch (sb.f) {
/*      */       case 1:
/*  777 */         zc_lut = C;
/*      */         break;
/*      */       case 0:
/*      */       case 2:
/*  781 */         zc_lut = B;
/*      */         break;
/*      */       case 3:
/*  784 */         zc_lut = D;
/*      */         break;
/*      */       default:
/*  787 */         throw new Error("JJ2000 internal error");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  801 */     int curbp = 30 - this.aG.c;
/*      */ 
/*      */     
/*  804 */     if (this.aH != -1 && this.aH * 3 - 2 < npasses) {
/*  805 */       npasses = this.aH * 3 - 2;
/*      */     }
/*      */ 
/*      */     
/*  809 */     if (curbp >= 0 && npasses > 0) {
/*  810 */       boolean isterm = ((this.j & 0x4) != 0 || ((this.j & 0x1) != 0 && 27 - this.aG.c >= curbp));
/*      */ 
/*      */       
/*  813 */       error = b((c)e1, this.h, curbp, this.Q, zc_lut, isterm);
/*  814 */       npasses--;
/*  815 */       if (!error || !this.y) curbp--;
/*      */     
/*      */     } 
/*      */     
/*  819 */     if (!error || !this.y) {
/*  820 */       while (curbp >= 0 && npasses > 0) {
/*      */         
/*  822 */         if ((this.j & 0x1) != 0 && curbp < 27 - this.aG.c) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  828 */           this.g.a(null, -1, this.aG.n[++tsidx]);
/*  829 */           boolean isterm = ((this.j & 0x4) != 0);
/*  830 */           error = a((c)e1, this.g, curbp, this.Q, isterm);
/*  831 */           npasses--;
/*  832 */           if (npasses <= 0 || (error && this.y))
/*      */             break; 
/*  834 */           if ((this.j & 0x4) != 0)
/*      */           {
/*  836 */             this.g.a(null, -1, this.aG.n[++tsidx]);
/*      */           }
/*  838 */           isterm = ((this.j & 0x4) != 0 || ((this.j & 0x1) != 0 && 27 - this.aG.c > curbp));
/*      */ 
/*      */           
/*  841 */           error = b((c)e1, this.g, curbp, this.Q, isterm);
/*      */         } else {
/*      */           
/*  844 */           if ((this.j & 0x4) != 0)
/*      */           {
/*  846 */             this.h.a((byte[])null, -1, this.aG.n[++tsidx]);
/*      */           }
/*  848 */           boolean isterm = ((this.j & 0x4) != 0);
/*  849 */           error = a((c)e1, this.h, curbp, this.Q, zc_lut, isterm);
/*  850 */           npasses--;
/*  851 */           if (npasses <= 0 || (error && this.y))
/*      */             break; 
/*  853 */           if ((this.j & 0x4) != 0)
/*      */           {
/*  855 */             this.h.a((byte[])null, -1, this.aG.n[++tsidx]);
/*      */           }
/*  857 */           isterm = ((this.j & 0x4) != 0 || ((this.j & 0x1) != 0 && 27 - this.aG.c > curbp));
/*      */ 
/*      */           
/*  860 */           error = a((c)e1, this.h, curbp, this.Q, isterm);
/*      */         } 
/*      */         
/*  863 */         npasses--;
/*  864 */         if (npasses <= 0 || (error && this.y))
/*      */           break; 
/*  866 */         if ((this.j & 0x4) != 0 || ((this.j & 0x1) != 0 && curbp < 27 - this.aG.c))
/*      */         {
/*      */ 
/*      */           
/*  870 */           this.h.a((byte[])null, -1, this.aG.n[++tsidx]);
/*      */         }
/*  872 */         boolean bool = ((this.j & 0x4) != 0 || ((this.j & 0x1) != 0 && 27 - this.aG.c >= curbp)) ? true : false;
/*      */ 
/*      */         
/*  875 */         error = b((c)e1, this.h, curbp, this.Q, zc_lut, bool);
/*  876 */         npasses--;
/*  877 */         if (error)
/*      */           break; 
/*  879 */         curbp--;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  884 */     if (error && this.y) {
/*  885 */       if (this.z) {
/*  886 */         c.b()
/*  887 */           .printmsg(2, "Error detected at bit-plane " + curbp + " in code-block (" + m + "," + n + "), sb_idx " + sb.k + ", res. level " + sb.h + ". Concealing...");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  894 */       a((c)e1, curbp);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  900 */     return (c)e1;
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
/*      */   public c b(int j, int m, int n, i sb, c cblk) {
/*  947 */     return a(j, m, n, sb, cblk);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean a(c cblk, f mq, int bp, int[] state, int[] zc_lut, boolean isterm) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: getfield j : I
/*      */     //   4: istore #11
/*      */     //   6: aload_1
/*      */     //   7: getfield g : I
/*      */     //   10: iconst_2
/*      */     //   11: iadd
/*      */     //   12: istore #12
/*      */     //   14: iload #12
/*      */     //   16: iconst_4
/*      */     //   17: imul
/*      */     //   18: iconst_2
/*      */     //   19: idiv
/*      */     //   20: aload_1
/*      */     //   21: getfield g : I
/*      */     //   24: isub
/*      */     //   25: istore #13
/*      */     //   27: iload #11
/*      */     //   29: iconst_4
/*      */     //   30: imul
/*      */     //   31: aload_1
/*      */     //   32: getfield g : I
/*      */     //   35: isub
/*      */     //   36: istore #14
/*      */     //   38: iconst_3
/*      */     //   39: iload_3
/*      */     //   40: ishl
/*      */     //   41: iconst_1
/*      */     //   42: ishr
/*      */     //   43: istore #17
/*      */     //   45: aload_1
/*      */     //   46: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   49: checkcast [I
/*      */     //   52: checkcast [I
/*      */     //   55: astore #20
/*      */     //   57: aload_1
/*      */     //   58: getfield h : I
/*      */     //   61: iconst_4
/*      */     //   62: iadd
/*      */     //   63: iconst_1
/*      */     //   64: isub
/*      */     //   65: iconst_4
/*      */     //   66: idiv
/*      */     //   67: istore #23
/*      */     //   69: aload_0
/*      */     //   70: getfield j : I
/*      */     //   73: bipush #8
/*      */     //   75: iand
/*      */     //   76: ifeq -> 83
/*      */     //   79: iconst_1
/*      */     //   80: goto -> 84
/*      */     //   83: iconst_0
/*      */     //   84: istore #22
/*      */     //   86: iload #12
/*      */     //   88: ineg
/*      */     //   89: iconst_1
/*      */     //   90: isub
/*      */     //   91: istore #25
/*      */     //   93: iload #12
/*      */     //   95: ineg
/*      */     //   96: iconst_1
/*      */     //   97: iadd
/*      */     //   98: istore #26
/*      */     //   100: iload #12
/*      */     //   102: iconst_1
/*      */     //   103: iadd
/*      */     //   104: istore #27
/*      */     //   106: iload #12
/*      */     //   108: iconst_1
/*      */     //   109: isub
/*      */     //   110: istore #28
/*      */     //   112: aload_1
/*      */     //   113: getfield i : I
/*      */     //   116: istore #10
/*      */     //   118: iload #12
/*      */     //   120: iconst_1
/*      */     //   121: iadd
/*      */     //   122: istore #8
/*      */     //   124: iload #23
/*      */     //   126: iconst_1
/*      */     //   127: isub
/*      */     //   128: istore #21
/*      */     //   130: iload #21
/*      */     //   132: iflt -> 1182
/*      */     //   135: iload #21
/*      */     //   137: ifeq -> 144
/*      */     //   140: iconst_4
/*      */     //   141: goto -> 155
/*      */     //   144: aload_1
/*      */     //   145: getfield h : I
/*      */     //   148: iload #23
/*      */     //   150: iconst_1
/*      */     //   151: isub
/*      */     //   152: iconst_4
/*      */     //   153: imul
/*      */     //   154: isub
/*      */     //   155: istore #24
/*      */     //   157: iload #10
/*      */     //   159: aload_1
/*      */     //   160: getfield g : I
/*      */     //   163: iadd
/*      */     //   164: istore #15
/*      */     //   166: iload #10
/*      */     //   168: iload #15
/*      */     //   170: if_icmpge -> 1162
/*      */     //   173: iload #8
/*      */     //   175: istore #7
/*      */     //   177: aload #4
/*      */     //   179: iload #7
/*      */     //   181: iaload
/*      */     //   182: istore #16
/*      */     //   184: iload #16
/*      */     //   186: iconst_m1
/*      */     //   187: ixor
/*      */     //   188: iload #16
/*      */     //   190: iconst_2
/*      */     //   191: ishl
/*      */     //   192: iand
/*      */     //   193: ldc -2147450880
/*      */     //   195: iand
/*      */     //   196: ifeq -> 662
/*      */     //   199: iload #10
/*      */     //   201: istore #9
/*      */     //   203: iload #16
/*      */     //   205: ldc 40960
/*      */     //   207: iand
/*      */     //   208: sipush #8192
/*      */     //   211: if_icmpne -> 422
/*      */     //   214: aload_2
/*      */     //   215: aload #5
/*      */     //   217: iload #16
/*      */     //   219: sipush #255
/*      */     //   222: iand
/*      */     //   223: iaload
/*      */     //   224: invokevirtual a : (I)I
/*      */     //   227: ifeq -> 414
/*      */     //   230: getstatic c/a/c/a/g.F : [I
/*      */     //   233: iload #16
/*      */     //   235: iconst_4
/*      */     //   236: iushr
/*      */     //   237: sipush #511
/*      */     //   240: iand
/*      */     //   241: iaload
/*      */     //   242: istore #19
/*      */     //   244: aload_2
/*      */     //   245: iload #19
/*      */     //   247: bipush #15
/*      */     //   249: iand
/*      */     //   250: invokevirtual a : (I)I
/*      */     //   253: iload #19
/*      */     //   255: bipush #31
/*      */     //   257: iushr
/*      */     //   258: ixor
/*      */     //   259: istore #18
/*      */     //   261: aload #20
/*      */     //   263: iload #9
/*      */     //   265: iload #18
/*      */     //   267: bipush #31
/*      */     //   269: ishl
/*      */     //   270: iload #17
/*      */     //   272: ior
/*      */     //   273: iastore
/*      */     //   274: iload #22
/*      */     //   276: ifne -> 305
/*      */     //   279: aload #4
/*      */     //   281: iload #7
/*      */     //   283: iload #25
/*      */     //   285: iadd
/*      */     //   286: dup2
/*      */     //   287: iaload
/*      */     //   288: ldc 536936448
/*      */     //   290: ior
/*      */     //   291: iastore
/*      */     //   292: aload #4
/*      */     //   294: iload #7
/*      */     //   296: iload #26
/*      */     //   298: iadd
/*      */     //   299: dup2
/*      */     //   300: iaload
/*      */     //   301: ldc 537001984
/*      */     //   303: ior
/*      */     //   304: iastore
/*      */     //   305: iload #18
/*      */     //   307: ifeq -> 362
/*      */     //   310: iload #16
/*      */     //   312: ldc 606126080
/*      */     //   314: ior
/*      */     //   315: istore #16
/*      */     //   317: iload #22
/*      */     //   319: ifne -> 335
/*      */     //   322: aload #4
/*      */     //   324: iload #7
/*      */     //   326: iload #12
/*      */     //   328: isub
/*      */     //   329: dup2
/*      */     //   330: iaload
/*      */     //   331: ldc 571473920
/*      */     //   333: ior
/*      */     //   334: iastore
/*      */     //   335: aload #4
/*      */     //   337: iload #7
/*      */     //   339: iconst_1
/*      */     //   340: iadd
/*      */     //   341: dup2
/*      */     //   342: iaload
/*      */     //   343: ldc 537407616
/*      */     //   345: ior
/*      */     //   346: iastore
/*      */     //   347: aload #4
/*      */     //   349: iload #7
/*      */     //   351: iconst_1
/*      */     //   352: isub
/*      */     //   353: dup2
/*      */     //   354: iaload
/*      */     //   355: ldc 537143360
/*      */     //   357: ior
/*      */     //   358: iastore
/*      */     //   359: goto -> 422
/*      */     //   362: iload #16
/*      */     //   364: ldc 539017216
/*      */     //   366: ior
/*      */     //   367: istore #16
/*      */     //   369: iload #22
/*      */     //   371: ifne -> 387
/*      */     //   374: aload #4
/*      */     //   376: iload #7
/*      */     //   378: iload #12
/*      */     //   380: isub
/*      */     //   381: dup2
/*      */     //   382: iaload
/*      */     //   383: ldc 537919488
/*      */     //   385: ior
/*      */     //   386: iastore
/*      */     //   387: aload #4
/*      */     //   389: iload #7
/*      */     //   391: iconst_1
/*      */     //   392: iadd
/*      */     //   393: dup2
/*      */     //   394: iaload
/*      */     //   395: ldc 537403520
/*      */     //   397: ior
/*      */     //   398: iastore
/*      */     //   399: aload #4
/*      */     //   401: iload #7
/*      */     //   403: iconst_1
/*      */     //   404: isub
/*      */     //   405: dup2
/*      */     //   406: iaload
/*      */     //   407: ldc 537141312
/*      */     //   409: ior
/*      */     //   410: iastore
/*      */     //   411: goto -> 422
/*      */     //   414: iload #16
/*      */     //   416: sipush #16384
/*      */     //   419: ior
/*      */     //   420: istore #16
/*      */     //   422: iload #24
/*      */     //   424: iconst_2
/*      */     //   425: if_icmpge -> 438
/*      */     //   428: aload #4
/*      */     //   430: iload #7
/*      */     //   432: iload #16
/*      */     //   434: iastore
/*      */     //   435: goto -> 1153
/*      */     //   438: iload #16
/*      */     //   440: ldc -1610612736
/*      */     //   442: iand
/*      */     //   443: ldc 536870912
/*      */     //   445: if_icmpne -> 655
/*      */     //   448: iload #9
/*      */     //   450: iload #11
/*      */     //   452: iadd
/*      */     //   453: istore #9
/*      */     //   455: aload_2
/*      */     //   456: aload #5
/*      */     //   458: iload #16
/*      */     //   460: bipush #16
/*      */     //   462: iushr
/*      */     //   463: sipush #255
/*      */     //   466: iand
/*      */     //   467: iaload
/*      */     //   468: invokevirtual a : (I)I
/*      */     //   471: ifeq -> 648
/*      */     //   474: getstatic c/a/c/a/g.F : [I
/*      */     //   477: iload #16
/*      */     //   479: bipush #20
/*      */     //   481: iushr
/*      */     //   482: sipush #511
/*      */     //   485: iand
/*      */     //   486: iaload
/*      */     //   487: istore #19
/*      */     //   489: aload_2
/*      */     //   490: iload #19
/*      */     //   492: bipush #15
/*      */     //   494: iand
/*      */     //   495: invokevirtual a : (I)I
/*      */     //   498: iload #19
/*      */     //   500: bipush #31
/*      */     //   502: iushr
/*      */     //   503: ixor
/*      */     //   504: istore #18
/*      */     //   506: aload #20
/*      */     //   508: iload #9
/*      */     //   510: iload #18
/*      */     //   512: bipush #31
/*      */     //   514: ishl
/*      */     //   515: iload #17
/*      */     //   517: ior
/*      */     //   518: iastore
/*      */     //   519: aload #4
/*      */     //   521: iload #7
/*      */     //   523: iload #28
/*      */     //   525: iadd
/*      */     //   526: dup2
/*      */     //   527: iaload
/*      */     //   528: sipush #8196
/*      */     //   531: ior
/*      */     //   532: iastore
/*      */     //   533: aload #4
/*      */     //   535: iload #7
/*      */     //   537: iload #27
/*      */     //   539: iadd
/*      */     //   540: dup2
/*      */     //   541: iaload
/*      */     //   542: sipush #8200
/*      */     //   545: ior
/*      */     //   546: iastore
/*      */     //   547: iload #18
/*      */     //   549: ifeq -> 600
/*      */     //   552: iload #16
/*      */     //   554: ldc -1073733104
/*      */     //   556: ior
/*      */     //   557: istore #16
/*      */     //   559: aload #4
/*      */     //   561: iload #7
/*      */     //   563: iload #12
/*      */     //   565: iadd
/*      */     //   566: dup2
/*      */     //   567: iaload
/*      */     //   568: sipush #9248
/*      */     //   571: ior
/*      */     //   572: iastore
/*      */     //   573: aload #4
/*      */     //   575: iload #7
/*      */     //   577: iconst_1
/*      */     //   578: iadd
/*      */     //   579: dup2
/*      */     //   580: iaload
/*      */     //   581: ldc 813703170
/*      */     //   583: ior
/*      */     //   584: iastore
/*      */     //   585: aload #4
/*      */     //   587: iload #7
/*      */     //   589: iconst_1
/*      */     //   590: isub
/*      */     //   591: dup2
/*      */     //   592: iaload
/*      */     //   593: ldc 675291137
/*      */     //   595: ior
/*      */     //   596: iastore
/*      */     //   597: goto -> 655
/*      */     //   600: iload #16
/*      */     //   602: ldc -1073733616
/*      */     //   604: ior
/*      */     //   605: istore #16
/*      */     //   607: aload #4
/*      */     //   609: iload #7
/*      */     //   611: iload #12
/*      */     //   613: iadd
/*      */     //   614: dup2
/*      */     //   615: iaload
/*      */     //   616: sipush #8224
/*      */     //   619: ior
/*      */     //   620: iastore
/*      */     //   621: aload #4
/*      */     //   623: iload #7
/*      */     //   625: iconst_1
/*      */     //   626: iadd
/*      */     //   627: dup2
/*      */     //   628: iaload
/*      */     //   629: ldc 545267714
/*      */     //   631: ior
/*      */     //   632: iastore
/*      */     //   633: aload #4
/*      */     //   635: iload #7
/*      */     //   637: iconst_1
/*      */     //   638: isub
/*      */     //   639: dup2
/*      */     //   640: iaload
/*      */     //   641: ldc 541073409
/*      */     //   643: ior
/*      */     //   644: iastore
/*      */     //   645: goto -> 655
/*      */     //   648: iload #16
/*      */     //   650: ldc 1073741824
/*      */     //   652: ior
/*      */     //   653: istore #16
/*      */     //   655: aload #4
/*      */     //   657: iload #7
/*      */     //   659: iload #16
/*      */     //   661: iastore
/*      */     //   662: iload #24
/*      */     //   664: iconst_3
/*      */     //   665: if_icmpge -> 671
/*      */     //   668: goto -> 1153
/*      */     //   671: iload #7
/*      */     //   673: iload #12
/*      */     //   675: iadd
/*      */     //   676: istore #7
/*      */     //   678: aload #4
/*      */     //   680: iload #7
/*      */     //   682: iaload
/*      */     //   683: istore #16
/*      */     //   685: iload #16
/*      */     //   687: iconst_m1
/*      */     //   688: ixor
/*      */     //   689: iload #16
/*      */     //   691: iconst_2
/*      */     //   692: ishl
/*      */     //   693: iand
/*      */     //   694: ldc -2147450880
/*      */     //   696: iand
/*      */     //   697: ifeq -> 1153
/*      */     //   700: iload #10
/*      */     //   702: iload #11
/*      */     //   704: iconst_1
/*      */     //   705: ishl
/*      */     //   706: iadd
/*      */     //   707: istore #9
/*      */     //   709: iload #16
/*      */     //   711: ldc 40960
/*      */     //   713: iand
/*      */     //   714: sipush #8192
/*      */     //   717: if_icmpne -> 913
/*      */     //   720: aload_2
/*      */     //   721: aload #5
/*      */     //   723: iload #16
/*      */     //   725: sipush #255
/*      */     //   728: iand
/*      */     //   729: iaload
/*      */     //   730: invokevirtual a : (I)I
/*      */     //   733: ifeq -> 905
/*      */     //   736: getstatic c/a/c/a/g.F : [I
/*      */     //   739: iload #16
/*      */     //   741: iconst_4
/*      */     //   742: iushr
/*      */     //   743: sipush #511
/*      */     //   746: iand
/*      */     //   747: iaload
/*      */     //   748: istore #19
/*      */     //   750: aload_2
/*      */     //   751: iload #19
/*      */     //   753: bipush #15
/*      */     //   755: iand
/*      */     //   756: invokevirtual a : (I)I
/*      */     //   759: iload #19
/*      */     //   761: bipush #31
/*      */     //   763: iushr
/*      */     //   764: ixor
/*      */     //   765: istore #18
/*      */     //   767: aload #20
/*      */     //   769: iload #9
/*      */     //   771: iload #18
/*      */     //   773: bipush #31
/*      */     //   775: ishl
/*      */     //   776: iload #17
/*      */     //   778: ior
/*      */     //   779: iastore
/*      */     //   780: aload #4
/*      */     //   782: iload #7
/*      */     //   784: iload #25
/*      */     //   786: iadd
/*      */     //   787: dup2
/*      */     //   788: iaload
/*      */     //   789: ldc 536936448
/*      */     //   791: ior
/*      */     //   792: iastore
/*      */     //   793: aload #4
/*      */     //   795: iload #7
/*      */     //   797: iload #26
/*      */     //   799: iadd
/*      */     //   800: dup2
/*      */     //   801: iaload
/*      */     //   802: ldc 537001984
/*      */     //   804: ior
/*      */     //   805: iastore
/*      */     //   806: iload #18
/*      */     //   808: ifeq -> 858
/*      */     //   811: iload #16
/*      */     //   813: ldc 606126080
/*      */     //   815: ior
/*      */     //   816: istore #16
/*      */     //   818: aload #4
/*      */     //   820: iload #7
/*      */     //   822: iload #12
/*      */     //   824: isub
/*      */     //   825: dup2
/*      */     //   826: iaload
/*      */     //   827: ldc 571473920
/*      */     //   829: ior
/*      */     //   830: iastore
/*      */     //   831: aload #4
/*      */     //   833: iload #7
/*      */     //   835: iconst_1
/*      */     //   836: iadd
/*      */     //   837: dup2
/*      */     //   838: iaload
/*      */     //   839: ldc 537407616
/*      */     //   841: ior
/*      */     //   842: iastore
/*      */     //   843: aload #4
/*      */     //   845: iload #7
/*      */     //   847: iconst_1
/*      */     //   848: isub
/*      */     //   849: dup2
/*      */     //   850: iaload
/*      */     //   851: ldc 537143360
/*      */     //   853: ior
/*      */     //   854: iastore
/*      */     //   855: goto -> 913
/*      */     //   858: iload #16
/*      */     //   860: ldc 539017216
/*      */     //   862: ior
/*      */     //   863: istore #16
/*      */     //   865: aload #4
/*      */     //   867: iload #7
/*      */     //   869: iload #12
/*      */     //   871: isub
/*      */     //   872: dup2
/*      */     //   873: iaload
/*      */     //   874: ldc 537919488
/*      */     //   876: ior
/*      */     //   877: iastore
/*      */     //   878: aload #4
/*      */     //   880: iload #7
/*      */     //   882: iconst_1
/*      */     //   883: iadd
/*      */     //   884: dup2
/*      */     //   885: iaload
/*      */     //   886: ldc 537403520
/*      */     //   888: ior
/*      */     //   889: iastore
/*      */     //   890: aload #4
/*      */     //   892: iload #7
/*      */     //   894: iconst_1
/*      */     //   895: isub
/*      */     //   896: dup2
/*      */     //   897: iaload
/*      */     //   898: ldc 537141312
/*      */     //   900: ior
/*      */     //   901: iastore
/*      */     //   902: goto -> 913
/*      */     //   905: iload #16
/*      */     //   907: sipush #16384
/*      */     //   910: ior
/*      */     //   911: istore #16
/*      */     //   913: iload #24
/*      */     //   915: iconst_4
/*      */     //   916: if_icmpge -> 929
/*      */     //   919: aload #4
/*      */     //   921: iload #7
/*      */     //   923: iload #16
/*      */     //   925: iastore
/*      */     //   926: goto -> 1153
/*      */     //   929: iload #16
/*      */     //   931: ldc -1610612736
/*      */     //   933: iand
/*      */     //   934: ldc 536870912
/*      */     //   936: if_icmpne -> 1146
/*      */     //   939: iload #9
/*      */     //   941: iload #11
/*      */     //   943: iadd
/*      */     //   944: istore #9
/*      */     //   946: aload_2
/*      */     //   947: aload #5
/*      */     //   949: iload #16
/*      */     //   951: bipush #16
/*      */     //   953: iushr
/*      */     //   954: sipush #255
/*      */     //   957: iand
/*      */     //   958: iaload
/*      */     //   959: invokevirtual a : (I)I
/*      */     //   962: ifeq -> 1139
/*      */     //   965: getstatic c/a/c/a/g.F : [I
/*      */     //   968: iload #16
/*      */     //   970: bipush #20
/*      */     //   972: iushr
/*      */     //   973: sipush #511
/*      */     //   976: iand
/*      */     //   977: iaload
/*      */     //   978: istore #19
/*      */     //   980: aload_2
/*      */     //   981: iload #19
/*      */     //   983: bipush #15
/*      */     //   985: iand
/*      */     //   986: invokevirtual a : (I)I
/*      */     //   989: iload #19
/*      */     //   991: bipush #31
/*      */     //   993: iushr
/*      */     //   994: ixor
/*      */     //   995: istore #18
/*      */     //   997: aload #20
/*      */     //   999: iload #9
/*      */     //   1001: iload #18
/*      */     //   1003: bipush #31
/*      */     //   1005: ishl
/*      */     //   1006: iload #17
/*      */     //   1008: ior
/*      */     //   1009: iastore
/*      */     //   1010: aload #4
/*      */     //   1012: iload #7
/*      */     //   1014: iload #28
/*      */     //   1016: iadd
/*      */     //   1017: dup2
/*      */     //   1018: iaload
/*      */     //   1019: sipush #8196
/*      */     //   1022: ior
/*      */     //   1023: iastore
/*      */     //   1024: aload #4
/*      */     //   1026: iload #7
/*      */     //   1028: iload #27
/*      */     //   1030: iadd
/*      */     //   1031: dup2
/*      */     //   1032: iaload
/*      */     //   1033: sipush #8200
/*      */     //   1036: ior
/*      */     //   1037: iastore
/*      */     //   1038: iload #18
/*      */     //   1040: ifeq -> 1091
/*      */     //   1043: iload #16
/*      */     //   1045: ldc -1073733104
/*      */     //   1047: ior
/*      */     //   1048: istore #16
/*      */     //   1050: aload #4
/*      */     //   1052: iload #7
/*      */     //   1054: iload #12
/*      */     //   1056: iadd
/*      */     //   1057: dup2
/*      */     //   1058: iaload
/*      */     //   1059: sipush #9248
/*      */     //   1062: ior
/*      */     //   1063: iastore
/*      */     //   1064: aload #4
/*      */     //   1066: iload #7
/*      */     //   1068: iconst_1
/*      */     //   1069: iadd
/*      */     //   1070: dup2
/*      */     //   1071: iaload
/*      */     //   1072: ldc 813703170
/*      */     //   1074: ior
/*      */     //   1075: iastore
/*      */     //   1076: aload #4
/*      */     //   1078: iload #7
/*      */     //   1080: iconst_1
/*      */     //   1081: isub
/*      */     //   1082: dup2
/*      */     //   1083: iaload
/*      */     //   1084: ldc 675291137
/*      */     //   1086: ior
/*      */     //   1087: iastore
/*      */     //   1088: goto -> 1146
/*      */     //   1091: iload #16
/*      */     //   1093: ldc -1073733616
/*      */     //   1095: ior
/*      */     //   1096: istore #16
/*      */     //   1098: aload #4
/*      */     //   1100: iload #7
/*      */     //   1102: iload #12
/*      */     //   1104: iadd
/*      */     //   1105: dup2
/*      */     //   1106: iaload
/*      */     //   1107: sipush #8224
/*      */     //   1110: ior
/*      */     //   1111: iastore
/*      */     //   1112: aload #4
/*      */     //   1114: iload #7
/*      */     //   1116: iconst_1
/*      */     //   1117: iadd
/*      */     //   1118: dup2
/*      */     //   1119: iaload
/*      */     //   1120: ldc 545267714
/*      */     //   1122: ior
/*      */     //   1123: iastore
/*      */     //   1124: aload #4
/*      */     //   1126: iload #7
/*      */     //   1128: iconst_1
/*      */     //   1129: isub
/*      */     //   1130: dup2
/*      */     //   1131: iaload
/*      */     //   1132: ldc 541073409
/*      */     //   1134: ior
/*      */     //   1135: iastore
/*      */     //   1136: goto -> 1146
/*      */     //   1139: iload #16
/*      */     //   1141: ldc 1073741824
/*      */     //   1143: ior
/*      */     //   1144: istore #16
/*      */     //   1146: aload #4
/*      */     //   1148: iload #7
/*      */     //   1150: iload #16
/*      */     //   1152: iastore
/*      */     //   1153: iinc #10, 1
/*      */     //   1156: iinc #8, 1
/*      */     //   1159: goto -> 166
/*      */     //   1162: iinc #21, -1
/*      */     //   1165: iload #10
/*      */     //   1167: iload #14
/*      */     //   1169: iadd
/*      */     //   1170: istore #10
/*      */     //   1172: iload #8
/*      */     //   1174: iload #13
/*      */     //   1176: iadd
/*      */     //   1177: istore #8
/*      */     //   1179: goto -> 130
/*      */     //   1182: iconst_0
/*      */     //   1183: istore #29
/*      */     //   1185: iload #6
/*      */     //   1187: ifeq -> 1206
/*      */     //   1190: aload_0
/*      */     //   1191: getfield j : I
/*      */     //   1194: bipush #16
/*      */     //   1196: iand
/*      */     //   1197: ifeq -> 1206
/*      */     //   1200: aload_2
/*      */     //   1201: invokevirtual a : ()Z
/*      */     //   1204: istore #29
/*      */     //   1206: aload_0
/*      */     //   1207: getfield j : I
/*      */     //   1210: iconst_2
/*      */     //   1211: iand
/*      */     //   1212: ifeq -> 1219
/*      */     //   1215: aload_2
/*      */     //   1216: invokevirtual c : ()V
/*      */     //   1219: iload #29
/*      */     //   1221: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1004	-> 0
/*      */     //   #1005	-> 6
/*      */     //   #1006	-> 14
/*      */     //   #1007	-> 27
/*      */     //   #1008	-> 38
/*      */     //   #1009	-> 45
/*      */     //   #1010	-> 57
/*      */     //   #1011	-> 69
/*      */     //   #1014	-> 86
/*      */     //   #1015	-> 93
/*      */     //   #1016	-> 100
/*      */     //   #1017	-> 106
/*      */     //   #1020	-> 112
/*      */     //   #1021	-> 118
/*      */     //   #1022	-> 124
/*      */     //   #1023	-> 135
/*      */     //   #1025	-> 157
/*      */     //   #1027	-> 166
/*      */     //   #1029	-> 173
/*      */     //   #1030	-> 177
/*      */     //   #1034	-> 184
/*      */     //   #1035	-> 199
/*      */     //   #1037	-> 203
/*      */     //   #1040	-> 214
/*      */     //   #1043	-> 230
/*      */     //   #1044	-> 244
/*      */     //   #1047	-> 261
/*      */     //   #1052	-> 274
/*      */     //   #1055	-> 279
/*      */     //   #1057	-> 292
/*      */     //   #1061	-> 305
/*      */     //   #1062	-> 310
/*      */     //   #1065	-> 317
/*      */     //   #1068	-> 322
/*      */     //   #1071	-> 335
/*      */     //   #1075	-> 347
/*      */     //   #1081	-> 362
/*      */     //   #1083	-> 369
/*      */     //   #1086	-> 374
/*      */     //   #1089	-> 387
/*      */     //   #1092	-> 399
/*      */     //   #1098	-> 414
/*      */     //   #1101	-> 422
/*      */     //   #1102	-> 428
/*      */     //   #1103	-> 435
/*      */     //   #1106	-> 438
/*      */     //   #1108	-> 448
/*      */     //   #1110	-> 455
/*      */     //   #1114	-> 474
/*      */     //   #1115	-> 489
/*      */     //   #1118	-> 506
/*      */     //   #1123	-> 519
/*      */     //   #1124	-> 533
/*      */     //   #1126	-> 547
/*      */     //   #1127	-> 552
/*      */     //   #1130	-> 559
/*      */     //   #1132	-> 573
/*      */     //   #1136	-> 585
/*      */     //   #1142	-> 600
/*      */     //   #1144	-> 607
/*      */     //   #1146	-> 621
/*      */     //   #1149	-> 633
/*      */     //   #1155	-> 648
/*      */     //   #1158	-> 655
/*      */     //   #1161	-> 662
/*      */     //   #1162	-> 671
/*      */     //   #1163	-> 678
/*      */     //   #1167	-> 685
/*      */     //   #1168	-> 700
/*      */     //   #1170	-> 709
/*      */     //   #1173	-> 720
/*      */     //   #1176	-> 736
/*      */     //   #1177	-> 750
/*      */     //   #1180	-> 767
/*      */     //   #1185	-> 780
/*      */     //   #1187	-> 793
/*      */     //   #1190	-> 806
/*      */     //   #1191	-> 811
/*      */     //   #1194	-> 818
/*      */     //   #1196	-> 831
/*      */     //   #1200	-> 843
/*      */     //   #1206	-> 858
/*      */     //   #1208	-> 865
/*      */     //   #1210	-> 878
/*      */     //   #1213	-> 890
/*      */     //   #1219	-> 905
/*      */     //   #1222	-> 913
/*      */     //   #1223	-> 919
/*      */     //   #1224	-> 926
/*      */     //   #1227	-> 929
/*      */     //   #1229	-> 939
/*      */     //   #1231	-> 946
/*      */     //   #1235	-> 965
/*      */     //   #1236	-> 980
/*      */     //   #1239	-> 997
/*      */     //   #1244	-> 1010
/*      */     //   #1245	-> 1024
/*      */     //   #1247	-> 1038
/*      */     //   #1248	-> 1043
/*      */     //   #1251	-> 1050
/*      */     //   #1253	-> 1064
/*      */     //   #1257	-> 1076
/*      */     //   #1263	-> 1091
/*      */     //   #1265	-> 1098
/*      */     //   #1267	-> 1112
/*      */     //   #1270	-> 1124
/*      */     //   #1276	-> 1139
/*      */     //   #1279	-> 1146
/*      */     //   #1027	-> 1153
/*      */     //   #1022	-> 1162
/*      */     //   #1284	-> 1182
/*      */     //   #1287	-> 1185
/*      */     //   #1288	-> 1200
/*      */     //   #1292	-> 1206
/*      */     //   #1293	-> 1215
/*      */     //   #1297	-> 1219
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	1222	0	this	Lc/a/c/a/g;
/*      */     //   0	1222	1	cblk	Lc/a/e/c;
/*      */     //   0	1222	2	mq	Lc/a/c/a/f;
/*      */     //   0	1222	3	bp	I
/*      */     //   0	1222	4	state	[I
/*      */     //   0	1222	5	zc_lut	[I
/*      */     //   0	1222	6	isterm	Z
/*      */     //   6	1216	11	dscanw	I
/*      */     //   14	1208	12	sscanw	I
/*      */     //   27	1195	13	jstep	I
/*      */     //   38	1184	14	kstep	I
/*      */     //   45	1177	17	setmask	I
/*      */     //   57	1165	20	data	[I
/*      */     //   69	1153	23	nstripes	I
/*      */     //   86	1136	22	causal	Z
/*      */     //   93	1129	25	off_ul	I
/*      */     //   100	1122	26	off_ur	I
/*      */     //   106	1116	27	off_dr	I
/*      */     //   112	1110	28	off_dl	I
/*      */     //   118	1104	10	sk	I
/*      */     //   124	1098	8	sj	I
/*      */     //   130	1092	21	s	I
/*      */     //   157	1022	24	sheight	I
/*      */     //   166	1013	15	stopsk	I
/*      */     //   177	982	7	j	I
/*      */     //   184	975	16	csj	I
/*      */     //   203	459	9	k	I
/*      */     //   244	170	19	ctxt	I
/*      */     //   261	153	18	sym	I
/*      */     //   489	159	19	ctxt	I
/*      */     //   506	142	18	sym	I
/*      */     //   709	444	9	k	I
/*      */     //   750	155	19	ctxt	I
/*      */     //   767	138	18	sym	I
/*      */     //   980	159	19	ctxt	I
/*      */     //   997	142	18	sym	I
/*      */     //   1185	37	29	error	Z
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean a(c cblk, b bin, int bp, int[] state, boolean isterm) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: getfield j : I
/*      */     //   4: istore #10
/*      */     //   6: aload_1
/*      */     //   7: getfield g : I
/*      */     //   10: iconst_2
/*      */     //   11: iadd
/*      */     //   12: istore #11
/*      */     //   14: iload #11
/*      */     //   16: iconst_4
/*      */     //   17: imul
/*      */     //   18: iconst_2
/*      */     //   19: idiv
/*      */     //   20: aload_1
/*      */     //   21: getfield g : I
/*      */     //   24: isub
/*      */     //   25: istore #12
/*      */     //   27: iload #10
/*      */     //   29: iconst_4
/*      */     //   30: imul
/*      */     //   31: aload_1
/*      */     //   32: getfield g : I
/*      */     //   35: isub
/*      */     //   36: istore #13
/*      */     //   38: iconst_3
/*      */     //   39: iload_3
/*      */     //   40: ishl
/*      */     //   41: iconst_1
/*      */     //   42: ishr
/*      */     //   43: istore #16
/*      */     //   45: aload_1
/*      */     //   46: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   49: checkcast [I
/*      */     //   52: checkcast [I
/*      */     //   55: astore #18
/*      */     //   57: aload_1
/*      */     //   58: getfield h : I
/*      */     //   61: iconst_4
/*      */     //   62: iadd
/*      */     //   63: iconst_1
/*      */     //   64: isub
/*      */     //   65: iconst_4
/*      */     //   66: idiv
/*      */     //   67: istore #21
/*      */     //   69: aload_0
/*      */     //   70: getfield j : I
/*      */     //   73: bipush #8
/*      */     //   75: iand
/*      */     //   76: ifeq -> 83
/*      */     //   79: iconst_1
/*      */     //   80: goto -> 84
/*      */     //   83: iconst_0
/*      */     //   84: istore #20
/*      */     //   86: iload #11
/*      */     //   88: ineg
/*      */     //   89: iconst_1
/*      */     //   90: isub
/*      */     //   91: istore #23
/*      */     //   93: iload #11
/*      */     //   95: ineg
/*      */     //   96: iconst_1
/*      */     //   97: iadd
/*      */     //   98: istore #24
/*      */     //   100: iload #11
/*      */     //   102: iconst_1
/*      */     //   103: iadd
/*      */     //   104: istore #25
/*      */     //   106: iload #11
/*      */     //   108: iconst_1
/*      */     //   109: isub
/*      */     //   110: istore #26
/*      */     //   112: aload_1
/*      */     //   113: getfield i : I
/*      */     //   116: istore #9
/*      */     //   118: iload #11
/*      */     //   120: iconst_1
/*      */     //   121: iadd
/*      */     //   122: istore #7
/*      */     //   124: iload #21
/*      */     //   126: iconst_1
/*      */     //   127: isub
/*      */     //   128: istore #19
/*      */     //   130: iload #19
/*      */     //   132: iflt -> 1038
/*      */     //   135: iload #19
/*      */     //   137: ifeq -> 144
/*      */     //   140: iconst_4
/*      */     //   141: goto -> 155
/*      */     //   144: aload_1
/*      */     //   145: getfield h : I
/*      */     //   148: iload #21
/*      */     //   150: iconst_1
/*      */     //   151: isub
/*      */     //   152: iconst_4
/*      */     //   153: imul
/*      */     //   154: isub
/*      */     //   155: istore #22
/*      */     //   157: iload #9
/*      */     //   159: aload_1
/*      */     //   160: getfield g : I
/*      */     //   163: iadd
/*      */     //   164: istore #14
/*      */     //   166: iload #9
/*      */     //   168: iload #14
/*      */     //   170: if_icmpge -> 1018
/*      */     //   173: iload #7
/*      */     //   175: istore #6
/*      */     //   177: aload #4
/*      */     //   179: iload #6
/*      */     //   181: iaload
/*      */     //   182: istore #15
/*      */     //   184: iload #15
/*      */     //   186: iconst_m1
/*      */     //   187: ixor
/*      */     //   188: iload #15
/*      */     //   190: iconst_2
/*      */     //   191: ishl
/*      */     //   192: iand
/*      */     //   193: ldc -2147450880
/*      */     //   195: iand
/*      */     //   196: ifeq -> 590
/*      */     //   199: iload #9
/*      */     //   201: istore #8
/*      */     //   203: iload #15
/*      */     //   205: ldc 40960
/*      */     //   207: iand
/*      */     //   208: sipush #8192
/*      */     //   211: if_icmpne -> 388
/*      */     //   214: aload_2
/*      */     //   215: invokevirtual a : ()I
/*      */     //   218: ifeq -> 380
/*      */     //   221: aload_2
/*      */     //   222: invokevirtual a : ()I
/*      */     //   225: istore #17
/*      */     //   227: aload #18
/*      */     //   229: iload #8
/*      */     //   231: iload #17
/*      */     //   233: bipush #31
/*      */     //   235: ishl
/*      */     //   236: iload #16
/*      */     //   238: ior
/*      */     //   239: iastore
/*      */     //   240: iload #20
/*      */     //   242: ifne -> 271
/*      */     //   245: aload #4
/*      */     //   247: iload #6
/*      */     //   249: iload #23
/*      */     //   251: iadd
/*      */     //   252: dup2
/*      */     //   253: iaload
/*      */     //   254: ldc 536936448
/*      */     //   256: ior
/*      */     //   257: iastore
/*      */     //   258: aload #4
/*      */     //   260: iload #6
/*      */     //   262: iload #24
/*      */     //   264: iadd
/*      */     //   265: dup2
/*      */     //   266: iaload
/*      */     //   267: ldc 537001984
/*      */     //   269: ior
/*      */     //   270: iastore
/*      */     //   271: iload #17
/*      */     //   273: ifeq -> 328
/*      */     //   276: iload #15
/*      */     //   278: ldc 606126080
/*      */     //   280: ior
/*      */     //   281: istore #15
/*      */     //   283: iload #20
/*      */     //   285: ifne -> 301
/*      */     //   288: aload #4
/*      */     //   290: iload #6
/*      */     //   292: iload #11
/*      */     //   294: isub
/*      */     //   295: dup2
/*      */     //   296: iaload
/*      */     //   297: ldc 571473920
/*      */     //   299: ior
/*      */     //   300: iastore
/*      */     //   301: aload #4
/*      */     //   303: iload #6
/*      */     //   305: iconst_1
/*      */     //   306: iadd
/*      */     //   307: dup2
/*      */     //   308: iaload
/*      */     //   309: ldc 537407616
/*      */     //   311: ior
/*      */     //   312: iastore
/*      */     //   313: aload #4
/*      */     //   315: iload #6
/*      */     //   317: iconst_1
/*      */     //   318: isub
/*      */     //   319: dup2
/*      */     //   320: iaload
/*      */     //   321: ldc 537143360
/*      */     //   323: ior
/*      */     //   324: iastore
/*      */     //   325: goto -> 388
/*      */     //   328: iload #15
/*      */     //   330: ldc 539017216
/*      */     //   332: ior
/*      */     //   333: istore #15
/*      */     //   335: iload #20
/*      */     //   337: ifne -> 353
/*      */     //   340: aload #4
/*      */     //   342: iload #6
/*      */     //   344: iload #11
/*      */     //   346: isub
/*      */     //   347: dup2
/*      */     //   348: iaload
/*      */     //   349: ldc 537919488
/*      */     //   351: ior
/*      */     //   352: iastore
/*      */     //   353: aload #4
/*      */     //   355: iload #6
/*      */     //   357: iconst_1
/*      */     //   358: iadd
/*      */     //   359: dup2
/*      */     //   360: iaload
/*      */     //   361: ldc 537403520
/*      */     //   363: ior
/*      */     //   364: iastore
/*      */     //   365: aload #4
/*      */     //   367: iload #6
/*      */     //   369: iconst_1
/*      */     //   370: isub
/*      */     //   371: dup2
/*      */     //   372: iaload
/*      */     //   373: ldc 537141312
/*      */     //   375: ior
/*      */     //   376: iastore
/*      */     //   377: goto -> 388
/*      */     //   380: iload #15
/*      */     //   382: sipush #16384
/*      */     //   385: ior
/*      */     //   386: istore #15
/*      */     //   388: iload #22
/*      */     //   390: iconst_2
/*      */     //   391: if_icmpge -> 404
/*      */     //   394: aload #4
/*      */     //   396: iload #6
/*      */     //   398: iload #15
/*      */     //   400: iastore
/*      */     //   401: goto -> 1009
/*      */     //   404: iload #15
/*      */     //   406: ldc -1610612736
/*      */     //   408: iand
/*      */     //   409: ldc 536870912
/*      */     //   411: if_icmpne -> 583
/*      */     //   414: iload #8
/*      */     //   416: iload #10
/*      */     //   418: iadd
/*      */     //   419: istore #8
/*      */     //   421: aload_2
/*      */     //   422: invokevirtual a : ()I
/*      */     //   425: ifeq -> 576
/*      */     //   428: aload_2
/*      */     //   429: invokevirtual a : ()I
/*      */     //   432: istore #17
/*      */     //   434: aload #18
/*      */     //   436: iload #8
/*      */     //   438: iload #17
/*      */     //   440: bipush #31
/*      */     //   442: ishl
/*      */     //   443: iload #16
/*      */     //   445: ior
/*      */     //   446: iastore
/*      */     //   447: aload #4
/*      */     //   449: iload #6
/*      */     //   451: iload #26
/*      */     //   453: iadd
/*      */     //   454: dup2
/*      */     //   455: iaload
/*      */     //   456: sipush #8196
/*      */     //   459: ior
/*      */     //   460: iastore
/*      */     //   461: aload #4
/*      */     //   463: iload #6
/*      */     //   465: iload #25
/*      */     //   467: iadd
/*      */     //   468: dup2
/*      */     //   469: iaload
/*      */     //   470: sipush #8200
/*      */     //   473: ior
/*      */     //   474: iastore
/*      */     //   475: iload #17
/*      */     //   477: ifeq -> 528
/*      */     //   480: iload #15
/*      */     //   482: ldc -1073733104
/*      */     //   484: ior
/*      */     //   485: istore #15
/*      */     //   487: aload #4
/*      */     //   489: iload #6
/*      */     //   491: iload #11
/*      */     //   493: iadd
/*      */     //   494: dup2
/*      */     //   495: iaload
/*      */     //   496: sipush #9248
/*      */     //   499: ior
/*      */     //   500: iastore
/*      */     //   501: aload #4
/*      */     //   503: iload #6
/*      */     //   505: iconst_1
/*      */     //   506: iadd
/*      */     //   507: dup2
/*      */     //   508: iaload
/*      */     //   509: ldc 813703170
/*      */     //   511: ior
/*      */     //   512: iastore
/*      */     //   513: aload #4
/*      */     //   515: iload #6
/*      */     //   517: iconst_1
/*      */     //   518: isub
/*      */     //   519: dup2
/*      */     //   520: iaload
/*      */     //   521: ldc 675291137
/*      */     //   523: ior
/*      */     //   524: iastore
/*      */     //   525: goto -> 583
/*      */     //   528: iload #15
/*      */     //   530: ldc -1073733616
/*      */     //   532: ior
/*      */     //   533: istore #15
/*      */     //   535: aload #4
/*      */     //   537: iload #6
/*      */     //   539: iload #11
/*      */     //   541: iadd
/*      */     //   542: dup2
/*      */     //   543: iaload
/*      */     //   544: sipush #8224
/*      */     //   547: ior
/*      */     //   548: iastore
/*      */     //   549: aload #4
/*      */     //   551: iload #6
/*      */     //   553: iconst_1
/*      */     //   554: iadd
/*      */     //   555: dup2
/*      */     //   556: iaload
/*      */     //   557: ldc 545267714
/*      */     //   559: ior
/*      */     //   560: iastore
/*      */     //   561: aload #4
/*      */     //   563: iload #6
/*      */     //   565: iconst_1
/*      */     //   566: isub
/*      */     //   567: dup2
/*      */     //   568: iaload
/*      */     //   569: ldc 541073409
/*      */     //   571: ior
/*      */     //   572: iastore
/*      */     //   573: goto -> 583
/*      */     //   576: iload #15
/*      */     //   578: ldc 1073741824
/*      */     //   580: ior
/*      */     //   581: istore #15
/*      */     //   583: aload #4
/*      */     //   585: iload #6
/*      */     //   587: iload #15
/*      */     //   589: iastore
/*      */     //   590: iload #22
/*      */     //   592: iconst_3
/*      */     //   593: if_icmpge -> 599
/*      */     //   596: goto -> 1009
/*      */     //   599: iload #6
/*      */     //   601: iload #11
/*      */     //   603: iadd
/*      */     //   604: istore #6
/*      */     //   606: aload #4
/*      */     //   608: iload #6
/*      */     //   610: iaload
/*      */     //   611: istore #15
/*      */     //   613: iload #15
/*      */     //   615: iconst_m1
/*      */     //   616: ixor
/*      */     //   617: iload #15
/*      */     //   619: iconst_2
/*      */     //   620: ishl
/*      */     //   621: iand
/*      */     //   622: ldc -2147450880
/*      */     //   624: iand
/*      */     //   625: ifeq -> 1009
/*      */     //   628: iload #9
/*      */     //   630: iload #10
/*      */     //   632: iconst_1
/*      */     //   633: ishl
/*      */     //   634: iadd
/*      */     //   635: istore #8
/*      */     //   637: iload #15
/*      */     //   639: ldc 40960
/*      */     //   641: iand
/*      */     //   642: sipush #8192
/*      */     //   645: if_icmpne -> 807
/*      */     //   648: aload_2
/*      */     //   649: invokevirtual a : ()I
/*      */     //   652: ifeq -> 799
/*      */     //   655: aload_2
/*      */     //   656: invokevirtual a : ()I
/*      */     //   659: istore #17
/*      */     //   661: aload #18
/*      */     //   663: iload #8
/*      */     //   665: iload #17
/*      */     //   667: bipush #31
/*      */     //   669: ishl
/*      */     //   670: iload #16
/*      */     //   672: ior
/*      */     //   673: iastore
/*      */     //   674: aload #4
/*      */     //   676: iload #6
/*      */     //   678: iload #23
/*      */     //   680: iadd
/*      */     //   681: dup2
/*      */     //   682: iaload
/*      */     //   683: ldc 536936448
/*      */     //   685: ior
/*      */     //   686: iastore
/*      */     //   687: aload #4
/*      */     //   689: iload #6
/*      */     //   691: iload #24
/*      */     //   693: iadd
/*      */     //   694: dup2
/*      */     //   695: iaload
/*      */     //   696: ldc 537001984
/*      */     //   698: ior
/*      */     //   699: iastore
/*      */     //   700: iload #17
/*      */     //   702: ifeq -> 752
/*      */     //   705: iload #15
/*      */     //   707: ldc 606126080
/*      */     //   709: ior
/*      */     //   710: istore #15
/*      */     //   712: aload #4
/*      */     //   714: iload #6
/*      */     //   716: iload #11
/*      */     //   718: isub
/*      */     //   719: dup2
/*      */     //   720: iaload
/*      */     //   721: ldc 571473920
/*      */     //   723: ior
/*      */     //   724: iastore
/*      */     //   725: aload #4
/*      */     //   727: iload #6
/*      */     //   729: iconst_1
/*      */     //   730: iadd
/*      */     //   731: dup2
/*      */     //   732: iaload
/*      */     //   733: ldc 537407616
/*      */     //   735: ior
/*      */     //   736: iastore
/*      */     //   737: aload #4
/*      */     //   739: iload #6
/*      */     //   741: iconst_1
/*      */     //   742: isub
/*      */     //   743: dup2
/*      */     //   744: iaload
/*      */     //   745: ldc 537143360
/*      */     //   747: ior
/*      */     //   748: iastore
/*      */     //   749: goto -> 807
/*      */     //   752: iload #15
/*      */     //   754: ldc 539017216
/*      */     //   756: ior
/*      */     //   757: istore #15
/*      */     //   759: aload #4
/*      */     //   761: iload #6
/*      */     //   763: iload #11
/*      */     //   765: isub
/*      */     //   766: dup2
/*      */     //   767: iaload
/*      */     //   768: ldc 537919488
/*      */     //   770: ior
/*      */     //   771: iastore
/*      */     //   772: aload #4
/*      */     //   774: iload #6
/*      */     //   776: iconst_1
/*      */     //   777: iadd
/*      */     //   778: dup2
/*      */     //   779: iaload
/*      */     //   780: ldc 537403520
/*      */     //   782: ior
/*      */     //   783: iastore
/*      */     //   784: aload #4
/*      */     //   786: iload #6
/*      */     //   788: iconst_1
/*      */     //   789: isub
/*      */     //   790: dup2
/*      */     //   791: iaload
/*      */     //   792: ldc 537141312
/*      */     //   794: ior
/*      */     //   795: iastore
/*      */     //   796: goto -> 807
/*      */     //   799: iload #15
/*      */     //   801: sipush #16384
/*      */     //   804: ior
/*      */     //   805: istore #15
/*      */     //   807: iload #22
/*      */     //   809: iconst_4
/*      */     //   810: if_icmpge -> 823
/*      */     //   813: aload #4
/*      */     //   815: iload #6
/*      */     //   817: iload #15
/*      */     //   819: iastore
/*      */     //   820: goto -> 1009
/*      */     //   823: iload #15
/*      */     //   825: ldc -1610612736
/*      */     //   827: iand
/*      */     //   828: ldc 536870912
/*      */     //   830: if_icmpne -> 1002
/*      */     //   833: iload #8
/*      */     //   835: iload #10
/*      */     //   837: iadd
/*      */     //   838: istore #8
/*      */     //   840: aload_2
/*      */     //   841: invokevirtual a : ()I
/*      */     //   844: ifeq -> 995
/*      */     //   847: aload_2
/*      */     //   848: invokevirtual a : ()I
/*      */     //   851: istore #17
/*      */     //   853: aload #18
/*      */     //   855: iload #8
/*      */     //   857: iload #17
/*      */     //   859: bipush #31
/*      */     //   861: ishl
/*      */     //   862: iload #16
/*      */     //   864: ior
/*      */     //   865: iastore
/*      */     //   866: aload #4
/*      */     //   868: iload #6
/*      */     //   870: iload #26
/*      */     //   872: iadd
/*      */     //   873: dup2
/*      */     //   874: iaload
/*      */     //   875: sipush #8196
/*      */     //   878: ior
/*      */     //   879: iastore
/*      */     //   880: aload #4
/*      */     //   882: iload #6
/*      */     //   884: iload #25
/*      */     //   886: iadd
/*      */     //   887: dup2
/*      */     //   888: iaload
/*      */     //   889: sipush #8200
/*      */     //   892: ior
/*      */     //   893: iastore
/*      */     //   894: iload #17
/*      */     //   896: ifeq -> 947
/*      */     //   899: iload #15
/*      */     //   901: ldc -1073733104
/*      */     //   903: ior
/*      */     //   904: istore #15
/*      */     //   906: aload #4
/*      */     //   908: iload #6
/*      */     //   910: iload #11
/*      */     //   912: iadd
/*      */     //   913: dup2
/*      */     //   914: iaload
/*      */     //   915: sipush #9248
/*      */     //   918: ior
/*      */     //   919: iastore
/*      */     //   920: aload #4
/*      */     //   922: iload #6
/*      */     //   924: iconst_1
/*      */     //   925: iadd
/*      */     //   926: dup2
/*      */     //   927: iaload
/*      */     //   928: ldc 813703170
/*      */     //   930: ior
/*      */     //   931: iastore
/*      */     //   932: aload #4
/*      */     //   934: iload #6
/*      */     //   936: iconst_1
/*      */     //   937: isub
/*      */     //   938: dup2
/*      */     //   939: iaload
/*      */     //   940: ldc 675291137
/*      */     //   942: ior
/*      */     //   943: iastore
/*      */     //   944: goto -> 1002
/*      */     //   947: iload #15
/*      */     //   949: ldc -1073733616
/*      */     //   951: ior
/*      */     //   952: istore #15
/*      */     //   954: aload #4
/*      */     //   956: iload #6
/*      */     //   958: iload #11
/*      */     //   960: iadd
/*      */     //   961: dup2
/*      */     //   962: iaload
/*      */     //   963: sipush #8224
/*      */     //   966: ior
/*      */     //   967: iastore
/*      */     //   968: aload #4
/*      */     //   970: iload #6
/*      */     //   972: iconst_1
/*      */     //   973: iadd
/*      */     //   974: dup2
/*      */     //   975: iaload
/*      */     //   976: ldc 545267714
/*      */     //   978: ior
/*      */     //   979: iastore
/*      */     //   980: aload #4
/*      */     //   982: iload #6
/*      */     //   984: iconst_1
/*      */     //   985: isub
/*      */     //   986: dup2
/*      */     //   987: iaload
/*      */     //   988: ldc 541073409
/*      */     //   990: ior
/*      */     //   991: iastore
/*      */     //   992: goto -> 1002
/*      */     //   995: iload #15
/*      */     //   997: ldc 1073741824
/*      */     //   999: ior
/*      */     //   1000: istore #15
/*      */     //   1002: aload #4
/*      */     //   1004: iload #6
/*      */     //   1006: iload #15
/*      */     //   1008: iastore
/*      */     //   1009: iinc #9, 1
/*      */     //   1012: iinc #7, 1
/*      */     //   1015: goto -> 166
/*      */     //   1018: iinc #19, -1
/*      */     //   1021: iload #9
/*      */     //   1023: iload #13
/*      */     //   1025: iadd
/*      */     //   1026: istore #9
/*      */     //   1028: iload #7
/*      */     //   1030: iload #12
/*      */     //   1032: iadd
/*      */     //   1033: istore #7
/*      */     //   1035: goto -> 130
/*      */     //   1038: iconst_0
/*      */     //   1039: istore #27
/*      */     //   1041: iload #5
/*      */     //   1043: ifeq -> 1052
/*      */     //   1046: aload_2
/*      */     //   1047: invokevirtual b : ()Z
/*      */     //   1050: istore #27
/*      */     //   1052: iload #27
/*      */     //   1054: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1354	-> 0
/*      */     //   #1355	-> 6
/*      */     //   #1356	-> 14
/*      */     //   #1357	-> 27
/*      */     //   #1358	-> 38
/*      */     //   #1359	-> 45
/*      */     //   #1360	-> 57
/*      */     //   #1361	-> 69
/*      */     //   #1364	-> 86
/*      */     //   #1365	-> 93
/*      */     //   #1366	-> 100
/*      */     //   #1367	-> 106
/*      */     //   #1370	-> 112
/*      */     //   #1371	-> 118
/*      */     //   #1372	-> 124
/*      */     //   #1373	-> 135
/*      */     //   #1375	-> 157
/*      */     //   #1377	-> 166
/*      */     //   #1379	-> 173
/*      */     //   #1380	-> 177
/*      */     //   #1384	-> 184
/*      */     //   #1385	-> 199
/*      */     //   #1387	-> 203
/*      */     //   #1390	-> 214
/*      */     //   #1393	-> 221
/*      */     //   #1395	-> 227
/*      */     //   #1400	-> 240
/*      */     //   #1403	-> 245
/*      */     //   #1405	-> 258
/*      */     //   #1409	-> 271
/*      */     //   #1410	-> 276
/*      */     //   #1413	-> 283
/*      */     //   #1416	-> 288
/*      */     //   #1419	-> 301
/*      */     //   #1423	-> 313
/*      */     //   #1429	-> 328
/*      */     //   #1431	-> 335
/*      */     //   #1434	-> 340
/*      */     //   #1437	-> 353
/*      */     //   #1440	-> 365
/*      */     //   #1446	-> 380
/*      */     //   #1449	-> 388
/*      */     //   #1450	-> 394
/*      */     //   #1451	-> 401
/*      */     //   #1453	-> 404
/*      */     //   #1455	-> 414
/*      */     //   #1457	-> 421
/*      */     //   #1460	-> 428
/*      */     //   #1462	-> 434
/*      */     //   #1467	-> 447
/*      */     //   #1468	-> 461
/*      */     //   #1470	-> 475
/*      */     //   #1471	-> 480
/*      */     //   #1474	-> 487
/*      */     //   #1476	-> 501
/*      */     //   #1480	-> 513
/*      */     //   #1486	-> 528
/*      */     //   #1488	-> 535
/*      */     //   #1490	-> 549
/*      */     //   #1493	-> 561
/*      */     //   #1499	-> 576
/*      */     //   #1502	-> 583
/*      */     //   #1505	-> 590
/*      */     //   #1506	-> 599
/*      */     //   #1507	-> 606
/*      */     //   #1511	-> 613
/*      */     //   #1512	-> 628
/*      */     //   #1514	-> 637
/*      */     //   #1517	-> 648
/*      */     //   #1520	-> 655
/*      */     //   #1522	-> 661
/*      */     //   #1527	-> 674
/*      */     //   #1529	-> 687
/*      */     //   #1532	-> 700
/*      */     //   #1533	-> 705
/*      */     //   #1536	-> 712
/*      */     //   #1538	-> 725
/*      */     //   #1542	-> 737
/*      */     //   #1548	-> 752
/*      */     //   #1550	-> 759
/*      */     //   #1552	-> 772
/*      */     //   #1555	-> 784
/*      */     //   #1561	-> 799
/*      */     //   #1564	-> 807
/*      */     //   #1565	-> 813
/*      */     //   #1566	-> 820
/*      */     //   #1569	-> 823
/*      */     //   #1571	-> 833
/*      */     //   #1573	-> 840
/*      */     //   #1576	-> 847
/*      */     //   #1578	-> 853
/*      */     //   #1583	-> 866
/*      */     //   #1584	-> 880
/*      */     //   #1586	-> 894
/*      */     //   #1587	-> 899
/*      */     //   #1590	-> 906
/*      */     //   #1592	-> 920
/*      */     //   #1596	-> 932
/*      */     //   #1602	-> 947
/*      */     //   #1604	-> 954
/*      */     //   #1606	-> 968
/*      */     //   #1609	-> 980
/*      */     //   #1615	-> 995
/*      */     //   #1618	-> 1002
/*      */     //   #1377	-> 1009
/*      */     //   #1372	-> 1018
/*      */     //   #1623	-> 1038
/*      */     //   #1626	-> 1041
/*      */     //   #1627	-> 1046
/*      */     //   #1631	-> 1052
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	1055	0	this	Lc/a/c/a/g;
/*      */     //   0	1055	1	cblk	Lc/a/e/c;
/*      */     //   0	1055	2	bin	Lc/a/c/a/b;
/*      */     //   0	1055	3	bp	I
/*      */     //   0	1055	4	state	[I
/*      */     //   0	1055	5	isterm	Z
/*      */     //   6	1049	10	dscanw	I
/*      */     //   14	1041	11	sscanw	I
/*      */     //   27	1028	12	jstep	I
/*      */     //   38	1017	13	kstep	I
/*      */     //   45	1010	16	setmask	I
/*      */     //   57	998	18	data	[I
/*      */     //   69	986	21	nstripes	I
/*      */     //   86	969	20	causal	Z
/*      */     //   93	962	23	off_ul	I
/*      */     //   100	955	24	off_ur	I
/*      */     //   106	949	25	off_dr	I
/*      */     //   112	943	26	off_dl	I
/*      */     //   118	937	9	sk	I
/*      */     //   124	931	7	sj	I
/*      */     //   130	925	19	s	I
/*      */     //   157	878	22	sheight	I
/*      */     //   166	869	14	stopsk	I
/*      */     //   177	838	6	j	I
/*      */     //   184	831	15	csj	I
/*      */     //   203	387	8	k	I
/*      */     //   227	153	17	sym	I
/*      */     //   434	142	17	sym	I
/*      */     //   637	372	8	k	I
/*      */     //   661	138	17	sym	I
/*      */     //   853	142	17	sym	I
/*      */     //   1041	14	27	error	Z
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean a(c cblk, f mq, int bp, int[] state, boolean isterm) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: getfield j : I
/*      */     //   4: istore #10
/*      */     //   6: aload_1
/*      */     //   7: getfield g : I
/*      */     //   10: iconst_2
/*      */     //   11: iadd
/*      */     //   12: istore #11
/*      */     //   14: iload #11
/*      */     //   16: iconst_4
/*      */     //   17: imul
/*      */     //   18: iconst_2
/*      */     //   19: idiv
/*      */     //   20: aload_1
/*      */     //   21: getfield g : I
/*      */     //   24: isub
/*      */     //   25: istore #12
/*      */     //   27: iload #10
/*      */     //   29: iconst_4
/*      */     //   30: imul
/*      */     //   31: aload_1
/*      */     //   32: getfield g : I
/*      */     //   35: isub
/*      */     //   36: istore #13
/*      */     //   38: iconst_1
/*      */     //   39: iload_3
/*      */     //   40: ishl
/*      */     //   41: iconst_1
/*      */     //   42: ishr
/*      */     //   43: istore #16
/*      */     //   45: iconst_m1
/*      */     //   46: iload_3
/*      */     //   47: iconst_1
/*      */     //   48: iadd
/*      */     //   49: ishl
/*      */     //   50: istore #17
/*      */     //   52: aload_1
/*      */     //   53: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   56: checkcast [I
/*      */     //   59: checkcast [I
/*      */     //   62: astore #19
/*      */     //   64: aload_1
/*      */     //   65: getfield h : I
/*      */     //   68: iconst_4
/*      */     //   69: iadd
/*      */     //   70: iconst_1
/*      */     //   71: isub
/*      */     //   72: iconst_4
/*      */     //   73: idiv
/*      */     //   74: istore #21
/*      */     //   76: aload_1
/*      */     //   77: getfield i : I
/*      */     //   80: istore #9
/*      */     //   82: iload #11
/*      */     //   84: iconst_1
/*      */     //   85: iadd
/*      */     //   86: istore #7
/*      */     //   88: iload #21
/*      */     //   90: iconst_1
/*      */     //   91: isub
/*      */     //   92: istore #20
/*      */     //   94: iload #20
/*      */     //   96: iflt -> 546
/*      */     //   99: iload #20
/*      */     //   101: ifeq -> 108
/*      */     //   104: iconst_4
/*      */     //   105: goto -> 119
/*      */     //   108: aload_1
/*      */     //   109: getfield h : I
/*      */     //   112: iload #21
/*      */     //   114: iconst_1
/*      */     //   115: isub
/*      */     //   116: iconst_4
/*      */     //   117: imul
/*      */     //   118: isub
/*      */     //   119: istore #22
/*      */     //   121: iload #9
/*      */     //   123: aload_1
/*      */     //   124: getfield g : I
/*      */     //   127: iadd
/*      */     //   128: istore #14
/*      */     //   130: iload #9
/*      */     //   132: iload #14
/*      */     //   134: if_icmpge -> 526
/*      */     //   137: iload #7
/*      */     //   139: istore #6
/*      */     //   141: aload #4
/*      */     //   143: iload #6
/*      */     //   145: iaload
/*      */     //   146: istore #15
/*      */     //   148: iload #15
/*      */     //   150: iconst_1
/*      */     //   151: iushr
/*      */     //   152: iload #15
/*      */     //   154: iconst_m1
/*      */     //   155: ixor
/*      */     //   156: iand
/*      */     //   157: ldc 1073758208
/*      */     //   159: iand
/*      */     //   160: ifeq -> 317
/*      */     //   163: iload #9
/*      */     //   165: istore #8
/*      */     //   167: iload #15
/*      */     //   169: ldc 49152
/*      */     //   171: iand
/*      */     //   172: ldc 32768
/*      */     //   174: if_icmpne -> 226
/*      */     //   177: aload_2
/*      */     //   178: getstatic c/a/c/a/g.K : [I
/*      */     //   181: iload #15
/*      */     //   183: sipush #511
/*      */     //   186: iand
/*      */     //   187: iaload
/*      */     //   188: invokevirtual a : (I)I
/*      */     //   191: istore #18
/*      */     //   193: aload #19
/*      */     //   195: iload #8
/*      */     //   197: dup2
/*      */     //   198: iaload
/*      */     //   199: iload #17
/*      */     //   201: iand
/*      */     //   202: iastore
/*      */     //   203: aload #19
/*      */     //   205: iload #8
/*      */     //   207: dup2
/*      */     //   208: iaload
/*      */     //   209: iload #18
/*      */     //   211: iload_3
/*      */     //   212: ishl
/*      */     //   213: iload #16
/*      */     //   215: ior
/*      */     //   216: ior
/*      */     //   217: iastore
/*      */     //   218: iload #15
/*      */     //   220: sipush #256
/*      */     //   223: ior
/*      */     //   224: istore #15
/*      */     //   226: iload #22
/*      */     //   228: iconst_2
/*      */     //   229: if_icmpge -> 242
/*      */     //   232: aload #4
/*      */     //   234: iload #6
/*      */     //   236: iload #15
/*      */     //   238: iastore
/*      */     //   239: goto -> 517
/*      */     //   242: iload #15
/*      */     //   244: ldc -1073741824
/*      */     //   246: iand
/*      */     //   247: ldc -2147483648
/*      */     //   249: if_icmpne -> 310
/*      */     //   252: iload #8
/*      */     //   254: iload #10
/*      */     //   256: iadd
/*      */     //   257: istore #8
/*      */     //   259: aload_2
/*      */     //   260: getstatic c/a/c/a/g.K : [I
/*      */     //   263: iload #15
/*      */     //   265: bipush #16
/*      */     //   267: iushr
/*      */     //   268: sipush #511
/*      */     //   271: iand
/*      */     //   272: iaload
/*      */     //   273: invokevirtual a : (I)I
/*      */     //   276: istore #18
/*      */     //   278: aload #19
/*      */     //   280: iload #8
/*      */     //   282: dup2
/*      */     //   283: iaload
/*      */     //   284: iload #17
/*      */     //   286: iand
/*      */     //   287: iastore
/*      */     //   288: aload #19
/*      */     //   290: iload #8
/*      */     //   292: dup2
/*      */     //   293: iaload
/*      */     //   294: iload #18
/*      */     //   296: iload_3
/*      */     //   297: ishl
/*      */     //   298: iload #16
/*      */     //   300: ior
/*      */     //   301: ior
/*      */     //   302: iastore
/*      */     //   303: iload #15
/*      */     //   305: ldc 16777216
/*      */     //   307: ior
/*      */     //   308: istore #15
/*      */     //   310: aload #4
/*      */     //   312: iload #6
/*      */     //   314: iload #15
/*      */     //   316: iastore
/*      */     //   317: iload #22
/*      */     //   319: iconst_3
/*      */     //   320: if_icmpge -> 326
/*      */     //   323: goto -> 517
/*      */     //   326: iload #6
/*      */     //   328: iload #11
/*      */     //   330: iadd
/*      */     //   331: istore #6
/*      */     //   333: aload #4
/*      */     //   335: iload #6
/*      */     //   337: iaload
/*      */     //   338: istore #15
/*      */     //   340: iload #15
/*      */     //   342: iconst_1
/*      */     //   343: iushr
/*      */     //   344: iload #15
/*      */     //   346: iconst_m1
/*      */     //   347: ixor
/*      */     //   348: iand
/*      */     //   349: ldc 1073758208
/*      */     //   351: iand
/*      */     //   352: ifeq -> 517
/*      */     //   355: iload #9
/*      */     //   357: iload #10
/*      */     //   359: iconst_1
/*      */     //   360: ishl
/*      */     //   361: iadd
/*      */     //   362: istore #8
/*      */     //   364: iload #15
/*      */     //   366: ldc 49152
/*      */     //   368: iand
/*      */     //   369: ldc 32768
/*      */     //   371: if_icmpne -> 423
/*      */     //   374: aload_2
/*      */     //   375: getstatic c/a/c/a/g.K : [I
/*      */     //   378: iload #15
/*      */     //   380: sipush #511
/*      */     //   383: iand
/*      */     //   384: iaload
/*      */     //   385: invokevirtual a : (I)I
/*      */     //   388: istore #18
/*      */     //   390: aload #19
/*      */     //   392: iload #8
/*      */     //   394: dup2
/*      */     //   395: iaload
/*      */     //   396: iload #17
/*      */     //   398: iand
/*      */     //   399: iastore
/*      */     //   400: aload #19
/*      */     //   402: iload #8
/*      */     //   404: dup2
/*      */     //   405: iaload
/*      */     //   406: iload #18
/*      */     //   408: iload_3
/*      */     //   409: ishl
/*      */     //   410: iload #16
/*      */     //   412: ior
/*      */     //   413: ior
/*      */     //   414: iastore
/*      */     //   415: iload #15
/*      */     //   417: sipush #256
/*      */     //   420: ior
/*      */     //   421: istore #15
/*      */     //   423: iload #22
/*      */     //   425: iconst_4
/*      */     //   426: if_icmpge -> 439
/*      */     //   429: aload #4
/*      */     //   431: iload #6
/*      */     //   433: iload #15
/*      */     //   435: iastore
/*      */     //   436: goto -> 517
/*      */     //   439: aload #4
/*      */     //   441: iload #6
/*      */     //   443: iaload
/*      */     //   444: ldc -1073741824
/*      */     //   446: iand
/*      */     //   447: ldc -2147483648
/*      */     //   449: if_icmpne -> 510
/*      */     //   452: iload #8
/*      */     //   454: iload #10
/*      */     //   456: iadd
/*      */     //   457: istore #8
/*      */     //   459: aload_2
/*      */     //   460: getstatic c/a/c/a/g.K : [I
/*      */     //   463: iload #15
/*      */     //   465: bipush #16
/*      */     //   467: iushr
/*      */     //   468: sipush #511
/*      */     //   471: iand
/*      */     //   472: iaload
/*      */     //   473: invokevirtual a : (I)I
/*      */     //   476: istore #18
/*      */     //   478: aload #19
/*      */     //   480: iload #8
/*      */     //   482: dup2
/*      */     //   483: iaload
/*      */     //   484: iload #17
/*      */     //   486: iand
/*      */     //   487: iastore
/*      */     //   488: aload #19
/*      */     //   490: iload #8
/*      */     //   492: dup2
/*      */     //   493: iaload
/*      */     //   494: iload #18
/*      */     //   496: iload_3
/*      */     //   497: ishl
/*      */     //   498: iload #16
/*      */     //   500: ior
/*      */     //   501: ior
/*      */     //   502: iastore
/*      */     //   503: iload #15
/*      */     //   505: ldc 16777216
/*      */     //   507: ior
/*      */     //   508: istore #15
/*      */     //   510: aload #4
/*      */     //   512: iload #6
/*      */     //   514: iload #15
/*      */     //   516: iastore
/*      */     //   517: iinc #9, 1
/*      */     //   520: iinc #7, 1
/*      */     //   523: goto -> 130
/*      */     //   526: iinc #20, -1
/*      */     //   529: iload #9
/*      */     //   531: iload #13
/*      */     //   533: iadd
/*      */     //   534: istore #9
/*      */     //   536: iload #7
/*      */     //   538: iload #12
/*      */     //   540: iadd
/*      */     //   541: istore #7
/*      */     //   543: goto -> 94
/*      */     //   546: iconst_0
/*      */     //   547: istore #23
/*      */     //   549: iload #5
/*      */     //   551: ifeq -> 570
/*      */     //   554: aload_0
/*      */     //   555: getfield j : I
/*      */     //   558: bipush #16
/*      */     //   560: iand
/*      */     //   561: ifeq -> 570
/*      */     //   564: aload_2
/*      */     //   565: invokevirtual a : ()Z
/*      */     //   568: istore #23
/*      */     //   570: aload_0
/*      */     //   571: getfield j : I
/*      */     //   574: iconst_2
/*      */     //   575: iand
/*      */     //   576: ifeq -> 583
/*      */     //   579: aload_2
/*      */     //   580: invokevirtual c : ()V
/*      */     //   583: iload #23
/*      */     //   585: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1681	-> 0
/*      */     //   #1682	-> 6
/*      */     //   #1683	-> 14
/*      */     //   #1684	-> 27
/*      */     //   #1685	-> 38
/*      */     //   #1686	-> 45
/*      */     //   #1687	-> 52
/*      */     //   #1688	-> 64
/*      */     //   #1691	-> 76
/*      */     //   #1692	-> 82
/*      */     //   #1693	-> 88
/*      */     //   #1694	-> 99
/*      */     //   #1696	-> 121
/*      */     //   #1698	-> 130
/*      */     //   #1700	-> 137
/*      */     //   #1701	-> 141
/*      */     //   #1704	-> 148
/*      */     //   #1705	-> 163
/*      */     //   #1707	-> 167
/*      */     //   #1710	-> 177
/*      */     //   #1712	-> 193
/*      */     //   #1713	-> 203
/*      */     //   #1715	-> 218
/*      */     //   #1717	-> 226
/*      */     //   #1718	-> 232
/*      */     //   #1719	-> 239
/*      */     //   #1722	-> 242
/*      */     //   #1724	-> 252
/*      */     //   #1726	-> 259
/*      */     //   #1729	-> 278
/*      */     //   #1730	-> 288
/*      */     //   #1732	-> 303
/*      */     //   #1734	-> 310
/*      */     //   #1737	-> 317
/*      */     //   #1738	-> 326
/*      */     //   #1739	-> 333
/*      */     //   #1742	-> 340
/*      */     //   #1743	-> 355
/*      */     //   #1745	-> 364
/*      */     //   #1748	-> 374
/*      */     //   #1750	-> 390
/*      */     //   #1751	-> 400
/*      */     //   #1753	-> 415
/*      */     //   #1755	-> 423
/*      */     //   #1756	-> 429
/*      */     //   #1757	-> 436
/*      */     //   #1760	-> 439
/*      */     //   #1762	-> 452
/*      */     //   #1764	-> 459
/*      */     //   #1767	-> 478
/*      */     //   #1768	-> 488
/*      */     //   #1770	-> 503
/*      */     //   #1772	-> 510
/*      */     //   #1698	-> 517
/*      */     //   #1693	-> 526
/*      */     //   #1777	-> 546
/*      */     //   #1780	-> 549
/*      */     //   #1781	-> 564
/*      */     //   #1785	-> 570
/*      */     //   #1786	-> 579
/*      */     //   #1790	-> 583
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	586	0	this	Lc/a/c/a/g;
/*      */     //   0	586	1	cblk	Lc/a/e/c;
/*      */     //   0	586	2	mq	Lc/a/c/a/f;
/*      */     //   0	586	3	bp	I
/*      */     //   0	586	4	state	[I
/*      */     //   0	586	5	isterm	Z
/*      */     //   6	580	10	dscanw	I
/*      */     //   14	572	11	sscanw	I
/*      */     //   27	559	12	jstep	I
/*      */     //   38	548	13	kstep	I
/*      */     //   45	541	16	setmask	I
/*      */     //   52	534	17	resetmask	I
/*      */     //   64	522	19	data	[I
/*      */     //   76	510	21	nstripes	I
/*      */     //   82	504	9	sk	I
/*      */     //   88	498	7	sj	I
/*      */     //   94	492	20	s	I
/*      */     //   121	422	22	sheight	I
/*      */     //   130	413	14	stopsk	I
/*      */     //   141	382	6	j	I
/*      */     //   148	375	15	csj	I
/*      */     //   167	150	8	k	I
/*      */     //   193	33	18	sym	I
/*      */     //   278	32	18	sym	I
/*      */     //   364	153	8	k	I
/*      */     //   390	33	18	sym	I
/*      */     //   478	32	18	sym	I
/*      */     //   549	37	23	error	Z
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean b(c cblk, b bin, int bp, int[] state, boolean isterm) {
/* 1843 */     int dscanw = cblk.j;
/* 1844 */     int sscanw = cblk.g + 2;
/* 1845 */     int jstep = sscanw * 4 / 2 - cblk.g;
/* 1846 */     int kstep = dscanw * 4 - cblk.g;
/* 1847 */     int setmask = 1 << bp >> 1;
/* 1848 */     int resetmask = -1 << bp + 1;
/* 1849 */     int[] data = (int[])cblk.b();
/* 1850 */     int nstripes = (cblk.h + 4 - 1) / 4;
/*      */ 
/*      */     
/* 1853 */     int sk = cblk.i;
/* 1854 */     int sj = sscanw + 1;
/* 1855 */     for (int s = nstripes - 1; s >= 0; s--, sk += kstep, sj += jstep) {
/* 1856 */       int sheight = (s != 0) ? 4 : (cblk.h - (nstripes - 1) * 4);
/*      */       
/* 1858 */       int stopsk = sk + cblk.g;
/*      */       
/* 1860 */       for (; sk < stopsk; sk++, sj++) {
/*      */         
/* 1862 */         int j = sj;
/* 1863 */         int csj = state[j];
/*      */ 
/*      */         
/* 1866 */         if ((csj >>> 1 & (csj ^ 0xFFFFFFFF) & 0x40004000) != 0) {
/* 1867 */           int k = sk;
/*      */           
/* 1869 */           if ((csj & 0xC000) == 32768) {
/*      */ 
/*      */             
/* 1872 */             int sym = bin.a();
/*      */             
/* 1874 */             data[k] = data[k] & resetmask;
/* 1875 */             data[k] = data[k] | sym << bp | setmask;
/*      */           } 
/*      */ 
/*      */           
/* 1879 */           if (sheight < 2)
/*      */             continue; 
/* 1881 */           if ((csj & 0xC0000000) == Integer.MIN_VALUE) {
/*      */             
/* 1883 */             k += dscanw;
/*      */             
/* 1885 */             int sym = bin.a();
/*      */             
/* 1887 */             data[k] = data[k] & resetmask;
/* 1888 */             data[k] = data[k] | sym << bp | setmask;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1894 */         if (sheight >= 3) {
/* 1895 */           j += sscanw;
/* 1896 */           csj = state[j];
/*      */ 
/*      */           
/* 1899 */           if ((csj >>> 1 & (csj ^ 0xFFFFFFFF) & 0x40004000) != 0) {
/* 1900 */             int k = sk + (dscanw << 1);
/*      */             
/* 1902 */             if ((csj & 0xC000) == 32768) {
/*      */ 
/*      */               
/* 1905 */               int sym = bin.a();
/*      */               
/* 1907 */               data[k] = data[k] & resetmask;
/* 1908 */               data[k] = data[k] | sym << bp | setmask;
/*      */             } 
/*      */ 
/*      */             
/* 1912 */             if (sheight >= 4)
/*      */             {
/* 1914 */               if ((state[j] & 0xC0000000) == Integer.MIN_VALUE) {
/*      */                 
/* 1916 */                 k += dscanw;
/*      */                 
/* 1918 */                 int sym = bin.a();
/*      */                 
/* 1920 */                 data[k] = data[k] & resetmask;
/* 1921 */                 data[k] = data[k] | sym << bp | setmask;
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */         continue;
/*      */       } 
/*      */     } 
/* 1929 */     boolean error = false;
/*      */ 
/*      */     
/* 1932 */     if (isterm && (this.j & 0x10) != 0) {
/* 1933 */       error = bin.b();
/*      */     }
/*      */ 
/*      */     
/* 1937 */     return error;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean b(c cblk, f mq, int bp, int[] state, int[] zc_lut, boolean isterm) {
/*      */     boolean bool1;
/* 1996 */     int dscanw = cblk.j;
/* 1997 */     int sscanw = cblk.g + 2;
/* 1998 */     int jstep = sscanw * 4 / 2 - cblk.g;
/* 1999 */     int kstep = dscanw * 4 - cblk.g;
/* 2000 */     int setmask = 3 << bp >> 1;
/* 2001 */     int[] data = (int[])cblk.b();
/* 2002 */     int nstripes = (cblk.h + 4 - 1) / 4;
/* 2003 */     boolean causal = ((this.j & 0x8) != 0);
/*      */ 
/*      */     
/* 2006 */     int off_ul = -sscanw - 1;
/* 2007 */     int off_ur = -sscanw + 1;
/* 2008 */     int off_dr = sscanw + 1;
/* 2009 */     int off_dl = sscanw - 1;
/*      */ 
/*      */     
/* 2012 */     int sk = cblk.i;
/* 2013 */     int sj = sscanw + 1;
/* 2014 */     for (int s = nstripes - 1; s >= 0; s--, sk += kstep, sj += jstep) {
/* 2015 */       int sheight = (s != 0) ? 4 : (cblk.h - (nstripes - 1) * 4);
/*      */       
/* 2017 */       int stopsk = sk + cblk.g;
/*      */       while (true) {
/* 2019 */         sk++; sj++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       continue;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2418 */     if ((this.j & 0x20) != 0) {
/* 2419 */       int sym = mq.a(0) << 3;
/* 2420 */       sym |= mq.a(0) << 2;
/* 2421 */       sym |= mq.a(0) << 1;
/* 2422 */       sym |= mq.a(0);
/*      */       
/* 2424 */       bool1 = (sym != 10);
/*      */     } else {
/*      */       
/* 2427 */       bool1 = false;
/*      */     } 
/*      */ 
/*      */     
/* 2431 */     if (isterm && (this.j & 0x10) != 0) {
/* 2432 */       bool1 = mq.a();
/*      */     }
/*      */ 
/*      */     
/* 2436 */     if ((this.j & 0x2) != 0) {
/* 2437 */       mq.c();
/*      */     }
/*      */ 
/*      */     
/* 2441 */     return bool1;
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
/*      */   private void a(c cblk, int bp) {
/* 2467 */     int setmask = 1 << bp;
/* 2468 */     int resetmask = -1 << bp;
/*      */ 
/*      */     
/* 2471 */     int[] data = (int[])cblk.b();
/*      */ 
/*      */ 
/*      */     
/* 2475 */     for (int l = cblk.h - 1, k = cblk.i; l >= 0; l--) {
/* 2476 */       for (int kmax = k + cblk.g; k < kmax; k++) {
/* 2477 */         int dk = data[k];
/* 2478 */         if ((dk & resetmask & Integer.MAX_VALUE) != 0) {
/*      */ 
/*      */           
/* 2481 */           data[k] = dk & resetmask | setmask;
/*      */         }
/*      */         else {
/*      */           
/* 2485 */           data[k] = 0;
/*      */         } 
/*      */       } 
/* 2488 */       k += cblk.j - cblk.g;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/a/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */