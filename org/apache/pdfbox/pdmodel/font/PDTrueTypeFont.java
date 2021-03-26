/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.ttf.CmapSubtable;
/*     */ import org.apache.fontbox.ttf.CmapTable;
/*     */ import org.apache.fontbox.ttf.GlyphData;
/*     */ import org.apache.fontbox.ttf.PostScriptTable;
/*     */ import org.apache.fontbox.ttf.TTFParser;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.BuiltInEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.MacOSRomanEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDTrueTypeFont
/*     */   extends PDSimpleFont
/*     */   implements PDVectorFont
/*     */ {
/*  58 */   private static final Log LOG = LogFactory.getLog(PDTrueTypeFont.class);
/*     */   
/*     */   private static final int START_RANGE_F000 = 61440;
/*     */   
/*     */   private static final int START_RANGE_F100 = 61696;
/*     */   private static final int START_RANGE_F200 = 61952;
/*  64 */   private static final Map<String, Integer> INVERTED_MACOS_ROMAN = new HashMap<String, Integer>(250);
/*     */   
/*     */   static {
/*  67 */     Map<Integer, String> codeToName = MacOSRomanEncoding.INSTANCE.getCodeToNameMap();
/*  68 */     for (Map.Entry<Integer, String> entry : codeToName.entrySet()) {
/*     */       
/*  70 */       if (!INVERTED_MACOS_ROMAN.containsKey(entry.getValue()))
/*     */       {
/*  72 */         INVERTED_MACOS_ROMAN.put(entry.getValue(), entry.getKey());
/*     */       }
/*     */     } 
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
/*     */   public static PDTrueTypeFont load(PDDocument doc, File file, Encoding encoding) throws IOException {
/*  92 */     return new PDTrueTypeFont(doc, (new TTFParser()).parse(file), encoding, true);
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
/*     */   public static PDTrueTypeFont load(PDDocument doc, InputStream input, Encoding encoding) throws IOException {
/* 110 */     return new PDTrueTypeFont(doc, (new TTFParser()).parse(input), encoding, true);
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
/*     */   public static PDTrueTypeFont load(PDDocument doc, TrueTypeFont ttf, Encoding encoding) throws IOException {
/* 130 */     return new PDTrueTypeFont(doc, ttf, encoding, false);
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
/*     */   @Deprecated
/*     */   public static PDTrueTypeFont loadTTF(PDDocument doc, File file) throws IOException {
/* 146 */     return new PDTrueTypeFont(doc, (new TTFParser()).parse(file), (Encoding)WinAnsiEncoding.INSTANCE, true);
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
/*     */   @Deprecated
/*     */   public static PDTrueTypeFont loadTTF(PDDocument doc, InputStream input) throws IOException {
/* 162 */     return new PDTrueTypeFont(doc, (new TTFParser()).parse(input), (Encoding)WinAnsiEncoding.INSTANCE, true);
/*     */   }
/*     */ 
/*     */   
/* 166 */   private CmapSubtable cmapWinUnicode = null;
/* 167 */   private CmapSubtable cmapWinSymbol = null;
/* 168 */   private CmapSubtable cmapMacRoman = null;
/*     */   
/*     */   private boolean cmapInitialized = false;
/*     */   
/*     */   private Map<Integer, Integer> gidToCode;
/*     */   
/*     */   private final TrueTypeFont ttf;
/*     */   
/*     */   private final boolean isEmbedded;
/*     */   
/*     */   private final boolean isDamaged;
/*     */   
/*     */   private BoundingBox fontBBox;
/*     */ 
/*     */   
/*     */   public PDTrueTypeFont(COSDictionary fontDictionary) throws IOException {
/* 184 */     super(fontDictionary);
/*     */     
/* 186 */     TrueTypeFont ttfFont = null;
/* 187 */     boolean fontIsDamaged = false;
/* 188 */     if (getFontDescriptor() != null) {
/*     */       
/* 190 */       PDFontDescriptor fd = getFontDescriptor();
/* 191 */       PDStream ff2Stream = fd.getFontFile2();
/* 192 */       if (ff2Stream != null) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 197 */           TTFParser ttfParser = new TTFParser(true);
/* 198 */           ttfFont = ttfParser.parse((InputStream)ff2Stream.createInputStream());
/*     */         }
/* 200 */         catch (NullPointerException e) {
/*     */           
/* 202 */           LOG.warn("Could not read embedded TTF for font " + getBaseFont(), e);
/* 203 */           fontIsDamaged = true;
/*     */         }
/* 205 */         catch (IOException e) {
/*     */           
/* 207 */           LOG.warn("Could not read embedded TTF for font " + getBaseFont(), e);
/* 208 */           fontIsDamaged = true;
/*     */         } 
/*     */       }
/*     */     } 
/* 212 */     this.isEmbedded = (ttfFont != null);
/* 213 */     this.isDamaged = fontIsDamaged;
/*     */ 
/*     */     
/* 216 */     if (ttfFont == null) {
/*     */ 
/*     */       
/* 219 */       FontMapping<TrueTypeFont> mapping = FontMappers.instance().getTrueTypeFont(getBaseFont(), 
/* 220 */           getFontDescriptor());
/* 221 */       ttfFont = mapping.getFont();
/*     */       
/* 223 */       if (mapping.isFallback())
/*     */       {
/* 225 */         LOG.warn("Using fallback font '" + ttfFont + "' for '" + getBaseFont() + "'");
/*     */       }
/*     */     } 
/* 228 */     this.ttf = ttfFont;
/* 229 */     readEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getBaseFont() {
/* 237 */     return this.dict.getNameAsString(COSName.BASE_FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Encoding readEncodingFromFont() throws IOException {
/* 243 */     if (!isEmbedded() && getStandard14AFM() != null)
/*     */     {
/*     */       
/* 246 */       return (Encoding)new Type1Encoding(getStandard14AFM());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     if (getSymbolicFlag() != null && !getSymbolicFlag().booleanValue())
/*     */     {
/* 255 */       return (Encoding)StandardEncoding.INSTANCE;
/*     */     }
/*     */ 
/*     */     
/* 259 */     String standard14Name = Standard14Fonts.getMappedFontName(getName());
/*     */ 
/*     */     
/* 262 */     if (isStandard14() && 
/* 263 */       !standard14Name.equals("Symbol") && 
/* 264 */       !standard14Name.equals("ZapfDingbats"))
/*     */     {
/* 266 */       return (Encoding)StandardEncoding.INSTANCE;
/*     */     }
/*     */ 
/*     */     
/* 270 */     PostScriptTable post = this.ttf.getPostScript();
/* 271 */     Map<Integer, String> codeToName = new HashMap<Integer, String>();
/* 272 */     for (int code = 0; code <= 256; code++) {
/*     */       
/* 274 */       int gid = codeToGID(code);
/* 275 */       if (gid > 0) {
/*     */         
/* 277 */         String name = null;
/* 278 */         if (post != null)
/*     */         {
/* 280 */           name = post.getName(gid);
/*     */         }
/* 282 */         if (name == null)
/*     */         {
/*     */           
/* 285 */           name = Integer.toString(gid);
/*     */         }
/* 287 */         codeToName.put(Integer.valueOf(code), name);
/*     */       } 
/*     */     } 
/* 290 */     return (Encoding)new BuiltInEncoding(codeToName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDTrueTypeFont(PDDocument document, TrueTypeFont ttf, Encoding encoding, boolean closeTTF) throws IOException {
/* 301 */     PDTrueTypeFontEmbedder embedder = new PDTrueTypeFontEmbedder(document, this.dict, ttf, encoding);
/*     */     
/* 303 */     this.encoding = encoding;
/* 304 */     this.ttf = ttf;
/* 305 */     setFontDescriptor(embedder.getFontDescriptor());
/* 306 */     this.isEmbedded = true;
/* 307 */     this.isDamaged = false;
/* 308 */     this.glyphList = GlyphList.getAdobeGlyphList();
/* 309 */     if (closeTTF)
/*     */     {
/*     */       
/* 312 */       ttf.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCode(InputStream in) throws IOException {
/* 319 */     return in.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 325 */     return getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() throws IOException {
/* 331 */     if (this.fontBBox == null)
/*     */     {
/* 333 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 335 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() throws IOException {
/* 340 */     if (getFontDescriptor() != null) {
/* 341 */       PDRectangle bbox = getFontDescriptor().getFontBoundingBox();
/* 342 */       if (bbox != null)
/*     */       {
/* 344 */         return new BoundingBox(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox
/* 345 */             .getUpperRightX(), bbox.getUpperRightY());
/*     */       }
/*     */     } 
/* 348 */     return this.ttf.getFontBBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 354 */     return this.isDamaged;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TrueTypeFont getTrueTypeFont() {
/* 362 */     return this.ttf;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 368 */     int gid = codeToGID(code);
/* 369 */     float width = this.ttf.getAdvanceWidth(gid);
/* 370 */     float unitsPerEM = this.ttf.getUnitsPerEm();
/* 371 */     if (unitsPerEM != 1000.0F)
/*     */     {
/* 373 */       width *= 1000.0F / unitsPerEM;
/*     */     }
/* 375 */     return width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 381 */     int gid = codeToGID(code);
/* 382 */     GlyphData glyph = this.ttf.getGlyph().getGlyph(gid);
/* 383 */     if (glyph != null)
/*     */     {
/* 385 */       return glyph.getBoundingBox().getHeight();
/*     */     }
/* 387 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] encode(int unicode) throws IOException {
/* 393 */     if (this.encoding != null) {
/*     */       
/* 395 */       if (!this.encoding.contains(getGlyphList().codePointToName(unicode)))
/*     */       {
/* 397 */         throw new IllegalArgumentException(
/* 398 */             String.format("U+%04X is not available in this font's encoding: %s", new Object[] {
/* 399 */                 Integer.valueOf(unicode), this.encoding.getEncodingName()
/*     */               }));
/*     */       }
/* 402 */       String str = getGlyphList().codePointToName(unicode);
/* 403 */       Map<String, Integer> inverted = this.encoding.getNameToCodeMap();
/*     */       
/* 405 */       if (!this.ttf.hasGlyph(str)) {
/*     */ 
/*     */         
/* 408 */         String uniName = UniUtil.getUniNameOfCodePoint(unicode);
/* 409 */         if (!this.ttf.hasGlyph(uniName))
/*     */         {
/* 411 */           throw new IllegalArgumentException(
/* 412 */               String.format("No glyph for U+%04X in font %s", new Object[] { Integer.valueOf(unicode), getName() }));
/*     */         }
/*     */       } 
/*     */       
/* 416 */       int i = ((Integer)inverted.get(str)).intValue();
/* 417 */       return new byte[] { (byte)i };
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 422 */     String name = getGlyphList().codePointToName(unicode);
/*     */     
/* 424 */     if (!this.ttf.hasGlyph(name))
/*     */     {
/* 426 */       throw new IllegalArgumentException(
/* 427 */           String.format("No glyph for U+%04X in font %s", new Object[] { Integer.valueOf(unicode), getName() }));
/*     */     }
/*     */     
/* 430 */     int gid = this.ttf.nameToGID(name);
/* 431 */     Integer code = getGIDToCode().get(Integer.valueOf(gid));
/* 432 */     if (code == null)
/*     */     {
/* 434 */       throw new IllegalArgumentException(
/* 435 */           String.format("U+%04X is not available in this font's Encoding", new Object[] { Integer.valueOf(unicode) }));
/*     */     }
/*     */     
/* 438 */     return new byte[] { (byte)code.intValue() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Integer, Integer> getGIDToCode() throws IOException {
/* 447 */     if (this.gidToCode != null)
/*     */     {
/* 449 */       return this.gidToCode;
/*     */     }
/*     */     
/* 452 */     this.gidToCode = new HashMap<Integer, Integer>();
/* 453 */     for (int code = 0; code <= 255; code++) {
/*     */       
/* 455 */       int gid = codeToGID(code);
/* 456 */       if (!this.gidToCode.containsKey(Integer.valueOf(gid)))
/*     */       {
/* 458 */         this.gidToCode.put(Integer.valueOf(gid), Integer.valueOf(code));
/*     */       }
/*     */     } 
/* 461 */     return this.gidToCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 467 */     return this.isEmbedded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(int code) throws IOException {
/* 473 */     int gid = codeToGID(code);
/* 474 */     GlyphData glyph = this.ttf.getGlyph().getGlyph(gid);
/*     */ 
/*     */     
/* 477 */     if (glyph == null)
/*     */     {
/* 479 */       return new GeneralPath();
/*     */     }
/*     */ 
/*     */     
/* 483 */     return glyph.getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 491 */     int gid = this.ttf.nameToGID(name);
/* 492 */     if (gid == 0) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 497 */         gid = Integer.parseInt(name);
/* 498 */         if (gid > this.ttf.getNumberOfGlyphs())
/*     */         {
/* 500 */           gid = 0;
/*     */         }
/*     */       }
/* 503 */       catch (NumberFormatException e) {
/*     */         
/* 505 */         gid = 0;
/*     */       } 
/*     */     }
/*     */     
/* 509 */     if (gid == 0)
/*     */     {
/* 511 */       return new GeneralPath();
/*     */     }
/*     */     
/* 514 */     GlyphData glyph = this.ttf.getGlyph().getGlyph(gid);
/* 515 */     if (glyph != null)
/*     */     {
/* 517 */       return glyph.getPath();
/*     */     }
/*     */ 
/*     */     
/* 521 */     return new GeneralPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) throws IOException {
/* 528 */     int gid = this.ttf.nameToGID(name);
/* 529 */     return (gid != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontBoxFont getFontBoxFont() {
/* 535 */     return (FontBoxFont)this.ttf;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(int code) throws IOException {
/* 541 */     return (codeToGID(code) != 0);
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
/*     */   public int codeToGID(int code) throws IOException {
/* 553 */     extractCmapTable();
/* 554 */     int gid = 0;
/*     */     
/* 556 */     if (!isSymbolic()) {
/*     */       
/* 558 */       String name = this.encoding.getName(code);
/* 559 */       if (".notdef".equals(name))
/*     */       {
/* 561 */         return 0;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 566 */       if (this.cmapWinUnicode != null) {
/*     */         
/* 568 */         String unicode = GlyphList.getAdobeGlyphList().toUnicode(name);
/* 569 */         if (unicode != null) {
/*     */           
/* 571 */           int uni = unicode.codePointAt(0);
/* 572 */           gid = this.cmapWinUnicode.getGlyphId(uni);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 577 */       if (gid == 0 && this.cmapMacRoman != null) {
/*     */         
/* 579 */         Integer macCode = INVERTED_MACOS_ROMAN.get(name);
/* 580 */         if (macCode != null)
/*     */         {
/* 582 */           gid = this.cmapMacRoman.getGlyphId(macCode.intValue());
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 587 */       if (gid == 0)
/*     */       {
/* 589 */         gid = this.ttf.nameToGID(name);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 596 */       if (this.cmapWinSymbol != null) {
/*     */         
/* 598 */         gid = this.cmapWinSymbol.getGlyphId(code);
/* 599 */         if (code >= 0 && code <= 255) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 604 */           if (gid == 0)
/*     */           {
/*     */             
/* 607 */             gid = this.cmapWinSymbol.getGlyphId(code + 61440);
/*     */           }
/* 609 */           if (gid == 0)
/*     */           {
/*     */             
/* 612 */             gid = this.cmapWinSymbol.getGlyphId(code + 61696);
/*     */           }
/* 614 */           if (gid == 0)
/*     */           {
/*     */             
/* 617 */             gid = this.cmapWinSymbol.getGlyphId(code + 61952);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 623 */       if (gid == 0 && this.cmapMacRoman != null)
/*     */       {
/* 625 */         gid = this.cmapMacRoman.getGlyphId(code);
/*     */       }
/*     */ 
/*     */       
/* 629 */       if (gid == 0 && this.cmapWinUnicode != null && this.encoding != null) {
/*     */         
/* 631 */         String name = this.encoding.getName(code);
/* 632 */         if (".notdef".equals(name))
/*     */         {
/* 634 */           return 0;
/*     */         }
/* 636 */         String unicode = GlyphList.getAdobeGlyphList().toUnicode(name);
/* 637 */         if (unicode != null) {
/*     */           
/* 639 */           int uni = unicode.codePointAt(0);
/* 640 */           gid = this.cmapWinUnicode.getGlyphId(uni);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 645 */     return gid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void extractCmapTable() throws IOException {
/* 653 */     if (this.cmapInitialized) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 658 */     CmapTable cmapTable = this.ttf.getCmap();
/* 659 */     if (cmapTable != null) {
/*     */ 
/*     */       
/* 662 */       CmapSubtable[] cmaps = cmapTable.getCmaps();
/* 663 */       for (CmapSubtable cmap : cmaps) {
/*     */         
/* 665 */         if (3 == cmap.getPlatformId()) {
/*     */           
/* 667 */           if (1 == cmap.getPlatformEncodingId())
/*     */           {
/* 669 */             this.cmapWinUnicode = cmap;
/*     */           }
/* 671 */           else if (0 == cmap.getPlatformEncodingId())
/*     */           {
/* 673 */             this.cmapWinSymbol = cmap;
/*     */           }
/*     */         
/* 676 */         } else if (1 == cmap.getPlatformId() && 0 == cmap
/* 677 */           .getPlatformEncodingId()) {
/*     */           
/* 679 */           this.cmapMacRoman = cmap;
/*     */         } 
/*     */       } 
/*     */     } 
/* 683 */     this.cmapInitialized = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDTrueTypeFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */