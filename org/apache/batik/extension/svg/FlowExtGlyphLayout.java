/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.bridge.GlyphLayout;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ import org.apache.batik.gvt.font.MultiGlyphVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlowExtGlyphLayout
/*     */   extends GlyphLayout
/*     */ {
/*     */   public FlowExtGlyphLayout(AttributedCharacterIterator aci, int[] charMap, Point2D offset, FontRenderContext frc) {
/*  45 */     super(aci, charMap, offset, frc);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void textWrapTextChunk(AttributedCharacterIterator[] acis, List chunkLayouts, List flowRects) {
/* 125 */     GVTGlyphVector[] gvs = new GVTGlyphVector[acis.length];
/* 126 */     List[] chunkLineInfos = new List[acis.length];
/* 127 */     GlyphIterator[] gis = new GlyphIterator[acis.length];
/* 128 */     Iterator<List> clIter = chunkLayouts.iterator();
/*     */ 
/*     */     
/* 131 */     Iterator<RegionInfo> flowRectsIter = flowRects.iterator();
/*     */     
/* 133 */     RegionInfo currentRegion = null;
/* 134 */     float height = 0.0F;
/* 135 */     if (flowRectsIter.hasNext()) {
/* 136 */       currentRegion = flowRectsIter.next();
/* 137 */       height = (float)currentRegion.getHeight();
/*     */     } 
/*     */     
/* 140 */     boolean lineHeightRelative = true;
/* 141 */     float lineHeight = 1.0F;
/* 142 */     float nextLineMult = 0.0F;
/* 143 */     float dy = 0.0F;
/*     */ 
/*     */     
/* 146 */     Point2D.Float verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */     
/* 150 */     float prevBotMargin = 0.0F; int chunk;
/* 151 */     for (chunk = 0; clIter.hasNext(); chunk++) {
/*     */       
/* 153 */       AttributedCharacterIterator aci = acis[chunk];
/* 154 */       if (currentRegion != null) {
/*     */         
/* 156 */         List extraP = (List)aci.getAttribute(FLOW_EMPTY_PARAGRAPH);
/* 157 */         if (extraP != null) {
/* 158 */           Iterator<MarginInfo> epi = extraP.iterator();
/* 159 */           while (epi.hasNext()) {
/* 160 */             MarginInfo emi = epi.next();
/* 161 */             float inc = (prevBotMargin > emi.getTopMargin()) ? prevBotMargin : emi.getTopMargin();
/*     */ 
/*     */             
/* 164 */             if (dy + inc <= height && !emi.isFlowRegionBreak()) {
/*     */               
/* 166 */               dy += inc;
/* 167 */               prevBotMargin = emi.getBottomMargin();
/*     */               continue;
/*     */             } 
/* 170 */             if (!flowRectsIter.hasNext()) {
/* 171 */               currentRegion = null;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 176 */             currentRegion = flowRectsIter.next();
/* 177 */             height = (float)currentRegion.getHeight();
/*     */             
/* 179 */             verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */             
/* 183 */             dy = 0.0F;
/* 184 */             prevBotMargin = 0.0F;
/*     */           } 
/*     */ 
/*     */           
/* 188 */           if (currentRegion == null)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 192 */       List<GVTGlyphVector> gvl = new LinkedList();
/* 193 */       List<GlyphLayout> layouts = clIter.next();
/* 194 */       Iterator<GlyphLayout> iter = layouts.iterator();
/* 195 */       while (iter.hasNext()) {
/* 196 */         GlyphLayout gl = iter.next();
/* 197 */         gvl.add(gl.getGlyphVector());
/*     */       } 
/* 199 */       MultiGlyphVector multiGlyphVector = new MultiGlyphVector(gvl);
/* 200 */       gvs[chunk] = (GVTGlyphVector)multiGlyphVector;
/* 201 */       int numGlyphs = multiGlyphVector.getNumGlyphs();
/*     */ 
/*     */ 
/*     */       
/* 205 */       aci.first();
/* 206 */       MarginInfo mi = (MarginInfo)aci.getAttribute(FLOW_PARAGRAPH);
/* 207 */       if (mi != null)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 212 */         if (currentRegion == null) {
/* 213 */           for (int idx = 0; idx < numGlyphs; idx++) {
/* 214 */             multiGlyphVector.setGlyphVisible(idx, false);
/*     */           }
/*     */         } else {
/*     */           
/* 218 */           float inc = (prevBotMargin > mi.getTopMargin()) ? prevBotMargin : mi.getTopMargin();
/*     */           
/* 220 */           if (dy + inc <= height) {
/* 221 */             dy += inc;
/*     */           }
/*     */           else {
/*     */             
/* 225 */             if (!flowRectsIter.hasNext()) {
/* 226 */               currentRegion = null;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 231 */             currentRegion = flowRectsIter.next();
/* 232 */             height = (float)currentRegion.getHeight();
/*     */             
/* 234 */             verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */             
/* 237 */             dy = mi.getTopMargin();
/*     */           } 
/* 239 */           prevBotMargin = mi.getBottomMargin();
/*     */           
/* 241 */           float leftMargin = mi.getLeftMargin();
/* 242 */           float rightMargin = mi.getRightMargin();
/* 243 */           if (((GlyphLayout)layouts.get(0)).isLeftToRight()) {
/* 244 */             leftMargin += mi.getIndent();
/*     */           } else {
/* 246 */             rightMargin += mi.getIndent();
/*     */           } 
/*     */           
/* 249 */           float x0 = (float)currentRegion.getX() + leftMargin;
/* 250 */           float y0 = (float)currentRegion.getY();
/* 251 */           float width = (float)(currentRegion.getWidth() - (leftMargin + rightMargin));
/*     */           
/* 253 */           height = (float)currentRegion.getHeight();
/*     */           
/* 255 */           List<LineInfo> lineInfos = new LinkedList();
/* 256 */           chunkLineInfos[chunk] = lineInfos;
/*     */           
/* 258 */           float prevDesc = 0.0F;
/* 259 */           GlyphIterator gi = new GlyphIterator(aci, (GVTGlyphVector)multiGlyphVector);
/* 260 */           gis[chunk] = gi;
/*     */           
/* 262 */           GlyphIterator breakGI = null, newBreakGI = null;
/*     */           
/* 264 */           if (!gi.done() && !gi.isPrinting()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 270 */             updateVerticalAlignOffset(verticalAlignOffset, currentRegion, dy);
/*     */             
/* 272 */             lineInfos.add(gi.newLine(new Point2D.Float(x0, y0 + dy), width, true, verticalAlignOffset));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 278 */           GlyphIterator lineGI = gi.copy();
/* 279 */           boolean firstLine = true;
/* 280 */           while (!gi.done()) {
/* 281 */             float lineBoxHeight; boolean doBreak = false;
/* 282 */             boolean partial = false;
/*     */             
/* 284 */             if (gi.isPrinting() && gi.getAdv() > width) {
/* 285 */               if (breakGI == null) {
/*     */ 
/*     */                 
/* 288 */                 if (!flowRectsIter.hasNext()) {
/* 289 */                   currentRegion = null;
/* 290 */                   gi = lineGI.copy(gi);
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */                 
/* 295 */                 currentRegion = flowRectsIter.next();
/* 296 */                 x0 = (float)currentRegion.getX() + leftMargin;
/* 297 */                 y0 = (float)currentRegion.getY();
/* 298 */                 width = (float)(currentRegion.getWidth() - (leftMargin + rightMargin));
/*     */                 
/* 300 */                 height = (float)currentRegion.getHeight();
/*     */                 
/* 302 */                 verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */                 
/* 305 */                 dy = firstLine ? mi.getTopMargin() : 0.0F;
/* 306 */                 prevDesc = 0.0F;
/* 307 */                 gi = lineGI.copy(gi);
/*     */                 
/*     */                 continue;
/*     */               } 
/* 311 */               gi = breakGI.copy(gi);
/*     */               
/* 313 */               nextLineMult = 1.0F;
/* 314 */               doBreak = true;
/* 315 */               partial = false;
/* 316 */             } else if (gi.isLastChar()) {
/* 317 */               nextLineMult = 1.0F;
/* 318 */               doBreak = true;
/* 319 */               partial = true;
/*     */             } 
/* 321 */             int lnBreaks = gi.getLineBreaks();
/* 322 */             if (lnBreaks != 0) {
/* 323 */               if (doBreak)
/* 324 */                 nextLineMult--; 
/* 325 */               nextLineMult += lnBreaks;
/* 326 */               doBreak = true;
/* 327 */               partial = true;
/*     */             } 
/*     */             
/* 330 */             if (!doBreak) {
/*     */ 
/*     */ 
/*     */               
/* 334 */               if (gi.isBreakChar() || breakGI == null || !breakGI.isBreakChar()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 341 */                 newBreakGI = gi.copy(newBreakGI);
/* 342 */                 gi.nextChar();
/* 343 */                 if (gi.getChar() != '‍') {
/* 344 */                   GlyphIterator tmpGI = breakGI;
/* 345 */                   breakGI = newBreakGI;
/* 346 */                   newBreakGI = tmpGI;
/*     */                 }  continue;
/*     */               } 
/* 349 */               gi.nextChar();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 366 */             float lineSize = gi.getMaxAscent() + gi.getMaxDescent();
/*     */             
/* 368 */             if (lineHeightRelative) {
/* 369 */               lineBoxHeight = gi.getMaxFontSize() * lineHeight;
/*     */             } else {
/* 371 */               lineBoxHeight = lineHeight;
/* 372 */             }  float halfLeading = (lineBoxHeight - lineSize) / 2.0F;
/*     */             
/* 374 */             float ladv = prevDesc + halfLeading + gi.getMaxAscent();
/* 375 */             float newDesc = halfLeading + gi.getMaxDescent();
/*     */             
/* 377 */             dy += ladv;
/* 378 */             float bottomEdge = newDesc;
/* 379 */             if (newDesc < gi.getMaxDescent()) {
/* 380 */               bottomEdge = gi.getMaxDescent();
/*     */             }
/* 382 */             if (dy + bottomEdge > height) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 389 */               if (!flowRectsIter.hasNext()) {
/* 390 */                 currentRegion = null;
/* 391 */                 gi = lineGI.copy(gi);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 396 */               float oldWidth = width;
/*     */ 
/*     */               
/* 399 */               currentRegion = flowRectsIter.next();
/* 400 */               x0 = (float)currentRegion.getX() + leftMargin;
/* 401 */               y0 = (float)currentRegion.getY();
/* 402 */               width = (float)(currentRegion.getWidth() - (leftMargin + rightMargin));
/*     */               
/* 404 */               height = (float)currentRegion.getHeight();
/*     */               
/* 406 */               verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */               
/* 409 */               dy = firstLine ? mi.getTopMargin() : 0.0F;
/* 410 */               prevDesc = 0.0F;
/*     */ 
/*     */               
/* 413 */               if (oldWidth > width || lnBreaks != 0)
/*     */               {
/* 415 */                 gi = lineGI.copy(gi);
/*     */               }
/*     */               
/*     */               continue;
/*     */             } 
/* 420 */             prevDesc = newDesc + (nextLineMult - 1.0F) * lineBoxHeight;
/* 421 */             nextLineMult = 0.0F;
/* 422 */             updateVerticalAlignOffset(verticalAlignOffset, currentRegion, dy + bottomEdge);
/*     */             
/* 424 */             lineInfos.add(gi.newLine(new Point2D.Float(x0, y0 + dy), width, partial, verticalAlignOffset));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 429 */             x0 -= leftMargin;
/* 430 */             width += leftMargin + rightMargin;
/*     */             
/* 432 */             leftMargin = mi.getLeftMargin();
/* 433 */             rightMargin = mi.getRightMargin();
/* 434 */             x0 += leftMargin;
/* 435 */             width -= leftMargin + rightMargin;
/*     */             
/* 437 */             firstLine = false;
/*     */             
/* 439 */             lineGI = gi.copy(lineGI);
/* 440 */             breakGI = null;
/*     */           } 
/* 442 */           dy += prevDesc;
/*     */           
/* 444 */           int idx = gi.getGlyphIndex();
/* 445 */           while (idx < numGlyphs) {
/* 446 */             multiGlyphVector.setGlyphVisible(idx++, false);
/*     */           }
/* 448 */           if (mi.isFlowRegionBreak()) {
/*     */             
/* 450 */             currentRegion = null;
/* 451 */             if (flowRectsIter.hasNext()) {
/* 452 */               currentRegion = flowRectsIter.next();
/* 453 */               height = (float)currentRegion.getHeight();
/*     */ 
/*     */ 
/*     */               
/* 457 */               dy = 0.0F;
/* 458 */               prevBotMargin = 0.0F;
/* 459 */               verticalAlignOffset = new Point2D.Float(0.0F, 0.0F);
/*     */             } 
/*     */           } 
/*     */         }  } 
/*     */     } 
/* 464 */     for (chunk = 0; chunk < acis.length; chunk++) {
/* 465 */       List lineInfos = chunkLineInfos[chunk];
/* 466 */       if (lineInfos != null) {
/*     */         
/* 468 */         AttributedCharacterIterator aci = acis[chunk];
/* 469 */         aci.first();
/* 470 */         MarginInfo mi = (MarginInfo)aci.getAttribute(FLOW_PARAGRAPH);
/* 471 */         if (mi != null) {
/*     */ 
/*     */           
/* 474 */           int justification = mi.getJustification();
/*     */           
/* 476 */           GVTGlyphVector gv = gvs[chunk];
/* 477 */           if (gv == null)
/*     */             break; 
/* 479 */           GlyphIterator gi = gis[chunk];
/*     */           
/* 481 */           layoutChunk(gv, gi.getOrigin(), justification, lineInfos);
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateVerticalAlignOffset(Point2D.Float verticalAlignOffset, RegionInfo region, float maxDescent) {
/* 508 */     float freeSpace = (float)region.getHeight() - maxDescent;
/* 509 */     verticalAlignOffset.setLocation(0.0F, region.getVerticalAlignment() * freeSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void layoutChunk(GVTGlyphVector gv, Point2D origin, int justification, List lineInfos) {
/* 516 */     Iterator<LineInfo> lInfoIter = lineInfos.iterator();
/* 517 */     int numGlyphs = gv.getNumGlyphs();
/* 518 */     float[] gp = gv.getGlyphPositions(0, numGlyphs + 1, null);
/* 519 */     Point2D.Float lineLoc = null;
/* 520 */     float lineAdv = 0.0F;
/* 521 */     float lineVAdv = 0.0F;
/*     */     
/* 523 */     float xOrig = (float)origin.getX();
/* 524 */     float yOrig = (float)origin.getY();
/*     */     
/* 526 */     float xScale = 1.0F;
/* 527 */     float xAdj = 0.0F;
/* 528 */     float charW = 0.0F;
/* 529 */     float lineWidth = 0.0F;
/* 530 */     boolean partial = false;
/* 531 */     float verticalAlignOffset = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 535 */     int lineEnd = 0;
/*     */     
/* 537 */     Point2D.Float pos = new Point2D.Float(); int i;
/* 538 */     for (i = 0; i < numGlyphs; i++) {
/* 539 */       if (i == lineEnd) {
/*     */ 
/*     */ 
/*     */         
/* 543 */         xOrig += lineAdv;
/*     */ 
/*     */         
/* 546 */         if (!lInfoIter.hasNext())
/*     */           break; 
/* 548 */         LineInfo li = lInfoIter.next();
/*     */ 
/*     */         
/* 551 */         lineEnd = li.getEndIdx();
/* 552 */         lineLoc = li.getLocation();
/* 553 */         lineAdv = li.getAdvance();
/* 554 */         lineVAdv = li.getVisualAdvance();
/* 555 */         charW = li.getLastCharWidth();
/* 556 */         lineWidth = li.getLineWidth();
/* 557 */         partial = li.isPartialLine();
/* 558 */         verticalAlignOffset = (li.getVerticalAlignOffset()).y;
/*     */         
/* 560 */         xAdj = 0.0F;
/* 561 */         xScale = 1.0F;
/*     */         
/* 563 */         switch (justification) {
/*     */           
/*     */           case 1:
/* 566 */             xAdj = (lineWidth - lineVAdv) / 2.0F;
/*     */             break;
/*     */           case 2:
/* 569 */             xAdj = lineWidth - lineVAdv;
/*     */             break;
/*     */           case 3:
/* 572 */             if (!partial && lineEnd != i + 1)
/*     */             {
/*     */               
/* 575 */               xScale = (lineWidth - charW) / (lineVAdv - charW);
/*     */             }
/*     */             break;
/*     */         } 
/*     */       } 
/* 580 */       pos.x = lineLoc.x + (gp[2 * i] - xOrig) * xScale + xAdj;
/* 581 */       lineLoc.y += gp[2 * i + 1] - yOrig + verticalAlignOffset;
/*     */       
/* 583 */       gv.setGlyphPosition(i, pos);
/*     */     } 
/*     */     
/* 586 */     pos.x = xOrig;
/* 587 */     pos.y = yOrig;
/* 588 */     if (lineLoc != null) {
/* 589 */       pos.x = lineLoc.x + (gp[2 * i] - xOrig) * xScale + xAdj;
/* 590 */       pos.y = lineLoc.y + gp[2 * i + 1] - yOrig + verticalAlignOffset;
/*     */     } 
/* 592 */     gv.setGlyphPosition(i, pos);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowExtGlyphLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */