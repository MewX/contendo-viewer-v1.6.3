/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.ttf.HorizontalMetricsTable;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PDTrueTypeFontEmbedder
/*     */   extends TrueTypeEmbedder
/*     */ {
/*     */   private final Encoding fontEncoding;
/*     */   
/*     */   PDTrueTypeFontEmbedder(PDDocument document, COSDictionary dict, TrueTypeFont ttf, Encoding encoding) throws IOException {
/*  57 */     super(document, dict, ttf, false);
/*  58 */     dict.setItem(COSName.SUBTYPE, (COSBase)COSName.TRUE_TYPE);
/*     */     
/*  60 */     GlyphList glyphList = GlyphList.getAdobeGlyphList();
/*  61 */     this.fontEncoding = encoding;
/*  62 */     dict.setItem(COSName.ENCODING, encoding.getCOSObject());
/*  63 */     this.fontDescriptor.setSymbolic(false);
/*  64 */     this.fontDescriptor.setNonSymbolic(true);
/*     */ 
/*     */     
/*  67 */     dict.setItem(COSName.FONT_DESC, this.fontDescriptor);
/*     */ 
/*     */     
/*  70 */     setWidths(dict, glyphList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setWidths(COSDictionary font, GlyphList glyphList) throws IOException {
/*  78 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*  79 */     HorizontalMetricsTable hmtx = this.ttf.getHorizontalMetrics();
/*     */     
/*  81 */     Map<Integer, String> codeToName = getFontEncoding().getCodeToNameMap();
/*     */     
/*  83 */     int firstChar = ((Integer)Collections.<Integer>min(codeToName.keySet())).intValue();
/*  84 */     int lastChar = ((Integer)Collections.<Integer>max(codeToName.keySet())).intValue();
/*     */     
/*  86 */     List<Integer> widths = new ArrayList<Integer>(lastChar - firstChar + 1);
/*  87 */     for (int i = 0; i < lastChar - firstChar + 1; i++)
/*     */     {
/*  89 */       widths.add(Integer.valueOf(0));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     for (Map.Entry<Integer, String> entry : codeToName.entrySet()) {
/*     */       
/*  96 */       int code = ((Integer)entry.getKey()).intValue();
/*  97 */       String name = entry.getValue();
/*     */       
/*  99 */       if (code >= firstChar && code <= lastChar) {
/*     */         
/* 101 */         String uni = glyphList.toUnicode(name);
/* 102 */         int charCode = uni.codePointAt(0);
/* 103 */         int gid = this.cmapLookup.getGlyphId(charCode);
/* 104 */         widths.set(((Integer)entry.getKey()).intValue() - firstChar, 
/* 105 */             Integer.valueOf(Math.round(hmtx.getAdvanceWidth(gid) * scaling)));
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     font.setInt(COSName.FIRST_CHAR, firstChar);
/* 110 */     font.setInt(COSName.LAST_CHAR, lastChar);
/* 111 */     font.setItem(COSName.WIDTHS, (COSBase)COSArrayList.converterToCOSArray(widths));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encoding getFontEncoding() {
/* 119 */     return this.fontEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buildSubset(InputStream ttfSubset, String tag, Map<Integer, Integer> gidToCid) throws IOException {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDTrueTypeFontEmbedder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */