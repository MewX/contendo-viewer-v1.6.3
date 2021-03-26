/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*    */ import org.apache.xmlgraphics.io.XmlSourceUtil;
/*    */ import org.apache.xmlgraphics.util.io.SubInputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageLoaderEPS
/*    */   extends AbstractImageLoader
/*    */ {
/*    */   public ImageFlavor getTargetFlavor() {
/* 50 */     return ImageFlavor.RAW_EPS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/*    */     SubInputStream subInputStream;
/* 56 */     if (!"application/postscript".equals(info.getMimeType())) {
/* 57 */       throw new IllegalArgumentException("ImageInfo must be from a image with MIME type: application/postscript");
/*    */     }
/*    */     
/* 60 */     Source src = session.needSource(info.getOriginalURI());
/* 61 */     InputStream in = XmlSourceUtil.needInputStream(src);
/* 62 */     XmlSourceUtil.removeStreams(src);
/*    */ 
/*    */     
/* 65 */     PreloaderEPS.EPSBinaryFileHeader binaryHeader = (PreloaderEPS.EPSBinaryFileHeader)info.getCustomObjects().get(PreloaderEPS.EPS_BINARY_HEADER);
/*    */     
/* 67 */     if (binaryHeader != null) {
/*    */       
/* 69 */       in.skip(binaryHeader.getPSStart());
/* 70 */       subInputStream = new SubInputStream(in, binaryHeader.getPSLength(), true);
/*    */     } 
/*    */     
/* 73 */     ImageRawEPS epsImage = new ImageRawEPS(info, (InputStream)subInputStream);
/* 74 */     return epsImage;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderEPS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */