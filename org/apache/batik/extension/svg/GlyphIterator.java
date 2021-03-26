/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.font.AWTGVTFont;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ import org.apache.batik.gvt.font.GVTLineMetrics;
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
/*     */ public class GlyphIterator
/*     */ {
/*  37 */   public static final AttributedCharacterIterator.Attribute PREFORMATTED = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PREFORMATTED;
/*     */ 
/*     */   
/*  40 */   public static final AttributedCharacterIterator.Attribute FLOW_LINE_BREAK = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_LINE_BREAK;
/*     */ 
/*     */   
/*  43 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_ID = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_ID;
/*     */ 
/*     */   
/*  46 */   public static final AttributedCharacterIterator.Attribute GVT_FONT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONT;
/*     */   
/*     */   public static final char SOFT_HYPHEN = '­';
/*     */   
/*     */   public static final char ZERO_WIDTH_SPACE = '​';
/*     */   
/*     */   public static final char ZERO_WIDTH_JOINER = '‍';
/*     */   
/*  54 */   int idx = -1;
/*     */   
/*  56 */   int chIdx = -1;
/*  57 */   int lineIdx = -1;
/*     */ 
/*     */   
/*  60 */   int aciIdx = -1;
/*     */   
/*  62 */   int charCount = -1;
/*     */ 
/*     */   
/*  65 */   float adv = 0.0F;
/*     */   
/*  67 */   float adj = 0.0F;
/*     */   
/*  69 */   int runLimit = 0;
/*     */ 
/*     */   
/*  72 */   int lineBreakRunLimit = 0;
/*  73 */   int lineBreakCount = 0;
/*     */ 
/*     */   
/*  76 */   GVTFont font = null;
/*  77 */   int fontStart = 0;
/*  78 */   float maxAscent = 0.0F;
/*  79 */   float maxDescent = 0.0F;
/*  80 */   float maxFontSize = 0.0F;
/*     */   
/*  82 */   float width = 0.0F;
/*     */   
/*  84 */   char ch = Character.MIN_VALUE;
/*     */   
/*  86 */   int numGlyphs = 0;
/*     */ 
/*     */   
/*     */   AttributedCharacterIterator aci;
/*     */   
/*     */   GVTGlyphVector gv;
/*     */   
/*     */   float[] gp;
/*     */   
/*     */   FontRenderContext frc;
/*     */   
/*  97 */   int[] leftShiftIdx = null;
/*     */   
/*  99 */   float[] leftShiftAmt = null;
/*     */   
/* 101 */   int leftShift = 0;
/*     */   
/* 103 */   Point2D gvBase = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphIterator(AttributedCharacterIterator aci, GVTGlyphVector gv) {
/* 108 */     this.aci = aci;
/* 109 */     this.gv = gv;
/*     */     
/* 111 */     this.idx = 0;
/* 112 */     this.chIdx = 0;
/* 113 */     this.lineIdx = 0;
/* 114 */     this.aciIdx = aci.getBeginIndex();
/* 115 */     this.charCount = gv.getCharacterCount(this.idx, this.idx);
/* 116 */     this.ch = aci.first();
/* 117 */     this.frc = gv.getFontRenderContext();
/*     */     
/* 119 */     this.font = (GVTFont)aci.getAttribute(GVT_FONT);
/* 120 */     if (this.font == null) {
/* 121 */       this.font = (GVTFont)new AWTGVTFont(aci.getAttributes());
/*     */     }
/* 123 */     this.fontStart = this.aciIdx;
/* 124 */     this.maxFontSize = -3.4028235E38F;
/* 125 */     this.maxAscent = -3.4028235E38F;
/* 126 */     this.maxDescent = -3.4028235E38F;
/*     */ 
/*     */     
/* 129 */     this.runLimit = aci.getRunLimit(TEXT_COMPOUND_ID);
/*     */     
/* 131 */     this.lineBreakRunLimit = aci.getRunLimit(FLOW_LINE_BREAK);
/* 132 */     Object o = aci.getAttribute(FLOW_LINE_BREAK);
/* 133 */     this.lineBreakCount = (o == null) ? 0 : 1;
/*     */ 
/*     */     
/* 136 */     this.numGlyphs = gv.getNumGlyphs();
/* 137 */     this.gp = gv.getGlyphPositions(0, this.numGlyphs + 1, null);
/* 138 */     this.gvBase = new Point2D.Float(this.gp[0], this.gp[1]);
/* 139 */     this.adv = getCharWidth();
/* 140 */     this.adj = getCharAdvance();
/*     */   }
/*     */   
/*     */   public GlyphIterator(GlyphIterator gi) {
/* 144 */     gi.copy(this);
/*     */   }
/*     */   
/*     */   public GlyphIterator copy() {
/* 148 */     return new GlyphIterator(this);
/*     */   }
/*     */   
/*     */   public GlyphIterator copy(GlyphIterator gi) {
/* 152 */     if (gi == null) {
/* 153 */       return new GlyphIterator(this);
/*     */     }
/* 155 */     gi.idx = this.idx;
/* 156 */     gi.chIdx = this.chIdx;
/* 157 */     gi.aciIdx = this.aciIdx;
/* 158 */     gi.charCount = this.charCount;
/* 159 */     gi.adv = this.adv;
/* 160 */     gi.adj = this.adj;
/* 161 */     gi.runLimit = this.runLimit;
/* 162 */     gi.ch = this.ch;
/* 163 */     gi.numGlyphs = this.numGlyphs;
/* 164 */     gi.gp = this.gp;
/* 165 */     gi.gvBase = this.gvBase;
/*     */     
/* 167 */     gi.lineBreakRunLimit = this.lineBreakRunLimit;
/* 168 */     gi.lineBreakCount = this.lineBreakCount;
/*     */     
/* 170 */     gi.frc = this.frc;
/* 171 */     gi.font = this.font;
/* 172 */     gi.fontStart = this.fontStart;
/* 173 */     gi.maxAscent = this.maxAscent;
/* 174 */     gi.maxDescent = this.maxDescent;
/* 175 */     gi.maxFontSize = this.maxFontSize;
/*     */     
/* 177 */     gi.leftShift = this.leftShift;
/* 178 */     gi.leftShiftIdx = this.leftShiftIdx;
/* 179 */     gi.leftShiftAmt = this.leftShiftAmt;
/* 180 */     return gi;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphIndex() {
/* 186 */     return this.idx;
/*     */   }
/*     */ 
/*     */   
/*     */   public char getChar() {
/* 191 */     return this.ch;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getACIIndex() {
/* 197 */     return this.aciIdx;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAdv() {
/* 203 */     return this.adv;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getOrigin() {
/* 209 */     return this.gvBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAdj() {
/* 216 */     return this.adj;
/*     */   }
/*     */   public float getMaxFontSize() {
/* 219 */     if (this.aciIdx >= this.fontStart) {
/* 220 */       int newFS = this.aciIdx + this.charCount;
/* 221 */       updateLineMetrics(newFS);
/* 222 */       this.fontStart = newFS;
/*     */     } 
/* 224 */     return this.maxFontSize;
/*     */   }
/*     */   
/*     */   public float getMaxAscent() {
/* 228 */     if (this.aciIdx >= this.fontStart) {
/* 229 */       int newFS = this.aciIdx + this.charCount;
/* 230 */       updateLineMetrics(newFS);
/* 231 */       this.fontStart = newFS;
/*     */     } 
/* 233 */     return this.maxAscent;
/*     */   }
/*     */   
/*     */   public float getMaxDescent() {
/* 237 */     if (this.aciIdx >= this.fontStart) {
/* 238 */       int newFS = this.aciIdx + this.charCount;
/* 239 */       updateLineMetrics(newFS);
/* 240 */       this.fontStart = newFS;
/*     */     } 
/* 242 */     return this.maxDescent;
/*     */   }
/*     */   
/*     */   public boolean isLastChar() {
/* 246 */     return (this.idx == this.numGlyphs - 1);
/*     */   }
/*     */   
/*     */   public boolean done() {
/* 250 */     return (this.idx >= this.numGlyphs);
/*     */   }
/*     */   
/*     */   public boolean isBreakChar() {
/* 254 */     switch (this.ch) { case '​':
/* 255 */         return true;
/* 256 */       case '‍': return false;
/* 257 */       case '­': return true;
/* 258 */       case '\t': case ' ': return true; }
/* 259 */      return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isPrinting(char tstCH) {
/* 264 */     switch (this.ch) { case '​':
/* 265 */         return false;
/* 266 */       case '‍': return false;
/* 267 */       case '­': return true;
/* 268 */       case '\t': case ' ': return false; }
/* 269 */      return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLineBreaks() {
/* 274 */     int ret = 0;
/* 275 */     if (this.aciIdx + this.charCount >= this.lineBreakRunLimit) {
/*     */ 
/*     */       
/* 278 */       ret = this.lineBreakCount;
/*     */ 
/*     */ 
/*     */       
/* 282 */       this.aci.setIndex(this.aciIdx + this.charCount);
/* 283 */       this.lineBreakRunLimit = this.aci.getRunLimit(FLOW_LINE_BREAK);
/* 284 */       this.aci.setIndex(this.aciIdx);
/*     */       
/* 286 */       Object o = this.aci.getAttribute(FLOW_LINE_BREAK);
/* 287 */       this.lineBreakCount = (o == null) ? 0 : 1;
/*     */     } 
/* 289 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextChar() {
/* 296 */     if (this.ch == '­' || this.ch == '​' || this.ch == '‍') {
/*     */ 
/*     */ 
/*     */       
/* 300 */       this.gv.setGlyphVisible(this.idx, false);
/* 301 */       float f = getCharAdvance();
/* 302 */       this.adj -= f;
/* 303 */       addLeftShift(this.idx, f);
/*     */     } 
/*     */     
/* 306 */     this.aciIdx += this.charCount;
/* 307 */     this.ch = this.aci.setIndex(this.aciIdx);
/* 308 */     this.idx++;
/* 309 */     this.charCount = this.gv.getCharacterCount(this.idx, this.idx);
/* 310 */     if (this.idx == this.numGlyphs)
/*     */       return; 
/* 312 */     if (this.aciIdx >= this.runLimit) {
/* 313 */       updateLineMetrics(this.aciIdx);
/* 314 */       this.runLimit = this.aci.getRunLimit(TEXT_COMPOUND_ID);
/* 315 */       this.font = (GVTFont)this.aci.getAttribute(GVT_FONT);
/* 316 */       if (this.font == null) {
/* 317 */         this.font = (GVTFont)new AWTGVTFont(this.aci.getAttributes());
/*     */       }
/* 319 */       this.fontStart = this.aciIdx;
/*     */     } 
/*     */     
/* 322 */     float chAdv = getCharAdvance();
/* 323 */     this.adj += chAdv;
/* 324 */     if (isPrinting()) {
/* 325 */       this.chIdx = this.idx;
/* 326 */       float chW = getCharWidth();
/* 327 */       this.adv = this.adj - chAdv - chW;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addLeftShift(int idx, float chAdv) {
/* 332 */     if (this.leftShiftIdx == null) {
/* 333 */       this.leftShiftIdx = new int[1];
/* 334 */       this.leftShiftIdx[0] = idx;
/* 335 */       this.leftShiftAmt = new float[1];
/* 336 */       this.leftShiftAmt[0] = chAdv;
/*     */     } else {
/* 338 */       int[] newLeftShiftIdx = new int[this.leftShiftIdx.length + 1];
/* 339 */       System.arraycopy(this.leftShiftIdx, 0, newLeftShiftIdx, 0, this.leftShiftIdx.length);
/* 340 */       newLeftShiftIdx[this.leftShiftIdx.length] = idx;
/* 341 */       this.leftShiftIdx = newLeftShiftIdx;
/*     */       
/* 343 */       float[] newLeftShiftAmt = new float[this.leftShiftAmt.length + 1];
/* 344 */       System.arraycopy(this.leftShiftAmt, 0, newLeftShiftAmt, 0, this.leftShiftAmt.length);
/* 345 */       newLeftShiftAmt[this.leftShiftAmt.length] = chAdv;
/* 346 */       this.leftShiftAmt = newLeftShiftAmt;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateLineMetrics(int end) {
/* 351 */     GVTLineMetrics glm = this.font.getLineMetrics(this.aci, this.fontStart, end, this.frc);
/*     */     
/* 353 */     float ascent = glm.getAscent();
/* 354 */     float descent = glm.getDescent();
/* 355 */     float fontSz = this.font.getSize();
/* 356 */     if (ascent > this.maxAscent) this.maxAscent = ascent; 
/* 357 */     if (descent > this.maxDescent) this.maxDescent = descent; 
/* 358 */     if (fontSz > this.maxFontSize) this.maxFontSize = fontSz; 
/*     */   }
/*     */   
/*     */   public LineInfo newLine(Point2D.Float loc, float lineWidth, boolean partial, Point2D.Float verticalAlignOffset) {
/*     */     int nextLSI;
/*     */     float lineInfoChW;
/*     */     int hideIdx;
/* 365 */     if (this.ch == '­') {
/* 366 */       this.gv.setGlyphVisible(this.idx, true);
/*     */     }
/*     */     
/* 369 */     int lsi = 0;
/*     */     
/* 371 */     if (this.leftShiftIdx != null) { nextLSI = this.leftShiftIdx[lsi]; }
/* 372 */     else { nextLSI = this.idx + 1; }
/* 373 */      for (int ci = this.lineIdx; ci <= this.idx; ci++) {
/* 374 */       if (ci == nextLSI) {
/* 375 */         this.leftShift = (int)(this.leftShift + this.leftShiftAmt[lsi++]);
/* 376 */         if (lsi < this.leftShiftIdx.length)
/* 377 */           nextLSI = this.leftShiftIdx[lsi]; 
/*     */       } 
/* 379 */       this.gv.setGlyphPosition(ci, new Point2D.Float(this.gp[2 * ci] - this.leftShift, this.gp[2 * ci + 1]));
/*     */     } 
/*     */     
/* 382 */     this.leftShiftIdx = null;
/* 383 */     this.leftShiftAmt = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     if (this.chIdx != 0 || isPrinting()) {
/* 389 */       lineInfoChW = getCharWidth(this.chIdx);
/* 390 */       hideIdx = this.chIdx + 1;
/*     */     } else {
/* 392 */       lineInfoChW = 0.0F;
/* 393 */       hideIdx = 0;
/*     */     } 
/*     */     
/* 396 */     int lineInfoIdx = this.idx + 1;
/* 397 */     float lineInfoAdv = this.adv;
/* 398 */     float lineInfoAdj = this.adj;
/* 399 */     while (!done()) {
/* 400 */       this.adv = 0.0F;
/* 401 */       this.adj = 0.0F;
/*     */       
/* 403 */       if (this.ch == '​' || this.ch == '‍')
/*     */       {
/* 405 */         this.gv.setGlyphVisible(this.idx, false);
/*     */       }
/* 407 */       this.ch = Character.MIN_VALUE;
/* 408 */       nextChar();
/*     */       
/* 410 */       if (isPrinting())
/*     */         break; 
/* 412 */       lineInfoIdx = this.idx + 1;
/* 413 */       lineInfoAdj += this.adj;
/*     */     } 
/*     */ 
/*     */     
/* 417 */     for (int i = hideIdx; i < lineInfoIdx; i++) {
/* 418 */       this.gv.setGlyphVisible(i, false);
/*     */     }
/*     */     
/* 421 */     this.maxAscent = -3.4028235E38F;
/* 422 */     this.maxDescent = -3.4028235E38F;
/* 423 */     this.maxFontSize = -3.4028235E38F;
/* 424 */     LineInfo ret = new LineInfo(loc, this.aci, this.gv, this.lineIdx, lineInfoIdx, lineInfoAdj, lineInfoAdv, lineInfoChW, lineWidth, partial, verticalAlignOffset);
/*     */ 
/*     */     
/* 427 */     this.lineIdx = this.idx;
/*     */     
/* 429 */     return ret;
/*     */   }
/*     */   
/*     */   public boolean isPrinting() {
/* 433 */     if (this.aci.getAttribute(PREFORMATTED) == Boolean.TRUE)
/* 434 */       return true; 
/* 435 */     return isPrinting(this.ch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCharAdvance() {
/* 442 */     return getCharAdvance(this.idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCharWidth() {
/* 451 */     return getCharWidth(this.idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getCharAdvance(int gvIdx) {
/* 458 */     return this.gp[2 * gvIdx + 2] - this.gp[2 * gvIdx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getCharWidth(int gvIdx) {
/* 467 */     Rectangle2D lcBound = this.gv.getGlyphVisualBounds(gvIdx).getBounds2D();
/* 468 */     Point2D lcLoc = this.gv.getGlyphPosition(gvIdx);
/* 469 */     return (float)(lcBound.getX() + lcBound.getWidth() - lcLoc.getX());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/GlyphIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */