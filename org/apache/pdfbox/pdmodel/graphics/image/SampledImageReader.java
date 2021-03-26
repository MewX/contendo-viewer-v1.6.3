/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class SampledImageReader
/*     */ {
/*  49 */   private static final Log LOG = LogFactory.getLog(SampledImageReader.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage getStencilImage(PDImage pdImage, Paint paint) throws IOException {
/*  64 */     int width = pdImage.getWidth();
/*  65 */     int height = pdImage.getHeight();
/*     */ 
/*     */     
/*  68 */     BufferedImage masked = new BufferedImage(width, height, 2);
/*  69 */     Graphics2D g = masked.createGraphics();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     g.setPaint(paint);
/*  77 */     g.fillRect(0, 0, width, height);
/*  78 */     g.dispose();
/*     */ 
/*     */     
/*  81 */     WritableRaster raster = masked.getRaster();
/*     */     
/*  83 */     int[] transparent = new int[4];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     ImageInputStream iis = null;
/*     */     
/*     */     try {
/*  91 */       iis = new MemoryCacheImageInputStream(pdImage.createInputStream());
/*  92 */       float[] decode = getDecodeArray(pdImage);
/*  93 */       int value = (decode[0] < decode[1]) ? 1 : 0;
/*  94 */       int rowLen = width / 8;
/*  95 */       if (width % 8 > 0)
/*     */       {
/*  97 */         rowLen++;
/*     */       }
/*  99 */       byte[] buff = new byte[rowLen];
/* 100 */       for (int y = 0; y < height; y++) {
/*     */         
/* 102 */         int x = 0;
/* 103 */         int readLen = iis.read(buff);
/* 104 */         for (int r = 0; r < rowLen && r < readLen; r++) {
/*     */           
/* 106 */           int byteValue = buff[r];
/* 107 */           int mask = 128;
/* 108 */           int shift = 7;
/* 109 */           for (int i = 0; i < 8; i++) {
/*     */             
/* 111 */             int bit = (byteValue & mask) >> shift;
/* 112 */             mask >>= 1;
/* 113 */             shift--;
/* 114 */             if (bit == value)
/*     */             {
/* 116 */               raster.setPixel(x, y, transparent);
/*     */             }
/* 118 */             x++;
/* 119 */             if (x == width) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 125 */         if (readLen != rowLen) {
/*     */           
/* 127 */           LOG.warn("premature EOF, image will be incomplete");
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 134 */       if (iis != null)
/*     */       {
/* 136 */         iis.close();
/*     */       }
/*     */     } 
/*     */     
/* 140 */     return masked;
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
/*     */   public static BufferedImage getRGBImage(PDImage pdImage, COSArray colorKey) throws IOException {
/* 154 */     return getRGBImage(pdImage, null, 1, colorKey);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Rectangle clipRegion(PDImage pdImage, Rectangle region) {
/* 159 */     if (region == null)
/*     */     {
/* 161 */       return new Rectangle(0, 0, pdImage.getWidth(), pdImage.getHeight());
/*     */     }
/*     */ 
/*     */     
/* 165 */     int x = Math.max(0, region.x);
/* 166 */     int y = Math.max(0, region.y);
/* 167 */     int width = Math.min(region.width, pdImage.getWidth() - x);
/* 168 */     int height = Math.min(region.height, pdImage.getHeight() - y);
/* 169 */     return new Rectangle(x, y, width, height);
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
/*     */   public static BufferedImage getRGBImage(PDImage pdImage, Rectangle region, int subsampling, COSArray colorKey) throws IOException {
/* 189 */     if (pdImage.isEmpty())
/*     */     {
/* 191 */       throw new IOException("Image stream is empty");
/*     */     }
/* 193 */     Rectangle clipped = clipRegion(pdImage, region);
/*     */ 
/*     */     
/* 196 */     PDColorSpace colorSpace = pdImage.getColorSpace();
/* 197 */     int numComponents = colorSpace.getNumberOfComponents();
/* 198 */     int width = (int)Math.ceil(clipped.getWidth() / subsampling);
/* 199 */     int height = (int)Math.ceil(clipped.getHeight() / subsampling);
/* 200 */     int bitsPerComponent = pdImage.getBitsPerComponent();
/* 201 */     float[] decode = getDecodeArray(pdImage);
/*     */     
/* 203 */     if (width <= 0 || height <= 0 || pdImage.getWidth() <= 0 || pdImage.getHeight() <= 0)
/*     */     {
/* 205 */       throw new IOException("image width and height must be positive");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 210 */       if (bitsPerComponent == 1 && colorKey == null && numComponents == 1)
/*     */       {
/* 212 */         return from1Bit(pdImage, clipped, subsampling, width, height);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 220 */       WritableRaster raster = Raster.createInterleavedRaster(0, width, height, numComponents, new Point(0, 0));
/*     */       
/* 222 */       float[] defaultDecode = pdImage.getColorSpace().getDefaultDecode(8);
/* 223 */       if (bitsPerComponent == 8 && Arrays.equals(decode, defaultDecode) && colorKey == null)
/*     */       {
/*     */         
/* 226 */         return from8bit(pdImage, raster, clipped, subsampling, width, height);
/*     */       }
/* 228 */       return fromAny(pdImage, raster, colorKey, clipped, subsampling, width, height);
/*     */     }
/* 230 */     catch (NegativeArraySizeException ex) {
/*     */       
/* 232 */       throw new IOException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage from1Bit(PDImage pdImage, Rectangle clipped, int subsampling, int width, int height) throws IOException {
/* 239 */     int currentSubsampling = subsampling;
/* 240 */     PDColorSpace colorSpace = pdImage.getColorSpace();
/* 241 */     float[] decode = getDecodeArray(pdImage);
/* 242 */     BufferedImage bim = null;
/*     */ 
/*     */ 
/*     */     
/* 246 */     DecodeOptions options = new DecodeOptions(currentSubsampling);
/* 247 */     options.setSourceRegion(clipped);
/*     */     
/* 249 */     InputStream iis = null; try {
/*     */       WritableRaster raster;
/*     */       int inputWidth, startx, starty, scanWidth, scanHeight;
/*     */       byte value0, value1;
/* 253 */       iis = pdImage.createInputStream(options);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 260 */       if (options.isFilterSubsampled()) {
/*     */ 
/*     */         
/* 263 */         inputWidth = width;
/* 264 */         startx = 0;
/* 265 */         starty = 0;
/* 266 */         scanWidth = width;
/* 267 */         scanHeight = height;
/* 268 */         currentSubsampling = 1;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 273 */         inputWidth = pdImage.getWidth();
/* 274 */         startx = clipped.x;
/* 275 */         starty = clipped.y;
/* 276 */         scanWidth = clipped.width;
/* 277 */         scanHeight = clipped.height;
/*     */       } 
/* 279 */       if (colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 284 */         bim = new BufferedImage(width, height, 10);
/* 285 */         raster = bim.getRaster();
/*     */       }
/*     */       else {
/*     */         
/* 289 */         raster = Raster.createBandedRaster(0, width, height, 1, new Point(0, 0));
/*     */       } 
/* 291 */       byte[] output = ((DataBufferByte)raster.getDataBuffer()).getData();
/* 292 */       boolean isIndexed = colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDIndexed;
/*     */       
/* 294 */       int rowLen = inputWidth / 8;
/* 295 */       if (inputWidth % 8 > 0)
/*     */       {
/* 297 */         rowLen++;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 303 */       if (isIndexed || decode[0] < decode[1]) {
/*     */         
/* 305 */         value0 = 0;
/* 306 */         value1 = -1;
/*     */       }
/*     */       else {
/*     */         
/* 310 */         value0 = -1;
/* 311 */         value1 = 0;
/*     */       } 
/* 313 */       byte[] buff = new byte[rowLen];
/* 314 */       int idx = 0;
/* 315 */       for (int y = 0; y < starty + scanHeight; y++) {
/*     */         
/* 317 */         int x = 0;
/* 318 */         int readLen = iis.read(buff);
/* 319 */         if (y >= starty && y % currentSubsampling <= 0) {
/*     */ 
/*     */ 
/*     */           
/* 323 */           for (int r = 0; r < rowLen && r < readLen; r++) {
/*     */             
/* 325 */             int value = buff[r];
/* 326 */             int mask = 128;
/* 327 */             for (int i = 0; i < 8; i++) {
/*     */               
/* 329 */               if (x >= startx + scanWidth) {
/*     */                 break;
/*     */               }
/*     */               
/* 333 */               int bit = value & mask;
/* 334 */               mask >>= 1;
/* 335 */               if (x >= startx && x % currentSubsampling == 0)
/*     */               {
/* 337 */                 output[idx++] = (bit == 0) ? value0 : value1;
/*     */               }
/* 339 */               x++;
/*     */             } 
/*     */           } 
/* 342 */           if (readLen != rowLen) {
/*     */             
/* 344 */             LOG.warn("premature EOF, image will be incomplete");
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 349 */       if (bim != null)
/*     */       {
/* 351 */         return bim;
/*     */       }
/*     */ 
/*     */       
/* 355 */       return colorSpace.toRGBImage(raster);
/*     */     }
/*     */     finally {
/*     */       
/* 359 */       if (iis != null)
/*     */       {
/* 361 */         iis.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage from8bit(PDImage pdImage, WritableRaster raster, Rectangle clipped, int subsampling, int width, int height) throws IOException {
/* 370 */     int currentSubsampling = subsampling;
/* 371 */     DecodeOptions options = new DecodeOptions(currentSubsampling);
/* 372 */     options.setSourceRegion(clipped);
/* 373 */     InputStream input = pdImage.createInputStream(options);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*     */       int inputWidth, startx, starty, scanWidth, scanHeight;
/*     */ 
/*     */       
/* 381 */       if (options.isFilterSubsampled()) {
/*     */ 
/*     */         
/* 384 */         inputWidth = width;
/* 385 */         startx = 0;
/* 386 */         starty = 0;
/* 387 */         scanWidth = width;
/* 388 */         scanHeight = height;
/* 389 */         currentSubsampling = 1;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 394 */         inputWidth = pdImage.getWidth();
/* 395 */         startx = clipped.x;
/* 396 */         starty = clipped.y;
/* 397 */         scanWidth = clipped.width;
/* 398 */         scanHeight = clipped.height;
/*     */       } 
/* 400 */       int numComponents = pdImage.getColorSpace().getNumberOfComponents();
/*     */       
/* 402 */       byte[] bank = ((DataBufferByte)raster.getDataBuffer()).getData();
/* 403 */       if (startx == 0 && starty == 0 && scanWidth == width && scanHeight == height && currentSubsampling == 1) {
/*     */ 
/*     */         
/* 406 */         long inputResult = input.read(bank);
/* 407 */         if (inputResult != (width * height) * numComponents)
/*     */         {
/* 409 */           LOG.debug("Tried reading " + ((width * height) * numComponents) + " bytes but only " + inputResult + " bytes read");
/*     */         }
/* 411 */         return pdImage.getColorSpace().toRGBImage(raster);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 416 */       byte[] tempBytes = new byte[numComponents * inputWidth];
/*     */ 
/*     */ 
/*     */       
/* 420 */       int i = 0;
/* 421 */       for (int y = 0; y < starty + scanHeight; y++) {
/*     */         
/* 423 */         input.read(tempBytes);
/* 424 */         if (y >= starty && y % currentSubsampling <= 0)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 429 */           if (currentSubsampling == 1) {
/*     */ 
/*     */ 
/*     */             
/* 433 */             System.arraycopy(tempBytes, startx * numComponents, bank, y * inputWidth * numComponents, scanWidth * numComponents);
/*     */           } else {
/*     */             int x;
/*     */             
/* 437 */             for (x = startx; x < startx + scanWidth; x += currentSubsampling) {
/*     */               
/* 439 */               for (int c = 0; c < numComponents; c++) {
/*     */                 
/* 441 */                 bank[i] = tempBytes[x * numComponents + c];
/* 442 */                 i++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 448 */       return pdImage.getColorSpace().toRGBImage(raster);
/*     */     }
/*     */     finally {
/*     */       
/* 452 */       IOUtils.closeQuietly(input);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage fromAny(PDImage pdImage, WritableRaster raster, COSArray colorKey, Rectangle clipped, int subsampling, int width, int height) throws IOException {
/* 461 */     int currentSubsampling = subsampling;
/* 462 */     PDColorSpace colorSpace = pdImage.getColorSpace();
/* 463 */     int numComponents = colorSpace.getNumberOfComponents();
/* 464 */     int bitsPerComponent = pdImage.getBitsPerComponent();
/* 465 */     float[] decode = getDecodeArray(pdImage);
/*     */     
/* 467 */     DecodeOptions options = new DecodeOptions(currentSubsampling);
/* 468 */     options.setSourceRegion(clipped);
/*     */     
/* 470 */     ImageInputStream iis = null;
/*     */     try {
/*     */       int inputWidth, startx, starty, scanWidth, scanHeight;
/* 473 */       iis = new MemoryCacheImageInputStream(pdImage.createInputStream(options));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 480 */       if (options.isFilterSubsampled()) {
/*     */ 
/*     */         
/* 483 */         inputWidth = width;
/* 484 */         startx = 0;
/* 485 */         starty = 0;
/* 486 */         scanWidth = width;
/* 487 */         scanHeight = height;
/* 488 */         currentSubsampling = 1;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 493 */         inputWidth = pdImage.getWidth();
/* 494 */         startx = clipped.x;
/* 495 */         starty = clipped.y;
/* 496 */         scanWidth = clipped.width;
/* 497 */         scanHeight = clipped.height;
/*     */       } 
/*     */       
/* 500 */       float sampleMax = (float)Math.pow(2.0D, bitsPerComponent) - 1.0F;
/* 501 */       boolean isIndexed = colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDIndexed;
/*     */ 
/*     */       
/* 504 */       float[] colorKeyRanges = null;
/* 505 */       BufferedImage colorKeyMask = null;
/* 506 */       if (colorKey != null) {
/*     */         
/* 508 */         colorKeyRanges = colorKey.toFloatArray();
/* 509 */         colorKeyMask = new BufferedImage(width, height, 10);
/*     */       } 
/*     */ 
/*     */       
/* 513 */       int padding = 0;
/* 514 */       if (inputWidth * numComponents * bitsPerComponent % 8 > 0)
/*     */       {
/* 516 */         padding = 8 - inputWidth * numComponents * bitsPerComponent % 8;
/*     */       }
/*     */ 
/*     */       
/* 520 */       byte[] srcColorValues = new byte[numComponents];
/* 521 */       byte[] alpha = new byte[1];
/* 522 */       for (int y = 0; y < starty + scanHeight; y++) {
/*     */         
/* 524 */         for (int x = 0; x < startx + scanWidth; x++) {
/*     */           int i;
/* 526 */           boolean isMasked = true;
/* 527 */           for (int c = 0; c < numComponents; c++) {
/*     */             
/* 529 */             int value = (int)iis.readBits(bitsPerComponent);
/*     */ 
/*     */             
/* 532 */             if (colorKeyRanges != null)
/*     */             {
/* 534 */               i = isMasked & ((value >= colorKeyRanges[c * 2] && value <= colorKeyRanges[c * 2 + 1]) ? 1 : 0);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 539 */             float dMin = decode[c * 2];
/* 540 */             float dMax = decode[c * 2 + 1];
/*     */ 
/*     */             
/* 543 */             float output = dMin + value * (dMax - dMin) / sampleMax;
/*     */             
/* 545 */             if (isIndexed) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 550 */               srcColorValues[c] = (byte)Math.round(output);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 555 */               int outputByte = Math.round((output - Math.min(dMin, dMax)) / 
/* 556 */                   Math.abs(dMax - dMin) * 255.0F);
/*     */               
/* 558 */               srcColorValues[c] = (byte)outputByte;
/*     */             } 
/*     */           } 
/*     */           
/* 562 */           if (x >= startx && y >= starty && x % currentSubsampling == 0 && y % currentSubsampling == 0) {
/*     */             
/* 564 */             raster.setDataElements((x - startx) / currentSubsampling, (y - starty) / currentSubsampling, srcColorValues);
/*     */ 
/*     */             
/* 567 */             if (colorKeyMask != null) {
/*     */               
/* 569 */               alpha[0] = (byte)((i != 0) ? 255 : 0);
/* 570 */               colorKeyMask.getRaster().setDataElements((x - startx) / currentSubsampling, (y - starty) / currentSubsampling, alpha);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 576 */         iis.readBits(padding);
/*     */       } 
/*     */ 
/*     */       
/* 580 */       BufferedImage rgbImage = colorSpace.toRGBImage(raster);
/*     */ 
/*     */       
/* 583 */       if (colorKeyMask != null)
/*     */       {
/* 585 */         return applyColorKeyMask(rgbImage, colorKeyMask);
/*     */       }
/*     */ 
/*     */       
/* 589 */       return rgbImage;
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 594 */       if (iis != null)
/*     */       {
/* 596 */         iis.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage applyColorKeyMask(BufferedImage image, BufferedImage mask) throws IOException {
/* 605 */     int width = image.getWidth();
/* 606 */     int height = image.getHeight();
/*     */ 
/*     */     
/* 609 */     BufferedImage masked = new BufferedImage(width, height, 2);
/*     */     
/* 611 */     WritableRaster src = image.getRaster();
/* 612 */     WritableRaster dest = masked.getRaster();
/* 613 */     WritableRaster alpha = mask.getRaster();
/*     */     
/* 615 */     float[] rgb = new float[3];
/* 616 */     float[] rgba = new float[4];
/* 617 */     float[] alphaPixel = null;
/* 618 */     for (int y = 0; y < height; y++) {
/*     */       
/* 620 */       for (int x = 0; x < width; x++) {
/*     */         
/* 622 */         src.getPixel(x, y, rgb);
/*     */         
/* 624 */         rgba[0] = rgb[0];
/* 625 */         rgba[1] = rgb[1];
/* 626 */         rgba[2] = rgb[2];
/* 627 */         alphaPixel = alpha.getPixel(x, y, alphaPixel);
/* 628 */         rgba[3] = 255.0F - alphaPixel[0];
/*     */         
/* 630 */         dest.setPixel(x, y, rgba);
/*     */       } 
/*     */     } 
/*     */     
/* 634 */     return masked;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static float[] getDecodeArray(PDImage pdImage) throws IOException {
/* 640 */     COSArray cosDecode = pdImage.getDecode();
/* 641 */     float[] decode = null;
/*     */     
/* 643 */     if (cosDecode != null) {
/*     */       
/* 645 */       int numberOfComponents = pdImage.getColorSpace().getNumberOfComponents();
/* 646 */       if (cosDecode.size() != numberOfComponents * 2) {
/*     */         
/* 648 */         if (pdImage.isStencil() && cosDecode.size() >= 2 && cosDecode
/* 649 */           .get(0) instanceof COSNumber && cosDecode
/* 650 */           .get(1) instanceof COSNumber) {
/*     */           
/* 652 */           float decode0 = ((COSNumber)cosDecode.get(0)).floatValue();
/* 653 */           float decode1 = ((COSNumber)cosDecode.get(1)).floatValue();
/* 654 */           if (decode0 >= 0.0F && decode0 <= 1.0F && decode1 >= 0.0F && decode1 <= 1.0F) {
/*     */             
/* 656 */             LOG.warn("decode array " + cosDecode + " not compatible with color space, using the first two entries");
/*     */             
/* 658 */             return new float[] { decode0, decode1 };
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 664 */         LOG.error("decode array " + cosDecode + " not compatible with color space, using default");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 669 */         decode = cosDecode.toFloatArray();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 674 */     if (decode == null)
/*     */     {
/* 676 */       return pdImage.getColorSpace().getDefaultDecode(pdImage.getBitsPerComponent());
/*     */     }
/*     */     
/* 679 */     return decode;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/SampledImageReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */