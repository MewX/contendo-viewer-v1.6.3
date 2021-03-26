/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFYCbCrDecompressor
/*     */   extends TIFFDecompressor
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final int FRAC_BITS = 16;
/*     */   private static final float FRAC_SCALE = 65536.0F;
/*  72 */   private float LumaRed = 0.299F;
/*  73 */   private float LumaGreen = 0.587F;
/*  74 */   private float LumaBlue = 0.114F;
/*     */   
/*  76 */   private float referenceBlackY = 0.0F;
/*  77 */   private float referenceWhiteY = 255.0F;
/*     */   
/*  79 */   private float referenceBlackCb = 128.0F;
/*  80 */   private float referenceWhiteCb = 255.0F;
/*     */   
/*  82 */   private float referenceBlackCr = 128.0F;
/*  83 */   private float referenceWhiteCr = 255.0F;
/*     */   
/*  85 */   private float codingRangeY = 255.0F;
/*     */   
/*  87 */   private int[] iYTab = new int[256];
/*  88 */   private int[] iCbTab = new int[256];
/*  89 */   private int[] iCrTab = new int[256];
/*     */   
/*  91 */   private int[] iGYTab = new int[256];
/*  92 */   private int[] iGCbTab = new int[256];
/*  93 */   private int[] iGCrTab = new int[256];
/*     */   
/*  95 */   private int chromaSubsampleH = 2;
/*  96 */   private int chromaSubsampleV = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean colorConvert;
/*     */ 
/*     */ 
/*     */   
/*     */   private TIFFDecompressor decompressor;
/*     */ 
/*     */   
/*     */   private BufferedImage tmpImage;
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFYCbCrDecompressor(TIFFDecompressor decompressor, boolean colorConvert) {
/* 112 */     this.decompressor = decompressor;
/* 113 */     this.colorConvert = colorConvert;
/*     */   }
/*     */   
/*     */   private void warning(String message) {
/* 117 */     if (this.reader instanceof TIFFImageReader) {
/* 118 */       ((TIFFImageReader)this.reader).forwardWarningMessage(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReader(ImageReader reader) {
/* 127 */     if (this.decompressor != null) {
/* 128 */       this.decompressor.setReader(reader);
/*     */     }
/* 130 */     super.setReader(reader);
/*     */   }
/*     */   
/*     */   public void setMetadata(IIOMetadata metadata) {
/* 134 */     if (this.decompressor != null) {
/* 135 */       this.decompressor.setMetadata(metadata);
/*     */     }
/* 137 */     super.setMetadata(metadata);
/*     */   }
/*     */   
/*     */   public void setPhotometricInterpretation(int photometricInterpretation) {
/* 141 */     if (this.decompressor != null) {
/* 142 */       this.decompressor.setPhotometricInterpretation(photometricInterpretation);
/*     */     }
/* 144 */     super.setPhotometricInterpretation(photometricInterpretation);
/*     */   }
/*     */   
/*     */   public void setCompression(int compression) {
/* 148 */     if (this.decompressor != null) {
/* 149 */       this.decompressor.setCompression(compression);
/*     */     }
/* 151 */     super.setCompression(compression);
/*     */   }
/*     */   
/*     */   public void setPlanar(boolean planar) {
/* 155 */     if (this.decompressor != null) {
/* 156 */       this.decompressor.setPlanar(planar);
/*     */     }
/* 158 */     super.setPlanar(planar);
/*     */   }
/*     */   
/*     */   public void setSamplesPerPixel(int samplesPerPixel) {
/* 162 */     if (this.decompressor != null) {
/* 163 */       this.decompressor.setSamplesPerPixel(samplesPerPixel);
/*     */     }
/* 165 */     super.setSamplesPerPixel(samplesPerPixel);
/*     */   }
/*     */   
/*     */   public void setBitsPerSample(int[] bitsPerSample) {
/* 169 */     if (this.decompressor != null) {
/* 170 */       this.decompressor.setBitsPerSample(bitsPerSample);
/*     */     }
/* 172 */     super.setBitsPerSample(bitsPerSample);
/*     */   }
/*     */   
/*     */   public void setSampleFormat(int[] sampleFormat) {
/* 176 */     if (this.decompressor != null) {
/* 177 */       this.decompressor.setSampleFormat(sampleFormat);
/*     */     }
/* 179 */     super.setSampleFormat(sampleFormat);
/*     */   }
/*     */   
/*     */   public void setExtraSamples(int[] extraSamples) {
/* 183 */     if (this.decompressor != null) {
/* 184 */       this.decompressor.setExtraSamples(extraSamples);
/*     */     }
/* 186 */     super.setExtraSamples(extraSamples);
/*     */   }
/*     */   
/*     */   public void setColorMap(char[] colorMap) {
/* 190 */     if (this.decompressor != null) {
/* 191 */       this.decompressor.setColorMap(colorMap);
/*     */     }
/* 193 */     super.setColorMap(colorMap);
/*     */   }
/*     */   
/*     */   public void setStream(ImageInputStream stream) {
/* 197 */     if (this.decompressor != null) {
/* 198 */       this.decompressor.setStream(stream);
/*     */     } else {
/* 200 */       super.setStream(stream);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOffset(long offset) {
/* 205 */     if (this.decompressor != null) {
/* 206 */       this.decompressor.setOffset(offset);
/*     */     }
/* 208 */     super.setOffset(offset);
/*     */   }
/*     */   
/*     */   public void setByteCount(int byteCount) {
/* 212 */     if (this.decompressor != null) {
/* 213 */       this.decompressor.setByteCount(byteCount);
/*     */     }
/* 215 */     super.setByteCount(byteCount);
/*     */   }
/*     */   
/*     */   public void setSrcMinX(int srcMinX) {
/* 219 */     if (this.decompressor != null) {
/* 220 */       this.decompressor.setSrcMinX(srcMinX);
/*     */     }
/* 222 */     super.setSrcMinX(srcMinX);
/*     */   }
/*     */   
/*     */   public void setSrcMinY(int srcMinY) {
/* 226 */     if (this.decompressor != null) {
/* 227 */       this.decompressor.setSrcMinY(srcMinY);
/*     */     }
/* 229 */     super.setSrcMinY(srcMinY);
/*     */   }
/*     */   
/*     */   public void setSrcWidth(int srcWidth) {
/* 233 */     if (this.decompressor != null) {
/* 234 */       this.decompressor.setSrcWidth(srcWidth);
/*     */     }
/* 236 */     super.setSrcWidth(srcWidth);
/*     */   }
/*     */   
/*     */   public void setSrcHeight(int srcHeight) {
/* 240 */     if (this.decompressor != null) {
/* 241 */       this.decompressor.setSrcHeight(srcHeight);
/*     */     }
/* 243 */     super.setSrcHeight(srcHeight);
/*     */   }
/*     */   
/*     */   public void setSourceXOffset(int sourceXOffset) {
/* 247 */     if (this.decompressor != null) {
/* 248 */       this.decompressor.setSourceXOffset(sourceXOffset);
/*     */     }
/* 250 */     super.setSourceXOffset(sourceXOffset);
/*     */   }
/*     */   
/*     */   public void setDstXOffset(int dstXOffset) {
/* 254 */     if (this.decompressor != null) {
/* 255 */       this.decompressor.setDstXOffset(dstXOffset);
/*     */     }
/* 257 */     super.setDstXOffset(dstXOffset);
/*     */   }
/*     */   
/*     */   public void setSourceYOffset(int sourceYOffset) {
/* 261 */     if (this.decompressor != null) {
/* 262 */       this.decompressor.setSourceYOffset(sourceYOffset);
/*     */     }
/* 264 */     super.setSourceYOffset(sourceYOffset);
/*     */   }
/*     */   
/*     */   public void setDstYOffset(int dstYOffset) {
/* 268 */     if (this.decompressor != null) {
/* 269 */       this.decompressor.setDstYOffset(dstYOffset);
/*     */     }
/* 271 */     super.setDstYOffset(dstYOffset);
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
/*     */   public void setSourceBands(int[] sourceBands) {
/* 292 */     if (this.decompressor != null) {
/* 293 */       this.decompressor.setSourceBands(sourceBands);
/*     */     }
/* 295 */     super.setSourceBands(sourceBands);
/*     */   }
/*     */   
/*     */   public void setDestinationBands(int[] destinationBands) {
/* 299 */     if (this.decompressor != null) {
/* 300 */       this.decompressor.setDestinationBands(destinationBands);
/*     */     }
/* 302 */     super.setDestinationBands(destinationBands);
/*     */   }
/*     */   
/*     */   public void setImage(BufferedImage image) {
/* 306 */     if (this.decompressor != null) {
/* 307 */       ColorModel cm = image.getColorModel();
/* 308 */       this
/*     */ 
/*     */         
/* 311 */         .tmpImage = new BufferedImage(cm, image.getRaster().createCompatibleWritableRaster(1, 1), cm.isAlphaPremultiplied(), null);
/*     */       
/* 313 */       this.decompressor.setImage(this.tmpImage);
/*     */     } 
/* 315 */     super.setImage(image);
/*     */   }
/*     */   
/*     */   public void setDstMinX(int dstMinX) {
/* 319 */     if (this.decompressor != null) {
/* 320 */       this.decompressor.setDstMinX(dstMinX);
/*     */     }
/* 322 */     super.setDstMinX(dstMinX);
/*     */   }
/*     */   
/*     */   public void setDstMinY(int dstMinY) {
/* 326 */     if (this.decompressor != null) {
/* 327 */       this.decompressor.setDstMinY(dstMinY);
/*     */     }
/* 329 */     super.setDstMinY(dstMinY);
/*     */   }
/*     */   
/*     */   public void setDstWidth(int dstWidth) {
/* 333 */     if (this.decompressor != null) {
/* 334 */       this.decompressor.setDstWidth(dstWidth);
/*     */     }
/* 336 */     super.setDstWidth(dstWidth);
/*     */   }
/*     */   
/*     */   public void setDstHeight(int dstHeight) {
/* 340 */     if (this.decompressor != null) {
/* 341 */       this.decompressor.setDstHeight(dstHeight);
/*     */     }
/* 343 */     super.setDstHeight(dstHeight);
/*     */   }
/*     */   
/*     */   public void setActiveSrcMinX(int activeSrcMinX) {
/* 347 */     if (this.decompressor != null) {
/* 348 */       this.decompressor.setActiveSrcMinX(activeSrcMinX);
/*     */     }
/* 350 */     super.setActiveSrcMinX(activeSrcMinX);
/*     */   }
/*     */   
/*     */   public void setActiveSrcMinY(int activeSrcMinY) {
/* 354 */     if (this.decompressor != null) {
/* 355 */       this.decompressor.setActiveSrcMinY(activeSrcMinY);
/*     */     }
/* 357 */     super.setActiveSrcMinY(activeSrcMinY);
/*     */   }
/*     */   
/*     */   public void setActiveSrcWidth(int activeSrcWidth) {
/* 361 */     if (this.decompressor != null) {
/* 362 */       this.decompressor.setActiveSrcWidth(activeSrcWidth);
/*     */     }
/* 364 */     super.setActiveSrcWidth(activeSrcWidth);
/*     */   }
/*     */   
/*     */   public void setActiveSrcHeight(int activeSrcHeight) {
/* 368 */     if (this.decompressor != null) {
/* 369 */       this.decompressor.setActiveSrcHeight(activeSrcHeight);
/*     */     }
/* 371 */     super.setActiveSrcHeight(activeSrcHeight);
/*     */   }
/*     */   
/*     */   private byte clamp(int f) {
/* 375 */     if (f < 0)
/* 376 */       return 0; 
/* 377 */     if (f > 16711680) {
/* 378 */       return -1;
/*     */     }
/* 380 */     return (byte)(f >> 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void beginDecoding() {
/* 385 */     if (this.decompressor != null) {
/* 386 */       this.decompressor.beginDecoding();
/*     */     }
/*     */     
/* 389 */     TIFFImageMetadata tmetadata = (TIFFImageMetadata)this.metadata;
/*     */ 
/*     */     
/* 392 */     TIFFField f = tmetadata.getTIFFField(530);
/* 393 */     if (f != null) {
/* 394 */       if (f.getCount() == 2) {
/* 395 */         this.chromaSubsampleH = f.getAsInt(0);
/* 396 */         this.chromaSubsampleV = f.getAsInt(1);
/*     */         
/* 398 */         if (this.chromaSubsampleH != 1 && this.chromaSubsampleH != 2 && this.chromaSubsampleH != 4) {
/*     */           
/* 400 */           warning("Y_CB_CR_SUBSAMPLING[0] has illegal value " + this.chromaSubsampleH + " (should be 1, 2, or 4), setting to 1");
/*     */ 
/*     */           
/* 403 */           this.chromaSubsampleH = 1;
/*     */         } 
/*     */         
/* 406 */         if (this.chromaSubsampleV != 1 && this.chromaSubsampleV != 2 && this.chromaSubsampleV != 4) {
/*     */           
/* 408 */           warning("Y_CB_CR_SUBSAMPLING[1] has illegal value " + this.chromaSubsampleV + " (should be 1, 2, or 4), setting to 1");
/*     */ 
/*     */           
/* 411 */           this.chromaSubsampleV = 1;
/*     */         } 
/*     */       } else {
/* 414 */         warning("Y_CB_CR_SUBSAMPLING count != 2, assuming no subsampling");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 420 */     f = tmetadata.getTIFFField(529);
/* 421 */     if (f != null) {
/* 422 */       if (f.getCount() == 3) {
/* 423 */         this.LumaRed = f.getAsFloat(0);
/* 424 */         this.LumaGreen = f.getAsFloat(1);
/* 425 */         this.LumaBlue = f.getAsFloat(2);
/*     */       } else {
/* 427 */         warning("Y_CB_CR_COEFFICIENTS count != 3, assuming default values for CCIR 601-1");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 433 */     f = tmetadata.getTIFFField(532);
/* 434 */     if (f != null) {
/* 435 */       if (f.getCount() == 6) {
/* 436 */         this.referenceBlackY = f.getAsFloat(0);
/* 437 */         this.referenceWhiteY = f.getAsFloat(1);
/* 438 */         this.referenceBlackCb = f.getAsFloat(2);
/* 439 */         this.referenceWhiteCb = f.getAsFloat(3);
/* 440 */         this.referenceBlackCr = f.getAsFloat(4);
/* 441 */         this.referenceWhiteCr = f.getAsFloat(5);
/*     */       } else {
/* 443 */         warning("REFERENCE_BLACK_WHITE count != 6, ignoring it");
/*     */       } 
/*     */     } else {
/* 446 */       warning("REFERENCE_BLACK_WHITE not found, assuming 0-255/128-255/128-255");
/*     */     } 
/*     */     
/* 449 */     this.colorConvert = true;
/*     */     
/* 451 */     float BCb = 2.0F - 2.0F * this.LumaBlue;
/* 452 */     float RCr = 2.0F - 2.0F * this.LumaRed;
/*     */     
/* 454 */     float GY = (1.0F - this.LumaBlue - this.LumaRed) / this.LumaGreen;
/* 455 */     float GCb = 2.0F * this.LumaBlue * (this.LumaBlue - 1.0F) / this.LumaGreen;
/* 456 */     float GCr = 2.0F * this.LumaRed * (this.LumaRed - 1.0F) / this.LumaGreen;
/*     */     
/* 458 */     for (int i = 0; i < 256; i++) {
/* 459 */       float fY = (i - this.referenceBlackY) * this.codingRangeY / (this.referenceWhiteY - this.referenceBlackY);
/*     */       
/* 461 */       float fCb = (i - this.referenceBlackCb) * 127.0F / (this.referenceWhiteCb - this.referenceBlackCb);
/*     */       
/* 463 */       float fCr = (i - this.referenceBlackCr) * 127.0F / (this.referenceWhiteCr - this.referenceBlackCr);
/*     */ 
/*     */       
/* 466 */       this.iYTab[i] = (int)(fY * 65536.0F);
/* 467 */       this.iCbTab[i] = (int)(fCb * BCb * 65536.0F);
/* 468 */       this.iCrTab[i] = (int)(fCr * RCr * 65536.0F);
/*     */       
/* 470 */       this.iGYTab[i] = (int)(fY * GY * 65536.0F);
/* 471 */       this.iGCbTab[i] = (int)(fCb * GCb * 65536.0F);
/* 472 */       this.iGCrTab[i] = (int)(fCr * GCr * 65536.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decodeRaw(byte[] buf, int dstOffset, int bitsPerPixel, int scanlineStride) throws IOException {
/* 480 */     byte[] rows = new byte[3 * this.srcWidth * this.chromaSubsampleV];
/*     */     
/* 482 */     int elementsPerPacket = this.chromaSubsampleH * this.chromaSubsampleV + 2;
/* 483 */     byte[] packet = new byte[elementsPerPacket];
/*     */     
/* 485 */     if (this.decompressor != null) {
/* 486 */       int bytesPerRow = 3 * this.srcWidth;
/* 487 */       byte[] tmpBuf = new byte[bytesPerRow * this.srcHeight];
/* 488 */       this.decompressor.decodeRaw(tmpBuf, dstOffset, bitsPerPixel, bytesPerRow);
/*     */       
/* 490 */       ByteArrayInputStream byteStream = new ByteArrayInputStream(tmpBuf);
/*     */       
/* 492 */       this.stream = new MemoryCacheImageInputStream(byteStream);
/*     */     } else {
/* 494 */       this.stream.seek(this.offset);
/*     */     } 
/*     */     int y;
/* 497 */     for (y = this.srcMinY; y < this.srcMinY + this.srcHeight; y += this.chromaSubsampleV) {
/*     */       int x;
/* 499 */       for (x = this.srcMinX; x < this.srcMinX + this.srcWidth; 
/* 500 */         x += this.chromaSubsampleH) {
/*     */         try {
/* 502 */           this.stream.readFully(packet);
/* 503 */         } catch (EOFException e) {
/* 504 */           System.out.println("e = " + e);
/*     */           
/*     */           return;
/*     */         } 
/* 508 */         byte Cb = packet[elementsPerPacket - 2];
/* 509 */         byte Cr = packet[elementsPerPacket - 1];
/*     */         
/* 511 */         int iCb = 0, iCr = 0, iGCb = 0, iGCr = 0;
/*     */         
/* 513 */         if (this.colorConvert) {
/* 514 */           int Cbp = Cb & 0xFF;
/* 515 */           int Crp = Cr & 0xFF;
/*     */           
/* 517 */           iCb = this.iCbTab[Cbp];
/* 518 */           iCr = this.iCrTab[Crp];
/*     */           
/* 520 */           iGCb = this.iGCbTab[Cbp];
/* 521 */           iGCr = this.iGCrTab[Crp];
/*     */         } 
/*     */         
/* 524 */         int yIndex = 0;
/* 525 */         for (int v = 0; v < this.chromaSubsampleV; v++) {
/* 526 */           int idx = dstOffset + 3 * (x - this.srcMinX) + scanlineStride * (y - this.srcMinY + v);
/*     */ 
/*     */ 
/*     */           
/* 530 */           if (y + v >= this.srcMinY + this.srcHeight) {
/*     */             break;
/*     */           }
/*     */           
/* 534 */           for (int h = 0; h < this.chromaSubsampleH && 
/* 535 */             x + h < this.srcMinX + this.srcWidth; h++) {
/*     */ 
/*     */ 
/*     */             
/* 539 */             byte Y = packet[yIndex++];
/*     */             
/* 541 */             if (this.colorConvert) {
/* 542 */               int Yp = Y & 0xFF;
/* 543 */               int iY = this.iYTab[Yp];
/* 544 */               int iGY = this.iGYTab[Yp];
/*     */               
/* 546 */               int iR = iY + iCr;
/* 547 */               int iG = iGY + iGCb + iGCr;
/* 548 */               int iB = iY + iCb;
/*     */               
/* 550 */               byte r = clamp(iR);
/* 551 */               byte g = clamp(iG);
/* 552 */               byte b = clamp(iB);
/*     */               
/* 554 */               buf[idx] = r;
/* 555 */               buf[idx + 1] = g;
/* 556 */               buf[idx + 2] = b;
/*     */             } else {
/* 558 */               buf[idx] = Y;
/* 559 */               buf[idx + 1] = Cb;
/* 560 */               buf[idx + 2] = Cr;
/*     */             } 
/*     */             
/* 563 */             idx += 3;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFYCbCrDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */