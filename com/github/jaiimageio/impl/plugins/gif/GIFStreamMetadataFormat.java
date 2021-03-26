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
/*     */ public class GIFStreamMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  55 */   private static IIOMetadataFormat instance = null;
/*     */   
/*     */   private GIFStreamMetadataFormat() {
/*  58 */     super("javax_imageio_gif_stream_1.0", 2);
/*     */ 
/*     */ 
/*     */     
/*  62 */     addElement("Version", "javax_imageio_gif_stream_1.0", 0);
/*     */     
/*  64 */     addAttribute("Version", "value", 0, true, (String)null, 
/*     */         
/*  66 */         Arrays.asList(GIFStreamMetadata.versionStrings));
/*     */ 
/*     */     
/*  69 */     addElement("LogicalScreenDescriptor", "javax_imageio_gif_stream_1.0", 0);
/*     */ 
/*     */     
/*  72 */     addAttribute("LogicalScreenDescriptor", "logicalScreenWidth", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  75 */     addAttribute("LogicalScreenDescriptor", "logicalScreenHeight", 2, true, null, "1", "65535", true, true);
/*     */ 
/*     */     
/*  78 */     addAttribute("LogicalScreenDescriptor", "colorResolution", 2, true, null, "1", "8", true, true);
/*     */ 
/*     */     
/*  81 */     addAttribute("LogicalScreenDescriptor", "pixelAspectRatio", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     addElement("GlobalColorTable", "javax_imageio_gif_stream_1.0", 2, 256);
/*     */ 
/*     */     
/*  89 */     addAttribute("GlobalColorTable", "sizeOfGlobalColorTable", 2, true, (String)null, 
/*     */         
/*  91 */         Arrays.asList(GIFStreamMetadata.colorTableSizes));
/*  92 */     addAttribute("GlobalColorTable", "backgroundColorIndex", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/*  95 */     addBooleanAttribute("GlobalColorTable", "sortFlag", false, false);
/*     */ 
/*     */ 
/*     */     
/*  99 */     addElement("ColorTableEntry", "GlobalColorTable", 0);
/*     */     
/* 101 */     addAttribute("ColorTableEntry", "index", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 104 */     addAttribute("ColorTableEntry", "red", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 107 */     addAttribute("ColorTableEntry", "green", 2, true, null, "0", "255", true, true);
/*     */ 
/*     */     
/* 110 */     addAttribute("ColorTableEntry", "blue", 2, true, null, "0", "255", true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 121 */     if (instance == null) {
/* 122 */       instance = new GIFStreamMetadataFormat();
/*     */     }
/* 124 */     return instance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFStreamMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */