/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.xmlgraphics.util.io.ASCII85OutputStream;
/*     */ import org.apache.xmlgraphics.util.io.Finalizable;
/*     */ import org.apache.xmlgraphics.util.io.FlateEncodeOutputStream;
/*     */ import org.apache.xmlgraphics.util.io.RunLengthEncodeOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PSImageUtils
/*     */ {
/*     */   public static void writeImage(final byte[] img, Dimension imgDim, String imgDescription, Rectangle2D targetRect, final boolean isJPEG, ColorSpace colorSpace, PSGenerator gen) throws IOException {
/*  73 */     ImageEncoder encoder = new ImageEncoder() {
/*     */         public void writeTo(OutputStream out) throws IOException {
/*  75 */           out.write(img);
/*     */         }
/*     */         
/*     */         public String getImplicitFilter() {
/*  79 */           if (isJPEG) {
/*  80 */             return "<< >> /DCTDecode";
/*     */           }
/*  82 */           return null;
/*     */         }
/*     */       };
/*     */     
/*  86 */     writeImage(encoder, imgDim, imgDescription, targetRect, colorSpace, 8, false, gen);
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
/*     */   public static void writeImage(ImageEncoder encoder, Dimension imgDim, String imgDescription, Rectangle2D targetRect, ColorSpace colorSpace, int bitsPerComponent, boolean invertImage, PSGenerator gen) throws IOException {
/* 106 */     gen.saveGraphicsState();
/* 107 */     translateAndScale(gen, null, targetRect);
/*     */     
/* 109 */     gen.commentln("%AXGBeginBitmap: " + imgDescription);
/*     */     
/* 111 */     gen.writeln("{{");
/*     */ 
/*     */ 
/*     */     
/* 115 */     String implicitFilter = encoder.getImplicitFilter();
/* 116 */     if (implicitFilter != null) {
/* 117 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 118 */       gen.writeln("/Data RawData " + implicitFilter + " filter def");
/*     */     }
/* 120 */     else if (gen.getPSLevel() >= 3) {
/* 121 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 122 */       gen.writeln("/Data RawData /FlateDecode filter def");
/*     */     } else {
/* 124 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 125 */       gen.writeln("/Data RawData /RunLengthDecode filter def");
/*     */     } 
/*     */     
/* 128 */     PSDictionary imageDict = new PSDictionary();
/* 129 */     imageDict.put((K)"/DataSource", (V)"Data");
/* 130 */     imageDict.put((K)"/BitsPerComponent", (V)Integer.toString(bitsPerComponent));
/* 131 */     writeImageCommand(imageDict, imgDim, colorSpace, invertImage, gen);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     gen.writeln("} stopped {handleerror} if");
/* 137 */     gen.writeln("  RawData flushfile");
/* 138 */     gen.writeln("} exec");
/*     */     
/* 140 */     compressAndWriteBitmap(encoder, gen);
/*     */     
/* 142 */     gen.newLine();
/* 143 */     gen.commentln("%AXGEndBitmap");
/* 144 */     gen.restoreGraphicsState();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeImage(ImageEncoder encoder, Dimension imgDim, String imgDescription, Rectangle2D targetRect, ColorModel colorModel, PSGenerator gen) throws IOException {
/* 149 */     writeImage(encoder, imgDim, imgDescription, targetRect, colorModel, gen, (RenderedImage)null);
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
/*     */   public static void writeImage(ImageEncoder encoder, Dimension imgDim, String imgDescription, Rectangle2D targetRect, ColorModel colorModel, PSGenerator gen, RenderedImage ri) throws IOException {
/* 166 */     gen.saveGraphicsState();
/* 167 */     translateAndScale(gen, null, targetRect);
/* 168 */     gen.commentln("%AXGBeginBitmap: " + imgDescription);
/* 169 */     gen.writeln("{{");
/*     */     
/* 171 */     String implicitFilter = encoder.getImplicitFilter();
/* 172 */     if (implicitFilter != null) {
/* 173 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 174 */       gen.writeln("/Data RawData " + implicitFilter + " filter def");
/*     */     }
/* 176 */     else if (gen.getPSLevel() >= 3) {
/* 177 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 178 */       gen.writeln("/Data RawData /FlateDecode filter def");
/*     */     } else {
/* 180 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 181 */       gen.writeln("/Data RawData /RunLengthDecode filter def");
/*     */     } 
/*     */ 
/*     */     
/* 185 */     PSDictionary imageDict = new PSDictionary();
/* 186 */     imageDict.put((K)"/DataSource", (V)"Data");
/*     */     
/* 188 */     populateImageDictionary(imgDim, colorModel, imageDict);
/*     */     
/* 190 */     if (ri != null) {
/* 191 */       DataBuffer buffer = ri.getData().getDataBuffer();
/* 192 */       if (!(buffer instanceof java.awt.image.DataBufferByte)) {
/* 193 */         imageDict.put((K)"/BitsPerComponent", (V)Integer.valueOf(8));
/*     */       }
/*     */     } 
/* 196 */     writeImageCommand(imageDict, colorModel, gen);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     gen.writeln("} stopped {handleerror} if");
/* 204 */     gen.writeln("  RawData flushfile");
/* 205 */     gen.writeln("} exec");
/*     */     
/* 207 */     compressAndWriteBitmap(encoder, gen);
/*     */     
/* 209 */     gen.newLine();
/* 210 */     gen.commentln("%AXGEndBitmap");
/* 211 */     gen.restoreGraphicsState();
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
/*     */   public static void writeImage(ImageEncoder encoder, Dimension imgDim, String imgDescription, Rectangle2D targetRect, ColorModel colorModel, PSGenerator gen, RenderedImage ri, Color maskColor) throws IOException {
/* 229 */     gen.saveGraphicsState();
/* 230 */     translateAndScale(gen, null, targetRect);
/* 231 */     gen.commentln("%AXGBeginBitmap: " + imgDescription);
/* 232 */     gen.writeln("{{");
/*     */     
/* 234 */     String implicitFilter = encoder.getImplicitFilter();
/* 235 */     if (implicitFilter != null) {
/* 236 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 237 */       gen.writeln("/Data RawData " + implicitFilter + " filter def");
/*     */     }
/* 239 */     else if (gen.getPSLevel() >= 3) {
/* 240 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 241 */       gen.writeln("/Data RawData /FlateDecode filter def");
/*     */     } else {
/* 243 */       gen.writeln("/RawData currentfile /ASCII85Decode filter def");
/* 244 */       gen.writeln("/Data RawData /RunLengthDecode filter def");
/*     */     } 
/*     */ 
/*     */     
/* 248 */     PSDictionary imageDict = new PSDictionary();
/* 249 */     imageDict.put((K)"/DataSource", (V)"Data");
/*     */     
/* 251 */     populateImageDictionary(imgDim, colorModel, imageDict, maskColor);
/*     */     
/* 253 */     if (ri != null) {
/* 254 */       DataBuffer buffer = ri.getData().getDataBuffer();
/* 255 */       if (!(buffer instanceof java.awt.image.DataBufferByte)) {
/* 256 */         imageDict.put((K)"/BitsPerComponent", (V)Integer.valueOf(8));
/*     */       }
/*     */     } 
/* 259 */     writeImageCommand(imageDict, colorModel, gen);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     gen.writeln("} stopped {handleerror} if");
/* 267 */     gen.writeln("  RawData flushfile");
/* 268 */     gen.writeln("} exec");
/*     */     
/* 270 */     compressAndWriteBitmap(encoder, gen);
/*     */     
/* 272 */     gen.newLine();
/* 273 */     gen.commentln("%AXGEndBitmap");
/* 274 */     gen.restoreGraphicsState();
/*     */   }
/*     */ 
/*     */   
/*     */   private static ColorModel populateImageDictionary(Dimension imgDim, ColorModel colorModel, PSDictionary imageDict) {
/* 279 */     imageDict.put((K)"/ImageType", (V)"1");
/* 280 */     colorModel = writeImageDictionary(imgDim, imageDict, colorModel);
/* 281 */     return colorModel;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ColorModel populateImageDictionary(Dimension imgDim, ColorModel colorModel, PSDictionary imageDict, Color maskColor) {
/* 286 */     imageDict.put((K)"/ImageType", (V)"4");
/*     */     
/* 288 */     colorModel = writeImageDictionary(imgDim, imageDict, colorModel);
/* 289 */     imageDict.put((K)"/MaskColor", (V)String.format("[ %d %d %d ]", new Object[] { Integer.valueOf(maskColor.getRed()), Integer.valueOf(maskColor.getGreen()), Integer.valueOf(maskColor.getBlue()) }));
/*     */     
/* 291 */     return colorModel;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ColorModel writeImageDictionary(Dimension imgDim, PSDictionary imageDict, ColorModel colorModel) {
/* 296 */     String w = Integer.toString(imgDim.width);
/* 297 */     String h = Integer.toString(imgDim.height);
/* 298 */     imageDict.put((K)"/Width", (V)w);
/* 299 */     imageDict.put((K)"/Height", (V)h);
/*     */     
/* 301 */     boolean invertColors = false;
/* 302 */     String decodeArray = getDecodeArray(colorModel.getNumColorComponents(), invertColors);
/* 303 */     int bitsPerComp = colorModel.getComponentSize(0);
/*     */ 
/*     */     
/* 306 */     imageDict.put((K)"/ImageMatrix", (V)("[" + w + " 0 0 " + h + " 0 0]"));
/*     */     
/* 308 */     if (colorModel instanceof IndexColorModel) {
/* 309 */       IndexColorModel indexColorModel = (IndexColorModel)colorModel;
/* 310 */       int c = indexColorModel.getMapSize();
/* 311 */       int hival = c - 1;
/* 312 */       if (hival > 4095) {
/* 313 */         throw new UnsupportedOperationException("hival must not go beyond 4095");
/*     */       }
/* 315 */       bitsPerComp = indexColorModel.getPixelSize();
/* 316 */       int ceiling = (int)Math.pow(2.0D, bitsPerComp) - 1;
/* 317 */       decodeArray = "[0 " + ceiling + "]";
/*     */     } 
/* 319 */     imageDict.put((K)"/BitsPerComponent", (V)Integer.toString(bitsPerComp));
/* 320 */     imageDict.put((K)"/Decode", (V)decodeArray);
/* 321 */     return colorModel;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getDecodeArray(int numComponents, boolean invertColors) {
/* 326 */     StringBuffer sb = new StringBuffer("[");
/* 327 */     for (int i = 0; i < numComponents; i++) {
/* 328 */       if (i > 0) {
/* 329 */         sb.append(" ");
/*     */       }
/* 331 */       if (invertColors) {
/* 332 */         sb.append("1 0");
/*     */       } else {
/* 334 */         sb.append("0 1");
/*     */       } 
/*     */     } 
/* 337 */     sb.append("]");
/* 338 */     String decodeArray = sb.toString();
/* 339 */     return decodeArray;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void prepareColorspace(PSGenerator gen, ColorSpace colorSpace) throws IOException {
/* 344 */     gen.writeln(getColorSpaceName(colorSpace) + " setcolorspace");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void prepareColorSpace(PSGenerator gen, ColorModel cm) throws IOException {
/* 349 */     if (cm instanceof IndexColorModel) {
/* 350 */       ColorSpace cs = cm.getColorSpace();
/* 351 */       IndexColorModel im = (IndexColorModel)cm;
/*     */       
/* 353 */       int c = im.getMapSize();
/* 354 */       int[] palette = new int[c];
/* 355 */       im.getRGBs(palette);
/* 356 */       byte[] reds = new byte[c];
/* 357 */       byte[] greens = new byte[c];
/* 358 */       byte[] blues = new byte[c];
/* 359 */       im.getReds(reds);
/* 360 */       im.getGreens(greens);
/* 361 */       im.getBlues(blues);
/* 362 */       int hival = c - 1;
/* 363 */       if (hival > 4095) {
/* 364 */         throw new UnsupportedOperationException("hival must not go beyond 4095");
/*     */       }
/* 366 */       boolean isDeviceGray = (Arrays.equals(reds, blues) && Arrays.equals(blues, greens));
/* 367 */       if (isDeviceGray) {
/* 368 */         gen.write("[/Indexed /DeviceGray");
/*     */       } else {
/* 370 */         gen.write("[/Indexed " + getColorSpaceName(cs));
/*     */       } 
/* 372 */       gen.writeln(" " + Integer.toString(hival));
/* 373 */       gen.write("  <");
/* 374 */       if (isDeviceGray) {
/* 375 */         gen.write(toHexString(blues));
/*     */       } else {
/* 377 */         for (int i = 0; i < c; i++) {
/* 378 */           if (i > 0) {
/* 379 */             if (i % 8 == 0) {
/* 380 */               gen.newLine();
/* 381 */               gen.write("   ");
/*     */             } else {
/* 383 */               gen.write(" ");
/*     */             } 
/*     */           }
/* 386 */           gen.write(rgb2Hex(palette[i]));
/*     */         } 
/*     */       } 
/* 389 */       gen.writeln(">");
/* 390 */       gen.writeln("] setcolorspace");
/*     */     } else {
/* 392 */       gen.writeln(getColorSpaceName(cm.getColorSpace()) + " setcolorspace");
/*     */     } 
/*     */   }
/*     */   
/*     */   static String toHexString(byte[] color) {
/* 397 */     char[] hexChars = new char[color.length * 2];
/*     */     
/* 399 */     for (int i = 0; i < color.length; i++) {
/* 400 */       int x = color[i] & 0xFF;
/* 401 */       hexChars[i * 2] = HEX[x >>> 4];
/* 402 */       hexChars[i * 2 + 1] = HEX[x & 0xF];
/*     */     } 
/* 404 */     return new String(hexChars);
/*     */   }
/*     */ 
/*     */   
/*     */   static void writeImageCommand(RenderedImage img, PSDictionary imageDict, PSGenerator gen) throws IOException {
/* 409 */     ImageEncodingHelper helper = new ImageEncodingHelper(img, true);
/* 410 */     ColorModel cm = helper.getEncodedColorModel();
/* 411 */     Dimension imgDim = new Dimension(img.getWidth(), img.getHeight());
/*     */     
/* 413 */     populateImageDictionary(imgDim, cm, imageDict);
/* 414 */     writeImageCommand(imageDict, cm, gen);
/*     */   }
/*     */ 
/*     */   
/*     */   static void writeImageCommand(PSDictionary imageDict, ColorModel cm, PSGenerator gen) throws IOException {
/* 419 */     prepareColorSpace(gen, cm);
/* 420 */     gen.write(imageDict.toString());
/* 421 */     gen.writeln(" image");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static void writeImageCommand(PSDictionary imageDict, Dimension imgDim, ColorSpace colorSpace, boolean invertImage, PSGenerator gen) throws IOException {
/* 427 */     imageDict.put((K)"/ImageType", (V)"1");
/* 428 */     imageDict.put((K)"/Width", (V)Integer.toString(imgDim.width));
/* 429 */     imageDict.put((K)"/Height", (V)Integer.toString(imgDim.height));
/* 430 */     String decodeArray = getDecodeArray(colorSpace.getNumComponents(), invertImage);
/* 431 */     imageDict.put((K)"/Decode", (V)decodeArray);
/*     */     
/* 433 */     imageDict.put((K)"/ImageMatrix", (V)("[" + imgDim.width + " 0 0 " + imgDim.height + " 0 0]"));
/*     */     
/* 435 */     prepareColorspace(gen, colorSpace);
/* 436 */     gen.write(imageDict.toString());
/* 437 */     gen.writeln(" image");
/*     */   }
/*     */   
/* 440 */   private static final char[] HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String rgb2Hex(int rgb) {
/* 446 */     StringBuffer sb = new StringBuffer();
/* 447 */     for (int i = 5; i >= 0; i--) {
/* 448 */       int shift = i * 4;
/* 449 */       int n = (rgb & 15 << shift) >> shift;
/* 450 */       sb.append(HEX[n % 16]);
/*     */     } 
/* 452 */     return sb.toString();
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
/*     */   public static void renderBitmapImage(RenderedImage img, float x, float y, float w, float h, PSGenerator gen) throws IOException {
/* 468 */     Rectangle2D targetRect = new Rectangle2D.Double(x, y, w, h);
/* 469 */     ImageEncoder encoder = ImageEncodingHelper.createRenderedImageEncoder(img);
/* 470 */     Dimension imgDim = new Dimension(img.getWidth(), img.getHeight());
/* 471 */     String imgDescription = img.getClass().getName();
/* 472 */     ImageEncodingHelper helper = new ImageEncodingHelper(img);
/* 473 */     ColorModel cm = helper.getEncodedColorModel();
/*     */     
/* 475 */     writeImage(encoder, imgDim, imgDescription, targetRect, cm, gen, img);
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
/*     */   public static PSResource writeReusableImage(final byte[] img, Dimension imgDim, String formName, String imageDescription, final boolean isJPEG, ColorSpace colorSpace, PSGenerator gen) throws IOException {
/* 497 */     ImageEncoder encoder = new ImageEncoder() {
/*     */         public void writeTo(OutputStream out) throws IOException {
/* 499 */           out.write(img);
/*     */         }
/*     */         public String getImplicitFilter() {
/* 502 */           if (isJPEG) {
/* 503 */             return "<< >> /DCTDecode";
/*     */           }
/* 505 */           return null;
/*     */         }
/*     */       };
/*     */     
/* 509 */     return writeReusableImage(encoder, imgDim, formName, imageDescription, colorSpace, false, gen);
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
/*     */   protected static PSResource writeReusableImage(ImageEncoder encoder, Dimension imgDim, String formName, String imageDescription, ColorSpace colorSpace, boolean invertImage, PSGenerator gen) throws IOException {
/*     */     String additionalFilters, dataSource;
/* 532 */     if (gen.getPSLevel() < 2) {
/* 533 */       throw new UnsupportedOperationException("Reusable images requires at least Level 2 PostScript");
/*     */     }
/*     */     
/* 536 */     String dataName = formName + ":Data";
/* 537 */     gen.writeDSCComment("BeginResource", formName);
/* 538 */     if (imageDescription != null) {
/* 539 */       gen.writeDSCComment("Title", imageDescription);
/*     */     }
/*     */ 
/*     */     
/* 543 */     String implicitFilter = encoder.getImplicitFilter();
/* 544 */     if (implicitFilter != null) {
/* 545 */       additionalFilters = "/ASCII85Decode filter " + implicitFilter + " filter";
/*     */     }
/* 547 */     else if (gen.getPSLevel() >= 3) {
/* 548 */       additionalFilters = "/ASCII85Decode filter /FlateDecode filter";
/*     */     } else {
/* 550 */       additionalFilters = "/ASCII85Decode filter /RunLengthDecode filter";
/*     */     } 
/*     */ 
/*     */     
/* 554 */     gen.writeln("/" + formName);
/* 555 */     gen.writeln("<< /FormType 1");
/* 556 */     gen.writeln("  /BBox [0 0 " + imgDim.width + " " + imgDim.height + "]");
/* 557 */     gen.writeln("  /Matrix [1 0 0 1 0 0]");
/* 558 */     gen.writeln("  /PaintProc {");
/* 559 */     gen.writeln("    pop");
/* 560 */     gen.writeln("    gsave");
/* 561 */     if (gen.getPSLevel() == 2) {
/* 562 */       gen.writeln("    userdict /i 0 put");
/*     */     } else {
/* 564 */       gen.writeln("    " + dataName + " 0 setfileposition");
/*     */     } 
/*     */     
/* 567 */     if (gen.getPSLevel() == 2) {
/* 568 */       dataSource = "{ " + dataName + " i get /i i 1 add store } bind";
/*     */     } else {
/* 570 */       dataSource = dataName;
/*     */     } 
/* 572 */     PSDictionary imageDict = new PSDictionary();
/* 573 */     imageDict.put((K)"/DataSource", (V)dataSource);
/* 574 */     imageDict.put((K)"/BitsPerComponent", (V)Integer.toString(8));
/* 575 */     writeImageCommand(imageDict, imgDim, colorSpace, invertImage, gen);
/* 576 */     gen.writeln("    grestore");
/* 577 */     gen.writeln("  } bind");
/* 578 */     gen.writeln(">> def");
/* 579 */     gen.writeln("/" + dataName + " currentfile");
/* 580 */     gen.writeln(additionalFilters);
/* 581 */     if (gen.getPSLevel() == 2) {
/*     */       
/* 583 */       gen.writeln("{ /temp exch def [ { temp 16384 string readstring not {exit } if } loop ] } exec");
/*     */     } else {
/*     */       
/* 586 */       gen.writeln("/ReusableStreamDecode filter");
/*     */     } 
/* 588 */     compressAndWriteBitmap(encoder, gen);
/* 589 */     gen.writeln("def");
/* 590 */     gen.writeDSCComment("EndResource");
/* 591 */     PSResource res = new PSResource("form", formName);
/* 592 */     gen.getResourceTracker().registerSuppliedResource(res);
/* 593 */     return res;
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
/*     */   public static void paintReusableImage(String formName, Rectangle2D targetRect, PSGenerator gen) throws IOException {
/* 609 */     PSResource form = new PSResource("form", formName);
/* 610 */     paintForm(form, null, targetRect, gen);
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
/*     */   public static void paintForm(PSResource form, Rectangle2D targetRect, PSGenerator gen) throws IOException {
/* 626 */     paintForm(form, null, targetRect, gen);
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
/*     */   public static void paintForm(PSResource form, Dimension2D formDimensions, Rectangle2D targetRect, PSGenerator gen) throws IOException {
/* 642 */     gen.saveGraphicsState();
/* 643 */     translateAndScale(gen, formDimensions, targetRect);
/* 644 */     gen.writeln(form.getName() + " execform");
/*     */     
/* 646 */     gen.getResourceTracker().notifyResourceUsageOnPage(form);
/* 647 */     gen.restoreGraphicsState();
/*     */   }
/*     */   
/*     */   private static String getColorSpaceName(ColorSpace colorSpace) {
/* 651 */     if (colorSpace.getType() == 9)
/* 652 */       return "/DeviceCMYK"; 
/* 653 */     if (colorSpace.getType() == 6) {
/* 654 */       return "/DeviceGray";
/*     */     }
/* 656 */     return "/DeviceRGB";
/*     */   }
/*     */ 
/*     */   
/*     */   static void compressAndWriteBitmap(ImageEncoder encoder, PSGenerator gen) throws IOException {
/*     */     RunLengthEncodeOutputStream runLengthEncodeOutputStream;
/* 662 */     OutputStream out = gen.getOutputStream();
/* 663 */     ASCII85OutputStream aSCII85OutputStream = new ASCII85OutputStream(out);
/* 664 */     String implicitFilter = encoder.getImplicitFilter();
/* 665 */     if (implicitFilter == null) {
/*     */       FlateEncodeOutputStream flateEncodeOutputStream;
/*     */       
/* 668 */       if (gen.getPSLevel() >= 3) {
/* 669 */         flateEncodeOutputStream = new FlateEncodeOutputStream((OutputStream)aSCII85OutputStream);
/*     */       } else {
/* 671 */         runLengthEncodeOutputStream = new RunLengthEncodeOutputStream((OutputStream)flateEncodeOutputStream);
/*     */       } 
/*     */     } 
/* 674 */     encoder.writeTo((OutputStream)runLengthEncodeOutputStream);
/* 675 */     if (runLengthEncodeOutputStream instanceof Finalizable) {
/* 676 */       ((Finalizable)runLengthEncodeOutputStream).finalizeStream();
/*     */     } else {
/* 678 */       runLengthEncodeOutputStream.flush();
/*     */     } 
/* 680 */     gen.newLine();
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
/*     */   public static void translateAndScale(PSGenerator gen, Dimension2D imageDimensions, Rectangle2D targetRect) throws IOException {
/* 694 */     gen.writeln(gen.formatDouble(targetRect.getX()) + " " + gen.formatDouble(targetRect.getY()) + " translate");
/*     */     
/* 696 */     if (imageDimensions == null) {
/* 697 */       imageDimensions = new Dimension(1, 1);
/*     */     }
/* 699 */     double sx = targetRect.getWidth() / imageDimensions.getWidth();
/* 700 */     double sy = targetRect.getHeight() / imageDimensions.getHeight();
/* 701 */     if (sx != 1.0D || sy != 1.0D) {
/* 702 */       gen.writeln(gen.formatDouble(sx) + " " + gen.formatDouble(sy) + " scale");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] getRGB(RenderedImage img, int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
/*     */     Object data;
/* 723 */     Raster raster = img.getData();
/* 724 */     int yoff = offset;
/*     */ 
/*     */     
/* 727 */     int nbands = raster.getNumBands();
/* 728 */     int dataType = raster.getDataBuffer().getDataType();
/* 729 */     switch (dataType) {
/*     */       case 0:
/* 731 */         data = new byte[nbands];
/*     */         break;
/*     */       case 1:
/* 734 */         data = new short[nbands];
/*     */         break;
/*     */       case 3:
/* 737 */         data = new int[nbands];
/*     */         break;
/*     */       case 4:
/* 740 */         data = new float[nbands];
/*     */         break;
/*     */       case 5:
/* 743 */         data = new double[nbands];
/*     */         break;
/*     */       default:
/* 746 */         throw new IllegalArgumentException("Unknown data buffer type: " + dataType);
/*     */     } 
/*     */ 
/*     */     
/* 750 */     if (rgbArray == null) {
/* 751 */       rgbArray = new int[offset + h * scansize];
/*     */     }
/*     */     
/* 754 */     ColorModel colorModel = img.getColorModel();
/* 755 */     for (int y = startY; y < startY + h; y++, yoff += scansize) {
/* 756 */       int off = yoff;
/* 757 */       for (int x = startX; x < startX + w; x++) {
/* 758 */         rgbArray[off++] = colorModel.getRGB(raster.getDataElements(x, y, data));
/*     */       }
/*     */     } 
/*     */     
/* 762 */     return rgbArray;
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
/*     */   public static void renderEPS(byte[] rawEPS, String name, float x, float y, float w, float h, float bboxx, float bboxy, float bboxw, float bboxh, PSGenerator gen) throws IOException {
/* 785 */     renderEPS(new ByteArrayInputStream(rawEPS), name, new Rectangle2D.Float(x, y, w, h), new Rectangle2D.Float(bboxx, bboxy, bboxw, bboxh), gen);
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
/*     */   public static void renderEPS(InputStream in, String name, Rectangle2D viewport, Rectangle2D bbox, PSGenerator gen) throws IOException {
/* 803 */     gen.getResourceTracker().notifyResourceUsageOnPage(PSProcSets.EPS_PROCSET);
/* 804 */     gen.writeln("%AXGBeginEPS: " + name);
/* 805 */     gen.writeln("BeginEPSF");
/*     */     
/* 807 */     gen.writeln(gen.formatDouble(viewport.getX()) + " " + gen.formatDouble(viewport.getY()) + " translate");
/*     */     
/* 809 */     gen.writeln("0 " + gen.formatDouble(viewport.getHeight()) + " translate");
/* 810 */     gen.writeln("1 -1 scale");
/* 811 */     double sx = viewport.getWidth() / bbox.getWidth();
/* 812 */     double sy = viewport.getHeight() / bbox.getHeight();
/* 813 */     if (sx != 1.0D || sy != 1.0D) {
/* 814 */       gen.writeln(gen.formatDouble(sx) + " " + gen.formatDouble(sy) + " scale");
/*     */     }
/* 816 */     if (bbox.getX() != 0.0D || bbox.getY() != 0.0D) {
/* 817 */       gen.writeln(gen.formatDouble(-bbox.getX()) + " " + gen.formatDouble(-bbox.getY()) + " translate");
/*     */     }
/*     */     
/* 820 */     gen.writeln(gen.formatDouble(bbox.getX()) + " " + gen.formatDouble(bbox.getY()) + " " + gen.formatDouble(bbox.getWidth()) + " " + gen.formatDouble(bbox.getHeight()) + " re clip");
/*     */ 
/*     */ 
/*     */     
/* 824 */     gen.writeln("newpath");
/*     */     
/* 826 */     PSResource res = new PSResource("file", name);
/* 827 */     gen.getResourceTracker().registerSuppliedResource(res);
/* 828 */     gen.getResourceTracker().notifyResourceUsageOnPage(res);
/* 829 */     gen.writeDSCComment("BeginDocument", res.getName());
/* 830 */     IOUtils.copy(in, gen.getOutputStream());
/* 831 */     gen.newLine();
/* 832 */     gen.writeDSCComment("EndDocument");
/* 833 */     gen.writeln("EndEPSF");
/* 834 */     gen.writeln("%AXGEndEPS");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSImageUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */