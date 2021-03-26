/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImagePreloader;
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
/*    */ 
/*    */ public abstract class AbstractImagePreloader
/*    */   implements ImagePreloader
/*    */ {
/*    */   protected byte[] getHeader(ImageInputStream in, int size) throws IOException {
/* 44 */     byte[] header = new byte[size];
/* 45 */     long startPos = in.getStreamPosition();
/* 46 */     int read = in.read(header);
/* 47 */     if (read < size) {
/* 48 */       Arrays.fill(header, (byte)0);
/*    */     }
/* 50 */     in.seek(startPos);
/* 51 */     return header;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority() {
/* 56 */     return 1000;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImagePreloader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */