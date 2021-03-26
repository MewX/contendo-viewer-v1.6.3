/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteOrder;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*    */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*    */ import org.apache.xmlgraphics.util.UnitConv;
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
/*    */ public class PreloaderBMP
/*    */   extends AbstractImagePreloader
/*    */ {
/*    */   protected static final int BMP_SIG_LENGTH = 2;
/*    */   private static final int WIDTH_OFFSET = 18;
/*    */   
/*    */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException, ImageException {
/* 49 */     if (!ImageUtil.hasImageInputStream(src)) {
/* 50 */       return null;
/*    */     }
/* 52 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/* 53 */     byte[] header = getHeader(in, 2);
/* 54 */     boolean supported = (header[0] == 66 && header[1] == 77);
/*    */ 
/*    */     
/* 57 */     if (supported) {
/* 58 */       ImageInfo info = new ImageInfo(uri, "image/bmp");
/* 59 */       info.setSize(determineSize(in, context));
/* 60 */       return info;
/*    */     } 
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private ImageSize determineSize(ImageInputStream in, ImageContext context) throws IOException, ImageException {
/* 68 */     in.mark();
/* 69 */     ByteOrder oldByteOrder = in.getByteOrder();
/*    */     try {
/* 71 */       ImageSize size = new ImageSize();
/*    */ 
/*    */       
/* 74 */       in.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*    */       
/* 76 */       in.skipBytes(18);
/* 77 */       int width = in.readInt();
/* 78 */       int height = in.readInt();
/* 79 */       size.setSizeInPixels(width, height);
/*    */       
/* 81 */       in.skipBytes(12);
/* 82 */       int xRes = in.readInt();
/* 83 */       double xResDPI = UnitConv.in2mm(xRes / 1000.0D);
/* 84 */       if (xResDPI == 0.0D) {
/* 85 */         xResDPI = context.getSourceResolution();
/*    */       }
/*    */       
/* 88 */       int yRes = in.readInt();
/* 89 */       double yResDPI = UnitConv.in2mm(yRes / 1000.0D);
/* 90 */       if (yResDPI == 0.0D) {
/* 91 */         yResDPI = context.getSourceResolution();
/*    */       }
/*    */       
/* 94 */       size.setResolution(xResDPI, yResDPI);
/* 95 */       size.calcSizeFromPixels();
/* 96 */       return size;
/*    */     } finally {
/* 98 */       in.setByteOrder(oldByteOrder);
/* 99 */       in.reset();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderBMP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */