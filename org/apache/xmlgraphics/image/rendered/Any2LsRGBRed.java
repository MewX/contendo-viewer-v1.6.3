/*     */ package org.apache.xmlgraphics.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BandCombineOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
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
/*     */ public class Any2LsRGBRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean srcIssRGB;
/*     */   private static final double GAMMA = 2.4D;
/*     */   private static final double LFACT = 0.07739938080495357D;
/*     */   
/*     */   public Any2LsRGBRed(CachableRed src) {
/*  61 */     super(src, src.getBounds(), fixColorModel(src), fixSampleModel(src), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     ColorModel srcCM = src.getColorModel();
/*  69 */     if (srcCM == null) {
/*     */       return;
/*     */     }
/*  72 */     ColorSpace srcCS = srcCM.getColorSpace();
/*  73 */     if (srcCS == ColorSpace.getInstance(1000)) {
/*  74 */       this.srcIssRGB = true;
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
/*     */   public static final double sRGBToLsRGB(double value) {
/*  86 */     if (value <= 0.003928D) {
/*  87 */       return value * 0.07739938080495357D;
/*     */     }
/*  89 */     return Math.pow((value + 0.055D) / 1.055D, 2.4D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   private static final int[] sRGBToLsRGBLut = new int[256];
/*     */   static {
/* 100 */     double scale = 0.00392156862745098D;
/*     */ 
/*     */     
/* 103 */     for (int i = 0; i < 256; i++) {
/* 104 */       double value = sRGBToLsRGB(i * 0.00392156862745098D);
/* 105 */       sRGBToLsRGBLut[i] = (int)Math.round(value * 255.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 113 */     CachableRed src = getSources().get(0);
/* 114 */     ColorModel srcCM = src.getColorModel();
/* 115 */     SampleModel srcSM = src.getSampleModel();
/*     */ 
/*     */     
/* 118 */     if (this.srcIssRGB && Any2sRGBRed.is_INT_PACK_COMP(wr.getSampleModel())) {
/*     */       
/* 120 */       src.copyData(wr);
/* 121 */       if (srcCM.hasAlpha()) {
/* 122 */         GraphicsUtil.coerceData(wr, srcCM, false);
/*     */       }
/* 124 */       Any2sRGBRed.applyLut_INT(wr, sRGBToLsRGBLut);
/* 125 */       return wr;
/*     */     } 
/*     */     
/* 128 */     if (srcCM == null) {
/*     */ 
/*     */ 
/*     */       
/* 132 */       float[][] matrix = (float[][])null;
/* 133 */       switch (srcSM.getNumBands()) {
/*     */         case 1:
/* 135 */           matrix = new float[1][3];
/* 136 */           matrix[0][0] = 1.0F;
/* 137 */           matrix[0][1] = 1.0F;
/* 138 */           matrix[0][2] = 1.0F;
/*     */           break;
/*     */         case 2:
/* 141 */           matrix = new float[2][4];
/* 142 */           matrix[0][0] = 1.0F;
/* 143 */           matrix[0][1] = 1.0F;
/* 144 */           matrix[0][2] = 1.0F;
/* 145 */           matrix[1][3] = 1.0F;
/*     */           break;
/*     */         case 3:
/* 148 */           matrix = new float[3][3];
/* 149 */           matrix[0][0] = 1.0F;
/* 150 */           matrix[1][1] = 1.0F;
/* 151 */           matrix[2][2] = 1.0F;
/*     */           break;
/*     */         default:
/* 154 */           matrix = new float[srcSM.getNumBands()][4];
/* 155 */           matrix[0][0] = 1.0F;
/* 156 */           matrix[1][1] = 1.0F;
/* 157 */           matrix[2][2] = 1.0F;
/* 158 */           matrix[3][3] = 1.0F;
/*     */           break;
/*     */       } 
/*     */       
/* 162 */       Raster srcRas = src.getData(wr.getBounds());
/* 163 */       BandCombineOp op = new BandCombineOp(matrix, null);
/* 164 */       op.filter(srcRas, wr);
/*     */     } else {
/* 166 */       BufferedImage dstBI; WritableRaster srcWr; ColorModel dstCM = getColorModel();
/*     */ 
/*     */       
/* 169 */       if (!dstCM.hasAlpha()) {
/*     */ 
/*     */         
/* 172 */         dstBI = new BufferedImage(dstCM, wr.createWritableTranslatedChild(0, 0), dstCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 180 */         SinglePixelPackedSampleModel dstSM = (SinglePixelPackedSampleModel)wr.getSampleModel();
/* 181 */         int[] masks = dstSM.getBitMasks();
/* 182 */         SampleModel dstSMNoA = new SinglePixelPackedSampleModel(dstSM.getDataType(), dstSM.getWidth(), dstSM.getHeight(), dstSM.getScanlineStride(), new int[] { masks[0], masks[1], masks[2] });
/*     */ 
/*     */ 
/*     */         
/* 186 */         ColorModel dstCMNoA = GraphicsUtil.Linear_sRGB;
/*     */ 
/*     */         
/* 189 */         WritableRaster dstWr = Raster.createWritableRaster(dstSMNoA, wr.getDataBuffer(), new Point(0, 0));
/*     */ 
/*     */         
/* 192 */         dstWr = dstWr.createWritableChild(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY(), wr.getWidth(), wr.getHeight(), 0, 0, (int[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 198 */         dstBI = new BufferedImage(dstCMNoA, dstWr, false, null);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 204 */       ColorModel srcBICM = srcCM;
/*     */       
/* 206 */       if (srcCM.hasAlpha() && srcCM.isAlphaPremultiplied()) {
/* 207 */         Rectangle wrR = wr.getBounds();
/* 208 */         SampleModel sm = srcCM.createCompatibleSampleModel(wrR.width, wrR.height);
/*     */ 
/*     */         
/* 211 */         srcWr = Raster.createWritableRaster(sm, new Point(wrR.x, wrR.y));
/*     */         
/* 213 */         src.copyData(srcWr);
/* 214 */         srcBICM = GraphicsUtil.coerceData(srcWr, srcCM, false);
/*     */       } else {
/* 216 */         Raster srcRas = src.getData(wr.getBounds());
/* 217 */         srcWr = GraphicsUtil.makeRasterWritable(srcRas);
/*     */       } 
/*     */ 
/*     */       
/* 221 */       BufferedImage srcBI = new BufferedImage(srcBICM, srcWr.createWritableTranslatedChild(0, 0), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 233 */       ColorConvertOp op = new ColorConvertOp(null);
/* 234 */       op.filter(srcBI, dstBI);
/*     */       
/* 236 */       if (dstCM.hasAlpha()) {
/* 237 */         copyBand(srcWr, srcSM.getNumBands() - 1, wr, getSampleModel().getNumBands() - 1);
/*     */       }
/*     */     } 
/*     */     
/* 241 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 250 */     ColorModel cm = src.getColorModel();
/* 251 */     if (cm != null) {
/* 252 */       if (cm.hasAlpha()) {
/* 253 */         return GraphicsUtil.Linear_sRGB_Unpre;
/*     */       }
/*     */       
/* 256 */       return GraphicsUtil.Linear_sRGB;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 266 */     switch (sm.getNumBands()) {
/*     */       case 1:
/* 268 */         return GraphicsUtil.Linear_sRGB;
/*     */       case 2:
/* 270 */         return GraphicsUtil.Linear_sRGB_Unpre;
/*     */       case 3:
/* 272 */         return GraphicsUtil.Linear_sRGB;
/*     */     } 
/* 274 */     return GraphicsUtil.Linear_sRGB_Unpre;
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
/* 285 */     SampleModel sm = src.getSampleModel();
/* 286 */     ColorModel cm = src.getColorModel();
/*     */     
/* 288 */     boolean alpha = false;
/*     */     
/* 290 */     if (cm != null) {
/* 291 */       alpha = cm.hasAlpha();
/*     */     } else {
/* 293 */       switch (sm.getNumBands()) { case 1:
/*     */         case 3:
/* 295 */           alpha = false;
/*     */           break;
/*     */         default:
/* 298 */           alpha = true;
/*     */           break; }
/*     */     
/*     */     } 
/* 302 */     if (alpha) {
/* 303 */       return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255, -16777216 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 309 */     return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255 });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/Any2LsRGBRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */