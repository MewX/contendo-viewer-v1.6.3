/*     */ package org.apache.batik.ext.awt.image.rendered;
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
/*     */ public class Any2sRGBRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean srcIsLsRGB = false;
/*     */   private static final double GAMMA = 2.4D;
/*     */   
/*     */   public Any2sRGBRed(CachableRed src) {
/*  54 */     super(src, src.getBounds(), fixColorModel(src), fixSampleModel(src), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     ColorModel srcCM = src.getColorModel();
/*  62 */     if (srcCM == null)
/*  63 */       return;  ColorSpace srcCS = srcCM.getColorSpace();
/*  64 */     if (srcCS == ColorSpace.getInstance(1004))
/*  65 */       this.srcIsLsRGB = true; 
/*     */   }
/*     */   
/*     */   public static boolean is_INT_PACK_COMP(SampleModel sm) {
/*  69 */     if (!(sm instanceof SinglePixelPackedSampleModel)) return false;
/*     */ 
/*     */     
/*  72 */     if (sm.getDataType() != 3) return false;
/*     */ 
/*     */     
/*  75 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*     */     
/*  77 */     int[] masks = sppsm.getBitMasks();
/*  78 */     if (masks.length != 3 && masks.length != 4) return false; 
/*  79 */     if (masks[0] != 16711680) return false; 
/*  80 */     if (masks[1] != 65280) return false; 
/*  81 */     if (masks[2] != 255) return false; 
/*  82 */     if (masks.length == 4 && masks[3] != -16777216) {
/*  83 */       return false;
/*     */     }
/*  85 */     return true;
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
/*  99 */   private static final int[] linearToSRGBLut = new int[256];
/*     */   
/*     */   static {
/* 102 */     double scale = 0.00392156862745098D;
/* 103 */     double exp = 0.4166666666666667D;
/*     */     
/* 105 */     for (int i = 0; i < 256; i++) {
/* 106 */       double value = i * 0.00392156862745098D;
/* 107 */       if (value <= 0.0031308D) {
/* 108 */         value *= 12.92D;
/*     */       } else {
/* 110 */         value = 1.055D * Math.pow(value, 0.4166666666666667D) - 0.055D;
/*     */       } 
/* 112 */       linearToSRGBLut[i] = (int)Math.round(value * 255.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WritableRaster applyLut_INT(WritableRaster wr, int[] lut) {
/* 120 */     SinglePixelPackedSampleModel sm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 122 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/*     */     
/* 124 */     int srcBase = db.getOffset() + sm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     int[] pixels = db.getBankData()[0];
/* 130 */     int width = wr.getWidth();
/* 131 */     int height = wr.getHeight();
/* 132 */     int scanStride = sm.getScanlineStride();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     for (int y = 0; y < height; y++) {
/* 138 */       int sp = srcBase + y * scanStride;
/* 139 */       int end = sp + width;
/*     */       
/* 141 */       while (sp < end) {
/* 142 */         int pix = pixels[sp];
/* 143 */         pixels[sp] = pix & 0xFF000000 | lut[pix >>> 16 & 0xFF] << 16 | lut[pix >>> 8 & 0xFF] << 8 | lut[pix & 0xFF];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 148 */         sp++;
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 158 */     CachableRed src = getSources().get(0);
/* 159 */     ColorModel srcCM = src.getColorModel();
/* 160 */     SampleModel srcSM = src.getSampleModel();
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (this.srcIsLsRGB && is_INT_PACK_COMP(wr.getSampleModel())) {
/*     */       
/* 166 */       src.copyData(wr);
/* 167 */       if (srcCM.hasAlpha())
/* 168 */         GraphicsUtil.coerceData(wr, srcCM, false); 
/* 169 */       applyLut_INT(wr, linearToSRGBLut);
/* 170 */       return wr;
/*     */     } 
/*     */     
/* 173 */     if (srcCM == null) {
/*     */ 
/*     */ 
/*     */       
/* 177 */       float[][] matrix = (float[][])null;
/* 178 */       switch (srcSM.getNumBands())
/*     */       { case 1:
/* 180 */           matrix = new float[3][1];
/* 181 */           matrix[0][0] = 1.0F;
/* 182 */           matrix[1][0] = 1.0F;
/* 183 */           matrix[2][0] = 1.0F;
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
/* 206 */           raster = src.getData(wr.getBounds());
/* 207 */           bandCombineOp = new BandCombineOp(matrix, null);
/* 208 */           bandCombineOp.filter(raster, wr);
/* 209 */           return wr;case 2: matrix = new float[4][2]; matrix[0][0] = 1.0F; matrix[1][0] = 1.0F; matrix[2][0] = 1.0F; matrix[3][1] = 1.0F; raster = src.getData(wr.getBounds()); bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr;case 3: matrix = new float[3][3]; matrix[0][0] = 1.0F; matrix[1][1] = 1.0F; matrix[2][2] = 1.0F; raster = src.getData(wr.getBounds()); bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr; }  matrix = new float[4][srcSM.getNumBands()]; matrix[0][0] = 1.0F; matrix[1][1] = 1.0F; matrix[2][2] = 1.0F; matrix[3][3] = 1.0F; Raster raster = src.getData(wr.getBounds()); BandCombineOp bandCombineOp = new BandCombineOp(matrix, null); bandCombineOp.filter(raster, wr); return wr;
/*     */     } 
/*     */     
/* 212 */     if (srcCM.getColorSpace() == ColorSpace.getInstance(1003)) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 220 */         float[][] matrix = (float[][])null;
/* 221 */         switch (srcSM.getNumBands()) {
/*     */           case 1:
/* 223 */             matrix = new float[3][1];
/* 224 */             matrix[0][0] = 1.0F;
/* 225 */             matrix[1][0] = 1.0F;
/* 226 */             matrix[2][0] = 1.0F;
/*     */             break;
/*     */           
/*     */           default:
/* 230 */             matrix = new float[4][2];
/* 231 */             matrix[0][0] = 1.0F;
/* 232 */             matrix[1][0] = 1.0F;
/* 233 */             matrix[2][0] = 1.0F;
/* 234 */             matrix[3][1] = 1.0F;
/*     */             break;
/*     */         } 
/* 237 */         Raster raster = src.getData(wr.getBounds());
/* 238 */         BandCombineOp bandCombineOp = new BandCombineOp(matrix, null);
/* 239 */         bandCombineOp.filter(raster, wr);
/* 240 */       } catch (Throwable t) {
/* 241 */         t.printStackTrace();
/*     */       } 
/* 243 */       return wr;
/*     */     } 
/*     */     
/* 246 */     ColorModel dstCM = getColorModel();
/* 247 */     if (srcCM.getColorSpace() == dstCM.getColorSpace()) {
/*     */ 
/*     */ 
/*     */       
/* 251 */       if (is_INT_PACK_COMP(srcSM)) {
/* 252 */         src.copyData(wr);
/*     */       } else {
/* 254 */         GraphicsUtil.copyData(src.getData(wr.getBounds()), wr);
/*     */       } 
/* 256 */       return wr;
/*     */     } 
/*     */     
/* 259 */     Raster srcRas = src.getData(wr.getBounds());
/* 260 */     WritableRaster srcWr = (WritableRaster)srcRas;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     ColorModel srcBICM = srcCM;
/* 266 */     if (srcCM.hasAlpha()) {
/* 267 */       srcBICM = GraphicsUtil.coerceData(srcWr, srcCM, false);
/*     */     }
/*     */     
/* 270 */     BufferedImage srcBI = new BufferedImage(srcBICM, srcWr.createWritableTranslatedChild(0, 0), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     ColorConvertOp op = new ColorConvertOp(dstCM.getColorSpace(), null);
/*     */     
/* 280 */     BufferedImage dstBI = op.filter(srcBI, (BufferedImage)null);
/*     */ 
/*     */ 
/*     */     
/* 284 */     WritableRaster wr00 = wr.createWritableTranslatedChild(0, 0);
/* 285 */     for (int i = 0; i < dstCM.getColorSpace().getNumComponents(); i++) {
/* 286 */       copyBand(dstBI.getRaster(), i, wr00, i);
/*     */     }
/* 288 */     if (dstCM.hasAlpha()) {
/* 289 */       copyBand(srcWr, srcSM.getNumBands() - 1, wr, getSampleModel().getNumBands() - 1);
/*     */     }
/* 291 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 300 */     ColorModel cm = src.getColorModel();
/* 301 */     if (cm != null) {
/* 302 */       if (cm.hasAlpha()) {
/* 303 */         return GraphicsUtil.sRGB_Unpre;
/*     */       }
/* 305 */       return GraphicsUtil.sRGB;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 316 */     switch (sm.getNumBands()) {
/*     */       case 1:
/* 318 */         return GraphicsUtil.sRGB;
/*     */       case 2:
/* 320 */         return GraphicsUtil.sRGB_Unpre;
/*     */       case 3:
/* 322 */         return GraphicsUtil.sRGB;
/*     */     } 
/* 324 */     return GraphicsUtil.sRGB_Unpre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static SampleModel fixSampleModel(CachableRed src) {
/* 334 */     SampleModel sm = src.getSampleModel();
/* 335 */     ColorModel cm = src.getColorModel();
/*     */     
/* 337 */     boolean alpha = false;
/*     */     
/* 339 */     if (cm != null) {
/* 340 */       alpha = cm.hasAlpha();
/*     */     } else {
/* 342 */       switch (sm.getNumBands()) { case 1:
/*     */         case 3:
/* 344 */           alpha = false;
/*     */           break;
/*     */         default:
/* 347 */           alpha = true;
/*     */           break; }
/*     */     
/*     */     } 
/* 351 */     if (alpha) {
/* 352 */       return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255, -16777216 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255 });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/Any2sRGBRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */