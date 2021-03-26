/*     */ package org.apache.xmlgraphics.image.loader.impl.imageio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*     */ import org.apache.xmlgraphics.image.loader.impl.AbstractImagePreloader;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreloaderImageIO
/*     */   extends AbstractImagePreloader
/*     */ {
/*     */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException, ImageException {
/*  51 */     if (!ImageUtil.hasImageInputStream(src)) {
/*  52 */       return null;
/*     */     }
/*  54 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*  55 */     Iterator<ImageReader> iter = ImageIO.getImageReaders(in);
/*  56 */     if (!iter.hasNext()) {
/*  57 */       return null;
/*     */     }
/*     */     
/*  60 */     IOException firstIOException = null;
/*  61 */     IIOMetadata iiometa = null;
/*  62 */     ImageSize size = null;
/*  63 */     String mime = null;
/*  64 */     while (iter.hasNext()) {
/*  65 */       in.mark();
/*     */       
/*  67 */       ImageReader reader = iter.next();
/*     */       try {
/*  69 */         reader.setInput(ImageUtil.ignoreFlushing(in), true, false);
/*  70 */         int imageIndex = 0;
/*  71 */         iiometa = reader.getImageMetadata(0);
/*  72 */         size = new ImageSize();
/*  73 */         size.setSizeInPixels(reader.getWidth(0), reader.getHeight(0));
/*  74 */         mime = reader.getOriginatingProvider().getMIMETypes()[0];
/*     */         break;
/*  76 */       } catch (IOException ioe) {
/*     */         
/*  78 */         if (firstIOException == null) {
/*  79 */           firstIOException = ioe;
/*     */         }
/*     */       } finally {
/*  82 */         reader.dispose();
/*  83 */         in.reset();
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     if (iiometa == null) {
/*  88 */       if (firstIOException == null) {
/*  89 */         throw new ImageException("Could not extract image metadata");
/*     */       }
/*  91 */       throw new ImageException("I/O error while extracting image metadata" + ((firstIOException.getMessage() != null) ? (": " + firstIOException.getMessage()) : ""), firstIOException);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     size.setResolution(context.getSourceResolution());
/* 101 */     ImageIOUtil.extractResolution(iiometa, size);
/* 102 */     if (size.getWidthPx() <= 0 || size.getHeightPx() <= 0)
/*     */     {
/*     */       
/* 105 */       return null;
/*     */     }
/* 107 */     if (size.getWidthMpt() == 0) {
/* 108 */       size.calcSizeFromPixels();
/*     */     }
/*     */     
/* 111 */     ImageInfo info = new ImageInfo(uri, mime);
/* 112 */     info.getCustomObjects().put(ImageIOUtil.IMAGEIO_METADATA, iiometa);
/* 113 */     info.setSize(size);
/*     */     
/* 115 */     return info;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPriority() {
/* 121 */     return 2000;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/imageio/PreloaderImageIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */