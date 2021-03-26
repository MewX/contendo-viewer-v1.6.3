/*      */ package org.apache.batik.ext.awt.image;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Composite;
/*      */ import java.awt.CompositeContext;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.PackedColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGComposite
/*      */   implements Composite
/*      */ {
/*   42 */   public static final SVGComposite OVER = new SVGComposite(CompositeRule.OVER);
/*      */ 
/*      */   
/*   45 */   public static final SVGComposite IN = new SVGComposite(CompositeRule.IN);
/*      */ 
/*      */   
/*   48 */   public static final SVGComposite OUT = new SVGComposite(CompositeRule.OUT);
/*      */ 
/*      */   
/*   51 */   public static final SVGComposite ATOP = new SVGComposite(CompositeRule.ATOP);
/*      */ 
/*      */   
/*   54 */   public static final SVGComposite XOR = new SVGComposite(CompositeRule.XOR);
/*      */ 
/*      */   
/*   57 */   public static final SVGComposite MULTIPLY = new SVGComposite(CompositeRule.MULTIPLY);
/*      */ 
/*      */   
/*   60 */   public static final SVGComposite SCREEN = new SVGComposite(CompositeRule.SCREEN);
/*      */ 
/*      */   
/*   63 */   public static final SVGComposite DARKEN = new SVGComposite(CompositeRule.DARKEN);
/*      */ 
/*      */   
/*   66 */   public static final SVGComposite LIGHTEN = new SVGComposite(CompositeRule.LIGHTEN);
/*      */   
/*      */   CompositeRule rule;
/*      */ 
/*      */   
/*      */   public CompositeRule getRule() {
/*   72 */     return this.rule;
/*      */   }
/*      */   public SVGComposite(CompositeRule rule) {
/*   75 */     this.rule = rule;
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/*   79 */     if (o instanceof SVGComposite) {
/*   80 */       SVGComposite svgc = (SVGComposite)o;
/*   81 */       return (svgc.getRule() == getRule());
/*   82 */     }  if (o instanceof AlphaComposite) {
/*   83 */       AlphaComposite ac = (AlphaComposite)o;
/*   84 */       switch (getRule().getRule()) {
/*      */         case 1:
/*   86 */           return (ac == AlphaComposite.SrcOver);
/*      */         case 2:
/*   88 */           return (ac == AlphaComposite.SrcIn);
/*      */         case 3:
/*   90 */           return (ac == AlphaComposite.SrcOut);
/*      */       } 
/*   92 */       return false;
/*      */     } 
/*      */     
/*   95 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean is_INT_PACK(ColorModel cm) {
/*  100 */     if (!(cm instanceof PackedColorModel)) return false;
/*      */     
/*  102 */     PackedColorModel pcm = (PackedColorModel)cm;
/*      */     
/*  104 */     int[] masks = pcm.getMasks();
/*      */ 
/*      */     
/*  107 */     if (masks.length != 4) return false;
/*      */     
/*  109 */     if (masks[0] != 16711680) return false; 
/*  110 */     if (masks[1] != 65280) return false; 
/*  111 */     if (masks[2] != 255) return false; 
/*  112 */     if (masks[3] != -16777216) return false;
/*      */     
/*  114 */     return true;
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
/*      */   public CompositeContext createContext(ColorModel srcCM, ColorModel dstCM, RenderingHints hints) {
/*      */     float[] coeff;
/*  133 */     boolean use_int_pack = (is_INT_PACK(srcCM) && is_INT_PACK(dstCM));
/*      */ 
/*      */     
/*  136 */     switch (this.rule.getRule()) {
/*      */       case 1:
/*  138 */         if (!dstCM.hasAlpha()) {
/*  139 */           if (use_int_pack) {
/*  140 */             return new OverCompositeContext_INT_PACK_NA(srcCM, dstCM);
/*      */           }
/*  142 */           return new OverCompositeContext_NA(srcCM, dstCM);
/*      */         } 
/*      */         
/*  145 */         if (!use_int_pack) {
/*  146 */           return new OverCompositeContext(srcCM, dstCM);
/*      */         }
/*  148 */         if (srcCM.isAlphaPremultiplied()) {
/*  149 */           return new OverCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  151 */         return new OverCompositeContext_INT_PACK_UNPRE(srcCM, dstCM);
/*      */       
/*      */       case 2:
/*  154 */         if (use_int_pack) {
/*  155 */           return new InCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  157 */         return new InCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 3:
/*  160 */         if (use_int_pack) {
/*  161 */           return new OutCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  163 */         return new OutCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 4:
/*  166 */         if (use_int_pack) {
/*  167 */           return new AtopCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  169 */         return new AtopCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 5:
/*  172 */         if (use_int_pack) {
/*  173 */           return new XorCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  175 */         return new XorCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 6:
/*  178 */         coeff = this.rule.getCoefficients();
/*  179 */         if (use_int_pack) {
/*  180 */           return new ArithCompositeContext_INT_PACK_LUT(srcCM, dstCM, coeff[0], coeff[1], coeff[2], coeff[3]);
/*      */         }
/*      */         
/*  183 */         return new ArithCompositeContext(srcCM, dstCM, coeff[0], coeff[1], coeff[2], coeff[3]);
/*      */ 
/*      */       
/*      */       case 7:
/*  187 */         if (use_int_pack) {
/*  188 */           return new MultiplyCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  190 */         return new MultiplyCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 8:
/*  193 */         if (use_int_pack) {
/*  194 */           return new ScreenCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  196 */         return new ScreenCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 9:
/*  199 */         if (use_int_pack) {
/*  200 */           return new DarkenCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  202 */         return new DarkenCompositeContext(srcCM, dstCM);
/*      */       
/*      */       case 10:
/*  205 */         if (use_int_pack) {
/*  206 */           return new LightenCompositeContext_INT_PACK(srcCM, dstCM);
/*      */         }
/*  208 */         return new LightenCompositeContext(srcCM, dstCM);
/*      */     } 
/*      */     
/*  211 */     throw new UnsupportedOperationException("Unknown composite rule requested.");
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class AlphaPreCompositeContext
/*      */     implements CompositeContext
/*      */   {
/*      */     ColorModel srcCM;
/*      */     ColorModel dstCM;
/*      */     
/*      */     AlphaPreCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  222 */       this.srcCM = srcCM;
/*  223 */       this.dstCM = dstCM;
/*      */     }
/*      */     
/*      */     public void dispose() {
/*  227 */       this.srcCM = null;
/*  228 */       this.dstCM = null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected abstract void precompose(Raster param1Raster1, Raster param1Raster2, WritableRaster param1WritableRaster);
/*      */     
/*      */     public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  235 */       ColorModel srcPreCM = this.srcCM;
/*  236 */       if (!this.srcCM.isAlphaPremultiplied()) {
/*  237 */         srcPreCM = GraphicsUtil.coerceData((WritableRaster)src, this.srcCM, true);
/*      */       }
/*      */       
/*  240 */       ColorModel dstPreCM = this.dstCM;
/*  241 */       if (!this.dstCM.isAlphaPremultiplied()) {
/*  242 */         dstPreCM = GraphicsUtil.coerceData((WritableRaster)dstIn, this.dstCM, true);
/*      */       }
/*      */       
/*  245 */       precompose(src, dstIn, dstOut);
/*      */       
/*  247 */       if (!this.srcCM.isAlphaPremultiplied()) {
/*  248 */         GraphicsUtil.coerceData((WritableRaster)src, srcPreCM, false);
/*      */       }
/*      */       
/*  251 */       if (!this.dstCM.isAlphaPremultiplied()) {
/*  252 */         GraphicsUtil.coerceData(dstOut, dstPreCM, false);
/*      */         
/*  254 */         if (dstIn != dstOut) {
/*  255 */           GraphicsUtil.coerceData((WritableRaster)dstIn, dstPreCM, false);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static abstract class AlphaPreCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     AlphaPreCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  265 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected abstract void precompose_INT_PACK(int param1Int1, int param1Int2, int[] param1ArrayOfint1, int param1Int3, int param1Int4, int[] param1ArrayOfint2, int param1Int5, int param1Int6, int[] param1ArrayOfint3, int param1Int7, int param1Int8);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  277 */       int x0 = dstOut.getMinX();
/*  278 */       int w = dstOut.getWidth();
/*      */       
/*  280 */       int y0 = dstOut.getMinY();
/*  281 */       int h = dstOut.getHeight();
/*      */ 
/*      */       
/*  284 */       SinglePixelPackedSampleModel srcSPPSM = (SinglePixelPackedSampleModel)src.getSampleModel();
/*      */       
/*  286 */       int srcScanStride = srcSPPSM.getScanlineStride();
/*  287 */       DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/*  288 */       int[] srcPixels = srcDB.getBankData()[0];
/*  289 */       int srcBase = srcDB.getOffset() + srcSPPSM.getOffset(x0 - src.getSampleModelTranslateX(), y0 - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  296 */       SinglePixelPackedSampleModel dstInSPPSM = (SinglePixelPackedSampleModel)dstIn.getSampleModel();
/*      */       
/*  298 */       int dstInScanStride = dstInSPPSM.getScanlineStride();
/*  299 */       DataBufferInt dstInDB = (DataBufferInt)dstIn.getDataBuffer();
/*  300 */       int[] dstInPixels = dstInDB.getBankData()[0];
/*  301 */       int dstInBase = dstInDB.getOffset() + dstInSPPSM.getOffset(x0 - dstIn.getSampleModelTranslateX(), y0 - dstIn.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  306 */       SinglePixelPackedSampleModel dstOutSPPSM = (SinglePixelPackedSampleModel)dstOut.getSampleModel();
/*      */ 
/*      */       
/*  309 */       int dstOutScanStride = dstOutSPPSM.getScanlineStride();
/*  310 */       DataBufferInt dstOutDB = (DataBufferInt)dstOut.getDataBuffer();
/*  311 */       int[] dstOutPixels = dstOutDB.getBankData()[0];
/*  312 */       int dstOutBase = dstOutDB.getOffset() + dstOutSPPSM.getOffset(x0 - dstOut.getSampleModelTranslateX(), y0 - dstOut.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  317 */       int srcAdjust = srcScanStride - w;
/*  318 */       int dstInAdjust = dstInScanStride - w;
/*  319 */       int dstOutAdjust = dstOutScanStride - w;
/*      */       
/*  321 */       precompose_INT_PACK(w, h, srcPixels, srcAdjust, srcBase, dstInPixels, dstInAdjust, dstInBase, dstOutPixels, dstOutAdjust, dstOutBase);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OverCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     OverCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  335 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  340 */       int[] srcPix = null;
/*  341 */       int[] dstPix = null;
/*      */       
/*  343 */       int x = dstOut.getMinX();
/*  344 */       int w = dstOut.getWidth();
/*      */       
/*  346 */       int y0 = dstOut.getMinY();
/*  347 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  349 */       int norm = 65793;
/*  350 */       int pt5 = 8388608;
/*      */       
/*  352 */       for (int y = y0; y < y1; y++) {
/*  353 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  354 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  355 */         int sp = 0;
/*  356 */         int end = w * 4;
/*  357 */         while (sp < end) {
/*  358 */           int dstM = (255 - srcPix[sp + 3]) * 65793;
/*  359 */           dstPix[sp] = srcPix[sp] + (dstPix[sp] * dstM + 8388608 >>> 24);
/*  360 */           sp++;
/*  361 */           dstPix[sp] = srcPix[sp] + (dstPix[sp] * dstM + 8388608 >>> 24);
/*  362 */           sp++;
/*  363 */           dstPix[sp] = srcPix[sp] + (dstPix[sp] * dstM + 8388608 >>> 24);
/*  364 */           sp++;
/*  365 */           dstPix[sp] = srcPix[sp] + (dstPix[sp] * dstM + 8388608 >>> 24);
/*  366 */           sp++;
/*      */         } 
/*  368 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OverCompositeContext_NA
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     OverCompositeContext_NA(ColorModel srcCM, ColorModel dstCM) {
/*  381 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  386 */       int[] srcPix = null;
/*  387 */       int[] dstPix = null;
/*      */       
/*  389 */       int x = dstOut.getMinX();
/*  390 */       int w = dstOut.getWidth();
/*      */       
/*  392 */       int y0 = dstOut.getMinY();
/*  393 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  395 */       int norm = 65793;
/*  396 */       int pt5 = 8388608;
/*      */       
/*  398 */       for (int y = y0; y < y1; y++) {
/*  399 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  400 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  401 */         int srcSP = 0;
/*  402 */         int dstSP = 0;
/*  403 */         int end = w * 4;
/*  404 */         while (srcSP < end) {
/*  405 */           int dstM = (255 - srcPix[srcSP + 3]) * 65793;
/*  406 */           dstPix[dstSP] = srcPix[srcSP] + (dstPix[dstSP] * dstM + 8388608 >>> 24);
/*      */           
/*  408 */           srcSP++; dstSP++;
/*  409 */           dstPix[dstSP] = srcPix[srcSP] + (dstPix[dstSP] * dstM + 8388608 >>> 24);
/*      */           
/*  411 */           srcSP++; dstSP++;
/*  412 */           dstPix[dstSP] = srcPix[srcSP] + (dstPix[dstSP] * dstM + 8388608 >>> 24);
/*      */           
/*  414 */           srcSP += 2; dstSP++;
/*      */         } 
/*  416 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OverCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     OverCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  428 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  437 */       int norm = 65793;
/*  438 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  442 */       for (int y = 0; y < height; y++) {
/*  443 */         int end = dstOutSp + width;
/*  444 */         while (dstOutSp < end) {
/*  445 */           int srcP = srcPixels[srcSp++];
/*  446 */           int dstInP = dstInPixels[dstInSp++];
/*      */           
/*  448 */           int dstM = (255 - (srcP >>> 24)) * 65793;
/*  449 */           dstOutPixels[dstOutSp++] = (srcP & 0xFF000000) + ((dstInP >>> 24) * dstM + 8388608 & 0xFF000000) | (srcP & 0xFF0000) + (((dstInP >> 16 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 8) | (srcP & 0xFF00) + (((dstInP >> 8 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 16) | (srcP & 0xFF) + ((dstInP & 0xFF) * dstM + 8388608 >>> 24);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  459 */         srcSp += srcAdjust;
/*  460 */         dstInSp += dstInAdjust;
/*  461 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OverCompositeContext_INT_PACK_NA
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     OverCompositeContext_INT_PACK_NA(ColorModel srcCM, ColorModel dstCM) {
/*  472 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  482 */       int norm = 65793;
/*  483 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  487 */       for (int y = 0; y < height; y++) {
/*  488 */         int end = dstOutSp + width;
/*  489 */         while (dstOutSp < end) {
/*  490 */           int srcP = srcPixels[srcSp++];
/*  491 */           int dstInP = dstInPixels[dstInSp++];
/*      */           
/*  493 */           int dstM = (255 - (srcP >>> 24)) * 65793;
/*  494 */           dstOutPixels[dstOutSp++] = (srcP & 0xFF0000) + (((dstInP >> 16 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 8) | (srcP & 0xFF00) + (((dstInP >> 8 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 16) | (srcP & 0xFF) + ((dstInP & 0xFF) * dstM + 8388608 >>> 24);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  502 */         srcSp += srcAdjust;
/*  503 */         dstInSp += dstInAdjust;
/*  504 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class OverCompositeContext_INT_PACK_UNPRE
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     OverCompositeContext_INT_PACK_UNPRE(ColorModel srcCM, ColorModel dstCM) {
/*  518 */       super(srcCM, dstCM);
/*      */       
/*  520 */       if (srcCM.isAlphaPremultiplied()) {
/*  521 */         throw new IllegalArgumentException("OverCompositeContext_INT_PACK_UNPRE is only forsources with unpremultiplied alpha");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  527 */       ColorModel dstPreCM = this.dstCM;
/*  528 */       if (!this.dstCM.isAlphaPremultiplied()) {
/*  529 */         dstPreCM = GraphicsUtil.coerceData((WritableRaster)dstIn, this.dstCM, true);
/*      */       }
/*      */       
/*  532 */       precompose(src, dstIn, dstOut);
/*      */       
/*  534 */       if (!this.dstCM.isAlphaPremultiplied()) {
/*  535 */         GraphicsUtil.coerceData(dstOut, dstPreCM, false);
/*      */         
/*  537 */         if (dstIn != dstOut) {
/*  538 */           GraphicsUtil.coerceData((WritableRaster)dstIn, dstPreCM, false);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  549 */       int norm = 65793;
/*  550 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  554 */       for (int y = 0; y < height; y++) {
/*  555 */         int end = dstOutSp + width;
/*  556 */         while (dstOutSp < end) {
/*  557 */           int srcP = srcPixels[srcSp++];
/*  558 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/*  560 */           int srcM = (srcP >>> 24) * 65793;
/*  561 */           int dstM = (255 - (srcP >>> 24)) * 65793;
/*      */           
/*  563 */           dstOutPixels[dstOutSp++] = (srcP & 0xFF000000) + (dstP >>> 24) * dstM + 8388608 & 0xFF000000 | ((srcP >> 16 & 0xFF) * srcM + (dstP >> 16 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 8 | ((srcP >> 8 & 0xFF) * srcM + (dstP >> 8 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 16 | (srcP & 0xFF) * srcM + (dstP & 0xFF) * dstM + 8388608 >>> 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  573 */         srcSp += srcAdjust;
/*  574 */         dstInSp += dstInAdjust;
/*  575 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InCompositeContext
/*      */     extends AlphaPreCompositeContext {
/*      */     InCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  583 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  588 */       int[] srcPix = null;
/*  589 */       int[] dstPix = null;
/*      */       
/*  591 */       int x = dstOut.getMinX();
/*  592 */       int w = dstOut.getWidth();
/*      */       
/*  594 */       int y0 = dstOut.getMinY();
/*  595 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  597 */       int norm = 65793;
/*  598 */       int pt5 = 8388608;
/*      */       
/*  600 */       for (int y = y0; y < y1; y++) {
/*  601 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  602 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  603 */         int sp = 0;
/*  604 */         int end = w * 4;
/*  605 */         while (sp < end) {
/*  606 */           int srcM = dstPix[sp + 3] * 65793;
/*  607 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  608 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  609 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  610 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*      */         } 
/*  612 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     InCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  621 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  630 */       int norm = 65793;
/*  631 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  635 */       for (int y = 0; y < height; y++) {
/*  636 */         int end = dstOutSp + width;
/*  637 */         while (dstOutSp < end) {
/*  638 */           int srcM = (dstInPixels[dstInSp++] >>> 24) * 65793;
/*  639 */           int srcP = srcPixels[srcSp++];
/*  640 */           dstOutPixels[dstOutSp++] = (srcP >>> 24) * srcM + 8388608 & 0xFF000000 | ((srcP >> 16 & 0xFF) * srcM + 8388608 & 0xFF000000) >>> 8 | ((srcP >> 8 & 0xFF) * srcM + 8388608 & 0xFF000000) >>> 16 | (srcP & 0xFF) * srcM + 8388608 >>> 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  646 */         srcSp += srcAdjust;
/*  647 */         dstInSp += dstInAdjust;
/*  648 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class OutCompositeContext
/*      */     extends AlphaPreCompositeContext {
/*      */     OutCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  656 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  661 */       int[] srcPix = null;
/*  662 */       int[] dstPix = null;
/*      */       
/*  664 */       int x = dstOut.getMinX();
/*  665 */       int w = dstOut.getWidth();
/*      */       
/*  667 */       int y0 = dstOut.getMinY();
/*  668 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  670 */       int norm = 65793;
/*  671 */       int pt5 = 8388608;
/*      */       
/*  673 */       for (int y = y0; y < y1; y++) {
/*  674 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  675 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  676 */         int sp = 0;
/*  677 */         int end = w * 4;
/*  678 */         while (sp < end) {
/*  679 */           int srcM = (255 - dstPix[sp + 3]) * 65793;
/*  680 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  681 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  682 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*  683 */           dstPix[sp] = srcPix[sp] * srcM + 8388608 >>> 24; sp++;
/*      */         } 
/*  685 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class OutCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     OutCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  694 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  703 */       int norm = 65793;
/*  704 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  708 */       for (int y = 0; y < height; y++) {
/*  709 */         int end = dstOutSp + width;
/*  710 */         while (dstOutSp < end) {
/*  711 */           int srcM = (255 - (dstInPixels[dstInSp++] >>> 24)) * 65793;
/*  712 */           int srcP = srcPixels[srcSp++];
/*  713 */           dstOutPixels[dstOutSp++] = (srcP >>> 24) * srcM + 8388608 & 0xFF000000 | ((srcP >> 16 & 0xFF) * srcM + 8388608 & 0xFF000000) >>> 8 | ((srcP >> 8 & 0xFF) * srcM + 8388608 & 0xFF000000) >>> 16 | (srcP & 0xFF) * srcM + 8388608 >>> 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  719 */         srcSp += srcAdjust;
/*  720 */         dstInSp += dstInAdjust;
/*  721 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class AtopCompositeContext
/*      */     extends AlphaPreCompositeContext {
/*      */     AtopCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  729 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  734 */       int[] srcPix = null;
/*  735 */       int[] dstPix = null;
/*      */       
/*  737 */       int x = dstOut.getMinX();
/*  738 */       int w = dstOut.getWidth();
/*      */       
/*  740 */       int y0 = dstOut.getMinY();
/*  741 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  743 */       int norm = 65793;
/*  744 */       int pt5 = 8388608;
/*      */       
/*  746 */       for (int y = y0; y < y1; y++) {
/*  747 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  748 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  749 */         int sp = 0;
/*  750 */         int end = w * 4;
/*  751 */         while (sp < end) {
/*  752 */           int srcM = dstPix[sp + 3] * 65793;
/*  753 */           int dstM = (255 - srcPix[sp + 3]) * 65793;
/*  754 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  755 */           sp++;
/*  756 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  757 */           sp++;
/*  758 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  759 */           sp += 2;
/*      */         } 
/*  761 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class AtopCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     AtopCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  770 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  779 */       int norm = 65793;
/*  780 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  784 */       for (int y = 0; y < height; y++) {
/*  785 */         int end = dstOutSp + width;
/*  786 */         while (dstOutSp < end) {
/*  787 */           int srcP = srcPixels[srcSp++];
/*  788 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/*  790 */           int srcM = (dstP >>> 24) * 65793;
/*  791 */           int dstM = (255 - (srcP >>> 24)) * 65793;
/*      */           
/*  793 */           dstOutPixels[dstOutSp++] = dstP & 0xFF000000 | ((srcP >> 16 & 0xFF) * srcM + (dstP >> 16 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 8 | ((srcP >> 8 & 0xFF) * srcM + (dstP >> 8 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 16 | (srcP & 0xFF) * srcM + (dstP & 0xFF) * dstM + 8388608 >>> 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  802 */         srcSp += srcAdjust;
/*  803 */         dstInSp += dstInAdjust;
/*  804 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class XorCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     XorCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/*  813 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  818 */       int[] srcPix = null;
/*  819 */       int[] dstPix = null;
/*      */       
/*  821 */       int x = dstOut.getMinX();
/*  822 */       int w = dstOut.getWidth();
/*      */       
/*  824 */       int y0 = dstOut.getMinY();
/*  825 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  827 */       int norm = 65793;
/*  828 */       int pt5 = 8388608;
/*      */       
/*  830 */       for (int y = y0; y < y1; y++) {
/*  831 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  832 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  833 */         int sp = 0;
/*  834 */         int end = w * 4;
/*  835 */         while (sp < end) {
/*  836 */           int srcM = (255 - dstPix[sp + 3]) * 65793;
/*  837 */           int dstM = (255 - srcPix[sp + 3]) * 65793;
/*      */           
/*  839 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  840 */           sp++;
/*  841 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  842 */           sp++;
/*  843 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  844 */           sp++;
/*  845 */           dstPix[sp] = srcPix[sp] * srcM + dstPix[sp] * dstM + 8388608 >>> 24;
/*  846 */           sp++;
/*      */         } 
/*  848 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class XorCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     XorCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/*  857 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  866 */       int norm = 65793;
/*  867 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/*  871 */       for (int y = 0; y < height; y++) {
/*  872 */         int end = dstOutSp + width;
/*  873 */         while (dstOutSp < end) {
/*  874 */           int srcP = srcPixels[srcSp++];
/*  875 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/*  877 */           int srcM = (255 - (dstP >>> 24)) * 65793;
/*  878 */           int dstM = (255 - (srcP >>> 24)) * 65793;
/*      */           
/*  880 */           dstOutPixels[dstOutSp++] = (srcP >>> 24) * srcM + (dstP >>> 24) * dstM + 8388608 & 0xFF000000 | ((srcP >> 16 & 0xFF) * srcM + (dstP >> 16 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 8 | ((srcP >> 8 & 0xFF) * srcM + (dstP >> 8 & 0xFF) * dstM + 8388608 & 0xFF000000) >>> 16 | (srcP & 0xFF) * srcM + (dstP & 0xFF) * dstM + 8388608 >>> 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  890 */         srcSp += srcAdjust;
/*  891 */         dstInSp += dstInAdjust;
/*  892 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     } }
/*      */   
/*      */   public static class ArithCompositeContext extends AlphaPreCompositeContext {
/*      */     float k1;
/*      */     float k2;
/*      */     float k3;
/*      */     float k4;
/*      */     
/*      */     ArithCompositeContext(ColorModel srcCM, ColorModel dstCM, float k1, float k2, float k3, float k4) {
/*  903 */       super(srcCM, dstCM);
/*  904 */       this.k1 = k1;
/*  905 */       this.k2 = k2;
/*  906 */       this.k3 = k3;
/*  907 */       this.k4 = k4;
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/*  912 */       int[] srcPix = null;
/*  913 */       int[] dstPix = null;
/*      */       
/*  915 */       int x = dstOut.getMinX();
/*  916 */       int w = dstOut.getWidth();
/*  917 */       int bands = dstOut.getNumBands();
/*      */       
/*  919 */       int y0 = dstOut.getMinY();
/*  920 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/*  922 */       float kk1 = this.k1 / 255.0F;
/*  923 */       float kk4 = this.k4 * 255.0F + 0.5F;
/*      */ 
/*      */       
/*  926 */       for (int y = y0; y < y1; y++) {
/*  927 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/*  928 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/*  929 */         for (int i = 0; i < srcPix.length; i++) {
/*  930 */           int max = 0;
/*  931 */           for (int b = 1; b < bands; b++, i++) {
/*  932 */             int j = (int)(kk1 * srcPix[i] * dstPix[i] + this.k2 * srcPix[i] + this.k3 * dstPix[i] + kk4);
/*      */             
/*  934 */             if ((j & 0xFFFFFF00) != 0)
/*  935 */               if ((j & Integer.MIN_VALUE) != 0) { j = 0; }
/*  936 */               else { j = 255; }
/*  937 */                 if (j > max) max = j; 
/*  938 */             dstPix[i] = j;
/*      */           } 
/*      */           
/*  941 */           int val = (int)(kk1 * srcPix[i] * dstPix[i] + this.k2 * srcPix[i] + this.k3 * dstPix[i] + kk4);
/*      */           
/*  943 */           if ((val & 0xFFFFFF00) != 0)
/*  944 */             if ((val & Integer.MIN_VALUE) != 0) { val = 0; }
/*  945 */             else { val = 255; }
/*  946 */               if (val > max) {
/*  947 */             dstPix[i] = val;
/*      */           } else {
/*  949 */             dstPix[i] = max;
/*      */           } 
/*  951 */         }  dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ArithCompositeContext_INT_PACK extends AlphaPreCompositeContext_INT_PACK {
/*      */     float k1;
/*      */     float k2;
/*      */     float k3;
/*      */     float k4;
/*      */     
/*      */     ArithCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM, float k1, float k2, float k3, float k4) {
/*  963 */       super(srcCM, dstCM);
/*  964 */       this.k1 = k1 / 255.0F;
/*  965 */       this.k2 = k2;
/*  966 */       this.k3 = k3;
/*  967 */       this.k4 = k4 * 255.0F + 0.5F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/*  978 */       for (int y = 0; y < height; y++) {
/*  979 */         int end = dstOutSp + width;
/*  980 */         while (dstOutSp < end) {
/*  981 */           int srcP = srcPixels[srcSp++];
/*  982 */           int dstP = dstInPixels[dstInSp++];
/*  983 */           int a = (int)(((srcP >>> 24) * (dstP >>> 24)) * this.k1 + (srcP >>> 24) * this.k2 + (dstP >>> 24) * this.k3 + this.k4);
/*      */           
/*  985 */           if ((a & 0xFFFFFF00) != 0)
/*  986 */             if ((a & Integer.MIN_VALUE) != 0) { a = 0; }
/*  987 */             else { a = 255; }
/*      */              
/*  989 */           int r = (int)(((srcP >> 16 & 0xFF) * (dstP >> 16 & 0xFF)) * this.k1 + (srcP >> 16 & 0xFF) * this.k2 + (dstP >> 16 & 0xFF) * this.k3 + this.k4);
/*      */ 
/*      */           
/*  992 */           if ((r & 0xFFFFFF00) != 0)
/*  993 */             if ((r & Integer.MIN_VALUE) != 0) { r = 0; }
/*  994 */             else { r = 255; }
/*  995 */               if (a < r) a = r;
/*      */           
/*  997 */           int g = (int)(((srcP >> 8 & 0xFF) * (dstP >> 8 & 0xFF)) * this.k1 + (srcP >> 8 & 0xFF) * this.k2 + (dstP >> 8 & 0xFF) * this.k3 + this.k4);
/*      */ 
/*      */           
/* 1000 */           if ((g & 0xFFFFFF00) != 0)
/* 1001 */             if ((g & Integer.MIN_VALUE) != 0) { g = 0; }
/* 1002 */             else { g = 255; }
/* 1003 */               if (a < g) a = g;
/*      */           
/* 1005 */           int b = (int)(((srcP & 0xFF) * (dstP & 0xFF)) * this.k1 + (srcP & 0xFF) * this.k2 + (dstP & 0xFF) * this.k3 + this.k4);
/*      */           
/* 1007 */           if ((b & 0xFFFFFF00) != 0)
/* 1008 */             if ((b & Integer.MIN_VALUE) != 0) { b = 0; }
/* 1009 */             else { b = 255; }
/* 1010 */               if (a < b) a = b;
/*      */           
/* 1012 */           dstOutPixels[dstOutSp++] = a << 24 | r << 16 | g << 8 | b;
/*      */         } 
/*      */         
/* 1015 */         srcSp += srcAdjust;
/* 1016 */         dstInSp += dstInAdjust;
/* 1017 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ArithCompositeContext_INT_PACK_LUT
/*      */     extends AlphaPreCompositeContext_INT_PACK
/*      */   {
/*      */     byte[] lut;
/*      */ 
/*      */     
/*      */     ArithCompositeContext_INT_PACK_LUT(ColorModel srcCM, ColorModel dstCM, float k1, float k2, float k3, float k4) {
/* 1031 */       super(srcCM, dstCM);
/* 1032 */       k1 /= 255.0F;
/* 1033 */       k4 = k4 * 255.0F + 0.5F;
/* 1034 */       int sz = 65536;
/* 1035 */       this.lut = new byte[sz];
/*      */       
/* 1037 */       for (int i = 0; i < sz; i++) {
/* 1038 */         int val = (int)(((i >> 8) * (i & 0xFF)) * k1 + (i >> 8) * k2 + (i & 0xFF) * k3 + k4);
/* 1039 */         if ((val & 0xFFFFFF00) != 0)
/* 1040 */           if ((val & Integer.MIN_VALUE) != 0) { val = 0; }
/* 1041 */           else { val = 255; }
/* 1042 */             this.lut[i] = (byte)val;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/* 1052 */       byte[] workTbl = this.lut;
/*      */       
/* 1054 */       for (int y = 0; y < height; y++) {
/* 1055 */         int end = dstOutSp + width;
/* 1056 */         while (dstOutSp < end) {
/* 1057 */           int srcP = srcPixels[srcSp++];
/* 1058 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/* 1060 */           int a = 0xFF & workTbl[srcP >> 16 & 0xFF00 | dstP >>> 24];
/* 1061 */           int r = 0xFF & workTbl[srcP >> 8 & 0xFF00 | dstP >> 16 & 0xFF];
/* 1062 */           int g = 0xFF & workTbl[srcP & 0xFF00 | dstP >> 8 & 0xFF];
/* 1063 */           int b = 0xFF & workTbl[srcP << 8 & 0xFF00 | dstP & 0xFF];
/* 1064 */           if (r > a) a = r; 
/* 1065 */           if (g > a) a = g; 
/* 1066 */           if (b > a) a = b; 
/* 1067 */           dstOutPixels[dstOutSp++] = a << 24 | r << 16 | g << 8 | b;
/*      */         } 
/* 1069 */         srcSp += srcAdjust;
/* 1070 */         dstInSp += dstInAdjust;
/* 1071 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class MultiplyCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     MultiplyCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/* 1085 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/* 1090 */       int[] srcPix = null;
/* 1091 */       int[] dstPix = null;
/*      */       
/* 1093 */       int x = dstOut.getMinX();
/* 1094 */       int w = dstOut.getWidth();
/*      */       
/* 1096 */       int y0 = dstOut.getMinY();
/* 1097 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/* 1099 */       int norm = 65793;
/* 1100 */       int pt5 = 8388608;
/*      */ 
/*      */       
/* 1103 */       for (int y = y0; y < y1; y++) {
/* 1104 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/* 1105 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/* 1106 */         int sp = 0;
/* 1107 */         int end = w * 4;
/* 1108 */         while (sp < end) {
/* 1109 */           int srcM = 255 - dstPix[sp + 3];
/* 1110 */           int dstM = 255 - srcPix[sp + 3];
/*      */           
/* 1112 */           dstPix[sp] = (srcPix[sp] * srcM + dstPix[sp] * dstM + srcPix[sp] * dstPix[sp]) * 65793 + 8388608 >>> 24;
/*      */           
/* 1114 */           sp++;
/*      */           
/* 1116 */           dstPix[sp] = (srcPix[sp] * srcM + dstPix[sp] * dstM + srcPix[sp] * dstPix[sp]) * 65793 + 8388608 >>> 24;
/*      */           
/* 1118 */           sp++;
/*      */           
/* 1120 */           dstPix[sp] = (srcPix[sp] * srcM + dstPix[sp] * dstM + srcPix[sp] * dstPix[sp]) * 65793 + 8388608 >>> 24;
/*      */           
/* 1122 */           sp++;
/*      */           
/* 1124 */           dstPix[sp] = srcPix[sp] + dstPix[sp] - (dstPix[sp] * srcPix[sp] * 65793 + 8388608 >>> 24);
/*      */           
/* 1126 */           sp++;
/*      */         } 
/* 1128 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class MultiplyCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK {
/*      */     MultiplyCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/* 1136 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/* 1145 */       int norm = 65793;
/* 1146 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1151 */       for (int y = 0; y < height; y++) {
/* 1152 */         int end = dstOutSp + width;
/* 1153 */         while (dstOutSp < end) {
/* 1154 */           int srcP = srcPixels[srcSp++];
/* 1155 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/* 1157 */           int srcA = srcP >>> 24;
/* 1158 */           int dstA = dstP >>> 24;
/* 1159 */           int srcR = srcP >> 16 & 0xFF;
/* 1160 */           int dstR = dstP >> 16 & 0xFF;
/* 1161 */           int srcG = srcP >> 8 & 0xFF;
/* 1162 */           int dstG = dstP >> 8 & 0xFF;
/* 1163 */           int srcB = srcP & 0xFF;
/* 1164 */           int dstB = dstP & 0xFF;
/*      */           
/* 1166 */           int srcM = 255 - dstA;
/* 1167 */           int dstM = 255 - srcA;
/*      */           
/* 1169 */           dstOutPixels[dstOutSp++] = ((srcR * srcM + dstR * dstM + srcR * dstR) * 65793 + 8388608 & 0xFF000000) >>> 8 | ((srcG * srcM + dstG * dstM + srcG * dstG) * 65793 + 8388608 & 0xFF000000) >>> 16 | (srcB * srcM + dstB * dstM + srcB * dstB) * 65793 + 8388608 >>> 24 | srcA + dstA - (srcA * dstA * 65793 + 8388608 >>> 24) << 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1178 */         srcSp += srcAdjust;
/* 1179 */         dstInSp += dstInAdjust;
/* 1180 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ScreenCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     ScreenCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/* 1189 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/* 1194 */       int[] srcPix = null;
/* 1195 */       int[] dstPix = null;
/*      */       
/* 1197 */       int x = dstOut.getMinX();
/* 1198 */       int w = dstOut.getWidth();
/*      */       
/* 1200 */       int y0 = dstOut.getMinY();
/* 1201 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/* 1203 */       int norm = 65793;
/* 1204 */       int pt5 = 8388608;
/*      */       
/* 1206 */       for (int y = y0; y < y1; y++) {
/* 1207 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/* 1208 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/* 1209 */         int sp = 0;
/* 1210 */         int end = w * 4;
/* 1211 */         while (sp < end) {
/*      */           
/* 1213 */           int iSrcPix = srcPix[sp];
/* 1214 */           int iDstPix = dstPix[sp];
/*      */           
/* 1216 */           dstPix[sp] = iSrcPix + iDstPix - (iDstPix * iSrcPix * 65793 + 8388608 >>> 24);
/*      */           
/* 1218 */           sp++;
/* 1219 */           iSrcPix = srcPix[sp];
/* 1220 */           iDstPix = dstPix[sp];
/* 1221 */           dstPix[sp] = iSrcPix + iDstPix - (iDstPix * iSrcPix * 65793 + 8388608 >>> 24);
/*      */           
/* 1223 */           sp++;
/* 1224 */           iSrcPix = srcPix[sp];
/* 1225 */           iDstPix = dstPix[sp];
/* 1226 */           dstPix[sp] = iSrcPix + iDstPix - (iDstPix * iSrcPix * 65793 + 8388608 >>> 24);
/*      */           
/* 1228 */           sp++;
/* 1229 */           iSrcPix = srcPix[sp];
/* 1230 */           iDstPix = dstPix[sp];
/* 1231 */           dstPix[sp] = iSrcPix + iDstPix - (iDstPix * iSrcPix * 65793 + 8388608 >>> 24);
/*      */           
/* 1233 */           sp++;
/*      */         } 
/* 1235 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ScreenCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK {
/*      */     ScreenCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/* 1243 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/* 1252 */       int norm = 65793;
/* 1253 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1258 */       for (int y = 0; y < height; y++) {
/* 1259 */         int end = dstOutSp + width;
/* 1260 */         while (dstOutSp < end) {
/* 1261 */           int srcP = srcPixels[srcSp++];
/* 1262 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/* 1264 */           int srcA = srcP >>> 24;
/* 1265 */           int dstA = dstP >>> 24;
/* 1266 */           int srcR = srcP >> 16 & 0xFF;
/* 1267 */           int dstR = dstP >> 16 & 0xFF;
/* 1268 */           int srcG = srcP >> 8 & 0xFF;
/* 1269 */           int dstG = dstP >> 8 & 0xFF;
/* 1270 */           int srcB = srcP & 0xFF;
/* 1271 */           int dstB = dstP & 0xFF;
/*      */           
/* 1273 */           dstOutPixels[dstOutSp++] = srcR + dstR - (srcR * dstR * 65793 + 8388608 >>> 24) << 16 | srcG + dstG - (srcG * dstG * 65793 + 8388608 >>> 24) << 8 | srcB + dstB - (srcB * dstB * 65793 + 8388608 >>> 24) | srcA + dstA - (srcA * dstA * 65793 + 8388608 >>> 24) << 24;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1279 */         srcSp += srcAdjust;
/* 1280 */         dstInSp += dstInAdjust;
/* 1281 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DarkenCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     DarkenCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/* 1290 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/* 1295 */       int[] srcPix = null;
/* 1296 */       int[] dstPix = null;
/*      */       
/* 1298 */       int x = dstOut.getMinX();
/* 1299 */       int w = dstOut.getWidth();
/*      */       
/* 1301 */       int y0 = dstOut.getMinY();
/* 1302 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/* 1304 */       int norm = 65793;
/* 1305 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/* 1309 */       for (int y = y0; y < y1; y++) {
/* 1310 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/* 1311 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/* 1312 */         int sp = 0;
/* 1313 */         int end = w * 4;
/* 1314 */         while (sp < end) {
/* 1315 */           int srcM = 255 - dstPix[sp + 3];
/* 1316 */           int dstM = 255 - srcPix[sp + 3];
/*      */           
/* 1318 */           int t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1319 */           int t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1320 */           if (t1 > t2) { dstPix[sp] = t2; }
/* 1321 */           else { dstPix[sp] = t1; }
/* 1322 */            sp++;
/*      */           
/* 1324 */           t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1325 */           t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1326 */           if (t1 > t2) { dstPix[sp] = t2; }
/* 1327 */           else { dstPix[sp] = t1; }
/* 1328 */            sp++;
/*      */           
/* 1330 */           t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1331 */           t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1332 */           if (t1 > t2) { dstPix[sp] = t2; }
/* 1333 */           else { dstPix[sp] = t1; }
/* 1334 */            sp++;
/*      */           
/* 1336 */           dstPix[sp] = srcPix[sp] + dstPix[sp] - (dstPix[sp] * srcPix[sp] * 65793 + 8388608 >>> 24);
/*      */           
/* 1338 */           sp++;
/*      */         } 
/* 1340 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DarkenCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK {
/*      */     DarkenCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/* 1348 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/* 1357 */       int norm = 65793;
/* 1358 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1365 */       for (int y = 0; y < height; y++) {
/* 1366 */         int end = dstOutSp + width;
/* 1367 */         while (dstOutSp < end) {
/* 1368 */           int srcP = srcPixels[srcSp++];
/* 1369 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/* 1371 */           int srcV = srcP >>> 24;
/* 1372 */           int dstV = dstP >>> 24;
/* 1373 */           int srcM = (255 - dstV) * 65793;
/* 1374 */           int dstM = (255 - srcV) * 65793;
/* 1375 */           int dstA = srcV + dstV - (srcV * dstV * 65793 + 8388608 >>> 24);
/*      */           
/* 1377 */           srcV = srcP >> 16 & 0xFF;
/* 1378 */           dstV = dstP >> 16 & 0xFF;
/* 1379 */           int dstR = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1380 */           int tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1381 */           if (dstR > tmp) dstR = tmp;
/*      */           
/* 1383 */           srcV = srcP >> 8 & 0xFF;
/* 1384 */           dstV = dstP >> 8 & 0xFF;
/* 1385 */           int dstG = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1386 */           tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1387 */           if (dstG > tmp) dstG = tmp;
/*      */ 
/*      */           
/* 1390 */           srcV = srcP & 0xFF;
/* 1391 */           dstV = dstP & 0xFF;
/* 1392 */           int dstB = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1393 */           tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1394 */           if (dstB > tmp) dstB = tmp;
/*      */           
/* 1396 */           dstA &= 0xFF;
/* 1397 */           dstR &= 0xFF;
/* 1398 */           dstG &= 0xFF;
/* 1399 */           dstB &= 0xFF;
/*      */           
/* 1401 */           dstOutPixels[dstOutSp++] = dstA << 24 | dstR << 16 | dstG << 8 | dstB;
/*      */         } 
/*      */         
/* 1404 */         srcSp += srcAdjust;
/* 1405 */         dstInSp += dstInAdjust;
/* 1406 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class LightenCompositeContext
/*      */     extends AlphaPreCompositeContext
/*      */   {
/*      */     LightenCompositeContext(ColorModel srcCM, ColorModel dstCM) {
/* 1415 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */     
/*      */     public void precompose(Raster src, Raster dstIn, WritableRaster dstOut) {
/* 1420 */       int[] srcPix = null;
/* 1421 */       int[] dstPix = null;
/*      */       
/* 1423 */       int x = dstOut.getMinX();
/* 1424 */       int w = dstOut.getWidth();
/*      */       
/* 1426 */       int y0 = dstOut.getMinY();
/* 1427 */       int y1 = y0 + dstOut.getHeight();
/*      */       
/* 1429 */       int norm = 65793;
/* 1430 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */       
/* 1434 */       for (int y = y0; y < y1; y++) {
/* 1435 */         srcPix = src.getPixels(x, y, w, 1, srcPix);
/* 1436 */         dstPix = dstIn.getPixels(x, y, w, 1, dstPix);
/* 1437 */         int sp = 0;
/* 1438 */         int end = w * 4;
/* 1439 */         while (sp < end) {
/* 1440 */           int srcM = 255 - dstPix[sp + 3];
/* 1441 */           int dstM = 255 - srcPix[sp + 3];
/*      */           
/* 1443 */           int t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1444 */           int t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1445 */           if (t1 > t2) { dstPix[sp] = t1; }
/* 1446 */           else { dstPix[sp] = t2; }
/* 1447 */            sp++;
/*      */           
/* 1449 */           t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1450 */           t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1451 */           if (t1 > t2) { dstPix[sp] = t1; }
/* 1452 */           else { dstPix[sp] = t2; }
/* 1453 */            sp++;
/*      */           
/* 1455 */           t1 = (srcM * srcPix[sp] * 65793 + 8388608 >>> 24) + dstPix[sp];
/* 1456 */           t2 = (dstM * dstPix[sp] * 65793 + 8388608 >>> 24) + srcPix[sp];
/* 1457 */           if (t1 > t2) { dstPix[sp] = t1; }
/* 1458 */           else { dstPix[sp] = t2; }
/* 1459 */            sp++;
/*      */           
/* 1461 */           dstPix[sp] = srcPix[sp] + dstPix[sp] - (dstPix[sp] * srcPix[sp] * 65793 + 8388608 >>> 24);
/*      */           
/* 1463 */           sp++;
/*      */         } 
/* 1465 */         dstOut.setPixels(x, y, w, 1, dstPix);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class LightenCompositeContext_INT_PACK
/*      */     extends AlphaPreCompositeContext_INT_PACK {
/*      */     LightenCompositeContext_INT_PACK(ColorModel srcCM, ColorModel dstCM) {
/* 1473 */       super(srcCM, dstCM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void precompose_INT_PACK(int width, int height, int[] srcPixels, int srcAdjust, int srcSp, int[] dstInPixels, int dstInAdjust, int dstInSp, int[] dstOutPixels, int dstOutAdjust, int dstOutSp) {
/* 1482 */       int norm = 65793;
/* 1483 */       int pt5 = 8388608;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1490 */       for (int y = 0; y < height; y++) {
/* 1491 */         int end = dstOutSp + width;
/* 1492 */         while (dstOutSp < end) {
/* 1493 */           int srcP = srcPixels[srcSp++];
/* 1494 */           int dstP = dstInPixels[dstInSp++];
/*      */           
/* 1496 */           int srcV = srcP >>> 24;
/* 1497 */           int dstV = dstP >>> 24;
/* 1498 */           int srcM = (255 - dstV) * 65793;
/* 1499 */           int dstM = (255 - srcV) * 65793;
/* 1500 */           int dstA = srcV + dstV - (srcV * dstV * 65793 + 8388608 >>> 24);
/*      */           
/* 1502 */           srcV = srcP >> 16 & 0xFF;
/* 1503 */           dstV = dstP >> 16 & 0xFF;
/* 1504 */           int dstR = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1505 */           int tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1506 */           if (dstR < tmp) dstR = tmp;
/*      */           
/* 1508 */           srcV = srcP >> 8 & 0xFF;
/* 1509 */           dstV = dstP >> 8 & 0xFF;
/* 1510 */           int dstG = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1511 */           tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1512 */           if (dstG < tmp) dstG = tmp;
/*      */ 
/*      */           
/* 1515 */           srcV = srcP & 0xFF;
/* 1516 */           dstV = dstP & 0xFF;
/* 1517 */           int dstB = (srcM * srcV + 8388608 >>> 24) + dstV;
/* 1518 */           tmp = (dstM * dstV + 8388608 >>> 24) + srcV;
/* 1519 */           if (dstB < tmp) dstB = tmp;
/*      */           
/* 1521 */           dstA &= 0xFF;
/* 1522 */           dstR &= 0xFF;
/* 1523 */           dstG &= 0xFF;
/* 1524 */           dstB &= 0xFF;
/*      */           
/* 1526 */           dstOutPixels[dstOutSp++] = dstA << 24 | dstR << 16 | dstG << 8 | dstB;
/*      */         } 
/*      */         
/* 1529 */         srcSp += srcAdjust;
/* 1530 */         dstInSp += dstInAdjust;
/* 1531 */         dstOutSp += dstOutAdjust;
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/SVGComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */