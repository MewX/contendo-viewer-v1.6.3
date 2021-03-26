/*    */ package org.apache.batik.ext.awt.image.codec.png;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
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
/*    */ public class PNGImageWriter
/*    */   implements ImageWriter
/*    */ {
/*    */   public void writeImage(RenderedImage image, OutputStream out) throws IOException {
/* 41 */     writeImage(image, out, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeImage(RenderedImage image, OutputStream out, ImageWriterParams params) throws IOException {
/* 49 */     PNGImageEncoder encoder = new PNGImageEncoder(out, null);
/* 50 */     encoder.encode(image);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMIMEType() {
/* 57 */     return "image/png";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGImageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */