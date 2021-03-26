/*     */ package org.apache.batik.ext.awt.image.rendered;
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
/*     */ public class Any2LsRGBRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean srcIssRGB = false;
/*     */   private static final double GAMMA = 2.4D;
/*     */   private static final double LFACT = 0.07739938080495357D;
/*     */   
/*     */   public Any2LsRGBRed(CachableRed src) {
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
/*  64 */     if (srcCS == ColorSpace.getInstance(1000)) {
/*  65 */       this.srcIssRGB = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final double sRGBToLsRGB(double value) {
/*  76 */     if (value <= 0.003928D)
/*  77 */       return value * 0.07739938080495357D; 
/*  78 */     return Math.pow((value + 0.055D) / 1.055D, 2.4D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final int[] sRGBToLsRGBLut = new int[256];
/*     */   static {
/*  89 */     double scale = 0.00392156862745098D;
/*     */ 
/*     */     
/*  92 */     for (int i = 0; i < 256; i++) {
/*  93 */       double value = sRGBToLsRGB(i * 0.00392156862745098D);
/*  94 */       sRGBToLsRGBLut[i] = (int)Math.round(value * 255.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 102 */     CachableRed src = getSources().get(0);
/* 103 */     ColorModel srcCM = src.getColorModel();
/* 104 */     SampleModel srcSM = src.getSampleModel();
/*     */ 
/*     */     
/* 107 */     if (this.srcIssRGB && Any2sRGBRed.is_INT_PACK_COMP(wr.getSampleModel())) {
/*     */       
/* 109 */       src.copyData(wr);
/* 110 */       if (srcCM.hasAlpha())
/* 111 */         GraphicsUtil.coerceData(wr, srcCM, false); 
/* 112 */       Any2sRGBRed.applyLut_INT(wr, sRGBToLsRGBLut);
/* 113 */       return wr;
/*     */     } 
/*     */     
/* 116 */     if (srcCM == null) {
/*     */ 
/*     */ 
/*     */       
/* 120 */       float[][] matrix = (float[][])null;
/* 121 */       switch (srcSM.getNumBands()) {
/*     */         case 1:
/* 123 */           matrix = new float[1][3];
/* 124 */           matrix[0][0] = 1.0F;
/* 125 */           matrix[0][1] = 1.0F;
/* 126 */           matrix[0][2] = 1.0F;
/*     */           break;
/*     */         case 2:
/* 129 */           matrix = new float[2][4];
/* 130 */           matrix[0][0] = 1.0F;
/* 131 */           matrix[0][1] = 1.0F;
/* 132 */           matrix[0][2] = 1.0F;
/* 133 */           matrix[1][3] = 1.0F;
/*     */           break;
/*     */         case 3:
/* 136 */           matrix = new float[3][3];
/* 137 */           matrix[0][0] = 1.0F;
/* 138 */           matrix[1][1] = 1.0F;
/* 139 */           matrix[2][2] = 1.0F;
/*     */           break;
/*     */         default:
/* 142 */           matrix = new float[srcSM.getNumBands()][4];
/* 143 */           matrix[0][0] = 1.0F;
/* 144 */           matrix[1][1] = 1.0F;
/* 145 */           matrix[2][2] = 1.0F;
/* 146 */           matrix[3][3] = 1.0F;
/*     */           break;
/*     */       } 
/*     */       
/* 150 */       Raster srcRas = src.getData(wr.getBounds());
/* 151 */       BandCombineOp op = new BandCombineOp(matrix, null);
/* 152 */       op.filter(srcRas, wr);
/*     */     } else {
/* 154 */       BufferedImage dstBI; WritableRaster srcWr; ColorModel dstCM = getColorModel();
/*     */ 
/*     */       
/* 157 */       if (!dstCM.hasAlpha()) {
/*     */ 
/*     */         
/* 160 */         dstBI = new BufferedImage(dstCM, wr.createWritableTranslatedChild(0, 0), dstCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 168 */         SinglePixelPackedSampleModel dstSM = (SinglePixelPackedSampleModel)wr.getSampleModel();
/* 169 */         int[] masks = dstSM.getBitMasks();
/* 170 */         SampleModel dstSMNoA = new SinglePixelPackedSampleModel(dstSM.getDataType(), dstSM.getWidth(), dstSM.getHeight(), dstSM.getScanlineStride(), new int[] { masks[0], masks[1], masks[2] });
/*     */ 
/*     */ 
/*     */         
/* 174 */         ColorModel dstCMNoA = GraphicsUtil.Linear_sRGB;
/*     */ 
/*     */         
/* 177 */         WritableRaster dstWr = Raster.createWritableRaster(dstSMNoA, wr.getDataBuffer(), new Point(0, 0));
/*     */ 
/*     */         
/* 180 */         dstWr = dstWr.createWritableChild(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY(), wr.getWidth(), wr.getHeight(), 0, 0, (int[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 186 */         dstBI = new BufferedImage(dstCMNoA, dstWr, false, null);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       ColorModel srcBICM = srcCM;
/*     */       
/* 194 */       if (srcCM.hasAlpha() && srcCM.isAlphaPremultiplied()) {
/* 195 */         Rectangle wrR = wr.getBounds();
/* 196 */         SampleModel sm = srcCM.createCompatibleSampleModel(wrR.width, wrR.height);
/*     */ 
/*     */         
/* 199 */         srcWr = Raster.createWritableRaster(sm, new Point(wrR.x, wrR.y));
/*     */         
/* 201 */         src.copyData(srcWr);
/* 202 */         srcBICM = GraphicsUtil.coerceData(srcWr, srcCM, false);
/*     */       } else {
/* 204 */         Raster srcRas = src.getData(wr.getBounds());
/* 205 */         srcWr = GraphicsUtil.makeRasterWritable(srcRas);
/*     */       } 
/*     */ 
/*     */       
/* 209 */       BufferedImage srcBI = new BufferedImage(srcBICM, srcWr.createWritableTranslatedChild(0, 0), false, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       ColorConvertOp op = new ColorConvertOp(null);
/* 222 */       op.filter(srcBI, dstBI);
/*     */       
/* 224 */       if (dstCM.hasAlpha()) {
/* 225 */         copyBand(srcWr, srcSM.getNumBands() - 1, wr, getSampleModel().getNumBands() - 1);
/*     */       }
/*     */     } 
/* 228 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 237 */     ColorModel cm = src.getColorModel();
/* 238 */     if (cm != null) {
/* 239 */       if (cm.hasAlpha()) {
/* 240 */         return GraphicsUtil.Linear_sRGB_Unpre;
/*     */       }
/* 242 */       return GraphicsUtil.Linear_sRGB;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 253 */     switch (sm.getNumBands()) {
/*     */       case 1:
/* 255 */         return GraphicsUtil.Linear_sRGB;
/*     */       case 2:
/* 257 */         return GraphicsUtil.Linear_sRGB_Unpre;
/*     */       case 3:
/* 259 */         return GraphicsUtil.Linear_sRGB;
/*     */     } 
/* 261 */     return GraphicsUtil.Linear_sRGB_Unpre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static SampleModel fixSampleModel(CachableRed src) {
/* 271 */     SampleModel sm = src.getSampleModel();
/* 272 */     ColorModel cm = src.getColorModel();
/*     */     
/* 274 */     boolean alpha = false;
/*     */     
/* 276 */     if (cm != null) {
/* 277 */       alpha = cm.hasAlpha();
/*     */     } else {
/* 279 */       switch (sm.getNumBands()) { case 1:
/*     */         case 3:
/* 281 */           alpha = false;
/*     */           break;
/*     */         default:
/* 284 */           alpha = true;
/*     */           break; }
/*     */     
/*     */     } 
/* 288 */     if (alpha) {
/* 289 */       return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255, -16777216 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     return new SinglePixelPackedSampleModel(3, sm.getWidth(), sm.getHeight(), new int[] { 16711680, 65280, 255 });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/Any2LsRGBRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */