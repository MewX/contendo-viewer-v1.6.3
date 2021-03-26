/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.filter.MissingImageReaderException;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JPEGFactory
/*     */ {
/*     */   public static PDImageXObject createFromStream(PDDocument document, InputStream stream) throws IOException {
/*  78 */     return createFromByteArray(document, IOUtils.toByteArray(stream));
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
/*     */   public static PDImageXObject createFromByteArray(PDDocument document, byte[] byteArray) throws IOException {
/*     */     PDDeviceGray pDDeviceGray;
/*     */     PDDeviceRGB pDDeviceRGB;
/*     */     PDDeviceCMYK pDDeviceCMYK;
/*  94 */     ByteArrayInputStream byteStream = new ByteArrayInputStream(byteArray);
/*     */ 
/*     */     
/*  97 */     Raster raster = readJPEGRaster(byteStream);
/*  98 */     byteStream.reset();
/*     */ 
/*     */     
/* 101 */     switch (raster.getNumDataElements()) {
/*     */       
/*     */       case 1:
/* 104 */         pDDeviceGray = PDDeviceGray.INSTANCE;
/*     */         break;
/*     */       case 3:
/* 107 */         pDDeviceRGB = PDDeviceRGB.INSTANCE;
/*     */         break;
/*     */       case 4:
/* 110 */         pDDeviceCMYK = PDDeviceCMYK.INSTANCE;
/*     */         break;
/*     */       default:
/* 113 */         throw new UnsupportedOperationException("number of data elements not supported: " + raster
/* 114 */             .getNumDataElements());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     PDImageXObject pdImage = new PDImageXObject(document, byteStream, (COSBase)COSName.DCT_DECODE, raster.getWidth(), raster.getHeight(), 8, (PDColorSpace)pDDeviceCMYK);
/*     */     
/* 121 */     if (pDDeviceCMYK instanceof PDDeviceCMYK) {
/*     */       
/* 123 */       COSArray decode = new COSArray();
/* 124 */       decode.add((COSBase)COSInteger.ONE);
/* 125 */       decode.add((COSBase)COSInteger.ZERO);
/* 126 */       decode.add((COSBase)COSInteger.ONE);
/* 127 */       decode.add((COSBase)COSInteger.ZERO);
/* 128 */       decode.add((COSBase)COSInteger.ONE);
/* 129 */       decode.add((COSBase)COSInteger.ZERO);
/* 130 */       decode.add((COSBase)COSInteger.ONE);
/* 131 */       decode.add((COSBase)COSInteger.ZERO);
/* 132 */       pdImage.setDecode(decode);
/*     */     } 
/*     */     
/* 135 */     return pdImage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Raster readJPEGRaster(InputStream stream) throws IOException {
/* 141 */     Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
/* 142 */     ImageReader reader = null;
/* 143 */     while (readers.hasNext()) {
/*     */       
/* 145 */       reader = readers.next();
/* 146 */       if (reader.canReadRaster()) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 152 */     if (reader == null)
/*     */     {
/* 154 */       throw new MissingImageReaderException("Cannot read JPEG image: a suitable JAI I/O image filter is not installed");
/*     */     }
/*     */ 
/*     */     
/* 158 */     ImageInputStream iis = null;
/*     */     
/*     */     try {
/* 161 */       iis = ImageIO.createImageInputStream(stream);
/* 162 */       reader.setInput(iis);
/* 163 */       ImageIO.setUseCache(false);
/* 164 */       return reader.readRaster(0, null);
/*     */     }
/*     */     finally {
/*     */       
/* 168 */       if (iis != null)
/*     */       {
/* 170 */         iis.close();
/*     */       }
/* 172 */       reader.dispose();
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
/*     */   public static PDImageXObject createFromImage(PDDocument document, BufferedImage image) throws IOException {
/* 191 */     return createFromImage(document, image, 0.75F);
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
/*     */   public static PDImageXObject createFromImage(PDDocument document, BufferedImage image, float quality) throws IOException {
/* 213 */     return createFromImage(document, image, quality, 72);
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
/*     */   public static PDImageXObject createFromImage(PDDocument document, BufferedImage image, float quality, int dpi) throws IOException {
/* 236 */     return createJPEG(document, image, quality, dpi);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage getAlphaImage(BufferedImage image) throws IOException {
/* 242 */     if (!image.getColorModel().hasAlpha())
/*     */     {
/* 244 */       return null;
/*     */     }
/* 246 */     if (image.getTransparency() == 2)
/*     */     {
/* 248 */       throw new UnsupportedOperationException("BITMASK Transparency JPEG compression is not useful, use LosslessImageFactory instead");
/*     */     }
/*     */     
/* 251 */     WritableRaster alphaRaster = image.getAlphaRaster();
/* 252 */     if (alphaRaster == null)
/*     */     {
/*     */       
/* 255 */       return null;
/*     */     }
/* 257 */     BufferedImage alphaImage = new BufferedImage(image.getWidth(), image.getHeight(), 10);
/*     */     
/* 259 */     alphaImage.setData(alphaRaster);
/* 260 */     return alphaImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDImageXObject createJPEG(PDDocument document, BufferedImage image, float quality, int dpi) throws IOException {
/* 268 */     BufferedImage awtColorImage = getColorImage(image);
/* 269 */     BufferedImage awtAlphaImage = getAlphaImage(image);
/*     */ 
/*     */     
/* 272 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 273 */     encodeImageToJPEGStream(awtColorImage, quality, dpi, baos);
/* 274 */     ByteArrayInputStream byteStream = new ByteArrayInputStream(baos.toByteArray());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     PDImageXObject pdImage = new PDImageXObject(document, byteStream, (COSBase)COSName.DCT_DECODE, awtColorImage.getWidth(), awtColorImage.getHeight(), awtColorImage.getColorModel().getComponentSize(0), getColorSpaceFromAWT(awtColorImage));
/*     */ 
/*     */     
/* 282 */     if (awtAlphaImage != null) {
/*     */       
/* 284 */       PDImage xAlpha = createFromImage(document, awtAlphaImage, quality);
/* 285 */       pdImage.getCOSObject().setItem(COSName.SMASK, xAlpha);
/*     */     } 
/*     */     
/* 288 */     return pdImage;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ImageWriter getJPEGImageWriter() throws IOException {
/* 293 */     ImageWriter writer = null;
/* 294 */     Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
/* 295 */     while (writers.hasNext()) {
/*     */       
/* 297 */       if (writer != null)
/*     */       {
/* 299 */         writer.dispose();
/*     */       }
/* 301 */       writer = writers.next();
/* 302 */       if (writer == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 307 */       if (writer.getDefaultWriteParam() instanceof JPEGImageWriteParam)
/*     */       {
/* 309 */         return writer;
/*     */       }
/*     */     } 
/* 312 */     throw new IOException("No ImageWriter found for JPEG format");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void encodeImageToJPEGStream(BufferedImage image, float quality, int dpi, OutputStream out) throws IOException {
/* 319 */     ImageOutputStream ios = null;
/* 320 */     ImageWriter imageWriter = null;
/*     */ 
/*     */     
/*     */     try {
/* 324 */       imageWriter = getJPEGImageWriter();
/* 325 */       ios = ImageIO.createImageOutputStream(out);
/* 326 */       imageWriter.setOutput(ios);
/*     */ 
/*     */       
/* 329 */       JPEGImageWriteParam jpegParam = (JPEGImageWriteParam)imageWriter.getDefaultWriteParam();
/* 330 */       jpegParam.setCompressionMode(2);
/* 331 */       jpegParam.setCompressionQuality(quality);
/*     */ 
/*     */       
/* 334 */       ImageTypeSpecifier imageTypeSpecifier = new ImageTypeSpecifier(image);
/* 335 */       IIOMetadata data = imageWriter.getDefaultImageMetadata(imageTypeSpecifier, jpegParam);
/* 336 */       Element tree = (Element)data.getAsTree("javax_imageio_jpeg_image_1.0");
/* 337 */       Element jfif = (Element)tree.getElementsByTagName("app0JFIF").item(0);
/* 338 */       jfif.setAttribute("Xdensity", Integer.toString(dpi));
/* 339 */       jfif.setAttribute("Ydensity", Integer.toString(dpi));
/* 340 */       jfif.setAttribute("resUnits", "1");
/*     */ 
/*     */       
/* 343 */       imageWriter.write(data, new IIOImage(image, null, null), jpegParam);
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 348 */       IOUtils.closeQuietly(out);
/* 349 */       if (ios != null)
/*     */       {
/* 351 */         ios.close();
/*     */       }
/* 353 */       if (imageWriter != null)
/*     */       {
/* 355 */         imageWriter.dispose();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDColorSpace getColorSpaceFromAWT(BufferedImage awtImage) {
/* 363 */     if (awtImage.getColorModel().getNumComponents() == 1)
/*     */     {
/*     */       
/* 366 */       return (PDColorSpace)PDDeviceGray.INSTANCE;
/*     */     }
/*     */     
/* 369 */     ColorSpace awtColorSpace = awtImage.getColorModel().getColorSpace();
/* 370 */     if (awtColorSpace instanceof java.awt.color.ICC_ColorSpace && !awtColorSpace.isCS_sRGB())
/*     */     {
/* 372 */       throw new UnsupportedOperationException("ICC color spaces not implemented");
/*     */     }
/*     */     
/* 375 */     switch (awtColorSpace.getType()) {
/*     */       
/*     */       case 5:
/* 378 */         return (PDColorSpace)PDDeviceRGB.INSTANCE;
/*     */       case 6:
/* 380 */         return (PDColorSpace)PDDeviceGray.INSTANCE;
/*     */       case 9:
/* 382 */         return (PDColorSpace)PDDeviceCMYK.INSTANCE;
/*     */     } 
/* 384 */     throw new UnsupportedOperationException("color space not implemented: " + awtColorSpace
/* 385 */         .getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BufferedImage getColorImage(BufferedImage image) {
/* 392 */     if (!image.getColorModel().hasAlpha())
/*     */     {
/* 394 */       return image;
/*     */     }
/*     */     
/* 397 */     if (image.getColorModel().getColorSpace().getType() != 5)
/*     */     {
/* 399 */       throw new UnsupportedOperationException("only RGB color spaces are implemented");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), 5);
/*     */     
/* 411 */     return (new ColorConvertOp(null)).filter(image, rgbImage);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/JPEGFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */