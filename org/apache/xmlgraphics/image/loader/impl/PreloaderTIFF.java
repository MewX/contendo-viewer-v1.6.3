/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.TIFFDirectory;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.TIFFField;
/*     */ import org.apache.xmlgraphics.image.codec.util.SeekableStream;
/*     */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*     */ import org.apache.xmlgraphics.image.loader.SubImageNotFoundException;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.image.loader.util.SeekableStreamAdapter;
/*     */ import org.apache.xmlgraphics.util.UnitConv;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreloaderTIFF
/*     */   extends AbstractImagePreloader
/*     */ {
/*  53 */   private static Log log = LogFactory.getLog(PreloaderTIFF.class);
/*     */ 
/*     */   
/*     */   private static final int TIFF_SIG_LENGTH = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException, ImageException {
/*  61 */     if (!ImageUtil.hasImageInputStream(src)) {
/*  62 */       return null;
/*     */     }
/*  64 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*  65 */     byte[] header = getHeader(in, 8);
/*  66 */     boolean supported = false;
/*     */ 
/*     */     
/*  69 */     if (header[0] == 73 && header[1] == 73)
/*     */     {
/*     */       
/*  72 */       if (header[2] == 42 && header[3] == 0) {
/*  73 */         supported = true;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*  78 */     if (header[0] == 77 && header[1] == 77)
/*     */     {
/*     */       
/*  81 */       if (header[2] == 0 && header[3] == 42) {
/*  82 */         supported = true;
/*     */       }
/*     */     }
/*     */     
/*  86 */     if (supported) {
/*  87 */       ImageInfo info = createImageInfo(uri, in, context);
/*  88 */       return info;
/*     */     } 
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ImageInfo createImageInfo(String uri, ImageInputStream in, ImageContext context) throws IOException, ImageException {
/*  96 */     ImageInfo info = null;
/*  97 */     in.mark(); try {
/*     */       TIFFDirectory dir;
/*  99 */       int stripCount, pageIndex = ImageUtil.needPageIndexFromURI(uri);
/*     */       
/* 101 */       SeekableStreamAdapter seekableStreamAdapter = new SeekableStreamAdapter(in);
/*     */       
/*     */       try {
/* 104 */         dir = new TIFFDirectory((SeekableStream)seekableStreamAdapter, pageIndex);
/* 105 */       } catch (IllegalArgumentException iae) {
/* 106 */         String errorMessage = MessageFormat.format("Subimage {0} does not exist.", new Object[] { Integer.valueOf(pageIndex) });
/*     */         
/* 108 */         throw new SubImageNotFoundException(errorMessage);
/*     */       } 
/* 110 */       int width = (int)dir.getFieldAsLong(256);
/* 111 */       int height = (int)dir.getFieldAsLong(257);
/* 112 */       ImageSize size = new ImageSize();
/* 113 */       size.setSizeInPixels(width, height);
/* 114 */       int unit = 2;
/* 115 */       if (dir.isTagPresent(296)) {
/* 116 */         unit = (int)dir.getFieldAsLong(296);
/*     */       }
/* 118 */       if (unit == 2 || unit == 3) {
/*     */         float xRes, yRes;
/*     */         
/* 121 */         TIFFField fldx = dir.getField(282);
/* 122 */         TIFFField fldy = dir.getField(283);
/* 123 */         if (fldx == null || fldy == null) {
/* 124 */           unit = 2;
/* 125 */           xRes = context.getSourceResolution();
/* 126 */           yRes = xRes;
/*     */         } else {
/* 128 */           xRes = fldx.getAsFloat(0);
/* 129 */           yRes = fldy.getAsFloat(0);
/*     */         } 
/* 131 */         if (xRes == 0.0F || yRes == 0.0F) {
/*     */           
/* 133 */           size.setResolution(context.getSourceResolution());
/* 134 */         } else if (unit == 2) {
/* 135 */           size.setResolution(xRes, yRes);
/*     */         } else {
/* 137 */           size.setResolution(UnitConv.in2mm(xRes) / 10.0D, UnitConv.in2mm(yRes) / 10.0D);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 142 */         size.setResolution(context.getSourceResolution());
/*     */       } 
/* 144 */       size.calcSizeFromPixels();
/* 145 */       if (log.isTraceEnabled()) {
/* 146 */         log.trace("TIFF image detected: " + size);
/*     */       }
/*     */       
/* 149 */       info = new ImageInfo(uri, "image/tiff");
/* 150 */       info.setSize(size);
/*     */ 
/*     */ 
/*     */       
/* 154 */       TIFFField fld = dir.getField(259);
/* 155 */       if (fld != null) {
/* 156 */         int compression = fld.getAsInt(0);
/* 157 */         if (log.isTraceEnabled()) {
/* 158 */           log.trace("TIFF compression: " + compression);
/*     */         }
/* 160 */         info.getCustomObjects().put("TIFF_COMPRESSION", Integer.valueOf(compression));
/*     */       } 
/*     */       
/* 163 */       fld = dir.getField(322);
/* 164 */       if (fld != null) {
/* 165 */         if (log.isTraceEnabled()) {
/* 166 */           log.trace("TIFF is tiled");
/*     */         }
/* 168 */         info.getCustomObjects().put("TIFF_TILED", Boolean.TRUE);
/*     */       } 
/*     */ 
/*     */       
/* 172 */       fld = dir.getField(278);
/* 173 */       if (fld == null) {
/* 174 */         stripCount = 1;
/*     */       } else {
/* 176 */         stripCount = (int)Math.ceil(size.getHeightPx() / fld.getAsLong(0));
/*     */       } 
/* 178 */       if (log.isTraceEnabled()) {
/* 179 */         log.trace("TIFF has " + stripCount + " strips.");
/*     */       }
/* 181 */       info.getCustomObjects().put("TIFF_STRIP_COUNT", Integer.valueOf(stripCount));
/*     */ 
/*     */       
/*     */       try {
/* 185 */         new TIFFDirectory((SeekableStream)seekableStreamAdapter, pageIndex + 1);
/* 186 */         info.getCustomObjects().put(ImageInfo.HAS_MORE_IMAGES, Boolean.TRUE);
/* 187 */         if (log.isTraceEnabled()) {
/* 188 */           log.trace("TIFF is multi-page.");
/*     */         }
/* 190 */       } catch (IllegalArgumentException iae) {
/* 191 */         info.getCustomObjects().put(ImageInfo.HAS_MORE_IMAGES, Boolean.FALSE);
/*     */       } 
/*     */     } finally {
/* 194 */       in.reset();
/*     */     } 
/*     */     
/* 197 */     return info;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderTIFF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */