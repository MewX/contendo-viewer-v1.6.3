/*    */ package com.levigo.jbig2;
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
/*    */ public class JBIG2ImageMetadataFormat
/*    */   extends IIOMetadataFormatImpl
/*    */ {
/* 30 */   private static IIOMetadataFormat instance = null;
/*    */   
/*    */   private JBIG2ImageMetadataFormat() {
/* 33 */     super("jbig2", 2);
/*    */ 
/*    */     
/* 36 */     addElement("ImageDescriptor", "jbig2", 0);
/* 37 */     addAttribute("ImageDescriptor", "imageWidth", 2, true, null, "1", "65535", true, true);
/* 38 */     addAttribute("ImageDescriptor", "imageHeight", 2, true, null, "1", "65535", true, true);
/* 39 */     addAttribute("ImageDescriptor", "Xdensity", 3, true, null, "1", "65535", true, true);
/* 40 */     addAttribute("ImageDescriptor", "Ydensity", 3, true, null, "1", "65535", true, true);
/*    */   }
/*    */   
/*    */   public boolean canNodeAppear(String paramString, ImageTypeSpecifier paramImageTypeSpecifier) {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public static synchronized IIOMetadataFormat getInstance() {
/* 48 */     if (instance == null) {
/* 49 */       instance = new JBIG2ImageMetadataFormat();
/*    */     }
/* 51 */     return instance;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2ImageMetadataFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */