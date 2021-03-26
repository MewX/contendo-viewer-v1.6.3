/*    */ package org.apache.xmlgraphics.image.loader.impl.imageio;
/*    */ 
/*    */ import javax.imageio.ImageIO;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.impl.AbstractImageLoaderFactory;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
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
/*    */ public class ImageLoaderFactoryImageIO
/*    */   extends AbstractImageLoaderFactory
/*    */ {
/* 33 */   private static final ImageFlavor[] FLAVORS = new ImageFlavor[] { ImageFlavor.RENDERED_IMAGE, ImageFlavor.BUFFERED_IMAGE };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getSupportedMIMETypes() {
/* 39 */     return ImageIO.getReaderMIMETypes();
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor[] getSupportedFlavors(String mime) {
/* 44 */     return FLAVORS;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/* 49 */     return (ImageLoader)new ImageLoaderImageIO(targetFlavor);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 54 */     return ((getSupportedMIMETypes()).length > 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/imageio/ImageLoaderFactoryImageIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */