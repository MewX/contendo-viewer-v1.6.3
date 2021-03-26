/*    */ package org.apache.xmlgraphics.image.writer.internal;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.xmlgraphics.image.codec.png.PNGImageEncoder;
/*    */ import org.apache.xmlgraphics.image.writer.AbstractImageWriter;
/*    */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
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
/*    */ public class PNGImageWriter
/*    */   extends AbstractImageWriter
/*    */ {
/*    */   public void writeImage(RenderedImage image, OutputStream out) throws IOException {
/* 41 */     writeImage(image, out, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeImage(RenderedImage image, OutputStream out, ImageWriterParams params) throws IOException {
/* 47 */     PNGImageEncoder encoder = new PNGImageEncoder(out, null);
/* 48 */     encoder.encode(image);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMIMEType() {
/* 53 */     return "image/png";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/internal/PNGImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */