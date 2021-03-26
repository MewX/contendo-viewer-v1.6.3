/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.ColorSpaceHintKey;
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
/*     */ public class FilterAsAlphaRed
/*     */   extends AbstractRed
/*     */ {
/*     */   public FilterAsAlphaRed(CachableRed src) {
/*  49 */     super(new Any2LumRed(src), src.getBounds(), new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0), new PixelInterleavedSampleModel(0, src.getSampleModel().getWidth(), src.getSampleModel().getHeight(), 1, src.getSampleModel().getWidth(), new int[] { 0 }), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
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
/*  65 */     this.props.put("org.apache.batik.gvt.filter.Colorspace", ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*  71 */     CachableRed srcRed = getSources().get(0);
/*     */     
/*  73 */     SampleModel sm = srcRed.getSampleModel();
/*  74 */     if (sm.getNumBands() == 1)
/*     */     {
/*  76 */       return srcRed.copyData(wr);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     Raster srcRas = srcRed.getData(wr.getBounds());
/*     */     
/*  85 */     PixelInterleavedSampleModel srcSM = (PixelInterleavedSampleModel)srcRas.getSampleModel();
/*     */     
/*  87 */     DataBufferByte srcDB = (DataBufferByte)srcRas.getDataBuffer();
/*  88 */     byte[] src = srcDB.getData();
/*     */ 
/*     */     
/*  91 */     PixelInterleavedSampleModel dstSM = (PixelInterleavedSampleModel)wr.getSampleModel();
/*     */     
/*  93 */     DataBufferByte dstDB = (DataBufferByte)wr.getDataBuffer();
/*  94 */     byte[] dst = dstDB.getData();
/*     */     
/*  96 */     int srcX0 = srcRas.getMinX() - srcRas.getSampleModelTranslateX();
/*  97 */     int srcY0 = srcRas.getMinY() - srcRas.getSampleModelTranslateY();
/*     */     
/*  99 */     int dstX0 = wr.getMinX() - wr.getSampleModelTranslateX();
/* 100 */     int dstX1 = dstX0 + wr.getWidth() - 1;
/* 101 */     int dstY0 = wr.getMinY() - wr.getSampleModelTranslateY();
/*     */     
/* 103 */     int srcStep = srcSM.getPixelStride();
/* 104 */     int[] offsets = srcSM.getBandOffsets();
/* 105 */     int srcLOff = offsets[0];
/* 106 */     int srcAOff = offsets[1];
/*     */     
/* 108 */     if (srcRed.getColorModel().isAlphaPremultiplied()) {
/*     */       
/* 110 */       for (int y = 0; y < srcRas.getHeight(); y++) {
/* 111 */         int srcI = srcDB.getOffset() + srcSM.getOffset(srcX0, srcY0);
/* 112 */         int dstI = dstDB.getOffset() + dstSM.getOffset(dstX0, dstY0);
/* 113 */         int dstE = dstDB.getOffset() + dstSM.getOffset(dstX1 + 1, dstY0);
/*     */         
/* 115 */         srcI += srcLOff;
/*     */         
/* 117 */         while (dstI < dstE) {
/* 118 */           dst[dstI++] = src[srcI];
/* 119 */           srcI += srcStep;
/*     */         } 
/* 121 */         srcY0++;
/* 122 */         dstY0++;
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 128 */       srcAOff -= srcLOff;
/*     */       
/* 130 */       for (int y = 0; y < srcRas.getHeight(); y++) {
/* 131 */         int srcI = srcDB.getOffset() + srcSM.getOffset(srcX0, srcY0);
/* 132 */         int dstI = dstDB.getOffset() + dstSM.getOffset(dstX0, dstY0);
/* 133 */         int dstE = dstDB.getOffset() + dstSM.getOffset(dstX1 + 1, dstY0);
/*     */         
/* 135 */         srcI += srcLOff;
/*     */         
/* 137 */         while (dstI < dstE) {
/* 138 */           int sl = src[srcI] & 0xFF;
/* 139 */           int sa = src[srcI + srcAOff] & 0xFF;
/*     */           
/* 141 */           dst[dstI++] = (byte)(sl * sa + 128 >> 8);
/*     */           
/* 143 */           srcI += srcStep;
/*     */         } 
/* 145 */         srcY0++;
/* 146 */         dstY0++;
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/FilterAsAlphaRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */