/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.ResourceCache;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDType3Font
/*     */   extends PDSimpleFont
/*     */ {
/*  50 */   private static final Log LOG = LogFactory.getLog(PDType3Font.class);
/*     */ 
/*     */   
/*     */   private PDResources resources;
/*     */   
/*     */   private COSDictionary charProcs;
/*     */   
/*     */   private Matrix fontMatrix;
/*     */   
/*     */   private BoundingBox fontBBox;
/*     */   
/*     */   private final ResourceCache resourceCache;
/*     */ 
/*     */   
/*     */   public PDType3Font(COSDictionary fontDictionary) throws IOException {
/*  65 */     this(fontDictionary, (ResourceCache)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDType3Font(COSDictionary fontDictionary, ResourceCache resourceCache) throws IOException {
/*  76 */     super(fontDictionary);
/*  77 */     this.resourceCache = resourceCache;
/*  78 */     readEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  84 */     return this.dict.getNameAsString(COSName.NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void readEncoding() throws IOException {
/*  90 */     COSBase encodingBase = this.dict.getDictionaryObject(COSName.ENCODING);
/*  91 */     if (encodingBase instanceof COSName) {
/*     */       
/*  93 */       COSName encodingName = (COSName)encodingBase;
/*  94 */       this.encoding = Encoding.getInstance(encodingName);
/*  95 */       if (this.encoding == null)
/*     */       {
/*  97 */         LOG.warn("Unknown encoding: " + encodingName.getName());
/*     */       }
/*     */     }
/* 100 */     else if (encodingBase instanceof COSDictionary) {
/*     */       
/* 102 */       this.encoding = (Encoding)new DictionaryEncoding((COSDictionary)encodingBase);
/*     */     } 
/* 104 */     this.glyphList = GlyphList.getAdobeGlyphList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Encoding readEncodingFromFont() throws IOException {
/* 111 */     throw new UnsupportedOperationException("not supported for Type 3 fonts");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Boolean isFontSymbolic() {
/* 117 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/* 124 */     throw new UnsupportedOperationException("not supported for Type 3 fonts");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) throws IOException {
/* 130 */     COSBase base = getCharProcs().getDictionaryObject(COSName.getPDFName(name));
/* 131 */     return base instanceof COSStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontBoxFont getFontBoxFont() {
/* 138 */     throw new UnsupportedOperationException("not supported for Type 3 fonts");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getDisplacement(int code) throws IOException {
/* 144 */     return getFontMatrix().transform(new Vector(getWidth(code), 0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(int code) throws IOException {
/* 150 */     int firstChar = this.dict.getInt(COSName.FIRST_CHAR, -1);
/* 151 */     int lastChar = this.dict.getInt(COSName.LAST_CHAR, -1);
/* 152 */     if (!getWidths().isEmpty() && code >= firstChar && code <= lastChar) {
/*     */       
/* 154 */       Float w = getWidths().get(code - firstChar);
/* 155 */       return (w == null) ? 0.0F : w.floatValue();
/*     */     } 
/*     */ 
/*     */     
/* 159 */     PDFontDescriptor fd = getFontDescriptor();
/* 160 */     if (fd != null)
/*     */     {
/* 162 */       return fd.getMissingWidth();
/*     */     }
/*     */ 
/*     */     
/* 166 */     return getWidthFromFont(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidthFromFont(int code) throws IOException {
/* 174 */     PDType3CharProc charProc = getCharProc(code);
/* 175 */     if (charProc == null || charProc.getContentStream() == null || charProc
/* 176 */       .getContentStream().getLength() == 0)
/*     */     {
/* 178 */       return 0.0F;
/*     */     }
/* 180 */     return charProc.getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmbedded() {
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight(int code) throws IOException {
/* 192 */     PDFontDescriptor desc = getFontDescriptor();
/* 193 */     if (desc != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 198 */       PDRectangle bbox = desc.getFontBoundingBox();
/* 199 */       float retval = 0.0F;
/* 200 */       if (bbox != null)
/*     */       {
/* 202 */         retval = bbox.getHeight() / 2.0F;
/*     */       }
/* 204 */       if (retval == 0.0F)
/*     */       {
/* 206 */         retval = desc.getCapHeight();
/*     */       }
/* 208 */       if (retval == 0.0F)
/*     */       {
/* 210 */         retval = desc.getAscent();
/*     */       }
/* 212 */       if (retval == 0.0F) {
/*     */         
/* 214 */         retval = desc.getXHeight();
/* 215 */         if (retval > 0.0F)
/*     */         {
/* 217 */           retval -= desc.getDescent();
/*     */         }
/*     */       } 
/* 220 */       return retval;
/*     */     } 
/* 222 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] encode(int unicode) throws IOException {
/* 228 */     throw new UnsupportedOperationException("Not implemented: Type3");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCode(InputStream in) throws IOException {
/* 234 */     return in.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getFontMatrix() {
/* 240 */     if (this.fontMatrix == null) {
/*     */       
/* 242 */       COSBase base = this.dict.getDictionaryObject(COSName.FONT_MATRIX);
/* 243 */       if (base instanceof COSArray) {
/*     */         
/* 245 */         this.fontMatrix = new Matrix((COSArray)base);
/*     */       }
/*     */       else {
/*     */         
/* 249 */         return super.getFontMatrix();
/*     */       } 
/*     */     } 
/* 252 */     return this.fontMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged() {
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getResources() {
/* 269 */     if (this.resources == null) {
/*     */       
/* 271 */       COSBase base = this.dict.getDictionaryObject(COSName.RESOURCES);
/* 272 */       if (base instanceof COSDictionary)
/*     */       {
/* 274 */         this.resources = new PDResources((COSDictionary)base, this.resourceCache);
/*     */       }
/*     */     } 
/* 277 */     return this.resources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getFontBBox() {
/* 287 */     COSBase base = this.dict.getDictionaryObject(COSName.FONT_BBOX);
/* 288 */     PDRectangle retval = null;
/* 289 */     if (base instanceof COSArray)
/*     */     {
/* 291 */       retval = new PDRectangle((COSArray)base);
/*     */     }
/* 293 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() {
/* 299 */     if (this.fontBBox == null)
/*     */     {
/* 301 */       this.fontBBox = generateBoundingBox();
/*     */     }
/* 303 */     return this.fontBBox;
/*     */   }
/*     */ 
/*     */   
/*     */   private BoundingBox generateBoundingBox() {
/* 308 */     PDRectangle rect = getFontBBox();
/* 309 */     if (rect.getLowerLeftX() == 0.0F && rect.getLowerLeftY() == 0.0F && rect
/* 310 */       .getUpperRightX() == 0.0F && rect.getUpperRightY() == 0.0F) {
/*     */ 
/*     */       
/* 313 */       COSDictionary cp = getCharProcs();
/* 314 */       for (COSName name : cp.keySet()) {
/*     */         
/* 316 */         COSBase base = cp.getDictionaryObject(name);
/* 317 */         if (base instanceof COSStream) {
/*     */           
/* 319 */           PDType3CharProc charProc = new PDType3CharProc(this, (COSStream)base);
/*     */           
/*     */           try {
/* 322 */             PDRectangle glyphBBox = charProc.getGlyphBBox();
/* 323 */             if (glyphBBox == null) {
/*     */               continue;
/*     */             }
/*     */             
/* 327 */             rect.setLowerLeftX(Math.min(rect.getLowerLeftX(), glyphBBox.getLowerLeftX()));
/* 328 */             rect.setLowerLeftY(Math.min(rect.getLowerLeftY(), glyphBBox.getLowerLeftY()));
/* 329 */             rect.setUpperRightX(Math.max(rect.getUpperRightX(), glyphBBox.getUpperRightX()));
/* 330 */             rect.setUpperRightY(Math.max(rect.getUpperRightY(), glyphBBox.getUpperRightY()));
/*     */           }
/* 332 */           catch (IOException iOException) {}
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 339 */     return new BoundingBox(rect.getLowerLeftX(), rect.getLowerLeftY(), rect
/* 340 */         .getUpperRightX(), rect.getUpperRightY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCharProcs() {
/* 350 */     if (this.charProcs == null)
/*     */     {
/* 352 */       this.charProcs = (COSDictionary)this.dict.getDictionaryObject(COSName.CHAR_PROCS);
/*     */     }
/* 354 */     return this.charProcs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDType3CharProc getCharProc(int code) {
/* 365 */     String name = getEncoding().getName(code);
/* 366 */     COSBase base = getCharProcs().getDictionaryObject(COSName.getPDFName(name));
/* 367 */     if (base instanceof COSStream)
/*     */     {
/* 369 */       return new PDType3CharProc(this, (COSStream)base);
/*     */     }
/* 371 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType3Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */