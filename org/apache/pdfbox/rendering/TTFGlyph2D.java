/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.ttf.HeaderTable;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDCIDFontType2;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType0Font;
/*     */ import org.apache.pdfbox.pdmodel.font.PDVectorFont;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TTFGlyph2D
/*     */   implements Glyph2D
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(TTFGlyph2D.class);
/*     */   
/*     */   private final PDFont font;
/*     */   private final TrueTypeFont ttf;
/*     */   private PDVectorFont vectorFont;
/*  46 */   private float scale = 1.0F;
/*     */   private boolean hasScaling;
/*  48 */   private final Map<Integer, GeneralPath> glyphs = new HashMap<Integer, GeneralPath>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean isCIDFont;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TTFGlyph2D(PDTrueTypeFont ttfFont) throws IOException {
/*  58 */     this(ttfFont.getTrueTypeFont(), (PDFont)ttfFont, false);
/*  59 */     this.vectorFont = (PDVectorFont)ttfFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TTFGlyph2D(PDType0Font type0Font) throws IOException {
/*  69 */     this(((PDCIDFontType2)type0Font.getDescendantFont()).getTrueTypeFont(), (PDFont)type0Font, true);
/*  70 */     this.vectorFont = (PDVectorFont)type0Font;
/*     */   }
/*     */ 
/*     */   
/*     */   private TTFGlyph2D(TrueTypeFont ttf, PDFont font, boolean isCIDFont) throws IOException {
/*  75 */     this.font = font;
/*  76 */     this.ttf = ttf;
/*  77 */     this.isCIDFont = isCIDFont;
/*     */     
/*  79 */     HeaderTable header = this.ttf.getHeader();
/*  80 */     if (header != null && header.getUnitsPerEm() != 1000) {
/*     */ 
/*     */ 
/*     */       
/*  84 */       this.scale = 1000.0F / header.getUnitsPerEm();
/*  85 */       this.hasScaling = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPathForCharacterCode(int code) throws IOException {
/*  92 */     int gid = getGIDForCharacterCode(code);
/*  93 */     return getPathForGID(gid, code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getGIDForCharacterCode(int code) throws IOException {
/*  99 */     if (this.isCIDFont)
/*     */     {
/* 101 */       return ((PDType0Font)this.font).codeToGID(code);
/*     */     }
/*     */ 
/*     */     
/* 105 */     return ((PDTrueTypeFont)this.font).codeToGID(code);
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
/*     */   public GeneralPath getPathForGID(int gid, int code) throws IOException {
/* 119 */     if (gid == 0 && !this.isCIDFont && code == 10 && this.font.isStandard14()) {
/*     */ 
/*     */ 
/*     */       
/* 123 */       LOG.warn("No glyph for code " + code + " in font " + this.font.getName());
/* 124 */       return new GeneralPath();
/*     */     } 
/* 126 */     GeneralPath glyphPath = this.glyphs.get(Integer.valueOf(gid));
/* 127 */     if (glyphPath == null) {
/*     */       
/* 129 */       if (gid == 0 || gid >= this.ttf.getMaximumProfile().getNumGlyphs())
/*     */       {
/* 131 */         if (this.isCIDFont) {
/*     */           
/* 133 */           int cid = ((PDType0Font)this.font).codeToCID(code);
/* 134 */           String cidHex = String.format("%04x", new Object[] { Integer.valueOf(cid) });
/* 135 */           LOG.warn("No glyph for code " + code + " (CID " + cidHex + ") in font " + this.font
/* 136 */               .getName());
/*     */         }
/*     */         else {
/*     */           
/* 140 */           LOG.warn("No glyph for " + code + " in font " + this.font.getName());
/*     */         } 
/*     */       }
/*     */       
/* 144 */       GeneralPath glyph = this.vectorFont.getPath(code);
/*     */ 
/*     */       
/* 147 */       if (gid == 0 && !this.font.isEmbedded() && !this.font.isStandard14())
/*     */       {
/* 149 */         glyph = null;
/*     */       }
/*     */       
/* 152 */       if (glyph == null) {
/*     */ 
/*     */         
/* 155 */         glyphPath = new GeneralPath();
/* 156 */         this.glyphs.put(Integer.valueOf(gid), glyphPath);
/*     */       }
/*     */       else {
/*     */         
/* 160 */         glyphPath = glyph;
/* 161 */         if (this.hasScaling) {
/*     */           
/* 163 */           AffineTransform atScale = AffineTransform.getScaleInstance(this.scale, this.scale);
/* 164 */           glyphPath.transform(atScale);
/*     */         } 
/* 166 */         this.glyphs.put(Integer.valueOf(gid), glyphPath);
/*     */       } 
/*     */     } 
/*     */     
/* 170 */     return (GeneralPath)glyphPath.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 176 */     this.glyphs.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/TTFGlyph2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */