/*      */ package org.apache.batik.ext.awt;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.PaintContext;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.lang.ref.WeakReference;
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
/*      */ abstract class MultipleGradientPaintContext
/*      */   implements PaintContext
/*      */ {
/*      */   protected static final boolean DEBUG = false;
/*      */   protected ColorModel dataModel;
/*      */   protected ColorModel model;
/*   66 */   private static ColorModel lrgbmodel_NA = new DirectColorModel(ColorSpace.getInstance(1004), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   71 */   private static ColorModel srgbmodel_NA = new DirectColorModel(ColorSpace.getInstance(1000), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   77 */   private static ColorModel lrgbmodel_A = new DirectColorModel(ColorSpace.getInstance(1004), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   private static ColorModel srgbmodel_A = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */   
/*      */   protected static ColorModel cachedModel;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static WeakReference cached;
/*      */ 
/*      */ 
/*      */   
/*      */   protected WritableRaster saved;
/*      */ 
/*      */ 
/*      */   
/*      */   protected MultipleGradientPaint.CycleMethodEnum cycleMethod;
/*      */ 
/*      */ 
/*      */   
/*      */   protected MultipleGradientPaint.ColorSpaceEnum colorSpace;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float a00;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float a01;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float a10;
/*      */ 
/*      */   
/*      */   protected float a11;
/*      */ 
/*      */   
/*      */   protected float a02;
/*      */ 
/*      */   
/*      */   protected float a12;
/*      */ 
/*      */   
/*      */   protected boolean isSimpleLookup = true;
/*      */ 
/*      */   
/*      */   protected boolean hasDiscontinuity = false;
/*      */ 
/*      */   
/*      */   protected int fastGradientArraySize;
/*      */ 
/*      */   
/*      */   protected int[] gradient;
/*      */ 
/*      */   
/*      */   protected int[][] gradients;
/*      */ 
/*      */   
/*      */   protected int gradientAverage;
/*      */ 
/*      */   
/*      */   protected int gradientUnderflow;
/*      */ 
/*      */   
/*      */   protected int gradientOverflow;
/*      */ 
/*      */   
/*      */   protected int gradientsLength;
/*      */ 
/*      */   
/*      */   protected float[] normalizedIntervals;
/*      */ 
/*      */   
/*      */   protected float[] fractions;
/*      */ 
/*      */   
/*      */   private int transparencyTest;
/*      */ 
/*      */   
/*  162 */   private static final int[] SRGBtoLinearRGB = new int[256];
/*  163 */   private static final int[] LinearRGBtoSRGB = new int[256];
/*      */   protected static final int GRADIENT_SIZE = 256;
/*      */   
/*      */   static {
/*  167 */     for (int k = 0; k < 256; k++) {
/*  168 */       SRGBtoLinearRGB[k] = convertSRGBtoLinearRGB(k);
/*  169 */       LinearRGBtoSRGB[k] = convertLinearRGBtoSRGB(k);
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
/*      */   protected static final int GRADIENT_SIZE_INDEX = 255;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MAX_GRADIENT_ARRAY_SIZE = 5000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MultipleGradientPaintContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform t, RenderingHints hints, float[] fractions, Color[] colors, MultipleGradientPaint.CycleMethodEnum cycleMethod, MultipleGradientPaint.ColorSpaceEnum colorSpace) throws NoninvertibleTransformException {
/*  210 */     boolean fixFirst = false;
/*  211 */     boolean fixLast = false;
/*  212 */     int len = fractions.length;
/*      */ 
/*      */     
/*  215 */     if (fractions[0] != 0.0F) {
/*  216 */       fixFirst = true;
/*  217 */       len++;
/*      */     } 
/*      */ 
/*      */     
/*  221 */     if (fractions[fractions.length - 1] != 1.0F) {
/*  222 */       fixLast = true;
/*  223 */       len++;
/*      */     } 
/*      */     
/*  226 */     for (int i = 0; i < fractions.length - 1; i++) {
/*  227 */       if (fractions[i] == fractions[i + 1])
/*  228 */         len--; 
/*      */     } 
/*  230 */     this.fractions = new float[len];
/*  231 */     Color[] loColors = new Color[len - 1];
/*  232 */     Color[] hiColors = new Color[len - 1];
/*  233 */     this.normalizedIntervals = new float[len - 1];
/*      */     
/*  235 */     this.gradientUnderflow = colors[0].getRGB();
/*  236 */     this.gradientOverflow = colors[colors.length - 1].getRGB();
/*      */     
/*  238 */     int idx = 0;
/*  239 */     if (fixFirst) {
/*  240 */       this.fractions[0] = 0.0F;
/*  241 */       loColors[0] = colors[0];
/*  242 */       hiColors[0] = colors[0];
/*  243 */       this.normalizedIntervals[0] = fractions[0];
/*  244 */       idx++;
/*      */     } 
/*      */     
/*  247 */     for (int j = 0; j < fractions.length - 1; j++) {
/*  248 */       if (fractions[j] == fractions[j + 1]) {
/*      */         
/*  250 */         if (!colors[j].equals(colors[j + 1])) {
/*  251 */           this.hasDiscontinuity = true;
/*      */         }
/*      */       } else {
/*      */         
/*  255 */         this.fractions[idx] = fractions[j];
/*  256 */         loColors[idx] = colors[j];
/*  257 */         hiColors[idx] = colors[j + 1];
/*  258 */         this.normalizedIntervals[idx] = fractions[j + 1] - fractions[j];
/*  259 */         idx++;
/*      */       } 
/*      */     } 
/*  262 */     this.fractions[idx] = fractions[fractions.length - 1];
/*      */     
/*  264 */     if (fixLast) {
/*  265 */       hiColors[idx] = colors[colors.length - 1]; loColors[idx] = colors[colors.length - 1];
/*  266 */       this.normalizedIntervals[idx] = 1.0F - fractions[fractions.length - 1];
/*  267 */       idx++;
/*  268 */       this.fractions[idx] = 1.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  273 */     AffineTransform tInv = t.createInverse();
/*      */     
/*  275 */     double[] m = new double[6];
/*  276 */     tInv.getMatrix(m);
/*  277 */     this.a00 = (float)m[0];
/*  278 */     this.a10 = (float)m[1];
/*  279 */     this.a01 = (float)m[2];
/*  280 */     this.a11 = (float)m[3];
/*  281 */     this.a02 = (float)m[4];
/*  282 */     this.a12 = (float)m[5];
/*      */ 
/*      */     
/*  285 */     this.cycleMethod = cycleMethod;
/*  286 */     this.colorSpace = colorSpace;
/*      */ 
/*      */     
/*  289 */     if (cm.getColorSpace() == lrgbmodel_A.getColorSpace()) {
/*  290 */       this.dataModel = lrgbmodel_A;
/*  291 */     } else if (cm.getColorSpace() == srgbmodel_A.getColorSpace()) {
/*  292 */       this.dataModel = srgbmodel_A;
/*      */     } else {
/*  294 */       throw new IllegalArgumentException("Unsupported ColorSpace for interpolation");
/*      */     } 
/*      */     
/*  297 */     calculateGradientFractions(loColors, hiColors);
/*      */     
/*  299 */     this.model = GraphicsUtil.coerceColorModel(this.dataModel, cm.isAlphaPremultiplied());
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
/*      */   protected final void calculateGradientFractions(Color[] loColors, Color[] hiColors) {
/*  313 */     if (this.colorSpace == LinearGradientPaint.LINEAR_RGB) {
/*  314 */       int[] arrayOfInt = SRGBtoLinearRGB;
/*      */       
/*  316 */       for (int j = 0; j < loColors.length; j++) {
/*      */         
/*  318 */         loColors[j] = interpolateColor(arrayOfInt, loColors[j]);
/*      */         
/*  320 */         hiColors[j] = interpolateColor(arrayOfInt, hiColors[j]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  326 */     this.transparencyTest = -16777216;
/*  327 */     if (this.cycleMethod == MultipleGradientPaint.NO_CYCLE) {
/*      */ 
/*      */       
/*  330 */       this.transparencyTest &= this.gradientUnderflow;
/*  331 */       this.transparencyTest &= this.gradientOverflow;
/*      */     } 
/*      */ 
/*      */     
/*  335 */     this.gradients = new int[this.fractions.length - 1][];
/*  336 */     this.gradientsLength = this.gradients.length;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  343 */     int n = this.normalizedIntervals.length;
/*      */     
/*  345 */     float Imin = 1.0F;
/*  346 */     float[] workTbl = this.normalizedIntervals;
/*  347 */     for (int i = 0; i < n; i++)
/*      */     {
/*  349 */       Imin = (Imin > workTbl[i]) ? workTbl[i] : Imin;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     int estimatedSize = 0;
/*      */     
/*  359 */     if (Imin == 0.0F) {
/*  360 */       estimatedSize = Integer.MAX_VALUE;
/*  361 */       this.hasDiscontinuity = true;
/*      */     } else {
/*  363 */       for (float aWorkTbl : workTbl) {
/*  364 */         estimatedSize = (int)(estimatedSize + aWorkTbl / Imin * 256.0F);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  369 */     if (estimatedSize > 5000) {
/*      */       
/*  371 */       calculateMultipleArrayGradient(loColors, hiColors);
/*  372 */       if (this.cycleMethod == MultipleGradientPaint.REPEAT && this.gradients[0][0] != this.gradients[this.gradients.length - 1][255])
/*      */       {
/*      */         
/*  375 */         this.hasDiscontinuity = true;
/*      */       }
/*      */     } else {
/*  378 */       calculateSingleArrayGradient(loColors, hiColors, Imin);
/*  379 */       if (this.cycleMethod == MultipleGradientPaint.REPEAT && this.gradient[0] != this.gradient[this.fastGradientArraySize])
/*      */       {
/*  381 */         this.hasDiscontinuity = true;
/*      */       }
/*      */     } 
/*      */     
/*  385 */     if (this.transparencyTest >>> 24 == 255) {
/*  386 */       if (this.dataModel.getColorSpace() == lrgbmodel_NA.getColorSpace()) {
/*  387 */         this.dataModel = lrgbmodel_NA;
/*  388 */       } else if (this.dataModel.getColorSpace() == srgbmodel_NA.getColorSpace()) {
/*  389 */         this.dataModel = srgbmodel_NA;
/*  390 */       }  this.model = this.dataModel;
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
/*      */   private static Color interpolateColor(int[] workTbl, Color inColor) {
/*  404 */     int oldColor = inColor.getRGB();
/*      */     
/*  406 */     int newColorValue = (workTbl[oldColor >> 24 & 0xFF] & 0xFF) << 24 | (workTbl[oldColor >> 16 & 0xFF] & 0xFF) << 16 | (workTbl[oldColor >> 8 & 0xFF] & 0xFF) << 8 | workTbl[oldColor & 0xFF] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  412 */     return new Color(newColorValue, true);
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
/*      */   private void calculateSingleArrayGradient(Color[] loColors, Color[] hiColors, float Imin) {
/*  443 */     this.isSimpleLookup = true;
/*      */     
/*  445 */     int gradientsTot = 1;
/*      */ 
/*      */     
/*  448 */     int aveA = 32768;
/*  449 */     int aveR = 32768;
/*  450 */     int aveG = 32768;
/*  451 */     int aveB = 32768;
/*      */ 
/*      */     
/*  454 */     for (int i = 0; i < this.gradients.length; i++) {
/*      */ 
/*      */ 
/*      */       
/*  458 */       int nGradients = (int)(this.normalizedIntervals[i] / Imin * 255.0F);
/*  459 */       gradientsTot += nGradients;
/*  460 */       this.gradients[i] = new int[nGradients];
/*      */ 
/*      */       
/*  463 */       int rgb1 = loColors[i].getRGB();
/*  464 */       int rgb2 = hiColors[i].getRGB();
/*      */ 
/*      */       
/*  467 */       interpolate(rgb1, rgb2, this.gradients[i]);
/*      */ 
/*      */       
/*  470 */       int argb = this.gradients[i][128];
/*  471 */       float norm = this.normalizedIntervals[i];
/*  472 */       aveA += (int)((argb >> 8 & 0xFF0000) * norm);
/*  473 */       aveR += (int)((argb & 0xFF0000) * norm);
/*  474 */       aveG += (int)((argb << 8 & 0xFF0000) * norm);
/*  475 */       aveB += (int)((argb << 16 & 0xFF0000) * norm);
/*      */ 
/*      */       
/*  478 */       this.transparencyTest &= rgb1 & rgb2;
/*      */     } 
/*      */     
/*  481 */     this.gradientAverage = (aveA & 0xFF0000) << 8 | aveR & 0xFF0000 | (aveG & 0xFF0000) >> 8 | (aveB & 0xFF0000) >> 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  487 */     this.gradient = new int[gradientsTot];
/*  488 */     int curOffset = 0;
/*  489 */     for (int[] gradient1 : this.gradients) {
/*  490 */       System.arraycopy(gradient1, 0, this.gradient, curOffset, gradient1.length);
/*      */       
/*  492 */       curOffset += gradient1.length;
/*      */     } 
/*  494 */     this.gradient[this.gradient.length - 1] = hiColors[hiColors.length - 1].getRGB();
/*      */ 
/*      */ 
/*      */     
/*  498 */     if (this.colorSpace == LinearGradientPaint.LINEAR_RGB) {
/*  499 */       if (this.dataModel.getColorSpace() == ColorSpace.getInstance(1000))
/*      */       {
/*  501 */         for (int j = 0; j < this.gradient.length; j++) {
/*  502 */           this.gradient[j] = convertEntireColorLinearRGBtoSRGB(this.gradient[j]);
/*      */         }
/*      */         
/*  505 */         this.gradientAverage = convertEntireColorLinearRGBtoSRGB(this.gradientAverage);
/*      */       }
/*      */     
/*      */     }
/*  509 */     else if (this.dataModel.getColorSpace() == ColorSpace.getInstance(1004)) {
/*      */       
/*  511 */       for (int j = 0; j < this.gradient.length; j++) {
/*  512 */         this.gradient[j] = convertEntireColorSRGBtoLinearRGB(this.gradient[j]);
/*      */       }
/*      */       
/*  515 */       this.gradientAverage = convertEntireColorSRGBtoLinearRGB(this.gradientAverage);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  520 */     this.fastGradientArraySize = this.gradient.length - 1;
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
/*      */   private void calculateMultipleArrayGradient(Color[] loColors, Color[] hiColors) {
/*  547 */     this.isSimpleLookup = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  553 */     int aveA = 32768;
/*  554 */     int aveR = 32768;
/*  555 */     int aveG = 32768;
/*  556 */     int aveB = 32768;
/*      */ 
/*      */     
/*  559 */     for (int i = 0; i < this.gradients.length; i++) {
/*      */ 
/*      */       
/*  562 */       if (this.normalizedIntervals[i] != 0.0F) {
/*      */ 
/*      */ 
/*      */         
/*  566 */         this.gradients[i] = new int[256];
/*      */ 
/*      */         
/*  569 */         int rgb1 = loColors[i].getRGB();
/*  570 */         int rgb2 = hiColors[i].getRGB();
/*      */ 
/*      */         
/*  573 */         interpolate(rgb1, rgb2, this.gradients[i]);
/*      */ 
/*      */         
/*  576 */         int argb = this.gradients[i][128];
/*  577 */         float norm = this.normalizedIntervals[i];
/*  578 */         aveA += (int)((argb >> 8 & 0xFF0000) * norm);
/*  579 */         aveR += (int)((argb & 0xFF0000) * norm);
/*  580 */         aveG += (int)((argb << 8 & 0xFF0000) * norm);
/*  581 */         aveB += (int)((argb << 16 & 0xFF0000) * norm);
/*      */ 
/*      */         
/*  584 */         this.transparencyTest &= rgb1;
/*  585 */         this.transparencyTest &= rgb2;
/*      */       } 
/*      */     } 
/*  588 */     this.gradientAverage = (aveA & 0xFF0000) << 8 | aveR & 0xFF0000 | (aveG & 0xFF0000) >> 8 | (aveB & 0xFF0000) >> 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  595 */     if (this.colorSpace == LinearGradientPaint.LINEAR_RGB) {
/*  596 */       if (this.dataModel.getColorSpace() == ColorSpace.getInstance(1000))
/*      */       {
/*  598 */         for (int j = 0; j < this.gradients.length; j++) {
/*  599 */           for (int k = 0; k < (this.gradients[j]).length; k++) {
/*  600 */             this.gradients[j][k] = convertEntireColorLinearRGBtoSRGB(this.gradients[j][k]);
/*      */           }
/*      */         } 
/*      */         
/*  604 */         this.gradientAverage = convertEntireColorLinearRGBtoSRGB(this.gradientAverage);
/*      */       }
/*      */     
/*      */     }
/*  608 */     else if (this.dataModel.getColorSpace() == ColorSpace.getInstance(1004)) {
/*      */       
/*  610 */       for (int j = 0; j < this.gradients.length; j++) {
/*  611 */         for (int k = 0; k < (this.gradients[j]).length; k++) {
/*  612 */           this.gradients[j][k] = convertEntireColorSRGBtoLinearRGB(this.gradients[j][k]);
/*      */         }
/*      */       } 
/*      */       
/*  616 */       this.gradientAverage = convertEntireColorSRGBtoLinearRGB(this.gradientAverage);
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
/*      */   private void interpolate(int rgb1, int rgb2, int[] output) {
/*  631 */     int nSteps = output.length;
/*      */ 
/*      */     
/*  634 */     float stepSize = 1.0F / nSteps;
/*      */ 
/*      */     
/*  637 */     int a1 = rgb1 >> 24 & 0xFF;
/*  638 */     int r1 = rgb1 >> 16 & 0xFF;
/*  639 */     int g1 = rgb1 >> 8 & 0xFF;
/*  640 */     int b1 = rgb1 & 0xFF;
/*      */ 
/*      */     
/*  643 */     int da = (rgb2 >> 24 & 0xFF) - a1;
/*  644 */     int dr = (rgb2 >> 16 & 0xFF) - r1;
/*  645 */     int dg = (rgb2 >> 8 & 0xFF) - g1;
/*  646 */     int db = (rgb2 & 0xFF) - b1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  651 */     float tempA = 2.0F * da * stepSize;
/*  652 */     float tempR = 2.0F * dr * stepSize;
/*  653 */     float tempG = 2.0F * dg * stepSize;
/*  654 */     float tempB = 2.0F * db * stepSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  673 */     output[0] = rgb1;
/*  674 */     nSteps--;
/*  675 */     output[nSteps] = rgb2;
/*  676 */     for (int i = 1; i < nSteps; i++) {
/*  677 */       float fI = i;
/*  678 */       output[i] = (a1 + ((int)(fI * tempA) + 1 >> 1) & 0xFF) << 24 | (r1 + ((int)(fI * tempR) + 1 >> 1) & 0xFF) << 16 | (g1 + ((int)(fI * tempG) + 1 >> 1) & 0xFF) << 8 | b1 + ((int)(fI * tempB) + 1 >> 1) & 0xFF;
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
/*      */   private static int convertEntireColorLinearRGBtoSRGB(int rgb) {
/*  695 */     int a1 = rgb >> 24 & 0xFF;
/*  696 */     int r1 = rgb >> 16 & 0xFF;
/*  697 */     int g1 = rgb >> 8 & 0xFF;
/*  698 */     int b1 = rgb & 0xFF;
/*      */ 
/*      */     
/*  701 */     int[] workTbl = LinearRGBtoSRGB;
/*  702 */     r1 = workTbl[r1];
/*  703 */     g1 = workTbl[g1];
/*  704 */     b1 = workTbl[b1];
/*      */ 
/*      */     
/*  707 */     return a1 << 24 | r1 << 16 | g1 << 8 | b1;
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
/*      */   private static int convertEntireColorSRGBtoLinearRGB(int rgb) {
/*  720 */     int a1 = rgb >> 24 & 0xFF;
/*  721 */     int r1 = rgb >> 16 & 0xFF;
/*  722 */     int g1 = rgb >> 8 & 0xFF;
/*  723 */     int b1 = rgb & 0xFF;
/*      */ 
/*      */     
/*  726 */     int[] workTbl = SRGBtoLinearRGB;
/*  727 */     r1 = workTbl[r1];
/*  728 */     g1 = workTbl[g1];
/*  729 */     b1 = workTbl[b1];
/*      */ 
/*      */     
/*  732 */     return a1 << 24 | r1 << 16 | g1 << 8 | b1;
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
/*      */   protected final int indexIntoGradientsArrays(float position) {
/*  754 */     if (this.cycleMethod == MultipleGradientPaint.NO_CYCLE) {
/*      */       
/*  756 */       if (position >= 1.0F) {
/*  757 */         return this.gradientOverflow;
/*      */       }
/*      */       
/*  760 */       if (position <= 0.0F) {
/*  761 */         return this.gradientUnderflow;
/*      */       }
/*      */     } else {
/*      */       
/*  765 */       if (this.cycleMethod == MultipleGradientPaint.REPEAT) {
/*      */ 
/*      */         
/*  768 */         position -= (int)position;
/*      */ 
/*      */ 
/*      */         
/*  772 */         if (position < 0.0F) {
/*  773 */           position++;
/*      */         }
/*      */         
/*  776 */         int w = 0, c1 = 0, c2 = 0;
/*  777 */         if (this.isSimpleLookup) {
/*  778 */           position *= this.gradient.length;
/*  779 */           int idx1 = (int)position;
/*  780 */           if (idx1 + 1 < this.gradient.length) {
/*  781 */             return this.gradient[idx1];
/*      */           }
/*  783 */           w = (int)((position - idx1) * 65536.0F);
/*  784 */           c1 = this.gradient[idx1];
/*  785 */           c2 = this.gradient[0];
/*      */         } else {
/*      */           
/*  788 */           for (int j = 0; j < this.gradientsLength; j++) {
/*      */             
/*  790 */             if (position < this.fractions[j + 1]) {
/*      */               
/*  792 */               float delta = position - this.fractions[j];
/*      */               
/*  794 */               delta = delta / this.normalizedIntervals[j] * 256.0F;
/*      */               
/*  796 */               int index = (int)delta;
/*  797 */               if (index + 1 < (this.gradients[j]).length || j + 1 < this.gradientsLength)
/*      */               {
/*  799 */                 return this.gradients[j][index];
/*      */               }
/*  801 */               w = (int)((delta - index) * 65536.0F);
/*  802 */               c1 = this.gradients[j][index];
/*  803 */               c2 = this.gradients[0][0];
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*  809 */         return ((c1 >> 8 & 0xFF0000) + ((c2 >>> 24) - (c1 >>> 24)) * w & 0xFF0000) << 8 | (c1 & 0xFF0000) + ((c2 >> 16 & 0xFF) - (c1 >> 16 & 0xFF)) * w & 0xFF0000 | ((c1 << 8 & 0xFF0000) + ((c2 >> 8 & 0xFF) - (c1 >> 8 & 0xFF)) * w & 0xFF0000) >> 8 | ((c1 << 16 & 0xFF0000) + ((c2 & 0xFF) - (c1 & 0xFF)) * w & 0xFF0000) >> 16;
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
/*  831 */       if (position < 0.0F) {
/*  832 */         position = -position;
/*      */       }
/*      */       
/*  835 */       int part = (int)position;
/*      */       
/*  837 */       position -= part;
/*      */       
/*  839 */       if ((part & 0x1) == 1) {
/*  840 */         position = 1.0F - position;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  846 */     if (this.isSimpleLookup) {
/*  847 */       return this.gradient[(int)(position * this.fastGradientArraySize)];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  853 */     for (int i = 0; i < this.gradientsLength; i++) {
/*      */       
/*  855 */       if (position < this.fractions[i + 1]) {
/*      */         
/*  857 */         float delta = position - this.fractions[i];
/*      */ 
/*      */         
/*  860 */         int index = (int)(delta / this.normalizedIntervals[i] * 255.0F);
/*      */ 
/*      */         
/*  863 */         return this.gradients[i][index];
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  869 */     return this.gradientOverflow;
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
/*      */   protected final int indexGradientAntiAlias(float position, float sz) {
/*  887 */     if (this.cycleMethod == MultipleGradientPaint.NO_CYCLE) {
/*      */       int interior;
/*  889 */       float frac, p1 = position - sz / 2.0F;
/*  890 */       float f2 = position + sz / 2.0F;
/*      */       
/*  892 */       if (p1 >= 1.0F) {
/*  893 */         return this.gradientOverflow;
/*      */       }
/*  895 */       if (f2 <= 0.0F) {
/*  896 */         return this.gradientUnderflow;
/*      */       }
/*      */       
/*  899 */       float top_weight = 0.0F, bottom_weight = 0.0F;
/*  900 */       if (f2 >= 1.0F) {
/*  901 */         top_weight = (f2 - 1.0F) / sz;
/*  902 */         if (p1 <= 0.0F) {
/*  903 */           bottom_weight = -p1 / sz;
/*  904 */           frac = 1.0F;
/*  905 */           interior = this.gradientAverage;
/*      */         } else {
/*  907 */           frac = 1.0F - p1;
/*  908 */           interior = getAntiAlias(p1, true, 1.0F, false, 1.0F - p1, 1.0F);
/*      */         } 
/*  910 */       } else if (p1 <= 0.0F) {
/*  911 */         bottom_weight = -p1 / sz;
/*  912 */         frac = f2;
/*  913 */         interior = getAntiAlias(0.0F, true, f2, false, f2, 1.0F);
/*      */       } else {
/*  915 */         return getAntiAlias(p1, true, f2, false, sz, 1.0F);
/*      */       } 
/*  917 */       int norm = (int)(65536.0F * frac / sz);
/*  918 */       int pA = (interior >>> 20 & 0xFF0) * norm >> 16;
/*  919 */       int pR = (interior >> 12 & 0xFF0) * norm >> 16;
/*  920 */       int pG = (interior >> 4 & 0xFF0) * norm >> 16;
/*  921 */       int pB = (interior << 4 & 0xFF0) * norm >> 16;
/*      */       
/*  923 */       if (bottom_weight != 0.0F) {
/*  924 */         int bPix = this.gradientUnderflow;
/*      */         
/*  926 */         norm = (int)(65536.0F * bottom_weight);
/*  927 */         pA += (bPix >>> 20 & 0xFF0) * norm >> 16;
/*  928 */         pR += (bPix >> 12 & 0xFF0) * norm >> 16;
/*  929 */         pG += (bPix >> 4 & 0xFF0) * norm >> 16;
/*  930 */         pB += (bPix << 4 & 0xFF0) * norm >> 16;
/*      */       } 
/*      */       
/*  933 */       if (top_weight != 0.0F) {
/*  934 */         int tPix = this.gradientOverflow;
/*      */         
/*  936 */         norm = (int)(65536.0F * top_weight);
/*  937 */         pA += (tPix >>> 20 & 0xFF0) * norm >> 16;
/*  938 */         pR += (tPix >> 12 & 0xFF0) * norm >> 16;
/*  939 */         pG += (tPix >> 4 & 0xFF0) * norm >> 16;
/*  940 */         pB += (tPix << 4 & 0xFF0) * norm >> 16;
/*      */       } 
/*      */       
/*  943 */       return (pA & 0xFF0) << 20 | (pR & 0xFF0) << 12 | (pG & 0xFF0) << 4 | (pB & 0xFF0) >> 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  951 */     int intSz = (int)sz;
/*      */     
/*  953 */     float weight = 1.0F;
/*  954 */     if (intSz != 0) {
/*      */ 
/*      */ 
/*      */       
/*  958 */       sz -= intSz;
/*  959 */       weight = sz / (intSz + sz);
/*  960 */       if (weight < 0.1D)
/*      */       {
/*      */ 
/*      */         
/*  964 */         return this.gradientAverage;
/*      */       }
/*      */     } 
/*      */     
/*  968 */     if (sz > 0.99D) {
/*  969 */       return this.gradientAverage;
/*      */     }
/*      */     
/*  972 */     float f1 = position - sz / 2.0F;
/*  973 */     float p2 = position + sz / 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     boolean p1_up = true;
/*  979 */     boolean bool = false;
/*      */     
/*  981 */     if (this.cycleMethod == MultipleGradientPaint.REPEAT) {
/*      */ 
/*      */ 
/*      */       
/*  985 */       f1 = f1 - (int)f1;
/*  986 */       p2 -= (int)p2;
/*      */ 
/*      */       
/*  989 */       if (f1 < 0.0F) f1++; 
/*  990 */       if (p2 < 0.0F) p2++;
/*      */       
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  998 */       if (p2 < 0.0F) {
/*  999 */         f1 = -f1; p1_up = !p1_up;
/* 1000 */         p2 = -p2; bool = !bool ? true : false;
/* 1001 */       } else if (f1 < 0.0F) {
/* 1002 */         f1 = -f1; p1_up = !p1_up;
/*      */       } 
/*      */ 
/*      */       
/* 1006 */       int part1 = (int)f1;
/* 1007 */       f1 -= part1;
/*      */       
/* 1009 */       int part2 = (int)p2;
/* 1010 */       p2 -= part2;
/*      */ 
/*      */ 
/*      */       
/* 1014 */       if ((part1 & 0x1) == 1) {
/* 1015 */         f1 = 1.0F - f1;
/* 1016 */         p1_up = !p1_up;
/*      */       } 
/*      */       
/* 1019 */       if ((part2 & 0x1) == 1) {
/* 1020 */         p2 = 1.0F - p2;
/* 1021 */         bool = !bool ? true : false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1026 */       if (f1 > p2 && !p1_up && bool) {
/* 1027 */         float t = f1;
/* 1028 */         f1 = p2;
/* 1029 */         p2 = t;
/* 1030 */         p1_up = true;
/* 1031 */         bool = false;
/*      */       } 
/*      */     } 
/*      */     
/* 1035 */     return getAntiAlias(f1, p1_up, p2, bool, sz, weight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int getAntiAlias(float p1, boolean p1_up, float p2, boolean p2_up, float sz, float weight) {
/* 1044 */     int ach = 0, rch = 0, gch = 0, bch = 0;
/* 1045 */     if (this.isSimpleLookup) {
/* 1046 */       int norm; p1 *= this.fastGradientArraySize;
/* 1047 */       p2 *= this.fastGradientArraySize;
/*      */       
/* 1049 */       int idx1 = (int)p1;
/* 1050 */       int idx2 = (int)p2;
/*      */ 
/*      */ 
/*      */       
/* 1054 */       if (p1_up && !p2_up && idx1 <= idx2) {
/*      */         
/* 1056 */         if (idx1 == idx2) {
/* 1057 */           return this.gradient[idx1];
/*      */         }
/*      */         
/* 1060 */         for (int i = idx1 + 1; i < idx2; i++) {
/* 1061 */           int j = this.gradient[i];
/* 1062 */           ach += j >>> 20 & 0xFF0;
/* 1063 */           rch += j >>> 12 & 0xFF0;
/* 1064 */           gch += j >>> 4 & 0xFF0;
/* 1065 */           bch += j << 4 & 0xFF0;
/*      */         } 
/*      */       } else {
/*      */         int iStart;
/*      */         
/*      */         int iEnd;
/*      */         
/* 1072 */         if (p1_up) {
/* 1073 */           iStart = idx1 + 1;
/* 1074 */           iEnd = this.fastGradientArraySize;
/*      */         } else {
/* 1076 */           iStart = 0;
/* 1077 */           iEnd = idx1;
/*      */         }  int i;
/* 1079 */         for (i = iStart; i < iEnd; i++) {
/* 1080 */           int j = this.gradient[i];
/* 1081 */           ach += j >>> 20 & 0xFF0;
/* 1082 */           rch += j >>> 12 & 0xFF0;
/* 1083 */           gch += j >>> 4 & 0xFF0;
/* 1084 */           bch += j << 4 & 0xFF0;
/*      */         } 
/*      */         
/* 1087 */         if (p2_up) {
/* 1088 */           iStart = idx2 + 1;
/* 1089 */           iEnd = this.fastGradientArraySize;
/*      */         } else {
/* 1091 */           iStart = 0;
/* 1092 */           iEnd = idx2;
/*      */         } 
/* 1094 */         for (i = iStart; i < iEnd; i++) {
/* 1095 */           int j = this.gradient[i];
/* 1096 */           ach += j >>> 20 & 0xFF0;
/* 1097 */           rch += j >>> 12 & 0xFF0;
/* 1098 */           gch += j >>> 4 & 0xFF0;
/* 1099 */           bch += j << 4 & 0xFF0;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1106 */       int isz = (int)(65536.0F / sz * this.fastGradientArraySize);
/* 1107 */       ach = ach * isz >> 16;
/* 1108 */       rch = rch * isz >> 16;
/* 1109 */       gch = gch * isz >> 16;
/* 1110 */       bch = bch * isz >> 16;
/*      */ 
/*      */       
/* 1113 */       if (p1_up) { norm = (int)((1.0F - p1 - idx1) * isz); }
/* 1114 */       else { norm = (int)((p1 - idx1) * isz); }
/* 1115 */        int pix = this.gradient[idx1];
/* 1116 */       ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1117 */       rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1118 */       gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1119 */       bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */       
/* 1121 */       if (p2_up) { norm = (int)((1.0F - p2 - idx2) * isz); }
/* 1122 */       else { norm = (int)((p2 - idx2) * isz); }
/* 1123 */        pix = this.gradient[idx2];
/* 1124 */       ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1125 */       rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1126 */       gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1127 */       bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */ 
/*      */       
/* 1130 */       ach = ach + 8 >> 4;
/* 1131 */       rch = rch + 8 >> 4;
/* 1132 */       gch = gch + 8 >> 4;
/* 1133 */       bch = bch + 8 >> 4;
/*      */     } else {
/*      */       
/* 1136 */       int idx1 = 0, idx2 = 0;
/* 1137 */       int i1 = -1, i2 = -1;
/* 1138 */       float f1 = 0.0F, f2 = 0.0F;
/*      */       
/* 1140 */       for (int i = 0; i < this.gradientsLength; i++) {
/* 1141 */         if (p1 < this.fractions[i + 1] && i1 == -1) {
/*      */           
/* 1143 */           i1 = i;
/* 1144 */           f1 = p1 - this.fractions[i];
/*      */           
/* 1146 */           f1 = f1 / this.normalizedIntervals[i] * 255.0F;
/*      */ 
/*      */           
/* 1149 */           idx1 = (int)f1;
/* 1150 */           if (i2 != -1)
/*      */             break; 
/* 1152 */         }  if (p2 < this.fractions[i + 1] && i2 == -1) {
/*      */           
/* 1154 */           i2 = i;
/* 1155 */           f2 = p2 - this.fractions[i];
/*      */           
/* 1157 */           f2 = f2 / this.normalizedIntervals[i] * 255.0F;
/*      */ 
/*      */           
/* 1160 */           idx2 = (int)f2;
/* 1161 */           if (i1 != -1)
/*      */             break; 
/*      */         } 
/*      */       } 
/* 1165 */       if (i1 == -1) {
/* 1166 */         i1 = this.gradients.length - 1;
/* 1167 */         f1 = (idx1 = 255);
/*      */       } 
/*      */       
/* 1170 */       if (i2 == -1) {
/* 1171 */         i2 = this.gradients.length - 1;
/* 1172 */         f2 = (idx2 = 255);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1180 */       if (i1 == i2 && idx1 <= idx2 && p1_up && !p2_up) {
/* 1181 */         return this.gradients[i1][idx1 + idx2 + 1 >> 1];
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1186 */       int base = (int)(65536.0F / sz);
/* 1187 */       if (i1 < i2 && p1_up && !p2_up) {
/* 1188 */         int norm = (int)(base * this.normalizedIntervals[i1] * (255.0F - f1) / 255.0F);
/*      */ 
/*      */ 
/*      */         
/* 1192 */         int pix = this.gradients[i1][idx1 + 256 >> 1];
/* 1193 */         ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1194 */         rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1195 */         gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1196 */         bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */         
/* 1198 */         for (int j = i1 + 1; j < i2; j++) {
/* 1199 */           norm = (int)(base * this.normalizedIntervals[j]);
/* 1200 */           pix = this.gradients[j][128];
/*      */           
/* 1202 */           ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1203 */           rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1204 */           gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1205 */           bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */         } 
/*      */         
/* 1208 */         norm = (int)(base * this.normalizedIntervals[i2] * f2 / 255.0F);
/*      */         
/* 1210 */         pix = this.gradients[i2][idx2 + 1 >> 1];
/* 1211 */         ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1212 */         rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1213 */         gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1214 */         bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */       } else {
/* 1216 */         int pix, norm, iStart, iEnd; if (p1_up) {
/* 1217 */           norm = (int)(base * this.normalizedIntervals[i1] * (255.0F - f1) / 255.0F);
/*      */ 
/*      */ 
/*      */           
/* 1221 */           pix = this.gradients[i1][idx1 + 256 >> 1];
/*      */         } else {
/* 1223 */           norm = (int)(base * this.normalizedIntervals[i1] * f1 / 255.0F);
/*      */           
/* 1225 */           pix = this.gradients[i1][idx1 + 1 >> 1];
/*      */         } 
/* 1227 */         ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1228 */         rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1229 */         gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1230 */         bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */         
/* 1232 */         if (p2_up) {
/* 1233 */           norm = (int)(base * this.normalizedIntervals[i2] * (255.0F - f2) / 255.0F);
/*      */ 
/*      */ 
/*      */           
/* 1237 */           pix = this.gradients[i2][idx2 + 256 >> 1];
/*      */         } else {
/* 1239 */           norm = (int)(base * this.normalizedIntervals[i2] * f2 / 255.0F);
/*      */           
/* 1241 */           pix = this.gradients[i2][idx2 + 1 >> 1];
/*      */         } 
/* 1243 */         ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1244 */         rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1245 */         gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1246 */         bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1253 */         if (p1_up) {
/* 1254 */           iStart = i1 + 1;
/* 1255 */           iEnd = this.gradientsLength;
/*      */         } else {
/* 1257 */           iStart = 0;
/* 1258 */           iEnd = i1;
/*      */         }  int j;
/* 1260 */         for (j = iStart; j < iEnd; j++) {
/* 1261 */           norm = (int)(base * this.normalizedIntervals[j]);
/* 1262 */           pix = this.gradients[j][128];
/*      */           
/* 1264 */           ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1265 */           rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1266 */           gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1267 */           bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */         } 
/*      */         
/* 1270 */         if (p2_up) {
/* 1271 */           iStart = i2 + 1;
/* 1272 */           iEnd = this.gradientsLength;
/*      */         } else {
/* 1274 */           iStart = 0;
/* 1275 */           iEnd = i2;
/*      */         } 
/* 1277 */         for (j = iStart; j < iEnd; j++) {
/* 1278 */           norm = (int)(base * this.normalizedIntervals[j]);
/* 1279 */           pix = this.gradients[j][128];
/*      */           
/* 1281 */           ach += (pix >>> 20 & 0xFF0) * norm >> 16;
/* 1282 */           rch += (pix >>> 12 & 0xFF0) * norm >> 16;
/* 1283 */           gch += (pix >>> 4 & 0xFF0) * norm >> 16;
/* 1284 */           bch += (pix << 4 & 0xFF0) * norm >> 16;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1289 */       ach = ach + 8 >> 4;
/* 1290 */       rch = rch + 8 >> 4;
/* 1291 */       gch = gch + 8 >> 4;
/* 1292 */       bch = bch + 8 >> 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1297 */     if (weight != 1.0F) {
/*      */       
/* 1299 */       int aveW = (int)(65536.0F * (1.0F - weight));
/* 1300 */       int aveA = (this.gradientAverage >>> 24 & 0xFF) * aveW;
/* 1301 */       int aveR = (this.gradientAverage >> 16 & 0xFF) * aveW;
/* 1302 */       int aveG = (this.gradientAverage >> 8 & 0xFF) * aveW;
/* 1303 */       int aveB = (this.gradientAverage & 0xFF) * aveW;
/*      */       
/* 1305 */       int iw = (int)(weight * 65536.0F);
/* 1306 */       ach = ach * iw + aveA >> 16;
/* 1307 */       rch = rch * iw + aveR >> 16;
/* 1308 */       gch = gch * iw + aveG >> 16;
/* 1309 */       bch = bch * iw + aveB >> 16;
/*      */     } 
/*      */     
/* 1312 */     return ach << 24 | rch << 16 | gch << 8 | bch;
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
/*      */   private static int convertSRGBtoLinearRGB(int color) {
/* 1325 */     float output, input = color / 255.0F;
/* 1326 */     if (input <= 0.04045F) {
/* 1327 */       output = input / 12.92F;
/*      */     } else {
/* 1329 */       output = (float)Math.pow((input + 0.055D) / 1.055D, 2.4D);
/*      */     } 
/* 1331 */     int o = Math.round(output * 255.0F);
/*      */     
/* 1333 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int convertLinearRGBtoSRGB(int color) {
/* 1344 */     float output, input = color / 255.0F;
/*      */     
/* 1346 */     if (input <= 0.0031308F) {
/* 1347 */       output = input * 12.92F;
/*      */     } else {
/* 1349 */       output = 1.055F * (float)Math.pow(input, 0.4166666666666667D) - 0.055F;
/*      */     } 
/*      */     
/* 1352 */     int o = Math.round(output * 255.0F);
/*      */     
/* 1354 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Raster getRaster(int x, int y, int w, int h) {
/* 1360 */     if (w == 0 || h == 0) {
/* 1361 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1368 */     WritableRaster raster = this.saved;
/* 1369 */     if (raster == null || raster.getWidth() < w || raster.getHeight() < h) {
/*      */       
/* 1371 */       raster = getCachedRaster(this.dataModel, w, h);
/* 1372 */       this.saved = raster;
/*      */ 
/*      */ 
/*      */       
/* 1376 */       raster = raster.createWritableChild(raster.getMinX(), raster.getMinY(), w, h, 0, 0, (int[])null);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1386 */     DataBufferInt rasterDB = (DataBufferInt)raster.getDataBuffer();
/* 1387 */     int[] pixels = rasterDB.getBankData()[0];
/* 1388 */     int off = rasterDB.getOffset();
/* 1389 */     int scanlineStride = ((SinglePixelPackedSampleModel)raster.getSampleModel()).getScanlineStride();
/*      */     
/* 1391 */     int adjust = scanlineStride - w;
/*      */     
/* 1393 */     fillRaster(pixels, off, adjust, x, y, w, h);
/*      */     
/* 1395 */     GraphicsUtil.coerceData(raster, this.dataModel, this.model.isAlphaPremultiplied());
/*      */ 
/*      */ 
/*      */     
/* 1399 */     return raster;
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
/*      */   protected static final synchronized WritableRaster getCachedRaster(ColorModel cm, int w, int h) {
/* 1414 */     if (cm == cachedModel && 
/* 1415 */       cached != null) {
/* 1416 */       WritableRaster ras = cached.get();
/* 1417 */       if (ras != null && ras.getWidth() >= w && ras.getHeight() >= h) {
/*      */ 
/*      */ 
/*      */         
/* 1421 */         cached = null;
/* 1422 */         return ras;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1427 */     if (w < 32) w = 32; 
/* 1428 */     if (h < 32) h = 32; 
/* 1429 */     return cm.createCompatibleWritableRaster(w, h);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final synchronized void putCachedRaster(ColorModel cm, WritableRaster ras) {
/* 1439 */     if (cached != null) {
/* 1440 */       WritableRaster cras = cached.get();
/* 1441 */       if (cras != null) {
/* 1442 */         int cw = cras.getWidth();
/* 1443 */         int ch = cras.getHeight();
/* 1444 */         int iw = ras.getWidth();
/* 1445 */         int ih = ras.getHeight();
/* 1446 */         if (cw >= iw && ch >= ih) {
/*      */           return;
/*      */         }
/* 1449 */         if (cw * ch >= iw * ih) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */     } 
/* 1454 */     cachedModel = cm;
/* 1455 */     cached = new WeakReference<WritableRaster>(ras);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void dispose() {
/* 1462 */     if (this.saved != null) {
/* 1463 */       putCachedRaster(this.model, this.saved);
/* 1464 */       this.saved = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final ColorModel getColorModel() {
/* 1472 */     return this.model;
/*      */   }
/*      */   
/*      */   protected abstract void fillRaster(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/MultipleGradientPaintContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */