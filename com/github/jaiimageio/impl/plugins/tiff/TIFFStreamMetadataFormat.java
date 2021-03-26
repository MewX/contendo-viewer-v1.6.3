/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadataFormat;
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
/*     */ public class TIFFStreamMetadataFormat
/*     */   extends TIFFMetadataFormat
/*     */ {
/*  64 */   private static TIFFStreamMetadataFormat theInstance = null;
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   private TIFFStreamMetadataFormat() {
/*  72 */     this.resourceBaseName = "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormatResources";
/*     */     
/*  74 */     this.rootName = "com_sun_media_imageio_plugins_tiff_stream_1.0";
/*     */ 
/*     */ 
/*     */     
/*  78 */     String[] empty = new String[0];
/*     */ 
/*     */ 
/*     */     
/*  82 */     String[] childNames = { "ByteOrder" };
/*  83 */     TIFFElementInfo einfo = new TIFFElementInfo(childNames, empty, 1);
/*     */     
/*  85 */     this.elementInfoMap.put("com_sun_media_imageio_plugins_tiff_stream_1.0", einfo);
/*     */ 
/*     */     
/*  88 */     childNames = empty;
/*  89 */     String[] attrNames = { "value" };
/*  90 */     einfo = new TIFFElementInfo(childNames, attrNames, 0);
/*  91 */     this.elementInfoMap.put("ByteOrder", einfo);
/*     */     
/*  93 */     TIFFAttrInfo ainfo = new TIFFAttrInfo();
/*  94 */     ainfo.dataType = 0;
/*  95 */     ainfo.isRequired = true;
/*  96 */     this.attrInfoMap.put("ByteOrder/value", ainfo);
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 100 */     if (theInstance == null) {
/* 101 */       theInstance = new TIFFStreamMetadataFormat();
/*     */     }
/* 103 */     return theInstance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFStreamMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */