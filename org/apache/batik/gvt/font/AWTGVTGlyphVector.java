/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphJustificationInfo;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.CharacterIterator;
/*     */ import org.apache.batik.gvt.text.ArabicTextHandler;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ import org.apache.batik.util.Platform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AWTGVTGlyphVector
/*     */   implements GVTGlyphVector
/*     */ {
/*  51 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*     */   
/*     */   private GlyphVector awtGlyphVector;
/*     */   
/*     */   private AWTGVTFont gvtFont;
/*     */   
/*     */   private CharacterIterator ci;
/*     */   
/*     */   private Point2D[] defaultGlyphPositions;
/*     */   
/*     */   private Point2D.Float[] glyphPositions;
/*     */   
/*     */   private AffineTransform[] glyphTransforms;
/*     */   
/*     */   private Shape[] glyphOutlines;
/*     */   
/*     */   private Shape[] glyphVisualBounds;
/*     */   
/*     */   private Shape[] glyphLogicalBounds;
/*     */   
/*     */   private boolean[] glyphVisible;
/*     */   
/*     */   private GVTGlyphMetrics[] glyphMetrics;
/*     */   
/*     */   private GeneralPath outline;
/*     */   
/*     */   private Rectangle2D visualBounds;
/*     */   
/*     */   private Rectangle2D logicalBounds;
/*     */   
/*     */   private Rectangle2D bounds2D;
/*     */   
/*     */   private double scaleFactor;
/*     */   
/*     */   private float ascent;
/*     */   
/*     */   private float descent;
/*     */   
/*     */   private TextPaintInfo cacheTPI;
/*     */   
/*     */   private static final boolean outlinesPositioned;
/*     */   
/*     */   private static final boolean drawGlyphVectorWorks;
/*     */   
/*     */   private static final boolean glyphVectorTransformWorks;
/*     */ 
/*     */   
/*     */   public AWTGVTGlyphVector(GlyphVector glyphVector, AWTGVTFont font, double scaleFactor, CharacterIterator ci) {
/*  99 */     this.awtGlyphVector = glyphVector;
/* 100 */     this.gvtFont = font;
/* 101 */     this.scaleFactor = scaleFactor;
/* 102 */     this.ci = ci;
/*     */     
/* 104 */     GVTLineMetrics lineMetrics = this.gvtFont.getLineMetrics("By", this.awtGlyphVector.getFontRenderContext());
/*     */ 
/*     */     
/* 107 */     this.ascent = lineMetrics.getAscent();
/* 108 */     this.descent = lineMetrics.getDescent();
/*     */     
/* 110 */     this.outline = null;
/* 111 */     this.visualBounds = null;
/* 112 */     this.logicalBounds = null;
/* 113 */     this.bounds2D = null;
/* 114 */     int numGlyphs = glyphVector.getNumGlyphs();
/* 115 */     this.glyphPositions = new Point2D.Float[numGlyphs + 1];
/* 116 */     this.glyphTransforms = new AffineTransform[numGlyphs];
/* 117 */     this.glyphOutlines = new Shape[numGlyphs];
/* 118 */     this.glyphVisualBounds = new Shape[numGlyphs];
/* 119 */     this.glyphLogicalBounds = new Shape[numGlyphs];
/* 120 */     this.glyphVisible = new boolean[numGlyphs];
/* 121 */     this.glyphMetrics = new GVTGlyphMetrics[numGlyphs];
/*     */     
/* 123 */     for (int i = 0; i < numGlyphs; i++) {
/* 124 */       this.glyphVisible[i] = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont getFont() {
/* 132 */     return this.gvtFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/* 139 */     return this.awtGlyphVector.getFontRenderContext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphCode(int glyphIndex) {
/* 146 */     return this.awtGlyphVector.getGlyphCode(glyphIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getGlyphCodes(int beginGlyphIndex, int numEntries, int[] codeReturn) {
/* 154 */     return this.awtGlyphVector.getGlyphCodes(beginGlyphIndex, numEntries, codeReturn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphJustificationInfo getGlyphJustificationInfo(int glyphIndex) {
/* 163 */     return this.awtGlyphVector.getGlyphJustificationInfo(glyphIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D(AttributedCharacterIterator aci) {
/* 170 */     aci.first();
/* 171 */     TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/* 172 */     if (this.bounds2D != null && TextPaintInfo.equivilent(tpi, this.cacheTPI))
/*     */     {
/* 174 */       return this.bounds2D;
/*     */     }
/* 176 */     if (tpi == null)
/* 177 */       return null; 
/* 178 */     if (!tpi.visible) {
/* 179 */       return null;
/*     */     }
/* 181 */     this.cacheTPI = new TextPaintInfo(tpi);
/* 182 */     Shape outline = null;
/* 183 */     if (tpi.fillPaint != null) {
/* 184 */       outline = getOutline();
/* 185 */       this.bounds2D = outline.getBounds2D();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 190 */     Stroke stroke = tpi.strokeStroke;
/* 191 */     Paint paint = tpi.strokePaint;
/* 192 */     if (stroke != null && paint != null) {
/* 193 */       if (outline == null)
/* 194 */         outline = getOutline(); 
/* 195 */       Rectangle2D strokeBounds = stroke.createStrokedShape(outline).getBounds2D();
/*     */       
/* 197 */       if (this.bounds2D == null) {
/* 198 */         this.bounds2D = strokeBounds;
/*     */       } else {
/*     */         
/* 201 */         this.bounds2D.add(strokeBounds);
/*     */       } 
/* 203 */     }  if (this.bounds2D == null) {
/* 204 */       return null;
/*     */     }
/* 206 */     if (this.bounds2D.getWidth() == 0.0D || this.bounds2D.getHeight() == 0.0D)
/*     */     {
/* 208 */       this.bounds2D = null;
/*     */     }
/* 210 */     return this.bounds2D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getLogicalBounds() {
/* 218 */     if (this.logicalBounds == null)
/*     */     {
/* 220 */       computeGlyphLogicalBounds();
/*     */     }
/* 222 */     return this.logicalBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphLogicalBounds(int glyphIndex) {
/* 230 */     if (this.glyphLogicalBounds[glyphIndex] == null && this.glyphVisible[glyphIndex])
/*     */     {
/*     */       
/* 233 */       computeGlyphLogicalBounds();
/*     */     }
/* 235 */     return this.glyphLogicalBounds[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeGlyphLogicalBounds() {
/* 245 */     Shape[] tempLogicalBounds = new Shape[getNumGlyphs()];
/* 246 */     boolean[] rotated = new boolean[getNumGlyphs()];
/*     */     
/* 248 */     double maxWidth = -1.0D;
/* 249 */     double maxHeight = -1.0D;
/* 250 */     for (int i = 0; i < getNumGlyphs(); i++) {
/*     */       
/* 252 */       if (!this.glyphVisible[i]) {
/*     */         
/* 254 */         tempLogicalBounds[i] = null;
/*     */       }
/*     */       else {
/*     */         
/* 258 */         AffineTransform glyphTransform = getGlyphTransform(i);
/* 259 */         GVTGlyphMetrics glyphMetrics = getGlyphMetrics(i);
/*     */         
/* 261 */         float glyphX = 0.0F;
/* 262 */         float glyphY = (float)(-this.ascent / this.scaleFactor);
/* 263 */         float glyphWidth = (float)(glyphMetrics.getHorizontalAdvance() / this.scaleFactor);
/*     */         
/* 265 */         float glyphHeight = (float)(glyphMetrics.getVerticalAdvance() / this.scaleFactor);
/*     */         
/* 267 */         Rectangle2D glyphBounds = new Rectangle2D.Double(glyphX, glyphY, glyphWidth, glyphHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 272 */         if (glyphBounds.isEmpty()) {
/* 273 */           if (i > 0) {
/*     */ 
/*     */             
/* 276 */             rotated[i] = rotated[i - 1];
/*     */           } else {
/* 278 */             rotated[i] = true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 283 */           Point2D p1 = new Point2D.Double(glyphBounds.getMinX(), glyphBounds.getMinY());
/*     */           
/* 285 */           Point2D p2 = new Point2D.Double(glyphBounds.getMaxX(), glyphBounds.getMinY());
/*     */           
/* 287 */           Point2D p3 = new Point2D.Double(glyphBounds.getMinX(), glyphBounds.getMaxY());
/*     */ 
/*     */           
/* 290 */           Point2D gpos = getGlyphPosition(i);
/* 291 */           AffineTransform tr = AffineTransform.getTranslateInstance(gpos.getX(), gpos.getY());
/*     */ 
/*     */           
/* 294 */           if (glyphTransform != null)
/* 295 */             tr.concatenate(glyphTransform); 
/* 296 */           tr.scale(this.scaleFactor, this.scaleFactor);
/*     */           
/* 298 */           tempLogicalBounds[i] = tr.createTransformedShape(glyphBounds);
/*     */           
/* 300 */           Point2D tp1 = new Point2D.Double();
/* 301 */           Point2D tp2 = new Point2D.Double();
/* 302 */           Point2D tp3 = new Point2D.Double();
/* 303 */           tr.transform(p1, tp1);
/* 304 */           tr.transform(p2, tp2);
/* 305 */           tr.transform(p3, tp3);
/* 306 */           double tdx12 = tp1.getX() - tp2.getX();
/* 307 */           double tdx13 = tp1.getX() - tp3.getX();
/* 308 */           double tdy12 = tp1.getY() - tp2.getY();
/* 309 */           double tdy13 = tp1.getY() - tp3.getY();
/*     */           
/* 311 */           if ((Math.abs(tdx12) < 0.001D && Math.abs(tdy13) < 0.001D) || (Math.abs(tdx13) < 0.001D && Math.abs(tdy12) < 0.001D)) {
/*     */ 
/*     */             
/* 314 */             rotated[i] = false;
/*     */           } else {
/* 316 */             rotated[i] = true;
/*     */           } 
/*     */ 
/*     */           
/* 320 */           Rectangle2D rectBounds = tempLogicalBounds[i].getBounds2D();
/* 321 */           if (rectBounds.getWidth() > maxWidth)
/* 322 */             maxWidth = rectBounds.getWidth(); 
/* 323 */           if (rectBounds.getHeight() > maxHeight) {
/* 324 */             maxHeight = rectBounds.getHeight();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 329 */     GeneralPath logicalBoundsPath = new GeneralPath(); int j;
/* 330 */     for (j = 0; j < getNumGlyphs(); j++) {
/* 331 */       if (tempLogicalBounds[j] != null) {
/* 332 */         logicalBoundsPath.append(tempLogicalBounds[j], false);
/*     */       }
/*     */     } 
/*     */     
/* 336 */     this.logicalBounds = logicalBoundsPath.getBounds2D();
/*     */     
/* 338 */     if (this.logicalBounds.getHeight() < maxHeight * 1.5D) {
/*     */       
/* 340 */       for (j = 0; j < getNumGlyphs(); j++) {
/*     */ 
/*     */         
/* 343 */         if (!rotated[j] && 
/* 344 */           tempLogicalBounds[j] != null)
/*     */         {
/* 346 */           Rectangle2D glyphBounds = tempLogicalBounds[j].getBounds2D();
/*     */           
/* 348 */           double x = glyphBounds.getMinX();
/* 349 */           double width = glyphBounds.getWidth();
/*     */           
/* 351 */           if (j < getNumGlyphs() - 1 && tempLogicalBounds[j + 1] != null) {
/*     */ 
/*     */             
/* 354 */             Rectangle2D ngb = tempLogicalBounds[j + 1].getBounds2D();
/*     */             
/* 356 */             if (ngb.getX() > x) {
/* 357 */               double nw = ngb.getX() - x;
/* 358 */               if (nw < width * 1.15D && nw > width * 0.85D) {
/* 359 */                 double delta = (nw - width) * 0.5D;
/* 360 */                 width += delta;
/* 361 */                 ngb.setRect(ngb.getX() - delta, ngb.getY(), ngb.getWidth() + delta, ngb.getHeight());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 366 */           tempLogicalBounds[j] = new Rectangle2D.Double(x, this.logicalBounds.getMinY(), width, this.logicalBounds.getHeight());
/*     */         }
/*     */       
/*     */       } 
/* 370 */     } else if (this.logicalBounds.getWidth() < maxWidth * 1.5D) {
/*     */       
/* 372 */       for (j = 0; j < getNumGlyphs(); j++) {
/*     */ 
/*     */         
/* 375 */         if (!rotated[j] && 
/* 376 */           tempLogicalBounds[j] != null) {
/*     */           
/* 378 */           Rectangle2D glyphBounds = tempLogicalBounds[j].getBounds2D();
/* 379 */           double y = glyphBounds.getMinY();
/* 380 */           double height = glyphBounds.getHeight();
/*     */           
/* 382 */           if (j < getNumGlyphs() - 1 && tempLogicalBounds[j + 1] != null) {
/*     */ 
/*     */             
/* 385 */             Rectangle2D ngb = tempLogicalBounds[j + 1].getBounds2D();
/* 386 */             if (ngb.getY() > y) {
/* 387 */               double nh = ngb.getY() - y;
/* 388 */               if (nh < height * 1.15D && nh > height * 0.85D) {
/* 389 */                 double delta = (nh - height) * 0.5D;
/* 390 */                 height += delta;
/* 391 */                 ngb.setRect(ngb.getX(), ngb.getY() - delta, ngb.getWidth(), ngb.getHeight() + delta);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 396 */           tempLogicalBounds[j] = new Rectangle2D.Double(this.logicalBounds.getMinX(), y, this.logicalBounds.getWidth(), height);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 402 */     System.arraycopy(tempLogicalBounds, 0, this.glyphLogicalBounds, 0, getNumGlyphs());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphMetrics getGlyphMetrics(int glyphIndex) {
/* 410 */     if (this.glyphMetrics[glyphIndex] != null) {
/* 411 */       return this.glyphMetrics[glyphIndex];
/*     */     }
/*     */     
/* 414 */     Point2D glyphPos = this.defaultGlyphPositions[glyphIndex];
/* 415 */     char c = this.ci.setIndex(this.ci.getBeginIndex() + glyphIndex);
/* 416 */     this.ci.setIndex(this.ci.getBeginIndex());
/* 417 */     AWTGlyphGeometryCache.Value v = AWTGVTFont.getGlyphGeometry(this.gvtFont, c, this.awtGlyphVector, glyphIndex, glyphPos);
/*     */     
/* 419 */     Rectangle2D gmB = v.getBounds2D();
/*     */ 
/*     */     
/* 422 */     Rectangle2D bounds = new Rectangle2D.Double(gmB.getX() * this.scaleFactor, gmB.getY() * this.scaleFactor, gmB.getWidth() * this.scaleFactor, gmB.getHeight() * this.scaleFactor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     float adv = (float)(this.defaultGlyphPositions[glyphIndex + 1].getX() - this.defaultGlyphPositions[glyphIndex].getX());
/*     */     
/* 431 */     this.glyphMetrics[glyphIndex] = new GVTGlyphMetrics((float)(adv * this.scaleFactor), this.ascent + this.descent, bounds, (byte)0);
/*     */ 
/*     */ 
/*     */     
/* 435 */     return this.glyphMetrics[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphOutline(int glyphIndex) {
/* 443 */     if (this.glyphOutlines[glyphIndex] == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 448 */       Point2D glyphPos = this.defaultGlyphPositions[glyphIndex];
/* 449 */       char c = this.ci.setIndex(this.ci.getBeginIndex() + glyphIndex);
/* 450 */       this.ci.setIndex(this.ci.getBeginIndex());
/* 451 */       AWTGlyphGeometryCache.Value v = AWTGVTFont.getGlyphGeometry(this.gvtFont, c, this.awtGlyphVector, glyphIndex, glyphPos);
/*     */       
/* 453 */       Shape glyphOutline = v.getOutline();
/*     */ 
/*     */       
/* 456 */       AffineTransform tr = AffineTransform.getTranslateInstance(getGlyphPosition(glyphIndex).getX(), getGlyphPosition(glyphIndex).getY());
/*     */ 
/*     */ 
/*     */       
/* 460 */       AffineTransform glyphTransform = getGlyphTransform(glyphIndex);
/*     */       
/* 462 */       if (glyphTransform != null) {
/* 463 */         tr.concatenate(glyphTransform);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       tr.scale(this.scaleFactor, this.scaleFactor);
/* 487 */       this.glyphOutlines[glyphIndex] = tr.createTransformedShape(glyphOutline);
/*     */     } 
/*     */     
/* 490 */     return this.glyphOutlines[glyphIndex];
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
/*     */   static {
/* 504 */     String s = System.getProperty("java.specification.version");
/* 505 */     if ("1.6".compareTo(s) <= 0) {
/* 506 */       outlinesPositioned = true;
/* 507 */       drawGlyphVectorWorks = false;
/* 508 */       glyphVectorTransformWorks = true;
/* 509 */     } else if ("1.4".compareTo(s) <= 0) {
/*     */       
/* 511 */       outlinesPositioned = true;
/* 512 */       drawGlyphVectorWorks = true;
/* 513 */       glyphVectorTransformWorks = true;
/* 514 */     } else if (Platform.isOSX) {
/* 515 */       outlinesPositioned = true;
/* 516 */       drawGlyphVectorWorks = false;
/* 517 */       glyphVectorTransformWorks = false;
/*     */     } else {
/* 519 */       outlinesPositioned = false;
/* 520 */       drawGlyphVectorWorks = true;
/* 521 */       glyphVectorTransformWorks = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean outlinesPositioned() {
/* 528 */     return outlinesPositioned;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGlyphCellBounds(int glyphIndex) {
/* 537 */     return getGlyphLogicalBounds(glyphIndex).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getGlyphPosition(int glyphIndex) {
/* 544 */     return this.glyphPositions[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getGlyphPositions(int beginGlyphIndex, int numEntries, float[] positionReturn) {
/* 554 */     if (positionReturn == null) {
/* 555 */       positionReturn = new float[numEntries * 2];
/*     */     }
/*     */     
/* 558 */     for (int i = beginGlyphIndex; i < beginGlyphIndex + numEntries; i++) {
/* 559 */       Point2D glyphPos = getGlyphPosition(i);
/* 560 */       positionReturn[(i - beginGlyphIndex) * 2] = (float)glyphPos.getX();
/* 561 */       positionReturn[(i - beginGlyphIndex) * 2 + 1] = (float)glyphPos.getY();
/*     */     } 
/*     */     
/* 564 */     return positionReturn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlyphTransform(int glyphIndex) {
/* 571 */     return this.glyphTransforms[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphVisualBounds(int glyphIndex) {
/* 578 */     if (this.glyphVisualBounds[glyphIndex] == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 584 */       Point2D glyphPos = this.defaultGlyphPositions[glyphIndex];
/* 585 */       char c = this.ci.setIndex(this.ci.getBeginIndex() + glyphIndex);
/* 586 */       this.ci.setIndex(this.ci.getBeginIndex());
/* 587 */       AWTGlyphGeometryCache.Value v = AWTGVTFont.getGlyphGeometry(this.gvtFont, c, this.awtGlyphVector, glyphIndex, glyphPos);
/*     */       
/* 589 */       Rectangle2D glyphBounds = v.getOutlineBounds2D();
/*     */ 
/*     */       
/* 592 */       AffineTransform tr = AffineTransform.getTranslateInstance(getGlyphPosition(glyphIndex).getX(), getGlyphPosition(glyphIndex).getY());
/*     */ 
/*     */ 
/*     */       
/* 596 */       AffineTransform glyphTransform = getGlyphTransform(glyphIndex);
/* 597 */       if (glyphTransform != null) {
/* 598 */         tr.concatenate(glyphTransform);
/*     */       }
/* 600 */       tr.scale(this.scaleFactor, this.scaleFactor);
/* 601 */       this.glyphVisualBounds[glyphIndex] = tr.createTransformedShape(glyphBounds);
/*     */     } 
/*     */ 
/*     */     
/* 605 */     return this.glyphVisualBounds[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumGlyphs() {
/* 612 */     return this.awtGlyphVector.getNumGlyphs();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 620 */     if (this.outline != null) {
/* 621 */       return this.outline;
/*     */     }
/* 623 */     this.outline = new GeneralPath();
/* 624 */     for (int i = 0; i < getNumGlyphs(); i++) {
/* 625 */       if (this.glyphVisible[i]) {
/* 626 */         Shape glyphOutline = getGlyphOutline(i);
/* 627 */         this.outline.append(glyphOutline, false);
/*     */       } 
/*     */     } 
/* 630 */     return this.outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline(float x, float y) {
/* 638 */     Shape outline = getOutline();
/* 639 */     AffineTransform tr = AffineTransform.getTranslateInstance(x, y);
/* 640 */     outline = tr.createTransformedShape(outline);
/* 641 */     return outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometricBounds() {
/* 650 */     if (this.visualBounds == null) {
/* 651 */       Shape outline = getOutline();
/* 652 */       this.visualBounds = outline.getBounds2D();
/*     */     } 
/* 654 */     return this.visualBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void performDefaultLayout() {
/* 661 */     if (this.defaultGlyphPositions == null) {
/* 662 */       this.awtGlyphVector.performDefaultLayout();
/* 663 */       this.defaultGlyphPositions = (Point2D[])new Point2D.Float[getNumGlyphs() + 1];
/* 664 */       for (int j = 0; j <= getNumGlyphs(); j++) {
/* 665 */         this.defaultGlyphPositions[j] = this.awtGlyphVector.getGlyphPosition(j);
/*     */       }
/*     */     } 
/* 668 */     this.outline = null;
/* 669 */     this.visualBounds = null;
/* 670 */     this.logicalBounds = null;
/* 671 */     this.bounds2D = null;
/* 672 */     float shiftLeft = 0.0F;
/* 673 */     int i = 0;
/* 674 */     for (; i < getNumGlyphs(); i++) {
/* 675 */       this.glyphTransforms[i] = null;
/* 676 */       this.glyphVisualBounds[i] = null;
/* 677 */       this.glyphLogicalBounds[i] = null;
/* 678 */       this.glyphOutlines[i] = null;
/* 679 */       this.glyphMetrics[i] = null;
/* 680 */       Point2D point2D = this.defaultGlyphPositions[i];
/* 681 */       float x = (float)(point2D.getX() * this.scaleFactor - shiftLeft);
/* 682 */       float y = (float)(point2D.getY() * this.scaleFactor);
/*     */ 
/*     */ 
/*     */       
/* 686 */       this.ci.setIndex(i + this.ci.getBeginIndex());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 719 */       if (this.glyphPositions[i] == null) {
/* 720 */         this.glyphPositions[i] = new Point2D.Float(x, y);
/*     */       } else {
/* 722 */         (this.glyphPositions[i]).x = x;
/* 723 */         (this.glyphPositions[i]).y = y;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 730 */     Point2D glyphPos = this.defaultGlyphPositions[i];
/* 731 */     this.glyphPositions[i] = new Point2D.Float((float)(glyphPos.getX() * this.scaleFactor - shiftLeft), (float)(glyphPos.getY() * this.scaleFactor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphPosition(int glyphIndex, Point2D newPos) {
/* 740 */     (this.glyphPositions[glyphIndex]).x = (float)newPos.getX();
/* 741 */     (this.glyphPositions[glyphIndex]).y = (float)newPos.getY();
/* 742 */     this.outline = null;
/* 743 */     this.visualBounds = null;
/* 744 */     this.logicalBounds = null;
/* 745 */     this.bounds2D = null;
/*     */     
/* 747 */     if (glyphIndex != getNumGlyphs()) {
/* 748 */       this.glyphVisualBounds[glyphIndex] = null;
/* 749 */       this.glyphLogicalBounds[glyphIndex] = null;
/* 750 */       this.glyphOutlines[glyphIndex] = null;
/* 751 */       this.glyphMetrics[glyphIndex] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphTransform(int glyphIndex, AffineTransform newTX) {
/* 759 */     this.glyphTransforms[glyphIndex] = newTX;
/* 760 */     this.outline = null;
/* 761 */     this.visualBounds = null;
/* 762 */     this.logicalBounds = null;
/* 763 */     this.bounds2D = null;
/*     */     
/* 765 */     this.glyphVisualBounds[glyphIndex] = null;
/* 766 */     this.glyphLogicalBounds[glyphIndex] = null;
/* 767 */     this.glyphOutlines[glyphIndex] = null;
/* 768 */     this.glyphMetrics[glyphIndex] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphVisible(int glyphIndex, boolean visible) {
/* 775 */     if (visible == this.glyphVisible[glyphIndex])
/*     */       return; 
/* 777 */     this.glyphVisible[glyphIndex] = visible;
/* 778 */     this.outline = null;
/* 779 */     this.visualBounds = null;
/* 780 */     this.logicalBounds = null;
/* 781 */     this.bounds2D = null;
/*     */     
/* 783 */     this.glyphVisualBounds[glyphIndex] = null;
/* 784 */     this.glyphLogicalBounds[glyphIndex] = null;
/* 785 */     this.glyphOutlines[glyphIndex] = null;
/* 786 */     this.glyphMetrics[glyphIndex] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGlyphVisible(int glyphIndex) {
/* 793 */     return this.glyphVisible[glyphIndex];
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
/*     */   public int getCharacterCount(int startGlyphIndex, int endGlyphIndex) {
/* 805 */     if (startGlyphIndex < 0) {
/* 806 */       startGlyphIndex = 0;
/*     */     }
/* 808 */     if (endGlyphIndex >= getNumGlyphs()) {
/* 809 */       endGlyphIndex = getNumGlyphs() - 1;
/*     */     }
/* 811 */     int charCount = 0;
/* 812 */     int start = startGlyphIndex + this.ci.getBeginIndex();
/* 813 */     int end = endGlyphIndex + this.ci.getBeginIndex();
/*     */     char c;
/* 815 */     for (c = this.ci.setIndex(start); this.ci.getIndex() <= end; c = this.ci.next()) {
/* 816 */       charCount += ArabicTextHandler.getNumChars(c);
/*     */     }
/*     */     
/* 819 */     return charCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReversed() {
/* 824 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void maybeReverse(boolean mirror) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Graphics2D graphics2D, AttributedCharacterIterator aci) {
/* 836 */     int numGlyphs = getNumGlyphs();
/*     */     
/* 838 */     aci.first();
/* 839 */     TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO);
/*     */     
/* 841 */     if (tpi == null)
/* 842 */       return;  if (!tpi.visible)
/*     */       return; 
/* 844 */     Paint fillPaint = tpi.fillPaint;
/* 845 */     Stroke stroke = tpi.strokeStroke;
/* 846 */     Paint strokePaint = tpi.strokePaint;
/*     */     
/* 848 */     if (fillPaint == null && (strokePaint == null || stroke == null)) {
/*     */       return;
/*     */     }
/*     */     
/* 852 */     boolean useHinting = drawGlyphVectorWorks;
/* 853 */     if (useHinting && stroke != null && strokePaint != null)
/*     */     {
/* 855 */       useHinting = false;
/*     */     }
/* 857 */     if (useHinting && fillPaint != null && !(fillPaint instanceof java.awt.Color))
/*     */     {
/*     */ 
/*     */       
/* 861 */       useHinting = false;
/*     */     }
/* 863 */     if (useHinting) {
/* 864 */       Object v1 = graphics2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
/*     */       
/* 866 */       Object v2 = graphics2D.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
/*     */ 
/*     */       
/* 869 */       if (v1 == RenderingHints.VALUE_TEXT_ANTIALIAS_ON && v2 == RenderingHints.VALUE_STROKE_PURE)
/*     */       {
/* 871 */         useHinting = false;
/*     */       }
/*     */     } 
/* 874 */     int typeGRot = 16;
/* 875 */     int typeGTrans = 32;
/*     */     
/* 877 */     if (useHinting) {
/*     */ 
/*     */       
/* 880 */       AffineTransform at = graphics2D.getTransform();
/* 881 */       int type = at.getType();
/* 882 */       if ((type & 0x20) != 0 || (type & 0x10) != 0) {
/* 883 */         useHinting = false;
/*     */       }
/*     */     } 
/* 886 */     if (useHinting) {
/* 887 */       for (int i = 0; i < numGlyphs; i++) {
/* 888 */         if (!this.glyphVisible[i]) {
/* 889 */           useHinting = false;
/*     */           break;
/*     */         } 
/* 892 */         AffineTransform at = this.glyphTransforms[i];
/* 893 */         if (at != null) {
/* 894 */           int type = at.getType();
/* 895 */           if ((type & 0xFFFFFFFE) != 0)
/*     */           {
/* 897 */             if (!glyphVectorTransformWorks || (type & 0x20) != 0 || (type & 0x10) != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 905 */               useHinting = false;
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 912 */     if (useHinting) {
/* 913 */       double sf = this.scaleFactor;
/* 914 */       double[] mat = new double[6]; int i;
/* 915 */       for (i = 0; i < numGlyphs; i++) {
/* 916 */         Point2D pos = this.glyphPositions[i];
/* 917 */         double x = pos.getX();
/* 918 */         double y = pos.getY();
/* 919 */         AffineTransform at = this.glyphTransforms[i];
/* 920 */         if (at != null) {
/*     */ 
/*     */           
/* 923 */           at.getMatrix(mat);
/* 924 */           x += mat[4];
/* 925 */           y += mat[5];
/* 926 */           if (mat[0] != 1.0D || mat[1] != 0.0D || mat[2] != 0.0D || mat[3] != 1.0D) {
/*     */ 
/*     */             
/* 929 */             mat[4] = 0.0D; mat[5] = 0.0D;
/* 930 */             at = new AffineTransform(mat);
/*     */           } else {
/* 932 */             at = null;
/*     */           } 
/*     */         } 
/* 935 */         pos = new Point2D.Double(x / sf, y / sf);
/* 936 */         this.awtGlyphVector.setGlyphPosition(i, pos);
/* 937 */         this.awtGlyphVector.setGlyphTransform(i, at);
/*     */       } 
/* 939 */       graphics2D.scale(sf, sf);
/* 940 */       graphics2D.setPaint(fillPaint);
/* 941 */       graphics2D.drawGlyphVector(this.awtGlyphVector, 0.0F, 0.0F);
/* 942 */       graphics2D.scale(1.0D / sf, 1.0D / sf);
/*     */       
/* 944 */       for (i = 0; i < numGlyphs; i++) {
/* 945 */         Point2D pos = this.defaultGlyphPositions[i];
/* 946 */         this.awtGlyphVector.setGlyphPosition(i, pos);
/* 947 */         this.awtGlyphVector.setGlyphTransform(i, null);
/*     */       } 
/*     */     } else {
/*     */       
/* 951 */       Shape outline = getOutline();
/*     */ 
/*     */       
/* 954 */       if (fillPaint != null) {
/* 955 */         graphics2D.setPaint(fillPaint);
/* 956 */         graphics2D.fill(outline);
/*     */       } 
/*     */ 
/*     */       
/* 960 */       if (stroke != null && strokePaint != null) {
/* 961 */         graphics2D.setStroke(stroke);
/* 962 */         graphics2D.setPaint(strokePaint);
/* 963 */         graphics2D.draw(outline);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/AWTGVTGlyphVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */