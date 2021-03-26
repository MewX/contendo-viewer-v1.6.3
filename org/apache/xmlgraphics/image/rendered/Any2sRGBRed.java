/*     */ package org.apache.xmlgraphics.image.rendered;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BandCombineOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.image.GraphicsUtil;
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
/*     */ public class Any2sRGBRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean srcIsLsRGB;
/*     */   private static final double GAMMA = 2.4D;
/*     */   
/*     */   public Any2sRGBRed(CachableRed src) {
/*  63 */     super(src, src.getBounds(), fixColorModel(src), fixSampleModel(src), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     ColorModel srcCM = src.getColorModel();
/*  71 */     if (srcCM == null) {
/*     */       return;
/*     */     }
/*  74 */     ColorSpace srcCS = srcCM.getColorSpace();
/*  75 */     if (srcCS == ColorSpace.getInstance(1004)) {
/*  76 */       this.srcIsLsRGB = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean is_INT_PACK_COMP(SampleModel sm) {
/*  81 */     if (!(sm instanceof SinglePixelPackedSampleModel)) {
/*  82 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  86 */     if (sm.getDataType() != 3) {
/*  87 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  91 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*     */     
/*  93 */     int[] masks = sppsm.getBitMasks();
/*  94 */     if (masks.length != 3 && masks.length != 4) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (masks[0] != 16711680) {
/*  98 */       return false;
/*     */     }
/* 100 */     if (masks[1] != 65280) {
/* 101 */       return false;
/*     */     }
/* 103 */     if (masks[2] != 255) {
/* 104 */       return false;
/*     */     }
/* 106 */     if (masks.length == 4 && masks[3] != -16777216)
/*     */     {
/* 108 */       return false;
/*     */     }
/*     */     
/* 111 */     return true;
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
/* 125 */   private static final int[] linearToSRGBLut = new int[256];
/*     */   static {
/* 127 */     double scale = 0.00392156862745098D;
/* 128 */     double exp = 0.4166666666666667D;
/*     */     
/* 130 */     for (int i = 0; i < 256; i++) {
/* 131 */       double value = i * 0.00392156862745098D;
/* 132 */       if (value <= 0.0031308D) {
/* 133 */         value *= 12.92D;
/*     */       } else {
/* 135 */         value = 1.055D * Math.pow(value, 0.4166666666666667D) - 0.055D;
/*     */       } 
/*     */       
/* 138 */       linearToSRGBLut[i] = (int)Math.round(value * 255.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WritableRaster applyLut_INT(WritableRaster wr, int[] lut) {
/* 146 */     SinglePixelPackedSampleModel sm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 148 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/*     */     
/* 150 */     int srcBase = db.getOffset() + sm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     int[] pixels = db.getBankData()[0];
/* 156 */     int width = wr.getWidth();
/* 157 */     int height = wr.getHeight();
/* 158 */     int scanStride = sm.getScanlineStride();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     for (int y = 0; y < height; y++) {
/* 165 */       int sp = srcBase + y * scanStride;
/* 166 */       int end = sp + width;
/*     */       
/* 168 */       while (sp < end) {
/* 169 */         int pix = pixels[sp];
/* 170 */         pixels[sp] = pix & 0xFF000000 | lut[pix >>> 16 & 0xFF] << 16 | lut[pix >>> 8 & 0xFF] << 8 | lut[pix & 0xFF];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         sp++;
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 185 */     CachableRed src = getSources().get(0);
/* 186 */     ColorModel srcCM = src.getColorModel();
/* 187 */     SampleModel srcSM = src.getSampleModel();
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (this.srcIsLsRGB && is_INT_PACK_COMP(wr.getSampleModel())) {
/*     */       
/* 193 */       src.copyData(wr);
/* 194 */       if (srcCM.hasAlpha()) {
/* 195 */         GraphicsUtil.coerceData(wr, srcCM, false);
/*     */       }
/* 197 */       applyLut_INT(wr, linearToSRGBLut);
/* 198 */       return wr;
/*     */     } 
/*     */     
/* 201 */     if (srcCM == null) {
/*     */ 
/*     */ 
/*     */       
/* 205 */       float[][] matrix = (float[][])null;
/* 206 */       switch (srcSM.getNumBands())
/*     */       { case 1:
/* 208 */           matrix = new float[3][1];
/* 209 */           matrix[0][0] = 1.0F;
/* 210 */           matrix[1][0] = 1.0F;
/* 211 */           matrix[2][0] = 1.0F;
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
/* 234 */           raster = src.getData(wr.getBounds());
/* 235 */           bandCombineOp = new BandCombineOp(matrix, null);
/* 236 */           bandCombineOp.filter(raster, wr);
/* 237 */           return wr;case 2: matrix = new float[4][2]; matrix[0][0] = 1.0F; matrix[1][0] = 1.0F; matrix[3][0] = 1.0F; matrix[3][1] = 1.0F; raster = src.getData(wr.getBounds()); bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr;case 3: matrix = new float[3][3]; matrix[0][0] = 1.0F; matrix[1][1] = 1.0F; matrix[2][2] = 1.0F; raster = src.getData(wr.getBounds()); bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr; }  matrix = new float[4][srcSM.getNumBands()]; matrix[0][0] = 1.0F; matrix[1][1] = 1.0F; matrix[2][2] = 1.0F; matrix[3][3] = 1.0F; Raster raster = src.getData(wr.getBounds()); BandCombineOp bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr;
/*     */     } 
/*     */     
/* 240 */     if (srcCM.getColorSpace() == ColorSpace.getInstance(1003)) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 248 */         float[][] matrix = (float[][])null;
/* 249 */         switch (srcSM.getNumBands()) {
/*     */           case 1:
/* 251 */             matrix = new float[3][1];
/* 252 */             matrix[0][0] = 1.0F;
/* 253 */             matrix[1][0] = 1.0F;
/* 254 */             matrix[2][0] = 1.0F;
/*     */             break;
/*     */           
/*     */           default:
/* 258 */             matrix = new float[4][2];
/* 259 */             matrix[0][0] = 1.0F;
/* 260 */             matrix[1][0] = 1.0F;
/* 261 */             matrix[3][0] = 1.0F;
/* 262 */             matrix[4][1] = 1.0F;
/*     */             break;
/*     */         } 
/* 265 */         Raster raster = src.getData(wr.getBounds());
/* 266 */         BandCombineOp bandCombineOp = new BandCombineOp(matrix, null);
/* 267 */         bandCombineOp.filter(raster, wr);
/* 268 */       } catch (Throwable t) {
/* 269 */         t.printStackTrace();
/*     */       } 
/* 271 */       return wr;
/*     */     } 
/*     */     
/* 274 */     ColorModel dstCM = getColorModel();
/* 275 */     if (srcCM.getColorSpace() == dstCM.getColorSpace()) {
/*     */ 
/*     */ 
/*     */       
/* 279 */       if (is_INT_PACK_COMP(srcSM)) {
/* 280 */         src.copyData(wr);
/*     */       } else {
/* 282 */         GraphicsUtil.copyData(src.getData(wr.getBounds()), wr);
/*     */       } 
/*     */       
/* 285 */       return wr;
/*     */     } 
/*     */     
/* 288 */     Raster srcRas = src.getData(wr.getBounds());
/* 289 */     assert srcRas instanceof WritableRaster;
/* 290 */     WritableRaster srcWr = (WritableRaster)srcRas;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     ColorModel srcBICM = srcCM;
/* 296 */     if (srcCM.hasAlpha()) {
/* 297 */       srcBICM = GraphicsUtil.coerceData(srcWr, srcCM, false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 302 */     BufferedImage srcBI = new BufferedImage(srcBICM, srcWr.createWritableTranslatedChild(0, 0), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     ColorConvertOp op = new ColorConvertOp(dstCM.getColorSpace(), null);
/*     */     
/* 312 */     BufferedImage dstBI = op.filter(srcBI, (BufferedImage)null);
/*     */ 
/*     */ 
/*     */     
/* 316 */     WritableRaster wr00 = wr.createWritableTranslatedChild(0, 0);
/* 317 */     for (int i = 0; i < dstCM.getColorSpace().getNumComponents(); i++) {
/* 318 */       copyBand(dstBI.getRaster(), i, wr00, i);
/*     */     }
/*     */     
/* 321 */     if (dstCM.hasAlpha()) {
/* 322 */       copyBand(srcWr, srcSM.getNumBands() - 1, wr, getSampleModel().getNumBands() - 1);
/*     */     }
/*     */     
/* 325 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 334 */     ColorModel cm = src.getColorModel();
/* 335 */     if (cm != null) {
/* 336 */       if (cm.hasAlpha()) {
/* 337 */         return GraphicsUtil.sRGB_Unpre;
/*     */       }
/*     */       
/* 340 */       return GraphicsUtil.sRGB;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 350 */     switch (sm.getNumBands()) {
/*     */       case 1:
/* 352 */         return GraphicsUtil.sRGB;
/*     */       case 2:
/* 354 */         return GraphicsUtil.sRGB_Unpre;
/*     */       case 3:
/* 356 */         return GraphicsUtil.sRGB;
/*     */     } 
/* 358 */     return GraphicsUtil.sRGB_Unpre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static SampleModel fixSampleModel(CachableRed src) {
/* 369 */     SampleModel sm = src.getSampleModel();
/* 370 */     ColorModel cm = src.getColorModel();
/*     */     
/* 372 */     boolean alpha = false;
/*     */     
/* 374 */     if (cm != null) {
/* 375 */       alpha = cm.hasAlpha();
/*     */     } else {
/* 377 */       switch (sm.getNumBands()) { case 1:
/*     */         case 3:
/* 379 */           alpha = false;
/*     */           break;
/*     */         default:
/* 382 */           alpha = true;
/*     */           break; }
/*     */     
/*     */     } 
/* 386 */     if (alpha) {
/* 387 */       return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255, -16777216 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255 });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/Any2sRGBRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */