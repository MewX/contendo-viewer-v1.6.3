/*    */ package org.apache.batik.ext.awt.image.codec.imageio;
/*    */ 
/*    */ import org.apache.batik.ext.awt.image.spi.MagicNumberRegistryEntry;
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
/*    */ public class ImageIOJPEGRegistryEntry
/*    */   extends AbstractImageIORegistryEntry
/*    */ {
/* 29 */   static final byte[] sigJPEG = new byte[] { -1, -40, -1 };
/*    */   
/* 31 */   static final String[] exts = new String[] { "jpeg", "jpg" };
/* 32 */   static final String[] mimeTypes = new String[] { "image/jpeg", "image/jpg" };
/* 33 */   static final MagicNumberRegistryEntry.MagicNumber[] magicNumbers = new MagicNumberRegistryEntry.MagicNumber[] { new MagicNumberRegistryEntry.MagicNumber(0, sigJPEG) };
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageIOJPEGRegistryEntry() {
/* 38 */     super("JPEG", exts, mimeTypes, magicNumbers);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIOJPEGRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */