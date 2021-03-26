/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
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
/*    */ 
/*    */ 
/*    */ public class ImageLoaderRaw
/*    */   extends AbstractImageLoader
/*    */ {
/*    */   private String mime;
/*    */   private ImageFlavor targetFlavor;
/*    */   
/*    */   public ImageLoaderRaw(ImageFlavor targetFlavor) {
/* 47 */     this.targetFlavor = targetFlavor;
/* 48 */     this.mime = ImageLoaderFactoryRaw.getMimeForRawFlavor(targetFlavor);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 53 */     return this.targetFlavor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 59 */     if (!this.mime.equals(info.getMimeType())) {
/* 60 */       throw new IllegalArgumentException("ImageInfo must be from a image with MIME type: " + this.mime);
/*    */     }
/*    */     
/* 63 */     Source src = session.needSource(info.getOriginalURI());
/* 64 */     ImageRawStream rawImage = new ImageRawStream(info, getTargetFlavor(), XmlSourceUtil.needInputStream(src));
/*    */     
/* 66 */     return rawImage;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderRaw.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */