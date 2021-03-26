/*      */ package c.a.a.b;
/*      */ 
/*      */ import c.a.a.a;
/*      */ import c.a.a.g;
/*      */ import c.a.c.b.c;
/*      */ import c.a.c.b.d;
/*      */ import c.a.i.a;
/*      */ import c.a.j.a.o;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*      */ import java.awt.Point;
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
/*      */ public class e
/*      */ {
/*      */   public static final char a = 'P';
/*   74 */   private static final String[][] c = new String[][] { { "Psop", "[<tile idx>] true|false[ [<tile idx>] true|false ...]", "Specifies whether start of packet (SOP) markers should be used. 'true' enables, 'false' disables it.", "false" }, { "Peph", "[<tile idx>] true|false[ [<tile  idx>] true|false ...]", "Specifies whether end of packet header (EPH) markers should be  used. 'true' enables, 'false' disables it.", "false" } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int d = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private d e;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   J2KImageWriteParamJava b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private f[][][][][] f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private f[][][][][] g;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][][][][] h;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][][][][] i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][][][][] j;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][][][][] k;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] l;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int m;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean n;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean o = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  217 */   private int p = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private g[][][][] q;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean r;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public e(d infoSrc, J2KImageWriteParamJava wp, Point[][][] numPrec) {
/*  257 */     this.e = infoSrc;
/*  258 */     this.b = wp;
/*      */ 
/*      */ 
/*      */     
/*  262 */     int nc = infoSrc.getNumComps();
/*  263 */     int nt = infoSrc.getNumTiles();
/*      */ 
/*      */     
/*  266 */     this.f = new f[nt][nc][][][];
/*  267 */     this.g = new f[nt][nc][][][];
/*  268 */     this.h = new int[nt][nc][][][];
/*  269 */     this.i = new int[nt][nc][][][];
/*  270 */     this.q = new g[nt][nc][][];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  276 */     Point tmpCoord = null;
/*      */     
/*  278 */     Vector cblks = null;
/*  279 */     infoSrc.setTile(0, 0);
/*  280 */     for (int t = 0; t < nt; t++) {
/*  281 */       for (int c = 0; c < nc; c++) {
/*      */         
/*  283 */         o root = infoSrc.e(t, c);
/*  284 */         int mrl = root.h;
/*      */         
/*  286 */         this.h[t][c] = new int[mrl + 1][][];
/*  287 */         this.f[t][c] = new f[mrl + 1][][];
/*  288 */         this.g[t][c] = new f[mrl + 1][][];
/*  289 */         this.i[t][c] = new int[mrl + 1][][];
/*  290 */         this.q[t][c] = new g[mrl + 1][];
/*      */         
/*  292 */         for (int r = 0; r <= mrl; r++) {
/*  293 */           int mins = (r == 0) ? 0 : 1;
/*  294 */           int maxs = (r == 0) ? 1 : 4;
/*      */           
/*  296 */           int maxPrec = (numPrec[t][c][r]).x * (numPrec[t][c][r]).y;
/*      */           
/*  298 */           this.f[t][c][r] = new f[maxPrec][maxs];
/*  299 */           this.g[t][c][r] = new f[maxPrec][maxs];
/*  300 */           this.i[t][c][r] = new int[maxs][];
/*  301 */           this.h[t][c][r] = new int[maxs][];
/*      */ 
/*      */           
/*  304 */           this.q[t][c][r] = new g[maxPrec];
/*  305 */           a(t, c, r);
/*      */           
/*  307 */           for (int s = mins; s < maxs; s++) {
/*      */             
/*  309 */             o sb = (o)root.a(r, s);
/*  310 */             int numcb = sb.i.x * sb.i.y;
/*      */             
/*  312 */             this.h[t][c][r][s] = new int[numcb];
/*  313 */             a.a(this.h[t][c][r][s], 3);
/*      */             
/*  315 */             this.i[t][c][r][s] = new int[numcb];
/*  316 */             a.a(this.i[t][c][r][s], -1);
/*      */           } 
/*      */         } 
/*      */       } 
/*  320 */       if (t != nt - 1) infoSrc.nextTile();
/*      */     
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
/*      */   private void a(int t, int c, int r) {
/*  337 */     if ((this.q[t][c][r]).length == 0) {
/*      */       return;
/*      */     }
/*  340 */     Point tileI = this.e.getTile(null);
/*  341 */     Point nTiles = this.e.getNumTiles(null);
/*      */     
/*  343 */     int x0siz = this.e.getImgULX();
/*  344 */     int y0siz = this.e.getImgULY();
/*  345 */     int xsiz = x0siz + this.e.getImgWidth();
/*  346 */     int ysiz = y0siz + this.e.getImgHeight();
/*  347 */     int xt0siz = this.e.getTilePartULX();
/*  348 */     int yt0siz = this.e.getTilePartULY();
/*  349 */     int xtsiz = this.e.getNomTileWidth();
/*  350 */     int ytsiz = this.e.getNomTileHeight();
/*      */     
/*  352 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/*  353 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/*  354 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/*  355 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */     
/*  357 */     int xrsiz = this.e.getCompSubsX(c);
/*  358 */     int yrsiz = this.e.getCompSubsY(c);
/*      */     
/*  360 */     int tcx0 = (int)Math.ceil(tx0 / xrsiz);
/*  361 */     int tcy0 = (int)Math.ceil(ty0 / yrsiz);
/*  362 */     int tcx1 = (int)Math.ceil(tx1 / xrsiz);
/*  363 */     int tcy1 = (int)Math.ceil(ty1 / yrsiz);
/*      */     
/*  365 */     int ndl = (this.e.e(t, c)).h - r;
/*  366 */     int trx0 = (int)Math.ceil(tcx0 / (1 << ndl));
/*  367 */     int try0 = (int)Math.ceil(tcy0 / (1 << ndl));
/*  368 */     int trx1 = (int)Math.ceil(tcx1 / (1 << ndl));
/*  369 */     int try1 = (int)Math.ceil(tcy1 / (1 << ndl));
/*      */     
/*  371 */     int cb0x = this.e.a();
/*  372 */     int cb0y = this.e.b();
/*      */     
/*  374 */     double twoppx = this.b.getPrecinctPartition().a(t, c, r);
/*  375 */     double twoppy = this.b.getPrecinctPartition().b(t, c, r);
/*  376 */     int twoppx2 = (int)(twoppx / 2.0D);
/*  377 */     int twoppy2 = (int)(twoppy / 2.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  382 */     int maxPrec = (this.q[t][c][r]).length;
/*  383 */     int nPrec = 0;
/*      */     
/*  385 */     int istart = (int)Math.floor((try0 - cb0y) / twoppy);
/*  386 */     int iend = (int)Math.floor((try1 - 1 - cb0y) / twoppy);
/*  387 */     int jstart = (int)Math.floor((trx0 - cb0x) / twoppx);
/*  388 */     int jend = (int)Math.floor((trx1 - 1 - cb0x) / twoppx);
/*      */ 
/*      */ 
/*      */     
/*  392 */     o root = this.e.e(t, c);
/*  393 */     o sb = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  400 */     int prg_w = (int)twoppx << ndl;
/*  401 */     int prg_h = (int)twoppy << ndl;
/*      */ 
/*      */ 
/*      */     
/*  405 */     for (int i = istart; i <= iend; i++) {
/*  406 */       for (int j = jstart; j <= jend; j++, nPrec++) {
/*  407 */         int prg_ulx, prg_uly; if (j == jstart && (trx0 - cb0x) % xrsiz * (int)twoppx != 0) {
/*  408 */           prg_ulx = tx0;
/*      */         } else {
/*  410 */           prg_ulx = cb0x + j * xrsiz * ((int)twoppx << ndl);
/*      */         } 
/*  412 */         if (i == istart && (try0 - cb0y) % yrsiz * (int)twoppy != 0) {
/*  413 */           prg_uly = ty0;
/*      */         } else {
/*  415 */           prg_uly = cb0y + i * yrsiz * ((int)twoppy << ndl);
/*      */         } 
/*      */         
/*  418 */         this.q[t][c][r][nPrec] = new g(r, (int)(cb0x + j * twoppx), (int)(cb0y + i * twoppy), (int)twoppx, (int)twoppy, prg_ulx, prg_uly, prg_w, prg_h);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  423 */         if (r == 0) {
/*  424 */           int acb0x = cb0x;
/*  425 */           int acb0y = cb0y;
/*      */           
/*  427 */           int p0x = acb0x + j * (int)twoppx;
/*  428 */           int p1x = p0x + (int)twoppx;
/*  429 */           int p0y = acb0y + i * (int)twoppy;
/*  430 */           int p1y = p0y + (int)twoppy;
/*      */           
/*  432 */           sb = (o)root.a(0, 0);
/*  433 */           int s0x = (p0x < sb.l) ? sb.l : p0x;
/*  434 */           int s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  435 */           int s0y = (p0y < sb.m) ? sb.m : p0y;
/*  436 */           int s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  439 */           int cw = sb.r;
/*  440 */           int ch = sb.s;
/*  441 */           int k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  442 */           int kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  443 */           int kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  444 */           int l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  445 */           int lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  446 */           int lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  448 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  449 */             (this.q[t][c][r][nPrec]).k[0] = 0;
/*  450 */             this.f[t][c][r][nPrec][0] = new f(0, 0);
/*  451 */             this.g[t][c][r][nPrec][0] = new f(0, 0);
/*      */           } else {
/*  453 */             this.f[t][c][r][nPrec][0] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  455 */             this.g[t][c][r][nPrec][0] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  457 */             (this.q[t][c][r][nPrec]).j[0] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  459 */             (this.q[t][c][r][nPrec]).k[0] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  462 */             for (int k = kstart; k <= kend; k++) {
/*  463 */               for (int l = lstart; l <= lend; l++)
/*      */               {
/*  465 */                 a cb = new a(k - k0, l - l0);
/*  466 */                 (this.q[t][c][r][nPrec]).j[0][k - kstart][l - lstart] = cb;
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           
/*  473 */           int acb0x = 0;
/*  474 */           int acb0y = cb0y;
/*      */           
/*  476 */           int p0x = acb0x + j * twoppx2;
/*  477 */           int p1x = p0x + twoppx2;
/*  478 */           int p0y = acb0y + i * twoppy2;
/*  479 */           int p1y = p0y + twoppy2;
/*      */           
/*  481 */           sb = (o)root.a(r, 1);
/*  482 */           int s0x = (p0x < sb.l) ? sb.l : p0x;
/*  483 */           int s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  484 */           int s0y = (p0y < sb.m) ? sb.m : p0y;
/*  485 */           int s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  488 */           int cw = sb.r;
/*  489 */           int ch = sb.s;
/*  490 */           int k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  491 */           int kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  492 */           int kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  493 */           int l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  494 */           int lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  495 */           int lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  497 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  498 */             (this.q[t][c][r][nPrec]).k[1] = 0;
/*  499 */             this.f[t][c][r][nPrec][1] = new f(0, 0);
/*  500 */             this.g[t][c][r][nPrec][1] = new f(0, 0);
/*      */           } else {
/*  502 */             this.f[t][c][r][nPrec][1] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  504 */             this.g[t][c][r][nPrec][1] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  506 */             (this.q[t][c][r][nPrec]).j[1] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  508 */             (this.q[t][c][r][nPrec]).k[1] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  511 */             for (int k = kstart; k <= kend; k++) {
/*  512 */               for (int l = lstart; l <= lend; l++) {
/*  513 */                 a cb = new a(k - k0, l - l0);
/*  514 */                 (this.q[t][c][r][nPrec]).j[1][k - kstart][l - lstart] = cb;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  521 */           acb0x = cb0x;
/*  522 */           acb0y = 0;
/*      */           
/*  524 */           p0x = acb0x + j * twoppx2;
/*  525 */           p1x = p0x + twoppx2;
/*  526 */           p0y = acb0y + i * twoppy2;
/*  527 */           p1y = p0y + twoppy2;
/*      */           
/*  529 */           sb = (o)root.a(r, 2);
/*  530 */           s0x = (p0x < sb.l) ? sb.l : p0x;
/*  531 */           s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  532 */           s0y = (p0y < sb.m) ? sb.m : p0y;
/*  533 */           s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  536 */           cw = sb.r;
/*  537 */           ch = sb.s;
/*  538 */           k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  539 */           kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  540 */           kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  541 */           l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  542 */           lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  543 */           lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  545 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  546 */             (this.q[t][c][r][nPrec]).k[2] = 0;
/*  547 */             this.f[t][c][r][nPrec][2] = new f(0, 0);
/*  548 */             this.g[t][c][r][nPrec][2] = new f(0, 0);
/*      */           } else {
/*  550 */             this.f[t][c][r][nPrec][2] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  552 */             this.g[t][c][r][nPrec][2] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  554 */             (this.q[t][c][r][nPrec]).j[2] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  556 */             (this.q[t][c][r][nPrec]).k[2] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  559 */             for (int k = kstart; k <= kend; k++) {
/*  560 */               for (int l = lstart; l <= lend; l++) {
/*  561 */                 a cb = new a(k - k0, l - l0);
/*  562 */                 (this.q[t][c][r][nPrec]).j[2][k - kstart][l - lstart] = cb;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  569 */           acb0x = 0;
/*  570 */           acb0y = 0;
/*      */           
/*  572 */           p0x = acb0x + j * twoppx2;
/*  573 */           p1x = p0x + twoppx2;
/*  574 */           p0y = acb0y + i * twoppy2;
/*  575 */           p1y = p0y + twoppy2;
/*      */           
/*  577 */           sb = (o)root.a(r, 3);
/*  578 */           s0x = (p0x < sb.l) ? sb.l : p0x;
/*  579 */           s1x = (p1x > sb.l + sb.p) ? (sb.l + sb.p) : p1x;
/*  580 */           s0y = (p0y < sb.m) ? sb.m : p0y;
/*  581 */           s1y = (p1y > sb.m + sb.q) ? (sb.m + sb.q) : p1y;
/*      */ 
/*      */           
/*  584 */           cw = sb.r;
/*  585 */           ch = sb.s;
/*  586 */           k0 = (int)Math.floor((sb.m - acb0y) / ch);
/*  587 */           kstart = (int)Math.floor((s0y - acb0y) / ch);
/*  588 */           kend = (int)Math.floor((s1y - 1 - acb0y) / ch);
/*  589 */           l0 = (int)Math.floor((sb.l - acb0x) / cw);
/*  590 */           lstart = (int)Math.floor((s0x - acb0x) / cw);
/*  591 */           lend = (int)Math.floor((s1x - 1 - acb0x) / cw);
/*      */           
/*  593 */           if (s1x - s0x <= 0 || s1y - s0y <= 0) {
/*  594 */             (this.q[t][c][r][nPrec]).k[3] = 0;
/*  595 */             this.f[t][c][r][nPrec][3] = new f(0, 0);
/*  596 */             this.g[t][c][r][nPrec][3] = new f(0, 0);
/*      */           } else {
/*  598 */             this.f[t][c][r][nPrec][3] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  600 */             this.g[t][c][r][nPrec][3] = new f(kend - kstart + 1, lend - lstart + 1);
/*      */             
/*  602 */             (this.q[t][c][r][nPrec]).j[3] = new a[kend - kstart + 1][lend - lstart + 1];
/*      */             
/*  604 */             (this.q[t][c][r][nPrec]).k[3] = (kend - kstart + 1) * (lend - lstart + 1);
/*      */ 
/*      */             
/*  607 */             for (int k = kstart; k <= kend; k++) {
/*  608 */               for (int l = lstart; l <= lend; l++) {
/*  609 */                 a cb = new a(k - k0, l - l0);
/*  610 */                 (this.q[t][c][r][nPrec]).j[3][k - kstart][l - lstart] = cb;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a a(int ly, int i, int r, int t, c[][] cbs, int[][] tIndx, a hbuf, byte[] bbuf, int pIdx) {
/*  683 */     int minsb = (r == 0) ? 0 : 1;
/*  684 */     int maxsb = (r == 0) ? 1 : 4;
/*  685 */     Point cbCoord = null;
/*  686 */     o root = this.e.e(t, i);
/*      */     
/*  688 */     this.o = false;
/*  689 */     this.p = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  694 */     if (pIdx >= (this.q[t][i][r]).length) {
/*  695 */       this.r = false;
/*  696 */       return hbuf;
/*      */     } 
/*  698 */     g prec = this.q[t][i][r][pIdx];
/*      */ 
/*      */ 
/*      */     
/*  702 */     boolean isPrecVoid = true;
/*      */     int s;
/*  704 */     for (s = minsb; s < maxsb; ) {
/*  705 */       if (prec.k[s] == 0) {
/*      */         s++;
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  711 */       isPrecVoid = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  716 */     if (isPrecVoid) {
/*  717 */       this.r = true;
/*      */       
/*  719 */       if (hbuf == null) {
/*  720 */         hbuf = new a();
/*      */       } else {
/*  722 */         hbuf.a();
/*      */       } 
/*  724 */       if (bbuf == null) {
/*  725 */         this.l = bbuf = new byte[1];
/*      */       }
/*  727 */       hbuf.a(0);
/*  728 */       this.m = 0;
/*      */       
/*  730 */       return hbuf;
/*      */     } 
/*      */     
/*  733 */     if (hbuf == null) {
/*  734 */       hbuf = new a();
/*      */     } else {
/*  736 */       hbuf.a();
/*      */     } 
/*      */ 
/*      */     
/*  740 */     this.l = null;
/*  741 */     this.m = 0;
/*      */ 
/*      */     
/*  744 */     hbuf.a(1);
/*      */     
/*  746 */     for (s = minsb; s < maxsb; s++) {
/*  747 */       o sb = (o)root.a(r, s);
/*      */ 
/*      */ 
/*      */       
/*  751 */       if (prec.k[s] != 0) {
/*      */ 
/*      */ 
/*      */         
/*  755 */         f cur_ttIncl = this.f[t][i][r][pIdx][s];
/*  756 */         f cur_ttMaxBP = this.g[t][i][r][pIdx][s];
/*  757 */         int[] cur_prevtIdxs = this.i[t][i][r][s];
/*  758 */         c[] cur_cbs = cbs[s];
/*  759 */         int[] cur_tIndx = tIndx[s];
/*      */ 
/*      */         
/*  762 */         int mend = (prec.j[s] == null) ? 0 : (prec.j[s]).length; int m;
/*  763 */         for (m = 0; m < mend; m++) {
/*  764 */           int nend = (prec.j[s][m] == null) ? 0 : (prec.j[s][m]).length;
/*  765 */           for (int n = 0; n < nend; n++) {
/*  766 */             cbCoord = (prec.j[s][m][n]).a;
/*  767 */             int b = cbCoord.x + cbCoord.y * sb.i.x;
/*      */             
/*  769 */             if (cur_tIndx[b] > cur_prevtIdxs[b] && cur_prevtIdxs[b] < 0)
/*      */             {
/*  771 */               cur_ttIncl.a(m, n, ly - 1);
/*      */             }
/*  773 */             if (ly == 1) {
/*  774 */               cur_ttMaxBP.a(m, n, (cur_cbs[b]).c);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  780 */         for (m = 0; m < (prec.j[s]).length; m++) {
/*  781 */           int n = 0; while (true) { if (n < (prec.j[s][m]).length) {
/*  782 */               cbCoord = (prec.j[s][m][n]).a;
/*  783 */               int b = cbCoord.x + cbCoord.y * sb.i.x;
/*      */ 
/*      */               
/*  786 */               if (cur_tIndx[b] > cur_prevtIdxs[b]) {
/*      */                 int i1;
/*  788 */                 if (cur_prevtIdxs[b] < 0) {
/*      */                   
/*  790 */                   cur_ttIncl.a(m, n, ly, hbuf);
/*      */ 
/*      */                   
/*  793 */                   int thmax = (cur_cbs[b]).c + 1;
/*  794 */                   for (int i2 = 1; i2 <= thmax; i2++) {
/*  795 */                     cur_ttMaxBP.a(m, n, i2, hbuf);
/*      */                   }
/*      */ 
/*      */                   
/*  799 */                   this.m += (cur_cbs[b]).h[(cur_cbs[b]).k[cur_tIndx[b]]];
/*      */                 }
/*      */                 else {
/*      */                   
/*  803 */                   hbuf.a(1);
/*      */                   
/*  805 */                   this.m += (cur_cbs[b]).h[(cur_cbs[b]).k[cur_tIndx[b]]] - (cur_cbs[b]).h[(cur_cbs[b]).k[cur_prevtIdxs[b]]];
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  815 */                 if (cur_prevtIdxs[b] < 0) {
/*  816 */                   i1 = (cur_cbs[b]).k[cur_tIndx[b]];
/*      */                 } else {
/*  818 */                   i1 = (cur_cbs[b]).k[cur_tIndx[b]] - (cur_cbs[b]).k[cur_prevtIdxs[b]] - 1;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  823 */                 switch (i1) {
/*      */                   case 0:
/*  825 */                     hbuf.a(0);
/*      */                     break;
/*      */                   case 1:
/*  828 */                     hbuf.a(2, 2);
/*      */                     break;
/*      */ 
/*      */                   
/*      */                   case 2:
/*      */                   case 3:
/*      */                   case 4:
/*  835 */                     hbuf.a(0xC | i1 - 2, 4);
/*      */                     break;
/*      */                   default:
/*  838 */                     if (i1 <= 35) {
/*      */ 
/*      */                       
/*  841 */                       hbuf.a(0x1E0 | i1 - 5, 9); break;
/*  842 */                     }  if (i1 <= 163) {
/*      */ 
/*      */                       
/*  845 */                       hbuf.a(0xFF80 | i1 - 36, 16); break;
/*      */                     } 
/*  847 */                     throw new ArithmeticException("Maximum number of truncation points exceeded");
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               } else {
/*  854 */                 if (cur_prevtIdxs[b] >= 0) {
/*      */                   
/*  856 */                   hbuf.a(0);
/*      */                 } else {
/*  858 */                   cur_ttIncl.a(m, n, ly, hbuf);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 n++;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  869 */               int newtp = 1;
/*  870 */               int maxi = (cur_cbs[b]).k[cur_tIndx[b]];
/*  871 */               int cblen = (cur_prevtIdxs[b] < 0) ? 0 : (cur_cbs[b]).h[(cur_cbs[b]).k[cur_prevtIdxs[b]]];
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  876 */               int k = (cur_prevtIdxs[b] < 0) ? 0 : ((cur_cbs[b]).k[cur_prevtIdxs[b]] + 1);
/*      */               
/*  878 */               int minbits = 0;
/*  879 */               for (; k < maxi; k++, newtp++) {
/*      */                 
/*  881 */                 if ((cur_cbs[b]).l != null && (cur_cbs[b]).l[k]) {
/*      */ 
/*      */ 
/*      */                   
/*  885 */                   cblen = (cur_cbs[b]).h[k] - cblen;
/*      */ 
/*      */ 
/*      */                   
/*  889 */                   int i1 = this.h[t][i][r][s][b] + c.a.i.e.a(newtp);
/*  890 */                   minbits = ((cblen > 0) ? c.a.i.e.a(cblen) : 0) + 1;
/*      */ 
/*      */                   
/*  893 */                   for (int i2 = i1; i2 < minbits; i2++) {
/*  894 */                     this.h[t][i][r][s][b] = this.h[t][i][r][s][b] + 1;
/*  895 */                     hbuf.a(1);
/*      */                   } 
/*      */                   
/*  898 */                   newtp = 0;
/*  899 */                   cblen = (cur_cbs[b]).h[k];
/*      */                 } 
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  905 */               cblen = (cur_cbs[b]).h[k] - cblen;
/*      */ 
/*      */               
/*  908 */               int prednbits = this.h[t][i][r][s][b] + c.a.i.e.a(newtp);
/*  909 */               minbits = ((cblen > 0) ? c.a.i.e.a(cblen) : 0) + 1;
/*      */               
/*  911 */               for (int j = prednbits; j < minbits; j++) {
/*  912 */                 this.h[t][i][r][s][b] = this.h[t][i][r][s][b] + 1;
/*  913 */                 hbuf.a(1);
/*      */               } 
/*      */ 
/*      */               
/*  917 */               hbuf.a(0);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  922 */               newtp = 1;
/*  923 */               maxi = (cur_cbs[b]).k[cur_tIndx[b]];
/*  924 */               cblen = (cur_prevtIdxs[b] < 0) ? 0 : (cur_cbs[b]).h[(cur_cbs[b]).k[cur_prevtIdxs[b]]];
/*      */ 
/*      */ 
/*      */               
/*  928 */               k = (cur_prevtIdxs[b] < 0) ? 0 : ((cur_cbs[b]).k[cur_prevtIdxs[b]] + 1);
/*      */               
/*  930 */               for (; k < maxi; k++, newtp++) {
/*      */                 
/*  932 */                 if ((cur_cbs[b]).l != null && (cur_cbs[b]).l[k]) {
/*      */ 
/*      */                   
/*  935 */                   cblen = (cur_cbs[b]).h[k] - cblen;
/*  936 */                   int i1 = c.a.i.e.a(newtp) + this.h[t][i][r][s][b];
/*  937 */                   hbuf.a(cblen, i1);
/*      */ 
/*      */                   
/*  940 */                   newtp = 0;
/*  941 */                   cblen = (cur_cbs[b]).h[k];
/*      */                 } 
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  947 */               cblen = (cur_cbs[b]).h[k] - cblen;
/*  948 */               int nbits = c.a.i.e.a(newtp) + this.h[t][i][r][s][b];
/*  949 */               hbuf.a(cblen, nbits);
/*      */             } else {
/*      */               break;
/*      */             } 
/*      */             n++; }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/*  958 */     if (bbuf == null || bbuf.length < this.m) {
/*  959 */       bbuf = new byte[this.m];
/*      */     }
/*  961 */     this.l = bbuf;
/*  962 */     this.m = 0;
/*      */     
/*  964 */     for (s = minsb; s < maxsb; s++) {
/*  965 */       o sb = (o)root.a(r, s);
/*      */       
/*  967 */       int[] cur_prevtIdxs = this.i[t][i][r][s];
/*  968 */       c[] cur_cbs = cbs[s];
/*  969 */       int[] cur_tIndx = tIndx[s];
/*  970 */       int ncb = cur_prevtIdxs.length;
/*      */       
/*  972 */       int mend = (prec.j[s] == null) ? 0 : (prec.j[s]).length;
/*  973 */       for (int m = 0; m < mend; m++) {
/*  974 */         int nend = (prec.j[s][m] == null) ? 0 : (prec.j[s][m]).length;
/*  975 */         for (int n = 0; n < nend; n++) {
/*  976 */           cbCoord = (prec.j[s][m][n]).a;
/*  977 */           int b = cbCoord.x + cbCoord.y * sb.i.x;
/*      */           
/*  979 */           if (cur_tIndx[b] > cur_prevtIdxs[b]) {
/*      */             int cblen;
/*      */ 
/*      */             
/*  983 */             if (cur_prevtIdxs[b] < 0) {
/*  984 */               cblen = (cur_cbs[b]).h[(cur_cbs[b]).k[cur_tIndx[b]]];
/*      */               
/*  986 */               System.arraycopy((cur_cbs[b]).d, 0, this.l, this.m, cblen);
/*      */             } else {
/*      */               
/*  989 */               cblen = (cur_cbs[b]).h[(cur_cbs[b]).k[cur_tIndx[b]]] - (cur_cbs[b]).h[(cur_cbs[b]).k[cur_prevtIdxs[b]]];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  996 */               System.arraycopy((cur_cbs[b]).d, (cur_cbs[b]).h[(cur_cbs[b]).k[cur_prevtIdxs[b]]], this.l, this.m, cblen);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1003 */             this.m += cblen;
/*      */ 
/*      */ 
/*      */             
/* 1007 */             if ((cur_cbs[b]).m != 0 && (cur_prevtIdxs[b] == -1 || (cur_cbs[b]).k[cur_prevtIdxs[b]] <= (cur_cbs[b]).n - 1)) {
/*      */ 
/*      */ 
/*      */               
/* 1011 */               this.o = true;
/* 1012 */               this.p = this.m;
/*      */             } 
/*      */ 
/*      */             
/* 1016 */             cur_prevtIdxs[b] = cur_tIndx[b];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1022 */     this.r = true;
/*      */ 
/*      */     
/* 1025 */     if (hbuf.b() == 0) {
/* 1026 */       throw new Error("You have found a bug in PktEncoder, method: encodePacket");
/*      */     }
/*      */ 
/*      */     
/* 1030 */     return hbuf;
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
/*      */   public byte[] a() {
/* 1047 */     if (this.l == null) {
/* 1048 */       throw new IllegalArgumentException();
/*      */     }
/* 1050 */     return this.l;
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
/*      */   public int b() {
/* 1063 */     return this.m;
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
/*      */   public void c() {
/* 1076 */     if (this.j == null) {
/*      */       
/* 1078 */       this.j = new int[this.f.length][][][][];
/* 1079 */       this.k = new int[this.f.length][][][][];
/* 1080 */       for (int i = this.f.length - 1; i >= 0; i--) {
/* 1081 */         this.j[i] = new int[(this.f[i]).length][][][];
/* 1082 */         this.k[i] = new int[(this.f[i]).length][][][];
/* 1083 */         for (int c = (this.f[i]).length - 1; c >= 0; c--) {
/* 1084 */           this.j[i][c] = new int[(this.h[i][c]).length][][];
/* 1085 */           this.k[i][c] = new int[(this.f[i][c]).length][][];
/* 1086 */           for (int r = (this.h[i][c]).length - 1; r >= 0; r--) {
/* 1087 */             this.j[i][c][r] = new int[(this.h[i][c][r]).length][];
/*      */             
/* 1089 */             this.k[i][c][r] = new int[(this.i[i][c][r]).length][];
/*      */             
/* 1091 */             int minsbi = (r == 0) ? 0 : 1;
/* 1092 */             int maxsbi = (r == 0) ? 1 : 4;
/* 1093 */             for (int s = minsbi; s < maxsbi; s++) {
/* 1094 */               this.j[i][c][r][s] = new int[(this.h[i][c][r][s]).length];
/*      */               
/* 1096 */               this.k[i][c][r][s] = new int[(this.i[i][c][r][s]).length];
/*      */             } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1119 */     for (int t = this.f.length - 1; t >= 0; t--) {
/*      */       
/* 1121 */       for (int c = (this.f[t]).length - 1; c >= 0; c--) {
/*      */         
/* 1123 */         int[][][] lblock_t_c = this.h[t][c];
/* 1124 */         int[][][] bak_lblock_t_c = this.j[t][c];
/* 1125 */         f[][][] ttIncl_t_c = this.f[t][c];
/* 1126 */         f[][][] ttMaxBP_t_c = this.g[t][c];
/*      */         
/* 1128 */         for (int r = lblock_t_c.length - 1; r >= 0; r--) {
/*      */           
/* 1130 */           f[][] ttIncl_t_c_r = ttIncl_t_c[r];
/* 1131 */           f[][] ttMaxBP_t_c_r = ttMaxBP_t_c[r];
/* 1132 */           int[][] prevtIdxs_t_c_r = this.i[t][c][r];
/* 1133 */           int[][] bak_prevtIdxs_t_c_r = this.k[t][c][r];
/*      */ 
/*      */           
/* 1136 */           int minsbi = (r == 0) ? 0 : 1;
/* 1137 */           int maxsbi = (r == 0) ? 1 : 4;
/* 1138 */           for (int s = minsbi; s < maxsbi; s++) {
/*      */             
/* 1140 */             System.arraycopy(lblock_t_c[r][s], 0, bak_lblock_t_c[r][s], 0, (lblock_t_c[r][s]).length);
/*      */ 
/*      */ 
/*      */             
/* 1144 */             System.arraycopy(prevtIdxs_t_c_r[s], 0, bak_prevtIdxs_t_c_r[s], 0, (prevtIdxs_t_c_r[s]).length);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1150 */           for (int p = (this.q[t][c][r]).length - 1; p >= 0; p--) {
/* 1151 */             if (p < ttIncl_t_c_r.length)
/*      */             {
/* 1153 */               for (int i = minsbi; i < maxsbi; i++) {
/* 1154 */                 ttIncl_t_c_r[p][i].c();
/* 1155 */                 ttMaxBP_t_c_r[p][i].c();
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1164 */     this.n = true;
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
/*      */   public void d() {
/* 1177 */     if (!this.n) {
/* 1178 */       throw new IllegalArgumentException();
/*      */     }
/*      */ 
/*      */     
/* 1182 */     this.l = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1193 */     for (int t = this.f.length - 1; t >= 0; t--) {
/*      */       
/* 1195 */       for (int c = (this.f[t]).length - 1; c >= 0; c--) {
/*      */         
/* 1197 */         int[][][] lblock_t_c = this.h[t][c];
/* 1198 */         int[][][] bak_lblock_t_c = this.j[t][c];
/* 1199 */         f[][][] ttIncl_t_c = this.f[t][c];
/* 1200 */         f[][][] ttMaxBP_t_c = this.g[t][c];
/*      */         
/* 1202 */         for (int r = lblock_t_c.length - 1; r >= 0; r--) {
/*      */           
/* 1204 */           f[][] ttIncl_t_c_r = ttIncl_t_c[r];
/* 1205 */           f[][] ttMaxBP_t_c_r = ttMaxBP_t_c[r];
/* 1206 */           int[][] prevtIdxs_t_c_r = this.i[t][c][r];
/* 1207 */           int[][] bak_prevtIdxs_t_c_r = this.k[t][c][r];
/*      */ 
/*      */           
/* 1210 */           int minsbi = (r == 0) ? 0 : 1;
/* 1211 */           int maxsbi = (r == 0) ? 1 : 4;
/* 1212 */           for (int s = minsbi; s < maxsbi; s++) {
/*      */             
/* 1214 */             System.arraycopy(bak_lblock_t_c[r][s], 0, lblock_t_c[r][s], 0, (lblock_t_c[r][s]).length);
/*      */ 
/*      */ 
/*      */             
/* 1218 */             System.arraycopy(bak_prevtIdxs_t_c_r[s], 0, prevtIdxs_t_c_r[s], 0, (prevtIdxs_t_c_r[s]).length);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1224 */           for (int p = (this.q[t][c][r]).length - 1; p >= 0; p--) {
/* 1225 */             if (p < ttIncl_t_c_r.length)
/*      */             {
/* 1227 */               for (int i = minsbi; i < maxsbi; i++) {
/* 1228 */                 ttIncl_t_c_r[p][i].d();
/* 1229 */                 ttMaxBP_t_c_r[p][i].d();
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
/*      */   public void e() {
/* 1246 */     this.n = false;
/*      */     
/* 1248 */     this.l = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1258 */     for (int t = this.f.length - 1; t >= 0; t--) {
/*      */       
/* 1260 */       for (int c = (this.f[t]).length - 1; c >= 0; c--) {
/*      */         
/* 1262 */         int[][][] lblock_t_c = this.h[t][c];
/* 1263 */         f[][][] ttIncl_t_c = this.f[t][c];
/* 1264 */         f[][][] ttMaxBP_t_c = this.g[t][c];
/*      */         
/* 1266 */         for (int r = lblock_t_c.length - 1; r >= 0; r--) {
/*      */           
/* 1268 */           f[][] ttIncl_t_c_r = ttIncl_t_c[r];
/* 1269 */           f[][] ttMaxBP_t_c_r = ttMaxBP_t_c[r];
/* 1270 */           int[][] prevtIdxs_t_c_r = this.i[t][c][r];
/*      */ 
/*      */           
/* 1273 */           int minsbi = (r == 0) ? 0 : 1;
/* 1274 */           int maxsbi = (r == 0) ? 1 : 4;
/* 1275 */           for (int s = minsbi; s < maxsbi; s++) {
/*      */             
/* 1277 */             a.a(prevtIdxs_t_c_r[s], -1);
/*      */             
/* 1279 */             a.a(lblock_t_c[r][s], 3);
/*      */           } 
/*      */ 
/*      */           
/* 1283 */           for (int p = (this.q[t][c][r]).length - 1; p >= 0; p--) {
/* 1284 */             if (p < ttIncl_t_c_r.length)
/*      */             {
/* 1286 */               for (int i = minsbi; i < maxsbi; i++) {
/* 1287 */                 ttIncl_t_c_r[p][i].e();
/* 1288 */                 ttMaxBP_t_c_r[p][i].e();
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
/*      */   public boolean f() {
/* 1302 */     return this.r;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean g() {
/* 1309 */     return this.o;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int h() {
/* 1315 */     return this.p;
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
/*      */   public static String[][] i() {
/* 1332 */     return c;
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
/*      */   public g a(int t, int c, int r, int p) {
/* 1347 */     return this.q[t][c][r][p];
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/b/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */