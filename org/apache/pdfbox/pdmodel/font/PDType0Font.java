/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.cmap.CMap;
/*     */ import org.apache.fontbox.ttf.TTFParser;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
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
/*     */ 
/*     */ public class PDType0Font
/*     */   extends PDFont
/*     */   implements PDVectorFont
/*     */ {
/*  46 */   private static final Log LOG = LogFactory.getLog(PDType0Font.class);
/*     */   private final PDCIDFont descendantFont;
/*     */   private CMap cMap;
/*     */   private CMap cMapUCS2;
/*     */   private boolean isCMapPredefined;
/*     */   private boolean isDescendantCJK;
/*     */   private PDCIDFontType2Embedder embedder;
/*  53 */   private final Set<Integer> noUnicode = new HashSet<Integer>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TrueTypeFont ttf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDType0Font load(PDDocument doc, File file) throws IOException {
/*  66 */     return new PDType0Font(doc, (new TTFParser()).parse(file), true, true, false);
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
/*     */   public static PDType0Font load(PDDocument doc, InputStream input) throws IOException {
/*  79 */     return new PDType0Font(doc, (new TTFParser()).parse(input), true, true, false);
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
/*     */   public static PDType0Font load(PDDocument doc, InputStream input, boolean embedSubset) throws IOException {
/*  94 */     return new PDType0Font(doc, (new TTFParser()).parse(input), embedSubset, true, false);
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
/*     */   public static PDType0Font load(PDDocument doc, TrueTypeFont ttf, boolean embedSubset) throws IOException {
/* 109 */     return new PDType0Font(doc, ttf, embedSubset, false, false);
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
/*     */   public static PDType0Font loadVertical(PDDocument doc, File file) throws IOException {
/* 122 */     return new PDType0Font(doc, (new TTFParser()).parse(file), true, true, true);
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
/*     */   public static PDType0Font loadVertical(PDDocument doc, InputStream input) throws IOException {
/* 135 */     return new PDType0Font(doc, (new TTFParser()).parse(input), true, true, true);
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
/*     */   public static PDType0Font loadVertical(PDDocument doc, InputStream input, boolean embedSubset) throws IOException {
/* 150 */     return new PDType0Font(doc, (new TTFParser()).parse(input), embedSubset, true, true);
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
/*     */   public static PDType0Font loadVertical(PDDocument doc, TrueTypeFont ttf, boolean embedSubset) throws IOException {
/* 165 */     return new PDType0Font(doc, ttf, embedSubset, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDType0Font(COSDictionary fontDictionary) throws IOException {
/* 176 */     super(fontDictionary);
/* 177 */     COSBase base = this.dict.getDictionaryObject(COSName.DESCENDANT_FONTS);
/* 178 */     if (!(base instanceof COSArray))
/*     */     {
/* 180 */       throw new IOException("Missing descendant font array");
/*     */     }
/* 182 */     COSArray descendantFonts = (COSArray)base;
/* 183 */     if (descendantFonts.size() == 0)
/*     */     {
/* 185 */       throw new IOException("Descendant font array is empty");
/*     */     }
/* 187 */     COSBase descendantFontDictBase = descendantFonts.getObject(0);
/* 188 */     if (!(descendantFontDictBase instanceof COSDictionary))
/*     */     {
/* 190 */       throw new IOException("Missing descendant font dictionary");
/*     */     }
/* 192 */     this.descendantFont = PDFontFactory.createDescendantFont((COSDictionary)descendantFontDictBase, this);
/* 193 */     readEncoding();
/* 194 */     fetchCMapUCS2();
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
/*     */   private PDType0Font(PDDocument document, TrueTypeFont ttf, boolean embedSubset, boolean closeTTF, boolean vertical) throws IOException {
/* 212 */     if (vertical)
/*     */     {
/* 214 */       ttf.enableVerticalSubstitutions();
/*     */     }
/* 216 */     this.embedder = new PDCIDFontType2Embedder(document, this.dict, ttf, embedSubset, this, vertical);
/* 217 */     this.descendantFont = this.embedder.getCIDFont();
/* 218 */     readEncoding();
/* 219 */     fetchCMapUCS2();
/* 220 */     if (closeTTF)
/*     */     {
/* 222 */       if (embedSubset) {
/*     */         
/* 224 */         this.ttf = ttf;
/* 225 */         document.registerTrueTypeFontForClosing(ttf);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 230 */         ttf.close();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSubset(int codePoint) {
/* 238 */     if (!willBeSubset())
/*     */     {
/* 240 */       throw new IllegalStateException("This font was created with subsetting disabled");
/*     */     }
/* 242 */     this.embedder.addToSubset(codePoint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void subset() throws IOException {
/* 248 */     if (!willBeSubset())
/*     */     {
/* 250 */       throw new IllegalStateException("This font was created with subsetting disabled");
/*     */     }
/* 252 */     this.embedder.subset();
/* 253 */     if (this.ttf != null) {
/*     */       
/* 255 */       this.ttf.close();
/* 256 */       this.ttf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean willBeSubset() {
/* 263 */     return (this.embedder != null && this.embedder.needsSubset());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readEncoding() throws IOException {
/* 271 */     COSBase encoding = this.dict.getDictionaryObject(COSName.ENCODING);
/* 272 */     if (encoding instanceof COSName) {
/*     */ 
/*     */       
/* 275 */       COSName encodingName = (COSName)encoding;
/* 276 */       this.cMap = CMapManager.getPredefinedCMap(encodingName.getName());
/* 277 */       if (this.cMap != null)
/*     */       {
/* 279 */         this.isCMapPredefined = true;
/*     */       }
/*     */       else
/*     */       {
/* 283 */         throw new IOException("Missing required CMap");
/*     */       }
/*     */     
/* 286 */     } else if (encoding != null) {
/*     */       
/* 288 */       this.cMap = readCMap(encoding);
/* 289 */       if (this.cMap == null)
/*     */       {
/* 291 */         throw new IOException("Missing required CMap");
/*     */       }
/* 293 */       if (!this.cMap.hasCIDMappings())
/*     */       {
/* 295 */         LOG.warn("Invalid Encoding CMap in font " + getName());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 300 */     PDCIDSystemInfo ros = this.descendantFont.getCIDSystemInfo();
/* 301 */     if (ros != null)
/*     */     {
/* 303 */       this
/*     */ 
/*     */ 
/*     */         
/* 307 */         .isDescendantCJK = ("Adobe".equals(ros.getRegistry()) && ("GB1".equals(ros.getOrdering()) || "CNS1".equals(ros.getOrdering()) || "Japan1".equals(ros.getOrdering()) || "Korea1".equals(ros.getOrdering())));
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
/*     */   private void fetchCMapUCS2() throws IOException {
/* 319 */     COSName name = this.dict.getCOSName(COSName.ENCODING);
/* 320 */     if ((this.isCMapPredefined && name != COSName.IDENTITY_H && name != COSName.IDENTITY_V) || this.isDescendantCJK) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 330 */       String strName = null;
/* 331 */       if (this.isDescendantCJK) {
/*     */ 
/*     */ 
/*     */         
/* 335 */         strName = this.descendantFont.getCIDSystemInfo().getRegistry() + "-" + this.descendantFont.getCIDSystemInfo().getOrdering() + "-" + this.descendantFont.getCIDSystemInfo().getSupplement();
/*     */       }
/* 337 */       else if (name != null) {
/*     */         
/* 339 */         strName = name.getName();
/*     */       } 
/*     */ 
/*     */       
/* 343 */       if (strName != null) {
/*     */         
/* 345 */         CMap prdCMap = CMapManager.getPredefinedCMap(strName);
/* 346 */         String ucs2Name = prdCMap.getRegistry() + "-" + prdCMap.getOrdering() + "-UCS2";
/* 347 */         this.cMapUCS2 = CMapManager.getPredefinedCMap(ucs2Name);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseFont() {
/* 357 */     return this.dict.getNameAsString(COSName.BASE_FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCIDFont getDescendantFont() {
/* 365 */     return this.descendantFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CMap getCMap() {
/* 373 */     return this.cMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CMap getCMapUCS2() {
/* 381 */     return this.cMapUCS2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontDescriptor getFontDescriptor() {
/* 387 */     return this.descendantFont.getFontDescriptor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getFontMatrix() {
/* 393 */     return this.descendantFont.getFontMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVertical() {
/* 399 */     return (this.cMap.getWMode() == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 405 */     return this.descendantFont.getHeight(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] encode(int unicode) throws IOException {
/* 411 */     return this.descendantFont.encode(unicode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExplicitWidth(int code) throws IOException {
/* 417 */     return this.descendantFont.hasExplicitWidth(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageFontWidth() {
/* 423 */     return this.descendantFont.getAverageFontWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getPositionVector(int code) {
/* 430 */     return this.descendantFont.getPositionVector(code).scale(-0.001F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getDisplacement(int code) throws IOException {
/* 436 */     if (isVertical())
/*     */     {
/* 438 */       return new Vector(0.0F, this.descendantFont.getVerticalDisplacementVectorY(code) / 1000.0F);
/*     */     }
/*     */ 
/*     */     
/* 442 */     return super.getDisplacement(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(int code) throws IOException {
/* 449 */     return this.descendantFont.getWidth(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getStandard14Width(int code) {
/* 455 */     throw new UnsupportedOperationException("not suppported");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 461 */     return this.descendantFont.getWidthFromFont(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 467 */     return this.descendantFont.isEmbedded();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(int code) throws IOException {
/* 474 */     String unicode = super.toUnicode(code);
/* 475 */     if (unicode != null)
/*     */     {
/* 477 */       return unicode;
/*     */     }
/*     */     
/* 480 */     if ((this.isCMapPredefined || this.isDescendantCJK) && this.cMapUCS2 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       int cid = codeToCID(code);
/*     */ 
/*     */       
/* 489 */       return this.cMapUCS2.toUnicode(cid);
/*     */     } 
/*     */ 
/*     */     
/* 493 */     if (LOG.isWarnEnabled() && !this.noUnicode.contains(Integer.valueOf(code))) {
/*     */ 
/*     */       
/* 496 */       String cid = "CID+" + codeToCID(code);
/* 497 */       LOG.warn("No Unicode mapping for " + cid + " (" + code + ") in font " + getName());
/*     */       
/* 499 */       this.noUnicode.add(Integer.valueOf(code));
/*     */     } 
/* 501 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 508 */     return getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() throws IOException {
/* 515 */     return this.descendantFont.getBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCode(InputStream in) throws IOException {
/* 521 */     return this.cMap.readCode(in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int codeToCID(int code) {
/* 532 */     return this.descendantFont.codeToCID(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int codeToGID(int code) throws IOException {
/* 543 */     return this.descendantFont.codeToGID(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandard14() {
/* 549 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 555 */     return this.descendantFont.isDamaged();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 561 */     String descendant = null;
/* 562 */     if (getDescendantFont() != null)
/*     */     {
/* 564 */       descendant = getDescendantFont().getClass().getSimpleName();
/*     */     }
/* 566 */     return getClass().getSimpleName() + "/" + descendant + ", PostScript name: " + getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(int code) throws IOException {
/* 572 */     return this.descendantFont.getPath(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(int code) throws IOException {
/* 578 */     return this.descendantFont.hasGlyph(code);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType0Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */