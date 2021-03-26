/*     */ package c.a.h.a;
/*     */ 
/*     */ import c.a.e.b.b;
/*     */ import c.a.e.e;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import c.a.g.b.a;
/*     */ import c.a.g.b.b;
/*     */ import c.a.h.a;
/*     */ import c.a.j.a.g;
/*     */ import c.a.j.a.o;
/*     */ import c.a.j.b;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends g
/*     */   implements a
/*     */ {
/*     */   public static final char a = 'R';
/*  86 */   private static final String[][] b = new String[][] { { "Rroi", "[<component idx>] R <left> <top> <width> <height> or [<component idx>] C <centre column> <centre row> <radius> or [<component idx>] A <filename>", "Specifies ROIs shape and location. The shape can be either rectangular 'R', or circular 'C' or arbitrary 'A'. Each new occurrence of an 'R', a 'C' or an 'A' is a new ROI. For circular and rectangular ROIs, all values are given as their pixel values relative to the canvas origin. Arbitrary shapes must be included in a PGM file where non 0 values correspond to ROI coefficients. The PGM file must have the size as the image. The component idx specifies which components contain the ROI. The component index is specified as described by points 3 and 4 in the general comment on tile-component idx. If this option is used, the codestream is layer progressive by default unless it is overridden by the 'Aptype' option.", null }, { "Ralign", "[true|false]", "By specifying this argument, the ROI mask will be limited to covering only entire code-blocks. The ROI coding can then be performed without any actual scaling of the coefficients but by instead scaling the distortion estimates.", "false" }, { "Rstart_level", "<level>", "This argument forces the lowest <level> resolution levels to belong to the ROI. By doing this, it is possible to avoid only getting information for the ROI at an early stage of transmission.<level> = 0 means the lowest resolution level belongs to the ROI, 1 means the two lowest etc. (-1 deactivates the option)", "-1" }, { "Rno_rect", "[true|false]", "This argument makes sure that the ROI mask generation is not done using the fast ROI mask generation for rectangular ROIs regardless of whether the specified ROIs are rectangular or not", "false" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[][] c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private e h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private b i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(b src, c mg, boolean roi, int sLev, boolean uba, J2KImageWriteParamJava wp) {
/* 167 */     super((f)src);
/* 168 */     this.i = src;
/* 169 */     this.d = roi;
/* 170 */     this.f = sLev;
/* 171 */     if (roi) {
/*     */       
/* 173 */       this.g = mg;
/* 174 */       this.h = new e();
/* 175 */       a(wp);
/* 176 */       this.e = uba;
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
/*     */   public boolean d(int t, int i) {
/* 191 */     return this.i.d(t, i);
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
/*     */   public o e(int t, int i) {
/* 209 */     return this.i.e(t, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 217 */     return this.i.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 225 */     return this.i.b();
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
/*     */   public static d a(b src, J2KImageWriteParamJava wp) {
/* 247 */     Vector roiVector = new Vector();
/* 248 */     c maskGen = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     String roiopt = wp.getROIs().a();
/* 257 */     if (roiopt == null)
/*     */     {
/* 259 */       return new d(src, null, false, -1, false, wp);
/*     */     }
/*     */ 
/*     */     
/* 263 */     int sLev = wp.getStartLevelROI();
/*     */ 
/*     */     
/* 266 */     boolean useBlockAligned = wp.getAlignROI();
/*     */ 
/*     */     
/* 269 */     boolean onlyRect = false;
/*     */ 
/*     */     
/* 272 */     a(roiopt, src.getNumComps(), roiVector);
/* 273 */     b[] roiArray = new b[roiVector.size()];
/* 274 */     roiVector.copyInto((Object[])roiArray);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     if (onlyRect) {
/* 280 */       for (int i = roiArray.length - 1; i >= 0; i--) {
/* 281 */         if (!(roiArray[i]).c) {
/* 282 */           onlyRect = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 287 */     if (onlyRect) {
/*     */ 
/*     */       
/* 290 */       maskGen = new e(roiArray, src.getNumComps());
/*     */     }
/*     */     else {
/*     */       
/* 294 */       maskGen = new a(roiArray, src.getNumComps(), src);
/*     */     } 
/*     */     
/* 297 */     return new d(src, maskGen, true, sLev, useBlockAligned, wp);
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
/*     */   protected static Vector a(String roiopt, int nc, Vector<b> roiVector) {
/* 326 */     int nrOfROIs = 0;
/*     */ 
/*     */     
/* 329 */     boolean[] roiInComp = null;
/*     */     
/* 331 */     StringTokenizer stok = new StringTokenizer(roiopt);
/*     */ 
/*     */     
/* 334 */     while (stok.hasMoreTokens()) {
/* 335 */       int ulx, uly, w, h, x, y, rad, i; String filename; b maskPGM; int j; String word = stok.nextToken();
/*     */       
/* 337 */       switch (word.charAt(0)) {
/*     */         case 'c':
/* 339 */           roiInComp = c.a.d.a(word, nc);
/*     */           continue;
/*     */         case 'R':
/* 342 */           nrOfROIs++;
/*     */           try {
/* 344 */             word = stok.nextToken();
/* 345 */             ulx = (new Integer(word)).intValue();
/* 346 */             word = stok.nextToken();
/* 347 */             uly = (new Integer(word)).intValue();
/* 348 */             word = stok.nextToken();
/* 349 */             w = (new Integer(word)).intValue();
/* 350 */             word = stok.nextToken();
/* 351 */             h = (new Integer(word)).intValue();
/*     */           }
/* 353 */           catch (NumberFormatException numberFormatException) {
/* 354 */             throw new IllegalArgumentException("Bad parameter for '-Rroi R' option : " + word);
/*     */ 
/*     */           
/*     */           }
/* 358 */           catch (NoSuchElementException f) {
/* 359 */             throw new IllegalArgumentException("Wrong number of parameters for  h'-Rroi R' option.");
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 365 */           if (roiInComp != null) {
/* 366 */             for (int k = 0; k < nc; k++) {
/* 367 */               if (roiInComp[k]) {
/* 368 */                 b roi = new b(k, ulx, uly, w, h);
/* 369 */                 roiVector.addElement(roi);
/*     */               } 
/*     */             }  continue;
/*     */           } 
/* 373 */           for (i = 0; i < nc; i++) {
/* 374 */             b roi = new b(i, ulx, uly, w, h);
/* 375 */             roiVector.addElement(roi);
/*     */           } 
/*     */           continue;
/*     */         
/*     */         case 'C':
/* 380 */           nrOfROIs++;
/*     */           
/*     */           try {
/* 383 */             word = stok.nextToken();
/* 384 */             x = (new Integer(word)).intValue();
/* 385 */             word = stok.nextToken();
/* 386 */             y = (new Integer(word)).intValue();
/* 387 */             word = stok.nextToken();
/* 388 */             rad = (new Integer(word)).intValue();
/*     */           }
/* 390 */           catch (NumberFormatException numberFormatException) {
/* 391 */             throw new IllegalArgumentException("Bad parameter for '-Rroi C' option : " + word);
/*     */ 
/*     */           
/*     */           }
/* 395 */           catch (NoSuchElementException f) {
/* 396 */             throw new IllegalArgumentException("Wrong number of parameters for '-Rroi C' option.");
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 402 */           if (roiInComp != null) {
/* 403 */             for (i = 0; i < nc; i++) {
/* 404 */               if (roiInComp[i]) {
/* 405 */                 b roi = new b(i, x, y, rad);
/* 406 */                 roiVector.addElement(roi);
/*     */               } 
/*     */             }  continue;
/*     */           } 
/* 410 */           for (i = 0; i < nc; i++) {
/* 411 */             b roi = new b(i, x, y, rad);
/* 412 */             roiVector.addElement(roi);
/*     */           } 
/*     */           continue;
/*     */         
/*     */         case 'A':
/* 417 */           nrOfROIs++;
/*     */ 
/*     */           
/* 420 */           maskPGM = null;
/*     */           
/*     */           try {
/* 423 */             filename = stok.nextToken();
/*     */           }
/* 425 */           catch (NoSuchElementException noSuchElementException) {
/* 426 */             throw new IllegalArgumentException("Wrong number of parameters for '-Rroi A' option.");
/*     */           } 
/*     */ 
/*     */           
/*     */           try {
/* 431 */             maskPGM = new b(filename);
/*     */           }
/* 433 */           catch (IOException iOException) {
/* 434 */             throw new Error("Cannot read PGM file with ROI");
/*     */           } 
/*     */ 
/*     */           
/* 438 */           if (roiInComp != null) {
/* 439 */             for (int k = 0; k < nc; k++) {
/* 440 */               if (roiInComp[k]) {
/* 441 */                 b roi = new b(k, maskPGM);
/* 442 */                 roiVector.addElement(roi);
/*     */               } 
/*     */             }  continue;
/*     */           } 
/* 446 */           for (j = 0; j < nc; j++) {
/* 447 */             b roi = new b(j, maskPGM);
/* 448 */             roiVector.addElement(roi);
/*     */           } 
/*     */           continue;
/*     */       } 
/*     */       
/* 453 */       throw new Error("Bad parameters for ROI nr " + roiVector.size());
/*     */     } 
/*     */ 
/*     */     
/* 457 */     return roiVector;
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
/*     */   public g a(int n, g cblk) {
/* 486 */     return b(n, cblk);
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
/*     */   public g b(int i, g cblk) {
/* 512 */     e mask = this.h;
/*     */ 
/*     */ 
/*     */     
/* 516 */     int bitMask = Integer.MAX_VALUE;
/*     */     
/* 518 */     int maxBits = 0;
/*     */ 
/*     */     
/* 521 */     int nROIcoeff = 0;
/*     */ 
/*     */     
/* 524 */     cblk = this.i.a(i, cblk);
/*     */ 
/*     */ 
/*     */     
/* 528 */     if (!this.d || cblk == null) {
/* 529 */       return cblk;
/*     */     }
/*     */     
/* 532 */     int[] data = (int[])cblk.b();
/* 533 */     o sb = cblk.e;
/* 534 */     int ulx = cblk.a;
/* 535 */     int uly = cblk.b;
/* 536 */     int w = cblk.f;
/* 537 */     int h = cblk.g;
/* 538 */     boolean sbInMask = (sb.h <= this.f);
/*     */ 
/*     */     
/* 541 */     int[] maskData = mask.c();
/* 542 */     if (maskData == null || w * h > maskData.length) {
/* 543 */       maskData = new int[w * h];
/* 544 */       mask.a(maskData);
/*     */     } else {
/*     */       
/* 547 */       for (int m = w * h - 1; m >= 0; m--)
/* 548 */         maskData[m] = 0; 
/*     */     } 
/* 550 */     mask.e = ulx;
/* 551 */     mask.f = uly;
/* 552 */     mask.g = w;
/* 553 */     mask.h = h;
/*     */ 
/*     */     
/* 556 */     o root = this.i.e(this.k, i);
/* 557 */     maxBits = this.c[this.k][i];
/* 558 */     boolean roiInTile = this.g.a(mask, (b)root, maxBits, i);
/*     */ 
/*     */     
/* 561 */     if (!roiInTile && !sbInMask) {
/* 562 */       cblk.o = 0;
/* 563 */       return cblk;
/*     */     } 
/*     */ 
/*     */     
/* 567 */     cblk.o = cblk.j;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     if (sbInMask) {
/*     */ 
/*     */       
/* 575 */       cblk.k *= (1 << maxBits << 1);
/* 576 */       cblk.n = w * h;
/* 577 */       return cblk;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 583 */     if (this.e) {
/* 584 */       int i2 = cblk.i - w;
/* 585 */       int m = h * w - 1;
/* 586 */       int n = cblk.h + cblk.i * (h - 1) + w - 1;
/* 587 */       int nroicoeff = 0;
/* 588 */       for (int i1 = h; i1 > 0; i1--) {
/* 589 */         for (int i3 = w - 1; i3 >= 0; i3--, n--, m--) {
/* 590 */           if (maskData[m] != 0) {
/* 591 */             nroicoeff++;
/*     */           }
/*     */         } 
/* 594 */         n -= i2;
/*     */       } 
/* 596 */       if (nroicoeff != 0) {
/* 597 */         cblk.k *= (1 << maxBits << 1);
/* 598 */         cblk.n = w * h;
/*     */       } 
/* 600 */       return cblk;
/*     */     } 
/*     */ 
/*     */     
/* 604 */     bitMask = (1 << cblk.j) - 1 << 31 - cblk.j;
/* 605 */     int wrap = cblk.i - w;
/* 606 */     int mi = h * w - 1;
/* 607 */     int k = cblk.h + cblk.i * (h - 1) + w - 1;
/* 608 */     for (int j = h; j > 0; j--) {
/* 609 */       for (int m = w; m > 0; m--, k--, mi--) {
/* 610 */         int tmp = data[k];
/* 611 */         if (maskData[mi] != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 620 */           data[k] = Integer.MIN_VALUE & tmp | tmp & bitMask;
/* 621 */           nROIcoeff++;
/*     */         }
/*     */         else {
/*     */           
/* 625 */           data[k] = Integer.MIN_VALUE & tmp | (tmp & Integer.MAX_VALUE) >> maxBits;
/*     */         } 
/*     */       } 
/*     */       
/* 629 */       k -= wrap;
/*     */     } 
/*     */ 
/*     */     
/* 633 */     cblk.j += maxBits;
/*     */ 
/*     */     
/* 636 */     cblk.n = nROIcoeff;
/*     */     
/* 638 */     return cblk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c c() {
/* 647 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean d() {
/* 656 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean e() {
/* 665 */     return this.d;
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
/*     */   public static String[][] f() {
/* 683 */     return b;
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
/*     */   public void setTile(int x, int y) {
/* 696 */     super.setTile(x, y);
/* 697 */     if (this.d) {
/* 698 */       this.g.b();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextTile() {
/* 707 */     super.nextTile();
/* 708 */     if (this.d) {
/* 709 */       this.g.b();
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
/*     */   private void a(J2KImageWriteParamJava wp) {
/* 721 */     a rois = wp.getROIs();
/*     */     
/* 723 */     int nt = this.i.getNumTiles();
/* 724 */     int nc = this.i.getNumComps();
/*     */     
/* 726 */     this.c = new int[nt][nc];
/*     */     
/* 728 */     this.i.setTile(0, 0);
/* 729 */     for (int t = 0; t < nt; t++) {
/* 730 */       for (int i = nc - 1; i >= 0; i--) {
/* 731 */         int tmp = this.i.a(i);
/* 732 */         this.c[t][i] = tmp;
/* 733 */         rois.a(t, i, new Integer(tmp));
/*     */       } 
/* 735 */       if (t < nt - 1) this.i.nextTile();
/*     */     
/*     */     } 
/* 738 */     this.i.setTile(0, 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */