/*    */ package org.apache.batik.ext.awt.image.codec.imageio;
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
/*    */ public class ImageIOPNGRegistryEntry
/*    */   extends AbstractImageIORegistryEntry
/*    */ {
/* 30 */   static final byte[] signature = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
/*    */   
/*    */   public ImageIOPNGRegistryEntry() {
/* 33 */     super("PNG", "png", "image/png", 0, signature);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIOPNGRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */