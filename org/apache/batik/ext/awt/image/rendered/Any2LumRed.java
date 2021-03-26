/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BandCombineOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.ColorSpaceHintKey;
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
/*     */ public class Any2LumRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean isColorConvertOpAplhaSupported;
/*     */   
/*     */   public Any2LumRed(CachableRed src) {
/*  59 */     super(src, src.getBounds(), fixColorModel(src), fixSampleModel(src), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.isColorConvertOpAplhaSupported = getColorConvertOpAplhaSupported();
/*     */     
/*  68 */     this.props.put("org.apache.batik.gvt.filter.Colorspace", ColorSpaceHintKey.VALUE_COLORSPACE_GREY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*  74 */     CachableRed src = getSources().get(0);
/*     */     
/*  76 */     SampleModel sm = src.getSampleModel();
/*  77 */     ColorModel srcCM = src.getColorModel();
/*  78 */     Raster srcRas = src.getData(wr.getBounds());
/*  79 */     if (srcCM == null) {
/*     */ 
/*     */       
/*  82 */       float[][] matrix = (float[][])null;
/*  83 */       if (sm.getNumBands() == 2) {
/*  84 */         matrix = new float[2][2];
/*  85 */         matrix[0][0] = 1.0F;
/*  86 */         matrix[1][1] = 1.0F;
/*     */       } else {
/*  88 */         matrix = new float[sm.getNumBands()][1];
/*  89 */         matrix[0][0] = 1.0F;
/*     */       } 
/*     */       
/*  92 */       BandCombineOp op = new BandCombineOp(matrix, null);
/*  93 */       op.filter(srcRas, wr);
/*     */     } else {
/*  95 */       BufferedImage dstBI; WritableRaster srcWr = (WritableRaster)srcRas;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       if (srcCM.hasAlpha()) {
/* 101 */         GraphicsUtil.coerceData(srcWr, srcCM, false);
/*     */       }
/*     */       
/* 104 */       BufferedImage srcBI = new BufferedImage(srcCM, srcWr.createWritableTranslatedChild(0, 0), false, null);
/*     */ 
/*     */ 
/*     */       
/* 108 */       ColorModel dstCM = getColorModel();
/*     */       
/* 110 */       if (dstCM.hasAlpha() && !this.isColorConvertOpAplhaSupported) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 118 */         PixelInterleavedSampleModel dstSM = (PixelInterleavedSampleModel)wr.getSampleModel();
/* 119 */         SampleModel smna = new PixelInterleavedSampleModel(dstSM.getDataType(), dstSM.getWidth(), dstSM.getHeight(), dstSM.getPixelStride(), dstSM.getScanlineStride(), new int[] { 0 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 126 */         WritableRaster dstWr = Raster.createWritableRaster(smna, wr.getDataBuffer(), new Point(0, 0));
/*     */ 
/*     */         
/* 129 */         dstWr = dstWr.createWritableChild(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY(), wr.getWidth(), wr.getHeight(), 0, 0, (int[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 135 */         ColorModel cmna = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         dstBI = new BufferedImage(cmna, dstWr, false, null);
/*     */       }
/*     */       else {
/*     */         
/* 145 */         dstBI = new BufferedImage(dstCM, wr.createWritableTranslatedChild(0, 0), dstCM.isAlphaPremultiplied(), null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 150 */       ColorConvertOp op = new ColorConvertOp(null);
/* 151 */       op.filter(srcBI, dstBI);
/*     */ 
/*     */       
/* 154 */       if (dstCM.hasAlpha()) {
/* 155 */         copyBand(srcWr, sm.getNumBands() - 1, wr, getSampleModel().getNumBands() - 1);
/*     */         
/* 157 */         if (dstCM.isAlphaPremultiplied())
/* 158 */           GraphicsUtil.multiplyAlpha(wr); 
/*     */       } 
/*     */     } 
/* 161 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 170 */     ColorModel cm = src.getColorModel();
/* 171 */     if (cm != null) {
/* 172 */       if (cm.hasAlpha()) {
/* 173 */         return new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8, 8 }, true, cm.isAlphaPremultiplied(), 3, 0);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       return new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
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
/* 192 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 194 */     if (sm.getNumBands() == 2) {
/* 195 */       return new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8, 8 }, true, true, 3, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     return new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
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
/*     */   protected static SampleModel fixSampleModel(CachableRed src) {
/* 215 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 217 */     int width = sm.getWidth();
/* 218 */     int height = sm.getHeight();
/*     */     
/* 220 */     ColorModel cm = src.getColorModel();
/* 221 */     if (cm != null) {
/* 222 */       if (cm.hasAlpha()) {
/* 223 */         return new PixelInterleavedSampleModel(0, width, height, 2, 2 * width, new int[] { 0, 1 });
/*     */       }
/*     */ 
/*     */       
/* 227 */       return new PixelInterleavedSampleModel(0, width, height, 1, width, new int[] { 0 });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (sm.getNumBands() == 2) {
/* 238 */       return new PixelInterleavedSampleModel(0, width, height, 2, 2 * width, new int[] { 0, 1 });
/*     */     }
/*     */ 
/*     */     
/* 242 */     return new PixelInterleavedSampleModel(0, width, height, 1, width, new int[] { 0 });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean getColorConvertOpAplhaSupported() {
/* 249 */     int size = 50;
/*     */ 
/*     */     
/* 252 */     BufferedImage srcImage = new BufferedImage(size, size, 2);
/*     */ 
/*     */     
/* 255 */     Graphics2D srcGraphics = srcImage.createGraphics();
/* 256 */     srcGraphics.setColor(Color.red);
/* 257 */     srcGraphics.fillRect(0, 0, size, size);
/* 258 */     srcGraphics.dispose();
/*     */ 
/*     */     
/* 261 */     BufferedImage dstImage = new BufferedImage(size, size, 2);
/*     */ 
/*     */     
/* 264 */     Graphics2D dstGraphics = dstImage.createGraphics();
/* 265 */     dstGraphics.setComposite(AlphaComposite.Clear);
/* 266 */     dstGraphics.fillRect(0, 0, size, size);
/* 267 */     dstGraphics.dispose();
/*     */     
/* 269 */     ColorSpace grayColorSpace = ColorSpace.getInstance(1003);
/* 270 */     ColorConvertOp op = new ColorConvertOp(grayColorSpace, null);
/* 271 */     op.filter(srcImage, dstImage);
/*     */     
/* 273 */     return (getAlpha(srcImage) == getAlpha(dstImage));
/*     */   }
/*     */   
/*     */   protected static int getAlpha(BufferedImage bufferedImage) {
/* 277 */     int x = bufferedImage.getWidth() / 2;
/* 278 */     int y = bufferedImage.getHeight() / 2;
/* 279 */     return 0xFF & bufferedImage.getRGB(x, y) >> 24;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/Any2LumRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */