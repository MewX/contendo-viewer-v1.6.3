/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.EncodedFont;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.cff.CFFParser;
/*     */ import org.apache.fontbox.cff.CFFType1Font;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDType1CFont
/*     */   extends PDSimpleFont
/*     */ {
/*  56 */   private static final Log LOG = LogFactory.getLog(PDType1CFont.class);
/*     */   
/*  58 */   private final Map<String, Float> glyphHeights = new HashMap<String, Float>();
/*  59 */   private Float avgWidth = null;
/*     */   
/*     */   private Matrix fontMatrix;
/*     */   
/*     */   private final AffineTransform fontMatrixTransform;
/*     */   
/*     */   private final CFFType1Font cffFont;
/*     */   
/*     */   private final FontBoxFont genericFont;
/*     */   
/*     */   private final boolean isEmbedded;
/*     */   
/*     */   private final boolean isDamaged;
/*     */   
/*     */   private BoundingBox fontBBox;
/*     */ 
/*     */   
/*     */   public PDType1CFont(COSDictionary fontDictionary) throws IOException {
/*  77 */     super(fontDictionary);
/*     */     
/*  79 */     PDFontDescriptor fd = getFontDescriptor();
/*  80 */     byte[] bytes = null;
/*  81 */     if (fd != null) {
/*     */       
/*  83 */       PDStream ff3Stream = fd.getFontFile3();
/*  84 */       if (ff3Stream != null) {
/*     */         
/*  86 */         bytes = IOUtils.toByteArray((InputStream)ff3Stream.createInputStream());
/*  87 */         if (bytes.length == 0) {
/*     */           
/*  89 */           LOG.error("Invalid data for embedded Type1C font " + getName());
/*  90 */           bytes = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     boolean fontIsDamaged = false;
/*  96 */     CFFType1Font cffEmbedded = null;
/*     */     
/*     */     try {
/*  99 */       if (bytes != null)
/*     */       {
/*     */         
/* 102 */         CFFParser cffParser = new CFFParser();
/* 103 */         cffEmbedded = cffParser.parse(bytes, new ByteSource()).get(0);
/*     */       }
/*     */     
/* 106 */     } catch (IOException e) {
/*     */       
/* 108 */       LOG.error("Can't read the embedded Type1C font " + getName(), e);
/* 109 */       fontIsDamaged = true;
/*     */     } 
/* 111 */     this.isDamaged = fontIsDamaged;
/* 112 */     this.cffFont = cffEmbedded;
/*     */     
/* 114 */     if (this.cffFont != null) {
/*     */       
/* 116 */       this.genericFont = (FontBoxFont)this.cffFont;
/* 117 */       this.isEmbedded = true;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 122 */       FontMapping<FontBoxFont> mapping = FontMappers.instance().getFontBoxFont(getBaseFont(), fd);
/* 123 */       this.genericFont = mapping.getFont();
/*     */       
/* 125 */       if (mapping.isFallback())
/*     */       {
/* 127 */         LOG.warn("Using fallback font " + this.genericFont.getName() + " for " + getBaseFont());
/*     */       }
/* 129 */       this.isEmbedded = false;
/*     */     } 
/* 131 */     readEncoding();
/* 132 */     this.fontMatrixTransform = getFontMatrix().createAffineTransform();
/* 133 */     this.fontMatrixTransform.scale(1000.0D, 1000.0D);
/*     */   }
/*     */   
/*     */   private class ByteSource
/*     */     implements CFFParser.ByteSource {
/*     */     private ByteSource() {}
/*     */     
/*     */     public byte[] getBytes() throws IOException {
/* 141 */       PDStream ff3Stream = PDType1CFont.this.getFontDescriptor().getFontFile3();
/* 142 */       return IOUtils.toByteArray((InputStream)ff3Stream.createInputStream());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontBoxFont getFontBoxFont() {
/* 149 */     return this.genericFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getBaseFont() {
/* 157 */     return this.dict.getNameAsString(COSName.BASE_FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 164 */     if (name.equals(".notdef") && !isEmbedded() && !isStandard14())
/*     */     {
/* 166 */       return new GeneralPath();
/*     */     }
/*     */ 
/*     */     
/* 170 */     return this.genericFont.getPath(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) throws IOException {
/* 177 */     return this.genericFont.hasGlyph(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/* 183 */     return getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() throws IOException {
/* 189 */     if (this.fontBBox == null)
/*     */     {
/* 191 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 193 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() throws IOException {
/* 198 */     if (getFontDescriptor() != null) {
/* 199 */       PDRectangle bbox = getFontDescriptor().getFontBoundingBox();
/* 200 */       if (bbox != null && (bbox
/* 201 */         .getLowerLeftX() != 0.0F || bbox.getLowerLeftY() != 0.0F || bbox
/* 202 */         .getUpperRightX() != 0.0F || bbox.getUpperRightY() != 0.0F))
/*     */       {
/* 204 */         return new BoundingBox(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox
/* 205 */             .getUpperRightX(), bbox.getUpperRightY());
/*     */       }
/*     */     } 
/* 208 */     return this.genericFont.getFontBBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String codeToName(int code) {
/* 214 */     return getEncoding().getName(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Encoding readEncodingFromFont() throws IOException {
/* 220 */     if (!isEmbedded() && getStandard14AFM() != null)
/*     */     {
/*     */       
/* 223 */       return (Encoding)new Type1Encoding(getStandard14AFM());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 228 */     if (this.genericFont instanceof EncodedFont)
/*     */     {
/* 230 */       return (Encoding)Type1Encoding.fromFontBox(((EncodedFont)this.genericFont).getEncoding());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 235 */     return (Encoding)StandardEncoding.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCode(InputStream in) throws IOException {
/* 243 */     return in.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Matrix getFontMatrix() {
/* 249 */     if (this.fontMatrix == null) {
/*     */       
/* 251 */       List<Number> numbers = null;
/*     */       
/*     */       try {
/* 254 */         numbers = this.genericFont.getFontMatrix();
/*     */       }
/* 256 */       catch (IOException e) {
/*     */         
/* 258 */         this.fontMatrix = DEFAULT_FONT_MATRIX;
/*     */       } 
/*     */       
/* 261 */       if (numbers != null && numbers.size() == 6) {
/*     */         
/* 263 */         this
/*     */ 
/*     */           
/* 266 */           .fontMatrix = new Matrix(((Number)numbers.get(0)).floatValue(), ((Number)numbers.get(1)).floatValue(), ((Number)numbers.get(2)).floatValue(), ((Number)numbers.get(3)).floatValue(), ((Number)numbers.get(4)).floatValue(), ((Number)numbers.get(5)).floatValue());
/*     */       }
/*     */       else {
/*     */         
/* 270 */         return super.getFontMatrix();
/*     */       } 
/*     */     } 
/* 273 */     return this.fontMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 279 */     return this.isDamaged;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 285 */     String name = codeToName(code);
/* 286 */     name = getNameInFont(name);
/* 287 */     float width = this.genericFont.getWidth(name);
/*     */     
/* 289 */     Point2D p = new Point2D.Float(width, 0.0F);
/* 290 */     this.fontMatrixTransform.transform(p, p);
/* 291 */     return (float)p.getX();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 297 */     return this.isEmbedded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 303 */     String name = codeToName(code);
/* 304 */     float height = 0.0F;
/* 305 */     if (!this.glyphHeights.containsKey(name)) {
/*     */       
/* 307 */       height = (float)this.cffFont.getType1CharString(name).getBounds().getHeight();
/* 308 */       this.glyphHeights.put(name, Float.valueOf(height));
/*     */     } 
/* 310 */     return height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] encode(int unicode) throws IOException {
/* 316 */     String name = getGlyphList().codePointToName(unicode);
/* 317 */     if (!this.encoding.contains(name))
/*     */     {
/* 319 */       throw new IllegalArgumentException(
/* 320 */           String.format("U+%04X ('%s') is not available in this font's encoding: %s", new Object[] {
/* 321 */               Integer.valueOf(unicode), name, this.encoding.getEncodingName()
/*     */             }));
/*     */     }
/* 324 */     String nameInFont = getNameInFont(name);
/*     */     
/* 326 */     Map<String, Integer> inverted = this.encoding.getNameToCodeMap();
/*     */     
/* 328 */     if (nameInFont.equals(".notdef") || !this.genericFont.hasGlyph(nameInFont))
/*     */     {
/* 330 */       throw new IllegalArgumentException(
/* 331 */           String.format("No glyph for U+%04X in font %s", new Object[] { Integer.valueOf(unicode), getName() }));
/*     */     }
/*     */     
/* 334 */     int code = ((Integer)inverted.get(name)).intValue();
/* 335 */     return new byte[] { (byte)code };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStringWidth(String string) throws IOException {
/* 341 */     float width = 0.0F;
/* 342 */     for (int i = 0; i < string.length(); i++) {
/*     */       
/* 344 */       int codePoint = string.codePointAt(i);
/* 345 */       String name = getGlyphList().codePointToName(codePoint);
/* 346 */       width += this.cffFont.getType1CharString(name).getWidth();
/*     */     } 
/* 348 */     return width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageFontWidth() {
/* 354 */     if (this.avgWidth == null)
/*     */     {
/* 356 */       this.avgWidth = Float.valueOf(getAverageCharacterWidth());
/*     */     }
/* 358 */     return this.avgWidth.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFFType1Font getCFFType1Font() {
/* 368 */     return this.cffFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getAverageCharacterWidth() {
/* 375 */     return 500.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getNameInFont(String name) throws IOException {
/* 384 */     if (isEmbedded() || this.genericFont.hasGlyph(name))
/*     */     {
/* 386 */       return name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 391 */     String unicodes = getGlyphList().toUnicode(name);
/* 392 */     if (unicodes != null && unicodes.length() == 1) {
/*     */       
/* 394 */       String uniName = UniUtil.getUniNameOfCodePoint(unicodes.codePointAt(0));
/* 395 */       if (this.genericFont.hasGlyph(uniName))
/*     */       {
/* 397 */         return uniName;
/*     */       }
/*     */     } 
/*     */     
/* 401 */     return ".notdef";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType1CFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */