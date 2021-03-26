/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphJustificationInfo;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.ArabicTextHandler;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SVGGVTGlyphVector
/*     */   implements GVTGlyphVector
/*     */ {
/*  43 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*     */   
/*     */   private GVTFont font;
/*     */   
/*     */   private Glyph[] glyphs;
/*     */   
/*     */   private FontRenderContext frc;
/*     */   
/*     */   private GeneralPath outline;
/*     */   
/*     */   private Rectangle2D logicalBounds;
/*     */   
/*     */   private Rectangle2D bounds2D;
/*     */   
/*     */   private Shape[] glyphLogicalBounds;
/*     */   
/*     */   private boolean[] glyphVisible;
/*     */   
/*     */   private Point2D endPos;
/*     */   
/*     */   private TextPaintInfo cacheTPI;
/*     */ 
/*     */   
/*     */   public SVGGVTGlyphVector(GVTFont font, Glyph[] glyphs, FontRenderContext frc) {
/*  67 */     this.font = font;
/*  68 */     this.glyphs = glyphs;
/*  69 */     this.frc = frc;
/*  70 */     this.outline = null;
/*  71 */     this.bounds2D = null;
/*  72 */     this.logicalBounds = null;
/*  73 */     this.glyphLogicalBounds = new Shape[glyphs.length];
/*  74 */     this.glyphVisible = new boolean[glyphs.length];
/*  75 */     for (int i = 0; i < glyphs.length; i++) {
/*  76 */       this.glyphVisible[i] = true;
/*     */     }
/*     */     
/*  79 */     this.endPos = glyphs[glyphs.length - 1].getPosition();
/*  80 */     this.endPos = new Point2D.Float((float)(this.endPos.getX() + glyphs[glyphs.length - 1].getHorizAdvX()), (float)this.endPos.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont getFont() {
/*  89 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/*  96 */     return this.frc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphCode(int glyphIndex) throws IndexOutOfBoundsException {
/* 103 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 104 */       throw new IndexOutOfBoundsException("glyphIndex " + glyphIndex + " is out of bounds, should be between 0 and " + (this.glyphs.length - 1));
/*     */     }
/*     */ 
/*     */     
/* 108 */     return this.glyphs[glyphIndex].getGlyphCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getGlyphCodes(int beginGlyphIndex, int numEntries, int[] codeReturn) throws IndexOutOfBoundsException, IllegalArgumentException {
/* 118 */     if (numEntries < 0) {
/* 119 */       throw new IllegalArgumentException("numEntries argument value, " + numEntries + ", is illegal. It must be > 0.");
/*     */     }
/*     */     
/* 122 */     if (beginGlyphIndex < 0) {
/* 123 */       throw new IndexOutOfBoundsException("beginGlyphIndex " + beginGlyphIndex + " is out of bounds, should be between 0 and " + (this.glyphs.length - 1));
/*     */     }
/*     */ 
/*     */     
/* 127 */     if (beginGlyphIndex + numEntries > this.glyphs.length) {
/* 128 */       throw new IndexOutOfBoundsException("beginGlyphIndex + numEntries (" + beginGlyphIndex + "+" + numEntries + ") exceeds the number of glpyhs in this GlyphVector");
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (codeReturn == null) {
/* 133 */       codeReturn = new int[numEntries];
/*     */     }
/* 135 */     for (int i = beginGlyphIndex; i < beginGlyphIndex + numEntries; i++) {
/* 136 */       codeReturn[i - beginGlyphIndex] = this.glyphs[i].getGlyphCode();
/*     */     }
/* 138 */     return codeReturn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphJustificationInfo getGlyphJustificationInfo(int glyphIndex) {
/* 146 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 147 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + ".");
/*     */     }
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphLogicalBounds(int glyphIndex) {
/* 158 */     if (this.glyphLogicalBounds[glyphIndex] == null && this.glyphVisible[glyphIndex]) {
/* 159 */       computeGlyphLogicalBounds();
/*     */     }
/* 161 */     return this.glyphLogicalBounds[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeGlyphLogicalBounds() {
/* 167 */     float ascent = 0.0F;
/* 168 */     float descent = 0.0F;
/* 169 */     if (this.font != null) {
/*     */       
/* 171 */       GVTLineMetrics lineMetrics = this.font.getLineMetrics("By", this.frc);
/* 172 */       ascent = lineMetrics.getAscent();
/* 173 */       descent = lineMetrics.getDescent();
/* 174 */       if (descent < 0.0F)
/*     */       {
/* 176 */         descent = -descent;
/*     */       }
/*     */     } 
/*     */     
/* 180 */     if (ascent == 0.0F) {
/* 181 */       float maxAscent = 0.0F;
/* 182 */       float maxDescent = 0.0F;
/* 183 */       for (int k = 0; k < getNumGlyphs(); k++) {
/* 184 */         if (this.glyphVisible[k]) {
/* 185 */           GVTGlyphMetrics glyphMetrics = getGlyphMetrics(k);
/* 186 */           Rectangle2D glyphBounds = glyphMetrics.getBounds2D();
/* 187 */           ascent = (float)-glyphBounds.getMinY();
/* 188 */           descent = (float)(glyphBounds.getHeight() - ascent);
/* 189 */           if (ascent > maxAscent) maxAscent = ascent; 
/* 190 */           if (descent > maxDescent) maxDescent = descent; 
/*     */         } 
/* 192 */       }  ascent = maxAscent;
/* 193 */       descent = maxDescent;
/*     */     } 
/*     */     
/* 196 */     Shape[] tempLogicalBounds = new Shape[getNumGlyphs()];
/* 197 */     boolean[] rotated = new boolean[getNumGlyphs()];
/*     */     
/* 199 */     double maxWidth = -1.0D;
/* 200 */     double maxHeight = -1.0D;
/*     */     
/* 202 */     for (int i = 0; i < getNumGlyphs(); i++) {
/*     */       
/* 204 */       if (!this.glyphVisible[i]) {
/*     */         
/* 206 */         tempLogicalBounds[i] = null;
/*     */       }
/*     */       else {
/*     */         
/* 210 */         AffineTransform glyphTransform = getGlyphTransform(i);
/* 211 */         GVTGlyphMetrics glyphMetrics = getGlyphMetrics(i);
/* 212 */         Rectangle2D glyphBounds = new Rectangle2D.Double(0.0D, -ascent, glyphMetrics.getHorizontalAdvance(), (ascent + descent));
/*     */ 
/*     */ 
/*     */         
/* 216 */         if (glyphBounds.isEmpty()) {
/*     */ 
/*     */           
/* 219 */           if (i > 0) {
/* 220 */             rotated[i] = rotated[i - 1];
/*     */           } else {
/* 222 */             rotated[i] = true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 227 */           Point2D p1 = new Point2D.Double(glyphBounds.getMinX(), glyphBounds.getMinY());
/*     */           
/* 229 */           Point2D p2 = new Point2D.Double(glyphBounds.getMaxX(), glyphBounds.getMinY());
/*     */           
/* 231 */           Point2D p3 = new Point2D.Double(glyphBounds.getMinX(), glyphBounds.getMaxY());
/*     */           
/* 233 */           Point2D gpos = getGlyphPosition(i);
/* 234 */           AffineTransform tr = AffineTransform.getTranslateInstance(gpos.getX(), gpos.getY());
/*     */ 
/*     */           
/* 237 */           if (glyphTransform != null) {
/* 238 */             tr.concatenate(glyphTransform);
/*     */           }
/* 240 */           tempLogicalBounds[i] = tr.createTransformedShape(glyphBounds);
/*     */ 
/*     */           
/* 243 */           Point2D tp1 = new Point2D.Double();
/* 244 */           Point2D tp2 = new Point2D.Double();
/* 245 */           Point2D tp3 = new Point2D.Double();
/* 246 */           tr.transform(p1, tp1);
/* 247 */           tr.transform(p2, tp2);
/* 248 */           tr.transform(p3, tp3);
/* 249 */           double tdx12 = tp1.getX() - tp2.getX();
/* 250 */           double tdx13 = tp1.getX() - tp3.getX();
/* 251 */           double tdy12 = tp1.getY() - tp2.getY();
/* 252 */           double tdy13 = tp1.getY() - tp3.getY();
/*     */           
/* 254 */           if (Math.abs(tdx12) < 0.001D && Math.abs(tdy13) < 0.001D) {
/*     */ 
/*     */ 
/*     */             
/* 258 */             rotated[i] = false;
/* 259 */           } else if (Math.abs(tdx13) < 0.001D && Math.abs(tdy12) < 0.001D) {
/*     */ 
/*     */ 
/*     */             
/* 263 */             rotated[i] = false;
/*     */           } else {
/* 265 */             rotated[i] = true;
/*     */           } 
/*     */ 
/*     */           
/* 269 */           Rectangle2D rectBounds = tempLogicalBounds[i].getBounds2D();
/* 270 */           if (rectBounds.getWidth() > maxWidth)
/* 271 */             maxWidth = rectBounds.getWidth(); 
/* 272 */           if (rectBounds.getHeight() > maxHeight) {
/* 273 */             maxHeight = rectBounds.getHeight();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 278 */     GeneralPath logicalBoundsPath = new GeneralPath();
/* 279 */     for (int j = 0; j < getNumGlyphs(); j++) {
/* 280 */       if (tempLogicalBounds[j] != null) {
/* 281 */         logicalBoundsPath.append(tempLogicalBounds[j], false);
/*     */       }
/*     */     } 
/* 284 */     Rectangle2D fullBounds = logicalBoundsPath.getBounds2D();
/*     */     
/* 286 */     if (fullBounds.getHeight() < maxHeight * 1.5D) {
/*     */       
/* 288 */       for (int k = 0; k < getNumGlyphs(); k++) {
/*     */ 
/*     */         
/* 291 */         if (!rotated[k] && 
/* 292 */           tempLogicalBounds[k] != null)
/*     */         {
/* 294 */           Rectangle2D glyphBounds = tempLogicalBounds[k].getBounds2D();
/*     */           
/* 296 */           double x = glyphBounds.getMinX();
/* 297 */           double width = glyphBounds.getWidth();
/*     */           
/* 299 */           if (k < getNumGlyphs() - 1 && tempLogicalBounds[k + 1] != null) {
/*     */ 
/*     */             
/* 302 */             Rectangle2D ngb = tempLogicalBounds[k + 1].getBounds2D();
/*     */             
/* 304 */             if (ngb.getX() > x) {
/* 305 */               double nw = ngb.getX() - x;
/* 306 */               if (nw < width * 1.15D && nw > width * 0.85D) {
/* 307 */                 double delta = (nw - width) * 0.5D;
/* 308 */                 width += delta;
/* 309 */                 ngb.setRect(ngb.getX() - delta, ngb.getY(), ngb.getWidth() + delta, ngb.getHeight());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 314 */           tempLogicalBounds[k] = new Rectangle2D.Double(x, fullBounds.getMinY(), width, fullBounds.getHeight());
/*     */         }
/*     */       
/*     */       } 
/* 318 */     } else if (fullBounds.getWidth() < maxWidth * 1.5D) {
/*     */       
/* 320 */       for (int k = 0; k < getNumGlyphs(); k++) {
/*     */ 
/*     */         
/* 323 */         if (!rotated[k] && 
/* 324 */           tempLogicalBounds[k] != null) {
/*     */           
/* 326 */           Rectangle2D glyphBounds = tempLogicalBounds[k].getBounds2D();
/* 327 */           double y = glyphBounds.getMinY();
/* 328 */           double height = glyphBounds.getHeight();
/*     */           
/* 330 */           if (k < getNumGlyphs() - 1 && tempLogicalBounds[k + 1] != null) {
/*     */ 
/*     */             
/* 333 */             Rectangle2D ngb = tempLogicalBounds[k + 1].getBounds2D();
/* 334 */             if (ngb.getY() > y) {
/* 335 */               double nh = ngb.getY() - y;
/* 336 */               if (nh < height * 1.15D && nh > height * 0.85D) {
/* 337 */                 double delta = (nh - height) * 0.5D;
/* 338 */                 height += delta;
/* 339 */                 ngb.setRect(ngb.getX(), ngb.getY() - delta, ngb.getWidth(), ngb.getHeight() + delta);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 344 */           tempLogicalBounds[k] = new Rectangle2D.Double(fullBounds.getMinX(), y, fullBounds.getWidth(), height);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 350 */     System.arraycopy(tempLogicalBounds, 0, this.glyphLogicalBounds, 0, getNumGlyphs());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphMetrics getGlyphMetrics(int idx) {
/* 359 */     if (idx < 0 || idx > this.glyphs.length - 1) {
/* 360 */       throw new IndexOutOfBoundsException("idx: " + idx + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     if (idx < this.glyphs.length - 1)
/*     */     {
/* 370 */       if (this.font != null) {
/* 371 */         float hkern = this.font.getHKern(this.glyphs[idx].getGlyphCode(), this.glyphs[idx + 1].getGlyphCode());
/*     */         
/* 373 */         float vkern = this.font.getVKern(this.glyphs[idx].getGlyphCode(), this.glyphs[idx + 1].getGlyphCode());
/*     */         
/* 375 */         return this.glyphs[idx].getGlyphMetrics(hkern, vkern);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 380 */     return this.glyphs[idx].getGlyphMetrics();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphOutline(int glyphIndex) {
/* 388 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 389 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + ".");
/*     */     }
/*     */     
/* 392 */     return this.glyphs[glyphIndex].getOutline();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGlyphCellBounds(int glyphIndex) {
/* 401 */     return getGlyphLogicalBounds(glyphIndex).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getGlyphPosition(int glyphIndex) {
/* 408 */     if (glyphIndex == this.glyphs.length) {
/* 409 */       return this.endPos;
/*     */     }
/* 411 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 412 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */     
/* 415 */     return this.glyphs[glyphIndex].getPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getGlyphPositions(int beginGlyphIndex, int numEntries, float[] positionReturn) {
/* 424 */     if (numEntries < 0) {
/* 425 */       throw new IllegalArgumentException("numEntries argument value, " + numEntries + ", is illegal. It must be > 0.");
/*     */     }
/*     */     
/* 428 */     if (beginGlyphIndex < 0) {
/* 429 */       throw new IndexOutOfBoundsException("beginGlyphIndex " + beginGlyphIndex + " is out of bounds, should be between 0 and " + (this.glyphs.length - 1));
/*     */     }
/*     */ 
/*     */     
/* 433 */     if (beginGlyphIndex + numEntries > this.glyphs.length + 1) {
/* 434 */       throw new IndexOutOfBoundsException("beginGlyphIndex + numEntries (" + beginGlyphIndex + '+' + numEntries + ") exceeds the number of glpyhs in this GlyphVector");
/*     */     }
/*     */ 
/*     */     
/* 438 */     if (positionReturn == null) {
/* 439 */       positionReturn = new float[numEntries * 2];
/*     */     }
/* 441 */     if (beginGlyphIndex + numEntries == this.glyphs.length + 1) {
/* 442 */       numEntries--;
/* 443 */       positionReturn[numEntries * 2] = (float)this.endPos.getX();
/* 444 */       positionReturn[numEntries * 2 + 1] = (float)this.endPos.getY();
/*     */     } 
/* 446 */     for (int i = beginGlyphIndex; i < beginGlyphIndex + numEntries; i++) {
/*     */       
/* 448 */       Point2D glyphPos = this.glyphs[i].getPosition();
/* 449 */       positionReturn[(i - beginGlyphIndex) * 2] = (float)glyphPos.getX();
/* 450 */       positionReturn[(i - beginGlyphIndex) * 2 + 1] = (float)glyphPos.getY();
/*     */     } 
/* 452 */     return positionReturn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlyphTransform(int glyphIndex) {
/* 459 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 460 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */     
/* 463 */     return this.glyphs[glyphIndex].getTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphVisualBounds(int glyphIndex) {
/* 470 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 471 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */     
/* 474 */     return this.glyphs[glyphIndex].getOutline();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D(AttributedCharacterIterator aci) {
/* 482 */     aci.first();
/* 483 */     TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/* 484 */     if (this.bounds2D != null && TextPaintInfo.equivilent(tpi, this.cacheTPI))
/*     */     {
/* 486 */       return this.bounds2D;
/*     */     }
/* 488 */     Rectangle2D b = null;
/* 489 */     if (tpi.visible)
/* 490 */       for (int i = 0; i < getNumGlyphs(); i++) {
/* 491 */         if (this.glyphVisible[i]) {
/*     */           
/* 493 */           Rectangle2D glyphBounds = this.glyphs[i].getBounds2D();
/*     */           
/* 495 */           if (glyphBounds != null)
/* 496 */             if (b == null) { b = glyphBounds; }
/*     */             else
/* 498 */             { b.add(glyphBounds); }
/*     */              
/*     */         } 
/*     */       }  
/* 502 */     this.bounds2D = b;
/* 503 */     if (this.bounds2D == null) {
/* 504 */       this.bounds2D = new Rectangle2D.Float();
/*     */     }
/* 506 */     this.cacheTPI = new TextPaintInfo(tpi);
/* 507 */     return this.bounds2D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getLogicalBounds() {
/* 515 */     if (this.logicalBounds == null) {
/* 516 */       GeneralPath logicalBoundsPath = new GeneralPath();
/* 517 */       for (int i = 0; i < getNumGlyphs(); i++) {
/* 518 */         Shape glyphLogicalBounds = getGlyphLogicalBounds(i);
/* 519 */         if (glyphLogicalBounds != null) {
/* 520 */           logicalBoundsPath.append(glyphLogicalBounds, false);
/*     */         }
/*     */       } 
/* 523 */       this.logicalBounds = logicalBoundsPath.getBounds2D();
/*     */     } 
/* 525 */     return this.logicalBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumGlyphs() {
/* 532 */     if (this.glyphs != null) {
/* 533 */       return this.glyphs.length;
/*     */     }
/* 535 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 543 */     if (this.outline == null) {
/* 544 */       this.outline = new GeneralPath();
/* 545 */       for (int i = 0; i < this.glyphs.length; i++) {
/* 546 */         if (this.glyphVisible[i]) {
/* 547 */           Shape glyphOutline = this.glyphs[i].getOutline();
/* 548 */           if (glyphOutline != null) {
/* 549 */             this.outline.append(glyphOutline, false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 554 */     return this.outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline(float x, float y) {
/* 562 */     Shape outline = getOutline();
/* 563 */     AffineTransform tr = AffineTransform.getTranslateInstance(x, y);
/* 564 */     Shape translatedOutline = tr.createTransformedShape(outline);
/* 565 */     return translatedOutline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometricBounds() {
/* 574 */     return getOutline().getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void performDefaultLayout() {
/* 582 */     this.logicalBounds = null;
/* 583 */     this.outline = null;
/* 584 */     this.bounds2D = null;
/*     */     
/* 586 */     float currentX = 0.0F;
/* 587 */     float currentY = 0.0F;
/* 588 */     for (int i = 0; i < this.glyphs.length; i++) {
/* 589 */       Glyph g = this.glyphs[i];
/* 590 */       g.setTransform(null);
/* 591 */       this.glyphLogicalBounds[i] = null;
/*     */       
/* 593 */       String uni = g.getUnicode();
/* 594 */       if (uni != null && uni.length() != 0 && ArabicTextHandler.arabicCharTransparent(uni.charAt(0))) {
/*     */         int j;
/*     */         
/* 597 */         for (j = i + 1; j < this.glyphs.length; j++) {
/* 598 */           uni = this.glyphs[j].getUnicode();
/* 599 */           if (uni == null || uni.length() == 0)
/* 600 */             break;  char ch = uni.charAt(0);
/* 601 */           if (!ArabicTextHandler.arabicCharTransparent(ch))
/*     */             break; 
/*     */         } 
/* 604 */         if (j != this.glyphs.length) {
/* 605 */           Glyph bg = this.glyphs[j];
/* 606 */           float rEdge = currentX + bg.getHorizAdvX();
/* 607 */           for (int k = i; k < j; k++) {
/* 608 */             g = this.glyphs[k];
/* 609 */             g.setTransform(null);
/* 610 */             this.glyphLogicalBounds[i] = null;
/* 611 */             g.setPosition(new Point2D.Float(rEdge - g.getHorizAdvX(), currentY));
/*     */           } 
/*     */           
/* 614 */           i = j;
/* 615 */           g = bg;
/*     */         } 
/*     */       } 
/*     */       
/* 619 */       g.setPosition(new Point2D.Float(currentX, currentY));
/* 620 */       currentX += g.getHorizAdvX();
/*     */     } 
/* 622 */     this.endPos = new Point2D.Float(currentX, currentY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphPosition(int glyphIndex, Point2D newPos) throws IndexOutOfBoundsException {
/* 630 */     if (glyphIndex == this.glyphs.length) {
/* 631 */       this.endPos = (Point2D)newPos.clone();
/*     */       
/*     */       return;
/*     */     } 
/* 635 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 636 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */     
/* 639 */     this.glyphs[glyphIndex].setPosition(newPos);
/* 640 */     this.glyphLogicalBounds[glyphIndex] = null;
/* 641 */     this.outline = null;
/* 642 */     this.bounds2D = null;
/* 643 */     this.logicalBounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphTransform(int glyphIndex, AffineTransform newTX) {
/* 650 */     if (glyphIndex < 0 || glyphIndex > this.glyphs.length - 1) {
/* 651 */       throw new IndexOutOfBoundsException("glyphIndex: " + glyphIndex + ", is out of bounds. Should be between 0 and " + (this.glyphs.length - 1) + '.');
/*     */     }
/*     */     
/* 654 */     this.glyphs[glyphIndex].setTransform(newTX);
/* 655 */     this.glyphLogicalBounds[glyphIndex] = null;
/* 656 */     this.outline = null;
/* 657 */     this.bounds2D = null;
/* 658 */     this.logicalBounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphVisible(int glyphIndex, boolean visible) {
/* 665 */     if (visible == this.glyphVisible[glyphIndex]) {
/*     */       return;
/*     */     }
/* 668 */     this.glyphVisible[glyphIndex] = visible;
/* 669 */     this.outline = null;
/* 670 */     this.bounds2D = null;
/* 671 */     this.logicalBounds = null;
/* 672 */     this.glyphLogicalBounds[glyphIndex] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGlyphVisible(int glyphIndex) {
/* 679 */     return this.glyphVisible[glyphIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacterCount(int startGlyphIndex, int endGlyphIndex) {
/* 690 */     int numChars = 0;
/* 691 */     if (startGlyphIndex < 0) {
/* 692 */       startGlyphIndex = 0;
/*     */     }
/* 694 */     if (endGlyphIndex > this.glyphs.length - 1) {
/* 695 */       endGlyphIndex = this.glyphs.length - 1;
/*     */     }
/* 697 */     for (int i = startGlyphIndex; i <= endGlyphIndex; i++) {
/* 698 */       Glyph glyph = this.glyphs[i];
/* 699 */       if (glyph.getGlyphCode() == -1) {
/*     */         
/* 701 */         numChars++;
/*     */       } else {
/* 703 */         String glyphUnicode = glyph.getUnicode();
/* 704 */         numChars += glyphUnicode.length();
/*     */       } 
/*     */     } 
/* 707 */     return numChars;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReversed() {
/* 712 */     return false;
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
/* 724 */     aci.first();
/* 725 */     TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/* 726 */     if (!tpi.visible)
/*     */       return; 
/* 728 */     for (int i = 0; i < this.glyphs.length; i++) {
/* 729 */       if (this.glyphVisible[i])
/* 730 */         this.glyphs[i].draw(graphics2D); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/SVGGVTGlyphVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */