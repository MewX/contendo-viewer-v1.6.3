/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.io.output.ByteArrayOutputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.io.XmlSourceUtil;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorSpaces;
/*     */ import org.apache.xmlgraphics.java2d.color.DeviceCMYKColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.profile.ColorProfileUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageLoaderRawJPEG
/*     */   extends AbstractImageLoader
/*     */   implements JPEGConstants
/*     */ {
/*  54 */   protected static final Log log = LogFactory.getLog(ImageLoaderRawJPEG.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFlavor getTargetFlavor() {
/*  64 */     return ImageFlavor.RAW_JPEG;
/*     */   }
/*     */ 
/*     */   
/*     */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/*     */     DeviceCMYKColorSpace deviceCMYKColorSpace;
/*  70 */     if (!"image/jpeg".equals(info.getMimeType())) {
/*  71 */       throw new IllegalArgumentException("ImageInfo must be from a image with MIME type: image/jpeg");
/*     */     }
/*     */ 
/*     */     
/*  75 */     ColorSpace colorSpace = null;
/*  76 */     boolean appeFound = false;
/*  77 */     int sofType = 0;
/*  78 */     ByteArrayOutputStream iccStream = null;
/*     */     
/*  80 */     Source src = session.needSource(info.getOriginalURI());
/*  81 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*  82 */     JPEGFile jpeg = new JPEGFile(in);
/*  83 */     in.mark();
/*     */ 
/*     */     
/*     */     try {
/*     */       while (true) {
/*  88 */         int reclen, segID = jpeg.readMarkerSegment();
/*  89 */         if (log.isTraceEnabled()) {
/*  90 */           log.trace("Seg Marker: " + Integer.toHexString(segID));
/*     */         }
/*  92 */         switch (segID) {
/*     */           case 217:
/*  94 */             log.trace("EOI found. Stopping.");
/*     */             break;
/*     */           case 218:
/*  97 */             log.trace("SOS found. Stopping early.");
/*     */             break;
/*     */           case 0:
/*     */           case 216:
/*     */             continue;
/*     */           case 192:
/*     */           case 193:
/*     */           case 194:
/*     */           case 202:
/* 106 */             sofType = segID;
/* 107 */             if (log.isTraceEnabled()) {
/* 108 */               log.trace("SOF: " + Integer.toHexString(sofType));
/*     */             }
/* 110 */             in.mark();
/*     */             try {
/* 112 */               reclen = jpeg.readSegmentLength();
/* 113 */               in.skipBytes(1);
/* 114 */               in.skipBytes(2);
/* 115 */               in.skipBytes(2);
/* 116 */               int numComponents = in.readUnsignedByte();
/* 117 */               if (numComponents == 1) {
/* 118 */                 colorSpace = ColorSpace.getInstance(1003);
/*     */               }
/* 120 */               else if (numComponents == 3) {
/* 121 */                 colorSpace = ColorSpace.getInstance(1004);
/*     */               }
/* 123 */               else if (numComponents == 4) {
/* 124 */                 deviceCMYKColorSpace = ColorSpaces.getDeviceCMYKColorSpace();
/*     */               } else {
/* 126 */                 throw new ImageException("Unsupported ColorSpace for image " + info + ". The number of components supported are 1, 3 and 4.");
/*     */               }
/*     */             
/*     */             } finally {
/*     */               
/* 131 */               in.reset();
/*     */             } 
/* 133 */             in.skipBytes(reclen);
/*     */             continue;
/*     */           case 226:
/* 136 */             in.mark();
/*     */             try {
/* 138 */               reclen = jpeg.readSegmentLength();
/*     */               
/* 140 */               byte[] iccString = new byte[11];
/* 141 */               in.readFully(iccString);
/* 142 */               in.skipBytes(1);
/*     */               
/* 144 */               if ("ICC_PROFILE".equals(new String(iccString, "US-ASCII"))) {
/* 145 */                 in.skipBytes(2);
/* 146 */                 int payloadSize = reclen - 2 - 12 - 2;
/* 147 */                 if (ignoreColorProfile(hints)) {
/* 148 */                   log.debug("Ignoring ICC profile data in JPEG");
/* 149 */                   in.skipBytes(payloadSize);
/*     */                 } else {
/* 151 */                   byte[] buf = new byte[payloadSize];
/* 152 */                   in.readFully(buf);
/* 153 */                   if (iccStream == null) {
/* 154 */                     if (log.isDebugEnabled()) {
/* 155 */                       log.debug("JPEG has an ICC profile");
/* 156 */                       DataInputStream din = new DataInputStream(new ByteArrayInputStream(buf));
/* 157 */                       log.debug("Declared ICC profile size: " + din.readInt());
/*     */                     } 
/*     */ 
/*     */                     
/* 161 */                     iccStream = new ByteArrayOutputStream();
/*     */                   } 
/* 163 */                   iccStream.write(buf);
/*     */                 } 
/*     */               } 
/*     */             } finally {
/* 167 */               in.reset();
/*     */             } 
/* 169 */             in.skipBytes(reclen);
/*     */             continue;
/*     */           case 238:
/* 172 */             in.mark();
/*     */             try {
/* 174 */               reclen = jpeg.readSegmentLength();
/*     */               
/* 176 */               byte[] adobeHeader = new byte[5];
/* 177 */               in.readFully(adobeHeader);
/*     */               
/* 179 */               if ("Adobe".equals(new String(adobeHeader, "US-ASCII")))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 185 */                 appeFound = true;
/*     */               }
/*     */             } finally {
/* 188 */               in.reset();
/*     */             } 
/* 190 */             in.skipBytes(reclen);
/*     */             continue;
/*     */         } 
/* 193 */         jpeg.skipCurrentMarkerSegment();
/*     */       } 
/*     */     } finally {
/*     */       
/* 197 */       in.reset();
/*     */     } 
/*     */     
/* 200 */     ICC_Profile iccProfile = buildICCProfile(info, (ColorSpace)deviceCMYKColorSpace, iccStream);
/* 201 */     if (iccProfile == null && deviceCMYKColorSpace == null) {
/* 202 */       throw new ImageException("ColorSpace could not be identified for JPEG image " + info);
/*     */     }
/*     */     
/* 205 */     boolean invertImage = false;
/* 206 */     if (appeFound && deviceCMYKColorSpace.getType() == 9) {
/* 207 */       if (log.isDebugEnabled()) {
/* 208 */         log.debug("JPEG has an Adobe APPE marker. Note: CMYK Image will be inverted. (" + info.getOriginalURI() + ")");
/*     */       }
/*     */       
/* 211 */       invertImage = true;
/*     */     } 
/*     */     
/* 214 */     ImageRawJPEG rawImage = new ImageRawJPEG(info, XmlSourceUtil.needInputStream(src), sofType, (ColorSpace)deviceCMYKColorSpace, iccProfile, invertImage);
/*     */ 
/*     */     
/* 217 */     return rawImage;
/*     */   }
/*     */ 
/*     */   
/*     */   private ICC_Profile buildICCProfile(ImageInfo info, ColorSpace colorSpace, ByteArrayOutputStream iccStream) throws IOException, ImageException {
/* 222 */     if (iccStream != null && iccStream.size() > 0) {
/* 223 */       if (log.isDebugEnabled()) {
/* 224 */         log.debug("Effective ICC profile size: " + iccStream.size());
/*     */       }
/* 226 */       int alignment = 4;
/* 227 */       int padding = (4 - iccStream.size() % 4) % 4;
/* 228 */       if (padding != 0) {
/*     */         try {
/* 230 */           iccStream.write(new byte[padding]);
/* 231 */         } catch (IOException ioe) {
/* 232 */           throw new IOException("Error while aligning ICC stream: " + ioe.getMessage());
/*     */         } 
/*     */       }
/*     */       
/* 236 */       ICC_Profile iccProfile = null;
/*     */       try {
/* 238 */         iccProfile = ColorProfileUtil.getICC_Profile(iccStream.toByteArray());
/* 239 */         if (log.isDebugEnabled()) {
/* 240 */           log.debug("JPEG has an ICC profile: " + iccProfile.toString());
/*     */         }
/* 242 */       } catch (IllegalArgumentException iae) {
/* 243 */         log.warn("An ICC profile is present in the JPEG file but it is invalid (" + iae.getMessage() + "). The color profile will be ignored. (" + info.getOriginalURI() + ")");
/*     */ 
/*     */         
/* 246 */         return null;
/*     */       } 
/* 248 */       if (iccProfile.getNumComponents() != colorSpace.getNumComponents()) {
/* 249 */         log.warn("The number of components of the ICC profile (" + iccProfile.getNumComponents() + ") doesn't match the image (" + colorSpace.getNumComponents() + "). Ignoring the ICC color profile.");
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 254 */         return null;
/*     */       } 
/* 256 */       return iccProfile;
/*     */     } 
/*     */     
/* 259 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderRawJPEG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */