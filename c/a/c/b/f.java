/*      */ package c.a.c.b;
/*      */ 
/*      */ import c.a.a.b.a;
/*      */ import c.a.a.b.b;
/*      */ import c.a.a.b.e;
/*      */ import c.a.a.g;
/*      */ import c.a.c.d;
/*      */ import c.a.i.c;
/*      */ import c.a.i.e;
/*      */ import c.a.i.i;
/*      */ import c.a.j.a.o;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*      */ import java.awt.Point;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class f
/*      */   extends j
/*      */ {
/*      */   private static final boolean g = false;
/*      */   private long h;
/*      */   private long i;
/*      */   private long j;
/*      */   private c[][][][][] m;
/*      */   private int[][][][][][] n;
/*      */   private Point[][][] o;
/*      */   private e[] p;
/*  147 */   private static final double q = Math.log(2.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int r = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int s = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final float t = 1.0E-4F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final float u = 1.0E-10F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int v = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] w;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private e x;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private h y;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float z;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float A;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public f(d src, h lyrs, b writer, J2KImageWriteParamJava wp) {
/*  216 */     super(src, lyrs.b(), writer, wp);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  221 */     Point ncblks = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  237 */     this.y = lyrs;
/*      */ 
/*      */     
/*  240 */     this.w = new int[64];
/*      */ 
/*      */     
/*  243 */     int nt = src.getNumTiles();
/*  244 */     int nc = getNumComps();
/*      */ 
/*      */     
/*  247 */     this.m = new c[nt][nc][][][];
/*  248 */     this.n = new int[nt][this.d][nc][][][];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  263 */     Point tileI = null;
/*  264 */     Point nTiles = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  269 */     int cb0x = src.a();
/*  270 */     int cb0y = src.b();
/*      */     
/*  272 */     src.setTile(0, 0);
/*  273 */     for (int t = 0; t < nt; t++) {
/*  274 */       nTiles = src.getNumTiles(nTiles);
/*  275 */       tileI = src.getTile(tileI);
/*  276 */       int x0siz = getImgULX();
/*  277 */       int y0siz = getImgULY();
/*  278 */       int xsiz = x0siz + getImgWidth();
/*  279 */       int ysiz = y0siz + getImgHeight();
/*  280 */       int xt0siz = src.getTilePartULX();
/*  281 */       int yt0siz = src.getTilePartULY();
/*  282 */       int xtsiz = src.getNomTileWidth();
/*  283 */       int ytsiz = src.getNomTileHeight();
/*      */ 
/*      */       
/*  286 */       int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/*  287 */       int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/*  288 */       int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/*  289 */       int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */       
/*  291 */       for (int i = 0; i < nc; i++) {
/*      */ 
/*      */         
/*  294 */         o sb = src.e(t, i);
/*  295 */         int mrl = sb.h + 1;
/*      */ 
/*      */         
/*  298 */         if (this.o == null) this.o = new Point[nt][nc][]; 
/*  299 */         if (this.o[t][i] == null) {
/*  300 */           this.o[t][i] = new Point[mrl];
/*      */         }
/*      */ 
/*      */         
/*  304 */         int xrsiz = src.getCompSubsX(i);
/*  305 */         int yrsiz = src.getCompSubsY(i);
/*      */ 
/*      */         
/*  308 */         int tcx0 = (int)Math.ceil(tx0 / xrsiz);
/*  309 */         int tcy0 = (int)Math.ceil(ty0 / yrsiz);
/*  310 */         int tcx1 = (int)Math.ceil(tx1 / xrsiz);
/*  311 */         int tcy1 = (int)Math.ceil(ty1 / yrsiz);
/*      */         
/*  313 */         this.m[t][i] = new c[mrl][][];
/*      */         int l;
/*  315 */         for (l = 0; l < this.d; l++) {
/*  316 */           this.n[t][l][i] = new int[mrl][][];
/*      */         }
/*      */         
/*  319 */         for (int r = 0; r < mrl; r++) {
/*      */ 
/*      */ 
/*      */           
/*  323 */           int trx0 = (int)Math.ceil(tcx0 / (1 << mrl - 1 - r));
/*  324 */           int try0 = (int)Math.ceil(tcy0 / (1 << mrl - 1 - r));
/*  325 */           int trx1 = (int)Math.ceil(tcx1 / (1 << mrl - 1 - r));
/*  326 */           int try1 = (int)Math.ceil(tcy1 / (1 << mrl - 1 - r));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  331 */           double twoppx = wp.getPrecinctPartition().a(t, i, r);
/*  332 */           double twoppy = wp.getPrecinctPartition().b(t, i, r);
/*  333 */           this.o[t][i][r] = new Point();
/*  334 */           if (trx1 > trx0) {
/*  335 */             (this.o[t][i][r])
/*  336 */               .x = (int)Math.ceil((trx1 - cb0x) / twoppx) - (int)Math.floor((trx0 - cb0x) / twoppx);
/*      */           } else {
/*  338 */             (this.o[t][i][r]).x = 0;
/*      */           } 
/*  340 */           if (try1 > try0) {
/*  341 */             (this.o[t][i][r])
/*  342 */               .y = (int)Math.ceil((try1 - cb0y) / twoppy) - (int)Math.floor((try0 - cb0y) / twoppy);
/*      */           } else {
/*  344 */             (this.o[t][i][r]).y = 0;
/*      */           } 
/*      */           
/*  347 */           int minsbi = (r == 0) ? 0 : 1;
/*  348 */           int maxsbi = (r == 0) ? 1 : 4;
/*      */           
/*  350 */           this.m[t][i][r] = new c[maxsbi][];
/*  351 */           for (l = 0; l < this.d; l++) {
/*  352 */             this.n[t][l][i][r] = new int[maxsbi][];
/*      */           }
/*      */           
/*  355 */           for (int s = minsbi; s < maxsbi; s++) {
/*      */             
/*  357 */             o sb2 = (o)sb.a(r, s);
/*  358 */             ncblks = sb2.i;
/*  359 */             int cblkPerSubband = ncblks.x * ncblks.y;
/*  360 */             this.m[t][i][r][s] = new c[cblkPerSubband];
/*      */ 
/*      */             
/*  363 */             for (l = 0; l < this.d; l++) {
/*  364 */               this.n[t][l][i][r][s] = new int[cblkPerSubband];
/*  365 */               for (int k = 0; k < cblkPerSubband; k++) {
/*  366 */                 this.n[t][l][i][r][s][k] = -1;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  372 */       if (t != nt - 1) {
/*  373 */         src.nextTile();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  378 */     this.x = new e(src, wp, this.o);
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
/*      */   public void finalize() throws Throwable {
/*  405 */     super.finalize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a() throws IOException {
/*  414 */     f();
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
/*      */   public void b() throws IOException {
/*  434 */     int numTiles = this.b.getNumTiles();
/*  435 */     int numComps = this.b.getNumComps();
/*      */ 
/*      */ 
/*      */     
/*  439 */     long stime = 0L;
/*      */ 
/*      */ 
/*      */     
/*  443 */     e();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  448 */     int totenclength = this.w[0];
/*      */     
/*      */     int t;
/*      */     
/*  452 */     for (t = 0; t < numTiles; t++) {
/*  453 */       int avgPktLen = 2;
/*      */       
/*  455 */       if (((String)this.c.getSOP().f(t)).equalsIgnoreCase("true")) {
/*  456 */         avgPktLen += 6;
/*      */       }
/*      */       
/*  459 */       if (((String)this.c.getEPH().f(t)).equalsIgnoreCase("true")) {
/*  460 */         avgPktLen += 2;
/*      */       }
/*      */       
/*  463 */       for (int m = 0; m < numComps; m++) {
/*  464 */         int numLvls = (this.b.e(t, m)).h + 1;
/*  465 */         if (!this.b.a(m, t)) {
/*      */ 
/*      */           
/*  468 */           totenclength += this.d * avgPktLen * numLvls;
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  474 */           for (int rl = 0; rl < numLvls; rl++) {
/*  475 */             int maxpkt = (this.o[t][m][rl]).x * (this.o[t][m][rl]).y;
/*  476 */             totenclength += this.d * avgPktLen * maxpkt;
/*      */           } 
/*      */         } 
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
/*  490 */     int ho = this.f.c();
/*  491 */     float np = (this.b.getImgWidth() * this.b.getImgHeight()) / 8.0F;
/*      */ 
/*      */     
/*  494 */     for (t = 0; t < numTiles; t++) {
/*  495 */       this.f.a();
/*  496 */       this.f.b(0, t);
/*  497 */       ho += this.f.c();
/*      */     } 
/*      */     
/*  500 */     this.p = new e[this.d]; int n;
/*  501 */     for (n = this.d - 1; n >= 0; n--) {
/*  502 */       this.p[n] = new e();
/*      */     }
/*      */     
/*  505 */     int minlsz = 0;
/*  506 */     for (t = 0; t < numTiles; t++) {
/*  507 */       for (int m = 0; m < numComps; m++) {
/*  508 */         int numLvls = (this.b.e(t, m)).h + 1;
/*      */         
/*  510 */         if (!this.b.a(m, t)) {
/*      */           
/*  512 */           minlsz += 32 * numLvls;
/*      */         }
/*      */         else {
/*      */           
/*  516 */           for (int rl = 0; rl < numLvls; rl++) {
/*  517 */             int maxpkt = (this.o[t][m][rl]).x * (this.o[t][m][rl]).y;
/*  518 */             minlsz += 32 * maxpkt;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  525 */     n = 0;
/*  526 */     int i = 0;
/*  527 */     int lastbytes = 0;
/*      */     
/*  529 */     while (n < this.d - 1) {
/*      */       int m;
/*  531 */       double basebytes = Math.floor((this.y.a(i) * np));
/*  532 */       if (i < this.y.c() - 1) {
/*  533 */         m = (int)(this.y.a(i + 1) * np);
/*      */         
/*  535 */         if (m > totenclength) {
/*  536 */           m = totenclength;
/*      */         }
/*      */       } else {
/*  539 */         m = 1;
/*      */       } 
/*  541 */       int loopnlyrs = this.y.b(i) + 1;
/*  542 */       double ls = Math.exp(Math.log(m / basebytes) / loopnlyrs);
/*  543 */       (this.p[n]).c = true;
/*  544 */       for (int l = 0; l < loopnlyrs; l++) {
/*  545 */         int i1 = (int)basebytes - lastbytes - ho;
/*  546 */         if (i1 < minlsz) {
/*  547 */           basebytes *= ls;
/*  548 */           this.d--;
/*      */         } else {
/*      */           
/*  551 */           lastbytes = (int)basebytes - ho;
/*  552 */           (this.p[n]).a = lastbytes;
/*  553 */           basebytes *= ls;
/*  554 */           n++;
/*      */         } 
/*  556 */       }  i++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  561 */     n = this.d - 2;
/*  562 */     int nextbytes = (int)(this.y.a() * np) - ho;
/*  563 */     int newbytes = nextbytes - ((n >= 0) ? (this.p[n]).a : 0);
/*  564 */     while (newbytes < minlsz) {
/*  565 */       if (this.d == 1) {
/*  566 */         if (newbytes <= 0) {
/*  567 */           throw new IllegalArgumentException("Overall target bitrate too low, given the current bit stream header overhead");
/*      */         }
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */ 
/*      */       
/*  575 */       this.d--;
/*  576 */       n--;
/*  577 */       newbytes = nextbytes - ((n >= 0) ? (this.p[n]).a : 0);
/*      */     } 
/*      */     
/*  580 */     n++;
/*  581 */     (this.p[n]).a = nextbytes;
/*  582 */     (this.p[n]).c = true;
/*      */ 
/*      */ 
/*      */     
/*  586 */     d[] prog1 = (d[])this.c.getProgressionType().d();
/*  587 */     int nValidProg = prog1.length;
/*  588 */     for (int prg = 0; prg < prog1.length; prg++) {
/*  589 */       if ((prog1[prg]).f > this.d) {
/*  590 */         (prog1[prg]).f = this.d;
/*      */       }
/*      */     } 
/*  593 */     if (nValidProg == 0) {
/*  594 */       throw new Error("Unable to initialize rate allocator: No default progression type has been defined.");
/*      */     }
/*      */ 
/*      */     
/*  598 */     for (int k = 0; k < numTiles; k++) {
/*  599 */       if (this.c.getProgressionType().h(k)) {
/*  600 */         prog1 = (d[])this.c.getProgressionType().f(k);
/*  601 */         nValidProg = prog1.length;
/*  602 */         for (int m = 0; m < prog1.length; m++) {
/*  603 */           if ((prog1[m]).f > this.d) {
/*  604 */             (prog1[m]).f = this.d;
/*      */           }
/*      */         } 
/*  607 */         if (nValidProg == 0) {
/*  608 */           throw new Error("Unable to initialize rate allocator: No default progression type has been defined.");
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
/*      */   private void e() {
/*  631 */     c ccb = null;
/*  632 */     Point ncblks = null;
/*      */ 
/*      */ 
/*      */     
/*  636 */     long stime = 0L;
/*      */     
/*  638 */     this.z = 0.0F;
/*  639 */     this.A = Float.MAX_VALUE;
/*      */ 
/*      */     
/*  642 */     int numComps = this.b.getNumComps();
/*  643 */     int numTiles = this.b.getNumTiles();
/*      */ 
/*      */     
/*  646 */     int cblkToEncode = 0;
/*  647 */     int nEncCblk = 0;
/*  648 */     i pw = c.a();
/*      */ 
/*      */     
/*  651 */     this.b.setTile(0, 0);
/*  652 */     for (int t = 0; t < numTiles; t++) {
/*  653 */       nEncCblk = 0;
/*  654 */       cblkToEncode = 0; int i;
/*  655 */       for (i = 0; i < numComps; i++) {
/*  656 */         o root = this.b.e(t, i);
/*  657 */         for (int r = 0; r <= root.h; r++) {
/*  658 */           if (r == 0) {
/*  659 */             o sb = (o)root.a(0, 0);
/*  660 */             if (sb != null) cblkToEncode += sb.i.x * sb.i.y; 
/*      */           } else {
/*  662 */             o sb = (o)root.a(r, 1);
/*  663 */             if (sb != null) cblkToEncode += sb.i.x * sb.i.y; 
/*  664 */             sb = (o)root.a(r, 2);
/*  665 */             if (sb != null) cblkToEncode += sb.i.x * sb.i.y; 
/*  666 */             sb = (o)root.a(r, 3);
/*  667 */             if (sb != null) cblkToEncode += sb.i.x * sb.i.y; 
/*      */           } 
/*      */         } 
/*      */       } 
/*  671 */       if (pw != null) {
/*  672 */         pw.a(0, cblkToEncode, "Encoding tile " + t + "...");
/*      */       }
/*      */       
/*  675 */       for (i = 0; i < numComps; i++) {
/*      */ 
/*      */         
/*  678 */         while ((ccb = this.b.a(i, ccb)) != null) {
/*      */ 
/*      */           
/*  681 */           if (pw != null) {
/*  682 */             nEncCblk++;
/*  683 */             pw.a(nEncCblk, null);
/*      */           } 
/*      */           
/*  686 */           o subb = ccb.e;
/*      */ 
/*      */           
/*  689 */           int r = subb.h;
/*      */ 
/*      */           
/*  692 */           int s = subb.k;
/*      */ 
/*      */           
/*  695 */           ncblks = subb.i;
/*      */ 
/*      */ 
/*      */           
/*  699 */           int last_sidx = -1;
/*  700 */           for (int k = ccb.g - 1; k >= 0; k--) {
/*  701 */             float fslope = ccb.j[k];
/*  702 */             if (fslope > this.z) this.z = fslope; 
/*  703 */             if (fslope < this.A) this.A = fslope; 
/*  704 */             int sidx = a(fslope);
/*  705 */             for (; sidx > last_sidx; sidx--) {
/*  706 */               this.w[sidx] = this.w[sidx] + ccb.h[ccb.k[k]];
/*      */             }
/*      */             
/*  709 */             last_sidx = a(fslope);
/*      */           } 
/*      */ 
/*      */           
/*  713 */           this.m[t][i][r][s][ccb.b * ncblks.x + ccb.a] = ccb;
/*  714 */           ccb = null;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  720 */       if (pw != null) {
/*  721 */         pw.a();
/*      */       }
/*      */ 
/*      */       
/*  725 */       if (t < numTiles - 1) {
/*  726 */         this.b.nextTile();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void f() throws IOException {
/*  737 */     int nPrec = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  742 */     a hBuff = null;
/*  743 */     byte[] bBuff = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  748 */     int nc = this.b.getNumComps();
/*  749 */     int nt = this.b.getNumTiles();
/*      */ 
/*      */     
/*  752 */     long stime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  757 */     float rdThreshold = this.z;
/*      */     
/*  759 */     int[] tileLengths = new int[nt];
/*  760 */     int actualBytes = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  766 */     for (int l = 0; l < this.d; l++) {
/*      */       
/*  768 */       int maxBytes = (this.p[l]).a;
/*  769 */       if ((this.p[l]).c) {
/*      */         
/*  771 */         rdThreshold = a(l, rdThreshold, maxBytes, actualBytes);
/*      */       } else {
/*  773 */         if (l <= 0 || l >= this.d - 1) {
/*  774 */           throw new IllegalArgumentException("The first and the last layer thresholds must be optimized");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  779 */         rdThreshold = a(maxBytes, this.p[l - 1]);
/*      */       } 
/*      */       
/*  782 */       for (int i = 0; i < nt; i++) {
/*  783 */         if (l == 0) {
/*      */           
/*  785 */           this.f.a();
/*  786 */           this.f.b(0, i);
/*  787 */           tileLengths[i] = tileLengths[i] + this.f.c();
/*      */         } 
/*      */         
/*  790 */         for (int k = 0; k < nc; k++) {
/*      */ 
/*      */ 
/*      */           
/*  794 */           boolean sopUsed = ((String)this.c.getSOP().f(i)).equalsIgnoreCase("true");
/*      */ 
/*      */           
/*  797 */           boolean ephUsed = ((String)this.c.getEPH().f(i)).equalsIgnoreCase("true");
/*      */ 
/*      */           
/*  800 */           o sb = this.b.e(i, k);
/*  801 */           int mrl = sb.h + 1;
/*      */           
/*  803 */           while (sb.u != null) {
/*  804 */             sb = sb.u;
/*      */           }
/*      */           
/*  807 */           for (int r = 0; r < mrl; r++) {
/*      */             
/*  809 */             nPrec = (this.o[i][k][r]).x * (this.o[i][k][r]).y;
/*  810 */             for (int p = 0; p < nPrec; p++) {
/*      */               
/*  812 */               a(l, k, r, i, sb, rdThreshold, p);
/*      */ 
/*      */               
/*  815 */               hBuff = this.x.a(l + 1, k, r, i, this.m[i][k][r], this.n[i][l][k][r], hBuff, bBuff, p);
/*      */ 
/*      */ 
/*      */               
/*  819 */               if (this.x.f()) {
/*      */                 
/*  821 */                 int tmp = this.e.a(hBuff.c(), hBuff
/*  822 */                     .b(), true, sopUsed, ephUsed);
/*      */                 
/*  824 */                 tmp += this.e
/*  825 */                   .a(this.x.a(), this.x
/*  826 */                     .b(), true, this.x
/*  827 */                     .g(), this.x
/*  828 */                     .h());
/*  829 */                 actualBytes += tmp;
/*  830 */                 tileLengths[i] = tileLengths[i] + tmp;
/*      */               } 
/*      */             } 
/*  833 */             sb = sb.t;
/*      */           } 
/*      */         } 
/*      */       } 
/*  837 */       (this.p[l]).d = rdThreshold;
/*  838 */       (this.p[l]).b = actualBytes;
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
/*  851 */     this.x.e();
/*      */ 
/*      */ 
/*      */     
/*  855 */     int[] mrlc = new int[nc];
/*  856 */     for (int t = 0; t < nt; t++) {
/*      */ 
/*      */       
/*  859 */       int[][] lys = new int[nc][];
/*  860 */       for (int i = 0; i < nc; i++) {
/*  861 */         mrlc[i] = (this.b.e(t, i)).h;
/*  862 */         lys[i] = new int[mrlc[i] + 1];
/*      */       } 
/*      */ 
/*      */       
/*  866 */       this.f.a();
/*  867 */       this.f.b(tileLengths[t], t);
/*  868 */       this.e.a(this.f);
/*  869 */       d[] prog = (d[])this.c.getProgressionType().f(t);
/*      */       
/*  871 */       for (int prg = 0; prg < prog.length; prg++) {
/*  872 */         int lye = (prog[prg]).f;
/*  873 */         int cs = (prog[prg]).b;
/*  874 */         int ce = (prog[prg]).c;
/*  875 */         int rs = (prog[prg]).d;
/*  876 */         int re = (prog[prg]).e;
/*      */         
/*  878 */         switch ((prog[prg]).a) {
/*      */           case 1:
/*  880 */             a(t, rs, re, cs, ce, lys, lye);
/*      */             break;
/*      */           case 0:
/*  883 */             b(t, rs, re, cs, ce, lys, lye);
/*      */             break;
/*      */           case 3:
/*  886 */             c(t, rs, re, cs, ce, lys, lye);
/*      */             break;
/*      */           case 4:
/*  889 */             d(t, rs, re, cs, ce, lys, lye);
/*      */             break;
/*      */           case 2:
/*  892 */             e(t, rs, re, cs, ce, lys, lye);
/*      */             break;
/*      */           default:
/*  895 */             throw new Error("Unsupported bit stream progression type");
/*      */         } 
/*      */ 
/*      */         
/*  899 */         for (int k = cs; k < ce; k++) {
/*  900 */           for (int r = rs; r < re; r++) {
/*  901 */             if (r <= mrlc[k]) {
/*  902 */               lys[k][r] = lye;
/*      */             }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(int t, int rs, int re, int cs, int ce, int[][] lys, int lye) throws IOException {
/*  933 */     int nc = this.b.getNumComps();
/*  934 */     int[] mrl = new int[nc];
/*      */ 
/*      */     
/*  937 */     a hBuff = null;
/*  938 */     byte[] bBuff = null;
/*  939 */     int nPrec = 0;
/*      */ 
/*      */     
/*  942 */     int maxResLvl = 0;
/*  943 */     for (int i = 0; i < nc; i++) {
/*  944 */       mrl[i] = (this.b.e(t, i)).h;
/*  945 */       if (mrl[i] > maxResLvl) maxResLvl = mrl[i];
/*      */     
/*      */     } 
/*      */ 
/*      */     
/*  950 */     for (int r = rs; r < re; r++) {
/*  951 */       if (r <= maxResLvl) {
/*      */         
/*  953 */         int minlys = 100000;
/*  954 */         for (int k = cs; k < ce; k++) {
/*  955 */           if (r < (lys[k]).length && lys[k][r] < minlys) {
/*  956 */             minlys = lys[k][r];
/*      */           }
/*      */         } 
/*      */         
/*  960 */         for (int l = minlys; l < lye; l++) {
/*  961 */           for (int m = cs; m < ce; m++) {
/*  962 */             if (r < (lys[m]).length && 
/*  963 */               l >= lys[m][r])
/*      */             {
/*      */               
/*  966 */               if (r <= mrl[m]) {
/*      */                 
/*  968 */                 nPrec = (this.o[t][m][r]).x * (this.o[t][m][r]).y;
/*  969 */                 for (int p = 0; p < nPrec; p++) {
/*      */ 
/*      */                   
/*  972 */                   boolean sopUsed = ((String)this.c.getSOP().f(t)).equals("true");
/*      */                   
/*  974 */                   boolean ephUsed = ((String)this.c.getEPH().f(t)).equals("true");
/*      */                   
/*  976 */                   o sb = this.b.e(t, m);
/*  977 */                   for (int n = mrl[m]; n > r; n--) {
/*  978 */                     sb = sb.u;
/*      */                   }
/*      */                   
/*  981 */                   float threshold = (this.p[l]).d;
/*  982 */                   a(l, m, r, t, sb, threshold, p);
/*      */                   
/*  984 */                   hBuff = this.x.a(l + 1, m, r, t, this.m[t][m][r], this.n[t][l][m][r], hBuff, bBuff, p);
/*      */ 
/*      */ 
/*      */                   
/*  988 */                   if (this.x.f()) {
/*  989 */                     this.e.a(hBuff.c(), hBuff
/*  990 */                         .b(), false, sopUsed, ephUsed);
/*      */                     
/*  992 */                     this.e.a(this.x.a(), this.x
/*  993 */                         .b(), false, this.x
/*  994 */                         .g(), this.x
/*  995 */                         .h());
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(int t, int rs, int re, int cs, int ce, int[][] lys, int lye) throws IOException {
/* 1027 */     int nc = this.b.getNumComps();
/*      */ 
/*      */ 
/*      */     
/* 1031 */     a hBuff = null;
/* 1032 */     byte[] bBuff = null;
/* 1033 */     int nPrec = 0;
/*      */     
/* 1035 */     int minlys = 100000;
/* 1036 */     for (int i = cs; i < ce; i++) {
/* 1037 */       for (int r = 0; r < lys.length; r++) {
/* 1038 */         if (lys[i] != null && r < (lys[i]).length && lys[i][r] < minlys) {
/* 1039 */           minlys = lys[i][r];
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1044 */     for (int l = minlys; l < lye; l++) {
/* 1045 */       for (int r = rs; r < re; r++) {
/* 1046 */         for (int k = cs; k < ce; k++) {
/* 1047 */           int mrl = (this.b.e(t, k)).h;
/* 1048 */           if (r <= mrl && 
/* 1049 */             r < (lys[k]).length && 
/* 1050 */             l >= lys[k][r]) {
/* 1051 */             nPrec = (this.o[t][k][r]).x * (this.o[t][k][r]).y;
/* 1052 */             for (int p = 0; p < nPrec; p++) {
/*      */ 
/*      */               
/* 1055 */               boolean sopUsed = ((String)this.c.getSOP().f(t)).equals("true");
/*      */               
/* 1057 */               boolean ephUsed = ((String)this.c.getEPH().f(t)).equals("true");
/*      */               
/* 1059 */               o sb = this.b.e(t, k);
/* 1060 */               for (int m = mrl; m > r; m--) {
/* 1061 */                 sb = sb.u;
/*      */               }
/*      */               
/* 1064 */               float threshold = (this.p[l]).d;
/* 1065 */               a(l, k, r, t, sb, threshold, p);
/*      */               
/* 1067 */               hBuff = this.x.a(l + 1, k, r, t, this.m[t][k][r], this.n[t][l][k][r], hBuff, bBuff, p);
/*      */ 
/*      */ 
/*      */               
/* 1071 */               if (this.x.f()) {
/* 1072 */                 this.e.a(hBuff.c(), hBuff
/* 1073 */                     .b(), false, sopUsed, ephUsed);
/*      */                 
/* 1075 */                 this.e.a(this.x.a(), this.x
/* 1076 */                     .b(), false, this.x
/* 1077 */                     .g(), this.x
/* 1078 */                     .h());
/*      */               } 
/*      */             } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void c(int t, int rs, int re, int cs, int ce, int[][] lys, int lye) throws IOException {
/* 1109 */     int nc = this.b.getNumComps();
/*      */ 
/*      */ 
/*      */     
/* 1113 */     a hBuff = null;
/* 1114 */     byte[] bBuff = null;
/*      */ 
/*      */     
/* 1117 */     Point nTiles = this.b.getNumTiles(null);
/* 1118 */     Point tileI = this.b.getTile(null);
/* 1119 */     int x0siz = this.b.getImgULX();
/* 1120 */     int y0siz = this.b.getImgULY();
/* 1121 */     int xsiz = x0siz + this.b.getImgWidth();
/* 1122 */     int ysiz = y0siz + this.b.getImgHeight();
/* 1123 */     int xt0siz = this.b.getTilePartULX();
/* 1124 */     int yt0siz = this.b.getTilePartULY();
/* 1125 */     int xtsiz = this.b.getNomTileWidth();
/* 1126 */     int ytsiz = this.b.getNomTileHeight();
/* 1127 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1128 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1129 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1130 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1137 */     int gcd_x = 0;
/* 1138 */     int gcd_y = 0;
/* 1139 */     int nPrec = 0;
/* 1140 */     int[][] nextPrec = new int[ce][];
/*      */     
/* 1142 */     int minlys = 100000;
/* 1143 */     int minx = tx1;
/*      */     
/* 1145 */     int miny = ty1;
/*      */     
/* 1147 */     int maxx = tx0;
/* 1148 */     int maxy = ty0;
/* 1149 */     for (int i = cs; i < ce; i++) {
/* 1150 */       int mrl = (this.b.e(t, i)).h;
/* 1151 */       nextPrec[i] = new int[mrl + 1];
/* 1152 */       for (int r = rs; r < re; r++) {
/* 1153 */         if (r <= mrl) {
/* 1154 */           if (r < (lys[i]).length && lys[i][r] < minlys) {
/* 1155 */             minlys = lys[i][r];
/*      */           }
/* 1157 */           int p = (this.o[t][i][r]).y * (this.o[t][i][r]).x - 1;
/* 1158 */           for (; p >= 0; p--) {
/* 1159 */             g prec = this.x.a(t, i, r, p);
/* 1160 */             if (prec.a != tx0) {
/* 1161 */               if (prec.a < minx) minx = prec.a; 
/* 1162 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1164 */             if (prec.b != ty0) {
/* 1165 */               if (prec.b < miny) miny = prec.b; 
/* 1166 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1169 */             if (nPrec == 0) {
/* 1170 */               gcd_x = prec.c;
/* 1171 */               gcd_y = prec.d;
/*      */             } else {
/* 1173 */               gcd_x = e.b(gcd_x, prec.c);
/* 1174 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1176 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1180 */     }  if (nPrec == 0) {
/* 1181 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1184 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1185 */     int pxend = (maxx - minx) / gcd_x + 1;
/* 1186 */     int y = ty0;
/* 1187 */     int x = tx0;
/* 1188 */     for (int py = 0; py <= pyend; py++) {
/* 1189 */       for (int px = 0; px <= pxend; px++) {
/* 1190 */         for (int m = cs; m < ce; m++) {
/* 1191 */           int mrl = (this.b.e(t, m)).h;
/* 1192 */           for (int r = rs; r < re; r++) {
/* 1193 */             if (r <= mrl && 
/* 1194 */               nextPrec[m][r] < (this.o[t][m][r]).x * (this.o[t][m][r]).y) {
/*      */ 
/*      */ 
/*      */               
/* 1198 */               g prec = this.x.a(t, m, r, nextPrec[m][r]);
/* 1199 */               if (prec.a == x && prec.b == y)
/*      */               
/*      */               { 
/* 1202 */                 for (int l = minlys; l < lye; l++) {
/* 1203 */                   if (r < (lys[m]).length && 
/* 1204 */                     l >= lys[m][r]) {
/*      */ 
/*      */                     
/* 1207 */                     boolean sopUsed = ((String)this.c.getSOP().f(t)).equals("true");
/*      */                     
/* 1209 */                     boolean ephUsed = ((String)this.c.getEPH().f(t)).equals("true");
/*      */                     
/* 1211 */                     o sb = this.b.e(t, m);
/* 1212 */                     for (int n = mrl; n > r; n--) {
/* 1213 */                       sb = sb.u;
/*      */                     }
/*      */                     
/* 1216 */                     float threshold = (this.p[l]).d;
/* 1217 */                     a(l, m, r, t, sb, threshold, nextPrec[m][r]);
/*      */ 
/*      */ 
/*      */                     
/* 1221 */                     hBuff = this.x.a(l + 1, m, r, t, this.m[t][m][r], this.n[t][l][m][r], hBuff, bBuff, nextPrec[m][r]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1227 */                     if (this.x.f()) {
/* 1228 */                       this.e.a(hBuff.c(), hBuff
/* 1229 */                           .b(), false, sopUsed, ephUsed);
/*      */ 
/*      */                       
/* 1232 */                       this.e.a(this.x
/* 1233 */                           .a(), this.x
/*      */                           
/* 1235 */                           .b(), false, this.x
/*      */                           
/* 1237 */                           .g(), this.x
/* 1238 */                           .h());
/*      */                     } 
/*      */                   } 
/* 1241 */                 }  nextPrec[m][r] = nextPrec[m][r] + 1; } 
/*      */             } 
/*      */           } 
/* 1244 */         }  if (px != pxend) {
/* 1245 */           x = minx + px * gcd_x;
/*      */         } else {
/* 1247 */           x = tx0;
/*      */         } 
/*      */       } 
/* 1250 */       if (py != pyend) {
/* 1251 */         y = miny + py * gcd_y;
/*      */       } else {
/* 1253 */         y = ty0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1258 */     for (int k = cs; k < ce; k++) {
/* 1259 */       int mrl = (this.b.e(t, k)).h;
/* 1260 */       for (int r = rs; r < re; r++) {
/* 1261 */         if (r <= mrl && 
/* 1262 */           nextPrec[k][r] < (this.o[t][k][r]).x * (this.o[t][k][r]).y - 1) {
/* 1263 */           throw new Error("JJ2000 bug: One precinct at least has not been written for resolution level " + r + " of component " + k + " in tile " + t + ".");
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
/*      */   public void d(int t, int rs, int re, int cs, int ce, int[][] lys, int lye) throws IOException {
/* 1294 */     int nc = this.b.getNumComps();
/*      */ 
/*      */ 
/*      */     
/* 1298 */     a hBuff = null;
/* 1299 */     byte[] bBuff = null;
/*      */ 
/*      */     
/* 1302 */     Point nTiles = this.b.getNumTiles(null);
/* 1303 */     Point tileI = this.b.getTile(null);
/* 1304 */     int x0siz = this.b.getImgULX();
/* 1305 */     int y0siz = this.b.getImgULY();
/* 1306 */     int xsiz = x0siz + this.b.getImgWidth();
/* 1307 */     int ysiz = y0siz + this.b.getImgHeight();
/* 1308 */     int xt0siz = this.b.getTilePartULX();
/* 1309 */     int yt0siz = this.b.getTilePartULY();
/* 1310 */     int xtsiz = this.b.getNomTileWidth();
/* 1311 */     int ytsiz = this.b.getNomTileHeight();
/* 1312 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1313 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1314 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1315 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1322 */     int gcd_x = 0;
/* 1323 */     int gcd_y = 0;
/* 1324 */     int nPrec = 0;
/* 1325 */     int[][] nextPrec = new int[ce][];
/*      */     
/* 1327 */     int minlys = 100000;
/* 1328 */     int minx = tx1;
/*      */     
/* 1330 */     int miny = ty1;
/*      */     
/* 1332 */     int maxx = tx0;
/* 1333 */     int maxy = ty0;
/* 1334 */     for (int i = cs; i < ce; i++) {
/* 1335 */       int mrl = (this.b.e(t, i)).h;
/* 1336 */       for (int r = rs; r < re; r++) {
/* 1337 */         if (r <= mrl) {
/* 1338 */           nextPrec[i] = new int[mrl + 1];
/* 1339 */           if (r < (lys[i]).length && lys[i][r] < minlys) {
/* 1340 */             minlys = lys[i][r];
/*      */           }
/* 1342 */           int p = (this.o[t][i][r]).y * (this.o[t][i][r]).x - 1;
/* 1343 */           for (; p >= 0; p--) {
/* 1344 */             g prec = this.x.a(t, i, r, p);
/* 1345 */             if (prec.a != tx0) {
/* 1346 */               if (prec.a < minx) minx = prec.a; 
/* 1347 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1349 */             if (prec.b != ty0) {
/* 1350 */               if (prec.b < miny) miny = prec.b; 
/* 1351 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1354 */             if (nPrec == 0) {
/* 1355 */               gcd_x = prec.c;
/* 1356 */               gcd_y = prec.d;
/*      */             } else {
/* 1358 */               gcd_x = e.b(gcd_x, prec.c);
/* 1359 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1361 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1365 */     }  if (nPrec == 0) {
/* 1366 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1369 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1370 */     int pxend = (maxx - minx) / gcd_x + 1;
/*      */     
/*      */     int k;
/* 1373 */     for (k = cs; k < ce; k++) {
/* 1374 */       int y = ty0;
/* 1375 */       int x = tx0;
/* 1376 */       int mrl = (this.b.e(t, k)).h;
/* 1377 */       for (int py = 0; py <= pyend; py++) {
/* 1378 */         for (int px = 0; px <= pxend; px++) {
/* 1379 */           for (int r = rs; r < re; r++) {
/* 1380 */             if (r <= mrl && 
/* 1381 */               nextPrec[k][r] < (this.o[t][k][r]).x * (this.o[t][k][r]).y) {
/*      */ 
/*      */ 
/*      */               
/* 1385 */               g prec = this.x.a(t, k, r, nextPrec[k][r]);
/* 1386 */               if (prec.a == x && prec.b == y)
/*      */               
/*      */               { 
/*      */                 
/* 1390 */                 for (int l = minlys; l < lye; l++) {
/* 1391 */                   if (r < (lys[k]).length && 
/* 1392 */                     l >= lys[k][r]) {
/*      */ 
/*      */                     
/* 1395 */                     boolean sopUsed = ((String)this.c.getSOP().f(t)).equals("true");
/*      */                     
/* 1397 */                     boolean ephUsed = ((String)this.c.getEPH().f(t)).equals("true");
/*      */                     
/* 1399 */                     o sb = this.b.e(t, k);
/* 1400 */                     for (int m = mrl; m > r; m--) {
/* 1401 */                       sb = sb.u;
/*      */                     }
/*      */                     
/* 1404 */                     float threshold = (this.p[l]).d;
/* 1405 */                     a(l, k, r, t, sb, threshold, nextPrec[k][r]);
/*      */ 
/*      */                     
/* 1408 */                     hBuff = this.x.a(l + 1, k, r, t, this.m[t][k][r], this.n[t][l][k][r], hBuff, bBuff, nextPrec[k][r]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1414 */                     if (this.x.f()) {
/* 1415 */                       this.e.a(hBuff.c(), hBuff
/* 1416 */                           .b(), false, sopUsed, ephUsed);
/*      */ 
/*      */                       
/* 1419 */                       this.e.a(this.x
/* 1420 */                           .a(), this.x
/*      */                           
/* 1422 */                           .b(), false, this.x
/*      */                           
/* 1424 */                           .g(), this.x
/* 1425 */                           .h());
/*      */                     } 
/*      */                   } 
/*      */                 } 
/* 1429 */                 nextPrec[k][r] = nextPrec[k][r] + 1; } 
/*      */             } 
/* 1431 */           }  if (px != pxend) {
/* 1432 */             x = minx + px * gcd_x;
/*      */           } else {
/* 1434 */             x = tx0;
/*      */           } 
/*      */         } 
/* 1437 */         if (py != pyend) {
/* 1438 */           y = miny + py * gcd_y;
/*      */         } else {
/* 1440 */           y = ty0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1446 */     for (k = cs; k < ce; k++) {
/* 1447 */       int mrl = (this.b.e(t, k)).h;
/* 1448 */       for (int r = rs; r < re; r++) {
/* 1449 */         if (r <= mrl && 
/* 1450 */           nextPrec[k][r] < (this.o[t][k][r]).x * (this.o[t][k][r]).y - 1) {
/* 1451 */           throw new Error("JJ2000 bug: One precinct at least has not been written for resolution level " + r + " of component " + k + " in tile " + t + ".");
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
/*      */   public void e(int t, int rs, int re, int cs, int ce, int[][] lys, int lye) throws IOException {
/* 1482 */     int nc = this.b.getNumComps();
/*      */ 
/*      */ 
/*      */     
/* 1486 */     a hBuff = null;
/* 1487 */     byte[] bBuff = null;
/*      */ 
/*      */     
/* 1490 */     Point nTiles = this.b.getNumTiles(null);
/* 1491 */     Point tileI = this.b.getTile(null);
/* 1492 */     int x0siz = this.b.getImgULX();
/* 1493 */     int y0siz = this.b.getImgULY();
/* 1494 */     int xsiz = x0siz + this.b.getImgWidth();
/* 1495 */     int ysiz = y0siz + this.b.getImgHeight();
/* 1496 */     int xt0siz = this.b.getTilePartULX();
/* 1497 */     int yt0siz = this.b.getTilePartULY();
/* 1498 */     int xtsiz = this.b.getNomTileWidth();
/* 1499 */     int ytsiz = this.b.getNomTileHeight();
/* 1500 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1501 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1502 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1503 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1510 */     int gcd_x = 0;
/* 1511 */     int gcd_y = 0;
/* 1512 */     int nPrec = 0;
/* 1513 */     int[][] nextPrec = new int[ce][];
/*      */     
/* 1515 */     int minlys = 100000;
/* 1516 */     int minx = tx1;
/*      */     
/* 1518 */     int miny = ty1;
/*      */     
/* 1520 */     int maxx = tx0;
/* 1521 */     int maxy = ty0;
/* 1522 */     for (int i = cs; i < ce; i++) {
/* 1523 */       int mrl = (this.b.e(t, i)).h;
/* 1524 */       nextPrec[i] = new int[mrl + 1];
/* 1525 */       for (int m = rs; m < re; m++) {
/* 1526 */         if (m <= mrl) {
/* 1527 */           if (m < (lys[i]).length && lys[i][m] < minlys) {
/* 1528 */             minlys = lys[i][m];
/*      */           }
/* 1530 */           int p = (this.o[t][i][m]).y * (this.o[t][i][m]).x - 1;
/* 1531 */           for (; p >= 0; p--) {
/* 1532 */             g prec = this.x.a(t, i, m, p);
/* 1533 */             if (prec.a != tx0) {
/* 1534 */               if (prec.a < minx) minx = prec.a; 
/* 1535 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1537 */             if (prec.b != ty0) {
/* 1538 */               if (prec.b < miny) miny = prec.b; 
/* 1539 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1542 */             if (nPrec == 0) {
/* 1543 */               gcd_x = prec.c;
/* 1544 */               gcd_y = prec.d;
/*      */             } else {
/* 1546 */               gcd_x = e.b(gcd_x, prec.c);
/* 1547 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1549 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1554 */     if (nPrec == 0) {
/* 1555 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1558 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1559 */     int pxend = (maxx - minx) / gcd_x + 1;
/*      */     
/* 1561 */     for (int r = rs; r < re; r++) {
/* 1562 */       int y = ty0;
/* 1563 */       int x = tx0;
/* 1564 */       for (int py = 0; py <= pyend; py++) {
/* 1565 */         for (int px = 0; px <= pxend; px++) {
/* 1566 */           for (int m = cs; m < ce; m++) {
/* 1567 */             int mrl = (this.b.e(t, m)).h;
/* 1568 */             if (r <= mrl && 
/* 1569 */               nextPrec[m][r] < (this.o[t][m][r]).x * (this.o[t][m][r]).y) {
/*      */ 
/*      */ 
/*      */               
/* 1573 */               g prec = this.x.a(t, m, r, nextPrec[m][r]);
/* 1574 */               if (prec.a == x && prec.b == y)
/*      */               
/*      */               { 
/* 1577 */                 for (int l = minlys; l < lye; l++) {
/* 1578 */                   if (r < (lys[m]).length && 
/* 1579 */                     l >= lys[m][r]) {
/*      */ 
/*      */                     
/* 1582 */                     boolean sopUsed = ((String)this.c.getSOP().f(t)).equals("true");
/*      */                     
/* 1584 */                     boolean ephUsed = ((String)this.c.getEPH().f(t)).equals("true");
/*      */                     
/* 1586 */                     o sb = this.b.e(t, m);
/* 1587 */                     for (int n = mrl; n > r; n--) {
/* 1588 */                       sb = sb.u;
/*      */                     }
/*      */                     
/* 1591 */                     float threshold = (this.p[l]).d;
/* 1592 */                     a(l, m, r, t, sb, threshold, nextPrec[m][r]);
/*      */ 
/*      */                     
/* 1595 */                     hBuff = this.x.a(l + 1, m, r, t, this.m[t][m][r], this.n[t][l][m][r], hBuff, bBuff, nextPrec[m][r]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1601 */                     if (this.x.f()) {
/* 1602 */                       this.e.a(hBuff.c(), hBuff
/* 1603 */                           .b(), false, sopUsed, ephUsed);
/*      */ 
/*      */                       
/* 1606 */                       this.e.a(this.x
/* 1607 */                           .a(), this.x
/*      */                           
/* 1609 */                           .b(), false, this.x
/*      */                           
/* 1611 */                           .g(), this.x
/* 1612 */                           .h());
/*      */                     } 
/*      */                   } 
/*      */                 } 
/* 1616 */                 nextPrec[m][r] = nextPrec[m][r] + 1; } 
/*      */             } 
/* 1618 */           }  if (px != pxend) {
/* 1619 */             x = minx + px * gcd_x;
/*      */           } else {
/* 1621 */             x = tx0;
/*      */           } 
/*      */         } 
/* 1624 */         if (py != pyend) {
/* 1625 */           y = miny + py * gcd_y;
/*      */         } else {
/* 1627 */           y = ty0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1633 */     for (int k = cs; k < ce; k++) {
/* 1634 */       int mrl = (this.b.e(t, k)).h;
/* 1635 */       for (int m = rs; m < re; m++) {
/* 1636 */         if (m <= mrl && 
/* 1637 */           nextPrec[k][m] < (this.o[t][k][m]).x * (this.o[t][k][m]).y - 1) {
/* 1638 */           throw new Error("JJ2000 bug: One precinct at least has not been written for resolution level " + m + " of component " + k + " in tile " + t + ".");
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
/*      */   private float a(int layerIdx, float fmaxt, int maxBytes, int prevBytes) throws IOException {
/* 1688 */     this.x.c();
/*      */     
/* 1690 */     int nt = this.b.getNumTiles();
/* 1691 */     int nc = this.b.getNumComps();
/* 1692 */     a hBuff = null;
/* 1693 */     byte[] bBuff = null;
/*      */ 
/*      */ 
/*      */     
/*      */     int sidx;
/*      */ 
/*      */ 
/*      */     
/* 1701 */     for (sidx = 63; sidx > 0 && 
/* 1702 */       this.w[sidx] < maxBytes; sidx--);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1707 */     float fmint = a(sidx);
/*      */     
/* 1709 */     if (fmint >= fmaxt) {
/* 1710 */       sidx--;
/* 1711 */       fmint = a(sidx);
/*      */     } 
/*      */ 
/*      */     
/* 1715 */     if (sidx <= 0) fmint = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1731 */     float ft = (fmaxt + fmint) / 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1736 */     if (ft <= fmint) ft = fmaxt;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 1741 */       int actualBytes = prevBytes;
/* 1742 */       this.b.setTile(0, 0);
/*      */       
/* 1744 */       for (int t = 0; t < nt; t++) {
/* 1745 */         for (int i = 0; i < nc; i++) {
/*      */           
/* 1747 */           boolean sopUsed = ((String)this.c.getSOP().f(t)).equalsIgnoreCase("true");
/*      */           
/* 1749 */           boolean ephUsed = ((String)this.c.getEPH().f(t)).equalsIgnoreCase("true");
/*      */ 
/*      */           
/* 1752 */           o sb = this.b.e(t, i);
/* 1753 */           int numLvls = sb.h + 1;
/* 1754 */           sb = (o)sb.a(0, 0);
/*      */           
/* 1756 */           for (int r = 0; r < numLvls; r++) {
/*      */             
/* 1758 */             int nPrec = (this.o[t][i][r]).x * (this.o[t][i][r]).y;
/* 1759 */             for (int p = 0; p < nPrec; p++) {
/*      */               
/* 1761 */               a(layerIdx, i, r, t, sb, ft, p);
/* 1762 */               hBuff = this.x.a(layerIdx + 1, i, r, t, this.m[t][i][r], this.n[t][layerIdx][i][r], hBuff, bBuff, p);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1767 */               if (this.x.f()) {
/* 1768 */                 bBuff = this.x.a();
/* 1769 */                 actualBytes += this.e
/* 1770 */                   .a(hBuff.c(), hBuff
/* 1771 */                     .b(), true, sopUsed, ephUsed);
/*      */                 
/* 1773 */                 actualBytes += this.e
/* 1774 */                   .a(bBuff, this.x
/* 1775 */                     .b(), true, this.x
/* 1776 */                     .g(), this.x
/* 1777 */                     .h());
/*      */               } 
/*      */             } 
/* 1780 */             sb = sb.t;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1786 */       if (actualBytes > maxBytes) {
/*      */ 
/*      */         
/* 1789 */         fmint = ft;
/*      */       }
/*      */       else {
/*      */         
/* 1793 */         fmaxt = ft;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1798 */       ft = (fmaxt + fmint) / 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1803 */       if (ft <= fmint) ft = fmaxt;
/*      */ 
/*      */       
/* 1806 */       this.x.d();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1812 */     while (ft < fmaxt * 0.9999F && ft < fmaxt - 1.0E-10F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1819 */     if (ft <= 1.0E-10F) {
/* 1820 */       ft = 0.0F;
/*      */     }
/*      */     else {
/*      */       
/* 1824 */       ft = fmaxt;
/*      */     } 
/* 1826 */     return ft;
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
/*      */   private float a(int targetBytes, e lastLayer) {
/* 1874 */     float log_len1, log_len2, log_ab, lthresh = lastLayer.d;
/* 1875 */     if (lthresh > this.z) lthresh = this.z;
/*      */ 
/*      */     
/* 1878 */     if (lthresh < 1.0E-10F) return 0.0F; 
/* 1879 */     int sidx = a(lthresh);
/*      */ 
/*      */     
/* 1882 */     if (sidx >= 63) sidx = 62;
/*      */ 
/*      */ 
/*      */     
/* 1886 */     if (this.w[sidx + 1] == 0) {
/*      */ 
/*      */ 
/*      */       
/* 1890 */       log_len1 = (float)Math.log(((this.w[sidx] << 1) + 1));
/* 1891 */       log_len2 = (float)Math.log((this.w[sidx] + 1));
/* 1892 */       log_ab = (float)Math.log((lastLayer.b + this.w[sidx] + 1));
/*      */     }
/*      */     else {
/*      */       
/* 1896 */       log_len1 = (float)Math.log(this.w[sidx]);
/* 1897 */       log_len2 = (float)Math.log(this.w[sidx + 1]);
/* 1898 */       log_ab = (float)Math.log(lastLayer.b);
/*      */     } 
/*      */     
/* 1901 */     float log_sl1 = (float)Math.log(a(sidx));
/* 1902 */     float log_sl2 = (float)Math.log(a(sidx + 1));
/*      */     
/* 1904 */     float log_isl = (float)Math.log(lthresh);
/*      */     
/* 1906 */     float log_ilen = log_len1 + (log_isl - log_sl1) * (log_len1 - log_len2) / (log_sl1 - log_sl2);
/*      */ 
/*      */     
/* 1909 */     float log_off = log_ab - log_ilen;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1914 */     if (log_off < 0.0F) log_off = 0.0F;
/*      */ 
/*      */ 
/*      */     
/* 1918 */     int tlen = (int)(targetBytes / (float)Math.exp(log_off));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1925 */     for (sidx = 63; sidx >= 0 && 
/* 1926 */       this.w[sidx] < tlen; sidx--);
/*      */     
/* 1928 */     sidx++;
/*      */     
/* 1930 */     if (sidx >= 64) sidx = 63; 
/* 1931 */     if (sidx <= 0) sidx = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1936 */     if (this.w[sidx] == 0) {
/*      */ 
/*      */ 
/*      */       
/* 1940 */       log_len1 = (float)Math.log((this.w[sidx - 1] + 1));
/* 1941 */       log_len2 = (float)Math.log(((this.w[sidx - 1] << 1) + 1));
/* 1942 */       log_ilen = (float)Math.log((tlen + this.w[sidx - 1] + 1));
/*      */     }
/*      */     else {
/*      */       
/* 1946 */       log_len1 = (float)Math.log(this.w[sidx]);
/* 1947 */       log_len2 = (float)Math.log(this.w[sidx - 1]);
/* 1948 */       log_ilen = (float)Math.log(tlen);
/*      */     } 
/*      */     
/* 1951 */     log_sl1 = (float)Math.log(a(sidx));
/* 1952 */     log_sl2 = (float)Math.log(a(sidx - 1));
/*      */ 
/*      */ 
/*      */     
/* 1956 */     log_isl = log_sl1 + (log_ilen - log_len1) * (log_sl1 - log_sl2) / (log_len1 - log_len2);
/*      */ 
/*      */     
/* 1959 */     float eth = (float)Math.exp(log_isl);
/*      */ 
/*      */     
/* 1962 */     if (eth > lthresh) eth = lthresh; 
/* 1963 */     if (eth < 1.0E-10F) eth = 0.0F;
/*      */ 
/*      */     
/* 1966 */     return eth;
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
/*      */   private void a(int layerIdx, int compIdx, int lvlIdx, int tileIdx, o subb, float fthresh, int precinctIdx) {
/* 1993 */     Point ncblks = null;
/*      */ 
/*      */     
/* 1996 */     g prec = this.x.a(tileIdx, compIdx, lvlIdx, precinctIdx);
/*      */ 
/*      */     
/* 1999 */     o sb = subb;
/* 2000 */     while (sb.x != null) {
/* 2001 */       sb = sb.x;
/*      */     }
/* 2003 */     int minsbi = (lvlIdx == 0) ? 0 : 1;
/* 2004 */     int maxsbi = (lvlIdx == 0) ? 1 : 4;
/*      */ 
/*      */ 
/*      */     
/* 2008 */     sb = (o)subb.a(lvlIdx, minsbi);
/* 2009 */     for (int s = minsbi; s < maxsbi; s++) {
/* 2010 */       int yend = (prec.j[s] != null) ? (prec.j[s]).length : 0;
/* 2011 */       for (int y = 0; y < yend; y++) {
/* 2012 */         int xend = (prec.j[s][y] != null) ? (prec.j[s][y]).length : 0;
/* 2013 */         for (int x = 0; x < xend; x++) {
/* 2014 */           Point cbCoord = (prec.j[s][y][x]).a;
/* 2015 */           int b = cbCoord.x + cbCoord.y * sb.i.x;
/*      */           
/* 2017 */           c cur_cblk = this.m[tileIdx][compIdx][lvlIdx][s][b]; int n;
/* 2018 */           for (n = 0; n < cur_cblk.g && 
/* 2019 */             cur_cblk.j[n] >= fthresh; n++);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2027 */           this.n[tileIdx][layerIdx][compIdx][lvlIdx][s][b] = n - 1;
/*      */         } 
/*      */       } 
/*      */       
/* 2031 */       sb = (o)sb.g();
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
/*      */   private static int a(float slope) {
/* 2052 */     int idx = (int)Math.floor(Math.log(slope) / q) + 24;
/*      */     
/* 2054 */     if (idx < 0) {
/* 2055 */       return 0;
/*      */     }
/* 2057 */     if (idx >= 64) {
/* 2058 */       return 63;
/*      */     }
/*      */     
/* 2061 */     return idx;
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
/*      */   private static float a(int index) {
/* 2074 */     return (float)Math.pow(2.0D, (index - 24));
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */