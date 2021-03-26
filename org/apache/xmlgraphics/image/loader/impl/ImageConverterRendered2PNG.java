/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.io.output.ByteArrayOutputStream;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.writer.ImageWriter;
/*    */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
/*    */ import org.apache.xmlgraphics.image.writer.ImageWriterRegistry;
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
/*    */ public class ImageConverterRendered2PNG
/*    */   extends AbstractImageConverter
/*    */ {
/*    */   public Image convert(Image src, Map hints) throws ImageException, IOException {
/* 42 */     checkSourceFlavor(src);
/* 43 */     assert src instanceof ImageRendered;
/* 44 */     ImageRendered rendered = (ImageRendered)src;
/* 45 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/* 46 */     if (writer == null) {
/* 47 */       throw new ImageException("Cannot convert image to PNG. No suitable ImageWriter found.");
/*    */     }
/* 49 */     ByteArrayOutputStream baout = new ByteArrayOutputStream();
/* 50 */     ImageWriterParams params = new ImageWriterParams();
/* 51 */     params.setResolution((int)Math.round(src.getSize().getDpiHorizontal()));
/* 52 */     writer.writeImage(rendered.getRenderedImage(), (OutputStream)baout, params);
/* 53 */     return new ImageRawStream(src.getInfo(), getTargetFlavor(), new ByteArrayInputStream(baout.toByteArray()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getSourceFlavor() {
/* 59 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 64 */     return ImageFlavor.RAW_PNG;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageConverterRendered2PNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */