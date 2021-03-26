/*     */ package com.github.jaiimageio.impl.plugins.wbmp;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WBMPImageWriter
/*     */   extends ImageWriter
/*     */ {
/*  83 */   private ImageOutputStream stream = null;
/*     */ 
/*     */   
/*     */   private static int getNumBits(int intValue) {
/*  87 */     int numBits = 32;
/*  88 */     int mask = Integer.MIN_VALUE;
/*  89 */     while (mask != 0 && (intValue & mask) == 0) {
/*  90 */       numBits--;
/*  91 */       mask >>>= 1;
/*     */     } 
/*  93 */     return numBits;
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] intToMultiByte(int intValue) {
/*  98 */     int numBitsLeft = getNumBits(intValue);
/*  99 */     byte[] multiBytes = new byte[(numBitsLeft + 6) / 7];
/*     */     
/* 101 */     int maxIndex = multiBytes.length - 1;
/* 102 */     for (int b = 0; b <= maxIndex; b++) {
/* 103 */       multiBytes[b] = (byte)(intValue >>> (maxIndex - b) * 7 & 0x7F);
/* 104 */       if (b != maxIndex) {
/* 105 */         multiBytes[b] = (byte)(multiBytes[b] | Byte.MIN_VALUE);
/*     */       }
/*     */     } 
/*     */     
/* 109 */     return multiBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WBMPImageWriter(ImageWriterSpi originator) {
/* 116 */     super(originator);
/*     */   }
/*     */   
/*     */   public void setOutput(Object output) {
/* 120 */     super.setOutput(output);
/* 121 */     if (output != null) {
/* 122 */       if (!(output instanceof ImageOutputStream))
/* 123 */         throw new IllegalArgumentException(I18N.getString("WBMPImageWriter")); 
/* 124 */       this.stream = (ImageOutputStream)output;
/*     */     } else {
/* 126 */       this.stream = null;
/*     */     } 
/*     */   }
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 135 */     WBMPMetadata meta = new WBMPMetadata();
/* 136 */     meta.wbmpType = 0;
/* 137 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata metadata, ImageTypeSpecifier type, ImageWriteParam param) {
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canWriteRasters() {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/* 158 */     if (this.stream == null) {
/* 159 */       throw new IllegalStateException(I18N.getString("WBMPImageWriter3"));
/*     */     }
/*     */     
/* 162 */     if (image == null) {
/* 163 */       throw new IllegalArgumentException(I18N.getString("WBMPImageWriter4"));
/*     */     }
/*     */     
/* 166 */     clearAbortRequest();
/* 167 */     processImageStarted(0);
/* 168 */     if (param == null) {
/* 169 */       param = getDefaultWriteParam();
/*     */     }
/* 171 */     RenderedImage input = null;
/* 172 */     Raster inputRaster = null;
/* 173 */     boolean writeRaster = image.hasRaster();
/* 174 */     Rectangle sourceRegion = param.getSourceRegion();
/* 175 */     SampleModel sampleModel = null;
/*     */     
/* 177 */     if (writeRaster) {
/* 178 */       inputRaster = image.getRaster();
/* 179 */       sampleModel = inputRaster.getSampleModel();
/*     */     } else {
/* 181 */       input = image.getRenderedImage();
/* 182 */       sampleModel = input.getSampleModel();
/*     */       
/* 184 */       inputRaster = input.getData();
/*     */     } 
/*     */     
/* 187 */     checkSampleModel(sampleModel);
/* 188 */     if (sourceRegion == null) {
/* 189 */       sourceRegion = inputRaster.getBounds();
/*     */     } else {
/* 191 */       sourceRegion = sourceRegion.intersection(inputRaster.getBounds());
/*     */     } 
/* 193 */     if (sourceRegion.isEmpty()) {
/* 194 */       throw new RuntimeException(I18N.getString("WBMPImageWriter1"));
/*     */     }
/* 196 */     int scaleX = param.getSourceXSubsampling();
/* 197 */     int scaleY = param.getSourceYSubsampling();
/* 198 */     int xOffset = param.getSubsamplingXOffset();
/* 199 */     int yOffset = param.getSubsamplingYOffset();
/*     */     
/* 201 */     sourceRegion.translate(xOffset, yOffset);
/* 202 */     sourceRegion.width -= xOffset;
/* 203 */     sourceRegion.height -= yOffset;
/*     */     
/* 205 */     int minX = sourceRegion.x / scaleX;
/* 206 */     int minY = sourceRegion.y / scaleY;
/* 207 */     int w = (sourceRegion.width + scaleX - 1) / scaleX;
/* 208 */     int h = (sourceRegion.height + scaleY - 1) / scaleY;
/*     */     
/* 210 */     Rectangle destinationRegion = new Rectangle(minX, minY, w, h);
/* 211 */     sampleModel = sampleModel.createCompatibleSampleModel(w, h);
/*     */     
/* 213 */     SampleModel destSM = sampleModel;
/*     */ 
/*     */     
/* 216 */     if (sampleModel.getDataType() != 0 || !(sampleModel instanceof MultiPixelPackedSampleModel) || ((MultiPixelPackedSampleModel)sampleModel)
/*     */       
/* 218 */       .getDataBitOffset() != 0) {
/* 219 */       destSM = new MultiPixelPackedSampleModel(0, w, h, 1, w + 7 >> 3, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     if (!destinationRegion.equals(sourceRegion)) {
/* 226 */       if (scaleX == 1 && scaleY == 1) {
/* 227 */         inputRaster = inputRaster.createChild(inputRaster.getMinX(), inputRaster
/* 228 */             .getMinY(), w, h, minX, minY, null);
/*     */       } else {
/*     */         
/* 231 */         WritableRaster ras = Raster.createWritableRaster(destSM, new Point(minX, minY));
/*     */ 
/*     */         
/* 234 */         byte[] data = ((DataBufferByte)ras.getDataBuffer()).getData();
/*     */         
/* 236 */         int j = minY, y = sourceRegion.y, k = 0;
/* 237 */         for (; j < minY + h; j++, y += scaleY) {
/*     */           
/* 239 */           int i = 0, x = sourceRegion.x;
/* 240 */           for (; i < w; i++, x += scaleX) {
/* 241 */             int v = inputRaster.getSample(x, y, 0);
/* 242 */             data[k + (i >> 3)] = (byte)(data[k + (i >> 3)] | v << 7 - (i & 0x7));
/*     */           } 
/* 244 */           k += w + 7 >> 3;
/*     */         } 
/* 246 */         inputRaster = ras;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 251 */     if (!destSM.equals(inputRaster.getSampleModel())) {
/*     */       
/* 253 */       WritableRaster raster = Raster.createWritableRaster(destSM, new Point(inputRaster
/* 254 */             .getMinX(), inputRaster
/* 255 */             .getMinY()));
/* 256 */       raster.setRect(inputRaster);
/* 257 */       inputRaster = raster;
/*     */     } 
/*     */ 
/*     */     
/* 261 */     boolean isWhiteZero = false;
/* 262 */     if (!writeRaster && input.getColorModel() instanceof IndexColorModel) {
/* 263 */       IndexColorModel icm = (IndexColorModel)input.getColorModel();
/* 264 */       isWhiteZero = (icm.getRed(0) > icm.getRed(1));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 269 */     int lineStride = ((MultiPixelPackedSampleModel)destSM).getScanlineStride();
/* 270 */     int bytesPerRow = (w + 7) / 8;
/* 271 */     byte[] bdata = ((DataBufferByte)inputRaster.getDataBuffer()).getData();
/*     */ 
/*     */     
/* 274 */     this.stream.write(0);
/* 275 */     this.stream.write(0);
/* 276 */     this.stream.write(intToMultiByte(w));
/* 277 */     this.stream.write(intToMultiByte(h));
/*     */ 
/*     */     
/* 280 */     if (!isWhiteZero && lineStride == bytesPerRow) {
/*     */       
/* 282 */       this.stream.write(bdata, 0, h * bytesPerRow);
/* 283 */       processImageProgress(100.0F);
/*     */     } else {
/*     */       
/* 286 */       int offset = 0;
/* 287 */       if (!isWhiteZero) {
/*     */         
/* 289 */         for (int row = 0; row < h && 
/* 290 */           !abortRequested(); row++) {
/*     */           
/* 292 */           this.stream.write(bdata, offset, bytesPerRow);
/* 293 */           offset += lineStride;
/* 294 */           processImageProgress(100.0F * row / h);
/*     */         } 
/*     */       } else {
/*     */         
/* 298 */         byte[] inverted = new byte[bytesPerRow];
/* 299 */         for (int row = 0; row < h && 
/* 300 */           !abortRequested(); row++) {
/*     */           
/* 302 */           for (int col = 0; col < bytesPerRow; col++) {
/* 303 */             inverted[col] = (byte)(bdata[col + offset] ^ 0xFFFFFFFF);
/*     */           }
/* 305 */           this.stream.write(inverted, 0, bytesPerRow);
/* 306 */           offset += lineStride;
/* 307 */           processImageProgress(100.0F * row / h);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     if (abortRequested()) {
/* 313 */       processWriteAborted();
/*     */     } else {
/* 315 */       processImageComplete();
/* 316 */       this.stream.flushBefore(this.stream.getStreamPosition());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/* 321 */     super.reset();
/* 322 */     this.stream = null;
/*     */   }
/*     */   
/*     */   private void checkSampleModel(SampleModel sm) {
/* 326 */     int type = sm.getDataType();
/* 327 */     if (type < 0 || type > 3 || sm
/* 328 */       .getNumBands() != 1 || sm.getSampleSize(0) != 1)
/* 329 */       throw new IllegalArgumentException(I18N.getString("WBMPImageWriter2")); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */