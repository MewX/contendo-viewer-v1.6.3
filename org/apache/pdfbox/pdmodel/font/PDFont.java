/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.afm.FontMetrics;
/*     */ import org.apache.fontbox.cmap.CMap;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ import org.apache.pdfbox.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDFont
/*     */   implements COSObjectable, PDFontLike
/*     */ {
/*  53 */   private static final Log LOG = LogFactory.getLog(PDFont.class);
/*  54 */   protected static final Matrix DEFAULT_FONT_MATRIX = new Matrix(0.001F, 0.0F, 0.0F, 0.001F, 0.0F, 0.0F);
/*     */   
/*     */   protected final COSDictionary dict;
/*     */   
/*     */   private final CMap toUnicodeCMap;
/*     */   
/*     */   private final FontMetrics afmStandard14;
/*     */   
/*     */   private PDFontDescriptor fontDescriptor;
/*     */   
/*     */   private List<Float> widths;
/*     */   
/*     */   private float avgFontWidth;
/*  67 */   private float fontWidthOfSpace = -1.0F;
/*     */ 
/*     */   
/*     */   private final Map<Integer, Float> codeToWidthMap;
/*     */ 
/*     */ 
/*     */   
/*     */   PDFont() {
/*  75 */     this.dict = new COSDictionary();
/*  76 */     this.dict.setItem(COSName.TYPE, (COSBase)COSName.FONT);
/*  77 */     this.toUnicodeCMap = null;
/*  78 */     this.fontDescriptor = null;
/*  79 */     this.afmStandard14 = null;
/*  80 */     this.codeToWidthMap = new HashMap<Integer, Float>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDFont(String baseFont) {
/*  88 */     this.dict = new COSDictionary();
/*  89 */     this.dict.setItem(COSName.TYPE, (COSBase)COSName.FONT);
/*  90 */     this.toUnicodeCMap = null;
/*  91 */     this.afmStandard14 = Standard14Fonts.getAFM(baseFont);
/*  92 */     if (this.afmStandard14 == null)
/*     */     {
/*  94 */       throw new IllegalArgumentException("No AFM for font " + baseFont);
/*     */     }
/*  96 */     this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(this.afmStandard14);
/*     */     
/*  98 */     this.codeToWidthMap = new ConcurrentHashMap<Integer, Float>();
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
/*     */   protected PDFont(COSDictionary fontDictionary) throws IOException {
/* 110 */     this.dict = fontDictionary;
/* 111 */     this.codeToWidthMap = new HashMap<Integer, Float>();
/*     */ 
/*     */     
/* 114 */     this.afmStandard14 = Standard14Fonts.getAFM(getName());
/* 115 */     this.fontDescriptor = loadFontDescriptor();
/* 116 */     this.toUnicodeCMap = loadUnicodeCmap();
/*     */   }
/*     */ 
/*     */   
/*     */   private PDFontDescriptor loadFontDescriptor() {
/* 121 */     COSDictionary fd = (COSDictionary)this.dict.getDictionaryObject(COSName.FONT_DESC);
/* 122 */     if (fd != null)
/*     */     {
/* 124 */       return new PDFontDescriptor(fd);
/*     */     }
/* 126 */     if (this.afmStandard14 != null)
/*     */     {
/*     */       
/* 129 */       return PDType1FontEmbedder.buildFontDescriptor(this.afmStandard14);
/*     */     }
/*     */ 
/*     */     
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CMap loadUnicodeCmap() {
/* 139 */     COSBase toUnicode = this.dict.getDictionaryObject(COSName.TO_UNICODE);
/* 140 */     if (toUnicode == null)
/*     */     {
/* 142 */       return null;
/*     */     }
/*     */     
/* 145 */     CMap cmap = null;
/*     */     
/*     */     try {
/* 148 */       cmap = readCMap(toUnicode);
/* 149 */       if (cmap != null && !cmap.hasUnicodeMappings())
/*     */       {
/* 151 */         LOG.warn("Invalid ToUnicode CMap in font " + getName());
/* 152 */         String cmapName = (cmap.getName() != null) ? cmap.getName() : "";
/* 153 */         String ordering = (cmap.getOrdering() != null) ? cmap.getOrdering() : "";
/* 154 */         COSBase encoding = this.dict.getDictionaryObject(COSName.ENCODING);
/* 155 */         if (cmapName.contains("Identity") || ordering
/* 156 */           .contains("Identity") || COSName.IDENTITY_H
/* 157 */           .equals(encoding) || COSName.IDENTITY_V
/* 158 */           .equals(encoding))
/*     */         {
/*     */           
/* 161 */           cmap = CMapManager.getPredefinedCMap(COSName.IDENTITY_H.getName());
/*     */         }
/*     */       }
/*     */     
/* 165 */     } catch (IOException ex) {
/*     */       
/* 167 */       LOG.error("Could not read ToUnicode CMap in font " + getName(), ex);
/*     */     } 
/* 169 */     return cmap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final FontMetrics getStandard14AFM() {
/* 177 */     return this.afmStandard14;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontDescriptor getFontDescriptor() {
/* 183 */     return this.fontDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setFontDescriptor(PDFontDescriptor fontDescriptor) {
/* 191 */     this.fontDescriptor = fontDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final CMap readCMap(COSBase base) throws IOException {
/* 201 */     if (base instanceof COSName) {
/*     */ 
/*     */       
/* 204 */       String name = ((COSName)base).getName();
/* 205 */       return CMapManager.getPredefinedCMap(name);
/*     */     } 
/* 207 */     if (base instanceof COSStream) {
/*     */       COSInputStream cOSInputStream;
/*     */       
/* 210 */       InputStream input = null;
/*     */       
/*     */       try {
/* 213 */         cOSInputStream = ((COSStream)base).createInputStream();
/* 214 */         return CMapManager.parseCMap((InputStream)cOSInputStream);
/*     */       }
/*     */       finally {
/*     */         
/* 218 */         IOUtils.closeQuietly((Closeable)cOSInputStream);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 223 */     throw new IOException("Expected Name or Stream");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 230 */     return this.dict;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getPositionVector(int code) {
/* 236 */     throw new UnsupportedOperationException("Horizontal fonts have no position vector");
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
/*     */   public Vector getDisplacement(int code) throws IOException {
/* 248 */     return new Vector(getWidth(code) / 1000.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(int code) throws IOException {
/* 254 */     Float width = this.codeToWidthMap.get(Integer.valueOf(code));
/* 255 */     if (width != null)
/*     */     {
/* 257 */       return width.floatValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     if (this.dict.getDictionaryObject(COSName.WIDTHS) != null || this.dict.containsKey(COSName.MISSING_WIDTH)) {
/*     */       
/* 269 */       int firstChar = this.dict.getInt(COSName.FIRST_CHAR, -1);
/* 270 */       int lastChar = this.dict.getInt(COSName.LAST_CHAR, -1);
/* 271 */       int siz = getWidths().size();
/* 272 */       int idx = code - firstChar;
/* 273 */       if (siz > 0 && code >= firstChar && code <= lastChar && idx < siz) {
/*     */         
/* 275 */         width = getWidths().get(idx);
/* 276 */         if (width == null)
/*     */         {
/* 278 */           width = Float.valueOf(0.0F);
/*     */         }
/* 280 */         this.codeToWidthMap.put(Integer.valueOf(code), width);
/* 281 */         return width.floatValue();
/*     */       } 
/*     */       
/* 284 */       PDFontDescriptor fd = getFontDescriptor();
/* 285 */       if (fd != null) {
/*     */ 
/*     */         
/* 288 */         width = Float.valueOf(fd.getMissingWidth());
/* 289 */         this.codeToWidthMap.put(Integer.valueOf(code), width);
/* 290 */         return width.floatValue();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 295 */     if (isStandard14()) {
/*     */       
/* 297 */       width = Float.valueOf(getStandard14Width(code));
/* 298 */       this.codeToWidthMap.put(Integer.valueOf(code), width);
/* 299 */       return width.floatValue();
/*     */     } 
/*     */ 
/*     */     
/* 303 */     width = Float.valueOf(getWidthFromFont(code));
/* 304 */     this.codeToWidthMap.put(Integer.valueOf(code), width);
/* 305 */     return width.floatValue();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte[] encode(String text) throws IOException {
/* 335 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 336 */     int offset = 0;
/* 337 */     while (offset < text.length()) {
/*     */       
/* 339 */       int codePoint = text.codePointAt(offset);
/*     */ 
/*     */       
/* 342 */       byte[] bytes = encode(codePoint);
/* 343 */       out.write(bytes);
/*     */       
/* 345 */       offset += Character.charCount(codePoint);
/*     */     } 
/* 347 */     return out.toByteArray();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStringWidth(String text) throws IOException {
/* 373 */     byte[] bytes = encode(text);
/* 374 */     ByteArrayInputStream in = new ByteArrayInputStream(bytes);
/*     */     
/* 376 */     float width = 0.0F;
/* 377 */     while (in.available() > 0) {
/*     */       
/* 379 */       int code = readCode(in);
/* 380 */       width += getWidth(code);
/*     */     } 
/*     */     
/* 383 */     return width;
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
/*     */   public float getAverageFontWidth() {
/*     */     float average;
/* 396 */     if (this.avgFontWidth != 0.0F) {
/*     */       
/* 398 */       average = this.avgFontWidth;
/*     */     }
/*     */     else {
/*     */       
/* 402 */       float totalWidth = 0.0F;
/* 403 */       float characterCount = 0.0F;
/* 404 */       COSArray widths = (COSArray)this.dict.getDictionaryObject(COSName.WIDTHS);
/* 405 */       if (widths != null)
/*     */       {
/* 407 */         for (int i = 0; i < widths.size(); i++) {
/*     */           
/* 409 */           COSNumber fontWidth = (COSNumber)widths.getObject(i);
/* 410 */           if (fontWidth.floatValue() > 0.0F) {
/*     */             
/* 412 */             totalWidth += fontWidth.floatValue();
/* 413 */             characterCount++;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 418 */       if (totalWidth > 0.0F) {
/*     */         
/* 420 */         average = totalWidth / characterCount;
/*     */       }
/*     */       else {
/*     */         
/* 424 */         average = 0.0F;
/*     */       } 
/* 426 */       this.avgFontWidth = average;
/*     */     } 
/* 428 */     return average;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(int code, GlyphList customGlyphList) throws IOException {
/* 449 */     return toUnicode(code);
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
/*     */   public String toUnicode(int code) throws IOException {
/* 461 */     if (this.toUnicodeCMap != null) {
/*     */       
/* 463 */       if (this.toUnicodeCMap.getName() != null && this.toUnicodeCMap
/* 464 */         .getName().startsWith("Identity-") && (this.dict
/* 465 */         .getDictionaryObject(COSName.TO_UNICODE) instanceof COSName || 
/* 466 */         !this.toUnicodeCMap.hasUnicodeMappings()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 473 */         return new String(new char[] { (char)code });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 478 */       return this.toUnicodeCMap.toUnicode(code);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 484 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 494 */     return this.dict.getNameAsString(COSName.TYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubType() {
/* 502 */     return this.dict.getNameAsString(COSName.SUBTYPE);
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
/*     */ 
/*     */   
/*     */   protected final List<Float> getWidths() {
/* 518 */     if (this.widths == null) {
/*     */       
/* 520 */       COSArray array = (COSArray)this.dict.getDictionaryObject(COSName.WIDTHS);
/* 521 */       if (array != null) {
/*     */         
/* 523 */         this.widths = COSArrayList.convertFloatCOSArrayToList(array);
/*     */       }
/*     */       else {
/*     */         
/* 527 */         this.widths = Collections.emptyList();
/*     */       } 
/*     */     } 
/* 530 */     return this.widths;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getFontMatrix() {
/* 536 */     return DEFAULT_FONT_MATRIX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSpaceWidth() {
/* 546 */     if (this.fontWidthOfSpace == -1.0F) {
/*     */       
/* 548 */       COSBase toUnicode = this.dict.getDictionaryObject(COSName.TO_UNICODE);
/*     */       
/*     */       try {
/* 551 */         if (toUnicode != null && this.toUnicodeCMap != null) {
/*     */           
/* 553 */           int spaceMapping = this.toUnicodeCMap.getSpaceMapping();
/* 554 */           if (spaceMapping > -1)
/*     */           {
/* 556 */             this.fontWidthOfSpace = getWidth(spaceMapping);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 561 */           this.fontWidthOfSpace = getWidth(32);
/*     */         } 
/*     */ 
/*     */         
/* 565 */         if (this.fontWidthOfSpace <= 0.0F)
/*     */         {
/* 567 */           this.fontWidthOfSpace = getWidthFromFont(32);
/*     */         }
/*     */         
/* 570 */         if (this.fontWidthOfSpace <= 0.0F)
/*     */         {
/* 572 */           this.fontWidthOfSpace = getAverageFontWidth();
/*     */         }
/*     */       }
/* 575 */       catch (Exception e) {
/*     */         
/* 577 */         LOG.error("Can't determine the width of the space character, assuming 250", e);
/* 578 */         this.fontWidthOfSpace = 250.0F;
/*     */       } 
/*     */     } 
/* 581 */     return this.fontWidthOfSpace;
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
/*     */ 
/*     */   
/*     */   public boolean isStandard14() {
/* 597 */     if (isEmbedded())
/*     */     {
/* 599 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 603 */     return Standard14Fonts.containsName(getName());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 631 */     return (other instanceof PDFont && ((PDFont)other).getCOSObject() == getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 637 */     return getCOSObject().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 643 */     return getClass().getSimpleName() + " " + getName();
/*     */   }
/*     */   
/*     */   protected abstract float getStandard14Width(int paramInt);
/*     */   
/*     */   public abstract float getWidthFromFont(int paramInt) throws IOException;
/*     */   
/*     */   public abstract boolean isEmbedded();
/*     */   
/*     */   public abstract float getHeight(int paramInt) throws IOException;
/*     */   
/*     */   protected abstract byte[] encode(int paramInt) throws IOException;
/*     */   
/*     */   public abstract int readCode(InputStream paramInputStream) throws IOException;
/*     */   
/*     */   public abstract String getName();
/*     */   
/*     */   public abstract BoundingBox getBoundingBox() throws IOException;
/*     */   
/*     */   public abstract boolean isVertical();
/*     */   
/*     */   public abstract void addToSubset(int paramInt);
/*     */   
/*     */   public abstract void subset() throws IOException;
/*     */   
/*     */   public abstract boolean willBeSubset();
/*     */   
/*     */   public abstract boolean isDamaged();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */