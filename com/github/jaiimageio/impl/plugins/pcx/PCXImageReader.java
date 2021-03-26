/*     */ package com.github.jaiimageio.impl.plugins.pcx;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PCXImageReader
/*     */   extends ImageReader
/*     */   implements PCXConstants
/*     */ {
/*     */   private ImageInputStream iis;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean gotHeader = false;
/*     */   private byte manufacturer;
/*     */   private byte encoding;
/*     */   private short xmax;
/*     */   private short ymax;
/*  66 */   private byte[] smallPalette = new byte[48];
/*  67 */   private byte[] largePalette = new byte[768];
/*     */   
/*     */   private byte colorPlanes;
/*     */   
/*     */   private short bytesPerLine;
/*     */   
/*     */   private short paletteType;
/*     */   
/*     */   private PCXMetadata metadata;
/*     */   
/*     */   private SampleModel sampleModel;
/*     */   
/*     */   private SampleModel originalSampleModel;
/*     */   
/*     */   private ColorModel colorModel;
/*     */   
/*     */   private ColorModel originalColorModel;
/*     */   
/*     */   private Rectangle destinationRegion;
/*     */   
/*     */   private Rectangle sourceRegion;
/*     */   
/*     */   private BufferedImage bi;
/*     */   
/*     */   private boolean noTransform = true;
/*     */   
/*     */   private boolean seleBand = false;
/*     */   
/*     */   private int scaleX;
/*     */   private int scaleY;
/*     */   private int[] sourceBands;
/*     */   private int[] destBands;
/*     */   
/*     */   public PCXImageReader(PCXImageReaderSpi imageReaderSpi) {
/* 101 */     super(imageReaderSpi);
/*     */   }
/*     */   
/*     */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/* 105 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/* 106 */     this.iis = (ImageInputStream)input;
/* 107 */     if (this.iis != null)
/* 108 */       this.iis.setByteOrder(ByteOrder.LITTLE_ENDIAN); 
/* 109 */     this.gotHeader = false;
/*     */   }
/*     */   
/*     */   public int getHeight(int imageIndex) throws IOException {
/* 113 */     checkIndex(imageIndex);
/* 114 */     readHeader();
/* 115 */     return this.height;
/*     */   }
/*     */   
/*     */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/* 119 */     checkIndex(imageIndex);
/* 120 */     readHeader();
/* 121 */     return this.metadata;
/*     */   }
/*     */   
/*     */   public Iterator getImageTypes(int imageIndex) throws IOException {
/* 125 */     checkIndex(imageIndex);
/* 126 */     readHeader();
/* 127 */     return Collections.<ImageTypeSpecifier>singletonList(new ImageTypeSpecifier(this.originalColorModel, this.originalSampleModel)).iterator();
/*     */   }
/*     */   
/*     */   public int getNumImages(boolean allowSearch) throws IOException {
/* 131 */     if (this.iis == null) {
/* 132 */       throw new IllegalStateException("input is null");
/*     */     }
/* 134 */     if (this.seekForwardOnly && allowSearch) {
/* 135 */       throw new IllegalStateException("cannot search with forward only input");
/*     */     }
/* 137 */     return 1;
/*     */   }
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public int getWidth(int imageIndex) throws IOException {
/* 145 */     checkIndex(imageIndex);
/* 146 */     readHeader();
/* 147 */     return this.width;
/*     */   }
/*     */   
/*     */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/* 151 */     checkIndex(imageIndex);
/* 152 */     readHeader();
/*     */     
/* 154 */     if (this.iis == null) {
/* 155 */       throw new IllegalStateException("input is null");
/*     */     }
/*     */ 
/*     */     
/* 159 */     clearAbortRequest();
/* 160 */     processImageStarted(imageIndex);
/*     */     
/* 162 */     if (param == null) {
/* 163 */       param = getDefaultReadParam();
/*     */     }
/* 165 */     this.sourceRegion = new Rectangle(0, 0, 0, 0);
/* 166 */     this.destinationRegion = new Rectangle(0, 0, 0, 0);
/*     */     
/* 168 */     computeRegions(param, this.width, this.height, param.getDestination(), this.sourceRegion, this.destinationRegion);
/*     */     
/* 170 */     this.scaleX = param.getSourceXSubsampling();
/* 171 */     this.scaleY = param.getSourceYSubsampling();
/*     */ 
/*     */     
/* 174 */     this.sourceBands = param.getSourceBands();
/* 175 */     this.destBands = param.getDestinationBands();
/*     */     
/* 177 */     this.seleBand = (this.sourceBands != null && this.destBands != null);
/* 178 */     this.noTransform = (this.destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) || this.seleBand);
/*     */     
/* 180 */     if (!this.seleBand) {
/* 181 */       this.sourceBands = new int[this.colorPlanes];
/* 182 */       this.destBands = new int[this.colorPlanes];
/* 183 */       for (int i = 0; i < this.colorPlanes; i++) {
/* 184 */         this.sourceBands[i] = i; this.destBands[i] = i;
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     this.bi = param.getDestination();
/*     */ 
/*     */     
/* 191 */     WritableRaster raster = null;
/*     */     
/* 193 */     if (this.bi == null) {
/* 194 */       if (this.sampleModel != null && this.colorModel != null) {
/* 195 */         this.sampleModel = this.sampleModel.createCompatibleSampleModel(this.destinationRegion.width + this.destinationRegion.x, this.destinationRegion.height + this.destinationRegion.y);
/*     */         
/* 197 */         if (this.seleBand)
/* 198 */           this.sampleModel = this.sampleModel.createSubsetSampleModel(this.sourceBands); 
/* 199 */         raster = Raster.createWritableRaster(this.sampleModel, new Point(0, 0));
/* 200 */         this.bi = new BufferedImage(this.colorModel, raster, false, null);
/*     */       } 
/*     */     } else {
/* 203 */       raster = this.bi.getWritableTile(0, 0);
/* 204 */       this.sampleModel = this.bi.getSampleModel();
/* 205 */       this.colorModel = this.bi.getColorModel();
/*     */       
/* 207 */       this.noTransform &= this.destinationRegion.equals(raster.getBounds());
/*     */     } 
/*     */     
/* 210 */     byte[] bdata = null;
/*     */     
/* 212 */     if (this.sampleModel.getDataType() == 0) {
/* 213 */       bdata = ((DataBufferByte)raster.getDataBuffer()).getData();
/*     */     }
/* 215 */     readImage(bdata);
/*     */     
/* 217 */     if (abortRequested()) {
/* 218 */       processReadAborted();
/*     */     } else {
/* 220 */       processImageComplete();
/*     */     } 
/* 222 */     return this.bi;
/*     */   }
/*     */ 
/*     */   
/*     */   private void readImage(byte[] data) throws IOException {
/* 227 */     byte[] scanline = new byte[this.bytesPerLine * this.colorPlanes];
/*     */     
/* 229 */     if (this.noTransform) {
/*     */       try {
/* 231 */         int offset = 0;
/* 232 */         int nbytes = (this.width * this.metadata.bitsPerPixel + 8 - this.metadata.bitsPerPixel) / 8;
/* 233 */         for (int line = 0; line < this.height; line++) {
/* 234 */           readScanLine(scanline);
/* 235 */           for (int band = 0; band < this.colorPlanes; band++) {
/* 236 */             System.arraycopy(scanline, this.bytesPerLine * band, data, offset, nbytes);
/* 237 */             offset += nbytes;
/*     */           } 
/* 239 */           processImageProgress(100.0F * line / this.height);
/*     */         } 
/* 241 */       } catch (EOFException eOFException) {}
/*     */     
/*     */     }
/* 244 */     else if (this.metadata.bitsPerPixel == 1) {
/* 245 */       read1Bit(data);
/* 246 */     } else if (this.metadata.bitsPerPixel == 4) {
/* 247 */       read4Bit(data);
/*     */     } else {
/* 249 */       read8Bit(data);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void read1Bit(byte[] data) throws IOException {
/* 254 */     byte[] scanline = new byte[this.bytesPerLine];
/*     */ 
/*     */     
/*     */     try {
/* 258 */       for (int line = 0; line < this.sourceRegion.y; line++) {
/* 259 */         readScanLine(scanline);
/*     */       }
/*     */       
/* 262 */       int lineStride = ((MultiPixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*     */ 
/*     */       
/* 265 */       int[] srcOff = new int[this.destinationRegion.width];
/* 266 */       int[] destOff = new int[this.destinationRegion.width];
/* 267 */       int[] srcPos = new int[this.destinationRegion.width];
/* 268 */       int[] destPos = new int[this.destinationRegion.width];
/*     */       int x;
/* 270 */       for (int i = this.destinationRegion.x, j = 0; i < this.destinationRegion.x + this.destinationRegion.width; i++, j++, x += this.scaleX) {
/* 271 */         srcPos[j] = x >> 3;
/* 272 */         srcOff[j] = 7 - (x & 0x7);
/* 273 */         destPos[j] = i >> 3;
/* 274 */         destOff[j] = 7 - (i & 0x7);
/*     */       } 
/*     */       
/* 277 */       int k = this.destinationRegion.y * lineStride;
/*     */       
/* 279 */       for (int m = 0; m < this.sourceRegion.height; m++) {
/* 280 */         readScanLine(scanline);
/* 281 */         if (m % this.scaleY == 0) {
/* 282 */           for (int n = 0; n < this.destinationRegion.width; n++) {
/*     */             
/* 284 */             int v = scanline[srcPos[n]] >> srcOff[n] & 0x1;
/* 285 */             data[k + destPos[n]] = (byte)(data[k + destPos[n]] | v << destOff[n]);
/*     */           } 
/* 287 */           k += lineStride;
/*     */         } 
/* 289 */         processImageProgress(100.0F * m / this.sourceRegion.height);
/*     */       } 
/* 291 */     } catch (EOFException eOFException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private void read4Bit(byte[] data) throws IOException {
/* 296 */     byte[] scanline = new byte[this.bytesPerLine];
/*     */     
/*     */     try {
/* 299 */       int lineStride = ((MultiPixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*     */ 
/*     */       
/* 302 */       int[] srcOff = new int[this.destinationRegion.width];
/* 303 */       int[] destOff = new int[this.destinationRegion.width];
/* 304 */       int[] srcPos = new int[this.destinationRegion.width];
/* 305 */       int[] destPos = new int[this.destinationRegion.width];
/*     */       
/* 307 */       int i = this.destinationRegion.x, x = this.sourceRegion.x, j = 0;
/* 308 */       for (; i < this.destinationRegion.x + this.destinationRegion.width; 
/* 309 */         i++, j++, x += this.scaleX) {
/* 310 */         srcPos[j] = x >> 1;
/* 311 */         srcOff[j] = 1 - (x & 0x1) << 2;
/* 312 */         destPos[j] = i >> 1;
/* 313 */         destOff[j] = 1 - (i & 0x1) << 2;
/*     */       } 
/*     */       
/* 316 */       int k = this.destinationRegion.y * lineStride;
/*     */       
/* 318 */       for (int line = 0; line < this.sourceRegion.height; line++) {
/* 319 */         readScanLine(scanline);
/*     */         
/* 321 */         if (abortRequested())
/*     */           break; 
/* 323 */         if (line % this.scaleY == 0) {
/* 324 */           for (int m = 0; m < this.destinationRegion.width; m++) {
/*     */             
/* 326 */             int v = scanline[srcPos[m]] >> srcOff[m] & 0xF;
/* 327 */             data[k + destPos[m]] = (byte)(data[k + destPos[m]] | v << destOff[m]);
/*     */           } 
/* 329 */           k += lineStride;
/*     */         } 
/* 331 */         processImageProgress(100.0F * line / this.sourceRegion.height);
/*     */       } 
/* 333 */     } catch (EOFException eOFException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void read8Bit(byte[] data) throws IOException {
/* 339 */     byte[] scanline = new byte[this.colorPlanes * this.bytesPerLine];
/*     */     
/*     */     try {
/* 342 */       for (int line = 0; line < this.sourceRegion.y; line++) {
/* 343 */         readScanLine(scanline);
/*     */       }
/* 345 */       int dstOffset = this.destinationRegion.y * (this.destinationRegion.x + this.destinationRegion.width) * this.colorPlanes;
/* 346 */       for (int i = 0; i < this.sourceRegion.height; i++) {
/* 347 */         readScanLine(scanline);
/* 348 */         if (i % this.scaleY == 0) {
/* 349 */           int srcOffset = this.sourceRegion.x;
/* 350 */           for (int band = 0; band < this.colorPlanes; band++) {
/* 351 */             dstOffset += this.destinationRegion.x; int x;
/* 352 */             for (x = 0; x < this.destinationRegion.width; x += this.scaleX) {
/* 353 */               data[dstOffset++] = scanline[srcOffset + x];
/*     */             }
/* 355 */             srcOffset += this.bytesPerLine;
/*     */           } 
/*     */         } 
/* 358 */         processImageProgress(100.0F * i / this.sourceRegion.height);
/*     */       } 
/* 360 */     } catch (EOFException eOFException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private void readScanLine(byte[] buffer) throws IOException {
/* 365 */     int max = this.bytesPerLine * this.colorPlanes;
/* 366 */     for (int j = 0; j < max; ) {
/* 367 */       int val = this.iis.readUnsignedByte();
/*     */       
/* 369 */       if ((val & 0xC0) == 192) {
/* 370 */         int count = val & 0xFFFFFF3F;
/* 371 */         val = this.iis.readUnsignedByte();
/* 372 */         for (int k = 0; k < count && j < max; k++)
/* 373 */           buffer[j++] = (byte)(val & 0xFF); 
/*     */         continue;
/*     */       } 
/* 376 */       buffer[j++] = (byte)(val & 0xFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkIndex(int imageIndex) {
/* 382 */     if (imageIndex != 0) {
/* 383 */       throw new IndexOutOfBoundsException("only one image exists in the stream");
/*     */     }
/*     */   }
/*     */   
/*     */   private void readHeader() throws IOException {
/* 388 */     if (this.gotHeader) {
/* 389 */       this.iis.seek(128L);
/*     */       
/*     */       return;
/*     */     } 
/* 393 */     this.metadata = new PCXMetadata();
/*     */     
/* 395 */     this.manufacturer = this.iis.readByte();
/* 396 */     if (this.manufacturer != 10)
/* 397 */       throw new IllegalStateException("image is not a PCX file"); 
/* 398 */     this.metadata.version = (short)this.iis.readByte();
/* 399 */     this.encoding = this.iis.readByte();
/* 400 */     if (this.encoding != 1) {
/* 401 */       throw new IllegalStateException("image is not a PCX file, invalid encoding " + this.encoding);
/*     */     }
/* 403 */     this.metadata.bitsPerPixel = this.iis.readByte();
/*     */     
/* 405 */     this.metadata.xmin = this.iis.readShort();
/* 406 */     this.metadata.ymin = this.iis.readShort();
/* 407 */     this.xmax = this.iis.readShort();
/* 408 */     this.ymax = this.iis.readShort();
/*     */     
/* 410 */     this.metadata.hdpi = this.iis.readShort();
/* 411 */     this.metadata.vdpi = this.iis.readShort();
/*     */     
/* 413 */     this.iis.readFully(this.smallPalette);
/*     */     
/* 415 */     this.iis.readByte();
/*     */     
/* 417 */     this.colorPlanes = this.iis.readByte();
/* 418 */     this.bytesPerLine = this.iis.readShort();
/* 419 */     this.paletteType = this.iis.readShort();
/*     */     
/* 421 */     this.metadata.hsize = this.iis.readShort();
/* 422 */     this.metadata.vsize = this.iis.readShort();
/*     */     
/* 424 */     this.iis.skipBytes(54);
/*     */     
/* 426 */     this.width = this.xmax - this.metadata.xmin + 1;
/* 427 */     this.height = this.ymax - this.metadata.ymin + 1;
/*     */     
/* 429 */     if (this.colorPlanes == 1) {
/* 430 */       if (this.paletteType == 2) {
/* 431 */         ColorSpace cs = ColorSpace.getInstance(1003);
/* 432 */         int[] nBits = { 8 };
/* 433 */         this.colorModel = new ComponentColorModel(cs, nBits, false, false, 1, 0);
/* 434 */         this.sampleModel = new ComponentSampleModel(0, this.width, this.height, 1, this.width, new int[] { 0 });
/*     */       }
/* 436 */       else if (this.metadata.bitsPerPixel == 8) {
/*     */         
/* 438 */         this.iis.mark();
/*     */         
/* 440 */         if (this.iis.length() == -1L) {
/*     */           
/* 442 */           while (this.iis.read() != -1);
/*     */           
/* 444 */           this.iis.seek(this.iis.getStreamPosition() - 768L - 1L);
/*     */         } else {
/* 446 */           this.iis.seek(this.iis.length() - 768L - 1L);
/*     */         } 
/*     */         
/* 449 */         int palletteMagic = this.iis.read();
/* 450 */         if (palletteMagic != 12) {
/* 451 */           processWarningOccurred("Expected palette magic number 12; instead read " + palletteMagic + " from this image.");
/*     */         }
/*     */         
/* 454 */         this.iis.readFully(this.largePalette);
/* 455 */         this.iis.reset();
/*     */         
/* 457 */         this.colorModel = new IndexColorModel(this.metadata.bitsPerPixel, 256, this.largePalette, 0, false);
/* 458 */         this.sampleModel = this.colorModel.createCompatibleSampleModel(this.width, this.height);
/*     */       } else {
/* 460 */         int msize = (this.metadata.bitsPerPixel == 1) ? 2 : 16;
/* 461 */         this.colorModel = new IndexColorModel(this.metadata.bitsPerPixel, msize, this.smallPalette, 0, false);
/* 462 */         this.sampleModel = this.colorModel.createCompatibleSampleModel(this.width, this.height);
/*     */       } 
/*     */     } else {
/*     */       
/* 466 */       ColorSpace cs = ColorSpace.getInstance(1000);
/* 467 */       int[] nBits = { 8, 8, 8 };
/* 468 */       this.colorModel = new ComponentColorModel(cs, nBits, false, false, 1, 0);
/* 469 */       this.sampleModel = new ComponentSampleModel(0, this.width, this.height, 1, this.width * this.colorPlanes, new int[] { 0, this.width, this.width * 2 });
/*     */     } 
/*     */     
/* 472 */     this.originalSampleModel = this.sampleModel;
/* 473 */     this.originalColorModel = this.colorModel;
/*     */     
/* 475 */     this.gotHeader = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pcx/PCXImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */