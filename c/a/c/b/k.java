/*      */ package c.a.c.b;
/*      */ 
/*      */ import c.a.c.c;
/*      */ import c.a.c.f;
/*      */ import c.a.g;
/*      */ import c.a.i.c;
/*      */ import c.a.i.l;
/*      */ import c.a.j.a.g;
/*      */ import java.awt.Point;
/*      */ import java.util.Stack;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class k
/*      */   extends g
/*      */   implements f
/*      */ {
/*      */   private static final boolean A = false;
/*      */   private long[] B;
/*      */   public static final String c = "jj2000.j2k.entropy.encoder.StdEntropyCoder.nthreads";
/*      */   public static final String d = "0";
/*      */   public static final int e = 0;
/*      */   private l C;
/*      */   private Stack D;
/*      */   private Stack[] E;
/*      */   private int[] F;
/*      */   private boolean[] G;
/*      */   private i[] H;
/*      */   private a[] I;
/*      */   private b[] J;
/*      */   private c.a.c.a K;
/*      */   private c L;
/*      */   public g f;
/*      */   public g g;
/*      */   public g h;
/*      */   public g i;
/*      */   public g j;
/*      */   public g y;
/*      */   public g z;
/*      */   private int[][] M;
/*      */   private int[][] N;
/*      */   private int[][] O;
/*      */   private static final int P = 8;
/*  220 */   private static final int[] Q = new int[256];
/*      */ 
/*      */   
/*  223 */   private static final int[] R = new int[256];
/*      */ 
/*      */   
/*  226 */   private static final int[] S = new int[256];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int T = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  240 */   private static final int[] U = new int[512];
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int V = 15;
/*      */ 
/*      */   
/*      */   private static final int W = 31;
/*      */ 
/*      */   
/*      */   private static final int X = -2147483648;
/*      */ 
/*      */   
/*      */   private static final int Y = 9;
/*      */ 
/*      */   
/*  256 */   private static final int[] Z = new int[512];
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aa = 19;
/*      */ 
/*      */   
/*      */   private static final int ab = 1;
/*      */ 
/*      */   
/*      */   private static final int ac = 0;
/*      */ 
/*      */   
/*  269 */   private static final int[] ad = new int[] { 46, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */   
/*  273 */   private static final int[] ae = new int[] { 1, 0, 1, 0 };
/*      */ 
/*      */ 
/*      */   
/*  277 */   private static final int[] af = new int[] { 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] ag;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ah = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ai = 32768;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aj = 16384;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ak = 8192;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int al = 4096;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int am = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int an = 1024;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ao = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ap = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aq = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ar = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int as = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int at = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int au = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int av = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aw = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ax = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ay = -2147483648;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int az = 1073741824;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aA = 536870912;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aB = 268435456;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aC = 134217728;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aD = 67108864;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aE = 33554432;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aF = 16777216;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aG = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aH = 4194304;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aI = 2097152;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aJ = 1048576;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aK = 524288;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aL = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aM = 131072;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aN = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aO = -2147450880;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aP = 1073758208;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aQ = -536813568;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aR = 255;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aS = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aT = 20;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aU = 511;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aV = 511;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aW = 7;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int aX = 13;
/*      */ 
/*      */ 
/*      */   
/*  494 */   private static final int[] aY = new int[64];
/*      */ 
/*      */ 
/*      */   
/*  498 */   private static final int[] aZ = new int[128];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  504 */   private static final int[] ba = new int[64];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  511 */   private static final int[] bb = new int[128];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double[][] bc;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] bd;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean[][] be;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private g[] bf;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] bg;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] bh;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean[][] bi;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class a
/*      */     implements Runnable
/*      */   {
/*      */     private final int h;
/*      */ 
/*      */ 
/*      */     
/*      */     c a;
/*      */ 
/*      */ 
/*      */     
/*      */     int b;
/*      */ 
/*      */ 
/*      */     
/*      */     int c;
/*      */ 
/*      */ 
/*      */     
/*      */     boolean d;
/*      */ 
/*      */ 
/*      */     
/*      */     int e;
/*      */ 
/*      */ 
/*      */     
/*      */     int f;
/*      */ 
/*      */ 
/*      */     
/*      */     private long[] i;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     a(k this$0, int idx) {
/*  588 */       this.h = idx;
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
/*      */     public void run() {
/*      */       try {
/*  602 */         long stime = 0L;
/*      */         
/*  604 */         k.a(this.b, this.a, k.a(this.g)[this.h], k.b(this.g)[this.h], k.c(this.g)[this.h], 
/*  605 */             k.d(this.g)[this.h], k.e(this.g)[this.h], k.f(this.g)[this.h], 
/*  606 */             k.g(this.g)[this.h], k.h(this.g)[this.h], 
/*  607 */             k.i(this.g)[this.h], k.j(this.g)[this.h], this.c, this.d, this.e, this.f);
/*      */ 
/*      */       
/*      */       }
/*      */       finally {
/*      */ 
/*      */         
/*  614 */         k.k(this.g)[this.b].push(this);
/*      */       } 
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
/*      */     synchronized long a(int i) {
/*  633 */       return 0L;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int a() {
/*  643 */       return this.h;
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
/*      */   static {
/*  661 */     Q[0] = 2;
/*      */     
/*      */     int m;
/*  664 */     for (m = 1; m < 16; m++) {
/*  665 */       Q[m] = 4;
/*      */     }
/*  667 */     for (m = 0; m < 4; m++) {
/*  668 */       Q[1 << m] = 3;
/*      */     }
/*      */     
/*  671 */     for (m = 0; m < 16; m++) {
/*      */       
/*  673 */       Q[0x20 | m] = 5;
/*  674 */       Q[0x10 | m] = 5;
/*      */       
/*  676 */       Q[0x30 | m] = 6;
/*      */     } 
/*      */     
/*  679 */     Q[128] = 7;
/*  680 */     Q[64] = 7;
/*      */ 
/*      */     
/*  683 */     for (m = 1; m < 16; m++) {
/*  684 */       Q[0x80 | m] = 8;
/*  685 */       Q[0x40 | m] = 8;
/*      */     } 
/*      */ 
/*      */     
/*  689 */     for (m = 1; m < 4; m++) {
/*  690 */       for (int n = 0; n < 16; n++) {
/*  691 */         Q[0x80 | m << 4 | n] = 9;
/*  692 */         Q[0x40 | m << 4 | n] = 9;
/*      */       } 
/*      */     } 
/*      */     
/*  696 */     for (m = 0; m < 64; m++) {
/*  697 */       Q[0xC0 | m] = 10;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  703 */     R[0] = 2;
/*      */     
/*  705 */     for (m = 1; m < 16; m++) {
/*  706 */       R[m] = 4;
/*      */     }
/*  708 */     for (m = 0; m < 4; m++) {
/*  709 */       R[1 << m] = 3;
/*      */     }
/*      */     
/*  712 */     for (m = 0; m < 16; m++) {
/*      */       
/*  714 */       R[0x80 | m] = 5;
/*  715 */       R[0x40 | m] = 5;
/*      */       
/*  717 */       R[0xC0 | m] = 6;
/*      */     } 
/*      */     
/*  720 */     R[32] = 7;
/*  721 */     R[16] = 7;
/*      */ 
/*      */     
/*  724 */     for (m = 1; m < 16; m++) {
/*  725 */       R[0x20 | m] = 8;
/*  726 */       R[0x10 | m] = 8;
/*      */     } 
/*      */ 
/*      */     
/*  730 */     for (m = 1; m < 4; m++) {
/*  731 */       for (int n = 0; n < 16; n++) {
/*  732 */         R[m << 6 | 0x20 | n] = 9;
/*  733 */         R[m << 6 | 0x10 | n] = 9;
/*      */       } 
/*      */     } 
/*      */     
/*  737 */     for (m = 0; m < 4; m++) {
/*  738 */       for (int n = 0; n < 16; n++) {
/*  739 */         R[m << 6 | 0x20 | 0x10 | n] = 10;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  744 */     int[] twoBits = { 3, 5, 6, 9, 10, 12 };
/*      */ 
/*      */     
/*  747 */     int[] oneBit = { 1, 2, 4, 8 };
/*      */ 
/*      */     
/*  750 */     int[] twoLeast = { 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15 };
/*      */ 
/*      */ 
/*      */     
/*  754 */     int[] threeLeast = { 7, 11, 13, 14, 15 };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  759 */     S[0] = 2;
/*      */ 
/*      */     
/*  762 */     for (m = 0; m < oneBit.length; m++) {
/*  763 */       S[oneBit[m] << 4] = 3;
/*      */     }
/*      */     
/*  766 */     for (m = 0; m < twoLeast.length; m++) {
/*  767 */       S[twoLeast[m] << 4] = 4;
/*      */     }
/*      */     
/*  770 */     for (m = 0; m < oneBit.length; m++) {
/*  771 */       S[oneBit[m]] = 5;
/*      */     }
/*      */     
/*  774 */     for (m = 0; m < oneBit.length; m++) {
/*  775 */       for (int n = 0; n < oneBit.length; n++) {
/*  776 */         S[oneBit[m] << 4 | oneBit[n]] = 6;
/*      */       }
/*      */     } 
/*  779 */     for (m = 0; m < twoLeast.length; m++) {
/*  780 */       for (int n = 0; n < oneBit.length; n++) {
/*  781 */         S[twoLeast[m] << 4 | oneBit[n]] = 7;
/*      */       }
/*      */     } 
/*  784 */     for (m = 0; m < twoBits.length; m++) {
/*  785 */       S[twoBits[m]] = 8;
/*      */     }
/*      */     int j;
/*  788 */     for (j = 0; j < twoBits.length; j++) {
/*  789 */       for (m = 1; m < 16; m++) {
/*  790 */         S[m << 4 | twoBits[j]] = 9;
/*      */       }
/*      */     } 
/*  793 */     for (m = 0; m < 16; m++) {
/*  794 */       for (j = 0; j < threeLeast.length; j++) {
/*  795 */         S[m << 4 | threeLeast[j]] = 10;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  803 */     int[] inter_sc_lut = new int[36];
/*  804 */     inter_sc_lut[18] = 15;
/*  805 */     inter_sc_lut[17] = 14;
/*  806 */     inter_sc_lut[16] = 13;
/*  807 */     inter_sc_lut[10] = 12;
/*  808 */     inter_sc_lut[9] = 11;
/*  809 */     inter_sc_lut[8] = -2147483636;
/*  810 */     inter_sc_lut[2] = -2147483635;
/*  811 */     inter_sc_lut[1] = -2147483634;
/*  812 */     inter_sc_lut[0] = -2147483633;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  821 */     for (m = 0; m < 511; m++) {
/*  822 */       int ds = m & 0x1;
/*  823 */       int us = m >> 1 & 0x1;
/*  824 */       int rs = m >> 2 & 0x1;
/*  825 */       int ls = m >> 3 & 0x1;
/*  826 */       int dsgn = m >> 5 & 0x1;
/*  827 */       int usgn = m >> 6 & 0x1;
/*  828 */       int rsgn = m >> 7 & 0x1;
/*  829 */       int lsgn = m >> 8 & 0x1;
/*      */       
/*  831 */       int h = ls * (1 - 2 * lsgn) + rs * (1 - 2 * rsgn);
/*  832 */       h = (h >= -1) ? h : -1;
/*  833 */       h = (h <= 1) ? h : 1;
/*  834 */       int v = us * (1 - 2 * usgn) + ds * (1 - 2 * dsgn);
/*  835 */       v = (v >= -1) ? v : -1;
/*  836 */       v = (v <= 1) ? v : 1;
/*      */       
/*  838 */       U[m] = inter_sc_lut[h + 1 << 3 | v + 1];
/*      */     } 
/*  840 */     inter_sc_lut = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  845 */     Z[0] = 16;
/*      */     
/*  847 */     for (m = 1; m < 256; m++) {
/*  848 */       Z[m] = 17;
/*      */     }
/*      */     
/*  851 */     for (; m < 512; m++) {
/*  852 */       Z[m] = 18;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     for (m = 0; m < 64; m++) {
/*      */       
/*  860 */       double val = m / 64.0D + 1.0D;
/*  861 */       double deltaMSE = val * val;
/*  862 */       ba[m] = 
/*  863 */         (int)Math.floor(deltaMSE * 8192.0D + 0.5D);
/*      */       
/*  865 */       val -= 1.5D;
/*  866 */       deltaMSE -= val * val;
/*  867 */       aY[m] = 
/*  868 */         (int)Math.floor(deltaMSE * 8192.0D + 0.5D);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  873 */     for (m = 0; m < 128; m++) {
/*  874 */       double val = m / 64.0D;
/*  875 */       double deltaMSE = (val - 1.0D) * (val - 1.0D);
/*  876 */       bb[m] = 
/*  877 */         (int)Math.floor(deltaMSE * 8192.0D + 0.5D);
/*      */       
/*  879 */       val -= (m < 64) ? 0.5D : 1.5D;
/*  880 */       deltaMSE -= val * val;
/*  881 */       aZ[m] = 
/*  882 */         (int)Math.floor(deltaMSE * 8192.0D + 0.5D);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public k(c.a.g.b.a src, c.a.c.a cblks, c pss, g bms, g mqrs, g rts, g css, g sss, g lcs, g tts) {
/*  921 */     super(src); int nt, tsl; this.M = (int[][])null; this.N = (int[][])null; this.O = (int[][])null;
/*  922 */     this.K = cblks;
/*  923 */     this.L = pss;
/*  924 */     this.f = bms;
/*  925 */     this.g = mqrs;
/*  926 */     this.h = rts;
/*  927 */     this.i = css;
/*  928 */     this.j = sss;
/*  929 */     this.y = lcs;
/*  930 */     this.z = tts;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  937 */     int maxCBlkWidth = cblks.a();
/*  938 */     int maxCBlkHeight = cblks.b();
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/*  943 */         nt = Integer.parseInt(System.getProperty("jj2000.j2k.entropy.encoder.StdEntropyCoder.nthreads", "0"));
/*      */       }
/*  945 */       catch (SecurityException se) {
/*      */         
/*  947 */         nt = Integer.parseInt("0");
/*      */       } 
/*  949 */       if (nt < 0) throw new NumberFormatException(); 
/*  950 */     } catch (NumberFormatException e) {
/*  951 */       throw new IllegalArgumentException("Invalid number of threads for entropy coding in property jj2000.j2k.entropy.encoder.StdEntropyCoder.nthreads");
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
/*  965 */     if (nt > 0) {
/*  966 */       c.b()
/*  967 */         .printmsg(1, "Using multithreaded entropy coder with " + nt + " compressor threads.");
/*      */ 
/*      */       
/*  970 */       tsl = nt;
/*  971 */       this.C = new l(nt, Thread.currentThread().getPriority() + 0, "StdEntropyCoder");
/*      */       
/*  973 */       this.D = new Stack();
/*  974 */       this.E = new Stack[src.getNumComps()];
/*  975 */       this.F = new int[src.getNumComps()];
/*  976 */       this.G = new boolean[src.getNumComps()]; int n;
/*  977 */       for (n = src.getNumComps() - 1; n >= 0; n--) {
/*  978 */         this.E[n] = new Stack();
/*      */       }
/*  980 */       for (n = 0; n < nt; n++) {
/*  981 */         this.D.push(new a(this, n));
/*      */       }
/*      */     } else {
/*      */       
/*  985 */       tsl = 1;
/*  986 */       this.C = null;
/*  987 */       this.D = null;
/*  988 */       this.E = null;
/*  989 */       this.F = null;
/*  990 */       this.G = null;
/*      */     } 
/*      */ 
/*      */     
/*  994 */     this.J = new b[tsl];
/*  995 */     this.H = new i[tsl];
/*  996 */     this.I = new a[tsl];
/*  997 */     this.ag = new int[tsl][(maxCBlkWidth + 2) * ((maxCBlkHeight + 1) / 2 + 2)];
/*  998 */     this.bg = new int[tsl][maxCBlkWidth * 10];
/*  999 */     this.bh = new int[tsl][maxCBlkWidth * 10];
/* 1000 */     this.bc = new double[tsl][96];
/* 1001 */     this.bd = new int[tsl][96];
/* 1002 */     this.be = new boolean[tsl][96];
/* 1003 */     this.bf = new g[tsl];
/* 1004 */     for (int j = 0; j < tsl; j++) {
/* 1005 */       this.J[j] = new b();
/* 1006 */       this.H[j] = new i(this.J[j], 19, ad);
/*      */     } 
/* 1008 */     this.bi = new boolean[src.getNumComps()][src.getNumTiles()];
/*      */ 
/*      */     
/* 1011 */     Point numTiles = src.getNumTiles(null);
/*      */     
/* 1013 */     int nc = getNumComps();
/* 1014 */     f(getNumTiles(), nc);
/*      */     
/* 1016 */     for (int m = 0; m < nc; m++) {
/* 1017 */       for (int tY = 0; tY < numTiles.y; tY++) {
/* 1018 */         for (int tX = 0; tX < numTiles.x; tX++) {
/* 1019 */           this.bi[m][this.k] = false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1079 */     super.finalize();
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
/*      */   public int b(int t, int j) {
/* 1092 */     return this.K.a((byte)3, t, j);
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
/*      */   public int c(int t, int j) {
/* 1105 */     return this.K.b((byte)3, t, j);
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
/*      */   public c a(int j, c ccb) {
/* 1139 */     long stime = 0L;
/* 1140 */     if (this.C == null) {
/*      */       
/* 1142 */       this.bf[0] = this.b.b(j, this.bf[0]);
/*      */ 
/*      */       
/* 1145 */       if (this.bf[0] == null) {
/* 1146 */         return null;
/*      */       }
/*      */       
/* 1149 */       if ((this.M[this.k][j] & 0x1) != 0 && this.I[0] == null) {
/* 1150 */         this.I[0] = new a(this.J[0]);
/*      */       }
/*      */       
/* 1153 */       if (ccb == null) {
/* 1154 */         ccb = new c();
/*      */       }
/*      */       
/* 1157 */       b(j, ccb, this.bf[0], this.H[0], this.I[0], this.J[0], this.ag[0], this.bc[0], this.bd[0], this.be[0], this.bg[0], this.bh[0], this.M[this.k][j], 
/*      */ 
/*      */           
/* 1160 */           d(this.k, j), this.N[this.k][j], this.O[this.k][j]);
/*      */ 
/*      */ 
/*      */       
/* 1164 */       return ccb;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1172 */     while (!this.G[j] && !this.D.empty()) {
/*      */       
/* 1174 */       a compr = this.D.pop();
/* 1175 */       int cIdx = compr.a();
/*      */ 
/*      */       
/* 1178 */       this.bf[cIdx] = this.b.b(j, this.bf[cIdx]);
/*      */       
/* 1180 */       if (this.bf[cIdx] != null) {
/*      */         
/* 1182 */         if ((this.M[this.k][j] & 0x1) != 0 && this.I[cIdx] == null) {
/* 1183 */           this.I[cIdx] = new a(this.J[cIdx]);
/*      */         }
/*      */         
/* 1186 */         if (ccb == null) ccb = new c(); 
/* 1187 */         compr.a = ccb;
/* 1188 */         compr.b = j;
/* 1189 */         compr.c = this.M[this.k][j];
/* 1190 */         compr.d = d(this.k, j);
/* 1191 */         compr.e = this.N[this.k][j];
/* 1192 */         compr.f = this.O[this.k][j];
/* 1193 */         this.F[j] = this.F[j] + 1;
/* 1194 */         ccb = null;
/*      */         
/* 1196 */         this.C.a(compr, this.E[j]);
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 1201 */       this.D.push(compr);
/* 1202 */       this.G[j] = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1207 */     if (this.F[j] > 0) {
/* 1208 */       synchronized (this.E[j]) {
/*      */         
/* 1210 */         if (this.E[j].empty()) {
/*      */           
/*      */           try {
/*      */ 
/*      */             
/* 1215 */             this.E[j].wait();
/*      */ 
/*      */           
/*      */           }
/* 1219 */           catch (InterruptedException interruptedException) {}
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1224 */         a compr = this.E[j].pop();
/* 1225 */         int cIdx = compr.a();
/* 1226 */         this.F[j] = this.F[j] - 1;
/* 1227 */         this.D.push(compr);
/*      */         
/* 1229 */         this.C.b();
/*      */ 
/*      */         
/* 1232 */         return compr.a;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1237 */     this.C.b();
/*      */ 
/*      */ 
/*      */     
/* 1241 */     return null;
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
/*      */   public void setTile(int x, int y) {
/* 1259 */     super.setTile(x, y);
/*      */     
/* 1261 */     if (this.G != null) {
/* 1262 */       for (int j = this.b.getNumComps() - 1; j >= 0; j--) {
/* 1263 */         this.G[j] = false;
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
/*      */   public void nextTile() {
/* 1278 */     if (this.G != null) {
/* 1279 */       for (int j = this.b.getNumComps() - 1; j >= 0; j--) {
/* 1280 */         this.G[j] = false;
/*      */       }
/*      */     }
/* 1283 */     super.nextTile();
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
/*      */   private static void b(int j, c ccb, g srcblk, i mq, a bout, b out, int[] state, double[] distbuf, int[] ratebuf, boolean[] istermbuf, int[] symbuf, int[] ctxtbuf, int options, boolean rev, int lcType, int tType) {
/*      */     int[] zc_lut;
/* 1360 */     if ((options & 0x10) != 0 && tType != 3) {
/* 1361 */       throw new IllegalArgumentException("Embedded error-resilient info in MQ termination option specified but incorrect MQ termination policy specified");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1369 */     mq.a(lcType);
/* 1370 */     mq.b(tType);
/*      */     
/* 1372 */     int lmb = 30 - srcblk.j + 1;
/*      */ 
/*      */     
/* 1375 */     lmb = (lmb < 0) ? 0 : lmb;
/*      */ 
/*      */     
/* 1378 */     c.a.i.a.a(state, 0);
/*      */ 
/*      */     
/* 1381 */     int skipbp = a(srcblk, lmb);
/*      */ 
/*      */     
/* 1384 */     ccb.b = srcblk.d;
/* 1385 */     ccb.a = srcblk.c;
/* 1386 */     ccb.e = srcblk.e;
/* 1387 */     ccb.m = srcblk.n;
/* 1388 */     ccb.c = skipbp;
/* 1389 */     if (ccb.m != 0) {
/* 1390 */       ccb.n = 3 * (srcblk.o - skipbp - 1) + 1;
/*      */     } else {
/* 1392 */       ccb.n = 0;
/*      */     } 
/*      */ 
/*      */     
/* 1396 */     switch (srcblk.e.f) {
/*      */       case 1:
/* 1398 */         zc_lut = R;
/*      */         break;
/*      */       case 0:
/*      */       case 2:
/* 1402 */         zc_lut = Q;
/*      */         break;
/*      */       case 3:
/* 1405 */         zc_lut = S;
/*      */         break;
/*      */       default:
/* 1408 */         throw new Error("JJ2000 internal error");
/*      */     } 
/*      */ 
/*      */     
/* 1412 */     int curbp = 30 - skipbp;
/* 1413 */     int[] fs = aY;
/* 1414 */     int[] fm = aZ;
/* 1415 */     double msew = Math.pow(2.0D, ((curbp - lmb << 1) - 13)) * srcblk.e.B * srcblk.k;
/*      */     
/* 1417 */     double totdist = 0.0D;
/* 1418 */     int npass = 0;
/* 1419 */     int ltpidx = -1;
/*      */     
/* 1421 */     if (curbp >= lmb) {
/*      */       
/* 1423 */       if (rev && curbp == lmb) {
/* 1424 */         fs = bb;
/*      */       }
/*      */ 
/*      */       
/* 1428 */       istermbuf[npass] = ((options & 0x4) != 0 || curbp == lmb || ((options & 0x1) != 0 && 27 - skipbp >= curbp));
/*      */ 
/*      */       
/* 1431 */       totdist += b(srcblk, mq, istermbuf[npass], curbp, state, fs, zc_lut, symbuf, ctxtbuf, ratebuf, npass, ltpidx, options) * msew;
/*      */ 
/*      */       
/* 1434 */       distbuf[npass] = totdist;
/* 1435 */       if (istermbuf[npass]) ltpidx = npass; 
/* 1436 */       npass++;
/* 1437 */       msew *= 0.25D;
/* 1438 */       curbp--;
/*      */     } 
/*      */     
/* 1441 */     while (curbp >= lmb) {
/*      */       
/* 1443 */       if (rev && curbp == lmb) {
/* 1444 */         fs = ba;
/* 1445 */         fm = bb;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1450 */       istermbuf[npass] = ((options & 0x4) != 0);
/* 1451 */       if ((options & 0x1) == 0 || 27 - skipbp <= curbp) {
/*      */         
/* 1453 */         totdist += a(srcblk, mq, istermbuf[npass], curbp, state, fs, zc_lut, symbuf, ctxtbuf, ratebuf, npass, ltpidx, options) * msew;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1459 */         bout.a(((options & 0x10) != 0));
/* 1460 */         totdist += a(srcblk, bout, istermbuf[npass], curbp, state, fs, ratebuf, npass, ltpidx, options) * msew;
/*      */       } 
/*      */ 
/*      */       
/* 1464 */       distbuf[npass] = totdist;
/* 1465 */       if (istermbuf[npass]) ltpidx = npass; 
/* 1466 */       npass++;
/*      */ 
/*      */ 
/*      */       
/* 1470 */       istermbuf[npass] = ((options & 0x4) != 0 || ((options & 0x1) != 0 && 27 - skipbp > curbp));
/*      */ 
/*      */       
/* 1473 */       if ((options & 0x1) == 0 || 27 - skipbp <= curbp) {
/*      */         
/* 1475 */         totdist += a(srcblk, mq, istermbuf[npass], curbp, state, fm, symbuf, ctxtbuf, ratebuf, npass, ltpidx, options) * msew;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1480 */         bout.a(((options & 0x10) != 0));
/* 1481 */         totdist += b(srcblk, bout, istermbuf[npass], curbp, state, fm, ratebuf, npass, ltpidx, options) * msew;
/*      */       } 
/*      */ 
/*      */       
/* 1485 */       distbuf[npass] = totdist;
/* 1486 */       if (istermbuf[npass]) ltpidx = npass; 
/* 1487 */       npass++;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1492 */       istermbuf[npass] = ((options & 0x4) != 0 || curbp == lmb || ((options & 0x1) != 0 && 27 - skipbp >= curbp));
/*      */ 
/*      */       
/* 1495 */       totdist += b(srcblk, mq, istermbuf[npass], curbp, state, fs, zc_lut, symbuf, ctxtbuf, ratebuf, npass, ltpidx, options) * msew;
/*      */ 
/*      */       
/* 1498 */       distbuf[npass] = totdist;
/* 1499 */       if (istermbuf[npass]) ltpidx = npass; 
/* 1500 */       npass++;
/*      */ 
/*      */       
/* 1503 */       msew *= 0.25D;
/* 1504 */       curbp--;
/*      */     } 
/*      */ 
/*      */     
/* 1508 */     ccb.d = new byte[out.a()];
/* 1509 */     out.a(0, out.a(), ccb.d, 0);
/* 1510 */     a(ccb.d, ratebuf, istermbuf, npass);
/* 1511 */     ccb.a(ratebuf, distbuf, ((options & 0x5) != 0) ? istermbuf : null, npass, rev);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1516 */     mq.e();
/* 1517 */     if (bout != null) bout.c();
/*      */   
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
/*      */   private static int a(g cblk, int lmb) {
/* 1543 */     int[] data = (int[])cblk.b();
/* 1544 */     int w = cblk.f;
/* 1545 */     int h = cblk.g;
/*      */ 
/*      */     
/* 1548 */     int maxmag = 0;
/*      */     
/* 1550 */     int mask = Integer.MAX_VALUE & ((1 << lmb) - 1 ^ 0xFFFFFFFF);
/* 1551 */     for (int m = h - 1, j = cblk.h; m >= 0; m--) {
/* 1552 */       for (int kmax = j + w; j < kmax; j++) {
/* 1553 */         int mag = data[j] & mask;
/* 1554 */         if (mag > maxmag) maxmag = mag; 
/*      */       } 
/* 1556 */       j += cblk.i - w;
/*      */     } 
/*      */ 
/*      */     
/* 1560 */     int msbp = 30;
/*      */     
/*      */     do {
/* 1563 */       msbp--;
/* 1564 */     } while ((1 << msbp & maxmag) == 0 && msbp >= lmb);
/*      */ 
/*      */     
/* 1567 */     return 30 - msbp;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int a(g srcblk, i mq, boolean doterm, int bp, int[] state, int[] fs, int[] zc_lut, int[] symbuf, int[] ctxtbuf, int[] ratebuf, int pidx, int ltpidx, int options) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield i : I
/*      */     //   4: istore #18
/*      */     //   6: aload_0
/*      */     //   7: getfield f : I
/*      */     //   10: iconst_2
/*      */     //   11: iadd
/*      */     //   12: istore #19
/*      */     //   14: iload #19
/*      */     //   16: iconst_4
/*      */     //   17: imul
/*      */     //   18: iconst_2
/*      */     //   19: idiv
/*      */     //   20: aload_0
/*      */     //   21: getfield f : I
/*      */     //   24: isub
/*      */     //   25: istore #20
/*      */     //   27: iload #18
/*      */     //   29: iconst_4
/*      */     //   30: imul
/*      */     //   31: aload_0
/*      */     //   32: getfield f : I
/*      */     //   35: isub
/*      */     //   36: istore #21
/*      */     //   38: iconst_1
/*      */     //   39: iload_3
/*      */     //   40: ishl
/*      */     //   41: istore #24
/*      */     //   43: aload_0
/*      */     //   44: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   47: checkcast [I
/*      */     //   50: checkcast [I
/*      */     //   53: astore #27
/*      */     //   55: aload_0
/*      */     //   56: getfield g : I
/*      */     //   59: iconst_4
/*      */     //   60: iadd
/*      */     //   61: iconst_1
/*      */     //   62: isub
/*      */     //   63: iconst_4
/*      */     //   64: idiv
/*      */     //   65: istore #35
/*      */     //   67: iconst_0
/*      */     //   68: istore #28
/*      */     //   70: iload_3
/*      */     //   71: bipush #6
/*      */     //   73: isub
/*      */     //   74: istore #29
/*      */     //   76: iload #29
/*      */     //   78: iflt -> 85
/*      */     //   81: iconst_0
/*      */     //   82: goto -> 88
/*      */     //   85: iload #29
/*      */     //   87: ineg
/*      */     //   88: istore #30
/*      */     //   90: iload #29
/*      */     //   92: ifgt -> 99
/*      */     //   95: iconst_0
/*      */     //   96: goto -> 101
/*      */     //   99: iload #29
/*      */     //   101: istore #31
/*      */     //   103: iload #12
/*      */     //   105: bipush #8
/*      */     //   107: iand
/*      */     //   108: ifeq -> 115
/*      */     //   111: iconst_1
/*      */     //   112: goto -> 116
/*      */     //   115: iconst_0
/*      */     //   116: istore #34
/*      */     //   118: iload #19
/*      */     //   120: ineg
/*      */     //   121: iconst_1
/*      */     //   122: isub
/*      */     //   123: istore #37
/*      */     //   125: iload #19
/*      */     //   127: ineg
/*      */     //   128: iconst_1
/*      */     //   129: iadd
/*      */     //   130: istore #38
/*      */     //   132: iload #19
/*      */     //   134: iconst_1
/*      */     //   135: iadd
/*      */     //   136: istore #39
/*      */     //   138: iload #19
/*      */     //   140: iconst_1
/*      */     //   141: isub
/*      */     //   142: istore #40
/*      */     //   144: aload_0
/*      */     //   145: getfield h : I
/*      */     //   148: istore #16
/*      */     //   150: iload #19
/*      */     //   152: iconst_1
/*      */     //   153: iadd
/*      */     //   154: istore #14
/*      */     //   156: iload #35
/*      */     //   158: iconst_1
/*      */     //   159: isub
/*      */     //   160: istore #33
/*      */     //   162: iload #33
/*      */     //   164: iflt -> 1435
/*      */     //   167: iload #33
/*      */     //   169: ifeq -> 176
/*      */     //   172: iconst_4
/*      */     //   173: goto -> 187
/*      */     //   176: aload_0
/*      */     //   177: getfield g : I
/*      */     //   180: iload #35
/*      */     //   182: iconst_1
/*      */     //   183: isub
/*      */     //   184: iconst_4
/*      */     //   185: imul
/*      */     //   186: isub
/*      */     //   187: istore #36
/*      */     //   189: iload #16
/*      */     //   191: aload_0
/*      */     //   192: getfield f : I
/*      */     //   195: iadd
/*      */     //   196: istore #22
/*      */     //   198: iconst_0
/*      */     //   199: istore #17
/*      */     //   201: iload #16
/*      */     //   203: iload #22
/*      */     //   205: if_icmpge -> 1405
/*      */     //   208: iload #14
/*      */     //   210: istore #13
/*      */     //   212: aload #4
/*      */     //   214: iload #13
/*      */     //   216: iaload
/*      */     //   217: istore #23
/*      */     //   219: iload #23
/*      */     //   221: iconst_m1
/*      */     //   222: ixor
/*      */     //   223: iload #23
/*      */     //   225: iconst_2
/*      */     //   226: ishl
/*      */     //   227: iand
/*      */     //   228: ldc -2147450880
/*      */     //   230: iand
/*      */     //   231: ifeq -> 801
/*      */     //   234: iload #16
/*      */     //   236: istore #15
/*      */     //   238: iload #23
/*      */     //   240: ldc 40960
/*      */     //   242: iand
/*      */     //   243: sipush #8192
/*      */     //   246: if_icmpne -> 509
/*      */     //   249: aload #8
/*      */     //   251: iload #17
/*      */     //   253: aload #6
/*      */     //   255: iload #23
/*      */     //   257: sipush #255
/*      */     //   260: iand
/*      */     //   261: iaload
/*      */     //   262: iastore
/*      */     //   263: aload #7
/*      */     //   265: iload #17
/*      */     //   267: iinc #17, 1
/*      */     //   270: aload #27
/*      */     //   272: iload #15
/*      */     //   274: iaload
/*      */     //   275: iload #24
/*      */     //   277: iand
/*      */     //   278: iload_3
/*      */     //   279: iushr
/*      */     //   280: dup_x2
/*      */     //   281: iastore
/*      */     //   282: ifeq -> 501
/*      */     //   285: aload #27
/*      */     //   287: iload #15
/*      */     //   289: iaload
/*      */     //   290: bipush #31
/*      */     //   292: iushr
/*      */     //   293: istore #25
/*      */     //   295: getstatic c/a/c/b/k.U : [I
/*      */     //   298: iload #23
/*      */     //   300: iconst_4
/*      */     //   301: iushr
/*      */     //   302: sipush #511
/*      */     //   305: iand
/*      */     //   306: iaload
/*      */     //   307: istore #26
/*      */     //   309: aload #7
/*      */     //   311: iload #17
/*      */     //   313: iload #25
/*      */     //   315: iload #26
/*      */     //   317: bipush #31
/*      */     //   319: iushr
/*      */     //   320: ixor
/*      */     //   321: iastore
/*      */     //   322: aload #8
/*      */     //   324: iload #17
/*      */     //   326: iinc #17, 1
/*      */     //   329: iload #26
/*      */     //   331: bipush #15
/*      */     //   333: iand
/*      */     //   334: iastore
/*      */     //   335: iload #34
/*      */     //   337: ifne -> 366
/*      */     //   340: aload #4
/*      */     //   342: iload #13
/*      */     //   344: iload #37
/*      */     //   346: iadd
/*      */     //   347: dup2
/*      */     //   348: iaload
/*      */     //   349: ldc 536936448
/*      */     //   351: ior
/*      */     //   352: iastore
/*      */     //   353: aload #4
/*      */     //   355: iload #13
/*      */     //   357: iload #38
/*      */     //   359: iadd
/*      */     //   360: dup2
/*      */     //   361: iaload
/*      */     //   362: ldc 537001984
/*      */     //   364: ior
/*      */     //   365: iastore
/*      */     //   366: iload #25
/*      */     //   368: ifeq -> 423
/*      */     //   371: iload #23
/*      */     //   373: ldc 606126080
/*      */     //   375: ior
/*      */     //   376: istore #23
/*      */     //   378: iload #34
/*      */     //   380: ifne -> 396
/*      */     //   383: aload #4
/*      */     //   385: iload #13
/*      */     //   387: iload #19
/*      */     //   389: isub
/*      */     //   390: dup2
/*      */     //   391: iaload
/*      */     //   392: ldc 571473920
/*      */     //   394: ior
/*      */     //   395: iastore
/*      */     //   396: aload #4
/*      */     //   398: iload #13
/*      */     //   400: iconst_1
/*      */     //   401: iadd
/*      */     //   402: dup2
/*      */     //   403: iaload
/*      */     //   404: ldc 537407616
/*      */     //   406: ior
/*      */     //   407: iastore
/*      */     //   408: aload #4
/*      */     //   410: iload #13
/*      */     //   412: iconst_1
/*      */     //   413: isub
/*      */     //   414: dup2
/*      */     //   415: iaload
/*      */     //   416: ldc 537143360
/*      */     //   418: ior
/*      */     //   419: iastore
/*      */     //   420: goto -> 472
/*      */     //   423: iload #23
/*      */     //   425: ldc 539017216
/*      */     //   427: ior
/*      */     //   428: istore #23
/*      */     //   430: iload #34
/*      */     //   432: ifne -> 448
/*      */     //   435: aload #4
/*      */     //   437: iload #13
/*      */     //   439: iload #19
/*      */     //   441: isub
/*      */     //   442: dup2
/*      */     //   443: iaload
/*      */     //   444: ldc 537919488
/*      */     //   446: ior
/*      */     //   447: iastore
/*      */     //   448: aload #4
/*      */     //   450: iload #13
/*      */     //   452: iconst_1
/*      */     //   453: iadd
/*      */     //   454: dup2
/*      */     //   455: iaload
/*      */     //   456: ldc 537403520
/*      */     //   458: ior
/*      */     //   459: iastore
/*      */     //   460: aload #4
/*      */     //   462: iload #13
/*      */     //   464: iconst_1
/*      */     //   465: isub
/*      */     //   466: dup2
/*      */     //   467: iaload
/*      */     //   468: ldc 537141312
/*      */     //   470: ior
/*      */     //   471: iastore
/*      */     //   472: aload #27
/*      */     //   474: iload #15
/*      */     //   476: iaload
/*      */     //   477: iload #31
/*      */     //   479: ishr
/*      */     //   480: iload #30
/*      */     //   482: ishl
/*      */     //   483: istore #32
/*      */     //   485: iload #28
/*      */     //   487: aload #5
/*      */     //   489: iload #32
/*      */     //   491: bipush #63
/*      */     //   493: iand
/*      */     //   494: iaload
/*      */     //   495: iadd
/*      */     //   496: istore #28
/*      */     //   498: goto -> 509
/*      */     //   501: iload #23
/*      */     //   503: sipush #16384
/*      */     //   506: ior
/*      */     //   507: istore #23
/*      */     //   509: iload #36
/*      */     //   511: iconst_2
/*      */     //   512: if_icmpge -> 525
/*      */     //   515: aload #4
/*      */     //   517: iload #13
/*      */     //   519: iload #23
/*      */     //   521: iastore
/*      */     //   522: goto -> 1396
/*      */     //   525: iload #23
/*      */     //   527: ldc -1610612736
/*      */     //   529: iand
/*      */     //   530: ldc 536870912
/*      */     //   532: if_icmpne -> 794
/*      */     //   535: iload #15
/*      */     //   537: iload #18
/*      */     //   539: iadd
/*      */     //   540: istore #15
/*      */     //   542: aload #8
/*      */     //   544: iload #17
/*      */     //   546: aload #6
/*      */     //   548: iload #23
/*      */     //   550: bipush #16
/*      */     //   552: iushr
/*      */     //   553: sipush #255
/*      */     //   556: iand
/*      */     //   557: iaload
/*      */     //   558: iastore
/*      */     //   559: aload #7
/*      */     //   561: iload #17
/*      */     //   563: iinc #17, 1
/*      */     //   566: aload #27
/*      */     //   568: iload #15
/*      */     //   570: iaload
/*      */     //   571: iload #24
/*      */     //   573: iand
/*      */     //   574: iload_3
/*      */     //   575: iushr
/*      */     //   576: dup_x2
/*      */     //   577: iastore
/*      */     //   578: ifeq -> 787
/*      */     //   581: aload #27
/*      */     //   583: iload #15
/*      */     //   585: iaload
/*      */     //   586: bipush #31
/*      */     //   588: iushr
/*      */     //   589: istore #25
/*      */     //   591: getstatic c/a/c/b/k.U : [I
/*      */     //   594: iload #23
/*      */     //   596: bipush #20
/*      */     //   598: iushr
/*      */     //   599: sipush #511
/*      */     //   602: iand
/*      */     //   603: iaload
/*      */     //   604: istore #26
/*      */     //   606: aload #7
/*      */     //   608: iload #17
/*      */     //   610: iload #25
/*      */     //   612: iload #26
/*      */     //   614: bipush #31
/*      */     //   616: iushr
/*      */     //   617: ixor
/*      */     //   618: iastore
/*      */     //   619: aload #8
/*      */     //   621: iload #17
/*      */     //   623: iinc #17, 1
/*      */     //   626: iload #26
/*      */     //   628: bipush #15
/*      */     //   630: iand
/*      */     //   631: iastore
/*      */     //   632: aload #4
/*      */     //   634: iload #13
/*      */     //   636: iload #40
/*      */     //   638: iadd
/*      */     //   639: dup2
/*      */     //   640: iaload
/*      */     //   641: sipush #8196
/*      */     //   644: ior
/*      */     //   645: iastore
/*      */     //   646: aload #4
/*      */     //   648: iload #13
/*      */     //   650: iload #39
/*      */     //   652: iadd
/*      */     //   653: dup2
/*      */     //   654: iaload
/*      */     //   655: sipush #8200
/*      */     //   658: ior
/*      */     //   659: iastore
/*      */     //   660: iload #25
/*      */     //   662: ifeq -> 713
/*      */     //   665: iload #23
/*      */     //   667: ldc -1073733104
/*      */     //   669: ior
/*      */     //   670: istore #23
/*      */     //   672: aload #4
/*      */     //   674: iload #13
/*      */     //   676: iload #19
/*      */     //   678: iadd
/*      */     //   679: dup2
/*      */     //   680: iaload
/*      */     //   681: sipush #9248
/*      */     //   684: ior
/*      */     //   685: iastore
/*      */     //   686: aload #4
/*      */     //   688: iload #13
/*      */     //   690: iconst_1
/*      */     //   691: iadd
/*      */     //   692: dup2
/*      */     //   693: iaload
/*      */     //   694: ldc 813703170
/*      */     //   696: ior
/*      */     //   697: iastore
/*      */     //   698: aload #4
/*      */     //   700: iload #13
/*      */     //   702: iconst_1
/*      */     //   703: isub
/*      */     //   704: dup2
/*      */     //   705: iaload
/*      */     //   706: ldc 675291137
/*      */     //   708: ior
/*      */     //   709: iastore
/*      */     //   710: goto -> 758
/*      */     //   713: iload #23
/*      */     //   715: ldc -1073733616
/*      */     //   717: ior
/*      */     //   718: istore #23
/*      */     //   720: aload #4
/*      */     //   722: iload #13
/*      */     //   724: iload #19
/*      */     //   726: iadd
/*      */     //   727: dup2
/*      */     //   728: iaload
/*      */     //   729: sipush #8224
/*      */     //   732: ior
/*      */     //   733: iastore
/*      */     //   734: aload #4
/*      */     //   736: iload #13
/*      */     //   738: iconst_1
/*      */     //   739: iadd
/*      */     //   740: dup2
/*      */     //   741: iaload
/*      */     //   742: ldc 545267714
/*      */     //   744: ior
/*      */     //   745: iastore
/*      */     //   746: aload #4
/*      */     //   748: iload #13
/*      */     //   750: iconst_1
/*      */     //   751: isub
/*      */     //   752: dup2
/*      */     //   753: iaload
/*      */     //   754: ldc 541073409
/*      */     //   756: ior
/*      */     //   757: iastore
/*      */     //   758: aload #27
/*      */     //   760: iload #15
/*      */     //   762: iaload
/*      */     //   763: iload #31
/*      */     //   765: ishr
/*      */     //   766: iload #30
/*      */     //   768: ishl
/*      */     //   769: istore #32
/*      */     //   771: iload #28
/*      */     //   773: aload #5
/*      */     //   775: iload #32
/*      */     //   777: bipush #63
/*      */     //   779: iand
/*      */     //   780: iaload
/*      */     //   781: iadd
/*      */     //   782: istore #28
/*      */     //   784: goto -> 794
/*      */     //   787: iload #23
/*      */     //   789: ldc 1073741824
/*      */     //   791: ior
/*      */     //   792: istore #23
/*      */     //   794: aload #4
/*      */     //   796: iload #13
/*      */     //   798: iload #23
/*      */     //   800: iastore
/*      */     //   801: iload #36
/*      */     //   803: iconst_3
/*      */     //   804: if_icmpge -> 810
/*      */     //   807: goto -> 1396
/*      */     //   810: iload #13
/*      */     //   812: iload #19
/*      */     //   814: iadd
/*      */     //   815: istore #13
/*      */     //   817: aload #4
/*      */     //   819: iload #13
/*      */     //   821: iaload
/*      */     //   822: istore #23
/*      */     //   824: iload #23
/*      */     //   826: iconst_m1
/*      */     //   827: ixor
/*      */     //   828: iload #23
/*      */     //   830: iconst_2
/*      */     //   831: ishl
/*      */     //   832: iand
/*      */     //   833: ldc -2147450880
/*      */     //   835: iand
/*      */     //   836: ifeq -> 1396
/*      */     //   839: iload #16
/*      */     //   841: iload #18
/*      */     //   843: iconst_1
/*      */     //   844: ishl
/*      */     //   845: iadd
/*      */     //   846: istore #15
/*      */     //   848: iload #23
/*      */     //   850: ldc 40960
/*      */     //   852: iand
/*      */     //   853: sipush #8192
/*      */     //   856: if_icmpne -> 1104
/*      */     //   859: aload #8
/*      */     //   861: iload #17
/*      */     //   863: aload #6
/*      */     //   865: iload #23
/*      */     //   867: sipush #255
/*      */     //   870: iand
/*      */     //   871: iaload
/*      */     //   872: iastore
/*      */     //   873: aload #7
/*      */     //   875: iload #17
/*      */     //   877: iinc #17, 1
/*      */     //   880: aload #27
/*      */     //   882: iload #15
/*      */     //   884: iaload
/*      */     //   885: iload #24
/*      */     //   887: iand
/*      */     //   888: iload_3
/*      */     //   889: iushr
/*      */     //   890: dup_x2
/*      */     //   891: iastore
/*      */     //   892: ifeq -> 1096
/*      */     //   895: aload #27
/*      */     //   897: iload #15
/*      */     //   899: iaload
/*      */     //   900: bipush #31
/*      */     //   902: iushr
/*      */     //   903: istore #25
/*      */     //   905: getstatic c/a/c/b/k.U : [I
/*      */     //   908: iload #23
/*      */     //   910: iconst_4
/*      */     //   911: iushr
/*      */     //   912: sipush #511
/*      */     //   915: iand
/*      */     //   916: iaload
/*      */     //   917: istore #26
/*      */     //   919: aload #7
/*      */     //   921: iload #17
/*      */     //   923: iload #25
/*      */     //   925: iload #26
/*      */     //   927: bipush #31
/*      */     //   929: iushr
/*      */     //   930: ixor
/*      */     //   931: iastore
/*      */     //   932: aload #8
/*      */     //   934: iload #17
/*      */     //   936: iinc #17, 1
/*      */     //   939: iload #26
/*      */     //   941: bipush #15
/*      */     //   943: iand
/*      */     //   944: iastore
/*      */     //   945: aload #4
/*      */     //   947: iload #13
/*      */     //   949: iload #37
/*      */     //   951: iadd
/*      */     //   952: dup2
/*      */     //   953: iaload
/*      */     //   954: ldc 536936448
/*      */     //   956: ior
/*      */     //   957: iastore
/*      */     //   958: aload #4
/*      */     //   960: iload #13
/*      */     //   962: iload #38
/*      */     //   964: iadd
/*      */     //   965: dup2
/*      */     //   966: iaload
/*      */     //   967: ldc 537001984
/*      */     //   969: ior
/*      */     //   970: iastore
/*      */     //   971: iload #25
/*      */     //   973: ifeq -> 1023
/*      */     //   976: iload #23
/*      */     //   978: ldc 606126080
/*      */     //   980: ior
/*      */     //   981: istore #23
/*      */     //   983: aload #4
/*      */     //   985: iload #13
/*      */     //   987: iload #19
/*      */     //   989: isub
/*      */     //   990: dup2
/*      */     //   991: iaload
/*      */     //   992: ldc 571473920
/*      */     //   994: ior
/*      */     //   995: iastore
/*      */     //   996: aload #4
/*      */     //   998: iload #13
/*      */     //   1000: iconst_1
/*      */     //   1001: iadd
/*      */     //   1002: dup2
/*      */     //   1003: iaload
/*      */     //   1004: ldc 537407616
/*      */     //   1006: ior
/*      */     //   1007: iastore
/*      */     //   1008: aload #4
/*      */     //   1010: iload #13
/*      */     //   1012: iconst_1
/*      */     //   1013: isub
/*      */     //   1014: dup2
/*      */     //   1015: iaload
/*      */     //   1016: ldc 537143360
/*      */     //   1018: ior
/*      */     //   1019: iastore
/*      */     //   1020: goto -> 1067
/*      */     //   1023: iload #23
/*      */     //   1025: ldc 539017216
/*      */     //   1027: ior
/*      */     //   1028: istore #23
/*      */     //   1030: aload #4
/*      */     //   1032: iload #13
/*      */     //   1034: iload #19
/*      */     //   1036: isub
/*      */     //   1037: dup2
/*      */     //   1038: iaload
/*      */     //   1039: ldc 537919488
/*      */     //   1041: ior
/*      */     //   1042: iastore
/*      */     //   1043: aload #4
/*      */     //   1045: iload #13
/*      */     //   1047: iconst_1
/*      */     //   1048: iadd
/*      */     //   1049: dup2
/*      */     //   1050: iaload
/*      */     //   1051: ldc 537403520
/*      */     //   1053: ior
/*      */     //   1054: iastore
/*      */     //   1055: aload #4
/*      */     //   1057: iload #13
/*      */     //   1059: iconst_1
/*      */     //   1060: isub
/*      */     //   1061: dup2
/*      */     //   1062: iaload
/*      */     //   1063: ldc 537141312
/*      */     //   1065: ior
/*      */     //   1066: iastore
/*      */     //   1067: aload #27
/*      */     //   1069: iload #15
/*      */     //   1071: iaload
/*      */     //   1072: iload #31
/*      */     //   1074: ishr
/*      */     //   1075: iload #30
/*      */     //   1077: ishl
/*      */     //   1078: istore #32
/*      */     //   1080: iload #28
/*      */     //   1082: aload #5
/*      */     //   1084: iload #32
/*      */     //   1086: bipush #63
/*      */     //   1088: iand
/*      */     //   1089: iaload
/*      */     //   1090: iadd
/*      */     //   1091: istore #28
/*      */     //   1093: goto -> 1104
/*      */     //   1096: iload #23
/*      */     //   1098: sipush #16384
/*      */     //   1101: ior
/*      */     //   1102: istore #23
/*      */     //   1104: iload #36
/*      */     //   1106: iconst_4
/*      */     //   1107: if_icmpge -> 1120
/*      */     //   1110: aload #4
/*      */     //   1112: iload #13
/*      */     //   1114: iload #23
/*      */     //   1116: iastore
/*      */     //   1117: goto -> 1396
/*      */     //   1120: iload #23
/*      */     //   1122: ldc -1610612736
/*      */     //   1124: iand
/*      */     //   1125: ldc 536870912
/*      */     //   1127: if_icmpne -> 1389
/*      */     //   1130: iload #15
/*      */     //   1132: iload #18
/*      */     //   1134: iadd
/*      */     //   1135: istore #15
/*      */     //   1137: aload #8
/*      */     //   1139: iload #17
/*      */     //   1141: aload #6
/*      */     //   1143: iload #23
/*      */     //   1145: bipush #16
/*      */     //   1147: iushr
/*      */     //   1148: sipush #255
/*      */     //   1151: iand
/*      */     //   1152: iaload
/*      */     //   1153: iastore
/*      */     //   1154: aload #7
/*      */     //   1156: iload #17
/*      */     //   1158: iinc #17, 1
/*      */     //   1161: aload #27
/*      */     //   1163: iload #15
/*      */     //   1165: iaload
/*      */     //   1166: iload #24
/*      */     //   1168: iand
/*      */     //   1169: iload_3
/*      */     //   1170: iushr
/*      */     //   1171: dup_x2
/*      */     //   1172: iastore
/*      */     //   1173: ifeq -> 1382
/*      */     //   1176: aload #27
/*      */     //   1178: iload #15
/*      */     //   1180: iaload
/*      */     //   1181: bipush #31
/*      */     //   1183: iushr
/*      */     //   1184: istore #25
/*      */     //   1186: getstatic c/a/c/b/k.U : [I
/*      */     //   1189: iload #23
/*      */     //   1191: bipush #20
/*      */     //   1193: iushr
/*      */     //   1194: sipush #511
/*      */     //   1197: iand
/*      */     //   1198: iaload
/*      */     //   1199: istore #26
/*      */     //   1201: aload #7
/*      */     //   1203: iload #17
/*      */     //   1205: iload #25
/*      */     //   1207: iload #26
/*      */     //   1209: bipush #31
/*      */     //   1211: iushr
/*      */     //   1212: ixor
/*      */     //   1213: iastore
/*      */     //   1214: aload #8
/*      */     //   1216: iload #17
/*      */     //   1218: iinc #17, 1
/*      */     //   1221: iload #26
/*      */     //   1223: bipush #15
/*      */     //   1225: iand
/*      */     //   1226: iastore
/*      */     //   1227: aload #4
/*      */     //   1229: iload #13
/*      */     //   1231: iload #40
/*      */     //   1233: iadd
/*      */     //   1234: dup2
/*      */     //   1235: iaload
/*      */     //   1236: sipush #8196
/*      */     //   1239: ior
/*      */     //   1240: iastore
/*      */     //   1241: aload #4
/*      */     //   1243: iload #13
/*      */     //   1245: iload #39
/*      */     //   1247: iadd
/*      */     //   1248: dup2
/*      */     //   1249: iaload
/*      */     //   1250: sipush #8200
/*      */     //   1253: ior
/*      */     //   1254: iastore
/*      */     //   1255: iload #25
/*      */     //   1257: ifeq -> 1308
/*      */     //   1260: iload #23
/*      */     //   1262: ldc -1073733104
/*      */     //   1264: ior
/*      */     //   1265: istore #23
/*      */     //   1267: aload #4
/*      */     //   1269: iload #13
/*      */     //   1271: iload #19
/*      */     //   1273: iadd
/*      */     //   1274: dup2
/*      */     //   1275: iaload
/*      */     //   1276: sipush #9248
/*      */     //   1279: ior
/*      */     //   1280: iastore
/*      */     //   1281: aload #4
/*      */     //   1283: iload #13
/*      */     //   1285: iconst_1
/*      */     //   1286: iadd
/*      */     //   1287: dup2
/*      */     //   1288: iaload
/*      */     //   1289: ldc 813703170
/*      */     //   1291: ior
/*      */     //   1292: iastore
/*      */     //   1293: aload #4
/*      */     //   1295: iload #13
/*      */     //   1297: iconst_1
/*      */     //   1298: isub
/*      */     //   1299: dup2
/*      */     //   1300: iaload
/*      */     //   1301: ldc 675291137
/*      */     //   1303: ior
/*      */     //   1304: iastore
/*      */     //   1305: goto -> 1353
/*      */     //   1308: iload #23
/*      */     //   1310: ldc -1073733616
/*      */     //   1312: ior
/*      */     //   1313: istore #23
/*      */     //   1315: aload #4
/*      */     //   1317: iload #13
/*      */     //   1319: iload #19
/*      */     //   1321: iadd
/*      */     //   1322: dup2
/*      */     //   1323: iaload
/*      */     //   1324: sipush #8224
/*      */     //   1327: ior
/*      */     //   1328: iastore
/*      */     //   1329: aload #4
/*      */     //   1331: iload #13
/*      */     //   1333: iconst_1
/*      */     //   1334: iadd
/*      */     //   1335: dup2
/*      */     //   1336: iaload
/*      */     //   1337: ldc 545267714
/*      */     //   1339: ior
/*      */     //   1340: iastore
/*      */     //   1341: aload #4
/*      */     //   1343: iload #13
/*      */     //   1345: iconst_1
/*      */     //   1346: isub
/*      */     //   1347: dup2
/*      */     //   1348: iaload
/*      */     //   1349: ldc 541073409
/*      */     //   1351: ior
/*      */     //   1352: iastore
/*      */     //   1353: aload #27
/*      */     //   1355: iload #15
/*      */     //   1357: iaload
/*      */     //   1358: iload #31
/*      */     //   1360: ishr
/*      */     //   1361: iload #30
/*      */     //   1363: ishl
/*      */     //   1364: istore #32
/*      */     //   1366: iload #28
/*      */     //   1368: aload #5
/*      */     //   1370: iload #32
/*      */     //   1372: bipush #63
/*      */     //   1374: iand
/*      */     //   1375: iaload
/*      */     //   1376: iadd
/*      */     //   1377: istore #28
/*      */     //   1379: goto -> 1389
/*      */     //   1382: iload #23
/*      */     //   1384: ldc 1073741824
/*      */     //   1386: ior
/*      */     //   1387: istore #23
/*      */     //   1389: aload #4
/*      */     //   1391: iload #13
/*      */     //   1393: iload #23
/*      */     //   1395: iastore
/*      */     //   1396: iinc #16, 1
/*      */     //   1399: iinc #14, 1
/*      */     //   1402: goto -> 201
/*      */     //   1405: aload_1
/*      */     //   1406: aload #7
/*      */     //   1408: aload #8
/*      */     //   1410: iload #17
/*      */     //   1412: invokevirtual a : ([I[II)V
/*      */     //   1415: iinc #33, -1
/*      */     //   1418: iload #16
/*      */     //   1420: iload #21
/*      */     //   1422: iadd
/*      */     //   1423: istore #16
/*      */     //   1425: iload #14
/*      */     //   1427: iload #20
/*      */     //   1429: iadd
/*      */     //   1430: istore #14
/*      */     //   1432: goto -> 162
/*      */     //   1435: iload #12
/*      */     //   1437: iconst_2
/*      */     //   1438: iand
/*      */     //   1439: ifeq -> 1446
/*      */     //   1442: aload_1
/*      */     //   1443: invokevirtual c : ()V
/*      */     //   1446: iload_2
/*      */     //   1447: ifeq -> 1462
/*      */     //   1450: aload #9
/*      */     //   1452: iload #10
/*      */     //   1454: aload_1
/*      */     //   1455: invokevirtual a : ()I
/*      */     //   1458: iastore
/*      */     //   1459: goto -> 1471
/*      */     //   1462: aload #9
/*      */     //   1464: iload #10
/*      */     //   1466: aload_1
/*      */     //   1467: invokevirtual d : ()I
/*      */     //   1470: iastore
/*      */     //   1471: iload #11
/*      */     //   1473: iflt -> 1489
/*      */     //   1476: aload #9
/*      */     //   1478: iload #10
/*      */     //   1480: dup2
/*      */     //   1481: iaload
/*      */     //   1482: aload #9
/*      */     //   1484: iload #11
/*      */     //   1486: iaload
/*      */     //   1487: iadd
/*      */     //   1488: iastore
/*      */     //   1489: iload_2
/*      */     //   1490: ifeq -> 1501
/*      */     //   1493: aload_1
/*      */     //   1494: aload #9
/*      */     //   1496: iload #10
/*      */     //   1498: invokevirtual a : ([II)V
/*      */     //   1501: iload #28
/*      */     //   1503: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1644	-> 0
/*      */     //   #1645	-> 6
/*      */     //   #1646	-> 14
/*      */     //   #1647	-> 27
/*      */     //   #1648	-> 38
/*      */     //   #1649	-> 43
/*      */     //   #1650	-> 55
/*      */     //   #1651	-> 67
/*      */     //   #1654	-> 70
/*      */     //   #1655	-> 76
/*      */     //   #1656	-> 90
/*      */     //   #1657	-> 103
/*      */     //   #1660	-> 118
/*      */     //   #1661	-> 125
/*      */     //   #1662	-> 132
/*      */     //   #1663	-> 138
/*      */     //   #1666	-> 144
/*      */     //   #1667	-> 150
/*      */     //   #1668	-> 156
/*      */     //   #1669	-> 167
/*      */     //   #1671	-> 189
/*      */     //   #1673	-> 198
/*      */     //   #1675	-> 208
/*      */     //   #1676	-> 212
/*      */     //   #1680	-> 219
/*      */     //   #1681	-> 234
/*      */     //   #1683	-> 238
/*      */     //   #1686	-> 249
/*      */     //   #1687	-> 263
/*      */     //   #1690	-> 285
/*      */     //   #1691	-> 295
/*      */     //   #1692	-> 309
/*      */     //   #1693	-> 322
/*      */     //   #1698	-> 335
/*      */     //   #1701	-> 340
/*      */     //   #1703	-> 353
/*      */     //   #1707	-> 366
/*      */     //   #1708	-> 371
/*      */     //   #1711	-> 378
/*      */     //   #1714	-> 383
/*      */     //   #1717	-> 396
/*      */     //   #1721	-> 408
/*      */     //   #1727	-> 423
/*      */     //   #1729	-> 430
/*      */     //   #1732	-> 435
/*      */     //   #1735	-> 448
/*      */     //   #1738	-> 460
/*      */     //   #1743	-> 472
/*      */     //   #1744	-> 485
/*      */     //   #1747	-> 501
/*      */     //   #1750	-> 509
/*      */     //   #1751	-> 515
/*      */     //   #1752	-> 522
/*      */     //   #1755	-> 525
/*      */     //   #1757	-> 535
/*      */     //   #1759	-> 542
/*      */     //   #1760	-> 559
/*      */     //   #1763	-> 581
/*      */     //   #1764	-> 591
/*      */     //   #1765	-> 606
/*      */     //   #1766	-> 619
/*      */     //   #1771	-> 632
/*      */     //   #1772	-> 646
/*      */     //   #1774	-> 660
/*      */     //   #1775	-> 665
/*      */     //   #1778	-> 672
/*      */     //   #1780	-> 686
/*      */     //   #1784	-> 698
/*      */     //   #1790	-> 713
/*      */     //   #1792	-> 720
/*      */     //   #1794	-> 734
/*      */     //   #1797	-> 746
/*      */     //   #1802	-> 758
/*      */     //   #1803	-> 771
/*      */     //   #1806	-> 787
/*      */     //   #1809	-> 794
/*      */     //   #1812	-> 801
/*      */     //   #1813	-> 810
/*      */     //   #1814	-> 817
/*      */     //   #1818	-> 824
/*      */     //   #1819	-> 839
/*      */     //   #1821	-> 848
/*      */     //   #1824	-> 859
/*      */     //   #1825	-> 873
/*      */     //   #1828	-> 895
/*      */     //   #1829	-> 905
/*      */     //   #1830	-> 919
/*      */     //   #1831	-> 932
/*      */     //   #1836	-> 945
/*      */     //   #1837	-> 958
/*      */     //   #1839	-> 971
/*      */     //   #1840	-> 976
/*      */     //   #1843	-> 983
/*      */     //   #1845	-> 996
/*      */     //   #1849	-> 1008
/*      */     //   #1855	-> 1023
/*      */     //   #1857	-> 1030
/*      */     //   #1859	-> 1043
/*      */     //   #1862	-> 1055
/*      */     //   #1867	-> 1067
/*      */     //   #1868	-> 1080
/*      */     //   #1871	-> 1096
/*      */     //   #1874	-> 1104
/*      */     //   #1875	-> 1110
/*      */     //   #1876	-> 1117
/*      */     //   #1879	-> 1120
/*      */     //   #1881	-> 1130
/*      */     //   #1883	-> 1137
/*      */     //   #1884	-> 1154
/*      */     //   #1887	-> 1176
/*      */     //   #1888	-> 1186
/*      */     //   #1889	-> 1201
/*      */     //   #1890	-> 1214
/*      */     //   #1895	-> 1227
/*      */     //   #1896	-> 1241
/*      */     //   #1898	-> 1255
/*      */     //   #1899	-> 1260
/*      */     //   #1902	-> 1267
/*      */     //   #1904	-> 1281
/*      */     //   #1908	-> 1293
/*      */     //   #1914	-> 1308
/*      */     //   #1916	-> 1315
/*      */     //   #1918	-> 1329
/*      */     //   #1921	-> 1341
/*      */     //   #1926	-> 1353
/*      */     //   #1927	-> 1366
/*      */     //   #1930	-> 1382
/*      */     //   #1933	-> 1389
/*      */     //   #1673	-> 1396
/*      */     //   #1937	-> 1405
/*      */     //   #1668	-> 1415
/*      */     //   #1941	-> 1435
/*      */     //   #1942	-> 1442
/*      */     //   #1946	-> 1446
/*      */     //   #1947	-> 1450
/*      */     //   #1950	-> 1462
/*      */     //   #1953	-> 1471
/*      */     //   #1954	-> 1476
/*      */     //   #1957	-> 1489
/*      */     //   #1958	-> 1493
/*      */     //   #1962	-> 1501
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	1504	0	srcblk	Lc/a/j/a/g;
/*      */     //   0	1504	1	mq	Lc/a/c/b/i;
/*      */     //   0	1504	2	doterm	Z
/*      */     //   0	1504	3	bp	I
/*      */     //   0	1504	4	state	[I
/*      */     //   0	1504	5	fs	[I
/*      */     //   0	1504	6	zc_lut	[I
/*      */     //   0	1504	7	symbuf	[I
/*      */     //   0	1504	8	ctxtbuf	[I
/*      */     //   0	1504	9	ratebuf	[I
/*      */     //   0	1504	10	pidx	I
/*      */     //   0	1504	11	ltpidx	I
/*      */     //   0	1504	12	options	I
/*      */     //   6	1498	18	dscanw	I
/*      */     //   14	1490	19	sscanw	I
/*      */     //   27	1477	20	jstep	I
/*      */     //   38	1466	21	kstep	I
/*      */     //   43	1461	24	mask	I
/*      */     //   55	1449	27	data	[I
/*      */     //   67	1437	35	nstripes	I
/*      */     //   70	1434	28	dist	I
/*      */     //   76	1428	29	shift	I
/*      */     //   90	1414	30	upshift	I
/*      */     //   103	1401	31	downshift	I
/*      */     //   118	1386	34	causal	Z
/*      */     //   125	1379	37	off_ul	I
/*      */     //   132	1372	38	off_ur	I
/*      */     //   138	1366	39	off_dr	I
/*      */     //   144	1360	40	off_dl	I
/*      */     //   150	1354	16	sk	I
/*      */     //   156	1348	14	sj	I
/*      */     //   162	1342	33	s	I
/*      */     //   189	1243	36	sheight	I
/*      */     //   198	1234	22	stopsk	I
/*      */     //   201	1231	17	nsym	I
/*      */     //   212	1190	13	j	I
/*      */     //   219	1183	23	csj	I
/*      */     //   238	563	15	k	I
/*      */     //   295	206	25	sym	I
/*      */     //   309	192	26	ctxt	I
/*      */     //   485	16	32	normval	I
/*      */     //   591	196	25	sym	I
/*      */     //   606	181	26	ctxt	I
/*      */     //   771	16	32	normval	I
/*      */     //   848	548	15	k	I
/*      */     //   905	191	25	sym	I
/*      */     //   919	177	26	ctxt	I
/*      */     //   1080	16	32	normval	I
/*      */     //   1186	196	25	sym	I
/*      */     //   1201	181	26	ctxt	I
/*      */     //   1366	16	32	normval	I
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int a(g srcblk, a bout, boolean doterm, int bp, int[] state, int[] fs, int[] ratebuf, int pidx, int ltpidx, int options) {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore #21
/*      */     //   3: aload_0
/*      */     //   4: getfield i : I
/*      */     //   7: istore #14
/*      */     //   9: aload_0
/*      */     //   10: getfield f : I
/*      */     //   13: iconst_2
/*      */     //   14: iadd
/*      */     //   15: istore #15
/*      */     //   17: iload #15
/*      */     //   19: iconst_4
/*      */     //   20: imul
/*      */     //   21: iconst_2
/*      */     //   22: idiv
/*      */     //   23: aload_0
/*      */     //   24: getfield f : I
/*      */     //   27: isub
/*      */     //   28: istore #16
/*      */     //   30: iload #14
/*      */     //   32: iconst_4
/*      */     //   33: imul
/*      */     //   34: aload_0
/*      */     //   35: getfield f : I
/*      */     //   38: isub
/*      */     //   39: istore #17
/*      */     //   41: iconst_1
/*      */     //   42: iload_3
/*      */     //   43: ishl
/*      */     //   44: istore #20
/*      */     //   46: aload_0
/*      */     //   47: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   50: checkcast [I
/*      */     //   53: checkcast [I
/*      */     //   56: astore #23
/*      */     //   58: aload_0
/*      */     //   59: getfield g : I
/*      */     //   62: iconst_4
/*      */     //   63: iadd
/*      */     //   64: iconst_1
/*      */     //   65: isub
/*      */     //   66: iconst_4
/*      */     //   67: idiv
/*      */     //   68: istore #31
/*      */     //   70: iconst_0
/*      */     //   71: istore #24
/*      */     //   73: iload_3
/*      */     //   74: bipush #6
/*      */     //   76: isub
/*      */     //   77: istore #25
/*      */     //   79: iload #25
/*      */     //   81: iflt -> 88
/*      */     //   84: iconst_0
/*      */     //   85: goto -> 91
/*      */     //   88: iload #25
/*      */     //   90: ineg
/*      */     //   91: istore #26
/*      */     //   93: iload #25
/*      */     //   95: ifgt -> 102
/*      */     //   98: iconst_0
/*      */     //   99: goto -> 104
/*      */     //   102: iload #25
/*      */     //   104: istore #27
/*      */     //   106: iload #9
/*      */     //   108: bipush #8
/*      */     //   110: iand
/*      */     //   111: ifeq -> 118
/*      */     //   114: iconst_1
/*      */     //   115: goto -> 119
/*      */     //   118: iconst_0
/*      */     //   119: istore #30
/*      */     //   121: iload #15
/*      */     //   123: ineg
/*      */     //   124: iconst_1
/*      */     //   125: isub
/*      */     //   126: istore #33
/*      */     //   128: iload #15
/*      */     //   130: ineg
/*      */     //   131: iconst_1
/*      */     //   132: iadd
/*      */     //   133: istore #34
/*      */     //   135: iload #15
/*      */     //   137: iconst_1
/*      */     //   138: iadd
/*      */     //   139: istore #35
/*      */     //   141: iload #15
/*      */     //   143: iconst_1
/*      */     //   144: isub
/*      */     //   145: istore #36
/*      */     //   147: aload_0
/*      */     //   148: getfield h : I
/*      */     //   151: istore #13
/*      */     //   153: iload #15
/*      */     //   155: iconst_1
/*      */     //   156: iadd
/*      */     //   157: istore #11
/*      */     //   159: iload #31
/*      */     //   161: iconst_1
/*      */     //   162: isub
/*      */     //   163: istore #29
/*      */     //   165: iload #29
/*      */     //   167: iflt -> 1253
/*      */     //   170: iload #29
/*      */     //   172: ifeq -> 179
/*      */     //   175: iconst_4
/*      */     //   176: goto -> 190
/*      */     //   179: aload_0
/*      */     //   180: getfield g : I
/*      */     //   183: iload #31
/*      */     //   185: iconst_1
/*      */     //   186: isub
/*      */     //   187: iconst_4
/*      */     //   188: imul
/*      */     //   189: isub
/*      */     //   190: istore #32
/*      */     //   192: iload #13
/*      */     //   194: aload_0
/*      */     //   195: getfield f : I
/*      */     //   198: iadd
/*      */     //   199: istore #18
/*      */     //   201: iload #13
/*      */     //   203: iload #18
/*      */     //   205: if_icmpge -> 1233
/*      */     //   208: iload #11
/*      */     //   210: istore #10
/*      */     //   212: aload #4
/*      */     //   214: iload #10
/*      */     //   216: iaload
/*      */     //   217: istore #19
/*      */     //   219: iload #19
/*      */     //   221: iconst_m1
/*      */     //   222: ixor
/*      */     //   223: iload #19
/*      */     //   225: iconst_2
/*      */     //   226: ishl
/*      */     //   227: iand
/*      */     //   228: ldc -2147450880
/*      */     //   230: iand
/*      */     //   231: ifeq -> 715
/*      */     //   234: iload #13
/*      */     //   236: istore #12
/*      */     //   238: iload #19
/*      */     //   240: ldc 40960
/*      */     //   242: iand
/*      */     //   243: sipush #8192
/*      */     //   246: if_icmpne -> 468
/*      */     //   249: aload #23
/*      */     //   251: iload #12
/*      */     //   253: iaload
/*      */     //   254: iload #20
/*      */     //   256: iand
/*      */     //   257: iload_3
/*      */     //   258: iushr
/*      */     //   259: istore #22
/*      */     //   261: aload_1
/*      */     //   262: iload #22
/*      */     //   264: invokevirtual a : (I)V
/*      */     //   267: iinc #21, 1
/*      */     //   270: iload #22
/*      */     //   272: ifeq -> 460
/*      */     //   275: aload #23
/*      */     //   277: iload #12
/*      */     //   279: iaload
/*      */     //   280: bipush #31
/*      */     //   282: iushr
/*      */     //   283: istore #22
/*      */     //   285: aload_1
/*      */     //   286: iload #22
/*      */     //   288: invokevirtual a : (I)V
/*      */     //   291: iinc #21, 1
/*      */     //   294: iload #30
/*      */     //   296: ifne -> 325
/*      */     //   299: aload #4
/*      */     //   301: iload #10
/*      */     //   303: iload #33
/*      */     //   305: iadd
/*      */     //   306: dup2
/*      */     //   307: iaload
/*      */     //   308: ldc 536936448
/*      */     //   310: ior
/*      */     //   311: iastore
/*      */     //   312: aload #4
/*      */     //   314: iload #10
/*      */     //   316: iload #34
/*      */     //   318: iadd
/*      */     //   319: dup2
/*      */     //   320: iaload
/*      */     //   321: ldc 537001984
/*      */     //   323: ior
/*      */     //   324: iastore
/*      */     //   325: iload #22
/*      */     //   327: ifeq -> 382
/*      */     //   330: iload #19
/*      */     //   332: ldc 606126080
/*      */     //   334: ior
/*      */     //   335: istore #19
/*      */     //   337: iload #30
/*      */     //   339: ifne -> 355
/*      */     //   342: aload #4
/*      */     //   344: iload #10
/*      */     //   346: iload #15
/*      */     //   348: isub
/*      */     //   349: dup2
/*      */     //   350: iaload
/*      */     //   351: ldc 571473920
/*      */     //   353: ior
/*      */     //   354: iastore
/*      */     //   355: aload #4
/*      */     //   357: iload #10
/*      */     //   359: iconst_1
/*      */     //   360: iadd
/*      */     //   361: dup2
/*      */     //   362: iaload
/*      */     //   363: ldc 537407616
/*      */     //   365: ior
/*      */     //   366: iastore
/*      */     //   367: aload #4
/*      */     //   369: iload #10
/*      */     //   371: iconst_1
/*      */     //   372: isub
/*      */     //   373: dup2
/*      */     //   374: iaload
/*      */     //   375: ldc 537143360
/*      */     //   377: ior
/*      */     //   378: iastore
/*      */     //   379: goto -> 431
/*      */     //   382: iload #19
/*      */     //   384: ldc 539017216
/*      */     //   386: ior
/*      */     //   387: istore #19
/*      */     //   389: iload #30
/*      */     //   391: ifne -> 407
/*      */     //   394: aload #4
/*      */     //   396: iload #10
/*      */     //   398: iload #15
/*      */     //   400: isub
/*      */     //   401: dup2
/*      */     //   402: iaload
/*      */     //   403: ldc 537919488
/*      */     //   405: ior
/*      */     //   406: iastore
/*      */     //   407: aload #4
/*      */     //   409: iload #10
/*      */     //   411: iconst_1
/*      */     //   412: iadd
/*      */     //   413: dup2
/*      */     //   414: iaload
/*      */     //   415: ldc 537403520
/*      */     //   417: ior
/*      */     //   418: iastore
/*      */     //   419: aload #4
/*      */     //   421: iload #10
/*      */     //   423: iconst_1
/*      */     //   424: isub
/*      */     //   425: dup2
/*      */     //   426: iaload
/*      */     //   427: ldc 537141312
/*      */     //   429: ior
/*      */     //   430: iastore
/*      */     //   431: aload #23
/*      */     //   433: iload #12
/*      */     //   435: iaload
/*      */     //   436: iload #27
/*      */     //   438: ishr
/*      */     //   439: iload #26
/*      */     //   441: ishl
/*      */     //   442: istore #28
/*      */     //   444: iload #24
/*      */     //   446: aload #5
/*      */     //   448: iload #28
/*      */     //   450: bipush #63
/*      */     //   452: iand
/*      */     //   453: iaload
/*      */     //   454: iadd
/*      */     //   455: istore #24
/*      */     //   457: goto -> 468
/*      */     //   460: iload #19
/*      */     //   462: sipush #16384
/*      */     //   465: ior
/*      */     //   466: istore #19
/*      */     //   468: iload #32
/*      */     //   470: iconst_2
/*      */     //   471: if_icmpge -> 484
/*      */     //   474: aload #4
/*      */     //   476: iload #10
/*      */     //   478: iload #19
/*      */     //   480: iastore
/*      */     //   481: goto -> 1224
/*      */     //   484: iload #19
/*      */     //   486: ldc -1610612736
/*      */     //   488: iand
/*      */     //   489: ldc 536870912
/*      */     //   491: if_icmpne -> 708
/*      */     //   494: iload #12
/*      */     //   496: iload #14
/*      */     //   498: iadd
/*      */     //   499: istore #12
/*      */     //   501: aload #23
/*      */     //   503: iload #12
/*      */     //   505: iaload
/*      */     //   506: iload #20
/*      */     //   508: iand
/*      */     //   509: iload_3
/*      */     //   510: iushr
/*      */     //   511: istore #22
/*      */     //   513: aload_1
/*      */     //   514: iload #22
/*      */     //   516: invokevirtual a : (I)V
/*      */     //   519: iinc #21, 1
/*      */     //   522: iload #22
/*      */     //   524: ifeq -> 701
/*      */     //   527: aload #23
/*      */     //   529: iload #12
/*      */     //   531: iaload
/*      */     //   532: bipush #31
/*      */     //   534: iushr
/*      */     //   535: istore #22
/*      */     //   537: aload_1
/*      */     //   538: iload #22
/*      */     //   540: invokevirtual a : (I)V
/*      */     //   543: iinc #21, 1
/*      */     //   546: aload #4
/*      */     //   548: iload #10
/*      */     //   550: iload #36
/*      */     //   552: iadd
/*      */     //   553: dup2
/*      */     //   554: iaload
/*      */     //   555: sipush #8196
/*      */     //   558: ior
/*      */     //   559: iastore
/*      */     //   560: aload #4
/*      */     //   562: iload #10
/*      */     //   564: iload #35
/*      */     //   566: iadd
/*      */     //   567: dup2
/*      */     //   568: iaload
/*      */     //   569: sipush #8200
/*      */     //   572: ior
/*      */     //   573: iastore
/*      */     //   574: iload #22
/*      */     //   576: ifeq -> 627
/*      */     //   579: iload #19
/*      */     //   581: ldc -1073733104
/*      */     //   583: ior
/*      */     //   584: istore #19
/*      */     //   586: aload #4
/*      */     //   588: iload #10
/*      */     //   590: iload #15
/*      */     //   592: iadd
/*      */     //   593: dup2
/*      */     //   594: iaload
/*      */     //   595: sipush #9248
/*      */     //   598: ior
/*      */     //   599: iastore
/*      */     //   600: aload #4
/*      */     //   602: iload #10
/*      */     //   604: iconst_1
/*      */     //   605: iadd
/*      */     //   606: dup2
/*      */     //   607: iaload
/*      */     //   608: ldc 813703170
/*      */     //   610: ior
/*      */     //   611: iastore
/*      */     //   612: aload #4
/*      */     //   614: iload #10
/*      */     //   616: iconst_1
/*      */     //   617: isub
/*      */     //   618: dup2
/*      */     //   619: iaload
/*      */     //   620: ldc 675291137
/*      */     //   622: ior
/*      */     //   623: iastore
/*      */     //   624: goto -> 672
/*      */     //   627: iload #19
/*      */     //   629: ldc -1073733616
/*      */     //   631: ior
/*      */     //   632: istore #19
/*      */     //   634: aload #4
/*      */     //   636: iload #10
/*      */     //   638: iload #15
/*      */     //   640: iadd
/*      */     //   641: dup2
/*      */     //   642: iaload
/*      */     //   643: sipush #8224
/*      */     //   646: ior
/*      */     //   647: iastore
/*      */     //   648: aload #4
/*      */     //   650: iload #10
/*      */     //   652: iconst_1
/*      */     //   653: iadd
/*      */     //   654: dup2
/*      */     //   655: iaload
/*      */     //   656: ldc 545267714
/*      */     //   658: ior
/*      */     //   659: iastore
/*      */     //   660: aload #4
/*      */     //   662: iload #10
/*      */     //   664: iconst_1
/*      */     //   665: isub
/*      */     //   666: dup2
/*      */     //   667: iaload
/*      */     //   668: ldc 541073409
/*      */     //   670: ior
/*      */     //   671: iastore
/*      */     //   672: aload #23
/*      */     //   674: iload #12
/*      */     //   676: iaload
/*      */     //   677: iload #27
/*      */     //   679: ishr
/*      */     //   680: iload #26
/*      */     //   682: ishl
/*      */     //   683: istore #28
/*      */     //   685: iload #24
/*      */     //   687: aload #5
/*      */     //   689: iload #28
/*      */     //   691: bipush #63
/*      */     //   693: iand
/*      */     //   694: iaload
/*      */     //   695: iadd
/*      */     //   696: istore #24
/*      */     //   698: goto -> 708
/*      */     //   701: iload #19
/*      */     //   703: ldc 1073741824
/*      */     //   705: ior
/*      */     //   706: istore #19
/*      */     //   708: aload #4
/*      */     //   710: iload #10
/*      */     //   712: iload #19
/*      */     //   714: iastore
/*      */     //   715: iload #32
/*      */     //   717: iconst_3
/*      */     //   718: if_icmpge -> 724
/*      */     //   721: goto -> 1224
/*      */     //   724: iload #10
/*      */     //   726: iload #15
/*      */     //   728: iadd
/*      */     //   729: istore #10
/*      */     //   731: aload #4
/*      */     //   733: iload #10
/*      */     //   735: iaload
/*      */     //   736: istore #19
/*      */     //   738: iload #19
/*      */     //   740: iconst_m1
/*      */     //   741: ixor
/*      */     //   742: iload #19
/*      */     //   744: iconst_2
/*      */     //   745: ishl
/*      */     //   746: iand
/*      */     //   747: ldc -2147450880
/*      */     //   749: iand
/*      */     //   750: ifeq -> 1224
/*      */     //   753: iload #13
/*      */     //   755: iload #14
/*      */     //   757: iconst_1
/*      */     //   758: ishl
/*      */     //   759: iadd
/*      */     //   760: istore #12
/*      */     //   762: iload #19
/*      */     //   764: ldc 40960
/*      */     //   766: iand
/*      */     //   767: sipush #8192
/*      */     //   770: if_icmpne -> 977
/*      */     //   773: aload #23
/*      */     //   775: iload #12
/*      */     //   777: iaload
/*      */     //   778: iload #20
/*      */     //   780: iand
/*      */     //   781: iload_3
/*      */     //   782: iushr
/*      */     //   783: istore #22
/*      */     //   785: aload_1
/*      */     //   786: iload #22
/*      */     //   788: invokevirtual a : (I)V
/*      */     //   791: iinc #21, 1
/*      */     //   794: iload #22
/*      */     //   796: ifeq -> 969
/*      */     //   799: aload #23
/*      */     //   801: iload #12
/*      */     //   803: iaload
/*      */     //   804: bipush #31
/*      */     //   806: iushr
/*      */     //   807: istore #22
/*      */     //   809: aload_1
/*      */     //   810: iload #22
/*      */     //   812: invokevirtual a : (I)V
/*      */     //   815: iinc #21, 1
/*      */     //   818: aload #4
/*      */     //   820: iload #10
/*      */     //   822: iload #33
/*      */     //   824: iadd
/*      */     //   825: dup2
/*      */     //   826: iaload
/*      */     //   827: ldc 536936448
/*      */     //   829: ior
/*      */     //   830: iastore
/*      */     //   831: aload #4
/*      */     //   833: iload #10
/*      */     //   835: iload #34
/*      */     //   837: iadd
/*      */     //   838: dup2
/*      */     //   839: iaload
/*      */     //   840: ldc 537001984
/*      */     //   842: ior
/*      */     //   843: iastore
/*      */     //   844: iload #22
/*      */     //   846: ifeq -> 896
/*      */     //   849: iload #19
/*      */     //   851: ldc 606126080
/*      */     //   853: ior
/*      */     //   854: istore #19
/*      */     //   856: aload #4
/*      */     //   858: iload #10
/*      */     //   860: iload #15
/*      */     //   862: isub
/*      */     //   863: dup2
/*      */     //   864: iaload
/*      */     //   865: ldc 571473920
/*      */     //   867: ior
/*      */     //   868: iastore
/*      */     //   869: aload #4
/*      */     //   871: iload #10
/*      */     //   873: iconst_1
/*      */     //   874: iadd
/*      */     //   875: dup2
/*      */     //   876: iaload
/*      */     //   877: ldc 537407616
/*      */     //   879: ior
/*      */     //   880: iastore
/*      */     //   881: aload #4
/*      */     //   883: iload #10
/*      */     //   885: iconst_1
/*      */     //   886: isub
/*      */     //   887: dup2
/*      */     //   888: iaload
/*      */     //   889: ldc 537143360
/*      */     //   891: ior
/*      */     //   892: iastore
/*      */     //   893: goto -> 940
/*      */     //   896: iload #19
/*      */     //   898: ldc 539017216
/*      */     //   900: ior
/*      */     //   901: istore #19
/*      */     //   903: aload #4
/*      */     //   905: iload #10
/*      */     //   907: iload #15
/*      */     //   909: isub
/*      */     //   910: dup2
/*      */     //   911: iaload
/*      */     //   912: ldc 537919488
/*      */     //   914: ior
/*      */     //   915: iastore
/*      */     //   916: aload #4
/*      */     //   918: iload #10
/*      */     //   920: iconst_1
/*      */     //   921: iadd
/*      */     //   922: dup2
/*      */     //   923: iaload
/*      */     //   924: ldc 537403520
/*      */     //   926: ior
/*      */     //   927: iastore
/*      */     //   928: aload #4
/*      */     //   930: iload #10
/*      */     //   932: iconst_1
/*      */     //   933: isub
/*      */     //   934: dup2
/*      */     //   935: iaload
/*      */     //   936: ldc 537141312
/*      */     //   938: ior
/*      */     //   939: iastore
/*      */     //   940: aload #23
/*      */     //   942: iload #12
/*      */     //   944: iaload
/*      */     //   945: iload #27
/*      */     //   947: ishr
/*      */     //   948: iload #26
/*      */     //   950: ishl
/*      */     //   951: istore #28
/*      */     //   953: iload #24
/*      */     //   955: aload #5
/*      */     //   957: iload #28
/*      */     //   959: bipush #63
/*      */     //   961: iand
/*      */     //   962: iaload
/*      */     //   963: iadd
/*      */     //   964: istore #24
/*      */     //   966: goto -> 977
/*      */     //   969: iload #19
/*      */     //   971: sipush #16384
/*      */     //   974: ior
/*      */     //   975: istore #19
/*      */     //   977: iload #32
/*      */     //   979: iconst_4
/*      */     //   980: if_icmpge -> 993
/*      */     //   983: aload #4
/*      */     //   985: iload #10
/*      */     //   987: iload #19
/*      */     //   989: iastore
/*      */     //   990: goto -> 1224
/*      */     //   993: iload #19
/*      */     //   995: ldc -1610612736
/*      */     //   997: iand
/*      */     //   998: ldc 536870912
/*      */     //   1000: if_icmpne -> 1217
/*      */     //   1003: iload #12
/*      */     //   1005: iload #14
/*      */     //   1007: iadd
/*      */     //   1008: istore #12
/*      */     //   1010: aload #23
/*      */     //   1012: iload #12
/*      */     //   1014: iaload
/*      */     //   1015: iload #20
/*      */     //   1017: iand
/*      */     //   1018: iload_3
/*      */     //   1019: iushr
/*      */     //   1020: istore #22
/*      */     //   1022: aload_1
/*      */     //   1023: iload #22
/*      */     //   1025: invokevirtual a : (I)V
/*      */     //   1028: iinc #21, 1
/*      */     //   1031: iload #22
/*      */     //   1033: ifeq -> 1210
/*      */     //   1036: aload #23
/*      */     //   1038: iload #12
/*      */     //   1040: iaload
/*      */     //   1041: bipush #31
/*      */     //   1043: iushr
/*      */     //   1044: istore #22
/*      */     //   1046: aload_1
/*      */     //   1047: iload #22
/*      */     //   1049: invokevirtual a : (I)V
/*      */     //   1052: iinc #21, 1
/*      */     //   1055: aload #4
/*      */     //   1057: iload #10
/*      */     //   1059: iload #36
/*      */     //   1061: iadd
/*      */     //   1062: dup2
/*      */     //   1063: iaload
/*      */     //   1064: sipush #8196
/*      */     //   1067: ior
/*      */     //   1068: iastore
/*      */     //   1069: aload #4
/*      */     //   1071: iload #10
/*      */     //   1073: iload #35
/*      */     //   1075: iadd
/*      */     //   1076: dup2
/*      */     //   1077: iaload
/*      */     //   1078: sipush #8200
/*      */     //   1081: ior
/*      */     //   1082: iastore
/*      */     //   1083: iload #22
/*      */     //   1085: ifeq -> 1136
/*      */     //   1088: iload #19
/*      */     //   1090: ldc -1073733104
/*      */     //   1092: ior
/*      */     //   1093: istore #19
/*      */     //   1095: aload #4
/*      */     //   1097: iload #10
/*      */     //   1099: iload #15
/*      */     //   1101: iadd
/*      */     //   1102: dup2
/*      */     //   1103: iaload
/*      */     //   1104: sipush #9248
/*      */     //   1107: ior
/*      */     //   1108: iastore
/*      */     //   1109: aload #4
/*      */     //   1111: iload #10
/*      */     //   1113: iconst_1
/*      */     //   1114: iadd
/*      */     //   1115: dup2
/*      */     //   1116: iaload
/*      */     //   1117: ldc 813703170
/*      */     //   1119: ior
/*      */     //   1120: iastore
/*      */     //   1121: aload #4
/*      */     //   1123: iload #10
/*      */     //   1125: iconst_1
/*      */     //   1126: isub
/*      */     //   1127: dup2
/*      */     //   1128: iaload
/*      */     //   1129: ldc 675291137
/*      */     //   1131: ior
/*      */     //   1132: iastore
/*      */     //   1133: goto -> 1181
/*      */     //   1136: iload #19
/*      */     //   1138: ldc -1073733616
/*      */     //   1140: ior
/*      */     //   1141: istore #19
/*      */     //   1143: aload #4
/*      */     //   1145: iload #10
/*      */     //   1147: iload #15
/*      */     //   1149: iadd
/*      */     //   1150: dup2
/*      */     //   1151: iaload
/*      */     //   1152: sipush #8224
/*      */     //   1155: ior
/*      */     //   1156: iastore
/*      */     //   1157: aload #4
/*      */     //   1159: iload #10
/*      */     //   1161: iconst_1
/*      */     //   1162: iadd
/*      */     //   1163: dup2
/*      */     //   1164: iaload
/*      */     //   1165: ldc 545267714
/*      */     //   1167: ior
/*      */     //   1168: iastore
/*      */     //   1169: aload #4
/*      */     //   1171: iload #10
/*      */     //   1173: iconst_1
/*      */     //   1174: isub
/*      */     //   1175: dup2
/*      */     //   1176: iaload
/*      */     //   1177: ldc 541073409
/*      */     //   1179: ior
/*      */     //   1180: iastore
/*      */     //   1181: aload #23
/*      */     //   1183: iload #12
/*      */     //   1185: iaload
/*      */     //   1186: iload #27
/*      */     //   1188: ishr
/*      */     //   1189: iload #26
/*      */     //   1191: ishl
/*      */     //   1192: istore #28
/*      */     //   1194: iload #24
/*      */     //   1196: aload #5
/*      */     //   1198: iload #28
/*      */     //   1200: bipush #63
/*      */     //   1202: iand
/*      */     //   1203: iaload
/*      */     //   1204: iadd
/*      */     //   1205: istore #24
/*      */     //   1207: goto -> 1217
/*      */     //   1210: iload #19
/*      */     //   1212: ldc 1073741824
/*      */     //   1214: ior
/*      */     //   1215: istore #19
/*      */     //   1217: aload #4
/*      */     //   1219: iload #10
/*      */     //   1221: iload #19
/*      */     //   1223: iastore
/*      */     //   1224: iinc #13, 1
/*      */     //   1227: iinc #11, 1
/*      */     //   1230: goto -> 201
/*      */     //   1233: iinc #29, -1
/*      */     //   1236: iload #13
/*      */     //   1238: iload #17
/*      */     //   1240: iadd
/*      */     //   1241: istore #13
/*      */     //   1243: iload #11
/*      */     //   1245: iload #16
/*      */     //   1247: iadd
/*      */     //   1248: istore #11
/*      */     //   1250: goto -> 165
/*      */     //   1253: iload_2
/*      */     //   1254: ifeq -> 1269
/*      */     //   1257: aload #6
/*      */     //   1259: iload #7
/*      */     //   1261: aload_1
/*      */     //   1262: invokevirtual b : ()I
/*      */     //   1265: iastore
/*      */     //   1266: goto -> 1278
/*      */     //   1269: aload #6
/*      */     //   1271: iload #7
/*      */     //   1273: aload_1
/*      */     //   1274: invokevirtual d : ()I
/*      */     //   1277: iastore
/*      */     //   1278: iload #8
/*      */     //   1280: iflt -> 1296
/*      */     //   1283: aload #6
/*      */     //   1285: iload #7
/*      */     //   1287: dup2
/*      */     //   1288: iaload
/*      */     //   1289: aload #6
/*      */     //   1291: iload #8
/*      */     //   1293: iaload
/*      */     //   1294: iadd
/*      */     //   1295: iastore
/*      */     //   1296: iload #24
/*      */     //   1298: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #2018	-> 0
/*      */     //   #2034	-> 3
/*      */     //   #2035	-> 9
/*      */     //   #2036	-> 17
/*      */     //   #2037	-> 30
/*      */     //   #2038	-> 41
/*      */     //   #2039	-> 46
/*      */     //   #2040	-> 58
/*      */     //   #2041	-> 70
/*      */     //   #2044	-> 73
/*      */     //   #2045	-> 79
/*      */     //   #2046	-> 93
/*      */     //   #2047	-> 106
/*      */     //   #2050	-> 121
/*      */     //   #2051	-> 128
/*      */     //   #2052	-> 135
/*      */     //   #2053	-> 141
/*      */     //   #2056	-> 147
/*      */     //   #2057	-> 153
/*      */     //   #2058	-> 159
/*      */     //   #2059	-> 170
/*      */     //   #2061	-> 192
/*      */     //   #2063	-> 201
/*      */     //   #2065	-> 208
/*      */     //   #2066	-> 212
/*      */     //   #2070	-> 219
/*      */     //   #2071	-> 234
/*      */     //   #2073	-> 238
/*      */     //   #2076	-> 249
/*      */     //   #2077	-> 261
/*      */     //   #2078	-> 267
/*      */     //   #2080	-> 270
/*      */     //   #2083	-> 275
/*      */     //   #2084	-> 285
/*      */     //   #2085	-> 291
/*      */     //   #2091	-> 294
/*      */     //   #2094	-> 299
/*      */     //   #2096	-> 312
/*      */     //   #2100	-> 325
/*      */     //   #2101	-> 330
/*      */     //   #2104	-> 337
/*      */     //   #2107	-> 342
/*      */     //   #2110	-> 355
/*      */     //   #2114	-> 367
/*      */     //   #2120	-> 382
/*      */     //   #2122	-> 389
/*      */     //   #2125	-> 394
/*      */     //   #2128	-> 407
/*      */     //   #2131	-> 419
/*      */     //   #2136	-> 431
/*      */     //   #2137	-> 444
/*      */     //   #2140	-> 460
/*      */     //   #2143	-> 468
/*      */     //   #2144	-> 474
/*      */     //   #2145	-> 481
/*      */     //   #2148	-> 484
/*      */     //   #2150	-> 494
/*      */     //   #2152	-> 501
/*      */     //   #2153	-> 513
/*      */     //   #2154	-> 519
/*      */     //   #2155	-> 522
/*      */     //   #2158	-> 527
/*      */     //   #2159	-> 537
/*      */     //   #2160	-> 543
/*      */     //   #2165	-> 546
/*      */     //   #2166	-> 560
/*      */     //   #2168	-> 574
/*      */     //   #2169	-> 579
/*      */     //   #2172	-> 586
/*      */     //   #2174	-> 600
/*      */     //   #2178	-> 612
/*      */     //   #2184	-> 627
/*      */     //   #2186	-> 634
/*      */     //   #2188	-> 648
/*      */     //   #2191	-> 660
/*      */     //   #2196	-> 672
/*      */     //   #2197	-> 685
/*      */     //   #2200	-> 701
/*      */     //   #2203	-> 708
/*      */     //   #2206	-> 715
/*      */     //   #2207	-> 724
/*      */     //   #2208	-> 731
/*      */     //   #2212	-> 738
/*      */     //   #2213	-> 753
/*      */     //   #2215	-> 762
/*      */     //   #2217	-> 773
/*      */     //   #2218	-> 785
/*      */     //   #2219	-> 791
/*      */     //   #2220	-> 794
/*      */     //   #2223	-> 799
/*      */     //   #2224	-> 809
/*      */     //   #2225	-> 815
/*      */     //   #2230	-> 818
/*      */     //   #2231	-> 831
/*      */     //   #2233	-> 844
/*      */     //   #2234	-> 849
/*      */     //   #2237	-> 856
/*      */     //   #2239	-> 869
/*      */     //   #2243	-> 881
/*      */     //   #2249	-> 896
/*      */     //   #2251	-> 903
/*      */     //   #2253	-> 916
/*      */     //   #2256	-> 928
/*      */     //   #2261	-> 940
/*      */     //   #2262	-> 953
/*      */     //   #2265	-> 969
/*      */     //   #2268	-> 977
/*      */     //   #2269	-> 983
/*      */     //   #2270	-> 990
/*      */     //   #2272	-> 993
/*      */     //   #2274	-> 1003
/*      */     //   #2276	-> 1010
/*      */     //   #2277	-> 1022
/*      */     //   #2278	-> 1028
/*      */     //   #2279	-> 1031
/*      */     //   #2282	-> 1036
/*      */     //   #2283	-> 1046
/*      */     //   #2284	-> 1052
/*      */     //   #2289	-> 1055
/*      */     //   #2290	-> 1069
/*      */     //   #2292	-> 1083
/*      */     //   #2293	-> 1088
/*      */     //   #2296	-> 1095
/*      */     //   #2298	-> 1109
/*      */     //   #2302	-> 1121
/*      */     //   #2308	-> 1136
/*      */     //   #2310	-> 1143
/*      */     //   #2312	-> 1157
/*      */     //   #2315	-> 1169
/*      */     //   #2320	-> 1181
/*      */     //   #2321	-> 1194
/*      */     //   #2324	-> 1210
/*      */     //   #2327	-> 1217
/*      */     //   #2063	-> 1224
/*      */     //   #2058	-> 1233
/*      */     //   #2333	-> 1253
/*      */     //   #2334	-> 1257
/*      */     //   #2336	-> 1269
/*      */     //   #2339	-> 1278
/*      */     //   #2340	-> 1283
/*      */     //   #2344	-> 1296
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	1299	0	srcblk	Lc/a/j/a/g;
/*      */     //   0	1299	1	bout	Lc/a/c/b/a;
/*      */     //   0	1299	2	doterm	Z
/*      */     //   0	1299	3	bp	I
/*      */     //   0	1299	4	state	[I
/*      */     //   0	1299	5	fs	[I
/*      */     //   0	1299	6	ratebuf	[I
/*      */     //   0	1299	7	pidx	I
/*      */     //   0	1299	8	ltpidx	I
/*      */     //   0	1299	9	options	I
/*      */     //   3	1296	21	nsym	I
/*      */     //   9	1290	14	dscanw	I
/*      */     //   17	1282	15	sscanw	I
/*      */     //   30	1269	16	jstep	I
/*      */     //   41	1258	17	kstep	I
/*      */     //   46	1253	20	mask	I
/*      */     //   58	1241	23	data	[I
/*      */     //   70	1229	31	nstripes	I
/*      */     //   73	1226	24	dist	I
/*      */     //   79	1220	25	shift	I
/*      */     //   93	1206	26	upshift	I
/*      */     //   106	1193	27	downshift	I
/*      */     //   121	1178	30	causal	Z
/*      */     //   128	1171	33	off_ul	I
/*      */     //   135	1164	34	off_ur	I
/*      */     //   141	1158	35	off_dr	I
/*      */     //   147	1152	36	off_dl	I
/*      */     //   153	1146	13	sk	I
/*      */     //   159	1140	11	sj	I
/*      */     //   165	1134	29	s	I
/*      */     //   192	1058	32	sheight	I
/*      */     //   201	1049	18	stopsk	I
/*      */     //   212	1018	10	j	I
/*      */     //   219	1011	19	csj	I
/*      */     //   238	477	12	k	I
/*      */     //   261	207	22	sym	I
/*      */     //   444	16	28	normval	I
/*      */     //   513	195	22	sym	I
/*      */     //   685	16	28	normval	I
/*      */     //   762	462	12	k	I
/*      */     //   785	192	22	sym	I
/*      */     //   953	16	28	normval	I
/*      */     //   1022	195	22	sym	I
/*      */     //   1194	16	28	normval	I
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int a(g srcblk, i mq, boolean doterm, int bp, int[] state, int[] fm, int[] symbuf, int[] ctxtbuf, int[] ratebuf, int pidx, int ltpidx, int options) {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore #16
/*      */     //   3: aload_0
/*      */     //   4: getfield i : I
/*      */     //   7: istore #17
/*      */     //   9: aload_0
/*      */     //   10: getfield f : I
/*      */     //   13: iconst_2
/*      */     //   14: iadd
/*      */     //   15: istore #18
/*      */     //   17: iload #18
/*      */     //   19: iconst_4
/*      */     //   20: imul
/*      */     //   21: iconst_2
/*      */     //   22: idiv
/*      */     //   23: aload_0
/*      */     //   24: getfield f : I
/*      */     //   27: isub
/*      */     //   28: istore #19
/*      */     //   30: iload #17
/*      */     //   32: iconst_4
/*      */     //   33: imul
/*      */     //   34: aload_0
/*      */     //   35: getfield f : I
/*      */     //   38: isub
/*      */     //   39: istore #20
/*      */     //   41: iconst_1
/*      */     //   42: iload_3
/*      */     //   43: ishl
/*      */     //   44: istore #23
/*      */     //   46: aload_0
/*      */     //   47: invokevirtual b : ()Ljava/lang/Object;
/*      */     //   50: checkcast [I
/*      */     //   53: checkcast [I
/*      */     //   56: astore #24
/*      */     //   58: aload_0
/*      */     //   59: getfield g : I
/*      */     //   62: iconst_4
/*      */     //   63: iadd
/*      */     //   64: iconst_1
/*      */     //   65: isub
/*      */     //   66: iconst_4
/*      */     //   67: idiv
/*      */     //   68: istore #31
/*      */     //   70: iconst_0
/*      */     //   71: istore #25
/*      */     //   73: iload_3
/*      */     //   74: bipush #6
/*      */     //   76: isub
/*      */     //   77: istore #26
/*      */     //   79: iload #26
/*      */     //   81: iflt -> 88
/*      */     //   84: iconst_0
/*      */     //   85: goto -> 91
/*      */     //   88: iload #26
/*      */     //   90: ineg
/*      */     //   91: istore #27
/*      */     //   93: iload #26
/*      */     //   95: ifgt -> 102
/*      */     //   98: iconst_0
/*      */     //   99: goto -> 104
/*      */     //   102: iload #26
/*      */     //   104: istore #28
/*      */     //   106: aload_0
/*      */     //   107: getfield h : I
/*      */     //   110: istore #15
/*      */     //   112: iload #18
/*      */     //   114: iconst_1
/*      */     //   115: iadd
/*      */     //   116: istore #13
/*      */     //   118: iload #31
/*      */     //   120: iconst_1
/*      */     //   121: isub
/*      */     //   122: istore #30
/*      */     //   124: iload #30
/*      */     //   126: iflt -> 666
/*      */     //   129: iload #30
/*      */     //   131: ifeq -> 138
/*      */     //   134: iconst_4
/*      */     //   135: goto -> 149
/*      */     //   138: aload_0
/*      */     //   139: getfield g : I
/*      */     //   142: iload #31
/*      */     //   144: iconst_1
/*      */     //   145: isub
/*      */     //   146: iconst_4
/*      */     //   147: imul
/*      */     //   148: isub
/*      */     //   149: istore #32
/*      */     //   151: iload #15
/*      */     //   153: aload_0
/*      */     //   154: getfield f : I
/*      */     //   157: iadd
/*      */     //   158: istore #21
/*      */     //   160: iconst_0
/*      */     //   161: istore #16
/*      */     //   163: iload #15
/*      */     //   165: iload #21
/*      */     //   167: if_icmpge -> 631
/*      */     //   170: iload #13
/*      */     //   172: istore #12
/*      */     //   174: aload #4
/*      */     //   176: iload #12
/*      */     //   178: iaload
/*      */     //   179: istore #22
/*      */     //   181: iload #22
/*      */     //   183: iconst_1
/*      */     //   184: iushr
/*      */     //   185: iload #22
/*      */     //   187: iconst_m1
/*      */     //   188: ixor
/*      */     //   189: iand
/*      */     //   190: ldc 1073758208
/*      */     //   192: iand
/*      */     //   193: ifeq -> 386
/*      */     //   196: iload #15
/*      */     //   198: istore #14
/*      */     //   200: iload #22
/*      */     //   202: ldc 49152
/*      */     //   204: iand
/*      */     //   205: ldc 32768
/*      */     //   207: if_icmpne -> 277
/*      */     //   210: aload #6
/*      */     //   212: iload #16
/*      */     //   214: aload #24
/*      */     //   216: iload #14
/*      */     //   218: iaload
/*      */     //   219: iload #23
/*      */     //   221: iand
/*      */     //   222: iload_3
/*      */     //   223: iushr
/*      */     //   224: iastore
/*      */     //   225: aload #7
/*      */     //   227: iload #16
/*      */     //   229: iinc #16, 1
/*      */     //   232: getstatic c/a/c/b/k.Z : [I
/*      */     //   235: iload #22
/*      */     //   237: sipush #511
/*      */     //   240: iand
/*      */     //   241: iaload
/*      */     //   242: iastore
/*      */     //   243: iload #22
/*      */     //   245: sipush #256
/*      */     //   248: ior
/*      */     //   249: istore #22
/*      */     //   251: aload #24
/*      */     //   253: iload #14
/*      */     //   255: iaload
/*      */     //   256: iload #28
/*      */     //   258: ishr
/*      */     //   259: iload #27
/*      */     //   261: ishl
/*      */     //   262: istore #29
/*      */     //   264: iload #25
/*      */     //   266: aload #5
/*      */     //   268: iload #29
/*      */     //   270: bipush #127
/*      */     //   272: iand
/*      */     //   273: iaload
/*      */     //   274: iadd
/*      */     //   275: istore #25
/*      */     //   277: iload #32
/*      */     //   279: iconst_2
/*      */     //   280: if_icmpge -> 293
/*      */     //   283: aload #4
/*      */     //   285: iload #12
/*      */     //   287: iload #22
/*      */     //   289: iastore
/*      */     //   290: goto -> 622
/*      */     //   293: iload #22
/*      */     //   295: ldc -1073741824
/*      */     //   297: iand
/*      */     //   298: ldc -2147483648
/*      */     //   300: if_icmpne -> 379
/*      */     //   303: iload #14
/*      */     //   305: iload #17
/*      */     //   307: iadd
/*      */     //   308: istore #14
/*      */     //   310: aload #6
/*      */     //   312: iload #16
/*      */     //   314: aload #24
/*      */     //   316: iload #14
/*      */     //   318: iaload
/*      */     //   319: iload #23
/*      */     //   321: iand
/*      */     //   322: iload_3
/*      */     //   323: iushr
/*      */     //   324: iastore
/*      */     //   325: aload #7
/*      */     //   327: iload #16
/*      */     //   329: iinc #16, 1
/*      */     //   332: getstatic c/a/c/b/k.Z : [I
/*      */     //   335: iload #22
/*      */     //   337: bipush #16
/*      */     //   339: iushr
/*      */     //   340: sipush #511
/*      */     //   343: iand
/*      */     //   344: iaload
/*      */     //   345: iastore
/*      */     //   346: iload #22
/*      */     //   348: ldc 16777216
/*      */     //   350: ior
/*      */     //   351: istore #22
/*      */     //   353: aload #24
/*      */     //   355: iload #14
/*      */     //   357: iaload
/*      */     //   358: iload #28
/*      */     //   360: ishr
/*      */     //   361: iload #27
/*      */     //   363: ishl
/*      */     //   364: istore #29
/*      */     //   366: iload #25
/*      */     //   368: aload #5
/*      */     //   370: iload #29
/*      */     //   372: bipush #127
/*      */     //   374: iand
/*      */     //   375: iaload
/*      */     //   376: iadd
/*      */     //   377: istore #25
/*      */     //   379: aload #4
/*      */     //   381: iload #12
/*      */     //   383: iload #22
/*      */     //   385: iastore
/*      */     //   386: iload #32
/*      */     //   388: iconst_3
/*      */     //   389: if_icmpge -> 395
/*      */     //   392: goto -> 622
/*      */     //   395: iload #12
/*      */     //   397: iload #18
/*      */     //   399: iadd
/*      */     //   400: istore #12
/*      */     //   402: aload #4
/*      */     //   404: iload #12
/*      */     //   406: iaload
/*      */     //   407: istore #22
/*      */     //   409: iload #22
/*      */     //   411: iconst_1
/*      */     //   412: iushr
/*      */     //   413: iload #22
/*      */     //   415: iconst_m1
/*      */     //   416: ixor
/*      */     //   417: iand
/*      */     //   418: ldc 1073758208
/*      */     //   420: iand
/*      */     //   421: ifeq -> 622
/*      */     //   424: iload #15
/*      */     //   426: iload #17
/*      */     //   428: iconst_1
/*      */     //   429: ishl
/*      */     //   430: iadd
/*      */     //   431: istore #14
/*      */     //   433: iload #22
/*      */     //   435: ldc 49152
/*      */     //   437: iand
/*      */     //   438: ldc 32768
/*      */     //   440: if_icmpne -> 510
/*      */     //   443: aload #6
/*      */     //   445: iload #16
/*      */     //   447: aload #24
/*      */     //   449: iload #14
/*      */     //   451: iaload
/*      */     //   452: iload #23
/*      */     //   454: iand
/*      */     //   455: iload_3
/*      */     //   456: iushr
/*      */     //   457: iastore
/*      */     //   458: aload #7
/*      */     //   460: iload #16
/*      */     //   462: iinc #16, 1
/*      */     //   465: getstatic c/a/c/b/k.Z : [I
/*      */     //   468: iload #22
/*      */     //   470: sipush #511
/*      */     //   473: iand
/*      */     //   474: iaload
/*      */     //   475: iastore
/*      */     //   476: iload #22
/*      */     //   478: sipush #256
/*      */     //   481: ior
/*      */     //   482: istore #22
/*      */     //   484: aload #24
/*      */     //   486: iload #14
/*      */     //   488: iaload
/*      */     //   489: iload #28
/*      */     //   491: ishr
/*      */     //   492: iload #27
/*      */     //   494: ishl
/*      */     //   495: istore #29
/*      */     //   497: iload #25
/*      */     //   499: aload #5
/*      */     //   501: iload #29
/*      */     //   503: bipush #127
/*      */     //   505: iand
/*      */     //   506: iaload
/*      */     //   507: iadd
/*      */     //   508: istore #25
/*      */     //   510: iload #32
/*      */     //   512: iconst_4
/*      */     //   513: if_icmpge -> 526
/*      */     //   516: aload #4
/*      */     //   518: iload #12
/*      */     //   520: iload #22
/*      */     //   522: iastore
/*      */     //   523: goto -> 622
/*      */     //   526: aload #4
/*      */     //   528: iload #12
/*      */     //   530: iaload
/*      */     //   531: ldc -1073741824
/*      */     //   533: iand
/*      */     //   534: ldc -2147483648
/*      */     //   536: if_icmpne -> 615
/*      */     //   539: iload #14
/*      */     //   541: iload #17
/*      */     //   543: iadd
/*      */     //   544: istore #14
/*      */     //   546: aload #6
/*      */     //   548: iload #16
/*      */     //   550: aload #24
/*      */     //   552: iload #14
/*      */     //   554: iaload
/*      */     //   555: iload #23
/*      */     //   557: iand
/*      */     //   558: iload_3
/*      */     //   559: iushr
/*      */     //   560: iastore
/*      */     //   561: aload #7
/*      */     //   563: iload #16
/*      */     //   565: iinc #16, 1
/*      */     //   568: getstatic c/a/c/b/k.Z : [I
/*      */     //   571: iload #22
/*      */     //   573: bipush #16
/*      */     //   575: iushr
/*      */     //   576: sipush #511
/*      */     //   579: iand
/*      */     //   580: iaload
/*      */     //   581: iastore
/*      */     //   582: iload #22
/*      */     //   584: ldc 16777216
/*      */     //   586: ior
/*      */     //   587: istore #22
/*      */     //   589: aload #24
/*      */     //   591: iload #14
/*      */     //   593: iaload
/*      */     //   594: iload #28
/*      */     //   596: ishr
/*      */     //   597: iload #27
/*      */     //   599: ishl
/*      */     //   600: istore #29
/*      */     //   602: iload #25
/*      */     //   604: aload #5
/*      */     //   606: iload #29
/*      */     //   608: bipush #127
/*      */     //   610: iand
/*      */     //   611: iaload
/*      */     //   612: iadd
/*      */     //   613: istore #25
/*      */     //   615: aload #4
/*      */     //   617: iload #12
/*      */     //   619: iload #22
/*      */     //   621: iastore
/*      */     //   622: iinc #15, 1
/*      */     //   625: iinc #13, 1
/*      */     //   628: goto -> 163
/*      */     //   631: iload #16
/*      */     //   633: ifle -> 646
/*      */     //   636: aload_1
/*      */     //   637: aload #6
/*      */     //   639: aload #7
/*      */     //   641: iload #16
/*      */     //   643: invokevirtual a : ([I[II)V
/*      */     //   646: iinc #30, -1
/*      */     //   649: iload #15
/*      */     //   651: iload #20
/*      */     //   653: iadd
/*      */     //   654: istore #15
/*      */     //   656: iload #13
/*      */     //   658: iload #19
/*      */     //   660: iadd
/*      */     //   661: istore #13
/*      */     //   663: goto -> 124
/*      */     //   666: iload #11
/*      */     //   668: iconst_2
/*      */     //   669: iand
/*      */     //   670: ifeq -> 677
/*      */     //   673: aload_1
/*      */     //   674: invokevirtual c : ()V
/*      */     //   677: iload_2
/*      */     //   678: ifeq -> 693
/*      */     //   681: aload #8
/*      */     //   683: iload #9
/*      */     //   685: aload_1
/*      */     //   686: invokevirtual a : ()I
/*      */     //   689: iastore
/*      */     //   690: goto -> 702
/*      */     //   693: aload #8
/*      */     //   695: iload #9
/*      */     //   697: aload_1
/*      */     //   698: invokevirtual d : ()I
/*      */     //   701: iastore
/*      */     //   702: iload #10
/*      */     //   704: iflt -> 720
/*      */     //   707: aload #8
/*      */     //   709: iload #9
/*      */     //   711: dup2
/*      */     //   712: iaload
/*      */     //   713: aload #8
/*      */     //   715: iload #10
/*      */     //   717: iaload
/*      */     //   718: iadd
/*      */     //   719: iastore
/*      */     //   720: iload_2
/*      */     //   721: ifeq -> 732
/*      */     //   724: aload_1
/*      */     //   725: aload #8
/*      */     //   727: iload #9
/*      */     //   729: invokevirtual a : ([II)V
/*      */     //   732: iload #25
/*      */     //   734: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #2392	-> 0
/*      */     //   #2411	-> 3
/*      */     //   #2412	-> 9
/*      */     //   #2413	-> 17
/*      */     //   #2414	-> 30
/*      */     //   #2415	-> 41
/*      */     //   #2416	-> 46
/*      */     //   #2417	-> 58
/*      */     //   #2418	-> 70
/*      */     //   #2421	-> 73
/*      */     //   #2422	-> 79
/*      */     //   #2423	-> 93
/*      */     //   #2426	-> 106
/*      */     //   #2427	-> 112
/*      */     //   #2428	-> 118
/*      */     //   #2429	-> 129
/*      */     //   #2431	-> 151
/*      */     //   #2433	-> 160
/*      */     //   #2435	-> 170
/*      */     //   #2436	-> 174
/*      */     //   #2439	-> 181
/*      */     //   #2440	-> 196
/*      */     //   #2442	-> 200
/*      */     //   #2445	-> 210
/*      */     //   #2446	-> 225
/*      */     //   #2448	-> 243
/*      */     //   #2450	-> 251
/*      */     //   #2451	-> 264
/*      */     //   #2453	-> 277
/*      */     //   #2454	-> 283
/*      */     //   #2455	-> 290
/*      */     //   #2458	-> 293
/*      */     //   #2460	-> 303
/*      */     //   #2462	-> 310
/*      */     //   #2463	-> 325
/*      */     //   #2465	-> 346
/*      */     //   #2467	-> 353
/*      */     //   #2468	-> 366
/*      */     //   #2470	-> 379
/*      */     //   #2473	-> 386
/*      */     //   #2474	-> 395
/*      */     //   #2475	-> 402
/*      */     //   #2478	-> 409
/*      */     //   #2479	-> 424
/*      */     //   #2481	-> 433
/*      */     //   #2484	-> 443
/*      */     //   #2485	-> 458
/*      */     //   #2487	-> 476
/*      */     //   #2489	-> 484
/*      */     //   #2490	-> 497
/*      */     //   #2492	-> 510
/*      */     //   #2493	-> 516
/*      */     //   #2494	-> 523
/*      */     //   #2497	-> 526
/*      */     //   #2499	-> 539
/*      */     //   #2501	-> 546
/*      */     //   #2502	-> 561
/*      */     //   #2504	-> 582
/*      */     //   #2506	-> 589
/*      */     //   #2507	-> 602
/*      */     //   #2509	-> 615
/*      */     //   #2433	-> 622
/*      */     //   #2513	-> 631
/*      */     //   #2428	-> 646
/*      */     //   #2517	-> 666
/*      */     //   #2518	-> 673
/*      */     //   #2522	-> 677
/*      */     //   #2523	-> 681
/*      */     //   #2526	-> 693
/*      */     //   #2529	-> 702
/*      */     //   #2530	-> 707
/*      */     //   #2533	-> 720
/*      */     //   #2534	-> 724
/*      */     //   #2538	-> 732
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	735	0	srcblk	Lc/a/j/a/g;
/*      */     //   0	735	1	mq	Lc/a/c/b/i;
/*      */     //   0	735	2	doterm	Z
/*      */     //   0	735	3	bp	I
/*      */     //   0	735	4	state	[I
/*      */     //   0	735	5	fm	[I
/*      */     //   0	735	6	symbuf	[I
/*      */     //   0	735	7	ctxtbuf	[I
/*      */     //   0	735	8	ratebuf	[I
/*      */     //   0	735	9	pidx	I
/*      */     //   0	735	10	ltpidx	I
/*      */     //   0	735	11	options	I
/*      */     //   3	732	16	nsym	I
/*      */     //   9	726	17	dscanw	I
/*      */     //   17	718	18	sscanw	I
/*      */     //   30	705	19	jstep	I
/*      */     //   41	694	20	kstep	I
/*      */     //   46	689	23	mask	I
/*      */     //   58	677	24	data	[I
/*      */     //   70	665	31	nstripes	I
/*      */     //   73	662	25	dist	I
/*      */     //   79	656	26	shift	I
/*      */     //   93	642	27	upshift	I
/*      */     //   106	629	28	downshift	I
/*      */     //   112	623	15	sk	I
/*      */     //   118	617	13	sj	I
/*      */     //   124	611	30	s	I
/*      */     //   151	512	32	sheight	I
/*      */     //   160	503	21	stopsk	I
/*      */     //   174	454	12	j	I
/*      */     //   181	447	22	csj	I
/*      */     //   200	186	14	k	I
/*      */     //   264	13	29	normval	I
/*      */     //   366	13	29	normval	I
/*      */     //   433	189	14	k	I
/*      */     //   497	13	29	normval	I
/*      */     //   602	13	29	normval	I
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int b(g srcblk, a bout, boolean doterm, int bp, int[] state, int[] fm, int[] ratebuf, int pidx, int ltpidx, int options) {
/* 2605 */     int nsym = 0;
/*      */ 
/*      */     
/* 2608 */     int dscanw = srcblk.i;
/* 2609 */     int sscanw = srcblk.f + 2;
/* 2610 */     int jstep = sscanw * 4 / 2 - srcblk.f;
/* 2611 */     int kstep = dscanw * 4 - srcblk.f;
/* 2612 */     int mask = 1 << bp;
/* 2613 */     int[] data = (int[])srcblk.b();
/* 2614 */     int nstripes = (srcblk.g + 4 - 1) / 4;
/* 2615 */     int dist = 0;
/*      */ 
/*      */     
/* 2618 */     int shift = bp - 6;
/* 2619 */     int upshift = (shift >= 0) ? 0 : -shift;
/* 2620 */     int downshift = (shift <= 0) ? 0 : shift;
/*      */ 
/*      */     
/* 2623 */     int sk = srcblk.h;
/* 2624 */     int sj = sscanw + 1;
/* 2625 */     for (int s = nstripes - 1; s >= 0; s--, sk += kstep, sj += jstep) {
/* 2626 */       int sheight = (s != 0) ? 4 : (srcblk.g - (nstripes - 1) * 4);
/*      */       
/* 2628 */       int stopsk = sk + srcblk.f;
/*      */       
/* 2630 */       for (; sk < stopsk; sk++, sj++) {
/*      */         
/* 2632 */         int j = sj;
/* 2633 */         int csj = state[j];
/*      */ 
/*      */         
/* 2636 */         if ((csj >>> 1 & (csj ^ 0xFFFFFFFF) & 0x40004000) != 0) {
/* 2637 */           int m = sk;
/*      */           
/* 2639 */           if ((csj & 0xC000) == 32768) {
/*      */ 
/*      */             
/* 2642 */             bout.a((data[m] & mask) >>> bp);
/* 2643 */             nsym++;
/*      */ 
/*      */ 
/*      */             
/* 2647 */             int normval = data[m] >> downshift << upshift;
/* 2648 */             dist += fm[normval & 0x7F];
/*      */           } 
/* 2650 */           if (sheight < 2)
/*      */             continue; 
/* 2652 */           if ((csj & 0xC0000000) == Integer.MIN_VALUE) {
/*      */             
/* 2654 */             m += dscanw;
/*      */             
/* 2656 */             bout.a((data[m] & mask) >>> bp);
/* 2657 */             nsym++;
/*      */ 
/*      */ 
/*      */             
/* 2661 */             int normval = data[m] >> downshift << upshift;
/* 2662 */             dist += fm[normval & 0x7F];
/*      */           } 
/*      */         } 
/*      */         
/* 2666 */         if (sheight >= 3) {
/* 2667 */           j += sscanw;
/* 2668 */           csj = state[j];
/*      */ 
/*      */           
/* 2671 */           if ((csj >>> 1 & (csj ^ 0xFFFFFFFF) & 0x40004000) != 0) {
/* 2672 */             int m = sk + (dscanw << 1);
/*      */             
/* 2674 */             if ((csj & 0xC000) == 32768) {
/*      */ 
/*      */               
/* 2677 */               bout.a((data[m] & mask) >>> bp);
/* 2678 */               nsym++;
/*      */ 
/*      */ 
/*      */               
/* 2682 */               int normval = data[m] >> downshift << upshift;
/* 2683 */               dist += fm[normval & 0x7F];
/*      */             } 
/* 2685 */             if (sheight >= 4)
/*      */             {
/* 2687 */               if ((state[j] & 0xC0000000) == Integer.MIN_VALUE) {
/*      */                 
/* 2689 */                 m += dscanw;
/*      */                 
/* 2691 */                 bout.a((data[m] & mask) >>> bp);
/* 2692 */                 nsym++;
/*      */ 
/*      */ 
/*      */                 
/* 2696 */                 int normval = data[m] >> downshift << upshift;
/* 2697 */                 dist += fm[normval & 0x7F];
/*      */               }  } 
/*      */           } 
/*      */         } 
/*      */         continue;
/*      */       } 
/*      */     } 
/* 2704 */     if (doterm) {
/* 2705 */       ratebuf[pidx] = bout.b();
/*      */     } else {
/* 2707 */       ratebuf[pidx] = bout.d();
/*      */     } 
/*      */ 
/*      */     
/* 2711 */     if (ltpidx >= 0) {
/* 2712 */       ratebuf[pidx] = ratebuf[pidx] + ratebuf[ltpidx];
/*      */     }
/*      */ 
/*      */     
/* 2716 */     return dist;
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
/*      */   private static int b(g srcblk, i mq, boolean doterm, int bp, int[] state, int[] fs, int[] zc_lut, int[] symbuf, int[] ctxtbuf, int[] ratebuf, int pidx, int ltpidx, int options) {
/* 2772 */     int nsym = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2797 */     int dscanw = srcblk.i;
/* 2798 */     int sscanw = srcblk.f + 2;
/* 2799 */     int jstep = sscanw * 4 / 2 - srcblk.f;
/* 2800 */     int kstep = dscanw * 4 - srcblk.f;
/* 2801 */     int mask = 1 << bp;
/* 2802 */     int[] data = (int[])srcblk.b();
/* 2803 */     int nstripes = (srcblk.g + 4 - 1) / 4;
/* 2804 */     int dist = 0;
/*      */ 
/*      */     
/* 2807 */     int shift = bp - 6;
/* 2808 */     int upshift = (shift >= 0) ? 0 : -shift;
/* 2809 */     int downshift = (shift <= 0) ? 0 : shift;
/* 2810 */     boolean causal = ((options & 0x8) != 0);
/*      */ 
/*      */     
/* 2813 */     int off_ul = -sscanw - 1;
/* 2814 */     int off_ur = -sscanw + 1;
/* 2815 */     int off_dr = sscanw + 1;
/* 2816 */     int off_dl = sscanw - 1;
/*      */ 
/*      */     
/* 2819 */     int sk = srcblk.h;
/* 2820 */     int sj = sscanw + 1;
/* 2821 */     for (int s = nstripes - 1; s >= 0; s--, sk += kstep, sj += jstep) {
/* 2822 */       int sheight = (s != 0) ? 4 : (srcblk.g - (nstripes - 1) * 4);
/*      */       
/* 2824 */       int stopsk = sk + srcblk.f;
/*      */       
/* 2826 */       nsym = 0; while (true) { sk++; sj++; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3249 */       if (nsym > 0) mq.a(symbuf, ctxtbuf, nsym);
/*      */       
/*      */       continue;
/*      */     } 
/* 3253 */     if ((options & 0x20) != 0) {
/* 3254 */       mq.a(ae, af, ae.length);
/*      */     }
/*      */ 
/*      */     
/* 3258 */     if ((options & 0x2) != 0) {
/* 3259 */       mq.c();
/*      */     }
/*      */ 
/*      */     
/* 3263 */     if (doterm) {
/* 3264 */       ratebuf[pidx] = mq.a();
/*      */     } else {
/*      */       
/* 3267 */       ratebuf[pidx] = mq.d();
/*      */     } 
/*      */     
/* 3270 */     if (ltpidx >= 0) {
/* 3271 */       ratebuf[pidx] = ratebuf[pidx] + ratebuf[ltpidx];
/*      */     }
/*      */     
/* 3274 */     if (doterm) {
/* 3275 */       mq.a(ratebuf, pidx);
/*      */     }
/*      */ 
/*      */     
/* 3279 */     return dist;
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
/*      */   private static void a(byte[] data, int[] rates, boolean[] isterm, int n) {
/* 3323 */     if (isterm == null) {
/* 3324 */       for (; --n >= 0; n--) {
/* 3325 */         int dp = rates[n] - 1;
/* 3326 */         if (dp >= 0 && data[dp] == -1) {
/* 3327 */           rates[n] = rates[n] - 1;
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 3332 */       for (; --n >= 0; n--) {
/* 3333 */         if (!isterm[n]) {
/* 3334 */           int dp = rates[n] - 1;
/* 3335 */           if (dp >= 0 && data[dp] == -1) {
/* 3336 */             rates[n] = rates[n] - 1;
/*      */           }
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
/*      */   public void f(int nt, int nc) {
/* 3353 */     this.M = new int[nt][nc];
/* 3354 */     this.N = new int[nt][nc];
/* 3355 */     this.O = new int[nt][nc];
/*      */     
/* 3357 */     for (int t = 0; t < nt; t++) {
/* 3358 */       for (int j = 0; j < nc; j++) {
/* 3359 */         this.M[t][j] = 0;
/*      */ 
/*      */         
/* 3362 */         if (((String)this.f.a(t, j))
/* 3363 */           .equalsIgnoreCase("true")) {
/* 3364 */           this.M[t][j] = this.M[t][j] | 0x1;
/*      */         }
/*      */         
/* 3367 */         if (((String)this.g.a(t, j))
/* 3368 */           .equalsIgnoreCase("true")) {
/* 3369 */           this.M[t][j] = this.M[t][j] | 0x2;
/*      */         }
/*      */         
/* 3372 */         if (((String)this.h.a(t, j))
/* 3373 */           .equalsIgnoreCase("true")) {
/* 3374 */           this.M[t][j] = this.M[t][j] | 0x4;
/*      */         }
/*      */         
/* 3377 */         if (((String)this.i.a(t, j))
/* 3378 */           .equalsIgnoreCase("true")) {
/* 3379 */           this.M[t][j] = this.M[t][j] | 0x8;
/*      */         }
/*      */         
/* 3382 */         if (((String)this.j.a(t, j))
/* 3383 */           .equalsIgnoreCase("true")) {
/* 3384 */           this.M[t][j] = this.M[t][j] | 0x20;
/*      */         }
/*      */ 
/*      */         
/* 3388 */         String lCalcType = (String)this.y.a(t, j);
/* 3389 */         if (lCalcType.equals("near_opt")) {
/* 3390 */           this.N[t][j] = 2;
/*      */         }
/* 3392 */         else if (lCalcType.equals("lazy_good")) {
/* 3393 */           this.N[t][j] = 1;
/*      */         }
/* 3395 */         else if (lCalcType.equals("lazy")) {
/* 3396 */           this.N[t][j] = 0;
/*      */         } else {
/*      */           
/* 3399 */           throw new IllegalArgumentException("Unrecognized or unsupported MQ length calculation.");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3405 */         String termType = (String)this.z.a(t, j);
/* 3406 */         if (termType.equalsIgnoreCase("easy")) {
/* 3407 */           this.O[t][j] = 2;
/*      */         }
/* 3409 */         else if (termType.equalsIgnoreCase("full")) {
/* 3410 */           this.O[t][j] = 0;
/*      */         }
/* 3412 */         else if (termType.equalsIgnoreCase("near_opt")) {
/* 3413 */           this.O[t][j] = 1;
/*      */         }
/* 3415 */         else if (termType.equalsIgnoreCase("predict")) {
/* 3416 */           this.O[t][j] = 3;
/* 3417 */           this.M[t][j] = this.M[t][j] | 0x10;
/* 3418 */           if ((this.M[t][j] & 0x5) == 0) {
/* 3419 */             c.b()
/* 3420 */               .printmsg(1, "Using error resilient MQ termination, but terminating only at the end of code-blocks. The error protection offered by this option will be very weak. Specify the 'Creg_term' and/or 'Cbypass' option for increased error resilience.");
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 3430 */           throw new IllegalArgumentException("Unrecognized or unsupported MQ coder termination.");
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
/*      */   public int a(int t, int j, int rl) {
/* 3453 */     return this.L.a(t, j, rl);
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
/*      */   public int b(int t, int j, int rl) {
/* 3470 */     return this.L.b(t, j, rl);
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
/*      */   public boolean a(int j, int t) {
/* 3485 */     return this.bi[j][t];
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/k.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */