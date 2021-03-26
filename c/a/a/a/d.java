/*      */ package c.a.a.a;
/*      */ 
/*      */ import c.a.a.c;
/*      */ import c.a.a.e;
/*      */ import c.a.a.h;
/*      */ import c.a.b.a;
/*      */ import c.a.c.a.c;
/*      */ import c.a.c.a.e;
/*      */ import c.a.c.a.g;
/*      */ import c.a.c.f;
/*      */ import c.a.f;
/*      */ import c.a.f.f;
/*      */ import c.a.g.a.a;
/*      */ import c.a.g.a.b;
/*      */ import c.a.g.a.e;
/*      */ import c.a.h.a;
/*      */ import c.a.h.b;
/*      */ import c.a.i.c;
/*      */ import c.a.j.b.j;
/*      */ import c.a.j.b.l;
/*      */ import c.a.j.b.n;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageReadParamJava;
/*      */ import java.awt.Point;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class d
/*      */   implements e, h, f
/*      */ {
/*      */   public static final char a = 'H';
/*  130 */   private static final String[][] j = (String[][])null;
/*      */ 
/*      */ 
/*      */   
/*      */   private c.a.a.d ae;
/*      */ 
/*      */   
/*  137 */   private String af = "";
/*      */ 
/*      */ 
/*      */   
/*      */   private J2KImageReadParamJava ag;
/*      */ 
/*      */ 
/*      */   
/*      */   private int ah;
/*      */ 
/*      */   
/*      */   public int[] b;
/*      */ 
/*      */   
/*  151 */   private int ai = 0;
/*      */ 
/*      */   
/*  154 */   private int aj = 0;
/*      */ 
/*      */   
/*  157 */   private int ak = 0;
/*      */ 
/*      */   
/*  160 */   private int al = 0;
/*      */ 
/*      */   
/*  163 */   private int am = 0;
/*      */ 
/*      */   
/*  166 */   private int an = 0;
/*      */ 
/*      */   
/*  169 */   private int[][] ao = (int[][])null;
/*      */ 
/*      */   
/*      */   private static final int ap = 1;
/*      */ 
/*      */   
/*      */   private static final int aq = 2;
/*      */ 
/*      */   
/*      */   private static final int ar = 4;
/*      */ 
/*      */   
/*      */   private static final int as = 8;
/*      */ 
/*      */   
/*      */   private static final int at = 16;
/*      */ 
/*      */   
/*      */   private static final int au = 32;
/*      */ 
/*      */   
/*      */   private static final int av = 64;
/*      */ 
/*      */   
/*      */   private static final int aw = 128;
/*      */ 
/*      */   
/*      */   private static final int ax = 256;
/*      */ 
/*      */   
/*      */   private static final int ay = 512;
/*      */ 
/*      */   
/*      */   private static final int az = 1024;
/*      */ 
/*      */   
/*      */   private static final int aA = 2048;
/*      */ 
/*      */   
/*      */   public static final int c = 8192;
/*      */ 
/*      */   
/*      */   public static final int d = 16384;
/*      */ 
/*      */   
/*      */   public static final int e = 32768;
/*      */ 
/*      */   
/*      */   public static final int f = 65536;
/*      */ 
/*      */   
/*      */   private static final int aB = -546;
/*      */ 
/*      */   
/*  223 */   private Hashtable aC = null;
/*      */ 
/*      */   
/*      */   private int aD;
/*      */ 
/*      */   
/*  229 */   private int aE = -1;
/*      */ 
/*      */   
/*  232 */   private int aF = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   private a aG;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean g;
/*      */ 
/*      */   
/*      */   public int h;
/*      */ 
/*      */   
/*      */   public Vector i;
/*      */ 
/*      */   
/*      */   private byte[][] aH;
/*      */ 
/*      */   
/*      */   private byte[][][][] aI;
/*      */ 
/*      */   
/*      */   private ByteArrayOutputStream[] aJ;
/*      */ 
/*      */ 
/*      */   
/*      */   public int a() {
/*  260 */     return this.ae.a.b();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int b() {
/*  267 */     return this.ae.a.a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int c() {
/*  274 */     return this.ae.a.c - this.ae.a.e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int d() {
/*  281 */     return this.ae.a.d - this.ae.a.f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int e() {
/*  289 */     return this.ae.a.e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int f() {
/*  297 */     return this.ae.a.f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int g() {
/*  304 */     return this.ae.a.g;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int h() {
/*  311 */     return this.ae.a.h;
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
/*      */   public final Point a(Point co) {
/*  326 */     if (co != null) {
/*  327 */       co.x = this.ae.a.i;
/*  328 */       co.y = this.ae.a.j;
/*  329 */       return co;
/*      */     } 
/*      */     
/*  332 */     return new Point(this.ae.a.i, this.ae.a.j);
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
/*      */   public final boolean a(int c) {
/*  346 */     return this.ae.a.c(c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int b(int c) {
/*  357 */     return this.ae.a.d(c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int i() {
/*  366 */     return this.aD;
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
/*      */   public final int c(int c) {
/*  378 */     return this.ae.a.m[c];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int d(int c) {
/*  389 */     return this.ae.a.n[c];
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
/*      */   public final b a(a src, int[] rb, a decSpec2) {
/*  407 */     return (b)new c.a.g.a.d(src, rb, decSpec2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int j() {
/*  415 */     return this.aE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int k() {
/*  423 */     return this.aF;
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
/*      */   public final int a(int t, int c, int rl) {
/*  440 */     return this.aG.n.a(t, c, rl);
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
/*      */   public final int b(int t, int c, int rl) {
/*  457 */     return this.aG.n.b(t, c, rl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean l() {
/*  464 */     return this.g;
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
/*      */   private j a(DataInputStream ehs, int[] filtIdx) throws IOException {
/*  480 */     int kid = filtIdx[0] = ehs.readUnsignedByte();
/*  481 */     if (kid >= 128) {
/*  482 */       throw new f("Custom filters not supported");
/*      */     }
/*      */     
/*  485 */     switch (kid) {
/*      */       case 0:
/*  487 */         return (j)new l();
/*      */       case 1:
/*  489 */         return (j)new n();
/*      */     } 
/*  491 */     throw new c("Specified wavelet filter not JPEG 2000 part I compliant");
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
/*      */   public void a(DataInputStream ehs, String str) throws IOException {
/*  509 */     if (ehs.available() != 0) {
/*  510 */       c.b()
/*  511 */         .printmsg(2, str + " length was short, attempting to resync.");
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
/*      */   private void a(DataInputStream ehs) throws IOException {
/*  530 */     c.a.a.d.i ms = this.ae.a();
/*  531 */     this.ae.a = ms;
/*      */ 
/*      */     
/*  534 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */     
/*  537 */     ms.b = ehs.readUnsignedShort();
/*  538 */     if (ms.b > 2) {
/*  539 */       throw new Error("Codestream capabiities not JPEG 2000 - Part I compliant");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  544 */     ms.c = ehs.readInt();
/*  545 */     ms.d = ehs.readInt();
/*  546 */     if (ms.c <= 0 || ms.d <= 0) {
/*  547 */       throw new IOException("JJ2000 does not support images whose width and/or height not in the range: 1 -- (2^31)-1");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  553 */     ms.e = ehs.readInt();
/*  554 */     ms.f = ehs.readInt();
/*  555 */     if (ms.e < 0 || ms.f < 0) {
/*  556 */       throw new IOException("JJ2000 does not support images offset not in the range: 0 -- (2^31)-1");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  561 */     ms.g = ehs.readInt();
/*  562 */     ms.h = ehs.readInt();
/*  563 */     if (ms.g <= 0 || ms.h <= 0) {
/*  564 */       throw new IOException("JJ2000 does not support tiles whose width and/or height are not in  the range: 1 -- (2^31)-1");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  570 */     ms.i = ehs.readInt();
/*  571 */     ms.j = ehs.readInt();
/*  572 */     if (ms.i < 0 || ms.j < 0) {
/*  573 */       throw new IOException("JJ2000 does not support tiles whose offset is not in  the range: 0 -- (2^31)-1");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  579 */     this.aD = ms.k = ehs.readUnsignedShort();
/*  580 */     if (this.aD < 1 || this.aD > 16384) {
/*  581 */       throw new IllegalArgumentException("Number of component out of range 1--16384: " + this.aD);
/*      */     }
/*      */ 
/*      */     
/*  585 */     ms.l = new int[this.aD];
/*  586 */     ms.m = new int[this.aD];
/*  587 */     ms.n = new int[this.aD];
/*      */ 
/*      */     
/*  590 */     for (int i = 0; i < this.aD; i++) {
/*  591 */       ms.l[i] = ehs.readUnsignedByte();
/*  592 */       ms.m[i] = ehs.readUnsignedByte();
/*  593 */       ms.n[i] = ehs.readUnsignedByte();
/*      */     } 
/*      */ 
/*      */     
/*  597 */     a(ehs, "SIZ marker");
/*      */ 
/*      */     
/*  600 */     this.ah = ms.c();
/*      */ 
/*      */     
/*  603 */     this.aG = new a(this.ah, this.aD);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void b(DataInputStream ehs) throws IOException {
/*  614 */     c.a.a.d.d ms = this.ae.i();
/*  615 */     this.ae.i = ms;
/*      */     
/*  617 */     ms.a = ehs.readUnsignedShort();
/*  618 */     ms.b = new int[this.aD];
/*  619 */     ms.c = new int[this.aD];
/*      */     
/*  621 */     c.b()
/*  622 */       .printmsg(2, "Information in CRG marker segment not taken into account. This may affect the display of the decoded image.");
/*      */ 
/*      */     
/*  625 */     for (int c = 0; c < this.aD; c++) {
/*  626 */       ms.b[c] = ehs.readUnsignedShort();
/*  627 */       ms.c[c] = ehs.readUnsignedShort();
/*      */     } 
/*      */ 
/*      */     
/*  631 */     a(ehs, "CRG marker");
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
/*      */   private void a(DataInputStream ehs, boolean mainh, int tileIdx, int comIdx) throws IOException {
/*      */     int i;
/*  656 */     c.a.a.d.c ms = this.ae.j();
/*      */ 
/*      */     
/*  659 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */     
/*  662 */     ms.b = ehs.readUnsignedShort();
/*  663 */     switch (ms.b) {
/*      */       case 1:
/*  665 */         ms.c = new byte[ms.a - 4];
/*  666 */         for (i = 0; i < ms.a - 4; i++) {
/*  667 */           ms.c[i] = ehs.readByte();
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/*  673 */         c.b()
/*  674 */           .printmsg(2, "COM marker registered as 0x" + 
/*      */             
/*  676 */             Integer.toHexString(ms.b) + " unknown, ignoring (this might crash the " + "decoder or decode a quality degraded or even " + "useless image)");
/*      */ 
/*      */ 
/*      */         
/*  680 */         ehs.skipBytes(ms.a - 4);
/*      */         break;
/*      */     } 
/*      */     
/*  684 */     if (mainh) {
/*  685 */       this.ae.j.put("main_" + comIdx, ms);
/*      */     } else {
/*  687 */       this.ae.j.put("t" + tileIdx + "_" + comIdx, ms);
/*      */     } 
/*      */ 
/*      */     
/*  691 */     a(ehs, "COM marker");
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
/*      */   private void b(DataInputStream ehs, boolean mainh, int tileIdx, int tpIdx) throws IOException {
/*  716 */     float[][] nStep = (float[][])null;
/*  717 */     c.a.a.d.g ms = this.ae.f();
/*      */ 
/*      */     
/*  720 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */     
/*  723 */     ms.b = ehs.readUnsignedByte();
/*      */     
/*  725 */     int guardBits = ms.b();
/*  726 */     int qType = ms.a();
/*      */     
/*  728 */     if (mainh) {
/*  729 */       this.ae.f.put("main", ms);
/*      */ 
/*      */       
/*  732 */       switch (qType) {
/*      */         case 0:
/*  734 */           this.aG.c.a("reversible");
/*      */           break;
/*      */         case 1:
/*  737 */           this.aG.c.a("derived");
/*      */           break;
/*      */         case 2:
/*  740 */           this.aG.c.a("expounded");
/*      */           break;
/*      */         default:
/*  743 */           throw new c("Unknown or unsupported quantization style in Sqcd field, QCD marker main header");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } else {
/*  750 */       this.ae.f.put("t" + tileIdx, ms);
/*      */ 
/*      */       
/*  753 */       switch (qType) {
/*      */         case 0:
/*  755 */           this.aG.c.b(tileIdx, "reversible");
/*      */           break;
/*      */         case 1:
/*  758 */           this.aG.c.b(tileIdx, "derived");
/*      */           break;
/*      */         case 2:
/*  761 */           this.aG.c.b(tileIdx, "expounded");
/*      */           break;
/*      */         default:
/*  764 */           throw new c("Unknown or unsupported quantization style in Sqcd field, QCD marker, tile header");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  772 */     e qParms = new e();
/*      */     
/*  774 */     if (qType == 0) {
/*      */ 
/*      */ 
/*      */       
/*  778 */       int maxrl = mainh ? ((Integer)this.aG.g.d()).intValue() : ((Integer)this.aG.g.f(tileIdx)).intValue();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  783 */       int[][] exp = qParms.a = new int[maxrl + 1][];
/*  784 */       ms.c = new int[maxrl + 1][4];
/*      */       
/*  786 */       for (int rl = 0; rl <= maxrl; rl++) {
/*      */         int minb, maxb;
/*  788 */         if (rl == 0) {
/*  789 */           minb = 0;
/*  790 */           maxb = 1;
/*      */         } else {
/*      */           
/*  793 */           int hpd = 1;
/*      */ 
/*      */           
/*  796 */           if (hpd > maxrl - rl) {
/*  797 */             hpd -= maxrl - rl;
/*      */           } else {
/*      */             
/*  800 */             hpd = 1;
/*      */           } 
/*      */           
/*  803 */           minb = 1 << hpd - 1 << 1;
/*  804 */           maxb = 1 << hpd << 1;
/*      */         } 
/*      */         
/*  807 */         exp[rl] = new int[maxb];
/*      */         
/*  809 */         for (int j = minb; j < maxb; j++) {
/*  810 */           int tmp = ms.c[rl][j] = ehs.readUnsignedByte();
/*  811 */           exp[rl][j] = tmp >> 3 & 0x1F;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  818 */       int maxrl = (qType == 1) ? 0 : (mainh ? ((Integer)this.aG.g.d()).intValue() : ((Integer)this.aG.g.f(tileIdx)).intValue());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  823 */       int[][] exp = qParms.a = new int[maxrl + 1][];
/*  824 */       nStep = qParms.b = new float[maxrl + 1][];
/*  825 */       ms.c = new int[maxrl + 1][4];
/*      */       
/*  827 */       for (int rl = 0; rl <= maxrl; rl++) {
/*      */         int minb, maxb;
/*  829 */         if (rl == 0) {
/*  830 */           minb = 0;
/*  831 */           maxb = 1;
/*      */         } else {
/*      */           
/*  834 */           int hpd = 1;
/*      */ 
/*      */           
/*  837 */           if (hpd > maxrl - rl) {
/*  838 */             hpd -= maxrl - rl;
/*      */           } else {
/*  840 */             hpd = 1;
/*      */           } 
/*      */           
/*  843 */           minb = 1 << hpd - 1 << 1;
/*  844 */           maxb = 1 << hpd << 1;
/*      */         } 
/*      */         
/*  847 */         exp[rl] = new int[maxb];
/*  848 */         nStep[rl] = new float[maxb];
/*      */         
/*  850 */         for (int j = minb; j < maxb; j++) {
/*  851 */           int tmp = ms.c[rl][j] = ehs.readUnsignedShort();
/*  852 */           exp[rl][j] = tmp >> 11 & 0x1F;
/*      */ 
/*      */ 
/*      */           
/*  856 */           nStep[rl][j] = (-1.0F - (tmp & 0x7FF) / 2048.0F) / (-1 << exp[rl][j]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  864 */     if (mainh) {
/*  865 */       this.aG.d.a(qParms);
/*  866 */       this.aG.e.a(new Integer(guardBits));
/*      */     } else {
/*      */       
/*  869 */       this.aG.d.b(tileIdx, qParms);
/*  870 */       this.aG.e.b(tileIdx, new Integer(guardBits));
/*      */     } 
/*      */ 
/*      */     
/*  874 */     a(ehs, "QCD marker");
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
/*      */   private void c(DataInputStream ehs, boolean mainh, int tileIdx, int tpIdx) throws IOException {
/*      */     int cComp;
/*  900 */     float[][] nStepC = (float[][])null;
/*  901 */     c.a.a.d.f ms = this.ae.g();
/*      */ 
/*      */     
/*  904 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */     
/*  907 */     if (this.aD < 257) {
/*  908 */       cComp = ms.b = ehs.readUnsignedByte();
/*      */     } else {
/*  910 */       cComp = ms.b = ehs.readUnsignedShort();
/*      */     } 
/*  912 */     if (cComp >= this.aD) {
/*  913 */       throw new c("Invalid component index in QCC marker");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  918 */     ms.c = ehs.readUnsignedByte();
/*  919 */     int guardBits = ms.b();
/*  920 */     int qType = ms.a();
/*      */     
/*  922 */     if (mainh) {
/*  923 */       this.ae.g.put("main_c" + cComp, ms);
/*      */ 
/*      */       
/*  926 */       switch (qType) {
/*      */         case 0:
/*  928 */           this.aG.c.a(cComp, "reversible");
/*      */           break;
/*      */         case 1:
/*  931 */           this.aG.c.a(cComp, "derived");
/*      */           break;
/*      */         case 2:
/*  934 */           this.aG.c.a(cComp, "expounded");
/*      */           break;
/*      */         default:
/*  937 */           throw new c("Unknown or unsupported quantization style in Sqcd field, QCD marker, main header");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } else {
/*  944 */       this.ae.g.put("t" + tileIdx + "_c" + cComp, ms);
/*      */ 
/*      */       
/*  947 */       switch (qType) {
/*      */         case 0:
/*  949 */           this.aG.c.a(tileIdx, cComp, "reversible");
/*      */           break;
/*      */         case 1:
/*  952 */           this.aG.c.a(tileIdx, cComp, "derived");
/*      */           break;
/*      */         case 2:
/*  955 */           this.aG.c.a(tileIdx, cComp, "expounded");
/*      */           break;
/*      */         default:
/*  958 */           throw new c("Unknown or unsupported quantization style in Sqcd field, QCD marker, main header");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  967 */     e qParms = new e();
/*      */     
/*  969 */     if (qType == 0) {
/*      */ 
/*      */ 
/*      */       
/*  973 */       int maxrl = mainh ? ((Integer)this.aG.g.e(cComp)).intValue() : ((Integer)this.aG.g.a(tileIdx, cComp)).intValue();
/*      */ 
/*      */ 
/*      */       
/*  977 */       int[][] expC = qParms.a = new int[maxrl + 1][];
/*  978 */       ms.d = new int[maxrl + 1][4];
/*      */       
/*  980 */       for (int rl = 0; rl <= maxrl; rl++) {
/*      */         int minb, maxb;
/*  982 */         if (rl == 0) {
/*  983 */           minb = 0;
/*  984 */           maxb = 1;
/*      */         } else {
/*      */           
/*  987 */           int hpd = 1;
/*      */ 
/*      */           
/*  990 */           if (hpd > maxrl - rl) {
/*  991 */             hpd -= maxrl - rl;
/*      */           } else {
/*  993 */             hpd = 1;
/*      */           } 
/*      */           
/*  996 */           minb = 1 << hpd - 1 << 1;
/*  997 */           maxb = 1 << hpd << 1;
/*      */         } 
/*      */         
/* 1000 */         expC[rl] = new int[maxb];
/*      */         
/* 1002 */         for (int j = minb; j < maxb; j++) {
/* 1003 */           int tmp = ms.d[rl][j] = ehs.readUnsignedByte();
/* 1004 */           expC[rl][j] = tmp >> 3 & 0x1F;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1012 */       int maxrl = (qType == 1) ? 0 : (mainh ? ((Integer)this.aG.g.e(cComp)).intValue() : ((Integer)this.aG.g.a(tileIdx, cComp)).intValue());
/*      */ 
/*      */ 
/*      */       
/* 1016 */       nStepC = qParms.b = new float[maxrl + 1][];
/* 1017 */       int[][] expC = qParms.a = new int[maxrl + 1][];
/* 1018 */       ms.d = new int[maxrl + 1][4];
/*      */       
/* 1020 */       for (int rl = 0; rl <= maxrl; rl++) {
/*      */         int minb, maxb;
/* 1022 */         if (rl == 0) {
/* 1023 */           minb = 0;
/* 1024 */           maxb = 1;
/*      */         } else {
/*      */           
/* 1027 */           int hpd = 1;
/*      */ 
/*      */           
/* 1030 */           if (hpd > maxrl - rl) {
/* 1031 */             hpd -= maxrl - rl;
/*      */           } else {
/* 1033 */             hpd = 1;
/*      */           } 
/*      */           
/* 1036 */           minb = 1 << hpd - 1 << 1;
/* 1037 */           maxb = 1 << hpd << 1;
/*      */         } 
/*      */         
/* 1040 */         expC[rl] = new int[maxb];
/* 1041 */         nStepC[rl] = new float[maxb];
/*      */         
/* 1043 */         for (int j = minb; j < maxb; j++) {
/* 1044 */           int tmp = ms.d[rl][j] = ehs.readUnsignedShort();
/* 1045 */           expC[rl][j] = tmp >> 11 & 0x1F;
/*      */ 
/*      */ 
/*      */           
/* 1049 */           nStepC[rl][j] = (-1.0F - (tmp & 0x7FF) / 2048.0F) / (-1 << expC[rl][j]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1057 */     if (mainh) {
/* 1058 */       this.aG.d.a(cComp, qParms);
/* 1059 */       this.aG.e.a(cComp, new Integer(guardBits));
/*      */     } else {
/*      */       
/* 1062 */       this.aG.d.a(tileIdx, cComp, qParms);
/* 1063 */       this.aG.e.a(tileIdx, cComp, new Integer(guardBits));
/*      */     } 
/*      */ 
/*      */     
/* 1067 */     a(ehs, "QCC marker");
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
/*      */   private void d(DataInputStream ehs, boolean mainh, int tileIdx, int tpIdx) throws IOException {
/* 1093 */     boolean sopUsed = false;
/* 1094 */     boolean ephUsed = false;
/* 1095 */     c.a.a.d.b ms = this.ae.c();
/*      */ 
/*      */     
/* 1098 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */ 
/*      */     
/* 1102 */     int cstyle = ms.b = ehs.readUnsignedByte();
/*      */     
/* 1104 */     if ((cstyle & 0x1) != 0) {
/* 1105 */       this.g = true;
/*      */       
/* 1107 */       cstyle &= 0xFFFFFFFE;
/*      */     } else {
/* 1109 */       this.g = false;
/*      */     } 
/*      */ 
/*      */     
/* 1113 */     if (mainh) {
/* 1114 */       this.ae.c.put("main", ms);
/*      */       
/* 1116 */       if ((cstyle & 0x2) != 0) {
/*      */         
/* 1118 */         this.aG.o.a(new Boolean("true"));
/* 1119 */         sopUsed = true;
/*      */         
/* 1121 */         cstyle &= 0xFFFFFFFD;
/*      */       } else {
/*      */         
/* 1124 */         this.aG.o.a(new Boolean("false"));
/*      */       } 
/*      */     } else {
/* 1127 */       this.ae.c.put("t" + tileIdx, ms);
/*      */       
/* 1129 */       if ((cstyle & 0x2) != 0) {
/*      */         
/* 1131 */         this.aG.o.b(tileIdx, new Boolean("true"));
/* 1132 */         sopUsed = true;
/*      */         
/* 1134 */         cstyle &= 0xFFFFFFFD;
/*      */       }
/*      */       else {
/*      */         
/* 1138 */         this.aG.o.b(tileIdx, new Boolean("false"));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1143 */     if (mainh) {
/* 1144 */       if ((cstyle & 0x4) != 0) {
/*      */         
/* 1146 */         this.aG.p.a(new Boolean("true"));
/* 1147 */         ephUsed = true;
/*      */         
/* 1149 */         cstyle &= 0xFFFFFFFB;
/*      */       } else {
/*      */         
/* 1152 */         this.aG.p.a(new Boolean("false"));
/*      */       }
/*      */     
/* 1155 */     } else if ((cstyle & 0x4) != 0) {
/*      */       
/* 1157 */       this.aG.p.b(tileIdx, new Boolean("true"));
/* 1158 */       ephUsed = true;
/*      */       
/* 1160 */       cstyle &= 0xFFFFFFFB;
/*      */     } else {
/*      */       
/* 1163 */       this.aG.p.b(tileIdx, new Boolean("false"));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1168 */     if ((cstyle & 0x18) != 0) {
/* 1169 */       c.b()
/* 1170 */         .printmsg(2, "Code-block partition origin different from (0,0). This is defined in JPEG 2000 part 2 and may not be supported by all JPEG 2000 decoders.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1175 */     if ((cstyle & 0x8) != 0) {
/* 1176 */       if (this.aE != -1 && this.aE == 0) {
/* 1177 */         throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1182 */       this.aE = 1;
/* 1183 */       cstyle &= 0xFFFFFFF7;
/*      */     } else {
/* 1185 */       if (this.aE != -1 && this.aE == 1) {
/* 1186 */         throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1191 */       this.aE = 0;
/*      */     } 
/* 1193 */     if ((cstyle & 0x10) != 0) {
/* 1194 */       if (this.aF != -1 && this.aF == 0) {
/* 1195 */         throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1200 */       this.aF = 1;
/* 1201 */       cstyle &= 0xFFFFFFEF;
/*      */     } else {
/* 1203 */       if (this.aF != -1 && this.aF == 1) {
/* 1204 */         throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1209 */       this.aF = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1214 */     ms.c = ehs.readUnsignedByte();
/*      */ 
/*      */     
/* 1217 */     ms.d = ehs.readUnsignedShort();
/* 1218 */     if (ms.d <= 0 || ms.d > 65535) {
/* 1219 */       throw new c("Number of layers out of range: 1--65535");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1224 */     ms.e = ehs.readUnsignedByte();
/*      */ 
/*      */ 
/*      */     
/* 1228 */     int mrl = ms.f = ehs.readUnsignedByte();
/* 1229 */     if (mrl > 32) {
/* 1230 */       throw new c("Number of decomposition levels out of range: 0--32");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1236 */     Integer[] cblk = new Integer[2];
/* 1237 */     ms.g = ehs.readUnsignedByte();
/* 1238 */     cblk[0] = new Integer(1 << ms.g + 2);
/* 1239 */     if (cblk[0].intValue() < 4 || cblk[0]
/* 1240 */       .intValue() > 1024) {
/* 1241 */       String errMsg = "Non-valid code-block width in SPcod field, COD marker";
/*      */       
/* 1243 */       throw new c(errMsg);
/*      */     } 
/* 1245 */     ms.h = ehs.readUnsignedByte();
/* 1246 */     cblk[1] = new Integer(1 << ms.h + 2);
/* 1247 */     if (cblk[1].intValue() < 4 || cblk[1]
/* 1248 */       .intValue() > 1024) {
/* 1249 */       String errMsg = "Non-valid code-block height in SPcod field, COD marker";
/*      */       
/* 1251 */       throw new c(errMsg);
/*      */     } 
/* 1253 */     if (cblk[0].intValue() * cblk[1].intValue() > 4096) {
/*      */       
/* 1255 */       String errMsg = "Non-valid code-block area in SPcod field, COD marker";
/*      */       
/* 1257 */       throw new c(errMsg);
/*      */     } 
/* 1259 */     if (mainh) {
/* 1260 */       this.aG.q.a(cblk);
/*      */     } else {
/*      */       
/* 1263 */       this.aG.q.b(tileIdx, cblk);
/*      */     } 
/*      */ 
/*      */     
/* 1267 */     int ecOptions = ms.i = ehs.readUnsignedByte();
/* 1268 */     if ((ecOptions & 0xFFFFFFC0) != 0)
/*      */     {
/*      */       
/* 1271 */       throw new c("Unknown \"code-block style\" in SPcod field, COD marker: 0x" + 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1276 */           Integer.toHexString(ecOptions));
/*      */     }
/*      */ 
/*      */     
/* 1280 */     j[] hfilters = new j[1];
/* 1281 */     j[] vfilters = new j[1];
/* 1282 */     hfilters[0] = a(ehs, ms.j);
/* 1283 */     vfilters[0] = hfilters[0];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1288 */     j[][] hvfilters = new j[2][];
/* 1289 */     hvfilters[0] = hfilters;
/* 1290 */     hvfilters[1] = vfilters;
/*      */ 
/*      */     
/* 1293 */     Vector[] v = new Vector[2];
/* 1294 */     v[0] = new Vector();
/* 1295 */     v[1] = new Vector();
/* 1296 */     int val = 65535;
/* 1297 */     if (!this.g) {
/*      */       
/* 1299 */       Integer w = new Integer(1 << (val & 0xF));
/* 1300 */       v[0].addElement(w);
/* 1301 */       Integer integer1 = new Integer(1 << (val & 0xF0) >> 4);
/* 1302 */       v[1].addElement(integer1);
/*      */     } else {
/* 1304 */       ms.k = new int[mrl + 1];
/* 1305 */       for (int rl = mrl; rl >= 0; rl--) {
/*      */         
/* 1307 */         val = ms.k[mrl - rl] = ehs.readUnsignedByte();
/* 1308 */         Integer w = new Integer(1 << (val & 0xF));
/* 1309 */         v[0].insertElementAt(w, 0);
/* 1310 */         Integer integer1 = new Integer(1 << (val & 0xF0) >> 4);
/* 1311 */         v[1].insertElementAt(integer1, 0);
/*      */       } 
/*      */     } 
/* 1314 */     if (mainh) {
/* 1315 */       this.aG.n.a(v);
/*      */     } else {
/* 1317 */       this.aG.n.b(tileIdx, v);
/*      */     } 
/* 1319 */     this.g = true;
/*      */ 
/*      */     
/* 1322 */     a(ehs, "COD marker");
/*      */ 
/*      */     
/* 1325 */     if (mainh) {
/* 1326 */       this.aG.f.a(hvfilters);
/* 1327 */       this.aG.g.a(new Integer(mrl));
/* 1328 */       this.aG.j.a(new Integer(ecOptions));
/* 1329 */       this.aG.k.a(new Integer(ms.e));
/* 1330 */       this.aG.h.a(new Integer(ms.d));
/* 1331 */       this.aG.i.a(new Integer(ms.c));
/*      */     } else {
/*      */       
/* 1334 */       this.aG.f.b(tileIdx, hvfilters);
/* 1335 */       this.aG.g.b(tileIdx, new Integer(mrl));
/* 1336 */       this.aG.j.b(tileIdx, new Integer(ecOptions));
/* 1337 */       this.aG.k.b(tileIdx, new Integer(ms.e));
/* 1338 */       this.aG.h.b(tileIdx, new Integer(ms.d));
/* 1339 */       this.aG.i.b(tileIdx, new Integer(ms.c));
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
/*      */   private void e(DataInputStream ehs, boolean mainh, int tileIdx, int tpIdx) throws IOException {
/*      */     int cComp;
/* 1367 */     c.a.a.d.a ms = this.ae.d();
/*      */ 
/*      */     
/* 1370 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */     
/* 1373 */     if (this.aD < 257) {
/* 1374 */       cComp = ms.b = ehs.readUnsignedByte();
/*      */     } else {
/* 1376 */       cComp = ms.b = ehs.readUnsignedShort();
/*      */     } 
/* 1378 */     if (cComp >= this.aD) {
/* 1379 */       throw new c("Invalid component index in QCC marker");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1384 */     int cstyle = ms.c = ehs.readUnsignedByte();
/* 1385 */     if ((cstyle & 0x1) != 0) {
/* 1386 */       this.g = true;
/*      */       
/* 1388 */       cstyle &= 0xFFFFFFFE;
/*      */     } else {
/* 1390 */       this.g = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1396 */     int mrl = ms.d = ehs.readUnsignedByte();
/*      */ 
/*      */     
/* 1399 */     Integer[] cblk = new Integer[2];
/* 1400 */     ms.e = ehs.readUnsignedByte();
/* 1401 */     cblk[0] = new Integer(1 << ms.e + 2);
/* 1402 */     if (cblk[0].intValue() < 4 || cblk[0]
/* 1403 */       .intValue() > 1024) {
/* 1404 */       String errMsg = "Non-valid code-block width in SPcod field, COC marker";
/*      */       
/* 1406 */       throw new c(errMsg);
/*      */     } 
/* 1408 */     ms.f = ehs.readUnsignedByte();
/* 1409 */     cblk[1] = new Integer(1 << ms.f + 2);
/* 1410 */     if (cblk[1].intValue() < 4 || cblk[1]
/* 1411 */       .intValue() > 1024) {
/* 1412 */       String errMsg = "Non-valid code-block height in SPcod field, COC marker";
/*      */       
/* 1414 */       throw new c(errMsg);
/*      */     } 
/* 1416 */     if (cblk[0].intValue() * cblk[1].intValue() > 4096) {
/*      */       
/* 1418 */       String errMsg = "Non-valid code-block area in SPcod field, COC marker";
/*      */       
/* 1420 */       throw new c(errMsg);
/*      */     } 
/* 1422 */     if (mainh) {
/* 1423 */       this.aG.q.a(cComp, cblk);
/*      */     } else {
/* 1425 */       this.aG.q.a(tileIdx, cComp, cblk);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1430 */     int ecOptions = ms.g = ehs.readUnsignedByte();
/* 1431 */     if ((ecOptions & 0xFFFFFFC0) != 0)
/*      */     {
/*      */       
/* 1434 */       throw new c("Unknown \"code-block context\" in SPcoc field, COC marker: 0x" + 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1439 */           Integer.toHexString(ecOptions));
/*      */     }
/*      */ 
/*      */     
/* 1443 */     j[] hfilters = new j[1];
/* 1444 */     j[] vfilters = new j[1];
/* 1445 */     hfilters[0] = a(ehs, ms.h);
/* 1446 */     vfilters[0] = hfilters[0];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1451 */     j[][] hvfilters = new j[2][];
/* 1452 */     hvfilters[0] = hfilters;
/* 1453 */     hvfilters[1] = vfilters;
/*      */ 
/*      */     
/* 1456 */     Vector[] v = new Vector[2];
/* 1457 */     v[0] = new Vector();
/* 1458 */     v[1] = new Vector();
/* 1459 */     int val = 65535;
/* 1460 */     if (!this.g) {
/*      */       
/* 1462 */       Integer w = new Integer(1 << (val & 0xF));
/* 1463 */       v[0].addElement(w);
/* 1464 */       Integer integer1 = new Integer(1 << (val & 0xF0) >> 4);
/* 1465 */       v[1].addElement(integer1);
/*      */     } else {
/* 1467 */       ms.i = new int[mrl + 1];
/* 1468 */       for (int rl = mrl; rl >= 0; rl--) {
/*      */         
/* 1470 */         val = ms.i[rl] = ehs.readUnsignedByte();
/* 1471 */         Integer w = new Integer(1 << (val & 0xF));
/* 1472 */         v[0].insertElementAt(w, 0);
/* 1473 */         Integer integer1 = new Integer(1 << (val & 0xF0) >> 4);
/* 1474 */         v[1].insertElementAt(integer1, 0);
/*      */       } 
/*      */     } 
/* 1477 */     if (mainh) {
/* 1478 */       this.aG.n.a(cComp, v);
/*      */     } else {
/* 1480 */       this.aG.n.a(tileIdx, cComp, v);
/*      */     } 
/* 1482 */     this.g = true;
/*      */ 
/*      */     
/* 1485 */     a(ehs, "COD marker");
/*      */     
/* 1487 */     if (mainh) {
/* 1488 */       this.ae.d.put("main_c" + cComp, ms);
/* 1489 */       this.aG.f.a(cComp, hvfilters);
/* 1490 */       this.aG.g.a(cComp, new Integer(mrl));
/* 1491 */       this.aG.j.a(cComp, new Integer(ecOptions));
/*      */     } else {
/* 1493 */       this.ae.d.put("t" + tileIdx + "_c" + cComp, ms);
/* 1494 */       this.aG.f.a(tileIdx, cComp, hvfilters);
/* 1495 */       this.aG.g.a(tileIdx, cComp, new Integer(mrl));
/* 1496 */       this.aG.j.a(tileIdx, cComp, new Integer(ecOptions));
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
/*      */   private void f(DataInputStream ehs, boolean mainh, int t, int tpIdx) throws IOException {
/*      */     c.a.a.d.e ms;
/*      */     int[][] change;
/* 1520 */     boolean useShort = (this.aD >= 256);
/*      */     
/* 1522 */     int nOldChg = 0;
/*      */     
/* 1524 */     if (mainh || this.ae.h.get("t" + t) == null) {
/* 1525 */       ms = this.ae.h();
/*      */     } else {
/* 1527 */       ms = (c.a.a.d.e)this.ae.h.get("t" + t);
/* 1528 */       nOldChg = ms.b.length;
/*      */     } 
/*      */ 
/*      */     
/* 1532 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1537 */     int newChg = (ms.a - 2) / (5 + (useShort ? 4 : 2));
/* 1538 */     int ntotChg = nOldChg + newChg;
/*      */ 
/*      */     
/* 1541 */     if (nOldChg != 0) {
/*      */       
/* 1543 */       change = new int[ntotChg][6];
/* 1544 */       int[] tmprspoc = new int[ntotChg];
/* 1545 */       int[] tmpcspoc = new int[ntotChg];
/* 1546 */       int[] tmplyepoc = new int[ntotChg];
/* 1547 */       int[] tmprepoc = new int[ntotChg];
/* 1548 */       int[] tmpcepoc = new int[ntotChg];
/* 1549 */       int[] tmpppoc = new int[ntotChg];
/*      */ 
/*      */       
/* 1552 */       int[][] prevChg = (int[][])this.aG.l.f(t);
/* 1553 */       for (int i = 0; i < nOldChg; i++) {
/* 1554 */         change[i] = prevChg[i];
/* 1555 */         tmprspoc[i] = ms.b[i];
/* 1556 */         tmpcspoc[i] = ms.c[i];
/* 1557 */         tmplyepoc[i] = ms.d[i];
/* 1558 */         tmprepoc[i] = ms.e[i];
/* 1559 */         tmpcepoc[i] = ms.f[i];
/* 1560 */         tmpppoc[i] = ms.g[i];
/*      */       } 
/* 1562 */       ms.b = tmprspoc;
/* 1563 */       ms.c = tmpcspoc;
/* 1564 */       ms.d = tmplyepoc;
/* 1565 */       ms.e = tmprepoc;
/* 1566 */       ms.f = tmpcepoc;
/* 1567 */       ms.g = tmpppoc;
/*      */     } else {
/* 1569 */       change = new int[newChg][6];
/* 1570 */       ms.b = new int[newChg];
/* 1571 */       ms.c = new int[newChg];
/* 1572 */       ms.d = new int[newChg];
/* 1573 */       ms.e = new int[newChg];
/* 1574 */       ms.f = new int[newChg];
/* 1575 */       ms.g = new int[newChg];
/*      */     } 
/*      */     
/* 1578 */     for (int chg = nOldChg; chg < ntotChg; chg++) {
/*      */       
/* 1580 */       ms.b[chg] = ehs.readUnsignedByte(); change[chg][0] = ehs.readUnsignedByte();
/*      */ 
/*      */       
/* 1583 */       if (useShort) {
/* 1584 */         ms.c[chg] = ehs.readUnsignedShort(); change[chg][1] = ehs.readUnsignedShort();
/*      */       } else {
/* 1586 */         ms.c[chg] = ehs.readUnsignedByte(); change[chg][1] = ehs.readUnsignedByte();
/*      */       } 
/*      */ 
/*      */       
/* 1590 */       ms.d[chg] = ehs.readUnsignedShort(); change[chg][2] = ehs.readUnsignedShort();
/* 1591 */       if (change[chg][2] < 1) {
/* 1592 */         throw new c("LYEpoc value must be greater than 1 in POC marker segment of tile " + t + ", tile-part " + tpIdx);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1598 */       ms.e[chg] = ehs.readUnsignedByte(); change[chg][3] = ehs.readUnsignedByte();
/* 1599 */       if (change[chg][3] <= change[chg][0]) {
/* 1600 */         throw new c("REpoc value must be greater than RSpoc in POC marker segment of tile " + t + ", tile-part " + tpIdx);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1606 */       if (useShort) {
/* 1607 */         ms.f[chg] = ehs.readUnsignedShort(); change[chg][4] = ehs.readUnsignedShort();
/*      */       } else {
/* 1609 */         int tmp = ms.f[chg] = ehs.readUnsignedByte();
/* 1610 */         if (tmp == 0) {
/* 1611 */           change[chg][4] = 0;
/*      */         } else {
/* 1613 */           change[chg][4] = tmp;
/*      */         } 
/*      */       } 
/* 1616 */       if (change[chg][4] <= change[chg][1]) {
/* 1617 */         throw new c("CEpoc value must be greater than CSpoc in POC marker segment of tile " + t + ", tile-part " + tpIdx);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1623 */       ms.g[chg] = ehs.readUnsignedByte(); change[chg][5] = ehs.readUnsignedByte();
/*      */     } 
/*      */ 
/*      */     
/* 1627 */     a(ehs, "POC marker");
/*      */ 
/*      */     
/* 1630 */     if (mainh) {
/* 1631 */       this.ae.h.put("main", ms);
/* 1632 */       this.aG.l.a(change);
/*      */     } else {
/* 1634 */       this.ae.h.put("t" + t, ms);
/* 1635 */       this.aG.l.b(t, change);
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
/*      */   private void c(DataInputStream ehs) throws IOException {
/* 1652 */     int length = ehs.readUnsignedShort();
/*      */     
/* 1654 */     ehs.skipBytes(length - 2);
/*      */     
/* 1656 */     c.b()
/* 1657 */       .printmsg(1, "Skipping unsupported TLM marker");
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
/*      */   private void d(DataInputStream ehs) throws IOException {
/* 1673 */     int length = ehs.readUnsignedShort();
/*      */     
/* 1675 */     ehs.skipBytes(length - 2);
/*      */     
/* 1677 */     c.b()
/* 1678 */       .printmsg(1, "Skipping unsupported PLM marker");
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
/*      */   private void e(DataInputStream ehs) throws IOException {
/* 1694 */     int length = ehs.readUnsignedShort();
/*      */     
/* 1696 */     ehs.skipBytes(length - 2);
/*      */     
/* 1698 */     c.b()
/* 1699 */       .printmsg(1, "Skipping unsupported PLT marker");
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
/*      */   private void g(DataInputStream ehs, boolean mainh, int tileIdx, int tpIdx) throws IOException {
/* 1726 */     c.a.a.d.h ms = this.ae.e();
/*      */ 
/*      */     
/* 1729 */     ms.a = ehs.readUnsignedShort();
/*      */ 
/*      */ 
/*      */     
/* 1733 */     int comp = (this.aD < 257) ? ehs.readUnsignedByte() : ehs.readUnsignedShort();
/* 1734 */     if (comp >= this.aD) {
/* 1735 */       throw new c("Invalid component index in RGN marker" + comp);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1741 */     ms.c = ehs.readUnsignedByte();
/*      */ 
/*      */     
/* 1744 */     if (ms.c != 0) {
/* 1745 */       throw new c("Unknown or unsupported Srgn parameter in ROI marker");
/*      */     }
/*      */ 
/*      */     
/* 1749 */     if (this.aG.b == null)
/*      */     {
/* 1751 */       this.aG.b = new a(this.ah, this.aD, (byte)2, "null");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1756 */     ms.d = ehs.readUnsignedByte();
/*      */     
/* 1758 */     if (mainh) {
/* 1759 */       this.ae.e.put("main_c" + comp, ms);
/* 1760 */       this.aG.b.a(comp, new Integer(ms.d));
/*      */     } else {
/* 1762 */       this.ae.e.put("t" + tileIdx + "_c" + comp, ms);
/* 1763 */       this.aG.b.a(tileIdx, comp, new Integer(ms.d));
/*      */     } 
/*      */ 
/*      */     
/* 1767 */     a(ehs, "RGN marker");
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
/*      */   private void f(DataInputStream ehs) throws IOException {
/* 1786 */     if (this.aH == null) {
/* 1787 */       this.aH = new byte[this.an][];
/* 1788 */       this.i = new Vector();
/* 1789 */       this.aG.r.a(new Boolean(true));
/*      */     } 
/*      */ 
/*      */     
/* 1793 */     int curMarkSegLen = ehs.readUnsignedShort();
/* 1794 */     int remSegLen = curMarkSegLen - 3;
/*      */ 
/*      */     
/* 1797 */     int indx = ehs.readUnsignedByte();
/*      */ 
/*      */     
/* 1800 */     this.aH[indx] = new byte[remSegLen];
/* 1801 */     ehs.read(this.aH[indx], 0, remSegLen);
/*      */ 
/*      */     
/* 1804 */     a(ehs, "PPM marker");
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
/*      */   private void a(DataInputStream ehs, int tile, int tpIdx) throws IOException {
/* 1822 */     int len = 0;
/*      */ 
/*      */     
/* 1825 */     if (this.aI == null) {
/* 1826 */       this.aI = new byte[this.ah][][][];
/*      */     }
/*      */     
/* 1829 */     if (this.aI[tile] == null) {
/* 1830 */       this.aI[tile] = new byte[this.b[tile]][][];
/*      */     }
/*      */     
/* 1833 */     if (this.aI[tile][tpIdx] == null) {
/* 1834 */       this.aI[tile][tpIdx] = new byte[this.ao[tile][tpIdx]][];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1839 */     int curMarkSegLen = ehs.readUnsignedShort();
/*      */ 
/*      */     
/* 1842 */     int indx = ehs.readUnsignedByte();
/*      */ 
/*      */     
/* 1845 */     byte[] temp = new byte[curMarkSegLen - 3];
/* 1846 */     ehs.read(temp);
/* 1847 */     this.aI[tile][tpIdx][indx] = temp;
/*      */ 
/*      */     
/* 1850 */     a(ehs, "PPT marker");
/*      */     
/* 1852 */     this.aG.r.b(tile, new Boolean(true));
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
/*      */   private void a(short marker, f ehs) throws IOException {
/* 1872 */     if (this.ai == 0)
/*      */     {
/* 1874 */       if (marker != -175) {
/* 1875 */         throw new c("First marker after SOC must be SIZ " + 
/*      */ 
/*      */ 
/*      */             
/* 1879 */             Integer.toHexString(marker));
/*      */       }
/*      */     }
/*      */     
/* 1883 */     String htKey = "";
/* 1884 */     if (this.aC == null) {
/* 1885 */       this.aC = new Hashtable<Object, Object>();
/*      */     }
/*      */     
/* 1888 */     switch (marker) {
/*      */       case -175:
/* 1890 */         if ((this.ai & 0x1) != 0) {
/* 1891 */           throw new c("More than one SIZ marker segment found in main header");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1896 */         this.ai |= 0x1;
/* 1897 */         htKey = "SIZ";
/*      */         break;
/*      */       case -109:
/* 1900 */         throw new c("SOD found in main header");
/*      */       case -39:
/* 1902 */         throw new c("EOC found in main header");
/*      */       case -112:
/* 1904 */         if ((this.ai & 0x40) != 0) {
/* 1905 */           throw new c("More than one SOT marker found right after main or tile header");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1911 */         this.ai |= 0x40;
/*      */         return;
/*      */       case -174:
/* 1914 */         if ((this.ai & 0x2) != 0) {
/* 1915 */           throw new c("More than one COD marker found in main header");
/*      */         }
/*      */ 
/*      */         
/* 1919 */         this.ai |= 0x2;
/* 1920 */         htKey = "COD";
/*      */         break;
/*      */       case -173:
/* 1923 */         this.ai |= 0x4;
/* 1924 */         htKey = "COC" + this.aj++;
/*      */         break;
/*      */       case -164:
/* 1927 */         if ((this.ai & 0x8) != 0) {
/* 1928 */           throw new c("More than one QCD marker found in main header");
/*      */         }
/*      */ 
/*      */         
/* 1932 */         this.ai |= 0x8;
/* 1933 */         htKey = "QCD";
/*      */         break;
/*      */       case -163:
/* 1936 */         this.ai |= 0x100;
/* 1937 */         htKey = "QCC" + this.ak++;
/*      */         break;
/*      */       case -162:
/* 1940 */         this.ai |= 0x200;
/* 1941 */         htKey = "RGN" + this.am++;
/*      */         break;
/*      */       case -156:
/* 1944 */         this.ai |= 0x800;
/* 1945 */         htKey = "COM" + this.al++;
/*      */         break;
/*      */       case -157:
/* 1948 */         if ((this.ai & 0x10000) != 0) {
/* 1949 */           throw new c("More than one CRG marker found in main header");
/*      */         }
/*      */ 
/*      */         
/* 1953 */         this.ai |= 0x10000;
/* 1954 */         htKey = "CRG";
/*      */         break;
/*      */       case -160:
/* 1957 */         this.ai |= 0x4000;
/* 1958 */         htKey = "PPM" + this.an++;
/*      */         break;
/*      */       case -171:
/* 1961 */         if ((this.ai & 0x10) != 0) {
/* 1962 */           c.b()
/* 1963 */             .printmsg(1, "More than one TLM marker found in main header");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1973 */         this.ai |= 0x10;
/*      */         break;
/*      */       case -169:
/* 1976 */         if ((this.ai & 0x20) != 0) {
/* 1977 */           throw new c("More than one PLM marker found in main header");
/*      */         }
/*      */ 
/*      */         
/* 1981 */         c.b()
/* 1982 */           .printmsg(2, "PLM marker segment found but not used by by JJ2000 decoder.");
/*      */         
/* 1984 */         this.ai |= 0x20;
/* 1985 */         htKey = "PLM";
/*      */         break;
/*      */       case -161:
/* 1988 */         if ((this.ai & 0x400) != 0) {
/* 1989 */           throw new c("More than one POC marker segment found in main header");
/*      */         }
/*      */ 
/*      */         
/* 1993 */         this.ai |= 0x400;
/* 1994 */         htKey = "POC";
/*      */         break;
/*      */       case -168:
/* 1997 */         throw new c("PLT found in main header");
/*      */       case -159:
/* 1999 */         throw new c("PPT found in main header");
/*      */       default:
/* 2001 */         htKey = "UNKNOWN";
/* 2002 */         c.b()
/* 2003 */           .printmsg(2, "Non recognized marker segment (0x" + 
/* 2004 */             Integer.toHexString(marker) + ") in main header!");
/*      */         break;
/*      */     } 
/*      */     
/* 2008 */     if (marker < -208 || marker > -193) {
/*      */       
/* 2010 */       int markSegLen = ehs.readUnsignedShort();
/* 2011 */       byte[] buf = new byte[markSegLen];
/*      */ 
/*      */       
/* 2014 */       buf[0] = (byte)(markSegLen >> 8 & 0xFF);
/* 2015 */       buf[1] = (byte)(markSegLen & 0xFF);
/* 2016 */       ehs.readFully(buf, 2, markSegLen - 2);
/*      */       
/* 2018 */       if (!htKey.equals("UNKNOWN"))
/*      */       {
/* 2020 */         this.aC.put(htKey, buf);
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
/*      */   public void a(short marker, f ehs, int tileIdx, int tilePartIdx) throws IOException {
/* 2045 */     String htKey = "";
/* 2046 */     if (this.aC == null) {
/* 2047 */       this.aC = new Hashtable<Object, Object>();
/*      */     }
/*      */     
/* 2050 */     switch (marker) {
/*      */       case -112:
/* 2052 */         throw new c("Second SOT marker segment found in tile-part header");
/*      */ 
/*      */       
/*      */       case -175:
/* 2056 */         throw new c("SIZ found in tile-part header");
/*      */       
/*      */       case -39:
/* 2059 */         throw new c("EOC found in tile-part header");
/*      */       
/*      */       case -171:
/* 2062 */         throw new c("TLM found in tile-part header");
/*      */       
/*      */       case -160:
/* 2065 */         throw new c("PPM found in tile-part header");
/*      */       
/*      */       case -174:
/* 2068 */         if ((this.ai & 0x2) != 0) {
/* 2069 */           throw new c("More than one COD marker found in tile-part header");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2074 */         this.ai |= 0x2;
/* 2075 */         htKey = "COD";
/*      */         break;
/*      */       case -173:
/* 2078 */         this.ai |= 0x4;
/* 2079 */         htKey = "COC" + this.aj++;
/*      */         break;
/*      */       case -164:
/* 2082 */         if ((this.ai & 0x8) != 0) {
/* 2083 */           throw new c("More than one QCD marker found in tile-part header");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2088 */         this.ai |= 0x8;
/* 2089 */         htKey = "QCD";
/*      */         break;
/*      */       case -163:
/* 2092 */         this.ai |= 0x100;
/* 2093 */         htKey = "QCC" + this.ak++;
/*      */         break;
/*      */       case -162:
/* 2096 */         this.ai |= 0x200;
/* 2097 */         htKey = "RGN" + this.am++;
/*      */         break;
/*      */       case -156:
/* 2100 */         this.ai |= 0x800;
/* 2101 */         htKey = "COM" + this.al++;
/*      */         break;
/*      */       case -157:
/* 2104 */         throw new c("CRG marker found in tile-part header");
/*      */       
/*      */       case -159:
/* 2107 */         this.ai |= 0x8000;
/* 2108 */         if (this.ao == null) {
/* 2109 */           this.ao = new int[this.ah][];
/*      */         }
/* 2111 */         if (this.ao[tileIdx] == null) {
/* 2112 */           this.ao[tileIdx] = new int[this.b[tileIdx]];
/*      */         }
/* 2114 */         this.ao[tileIdx][tilePartIdx] = this.ao[tileIdx][tilePartIdx] + 1; htKey = "PPT" + this.ao[tileIdx][tilePartIdx];
/*      */         break;
/*      */       case -109:
/* 2117 */         this.ai |= 0x2000;
/*      */         return;
/*      */       case -161:
/* 2120 */         if ((this.ai & 0x400) != 0) {
/* 2121 */           throw new c("More than one POC marker segment found in tile-part header");
/*      */         }
/*      */ 
/*      */         
/* 2125 */         this.ai |= 0x400;
/* 2126 */         htKey = "POC";
/*      */         break;
/*      */       case -168:
/* 2129 */         if ((this.ai & 0x20) != 0) {
/* 2130 */           throw new c("PLT marker found eventhough PLM marker found in main header");
/*      */         }
/*      */ 
/*      */         
/* 2134 */         c.b()
/* 2135 */           .printmsg(2, "PLT marker segment found but not used by JJ2000 decoder.");
/*      */         
/* 2137 */         htKey = "UNKNOWN";
/*      */         break;
/*      */       default:
/* 2140 */         htKey = "UNKNOWN";
/* 2141 */         c.b()
/* 2142 */           .printmsg(2, "Non recognized marker segment (0x" + 
/* 2143 */             Integer.toHexString(marker) + ") in tile-part header" + " of tile " + tileIdx + " !");
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2149 */     int markSegLen = ehs.readUnsignedShort();
/* 2150 */     byte[] buf = new byte[markSegLen];
/*      */ 
/*      */     
/* 2153 */     buf[0] = (byte)(markSegLen >> 8 & 0xFF);
/* 2154 */     buf[1] = (byte)(markSegLen & 0xFF);
/* 2155 */     ehs.readFully(buf, 2, markSegLen - 2);
/*      */     
/* 2157 */     if (!htKey.equals("UNKNOWN"))
/*      */     {
/* 2159 */       this.aC.put(htKey, buf);
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
/*      */   private void r() throws IOException {
/* 2174 */     if ((this.ai & 0x1) != 0) {
/* 2175 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("SIZ"));
/* 2176 */       a(new DataInputStream(bais));
/*      */     } 
/*      */ 
/*      */     
/* 2180 */     if ((this.ai & 0x800) != 0) {
/* 2181 */       for (int i = 0; i < this.al; i++) {
/* 2182 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COM" + i));
/* 2183 */         a(new DataInputStream(bais), true, 0, i);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2188 */     if ((this.ai & 0x10000) != 0) {
/* 2189 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("CRG"));
/* 2190 */       b(new DataInputStream(bais));
/*      */     } 
/*      */ 
/*      */     
/* 2194 */     if ((this.ai & 0x2) != 0) {
/* 2195 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COD"));
/* 2196 */       d(new DataInputStream(bais), true, 0, 0);
/*      */     } 
/*      */ 
/*      */     
/* 2200 */     if ((this.ai & 0x4) != 0) {
/* 2201 */       for (int i = 0; i < this.aj; i++) {
/* 2202 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COC" + i));
/* 2203 */         e(new DataInputStream(bais), true, 0, 0);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2208 */     if ((this.ai & 0x200) != 0) {
/* 2209 */       for (int i = 0; i < this.am; i++) {
/* 2210 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("RGN" + i));
/* 2211 */         g(new DataInputStream(bais), true, 0, 0);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2216 */     if ((this.ai & 0x8) != 0) {
/* 2217 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("QCD"));
/* 2218 */       b(new DataInputStream(bais), true, 0, 0);
/*      */     } 
/*      */ 
/*      */     
/* 2222 */     if ((this.ai & 0x100) != 0) {
/* 2223 */       for (int i = 0; i < this.ak; i++) {
/* 2224 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("QCC" + i));
/* 2225 */         c(new DataInputStream(bais), true, 0, 0);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2230 */     if ((this.ai & 0x400) != 0) {
/* 2231 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("POC"));
/* 2232 */       f(new DataInputStream(bais), true, 0, 0);
/*      */     } 
/*      */ 
/*      */     
/* 2236 */     if ((this.ai & 0x4000) != 0) {
/* 2237 */       for (int i = 0; i < this.an; i++) {
/* 2238 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("PPM" + i));
/* 2239 */         f(new DataInputStream(bais));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2244 */     this.aC = null;
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
/*      */   public void a(int tileIdx, int tpIdx) throws IOException {
/* 2265 */     if ((this.ai & 0x2) != 0) {
/* 2266 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COD"));
/* 2267 */       d(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */     } 
/*      */ 
/*      */     
/* 2271 */     if ((this.ai & 0x4) != 0) {
/* 2272 */       for (int i = 0; i < this.aj; i++) {
/* 2273 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COC" + i));
/* 2274 */         e(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2279 */     if ((this.ai & 0x200) != 0) {
/* 2280 */       for (int i = 0; i < this.am; i++) {
/* 2281 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("RGN" + i));
/* 2282 */         g(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2287 */     if ((this.ai & 0x8) != 0) {
/* 2288 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("QCD"));
/* 2289 */       b(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */     } 
/*      */ 
/*      */     
/* 2293 */     if ((this.ai & 0x100) != 0) {
/* 2294 */       for (int i = 0; i < this.ak; i++) {
/* 2295 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("QCC" + i));
/* 2296 */         c(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */       } 
/*      */     }
/*      */     
/* 2300 */     if ((this.ai & 0x400) != 0) {
/* 2301 */       ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("POC"));
/* 2302 */       f(new DataInputStream(bais), false, tileIdx, tpIdx);
/*      */     } 
/*      */ 
/*      */     
/* 2306 */     if ((this.ai & 0x800) != 0) {
/* 2307 */       for (int i = 0; i < this.al; i++) {
/* 2308 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("COM" + i));
/* 2309 */         a(new DataInputStream(bais), false, tileIdx, i);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2314 */     if ((this.ai & 0x8000) != 0) {
/* 2315 */       for (int i = 0; i < this.ao[tileIdx][tpIdx]; i++) {
/* 2316 */         ByteArrayInputStream bais = new ByteArrayInputStream((byte[])this.aC.get("PPT" + i));
/* 2317 */         a(new DataInputStream(bais), tileIdx, tpIdx);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2322 */     this.aC = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a m() {
/* 2332 */     return this.aG;
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
/*      */   public d(f ehs, J2KImageReadParamJava j2krparam, c.a.a.d hi) throws IOException {
/* 2361 */     this.ae = hi;
/* 2362 */     this.ag = j2krparam;
/* 2363 */     this.h = ehs.getPos();
/* 2364 */     if (ehs.readShort() != -177) {
/* 2365 */       throw new c("SOC marker segment not  found at the beginning of the codestream.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2373 */     this.ai = 0;
/*      */     while (true) {
/* 2375 */       a(ehs.readShort(), ehs);
/* 2376 */       if ((this.ai & 0x40) != 0) {
/* 2377 */         ehs.seek(ehs.getPos() - 2);
/*      */ 
/*      */         
/* 2380 */         r();
/*      */         return;
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
/*      */   public e a(c src, J2KImageReadParamJava j2krparam) {
/* 2400 */     boolean doer = true;
/*      */ 
/*      */     
/* 2403 */     boolean verber = false;
/*      */ 
/*      */ 
/*      */     
/* 2407 */     int mMax = -1;
/* 2408 */     return (e)new g(src, this.aG, doer, verber, mMax);
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
/*      */   public b a(a src, J2KImageReadParamJava j2krparam, a decSpec2) {
/* 2507 */     return b.a(src, j2krparam, decSpec2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void n() {
/* 2517 */     this.ai &= 0x4020;
/* 2518 */     this.aj = 0;
/* 2519 */     this.ak = 0;
/* 2520 */     this.al = 0;
/* 2521 */     this.am = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2531 */     return this.af;
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
/*      */   public static String[][] o() {
/* 2546 */     return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int p() {
/* 2555 */     return this.ah;
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
/*      */   public ByteArrayInputStream e(int tile) throws IOException {
/* 2570 */     if (this.aJ == null) {
/*      */       
/* 2572 */       this.aJ = new ByteArrayOutputStream[this.ah]; int i;
/* 2573 */       for (i = this.ah - 1; i >= 0; i--) {
/* 2574 */         this.aJ[i] = new ByteArrayOutputStream();
/*      */       }
/* 2576 */       if (this.an != 0) {
/*      */ 
/*      */ 
/*      */         
/* 2580 */         int nTileParts = this.i.size();
/*      */ 
/*      */         
/* 2583 */         ByteArrayOutputStream allNppmIppm = new ByteArrayOutputStream();
/*      */ 
/*      */ 
/*      */         
/* 2587 */         for (i = 0; i < this.an; i++) {
/* 2588 */           allNppmIppm.write(this.aH[i]);
/*      */         }
/* 2590 */         ByteArrayInputStream pph = new ByteArrayInputStream(allNppmIppm.toByteArray());
/*      */ 
/*      */ 
/*      */         
/* 2594 */         for (i = 0; i < nTileParts; i++) {
/* 2595 */           int t = ((Integer)this.i.elementAt(i)).intValue();
/*      */ 
/*      */           
/* 2598 */           int nppm = pph.read() << 24 | pph.read() << 16 | pph.read() << 8 | pph.read();
/*      */           
/* 2600 */           byte[] temp = new byte[nppm];
/*      */           
/* 2602 */           pph.read(temp);
/* 2603 */           this.aJ[t].write(temp);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2608 */         for (int t = this.ah - 1; t >= 0; t--) {
/* 2609 */           for (int tp = 0; tp < this.b[t]; tp++) {
/* 2610 */             for (i = 0; i < this.ao[t][tp]; i++) {
/* 2611 */               this.aJ[t]
/* 2612 */                 .write(this.aI[t][tp][i]);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2619 */     return new ByteArrayInputStream(this.aJ[tile].toByteArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void f(int tile) {
/* 2630 */     if (this.an != 0) {
/* 2631 */       this.i.addElement(new Integer(tile));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int q() {
/* 2641 */     return this.ai;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */