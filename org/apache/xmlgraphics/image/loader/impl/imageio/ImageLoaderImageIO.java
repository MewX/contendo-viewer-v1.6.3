/*     */ package org.apache.xmlgraphics.image.loader.impl.imageio;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.spi.IIOServiceProvider;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.impl.AbstractImageLoader;
/*     */ import org.apache.xmlgraphics.image.loader.impl.ImageBuffered;
/*     */ import org.apache.xmlgraphics.image.loader.impl.ImageRendered;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.io.XmlSourceUtil;
/*     */ import org.apache.xmlgraphics.java2d.color.profile.ColorProfileUtil;
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
/*     */ public class ImageLoaderImageIO
/*     */   extends AbstractImageLoader
/*     */ {
/*  78 */   protected static final Log log = LogFactory.getLog(ImageLoaderImageIO.class);
/*     */   
/*     */   private ImageFlavor targetFlavor;
/*     */   
/*     */   private static final String PNG_METADATA_NODE = "javax_imageio_png_1.0";
/*     */   
/*     */   private static final String JPEG_METADATA_NODE = "javax_imageio_jpeg_image_1.0";
/*     */   
/*  86 */   private static final Set PROVIDERS_IGNORING_ICC = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageLoaderImageIO(ImageFlavor targetFlavor) {
/*  93 */     if (!ImageFlavor.BUFFERED_IMAGE.equals(targetFlavor) && !ImageFlavor.RENDERED_IMAGE.equals(targetFlavor))
/*     */     {
/*  95 */       throw new IllegalArgumentException("Unsupported target ImageFlavor: " + targetFlavor);
/*     */     }
/*  97 */     this.targetFlavor = targetFlavor;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageFlavor getTargetFlavor() {
/* 102 */     return this.targetFlavor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 108 */     RenderedImage imageData = null;
/* 109 */     IIOException firstException = null;
/*     */     
/* 111 */     IIOMetadata iiometa = (IIOMetadata)info.getCustomObjects().get(ImageIOUtil.IMAGEIO_METADATA);
/*     */     
/* 113 */     boolean ignoreMetadata = (iiometa != null);
/* 114 */     boolean providerIgnoresICC = false;
/*     */     
/* 116 */     Source src = session.needSource(info.getOriginalURI());
/* 117 */     ImageInputStream imgStream = ImageUtil.needImageInputStream(src);
/*     */     try {
/* 119 */       Iterator<ImageReader> iter = ImageIO.getImageReaders(imgStream);
/* 120 */       while (iter.hasNext()) {
/* 121 */         ImageReader reader = iter.next();
/*     */         try {
/* 123 */           imgStream.mark();
/* 124 */           reader.setInput(imgStream, false, ignoreMetadata);
/* 125 */           ImageReadParam param = getParam(reader, hints);
/* 126 */           int pageIndex = ImageUtil.needPageIndexFromURI(info.getOriginalURI());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         finally {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 168 */           reader.dispose();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 172 */       XmlSourceUtil.closeQuietly(src);
/*     */     } 
/*     */     
/* 175 */     if (firstException != null) {
/* 176 */       throw new ImageException("Error while loading image: " + firstException.getMessage(), firstException);
/*     */     }
/*     */     
/* 179 */     if (imageData == null) {
/* 180 */       throw new ImageException("No ImageIO ImageReader found .");
/*     */     }
/*     */     
/* 183 */     ColorModel cm = imageData.getColorModel();
/*     */     
/* 185 */     Color transparentColor = null;
/* 186 */     if (!(cm instanceof java.awt.image.IndexColorModel)) {
/*     */ 
/*     */       
/* 189 */       if (providerIgnoresICC && cm instanceof ComponentColorModel) {
/*     */ 
/*     */         
/* 192 */         ICC_Profile iccProf = tryToExctractICCProfile(iiometa);
/* 193 */         if (iccProf != null) {
/* 194 */           ColorModel cm2 = new ComponentColorModel(new ICC_ColorSpace(iccProf), cm.hasAlpha(), cm.isAlphaPremultiplied(), cm.getTransparency(), cm.getTransferType());
/*     */ 
/*     */ 
/*     */           
/* 198 */           WritableRaster wr = Raster.createWritableRaster(imageData.getSampleModel(), null);
/*     */           
/* 200 */           imageData.copyData(wr);
/*     */           try {
/* 202 */             BufferedImage bi = new BufferedImage(cm2, wr, cm2.isAlphaPremultiplied(), null);
/*     */             
/* 204 */             imageData = bi;
/* 205 */             cm = cm2;
/* 206 */           } catch (IllegalArgumentException iae) {
/* 207 */             String msg = "Image " + info.getOriginalURI() + " has an incompatible color profile." + " The color profile will be ignored." + "\nColor model of loaded bitmap: " + cm + "\nColor model of color profile: " + cm2;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 212 */             if (info.getCustomObjects().get("warningincustomobject") != null) {
/* 213 */               info.getCustomObjects().put("warning", msg);
/*     */             } else {
/* 215 */               log.warn(msg);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 223 */       if (iiometa != null && iiometa.isStandardMetadataFormatSupported()) {
/* 224 */         Element metanode = (Element)iiometa.getAsTree("javax_imageio_1.0");
/*     */         
/* 226 */         Element dim = ImageIOUtil.getChild(metanode, "Transparency");
/* 227 */         if (dim != null) {
/*     */           
/* 229 */           Element child = ImageIOUtil.getChild(dim, "TransparentColor");
/* 230 */           if (child != null) {
/* 231 */             String value = child.getAttribute("value");
/* 232 */             if (value.length() != 0)
/*     */             {
/* 234 */               if (cm.getNumColorComponents() == 1) {
/* 235 */                 int gray = Integer.parseInt(value);
/* 236 */                 transparentColor = new Color(gray, gray, gray);
/*     */               } else {
/* 238 */                 StringTokenizer st = new StringTokenizer(value);
/* 239 */                 transparentColor = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 249 */     if (ImageFlavor.BUFFERED_IMAGE.equals(this.targetFlavor)) {
/* 250 */       return (Image)new ImageBuffered(info, (BufferedImage)imageData, transparentColor);
/*     */     }
/* 252 */     return (Image)new ImageRendered(info, imageData, transparentColor);
/*     */   }
/*     */ 
/*     */   
/*     */   private ImageReadParam getParam(ImageReader reader, Map hints) throws IOException {
/* 257 */     if (hints != null && Boolean.TRUE.equals(hints.get("CMYK"))) {
/* 258 */       Iterator<ImageTypeSpecifier> types = reader.getImageTypes(0);
/* 259 */       while (types.hasNext()) {
/* 260 */         ImageTypeSpecifier type = types.next();
/* 261 */         if (type.getNumComponents() == 4) {
/* 262 */           ImageReadParam param = new ImageReadParam();
/* 263 */           param.setDestinationType(type);
/* 264 */           return param;
/*     */         } 
/*     */       } 
/*     */     } 
/* 268 */     return reader.getDefaultReadParam();
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
/*     */   private boolean checkProviderIgnoresICC(IIOServiceProvider provider) {
/* 283 */     StringBuffer b = new StringBuffer(provider.getDescription(Locale.ENGLISH));
/* 284 */     b.append('/').append(provider.getVendorName());
/* 285 */     b.append('/').append(provider.getVersion());
/* 286 */     if (log.isDebugEnabled()) {
/* 287 */       log.debug("Image Provider: " + b.toString());
/*     */     }
/* 289 */     return PROVIDERS_IGNORING_ICC.contains(b.toString());
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
/*     */   private ICC_Profile tryToExctractICCProfile(IIOMetadata iiometa) {
/* 301 */     ICC_Profile iccProf = null;
/* 302 */     String[] supportedFormats = iiometa.getMetadataFormatNames();
/* 303 */     for (int i = 0; i < supportedFormats.length; i++) {
/* 304 */       String format = supportedFormats[i];
/* 305 */       Element root = (Element)iiometa.getAsTree(format);
/* 306 */       if ("javax_imageio_png_1.0".equals(format)) {
/* 307 */         iccProf = tryToExctractICCProfileFromPNGMetadataNode(root);
/*     */       }
/* 309 */       else if ("javax_imageio_jpeg_image_1.0".equals(format)) {
/* 310 */         iccProf = tryToExctractICCProfileFromJPEGMetadataNode(root);
/*     */       } 
/*     */     } 
/* 313 */     return iccProf;
/*     */   }
/*     */ 
/*     */   
/*     */   private ICC_Profile tryToExctractICCProfileFromPNGMetadataNode(Element pngNode) {
/* 318 */     ICC_Profile iccProf = null;
/* 319 */     Element iccpNode = ImageIOUtil.getChild(pngNode, "iCCP");
/* 320 */     if (iccpNode instanceof IIOMetadataNode) {
/* 321 */       IIOMetadataNode imn = (IIOMetadataNode)iccpNode;
/* 322 */       byte[] prof = (byte[])imn.getUserObject();
/* 323 */       String comp = imn.getAttribute("compressionMethod");
/* 324 */       if ("deflate".equalsIgnoreCase(comp)) {
/* 325 */         Inflater decompresser = new Inflater();
/* 326 */         decompresser.setInput(prof);
/* 327 */         byte[] result = new byte[100];
/* 328 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 329 */         boolean failed = false;
/* 330 */         while (!decompresser.finished() && !failed) {
/*     */           try {
/* 332 */             int resultLength = decompresser.inflate(result);
/* 333 */             bos.write(result, 0, resultLength);
/* 334 */             if (resultLength == 0) {
/*     */ 
/*     */ 
/*     */               
/* 338 */               log.debug("Failed to deflate ICC Profile");
/* 339 */               failed = true;
/*     */             } 
/* 341 */           } catch (DataFormatException e) {
/* 342 */             log.debug("Failed to deflate ICC Profile", e);
/* 343 */             failed = true;
/*     */           } 
/*     */         } 
/* 346 */         decompresser.end();
/*     */         try {
/* 348 */           iccProf = ColorProfileUtil.getICC_Profile(bos.toByteArray());
/* 349 */         } catch (IllegalArgumentException e) {
/* 350 */           log.debug("Failed to interpret embedded ICC Profile", e);
/* 351 */           iccProf = null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 355 */     return iccProf;
/*     */   }
/*     */ 
/*     */   
/*     */   private ICC_Profile tryToExctractICCProfileFromJPEGMetadataNode(Element jpgNode) {
/* 360 */     ICC_Profile iccProf = null;
/* 361 */     Element jfifNode = ImageIOUtil.getChild(jpgNode, "app0JFIF");
/* 362 */     if (jfifNode != null) {
/* 363 */       Element app2iccNode = ImageIOUtil.getChild(jfifNode, "app2ICC");
/* 364 */       if (app2iccNode instanceof IIOMetadataNode) {
/* 365 */         IIOMetadataNode imn = (IIOMetadataNode)app2iccNode;
/* 366 */         iccProf = (ICC_Profile)imn.getUserObject();
/*     */       } 
/*     */     } 
/* 369 */     return iccProf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage getFallbackBufferedImage(ImageReader reader, int pageIndex, ImageReadParam param) throws IOException {
/*     */     int imageType;
/*     */     BufferedImage bi;
/* 378 */     Raster raster = reader.readRaster(pageIndex, param);
/*     */ 
/*     */ 
/*     */     
/* 382 */     int numBands = raster.getNumBands();
/* 383 */     switch (numBands) {
/*     */       case 1:
/* 385 */         imageType = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 398 */         bi = new BufferedImage(raster.getWidth(), raster.getHeight(), imageType);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 403 */         bi.getRaster().setRect(raster);
/* 404 */         return bi;case 3: imageType = 5; bi = new BufferedImage(raster.getWidth(), raster.getHeight(), imageType); bi.getRaster().setRect(raster); return bi;case 4: imageType = 6; bi = new BufferedImage(raster.getWidth(), raster.getHeight(), imageType); bi.getRaster().setRect(raster); return bi;
/*     */     } 
/*     */     throw new UnsupportedOperationException("Unsupported band count: " + numBands);
/*     */   }
/*     */   static {
/* 409 */     PROVIDERS_IGNORING_ICC.add("Standard PNG image reader/Sun Microsystems, Inc./1.0");
/*     */     
/* 411 */     PROVIDERS_IGNORING_ICC.add("Standard PNG image reader/Oracle Corporation/1.0");
/*     */     
/* 413 */     PROVIDERS_IGNORING_ICC.add("Standard JPEG Image Reader/Sun Microsystems, Inc./0.5");
/*     */     
/* 415 */     PROVIDERS_IGNORING_ICC.add("Standard JPEG Image Reader/Oracle Corporation/0.5");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/imageio/ImageLoaderImageIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */