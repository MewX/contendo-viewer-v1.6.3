/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndexImage
/*     */ {
/*     */   private static class Counter
/*     */   {
/*     */     final int val;
/*  69 */     int count = 1;
/*     */     Counter(int val) {
/*  71 */       this.val = val;
/*     */     }
/*     */     
/*     */     boolean add(int val) {
/*  75 */       if (this.val != val)
/*  76 */         return false; 
/*  77 */       this.count++;
/*  78 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int[] getRgb(int[] rgb) {
/*  90 */       rgb[0] = (this.val & 0xFF0000) >> 16;
/*  91 */       rgb[1] = (this.val & 0xFF00) >> 8;
/*  92 */       rgb[2] = this.val & 0xFF;
/*  93 */       return rgb;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class Cube
/*     */   {
/* 102 */     static final byte[] RGB_BLACK = new byte[] { 0, 0, 0 };
/*     */     
/* 104 */     int[] min = new int[] { 0, 0, 0 }; int[] max = new int[] { 255, 255, 255 };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean done = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final IndexImage.Counter[][] colors;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     int count = 0;
/*     */ 
/*     */     
/*     */     static final int RED = 0;
/*     */ 
/*     */     
/*     */     static final int GRN = 1;
/*     */     
/*     */     static final int BLU = 2;
/*     */ 
/*     */     
/*     */     Cube(IndexImage.Counter[][] colors, int count) {
/* 131 */       this.colors = colors;
/* 132 */       this.count = count;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isDone() {
/* 139 */       return this.done;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean contains(int[] val) {
/* 149 */       int vRed = val[0];
/* 150 */       int vGrn = val[1];
/* 151 */       int vBlu = val[2];
/*     */       
/* 153 */       return (this.min[0] <= vRed && vRed <= this.max[0] && this.min[1] <= vGrn && vGrn <= this.max[1] && this.min[2] <= vBlu && vBlu <= this.max[2]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Cube split() {
/* 165 */       int c0, c1, splitChannel, dr = this.max[0] - this.min[0] + 1;
/* 166 */       int dg = this.max[1] - this.min[1] + 1;
/* 167 */       int db = this.max[2] - this.min[2] + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (dr >= dg) {
/* 173 */         if (dr >= db) { splitChannel = 0; c0 = 1; c1 = 2; }
/* 174 */         else { splitChannel = 2; c0 = 0; c1 = 1; } 
/* 175 */       } else if (dg >= db) {
/* 176 */         splitChannel = 1;
/* 177 */         c0 = 0;
/* 178 */         c1 = 2;
/*     */       } else {
/* 180 */         splitChannel = 2;
/* 181 */         c0 = 1;
/* 182 */         c1 = 0;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 195 */       Cube ret = splitChannel(splitChannel, c0, c1);
/* 196 */       if (ret != null) return ret;
/*     */ 
/*     */       
/* 199 */       ret = splitChannel(c0, splitChannel, c1);
/* 200 */       if (ret != null) return ret;
/*     */ 
/*     */       
/* 203 */       ret = splitChannel(c1, splitChannel, c0);
/* 204 */       if (ret != null) return ret;
/*     */ 
/*     */ 
/*     */       
/* 208 */       this.done = true;
/* 209 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void normalize(int splitChannel, int[] counts) {
/* 230 */       if (this.count == 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 235 */       int iMin = this.min[splitChannel];
/* 236 */       int iMax = this.max[splitChannel];
/* 237 */       int loBound = -1;
/* 238 */       int hiBound = -1;
/*     */       
/*     */       int i;
/*     */       
/* 242 */       for (i = iMin; i <= iMax; ) {
/* 243 */         if (counts[i] == 0) {
/*     */           i++;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 249 */         loBound = i;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 255 */       for (i = iMax; i >= iMin; ) {
/* 256 */         if (counts[i] == 0) {
/*     */           i--;
/*     */           
/*     */           continue;
/*     */         } 
/* 261 */         hiBound = i;
/*     */       } 
/*     */ 
/*     */       
/* 265 */       boolean flagChangedLo = (loBound != -1 && iMin != loBound);
/* 266 */       boolean flagChangedHi = (hiBound != -1 && iMax != hiBound);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       if (flagChangedLo) {
/* 279 */         this.min[splitChannel] = loBound;
/*     */       }
/* 281 */       if (flagChangedHi) {
/* 282 */         this.max[splitChannel] = hiBound;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Cube splitChannel(int splitChannel, int c0, int c1) {
/* 294 */       if (this.min[splitChannel] == this.max[splitChannel])
/*     */       {
/* 296 */         return null;
/*     */       }
/*     */       
/* 299 */       if (this.count == 0)
/*     */       {
/* 301 */         return null;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 306 */       int half = this.count / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 311 */       int[] counts = computeCounts(splitChannel, c0, c1);
/*     */       
/* 313 */       int tcount = 0;
/* 314 */       int lastAdd = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       int splitLo = this.min[splitChannel];
/* 320 */       int splitHi = this.max[splitChannel];
/* 321 */       for (int i = this.min[splitChannel]; i <= this.max[splitChannel]; i++) {
/* 322 */         int c = counts[i];
/* 323 */         if (c == 0) {
/*     */           
/* 325 */           if (tcount == 0 && i < this.max[splitChannel]) {
/* 326 */             this.min[splitChannel] = i + 1;
/*     */           
/*     */           }
/*     */         }
/* 330 */         else if (tcount + c < half) {
/* 331 */           lastAdd = i;
/* 332 */           tcount += c;
/*     */         } else {
/*     */           
/* 335 */           if (half - tcount <= tcount + c - half) {
/*     */             
/* 337 */             if (lastAdd == -1) {
/*     */               
/* 339 */               if (c == this.count) {
/*     */ 
/*     */                 
/* 342 */                 this.max[splitChannel] = i;
/* 343 */                 return null;
/*     */               } 
/*     */ 
/*     */               
/* 347 */               splitLo = i;
/* 348 */               splitHi = i + 1;
/* 349 */               tcount += c;
/*     */               
/*     */               break;
/*     */             } 
/* 353 */             splitLo = lastAdd;
/* 354 */             splitHi = i; break;
/*     */           } 
/* 356 */           if (i == this.max[splitChannel]) {
/* 357 */             if (c == this.count)
/*     */             {
/*     */               
/* 360 */               return null;
/*     */             }
/*     */ 
/*     */             
/* 364 */             splitLo = lastAdd;
/* 365 */             splitHi = i;
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 370 */           tcount += c;
/* 371 */           splitLo = i;
/* 372 */           splitHi = i + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 383 */       Cube ret = new Cube(this.colors, tcount);
/* 384 */       this.count -= tcount;
/* 385 */       ret.min[splitChannel] = this.min[splitChannel];
/* 386 */       ret.max[splitChannel] = splitLo;
/* 387 */       this.min[splitChannel] = splitHi;
/*     */ 
/*     */ 
/*     */       
/* 391 */       ret.min[c0] = this.min[c0];
/* 392 */       ret.max[c0] = this.max[c0];
/* 393 */       ret.min[c1] = this.min[c1];
/* 394 */       ret.max[c1] = this.max[c1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 408 */       normalize(splitChannel, counts);
/* 409 */       ret.normalize(splitChannel, counts);
/*     */       
/* 411 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int[] computeCounts(int splitChannel, int c0, int c1) {
/* 427 */       int splitSh4 = (2 - splitChannel) * 4;
/* 428 */       int c0Sh4 = (2 - c0) * 4;
/* 429 */       int c1Sh4 = (2 - c1) * 4;
/*     */ 
/*     */       
/* 432 */       int half = this.count / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 438 */       int[] counts = new int[256];
/* 439 */       int tcount = 0;
/*     */       
/* 441 */       int minR = this.min[0], minG = this.min[1], minB = this.min[2];
/* 442 */       int maxR = this.max[0], maxG = this.max[1], maxB = this.max[2];
/*     */       
/* 444 */       int[] minIdx = { minR >> 4, minG >> 4, minB >> 4 };
/* 445 */       int[] maxIdx = { maxR >> 4, maxG >> 4, maxB >> 4 };
/*     */       
/* 447 */       int[] vals = { 0, 0, 0 };
/* 448 */       for (int i = minIdx[splitChannel]; i <= maxIdx[splitChannel]; i++) {
/* 449 */         int idx1 = i << splitSh4;
/* 450 */         for (int j = minIdx[c0]; j <= maxIdx[c0]; j++) {
/* 451 */           int idx2 = idx1 | j << c0Sh4;
/* 452 */           for (int k = minIdx[c1]; k <= maxIdx[c1]; k++) {
/* 453 */             int idx = idx2 | k << c1Sh4;
/* 454 */             IndexImage.Counter[] v = this.colors[idx];
/* 455 */             for (IndexImage.Counter c : v) {
/* 456 */               vals = c.getRgb(vals);
/* 457 */               if (contains(vals)) {
/*     */ 
/*     */                 
/* 460 */                 counts[vals[splitChannel]] = counts[vals[splitChannel]] + c.count;
/* 461 */                 tcount += c.count;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 476 */       return counts;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 486 */       return "Cube: [" + this.min[0] + '-' + this.max[0] + "] [" + this.min[1] + '-' + this.max[1] + "] [" + this.min[2] + '-' + this.max[2] + "] n:" + this.count;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int averageColor() {
/* 497 */       if (this.count == 0)
/*     */       {
/* 499 */         return 0;
/*     */       }
/*     */       
/* 502 */       byte[] rgb = averageColorRGB(null);
/*     */       
/* 504 */       return rgb[0] << 16 & 0xFF0000 | rgb[1] << 8 & 0xFF00 | rgb[2] & 0xFF;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public byte[] averageColorRGB(byte[] rgb) {
/* 514 */       if (this.count == 0) return RGB_BLACK;
/*     */       
/* 516 */       float red = 0.0F, grn = 0.0F, blu = 0.0F;
/*     */ 
/*     */       
/* 519 */       int minR = this.min[0], minG = this.min[1], minB = this.min[2];
/* 520 */       int maxR = this.max[0], maxG = this.max[1], maxB = this.max[2];
/* 521 */       int[] minIdx = { minR >> 4, minG >> 4, minB >> 4 };
/* 522 */       int[] maxIdx = { maxR >> 4, maxG >> 4, maxB >> 4 };
/* 523 */       int[] vals = new int[3];
/*     */       
/* 525 */       for (int i = minIdx[0]; i <= maxIdx[0]; i++) {
/* 526 */         int idx1 = i << 8;
/* 527 */         for (int j = minIdx[1]; j <= maxIdx[1]; j++) {
/* 528 */           int idx2 = idx1 | j << 4;
/* 529 */           for (int k = minIdx[2]; k <= maxIdx[2]; k++) {
/* 530 */             int idx = idx2 | k;
/* 531 */             IndexImage.Counter[] v = this.colors[idx];
/* 532 */             for (IndexImage.Counter c : v) {
/* 533 */               vals = c.getRgb(vals);
/* 534 */               if (contains(vals)) {
/* 535 */                 float weight = c.count / this.count;
/* 536 */                 red += vals[0] * weight;
/* 537 */                 grn += vals[1] * weight;
/* 538 */                 blu += vals[2] * weight;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 544 */       byte[] result = (rgb == null) ? new byte[3] : rgb;
/* 545 */       result[0] = (byte)(int)(red + 0.5F);
/* 546 */       result[1] = (byte)(int)(grn + 0.5F);
/* 547 */       result[2] = (byte)(int)(blu + 0.5F);
/*     */       
/* 549 */       return result;
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
/*     */   static byte[][] computeRGB(int nCubes, Cube[] cubes) {
/* 565 */     byte[] r = new byte[nCubes];
/* 566 */     byte[] g = new byte[nCubes];
/* 567 */     byte[] b = new byte[nCubes];
/*     */     
/* 569 */     byte[] rgb = new byte[3];
/* 570 */     for (int i = 0; i < nCubes; i++) {
/* 571 */       rgb = cubes[i].averageColorRGB(rgb);
/* 572 */       r[i] = rgb[0];
/* 573 */       g[i] = rgb[1];
/* 574 */       b[i] = rgb[2];
/*     */     } 
/*     */     
/* 577 */     byte[][] result = new byte[3][];
/* 578 */     result[0] = r;
/* 579 */     result[1] = g;
/* 580 */     result[2] = b;
/*     */ 
/*     */ 
/*     */     
/* 584 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void logRGB(byte[] r, byte[] g, byte[] b) {
/* 595 */     StringBuffer buff = new StringBuffer(100);
/* 596 */     int nColors = r.length;
/* 597 */     for (int i = 0; i < nColors; i++) {
/* 598 */       String rgbStr = "(" + (r[i] + 128) + ',' + (g[i] + 128) + ',' + (b[i] + 128) + "),";
/* 599 */       buff.append(rgbStr);
/*     */     } 
/* 601 */     System.out.println("RGB:" + nColors + buff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List[] createColorList(BufferedImage bi) {
/* 612 */     int w = bi.getWidth();
/* 613 */     int h = bi.getHeight();
/*     */ 
/*     */     
/* 616 */     ArrayList[] arrayOfArrayList = new ArrayList[4096];
/*     */     
/* 618 */     for (int i_w = 0; i_w < w; i_w++) {
/* 619 */       for (int i_h = 0; i_h < h; i_h++) {
/* 620 */         int rgb = bi.getRGB(i_w, i_h) & 0xFFFFFF;
/*     */         
/* 622 */         int idx = (rgb & 0xF00000) >>> 12 | (rgb & 0xF000) >>> 8 | (rgb & 0xF0) >>> 4;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 627 */         List<Counter> v = arrayOfArrayList[idx];
/* 628 */         if (v == null) {
/*     */ 
/*     */           
/* 631 */           v = new ArrayList();
/* 632 */           v.add(new Counter(rgb));
/* 633 */           arrayOfArrayList[idx] = (ArrayList)v;
/*     */         } else {
/*     */           
/* 636 */           Iterator<Counter> i = v.iterator();
/*     */           while (true) {
/* 638 */             if (i.hasNext()) {
/*     */               
/* 640 */               if (((Counter)i.next()).add(rgb))
/*     */                 break;  continue;
/* 642 */             }  v.add(new Counter(rgb));
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 650 */     return (List[])arrayOfArrayList;
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
/*     */   static Counter[][] convertColorList(List[] colors) {
/* 668 */     Counter[] EMPTY_COUNTER = new Counter[0];
/*     */     
/* 670 */     Counter[][] colorTbl = new Counter[4096][];
/* 671 */     for (int i = 0; i < colors.length; i++) {
/* 672 */       List cl = colors[i];
/* 673 */       if (cl == null) {
/* 674 */         colorTbl[i] = EMPTY_COUNTER;
/*     */       } else {
/*     */         
/* 677 */         int nSlots = cl.size();
/* 678 */         colorTbl[i] = (Counter[])cl.toArray((Object[])new Counter[nSlots]);
/*     */ 
/*     */         
/* 681 */         colors[i] = null;
/*     */       } 
/*     */     } 
/* 684 */     return colorTbl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage getIndexedImage(BufferedImage bi, int nColors) {
/* 695 */     int w = bi.getWidth();
/* 696 */     int h = bi.getHeight();
/*     */ 
/*     */     
/* 699 */     List[] colors = createColorList(bi);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 704 */     Counter[][] colorTbl = convertColorList(colors);
/*     */ 
/*     */     
/* 707 */     colors = null;
/*     */     
/* 709 */     int nCubes = 1;
/* 710 */     int fCube = 0;
/* 711 */     Cube[] cubes = new Cube[nColors];
/* 712 */     cubes[0] = new Cube(colorTbl, w * h);
/*     */     
/* 714 */     while (nCubes < nColors) {
/*     */       do {
/* 716 */         fCube++;
/* 717 */       } while (cubes[fCube].isDone() && fCube != nCubes);
/*     */       
/* 719 */       if (fCube == nCubes) {
/*     */         break;
/*     */       }
/*     */       
/* 723 */       Cube c = cubes[fCube];
/* 724 */       Cube nc = c.split();
/* 725 */       if (nc != null) {
/*     */ 
/*     */         
/* 728 */         if (nc.count > c.count) {
/*     */           
/* 730 */           Cube tmp = c; c = nc; nc = tmp;
/*     */         } 
/* 732 */         int j = fCube;
/* 733 */         int cnt = c.count; int i;
/* 734 */         for (i = fCube + 1; i < nCubes && 
/* 735 */           (cubes[i]).count >= cnt; i++)
/*     */         {
/* 737 */           cubes[j++] = cubes[i];
/*     */         }
/* 739 */         cubes[j++] = c;
/*     */         
/* 741 */         cnt = nc.count;
/* 742 */         while (j < nCubes && 
/* 743 */           (cubes[j]).count >= cnt)
/*     */         {
/* 745 */           j++;
/*     */         }
/* 747 */         for (i = nCubes; i > j; i--)
/* 748 */           cubes[i] = cubes[i - 1]; 
/* 749 */         cubes[j++] = nc;
/* 750 */         nCubes++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 755 */     byte[][] rgbTbl = computeRGB(nCubes, cubes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 761 */     IndexColorModel icm = new IndexColorModel(8, nCubes, rgbTbl[0], rgbTbl[1], rgbTbl[2]);
/*     */     
/* 763 */     BufferedImage indexed = new BufferedImage(w, h, 13, icm);
/*     */     
/* 765 */     Graphics2D g2d = indexed.createGraphics();
/* 766 */     g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
/*     */ 
/*     */     
/* 769 */     g2d.drawImage(bi, 0, 0, (ImageObserver)null);
/* 770 */     g2d.dispose();
/*     */     
/*     */     int bits;
/*     */     
/* 774 */     for (bits = 1; bits <= 8 && 
/* 775 */       1 << bits < nCubes; bits++);
/*     */ 
/*     */ 
/*     */     
/* 779 */     if (bits > 4)
/*     */     {
/* 781 */       return indexed;
/*     */     }
/*     */ 
/*     */     
/* 785 */     if (bits == 3) bits = 4; 
/* 786 */     ColorModel cm = new IndexColorModel(bits, nCubes, rgbTbl[0], rgbTbl[1], rgbTbl[2]);
/*     */ 
/*     */     
/* 789 */     SampleModel sm = new MultiPixelPackedSampleModel(0, w, h, bits);
/* 790 */     WritableRaster ras = Raster.createWritableRaster(sm, new Point(0, 0));
/*     */ 
/*     */     
/* 793 */     bi = indexed;
/* 794 */     indexed = new BufferedImage(cm, ras, bi.isAlphaPremultiplied(), null);
/* 795 */     GraphicsUtil.copyData(bi, indexed);
/* 796 */     return indexed;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/IndexImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */