/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.xmlgraphics.image.codec.util.ImageInputStreamSeekableStreamAdapter;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*    */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*    */ import org.apache.xmlgraphics.io.XmlSourceUtil;
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
/*    */ 
/*    */ 
/*    */ public class ImageLoaderRawPNG
/*    */   extends AbstractImageLoader
/*    */ {
/* 47 */   protected static final Log log = LogFactory.getLog(ImageLoaderRawPNG.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 57 */     return ImageFlavor.RAW_PNG;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 63 */     if (!"image/png".equals(info.getMimeType())) {
/* 64 */       throw new IllegalArgumentException("ImageInfo must be from a image with MIME type: image/png");
/*    */     }
/*    */ 
/*    */     
/* 68 */     Source src = session.needSource(info.getOriginalURI());
/* 69 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*    */     
/* 71 */     XmlSourceUtil.removeStreams(src);
/* 72 */     ImageInputStreamSeekableStreamAdapter imageInputStreamSeekableStreamAdapter = new ImageInputStreamSeekableStreamAdapter(in);
/* 73 */     PNGFile im = new PNGFile((InputStream)imageInputStreamSeekableStreamAdapter);
/* 74 */     ImageRawPNG irpng = im.getImageRawPNG(info);
/* 75 */     return irpng;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUsagePenalty() {
/* 83 */     return 1000;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderRawPNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */