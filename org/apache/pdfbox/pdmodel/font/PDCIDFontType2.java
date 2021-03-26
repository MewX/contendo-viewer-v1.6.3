/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.cff.Type2CharString;
/*     */ import org.apache.fontbox.cmap.CMap;
/*     */ import org.apache.fontbox.ttf.CmapLookup;
/*     */ import org.apache.fontbox.ttf.GlyphData;
/*     */ import org.apache.fontbox.ttf.OTFParser;
/*     */ import org.apache.fontbox.ttf.OpenTypeFont;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public class PDCIDFontType2
/*     */   extends PDCIDFont
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(PDCIDFontType2.class);
/*     */ 
/*     */   
/*     */   private final TrueTypeFont ttf;
/*     */   
/*     */   private final int[] cid2gid;
/*     */   
/*     */   private final boolean isEmbedded;
/*     */   
/*     */   private final boolean isDamaged;
/*     */   
/*     */   private final CmapLookup cmap;
/*     */   
/*     */   private Matrix fontMatrix;
/*     */   
/*     */   private BoundingBox fontBBox;
/*     */ 
/*     */   
/*     */   public PDCIDFontType2(COSDictionary fontDictionary, PDType0Font parent) throws IOException {
/*  62 */     this(fontDictionary, parent, (TrueTypeFont)null);
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
/*     */   public PDCIDFontType2(COSDictionary fontDictionary, PDType0Font parent, TrueTypeFont trueTypeFont) throws IOException {
/*  75 */     super(fontDictionary, parent);
/*     */     
/*  77 */     PDFontDescriptor fd = getFontDescriptor();
/*  78 */     if (trueTypeFont != null) {
/*     */       
/*  80 */       this.ttf = trueTypeFont;
/*  81 */       this.isEmbedded = true;
/*  82 */       this.isDamaged = false;
/*     */     } else {
/*     */       OpenTypeFont openTypeFont;
/*     */       TrueTypeFont trueTypeFont1;
/*  86 */       boolean fontIsDamaged = false;
/*  87 */       TrueTypeFont ttfFont = null;
/*     */       
/*  89 */       PDStream stream = null;
/*  90 */       if (fd != null) {
/*     */         
/*  92 */         stream = fd.getFontFile2();
/*  93 */         if (stream == null)
/*     */         {
/*  95 */           stream = fd.getFontFile3();
/*     */         }
/*  97 */         if (stream == null)
/*     */         {
/*     */           
/* 100 */           stream = fd.getFontFile();
/*     */         }
/*     */       } 
/* 103 */       if (stream != null) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 108 */           OTFParser otfParser = new OTFParser(true);
/* 109 */           OpenTypeFont otf = otfParser.parse((InputStream)stream.createInputStream());
/* 110 */           openTypeFont = otf;
/*     */           
/* 112 */           if (otf.isPostScript()) {
/*     */ 
/*     */             
/* 115 */             fontIsDamaged = true;
/* 116 */             LOG.warn("Found CFF/OTF but expected embedded TTF font " + fd.getFontName());
/*     */           } 
/*     */           
/* 119 */           if (otf.hasLayoutTables())
/*     */           {
/* 121 */             LOG.info("OpenType Layout tables used in font " + getBaseFont() + " are not implemented in PDFBox and will be ignored");
/*     */           
/*     */           }
/*     */         }
/* 125 */         catch (NullPointerException e) {
/*     */           
/* 127 */           fontIsDamaged = true;
/* 128 */           LOG.warn("Could not read embedded OTF for font " + getBaseFont(), e);
/*     */         }
/* 130 */         catch (IOException e) {
/*     */           
/* 132 */           fontIsDamaged = true;
/* 133 */           LOG.warn("Could not read embedded OTF for font " + getBaseFont(), e);
/*     */         } 
/*     */       }
/* 136 */       this.isEmbedded = (openTypeFont != null);
/* 137 */       this.isDamaged = fontIsDamaged;
/*     */       
/* 139 */       if (openTypeFont == null)
/*     */       {
/* 141 */         trueTypeFont1 = findFontOrSubstitute();
/*     */       }
/* 143 */       this.ttf = trueTypeFont1;
/*     */     } 
/* 145 */     this.cmap = this.ttf.getUnicodeCmapLookup(false);
/* 146 */     this.cid2gid = readCIDToGIDMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TrueTypeFont findFontOrSubstitute() throws IOException {
/*     */     TrueTypeFont ttfFont;
/* 154 */     CIDFontMapping mapping = FontMappers.instance().getCIDFont(getBaseFont(), getFontDescriptor(), 
/* 155 */         getCIDSystemInfo());
/* 156 */     if (mapping.isCIDFont()) {
/*     */       
/* 158 */       ttfFont = (TrueTypeFont)mapping.getFont();
/*     */     }
/*     */     else {
/*     */       
/* 162 */       ttfFont = (TrueTypeFont)mapping.getTrueTypeFont();
/*     */     } 
/* 164 */     if (mapping.isFallback())
/*     */     {
/* 166 */       LOG.warn("Using fallback font " + ttfFont.getName() + " for CID-keyed TrueType font " + getBaseFont());
/*     */     }
/* 168 */     return ttfFont;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getFontMatrix() {
/* 174 */     if (this.fontMatrix == null)
/*     */     {
/*     */       
/* 177 */       this.fontMatrix = new Matrix(0.001F, 0.0F, 0.0F, 0.001F, 0.0F, 0.0F);
/*     */     }
/* 179 */     return this.fontMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() throws IOException {
/* 185 */     if (this.fontBBox == null)
/*     */     {
/* 187 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 189 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() throws IOException {
/* 194 */     if (getFontDescriptor() != null) {
/*     */       
/* 196 */       PDRectangle bbox = getFontDescriptor().getFontBoundingBox();
/* 197 */       if (bbox != null && (
/* 198 */         Float.compare(bbox.getLowerLeftX(), 0.0F) != 0 || 
/* 199 */         Float.compare(bbox.getLowerLeftY(), 0.0F) != 0 || 
/* 200 */         Float.compare(bbox.getUpperRightX(), 0.0F) != 0 || 
/* 201 */         Float.compare(bbox.getUpperRightY(), 0.0F) != 0))
/*     */       {
/* 203 */         return new BoundingBox(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox
/* 204 */             .getUpperRightX(), bbox.getUpperRightY());
/*     */       }
/*     */     } 
/* 207 */     return this.ttf.getFontBBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int codeToCID(int code) {
/* 213 */     CMap cMap = this.parent.getCMap();
/*     */ 
/*     */     
/* 216 */     if (!cMap.hasCIDMappings() && cMap.hasUnicodeMappings())
/*     */     {
/* 218 */       return cMap.toUnicode(code).codePointAt(0);
/*     */     }
/*     */     
/* 221 */     return cMap.toCID(code);
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
/*     */   public int codeToGID(int code) throws IOException {
/* 234 */     if (!this.isEmbedded) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       if (this.cid2gid != null && !this.isDamaged) {
/*     */ 
/*     */         
/* 244 */         LOG.warn("Using non-embedded GIDs in font " + getName());
/* 245 */         int i = codeToCID(code);
/* 246 */         return this.cid2gid[i];
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 251 */       String unicode = this.parent.toUnicode(code);
/* 252 */       if (unicode == null) {
/*     */         
/* 254 */         LOG.warn("Failed to find a character mapping for " + code + " in " + getName());
/*     */ 
/*     */         
/* 257 */         return codeToCID(code);
/*     */       } 
/* 259 */       if (unicode.length() > 1)
/*     */       {
/* 261 */         LOG.warn("Trying to map multi-byte character using 'cmap', result will be poor");
/*     */       }
/*     */ 
/*     */       
/* 265 */       return this.cmap.getGlyphId(unicode.codePointAt(0));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     int cid = codeToCID(code);
/* 275 */     if (this.cid2gid != null) {
/*     */ 
/*     */       
/* 278 */       if (cid < this.cid2gid.length)
/*     */       {
/* 280 */         return this.cid2gid[cid];
/*     */       }
/*     */ 
/*     */       
/* 284 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (cid < this.ttf.getNumberOfGlyphs())
/*     */     {
/* 292 */       return cid;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 297 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 307 */     return ((this.ttf.getHorizontalHeader().getAscender() + -this.ttf.getHorizontalHeader().getDescender()) / this.ttf
/* 308 */       .getUnitsPerEm());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 314 */     int gid = codeToGID(code);
/* 315 */     int width = this.ttf.getAdvanceWidth(gid);
/* 316 */     int unitsPerEM = this.ttf.getUnitsPerEm();
/* 317 */     if (unitsPerEM != 1000)
/*     */     {
/* 319 */       width = (int)(width * 1000.0F / unitsPerEM);
/*     */     }
/* 321 */     return width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encode(int unicode) {
/* 327 */     int cid = -1;
/* 328 */     if (this.isEmbedded) {
/*     */ 
/*     */       
/* 331 */       if (this.parent.getCMap().getName().startsWith("Identity-")) {
/*     */         
/* 333 */         if (this.cmap != null)
/*     */         {
/* 335 */           cid = this.cmap.getGlyphId(unicode);
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 341 */       else if (this.parent.getCMapUCS2() != null) {
/*     */         
/* 343 */         cid = this.parent.getCMapUCS2().toCID(unicode);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 348 */       if (cid == -1)
/*     */       {
/*     */ 
/*     */         
/* 352 */         cid = 0;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 358 */       cid = this.cmap.getGlyphId(unicode);
/*     */     } 
/*     */     
/* 361 */     if (cid == 0)
/*     */     {
/* 363 */       throw new IllegalArgumentException(
/* 364 */           String.format("No glyph for U+%04X in font %s", new Object[] { Integer.valueOf(unicode), getName() }));
/*     */     }
/*     */ 
/*     */     
/* 368 */     return new byte[] { (byte)(cid >> 8 & 0xFF), (byte)(cid & 0xFF) };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 374 */     return this.isEmbedded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 380 */     return this.isDamaged;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TrueTypeFont getTrueTypeFont() {
/* 389 */     return this.ttf;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(int code) throws IOException {
/* 395 */     if (this.ttf instanceof OpenTypeFont && ((OpenTypeFont)this.ttf).isPostScript()) {
/*     */ 
/*     */ 
/*     */       
/* 399 */       int cid = codeToGID(code);
/* 400 */       Type2CharString charstring = ((OpenTypeFont)this.ttf).getCFF().getFont().getType2CharString(cid);
/* 401 */       return charstring.getPath();
/*     */     } 
/*     */ 
/*     */     
/* 405 */     int gid = codeToGID(code);
/* 406 */     GlyphData glyph = this.ttf.getGlyph().getGlyph(gid);
/* 407 */     if (glyph != null)
/*     */     {
/* 409 */       return glyph.getPath();
/*     */     }
/* 411 */     return new GeneralPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(int code) throws IOException {
/* 418 */     return (codeToGID(code) != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDCIDFontType2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */