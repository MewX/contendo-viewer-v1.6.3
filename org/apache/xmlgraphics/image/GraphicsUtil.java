/*      */ package org.apache.xmlgraphics.image;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import org.apache.xmlgraphics.image.rendered.Any2LsRGBRed;
/*      */ import org.apache.xmlgraphics.image.rendered.Any2sRGBRed;
/*      */ import org.apache.xmlgraphics.image.rendered.BufferedImageCachableRed;
/*      */ import org.apache.xmlgraphics.image.rendered.CachableRed;
/*      */ import org.apache.xmlgraphics.image.rendered.RenderedImageCachableRed;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class GraphicsUtil
/*      */ {
/*   72 */   public static final AffineTransform IDENTITY = new AffineTransform();
/*      */ 
/*      */ 
/*      */   
/*   76 */   public static final ColorModel Linear_sRGB = new DirectColorModel(ColorSpace.getInstance(1004), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   85 */   public static final ColorModel Linear_sRGB_Pre = new DirectColorModel(ColorSpace.getInstance(1004), 32, 16711680, 65280, 255, -16777216, true, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   94 */   public static final ColorModel Linear_sRGB_Unpre = new DirectColorModel(ColorSpace.getInstance(1004), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  104 */   public static final ColorModel sRGB = new DirectColorModel(ColorSpace.getInstance(1000), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   public static final ColorModel sRGB_Pre = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, true, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   public static final ColorModel sRGB_Unpre = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel makeLinear_sRGBCM(boolean premult) {
/*  138 */     return premult ? Linear_sRGB_Pre : Linear_sRGB_Unpre;
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
/*      */   public static BufferedImage makeLinearBufferedImage(int width, int height, boolean premult) {
/*  151 */     ColorModel cm = makeLinear_sRGBCM(premult);
/*  152 */     WritableRaster wr = cm.createCompatibleWritableRaster(width, height);
/*  153 */     return new BufferedImage(cm, wr, premult, null);
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
/*      */   public static CachableRed convertToLsRGB(CachableRed src) {
/*  168 */     ColorModel cm = src.getColorModel();
/*  169 */     ColorSpace cs = cm.getColorSpace();
/*  170 */     if (cs == ColorSpace.getInstance(1004)) {
/*  171 */       return src;
/*      */     }
/*      */     
/*  174 */     return (CachableRed)new Any2LsRGBRed(src);
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
/*      */   public static CachableRed convertTosRGB(CachableRed src) {
/*  188 */     ColorModel cm = src.getColorModel();
/*  189 */     ColorSpace cs = cm.getColorSpace();
/*  190 */     if (cs == ColorSpace.getInstance(1000)) {
/*  191 */       return src;
/*      */     }
/*      */     
/*  194 */     return (CachableRed)new Any2sRGBRed(src);
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
/*      */   public static CachableRed wrap(RenderedImage ri) {
/*  211 */     if (ri instanceof CachableRed) {
/*  212 */       return (CachableRed)ri;
/*      */     }
/*  214 */     if (ri instanceof BufferedImage) {
/*  215 */       return (CachableRed)new BufferedImageCachableRed((BufferedImage)ri);
/*      */     }
/*  217 */     return (CachableRed)new RenderedImageCachableRed(ri);
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
/*      */   public static void copyData_INT_PACK(Raster src, WritableRaster dst) {
/*  233 */     int x0 = dst.getMinX();
/*  234 */     if (x0 < src.getMinX()) {
/*  235 */       x0 = src.getMinX();
/*      */     }
/*      */     
/*  238 */     int y0 = dst.getMinY();
/*  239 */     if (y0 < src.getMinY()) {
/*  240 */       y0 = src.getMinY();
/*      */     }
/*      */     
/*  243 */     int x1 = dst.getMinX() + dst.getWidth() - 1;
/*  244 */     if (x1 > src.getMinX() + src.getWidth() - 1) {
/*  245 */       x1 = src.getMinX() + src.getWidth() - 1;
/*      */     }
/*      */     
/*  248 */     int y1 = dst.getMinY() + dst.getHeight() - 1;
/*  249 */     if (y1 > src.getMinY() + src.getHeight() - 1) {
/*  250 */       y1 = src.getMinY() + src.getHeight() - 1;
/*      */     }
/*      */     
/*  253 */     int width = x1 - x0 + 1;
/*  254 */     int height = y1 - y0 + 1;
/*      */ 
/*      */     
/*  257 */     SinglePixelPackedSampleModel srcSPPSM = (SinglePixelPackedSampleModel)src.getSampleModel();
/*      */     
/*  259 */     int srcScanStride = srcSPPSM.getScanlineStride();
/*  260 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/*  261 */     int[] srcPixels = srcDB.getBankData()[0];
/*  262 */     int srcBase = srcDB.getOffset() + srcSPPSM.getOffset(x0 - src.getSampleModelTranslateX(), y0 - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  269 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dst.getSampleModel();
/*      */     
/*  271 */     int dstScanStride = dstSPPSM.getScanlineStride();
/*  272 */     DataBufferInt dstDB = (DataBufferInt)dst.getDataBuffer();
/*  273 */     int[] dstPixels = dstDB.getBankData()[0];
/*  274 */     int dstBase = dstDB.getOffset() + dstSPPSM.getOffset(x0 - dst.getSampleModelTranslateX(), y0 - dst.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  279 */     if (srcScanStride == dstScanStride && srcScanStride == width) {
/*      */ 
/*      */ 
/*      */       
/*  283 */       System.arraycopy(srcPixels, srcBase, dstPixels, dstBase, width * height);
/*      */     }
/*  285 */     else if (width > 128) {
/*  286 */       int srcSP = srcBase;
/*  287 */       int dstSP = dstBase;
/*  288 */       for (int y = 0; y < height; y++) {
/*  289 */         System.arraycopy(srcPixels, srcSP, dstPixels, dstSP, width);
/*  290 */         srcSP += srcScanStride;
/*  291 */         dstSP += dstScanStride;
/*      */       } 
/*      */     } else {
/*  294 */       for (int y = 0; y < height; y++) {
/*  295 */         int srcSP = srcBase + y * srcScanStride;
/*  296 */         int dstSP = dstBase + y * dstScanStride;
/*  297 */         for (int x = 0; x < width; x++) {
/*  298 */           dstPixels[dstSP++] = srcPixels[srcSP++];
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyData_FALLBACK(Raster src, WritableRaster dst) {
/*  307 */     int x0 = dst.getMinX();
/*  308 */     if (x0 < src.getMinX()) {
/*  309 */       x0 = src.getMinX();
/*      */     }
/*      */     
/*  312 */     int y0 = dst.getMinY();
/*  313 */     if (y0 < src.getMinY()) {
/*  314 */       y0 = src.getMinY();
/*      */     }
/*      */     
/*  317 */     int x1 = dst.getMinX() + dst.getWidth() - 1;
/*  318 */     if (x1 > src.getMinX() + src.getWidth() - 1) {
/*  319 */       x1 = src.getMinX() + src.getWidth() - 1;
/*      */     }
/*      */     
/*  322 */     int y1 = dst.getMinY() + dst.getHeight() - 1;
/*  323 */     if (y1 > src.getMinY() + src.getHeight() - 1) {
/*  324 */       y1 = src.getMinY() + src.getHeight() - 1;
/*      */     }
/*      */     
/*  327 */     int width = x1 - x0 + 1;
/*  328 */     int[] data = null;
/*      */     
/*  330 */     for (int y = y0; y <= y1; y++) {
/*  331 */       data = src.getPixels(x0, y, width, 1, data);
/*  332 */       dst.setPixels(x0, y, width, 1, data);
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
/*      */   public static void copyData(Raster src, WritableRaster dst) {
/*  345 */     if (is_INT_PACK_Data(src.getSampleModel(), false) && is_INT_PACK_Data(dst.getSampleModel(), false)) {
/*      */       
/*  347 */       copyData_INT_PACK(src, dst);
/*      */       
/*      */       return;
/*      */     } 
/*  351 */     copyData_FALLBACK(src, dst);
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
/*      */   public static WritableRaster copyRaster(Raster ras) {
/*  366 */     return copyRaster(ras, ras.getMinX(), ras.getMinY());
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
/*      */   public static WritableRaster copyRaster(Raster ras, int minX, int minY) {
/*  391 */     WritableRaster ret = Raster.createWritableRaster(ras.getSampleModel(), new Point(0, 0));
/*      */ 
/*      */     
/*  394 */     ret = ret.createWritableChild(ras.getMinX() - ras.getSampleModelTranslateX(), ras.getMinY() - ras.getSampleModelTranslateY(), ras.getWidth(), ras.getHeight(), minX, minY, (int[])null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  401 */     DataBuffer srcDB = ras.getDataBuffer();
/*  402 */     DataBuffer retDB = ret.getDataBuffer();
/*  403 */     if (srcDB.getDataType() != retDB.getDataType()) {
/*  404 */       throw new IllegalArgumentException("New DataBuffer doesn't match original");
/*      */     }
/*      */     
/*  407 */     int len = srcDB.getSize();
/*  408 */     int banks = srcDB.getNumBanks();
/*  409 */     int[] offsets = srcDB.getOffsets();
/*  410 */     for (int b = 0; b < banks; b++) {
/*  411 */       DataBufferByte dataBufferByte1; DataBufferInt dataBufferInt1; DataBufferShort dataBufferShort1; DataBufferUShort srcDBT; DataBufferByte dataBufferByte2; DataBufferInt dataBufferInt2; DataBufferShort dataBufferShort2; DataBufferUShort retDBT; switch (srcDB.getDataType()) {
/*      */         case 0:
/*  413 */           dataBufferByte1 = (DataBufferByte)srcDB;
/*  414 */           dataBufferByte2 = (DataBufferByte)retDB;
/*  415 */           System.arraycopy(dataBufferByte1.getData(b), offsets[b], dataBufferByte2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  420 */           dataBufferInt1 = (DataBufferInt)srcDB;
/*  421 */           dataBufferInt2 = (DataBufferInt)retDB;
/*  422 */           System.arraycopy(dataBufferInt1.getData(b), offsets[b], dataBufferInt2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/*  427 */           dataBufferShort1 = (DataBufferShort)srcDB;
/*  428 */           dataBufferShort2 = (DataBufferShort)retDB;
/*  429 */           System.arraycopy(dataBufferShort1.getData(b), offsets[b], dataBufferShort2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 1:
/*  434 */           srcDBT = (DataBufferUShort)srcDB;
/*  435 */           retDBT = (DataBufferUShort)retDB;
/*  436 */           System.arraycopy(srcDBT.getData(b), offsets[b], retDBT.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  441 */           throw new UnsupportedOperationException("unsupported data type: " + srcDB.getDataType());
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  447 */     return ret;
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
/*      */   public static WritableRaster makeRasterWritable(Raster ras) {
/*  465 */     return makeRasterWritable(ras, ras.getMinX(), ras.getMinY());
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
/*      */   public static WritableRaster makeRasterWritable(Raster ras, int minX, int minY) {
/*  497 */     WritableRaster ret = Raster.createWritableRaster(ras.getSampleModel(), ras.getDataBuffer(), new Point(0, 0));
/*      */ 
/*      */ 
/*      */     
/*  501 */     ret = ret.createWritableChild(ras.getMinX() - ras.getSampleModelTranslateX(), ras.getMinY() - ras.getSampleModelTranslateY(), ras.getWidth(), ras.getHeight(), minX, minY, (int[])null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     return ret;
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
/*      */   public static ColorModel coerceColorModel(ColorModel cm, boolean newAlphaPreMult) {
/*  519 */     if (cm.isAlphaPremultiplied() == newAlphaPreMult) {
/*  520 */       return cm;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  526 */     WritableRaster wr = cm.createCompatibleWritableRaster(1, 1);
/*  527 */     return cm.coerceData(wr, newAlphaPreMult);
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
/*      */   public static ColorModel coerceData(WritableRaster wr, ColorModel cm, boolean newAlphaPreMult) {
/*  544 */     if (!cm.hasAlpha())
/*      */     {
/*  546 */       return cm;
/*      */     }
/*      */     
/*  549 */     if (cm.isAlphaPremultiplied() == newAlphaPreMult)
/*      */     {
/*  551 */       return cm;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  556 */     if (newAlphaPreMult) {
/*  557 */       multiplyAlpha(wr);
/*      */     } else {
/*  559 */       divideAlpha(wr);
/*      */     } 
/*      */     
/*  562 */     return coerceColorModel(cm, newAlphaPreMult);
/*      */   }
/*      */   
/*      */   public static void multiplyAlpha(WritableRaster wr) {
/*  566 */     if (is_BYTE_COMP_Data(wr.getSampleModel())) {
/*  567 */       mult_BYTE_COMP_Data(wr);
/*  568 */     } else if (is_INT_PACK_Data(wr.getSampleModel(), true)) {
/*  569 */       mult_INT_PACK_Data(wr);
/*      */     } else {
/*  571 */       int[] pixel = null;
/*  572 */       int bands = wr.getNumBands();
/*  573 */       float norm = 0.003921569F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  581 */       int x0 = wr.getMinX();
/*  582 */       int x1 = x0 + wr.getWidth();
/*  583 */       int y0 = wr.getMinY();
/*  584 */       int y1 = y0 + wr.getHeight();
/*  585 */       for (int y = y0; y < y1; y++) {
/*  586 */         for (int x = x0; x < x1; x++) {
/*  587 */           pixel = wr.getPixel(x, y, pixel);
/*  588 */           int a = pixel[bands - 1];
/*  589 */           if (a >= 0 && a < 255) {
/*  590 */             float alpha = a * norm;
/*  591 */             for (int b = 0; b < bands - 1; b++) {
/*  592 */               pixel[b] = (int)(pixel[b] * alpha + 0.5F);
/*      */             }
/*  594 */             wr.setPixel(x, y, pixel);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void divideAlpha(WritableRaster wr) {
/*  602 */     if (is_BYTE_COMP_Data(wr.getSampleModel())) {
/*  603 */       divide_BYTE_COMP_Data(wr);
/*  604 */     } else if (is_INT_PACK_Data(wr.getSampleModel(), true)) {
/*  605 */       divide_INT_PACK_Data(wr);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  614 */       int bands = wr.getNumBands();
/*  615 */       int[] pixel = null;
/*      */       
/*  617 */       int x0 = wr.getMinX();
/*  618 */       int x1 = x0 + wr.getWidth();
/*  619 */       int y0 = wr.getMinY();
/*  620 */       int y1 = y0 + wr.getHeight();
/*  621 */       for (int y = y0; y < y1; y++) {
/*  622 */         for (int x = x0; x < x1; x++) {
/*  623 */           pixel = wr.getPixel(x, y, pixel);
/*  624 */           int a = pixel[bands - 1];
/*  625 */           if (a > 0 && a < 255) {
/*  626 */             float ialpha = 255.0F / a;
/*  627 */             for (int b = 0; b < bands - 1; b++) {
/*  628 */               pixel[b] = (int)(pixel[b] * ialpha + 0.5F);
/*      */             }
/*  630 */             wr.setPixel(x, y, pixel);
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
/*      */   public static void copyData(BufferedImage src, BufferedImage dst) {
/*  646 */     Rectangle srcRect = new Rectangle(0, 0, src.getWidth(), src.getHeight());
/*      */     
/*  648 */     copyData(src, srcRect, dst, new Point(0, 0));
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
/*      */   public static void copyData(BufferedImage src, Rectangle srcRect, BufferedImage dst, Point destP) {
/*  671 */     boolean srcAlpha = src.getColorModel().hasAlpha();
/*  672 */     boolean dstAlpha = dst.getColorModel().hasAlpha();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  680 */     if (srcAlpha == dstAlpha && (
/*  681 */       !srcAlpha || src.isAlphaPremultiplied() == dst.isAlphaPremultiplied())) {
/*      */ 
/*      */       
/*  684 */       copyData(src.getRaster(), dst.getRaster());
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  691 */     int[] pixel = null;
/*  692 */     Raster srcR = src.getRaster();
/*  693 */     WritableRaster dstR = dst.getRaster();
/*  694 */     int bands = dstR.getNumBands();
/*      */     
/*  696 */     int dx = destP.x - srcRect.x;
/*  697 */     int dy = destP.y - srcRect.y;
/*      */     
/*  699 */     int w = srcRect.width;
/*  700 */     int x0 = srcRect.x;
/*  701 */     int y0 = srcRect.y;
/*  702 */     int y1 = y0 + srcRect.height - 1;
/*      */     
/*  704 */     if (!srcAlpha) {
/*      */ 
/*      */       
/*  707 */       int[] oPix = new int[bands * w];
/*  708 */       int out = w * bands - 1;
/*  709 */       while (out >= 0) {
/*      */         
/*  711 */         oPix[out] = 255;
/*  712 */         out -= bands;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  717 */       for (int y = y0; y <= y1; y++) {
/*  718 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/*  719 */         int in = w * (bands - 1) - 1;
/*  720 */         out = w * bands - 2;
/*  721 */         switch (bands) {
/*      */           case 4:
/*  723 */             while (in >= 0) {
/*  724 */               oPix[out--] = pixel[in--];
/*  725 */               oPix[out--] = pixel[in--];
/*  726 */               oPix[out--] = pixel[in--];
/*  727 */               out--;
/*      */             } 
/*      */             break;
/*      */           default:
/*  731 */             while (in >= 0) {
/*  732 */               for (int b = 0; b < bands - 1; b++) {
/*  733 */                 oPix[out--] = pixel[in--];
/*      */               }
/*  735 */               out--;
/*      */             }  break;
/*      */         } 
/*  738 */         dstR.setPixels(x0 + dx, y + dy, w, 1, oPix);
/*      */       } 
/*  740 */     } else if (dstAlpha && dst.isAlphaPremultiplied()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  747 */       int fpNorm = 65793;
/*  748 */       int pt5 = 8388608;
/*  749 */       for (int y = y0; y <= y1; y++) {
/*  750 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/*  751 */         int in = bands * w - 1;
/*  752 */         switch (bands) {
/*      */           case 4:
/*  754 */             while (in >= 0) {
/*  755 */               int a = pixel[in];
/*  756 */               if (a == 255) {
/*  757 */                 in -= 4; continue;
/*      */               } 
/*  759 */               in--;
/*  760 */               int alpha = fpNorm * a;
/*  761 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24;
/*  762 */               in--;
/*  763 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24;
/*  764 */               in--;
/*  765 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24;
/*  766 */               in--;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/*  771 */             while (in >= 0) {
/*  772 */               int i = pixel[in];
/*  773 */               if (i == 255) {
/*  774 */                 in -= bands; continue;
/*      */               } 
/*  776 */               in--;
/*  777 */               int j = fpNorm * i;
/*  778 */               for (int b = 0; b < bands - 1; b++) {
/*  779 */                 pixel[in] = pixel[in] * j + pt5 >>> 24;
/*  780 */                 in--;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/*  785 */         dstR.setPixels(x0 + dx, y + dy, w, 1, pixel);
/*      */       } 
/*  787 */     } else if (dstAlpha && !dst.isAlphaPremultiplied()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  794 */       int fpNorm = 16711680;
/*  795 */       int pt5 = 32768;
/*  796 */       for (int y = y0; y <= y1; y++) {
/*  797 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/*  798 */         int in = bands * w - 1;
/*  799 */         switch (bands) {
/*      */           case 4:
/*  801 */             while (in >= 0) {
/*  802 */               int a = pixel[in];
/*  803 */               if (a <= 0 || a >= 255) {
/*  804 */                 in -= 4; continue;
/*      */               } 
/*  806 */               in--;
/*  807 */               int ialpha = fpNorm / a;
/*  808 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16;
/*  809 */               in--;
/*  810 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16;
/*  811 */               in--;
/*  812 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16;
/*  813 */               in--;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/*  818 */             while (in >= 0) {
/*  819 */               int i = pixel[in];
/*  820 */               if (i <= 0 || i >= 255) {
/*  821 */                 in -= bands; continue;
/*      */               } 
/*  823 */               in--;
/*  824 */               int j = fpNorm / i;
/*  825 */               for (int b = 0; b < bands - 1; b++) {
/*  826 */                 pixel[in] = pixel[in] * j + pt5 >>> 16;
/*  827 */                 in--;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/*  832 */         dstR.setPixels(x0 + dx, y + dy, w, 1, pixel);
/*      */       } 
/*  834 */     } else if (src.isAlphaPremultiplied()) {
/*  835 */       int[] oPix = new int[bands * w];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  843 */       int fpNorm = 16711680;
/*  844 */       int pt5 = 32768;
/*  845 */       for (int y = y0; y <= y1; y++) {
/*  846 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/*  847 */         int in = (bands + 1) * w - 1;
/*  848 */         int out = bands * w - 1;
/*  849 */         while (in >= 0) {
/*  850 */           int a = pixel[in];
/*  851 */           in--;
/*  852 */           if (a > 0) {
/*  853 */             if (a < 255) {
/*  854 */               int ialpha = fpNorm / a;
/*  855 */               for (int j = 0; j < bands; j++)
/*  856 */                 oPix[out--] = pixel[in--] * ialpha + pt5 >>> 16; 
/*      */               continue;
/*      */             } 
/*  859 */             for (int i = 0; i < bands; i++) {
/*  860 */               oPix[out--] = pixel[in--];
/*      */             }
/*      */             continue;
/*      */           } 
/*  864 */           in -= bands;
/*  865 */           for (int b = 0; b < bands; b++) {
/*  866 */             oPix[out--] = 255;
/*      */           }
/*      */         } 
/*      */         
/*  870 */         dstR.setPixels(x0 + dx, y + dy, w, 1, oPix);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  875 */       Rectangle dstRect = new Rectangle(destP.x, destP.y, srcRect.width, srcRect.height);
/*      */       
/*  877 */       for (int b = 0; b < bands; b++) {
/*  878 */         copyBand(srcR, srcRect, b, dstR, dstRect, b);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyBand(Raster src, int srcBand, WritableRaster dst, int dstBand) {
/*  887 */     Rectangle sR = src.getBounds();
/*  888 */     Rectangle dR = dst.getBounds();
/*  889 */     Rectangle cpR = sR.intersection(dR);
/*      */     
/*  891 */     copyBand(src, cpR, srcBand, dst, cpR, dstBand);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void copyBand(Raster src, Rectangle sR, int sBand, WritableRaster dst, Rectangle dR, int dBand) {
/*  896 */     int width, height, dy = dR.y - sR.y;
/*  897 */     int dx = dR.x - sR.x;
/*  898 */     sR = sR.intersection(src.getBounds());
/*  899 */     dR = dR.intersection(dst.getBounds());
/*      */ 
/*      */     
/*  902 */     if (dR.width < sR.width) {
/*  903 */       width = dR.width;
/*      */     } else {
/*  905 */       width = sR.width;
/*      */     } 
/*  907 */     if (dR.height < sR.height) {
/*  908 */       height = dR.height;
/*      */     } else {
/*  910 */       height = sR.height;
/*      */     } 
/*  912 */     int x = sR.x + dx;
/*  913 */     int[] samples = null;
/*  914 */     for (int y = sR.y; y < sR.y + height; y++) {
/*  915 */       samples = src.getSamples(sR.x, y, width, 1, sBand, samples);
/*  916 */       dst.setSamples(x, y + dy, width, 1, dBand, samples);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean is_INT_PACK_Data(SampleModel sm, boolean requireAlpha) {
/*  923 */     if (!(sm instanceof SinglePixelPackedSampleModel)) {
/*  924 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  928 */     if (sm.getDataType() != 3) {
/*  929 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  933 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*      */     
/*  935 */     int[] masks = sppsm.getBitMasks();
/*  936 */     if (masks.length == 3) {
/*  937 */       if (requireAlpha) {
/*  938 */         return false;
/*      */       }
/*  940 */     } else if (masks.length != 4) {
/*  941 */       return false;
/*      */     } 
/*      */     
/*  944 */     if (masks[0] != 16711680) {
/*  945 */       return false;
/*      */     }
/*  947 */     if (masks[1] != 65280) {
/*  948 */       return false;
/*      */     }
/*  950 */     if (masks[2] != 255) {
/*  951 */       return false;
/*      */     }
/*  953 */     if (masks.length == 4 && masks[3] != -16777216)
/*      */     {
/*  955 */       return false;
/*      */     }
/*      */     
/*  958 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean is_BYTE_COMP_Data(SampleModel sm) {
/*  963 */     if (!(sm instanceof ComponentSampleModel)) {
/*  964 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  968 */     if (sm.getDataType() != 0) {
/*  969 */       return false;
/*      */     }
/*      */     
/*  972 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void divide_INT_PACK_Data(WritableRaster wr) {
/*  979 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*      */     
/*  981 */     int width = wr.getWidth();
/*      */     
/*  983 */     int scanStride = sppsm.getScanlineStride();
/*  984 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/*  985 */     int base = db.getOffset() + sppsm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  991 */     int[] pixels = db.getBankData()[0];
/*  992 */     for (int y = 0; y < wr.getHeight(); y++) {
/*  993 */       int sp = base + y * scanStride;
/*  994 */       int end = sp + width;
/*  995 */       while (sp < end) {
/*  996 */         int pixel = pixels[sp];
/*  997 */         int a = pixel >>> 24;
/*  998 */         if (a <= 0) {
/*  999 */           pixels[sp] = 16777215;
/* 1000 */         } else if (a < 255) {
/* 1001 */           int aFP = 16711680 / a;
/* 1002 */           pixels[sp] = a << 24 | ((pixel & 0xFF0000) >> 16) * aFP & 0xFF0000 | (((pixel & 0xFF00) >> 8) * aFP & 0xFF0000) >> 8 | ((pixel & 0xFF) * aFP & 0xFF0000) >> 16;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1008 */         sp++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void mult_INT_PACK_Data(WritableRaster wr) {
/* 1017 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*      */     
/* 1019 */     int width = wr.getWidth();
/*      */     
/* 1021 */     int scanStride = sppsm.getScanlineStride();
/* 1022 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 1023 */     int base = db.getOffset() + sppsm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1028 */     int[] pixels = db.getBankData()[0];
/* 1029 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1030 */       int sp = base + y * scanStride;
/* 1031 */       int end = sp + width;
/* 1032 */       while (sp < end) {
/* 1033 */         int pixel = pixels[sp];
/* 1034 */         int a = pixel >>> 24;
/* 1035 */         if (a >= 0 && a < 255) {
/* 1036 */           pixels[sp] = a << 24 | (pixel & 0xFF0000) * a >> 8 & 0xFF0000 | (pixel & 0xFF00) * a >> 8 & 0xFF00 | (pixel & 0xFF) * a >> 8 & 0xFF;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1041 */         sp++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void divide_BYTE_COMP_Data(WritableRaster wr) {
/* 1051 */     ComponentSampleModel csm = (ComponentSampleModel)wr.getSampleModel();
/*      */     
/* 1053 */     int width = wr.getWidth();
/*      */     
/* 1055 */     int scanStride = csm.getScanlineStride();
/* 1056 */     int pixStride = csm.getPixelStride();
/* 1057 */     int[] bandOff = csm.getBandOffsets();
/*      */     
/* 1059 */     DataBufferByte db = (DataBufferByte)wr.getDataBuffer();
/* 1060 */     int base = db.getOffset() + csm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1065 */     int aOff = bandOff[bandOff.length - 1];
/* 1066 */     int bands = bandOff.length - 1;
/*      */ 
/*      */     
/* 1069 */     byte[] pixels = db.getBankData()[0];
/* 1070 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1071 */       int sp = base + y * scanStride;
/* 1072 */       int end = sp + width * pixStride;
/* 1073 */       while (sp < end) {
/* 1074 */         int a = pixels[sp + aOff] & 0xFF;
/* 1075 */         if (a == 0) {
/* 1076 */           for (int b = 0; b < bands; b++) {
/* 1077 */             pixels[sp + bandOff[b]] = -1;
/*      */           }
/* 1079 */         } else if (a < 255) {
/* 1080 */           int aFP = 16711680 / a;
/* 1081 */           for (int b = 0; b < bands; b++) {
/* 1082 */             int i = sp + bandOff[b];
/* 1083 */             pixels[i] = (byte)((pixels[i] & 0xFF) * aFP >>> 16);
/*      */           } 
/*      */         } 
/* 1086 */         sp += pixStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void mult_BYTE_COMP_Data(WritableRaster wr) {
/* 1095 */     ComponentSampleModel csm = (ComponentSampleModel)wr.getSampleModel();
/*      */     
/* 1097 */     int width = wr.getWidth();
/*      */     
/* 1099 */     int scanStride = csm.getScanlineStride();
/* 1100 */     int pixStride = csm.getPixelStride();
/* 1101 */     int[] bandOff = csm.getBandOffsets();
/*      */     
/* 1103 */     DataBufferByte db = (DataBufferByte)wr.getDataBuffer();
/* 1104 */     int base = db.getOffset() + csm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1110 */     int aOff = bandOff[bandOff.length - 1];
/* 1111 */     int bands = bandOff.length - 1;
/*      */ 
/*      */     
/* 1114 */     byte[] pixels = db.getBankData()[0];
/* 1115 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1116 */       int sp = base + y * scanStride;
/* 1117 */       int end = sp + width * pixStride;
/* 1118 */       while (sp < end) {
/* 1119 */         int a = pixels[sp + aOff] & 0xFF;
/* 1120 */         if (a != 255) {
/* 1121 */           for (int b = 0; b < bands; b++) {
/* 1122 */             int i = sp + bandOff[b];
/* 1123 */             pixels[i] = (byte)((pixels[i] & 0xFF) * a >> 8);
/*      */           } 
/*      */         }
/* 1126 */         sp += pixStride;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Raster getAlphaRaster(RenderedImage image) {
/*      */     Raster alpha;
/* 1206 */     ColorModel cm = image.getColorModel();
/* 1207 */     if (!cm.hasAlpha() || cm.getTransparency() != 3) {
/* 1208 */       throw new IllegalStateException("Image doesn't have an alpha channel");
/*      */     }
/*      */     
/* 1211 */     if (image instanceof BufferedImage) {
/*      */       
/* 1213 */       alpha = ((BufferedImage)image).getAlphaRaster();
/*      */     } else {
/* 1215 */       WritableRaster wraster = makeRasterWritable(image.getData());
/* 1216 */       alpha = image.getColorModel().getAlphaRaster(wraster);
/*      */     } 
/* 1218 */     return alpha;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/GraphicsUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */