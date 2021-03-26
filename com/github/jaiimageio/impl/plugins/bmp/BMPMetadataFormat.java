/*     */ package com.github.jaiimageio.impl.plugins.bmp;
/*     */ 
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadataFormat;
/*     */ import javax.imageio.metadata.IIOMetadataFormatImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BMPMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  54 */   private static IIOMetadataFormat instance = null;
/*     */   
/*     */   private BMPMetadataFormat() {
/*  57 */     super("com_sun_media_imageio_plugins_bmp_image_1.0", 2);
/*     */ 
/*     */ 
/*     */     
/*  61 */     addElement("ImageDescriptor", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/*  64 */     addAttribute("ImageDescriptor", "bmpVersion", 0, true, null);
/*     */     
/*  66 */     addAttribute("ImageDescriptor", "width", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/*  69 */     addAttribute("ImageDescriptor", "height", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  72 */     addAttribute("ImageDescriptor", "bitsPerPixel", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  75 */     addAttribute("ImageDescriptor", "compression", 2, false, null);
/*     */     
/*  77 */     addAttribute("ImageDescriptor", "imageSize", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/*  81 */     addElement("PixelsPerMeter", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/*  84 */     addAttribute("PixelsPerMeter", "X", 2, false, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  87 */     addAttribute("PixelsPerMeter", "Y", 2, false, null, "1", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/*  91 */     addElement("ColorsUsed", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/*  94 */     addAttribute("ColorsUsed", "value", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/*  98 */     addElement("ColorsImportant", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/* 101 */     addAttribute("ColorsImportant", "value", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/* 105 */     addElement("BI_BITFIELDS_Mask", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/* 108 */     addAttribute("BI_BITFIELDS_Mask", "red", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 111 */     addAttribute("BI_BITFIELDS_Mask", "green", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 114 */     addAttribute("BI_BITFIELDS_Mask", "blue", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/* 118 */     addElement("ColorSpace", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/* 121 */     addAttribute("ColorSpace", "value", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/* 125 */     addElement("LCS_CALIBRATED_RGB", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     addAttribute("LCS_CALIBRATED_RGB", "redX", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 133 */     addAttribute("LCS_CALIBRATED_RGB", "redY", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 136 */     addAttribute("LCS_CALIBRATED_RGB", "redZ", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 139 */     addAttribute("LCS_CALIBRATED_RGB", "greenX", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 142 */     addAttribute("LCS_CALIBRATED_RGB", "greenY", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 145 */     addAttribute("LCS_CALIBRATED_RGB", "greenZ", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 148 */     addAttribute("LCS_CALIBRATED_RGB", "blueX", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 151 */     addAttribute("LCS_CALIBRATED_RGB", "blueY", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 154 */     addAttribute("LCS_CALIBRATED_RGB", "blueZ", 4, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/* 158 */     addElement("LCS_CALIBRATED_RGB_GAMMA", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/* 161 */     addAttribute("LCS_CALIBRATED_RGB_GAMMA", "red", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 164 */     addAttribute("LCS_CALIBRATED_RGB_GAMMA", "green", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 167 */     addAttribute("LCS_CALIBRATED_RGB_GAMMA", "blue", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */     
/* 171 */     addElement("Intent", "com_sun_media_imageio_plugins_bmp_image_1.0", 0);
/*     */ 
/*     */     
/* 174 */     addAttribute("Intent", "value", 2, false, null, "0", "65535", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     addElement("Palette", "com_sun_media_imageio_plugins_bmp_image_1.0", 2, 256);
/*     */ 
/*     */     
/* 182 */     addAttribute("Palette", "sizeOfPalette", 2, true, null);
/*     */     
/* 184 */     addBooleanAttribute("Palette", "sortFlag", false, false);
/*     */ 
/*     */ 
/*     */     
/* 188 */     addElement("PaletteEntry", "Palette", 0);
/*     */     
/* 190 */     addAttribute("PaletteEntry", "index", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 193 */     addAttribute("PaletteEntry", "red", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 196 */     addAttribute("PaletteEntry", "green", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 199 */     addAttribute("PaletteEntry", "blue", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     addElement("CommentExtensions", "com_sun_media_imageio_plugins_bmp_image_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     addElement("CommentExtension", "CommentExtensions", 0);
/*     */     
/* 212 */     addAttribute("CommentExtension", "value", 0, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 218 */     return true;
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 222 */     if (instance == null) {
/* 223 */       instance = new BMPMetadataFormat();
/*     */     }
/* 225 */     return instance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */