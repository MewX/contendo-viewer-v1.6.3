/*     */ package org.apache.batik.ext.awt.font;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.GlyphMetrics;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.ext.awt.geom.PathLength;
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
/*     */ public class TextPathLayout
/*     */ {
/*     */   public static final int ALIGN_START = 0;
/*     */   public static final int ALIGN_MIDDLE = 1;
/*     */   public static final int ALIGN_END = 2;
/*     */   public static final int ADJUST_SPACING = 0;
/*     */   public static final int ADJUST_GLYPHS = 1;
/*     */   
/*     */   public static Shape layoutGlyphVector(GlyphVector glyphs, Shape path, int align, float startOffset, float textLength, int lengthAdjustMode) {
/*  98 */     GeneralPath newPath = new GeneralPath();
/*  99 */     PathLength pl = new PathLength(path);
/* 100 */     float pathLength = pl.lengthOfPath();
/*     */     
/* 102 */     if (glyphs == null) {
/* 103 */       return newPath;
/*     */     }
/* 105 */     float glyphsLength = (float)glyphs.getVisualBounds().getWidth();
/*     */ 
/*     */     
/* 108 */     if (path == null || glyphs.getNumGlyphs() == 0 || pl.lengthOfPath() == 0.0F || glyphsLength == 0.0F)
/*     */     {
/*     */ 
/*     */       
/* 112 */       return newPath;
/*     */     }
/*     */ 
/*     */     
/* 116 */     float lengthRatio = textLength / glyphsLength;
/*     */ 
/*     */     
/* 119 */     float currentPosition = startOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (align == 2) {
/* 130 */       currentPosition += pathLength - textLength;
/* 131 */     } else if (align == 1) {
/* 132 */       currentPosition += (pathLength - textLength) / 2.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 137 */     for (int i = 0; i < glyphs.getNumGlyphs(); i++) {
/*     */       
/* 139 */       GlyphMetrics gm = glyphs.getGlyphMetrics(i);
/*     */       
/* 141 */       float charAdvance = gm.getAdvance();
/*     */       
/* 143 */       Shape glyph = glyphs.getGlyphOutline(i);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 148 */       if (lengthAdjustMode == 1) {
/* 149 */         AffineTransform scale = AffineTransform.getScaleInstance(lengthRatio, 1.0D);
/* 150 */         glyph = scale.createTransformedShape(glyph);
/*     */ 
/*     */         
/* 153 */         charAdvance *= lengthRatio;
/*     */       } 
/*     */       
/* 156 */       float glyphWidth = (float)glyph.getBounds2D().getWidth();
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
/* 167 */       float charMidPos = currentPosition + glyphWidth / 2.0F;
/*     */ 
/*     */       
/* 170 */       Point2D charMidPoint = pl.pointAtLength(charMidPos);
/*     */ 
/*     */ 
/*     */       
/* 174 */       if (charMidPoint != null) {
/*     */ 
/*     */         
/* 177 */         float angle = pl.angleAtLength(charMidPos);
/*     */ 
/*     */         
/* 180 */         AffineTransform glyphTrans = new AffineTransform();
/*     */ 
/*     */         
/* 183 */         glyphTrans.translate(charMidPoint.getX(), charMidPoint.getY());
/*     */ 
/*     */         
/* 186 */         glyphTrans.rotate(angle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 192 */         glyphTrans.translate((charAdvance / -2.0F), 0.0D);
/*     */ 
/*     */         
/* 195 */         glyph = glyphTrans.createTransformedShape(glyph);
/* 196 */         newPath.append(glyph, false);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       if (lengthAdjustMode == 0) {
/* 204 */         currentPosition += charAdvance * lengthRatio;
/*     */       } else {
/* 206 */         currentPosition += charAdvance;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 211 */     return newPath;
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
/*     */   public static Shape layoutGlyphVector(GlyphVector glyphs, Shape path, int align) {
/* 228 */     return layoutGlyphVector(glyphs, path, align, 0.0F, (float)glyphs.getVisualBounds().getWidth(), 0);
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
/*     */   public static Shape layoutGlyphVector(GlyphVector glyphs, Shape path) {
/* 245 */     return layoutGlyphVector(glyphs, path, 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/font/TextPathLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */