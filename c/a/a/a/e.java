/*      */ package c.a.a.a;
/*      */ 
/*      */ import c.a.a.a;
/*      */ import c.a.a.g;
/*      */ import c.a.b.a;
/*      */ import c.a.c.f;
/*      */ import c.a.f.f;
/*      */ import c.a.i.a;
/*      */ import c.a.j.b.i;
/*      */ import java.awt.Point;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
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
/*      */ public class e
/*      */   implements f
/*      */ {
/*      */   private a a;
/*      */   private boolean b = false;
/*      */   private ByteArrayInputStream c;
/*      */   private a d;
/*      */   private d e;
/*   86 */   private final int f = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private f g;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private f h;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Point[][] i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int j;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private g[][][] y;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][][][][] z;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private h[][][][] A;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private h[][][][] B;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private int C = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int D;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean E = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean F = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int G;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector[] H;
/*      */ 
/*      */ 
/*      */   
/*      */   private int I;
/*      */ 
/*      */ 
/*      */   
/*      */   private int J;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean K;
/*      */ 
/*      */ 
/*      */   
/*      */   private int L;
/*      */ 
/*      */ 
/*      */   
/*      */   private int M;
/*      */ 
/*      */ 
/*      */   
/*      */   private int N;
/*      */ 
/*      */ 
/*      */   
/*      */   private int O;
/*      */ 
/*      */ 
/*      */   
/*      */   private int P;
/*      */ 
/*      */ 
/*      */   
/*      */   private int Q;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean R;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public e(a decSpec, d hd, f ehs, a src, boolean isTruncMode, int maxCB) {
/*  226 */     this.d = decSpec;
/*  227 */     this.e = hd;
/*  228 */     this.h = ehs;
/*  229 */     this.R = isTruncMode;
/*  230 */     this.g = new f(ehs);
/*  231 */     this.a = src;
/*  232 */     this.I = 0;
/*  233 */     this.K = false;
/*  234 */     this.J = maxCB;
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
/*      */   public b[][][][][] a(int nc, int[] mdl, int nl, b[][][][][] cbI, boolean pph, ByteArrayInputStream pphbais) {
/*  256 */     this.D = nc;
/*  257 */     this.C = nl;
/*  258 */     this.j = this.a.e();
/*  259 */     this.b = pph;
/*  260 */     this.c = pphbais;
/*      */     
/*  262 */     this.E = ((Boolean)this.d.o.f(this.j)).booleanValue();
/*  263 */     this.G = 0;
/*  264 */     this.F = ((Boolean)this.d.p.f(this.j)).booleanValue();
/*      */     
/*  266 */     cbI = new b[nc][][][][];
/*  267 */     this.z = new int[nc][][][][];
/*  268 */     this.A = new h[nc][][][];
/*  269 */     this.B = new h[nc][][][];
/*  270 */     this.i = new Point[nc][];
/*  271 */     this.y = new g[nc][][];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  283 */     Point nBlk = null;
/*  284 */     int cb0x = this.a.a();
/*  285 */     int cb0y = this.a.b();
/*      */     
/*  287 */     for (int c = 0; c < nc; c++) {
/*  288 */       cbI[c] = new b[mdl[c] + 1][][][];
/*  289 */       this.z[c] = new int[mdl[c] + 1][][][];
/*  290 */       this.A[c] = new h[mdl[c] + 1][][];
/*  291 */       this.B[c] = new h[mdl[c] + 1][][];
/*  292 */       this.i[c] = new Point[mdl[c] + 1];
/*  293 */       this.y[c] = new g[mdl[c] + 1][];
/*      */ 
/*      */       
/*  296 */       int tcx0 = this.a.d(c, mdl[c]);
/*  297 */       int tcy0 = this.a.e(c, mdl[c]);
/*  298 */       int tcx1 = tcx0 + this.a.a(this.j, c, mdl[c]);
/*  299 */       int tcy1 = tcy0 + this.a.b(this.j, c, mdl[c]);
/*      */       
/*  301 */       for (int r = 0; r <= mdl[c]; r++) {
/*      */ 
/*      */         
/*  304 */         int trx0 = (int)Math.ceil(tcx0 / (1 << mdl[c] - r));
/*  305 */         int try0 = (int)Math.ceil(tcy0 / (1 << mdl[c] - r));
/*  306 */         int trx1 = (int)Math.ceil(tcx1 / (1 << mdl[c] - r));
/*  307 */         int try1 = (int)Math.ceil(tcy1 / (1 << mdl[c] - r));
/*      */ 
/*      */ 
/*      */         
/*  311 */         double twoppx = a(this.j, c, r);
/*  312 */         double twoppy = b(this.j, c, r);
/*  313 */         this.i[c][r] = new Point();
/*  314 */         if (trx1 > trx0) {
/*  315 */           (this.i[c][r])
/*  316 */             .x = (int)Math.ceil((trx1 - cb0x) / twoppx) - (int)Math.floor((trx0 - cb0x) / twoppx);
/*      */         } else {
/*  318 */           (this.i[c][r]).x = 0;
/*      */         } 
/*  320 */         if (try1 > try0) {
/*  321 */           (this.i[c][r])
/*  322 */             .y = (int)Math.ceil((try1 - cb0y) / twoppy) - (int)Math.floor((try0 - cb0y) / twoppy);
/*      */         } else {
/*  324 */           (this.i[c][r]).y = 0;
/*      */         } 
/*      */ 
/*      */         
/*  328 */         int mins = (r == 0) ? 0 : 1;
/*  329 */         int maxs = (r == 0) ? 1 : 4;
/*      */         
/*  331 */         int maxPrec = (this.i[c][r]).x * (this.i[c][r]).y;
/*      */         
/*  333 */         this.A[c][r] = new h[maxPrec][maxs + 1];
/*  334 */         this.B[c][r] = new h[maxPrec][maxs + 1];
/*  335 */         cbI[c][r] = new b[maxs + 1][][];
/*  336 */         this.z[c][r] = new int[maxs + 1][][];
/*      */         
/*  338 */         this.y[c][r] = new g[maxPrec];
/*  339 */         d(c, r, mdl[c]);
/*      */         
/*  341 */         i root = this.a.f(this.j, c);
/*  342 */         for (int s = mins; s < maxs; s++) {
/*  343 */           i sb = (i)root.a(r, s);
/*  344 */           nBlk = sb.i;
/*      */           
/*  346 */           cbI[c][r][s] = new b[nBlk.y][nBlk.x];
/*  347 */           this.z[c][r][s] = new int[nBlk.y][nBlk.x];
/*      */           
/*  349 */           for (int i = nBlk.y - 1; i >= 0; i--) {
/*  350 */             a.a(this.z[c][r][s][i], 3);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  356 */     return cbI;
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
/*      */   private void d(int c, int r, int mdl) {
/*  370 */     if ((this.y[c][r]).length == 0) {
/*      */       return;
/*      */     }
/*  373 */     Point tileI = this.a.a((Point)null);
/*  374 */     Point nTiles = this.a.b((Point)null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  380 */     int xt0siz = this.a.m();
/*  381 */     int yt0siz = this.a.n();
/*  382 */     int xtsiz = this.a.o();
/*  383 */     int ytsiz = this.a.p();
/*  384 */     int x0siz = this.e.e();
/*  385 */     int y0siz = this.e.f();
/*  386 */     int xsiz = this.e.c();
/*  387 */     int ysiz = this.e.d();
/*      */     
/*  389 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/*  390 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/*  391 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/*  392 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */     
/*  394 */     int xrsiz = this.e.c(c);
/*  395 */     int yrsiz = this.e.d(c);
/*      */     
/*  397 */     int tcx0 = this.a.d(c, mdl);
/*  398 */     int tcy0 = this.a.e(c, mdl);
/*  399 */     int tcx1 = tcx0 + this.a.a(this.j, c, mdl);
/*  400 */     int tcy1 = tcy0 + this.a.b(this.j, c, mdl);
/*      */     
/*  402 */     int ndl = mdl - r;
/*  403 */     int trx0 = (int)Math.ceil(tcx0 / (1 << ndl));
/*  404 */     int try0 = (int)Math.ceil(tcy0 / (1 << ndl));
/*  405 */     int trx1 = (int)Math.ceil(tcx1 / (1 << ndl));
/*  406 */     int try1 = (int)Math.ceil(tcy1 / (1 << ndl));
/*      */     
/*  408 */     int cb0x = this.a.a();
/*  409 */     int cb0y = this.a.b();
/*      */     
/*  411 */     double twoppx = a(this.j, c, r);
/*  412 */     double twoppy = b(this.j, c, r);
/*  413 */     int twoppx2 = (int)(twoppx / 2.0D);
/*  414 */     int twoppy2 = (int)(twoppy / 2.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  419 */     int maxPrec = (this.y[c][r]).length;
/*  420 */     int nPrec = 0;
/*      */     
/*  422 */     int istart = (int)Math.floor((try0 - cb0y) / twoppy);
/*  423 */     int iend = (int)Math.floor((try1 - 1 - cb0y) / twoppy);
/*  424 */     int jstart = (int)Math.floor((trx0 - cb0x) / twoppx);
/*  425 */     int jend = (int)Math.floor((trx1 - 1 - cb0x) / twoppx);
/*      */ 
/*      */ 
/*      */     
/*  429 */     i root = this.a.f(this.j, c);
/*  430 */     i sb = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  437 */     int prg_w = (int)twoppx << ndl;
/*  438 */     int prg_h = (int)twoppy << ndl;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  443 */     for (int i = istart; i <= iend; i++) {
/*  444 */       for (int j = jstart; j <= jend; j++, nPrec++) {
/*  445 */         int prg_ulx, prg_uly; if (j == jstart && (trx0 - cb0x) % xrsiz * (int)twoppx != 0) {
/*  446 */           prg_ulx = tx0;
/*      */         } else {
/*  448 */           prg_ulx = cb0x + j * xrsiz * ((int)twoppx << ndl);
/*      */         } 
/*  450 */         if (i == istart && (try0 - cb0y) % yrsiz * (int)twoppy != 0) {
/*  451 */           prg_uly = ty0;
/*      */         } else {
/*  453 */           prg_uly = cb0y + i * yrsiz * ((int)twoppy << ndl);
/*      */         } 
/*      */         
/*  456 */         this.y[c][r][nPrec] = new g(r, (int)(cb0x + j * twoppx), (int)(cb0y + i * twoppy), (int)twoppx, (int)twoppy, prg_ulx, prg_uly, prg_w, prg_h);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  461 */         if (r == 0) {
/*  462 */           int acb0x = cb0x;
/*  463 */           int acb0y = cb0y;
/*      */           
/*  465 */           int p0x = acb0x + j * (int)twoppx;
/*  466 */           int p1x = p0x + (int)twoppx;
/*  467 */           int p0y = acb0y + i * (int)twoppy;
/*  468 */           int p1y = p0y + (int)twoppy;
/*      */           
/*  470 */           sb = (i)root.a(0, 0);
/*  471 */           int s0x = (p0x < sb.l) ? sb.l : p0x;
/*  472 */           int s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  473 */           int s0y = (p0y < sb.m) ? sb.m : p0y;
/*  474 */           int s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  477 */           int cw = sb.r;
/*  478 */           int ch = sb.s;
/*  479 */           int k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  480 */           int kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  481 */           int kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  482 */           int l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  483 */           int lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  484 */           int lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  486 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  487 */             (this.y[c][r][nPrec]).k[0] = 0;
/*  488 */             this.A[c][r][nPrec][0] = new h(0, 0);
/*  489 */             this.B[c][r][nPrec][0] = new h(0, 0);
/*      */           } else {
/*  491 */             this.A[c][r][nPrec][0] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  493 */             this.B[c][r][nPrec][0] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  495 */             (this.y[c][r][nPrec]).j[0] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  497 */             (this.y[c][r][nPrec]).k[0] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  500 */             for (int k = kstart; k <= kend; k++) {
/*  501 */               for (int l = lstart; l <= lend; l++) {
/*  502 */                 a cb = new a(k - k0, l - l0);
/*  503 */                 if (l == l0) {
/*  504 */                   cb.b = sb.n;
/*      */                 } else {
/*  506 */                   cb.b = sb.n + l * cw - sb.l - acb0x;
/*      */                 } 
/*  508 */                 if (k == k0) {
/*  509 */                   cb.c = sb.o;
/*      */                 } else {
/*  511 */                   cb.c = sb.o + k * ch - sb.m - acb0y;
/*      */                 } 
/*  513 */                 int tmp1 = acb0x + l * cw;
/*  514 */                 tmp1 = (tmp1 > sb.l) ? tmp1 : sb.l;
/*  515 */                 int tmp2 = acb0x + (l + 1) * cw;
/*  516 */                 tmp2 = (tmp2 > sb.l + sb.p) ? (sb.l + sb.p) : tmp2;
/*      */                 
/*  518 */                 cb.d = tmp2 - tmp1;
/*  519 */                 tmp1 = acb0y + k * ch;
/*  520 */                 tmp1 = (tmp1 > sb.m) ? tmp1 : sb.m;
/*  521 */                 tmp2 = acb0y + (k + 1) * ch;
/*  522 */                 tmp2 = (tmp2 > sb.m + sb.q) ? (sb.m + sb.q) : tmp2;
/*      */                 
/*  524 */                 cb.e = tmp2 - tmp1;
/*  525 */                 (this.y[c][r][nPrec]).j[0][k - kstart][l - lstart] = cb;
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/*  532 */           int acb0x = 0;
/*  533 */           int acb0y = cb0y;
/*      */           
/*  535 */           int p0x = acb0x + j * twoppx2;
/*  536 */           int p1x = p0x + twoppx2;
/*  537 */           int p0y = acb0y + i * twoppy2;
/*  538 */           int p1y = p0y + twoppy2;
/*      */           
/*  540 */           sb = (i)root.a(r, 1);
/*  541 */           int s0x = (p0x < sb.l) ? sb.l : p0x;
/*  542 */           int s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  543 */           int s0y = (p0y < sb.m) ? sb.m : p0y;
/*  544 */           int s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  547 */           int cw = sb.r;
/*  548 */           int ch = sb.s;
/*  549 */           int k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  550 */           int kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  551 */           int kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  552 */           int l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  553 */           int lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  554 */           int lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  556 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  557 */             (this.y[c][r][nPrec]).k[1] = 0;
/*  558 */             this.A[c][r][nPrec][1] = new h(0, 0);
/*  559 */             this.B[c][r][nPrec][1] = new h(0, 0);
/*      */           } else {
/*  561 */             this.A[c][r][nPrec][1] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  563 */             this.B[c][r][nPrec][1] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  565 */             (this.y[c][r][nPrec]).j[1] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  567 */             (this.y[c][r][nPrec]).k[1] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  570 */             for (int k = kstart; k <= kend; k++) {
/*  571 */               for (int l = lstart; l <= lend; l++) {
/*  572 */                 a cb = new a(k - k0, l - l0);
/*  573 */                 if (l == l0) {
/*  574 */                   cb.b = sb.n;
/*      */                 } else {
/*  576 */                   cb.b = sb.n + l * cw - sb.l - acb0x;
/*      */                 } 
/*  578 */                 if (k == k0) {
/*  579 */                   cb.c = sb.o;
/*      */                 } else {
/*  581 */                   cb.c = sb.o + k * ch - sb.m - acb0y;
/*      */                 } 
/*  583 */                 int tmp1 = acb0x + l * cw;
/*  584 */                 tmp1 = (tmp1 > sb.l) ? tmp1 : sb.l;
/*  585 */                 int tmp2 = acb0x + (l + 1) * cw;
/*  586 */                 tmp2 = (tmp2 > sb.l + sb.p) ? (sb.l + sb.p) : tmp2;
/*      */                 
/*  588 */                 cb.d = tmp2 - tmp1;
/*  589 */                 tmp1 = acb0y + k * ch;
/*  590 */                 tmp1 = (tmp1 > sb.m) ? tmp1 : sb.m;
/*  591 */                 tmp2 = acb0y + (k + 1) * ch;
/*  592 */                 tmp2 = (tmp2 > sb.m + sb.q) ? (sb.m + sb.q) : tmp2;
/*      */                 
/*  594 */                 cb.e = tmp2 - tmp1;
/*  595 */                 (this.y[c][r][nPrec]).j[1][k - kstart][l - lstart] = cb;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  602 */           acb0x = cb0x;
/*  603 */           acb0y = 0;
/*      */           
/*  605 */           p0x = acb0x + j * twoppx2;
/*  606 */           p1x = p0x + twoppx2;
/*  607 */           p0y = acb0y + i * twoppy2;
/*  608 */           p1y = p0y + twoppy2;
/*      */           
/*  610 */           sb = (i)root.a(r, 2);
/*  611 */           s0x = (p0x < sb.l) ? sb.l : p0x;
/*  612 */           s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  613 */           s0y = (p0y < sb.m) ? sb.m : p0y;
/*  614 */           s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  617 */           cw = sb.r;
/*  618 */           ch = sb.s;
/*  619 */           k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  620 */           kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  621 */           kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  622 */           l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  623 */           lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  624 */           lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  626 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  627 */             (this.y[c][r][nPrec]).k[2] = 0;
/*  628 */             this.A[c][r][nPrec][2] = new h(0, 0);
/*  629 */             this.B[c][r][nPrec][2] = new h(0, 0);
/*      */           } else {
/*  631 */             this.A[c][r][nPrec][2] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  633 */             this.B[c][r][nPrec][2] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  635 */             (this.y[c][r][nPrec]).j[2] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  637 */             (this.y[c][r][nPrec]).k[2] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  640 */             for (int k = kstart; k <= kend; k++) {
/*  641 */               for (int l = lstart; l <= lend; l++) {
/*  642 */                 a cb = new a(k - k0, l - l0);
/*  643 */                 if (l == l0) {
/*  644 */                   cb.b = sb.n;
/*      */                 } else {
/*  646 */                   cb.b = sb.n + l * cw - sb.l - acb0x;
/*      */                 } 
/*  648 */                 if (k == k0) {
/*  649 */                   cb.c = sb.o;
/*      */                 } else {
/*  651 */                   cb.c = sb.o + k * ch - sb.m - acb0y;
/*      */                 } 
/*  653 */                 int tmp1 = acb0x + l * cw;
/*  654 */                 tmp1 = (tmp1 > sb.l) ? tmp1 : sb.l;
/*  655 */                 int tmp2 = acb0x + (l + 1) * cw;
/*  656 */                 tmp2 = (tmp2 > sb.l + sb.p) ? (sb.l + sb.p) : tmp2;
/*      */                 
/*  658 */                 cb.d = tmp2 - tmp1;
/*  659 */                 tmp1 = acb0y + k * ch;
/*  660 */                 tmp1 = (tmp1 > sb.m) ? tmp1 : sb.m;
/*  661 */                 tmp2 = acb0y + (k + 1) * ch;
/*  662 */                 tmp2 = (tmp2 > sb.m + sb.q) ? (sb.m + sb.q) : tmp2;
/*      */                 
/*  664 */                 cb.e = tmp2 - tmp1;
/*  665 */                 (this.y[c][r][nPrec]).j[2][k - kstart][l - lstart] = cb;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  672 */           acb0x = 0;
/*  673 */           acb0y = 0;
/*      */           
/*  675 */           p0x = acb0x + j * twoppx2;
/*  676 */           p1x = p0x + twoppx2;
/*  677 */           p0y = acb0y + i * twoppy2;
/*  678 */           p1y = p0y + twoppy2;
/*      */           
/*  680 */           sb = (i)root.a(r, 3);
/*  681 */           s0x = (p0x < sb.l) ? sb.l : p0x;
/*  682 */           s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  683 */           s0y = (p0y < sb.m) ? sb.m : p0y;
/*  684 */           s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  687 */           cw = sb.r;
/*  688 */           ch = sb.s;
/*  689 */           k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  690 */           kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  691 */           kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  692 */           l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  693 */           lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  694 */           lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  696 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  697 */             (this.y[c][r][nPrec]).k[3] = 0;
/*  698 */             this.A[c][r][nPrec][3] = new h(0, 0);
/*  699 */             this.B[c][r][nPrec][3] = new h(0, 0);
/*      */           } else {
/*  701 */             this.A[c][r][nPrec][3] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  703 */             this.B[c][r][nPrec][3] = new h(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  705 */             (this.y[c][r][nPrec]).j[3] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  707 */             (this.y[c][r][nPrec]).k[3] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  710 */             for (int k = kstart; k <= kend; k++) {
/*  711 */               for (int l = lstart; l <= lend; l++) {
/*  712 */                 a cb = new a(k - k0, l - l0);
/*  713 */                 if (l == l0) {
/*  714 */                   cb.b = sb.n;
/*      */                 } else {
/*  716 */                   cb.b = sb.n + l * cw - sb.l - acb0x;
/*      */                 } 
/*  718 */                 if (k == k0) {
/*  719 */                   cb.c = sb.o;
/*      */                 } else {
/*  721 */                   cb.c = sb.o + k * ch - sb.m - acb0y;
/*      */                 } 
/*  723 */                 int tmp1 = acb0x + l * cw;
/*  724 */                 tmp1 = (tmp1 > sb.l) ? tmp1 : sb.l;
/*  725 */                 int tmp2 = acb0x + (l + 1) * cw;
/*  726 */                 tmp2 = (tmp2 > sb.l + sb.p) ? (sb.l + sb.p) : tmp2;
/*      */                 
/*  728 */                 cb.d = tmp2 - tmp1;
/*  729 */                 tmp1 = acb0y + k * ch;
/*  730 */                 tmp1 = (tmp1 > sb.m) ? tmp1 : sb.m;
/*  731 */                 tmp2 = acb0y + (k + 1) * ch;
/*  732 */                 tmp2 = (tmp2 > sb.m + sb.q) ? (sb.m + sb.q) : tmp2;
/*      */                 
/*  734 */                 cb.e = tmp2 - tmp1;
/*  735 */                 (this.y[c][r][nPrec]).j[3][k - kstart][l - lstart] = cb;
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
/*      */   public int a(int c, int r) {
/*  754 */     return (this.i[c][r]).x * (this.i[c][r]).y;
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
/*      */   public boolean a(int l, int r, int c, int p, b[][][] cbI, int[] nb) throws IOException {
/*      */     f bin;
/*  787 */     int sumtotnewtp = 0;
/*      */     
/*  789 */     int startPktHead = this.h.getPos();
/*  790 */     if (startPktHead >= this.h.length())
/*      */     {
/*  792 */       return true;
/*      */     }
/*  794 */     int tIdx = this.a.e();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  799 */     i root = this.a.f(tIdx, c);
/*      */ 
/*      */ 
/*      */     
/*  803 */     if (this.b) {
/*  804 */       bin = new f(this.c);
/*      */     } else {
/*  806 */       bin = this.g;
/*      */     } 
/*      */     
/*  809 */     int mins = (r == 0) ? 0 : 1;
/*  810 */     int maxs = (r == 0) ? 1 : 4;
/*      */     
/*  812 */     boolean precFound = false;
/*  813 */     for (int s = mins; s < maxs; s++) {
/*  814 */       if (p < (this.y[c][r]).length) {
/*  815 */         precFound = true;
/*      */       }
/*      */     } 
/*  818 */     if (!precFound) {
/*  819 */       return false;
/*      */     }
/*      */     
/*  822 */     g prec = this.y[c][r][p];
/*      */ 
/*      */     
/*  825 */     bin.b();
/*      */ 
/*      */     
/*  828 */     if (bin.a() == 0) {
/*      */       
/*  830 */       this.H = new Vector[maxs + 1];
/*  831 */       for (int j = mins; j < maxs; j++) {
/*  832 */         this.H[j] = new Vector();
/*      */       }
/*  834 */       this.G++;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  839 */       if (this.R && this.J == -1) {
/*  840 */         int tmp = this.h.getPos() - startPktHead;
/*  841 */         if (tmp > nb[tIdx]) {
/*  842 */           nb[tIdx] = 0;
/*  843 */           return true;
/*      */         } 
/*  845 */         nb[tIdx] = nb[tIdx] - tmp;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  850 */       if (this.F) {
/*  851 */         a(bin);
/*      */       }
/*  853 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  858 */     if (this.H == null || this.H.length < maxs + 1) {
/*  859 */       this.H = new Vector[maxs + 1];
/*      */     }
/*      */     
/*  862 */     for (int i = mins; i < maxs; i++) {
/*  863 */       if (this.H[i] == null) {
/*  864 */         this.H[i] = new Vector();
/*      */       } else {
/*  866 */         this.H[i].removeAllElements();
/*      */       } 
/*  868 */       i sb = (i)root.a(r, i);
/*      */       
/*  870 */       if (prec.k[i] != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  875 */         h tdIncl = this.A[c][r][p][i];
/*  876 */         h tdBD = this.B[c][r][p][i];
/*      */         
/*  878 */         int mend = (prec.j[i] == null) ? 0 : (prec.j[i]).length;
/*  879 */         for (int m = 0; m < mend; m++) {
/*  880 */           int nend = (prec.j[i][m] == null) ? 0 : (prec.j[i][m]).length;
/*  881 */           for (int n = 0; n < nend; n++) {
/*  882 */             Point cbc = (prec.j[i][m][n]).a;
/*  883 */             int j = cbc.x + cbc.y * sb.i.x;
/*      */             
/*  885 */             b ccb = cbI[i][cbc.y][cbc.x];
/*      */             
/*      */             try {
/*      */               int nSeg, cbLen, totnewtp;
/*  889 */               if (ccb == null || ccb.i == 0) {
/*  890 */                 if (ccb == null) {
/*  891 */                   ccb = cbI[i][cbc.y][cbc.x] = new b((prec.j[i][m][n]).b, (prec.j[i][m][n]).c, (prec.j[i][m][n]).d, (prec.j[i][m][n]).e, this.C);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  897 */                 ccb.k[l] = this.G;
/*      */ 
/*      */                 
/*  900 */                 int tmp = tdIncl.a(m, n, l + 1, bin);
/*  901 */                 if (tmp > l) {
/*      */                   continue;
/*      */                 }
/*      */ 
/*      */                 
/*  906 */                 tmp = 1; int tmp2;
/*  907 */                 for (tmp2 = 1; tmp >= tmp2; tmp2++) {
/*  908 */                   tmp = tdBD.a(m, n, tmp2, bin);
/*      */                 }
/*  910 */                 ccb.e = tmp2 - 2;
/*      */ 
/*      */                 
/*  913 */                 totnewtp = 1;
/*  914 */                 ccb.a(l, 0);
/*      */ 
/*      */                 
/*  917 */                 this.I++;
/*  918 */                 if (this.J != -1 && !this.K && this.I == this.J)
/*      */                 {
/*  920 */                   this.K = true;
/*  921 */                   this.L = tIdx;
/*  922 */                   this.M = c;
/*  923 */                   this.N = i;
/*  924 */                   this.O = r;
/*  925 */                   this.P = cbc.x;
/*  926 */                   this.Q = cbc.y;
/*      */                 }
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/*  932 */                 ccb.k[l] = this.G;
/*      */ 
/*      */                 
/*  935 */                 if (bin.a() != 1) {
/*      */                   continue;
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  941 */                 totnewtp = 1;
/*      */               } 
/*      */ 
/*      */               
/*  945 */               if (bin.a() == 1) {
/*  946 */                 totnewtp++;
/*      */ 
/*      */                 
/*  949 */                 if (bin.a() == 1) {
/*  950 */                   totnewtp++;
/*      */                   
/*  952 */                   int tmp = bin.a(2);
/*  953 */                   totnewtp += tmp;
/*      */                   
/*  955 */                   if (tmp == 3) {
/*  956 */                     tmp = bin.a(5);
/*  957 */                     totnewtp += tmp;
/*      */ 
/*      */                     
/*  960 */                     if (tmp == 31) {
/*  961 */                       totnewtp += bin.a(7);
/*      */                     }
/*      */                   } 
/*      */                 } 
/*      */               } 
/*  966 */               ccb.a(l, totnewtp);
/*  967 */               sumtotnewtp += totnewtp;
/*  968 */               this.H[i].addElement(prec.j[i][m][n]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  988 */               int options = ((Integer)this.d.j.a(tIdx, c)).intValue();
/*      */               
/*  990 */               if ((options & 0x4) != 0) {
/*      */ 
/*      */                 
/*  993 */                 nSeg = totnewtp;
/*  994 */               } else if ((options & 0x1) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1002 */                 if (ccb.i <= 10) {
/* 1003 */                   nSeg = 1;
/*      */                 } else {
/* 1005 */                   nSeg = 1;
/*      */                   
/* 1007 */                   int tpidx = ccb.i - totnewtp;
/* 1008 */                   for (; tpidx < ccb.i - 1; tpidx++) {
/* 1009 */                     if (tpidx >= 9) {
/* 1010 */                       int passtype = (tpidx + 2) % 3;
/*      */ 
/*      */                       
/* 1013 */                       if (passtype == 1 || passtype == 2)
/*      */                       {
/*      */ 
/*      */                         
/* 1017 */                         nSeg++;
/*      */                       }
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } else {
/*      */                 
/* 1024 */                 nSeg = 1;
/*      */               } 
/*      */ 
/*      */               
/* 1028 */               while (bin.a() != 0) {
/* 1029 */                 this.z[c][r][i][cbc.y][cbc.x] = this.z[c][r][i][cbc.y][cbc.x] + 1;
/*      */               }
/*      */               
/* 1032 */               if (nSeg == 1) {
/* 1033 */                 cbLen = bin.a(this.z[c][r][i][cbc.y][cbc.x] + 
/* 1034 */                     c.a.i.e.a(totnewtp));
/*      */               } else {
/*      */                 
/* 1037 */                 ccb.j[l] = new int[nSeg];
/* 1038 */                 cbLen = 0;
/*      */                 
/* 1040 */                 if ((options & 0x4) != 0) {
/*      */                   
/* 1042 */                   int tpidx = ccb.i - totnewtp, k = 0;
/* 1043 */                   for (; tpidx < ccb.i; tpidx++, k++)
/*      */                   {
/* 1045 */                     int lblockCur = this.z[c][r][i][cbc.y][cbc.x];
/*      */                     
/* 1047 */                     int tmp = bin.a(lblockCur);
/* 1048 */                     ccb.j[l][k] = tmp;
/* 1049 */                     cbLen += tmp;
/*      */                   }
/*      */                 
/*      */                 } else {
/*      */                   
/* 1054 */                   int ltp = ccb.i - totnewtp - 1;
/* 1055 */                   int tpidx = ccb.i - totnewtp, k = 0;
/* 1056 */                   for (; tpidx < ccb.i - 1; tpidx++) {
/* 1057 */                     if (tpidx >= 9) {
/* 1058 */                       int passtype = (tpidx + 2) % 3;
/*      */ 
/*      */                       
/* 1061 */                       if (passtype != 0) {
/*      */                         
/* 1063 */                         int i2 = this.z[c][r][i][cbc.y][cbc.x];
/*      */ 
/*      */ 
/*      */                         
/* 1067 */                         int i1 = bin.a(i2 + 
/* 1068 */                             c.a.i.e.a(tpidx - ltp));
/* 1069 */                         ccb.j[l][k] = i1;
/* 1070 */                         cbLen += i1;
/* 1071 */                         ltp = tpidx;
/* 1072 */                         k++;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 1076 */                   int lblockCur = this.z[c][r][i][cbc.y][cbc.x];
/* 1077 */                   int tmp = bin.a(lblockCur + 
/* 1078 */                       c.a.i.e.a(tpidx - ltp));
/* 1079 */                   cbLen += tmp;
/* 1080 */                   ccb.j[l][k] = tmp;
/*      */                 } 
/*      */               } 
/* 1083 */               ccb.f[l] = cbLen;
/*      */ 
/*      */ 
/*      */               
/* 1087 */               if (this.R && this.J == -1) {
/* 1088 */                 int tmp = this.h.getPos() - startPktHead;
/* 1089 */                 if (tmp > nb[tIdx]) {
/* 1090 */                   nb[tIdx] = 0;
/*      */                   
/* 1092 */                   if (l == 0) {
/* 1093 */                     cbI[i][cbc.y][cbc.x] = null;
/*      */                   } else {
/* 1095 */                     ccb.f[l] = 0; ccb.g[l] = 0;
/* 1096 */                     ccb.i -= ccb.h[l];
/* 1097 */                     ccb.h[l] = 0;
/* 1098 */                     ccb.k[l] = -1;
/*      */                   } 
/* 1100 */                   return true;
/*      */                 }
/*      */               
/*      */               } 
/* 1104 */             } catch (EOFException eOFException) {
/*      */               
/* 1106 */               if (l == 0) {
/* 1107 */                 cbI[i][cbc.y][cbc.x] = null;
/*      */               } else {
/* 1109 */                 ccb.f[l] = 0; ccb.g[l] = 0;
/* 1110 */                 ccb.i -= ccb.h[l];
/* 1111 */                 ccb.h[l] = 0;
/* 1112 */                 ccb.k[l] = -1;
/*      */               } 
/*      */               
/* 1115 */               return true;
/*      */             } 
/*      */             continue;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1122 */     if (this.F) {
/* 1123 */       a(bin);
/*      */     }
/*      */     
/* 1126 */     this.G++;
/*      */ 
/*      */     
/* 1129 */     if (this.R && this.J == -1) {
/* 1130 */       int tmp = this.h.getPos() - startPktHead;
/* 1131 */       if (tmp > nb[tIdx]) {
/* 1132 */         nb[tIdx] = 0;
/* 1133 */         return true;
/*      */       } 
/* 1135 */       nb[tIdx] = nb[tIdx] - tmp;
/*      */     } 
/*      */     
/* 1138 */     return false;
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
/*      */   public boolean b(int l, int r, int c, int p, b[][][] cbI, int[] nb) throws IOException {
/* 1164 */     int curOff = this.h.getPos();
/*      */ 
/*      */     
/* 1167 */     boolean stopRead = false;
/* 1168 */     int tIdx = this.a.e();
/*      */ 
/*      */     
/* 1171 */     boolean precFound = false;
/* 1172 */     int mins = (r == 0) ? 0 : 1;
/* 1173 */     int maxs = (r == 0) ? 1 : 4; int s;
/* 1174 */     for (s = mins; s < maxs; s++) {
/* 1175 */       if (p < (this.y[c][r]).length) {
/* 1176 */         precFound = true;
/*      */       }
/*      */     } 
/* 1179 */     if (!precFound) {
/* 1180 */       return false;
/*      */     }
/*      */     
/* 1183 */     for (s = mins; s < maxs; s++) {
/* 1184 */       for (int numCB = 0; numCB < this.H[s].size(); numCB++) {
/* 1185 */         Point cbc = ((a)this.H[s].elementAt(numCB)).a;
/* 1186 */         b ccb = cbI[s][cbc.y][cbc.x];
/* 1187 */         ccb.g[l] = curOff;
/* 1188 */         curOff += ccb.f[l];
/*      */         try {
/* 1190 */           this.h.seek(curOff);
/* 1191 */         } catch (EOFException eOFException) {
/* 1192 */           if (l == 0) {
/* 1193 */             cbI[s][cbc.y][cbc.x] = null;
/*      */           } else {
/* 1195 */             ccb.f[l] = 0; ccb.g[l] = 0;
/* 1196 */             ccb.i -= ccb.h[l];
/* 1197 */             ccb.h[l] = 0;
/* 1198 */             ccb.k[l] = -1;
/*      */           } 
/* 1200 */           throw new EOFException();
/*      */         } 
/*      */ 
/*      */         
/* 1204 */         if (this.R) {
/* 1205 */           if (stopRead || ccb.f[l] > nb[tIdx]) {
/*      */             
/* 1207 */             if (l == 0) {
/* 1208 */               cbI[s][cbc.y][cbc.x] = null;
/*      */             } else {
/* 1210 */               ccb.f[l] = 0; ccb.g[l] = 0;
/* 1211 */               ccb.i -= ccb.h[l];
/* 1212 */               ccb.h[l] = 0;
/* 1213 */               ccb.k[l] = -1;
/*      */             } 
/* 1215 */             stopRead = true;
/*      */           } 
/* 1217 */           if (!stopRead) {
/* 1218 */             nb[tIdx] = nb[tIdx] - ccb.f[l];
/*      */           }
/*      */         } 
/*      */         
/* 1222 */         if (this.K && r == this.O && s == this.N && cbc.x == this.P && cbc.y == this.Q && tIdx == this.L && c == this.M) {
/*      */           
/* 1224 */           cbI[s][cbc.y][cbc.x] = null;
/* 1225 */           stopRead = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1231 */     this.h.seek(curOff);
/*      */     
/* 1233 */     if (stopRead) {
/* 1234 */       return true;
/*      */     }
/* 1236 */     return false;
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
/*      */   public final int a(int t, int c, int r) {
/* 1254 */     return this.d.n.a(t, c, r);
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
/* 1271 */     return this.d.n.b(t, c, rl);
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
/*      */   public boolean a(int[] nBytes, int p, int c, int r) throws IOException {
/* 1289 */     byte[] sopArray = new byte[6];
/* 1290 */     int tIdx = this.a.e();
/* 1291 */     int mins = (r == 0) ? 0 : 1;
/* 1292 */     int maxs = (r == 0) ? 1 : 4;
/* 1293 */     boolean precFound = false;
/* 1294 */     for (int s = mins; s < maxs; s++) {
/* 1295 */       if (p < (this.y[c][r]).length) {
/* 1296 */         precFound = true;
/*      */       }
/*      */     } 
/* 1299 */     if (!precFound) {
/* 1300 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1304 */     if (!this.E) {
/* 1305 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1309 */     int pos = this.h.getPos();
/* 1310 */     if ((short)(this.h.read() << 8 | this.h.read()) != -111) {
/* 1311 */       this.h.seek(pos);
/* 1312 */       return false;
/*      */     } 
/* 1314 */     this.h.seek(pos);
/*      */ 
/*      */ 
/*      */     
/* 1318 */     if (nBytes[tIdx] < 6) {
/* 1319 */       return true;
/*      */     }
/* 1321 */     nBytes[tIdx] = nBytes[tIdx] - 6;
/*      */ 
/*      */     
/* 1324 */     this.h.readFully(sopArray, 0, 6);
/*      */ 
/*      */     
/* 1327 */     int val = sopArray[0];
/* 1328 */     val <<= 8;
/* 1329 */     val |= sopArray[1];
/* 1330 */     if (val != -111) {
/* 1331 */       throw new Error("Corrupted Bitstream: Could not parse SOP marker !");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1336 */     val = sopArray[2] & 0xFF;
/* 1337 */     val <<= 8;
/* 1338 */     val |= sopArray[3] & 0xFF;
/* 1339 */     if (val != 4) {
/* 1340 */       throw new Error("Corrupted Bitstream: Corrupted SOP marker !");
/*      */     }
/*      */ 
/*      */     
/* 1344 */     val = sopArray[4] & 0xFF;
/* 1345 */     val <<= 8;
/* 1346 */     val |= sopArray[5] & 0xFF;
/*      */     
/* 1348 */     if (!this.b && val != this.G) {
/* 1349 */       throw new Error("Corrupted Bitstream: SOP marker out of sequence !");
/*      */     }
/*      */     
/* 1352 */     if (this.b && val != this.G - 1)
/*      */     {
/*      */       
/* 1355 */       throw new Error("Corrupted Bitstream: SOP marker out of sequence !");
/*      */     }
/*      */     
/* 1358 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(f bin) throws IOException {
/* 1369 */     byte[] ephArray = new byte[2];
/*      */     
/* 1371 */     if (bin.c) {
/* 1372 */       bin.b.read(ephArray, 0, 2);
/*      */     } else {
/* 1374 */       bin.a.readFully(ephArray, 0, 2);
/*      */     } 
/*      */ 
/*      */     
/* 1378 */     int val = ephArray[0];
/* 1379 */     val <<= 8;
/* 1380 */     val |= ephArray[1];
/* 1381 */     if (val != -110) {
/* 1382 */       throw new Error("Corrupted Bitstream: Could not parse EPH marker ! ");
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
/*      */   public g c(int c, int r, int p) {
/* 1398 */     return this.y[c][r][p];
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */