/*      */ package org.apache.batik.ext.awt.image.rendered;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RasterOp;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MorphologyOp
/*      */   implements BufferedImageOp, RasterOp
/*      */ {
/*      */   private int radiusX;
/*      */   private int radiusY;
/*      */   private boolean doDilation;
/*      */   private final int rangeX;
/*      */   private final int rangeY;
/*   76 */   private final ColorSpace sRGB = ColorSpace.getInstance(1000);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   81 */   private final ColorSpace lRGB = ColorSpace.getInstance(1004);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MorphologyOp(int radiusX, int radiusY, boolean doDilation) {
/*   94 */     if (radiusX <= 0 || radiusY <= 0) {
/*   95 */       throw new IllegalArgumentException("The radius of X-axis or Y-axis should not be Zero or Negatives.");
/*      */     }
/*      */     
/*   98 */     this.radiusX = radiusX;
/*   99 */     this.radiusY = radiusY;
/*  100 */     this.doDilation = doDilation;
/*  101 */     this.rangeX = 2 * radiusX + 1;
/*  102 */     this.rangeY = 2 * radiusY + 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public Rectangle2D getBounds2D(Raster src) {
/*  107 */     checkCompatible(src.getSampleModel());
/*  108 */     return new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*      */   }
/*      */   
/*      */   public Rectangle2D getBounds2D(BufferedImage src) {
/*  112 */     return new Rectangle(0, 0, src.getWidth(), src.getHeight());
/*      */   }
/*      */ 
/*      */   
/*      */   public Point2D getPoint2D(Point2D srcPt, Point2D destPt) {
/*  117 */     if (destPt == null)
/*  118 */       destPt = new Point2D.Float(); 
/*  119 */     destPt.setLocation(srcPt.getX(), srcPt.getY());
/*  120 */     return destPt;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkCompatible(ColorModel colorModel, SampleModel sampleModel) {
/*  125 */     ColorSpace cs = colorModel.getColorSpace();
/*      */ 
/*      */     
/*  128 */     if (!cs.equals(this.sRGB) && !cs.equals(this.lRGB)) {
/*  129 */       throw new IllegalArgumentException("Expected CS_sRGB or CS_LINEAR_RGB color model");
/*      */     }
/*      */     
/*  132 */     if (!(colorModel instanceof DirectColorModel)) {
/*  133 */       throw new IllegalArgumentException("colorModel should be an instance of DirectColorModel");
/*      */     }
/*      */     
/*  136 */     if (sampleModel.getDataType() != 3) {
/*  137 */       throw new IllegalArgumentException("colorModel's transferType should be DataBuffer.TYPE_INT");
/*      */     }
/*      */     
/*  140 */     DirectColorModel dcm = (DirectColorModel)colorModel;
/*  141 */     if (dcm.getRedMask() != 16711680)
/*  142 */       throw new IllegalArgumentException("red mask in source should be 0x00ff0000"); 
/*  143 */     if (dcm.getGreenMask() != 65280)
/*  144 */       throw new IllegalArgumentException("green mask in source should be 0x0000ff00"); 
/*  145 */     if (dcm.getBlueMask() != 255)
/*  146 */       throw new IllegalArgumentException("blue mask in source should be 0x000000ff"); 
/*  147 */     if (dcm.getAlphaMask() != -16777216) {
/*  148 */       throw new IllegalArgumentException("alpha mask in source should be 0xff000000");
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean isCompatible(ColorModel colorModel, SampleModel sampleModel) {
/*  153 */     ColorSpace cs = colorModel.getColorSpace();
/*      */     
/*  155 */     if (cs != ColorSpace.getInstance(1000) && cs != ColorSpace.getInstance(1004))
/*      */     {
/*      */       
/*  158 */       return false;
/*      */     }
/*      */     
/*  161 */     if (!(colorModel instanceof DirectColorModel)) {
/*  162 */       return false;
/*      */     }
/*      */     
/*  165 */     if (sampleModel.getDataType() != 3) {
/*  166 */       return false;
/*      */     }
/*      */     
/*  169 */     DirectColorModel dcm = (DirectColorModel)colorModel;
/*  170 */     if (dcm.getRedMask() != 16711680)
/*  171 */       return false; 
/*  172 */     if (dcm.getGreenMask() != 65280)
/*  173 */       return false; 
/*  174 */     if (dcm.getBlueMask() != 255)
/*  175 */       return false; 
/*  176 */     if (dcm.getAlphaMask() != -16777216)
/*  177 */       return false; 
/*  178 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkCompatible(SampleModel model) {
/*  183 */     if (!(model instanceof SinglePixelPackedSampleModel)) {
/*  184 */       throw new IllegalArgumentException("MorphologyOp only works with Rasters using SinglePixelPackedSampleModels");
/*      */     }
/*      */ 
/*      */     
/*  188 */     int nBands = model.getNumBands();
/*  189 */     if (nBands != 4) {
/*  190 */       throw new IllegalArgumentException("MorphologyOp only words with Rasters having 4 bands");
/*      */     }
/*      */     
/*  193 */     if (model.getDataType() != 3) {
/*  194 */       throw new IllegalArgumentException("MorphologyOp only works with Rasters using DataBufferInt");
/*      */     }
/*      */ 
/*      */     
/*  198 */     int[] bitOffsets = ((SinglePixelPackedSampleModel)model).getBitOffsets();
/*  199 */     for (int i = 0; i < bitOffsets.length; i++) {
/*  200 */       if (bitOffsets[i] % 8 != 0) {
/*  201 */         throw new IllegalArgumentException("MorphologyOp only works with Rasters using 8 bits per band : " + i + " : " + bitOffsets[i]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  208 */     return null;
/*      */   }
/*      */   
/*      */   public WritableRaster createCompatibleDestRaster(Raster src) {
/*  212 */     checkCompatible(src.getSampleModel());
/*      */     
/*  214 */     return src.createCompatibleWritableRaster();
/*      */   }
/*      */ 
/*      */   
/*      */   public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
/*  219 */     BufferedImage dest = null;
/*  220 */     if (destCM == null) {
/*  221 */       destCM = src.getColorModel();
/*      */     }
/*      */     
/*  224 */     WritableRaster wr = destCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight());
/*      */     
/*  226 */     checkCompatible(destCM, wr.getSampleModel());
/*      */     
/*  228 */     dest = new BufferedImage(destCM, wr, destCM.isAlphaPremultiplied(), null);
/*      */     
/*  230 */     return dest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final boolean isBetter(int v1, int v2, boolean doDilation) {
/*  238 */     if (v1 > v2)
/*  239 */       return doDilation; 
/*  240 */     if (v1 < v2)
/*  241 */       return !doDilation; 
/*  242 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void specialProcessRow(Raster src, WritableRaster dest) {
/*  250 */     int w = src.getWidth();
/*  251 */     int h = src.getHeight();
/*      */ 
/*      */     
/*  254 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/*  255 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*      */ 
/*      */ 
/*      */     
/*  259 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)src.getSampleModel();
/*      */     
/*  261 */     int srcOff = srcDB.getOffset() + sppsm.getOffset(src.getMinX() - src.getSampleModelTranslateX(), src.getMinY() - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  266 */     sppsm = (SinglePixelPackedSampleModel)dest.getSampleModel();
/*  267 */     int dstOff = dstDB.getOffset() + sppsm.getOffset(dest.getMinX() - dest.getSampleModelTranslateX(), dest.getMinY() - dest.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  273 */     int srcScanStride = ((SinglePixelPackedSampleModel)src.getSampleModel()).getScanlineStride();
/*  274 */     int dstScanStride = ((SinglePixelPackedSampleModel)dest.getSampleModel()).getScanlineStride();
/*      */ 
/*      */     
/*  277 */     int[] srcPixels = srcDB.getBankData()[0];
/*  278 */     int[] destPixels = dstDB.getBankData()[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  302 */     if (w <= this.radiusX) {
/*  303 */       for (int i = 0; i < h; i++)
/*      */       {
/*  305 */         int sp = srcOff + i * srcScanStride;
/*  306 */         int dp = dstOff + i * dstScanStride;
/*  307 */         int pel = srcPixels[sp++];
/*  308 */         int a = pel >>> 24;
/*  309 */         int r = pel & 0xFF0000;
/*  310 */         int g = pel & 0xFF00;
/*  311 */         int b = pel & 0xFF;
/*      */         int k;
/*  313 */         for (k = 1; k < w; k++) {
/*  314 */           int currentPixel = srcPixels[sp++];
/*  315 */           int a1 = currentPixel >>> 24;
/*  316 */           int r1 = currentPixel & 0xFF0000;
/*  317 */           int g1 = currentPixel & 0xFF00;
/*  318 */           int b1 = currentPixel & 0xFF;
/*      */           
/*  320 */           if (isBetter(a1, a, this.doDilation)) {
/*  321 */             a = a1;
/*      */           }
/*  323 */           if (isBetter(r1, r, this.doDilation)) {
/*  324 */             r = r1;
/*      */           }
/*  326 */           if (isBetter(g1, g, this.doDilation)) {
/*  327 */             g = g1;
/*      */           }
/*  329 */           if (isBetter(b1, b, this.doDilation)) {
/*  330 */             b = b1;
/*      */           }
/*      */         } 
/*      */         
/*  334 */         for (k = 0; k < w; k++) {
/*  335 */           destPixels[dp++] = a << 24 | r | g | b;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  344 */       int[] bufferA = new int[w];
/*  345 */       int[] bufferR = new int[w];
/*  346 */       int[] bufferG = new int[w];
/*  347 */       int[] bufferB = new int[w];
/*      */       
/*  349 */       for (int i = 0; i < h; i++) {
/*      */ 
/*      */         
/*  352 */         int sp = srcOff + i * srcScanStride;
/*  353 */         int dp = dstOff + i * dstScanStride;
/*      */         
/*  355 */         int bufferHead = 0;
/*  356 */         int maxIndexA = 0;
/*  357 */         int maxIndexR = 0;
/*  358 */         int maxIndexG = 0;
/*  359 */         int maxIndexB = 0;
/*      */         
/*  361 */         int pel = srcPixels[sp++];
/*  362 */         int a = pel >>> 24;
/*  363 */         int r = pel & 0xFF0000;
/*  364 */         int g = pel & 0xFF00;
/*  365 */         int b = pel & 0xFF;
/*  366 */         bufferA[0] = a;
/*  367 */         bufferR[0] = r;
/*  368 */         bufferG[0] = g;
/*  369 */         bufferB[0] = b;
/*      */         
/*  371 */         for (int k = 1; k <= this.radiusX; k++) {
/*  372 */           int currentPixel = srcPixels[sp++];
/*  373 */           int a1 = currentPixel >>> 24;
/*  374 */           int r1 = currentPixel & 0xFF0000;
/*  375 */           int g1 = currentPixel & 0xFF00;
/*  376 */           int b1 = currentPixel & 0xFF;
/*  377 */           bufferA[k] = a1;
/*  378 */           bufferR[k] = r1;
/*  379 */           bufferG[k] = g1;
/*  380 */           bufferB[k] = b1;
/*      */           
/*  382 */           if (isBetter(a1, a, this.doDilation)) {
/*  383 */             a = a1;
/*  384 */             maxIndexA = k;
/*      */           } 
/*  386 */           if (isBetter(r1, r, this.doDilation)) {
/*  387 */             r = r1;
/*  388 */             maxIndexR = k;
/*      */           } 
/*  390 */           if (isBetter(g1, g, this.doDilation)) {
/*  391 */             g = g1;
/*  392 */             maxIndexG = k;
/*      */           } 
/*  394 */           if (isBetter(b1, b, this.doDilation)) {
/*  395 */             b = b1;
/*  396 */             maxIndexB = k;
/*      */           } 
/*      */         } 
/*  399 */         destPixels[dp++] = a << 24 | r | g | b;
/*      */ 
/*      */         
/*      */         int j;
/*      */         
/*  404 */         for (j = 1; j <= w - this.radiusX - 1; j++) {
/*  405 */           int lastPixel = srcPixels[sp++];
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  410 */           a = bufferA[maxIndexA];
/*  411 */           int a1 = lastPixel >>> 24;
/*  412 */           bufferA[j + this.radiusX] = a1;
/*  413 */           if (isBetter(a1, a, this.doDilation)) {
/*  414 */             a = a1;
/*  415 */             maxIndexA = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  420 */           r = bufferR[maxIndexR];
/*  421 */           int r1 = lastPixel & 0xFF0000;
/*  422 */           bufferR[j + this.radiusX] = r1;
/*  423 */           if (isBetter(r1, r, this.doDilation)) {
/*  424 */             r = r1;
/*  425 */             maxIndexR = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  430 */           g = bufferG[maxIndexG];
/*  431 */           int g1 = lastPixel & 0xFF00;
/*  432 */           bufferG[j + this.radiusX] = g1;
/*  433 */           if (isBetter(g1, g, this.doDilation)) {
/*  434 */             g = g1;
/*  435 */             maxIndexG = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  440 */           b = bufferB[maxIndexB];
/*  441 */           int b1 = lastPixel & 0xFF;
/*  442 */           bufferB[j + this.radiusX] = b1;
/*  443 */           if (isBetter(b1, b, this.doDilation)) {
/*  444 */             b = b1;
/*  445 */             maxIndexB = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  452 */           destPixels[dp++] = a << 24 | r | g | b;
/*      */         } 
/*      */ 
/*      */         
/*  456 */         for (j = w - this.radiusX; j <= this.radiusX; j++) {
/*  457 */           destPixels[dp] = destPixels[dp - 1];
/*  458 */           dp++;
/*      */         } 
/*      */ 
/*      */         
/*  462 */         for (j = this.radiusX + 1; j < w; j++) {
/*      */           
/*  464 */           if (maxIndexA == bufferHead) {
/*  465 */             a = bufferA[bufferHead + 1];
/*  466 */             maxIndexA = bufferHead + 1;
/*  467 */             for (int m = bufferHead + 2; m < w; m++) {
/*  468 */               int a1 = bufferA[m];
/*  469 */               if (isBetter(a1, a, this.doDilation)) {
/*  470 */                 a = a1;
/*  471 */                 maxIndexA = m;
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/*  476 */             a = bufferA[maxIndexA];
/*      */           } 
/*  478 */           if (maxIndexR == bufferHead) {
/*  479 */             r = bufferR[bufferHead + 1];
/*  480 */             maxIndexR = bufferHead + 1;
/*  481 */             for (int m = bufferHead + 2; m < w; m++) {
/*  482 */               int r1 = bufferR[m];
/*  483 */               if (isBetter(r1, r, this.doDilation)) {
/*  484 */                 r = r1;
/*  485 */                 maxIndexR = m;
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/*  490 */             r = bufferR[maxIndexR];
/*      */           } 
/*      */           
/*  493 */           if (maxIndexG == bufferHead) {
/*  494 */             g = bufferG[bufferHead + 1];
/*  495 */             maxIndexG = bufferHead + 1;
/*  496 */             for (int m = bufferHead + 2; m < w; m++) {
/*  497 */               int g1 = bufferG[m];
/*  498 */               if (isBetter(g1, g, this.doDilation)) {
/*  499 */                 g = g1;
/*  500 */                 maxIndexG = m;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/*  506 */             g = bufferG[maxIndexG];
/*      */           } 
/*      */           
/*  509 */           if (maxIndexB == bufferHead) {
/*  510 */             b = bufferB[bufferHead + 1];
/*  511 */             maxIndexB = bufferHead + 1;
/*  512 */             for (int m = bufferHead + 2; m < w; m++) {
/*  513 */               int b1 = bufferB[m];
/*  514 */               if (isBetter(b1, b, this.doDilation)) {
/*  515 */                 b = b1;
/*  516 */                 maxIndexB = m;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/*  522 */             b = bufferB[maxIndexB];
/*      */           } 
/*      */ 
/*      */           
/*  526 */           bufferHead++;
/*      */           
/*  528 */           destPixels[dp++] = a << 24 | r | g | b;
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
/*      */   private void specialProcessColumn(Raster src, WritableRaster dest) {
/*  541 */     int w = src.getWidth();
/*  542 */     int h = src.getHeight();
/*      */ 
/*      */     
/*  545 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*      */ 
/*      */     
/*  548 */     int dstOff = dstDB.getOffset();
/*      */ 
/*      */ 
/*      */     
/*  552 */     int dstScanStride = ((SinglePixelPackedSampleModel)dest.getSampleModel()).getScanlineStride();
/*      */ 
/*      */     
/*  555 */     int[] destPixels = dstDB.getBankData()[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  579 */     if (h <= this.radiusY) {
/*  580 */       for (int j = 0; j < w; j++) {
/*  581 */         int dp = dstOff + j;
/*  582 */         int cp = dstOff + j;
/*  583 */         int pel = destPixels[cp];
/*  584 */         cp += dstScanStride;
/*  585 */         int a = pel >>> 24;
/*  586 */         int r = pel & 0xFF0000;
/*  587 */         int g = pel & 0xFF00;
/*  588 */         int b = pel & 0xFF;
/*      */         int k;
/*  590 */         for (k = 1; k < h; k++) {
/*  591 */           int currentPixel = destPixels[cp];
/*  592 */           cp += dstScanStride;
/*  593 */           int a1 = currentPixel >>> 24;
/*  594 */           int r1 = currentPixel & 0xFF0000;
/*  595 */           int g1 = currentPixel & 0xFF00;
/*  596 */           int b1 = currentPixel & 0xFF;
/*      */           
/*  598 */           if (isBetter(a1, a, this.doDilation)) {
/*  599 */             a = a1;
/*      */           }
/*  601 */           if (isBetter(r1, r, this.doDilation)) {
/*  602 */             r = r1;
/*      */           }
/*  604 */           if (isBetter(g1, g, this.doDilation)) {
/*  605 */             g = g1;
/*      */           }
/*  607 */           if (isBetter(b1, b, this.doDilation)) {
/*  608 */             b = b1;
/*      */           }
/*      */         } 
/*  611 */         for (k = 0; k < h; k++) {
/*  612 */           destPixels[dp] = a << 24 | r | g | b;
/*  613 */           dp += dstScanStride;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  623 */       int[] bufferA = new int[h];
/*  624 */       int[] bufferR = new int[h];
/*  625 */       int[] bufferG = new int[h];
/*  626 */       int[] bufferB = new int[h];
/*      */       
/*  628 */       for (int j = 0; j < w; j++) {
/*      */ 
/*      */         
/*  631 */         int dp = dstOff + j;
/*  632 */         int cp = dstOff + j;
/*      */         
/*  634 */         int bufferHead = 0;
/*  635 */         int maxIndexA = 0;
/*  636 */         int maxIndexR = 0;
/*  637 */         int maxIndexG = 0;
/*  638 */         int maxIndexB = 0;
/*      */         
/*  640 */         int pel = destPixels[cp];
/*  641 */         cp += dstScanStride;
/*  642 */         int a = pel >>> 24;
/*  643 */         int r = pel & 0xFF0000;
/*  644 */         int g = pel & 0xFF00;
/*  645 */         int b = pel & 0xFF;
/*  646 */         bufferA[0] = a;
/*  647 */         bufferR[0] = r;
/*  648 */         bufferG[0] = g;
/*  649 */         bufferB[0] = b;
/*      */         
/*  651 */         for (int k = 1; k <= this.radiusY; k++) {
/*  652 */           int currentPixel = destPixels[cp];
/*  653 */           cp += dstScanStride;
/*  654 */           int a1 = currentPixel >>> 24;
/*  655 */           int r1 = currentPixel & 0xFF0000;
/*  656 */           int g1 = currentPixel & 0xFF00;
/*  657 */           int b1 = currentPixel & 0xFF;
/*  658 */           bufferA[k] = a1;
/*  659 */           bufferR[k] = r1;
/*  660 */           bufferG[k] = g1;
/*  661 */           bufferB[k] = b1;
/*      */           
/*  663 */           if (isBetter(a1, a, this.doDilation)) {
/*  664 */             a = a1;
/*  665 */             maxIndexA = k;
/*      */           } 
/*  667 */           if (isBetter(r1, r, this.doDilation)) {
/*  668 */             r = r1;
/*  669 */             maxIndexR = k;
/*      */           } 
/*  671 */           if (isBetter(g1, g, this.doDilation)) {
/*  672 */             g = g1;
/*  673 */             maxIndexG = k;
/*      */           } 
/*  675 */           if (isBetter(b1, b, this.doDilation)) {
/*  676 */             b = b1;
/*  677 */             maxIndexB = k;
/*      */           } 
/*      */         } 
/*      */         
/*  681 */         destPixels[dp] = a << 24 | r | g | b;
/*  682 */         dp += dstScanStride;
/*      */ 
/*      */         
/*      */         int i;
/*      */         
/*  687 */         for (i = 1; i <= h - this.radiusY - 1; i++) {
/*  688 */           int lastPixel = destPixels[cp];
/*  689 */           cp += dstScanStride;
/*      */ 
/*      */ 
/*      */           
/*  693 */           a = bufferA[maxIndexA];
/*  694 */           int a1 = lastPixel >>> 24;
/*  695 */           bufferA[i + this.radiusY] = a1;
/*  696 */           if (isBetter(a1, a, this.doDilation)) {
/*  697 */             a = a1;
/*  698 */             maxIndexA = i + this.radiusY;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  703 */           r = bufferR[maxIndexR];
/*  704 */           int r1 = lastPixel & 0xFF0000;
/*  705 */           bufferR[i + this.radiusY] = r1;
/*  706 */           if (isBetter(r1, r, this.doDilation)) {
/*  707 */             r = r1;
/*  708 */             maxIndexR = i + this.radiusY;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  713 */           g = bufferG[maxIndexG];
/*  714 */           int g1 = lastPixel & 0xFF00;
/*  715 */           bufferG[i + this.radiusY] = g1;
/*  716 */           if (isBetter(g1, g, this.doDilation)) {
/*  717 */             g = g1;
/*  718 */             maxIndexG = i + this.radiusY;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  723 */           b = bufferB[maxIndexB];
/*  724 */           int b1 = lastPixel & 0xFF;
/*  725 */           bufferB[i + this.radiusY] = b1;
/*  726 */           if (isBetter(b1, b, this.doDilation)) {
/*  727 */             b = b1;
/*  728 */             maxIndexB = i + this.radiusY;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  735 */           destPixels[dp] = a << 24 | r | g | b;
/*  736 */           dp += dstScanStride;
/*      */         } 
/*      */ 
/*      */         
/*  740 */         for (i = h - this.radiusY; i <= this.radiusY; i++) {
/*  741 */           destPixels[dp] = destPixels[dp - dstScanStride];
/*  742 */           dp += dstScanStride;
/*      */         } 
/*      */ 
/*      */         
/*  746 */         for (i = this.radiusY + 1; i < h; i++) {
/*      */           
/*  748 */           if (maxIndexA == bufferHead) {
/*  749 */             a = bufferA[bufferHead + 1];
/*  750 */             maxIndexA = bufferHead + 1;
/*  751 */             for (int m = bufferHead + 2; m < h; m++) {
/*  752 */               int a1 = bufferA[m];
/*  753 */               if (isBetter(a1, a, this.doDilation)) {
/*  754 */                 a = a1;
/*  755 */                 maxIndexA = m;
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/*  760 */             a = bufferA[maxIndexA];
/*      */           } 
/*  762 */           if (maxIndexR == bufferHead) {
/*  763 */             r = bufferR[bufferHead + 1];
/*  764 */             maxIndexR = bufferHead + 1;
/*  765 */             for (int m = bufferHead + 2; m < h; m++) {
/*  766 */               int r1 = bufferR[m];
/*  767 */               if (isBetter(r1, r, this.doDilation)) {
/*  768 */                 r = r1;
/*  769 */                 maxIndexR = m;
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/*  774 */             r = bufferR[maxIndexR];
/*      */           } 
/*      */           
/*  777 */           if (maxIndexG == bufferHead) {
/*  778 */             g = bufferG[bufferHead + 1];
/*  779 */             maxIndexG = bufferHead + 1;
/*  780 */             for (int m = bufferHead + 2; m < h; m++) {
/*  781 */               int g1 = bufferG[m];
/*  782 */               if (isBetter(g1, g, this.doDilation)) {
/*  783 */                 g = g1;
/*  784 */                 maxIndexG = m;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/*  790 */             g = bufferG[maxIndexG];
/*      */           } 
/*      */           
/*  793 */           if (maxIndexB == bufferHead) {
/*  794 */             b = bufferB[bufferHead + 1];
/*  795 */             maxIndexB = bufferHead + 1;
/*  796 */             for (int m = bufferHead + 2; m < h; m++) {
/*  797 */               int b1 = bufferB[m];
/*  798 */               if (isBetter(b1, b, this.doDilation)) {
/*  799 */                 b = b1;
/*  800 */                 maxIndexB = m;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/*  806 */             b = bufferB[maxIndexB];
/*      */           } 
/*      */ 
/*      */           
/*  810 */           bufferHead++;
/*      */           
/*  812 */           destPixels[dp] = a << 24 | r | g | b;
/*  813 */           dp += dstScanStride;
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
/*      */   public WritableRaster filter(Raster src, WritableRaster dest) {
/*  842 */     if (dest != null) { checkCompatible(dest.getSampleModel()); }
/*      */     else
/*  844 */     { if (src == null)
/*  845 */         throw new IllegalArgumentException("src should not be null when dest is null"); 
/*  846 */       dest = createCompatibleDestRaster(src); }
/*      */ 
/*      */     
/*  849 */     int w = src.getWidth();
/*  850 */     int h = src.getHeight();
/*      */ 
/*      */     
/*  853 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/*  854 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*      */ 
/*      */     
/*  857 */     int srcOff = srcDB.getOffset();
/*  858 */     int dstOff = dstDB.getOffset();
/*      */ 
/*      */ 
/*      */     
/*  862 */     int srcScanStride = ((SinglePixelPackedSampleModel)src.getSampleModel()).getScanlineStride();
/*  863 */     int dstScanStride = ((SinglePixelPackedSampleModel)dest.getSampleModel()).getScanlineStride();
/*      */ 
/*      */     
/*  866 */     int[] srcPixels = srcDB.getBankData()[0];
/*  867 */     int[] destPixels = dstDB.getBankData()[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  907 */     if (w <= 2 * this.radiusX) {
/*  908 */       specialProcessRow(src, dest);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  915 */       int[] bufferA = new int[this.rangeX];
/*  916 */       int[] bufferR = new int[this.rangeX];
/*  917 */       int[] bufferG = new int[this.rangeX];
/*  918 */       int[] bufferB = new int[this.rangeX];
/*      */       
/*  920 */       for (int i = 0; i < h; i++) {
/*      */ 
/*      */         
/*  923 */         int sp = srcOff + i * srcScanStride;
/*  924 */         int dp = dstOff + i * dstScanStride;
/*  925 */         int bufferHead = 0;
/*  926 */         int maxIndexA = 0;
/*  927 */         int maxIndexR = 0;
/*  928 */         int maxIndexG = 0;
/*  929 */         int maxIndexB = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  935 */         int pel = srcPixels[sp++];
/*  936 */         int a = pel >>> 24;
/*  937 */         int r = pel & 0xFF0000;
/*  938 */         int g = pel & 0xFF00;
/*  939 */         int b = pel & 0xFF;
/*  940 */         bufferA[0] = a;
/*  941 */         bufferR[0] = r;
/*  942 */         bufferG[0] = g;
/*  943 */         bufferB[0] = b;
/*      */         
/*  945 */         for (int k = 1; k <= this.radiusX; k++) {
/*  946 */           int currentPixel = srcPixels[sp++];
/*  947 */           int a1 = currentPixel >>> 24;
/*  948 */           int r1 = currentPixel & 0xFF0000;
/*  949 */           int g1 = currentPixel & 0xFF00;
/*  950 */           int b1 = currentPixel & 0xFF;
/*  951 */           bufferA[k] = a1;
/*  952 */           bufferR[k] = r1;
/*  953 */           bufferG[k] = g1;
/*  954 */           bufferB[k] = b1;
/*      */           
/*  956 */           if (isBetter(a1, a, this.doDilation)) {
/*  957 */             a = a1;
/*  958 */             maxIndexA = k;
/*      */           } 
/*  960 */           if (isBetter(r1, r, this.doDilation)) {
/*  961 */             r = r1;
/*  962 */             maxIndexR = k;
/*      */           } 
/*  964 */           if (isBetter(g1, g, this.doDilation)) {
/*  965 */             g = g1;
/*  966 */             maxIndexG = k;
/*      */           } 
/*  968 */           if (isBetter(b1, b, this.doDilation)) {
/*  969 */             b = b1;
/*  970 */             maxIndexB = k;
/*      */           } 
/*      */         } 
/*  973 */         destPixels[dp++] = a << 24 | r | g | b;
/*      */ 
/*      */         
/*      */         int j;
/*      */         
/*  978 */         for (j = 1; j <= this.radiusX; j++) {
/*  979 */           int lastPixel = srcPixels[sp++];
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  984 */           a = bufferA[maxIndexA];
/*  985 */           int a1 = lastPixel >>> 24;
/*  986 */           bufferA[j + this.radiusX] = a1;
/*  987 */           if (isBetter(a1, a, this.doDilation)) {
/*  988 */             a = a1;
/*  989 */             maxIndexA = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  994 */           r = bufferR[maxIndexR];
/*  995 */           int r1 = lastPixel & 0xFF0000;
/*  996 */           bufferR[j + this.radiusX] = r1;
/*  997 */           if (isBetter(r1, r, this.doDilation)) {
/*  998 */             r = r1;
/*  999 */             maxIndexR = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1004 */           g = bufferG[maxIndexG];
/* 1005 */           int g1 = lastPixel & 0xFF00;
/* 1006 */           bufferG[j + this.radiusX] = g1;
/* 1007 */           if (isBetter(g1, g, this.doDilation)) {
/* 1008 */             g = g1;
/* 1009 */             maxIndexG = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1014 */           b = bufferB[maxIndexB];
/* 1015 */           int b1 = lastPixel & 0xFF;
/* 1016 */           bufferB[j + this.radiusX] = b1;
/* 1017 */           if (isBetter(b1, b, this.doDilation)) {
/* 1018 */             b = b1;
/* 1019 */             maxIndexB = j + this.radiusX;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1026 */           destPixels[dp++] = a << 24 | r | g | b;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1033 */         for (j = this.radiusX + 1; j <= w - 1 - this.radiusX; j++) {
/* 1034 */           int lastPixel = srcPixels[sp++];
/* 1035 */           int a1 = lastPixel >>> 24;
/* 1036 */           int r1 = lastPixel & 0xFF0000;
/* 1037 */           int g1 = lastPixel & 0xFF00;
/* 1038 */           int b1 = lastPixel & 0xFF;
/* 1039 */           bufferA[bufferHead] = a1;
/* 1040 */           bufferR[bufferHead] = r1;
/* 1041 */           bufferG[bufferHead] = g1;
/* 1042 */           bufferB[bufferHead] = b1;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1047 */           if (maxIndexA == bufferHead) {
/* 1048 */             a = bufferA[0];
/* 1049 */             maxIndexA = 0;
/* 1050 */             for (int n = 1; n < this.rangeX; n++) {
/* 1051 */               a1 = bufferA[n];
/* 1052 */               if (isBetter(a1, a, this.doDilation)) {
/* 1053 */                 a = a1;
/* 1054 */                 maxIndexA = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1060 */             a = bufferA[maxIndexA];
/* 1061 */             if (isBetter(a1, a, this.doDilation)) {
/* 1062 */               a = a1;
/* 1063 */               maxIndexA = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1071 */           if (maxIndexR == bufferHead) {
/* 1072 */             r = bufferR[0];
/* 1073 */             maxIndexR = 0;
/* 1074 */             for (int n = 1; n < this.rangeX; n++) {
/* 1075 */               r1 = bufferR[n];
/* 1076 */               if (isBetter(r1, r, this.doDilation)) {
/* 1077 */                 r = r1;
/* 1078 */                 maxIndexR = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1084 */             r = bufferR[maxIndexR];
/* 1085 */             if (isBetter(r1, r, this.doDilation)) {
/* 1086 */               r = r1;
/* 1087 */               maxIndexR = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1095 */           if (maxIndexG == bufferHead) {
/* 1096 */             g = bufferG[0];
/* 1097 */             maxIndexG = 0;
/* 1098 */             for (int n = 1; n < this.rangeX; n++) {
/* 1099 */               g1 = bufferG[n];
/* 1100 */               if (isBetter(g1, g, this.doDilation)) {
/* 1101 */                 g = g1;
/* 1102 */                 maxIndexG = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1108 */             g = bufferG[maxIndexG];
/* 1109 */             if (isBetter(g1, g, this.doDilation)) {
/* 1110 */               g = g1;
/* 1111 */               maxIndexG = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1119 */           if (maxIndexB == bufferHead) {
/* 1120 */             b = bufferB[0];
/* 1121 */             maxIndexB = 0;
/* 1122 */             for (int n = 1; n < this.rangeX; n++) {
/* 1123 */               b1 = bufferB[n];
/* 1124 */               if (isBetter(b1, b, this.doDilation)) {
/* 1125 */                 b = b1;
/* 1126 */                 maxIndexB = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1132 */             b = bufferB[maxIndexB];
/* 1133 */             if (isBetter(b1, b, this.doDilation)) {
/* 1134 */               b = b1;
/* 1135 */               maxIndexB = bufferHead;
/*      */             } 
/*      */           } 
/* 1138 */           destPixels[dp++] = a << 24 | r | g | b;
/* 1139 */           bufferHead = (bufferHead + 1) % this.rangeX;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1150 */         int tail = (bufferHead == 0) ? (this.rangeX - 1) : (bufferHead - 1);
/* 1151 */         int count = this.rangeX - 1;
/*      */         
/* 1153 */         for (int m = w - this.radiusX; m < w; m++) {
/* 1154 */           int head = (bufferHead + 1) % this.rangeX;
/*      */           
/* 1156 */           if (maxIndexA == bufferHead) {
/* 1157 */             a = bufferA[tail];
/* 1158 */             int hd = head;
/* 1159 */             for (int n = 1; n < count; n++) {
/* 1160 */               int a1 = bufferA[hd];
/* 1161 */               if (isBetter(a1, a, this.doDilation)) {
/* 1162 */                 a = a1;
/* 1163 */                 maxIndexA = hd;
/*      */               } 
/* 1165 */               hd = (hd + 1) % this.rangeX;
/*      */             } 
/*      */           } 
/*      */           
/* 1169 */           if (maxIndexR == bufferHead) {
/* 1170 */             r = bufferR[tail];
/* 1171 */             int hd = head;
/* 1172 */             for (int n = 1; n < count; n++) {
/* 1173 */               int r1 = bufferR[hd];
/* 1174 */               if (isBetter(r1, r, this.doDilation)) {
/* 1175 */                 r = r1;
/* 1176 */                 maxIndexR = hd;
/*      */               } 
/* 1178 */               hd = (hd + 1) % this.rangeX;
/*      */             } 
/*      */           } 
/*      */           
/* 1182 */           if (maxIndexG == bufferHead) {
/* 1183 */             g = bufferG[tail];
/* 1184 */             int hd = head;
/* 1185 */             for (int n = 1; n < count; n++) {
/* 1186 */               int g1 = bufferG[hd];
/* 1187 */               if (isBetter(g1, g, this.doDilation)) {
/* 1188 */                 g = g1;
/* 1189 */                 maxIndexG = hd;
/*      */               } 
/* 1191 */               hd = (hd + 1) % this.rangeX;
/*      */             } 
/*      */           } 
/*      */           
/* 1195 */           if (maxIndexB == bufferHead) {
/* 1196 */             b = bufferB[tail];
/* 1197 */             int hd = head;
/* 1198 */             for (int n = 1; n < count; n++) {
/* 1199 */               int b1 = bufferB[hd];
/* 1200 */               if (isBetter(b1, b, this.doDilation)) {
/* 1201 */                 b = b1;
/* 1202 */                 maxIndexB = hd;
/*      */               } 
/* 1204 */               hd = (hd + 1) % this.rangeX;
/*      */             } 
/*      */           } 
/* 1207 */           destPixels[dp++] = a << 24 | r | g | b;
/* 1208 */           bufferHead = (bufferHead + 1) % this.rangeX;
/*      */           
/* 1210 */           count--;
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
/* 1225 */     if (h <= 2 * this.radiusY) {
/* 1226 */       specialProcessColumn(src, dest);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1232 */       int[] bufferA = new int[this.rangeY];
/* 1233 */       int[] bufferR = new int[this.rangeY];
/* 1234 */       int[] bufferG = new int[this.rangeY];
/* 1235 */       int[] bufferB = new int[this.rangeY];
/*      */       
/* 1237 */       for (int j = 0; j < w; j++) {
/*      */ 
/*      */         
/* 1240 */         int dp = dstOff + j;
/* 1241 */         int cp = dstOff + j;
/* 1242 */         int bufferHead = 0;
/* 1243 */         int maxIndexA = 0;
/* 1244 */         int maxIndexR = 0;
/* 1245 */         int maxIndexG = 0;
/* 1246 */         int maxIndexB = 0;
/*      */ 
/*      */         
/* 1249 */         int pel = destPixels[cp];
/* 1250 */         cp += dstScanStride;
/* 1251 */         int a = pel >>> 24;
/* 1252 */         int r = pel & 0xFF0000;
/* 1253 */         int g = pel & 0xFF00;
/* 1254 */         int b = pel & 0xFF;
/* 1255 */         bufferA[0] = a;
/* 1256 */         bufferR[0] = r;
/* 1257 */         bufferG[0] = g;
/* 1258 */         bufferB[0] = b;
/*      */         
/* 1260 */         for (int k = 1; k <= this.radiusY; k++) {
/* 1261 */           int currentPixel = destPixels[cp];
/* 1262 */           cp += dstScanStride;
/* 1263 */           int a1 = currentPixel >>> 24;
/* 1264 */           int r1 = currentPixel & 0xFF0000;
/* 1265 */           int g1 = currentPixel & 0xFF00;
/* 1266 */           int b1 = currentPixel & 0xFF;
/* 1267 */           bufferA[k] = a1;
/* 1268 */           bufferR[k] = r1;
/* 1269 */           bufferG[k] = g1;
/* 1270 */           bufferB[k] = b1;
/*      */           
/* 1272 */           if (isBetter(a1, a, this.doDilation)) {
/* 1273 */             a = a1;
/* 1274 */             maxIndexA = k;
/*      */           } 
/* 1276 */           if (isBetter(r1, r, this.doDilation)) {
/* 1277 */             r = r1;
/* 1278 */             maxIndexR = k;
/*      */           } 
/* 1280 */           if (isBetter(g1, g, this.doDilation)) {
/* 1281 */             g = g1;
/* 1282 */             maxIndexG = k;
/*      */           } 
/* 1284 */           if (isBetter(b1, b, this.doDilation)) {
/* 1285 */             b = b1;
/* 1286 */             maxIndexB = k;
/*      */           } 
/*      */         } 
/* 1289 */         destPixels[dp] = a << 24 | r | g | b;
/*      */         
/* 1291 */         dp += dstScanStride;
/*      */         
/*      */         int i;
/* 1294 */         for (i = 1; i <= this.radiusY; i++) {
/* 1295 */           int maxI = i + this.radiusY;
/*      */           
/* 1297 */           int lastPixel = destPixels[cp];
/* 1298 */           cp += dstScanStride;
/*      */ 
/*      */           
/* 1301 */           a = bufferA[maxIndexA];
/* 1302 */           int a1 = lastPixel >>> 24;
/* 1303 */           bufferA[maxI] = a1;
/* 1304 */           if (isBetter(a1, a, this.doDilation)) {
/* 1305 */             a = a1;
/* 1306 */             maxIndexA = maxI;
/*      */           } 
/*      */ 
/*      */           
/* 1310 */           r = bufferR[maxIndexR];
/* 1311 */           int r1 = lastPixel & 0xFF0000;
/* 1312 */           bufferR[maxI] = r1;
/* 1313 */           if (isBetter(r1, r, this.doDilation)) {
/* 1314 */             r = r1;
/* 1315 */             maxIndexR = maxI;
/*      */           } 
/*      */ 
/*      */           
/* 1319 */           g = bufferG[maxIndexG];
/* 1320 */           int g1 = lastPixel & 0xFF00;
/* 1321 */           bufferG[maxI] = g1;
/* 1322 */           if (isBetter(g1, g, this.doDilation)) {
/* 1323 */             g = g1;
/* 1324 */             maxIndexG = maxI;
/*      */           } 
/*      */ 
/*      */           
/* 1328 */           b = bufferB[maxIndexB];
/* 1329 */           int b1 = lastPixel & 0xFF;
/* 1330 */           bufferB[maxI] = b1;
/* 1331 */           if (isBetter(b1, b, this.doDilation)) {
/* 1332 */             b = b1;
/* 1333 */             maxIndexB = maxI;
/*      */           } 
/* 1335 */           destPixels[dp] = a << 24 | r | g | b;
/* 1336 */           dp += dstScanStride;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1344 */         for (i = this.radiusY + 1; i <= h - 1 - this.radiusY; i++) {
/*      */           
/* 1346 */           int lastPixel = destPixels[cp];
/* 1347 */           cp += dstScanStride;
/* 1348 */           int a1 = lastPixel >>> 24;
/* 1349 */           int r1 = lastPixel & 0xFF0000;
/* 1350 */           int g1 = lastPixel & 0xFF00;
/* 1351 */           int b1 = lastPixel & 0xFF;
/* 1352 */           bufferA[bufferHead] = a1;
/* 1353 */           bufferR[bufferHead] = r1;
/* 1354 */           bufferG[bufferHead] = g1;
/* 1355 */           bufferB[bufferHead] = b1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1364 */           if (maxIndexA == bufferHead) {
/* 1365 */             a = bufferA[0];
/* 1366 */             maxIndexA = 0;
/* 1367 */             for (int n = 1; n <= 2 * this.radiusY; n++) {
/* 1368 */               a1 = bufferA[n];
/* 1369 */               if (isBetter(a1, a, this.doDilation)) {
/* 1370 */                 a = a1;
/* 1371 */                 maxIndexA = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1377 */             a = bufferA[maxIndexA];
/* 1378 */             if (isBetter(a1, a, this.doDilation)) {
/* 1379 */               a = a1;
/* 1380 */               maxIndexA = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1386 */           if (maxIndexR == bufferHead) {
/* 1387 */             r = bufferR[0];
/* 1388 */             maxIndexR = 0;
/* 1389 */             for (int n = 1; n <= 2 * this.radiusY; n++) {
/* 1390 */               r1 = bufferR[n];
/* 1391 */               if (isBetter(r1, r, this.doDilation)) {
/* 1392 */                 r = r1;
/* 1393 */                 maxIndexR = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1399 */             r = bufferR[maxIndexR];
/* 1400 */             if (isBetter(r1, r, this.doDilation)) {
/* 1401 */               r = r1;
/* 1402 */               maxIndexR = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1407 */           if (maxIndexG == bufferHead) {
/* 1408 */             g = bufferG[0];
/* 1409 */             maxIndexG = 0;
/* 1410 */             for (int n = 1; n <= 2 * this.radiusY; n++) {
/* 1411 */               g1 = bufferG[n];
/* 1412 */               if (isBetter(g1, g, this.doDilation)) {
/* 1413 */                 g = g1;
/* 1414 */                 maxIndexG = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1420 */             g = bufferG[maxIndexG];
/* 1421 */             if (isBetter(g1, g, this.doDilation)) {
/* 1422 */               g = g1;
/* 1423 */               maxIndexG = bufferHead;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1428 */           if (maxIndexB == bufferHead) {
/* 1429 */             b = bufferB[0];
/* 1430 */             maxIndexB = 0;
/* 1431 */             for (int n = 1; n <= 2 * this.radiusY; n++) {
/* 1432 */               b1 = bufferB[n];
/* 1433 */               if (isBetter(b1, b, this.doDilation)) {
/* 1434 */                 b = b1;
/* 1435 */                 maxIndexB = n;
/*      */               }
/*      */             
/*      */             } 
/*      */           } else {
/*      */             
/* 1441 */             b = bufferB[maxIndexB];
/* 1442 */             if (isBetter(b1, b, this.doDilation)) {
/* 1443 */               b = b1;
/* 1444 */               maxIndexB = bufferHead;
/*      */             } 
/*      */           } 
/* 1447 */           destPixels[dp] = a << 24 | r | g | b;
/* 1448 */           dp += dstScanStride;
/* 1449 */           bufferHead = (bufferHead + 1) % this.rangeY;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1460 */         int tail = (bufferHead == 0) ? (2 * this.radiusY) : (bufferHead - 1);
/* 1461 */         int count = this.rangeY - 1;
/*      */         
/* 1463 */         for (int m = h - this.radiusY; m < h - 1; m++) {
/* 1464 */           int head = (bufferHead + 1) % this.rangeY;
/*      */           
/* 1466 */           if (maxIndexA == bufferHead) {
/* 1467 */             a = bufferA[tail];
/* 1468 */             int hd = head;
/* 1469 */             for (int n = 1; n < count; n++) {
/* 1470 */               int a1 = bufferA[hd];
/* 1471 */               if (isBetter(a1, a, this.doDilation)) {
/* 1472 */                 a = a1;
/* 1473 */                 maxIndexA = hd;
/*      */               } 
/* 1475 */               hd = (hd + 1) % this.rangeY;
/*      */             } 
/*      */           } 
/* 1478 */           if (maxIndexR == bufferHead) {
/* 1479 */             r = bufferR[tail];
/* 1480 */             int hd = head;
/* 1481 */             for (int n = 1; n < count; n++) {
/* 1482 */               int r1 = bufferR[hd];
/* 1483 */               if (isBetter(r1, r, this.doDilation)) {
/* 1484 */                 r = r1;
/* 1485 */                 maxIndexR = hd;
/*      */               } 
/* 1487 */               hd = (hd + 1) % this.rangeY;
/*      */             } 
/*      */           } 
/* 1490 */           if (maxIndexG == bufferHead) {
/* 1491 */             g = bufferG[tail];
/* 1492 */             int hd = head;
/* 1493 */             for (int n = 1; n < count; n++) {
/* 1494 */               int g1 = bufferG[hd];
/* 1495 */               if (isBetter(g1, g, this.doDilation)) {
/* 1496 */                 g = g1;
/* 1497 */                 maxIndexG = hd;
/*      */               } 
/* 1499 */               hd = (hd + 1) % this.rangeY;
/*      */             } 
/*      */           } 
/* 1502 */           if (maxIndexB == bufferHead) {
/* 1503 */             b = bufferB[tail];
/* 1504 */             int hd = head;
/* 1505 */             for (int n = 1; n < count; n++) {
/* 1506 */               int b1 = bufferB[hd];
/* 1507 */               if (isBetter(b1, b, this.doDilation)) {
/* 1508 */                 b = b1;
/* 1509 */                 maxIndexB = hd;
/*      */               } 
/* 1511 */               hd = (hd + 1) % this.rangeY;
/*      */             } 
/*      */           } 
/* 1514 */           destPixels[dp] = a << 24 | r | g | b;
/* 1515 */           dp += dstScanStride;
/* 1516 */           bufferHead = (bufferHead + 1) % this.rangeY;
/*      */           
/* 1518 */           count--;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1524 */     return dest;
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
/*      */   public BufferedImage filter(BufferedImage src, BufferedImage dest) {
/* 1536 */     if (src == null) {
/* 1537 */       throw new NullPointerException("Source image should not be null");
/*      */     }
/* 1539 */     BufferedImage origSrc = src;
/* 1540 */     BufferedImage finalDest = dest;
/*      */     
/* 1542 */     if (!isCompatible(src.getColorModel(), src.getSampleModel())) {
/* 1543 */       src = new BufferedImage(src.getWidth(), src.getHeight(), 3);
/*      */       
/* 1545 */       GraphicsUtil.copyData(origSrc, src);
/*      */     }
/* 1547 */     else if (!src.isAlphaPremultiplied()) {
/*      */ 
/*      */       
/* 1550 */       ColorModel srcCM = src.getColorModel();
/* 1551 */       ColorModel srcCMPre = GraphicsUtil.coerceColorModel(srcCM, true);
/*      */       
/* 1553 */       src = new BufferedImage(srcCMPre, src.getRaster(), true, null);
/*      */ 
/*      */       
/* 1556 */       GraphicsUtil.copyData(origSrc, src);
/*      */     } 
/*      */ 
/*      */     
/* 1560 */     if (dest == null) {
/* 1561 */       dest = createCompatibleDestImage(src, null);
/* 1562 */       finalDest = dest;
/* 1563 */     } else if (!isCompatible(dest.getColorModel(), dest.getSampleModel())) {
/*      */       
/* 1565 */       dest = createCompatibleDestImage(src, null);
/* 1566 */     } else if (!dest.isAlphaPremultiplied()) {
/*      */ 
/*      */       
/* 1569 */       ColorModel dstCM = dest.getColorModel();
/* 1570 */       ColorModel dstCMPre = GraphicsUtil.coerceColorModel(dstCM, true);
/*      */       
/* 1572 */       dest = new BufferedImage(dstCMPre, finalDest.getRaster(), true, null);
/*      */     } 
/*      */ 
/*      */     
/* 1576 */     filter(src.getRaster(), dest.getRaster());
/*      */ 
/*      */     
/* 1579 */     if (src.getRaster() == origSrc.getRaster() && src.isAlphaPremultiplied() != origSrc.isAlphaPremultiplied())
/*      */     {
/*      */       
/* 1582 */       GraphicsUtil.copyData(src, origSrc);
/*      */     }
/*      */ 
/*      */     
/* 1586 */     if (dest.getRaster() != finalDest.getRaster() || dest.isAlphaPremultiplied() != finalDest.isAlphaPremultiplied())
/*      */     {
/*      */       
/* 1589 */       GraphicsUtil.copyData(dest, finalDest);
/*      */     }
/*      */     
/* 1592 */     return finalDest;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/MorphologyOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */