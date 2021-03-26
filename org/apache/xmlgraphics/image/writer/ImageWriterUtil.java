/*    */ package org.apache.xmlgraphics.image.writer;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.commons.io.IOUtils;
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
/*    */ public final class ImageWriterUtil
/*    */ {
/*    */   public static void saveAsPNG(RenderedImage bitmap, File outputFile) throws IOException {
/* 45 */     saveAsPNG(bitmap, 96, outputFile);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void saveAsPNG(RenderedImage bitmap, int resolution, File outputFile) throws IOException {
/* 57 */     saveAsFile(bitmap, resolution, outputFile, "image/png");
/*    */   }
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
/*    */   public static void saveAsFile(RenderedImage bitmap, int resolution, File outputFile, String mime) throws IOException {
/* 71 */     OutputStream out = new FileOutputStream(outputFile);
/*    */     try {
/* 73 */       ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor(mime);
/* 74 */       ImageWriterParams params = new ImageWriterParams();
/* 75 */       params.setResolution(resolution);
/* 76 */       writer.writeImage(bitmap, out, params);
/*    */     } finally {
/* 78 */       IOUtils.closeQuietly(out);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/ImageWriterUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */