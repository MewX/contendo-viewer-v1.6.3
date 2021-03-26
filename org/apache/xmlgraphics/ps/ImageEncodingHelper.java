/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
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
/*     */ public class ImageEncodingHelper
/*     */ {
/*  45 */   private static final ColorModel DEFAULT_RGB_COLOR_MODEL = new ComponentColorModel(ColorSpace.getInstance(1000), false, false, 1, 0);
/*     */   
/*     */   private final RenderedImage image;
/*     */   
/*     */   private ColorModel encodedColorModel;
/*     */   
/*     */   private boolean firstTileDump;
/*     */   
/*     */   private boolean enableCMYK;
/*     */   
/*     */   private boolean isBGR;
/*     */   
/*     */   private boolean isKMYC;
/*     */   
/*     */   private boolean outputbw;
/*     */   private boolean bwinvert;
/*     */   
/*     */   public ImageEncodingHelper(RenderedImage image) {
/*  63 */     this(image, true);
/*  64 */     this.outputbw = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageEncodingHelper(RenderedImage image, boolean enableCMYK) {
/*  73 */     this.image = image;
/*  74 */     this.enableCMYK = enableCMYK;
/*  75 */     determineEncodedColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage getImage() {
/*  83 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getNativeColorModel() {
/*  91 */     return getImage().getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getEncodedColorModel() {
/* 101 */     return this.encodedColorModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAlpha() {
/* 109 */     return this.image.getColorModel().hasAlpha();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverted() {
/* 117 */     return (getNativeColorModel() != getEncodedColorModel());
/*     */   }
/*     */   
/*     */   private void writeRGBTo(OutputStream out) throws IOException {
/* 121 */     boolean encoded = encodeRenderedImageWithDirectColorModelAsRGB(this.image, out);
/* 122 */     if (encoded) {
/*     */       return;
/*     */     }
/* 125 */     encodeRenderedImageAsRGB(this.image, out, this.outputbw, this.bwinvert);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void encodeRenderedImageAsRGB(RenderedImage image, OutputStream out) throws IOException {
/* 130 */     encodeRenderedImageAsRGB(image, out, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeRenderedImageAsRGB(RenderedImage image, OutputStream out, boolean outputbw, boolean bwinvert) throws IOException {
/*     */     Object data;
/* 141 */     Raster raster = getRaster(image);
/*     */     
/* 143 */     int nbands = raster.getNumBands();
/* 144 */     int dataType = raster.getDataBuffer().getDataType();
/* 145 */     switch (dataType) {
/*     */       case 0:
/* 147 */         data = new byte[nbands];
/*     */         break;
/*     */       case 1:
/* 150 */         data = null;
/*     */         break;
/*     */       case 3:
/* 153 */         data = new int[nbands];
/*     */         break;
/*     */       case 4:
/* 156 */         data = new float[nbands];
/*     */         break;
/*     */       case 5:
/* 159 */         data = new double[nbands];
/*     */         break;
/*     */       default:
/* 162 */         throw new IllegalArgumentException("Unknown data buffer type: " + dataType);
/*     */     } 
/*     */     
/* 165 */     ColorModel colorModel = image.getColorModel();
/* 166 */     int w = image.getWidth();
/* 167 */     int h = image.getHeight();
/* 168 */     int numDataElements = 3;
/* 169 */     if (colorModel.getPixelSize() == 1 && outputbw) {
/* 170 */       numDataElements = 1;
/*     */     }
/*     */     
/* 173 */     byte[] buf = new byte[w * numDataElements];
/*     */     
/* 175 */     for (int y = 0; y < h; y++) {
/* 176 */       int idx = -1;
/* 177 */       for (int x = 0; x < w; x++) {
/* 178 */         int rgb = colorModel.getRGB(raster.getDataElements(x, y, data));
/* 179 */         if (numDataElements > 1) {
/* 180 */           buf[++idx] = (byte)(rgb >> 16);
/* 181 */           buf[++idx] = (byte)(rgb >> 8);
/* 182 */         } else if (bwinvert && rgb == -1) {
/* 183 */           rgb = 1;
/*     */         } 
/* 185 */         buf[++idx] = (byte)rgb;
/*     */       } 
/* 187 */       out.write(buf);
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
/*     */ 
/*     */   
/*     */   public static boolean encodeRenderedImageWithDirectColorModelAsRGB(RenderedImage image, OutputStream out) throws IOException {
/* 201 */     ColorModel cm = image.getColorModel();
/* 202 */     if (cm.getColorSpace() != ColorSpace.getInstance(1000)) {
/* 203 */       return false;
/*     */     }
/* 205 */     if (!(cm instanceof DirectColorModel)) {
/* 206 */       return false;
/*     */     }
/* 208 */     DirectColorModel dcm = (DirectColorModel)cm;
/* 209 */     int[] templateMasks = { 16711680, 65280, 255, -16777216 };
/*     */     
/* 211 */     int[] masks = dcm.getMasks();
/* 212 */     if (!Arrays.equals(templateMasks, masks)) {
/* 213 */       return false;
/*     */     }
/*     */     
/* 216 */     Raster raster = getRaster(image);
/* 217 */     int dataType = raster.getDataBuffer().getDataType();
/* 218 */     if (dataType != 3) {
/* 219 */       return false;
/*     */     }
/*     */     
/* 222 */     int w = image.getWidth();
/* 223 */     int h = image.getHeight();
/*     */     
/* 225 */     int[] data = new int[w];
/* 226 */     byte[] buf = new byte[w * 3];
/* 227 */     for (int y = 0; y < h; y++) {
/* 228 */       int idx = -1;
/* 229 */       raster.getDataElements(0, y, w, 1, data);
/* 230 */       for (int x = 0; x < w; x++) {
/* 231 */         int rgb = data[x];
/* 232 */         buf[++idx] = (byte)(rgb >> 16);
/* 233 */         buf[++idx] = (byte)(rgb >> 8);
/* 234 */         buf[++idx] = (byte)rgb;
/*     */       } 
/* 236 */       out.write(buf);
/*     */     } 
/*     */     
/* 239 */     return true;
/*     */   }
/*     */   
/*     */   private static Raster getRaster(RenderedImage image) {
/* 243 */     if (image instanceof BufferedImage) {
/* 244 */       return ((BufferedImage)image).getRaster();
/*     */     }
/*     */ 
/*     */     
/* 248 */     return image.getData();
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
/*     */   public static void encodeRGBAsGrayScale(byte[] raw, int width, int height, int bitsPerPixel, OutputStream out) throws IOException {
/* 271 */     int pixelsPerByte = 8 / bitsPerPixel;
/* 272 */     int bytewidth = width / pixelsPerByte;
/* 273 */     if (width % pixelsPerByte != 0) {
/* 274 */       bytewidth++;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 279 */     byte[] linedata = new byte[bytewidth];
/*     */     
/* 281 */     for (int y = 0; y < height; y++) {
/* 282 */       byte ib = 0;
/* 283 */       int i = 3 * y * width;
/* 284 */       for (int x = 0; x < width; x++, i += 3) {
/*     */ 
/*     */         
/* 287 */         double greyVal = 0.212671D * (raw[i] & 0xFF) + 0.71516D * (raw[i + 1] & 0xFF) + 0.072169D * (raw[i + 2] & 0xFF);
/*     */ 
/*     */         
/* 290 */         switch (bitsPerPixel) {
/*     */           case 1:
/* 292 */             if (greyVal < 128.0D) {
/* 293 */               ib = (byte)(ib | (byte)(1 << 7 - x % 8));
/*     */             }
/*     */             break;
/*     */           case 4:
/* 297 */             greyVal /= 16.0D;
/* 298 */             ib = (byte)(ib | (byte)((byte)(int)greyVal << (1 - x % 2) * 4));
/*     */             break;
/*     */           case 8:
/* 301 */             ib = (byte)(int)greyVal;
/*     */             break;
/*     */           default:
/* 304 */             throw new UnsupportedOperationException("Unsupported bits per pixel: " + bitsPerPixel);
/*     */         } 
/*     */ 
/*     */         
/* 308 */         if (x % pixelsPerByte == pixelsPerByte - 1 || x + 1 == width) {
/*     */           
/* 310 */           linedata[x / pixelsPerByte] = ib;
/* 311 */           ib = 0;
/*     */         } 
/*     */       } 
/* 314 */       out.write(linedata);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean optimizedWriteTo(OutputStream out) throws IOException {
/* 320 */     if (this.firstTileDump) {
/* 321 */       Raster raster = this.image.getTile(0, 0);
/* 322 */       DataBuffer buffer = raster.getDataBuffer();
/* 323 */       if (buffer instanceof DataBufferByte) {
/* 324 */         byte[] bytes = ((DataBufferByte)buffer).getData();
/*     */         
/* 326 */         if (this.isBGR) {
/* 327 */           byte[] bytesPermutated = new byte[bytes.length];
/* 328 */           for (int i = 0; i < bytes.length; i += 3) {
/* 329 */             bytesPermutated[i] = bytes[i + 2];
/* 330 */             bytesPermutated[i + 1] = bytes[i + 1];
/* 331 */             bytesPermutated[i + 2] = bytes[i];
/*     */           } 
/* 333 */           out.write(bytesPermutated);
/* 334 */         } else if (this.isKMYC) {
/* 335 */           byte[] bytesPermutated = new byte[bytes.length];
/* 336 */           for (int i = 0; i < bytes.length; i += 4) {
/* 337 */             bytesPermutated[i] = bytes[i + 3];
/* 338 */             bytesPermutated[i + 1] = bytes[i + 2];
/* 339 */             bytesPermutated[i + 2] = bytes[i + 1];
/* 340 */             bytesPermutated[i + 3] = bytes[i];
/*     */           } 
/* 342 */           out.write(bytesPermutated);
/*     */         } else {
/* 344 */           out.write(bytes);
/*     */         } 
/* 346 */         return true;
/*     */       } 
/*     */     } 
/* 349 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isMultiTile() {
/* 357 */     int tilesX = this.image.getNumXTiles();
/* 358 */     int tilesY = this.image.getNumYTiles();
/* 359 */     return (tilesX != 1 || tilesY != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void determineEncodedColorModel() {
/* 366 */     this.firstTileDump = false;
/* 367 */     this.encodedColorModel = DEFAULT_RGB_COLOR_MODEL;
/*     */     
/* 369 */     ColorModel cm = this.image.getColorModel();
/* 370 */     ColorSpace cs = cm.getColorSpace();
/*     */     
/* 372 */     int numComponents = cm.getNumComponents();
/*     */     
/* 374 */     if (!isMultiTile()) {
/* 375 */       if (numComponents == 1 && cs.getType() == 6) {
/* 376 */         if (cm.getTransferType() == 0) {
/* 377 */           this.firstTileDump = true;
/* 378 */           this.encodedColorModel = cm;
/*     */         } 
/* 380 */       } else if (cm instanceof java.awt.image.IndexColorModel) {
/* 381 */         if (cm.getTransferType() == 0) {
/* 382 */           this.firstTileDump = true;
/* 383 */           this.encodedColorModel = cm;
/*     */         } 
/* 385 */       } else if (cm instanceof ComponentColorModel && (numComponents == 3 || (this.enableCMYK && numComponents == 4)) && !cm.hasAlpha()) {
/*     */ 
/*     */         
/* 388 */         Raster raster = this.image.getTile(0, 0);
/* 389 */         DataBuffer buffer = raster.getDataBuffer();
/* 390 */         SampleModel sampleModel = raster.getSampleModel();
/* 391 */         if (sampleModel instanceof PixelInterleavedSampleModel) {
/*     */           
/* 393 */           PixelInterleavedSampleModel piSampleModel = (PixelInterleavedSampleModel)sampleModel;
/* 394 */           int[] offsets = piSampleModel.getBandOffsets();
/* 395 */           for (int i = 0; i < offsets.length; i++) {
/* 396 */             if (offsets[i] != i && offsets[i] != offsets.length - 1 - i) {
/*     */               return;
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 407 */           this.isBGR = false;
/* 408 */           if (offsets.length == 3 && offsets[0] == 2 && offsets[1] == 1 && offsets[2] == 0) {
/* 409 */             this.isBGR = true;
/*     */           }
/*     */           
/* 412 */           if (offsets.length == 4 && offsets[0] == 3 && offsets[1] == 2 && offsets[2] == 1 && offsets[3] == 0)
/*     */           {
/* 414 */             this.isKMYC = true;
/*     */           }
/*     */         } 
/* 417 */         if (cm.getTransferType() == 0 && buffer.getOffset() == 0 && buffer.getNumBanks() == 1) {
/*     */ 
/*     */           
/* 420 */           this.firstTileDump = true;
/* 421 */           this.encodedColorModel = cm;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(OutputStream out) throws IOException {
/* 434 */     if (!isConverted() && 
/* 435 */       optimizedWriteTo(out)) {
/*     */       return;
/*     */     }
/*     */     
/* 439 */     writeRGBTo(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encodeAlpha(OutputStream out) throws IOException {
/* 449 */     if (!hasAlpha()) {
/* 450 */       throw new IllegalStateException("Image doesn't have an alpha channel");
/*     */     }
/* 452 */     Raster alpha = GraphicsUtil.getAlphaRaster(this.image);
/* 453 */     DataBuffer buffer = alpha.getDataBuffer();
/* 454 */     if (buffer instanceof DataBufferByte) {
/* 455 */       out.write(((DataBufferByte)buffer).getData());
/*     */     } else {
/* 457 */       throw new UnsupportedOperationException("Alpha raster not supported: " + buffer.getClass().getName());
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
/*     */   
/*     */   public static void encodePackedColorComponents(RenderedImage image, OutputStream out) throws IOException {
/* 470 */     ImageEncodingHelper helper = new ImageEncodingHelper(image);
/* 471 */     helper.encode(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageEncoder createRenderedImageEncoder(RenderedImage img) {
/* 480 */     return new RenderedImageEncoder(img);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class RenderedImageEncoder
/*     */     implements ImageEncoder
/*     */   {
/*     */     private final RenderedImage img;
/*     */ 
/*     */     
/*     */     public RenderedImageEncoder(RenderedImage img) {
/* 491 */       this.img = img;
/*     */     }
/*     */     
/*     */     public void writeTo(OutputStream out) throws IOException {
/* 495 */       ImageEncodingHelper.encodePackedColorComponents(this.img, out);
/*     */     }
/*     */     
/*     */     public String getImplicitFilter() {
/* 499 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBWInvert(boolean v) {
/* 504 */     this.bwinvert = v;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/ImageEncodingHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */