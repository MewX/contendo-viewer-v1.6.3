/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.MacRomanEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
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
/*     */ public abstract class PDSimpleFont
/*     */   extends PDFont
/*     */ {
/*  44 */   private static final Log LOG = LogFactory.getLog(PDSimpleFont.class);
/*     */   
/*     */   protected Encoding encoding;
/*     */   protected GlyphList glyphList;
/*     */   private Boolean isSymbolic;
/*  49 */   private final Set<Integer> noUnicode = new HashSet<Integer>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDSimpleFont() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDSimpleFont(String baseFont) {
/*  64 */     super(baseFont);
/*     */ 
/*     */     
/*  67 */     if ("ZapfDingbats".equals(baseFont)) {
/*     */       
/*  69 */       this.glyphList = GlyphList.getZapfDingbats();
/*     */     }
/*     */     else {
/*     */       
/*  73 */       this.glyphList = GlyphList.getAdobeGlyphList();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDSimpleFont(COSDictionary fontDictionary) throws IOException {
/*  84 */     super(fontDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEncoding() throws IOException {
/*  95 */     COSBase encoding = this.dict.getDictionaryObject(COSName.ENCODING);
/*  96 */     if (encoding != null) {
/*     */       
/*  98 */       if (encoding instanceof COSName) {
/*     */         
/* 100 */         COSName encodingName = (COSName)encoding;
/* 101 */         this.encoding = Encoding.getInstance(encodingName);
/* 102 */         if (this.encoding == null)
/*     */         {
/* 104 */           LOG.warn("Unknown encoding: " + encodingName.getName());
/* 105 */           this.encoding = readEncodingFromFont();
/*     */         }
/*     */       
/* 108 */       } else if (encoding instanceof COSDictionary) {
/*     */         
/* 110 */         COSDictionary encodingDict = (COSDictionary)encoding;
/* 111 */         Encoding builtIn = null;
/* 112 */         Boolean symbolic = getSymbolicFlag();
/* 113 */         boolean isFlaggedAsSymbolic = (symbolic != null && symbolic.booleanValue());
/*     */         
/* 115 */         COSName baseEncoding = encodingDict.getCOSName(COSName.BASE_ENCODING);
/*     */ 
/*     */         
/* 118 */         boolean hasValidBaseEncoding = (baseEncoding != null && Encoding.getInstance(baseEncoding) != null);
/*     */         
/* 120 */         if (!hasValidBaseEncoding && isFlaggedAsSymbolic)
/*     */         {
/* 122 */           builtIn = readEncodingFromFont();
/*     */         }
/*     */         
/* 125 */         if (symbolic == null)
/*     */         {
/* 127 */           symbolic = Boolean.valueOf(false);
/*     */         }
/* 129 */         this.encoding = (Encoding)new DictionaryEncoding(encodingDict, !symbolic.booleanValue(), builtIn);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 134 */       this.encoding = readEncodingFromFont();
/*     */     } 
/*     */ 
/*     */     
/* 138 */     String standard14Name = Standard14Fonts.getMappedFontName(getName());
/*     */ 
/*     */     
/* 141 */     if ("ZapfDingbats".equals(standard14Name)) {
/*     */       
/* 143 */       this.glyphList = GlyphList.getZapfDingbats();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 148 */       this.glyphList = GlyphList.getAdobeGlyphList();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Encoding readEncodingFromFont() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encoding getEncoding() {
/* 164 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphList getGlyphList() {
/* 172 */     return this.glyphList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isSymbolic() {
/* 181 */     if (this.isSymbolic == null) {
/*     */       
/* 183 */       Boolean result = isFontSymbolic();
/* 184 */       if (result != null) {
/*     */         
/* 186 */         this.isSymbolic = result;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 191 */         this.isSymbolic = Boolean.valueOf(true);
/*     */       } 
/*     */     } 
/* 194 */     return this.isSymbolic.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Boolean isFontSymbolic() {
/* 203 */     Boolean result = getSymbolicFlag();
/* 204 */     if (result != null)
/*     */     {
/* 206 */       return result;
/*     */     }
/* 208 */     if (isStandard14()) {
/*     */       
/* 210 */       String mappedName = Standard14Fonts.getMappedFontName(getName());
/* 211 */       return Boolean.valueOf((mappedName.equals("Symbol") || mappedName.equals("ZapfDingbats")));
/*     */     } 
/*     */ 
/*     */     
/* 215 */     if (this.encoding == null) {
/*     */ 
/*     */       
/* 218 */       if (!(this instanceof PDTrueTypeFont))
/*     */       {
/* 220 */         throw new IllegalStateException("PDFBox bug: encoding should not be null!");
/*     */       }
/*     */ 
/*     */       
/* 224 */       return Boolean.valueOf(true);
/*     */     } 
/* 226 */     if (this.encoding instanceof WinAnsiEncoding || this.encoding instanceof MacRomanEncoding || this.encoding instanceof StandardEncoding)
/*     */     {
/*     */ 
/*     */       
/* 230 */       return Boolean.valueOf(false);
/*     */     }
/* 232 */     if (this.encoding instanceof DictionaryEncoding) {
/*     */ 
/*     */       
/* 235 */       for (String name : ((DictionaryEncoding)this.encoding).getDifferences().values()) {
/*     */         
/* 237 */         if (".notdef".equals(name)) {
/*     */           continue;
/*     */         }
/*     */         
/* 241 */         if (!WinAnsiEncoding.INSTANCE.contains(name) || 
/* 242 */           !MacRomanEncoding.INSTANCE.contains(name) || 
/* 243 */           !StandardEncoding.INSTANCE.contains(name))
/*     */         {
/* 245 */           return Boolean.valueOf(true);
/*     */         }
/*     */       } 
/*     */       
/* 249 */       return Boolean.valueOf(false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 254 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Boolean getSymbolicFlag() {
/* 265 */     if (getFontDescriptor() != null)
/*     */     {
/*     */       
/* 268 */       return Boolean.valueOf(getFontDescriptor().isSymbolic());
/*     */     }
/* 270 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(int code) throws IOException {
/* 276 */     return toUnicode(code, GlyphList.getAdobeGlyphList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(int code, GlyphList customGlyphList) throws IOException {
/*     */     GlyphList unicodeGlyphList;
/* 285 */     if (this.glyphList == GlyphList.getAdobeGlyphList()) {
/*     */       
/* 287 */       unicodeGlyphList = customGlyphList;
/*     */     }
/*     */     else {
/*     */       
/* 291 */       unicodeGlyphList = this.glyphList;
/*     */     } 
/*     */ 
/*     */     
/* 295 */     String unicode = super.toUnicode(code);
/* 296 */     if (unicode != null)
/*     */     {
/* 298 */       return unicode;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     String name = null;
/* 308 */     if (this.encoding != null) {
/*     */       
/* 310 */       name = this.encoding.getName(code);
/* 311 */       unicode = unicodeGlyphList.toUnicode(name);
/* 312 */       if (unicode != null)
/*     */       {
/* 314 */         return unicode;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 319 */     if (LOG.isWarnEnabled() && !this.noUnicode.contains(Integer.valueOf(code))) {
/*     */ 
/*     */       
/* 322 */       this.noUnicode.add(Integer.valueOf(code));
/* 323 */       if (name != null) {
/*     */         
/* 325 */         LOG.warn("No Unicode mapping for " + name + " (" + code + ") in font " + 
/* 326 */             getName());
/*     */       }
/*     */       else {
/*     */         
/* 330 */         LOG.warn("No Unicode mapping for character code " + code + " in font " + 
/* 331 */             getName());
/*     */       } 
/*     */     } 
/*     */     
/* 335 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVertical() {
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final float getStandard14Width(int code) {
/* 347 */     if (getStandard14AFM() != null) {
/*     */       
/* 349 */       String nameInAFM = getEncoding().getName(code);
/*     */ 
/*     */       
/* 352 */       if (".notdef".equals(nameInAFM))
/*     */       {
/* 354 */         return 250.0F;
/*     */       }
/*     */       
/* 357 */       return getStandard14AFM().getCharacterWidth(nameInAFM);
/*     */     } 
/* 359 */     throw new IllegalStateException("No AFM");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandard14() {
/* 367 */     if (getEncoding() instanceof DictionaryEncoding) {
/*     */       
/* 369 */       DictionaryEncoding dictionary = (DictionaryEncoding)getEncoding();
/* 370 */       if (dictionary.getDifferences().size() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 374 */         Encoding baseEncoding = dictionary.getBaseEncoding();
/* 375 */         for (Map.Entry<Integer, String> entry : (Iterable<Map.Entry<Integer, String>>)dictionary.getDifferences().entrySet()) {
/*     */           
/* 377 */           if (!((String)entry.getValue()).equals(baseEncoding.getName(((Integer)entry.getKey()).intValue())))
/*     */           {
/* 379 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 384 */     return super.isStandard14();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract GeneralPath getPath(String paramString) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean hasGlyph(String paramString) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract FontBoxFont getFontBoxFont();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSubset(int codePoint) {
/* 411 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subset() throws IOException {
/* 418 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean willBeSubset() {
/* 424 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExplicitWidth(int code) throws IOException {
/* 430 */     if (this.dict.containsKey(COSName.WIDTHS)) {
/*     */       
/* 432 */       int firstChar = this.dict.getInt(COSName.FIRST_CHAR, -1);
/* 433 */       if (code >= firstChar && code - firstChar < getWidths().size())
/*     */       {
/* 435 */         return true;
/*     */       }
/*     */     } 
/* 438 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDSimpleFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */