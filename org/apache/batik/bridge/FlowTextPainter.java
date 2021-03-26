/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.gvt.flow.BlockInfo;
/*     */ import org.apache.batik.gvt.flow.FlowRegions;
/*     */ import org.apache.batik.gvt.flow.GlyphGroupInfo;
/*     */ import org.apache.batik.gvt.flow.LineInfo;
/*     */ import org.apache.batik.gvt.flow.RegionInfo;
/*     */ import org.apache.batik.gvt.flow.TextLineBreaks;
/*     */ import org.apache.batik.gvt.flow.WordInfo;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ import org.apache.batik.gvt.font.GVTLineMetrics;
/*     */ import org.apache.batik.gvt.font.MultiGlyphVector;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlowTextPainter
/*     */   extends StrokingTextPainter
/*     */ {
/*  58 */   protected static TextPainter singleton = new FlowTextPainter(); public static final char SOFT_HYPHEN = '­';
/*     */   public static final char ZERO_WIDTH_SPACE = '​';
/*     */   public static final char ZERO_WIDTH_JOINER = '‍';
/*     */   public static final char SPACE = ' ';
/*     */   
/*     */   public static TextPainter getInstance() {
/*  64 */     return singleton;
/*     */   }
/*     */   
/*     */   public List getTextRuns(TextNode node, AttributedCharacterIterator aci) {
/*  68 */     List textRuns = node.getTextRuns();
/*  69 */     if (textRuns != null) {
/*  70 */       return textRuns;
/*     */     }
/*     */     
/*  73 */     AttributedCharacterIterator[] chunkACIs = getTextChunkACIs(aci);
/*  74 */     textRuns = computeTextRuns(node, aci, chunkACIs);
/*     */     
/*  76 */     aci.first();
/*  77 */     List rgns = (List)aci.getAttribute(FLOW_REGIONS);
/*     */     
/*  79 */     if (rgns != null) {
/*  80 */       Iterator<StrokingTextPainter.TextRun> i = textRuns.iterator();
/*  81 */       List<List> chunkLayouts = new ArrayList();
/*  82 */       StrokingTextPainter.TextRun tr = i.next();
/*  83 */       List<TextSpanLayout> layouts = new ArrayList();
/*  84 */       chunkLayouts.add(layouts);
/*  85 */       layouts.add(tr.getLayout());
/*  86 */       while (i.hasNext()) {
/*  87 */         tr = i.next();
/*  88 */         if (tr.isFirstRunInChunk()) {
/*  89 */           layouts = new ArrayList<TextSpanLayout>();
/*  90 */           chunkLayouts.add(layouts);
/*     */         } 
/*  92 */         layouts.add(tr.getLayout());
/*     */       } 
/*     */       
/*  95 */       textWrap(chunkACIs, chunkLayouts, rgns, this.fontRenderContext);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     node.setTextRuns(textRuns);
/* 100 */     return node.getTextRuns();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final AttributedCharacterIterator.Attribute WORD_LIMIT = TextLineBreaks.WORD_LIMIT;
/*     */ 
/*     */   
/* 111 */   public static final AttributedCharacterIterator.Attribute FLOW_REGIONS = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_REGIONS;
/*     */ 
/*     */   
/* 114 */   public static final AttributedCharacterIterator.Attribute FLOW_LINE_BREAK = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_LINE_BREAK;
/*     */   
/* 116 */   public static final AttributedCharacterIterator.Attribute LINE_HEIGHT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.LINE_HEIGHT;
/*     */ 
/*     */   
/* 119 */   public static final AttributedCharacterIterator.Attribute GVT_FONT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONT;
/*     */ 
/*     */   
/* 122 */   protected static Set szAtts = new HashSet();
/*     */   
/*     */   static {
/* 125 */     szAtts.add(TextAttribute.SIZE);
/* 126 */     szAtts.add(GVT_FONT);
/* 127 */     szAtts.add(LINE_HEIGHT);
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
/*     */   public static boolean textWrap(AttributedCharacterIterator[] acis, List chunkLayouts, List flowRects, FontRenderContext frc) {
/* 142 */     WordInfo[][] wordInfos = new WordInfo[acis.length][];
/* 143 */     Iterator<List> clIter = chunkLayouts.iterator();
/*     */     
/* 145 */     float prevBotMargin = 0.0F;
/* 146 */     int numWords = 0;
/* 147 */     BlockInfo[] blockInfos = new BlockInfo[acis.length];
/* 148 */     float[] topSkip = new float[acis.length];
/* 149 */     for (int chunk = 0; clIter.hasNext(); chunk++) {
/*     */       
/* 151 */       AttributedCharacterIterator aci = acis[chunk];
/* 152 */       List<GVTGlyphVector> gvl = new LinkedList();
/* 153 */       List layouts = clIter.next();
/* 154 */       for (Object layout : layouts) {
/* 155 */         GlyphLayout gl = (GlyphLayout)layout;
/* 156 */         gvl.add(gl.getGlyphVector());
/*     */       } 
/* 158 */       MultiGlyphVector multiGlyphVector = new MultiGlyphVector(gvl);
/*     */       
/* 160 */       wordInfos[chunk] = doWordAnalysis((GVTGlyphVector)multiGlyphVector, aci, numWords, frc);
/* 161 */       aci.first();
/* 162 */       BlockInfo bi = (BlockInfo)aci.getAttribute(FLOW_PARAGRAPH);
/* 163 */       bi.initLineInfo(frc);
/* 164 */       blockInfos[chunk] = bi;
/* 165 */       if (prevBotMargin > bi.getTopMargin()) {
/* 166 */         topSkip[chunk] = prevBotMargin;
/*     */       } else {
/* 168 */         topSkip[chunk] = bi.getTopMargin();
/* 169 */       }  prevBotMargin = bi.getBottomMargin();
/* 170 */       numWords += (wordInfos[chunk]).length;
/*     */     } 
/*     */     
/* 173 */     Iterator<RegionInfo> frIter = flowRects.iterator();
/* 174 */     RegionInfo currentRegion = null;
/* 175 */     int currWord = 0;
/* 176 */     int i = 0;
/* 177 */     List<LineInfo> lineInfos = new LinkedList();
/* 178 */     while (frIter.hasNext()) {
/* 179 */       currentRegion = frIter.next();
/* 180 */       FlowRegions fr = new FlowRegions(currentRegion.getShape());
/*     */       
/* 182 */       while (i < wordInfos.length) {
/* 183 */         WordInfo[] chunkInfo = wordInfos[i];
/* 184 */         BlockInfo bi = blockInfos[i];
/* 185 */         WordInfo wi = chunkInfo[currWord];
/* 186 */         Object flowLine = wi.getFlowLine();
/* 187 */         double lh = Math.max(wi.getLineHeight(), bi.getLineHeight());
/* 188 */         LineInfo li = new LineInfo(fr, bi, true);
/* 189 */         double newY = li.getCurrentY() + topSkip[i];
/* 190 */         topSkip[i] = 0.0F;
/* 191 */         if (li.gotoY(newY))
/*     */           break; 
/* 193 */         while (!li.addWord(wi)) {
/*     */           
/* 195 */           newY = li.getCurrentY() + lh * 0.1D;
/* 196 */           if (li.gotoY(newY))
/*     */             break; 
/* 198 */         }  if (fr.done())
/*     */           break; 
/* 200 */         currWord++;
/* 201 */         for (; currWord < chunkInfo.length; currWord++) {
/* 202 */           wi = chunkInfo[currWord];
/* 203 */           if (wi.getFlowLine() != flowLine || !li.addWord(wi))
/*     */           
/*     */           { 
/*     */ 
/*     */             
/* 208 */             li.layout();
/* 209 */             lineInfos.add(li);
/* 210 */             li = null;
/*     */             
/* 212 */             flowLine = wi.getFlowLine();
/* 213 */             lh = Math.max(wi.getLineHeight(), bi.getLineHeight());
/* 214 */             if (!fr.newLine(lh))
/*     */               break; 
/* 216 */             li = new LineInfo(fr, bi, false);
/* 217 */             while (!li.addWord(wi)) {
/* 218 */               newY = li.getCurrentY() + lh * 0.1D;
/* 219 */               if (li.gotoY(newY))
/*     */                 break; 
/* 221 */             }  if (fr.done())
/*     */               break;  } 
/* 223 */         }  if (li != null) {
/* 224 */           li.setParaEnd(true);
/* 225 */           li.layout();
/*     */         } 
/*     */         
/* 228 */         if (fr.done())
/*     */           break; 
/* 230 */         i++;
/* 231 */         currWord = 0;
/*     */         
/* 233 */         if (bi.isFlowRegionBreak()) {
/*     */           break;
/*     */         }
/* 236 */         if (!fr.newLine(lh))
/*     */           break; 
/*     */       } 
/* 239 */       if (i == wordInfos.length) {
/*     */         break;
/*     */       }
/*     */     } 
/* 243 */     boolean overflow = (i < wordInfos.length);
/*     */     
/* 245 */     while (i < wordInfos.length) {
/* 246 */       WordInfo[] chunkInfo = wordInfos[i];
/* 247 */       while (currWord < chunkInfo.length) {
/* 248 */         WordInfo wi = chunkInfo[currWord];
/* 249 */         int numGG = wi.getNumGlyphGroups();
/* 250 */         for (int gg = 0; gg < numGG; gg++) {
/* 251 */           GlyphGroupInfo ggi = wi.getGlyphGroup(gg);
/* 252 */           GVTGlyphVector gv = ggi.getGlyphVector();
/* 253 */           int end = ggi.getEnd();
/* 254 */           for (int g = ggi.getStart(); g <= end; g++) {
/* 255 */             gv.setGlyphVisible(g, false);
/*     */           }
/*     */         } 
/* 258 */         currWord++;
/*     */       } 
/* 260 */       i++;
/* 261 */       currWord = 0;
/*     */     } 
/*     */     
/* 264 */     return overflow;
/*     */   }
/*     */   
/*     */   static int[] allocWordMap(int[] wordMap, int sz) {
/* 268 */     if (wordMap != null) {
/* 269 */       if (sz <= wordMap.length) {
/* 270 */         return wordMap;
/*     */       }
/* 272 */       if (sz < wordMap.length * 2) {
/* 273 */         sz = wordMap.length * 2;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 278 */     int[] ret = new int[sz];
/* 279 */     int ext = (wordMap != null) ? wordMap.length : 0;
/* 280 */     if (sz < ext) {
/* 281 */       ext = sz;
/*     */     }
/* 283 */     if (ext != 0) {
/* 284 */       System.arraycopy(wordMap, 0, ret, 0, ext);
/*     */     }
/* 286 */     Arrays.fill(ret, ext, sz, -1);
/*     */     
/* 288 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static WordInfo[] doWordAnalysis(GVTGlyphVector gv, AttributedCharacterIterator aci, int numWords, FontRenderContext frc) {
/* 299 */     int numGlyphs = gv.getNumGlyphs();
/* 300 */     int[] glyphWords = new int[numGlyphs];
/* 301 */     int[] wordMap = allocWordMap((int[])null, 10);
/* 302 */     int maxWord = 0;
/* 303 */     int aciIdx = aci.getBeginIndex();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     for (int i = 0; i < numGlyphs; i++) {
/* 309 */       int cnt = gv.getCharacterCount(i, i);
/* 310 */       aci.setIndex(aciIdx);
/* 311 */       Integer integer = (Integer)aci.getAttribute(WORD_LIMIT);
/* 312 */       int minWord = integer.intValue() - numWords;
/* 313 */       if (minWord > maxWord) {
/* 314 */         maxWord = minWord;
/* 315 */         wordMap = allocWordMap(wordMap, maxWord + 1);
/*     */       } 
/* 317 */       aciIdx++;
/* 318 */       for (int c = 1; c < cnt; c++) {
/* 319 */         aci.setIndex(aciIdx);
/* 320 */         integer = (Integer)aci.getAttribute(WORD_LIMIT);
/* 321 */         int cWord = integer.intValue() - numWords;
/* 322 */         if (cWord > maxWord) {
/* 323 */           maxWord = cWord;
/* 324 */           wordMap = allocWordMap(wordMap, maxWord + 1);
/*     */         } 
/*     */ 
/*     */         
/* 328 */         if (cWord < minWord) {
/* 329 */           wordMap[minWord] = cWord;
/* 330 */           minWord = cWord;
/* 331 */         } else if (cWord > minWord) {
/* 332 */           wordMap[cWord] = minWord;
/*     */         } 
/* 334 */         aciIdx++;
/*     */       } 
/* 336 */       glyphWords[i] = minWord;
/*     */     } 
/* 338 */     int words = 0;
/* 339 */     WordInfo[] cWordMap = new WordInfo[maxWord + 1];
/* 340 */     for (int j = 0; j <= maxWord; j++) {
/* 341 */       int nw = wordMap[j];
/* 342 */       if (nw == -1) {
/*     */         
/* 344 */         cWordMap[j] = new WordInfo(words++);
/*     */       } else {
/* 346 */         int word = nw;
/* 347 */         nw = wordMap[j];
/* 348 */         while (nw != -1) {
/* 349 */           word = nw;
/* 350 */           nw = wordMap[word];
/*     */         } 
/* 352 */         wordMap[j] = word;
/* 353 */         cWordMap[j] = cWordMap[word];
/*     */       } 
/*     */     } 
/* 356 */     wordMap = null;
/* 357 */     WordInfo[] wordInfos = new WordInfo[words];
/* 358 */     for (int k = 0; k <= maxWord; k++) {
/* 359 */       WordInfo wi = cWordMap[k];
/* 360 */       wordInfos[wi.getIndex()] = cWordMap[k];
/*     */     } 
/*     */     
/* 363 */     aciIdx = aci.getBeginIndex();
/* 364 */     int aciEnd = aci.getEndIndex();
/* 365 */     char ch = aci.setIndex(aciIdx);
/*     */     
/* 367 */     int aciWordStart = aciIdx;
/* 368 */     GVTFont gvtFont = (GVTFont)aci.getAttribute(GVT_FONT);
/* 369 */     float lineHeight = 1.0F;
/* 370 */     Float lineHeightFloat = (Float)aci.getAttribute(LINE_HEIGHT);
/* 371 */     if (lineHeightFloat != null)
/* 372 */       lineHeight = lineHeightFloat.floatValue(); 
/* 373 */     int runLimit = aci.getRunLimit(szAtts);
/* 374 */     WordInfo prevWI = null;
/* 375 */     float[] lastAdvAdj = new float[numGlyphs];
/* 376 */     float[] advAdj = new float[numGlyphs];
/* 377 */     boolean[] hideLast = new boolean[numGlyphs];
/* 378 */     boolean[] hide = new boolean[numGlyphs];
/* 379 */     boolean[] space = new boolean[numGlyphs];
/* 380 */     float[] glyphPos = gv.getGlyphPositions(0, numGlyphs + 1, null);
/* 381 */     for (int m = 0; m < numGlyphs; m++) {
/* 382 */       char pch = ch;
/* 383 */       ch = aci.setIndex(aciIdx);
/* 384 */       Integer integer = (Integer)aci.getAttribute(WORD_LIMIT);
/* 385 */       WordInfo theWI = cWordMap[integer.intValue() - numWords];
/* 386 */       if (theWI.getFlowLine() == null) {
/* 387 */         theWI.setFlowLine(aci.getAttribute(FLOW_LINE_BREAK));
/*     */       }
/* 389 */       if (prevWI == null) {
/* 390 */         prevWI = theWI;
/* 391 */       } else if (prevWI != theWI) {
/* 392 */         GVTLineMetrics gVTLineMetrics = gvtFont.getLineMetrics(aci, aciWordStart, aciIdx, frc);
/*     */         
/* 394 */         prevWI.addLineMetrics(gvtFont, gVTLineMetrics);
/* 395 */         prevWI.addLineHeight(lineHeight);
/* 396 */         aciWordStart = aciIdx;
/* 397 */         prevWI = theWI;
/*     */       } 
/*     */       
/* 400 */       int chCnt = gv.getCharacterCount(m, m);
/* 401 */       if (chCnt == 1) {
/*     */         char nch;
/*     */         float kern;
/* 404 */         switch (ch) {
/*     */           case '­':
/* 406 */             hideLast[m] = true;
/* 407 */             nch = aci.next(); aci.previous();
/* 408 */             kern = gvtFont.getHKern(pch, nch);
/* 409 */             advAdj[m] = -(glyphPos[2 * m + 2] - glyphPos[2 * m] + kern);
/*     */             break;
/*     */           case '‍':
/* 412 */             hide[m] = true;
/*     */             break;
/*     */           case '​':
/* 415 */             hide[m] = true;
/*     */             break;
/*     */           case ' ':
/* 418 */             space[m] = true;
/* 419 */             nch = aci.next(); aci.previous();
/* 420 */             kern = gvtFont.getHKern(pch, nch);
/* 421 */             lastAdvAdj[m] = -(glyphPos[2 * m + 2] - glyphPos[2 * m] + kern);
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/* 426 */       aciIdx += chCnt;
/* 427 */       if (aciIdx > runLimit && aciIdx < aciEnd) {
/*     */ 
/*     */         
/* 430 */         GVTLineMetrics gVTLineMetrics = gvtFont.getLineMetrics(aci, aciWordStart, runLimit, frc);
/*     */         
/* 432 */         prevWI.addLineMetrics(gvtFont, gVTLineMetrics);
/* 433 */         prevWI.addLineHeight(lineHeight);
/* 434 */         prevWI = null;
/* 435 */         aciWordStart = aciIdx;
/* 436 */         aci.setIndex(aciIdx);
/* 437 */         gvtFont = (GVTFont)aci.getAttribute(GVT_FONT);
/* 438 */         Float f = (Float)aci.getAttribute(LINE_HEIGHT);
/* 439 */         lineHeight = f.floatValue();
/* 440 */         runLimit = aci.getRunLimit(szAtts);
/*     */       } 
/*     */     } 
/* 443 */     GVTLineMetrics lm = gvtFont.getLineMetrics(aci, aciWordStart, runLimit, frc);
/*     */     
/* 445 */     prevWI.addLineMetrics(gvtFont, lm);
/* 446 */     prevWI.addLineHeight(lineHeight);
/*     */     
/* 448 */     int[] wordGlyphCounts = new int[words];
/*     */     
/* 450 */     for (int n = 0; n < numGlyphs; n++) {
/* 451 */       int word = glyphWords[n];
/* 452 */       int cWord = cWordMap[word].getIndex();
/* 453 */       glyphWords[n] = cWord;
/* 454 */       wordGlyphCounts[cWord] = wordGlyphCounts[cWord] + 1;
/*     */     } 
/*     */     
/* 457 */     cWordMap = null;
/* 458 */     int[][] wordGlyphs = new int[words][];
/* 459 */     int[] wordGlyphGroupsCounts = new int[words]; int i1;
/* 460 */     for (i1 = 0; i1 < numGlyphs; i1++) {
/* 461 */       int cWord = glyphWords[i1];
/*     */       
/* 463 */       int[] wgs = wordGlyphs[cWord];
/* 464 */       if (wgs == null) {
/* 465 */         wgs = wordGlyphs[cWord] = new int[wordGlyphCounts[cWord]];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 470 */         wordGlyphCounts[cWord] = 0;
/*     */       } 
/* 472 */       int cnt = wordGlyphCounts[cWord];
/* 473 */       wgs[cnt] = i1;
/*     */       
/* 475 */       if (cnt == 0) {
/* 476 */         wordGlyphGroupsCounts[cWord] = wordGlyphGroupsCounts[cWord] + 1;
/*     */       }
/* 478 */       else if (wgs[cnt - 1] != i1 - 1) {
/* 479 */         wordGlyphGroupsCounts[cWord] = wordGlyphGroupsCounts[cWord] + 1;
/*     */       } 
/* 481 */       wordGlyphCounts[cWord] = wordGlyphCounts[cWord] + 1;
/*     */     } 
/*     */     
/* 484 */     for (i1 = 0; i1 < words; i1++) {
/* 485 */       int cnt = wordGlyphGroupsCounts[i1];
/*     */       
/* 487 */       GlyphGroupInfo[] wordGlyphGroups = new GlyphGroupInfo[cnt];
/* 488 */       if (cnt == 1) {
/* 489 */         int[] glyphs = wordGlyphs[i1];
/* 490 */         int start = glyphs[0];
/* 491 */         int end = glyphs[glyphs.length - 1];
/* 492 */         wordGlyphGroups[0] = new GlyphGroupInfo(gv, start, end, hide, hideLast[end], glyphPos, advAdj, lastAdvAdj, space);
/*     */       }
/*     */       else {
/*     */         
/* 496 */         int glyphGroup = 0;
/* 497 */         int[] glyphs = wordGlyphs[i1];
/* 498 */         int prev = glyphs[0];
/* 499 */         int start = prev;
/* 500 */         for (int i2 = 1; i2 < glyphs.length; i2++) {
/* 501 */           if (prev + 1 != glyphs[i2]) {
/* 502 */             int i3 = glyphs[i2 - 1];
/* 503 */             wordGlyphGroups[glyphGroup] = new GlyphGroupInfo(gv, start, i3, hide, hideLast[i3], glyphPos, advAdj, lastAdvAdj, space);
/*     */ 
/*     */             
/* 506 */             start = glyphs[i2];
/* 507 */             glyphGroup++;
/*     */           } 
/* 509 */           prev = glyphs[i2];
/*     */         } 
/* 511 */         int end = glyphs[glyphs.length - 1];
/* 512 */         wordGlyphGroups[glyphGroup] = new GlyphGroupInfo(gv, start, end, hide, hideLast[end], glyphPos, advAdj, lastAdvAdj, space);
/*     */       } 
/*     */ 
/*     */       
/* 516 */       wordInfos[i1].setGlyphGroups(wordGlyphGroups);
/*     */     } 
/* 518 */     return wordInfos;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FlowTextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */