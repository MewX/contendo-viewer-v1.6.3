/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSize;
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
/*    */ 
/*    */ 
/*    */ public class PreloaderJPEG
/*    */   extends AbstractImagePreloader
/*    */   implements JPEGConstants
/*    */ {
/*    */   private static final int JPG_SIG_LENGTH = 3;
/* 42 */   private static final int[] BYTES_PER_COMPONENT = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
/*    */   
/*    */   private static final int EXIF = 1165519206;
/*    */   
/*    */   private static final int II = 18761;
/*    */   
/*    */   private static final int MM = 19789;
/*    */   private static final int X_RESOLUTION = 282;
/*    */   private static final int Y_RESOLUTION = 283;
/*    */   private static final int RESOLUTION_UNIT = 296;
/*    */   
/*    */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException, ImageException {
/* 54 */     if (!ImageUtil.hasImageInputStream(src)) {
/* 55 */       return null;
/*    */     }
/* 57 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/* 58 */     byte[] header = getHeader(in, 3);
/* 59 */     boolean supported = (header[0] == -1 && header[1] == -40 && header[2] == -1);
/*    */ 
/*    */ 
/*    */     
/* 63 */     if (supported) {
/* 64 */       ImageInfo info = new ImageInfo(uri, "image/jpeg");
/* 65 */       info.setSize(determineSize(in, context));
/* 66 */       return info;
/*    */     } 
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private ImageSize determineSize(ImageInputStream in, ImageContext context) throws IOException, ImageException {
/* 74 */     in.mark();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderJPEG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */