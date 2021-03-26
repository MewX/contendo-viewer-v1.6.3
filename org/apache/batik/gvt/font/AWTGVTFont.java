/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphMetrics;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.CharacterIterator;
/*     */ import java.text.StringCharacterIterator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.gvt.text.ArabicTextHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AWTGVTFont
/*     */   implements GVTFont
/*     */ {
/*     */   protected Font awtFont;
/*     */   protected double size;
/*     */   protected double scale;
/*     */   public static final float FONT_SIZE = 48.0F;
/*     */   
/*     */   public AWTGVTFont(Font font) {
/*  57 */     this.size = font.getSize2D();
/*  58 */     this.awtFont = font.deriveFont(48.0F);
/*  59 */     this.scale = this.size / this.awtFont.getSize2D();
/*  60 */     initializeFontCache(this.awtFont);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTGVTFont(Font font, double scale) {
/*  70 */     this.size = font.getSize2D() * scale;
/*  71 */     this.awtFont = font.deriveFont(48.0F);
/*  72 */     this.scale = this.size / this.awtFont.getSize2D();
/*  73 */     initializeFontCache(this.awtFont);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTGVTFont(Map<TextAttribute, Float> attributes) {
/*  82 */     Float sz = (Float)attributes.get(TextAttribute.SIZE);
/*  83 */     if (sz != null) {
/*  84 */       this.size = sz.floatValue();
/*  85 */       attributes.put(TextAttribute.SIZE, Float.valueOf(48.0F));
/*  86 */       this.awtFont = new Font((Map)attributes);
/*     */     } else {
/*  88 */       this.awtFont = new Font((Map)attributes);
/*  89 */       this.size = this.awtFont.getSize2D();
/*     */     } 
/*  91 */     this.scale = this.size / this.awtFont.getSize2D();
/*  92 */     initializeFontCache(this.awtFont);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTGVTFont(String name, int style, int size) {
/* 103 */     this.awtFont = new Font(name, style, 48);
/* 104 */     this.size = size;
/* 105 */     this.scale = (size / this.awtFont.getSize2D());
/* 106 */     initializeFontCache(this.awtFont);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDisplay(char c) {
/* 116 */     return this.awtFont.canDisplay(c);
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
/*     */   public int canDisplayUpTo(char[] text, int start, int limit) {
/* 131 */     return this.awtFont.canDisplayUpTo(text, start, limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int canDisplayUpTo(CharacterIterator iter, int start, int limit) {
/* 139 */     return this.awtFont.canDisplayUpTo(iter, start, limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int canDisplayUpTo(String str) {
/* 146 */     return this.awtFont.canDisplayUpTo(str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, char[] chars) {
/* 156 */     StringCharacterIterator sci = new StringCharacterIterator(new String(chars));
/*     */     
/* 158 */     GlyphVector gv = this.awtFont.createGlyphVector(frc, chars);
/* 159 */     return new AWTGVTGlyphVector(gv, this, this.scale, sci);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator ci) {
/* 169 */     if (ci instanceof AttributedCharacterIterator) {
/* 170 */       AttributedCharacterIterator aci = (AttributedCharacterIterator)ci;
/* 171 */       if (ArabicTextHandler.containsArabic(aci)) {
/* 172 */         String str = ArabicTextHandler.createSubstituteString(aci);
/*     */         
/* 174 */         return createGlyphVector(frc, str);
/*     */       } 
/*     */     } 
/* 177 */     GlyphVector gv = this.awtFont.createGlyphVector(frc, ci);
/* 178 */     return new AWTGVTGlyphVector(gv, this, this.scale, ci);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes, CharacterIterator ci) {
/* 188 */     return new AWTGVTGlyphVector(this.awtFont.createGlyphVector(frc, glyphCodes), this, this.scale, ci);
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
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, String str) {
/* 200 */     StringCharacterIterator sci = new StringCharacterIterator(str);
/*     */     
/* 202 */     return new AWTGVTGlyphVector(this.awtFont.createGlyphVector(frc, str), this, this.scale, sci);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont deriveFont(float size) {
/* 211 */     return new AWTGVTFont(this.awtFont, size / this.size);
/*     */   }
/*     */   
/*     */   public String getFamilyName() {
/* 215 */     return this.awtFont.getFamily();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTLineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, FontRenderContext frc) {
/* 225 */     return new GVTLineMetrics(this.awtFont.getLineMetrics(chars, beginIndex, limit, frc), (float)this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTLineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc) {
/* 236 */     return new GVTLineMetrics(this.awtFont.getLineMetrics(ci, beginIndex, limit, frc), (float)this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTLineMetrics getLineMetrics(String str, FontRenderContext frc) {
/* 245 */     return new GVTLineMetrics(this.awtFont.getLineMetrics(str, frc), (float)this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTLineMetrics getLineMetrics(String str, int beginIndex, int limit, FontRenderContext frc) {
/* 255 */     return new GVTLineMetrics(this.awtFont.getLineMetrics(str, beginIndex, limit, frc), (float)this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 263 */     return (float)this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHKern(int glyphCode1, int glyphCode2) {
/* 270 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVKern(int glyphCode1, int glyphCode2) {
/* 277 */     return 0.0F;
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
/*     */   public static AWTGlyphGeometryCache.Value getGlyphGeometry(AWTGVTFont font, char c, GlyphVector gv, int glyphIndex, Point2D glyphPos) {
/* 296 */     AWTGlyphGeometryCache glyphCache = (AWTGlyphGeometryCache)fontCache.get(font.awtFont);
/*     */ 
/*     */     
/* 299 */     AWTGlyphGeometryCache.Value v = glyphCache.get(c);
/* 300 */     if (v == null) {
/* 301 */       Shape outline = gv.getGlyphOutline(glyphIndex);
/* 302 */       GlyphMetrics metrics = gv.getGlyphMetrics(glyphIndex);
/* 303 */       Rectangle2D gmB = metrics.getBounds2D();
/* 304 */       if (AWTGVTGlyphVector.outlinesPositioned()) {
/* 305 */         AffineTransform tr = AffineTransform.getTranslateInstance(-glyphPos.getX(), -glyphPos.getY());
/*     */         
/* 307 */         outline = tr.createTransformedShape(outline);
/*     */       } 
/* 309 */       v = new AWTGlyphGeometryCache.Value(outline, gmB);
/*     */       
/* 311 */       glyphCache.put(c, v);
/*     */     } 
/* 313 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   static Map fontCache = new HashMap<Object, Object>(11);
/*     */   
/*     */   static void initializeFontCache(Font awtFont) {
/* 323 */     if (!fontCache.containsKey(awtFont)) {
/* 324 */       fontCache.put(awtFont, new AWTGlyphGeometryCache());
/*     */     }
/*     */   }
/*     */   
/*     */   static void putAWTGVTFont(AWTGVTFont font) {
/* 329 */     fontCache.put(font.awtFont, font);
/*     */   }
/*     */   
/*     */   static AWTGVTFont getAWTGVTFont(Font awtFont) {
/* 333 */     return (AWTGVTFont)fontCache.get(awtFont);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/AWTGVTFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */