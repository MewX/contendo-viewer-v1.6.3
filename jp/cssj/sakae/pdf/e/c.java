/*     */ package jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.stream.FileCacheImageInputStream;
/*     */ import javax.imageio.stream.FileCacheImageOutputStream;
/*     */ import javax.imageio.stream.FileImageInputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.sakae.b.c.a;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.e.b;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.f.b;
/*     */ import jp.cssj.sakae.pdf.g.a.a;
/*     */ import jp.cssj.sakae.pdf.g.a.b;
/*     */ import jp.cssj.sakae.pdf.g.c.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class c
/*     */ {
/*  48 */   private final Logger a = Logger.getLogger(c.class.getName());
/*     */ 
/*     */   
/*     */   private final Map<String, b> b;
/*     */   
/*     */   private final j c;
/*     */   
/*     */   private final p d;
/*     */   
/*     */   private final b e;
/*     */   
/*  59 */   private final Map<URI, b> f = new HashMap<>();
/*     */   
/*  61 */   private int g = 0;
/*     */ 
/*     */   
/*     */   public c(Map<String, b> nameToResourceRef, j objectsFlow, p xref, b params) throws IOException {
/*  65 */     this.d = xref;
/*  66 */     this.b = nameToResourceRef;
/*  67 */     this.c = objectsFlow;
/*  68 */     this.e = params;
/*     */   }
/*     */   public b a(b source) throws IOException {
/*     */     ImageInputStream in;
/*  72 */     URI uri = source.d();
/*  73 */     b pdfImage = this.f.get(uri);
/*  74 */     if (pdfImage != null) {
/*  75 */       return pdfImage;
/*     */     }
/*     */     
/*  78 */     if (source.k()) {
/*  79 */       in = new FileImageInputStream(this, source.l())
/*     */         {
/*     */           
/*     */           public void flushBefore(long pos) throws IOException {}
/*     */         };
/*     */     } else {
/*  85 */       in = new FileCacheImageInputStream(this, source.h(), null)
/*     */         {
/*     */           public void flushBefore(long pos) throws IOException {}
/*     */         };
/*     */     } 
/*     */     
/*     */     try {
/*  92 */       pdfImage = a(in, null);
/*  93 */       this.f.put(uri, pdfImage);
/*     */     } finally {
/*  95 */       in.close();
/*     */     } 
/*  97 */     return pdfImage;
/*     */   }
/*     */   
/*     */   public b a(BufferedImage image) throws IOException {
/* 101 */     return a(null, image);
/*     */   }
/*     */   
/*     */   private b a(ImageInputStream imageIn, BufferedImage image) throws IOException {
/*     */     jp.cssj.sakae.pdf.d.c pdfImage;
/*     */     ImageReader ir;
/* 107 */     if (imageIn != null) {
/* 108 */       Iterator<?> i = ImageIO.getImageReaders(imageIn);
/* 109 */       if (i != null && i.hasNext()) {
/* 110 */         ir = (ImageReader)i.next();
/* 111 */         ir.setInput(imageIn);
/*     */       } else {
/* 113 */         throw new IOException("画像ファイル内に読み込み可能な画像がありません:" + String.valueOf(i));
/*     */       } 
/*     */     } else {
/* 116 */       ir = null;
/*     */     } 
/*     */     
/*     */     try {
/*     */       int width, height;
/*     */       
/* 122 */       short colorMode = this.e.l();
/* 123 */       short streamCompression = this.e.b();
/* 124 */       short imageCompression = this.e.d();
/* 125 */       int pdfVersion = this.e.h();
/* 126 */       boolean softMaskSupport = (pdfVersion >= 1400 && pdfVersion != 1412 && pdfVersion != 1421);
/*     */       
/* 128 */       boolean jpeg2000Support = (pdfVersion >= 1500);
/* 129 */       short imageType = 0;
/*     */       
/* 131 */       if (ir != null && colorMode == 0) {
/* 132 */         String formatName = ir.getFormatName();
/* 133 */         if (this.e.c() == 0) {
/*     */           
/* 135 */           if (formatName.equalsIgnoreCase("jpeg")) {
/* 136 */             imageType = 1;
/* 137 */           } else if (jpeg2000Support && 
/* 138 */             formatName.equalsIgnoreCase("jpeg 2000")) {
/* 139 */             imageType = 2;
/*     */           }
/*     */         
/* 142 */         } else if (imageCompression == 1 && formatName
/* 143 */           .equalsIgnoreCase("jpeg")) {
/* 144 */           imageType = 1;
/* 145 */         } else if (imageCompression == 2 && 
/* 146 */           formatName.equalsIgnoreCase("jpeg 2000")) {
/* 147 */           imageType = 2;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 152 */       boolean iccErrorHuck = false;
/* 153 */       boolean iccGray = false;
/* 154 */       int maxWidth = this.e.f();
/* 155 */       int maxHeight = this.e.g();
/*     */       
/* 157 */       if (image == null) {
/*     */         try {
/* 159 */           width = ir.getWidth(0);
/* 160 */           height = ir.getHeight(0);
/* 161 */         } catch (RuntimeException e) {
/*     */ 
/*     */           
/* 164 */           this.a.log(Level.WARNING, "Error(Probably JVM bug?)", e);
/* 165 */           imageIn.seek(2L);
/*     */           while (true) {
/* 167 */             iccErrorHuck = true;
/* 168 */             int code = imageIn.readShort() & 0xFFFF;
/* 169 */             if (code >> 8 != 255) {
/* 170 */               throw e;
/*     */             }
/* 172 */             if ((code >= 65472 && code <= 65475) || (code >= 65477 && code <= 65479) || (code >= 65481 && code <= 65483) || (code >= 65485 && code <= 65487)) {
/*     */ 
/*     */               
/* 175 */               imageIn.skipBytes(3);
/* 176 */               height = imageIn.readShort() & 0xFFFF;
/* 177 */               width = imageIn.readShort() & 0xFFFF;
/* 178 */               byte comps = imageIn.readByte();
/* 179 */               iccGray = (comps == 1);
/*     */               break;
/*     */             } 
/* 182 */             int length = imageIn.readShort() & 0xFFFF;
/* 183 */             if (length <= 2) {
/* 184 */               throw e;
/*     */             }
/* 186 */             imageIn.skipBytes(length - 2);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 190 */         width = image.getWidth();
/* 191 */         height = image.getHeight();
/*     */       } 
/* 193 */       boolean resize = ((maxWidth > 0 && width > maxWidth) || (maxHeight > 0 && height > maxHeight));
/* 194 */       double orgWidth = width;
/* 195 */       double orgHeight = height;
/* 196 */       if (resize || imageType == 0) {
/*     */         
/* 198 */         if (ir != null) {
/* 199 */           imageIn.seek(0L);
/* 200 */           image = a.a(ir, imageIn);
/*     */         } 
/* 202 */         if (resize) {
/*     */           
/* 204 */           int type = image.getType();
/* 205 */           if (maxWidth > 0 && width > maxWidth)
/*     */           {
/*     */             
/* 208 */             width = maxWidth;
/*     */           }
/* 210 */           if (maxHeight > 0 && height > maxHeight)
/*     */           {
/*     */             
/* 213 */             height = maxHeight;
/*     */           }
/* 215 */           Image scaled = image.getScaledInstance(width, height, 4);
/*     */           try {
/* 217 */             image.flush();
/* 218 */             switch (type) {
/*     */               case 12:
/*     */               case 13:
/* 221 */                 image = new BufferedImage(width, height, type, (IndexColorModel)image.getColorModel());
/*     */                 break;
/*     */               
/*     */               case 1:
/*     */               case 2:
/*     */               case 3:
/*     */               case 4:
/*     */               case 5:
/*     */               case 6:
/*     */               case 7:
/*     */               case 8:
/*     */               case 9:
/*     */               case 10:
/*     */               case 11:
/* 235 */                 image = new BufferedImage(width, height, type);
/*     */                 break;
/*     */ 
/*     */               
/*     */               default:
/* 240 */                 image = new BufferedImage(width, height, image.getColorModel().hasAlpha() ? 2 : 1);
/*     */                 break;
/*     */             } 
/*     */             
/* 244 */             Graphics2D g2d = image.createGraphics();
/* 245 */             g2d.drawImage(scaled, 0, 0, null);
/*     */           } finally {
/* 247 */             scaled.flush();
/*     */           } 
/* 249 */           imageType = 0;
/*     */         } 
/*     */ 
/*     */         
/* 253 */         if (colorMode == 1 && image.getType() != 10 && image
/* 254 */           .getType() != 11) {
/* 255 */           for (int y = 0; y < height; y++) {
/* 256 */             for (int x = 0; x < width; x++) {
/* 257 */               int rgb = image.getRGB(x, y);
/* 258 */               float r = (rgb >> 16 & 0xFF) / 255.0F;
/* 259 */               float g = (rgb >> 8 & 0xFF) / 255.0F;
/* 260 */               float f1 = (rgb & 0xFF) / 255.0F;
/* 261 */               float gr = b.a(r, g, f1);
/* 262 */               int octet = (int)(gr * 255.0F);
/* 263 */               rgb = rgb & 0xFF000000 | octet << 16 | octet << 8 | octet;
/* 264 */               image.setRGB(x, y, rgb);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       try {
/* 270 */         String name = "I" + this.g;
/* 271 */         pdfImage = new jp.cssj.sakae.pdf.d.c(name, orgWidth, orgHeight);
/*     */         
/* 273 */         b imageRef = this.d.a();
/* 274 */         this.b.put(name, imageRef);
/*     */         
/* 276 */         this.c.a(imageRef);
/* 277 */         this.c.g();
/*     */         
/* 279 */         this.c.a("Type");
/* 280 */         this.c.a("XObject");
/* 281 */         this.c.m();
/*     */         
/* 283 */         this.c.a("Subtype");
/* 284 */         this.c.a("Image");
/* 285 */         this.c.m();
/*     */         
/* 287 */         this.c.a("Name");
/* 288 */         this.c.a(name);
/* 289 */         this.c.m();
/*     */         
/* 291 */         this.c.a("Width");
/* 292 */         this.c.a(width);
/* 293 */         this.c.m();
/*     */         
/* 295 */         this.c.a("Height");
/* 296 */         this.c.a(height);
/* 297 */         this.c.m();
/*     */         
/* 299 */         if (imageType != 0) {
/*     */           try {
/*     */             boolean srcGray; b b1;
/* 302 */             this.c.a("BitsPerComponent");
/* 303 */             this.c.a(8);
/* 304 */             this.c.m();
/*     */ 
/*     */             
/* 307 */             if (iccErrorHuck) {
/* 308 */               srcGray = iccGray;
/*     */             } else {
/* 310 */               Iterator<?> itr = ir.getImageTypes(0);
/* 311 */               ImageTypeSpecifier its = (ImageTypeSpecifier)itr.next();
/* 312 */               ColorModel cm = its.getColorModel();
/* 313 */               srcGray = (cm.getNumComponents() == 1);
/*     */             } 
/*     */             
/* 316 */             this.c.a("ColorSpace");
/* 317 */             this.c.a(srcGray ? "DeviceGray" : "DeviceRGB");
/* 318 */             this.c.m();
/*     */             
/* 320 */             this.c.a("Filter");
/* 321 */             this.c.i();
/* 322 */             switch (streamCompression) {
/*     */               case 2:
/* 324 */                 this.c.a("ASCII85Decode");
/*     */                 break;
/*     */               case 0:
/* 327 */                 this.c.a("ASCIIHexDecode");
/*     */                 break;
/*     */             } 
/* 330 */             switch (imageType) {
/*     */               case 1:
/* 332 */                 this.c.a("DCTDecode");
/*     */                 break;
/*     */               case 2:
/* 335 */                 this.c.a("JPXDecode");
/*     */                 break;
/*     */               default:
/* 338 */                 throw new IllegalStateException();
/*     */             } 
/* 340 */             this.c.j();
/* 341 */             this.c.m();
/*     */             
/* 343 */             OutputStream out = this.c.b((short)0); try {
/*     */               a a;
/* 345 */               switch (streamCompression) {
/*     */                 case 2:
/* 347 */                   a = new a(out);
/*     */                   break;
/*     */                 case 0:
/* 350 */                   b1 = new b((OutputStream)a);
/*     */                   break;
/*     */               } 
/* 353 */               imageIn.seek(0L);
/* 354 */               byte[] buff = this.c.b(); int len;
/* 355 */               for (len = imageIn.read(buff); len != -1; len = imageIn.read(buff)) {
/* 356 */                 b1.write(buff, 0, len);
/*     */               }
/*     */             } finally {
/* 359 */               b1.close();
/*     */             } 
/*     */           } finally {
/* 362 */             this.c.a();
/*     */           } 
/*     */         } else {
/*     */           b imageMaskRef; ColorModel cm; try {
/*     */             ImageWriter iw; ImageWriteParam iwParams; Iterator<?> i; a a1; DeflaterOutputStream deflaterOutputStream2; b b1; DeflaterOutputStream deflaterOutputStream1; a a; BufferedImage ximage; Raster raster;
/*     */             Object pixel;
/*     */             int y;
/* 369 */             cm = image.getColorModel();
/*     */             
/* 371 */             if (width + height > this.e.e()) {
/* 372 */               imageType = imageCompression;
/*     */             }
/*     */ 
/*     */             
/* 376 */             switch (imageType) {
/*     */               case 0:
/* 378 */                 iw = null;
/* 379 */                 iwParams = null;
/*     */                 break;
/*     */               case 1:
/* 382 */                 i = ImageIO.getImageWritersByFormatName("jpeg");
/* 383 */                 if (i == null || !i.hasNext()) {
/* 384 */                   throw new IOException("この環境ではJPEGの出力をサポートしていません。");
/*     */                 }
/* 386 */                 iw = (ImageWriter)i.next();
/* 387 */                 iwParams = iw.getDefaultWriteParam();
/* 388 */                 iwParams.setCompressionMode(2);
/* 389 */                 iwParams.setCompressionQuality(0.8F);
/*     */                 break;
/*     */               
/*     */               case 2:
/* 393 */                 i = ImageIO.getImageWritersByFormatName("jpeg 2000");
/* 394 */                 if (i == null || !i.hasNext()) {
/* 395 */                   throw new IOException("JPEG2000を出力するにはJava Advanced Imaging Image I/O Tools(JAI-ImageIO)が必要です。");
/*     */                 }
/*     */                 
/* 398 */                 iw = (ImageWriter)i.next();
/* 399 */                 iwParams = null;
/*     */                 break;
/*     */               default:
/* 402 */                 throw new IllegalStateException();
/*     */             } 
/*     */             
/* 405 */             if (cm.hasAlpha()) {
/*     */               
/* 407 */               imageMaskRef = this.d.a();
/* 408 */               if (softMaskSupport) {
/* 409 */                 this.c.a("SMask");
/*     */               } else {
/* 411 */                 this.c.a("Mask");
/*     */               } 
/* 413 */               this.c.b(imageMaskRef);
/* 414 */               this.c.m();
/*     */             } else {
/*     */               
/* 417 */               imageMaskRef = null;
/*     */             } 
/*     */             
/* 420 */             this.c.a("BitsPerComponent");
/* 421 */             this.c.a(8);
/* 422 */             this.c.m();
/*     */             
/* 424 */             boolean deviceGray = (cm.getNumComponents() == 1);
/* 425 */             this.c.a("ColorSpace");
/* 426 */             this.c.a(deviceGray ? "DeviceGray" : "DeviceRGB");
/* 427 */             this.c.m();
/*     */             
/* 429 */             this.c.a("Filter");
/* 430 */             this.c.i();
/* 431 */             switch (streamCompression) {
/*     */               case 2:
/* 433 */                 this.c.a("ASCII85Decode");
/* 434 */                 if (imageType == 0) {
/* 435 */                   this.c.a("FlateDecode");
/*     */                 }
/*     */                 break;
/*     */               case 0:
/* 439 */                 this.c.a("ASCIIHexDecode");
/*     */                 break;
/*     */               default:
/* 442 */                 if (imageType == 0) {
/* 443 */                   this.c.a("FlateDecode");
/*     */                 }
/*     */                 break;
/*     */             } 
/* 447 */             switch (imageType) {
/*     */               case 0:
/*     */                 break;
/*     */               case 1:
/* 451 */                 this.c.a("DCTDecode");
/*     */                 break;
/*     */               case 2:
/* 454 */                 this.c.a("JPXDecode");
/*     */                 break;
/*     */               default:
/* 457 */                 throw new IllegalStateException();
/*     */             } 
/* 459 */             this.c.j();
/* 460 */             this.c.m();
/*     */             
/* 462 */             OutputStream out = this.c.b((short)0);
/* 463 */             switch (streamCompression) {
/*     */               case 2:
/* 465 */                 a1 = new a(out);
/* 466 */                 if (imageType == 0) {
/* 467 */                   deflaterOutputStream2 = new DeflaterOutputStream((OutputStream)a1);
/*     */                 }
/*     */                 break;
/*     */               case 0:
/* 471 */                 b1 = new b(deflaterOutputStream2);
/*     */                 break;
/*     */               default:
/* 474 */                 if (imageType == 0) {
/* 475 */                   deflaterOutputStream1 = new DeflaterOutputStream((OutputStream)b1);
/*     */                 }
/*     */                 break;
/*     */             } 
/* 479 */             switch (imageType) {
/*     */               
/*     */               case 1:
/*     */               case 2:
/* 483 */                 ximage = image;
/* 484 */                 if (cm.hasAlpha()) {
/* 485 */                   ximage = new BufferedImage(width, height, 1);
/* 486 */                   ximage.createGraphics().drawImage(image, 0, 0, null);
/*     */                 } 
/* 488 */                 if (image.getType() == 11) {
/* 489 */                   ximage = new BufferedImage(width, height, 10);
/* 490 */                   ximage.createGraphics().drawImage(image, 0, 0, null);
/*     */                 } 
/*     */                 try {
/*     */                   try {
/* 494 */                     FileCacheImageOutputStream iout = new FileCacheImageOutputStream(deflaterOutputStream1, null);
/*     */                     try {
/* 496 */                       iw.setOutput(iout);
/* 497 */                       iw.write(null, new IIOImage(ximage, null, null), iwParams);
/*     */                     } finally {
/* 499 */                       iout.close();
/*     */                     } 
/*     */                   } finally {
/* 502 */                     iw.dispose();
/*     */                   } 
/*     */                 } finally {
/* 505 */                   if (ximage != image) {
/* 506 */                     ximage.flush();
/*     */                   }
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */               
/*     */               case 0:
/* 513 */                 raster = image.getRaster();
/* 514 */                 a = new a(deflaterOutputStream1, this.c.b());
/* 515 */                 if (deviceGray) {
/*     */                   
/* 517 */                   Object object = raster.getDataElements(0, 0, null);
/* 518 */                   for (int k = 0; k < height; k++) {
/* 519 */                     for (int x = 0; x < width; x++) {
/* 520 */                       object = raster.getDataElements(x, k, object);
/* 521 */                       a.write(cm.getGreen(object));
/*     */                     } 
/*     */                   } 
/*     */                   break;
/*     */                 } 
/* 526 */                 pixel = raster.getDataElements(0, 0, null);
/* 527 */                 for (y = 0; y < height; y++) {
/* 528 */                   for (int x = 0; x < width; x++) {
/* 529 */                     pixel = raster.getDataElements(x, y, pixel);
/* 530 */                     a.write(cm.getRed(pixel));
/* 531 */                     a.write(cm.getGreen(pixel));
/* 532 */                     a.write(cm.getBlue(pixel));
/*     */                   } 
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */ 
/*     */               
/*     */               default:
/* 540 */                 throw new IllegalStateException();
/*     */             } 
/* 542 */             a.close();
/*     */           } finally {
/* 544 */             this.c.a();
/*     */           } 
/*     */           
/* 547 */           if (imageMaskRef != null) {
/*     */             
/* 549 */             this.c.a(imageMaskRef); try {
/*     */               DeflaterOutputStream deflaterOutputStream2; b b1; DeflaterOutputStream deflaterOutputStream1;
/* 551 */               this.c.g();
/*     */               
/* 553 */               this.c.a("Type");
/* 554 */               this.c.a("XObject");
/* 555 */               this.c.m();
/*     */               
/* 557 */               this.c.a("Subtype");
/* 558 */               this.c.a("Image");
/* 559 */               this.c.m();
/*     */               
/* 561 */               if (!softMaskSupport) {
/* 562 */                 this.c.a("ImageMask");
/* 563 */                 this.c.a(true);
/* 564 */                 this.c.m();
/*     */               } 
/*     */               
/* 567 */               this.c.a("Width");
/* 568 */               this.c.a(width);
/* 569 */               this.c.m();
/*     */               
/* 571 */               this.c.a("Height");
/* 572 */               this.c.a(height);
/* 573 */               this.c.m();
/*     */               
/* 575 */               if (softMaskSupport) {
/* 576 */                 this.c.a("ColorSpace");
/* 577 */                 this.c.a("DeviceGray");
/* 578 */                 this.c.m();
/*     */               } 
/*     */               
/* 581 */               this.c.a("BitsPerComponent");
/* 582 */               this.c.a(softMaskSupport ? 8 : 1);
/* 583 */               this.c.m();
/*     */               
/* 585 */               this.c.a("Filter");
/* 586 */               this.c.i();
/* 587 */               switch (streamCompression) {
/*     */                 case 2:
/* 589 */                   this.c.a("ASCII85Decode");
/* 590 */                   this.c.a("FlateDecode");
/*     */                   break;
/*     */                 case 0:
/* 593 */                   this.c.a("ASCIIHexDecode");
/*     */                   break;
/*     */                 default:
/* 596 */                   this.c.a("FlateDecode");
/*     */                   break;
/*     */               } 
/* 599 */               this.c.j();
/* 600 */               this.c.m();
/*     */               
/* 602 */               OutputStream out = this.c.b((short)0);
/* 603 */               switch (streamCompression) {
/*     */                 case 2:
/* 605 */                   deflaterOutputStream2 = new DeflaterOutputStream((OutputStream)new a(out));
/*     */                   break;
/*     */                 case 0:
/* 608 */                   b1 = new b(deflaterOutputStream2);
/*     */                   break;
/*     */                 default:
/* 611 */                   deflaterOutputStream1 = new DeflaterOutputStream((OutputStream)b1);
/*     */                   break;
/*     */               } 
/* 614 */               a a = new a(deflaterOutputStream1, this.c.b());
/*     */               
/* 616 */               Raster raster = image.getRaster();
/* 617 */               Object pixel = raster.getDataElements(0, 0, null);
/* 618 */               if (softMaskSupport) {
/* 619 */                 for (int y = 0; y < height; y++) {
/* 620 */                   for (int x = 0; x < width; x++) {
/* 621 */                     pixel = raster.getDataElements(x, y, pixel);
/* 622 */                     a.write(cm.getAlpha(pixel) & 0xFF);
/*     */                   } 
/*     */                 } 
/*     */               } else {
/* 626 */                 int i = 0;
/* 627 */                 for (int y = 0; y < height; y++) {
/* 628 */                   for (int x = 0; x < width; x++) {
/* 629 */                     pixel = raster.getDataElements(x, y, pixel);
/* 630 */                     int off = 7 - x % 8;
/* 631 */                     if ((cm.getAlpha(pixel) & 0xFF) <= 127) {
/* 632 */                       i |= 1 << off;
/*     */                     }
/* 634 */                     if (off == 0) {
/* 635 */                       a.write(i);
/* 636 */                       i = 0;
/*     */                     } 
/*     */                   } 
/* 639 */                   if (width % 8 != 0) {
/* 640 */                     a.write(i);
/* 641 */                     i = 0;
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 645 */               a.close();
/*     */             } finally {
/* 647 */               this.c.a();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 652 */         if (image != null) {
/* 653 */           image.flush();
/*     */         }
/*     */       } 
/* 656 */     } catch (IOException e) {
/* 657 */       this.a.log(Level.WARNING, "画像生成中のI/Oエラー", e);
/* 658 */       throw e;
/* 659 */     } catch (RuntimeException e) {
/* 660 */       this.a.log(Level.SEVERE, "画像生成中の予期しないエラー", e);
/* 661 */       throw e;
/*     */     } finally {
/* 663 */       if (ir != null) {
/* 664 */         ir.dispose();
/*     */       }
/*     */     } 
/* 667 */     this.g++;
/* 668 */     return (b)pdfImage;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */