/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.codec.png.PNGImageDecoder;
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
/*    */ public class PreloaderRawPNG
/*    */   extends AbstractImagePreloader
/*    */ {
/*    */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws ImageException, IOException {
/* 36 */     if (!ImageUtil.hasImageInputStream(src)) {
/* 37 */       return null;
/*    */     }
/* 39 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/* 40 */     long bb = ByteBuffer.wrap(getHeader(in, 8)).getLong();
/* 41 */     if (bb != -8552249625308161526L) {
/* 42 */       return null;
/*    */     }
/* 44 */     in.mark();
/* 45 */     ImageSize size = new ImageSize();
/*    */     
/* 47 */     size.setResolution(context.getSourceResolution());
/*    */     try {
/* 49 */       PNGImageDecoder.readPNGHeader(in, size);
/*    */     } finally {
/* 51 */       in.reset();
/*    */     } 
/*    */     
/* 54 */     ImageInfo info = new ImageInfo(uri, "image/png");
/* 55 */     info.setSize(size);
/* 56 */     return info;
/*    */   }
/*    */   
/*    */   public int getPriority() {
/* 60 */     return 2000;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderRawPNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */