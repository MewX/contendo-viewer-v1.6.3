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
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.gvt.text.AttributedCharacterSpanIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiGlyphVector
/*     */   implements GVTGlyphVector
/*     */ {
/*     */   GVTGlyphVector[] gvs;
/*     */   int[] nGlyphs;
/*     */   int[] off;
/*     */   int nGlyph;
/*     */   
/*     */   public MultiGlyphVector(List gvs) {
/*  48 */     int nSlots = gvs.size();
/*  49 */     this.gvs = new GVTGlyphVector[nSlots];
/*  50 */     this.nGlyphs = new int[nSlots];
/*  51 */     this.off = new int[nSlots];
/*     */     
/*  53 */     Iterator<GVTGlyphVector> iter = gvs.iterator();
/*  54 */     int i = 0;
/*  55 */     while (iter.hasNext()) {
/*  56 */       this.off[i] = this.nGlyph;
/*     */       
/*  58 */       GVTGlyphVector gv = iter.next();
/*  59 */       this.gvs[i] = gv;
/*  60 */       this.nGlyphs[i] = gv.getNumGlyphs();
/*  61 */       this.nGlyph += this.nGlyphs[i];
/*  62 */       i++;
/*     */     } 
/*  64 */     this.nGlyphs[i - 1] = this.nGlyphs[i - 1] + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumGlyphs() {
/*  71 */     return this.nGlyph;
/*     */   }
/*     */ 
/*     */   
/*     */   int getGVIdx(int glyphIdx) {
/*  76 */     if (glyphIdx > this.nGlyph) return -1; 
/*  77 */     if (glyphIdx == this.nGlyph) return this.gvs.length - 1; 
/*  78 */     for (int i = 0; i < this.nGlyphs.length; i++) {
/*  79 */       if (glyphIdx - this.off[i] < this.nGlyphs[i]) return i; 
/*  80 */     }  return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont getFont() {
/*  87 */     throw new IllegalArgumentException("Can't be correctly Implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/*  94 */     return this.gvs[0].getFontRenderContext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphCode(int glyphIndex) {
/* 101 */     int idx = getGVIdx(glyphIndex);
/* 102 */     return this.gvs[idx].getGlyphCode(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphJustificationInfo getGlyphJustificationInfo(int glyphIndex) {
/* 110 */     int idx = getGVIdx(glyphIndex);
/* 111 */     return this.gvs[idx].getGlyphJustificationInfo(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphLogicalBounds(int glyphIndex) {
/* 119 */     int idx = getGVIdx(glyphIndex);
/* 120 */     return this.gvs[idx].getGlyphLogicalBounds(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphMetrics getGlyphMetrics(int glyphIndex) {
/* 128 */     int idx = getGVIdx(glyphIndex);
/* 129 */     return this.gvs[idx].getGlyphMetrics(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphOutline(int glyphIndex) {
/* 137 */     int idx = getGVIdx(glyphIndex);
/* 138 */     return this.gvs[idx].getGlyphOutline(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGlyphCellBounds(int glyphIndex) {
/* 147 */     return getGlyphLogicalBounds(glyphIndex).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getGlyphPosition(int glyphIndex) {
/* 154 */     int idx = getGVIdx(glyphIndex);
/* 155 */     return this.gvs[idx].getGlyphPosition(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlyphTransform(int glyphIndex) {
/* 162 */     int idx = getGVIdx(glyphIndex);
/* 163 */     return this.gvs[idx].getGlyphTransform(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getGlyphVisualBounds(int glyphIndex) {
/* 170 */     int idx = getGVIdx(glyphIndex);
/* 171 */     return this.gvs[idx].getGlyphVisualBounds(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphPosition(int glyphIndex, Point2D newPos) {
/* 178 */     int idx = getGVIdx(glyphIndex);
/*     */ 
/*     */     
/* 181 */     this.gvs[idx].setGlyphPosition(glyphIndex - this.off[idx], newPos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphTransform(int glyphIndex, AffineTransform newTX) {
/* 188 */     int idx = getGVIdx(glyphIndex);
/* 189 */     this.gvs[idx].setGlyphTransform(glyphIndex - this.off[idx], newTX);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphVisible(int glyphIndex, boolean visible) {
/* 196 */     int idx = getGVIdx(glyphIndex);
/* 197 */     this.gvs[idx].setGlyphVisible(glyphIndex - this.off[idx], visible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGlyphVisible(int glyphIndex) {
/* 204 */     int idx = getGVIdx(glyphIndex);
/* 205 */     return this.gvs[idx].isGlyphVisible(glyphIndex - this.off[idx]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getGlyphCodes(int beginGlyphIndex, int numEntries, int[] codeReturn) {
/* 213 */     int[] ret = codeReturn;
/* 214 */     if (ret == null)
/* 215 */       ret = new int[numEntries]; 
/* 216 */     int[] tmp = null;
/*     */     
/* 218 */     int gvIdx = getGVIdx(beginGlyphIndex);
/* 219 */     int gi = beginGlyphIndex - this.off[gvIdx];
/* 220 */     int i = 0;
/*     */     
/* 222 */     while (numEntries != 0) {
/* 223 */       int len = numEntries;
/* 224 */       if (gi + len > this.nGlyphs[gvIdx])
/* 225 */         len = this.nGlyphs[gvIdx] - gi; 
/* 226 */       GVTGlyphVector gv = this.gvs[gvIdx];
/* 227 */       if (i == 0) {
/* 228 */         gv.getGlyphCodes(gi, len, ret);
/*     */       } else {
/* 230 */         if (tmp == null || tmp.length < len) {
/* 231 */           tmp = new int[len];
/*     */         }
/* 233 */         gv.getGlyphCodes(gi, len, tmp);
/* 234 */         System.arraycopy(tmp, 0, ret, i, len);
/*     */       } 
/* 236 */       gi = 0;
/* 237 */       gvIdx++;
/* 238 */       numEntries -= len;
/* 239 */       i += len;
/*     */     } 
/* 241 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getGlyphPositions(int beginGlyphIndex, int numEntries, float[] positionReturn) {
/* 251 */     float[] ret = positionReturn;
/* 252 */     if (ret == null)
/* 253 */       ret = new float[numEntries * 2]; 
/* 254 */     float[] tmp = null;
/*     */     
/* 256 */     int gvIdx = getGVIdx(beginGlyphIndex);
/* 257 */     int gi = beginGlyphIndex - this.off[gvIdx];
/* 258 */     int i = 0;
/*     */     
/* 260 */     while (numEntries != 0) {
/* 261 */       int len = numEntries;
/* 262 */       if (gi + len > this.nGlyphs[gvIdx]) {
/* 263 */         len = this.nGlyphs[gvIdx] - gi;
/*     */       }
/* 265 */       GVTGlyphVector gv = this.gvs[gvIdx];
/* 266 */       if (i == 0) {
/* 267 */         gv.getGlyphPositions(gi, len, ret);
/*     */       } else {
/* 269 */         if (tmp == null || tmp.length < len * 2) {
/* 270 */           tmp = new float[len * 2];
/*     */         }
/* 272 */         gv.getGlyphPositions(gi, len, tmp);
/* 273 */         System.arraycopy(tmp, 0, ret, i, len * 2);
/*     */       } 
/* 275 */       gi = 0;
/* 276 */       gvIdx++;
/* 277 */       numEntries -= len;
/* 278 */       i += len * 2;
/*     */     } 
/* 280 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getLogicalBounds() {
/* 288 */     Rectangle2D ret = null;
/* 289 */     for (GVTGlyphVector gv : this.gvs) {
/* 290 */       Rectangle2D b = gv.getLogicalBounds();
/* 291 */       if (ret == null) { ret = b; }
/*     */       else
/* 293 */       { ret.add(b); }
/*     */     
/* 295 */     }  return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 303 */     GeneralPath ret = null;
/* 304 */     for (GVTGlyphVector gv : this.gvs) {
/* 305 */       Shape s = gv.getOutline();
/* 306 */       if (ret == null) { ret = new GeneralPath(s); }
/* 307 */       else { ret.append(s, false); }
/*     */     
/* 309 */     }  return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline(float x, float y) {
/* 317 */     Shape outline = getOutline();
/* 318 */     AffineTransform tr = AffineTransform.getTranslateInstance(x, y);
/* 319 */     outline = tr.createTransformedShape(outline);
/* 320 */     return outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D(AttributedCharacterIterator aci) {
/* 328 */     Rectangle2D ret = null;
/* 329 */     int begin = aci.getBeginIndex();
/* 330 */     for (GVTGlyphVector gv : this.gvs) {
/* 331 */       int end = gv.getCharacterCount(0, gv.getNumGlyphs()) + 1;
/* 332 */       Rectangle2D b = gv.getBounds2D((AttributedCharacterIterator)new AttributedCharacterSpanIterator(aci, begin, end));
/*     */       
/* 334 */       if (ret == null) { ret = b; }
/*     */       else
/* 336 */       { ret.add(b); }
/* 337 */        begin = end;
/*     */     } 
/* 339 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometricBounds() {
/* 348 */     Rectangle2D ret = null;
/* 349 */     for (GVTGlyphVector gv : this.gvs) {
/* 350 */       Rectangle2D b = gv.getGeometricBounds();
/* 351 */       if (ret == null) { ret = b; }
/*     */       else
/* 353 */       { ret.add(b); }
/*     */     
/* 355 */     }  return ret;
/*     */   }
/*     */   
/*     */   public void performDefaultLayout() {
/* 359 */     for (GVTGlyphVector gv : this.gvs) {
/* 360 */       gv.performDefaultLayout();
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
/*     */ 
/*     */   
/*     */   public int getCharacterCount(int startGlyphIndex, int endGlyphIndex) {
/* 374 */     int idx1 = getGVIdx(startGlyphIndex);
/* 375 */     int idx2 = getGVIdx(endGlyphIndex);
/* 376 */     int ret = 0;
/* 377 */     for (int idx = idx1; idx <= idx2; idx++) {
/* 378 */       int gi1 = startGlyphIndex - this.off[idx];
/* 379 */       int gi2 = endGlyphIndex - this.off[idx];
/* 380 */       if (gi2 >= this.nGlyphs[idx]) {
/* 381 */         gi2 = this.nGlyphs[idx] - 1;
/*     */       }
/* 383 */       ret += this.gvs[idx].getCharacterCount(gi1, gi2);
/* 384 */       startGlyphIndex += gi2 - gi1 + 1;
/*     */     } 
/* 386 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReversed() {
/* 391 */     return false;
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
/*     */   public void draw(Graphics2D g2d, AttributedCharacterIterator aci) {
/* 403 */     int begin = aci.getBeginIndex();
/* 404 */     for (GVTGlyphVector gv : this.gvs) {
/* 405 */       int end = gv.getCharacterCount(0, gv.getNumGlyphs()) + 1;
/* 406 */       gv.draw(g2d, (AttributedCharacterIterator)new AttributedCharacterSpanIterator(aci, begin, end));
/* 407 */       begin = end;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/MultiGlyphVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */