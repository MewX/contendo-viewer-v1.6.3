/*     */ package com.github.jaiimageio.impl.plugins.gif;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public class GIFImageMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  55 */   private static IIOMetadataFormat instance = null;
/*     */   
/*     */   private GIFImageMetadataFormat() {
/*  58 */     super("javax_imageio_gif_image_1.0", 2);
/*     */ 
/*     */ 
/*     */     
/*  62 */     addElement("ImageDescriptor", "javax_imageio_gif_image_1.0", 0);
/*     */ 
/*     */     
/*  65 */     addAttribute("ImageDescriptor", "imageLeftPosition", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/*  68 */     addAttribute("ImageDescriptor", "imageTopPosition", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/*  71 */     addAttribute("ImageDescriptor", "imageWidth", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  74 */     addAttribute("ImageDescriptor", "imageHeight", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  77 */     addBooleanAttribute("ImageDescriptor", "interlaceFlag", false, false);
/*     */ 
/*     */ 
/*     */     
/*  81 */     addElement("LocalColorTable", "javax_imageio_gif_image_1.0", 2, 256);
/*     */ 
/*     */     
/*  84 */     addAttribute("LocalColorTable", "sizeOfLocalColorTable", 2, true, (String)null, 
/*     */         
/*  86 */         Arrays.asList(GIFStreamMetadata.colorTableSizes));
/*  87 */     addBooleanAttribute("LocalColorTable", "sortFlag", false, false);
/*     */ 
/*     */ 
/*     */     
/*  91 */     addElement("ColorTableEntry", "LocalColorTable", 0);
/*     */     
/*  93 */     addAttribute("ColorTableEntry", "index", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/*  96 */     addAttribute("ColorTableEntry", "red", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/*  99 */     addAttribute("ColorTableEntry", "green", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 102 */     addAttribute("ColorTableEntry", "blue", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     addElement("GraphicControlExtension", "javax_imageio_gif_image_1.0", 0);
/*     */ 
/*     */     
/* 110 */     addAttribute("GraphicControlExtension", "disposalMethod", 0, true, (String)null, 
/*     */         
/* 112 */         Arrays.asList(GIFImageMetadata.disposalMethodNames));
/* 113 */     addBooleanAttribute("GraphicControlExtension", "userInputFlag", false, false);
/*     */     
/* 115 */     addBooleanAttribute("GraphicControlExtension", "transparentColorFlag", false, false);
/*     */     
/* 117 */     addAttribute("GraphicControlExtension", "delayTime", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 120 */     addAttribute("GraphicControlExtension", "transparentColorIndex", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     addElement("PlainTextExtension", "javax_imageio_gif_image_1.0", 0);
/*     */ 
/*     */     
/* 128 */     addAttribute("PlainTextExtension", "textGridLeft", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 131 */     addAttribute("PlainTextExtension", "textGridTop", 2, true, null, "0", "65535", true, true);
/*     */ 
/*     */     
/* 134 */     addAttribute("PlainTextExtension", "textGridWidth", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/* 137 */     addAttribute("PlainTextExtension", "textGridHeight", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/* 140 */     addAttribute("PlainTextExtension", "characterCellWidth", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/* 143 */     addAttribute("PlainTextExtension", "characterCellHeight", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/* 146 */     addAttribute("PlainTextExtension", "textForegroundColor", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 149 */     addAttribute("PlainTextExtension", "textBackgroundColor", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     addElement("ApplicationExtensions", "javax_imageio_gif_image_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     addElement("ApplicationExtension", "ApplicationExtensions", 0);
/*     */     
/* 161 */     addAttribute("ApplicationExtension", "applicationID", 0, true, null);
/*     */     
/* 163 */     addAttribute("ApplicationExtension", "authenticationCode", 0, true, null);
/*     */     
/* 165 */     addObjectValue("ApplicationExtension", byte.class, 0, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 169 */     addElement("CommentExtensions", "javax_imageio_gif_image_1.0", 1, 2147483647);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     addElement("CommentExtension", "CommentExtensions", 0);
/*     */     
/* 176 */     addAttribute("CommentExtension", "value", 0, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 182 */     return true;
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 186 */     if (instance == null) {
/* 187 */       instance = new GIFImageMetadataFormat();
/*     */     }
/* 189 */     return instance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFImageMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */