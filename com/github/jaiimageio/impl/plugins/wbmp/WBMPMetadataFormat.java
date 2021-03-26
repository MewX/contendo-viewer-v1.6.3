/*    */ package com.github.jaiimageio.impl.plugins.wbmp;
/*    */ 
/*    */ import javax.imageio.ImageTypeSpecifier;
/*    */ import javax.imageio.metadata.IIOMetadataFormat;
/*    */ import javax.imageio.metadata.IIOMetadataFormatImpl;
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
/*    */ class WBMPMetadataFormat
/*    */   extends IIOMetadataFormatImpl
/*    */ {
/* 55 */   private static IIOMetadataFormat instance = null;
/*    */   
/*    */   private WBMPMetadataFormat() {
/* 58 */     super("com_sun_media_imageio_plugins_wbmp_image_1.0", 2);
/*    */ 
/*    */ 
/*    */     
/* 62 */     addElement("ImageDescriptor", "com_sun_media_imageio_plugins_wbmp_image_1.0", 0);
/*    */ 
/*    */ 
/*    */     
/* 66 */     addAttribute("ImageDescriptor", "WBMPType", 2, true, "0");
/*    */ 
/*    */     
/* 69 */     addAttribute("ImageDescriptor", "Width", 2, true, null, "0", "65535", true, true);
/*    */ 
/*    */     
/* 72 */     addAttribute("ImageDescriptor", "Height", 2, true, null, "1", "65535", true, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 81 */     return true;
/*    */   }
/*    */   
/*    */   public static synchronized IIOMetadataFormat getInstance() {
/* 85 */     if (instance == null) {
/* 86 */       instance = new WBMPMetadataFormat();
/*    */     }
/* 88 */     return instance;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */