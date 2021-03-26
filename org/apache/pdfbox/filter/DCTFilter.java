/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DCTFilter
/*     */   extends Filter
/*     */ {
/*  49 */   private static final Log LOG = LogFactory.getLog(DCTFilter.class);
/*     */ 
/*     */   
/*     */   private static final int POS_TRANSFORM = 11;
/*     */   
/*     */   private static final String ADOBE = "Adobe";
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index, DecodeOptions options) throws IOException {
/*  58 */     ImageReader reader = findImageReader("JPEG", "a suitable JAI I/O image filter is not installed");
/*  59 */     ImageInputStream iis = null;
/*     */     try {
/*     */       Raster raster;
/*  62 */       iis = ImageIO.createImageInputStream(encoded);
/*     */ 
/*     */       
/*  65 */       if (iis.read() != 10)
/*     */       {
/*  67 */         iis.seek(0L);
/*     */       }
/*     */       
/*  70 */       reader.setInput(iis);
/*  71 */       ImageReadParam irp = reader.getDefaultReadParam();
/*  72 */       irp.setSourceSubsampling(options.getSubsamplingX(), options.getSubsamplingY(), options
/*  73 */           .getSubsamplingOffsetX(), options.getSubsamplingOffsetY());
/*  74 */       irp.setSourceRegion(options.getSourceRegion());
/*  75 */       options.setFilterSubsampled(true);
/*     */       
/*  77 */       String numChannels = getNumChannels(reader);
/*     */ 
/*     */       
/*  80 */       ImageIO.setUseCache(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       if ("3".equals(numChannels) || numChannels.isEmpty()) {
/*     */         
/*     */         try
/*     */         {
/*     */           
/*  91 */           BufferedImage image = reader.read(0, irp);
/*  92 */           raster = image.getRaster();
/*     */         }
/*  94 */         catch (IIOException e)
/*     */         {
/*     */ 
/*     */           
/*  98 */           raster = reader.readRaster(0, irp);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 105 */         raster = reader.readRaster(0, irp);
/*     */       } 
/*     */ 
/*     */       
/* 109 */       if (raster.getNumBands() == 4) {
/*     */         Integer transform;
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 115 */           transform = getAdobeTransform(reader.getImageMetadata(0));
/*     */         }
/* 117 */         catch (IIOException e) {
/*     */ 
/*     */           
/* 120 */           transform = Integer.valueOf(getAdobeTransformByBruteForce(iis));
/*     */         }
/* 122 */         catch (NegativeArraySizeException e) {
/*     */ 
/*     */           
/* 125 */           transform = Integer.valueOf(getAdobeTransformByBruteForce(iis));
/*     */         } 
/* 127 */         int colorTransform = (transform != null) ? transform.intValue() : 0;
/*     */ 
/*     */         
/* 130 */         switch (colorTransform) {
/*     */           case 0:
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 136 */             raster = fromYCbCrtoCMYK(raster);
/*     */             break;
/*     */           case 2:
/* 139 */             raster = fromYCCKtoCMYK(raster);
/*     */             break;
/*     */           default:
/* 142 */             throw new IllegalArgumentException("Unknown colorTransform");
/*     */         } 
/*     */       
/* 145 */       } else if (raster.getNumBands() == 3) {
/*     */ 
/*     */         
/* 148 */         raster = fromBGRtoRGB(raster);
/*     */       } 
/*     */       
/* 151 */       DataBufferByte dataBuffer = (DataBufferByte)raster.getDataBuffer();
/* 152 */       decoded.write(dataBuffer.getData());
/*     */     }
/*     */     finally {
/*     */       
/* 156 */       if (iis != null)
/*     */       {
/* 158 */         iis.close();
/*     */       }
/* 160 */       reader.dispose();
/*     */     } 
/* 162 */     return new DecodeResult(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 169 */     return decode(encoded, decoded, parameters, index, DecodeOptions.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer getAdobeTransform(IIOMetadata metadata) {
/* 175 */     Element tree = (Element)metadata.getAsTree("javax_imageio_jpeg_image_1.0");
/* 176 */     Element markerSequence = (Element)tree.getElementsByTagName("markerSequence").item(0);
/* 177 */     NodeList app14AdobeNodeList = markerSequence.getElementsByTagName("app14Adobe");
/* 178 */     if (app14AdobeNodeList != null && app14AdobeNodeList.getLength() > 0) {
/*     */       
/* 180 */       Element adobe = (Element)app14AdobeNodeList.item(0);
/* 181 */       return Integer.valueOf(Integer.parseInt(adobe.getAttribute("transform")));
/*     */     } 
/* 183 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getAdobeTransformByBruteForce(ImageInputStream iis) throws IOException {
/* 190 */     int a = 0;
/* 191 */     iis.seek(0L);
/*     */     int by;
/* 193 */     while ((by = iis.read()) != -1) {
/*     */       
/* 195 */       if ("Adobe".charAt(a) == by) {
/*     */         
/* 197 */         a++;
/* 198 */         if (a != "Adobe".length()) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 203 */         a = 0;
/* 204 */         long afterAdobePos = iis.getStreamPosition();
/* 205 */         iis.seek(iis.getStreamPosition() - 9L);
/* 206 */         int tag = iis.readUnsignedShort();
/* 207 */         if (tag != 65518) {
/*     */           
/* 209 */           iis.seek(afterAdobePos);
/*     */           continue;
/*     */         } 
/* 212 */         int len = iis.readUnsignedShort();
/* 213 */         if (len >= 12) {
/*     */           
/* 215 */           byte[] app14 = new byte[Math.max(len, 12)];
/* 216 */           if (iis.read(app14) >= 12)
/*     */           {
/* 218 */             return app14[11];
/*     */           }
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 224 */       a = 0;
/*     */     } 
/*     */     
/* 227 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WritableRaster fromYCCKtoCMYK(Raster raster) {
/* 235 */     WritableRaster writableRaster = raster.createCompatibleWritableRaster();
/*     */     
/* 237 */     int[] value = new int[4];
/* 238 */     for (int y = 0, height = raster.getHeight(); y < height; y++) {
/*     */       
/* 240 */       for (int x = 0, width = raster.getWidth(); x < width; x++) {
/*     */         
/* 242 */         raster.getPixel(x, y, value);
/*     */ 
/*     */         
/* 245 */         float Y = value[0];
/* 246 */         float Cb = value[1];
/* 247 */         float Cr = value[2];
/* 248 */         float K = value[3];
/*     */ 
/*     */         
/* 251 */         int r = clamp(Y + 1.402F * Cr - 179.456F);
/* 252 */         int g = clamp(Y - 0.34414F * Cb - 0.71414F * Cr + 135.45984F);
/* 253 */         int b = clamp(Y + 1.772F * Cb - 226.816F);
/*     */ 
/*     */         
/* 256 */         int cyan = 255 - r;
/* 257 */         int magenta = 255 - g;
/* 258 */         int yellow = 255 - b;
/*     */ 
/*     */         
/* 261 */         value[0] = cyan;
/* 262 */         value[1] = magenta;
/* 263 */         value[2] = yellow;
/* 264 */         value[3] = (int)K;
/* 265 */         writableRaster.setPixel(x, y, value);
/*     */       } 
/*     */     } 
/* 268 */     return writableRaster;
/*     */   }
/*     */ 
/*     */   
/*     */   private WritableRaster fromYCbCrtoCMYK(Raster raster) {
/* 273 */     WritableRaster writableRaster = raster.createCompatibleWritableRaster();
/*     */     
/* 275 */     int[] value = new int[4];
/* 276 */     for (int y = 0, height = raster.getHeight(); y < height; y++) {
/*     */       
/* 278 */       for (int x = 0, width = raster.getWidth(); x < width; x++) {
/*     */         
/* 280 */         raster.getPixel(x, y, value);
/*     */ 
/*     */         
/* 283 */         float Y = value[0];
/* 284 */         float Cb = value[1];
/* 285 */         float Cr = value[2];
/* 286 */         float K = value[3];
/*     */ 
/*     */         
/* 289 */         int r = clamp(1.164F * (Y - 16.0F) + 1.596F * (Cr - 128.0F));
/* 290 */         int g = clamp(1.164F * (Y - 16.0F) + -0.392F * (Cb - 128.0F) + -0.813F * (Cr - 128.0F));
/* 291 */         int b = clamp(1.164F * (Y - 16.0F) + 2.017F * (Cb - 128.0F));
/*     */ 
/*     */         
/* 294 */         int cyan = 255 - r;
/* 295 */         int magenta = 255 - g;
/* 296 */         int yellow = 255 - b;
/*     */ 
/*     */         
/* 299 */         value[0] = cyan;
/* 300 */         value[1] = magenta;
/* 301 */         value[2] = yellow;
/* 302 */         value[3] = (int)K;
/* 303 */         writableRaster.setPixel(x, y, value);
/*     */       } 
/*     */     } 
/* 306 */     return writableRaster;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private WritableRaster fromBGRtoRGB(Raster raster) {
/* 312 */     WritableRaster writableRaster = raster.createCompatibleWritableRaster();
/*     */     
/* 314 */     int width = raster.getWidth();
/* 315 */     int height = raster.getHeight();
/* 316 */     int w3 = width * 3;
/* 317 */     int[] tab = new int[w3];
/*     */     
/* 319 */     for (int y = 0; y < height; y++) {
/*     */       
/* 321 */       raster.getPixels(0, y, width, 1, tab);
/* 322 */       for (int off = 0; off < w3; off += 3) {
/*     */         
/* 324 */         int tmp = tab[off];
/* 325 */         tab[off] = tab[off + 2];
/* 326 */         tab[off + 2] = tmp;
/*     */       } 
/* 328 */       writableRaster.setPixels(0, y, width, 1, tab);
/*     */     } 
/* 330 */     return writableRaster;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getNumChannels(ImageReader reader) {
/*     */     try {
/* 338 */       IIOMetadata imageMetadata = reader.getImageMetadata(0);
/* 339 */       if (imageMetadata == null)
/*     */       {
/* 341 */         return "";
/*     */       }
/* 343 */       IIOMetadataNode metaTree = (IIOMetadataNode)imageMetadata.getAsTree("javax_imageio_1.0");
/* 344 */       Element numChannelsItem = (Element)metaTree.getElementsByTagName("NumChannels").item(0);
/* 345 */       if (numChannelsItem == null)
/*     */       {
/* 347 */         return "";
/*     */       }
/* 349 */       return numChannelsItem.getAttribute("value");
/*     */     }
/* 351 */     catch (IOException e) {
/*     */       
/* 353 */       return "";
/*     */     }
/* 355 */     catch (NegativeArraySizeException e) {
/*     */       
/* 357 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int clamp(float value) {
/* 364 */     return (int)((value < 0.0F) ? 0.0F : ((value > 255.0F) ? 255.0F : value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 371 */     throw new UnsupportedOperationException("DCTFilter encoding not implemented, use the JPEGFactory methods instead");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/DCTFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */