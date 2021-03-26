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
/*     */ public class TIFFImageMetadataFormat
/*     */   extends TIFFMetadataFormat
/*     */ {
/*  64 */   private static TIFFImageMetadataFormat theInstance = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canNodeAppear(String elementName, ImageTypeSpecifier imageType) {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   private TIFFImageMetadataFormat() {
/*  75 */     this.resourceBaseName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormatResources";
/*     */     
/*  77 */     this.rootName = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*     */ 
/*     */ 
/*     */     
/*  81 */     String[] empty = new String[0];
/*     */ 
/*     */ 
/*     */     
/*  85 */     String[] childNames = { "TIFFIFD" };
/*  86 */     TIFFElementInfo einfo = new TIFFElementInfo(childNames, empty, 4);
/*     */     
/*  88 */     this.elementInfoMap.put("com_sun_media_imageio_plugins_tiff_image_1.0", einfo);
/*     */ 
/*     */     
/*  91 */     childNames = new String[] { "TIFFField", "TIFFIFD" };
/*  92 */     String[] attrNames = { "tagSets", "parentTagNumber", "parentTagName" };
/*     */     
/*  94 */     einfo = new TIFFElementInfo(childNames, attrNames, 4);
/*  95 */     this.elementInfoMap.put("TIFFIFD", einfo);
/*     */     
/*  97 */     TIFFAttrInfo ainfo = new TIFFAttrInfo();
/*  98 */     ainfo.dataType = 0;
/*  99 */     ainfo.isRequired = true;
/* 100 */     this.attrInfoMap.put("TIFFIFD/tagSets", ainfo);
/*     */     
/* 102 */     ainfo = new TIFFAttrInfo();
/* 103 */     ainfo.dataType = 2;
/* 104 */     ainfo.isRequired = false;
/* 105 */     this.attrInfoMap.put("TIFFIFD/parentTagNumber", ainfo);
/*     */     
/* 107 */     ainfo = new TIFFAttrInfo();
/* 108 */     ainfo.dataType = 0;
/* 109 */     ainfo.isRequired = false;
/* 110 */     this.attrInfoMap.put("TIFFIFD/parentTagName", ainfo);
/*     */     
/* 112 */     String[] types = { "TIFFByte", "TIFFAscii", "TIFFShort", "TIFFSShort", "TIFFLong", "TIFFSLong", "TIFFRational", "TIFFSRational", "TIFFFloat", "TIFFDouble", "TIFFUndefined" };
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
/* 126 */     attrNames = new String[] { "value", "description" };
/* 127 */     String[] attrNamesValueOnly = { "value" };
/* 128 */     TIFFAttrInfo ainfoValue = new TIFFAttrInfo();
/* 129 */     TIFFAttrInfo ainfoDescription = new TIFFAttrInfo();
/*     */     int i;
/* 131 */     for (i = 0; i < types.length; i++) {
/* 132 */       if (!types[i].equals("TIFFUndefined")) {
/* 133 */         childNames = new String[1];
/* 134 */         childNames[0] = types[i];
/* 135 */         einfo = new TIFFElementInfo(childNames, empty, 4);
/*     */         
/* 137 */         this.elementInfoMap.put(types[i] + "s", einfo);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       boolean hasDescription = (!types[i].equals("TIFFUndefined") && !types[i].equals("TIFFAscii") && !types[i].equals("TIFFRational") && !types[i].equals("TIFFSRational") && !types[i].equals("TIFFFloat") && !types[i].equals("TIFFDouble"));
/*     */       
/* 148 */       String[] anames = hasDescription ? attrNames : attrNamesValueOnly;
/* 149 */       einfo = new TIFFElementInfo(empty, anames, 0);
/* 150 */       this.elementInfoMap.put(types[i], einfo);
/*     */       
/* 152 */       this.attrInfoMap.put(types[i] + "/value", ainfoValue);
/* 153 */       if (hasDescription) {
/* 154 */         this.attrInfoMap.put(types[i] + "/description", ainfoDescription);
/*     */       }
/*     */     } 
/*     */     
/* 158 */     childNames = new String[2 * types.length - 1];
/* 159 */     for (i = 0; i < types.length; i++) {
/* 160 */       childNames[2 * i] = types[i];
/* 161 */       if (!types[i].equals("TIFFUndefined")) {
/* 162 */         childNames[2 * i + 1] = types[i] + "s";
/*     */       }
/*     */     } 
/* 165 */     attrNames = new String[] { "number", "name" };
/* 166 */     einfo = new TIFFElementInfo(childNames, attrNames, 3);
/* 167 */     this.elementInfoMap.put("TIFFField", einfo);
/*     */     
/* 169 */     ainfo = new TIFFAttrInfo();
/* 170 */     ainfo.isRequired = true;
/* 171 */     this.attrInfoMap.put("TIFFField/number", ainfo);
/*     */     
/* 173 */     ainfo = new TIFFAttrInfo();
/* 174 */     this.attrInfoMap.put("TIFFField/name", ainfo);
/*     */   }
/*     */   
/*     */   public static synchronized IIOMetadataFormat getInstance() {
/* 178 */     if (theInstance == null) {
/* 179 */       theInstance = new TIFFImageMetadataFormat();
/*     */     }
/* 181 */     return theInstance;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFImageMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */