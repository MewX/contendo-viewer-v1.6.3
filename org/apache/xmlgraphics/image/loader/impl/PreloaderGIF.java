/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.imageio.ImageReader;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.loader.ImageContext;
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
/*    */ 
/*    */ public class PreloaderGIF
/*    */   extends AbstractImagePreloader
/*    */ {
/*    */   private static final int GIF_SIG_LENGTH = 10;
/*    */   
/*    */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException {
/* 46 */     if (!ImageUtil.hasImageInputStream(src)) {
/* 47 */       return null;
/*    */     }
/* 49 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/* 50 */     byte[] header = getHeader(in, 10);
/* 51 */     boolean supported = (header[0] == 71 && header[1] == 73 && header[2] == 70 && header[3] == 56 && (header[4] == 55 || header[4] == 57) && header[5] == 97);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     if (supported) {
/* 59 */       ImageInfo info = new ImageInfo(uri, "image/gif");
/* 60 */       info.setSize(determineSize(header, context, in));
/* 61 */       return info;
/*    */     } 
/* 63 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   private ImageSize determineSize(byte[] header, ImageContext context, ImageInputStream in) throws IOException {
/* 68 */     int[] dim = extractImageMetadata(in);
/* 69 */     ImageSize size = new ImageSize(dim[0], dim[1], context.getSourceResolution());
/* 70 */     size.calcSizeFromPixels();
/* 71 */     return size;
/*    */   }
/*    */   
/*    */   private int[] extractImageMetadata(ImageInputStream in) throws IOException {
/* 75 */     long startPos = in.getStreamPosition();
/* 76 */     Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");
/* 77 */     ImageReader reader = readers.next();
/* 78 */     reader.setInput(in, true);
/* 79 */     int width = reader.getWidth(0);
/* 80 */     int height = reader.getHeight(0);
/* 81 */     int[] dim = { width, height };
/* 82 */     in.seek(startPos);
/* 83 */     return dim;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderGIF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */