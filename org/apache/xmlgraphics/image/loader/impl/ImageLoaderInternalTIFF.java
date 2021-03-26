/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.xmlgraphics.image.codec.tiff.TIFFImage;
/*    */ import org.apache.xmlgraphics.image.codec.util.ImageInputStreamSeekableStreamAdapter;
/*    */ import org.apache.xmlgraphics.image.codec.util.SeekableStream;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*    */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageLoaderInternalTIFF
/*    */   extends AbstractImageLoader
/*    */ {
/* 46 */   protected static final Log log = LogFactory.getLog(ImageLoaderInternalTIFF.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 57 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 64 */     Source src = session.needSource(info.getOriginalURI());
/* 65 */     ImageInputStream imgStream = ImageUtil.needImageInputStream(src);
/*    */     
/* 67 */     ImageInputStreamSeekableStreamAdapter imageInputStreamSeekableStreamAdapter = new ImageInputStreamSeekableStreamAdapter(imgStream);
/*    */     try {
/* 69 */       TIFFImage img = new TIFFImage((SeekableStream)imageInputStreamSeekableStreamAdapter, null, 0);
/*    */ 
/*    */ 
/*    */       
/* 73 */       return new ImageRendered(info, (RenderedImage)img, null);
/* 74 */     } catch (RuntimeException e) {
/* 75 */       throw new ImageException("Could not load image with internal TIFF codec", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUsagePenalty() {
/* 81 */     return 1000;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderInternalTIFF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */