/*     */ package com.github.jaiimageio.impl.plugins.clib;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageWriterSpi;
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
/*     */ public abstract class CLibImageWriter
/*     */   extends ImageWriter
/*     */ {
/*     */   private static final Object getDataBufferData(DataBuffer db) {
/*     */     Object data;
/*  80 */     int dType = db.getDataType();
/*  81 */     switch (dType) {
/*     */       case 0:
/*  83 */         data = ((DataBufferByte)db).getData();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         return data;case 1: data = ((DataBufferUShort)db).getData(); return data;
/*     */     } 
/*     */     throw new IllegalArgumentException(I18N.getString("Generic0") + " " + dType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Raster getContiguousData(RenderedImage im, Rectangle region) {
/*     */     Raster raster;
/* 107 */     if (im == null)
/* 108 */       throw new IllegalArgumentException("im == null"); 
/* 109 */     if (region == null) {
/* 110 */       throw new IllegalArgumentException("region == null");
/*     */     }
/*     */ 
/*     */     
/* 114 */     if (im.getNumXTiles() == 1 && im.getNumYTiles() == 1) {
/*     */       
/* 116 */       raster = im.getTile(im.getMinTileX(), im.getMinTileY());
/*     */ 
/*     */       
/* 119 */       Rectangle bounds = raster.getBounds();
/* 120 */       if (!bounds.equals(region)) {
/* 121 */         raster = raster.createChild(region.x, region.y, region.width, region.height, region.x, region.y, null);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 131 */       SampleModel sampleModel = im.getSampleModel();
/*     */       
/* 133 */       WritableRaster target = (sampleModel.getSampleSize(0) == 8) ? Raster.createInterleavedRaster(0, im
/* 134 */           .getWidth(), im
/* 135 */           .getHeight(), sampleModel
/* 136 */           .getNumBands(), new Point(im
/* 137 */             .getMinX(), im
/* 138 */             .getMinY())) : null;
/*     */ 
/*     */ 
/*     */       
/* 142 */       raster = im.copyData(target);
/*     */     } 
/*     */     
/* 145 */     return raster;
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
/*     */   private static void reformat(Raster source, int[] sourceBands, int subsampleX, int subsampleY, WritableRaster dst) {
/* 171 */     if (source == null)
/* 172 */       throw new IllegalArgumentException("source == null!"); 
/* 173 */     if (dst == null) {
/* 174 */       throw new IllegalArgumentException("dst == null!");
/*     */     }
/*     */ 
/*     */     
/* 178 */     Rectangle sourceBounds = source.getBounds();
/* 179 */     if (sourceBounds.isEmpty()) {
/* 180 */       throw new IllegalArgumentException("source.getBounds().isEmpty()!");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 185 */     boolean isSubBanding = false;
/* 186 */     int numSourceBands = source.getSampleModel().getNumBands();
/* 187 */     if (sourceBands != null) {
/* 188 */       if (sourceBands.length > numSourceBands) {
/* 189 */         throw new IllegalArgumentException("sourceBands.length > numSourceBands!");
/*     */       }
/*     */ 
/*     */       
/* 193 */       boolean isRamp = (sourceBands.length == numSourceBands);
/* 194 */       for (int i = 0; i < sourceBands.length; i++) {
/* 195 */         if (sourceBands[i] < 0 || sourceBands[i] >= numSourceBands) {
/* 196 */           throw new IllegalArgumentException("sourceBands[i] < 0 || sourceBands[i] >= numSourceBands!");
/*     */         }
/* 198 */         if (sourceBands[i] != i) {
/* 199 */           isRamp = false;
/*     */         }
/*     */       } 
/*     */       
/* 203 */       isSubBanding = !isRamp;
/*     */     } 
/*     */ 
/*     */     
/* 207 */     int sourceWidth = sourceBounds.width;
/* 208 */     int[] pixels = new int[sourceWidth * numSourceBands];
/*     */ 
/*     */     
/* 211 */     int sourceX = sourceBounds.x;
/* 212 */     int sourceY = sourceBounds.y;
/* 213 */     int numBands = (sourceBands != null) ? sourceBands.length : numSourceBands;
/*     */     
/* 215 */     int dstWidth = dst.getWidth();
/* 216 */     int dstYMax = dst.getHeight() - 1;
/* 217 */     int copyFromIncrement = numSourceBands * subsampleX;
/*     */ 
/*     */     
/* 220 */     for (int dstY = 0; dstY <= dstYMax; dstY++) {
/*     */       
/* 222 */       source.getPixels(sourceX, sourceY, sourceWidth, 1, pixels);
/*     */ 
/*     */       
/* 225 */       if (isSubBanding) {
/* 226 */         int copyFrom = 0;
/* 227 */         int copyTo = 0;
/* 228 */         for (int i = 0; i < dstWidth; i++) {
/* 229 */           for (int j = 0; j < numBands; j++) {
/* 230 */             pixels[copyTo++] = pixels[copyFrom + sourceBands[j]];
/*     */           }
/* 232 */           copyFrom += copyFromIncrement;
/*     */         } 
/*     */       } else {
/* 235 */         int copyFrom = copyFromIncrement;
/* 236 */         int copyTo = numSourceBands;
/*     */         
/* 238 */         for (int i = 1; i < dstWidth; i++) {
/* 239 */           int k = copyFrom;
/* 240 */           for (int j = 0; j < numSourceBands; j++) {
/* 241 */             pixels[copyTo++] = pixels[k++];
/*     */           }
/* 243 */           copyFrom += copyFromIncrement;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 248 */       dst.setPixels(0, dstY, dstWidth, 1, pixels);
/*     */ 
/*     */       
/* 251 */       sourceY += subsampleY;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected CLibImageWriter(ImageWriterSpi originatingProvider) {
/* 256 */     super(originatingProvider);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 277 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Rectangle getSourceRegion(ImageWriteParam param, int sourceMinX, int sourceMinY, int srcWidth, int srcHeight) {
/* 301 */     Rectangle sourceRegion = new Rectangle(sourceMinX, sourceMinY, srcWidth, srcHeight);
/*     */     
/* 303 */     if (param != null) {
/* 304 */       Rectangle region = param.getSourceRegion();
/* 305 */       if (region != null) {
/* 306 */         sourceRegion = sourceRegion.intersection(region);
/*     */       }
/*     */       
/* 309 */       int subsampleXOffset = param.getSubsamplingXOffset();
/* 310 */       int subsampleYOffset = param.getSubsamplingYOffset();
/* 311 */       sourceRegion.x += subsampleXOffset;
/* 312 */       sourceRegion.y += subsampleYOffset;
/* 313 */       sourceRegion.width -= subsampleXOffset;
/* 314 */       sourceRegion.height -= subsampleYOffset;
/*     */     } 
/*     */     
/* 317 */     return sourceRegion;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/clib/CLibImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */