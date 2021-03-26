/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.TextAttribute;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.text.AttributedString;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.gvt.font.GVTFont;
/*      */ import org.apache.batik.gvt.font.GVTFontFamily;
/*      */ import org.apache.batik.gvt.font.GVTGlyphMetrics;
/*      */ import org.apache.batik.gvt.font.GVTLineMetrics;
/*      */ import org.apache.batik.gvt.text.AttributedCharacterSpanIterator;
/*      */ import org.apache.batik.gvt.text.BidiAttributedCharacterIterator;
/*      */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*      */ import org.apache.batik.gvt.text.TextPaintInfo;
/*      */ import org.apache.batik.gvt.text.TextPath;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StrokingTextPainter
/*      */   extends BasicTextPainter
/*      */ {
/*   67 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*      */ 
/*      */ 
/*      */   
/*   71 */   public static final AttributedCharacterIterator.Attribute FLOW_REGIONS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_REGIONS;
/*      */ 
/*      */ 
/*      */   
/*   75 */   public static final AttributedCharacterIterator.Attribute FLOW_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_PARAGRAPH;
/*      */ 
/*      */ 
/*      */   
/*   79 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_ID = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_ID;
/*      */ 
/*      */ 
/*      */   
/*   83 */   public static final AttributedCharacterIterator.Attribute GVT_FONT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONT;
/*      */ 
/*      */ 
/*      */   
/*   87 */   public static final AttributedCharacterIterator.Attribute GVT_FONTS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONTS;
/*      */ 
/*      */ 
/*      */   
/*   91 */   public static final AttributedCharacterIterator.Attribute BIDI_LEVEL = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.BIDI_LEVEL;
/*      */ 
/*      */ 
/*      */   
/*   95 */   public static final AttributedCharacterIterator.Attribute XPOS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.X;
/*      */ 
/*      */ 
/*      */   
/*   99 */   public static final AttributedCharacterIterator.Attribute YPOS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.Y;
/*      */ 
/*      */ 
/*      */   
/*  103 */   public static final AttributedCharacterIterator.Attribute TEXTPATH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXTPATH;
/*      */ 
/*      */ 
/*      */   
/*  107 */   public static final AttributedCharacterIterator.Attribute WRITING_MODE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE;
/*      */ 
/*      */   
/*  110 */   public static final Integer WRITING_MODE_TTB = GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_TTB;
/*      */ 
/*      */   
/*  113 */   public static final Integer WRITING_MODE_RTL = GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_RTL;
/*      */ 
/*      */ 
/*      */   
/*  117 */   public static final AttributedCharacterIterator.Attribute ANCHOR_TYPE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ANCHOR_TYPE;
/*      */ 
/*      */   
/*  120 */   public static final Integer ADJUST_SPACING = GVTAttributedCharacterIterator.TextAttribute.ADJUST_SPACING;
/*      */   
/*  122 */   public static final Integer ADJUST_ALL = GVTAttributedCharacterIterator.TextAttribute.ADJUST_ALL;
/*      */   
/*  124 */   public static final GVTAttributedCharacterIterator.TextAttribute ALT_GLYPH_HANDLER = GVTAttributedCharacterIterator.TextAttribute.ALT_GLYPH_HANDLER;
/*      */ 
/*      */   
/*  127 */   static Set extendedAtts = new HashSet();
/*      */   
/*      */   static {
/*  130 */     extendedAtts.add(FLOW_PARAGRAPH);
/*  131 */     extendedAtts.add(TEXT_COMPOUND_ID);
/*  132 */     extendedAtts.add(GVT_FONT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   protected static TextPainter singleton = new StrokingTextPainter();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static TextPainter getInstance() {
/*  145 */     return singleton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void paint(TextNode node, Graphics2D g2d) {
/*  156 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/*  157 */     if (aci == null) {
/*      */       return;
/*      */     }
/*  160 */     List textRuns = getTextRuns(node, aci);
/*      */ 
/*      */ 
/*      */     
/*  164 */     paintDecorations(textRuns, g2d, 1);
/*  165 */     paintDecorations(textRuns, g2d, 4);
/*  166 */     paintTextRuns(textRuns, g2d);
/*  167 */     paintDecorations(textRuns, g2d, 2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void printAttrs(AttributedCharacterIterator aci) {
/*  172 */     aci.first();
/*  173 */     int start = aci.getBeginIndex();
/*  174 */     System.out.print("AttrRuns: ");
/*  175 */     while (aci.current() != Character.MAX_VALUE) {
/*  176 */       int end = aci.getRunLimit();
/*  177 */       System.out.print("" + (end - start) + ", ");
/*  178 */       aci.setIndex(end);
/*  179 */       start = end;
/*      */     } 
/*  181 */     System.out.println("");
/*      */   }
/*      */   
/*      */   public List getTextRuns(TextNode node, AttributedCharacterIterator aci) {
/*  185 */     List textRuns = node.getTextRuns();
/*  186 */     if (textRuns != null) {
/*  187 */       return textRuns;
/*      */     }
/*      */     
/*  190 */     AttributedCharacterIterator[] chunkACIs = getTextChunkACIs(aci);
/*  191 */     textRuns = computeTextRuns(node, aci, chunkACIs);
/*      */ 
/*      */     
/*  194 */     node.setTextRuns(textRuns);
/*  195 */     return node.getTextRuns();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List computeTextRuns(TextNode node, AttributedCharacterIterator aci, AttributedCharacterIterator[] chunkACIs) {
/*  201 */     int[][] chunkCharMaps = new int[chunkACIs.length][];
/*      */ 
/*      */     
/*  204 */     int chunkStart = aci.getBeginIndex();
/*  205 */     for (int i = 0; i < chunkACIs.length; i++) {
/*      */       
/*  207 */       BidiAttributedCharacterIterator iter = new BidiAttributedCharacterIterator(chunkACIs[i], this.fontRenderContext, chunkStart);
/*      */       
/*  209 */       chunkACIs[i] = (AttributedCharacterIterator)iter;
/*  210 */       chunkCharMaps[i] = iter.getCharMap();
/*  211 */       chunkStart += chunkACIs[i].getEndIndex() - chunkACIs[i].getBeginIndex();
/*      */     } 
/*      */     
/*  214 */     return computeTextRuns(node, aci, chunkACIs, chunkCharMaps);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List computeTextRuns(TextNode node, AttributedCharacterIterator aci, AttributedCharacterIterator[] chunkACIs, int[][] chunkCharMaps) {
/*      */     TextChunk chunk;
/*  222 */     int chunkStart = aci.getBeginIndex();
/*  223 */     for (int i = 0; i < chunkACIs.length; i++) {
/*  224 */       chunkACIs[i] = createModifiedACIForFontMatching(chunkACIs[i]);
/*  225 */       chunkStart += chunkACIs[i].getEndIndex() - chunkACIs[i].getBeginIndex();
/*      */     } 
/*      */ 
/*      */     
/*  229 */     List perNodeRuns = new ArrayList();
/*  230 */     TextChunk prevChunk = null;
/*  231 */     int currentChunk = 0;
/*      */     
/*  233 */     Point2D location = node.getLocation();
/*      */ 
/*      */     
/*      */     do {
/*  237 */       chunkACIs[currentChunk].first();
/*      */       
/*  239 */       List perChunkRuns = new ArrayList();
/*  240 */       chunk = getTextChunk(node, chunkACIs[currentChunk], (chunkCharMaps != null) ? chunkCharMaps[currentChunk] : null, perChunkRuns, prevChunk);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  248 */       perChunkRuns = reorderTextRuns(chunk, perChunkRuns);
/*      */ 
/*      */       
/*  251 */       chunkACIs[currentChunk].first();
/*  252 */       if (chunk != null) {
/*  253 */         location = adjustChunkOffsets(location, perChunkRuns, chunk);
/*      */       }
/*      */ 
/*      */       
/*  257 */       perNodeRuns.addAll(perChunkRuns);
/*      */       
/*  259 */       prevChunk = chunk;
/*  260 */       currentChunk++;
/*      */     }
/*  262 */     while (chunk != null && currentChunk < chunkACIs.length);
/*      */     
/*  264 */     return perNodeRuns;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List reorderTextRuns(TextChunk chunk, List runs) {
/*  278 */     return runs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AttributedCharacterIterator[] getTextChunkACIs(AttributedCharacterIterator aci) {
/*  288 */     List<AttributedCharacterSpanIterator> aciList = new ArrayList();
/*  289 */     int chunkStartIndex = aci.getBeginIndex();
/*  290 */     aci.first();
/*  291 */     Object writingMode = aci.getAttribute(WRITING_MODE);
/*  292 */     boolean vertical = (writingMode == WRITING_MODE_TTB);
/*      */     
/*  294 */     while (aci.setIndex(chunkStartIndex) != Character.MAX_VALUE) {
/*  295 */       TextPath prevTextPath = null;
/*  296 */       int start = chunkStartIndex, end = 0;
/*  297 */       for (; aci.setIndex(start) != Character.MAX_VALUE; start = end) {
/*      */         
/*  299 */         TextPath textPath = (TextPath)aci.getAttribute(TEXTPATH);
/*      */         
/*  301 */         if (start != chunkStartIndex) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  307 */           if (vertical) {
/*  308 */             Float runY = (Float)aci.getAttribute(YPOS);
/*      */             
/*  310 */             if (runY != null && !runY.isNaN())
/*      */               break; 
/*      */           } else {
/*  313 */             Float runX = (Float)aci.getAttribute(XPOS);
/*      */             
/*  315 */             if (runX != null && !runX.isNaN()) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */           
/*  320 */           if (prevTextPath == null && textPath != null) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  326 */           if (prevTextPath != null && textPath == null) {
/*      */             break;
/*      */           }
/*      */         } 
/*  330 */         prevTextPath = textPath;
/*      */ 
/*      */ 
/*      */         
/*  334 */         if (aci.getAttribute(FLOW_PARAGRAPH) != null) {
/*  335 */           end = aci.getRunLimit(FLOW_PARAGRAPH);
/*  336 */           aci.setIndex(end);
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/*  341 */         end = aci.getRunLimit(TEXT_COMPOUND_ID);
/*      */         
/*  343 */         if (start != chunkStartIndex) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  352 */         TextNode.Anchor anchor = (TextNode.Anchor)aci.getAttribute(ANCHOR_TYPE);
/*  353 */         if (anchor == TextNode.Anchor.START) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  361 */         if (vertical) {
/*  362 */           Float runY = (Float)aci.getAttribute(YPOS);
/*      */           
/*  364 */           if (runY == null || runY.isNaN()) {
/*      */             continue;
/*      */           }
/*      */         } else {
/*  368 */           Float runX = (Float)aci.getAttribute(XPOS);
/*      */           
/*  370 */           if (runX == null || runX.isNaN()) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  377 */         for (int j = start + 1; j < end; j++) {
/*  378 */           aci.setIndex(j);
/*  379 */           if (vertical) {
/*  380 */             Float runY = (Float)aci.getAttribute(YPOS);
/*  381 */             if (runY == null || runY.isNaN())
/*      */               break; 
/*      */           } else {
/*  384 */             Float runX = (Float)aci.getAttribute(XPOS);
/*  385 */             if (runX == null || runX.isNaN())
/*      */               break; 
/*      */           } 
/*  388 */           aciList.add(new AttributedCharacterSpanIterator(aci, j - 1, j));
/*      */           
/*  390 */           chunkStartIndex = j;
/*      */         } 
/*      */         
/*      */         continue;
/*      */       } 
/*  395 */       int chunkEndIndex = aci.getIndex();
/*  396 */       aciList.add(new AttributedCharacterSpanIterator(aci, chunkStartIndex, chunkEndIndex));
/*      */ 
/*      */       
/*  399 */       chunkStartIndex = chunkEndIndex;
/*      */     } 
/*      */ 
/*      */     
/*  403 */     AttributedCharacterIterator[] aciArray = new AttributedCharacterIterator[aciList.size()];
/*      */     
/*  405 */     Iterator<AttributedCharacterSpanIterator> iter = aciList.iterator();
/*  406 */     for (int i = 0; iter.hasNext(); i++) {
/*  407 */       aciArray[i] = (AttributedCharacterIterator)iter.next();
/*      */     }
/*  409 */     return aciArray;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AttributedCharacterIterator createModifiedACIForFontMatching(AttributedCharacterIterator aci) {
/*  425 */     aci.first();
/*  426 */     AttributedString as = null;
/*  427 */     int asOff = 0;
/*  428 */     int begin = aci.getBeginIndex();
/*  429 */     boolean moreChunks = true;
/*  430 */     int end = aci.getRunStart(TEXT_COMPOUND_ID);
/*  431 */     while (moreChunks) {
/*  432 */       int start = end;
/*  433 */       end = aci.getRunLimit(TEXT_COMPOUND_ID);
/*  434 */       int aciLength = end - start;
/*      */ 
/*      */       
/*  437 */       List<GVTFont> fonts = (List)aci.getAttribute(GVT_FONTS);
/*      */ 
/*      */       
/*  440 */       float fontSize = 12.0F;
/*  441 */       Float fsFloat = (Float)aci.getAttribute(TextAttribute.SIZE);
/*  442 */       if (fsFloat != null) {
/*  443 */         fontSize = fsFloat.floatValue();
/*      */       }
/*      */ 
/*      */       
/*  447 */       if (fonts.size() == 0)
/*      */       {
/*  449 */         fonts.add(getFontFamilyResolver().getDefault().deriveFont(fontSize, aci));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  454 */       boolean[] fontAssigned = new boolean[aciLength];
/*      */       
/*  456 */       if (as == null) {
/*  457 */         as = new AttributedString(aci);
/*      */       }
/*  459 */       GVTFont defaultFont = null;
/*  460 */       int numSet = 0;
/*  461 */       int firstUnset = start;
/*      */       
/*  463 */       for (GVTFont font1 : fonts) {
/*      */ 
/*      */         
/*  466 */         int currentIndex = firstUnset;
/*  467 */         boolean firstUnsetSet = false;
/*  468 */         aci.setIndex(currentIndex);
/*      */         
/*  470 */         GVTFont font = font1;
/*  471 */         if (defaultFont == null) {
/*  472 */           defaultFont = font;
/*      */         }
/*  474 */         while (currentIndex < end) {
/*  475 */           int displayUpToIndex = font.canDisplayUpTo(aci, currentIndex, end);
/*      */ 
/*      */ 
/*      */           
/*  479 */           Object altGlyphElement = aci.getAttribute((AttributedCharacterIterator.Attribute)ALT_GLYPH_HANDLER);
/*  480 */           if (altGlyphElement != null)
/*      */           {
/*      */             
/*  483 */             displayUpToIndex = -1;
/*      */           }
/*      */           
/*  486 */           if (displayUpToIndex == -1)
/*      */           {
/*  488 */             displayUpToIndex = end;
/*      */           }
/*      */           
/*  491 */           if (displayUpToIndex <= currentIndex) {
/*  492 */             if (!firstUnsetSet) {
/*  493 */               firstUnset = currentIndex;
/*  494 */               firstUnsetSet = true;
/*      */             } 
/*      */             
/*  497 */             currentIndex++;
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  502 */           int k = -1;
/*  503 */           for (int j = currentIndex; j < displayUpToIndex; j++) {
/*  504 */             if (fontAssigned[j - start]) {
/*  505 */               if (k != -1) {
/*  506 */                 as.addAttribute(GVT_FONT, font, k - begin, j - begin);
/*      */                 
/*  508 */                 k = -1;
/*      */               }
/*      */             
/*  511 */             } else if (k == -1) {
/*  512 */               k = j;
/*      */             } 
/*  514 */             fontAssigned[j - start] = true;
/*  515 */             numSet++;
/*      */           } 
/*  517 */           if (k != -1) {
/*  518 */             as.addAttribute(GVT_FONT, font, k - begin, displayUpToIndex - begin);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  525 */           currentIndex = displayUpToIndex + 1;
/*      */         } 
/*      */ 
/*      */         
/*  529 */         if (numSet == aciLength) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/*  534 */       int runStart = -1;
/*  535 */       GVTFontFamily prevFF = null;
/*  536 */       GVTFont prevF = defaultFont;
/*  537 */       for (int i = 0; i < aciLength; i++) {
/*  538 */         if (fontAssigned[i]) {
/*  539 */           if (runStart != -1) {
/*  540 */             as.addAttribute(GVT_FONT, prevF, runStart + asOff, i + asOff);
/*      */             
/*  542 */             runStart = -1;
/*  543 */             prevF = null;
/*  544 */             prevFF = null;
/*      */           } 
/*      */         } else {
/*  547 */           char c = aci.setIndex(start + i);
/*      */           
/*  549 */           GVTFontFamily fontFamily = getFontFamilyResolver().getFamilyThatCanDisplay(c);
/*      */ 
/*      */           
/*  552 */           if (runStart == -1) {
/*      */             
/*  554 */             runStart = i;
/*  555 */             prevFF = fontFamily;
/*  556 */             if (prevFF == null)
/*  557 */             { prevF = defaultFont; }
/*      */             else
/*  559 */             { prevF = fontFamily.deriveFont(fontSize, aci); } 
/*  560 */           } else if (prevFF != fontFamily) {
/*      */             
/*  562 */             as.addAttribute(GVT_FONT, prevF, runStart + asOff, i + asOff);
/*      */ 
/*      */             
/*  565 */             runStart = i;
/*  566 */             prevFF = fontFamily;
/*  567 */             if (prevFF == null) {
/*  568 */               prevF = defaultFont;
/*      */             } else {
/*  570 */               prevF = fontFamily.deriveFont(fontSize, aci);
/*      */             } 
/*      */           } 
/*      */         } 
/*  574 */       }  if (runStart != -1) {
/*  575 */         as.addAttribute(GVT_FONT, prevF, runStart + asOff, aciLength + asOff);
/*      */       }
/*      */ 
/*      */       
/*  579 */       asOff += aciLength;
/*  580 */       if (aci.setIndex(end) == Character.MAX_VALUE) {
/*  581 */         moreChunks = false;
/*      */       }
/*  583 */       start = end;
/*      */     } 
/*  585 */     if (as != null) {
/*  586 */       return as.getIterator();
/*      */     }
/*      */     
/*  589 */     return aci;
/*      */   }
/*      */   
/*      */   protected FontFamilyResolver getFontFamilyResolver() {
/*  593 */     return DefaultFontFamilyResolver.SINGLETON;
/*      */   }
/*      */   
/*      */   protected Set getTextRunBoundaryAttributes() {
/*  597 */     return extendedAtts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TextChunk getTextChunk(TextNode node, AttributedCharacterIterator aci, int[] charMap, List<TextRun> textRuns, TextChunk prevChunk) {
/*  605 */     int beginChunk = 0;
/*  606 */     if (prevChunk != null)
/*  607 */       beginChunk = prevChunk.end; 
/*  608 */     int endChunk = beginChunk;
/*  609 */     int begin = aci.getIndex();
/*  610 */     if (aci.current() == Character.MAX_VALUE) {
/*  611 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  615 */     Point2D.Float offset = new Point2D.Float(0.0F, 0.0F);
/*  616 */     Point2D.Float advance = new Point2D.Float(0.0F, 0.0F);
/*  617 */     boolean isChunkStart = true;
/*  618 */     TextSpanLayout layout = null;
/*  619 */     Set<? extends AttributedCharacterIterator.Attribute> textRunBoundaryAttributes = getTextRunBoundaryAttributes();
/*      */     while (true) {
/*  621 */       int start = aci.getRunStart(textRunBoundaryAttributes);
/*  622 */       int end = aci.getRunLimit(textRunBoundaryAttributes);
/*      */ 
/*      */       
/*  625 */       AttributedCharacterSpanIterator attributedCharacterSpanIterator = new AttributedCharacterSpanIterator(aci, start, end);
/*      */       
/*  627 */       int[] subCharMap = new int[end - start];
/*  628 */       if (charMap != null) {
/*  629 */         System.arraycopy(charMap, start - begin, subCharMap, 0, subCharMap.length);
/*      */       } else {
/*  631 */         for (int i = 0, n = subCharMap.length; i < n; i++) {
/*  632 */           subCharMap[i] = i;
/*      */         }
/*      */       } 
/*      */       
/*  636 */       FontRenderContext frc = this.fontRenderContext;
/*  637 */       RenderingHints rh = node.getRenderingHints();
/*      */ 
/*      */       
/*  640 */       if (rh != null && rh.get(RenderingHints.KEY_TEXT_ANTIALIASING) == RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  645 */         frc = this.aaOffFontRenderContext;
/*      */       }
/*      */       
/*  648 */       layout = getTextLayoutFactory().createTextLayout((AttributedCharacterIterator)attributedCharacterSpanIterator, subCharMap, offset, frc);
/*      */ 
/*      */       
/*  651 */       textRuns.add(new TextRun(layout, (AttributedCharacterIterator)attributedCharacterSpanIterator, isChunkStart));
/*      */       
/*  653 */       Point2D layoutAdvance = layout.getAdvance2D();
/*  654 */       advance.x += (float)layoutAdvance.getX();
/*  655 */       advance.y += (float)layoutAdvance.getY();
/*      */       
/*  657 */       endChunk++;
/*  658 */       if (aci.setIndex(end) == Character.MAX_VALUE)
/*  659 */         break;  isChunkStart = false;
/*      */     } 
/*      */     
/*  662 */     return new TextChunk(beginChunk, endChunk, advance);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Point2D adjustChunkOffsets(Point2D location, List<TextRun> textRuns, TextChunk chunk) {
/*      */     Point2D visualAdvance;
/*  674 */     int numRuns = chunk.end - chunk.begin;
/*  675 */     TextRun r = textRuns.get(0);
/*  676 */     int anchorType = r.getAnchorType();
/*  677 */     Float length = r.getLength();
/*  678 */     Integer lengthAdj = r.getLengthAdjust();
/*      */     
/*  680 */     boolean doAdjust = true;
/*  681 */     if (length == null || length.isNaN()) {
/*  682 */       doAdjust = false;
/*      */     }
/*  684 */     int numChars = 0;
/*  685 */     for (int i = 0; i < numRuns; i++) {
/*  686 */       r = textRuns.get(i);
/*  687 */       AttributedCharacterIterator aci = r.getACI();
/*  688 */       numChars += aci.getEndIndex() - aci.getBeginIndex();
/*      */     } 
/*  690 */     if (lengthAdj == GVTAttributedCharacterIterator.TextAttribute.ADJUST_SPACING && numChars == 1)
/*      */     {
/*      */       
/*  693 */       doAdjust = false;
/*      */     }
/*  695 */     float xScale = 1.0F;
/*  696 */     float yScale = 1.0F;
/*      */     
/*  698 */     r = textRuns.get(numRuns - 1);
/*  699 */     TextSpanLayout layout = r.getLayout();
/*  700 */     GVTGlyphMetrics lastMetrics = layout.getGlyphMetrics(layout.getGlyphCount() - 1);
/*      */     
/*  702 */     GVTLineMetrics lastLineMetrics = layout.getLineMetrics();
/*  703 */     Rectangle2D lastBounds = lastMetrics.getBounds2D();
/*  704 */     float halfLeading = (lastMetrics.getVerticalAdvance() - lastLineMetrics.getAscent() + lastLineMetrics.getDescent()) / 2.0F;
/*      */ 
/*      */     
/*  707 */     float lastW = (float)(lastBounds.getWidth() + lastBounds.getX());
/*  708 */     float lastH = (float)((halfLeading + lastLineMetrics.getAscent()) + lastBounds.getHeight() + lastBounds.getY());
/*      */ 
/*      */ 
/*      */     
/*  712 */     if (!doAdjust) {
/*  713 */       visualAdvance = new Point2D.Float((float)chunk.advance.getX(), (float)(chunk.advance.getY() + lastH - lastMetrics.getVerticalAdvance()));
/*      */     }
/*      */     else {
/*      */       
/*  717 */       Point2D advance = chunk.advance;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  722 */       if (layout.isVertical()) {
/*  723 */         if (lengthAdj == ADJUST_SPACING) {
/*  724 */           yScale = (float)((length.floatValue() - lastH) / (advance.getY() - lastMetrics.getVerticalAdvance()));
/*      */         }
/*      */         else {
/*      */           
/*  728 */           double d = advance.getY() + lastH - lastMetrics.getVerticalAdvance();
/*      */           
/*  730 */           yScale = (float)(length.floatValue() / d);
/*      */         } 
/*  732 */         visualAdvance = new Point2D.Float(0.0F, length.floatValue());
/*      */       } else {
/*  734 */         if (lengthAdj == ADJUST_SPACING) {
/*  735 */           xScale = (float)((length.floatValue() - lastW) / (advance.getX() - lastMetrics.getHorizontalAdvance()));
/*      */         }
/*      */         else {
/*      */           
/*  739 */           double d = advance.getX() + lastW - lastMetrics.getHorizontalAdvance();
/*      */           
/*  741 */           xScale = (float)(length.floatValue() / d);
/*      */         } 
/*  743 */         visualAdvance = new Point2D.Float(length.floatValue(), 0.0F);
/*      */       } 
/*      */       
/*  746 */       Point2D.Float adv = new Point2D.Float(0.0F, 0.0F);
/*  747 */       for (int k = 0; k < numRuns; k++) {
/*  748 */         r = textRuns.get(k);
/*  749 */         layout = r.getLayout();
/*  750 */         layout.setScale(xScale, yScale, (lengthAdj == ADJUST_SPACING));
/*  751 */         Point2D lAdv = layout.getAdvance2D();
/*  752 */         adv.x += (float)lAdv.getX();
/*  753 */         adv.y += (float)lAdv.getY();
/*      */       } 
/*  755 */       chunk.advance = adv;
/*      */     } 
/*      */     
/*  758 */     float dx = 0.0F;
/*  759 */     float dy = 0.0F;
/*  760 */     switch (anchorType) {
/*      */       case 1:
/*  762 */         dx = (float)(-visualAdvance.getX() / 2.0D);
/*  763 */         dy = (float)(-visualAdvance.getY() / 2.0D);
/*      */         break;
/*      */       case 2:
/*  766 */         dx = (float)-visualAdvance.getX();
/*  767 */         dy = (float)-visualAdvance.getY();
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  773 */     r = textRuns.get(0);
/*  774 */     layout = r.getLayout();
/*  775 */     AttributedCharacterIterator runaci = r.getACI();
/*  776 */     runaci.first();
/*  777 */     boolean vertical = layout.isVertical();
/*  778 */     Float runX = (Float)runaci.getAttribute(XPOS);
/*  779 */     Float runY = (Float)runaci.getAttribute(YPOS);
/*  780 */     TextPath textPath = (TextPath)runaci.getAttribute(TEXTPATH);
/*      */ 
/*      */ 
/*      */     
/*  784 */     float absX = (float)location.getX();
/*  785 */     float absY = (float)location.getY();
/*      */     
/*  787 */     float tpShiftX = 0.0F;
/*  788 */     float tpShiftY = 0.0F;
/*      */ 
/*      */ 
/*      */     
/*  792 */     if (runX != null && !runX.isNaN()) {
/*  793 */       absX = runX.floatValue();
/*  794 */       tpShiftX = absX;
/*      */     } 
/*      */     
/*  797 */     if (runY != null && !runY.isNaN()) {
/*  798 */       absY = runY.floatValue();
/*  799 */       tpShiftY = absY;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  804 */     if (vertical) {
/*  805 */       absY += dy;
/*  806 */       tpShiftY += dy;
/*  807 */       tpShiftX = 0.0F;
/*      */     } else {
/*  809 */       absX += dx;
/*  810 */       tpShiftX += dx;
/*  811 */       tpShiftY = 0.0F;
/*      */     } 
/*      */     
/*  814 */     for (int j = 0; j < numRuns; j++) {
/*  815 */       r = textRuns.get(j);
/*  816 */       layout = r.getLayout();
/*  817 */       runaci = r.getACI();
/*  818 */       runaci.first();
/*  819 */       textPath = (TextPath)runaci.getAttribute(TEXTPATH);
/*  820 */       if (vertical) {
/*  821 */         runX = (Float)runaci.getAttribute(XPOS);
/*  822 */         if (runX != null && !runX.isNaN()) {
/*  823 */           absX = runX.floatValue();
/*      */         }
/*      */       } else {
/*  826 */         runY = (Float)runaci.getAttribute(YPOS);
/*  827 */         if (runY != null && !runY.isNaN()) {
/*  828 */           absY = runY.floatValue();
/*      */         }
/*      */       } 
/*      */       
/*  832 */       if (textPath == null) {
/*  833 */         layout.setOffset(new Point2D.Float(absX, absY));
/*      */         
/*  835 */         Point2D ladv = layout.getAdvance2D();
/*  836 */         absX = (float)(absX + ladv.getX());
/*  837 */         absY = (float)(absY + ladv.getY());
/*      */       } else {
/*  839 */         layout.setOffset(new Point2D.Float(tpShiftX, tpShiftY));
/*      */         
/*  841 */         Point2D ladv = layout.getAdvance2D();
/*  842 */         tpShiftX += (float)ladv.getX();
/*  843 */         tpShiftY += (float)ladv.getY();
/*      */         
/*  845 */         ladv = layout.getTextPathAdvance();
/*  846 */         absX = (float)ladv.getX();
/*  847 */         absY = (float)ladv.getY();
/*      */       } 
/*      */     } 
/*  850 */     return new Point2D.Float(absX, absY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintDecorations(List textRuns, Graphics2D g2d, int decorationType) {
/*  859 */     Paint prevPaint = null;
/*  860 */     Paint prevStrokePaint = null;
/*  861 */     Stroke prevStroke = null;
/*  862 */     boolean prevVisible = true;
/*  863 */     Rectangle2D decorationRect = null;
/*  864 */     double yLoc = 0.0D, height = 0.0D;
/*      */     
/*  866 */     for (Object textRun1 : textRuns) {
/*  867 */       TextRun textRun = (TextRun)textRun1;
/*  868 */       AttributedCharacterIterator runaci = textRun.getACI();
/*  869 */       runaci.first();
/*      */       
/*  871 */       Paint paint = null;
/*  872 */       Stroke stroke = null;
/*  873 */       Paint strokePaint = null;
/*  874 */       boolean visible = true;
/*  875 */       TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
/*  876 */       if (tpi != null) {
/*  877 */         visible = tpi.visible;
/*  878 */         if (tpi.composite != null) {
/*  879 */           g2d.setComposite(tpi.composite);
/*      */         }
/*  881 */         switch (decorationType) {
/*      */           case 1:
/*  883 */             paint = tpi.underlinePaint;
/*  884 */             stroke = tpi.underlineStroke;
/*  885 */             strokePaint = tpi.underlineStrokePaint;
/*      */             break;
/*      */           case 4:
/*  888 */             paint = tpi.overlinePaint;
/*  889 */             stroke = tpi.overlineStroke;
/*  890 */             strokePaint = tpi.overlineStrokePaint;
/*      */             break;
/*      */           case 2:
/*  893 */             paint = tpi.strikethroughPaint;
/*  894 */             stroke = tpi.strikethroughStroke;
/*  895 */             strokePaint = tpi.strikethroughStrokePaint;
/*      */             break;
/*      */           
/*      */           default:
/*      */             return;
/*      */         } 
/*      */       } 
/*  902 */       if (textRun.isFirstRunInChunk()) {
/*  903 */         Shape s = textRun.getLayout().getDecorationOutline(decorationType);
/*      */         
/*  905 */         Rectangle2D r2d = s.getBounds2D();
/*  906 */         yLoc = r2d.getY();
/*  907 */         height = r2d.getHeight();
/*      */       } 
/*      */       
/*  910 */       if (textRun.isFirstRunInChunk() || paint != prevPaint || stroke != prevStroke || strokePaint != prevStrokePaint || visible != prevVisible) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  916 */         if (prevVisible && decorationRect != null) {
/*  917 */           if (prevPaint != null) {
/*      */             
/*  919 */             g2d.setPaint(prevPaint);
/*  920 */             g2d.fill(decorationRect);
/*      */           } 
/*  922 */           if (prevStroke != null && prevStrokePaint != null) {
/*      */             
/*  924 */             g2d.setPaint(prevStrokePaint);
/*  925 */             g2d.setStroke(prevStroke);
/*  926 */             g2d.draw(decorationRect);
/*      */           } 
/*      */         } 
/*  929 */         decorationRect = null;
/*      */       } 
/*      */       
/*  932 */       if ((paint != null || strokePaint != null) && !textRun.getLayout().isVertical() && !textRun.getLayout().isOnATextPath()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  941 */         Shape decorationShape = textRun.getLayout().getDecorationOutline(decorationType);
/*      */         
/*  943 */         if (decorationRect == null) {
/*      */           
/*  945 */           Rectangle2D r2d = decorationShape.getBounds2D();
/*  946 */           decorationRect = new Rectangle2D.Double(r2d.getX(), yLoc, r2d.getWidth(), height);
/*      */         }
/*      */         else {
/*      */           
/*  950 */           Rectangle2D bounds = decorationShape.getBounds2D();
/*  951 */           double minX = Math.min(decorationRect.getX(), bounds.getX());
/*      */           
/*  953 */           double maxX = Math.max(decorationRect.getMaxX(), bounds.getMaxX());
/*      */           
/*  955 */           decorationRect.setRect(minX, yLoc, maxX - minX, height);
/*      */         } 
/*      */       } 
/*  958 */       prevPaint = paint;
/*  959 */       prevStroke = stroke;
/*  960 */       prevStrokePaint = strokePaint;
/*  961 */       prevVisible = visible;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  966 */     if (prevVisible && decorationRect != null) {
/*  967 */       if (prevPaint != null) {
/*      */         
/*  969 */         g2d.setPaint(prevPaint);
/*  970 */         g2d.fill(decorationRect);
/*      */       } 
/*  972 */       if (prevStroke != null && prevStrokePaint != null) {
/*      */         
/*  974 */         g2d.setPaint(prevStrokePaint);
/*  975 */         g2d.setStroke(prevStroke);
/*  976 */         g2d.draw(decorationRect);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTextRuns(List textRuns, Graphics2D g2d) {
/*  987 */     for (Object textRun1 : textRuns) {
/*  988 */       TextRun textRun = (TextRun)textRun1;
/*  989 */       AttributedCharacterIterator runaci = textRun.getACI();
/*  990 */       runaci.first();
/*      */       
/*  992 */       TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
/*  993 */       if (tpi != null && tpi.composite != null) {
/*  994 */         g2d.setComposite(tpi.composite);
/*      */       }
/*  996 */       textRun.getLayout().draw(g2d);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape getOutline(TextNode node) {
/* 1006 */     GeneralPath outline = null;
/*      */     
/* 1008 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1009 */     if (aci == null) {
/* 1010 */       return null;
/*      */     }
/*      */     
/* 1013 */     List textRuns = getTextRuns(node, aci);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1018 */     for (Object textRun1 : textRuns) {
/* 1019 */       TextRun textRun = (TextRun)textRun1;
/* 1020 */       TextSpanLayout textRunLayout = textRun.getLayout();
/* 1021 */       GeneralPath textRunOutline = new GeneralPath(textRunLayout.getOutline());
/*      */ 
/*      */       
/* 1024 */       if (outline == null) {
/* 1025 */         outline = textRunOutline; continue;
/*      */       } 
/* 1027 */       outline.setWindingRule(1);
/* 1028 */       outline.append(textRunOutline, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1033 */     Shape underline = getDecorationOutline(textRuns, 1);
/*      */ 
/*      */     
/* 1036 */     Shape strikeThrough = getDecorationOutline(textRuns, 2);
/*      */ 
/*      */     
/* 1039 */     Shape overline = getDecorationOutline(textRuns, 4);
/*      */ 
/*      */     
/* 1042 */     if (underline != null) {
/* 1043 */       if (outline == null) {
/* 1044 */         outline = new GeneralPath(underline);
/*      */       } else {
/* 1046 */         outline.setWindingRule(1);
/* 1047 */         outline.append(underline, false);
/*      */       } 
/*      */     }
/* 1050 */     if (strikeThrough != null) {
/* 1051 */       if (outline == null) {
/* 1052 */         outline = new GeneralPath(strikeThrough);
/*      */       } else {
/* 1054 */         outline.setWindingRule(1);
/* 1055 */         outline.append(strikeThrough, false);
/*      */       } 
/*      */     }
/* 1058 */     if (overline != null) {
/* 1059 */       if (outline == null) {
/* 1060 */         outline = new GeneralPath(overline);
/*      */       } else {
/* 1062 */         outline.setWindingRule(1);
/* 1063 */         outline.append(overline, false);
/*      */       } 
/*      */     }
/*      */     
/* 1067 */     return outline;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getBounds2D(TextNode node) {
/* 1077 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1078 */     if (aci == null) {
/* 1079 */       return null;
/*      */     }
/*      */     
/* 1082 */     List textRuns = getTextRuns(node, aci);
/*      */     
/* 1084 */     Rectangle2D bounds = null;
/*      */ 
/*      */     
/* 1087 */     for (Object textRun1 : textRuns) {
/* 1088 */       TextRun textRun = (TextRun)textRun1;
/* 1089 */       TextSpanLayout textRunLayout = textRun.getLayout();
/* 1090 */       Rectangle2D runBounds = textRunLayout.getBounds2D();
/* 1091 */       if (runBounds != null) {
/* 1092 */         if (bounds == null) {
/* 1093 */           bounds = runBounds; continue;
/*      */         } 
/* 1095 */         bounds.add(runBounds);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1101 */     Shape underline = getDecorationStrokeOutline(textRuns, 1);
/*      */ 
/*      */     
/* 1104 */     if (underline != null) {
/* 1105 */       if (bounds == null) {
/* 1106 */         bounds = underline.getBounds2D();
/*      */       } else {
/* 1108 */         bounds.add(underline.getBounds2D());
/*      */       } 
/*      */     }
/* 1111 */     Shape strikeThrough = getDecorationStrokeOutline(textRuns, 2);
/*      */     
/* 1113 */     if (strikeThrough != null) {
/* 1114 */       if (bounds == null) {
/* 1115 */         bounds = strikeThrough.getBounds2D();
/*      */       } else {
/* 1117 */         bounds.add(strikeThrough.getBounds2D());
/*      */       } 
/*      */     }
/* 1120 */     Shape overline = getDecorationStrokeOutline(textRuns, 4);
/*      */     
/* 1122 */     if (overline != null)
/* 1123 */       if (bounds == null) {
/* 1124 */         bounds = overline.getBounds2D();
/*      */       } else {
/* 1126 */         bounds.add(overline.getBounds2D());
/*      */       }  
/* 1128 */     return bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Shape getDecorationOutline(List textRuns, int decorationType) {
/* 1143 */     GeneralPath outline = null;
/*      */     
/* 1145 */     Paint prevPaint = null;
/* 1146 */     Paint prevStrokePaint = null;
/* 1147 */     Stroke prevStroke = null;
/* 1148 */     Rectangle2D decorationRect = null;
/* 1149 */     double yLoc = 0.0D, height = 0.0D;
/*      */     
/* 1151 */     for (Object textRun1 : textRuns) {
/* 1152 */       TextRun textRun = (TextRun)textRun1;
/* 1153 */       AttributedCharacterIterator runaci = textRun.getACI();
/* 1154 */       runaci.first();
/*      */       
/* 1156 */       Paint paint = null;
/* 1157 */       Stroke stroke = null;
/* 1158 */       Paint strokePaint = null;
/* 1159 */       TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
/* 1160 */       if (tpi != null) {
/* 1161 */         switch (decorationType) {
/*      */           case 1:
/* 1163 */             paint = tpi.underlinePaint;
/* 1164 */             stroke = tpi.underlineStroke;
/* 1165 */             strokePaint = tpi.underlineStrokePaint;
/*      */             break;
/*      */           case 4:
/* 1168 */             paint = tpi.overlinePaint;
/* 1169 */             stroke = tpi.overlineStroke;
/* 1170 */             strokePaint = tpi.overlineStrokePaint;
/*      */             break;
/*      */           case 2:
/* 1173 */             paint = tpi.strikethroughPaint;
/* 1174 */             stroke = tpi.strikethroughStroke;
/* 1175 */             strokePaint = tpi.strikethroughStrokePaint;
/*      */             break;
/*      */           
/*      */           default:
/* 1179 */             return null;
/*      */         } 
/*      */       
/*      */       }
/* 1183 */       if (textRun.isFirstRunInChunk()) {
/* 1184 */         Shape s = textRun.getLayout().getDecorationOutline(decorationType);
/*      */         
/* 1186 */         Rectangle2D r2d = s.getBounds2D();
/* 1187 */         yLoc = r2d.getY();
/* 1188 */         height = r2d.getHeight();
/*      */       } 
/*      */       
/* 1191 */       if (textRun.isFirstRunInChunk() || paint != prevPaint || stroke != prevStroke || strokePaint != prevStrokePaint)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1198 */         if (decorationRect != null) {
/* 1199 */           if (outline == null) {
/* 1200 */             outline = new GeneralPath(decorationRect);
/*      */           } else {
/* 1202 */             outline.append(decorationRect, false);
/*      */           } 
/* 1204 */           decorationRect = null;
/*      */         } 
/*      */       }
/*      */       
/* 1208 */       if ((paint != null || strokePaint != null) && !textRun.getLayout().isVertical() && !textRun.getLayout().isOnATextPath()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1216 */         Shape decorationShape = textRun.getLayout().getDecorationOutline(decorationType);
/*      */         
/* 1218 */         if (decorationRect == null) {
/*      */           
/* 1220 */           Rectangle2D r2d = decorationShape.getBounds2D();
/* 1221 */           decorationRect = new Rectangle2D.Double(r2d.getX(), yLoc, r2d.getWidth(), height);
/*      */         }
/*      */         else {
/*      */           
/* 1225 */           Rectangle2D bounds = decorationShape.getBounds2D();
/* 1226 */           double minX = Math.min(decorationRect.getX(), bounds.getX());
/*      */           
/* 1228 */           double maxX = Math.max(decorationRect.getMaxX(), bounds.getMaxX());
/*      */           
/* 1230 */           decorationRect.setRect(minX, yLoc, maxX - minX, height);
/*      */         } 
/*      */       } 
/*      */       
/* 1234 */       prevPaint = paint;
/* 1235 */       prevStroke = stroke;
/* 1236 */       prevStrokePaint = strokePaint;
/*      */     } 
/*      */ 
/*      */     
/* 1240 */     if (decorationRect != null) {
/* 1241 */       if (outline == null) {
/* 1242 */         outline = new GeneralPath(decorationRect);
/*      */       } else {
/* 1244 */         outline.append(decorationRect, false);
/*      */       } 
/*      */     }
/*      */     
/* 1248 */     return outline;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Shape getDecorationStrokeOutline(List textRuns, int decorationType) {
/* 1264 */     GeneralPath outline = null;
/*      */     
/* 1266 */     Paint prevPaint = null;
/* 1267 */     Paint prevStrokePaint = null;
/* 1268 */     Stroke prevStroke = null;
/* 1269 */     Rectangle2D decorationRect = null;
/* 1270 */     double yLoc = 0.0D, height = 0.0D;
/*      */     
/* 1272 */     for (Object textRun1 : textRuns) {
/*      */       
/* 1274 */       TextRun textRun = (TextRun)textRun1;
/* 1275 */       AttributedCharacterIterator runaci = textRun.getACI();
/* 1276 */       runaci.first();
/*      */       
/* 1278 */       Paint paint = null;
/* 1279 */       Stroke stroke = null;
/* 1280 */       Paint strokePaint = null;
/* 1281 */       TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
/* 1282 */       if (tpi != null) {
/* 1283 */         switch (decorationType) {
/*      */           case 1:
/* 1285 */             paint = tpi.underlinePaint;
/* 1286 */             stroke = tpi.underlineStroke;
/* 1287 */             strokePaint = tpi.underlineStrokePaint;
/*      */             break;
/*      */           case 4:
/* 1290 */             paint = tpi.overlinePaint;
/* 1291 */             stroke = tpi.overlineStroke;
/* 1292 */             strokePaint = tpi.overlineStrokePaint;
/*      */             break;
/*      */           case 2:
/* 1295 */             paint = tpi.strikethroughPaint;
/* 1296 */             stroke = tpi.strikethroughStroke;
/* 1297 */             strokePaint = tpi.strikethroughStrokePaint;
/*      */             break;
/*      */           default:
/* 1300 */             return null;
/*      */         } 
/*      */       
/*      */       }
/* 1304 */       if (textRun.isFirstRunInChunk()) {
/* 1305 */         Shape s = textRun.getLayout().getDecorationOutline(decorationType);
/*      */         
/* 1307 */         Rectangle2D r2d = s.getBounds2D();
/* 1308 */         yLoc = r2d.getY();
/* 1309 */         height = r2d.getHeight();
/*      */       } 
/*      */       
/* 1312 */       if (textRun.isFirstRunInChunk() || paint != prevPaint || stroke != prevStroke || strokePaint != prevStrokePaint)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1319 */         if (decorationRect != null) {
/*      */           
/* 1321 */           Shape s = null;
/* 1322 */           if (prevStroke != null && prevStrokePaint != null) {
/*      */             
/* 1324 */             s = prevStroke.createStrokedShape(decorationRect);
/* 1325 */           } else if (prevPaint != null) {
/* 1326 */             s = decorationRect;
/* 1327 */           }  if (s != null)
/* 1328 */             if (outline == null) {
/* 1329 */               outline = new GeneralPath(s);
/*      */             } else {
/* 1331 */               outline.append(s, false);
/*      */             }  
/* 1333 */           decorationRect = null;
/*      */         } 
/*      */       }
/*      */       
/* 1337 */       if ((paint != null || strokePaint != null) && !textRun.getLayout().isVertical() && !textRun.getLayout().isOnATextPath()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1345 */         Shape decorationShape = textRun.getLayout().getDecorationOutline(decorationType);
/*      */ 
/*      */         
/* 1348 */         if (decorationRect == null) {
/*      */           
/* 1350 */           Rectangle2D r2d = decorationShape.getBounds2D();
/* 1351 */           decorationRect = new Rectangle2D.Double(r2d.getX(), yLoc, r2d.getWidth(), height);
/*      */         }
/*      */         else {
/*      */           
/* 1355 */           Rectangle2D bounds = decorationShape.getBounds2D();
/* 1356 */           double minX = Math.min(decorationRect.getX(), bounds.getX());
/*      */           
/* 1358 */           double maxX = Math.max(decorationRect.getMaxX(), bounds.getMaxX());
/*      */           
/* 1360 */           decorationRect.setRect(minX, yLoc, maxX - minX, height);
/*      */         } 
/*      */       } 
/*      */       
/* 1364 */       prevPaint = paint;
/* 1365 */       prevStroke = stroke;
/* 1366 */       prevStrokePaint = strokePaint;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1371 */     if (decorationRect != null) {
/* 1372 */       Shape s = null;
/* 1373 */       if (prevStroke != null && prevStrokePaint != null) {
/*      */         
/* 1375 */         s = prevStroke.createStrokedShape(decorationRect);
/* 1376 */       } else if (prevPaint != null) {
/* 1377 */         s = decorationRect;
/* 1378 */       }  if (s != null) {
/* 1379 */         if (outline == null) {
/* 1380 */           outline = new GeneralPath(s);
/*      */         } else {
/* 1382 */           outline.append(s, false);
/*      */         } 
/*      */       }
/*      */     } 
/* 1386 */     return outline;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Mark getMark(TextNode node, int index, boolean leadingEdge) {
/* 1392 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1393 */     if (aci == null) {
/* 1394 */       return null;
/*      */     }
/* 1396 */     if (index < aci.getBeginIndex() || index > aci.getEndIndex())
/*      */     {
/* 1398 */       return null;
/*      */     }
/* 1400 */     TextHit textHit = new TextHit(index, leadingEdge);
/* 1401 */     return new BasicTextPainter.BasicMark(node, textHit);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Mark hitTest(double x, double y, TextNode node) {
/* 1406 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1407 */     if (aci == null) {
/* 1408 */       return null;
/*      */     }
/*      */     
/* 1411 */     List textRuns = getTextRuns(node, aci);
/* 1412 */     if (textRuns != null)
/*      */     {
/* 1414 */       for (Object textRun1 : textRuns) {
/* 1415 */         TextRun textRun = (TextRun)textRun1;
/* 1416 */         TextSpanLayout layout = textRun.getLayout();
/* 1417 */         TextHit textHit = layout.hitTestChar((float)x, (float)y);
/* 1418 */         Rectangle2D bounds = layout.getBounds2D();
/* 1419 */         if (textHit != null && bounds != null && bounds.contains(x, y))
/*      */         {
/* 1421 */           return new BasicTextPainter.BasicMark(node, textHit);
/*      */         }
/*      */       } 
/*      */     }
/* 1425 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Mark selectFirst(TextNode node) {
/* 1433 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1434 */     if (aci == null) {
/* 1435 */       return null;
/*      */     }
/* 1437 */     TextHit textHit = new TextHit(aci.getBeginIndex(), false);
/* 1438 */     return new BasicTextPainter.BasicMark(node, textHit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Mark selectLast(TextNode node) {
/* 1446 */     AttributedCharacterIterator aci = node.getAttributedCharacterIterator();
/* 1447 */     if (aci == null) {
/* 1448 */       return null;
/*      */     }
/* 1450 */     TextHit textHit = new TextHit(aci.getEndIndex() - 1, false);
/* 1451 */     return new BasicTextPainter.BasicMark(node, textHit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getSelected(Mark startMark, Mark finishMark) {
/*      */     BasicTextPainter.BasicMark start, finish;
/* 1464 */     if (startMark == null || finishMark == null) {
/* 1465 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1470 */       start = (BasicTextPainter.BasicMark)startMark;
/* 1471 */       finish = (BasicTextPainter.BasicMark)finishMark;
/* 1472 */     } catch (ClassCastException cce) {
/* 1473 */       throw new RuntimeException("This Mark was not instantiated by this TextPainter class!");
/*      */     } 
/*      */ 
/*      */     
/* 1477 */     TextNode textNode = start.getTextNode();
/* 1478 */     if (textNode == null)
/* 1479 */       return null; 
/* 1480 */     if (textNode != finish.getTextNode()) {
/* 1481 */       throw new RuntimeException("Markers are from different TextNodes!");
/*      */     }
/*      */     
/* 1484 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 1485 */     if (aci == null) {
/* 1486 */       return null;
/*      */     }
/* 1488 */     int[] result = new int[2];
/* 1489 */     result[0] = start.getHit().getCharIndex();
/* 1490 */     result[1] = finish.getHit().getCharIndex();
/*      */ 
/*      */     
/* 1493 */     List textRuns = getTextRuns(textNode, aci);
/* 1494 */     Iterator<TextRun> trI = textRuns.iterator();
/* 1495 */     int startGlyphIndex = -1;
/* 1496 */     int endGlyphIndex = -1;
/* 1497 */     TextSpanLayout startLayout = null, endLayout = null;
/* 1498 */     while (trI.hasNext()) {
/* 1499 */       TextRun tr = trI.next();
/* 1500 */       TextSpanLayout tsl = tr.getLayout();
/* 1501 */       if (startGlyphIndex == -1) {
/* 1502 */         startGlyphIndex = tsl.getGlyphIndex(result[0]);
/* 1503 */         if (startGlyphIndex != -1) {
/* 1504 */           startLayout = tsl;
/*      */         }
/*      */       } 
/* 1507 */       if (endGlyphIndex == -1) {
/* 1508 */         endGlyphIndex = tsl.getGlyphIndex(result[1]);
/* 1509 */         if (endGlyphIndex != -1)
/* 1510 */           endLayout = tsl; 
/*      */       } 
/* 1512 */       if (startGlyphIndex != -1 && endGlyphIndex != -1)
/*      */         break; 
/*      */     } 
/* 1515 */     if (startLayout == null || endLayout == null) {
/* 1516 */       return null;
/*      */     }
/* 1518 */     int startCharCount = startLayout.getCharacterCount(startGlyphIndex, startGlyphIndex);
/*      */     
/* 1520 */     int endCharCount = endLayout.getCharacterCount(endGlyphIndex, endGlyphIndex);
/*      */     
/* 1522 */     if (startCharCount > 1) {
/* 1523 */       if (result[0] > result[1] && startLayout.isLeftToRight()) {
/* 1524 */         result[0] = result[0] + startCharCount - 1;
/* 1525 */       } else if (result[1] > result[0] && !startLayout.isLeftToRight()) {
/* 1526 */         result[0] = result[0] - startCharCount - 1;
/*      */       } 
/*      */     }
/* 1529 */     if (endCharCount > 1) {
/* 1530 */       if (result[1] > result[0] && endLayout.isLeftToRight()) {
/* 1531 */         result[1] = result[1] + endCharCount - 1;
/* 1532 */       } else if (result[0] > result[1] && !endLayout.isLeftToRight()) {
/* 1533 */         result[1] = result[1] - endCharCount - 1;
/*      */       } 
/*      */     }
/*      */     
/* 1537 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape getHighlightShape(Mark beginMark, Mark endMark) {
/*      */     BasicTextPainter.BasicMark begin, end;
/* 1548 */     if (beginMark == null || endMark == null) {
/* 1549 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1555 */       begin = (BasicTextPainter.BasicMark)beginMark;
/* 1556 */       end = (BasicTextPainter.BasicMark)endMark;
/* 1557 */     } catch (ClassCastException cce) {
/* 1558 */       throw new RuntimeException("This Mark was not instantiated by this TextPainter class!");
/*      */     } 
/*      */ 
/*      */     
/* 1562 */     TextNode textNode = begin.getTextNode();
/* 1563 */     if (textNode == null)
/* 1564 */       return null; 
/* 1565 */     if (textNode != end.getTextNode()) {
/* 1566 */       throw new RuntimeException("Markers are from different TextNodes!");
/*      */     }
/*      */     
/* 1569 */     AttributedCharacterIterator aci = textNode.getAttributedCharacterIterator();
/* 1570 */     if (aci == null) {
/* 1571 */       return null;
/*      */     }
/* 1573 */     int beginIndex = begin.getHit().getCharIndex();
/* 1574 */     int endIndex = end.getHit().getCharIndex();
/* 1575 */     if (beginIndex > endIndex) {
/*      */       
/* 1577 */       BasicTextPainter.BasicMark tmpMark = begin;
/* 1578 */       begin = end; end = tmpMark;
/*      */       
/* 1580 */       int tmpIndex = beginIndex;
/* 1581 */       beginIndex = endIndex; endIndex = tmpIndex;
/*      */     } 
/*      */ 
/*      */     
/* 1585 */     List textRuns = getTextRuns(textNode, aci);
/*      */     
/* 1587 */     GeneralPath highlightedShape = new GeneralPath();
/*      */ 
/*      */ 
/*      */     
/* 1591 */     for (Object textRun1 : textRuns) {
/* 1592 */       TextRun textRun = (TextRun)textRun1;
/* 1593 */       TextSpanLayout layout = textRun.getLayout();
/*      */       
/* 1595 */       Shape layoutHighlightedShape = layout.getHighlightShape(beginIndex, endIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1600 */       if (layoutHighlightedShape != null && !layoutHighlightedShape.getBounds().isEmpty())
/*      */       {
/* 1602 */         highlightedShape.append(layoutHighlightedShape, false);
/*      */       }
/*      */     } 
/* 1605 */     return highlightedShape;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class TextChunk
/*      */   {
/*      */     public int begin;
/*      */ 
/*      */     
/*      */     public int end;
/*      */ 
/*      */     
/*      */     public Point2D advance;
/*      */ 
/*      */     
/*      */     public TextChunk(int begin, int end, Point2D advance) {
/* 1622 */       this.begin = begin;
/* 1623 */       this.end = end;
/* 1624 */       this.advance = new Point2D.Float((float)advance.getX(), (float)advance.getY());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class TextRun
/*      */   {
/*      */     protected AttributedCharacterIterator aci;
/*      */     
/*      */     protected TextSpanLayout layout;
/*      */     
/*      */     protected int anchorType;
/*      */     
/*      */     protected boolean firstRunInChunk;
/*      */     
/*      */     protected Float length;
/*      */     
/*      */     protected Integer lengthAdjust;
/*      */     
/*      */     private int level;
/*      */     
/*      */     private int reversals;
/*      */ 
/*      */     
/*      */     public TextRun(TextSpanLayout layout, AttributedCharacterIterator aci, boolean firstRunInChunk) {
/* 1649 */       this.layout = layout;
/* 1650 */       this.aci = aci;
/* 1651 */       this.aci.first();
/* 1652 */       this.firstRunInChunk = firstRunInChunk;
/* 1653 */       this.anchorType = 0;
/*      */       
/* 1655 */       TextNode.Anchor anchor = (TextNode.Anchor)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ANCHOR_TYPE);
/*      */       
/* 1657 */       if (anchor != null) {
/* 1658 */         this.anchorType = anchor.getType();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1663 */       if (aci.getAttribute(StrokingTextPainter.WRITING_MODE) == StrokingTextPainter.WRITING_MODE_RTL) {
/* 1664 */         if (this.anchorType == 0) {
/* 1665 */           this.anchorType = 2;
/* 1666 */         } else if (this.anchorType == 2) {
/* 1667 */           this.anchorType = 0;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1672 */       this.length = (Float)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.BBOX_WIDTH);
/*      */       
/* 1674 */       this.lengthAdjust = (Integer)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.LENGTH_ADJUST);
/*      */ 
/*      */       
/* 1677 */       Integer level = (Integer)aci.getAttribute(StrokingTextPainter.BIDI_LEVEL);
/* 1678 */       if (level != null) {
/* 1679 */         this.level = level.intValue();
/*      */       } else {
/* 1681 */         this.level = -1;
/*      */       } 
/*      */     }
/*      */     
/*      */     public AttributedCharacterIterator getACI() {
/* 1686 */       return this.aci;
/*      */     }
/*      */     
/*      */     public TextSpanLayout getLayout() {
/* 1690 */       return this.layout;
/*      */     }
/*      */     
/*      */     public int getAnchorType() {
/* 1694 */       return this.anchorType;
/*      */     }
/*      */     
/*      */     public Float getLength() {
/* 1698 */       return this.length;
/*      */     }
/*      */     
/*      */     public Integer getLengthAdjust() {
/* 1702 */       return this.lengthAdjust;
/*      */     }
/*      */     
/*      */     public boolean isFirstRunInChunk() {
/* 1706 */       return this.firstRunInChunk;
/*      */     }
/*      */     
/*      */     public int getBidiLevel() {
/* 1710 */       return this.level;
/*      */     }
/*      */     
/*      */     public void reverse() {
/* 1714 */       this.reversals++;
/*      */     }
/*      */     
/*      */     public void maybeReverseGlyphs(boolean mirror) {
/* 1718 */       if ((this.reversals & 0x1) == 1)
/* 1719 */         this.layout.maybeReverse(mirror); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/StrokingTextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */