/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.EncodedFont;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.type1.DamagedFontException;
/*     */ import org.apache.fontbox.type1.Type1Font;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.SymbolEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.ZapfDingbatsEncoding;
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
/*     */ public class PDType1Font
/*     */   extends PDSimpleFont
/*     */ {
/*  60 */   private static final Log LOG = LogFactory.getLog(PDType1Font.class);
/*     */ 
/*     */   
/*  63 */   private static final Map<String, String> ALT_NAMES = new HashMap<String, String>();
/*     */   
/*     */   static {
/*  66 */     ALT_NAMES.put("ff", "f_f");
/*  67 */     ALT_NAMES.put("ffi", "f_f_i");
/*  68 */     ALT_NAMES.put("ffl", "f_f_l");
/*  69 */     ALT_NAMES.put("fi", "f_i");
/*  70 */     ALT_NAMES.put("fl", "f_l");
/*  71 */     ALT_NAMES.put("st", "s_t");
/*  72 */     ALT_NAMES.put("IJ", "I_J");
/*  73 */     ALT_NAMES.put("ij", "i_j");
/*  74 */     ALT_NAMES.put("ellipsis", "elipsis");
/*     */   }
/*     */ 
/*     */   
/*     */   private static final int PFB_START_MARKER = 128;
/*  79 */   public static final PDType1Font TIMES_ROMAN = new PDType1Font("Times-Roman");
/*  80 */   public static final PDType1Font TIMES_BOLD = new PDType1Font("Times-Bold");
/*  81 */   public static final PDType1Font TIMES_ITALIC = new PDType1Font("Times-Italic");
/*  82 */   public static final PDType1Font TIMES_BOLD_ITALIC = new PDType1Font("Times-BoldItalic");
/*  83 */   public static final PDType1Font HELVETICA = new PDType1Font("Helvetica");
/*  84 */   public static final PDType1Font HELVETICA_BOLD = new PDType1Font("Helvetica-Bold");
/*  85 */   public static final PDType1Font HELVETICA_OBLIQUE = new PDType1Font("Helvetica-Oblique");
/*  86 */   public static final PDType1Font HELVETICA_BOLD_OBLIQUE = new PDType1Font("Helvetica-BoldOblique");
/*  87 */   public static final PDType1Font COURIER = new PDType1Font("Courier");
/*  88 */   public static final PDType1Font COURIER_BOLD = new PDType1Font("Courier-Bold");
/*  89 */   public static final PDType1Font COURIER_OBLIQUE = new PDType1Font("Courier-Oblique");
/*  90 */   public static final PDType1Font COURIER_BOLD_OBLIQUE = new PDType1Font("Courier-BoldOblique");
/*  91 */   public static final PDType1Font SYMBOL = new PDType1Font("Symbol");
/*  92 */   public static final PDType1Font ZAPF_DINGBATS = new PDType1Font("ZapfDingbats");
/*     */ 
/*     */ 
/*     */   
/*     */   private final Type1Font type1font;
/*     */ 
/*     */   
/*     */   private final FontBoxFont genericFont;
/*     */ 
/*     */   
/*     */   private final boolean isEmbedded;
/*     */ 
/*     */   
/*     */   private final boolean isDamaged;
/*     */ 
/*     */   
/*     */   private Matrix fontMatrix;
/*     */ 
/*     */   
/*     */   private final AffineTransform fontMatrixTransform;
/*     */ 
/*     */   
/*     */   private BoundingBox fontBBox;
/*     */ 
/*     */   
/*     */   private final Map<Integer, byte[]> codeToBytesMap;
/*     */ 
/*     */ 
/*     */   
/*     */   private PDType1Font(String baseFont) {
/* 122 */     super(baseFont);
/*     */     
/* 124 */     this.dict.setItem(COSName.SUBTYPE, (COSBase)COSName.TYPE1);
/* 125 */     this.dict.setName(COSName.BASE_FONT, baseFont);
/* 126 */     if ("ZapfDingbats".equals(baseFont)) {
/*     */       
/* 128 */       this.encoding = (Encoding)ZapfDingbatsEncoding.INSTANCE;
/*     */     }
/* 130 */     else if ("Symbol".equals(baseFont)) {
/*     */       
/* 132 */       this.encoding = (Encoding)SymbolEncoding.INSTANCE;
/*     */     }
/*     */     else {
/*     */       
/* 136 */       this.encoding = (Encoding)WinAnsiEncoding.INSTANCE;
/* 137 */       this.dict.setItem(COSName.ENCODING, (COSBase)COSName.WIN_ANSI_ENCODING);
/*     */     } 
/*     */ 
/*     */     
/* 141 */     this.codeToBytesMap = (Map)new ConcurrentHashMap<Integer, byte>();
/*     */ 
/*     */     
/* 144 */     this.type1font = null;
/*     */     
/* 146 */     FontMapping<FontBoxFont> mapping = FontMappers.instance().getFontBoxFont(getBaseFont(), 
/* 147 */         getFontDescriptor());
/* 148 */     this.genericFont = mapping.getFont();
/*     */     
/* 150 */     if (mapping.isFallback()) {
/*     */       String fontName;
/*     */ 
/*     */       
/*     */       try {
/* 155 */         fontName = this.genericFont.getName();
/*     */       }
/* 157 */       catch (IOException e) {
/*     */         
/* 159 */         fontName = "?";
/*     */       } 
/* 161 */       LOG.warn("Using fallback font " + fontName + " for base font " + getBaseFont());
/*     */     } 
/* 163 */     this.isEmbedded = false;
/* 164 */     this.isDamaged = false;
/* 165 */     this.fontMatrixTransform = new AffineTransform();
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
/*     */   public PDType1Font(PDDocument doc, InputStream pfbIn) throws IOException {
/* 177 */     PDType1FontEmbedder embedder = new PDType1FontEmbedder(doc, this.dict, pfbIn, null);
/* 178 */     this.encoding = embedder.getFontEncoding();
/* 179 */     this.glyphList = embedder.getGlyphList();
/* 180 */     this.type1font = embedder.getType1Font();
/* 181 */     this.genericFont = (FontBoxFont)embedder.getType1Font();
/* 182 */     this.isEmbedded = true;
/* 183 */     this.isDamaged = false;
/* 184 */     this.fontMatrixTransform = new AffineTransform();
/* 185 */     this.codeToBytesMap = (Map)new HashMap<Integer, byte>();
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
/*     */   public PDType1Font(PDDocument doc, InputStream pfbIn, Encoding encoding) throws IOException {
/* 198 */     PDType1FontEmbedder embedder = new PDType1FontEmbedder(doc, this.dict, pfbIn, encoding);
/* 199 */     this.encoding = encoding;
/* 200 */     this.glyphList = embedder.getGlyphList();
/* 201 */     this.type1font = embedder.getType1Font();
/* 202 */     this.genericFont = (FontBoxFont)embedder.getType1Font();
/* 203 */     this.isEmbedded = true;
/* 204 */     this.isDamaged = false;
/* 205 */     this.fontMatrixTransform = new AffineTransform();
/* 206 */     this.codeToBytesMap = (Map)new HashMap<Integer, byte>();
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
/*     */   public PDType1Font(COSDictionary fontDictionary) throws IOException {
/* 218 */     super(fontDictionary);
/* 219 */     this.codeToBytesMap = (Map)new HashMap<Integer, byte>();
/*     */     
/* 221 */     PDFontDescriptor fd = getFontDescriptor();
/* 222 */     Type1Font t1 = null;
/* 223 */     boolean fontIsDamaged = false;
/* 224 */     if (fd != null) {
/*     */ 
/*     */       
/* 227 */       PDStream fontFile3 = fd.getFontFile3();
/* 228 */       if (fontFile3 != null)
/*     */       {
/* 230 */         throw new IllegalArgumentException("Use PDType1CFont for FontFile3");
/*     */       }
/*     */ 
/*     */       
/* 234 */       PDStream fontFile = fd.getFontFile();
/* 235 */       if (fontFile != null) {
/*     */         
/*     */         try {
/*     */           
/* 239 */           COSStream stream = fontFile.getCOSObject();
/* 240 */           int length1 = stream.getInt(COSName.LENGTH1);
/* 241 */           int length2 = stream.getInt(COSName.LENGTH2);
/*     */ 
/*     */           
/* 244 */           byte[] bytes = fontFile.toByteArray();
/* 245 */           length1 = repairLength1(bytes, length1);
/* 246 */           length2 = repairLength2(bytes, length1, length2);
/*     */           
/* 248 */           if (bytes.length > 0 && (bytes[0] & 0xFF) == 128)
/*     */           {
/*     */             
/* 251 */             t1 = Type1Font.createWithPFB(bytes);
/*     */           
/*     */           }
/*     */           else
/*     */           {
/* 256 */             byte[] segment1 = Arrays.copyOfRange(bytes, 0, length1);
/* 257 */             byte[] segment2 = Arrays.copyOfRange(bytes, length1, length1 + length2);
/*     */ 
/*     */             
/* 260 */             if (length1 > 0 && length2 > 0)
/*     */             {
/* 262 */               t1 = Type1Font.createWithSegments(segment1, segment2);
/*     */             }
/*     */           }
/*     */         
/* 266 */         } catch (DamagedFontException e) {
/*     */           
/* 268 */           LOG.warn("Can't read damaged embedded Type1 font " + fd.getFontName());
/* 269 */           fontIsDamaged = true;
/*     */         }
/* 271 */         catch (IOException e) {
/*     */           
/* 273 */           LOG.error("Can't read the embedded Type1 font " + fd.getFontName(), e);
/* 274 */           fontIsDamaged = true;
/*     */         } 
/*     */       }
/*     */     } 
/* 278 */     this.isEmbedded = (t1 != null);
/* 279 */     this.isDamaged = fontIsDamaged;
/* 280 */     this.type1font = t1;
/*     */ 
/*     */     
/* 283 */     if (this.type1font != null) {
/*     */       
/* 285 */       this.genericFont = (FontBoxFont)this.type1font;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 290 */       FontMapping<FontBoxFont> mapping = FontMappers.instance().getFontBoxFont(getBaseFont(), fd);
/* 291 */       this.genericFont = mapping.getFont();
/*     */       
/* 293 */       if (mapping.isFallback())
/*     */       {
/* 295 */         LOG.warn("Using fallback font " + this.genericFont.getName() + " for " + getBaseFont());
/*     */       }
/*     */     } 
/* 298 */     readEncoding();
/* 299 */     this.fontMatrixTransform = getFontMatrix().createAffineTransform();
/* 300 */     this.fontMatrixTransform.scale(1000.0D, 1000.0D);
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
/*     */   private int repairLength1(byte[] bytes, int length1) {
/* 314 */     int offset = Math.max(0, length1 - 4);
/* 315 */     if (offset <= 0 || offset > bytes.length - 4)
/*     */     {
/* 317 */       offset = bytes.length - 4;
/*     */     }
/*     */     
/* 320 */     offset = findBinaryOffsetAfterExec(bytes, offset);
/* 321 */     if (offset == 0 && length1 > 0)
/*     */     {
/*     */       
/* 324 */       offset = findBinaryOffsetAfterExec(bytes, bytes.length - 4);
/*     */     }
/*     */     
/* 327 */     if (length1 - offset != 0 && offset > 0) {
/*     */       
/* 329 */       if (LOG.isWarnEnabled())
/*     */       {
/* 331 */         LOG.warn("Ignored invalid Length1 " + length1 + " for Type 1 font " + getName());
/*     */       }
/* 333 */       return offset;
/*     */     } 
/*     */     
/* 336 */     return length1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int findBinaryOffsetAfterExec(byte[] bytes, int startOffset) {
/* 341 */     int offset = startOffset;
/* 342 */     while (offset > 0) {
/*     */       
/* 344 */       if (bytes[offset + 0] == 101 && bytes[offset + 1] == 120 && bytes[offset + 2] == 101 && bytes[offset + 3] == 99) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 349 */         offset += 4;
/*     */         
/* 351 */         while (offset < bytes.length && (bytes[offset] == 13 || bytes[offset] == 10 || bytes[offset] == 32 || bytes[offset] == 9))
/*     */         {
/*     */ 
/*     */           
/* 355 */           offset++;
/*     */         }
/*     */         break;
/*     */       } 
/* 359 */       offset--;
/*     */     } 
/* 361 */     return offset;
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
/*     */   private int repairLength2(byte[] bytes, int length1, int length2) {
/* 377 */     if (length2 < 0 || length2 > bytes.length - length1) {
/*     */       
/* 379 */       LOG.warn("Ignored invalid Length2 " + length2 + " for Type 1 font " + getName());
/* 380 */       return bytes.length - length1;
/*     */     } 
/* 382 */     return length2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getBaseFont() {
/* 390 */     return this.dict.getNameAsString(COSName.BASE_FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 396 */     String name = codeToName(code);
/* 397 */     if (getStandard14AFM() != null) {
/*     */       
/* 399 */       String afmName = getEncoding().getName(code);
/* 400 */       return getStandard14AFM().getCharacterHeight(afmName);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 405 */     return (float)this.genericFont.getPath(name).getBounds().getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] encode(int unicode) throws IOException {
/* 412 */     byte[] bytes = this.codeToBytesMap.get(Integer.valueOf(unicode));
/* 413 */     if (bytes != null)
/*     */     {
/* 415 */       return bytes;
/*     */     }
/*     */     
/* 418 */     String name = getGlyphList().codePointToName(unicode);
/* 419 */     if (isStandard14()) {
/*     */ 
/*     */ 
/*     */       
/* 423 */       if (!this.encoding.contains(name))
/*     */       {
/* 425 */         throw new IllegalArgumentException(
/* 426 */             String.format("U+%04X ('%s') is not available in this font %s encoding: %s", new Object[] {
/* 427 */                 Integer.valueOf(unicode), name, getName(), this.encoding.getEncodingName()
/*     */               })); } 
/* 429 */       if (".notdef".equals(name))
/*     */       {
/* 431 */         throw new IllegalArgumentException(
/* 432 */             String.format("No glyph for U+%04X in font %s", new Object[] { Integer.valueOf(unicode), getName() }));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 437 */       if (!this.encoding.contains(name))
/*     */       {
/* 439 */         throw new IllegalArgumentException(
/* 440 */             String.format("U+%04X ('%s') is not available in this font %s (generic: %s) encoding: %s", new Object[] {
/* 441 */                 Integer.valueOf(unicode), name, getName(), this.genericFont.getName(), this.encoding.getEncodingName()
/*     */               }));
/*     */       }
/* 444 */       String nameInFont = getNameInFont(name);
/*     */       
/* 446 */       if (nameInFont.equals(".notdef") || !this.genericFont.hasGlyph(nameInFont))
/*     */       {
/* 448 */         throw new IllegalArgumentException(
/* 449 */             String.format("No glyph for U+%04X in font %s (generic: %s)", new Object[] { Integer.valueOf(unicode), getName(), this.genericFont.getName() }));
/*     */       }
/*     */     } 
/*     */     
/* 453 */     Map<String, Integer> inverted = this.encoding.getNameToCodeMap();
/* 454 */     int code = ((Integer)inverted.get(name)).intValue();
/* 455 */     bytes = new byte[] { (byte)code };
/* 456 */     this.codeToBytesMap.put(Integer.valueOf(unicode), bytes);
/* 457 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 463 */     String name = codeToName(code);
/*     */ 
/*     */     
/* 466 */     if (!this.isEmbedded && ".notdef".equals(name))
/*     */     {
/* 468 */       return 250.0F;
/*     */     }
/* 470 */     float width = this.genericFont.getWidth(name);
/*     */     
/* 472 */     Point2D p = new Point2D.Float(width, 0.0F);
/* 473 */     this.fontMatrixTransform.transform(p, p);
/* 474 */     return (float)p.getX();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 480 */     return this.isEmbedded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageFontWidth() {
/* 486 */     if (getStandard14AFM() != null)
/*     */     {
/* 488 */       return getStandard14AFM().getAverageCharacterWidth();
/*     */     }
/*     */ 
/*     */     
/* 492 */     return super.getAverageFontWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCode(InputStream in) throws IOException {
/* 499 */     return in.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Encoding readEncodingFromFont() throws IOException {
/* 505 */     if (!isEmbedded() && getStandard14AFM() != null)
/*     */     {
/*     */       
/* 508 */       return (Encoding)new Type1Encoding(getStandard14AFM());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 513 */     if (this.genericFont instanceof EncodedFont)
/*     */     {
/* 515 */       return (Encoding)Type1Encoding.fromFontBox(((EncodedFont)this.genericFont).getEncoding());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 520 */     return (Encoding)StandardEncoding.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type1Font getType1Font() {
/* 530 */     return this.type1font;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontBoxFont getFontBoxFont() {
/* 536 */     return this.genericFont;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 542 */     return getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() throws IOException {
/* 548 */     if (this.fontBBox == null)
/*     */     {
/* 550 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 552 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() throws IOException {
/* 557 */     if (getFontDescriptor() != null) {
/* 558 */       PDRectangle bbox = getFontDescriptor().getFontBoundingBox();
/* 559 */       if (bbox != null && (bbox
/* 560 */         .getLowerLeftX() != 0.0F || bbox.getLowerLeftY() != 0.0F || bbox
/* 561 */         .getUpperRightX() != 0.0F || bbox.getUpperRightY() != 0.0F))
/*     */       {
/* 563 */         return new BoundingBox(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox
/* 564 */             .getUpperRightX(), bbox.getUpperRightY());
/*     */       }
/*     */     } 
/* 567 */     return this.genericFont.getFontBBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String codeToName(int code) throws IOException {
/* 573 */     String name = getEncoding().getName(code);
/* 574 */     return getNameInFont(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getNameInFont(String name) throws IOException {
/* 583 */     if (isEmbedded() || this.genericFont.hasGlyph(name))
/*     */     {
/* 585 */       return name;
/*     */     }
/*     */ 
/*     */     
/* 589 */     String altName = ALT_NAMES.get(name);
/* 590 */     if (altName != null && !name.equals(".notdef") && this.genericFont.hasGlyph(altName))
/*     */     {
/* 592 */       return altName;
/*     */     }
/*     */ 
/*     */     
/* 596 */     String unicodes = getGlyphList().toUnicode(name);
/* 597 */     if (unicodes != null && unicodes.length() == 1) {
/*     */       
/* 599 */       String uniName = UniUtil.getUniNameOfCodePoint(unicodes.codePointAt(0));
/* 600 */       if (this.genericFont.hasGlyph(uniName))
/*     */       {
/* 602 */         return uniName;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 609 */       if ("SymbolMT".equals(this.genericFont.getName())) {
/*     */         
/* 611 */         Integer code = (Integer)SymbolEncoding.INSTANCE.getNameToCodeMap().get(name);
/* 612 */         if (code != null) {
/*     */           
/* 614 */           uniName = UniUtil.getUniNameOfCodePoint(code.intValue() + 61440);
/* 615 */           if (this.genericFont.hasGlyph(uniName))
/*     */           {
/* 617 */             return uniName;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 623 */     return ".notdef";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 631 */     if (name.equals(".notdef") && !this.isEmbedded)
/*     */     {
/* 633 */       return new GeneralPath();
/*     */     }
/*     */ 
/*     */     
/* 637 */     return this.genericFont.getPath(getNameInFont(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) throws IOException {
/* 644 */     return this.genericFont.hasGlyph(getNameInFont(name));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Matrix getFontMatrix() {
/* 650 */     if (this.fontMatrix == null) {
/*     */ 
/*     */ 
/*     */       
/* 654 */       List<Number> numbers = null;
/*     */       
/*     */       try {
/* 657 */         numbers = this.genericFont.getFontMatrix();
/*     */       }
/* 659 */       catch (IOException e) {
/*     */         
/* 661 */         this.fontMatrix = DEFAULT_FONT_MATRIX;
/*     */       } 
/*     */       
/* 664 */       if (numbers != null && numbers.size() == 6) {
/*     */         
/* 666 */         this
/*     */ 
/*     */           
/* 669 */           .fontMatrix = new Matrix(((Number)numbers.get(0)).floatValue(), ((Number)numbers.get(1)).floatValue(), ((Number)numbers.get(2)).floatValue(), ((Number)numbers.get(3)).floatValue(), ((Number)numbers.get(4)).floatValue(), ((Number)numbers.get(5)).floatValue());
/*     */       }
/*     */       else {
/*     */         
/* 673 */         return super.getFontMatrix();
/*     */       } 
/*     */     } 
/* 676 */     return this.fontMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 682 */     return this.isDamaged;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType1Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */