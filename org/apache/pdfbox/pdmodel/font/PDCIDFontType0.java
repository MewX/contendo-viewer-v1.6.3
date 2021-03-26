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
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.cff.CFFCIDFont;
/*     */ import org.apache.fontbox.cff.CFFFont;
/*     */ import org.apache.fontbox.cff.CFFParser;
/*     */ import org.apache.fontbox.cff.CFFType1Font;
/*     */ import org.apache.fontbox.cff.Type2CharString;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.io.IOUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDCIDFontType0
/*     */   extends PDCIDFont
/*     */ {
/*  52 */   private static final Log LOG = LogFactory.getLog(PDCIDFontType0.class);
/*     */   
/*     */   private final CFFCIDFont cidFont;
/*     */   
/*     */   private final FontBoxFont t1Font;
/*  57 */   private final Map<Integer, Float> glyphHeights = new HashMap<Integer, Float>();
/*     */   
/*     */   private final boolean isEmbedded;
/*     */   private final boolean isDamaged;
/*  61 */   private Float avgWidth = null;
/*     */   private Matrix fontMatrix;
/*     */   private final AffineTransform fontMatrixTransform;
/*     */   private BoundingBox fontBBox;
/*  65 */   private int[] cid2gid = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCIDFontType0(COSDictionary fontDictionary, PDType0Font parent) throws IOException {
/*  75 */     super(fontDictionary, parent);
/*     */     
/*  77 */     PDFontDescriptor fd = getFontDescriptor();
/*  78 */     byte[] bytes = null;
/*  79 */     if (fd != null) {
/*     */       
/*  81 */       PDStream ff3Stream = fd.getFontFile3();
/*  82 */       if (ff3Stream != null)
/*     */       {
/*  84 */         bytes = IOUtils.toByteArray((InputStream)ff3Stream.createInputStream());
/*     */       }
/*     */     } 
/*     */     
/*  88 */     boolean fontIsDamaged = false;
/*  89 */     CFFFont cffFont = null;
/*  90 */     if (bytes != null && bytes.length > 0 && (bytes[0] & 0xFF) == 37) {
/*     */ 
/*     */       
/*  93 */       LOG.warn("Found PFB but expected embedded CFF font " + fd.getFontName());
/*  94 */       fontIsDamaged = true;
/*     */     }
/*  96 */     else if (bytes != null) {
/*     */       
/*  98 */       CFFParser cffParser = new CFFParser();
/*     */       
/*     */       try {
/* 101 */         cffFont = cffParser.parse(bytes, new ByteSource()).get(0);
/*     */       }
/* 103 */       catch (IOException e) {
/*     */         
/* 105 */         LOG.error("Can't read the embedded CFF font " + fd.getFontName(), e);
/* 106 */         fontIsDamaged = true;
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     if (cffFont != null) {
/*     */ 
/*     */       
/* 113 */       if (cffFont instanceof CFFCIDFont) {
/*     */         
/* 115 */         this.cidFont = (CFFCIDFont)cffFont;
/* 116 */         this.t1Font = null;
/*     */       }
/*     */       else {
/*     */         
/* 120 */         this.cidFont = null;
/* 121 */         this.t1Font = (FontBoxFont)cffFont;
/*     */       } 
/* 123 */       this.cid2gid = readCIDToGIDMap();
/* 124 */       this.isEmbedded = true;
/* 125 */       this.isDamaged = false;
/*     */     } else {
/*     */       FontBoxFont font;
/*     */ 
/*     */ 
/*     */       
/* 131 */       CIDFontMapping mapping = FontMappers.instance().getCIDFont(getBaseFont(), getFontDescriptor(), 
/* 132 */           getCIDSystemInfo());
/*     */       
/* 134 */       if (mapping.isCIDFont()) {
/*     */         
/* 136 */         cffFont = mapping.getFont().getCFF().getFont();
/* 137 */         if (cffFont instanceof CFFCIDFont)
/*     */         {
/* 139 */           this.cidFont = (CFFCIDFont)cffFont;
/* 140 */           this.t1Font = null;
/* 141 */           CFFCIDFont cFFCIDFont = this.cidFont;
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 146 */           CFFType1Font f = (CFFType1Font)cffFont;
/* 147 */           this.cidFont = null;
/* 148 */           this.t1Font = (FontBoxFont)f;
/* 149 */           CFFType1Font cFFType1Font1 = f;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 154 */         this.cidFont = null;
/* 155 */         this.t1Font = mapping.getTrueTypeFont();
/* 156 */         font = this.t1Font;
/*     */       } 
/*     */       
/* 159 */       if (mapping.isFallback())
/*     */       {
/* 161 */         LOG.warn("Using fallback " + font.getName() + " for CID-keyed font " + 
/* 162 */             getBaseFont());
/*     */       }
/* 164 */       this.isEmbedded = false;
/* 165 */       this.isDamaged = fontIsDamaged;
/*     */     } 
/* 167 */     this.fontMatrixTransform = getFontMatrix().createAffineTransform();
/* 168 */     this.fontMatrixTransform.scale(1000.0D, 1000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Matrix getFontMatrix() {
/* 174 */     if (this.fontMatrix == null) {
/*     */       List<Number> numbers;
/*     */       
/* 177 */       if (this.cidFont != null) {
/*     */         
/* 179 */         numbers = this.cidFont.getFontMatrix();
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 185 */           numbers = this.t1Font.getFontMatrix();
/*     */         }
/* 187 */         catch (IOException e) {
/*     */           
/* 189 */           return new Matrix(0.001F, 0.0F, 0.0F, 0.001F, 0.0F, 0.0F);
/*     */         } 
/*     */       } 
/*     */       
/* 193 */       if (numbers != null && numbers.size() == 6) {
/*     */         
/* 195 */         this
/*     */           
/* 197 */           .fontMatrix = new Matrix(((Number)numbers.get(0)).floatValue(), ((Number)numbers.get(1)).floatValue(), ((Number)numbers.get(2)).floatValue(), ((Number)numbers.get(3)).floatValue(), ((Number)numbers.get(4)).floatValue(), ((Number)numbers.get(5)).floatValue());
/*     */       }
/*     */       else {
/*     */         
/* 201 */         this.fontMatrix = new Matrix(0.001F, 0.0F, 0.0F, 0.001F, 0.0F, 0.0F);
/*     */       } 
/*     */     } 
/* 204 */     return this.fontMatrix;
/*     */   }
/*     */   
/*     */   private class ByteSource
/*     */     implements CFFParser.ByteSource {
/*     */     private ByteSource() {}
/*     */     
/*     */     public byte[] getBytes() throws IOException {
/* 212 */       PDStream ff3Stream = PDCIDFontType0.this.getFontDescriptor().getFontFile3();
/* 213 */       return IOUtils.toByteArray((InputStream)ff3Stream.createInputStream());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() {
/* 220 */     if (this.fontBBox == null)
/*     */     {
/* 222 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 224 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() {
/* 229 */     if (getFontDescriptor() != null) {
/* 230 */       PDRectangle bbox = getFontDescriptor().getFontBoundingBox();
/* 231 */       if (bbox.getLowerLeftX() != 0.0F || bbox.getLowerLeftY() != 0.0F || bbox
/* 232 */         .getUpperRightX() != 0.0F || bbox.getUpperRightY() != 0.0F) {
/* 233 */         return new BoundingBox(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox
/* 234 */             .getUpperRightX(), bbox.getUpperRightY());
/*     */       }
/*     */     } 
/* 237 */     if (this.cidFont != null)
/*     */     {
/* 239 */       return this.cidFont.getFontBBox();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 245 */       return this.t1Font.getFontBBox();
/*     */     }
/* 247 */     catch (IOException e) {
/*     */       
/* 249 */       return new BoundingBox();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFFFont getCFFFont() {
/* 259 */     if (this.cidFont != null)
/*     */     {
/* 261 */       return (CFFFont)this.cidFont;
/*     */     }
/* 263 */     if (this.t1Font instanceof CFFType1Font)
/*     */     {
/* 265 */       return (CFFFont)this.t1Font;
/*     */     }
/*     */ 
/*     */     
/* 269 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontBoxFont getFontBoxFont() {
/* 278 */     if (this.cidFont != null)
/*     */     {
/* 280 */       return (FontBoxFont)this.cidFont;
/*     */     }
/*     */ 
/*     */     
/* 284 */     return this.t1Font;
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
/*     */   public Type2CharString getType2CharString(int cid) throws IOException {
/* 297 */     if (this.cidFont != null)
/*     */     {
/* 299 */       return (Type2CharString)this.cidFont.getType2CharString(cid);
/*     */     }
/* 301 */     if (this.t1Font instanceof CFFType1Font)
/*     */     {
/* 303 */       return ((CFFType1Font)this.t1Font).getType2CharString(cid);
/*     */     }
/*     */ 
/*     */     
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getGlyphName(int code) throws IOException {
/* 317 */     String unicodes = this.parent.toUnicode(code);
/* 318 */     if (unicodes == null)
/*     */     {
/* 320 */       return ".notdef";
/*     */     }
/* 322 */     return UniUtil.getUniNameOfCodePoint(unicodes.codePointAt(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(int code) throws IOException {
/* 328 */     int cid = codeToCID(code);
/* 329 */     if (this.cid2gid != null && this.isEmbedded)
/*     */     {
/*     */       
/* 332 */       cid = this.cid2gid[cid];
/*     */     }
/* 334 */     Type2CharString charstring = getType2CharString(cid);
/* 335 */     if (charstring != null)
/*     */     {
/* 337 */       return charstring.getPath();
/*     */     }
/* 339 */     if (this.isEmbedded && this.t1Font instanceof CFFType1Font)
/*     */     {
/* 341 */       return ((CFFType1Font)this.t1Font).getType2CharString(cid).getPath();
/*     */     }
/*     */ 
/*     */     
/* 345 */     return this.t1Font.getPath(getGlyphName(code));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(int code) throws IOException {
/* 352 */     int cid = codeToCID(code);
/* 353 */     Type2CharString charstring = getType2CharString(cid);
/* 354 */     if (charstring != null)
/*     */     {
/* 356 */       return (charstring.getGID() != 0);
/*     */     }
/* 358 */     if (this.isEmbedded && this.t1Font instanceof CFFType1Font)
/*     */     {
/* 360 */       return (((CFFType1Font)this.t1Font).getType2CharString(cid).getGID() != 0);
/*     */     }
/*     */ 
/*     */     
/* 364 */     return this.t1Font.hasGlyph(getGlyphName(code));
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
/*     */   public int codeToCID(int code) {
/* 377 */     return this.parent.getCMap().toCID(code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int codeToGID(int code) {
/* 383 */     int cid = codeToCID(code);
/* 384 */     if (this.cidFont != null)
/*     */     {
/*     */ 
/*     */       
/* 388 */       return this.cidFont.getCharset().getGIDForCID(cid);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 393 */     return cid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encode(int unicode) {
/* 402 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/*     */     float width;
/* 408 */     int cid = codeToCID(code);
/*     */     
/* 410 */     if (this.cidFont != null) {
/*     */       
/* 412 */       width = getType2CharString(cid).getWidth();
/*     */     }
/* 414 */     else if (this.isEmbedded && this.t1Font instanceof CFFType1Font) {
/*     */       
/* 416 */       width = ((CFFType1Font)this.t1Font).getType2CharString(cid).getWidth();
/*     */     }
/*     */     else {
/*     */       
/* 420 */       width = this.t1Font.getWidth(getGlyphName(code));
/*     */     } 
/*     */     
/* 423 */     Point2D p = new Point2D.Float(width, 0.0F);
/* 424 */     this.fontMatrixTransform.transform(p, p);
/* 425 */     return (float)p.getX();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 431 */     return this.isEmbedded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 437 */     return this.isDamaged;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 443 */     int cid = codeToCID(code);
/*     */     
/* 445 */     float height = 0.0F;
/* 446 */     if (!this.glyphHeights.containsKey(Integer.valueOf(cid))) {
/*     */       
/* 448 */       height = (float)getType2CharString(cid).getBounds().getHeight();
/* 449 */       this.glyphHeights.put(Integer.valueOf(cid), Float.valueOf(height));
/*     */     } 
/* 451 */     return height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAverageFontWidth() {
/* 457 */     if (this.avgWidth == null)
/*     */     {
/* 459 */       this.avgWidth = Float.valueOf(getAverageCharacterWidth());
/*     */     }
/* 461 */     return this.avgWidth.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getAverageCharacterWidth() {
/* 468 */     return 500.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDCIDFontType0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */