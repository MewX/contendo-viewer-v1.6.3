/*     */ package com.github.jaiimageio.impl.plugins.pcx;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteOrder;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOMetadata;
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
/*     */ public class PCXImageWriter
/*     */   extends ImageWriter
/*     */   implements PCXConstants
/*     */ {
/*     */   private ImageOutputStream ios;
/*     */   private Rectangle sourceRegion;
/*     */   private Rectangle destinationRegion;
/*     */   private int colorPlanes;
/*     */   private int bytesPerLine;
/*  65 */   private Raster inputRaster = null;
/*     */   private int scaleX;
/*     */   
/*     */   public PCXImageWriter(PCXImageWriterSpi imageWriterSpi) {
/*  69 */     super(imageWriterSpi);
/*     */   }
/*     */   private int scaleY;
/*     */   public void setOutput(Object output) {
/*  73 */     super.setOutput(output);
/*  74 */     if (output != null) {
/*  75 */       if (!(output instanceof ImageOutputStream))
/*  76 */         throw new IllegalArgumentException("output not instance of ImageOutputStream"); 
/*  77 */       this.ios = (ImageOutputStream)output;
/*  78 */       this.ios.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*     */     } else {
/*  80 */       this.ios = null;
/*     */     } 
/*     */   }
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  84 */     if (inData instanceof PCXMetadata)
/*  85 */       return inData; 
/*  86 */     return null;
/*     */   }
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/*  90 */     return null;
/*     */   }
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  94 */     PCXMetadata md = new PCXMetadata();
/*  95 */     md.bitsPerPixel = (byte)imageType.getSampleModel().getSampleSize()[0];
/*  96 */     return md;
/*     */   }
/*     */   
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/* 104 */     if (this.ios == null) {
/* 105 */       throw new IllegalStateException("output stream is null");
/*     */     }
/*     */     
/* 108 */     if (image == null) {
/* 109 */       throw new IllegalArgumentException("image is null");
/*     */     }
/*     */     
/* 112 */     clearAbortRequest();
/* 113 */     processImageStarted(0);
/* 114 */     if (param == null) {
/* 115 */       param = getDefaultWriteParam();
/*     */     }
/* 117 */     boolean writeRaster = image.hasRaster();
/*     */     
/* 119 */     this.sourceRegion = param.getSourceRegion();
/*     */     
/* 121 */     SampleModel sampleModel = null;
/* 122 */     ColorModel colorModel = null;
/*     */     
/* 124 */     if (writeRaster)
/* 125 */     { this.inputRaster = image.getRaster();
/* 126 */       sampleModel = this.inputRaster.getSampleModel();
/* 127 */       colorModel = ImageUtil.createColorModel(null, sampleModel);
/* 128 */       if (this.sourceRegion == null) {
/* 129 */         this.sourceRegion = this.inputRaster.getBounds();
/*     */       } else {
/* 131 */         this.sourceRegion = this.sourceRegion.intersection(this.inputRaster.getBounds());
/*     */       }  }
/* 133 */     else { RenderedImage input = image.getRenderedImage();
/* 134 */       this.inputRaster = input.getData();
/* 135 */       sampleModel = input.getSampleModel();
/* 136 */       colorModel = input.getColorModel();
/*     */       
/* 138 */       Rectangle rect = new Rectangle(input.getMinX(), input.getMinY(), input.getWidth(), input.getHeight());
/* 139 */       if (this.sourceRegion == null) {
/* 140 */         this.sourceRegion = rect;
/*     */       } else {
/* 142 */         this.sourceRegion = this.sourceRegion.intersection(rect);
/*     */       }  }
/*     */     
/* 145 */     if (this.sourceRegion.isEmpty()) {
/* 146 */       throw new IllegalArgumentException("source region is empty");
/*     */     }
/* 148 */     IIOMetadata imageMetadata = image.getMetadata();
/* 149 */     PCXMetadata pcxImageMetadata = null;
/*     */     
/* 151 */     ImageTypeSpecifier imageType = new ImageTypeSpecifier(colorModel, sampleModel);
/* 152 */     if (imageMetadata != null) {
/*     */       
/* 154 */       pcxImageMetadata = (PCXMetadata)convertImageMetadata(imageMetadata, imageType, param);
/*     */     } else {
/*     */       
/* 157 */       pcxImageMetadata = (PCXMetadata)getDefaultImageMetadata(imageType, param);
/*     */     } 
/*     */     
/* 160 */     this.scaleX = param.getSourceXSubsampling();
/* 161 */     this.scaleY = param.getSourceYSubsampling();
/*     */     
/* 163 */     int xOffset = param.getSubsamplingXOffset();
/* 164 */     int yOffset = param.getSubsamplingYOffset();
/*     */ 
/*     */     
/* 167 */     int dataType = sampleModel.getDataType();
/*     */     
/* 169 */     this.sourceRegion.translate(xOffset, yOffset);
/* 170 */     this.sourceRegion.width -= xOffset;
/* 171 */     this.sourceRegion.height -= yOffset;
/*     */     
/* 173 */     int minX = this.sourceRegion.x / this.scaleX;
/* 174 */     int minY = this.sourceRegion.y / this.scaleY;
/* 175 */     int w = (this.sourceRegion.width + this.scaleX - 1) / this.scaleX;
/* 176 */     int h = (this.sourceRegion.height + this.scaleY - 1) / this.scaleY;
/*     */     
/* 178 */     xOffset = this.sourceRegion.x % this.scaleX;
/* 179 */     yOffset = this.sourceRegion.y % this.scaleY;
/*     */     
/* 181 */     this.destinationRegion = new Rectangle(minX, minY, w, h);
/*     */     
/* 183 */     boolean noTransform = this.destinationRegion.equals(this.sourceRegion);
/*     */ 
/*     */     
/* 186 */     int[] sourceBands = param.getSourceBands();
/* 187 */     boolean noSubband = true;
/* 188 */     int numBands = sampleModel.getNumBands();
/*     */     
/* 190 */     if (sourceBands != null) {
/* 191 */       sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
/* 192 */       colorModel = null;
/* 193 */       noSubband = false;
/* 194 */       numBands = sampleModel.getNumBands();
/*     */     } else {
/* 196 */       sourceBands = new int[numBands];
/* 197 */       for (int j = 0; j < numBands; j++) {
/* 198 */         sourceBands[j] = j;
/*     */       }
/*     */     } 
/* 201 */     this.ios.writeByte(10);
/* 202 */     this.ios.writeByte(5);
/* 203 */     this.ios.writeByte(1);
/*     */     
/* 205 */     int bitsPerPixel = sampleModel.getSampleSize(0);
/* 206 */     this.ios.writeByte(bitsPerPixel);
/*     */     
/* 208 */     this.ios.writeShort(this.destinationRegion.x);
/* 209 */     this.ios.writeShort(this.destinationRegion.y);
/* 210 */     this.ios.writeShort(this.destinationRegion.x + this.destinationRegion.width - 1);
/* 211 */     this.ios.writeShort(this.destinationRegion.y + this.destinationRegion.height - 1);
/*     */     
/* 213 */     this.ios.writeShort(pcxImageMetadata.hdpi);
/* 214 */     this.ios.writeShort(pcxImageMetadata.vdpi);
/*     */     
/* 216 */     byte[] smallpalette = createSmallPalette(colorModel);
/* 217 */     this.ios.write(smallpalette);
/* 218 */     this.ios.writeByte(0);
/*     */     
/* 220 */     this.colorPlanes = sampleModel.getNumBands();
/*     */     
/* 222 */     this.ios.writeByte(this.colorPlanes);
/*     */     
/* 224 */     this.bytesPerLine = this.destinationRegion.width * bitsPerPixel / 8;
/* 225 */     this.bytesPerLine += this.bytesPerLine % 2;
/*     */     
/* 227 */     this.ios.writeShort(this.bytesPerLine);
/*     */     
/* 229 */     if (colorModel.getColorSpace().getType() == 6) {
/* 230 */       this.ios.writeShort(2);
/*     */     } else {
/* 232 */       this.ios.writeShort(1);
/*     */     } 
/* 234 */     this.ios.writeShort(pcxImageMetadata.hsize);
/* 235 */     this.ios.writeShort(pcxImageMetadata.vsize);
/*     */     
/* 237 */     for (int i = 0; i < 54; i++) {
/* 238 */       this.ios.writeByte(0);
/*     */     }
/*     */ 
/*     */     
/* 242 */     if (this.colorPlanes == 1 && bitsPerPixel == 1) {
/* 243 */       write1Bit();
/*     */     }
/* 245 */     else if (this.colorPlanes == 1 && bitsPerPixel == 4) {
/* 246 */       write4Bit();
/*     */     } else {
/*     */       
/* 249 */       write8Bit();
/*     */     } 
/*     */ 
/*     */     
/* 253 */     if (this.colorPlanes == 1 && bitsPerPixel == 8 && colorModel
/* 254 */       .getColorSpace().getType() != 6) {
/* 255 */       this.ios.writeByte(12);
/* 256 */       this.ios.write(createLargePalette(colorModel));
/*     */     } 
/*     */     
/* 259 */     if (abortRequested()) {
/* 260 */       processWriteAborted();
/*     */     } else {
/* 262 */       processImageComplete();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void write4Bit() throws IOException {
/* 267 */     int[] unpacked = new int[this.sourceRegion.width];
/* 268 */     int[] samples = new int[this.bytesPerLine];
/*     */     
/* 270 */     for (int line = 0; line < this.sourceRegion.height; line += this.scaleY) {
/* 271 */       this.inputRaster.getSamples(this.sourceRegion.x, line + this.sourceRegion.y, this.sourceRegion.width, 1, 0, unpacked);
/*     */       
/* 273 */       int val = 0, dst = 0; int x, nibble;
/* 274 */       for (x = 0, nibble = 0; x < this.sourceRegion.width; x += this.scaleX) {
/* 275 */         val |= unpacked[x] & 0xF;
/* 276 */         if (nibble == 1) {
/* 277 */           samples[dst++] = val;
/* 278 */           nibble = 0;
/* 279 */           val = 0;
/*     */         } else {
/* 281 */           nibble = 1;
/* 282 */           val <<= 4;
/*     */         } 
/*     */       } 
/*     */       
/* 286 */       int last = samples[0];
/* 287 */       int count = 0;
/*     */       int i;
/* 289 */       for (i = 0; i < this.bytesPerLine; i += this.scaleX) {
/* 290 */         int sample = samples[i];
/* 291 */         if (sample != last || count == 63) {
/* 292 */           writeRLE(last, count);
/* 293 */           count = 1;
/* 294 */           last = sample;
/*     */         } else {
/* 296 */           count++;
/*     */         } 
/* 298 */       }  if (count >= 1) {
/* 299 */         writeRLE(last, count);
/*     */       }
/*     */       
/* 302 */       processImageProgress(100.0F * line / this.sourceRegion.height);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void write1Bit() throws IOException {
/* 307 */     int[] unpacked = new int[this.sourceRegion.width];
/* 308 */     int[] samples = new int[this.bytesPerLine];
/*     */     
/* 310 */     for (int line = 0; line < this.sourceRegion.height; line += this.scaleY) {
/* 311 */       this.inputRaster.getSamples(this.sourceRegion.x, line + this.sourceRegion.y, this.sourceRegion.width, 1, 0, unpacked);
/*     */       
/* 313 */       int val = 0, dst = 0; int x, bit;
/* 314 */       for (x = 0, bit = 128; x < this.sourceRegion.width; x += this.scaleX) {
/* 315 */         if (unpacked[x] > 0)
/* 316 */           val |= bit; 
/* 317 */         if (bit == 1) {
/* 318 */           samples[dst++] = val;
/* 319 */           bit = 128;
/* 320 */           val = 0;
/*     */         } else {
/* 322 */           bit >>= 1;
/*     */         } 
/*     */       } 
/*     */       
/* 326 */       int last = samples[0];
/* 327 */       int count = 0;
/*     */       int i;
/* 329 */       for (i = 0; i < this.bytesPerLine; i += this.scaleX) {
/* 330 */         int sample = samples[i];
/* 331 */         if (sample != last || count == 63) {
/* 332 */           writeRLE(last, count);
/* 333 */           count = 1;
/* 334 */           last = sample;
/*     */         } else {
/* 336 */           count++;
/*     */         } 
/* 338 */       }  if (count >= 1) {
/* 339 */         writeRLE(last, count);
/*     */       }
/*     */       
/* 342 */       processImageProgress(100.0F * line / this.sourceRegion.height);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void write8Bit() throws IOException {
/* 347 */     int[][] samples = new int[this.colorPlanes][this.bytesPerLine];
/*     */     
/* 349 */     for (int line = 0; line < this.sourceRegion.height; line += this.scaleY) {
/* 350 */       for (int band = 0; band < this.colorPlanes; band++) {
/* 351 */         this.inputRaster.getSamples(this.sourceRegion.x, line + this.sourceRegion.y, this.sourceRegion.width, 1, band, samples[band]);
/*     */       }
/*     */       
/* 354 */       int last = samples[0][0];
/* 355 */       int count = 0;
/*     */       
/* 357 */       for (int i = 0; i < this.colorPlanes; i++) {
/* 358 */         int x; for (x = 0; x < this.bytesPerLine; x += this.scaleX) {
/* 359 */           int sample = samples[i][x];
/* 360 */           if (sample != last || count == 63) {
/* 361 */             writeRLE(last, count);
/* 362 */             count = 1;
/* 363 */             last = sample;
/*     */           } else {
/* 365 */             count++;
/*     */           } 
/*     */         } 
/* 368 */       }  if (count >= 1) {
/* 369 */         writeRLE(last, count);
/*     */       }
/*     */       
/* 372 */       processImageProgress(100.0F * line / this.sourceRegion.height);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeRLE(int val, int count) throws IOException {
/* 377 */     if (count == 1 && (val & 0xC0) != 192) {
/* 378 */       this.ios.writeByte(val);
/*     */     } else {
/* 380 */       this.ios.writeByte(0xC0 | count);
/* 381 */       this.ios.writeByte(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   private byte[] createSmallPalette(ColorModel cm) {
/* 386 */     byte[] palette = new byte[48];
/*     */     
/* 388 */     if (!(cm instanceof IndexColorModel)) {
/* 389 */       return palette;
/*     */     }
/* 391 */     IndexColorModel icm = (IndexColorModel)cm;
/* 392 */     if (icm.getMapSize() > 16) {
/* 393 */       return palette;
/*     */     }
/* 395 */     for (int i = 0, offset = 0; i < icm.getMapSize(); i++) {
/* 396 */       palette[offset++] = (byte)icm.getRed(i);
/* 397 */       palette[offset++] = (byte)icm.getGreen(i);
/* 398 */       palette[offset++] = (byte)icm.getBlue(i);
/*     */     } 
/*     */     
/* 401 */     return palette;
/*     */   }
/*     */   private byte[] createLargePalette(ColorModel cm) {
/* 404 */     byte[] palette = new byte[768];
/*     */     
/* 406 */     if (!(cm instanceof IndexColorModel)) {
/* 407 */       return palette;
/*     */     }
/* 409 */     IndexColorModel icm = (IndexColorModel)cm;
/*     */     
/* 411 */     for (int i = 0, offset = 0; i < icm.getMapSize(); i++) {
/* 412 */       palette[offset++] = (byte)icm.getRed(i);
/* 413 */       palette[offset++] = (byte)icm.getGreen(i);
/* 414 */       palette[offset++] = (byte)icm.getBlue(i);
/*     */     } 
/*     */     
/* 417 */     return palette;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pcx/PCXImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */