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
/*    */ 
/*    */ 
/*    */ public class ImageIOTIFFRegistryEntry
/*    */   extends AbstractImageIORegistryEntry
/*    */ {
/* 31 */   static final byte[] sig1 = new byte[] { 73, 73, 42, 0 };
/* 32 */   static final byte[] sig2 = new byte[] { 77, 77, 0, 42 };
/*    */   
/* 34 */   static MagicNumberRegistryEntry.MagicNumber[] magicNumbers = new MagicNumberRegistryEntry.MagicNumber[] { new MagicNumberRegistryEntry.MagicNumber(0, sig1), new MagicNumberRegistryEntry.MagicNumber(0, sig2) };
/*    */ 
/*    */ 
/*    */   
/* 38 */   static final String[] exts = new String[] { "tiff", "tif" };
/* 39 */   static final String[] mimeTypes = new String[] { "image/tiff", "image/tif" };
/*    */   
/*    */   public ImageIOTIFFRegistryEntry() {
/* 42 */     super("TIFF", exts, mimeTypes, magicNumbers);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIOTIFFRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */