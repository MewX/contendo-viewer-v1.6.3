/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.fontbox.afm.FontMetrics;
/*     */ import org.apache.fontbox.pfb.PfbParser;
/*     */ import org.apache.fontbox.type1.Type1Font;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PDType1FontEmbedder
/*     */ {
/*     */   private final Encoding fontEncoding;
/*     */   private final Type1Font type1;
/*     */   
/*     */   PDType1FontEmbedder(PDDocument doc, COSDictionary dict, InputStream pfbStream, Encoding encoding) throws IOException {
/*  58 */     dict.setItem(COSName.SUBTYPE, (COSBase)COSName.TYPE1);
/*     */ 
/*     */     
/*  61 */     byte[] pfbBytes = IOUtils.toByteArray(pfbStream);
/*  62 */     PfbParser pfbParser = new PfbParser(pfbBytes);
/*  63 */     this.type1 = Type1Font.createWithPFB(pfbBytes);
/*     */     
/*  65 */     if (encoding == null) {
/*     */       
/*  67 */       this.fontEncoding = (Encoding)Type1Encoding.fromFontBox(this.type1.getEncoding());
/*     */     }
/*     */     else {
/*     */       
/*  71 */       this.fontEncoding = encoding;
/*     */     } 
/*     */ 
/*     */     
/*  75 */     PDFontDescriptor fd = buildFontDescriptor(this.type1);
/*     */     
/*  77 */     PDStream fontStream = new PDStream(doc, pfbParser.getInputStream(), COSName.FLATE_DECODE);
/*  78 */     fontStream.getCOSObject().setInt("Length", pfbParser.size());
/*  79 */     for (int i = 0; i < (pfbParser.getLengths()).length; i++)
/*     */     {
/*  81 */       fontStream.getCOSObject().setInt("Length" + (i + 1), pfbParser.getLengths()[i]);
/*     */     }
/*  83 */     fd.setFontFile(fontStream);
/*     */ 
/*     */     
/*  86 */     dict.setItem(COSName.FONT_DESC, fd);
/*  87 */     dict.setName(COSName.BASE_FONT, this.type1.getName());
/*     */ 
/*     */     
/*  90 */     List<Integer> widths = new ArrayList<Integer>(256);
/*  91 */     for (int code = 0; code <= 255; code++) {
/*     */       
/*  93 */       String name = this.fontEncoding.getName(code);
/*  94 */       int width = Math.round(this.type1.getWidth(name));
/*  95 */       widths.add(Integer.valueOf(width));
/*     */     } 
/*     */     
/*  98 */     dict.setInt(COSName.FIRST_CHAR, 0);
/*  99 */     dict.setInt(COSName.LAST_CHAR, 255);
/* 100 */     dict.setItem(COSName.WIDTHS, (COSBase)COSArrayList.converterToCOSArray(widths));
/* 101 */     dict.setItem(COSName.ENCODING, (COSObjectable)encoding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static PDFontDescriptor buildFontDescriptor(Type1Font type1) {
/* 109 */     boolean isSymbolic = type1.getEncoding() instanceof org.apache.fontbox.encoding.BuiltInEncoding;
/*     */ 
/*     */     
/* 112 */     PDFontDescriptor fd = new PDFontDescriptor();
/* 113 */     fd.setFontName(type1.getName());
/* 114 */     fd.setFontFamily(type1.getFamilyName());
/* 115 */     fd.setNonSymbolic(!isSymbolic);
/* 116 */     fd.setSymbolic(isSymbolic);
/* 117 */     fd.setFontBoundingBox(new PDRectangle(type1.getFontBBox()));
/* 118 */     fd.setItalicAngle(type1.getItalicAngle());
/* 119 */     fd.setAscent(type1.getFontBBox().getUpperRightY());
/* 120 */     fd.setDescent(type1.getFontBBox().getLowerLeftY());
/* 121 */     fd.setCapHeight(((Number)type1.getBlueValues().get(2)).floatValue());
/* 122 */     fd.setStemV(0.0F);
/* 123 */     return fd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static PDFontDescriptor buildFontDescriptor(FontMetrics metrics) {
/* 134 */     boolean isSymbolic = metrics.getEncodingScheme().equals("FontSpecific");
/*     */     
/* 136 */     PDFontDescriptor fd = new PDFontDescriptor();
/* 137 */     fd.setFontName(metrics.getFontName());
/* 138 */     fd.setFontFamily(metrics.getFamilyName());
/* 139 */     fd.setNonSymbolic(!isSymbolic);
/* 140 */     fd.setSymbolic(isSymbolic);
/* 141 */     fd.setFontBoundingBox(new PDRectangle(metrics.getFontBBox()));
/* 142 */     fd.setItalicAngle(metrics.getItalicAngle());
/* 143 */     fd.setAscent(metrics.getAscender());
/* 144 */     fd.setDescent(metrics.getDescender());
/* 145 */     fd.setCapHeight(metrics.getCapHeight());
/* 146 */     fd.setXHeight(metrics.getXHeight());
/* 147 */     fd.setAverageWidth(metrics.getAverageCharacterWidth());
/* 148 */     fd.setCharacterSet(metrics.getCharacterSet());
/* 149 */     fd.setStemV(0.0F);
/* 150 */     return fd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encoding getFontEncoding() {
/* 158 */     return this.fontEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphList getGlyphList() {
/* 166 */     return GlyphList.getAdobeGlyphList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type1Font getType1Font() {
/* 174 */     return this.type1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType1FontEmbedder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */