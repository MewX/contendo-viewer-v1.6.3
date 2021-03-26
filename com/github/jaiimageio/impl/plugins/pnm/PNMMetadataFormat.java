/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.imageio.ImageTypeSpecifier;
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
/*     */ public class PNMMetadataFormat
/*     */   extends IIOMetadataFormatImpl
/*     */ {
/*  54 */   private static Hashtable parents = new Hashtable<Object, Object>();
/*     */   private static PNMMetadataFormat instance;
/*     */   
/*     */   static {
/*  58 */     parents.put("FormatName", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*  59 */     parents.put("Variant", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*  60 */     parents.put("Width", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*  61 */     parents.put("Height", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*  62 */     parents.put("MaximumSample", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*  63 */     parents.put("Comment", "com_sun_media_imageio_plugins_pnm_image_1.0");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized PNMMetadataFormat getInstance() {
/*  69 */     if (instance == null)
/*  70 */       instance = new PNMMetadataFormat(); 
/*  71 */     return instance;
/*     */   }
/*     */   
/*  74 */   String resourceBaseName = getClass().getName() + "Resources";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PNMMetadataFormat() {
/*  81 */     super("com_sun_media_imageio_plugins_pnm_image_1.0", 1);
/*  82 */     setResourceBaseName(this.resourceBaseName);
/*  83 */     addElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addElements() {
/*  90 */     addElement("FormatName", 
/*  91 */         getParent("FormatName"), 0);
/*     */ 
/*     */     
/*  94 */     addElement("Variant", 
/*  95 */         getParent("Variant"), 0);
/*     */     
/*  97 */     addElement("Width", 
/*  98 */         getParent("Width"), 0);
/*     */     
/* 100 */     addElement("Height", 
/* 101 */         getParent("Height"), 0);
/*     */     
/* 103 */     addElement("MaximumSample", 
/* 104 */         getParent("MaximumSample"), 0);
/*     */     
/* 106 */     addElement("Comment", 
/* 107 */         getParent("Comment"), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParent(String elementName) {
/* 112 */     return (String)parents.get(elementName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/* 117 */     if (getParent(elementName) != null)
/* 118 */       return true; 
/* 119 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */