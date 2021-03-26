/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.ResourceCache;
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
/*     */ public final class PDFontFactory
/*     */ {
/*  34 */   private static final Log LOG = LogFactory.getLog(PDFontFactory.class);
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
/*     */   public static PDFont createFont(COSDictionary dictionary) throws IOException {
/*  49 */     return createFont(dictionary, null);
/*     */   }
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
/*     */   public static PDFont createFont(COSDictionary dictionary, ResourceCache resourceCache) throws IOException {
/*  62 */     COSName type = dictionary.getCOSName(COSName.TYPE, COSName.FONT);
/*  63 */     if (!COSName.FONT.equals(type))
/*     */     {
/*  65 */       LOG.error("Expected 'Font' dictionary but found '" + type.getName() + "'");
/*     */     }
/*     */     
/*  68 */     COSName subType = dictionary.getCOSName(COSName.SUBTYPE);
/*  69 */     if (COSName.TYPE1.equals(subType)) {
/*     */       
/*  71 */       COSBase fd = dictionary.getDictionaryObject(COSName.FONT_DESC);
/*  72 */       if (fd instanceof COSDictionary && ((COSDictionary)fd).containsKey(COSName.FONT_FILE3))
/*     */       {
/*  74 */         return new PDType1CFont(dictionary);
/*     */       }
/*  76 */       return new PDType1Font(dictionary);
/*     */     } 
/*  78 */     if (COSName.MM_TYPE1.equals(subType)) {
/*     */       
/*  80 */       COSBase fd = dictionary.getDictionaryObject(COSName.FONT_DESC);
/*  81 */       if (fd instanceof COSDictionary && ((COSDictionary)fd).containsKey(COSName.FONT_FILE3))
/*     */       {
/*  83 */         return new PDType1CFont(dictionary);
/*     */       }
/*  85 */       return new PDMMType1Font(dictionary);
/*     */     } 
/*  87 */     if (COSName.TRUE_TYPE.equals(subType))
/*     */     {
/*  89 */       return new PDTrueTypeFont(dictionary);
/*     */     }
/*  91 */     if (COSName.TYPE3.equals(subType))
/*     */     {
/*  93 */       return new PDType3Font(dictionary, resourceCache);
/*     */     }
/*  95 */     if (COSName.TYPE0.equals(subType))
/*     */     {
/*  97 */       return new PDType0Font(dictionary);
/*     */     }
/*  99 */     if (COSName.CID_FONT_TYPE0.equals(subType))
/*     */     {
/* 101 */       throw new IllegalArgumentException("Type 0 descendant font not allowed");
/*     */     }
/* 103 */     if (COSName.CID_FONT_TYPE2.equals(subType))
/*     */     {
/* 105 */       throw new IllegalArgumentException("Type 2 descendant font not allowed");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     LOG.warn("Invalid font subtype '" + subType + "'");
/* 112 */     return new PDType1Font(dictionary);
/*     */   }
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
/*     */   static PDCIDFont createDescendantFont(COSDictionary dictionary, PDType0Font parent) throws IOException {
/* 126 */     COSName type = dictionary.getCOSName(COSName.TYPE, COSName.FONT);
/* 127 */     if (!COSName.FONT.equals(type))
/*     */     {
/* 129 */       throw new IllegalArgumentException("Expected 'Font' dictionary but found '" + type.getName() + "'");
/*     */     }
/*     */     
/* 132 */     COSName subType = dictionary.getCOSName(COSName.SUBTYPE);
/* 133 */     if (COSName.CID_FONT_TYPE0.equals(subType))
/*     */     {
/* 135 */       return new PDCIDFontType0(dictionary, parent);
/*     */     }
/* 137 */     if (COSName.CID_FONT_TYPE2.equals(subType))
/*     */     {
/* 139 */       return new PDCIDFontType2(dictionary, parent);
/*     */     }
/*     */ 
/*     */     
/* 143 */     throw new IOException("Invalid font type: " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDFont createDefaultFont() throws IOException {
/* 155 */     COSDictionary dict = new COSDictionary();
/* 156 */     dict.setItem(COSName.TYPE, (COSBase)COSName.FONT);
/* 157 */     dict.setItem(COSName.SUBTYPE, (COSBase)COSName.TRUE_TYPE);
/* 158 */     dict.setString(COSName.BASE_FONT, "Arial");
/* 159 */     return createFont(dict);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDFontFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */