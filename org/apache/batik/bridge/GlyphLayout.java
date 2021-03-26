/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.TextAttribute;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.gvt.font.AWTGVTFont;
/*      */ import org.apache.batik.gvt.font.AltGlyphHandler;
/*      */ import org.apache.batik.gvt.font.GVTFont;
/*      */ import org.apache.batik.gvt.font.GVTGlyphMetrics;
/*      */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*      */ import org.apache.batik.gvt.font.GVTLineMetrics;
/*      */ import org.apache.batik.gvt.text.ArabicTextHandler;
/*      */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
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
/*      */ public class GlyphLayout
/*      */   implements TextSpanLayout
/*      */ {
/*      */   protected GVTGlyphVector gv;
/*      */   private GVTFont font;
/*      */   private GVTLineMetrics metrics;
/*      */   private AttributedCharacterIterator aci;
/*      */   protected Point2D advance;
/*      */   private Point2D offset;
/*   64 */   private float xScale = 1.0F;
/*   65 */   private float yScale = 1.0F;
/*      */ 
/*      */   
/*      */   private TextPath textPath;
/*      */ 
/*      */   
/*      */   private Point2D textPathAdvance;
/*      */ 
/*      */   
/*      */   private int[] charMap;
/*      */ 
/*      */   
/*      */   private boolean vertical;
/*      */ 
/*      */   
/*      */   private boolean adjSpacing = true;
/*      */   
/*      */   private float[] glyphAdvances;
/*      */   
/*      */   private boolean isAltGlyph;
/*      */   
/*      */   protected boolean layoutApplied = false;
/*      */   
/*      */   private boolean spacingApplied = false;
/*      */   
/*      */   private boolean pathApplied = false;
/*      */   
/*   92 */   public static final AttributedCharacterIterator.Attribute FLOW_LINE_BREAK = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_LINE_BREAK;
/*      */ 
/*      */   
/*   95 */   public static final AttributedCharacterIterator.Attribute FLOW_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_PARAGRAPH;
/*      */ 
/*      */ 
/*      */   
/*   99 */   public static final AttributedCharacterIterator.Attribute FLOW_EMPTY_PARAGRAPH = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.FLOW_EMPTY_PARAGRAPH;
/*      */ 
/*      */   
/*  102 */   public static final AttributedCharacterIterator.Attribute LINE_HEIGHT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.LINE_HEIGHT;
/*      */ 
/*      */ 
/*      */   
/*  106 */   public static final AttributedCharacterIterator.Attribute VERTICAL_ORIENTATION = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION;
/*      */ 
/*      */ 
/*      */   
/*  110 */   public static final AttributedCharacterIterator.Attribute VERTICAL_ORIENTATION_ANGLE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.VERTICAL_ORIENTATION_ANGLE;
/*      */ 
/*      */ 
/*      */   
/*  114 */   public static final AttributedCharacterIterator.Attribute HORIZONTAL_ORIENTATION_ANGLE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.HORIZONTAL_ORIENTATION_ANGLE;
/*      */ 
/*      */   
/*  117 */   private static final AttributedCharacterIterator.Attribute X = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.X;
/*      */ 
/*      */   
/*  120 */   private static final AttributedCharacterIterator.Attribute Y = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.Y;
/*      */ 
/*      */   
/*  123 */   private static final AttributedCharacterIterator.Attribute DX = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.DX;
/*      */ 
/*      */   
/*  126 */   private static final AttributedCharacterIterator.Attribute DY = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.DY;
/*      */ 
/*      */   
/*  129 */   private static final AttributedCharacterIterator.Attribute ROTATION = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ROTATION;
/*      */ 
/*      */   
/*  132 */   private static final AttributedCharacterIterator.Attribute BASELINE_SHIFT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.BASELINE_SHIFT;
/*      */ 
/*      */   
/*  135 */   private static final AttributedCharacterIterator.Attribute WRITING_MODE = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE;
/*      */ 
/*      */   
/*  138 */   private static final Integer WRITING_MODE_TTB = GVTAttributedCharacterIterator.TextAttribute.WRITING_MODE_TTB;
/*      */ 
/*      */   
/*  141 */   private static final Integer ORIENTATION_AUTO = GVTAttributedCharacterIterator.TextAttribute.ORIENTATION_AUTO;
/*      */ 
/*      */   
/*  144 */   public static final AttributedCharacterIterator.Attribute GVT_FONT = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.GVT_FONT;
/*      */ 
/*      */   
/*  147 */   protected static Set runAtts = new HashSet();
/*      */   
/*      */   static {
/*  150 */     runAtts.add(X);
/*  151 */     runAtts.add(Y);
/*  152 */     runAtts.add(DX);
/*  153 */     runAtts.add(DY);
/*  154 */     runAtts.add(ROTATION);
/*  155 */     runAtts.add(BASELINE_SHIFT);
/*      */   }
/*      */   
/*  158 */   protected static Set szAtts = new HashSet(); public static final double eps = 1.0E-5D;
/*      */   
/*      */   static {
/*  161 */     szAtts.add(TextAttribute.SIZE);
/*  162 */     szAtts.add(GVT_FONT);
/*  163 */     szAtts.add(LINE_HEIGHT);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GlyphLayout(AttributedCharacterIterator aci, int[] charMap, Point2D offset, FontRenderContext frc) {
/*  183 */     this.aci = aci;
/*  184 */     this.offset = offset;
/*  185 */     this.font = getFont();
/*  186 */     this.charMap = charMap;
/*      */     
/*  188 */     this.metrics = this.font.getLineMetrics(aci, aci.getBeginIndex(), aci.getEndIndex(), frc);
/*      */ 
/*      */ 
/*      */     
/*  192 */     this.gv = null;
/*  193 */     this.aci.first();
/*  194 */     this.vertical = (aci.getAttribute(WRITING_MODE) == WRITING_MODE_TTB);
/*  195 */     this.textPath = (TextPath)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXTPATH);
/*      */ 
/*      */     
/*  198 */     AltGlyphHandler altGlyphHandler = (AltGlyphHandler)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ALT_GLYPH_HANDLER);
/*      */ 
/*      */     
/*  201 */     if (altGlyphHandler != null) {
/*      */ 
/*      */       
/*  204 */       this.gv = altGlyphHandler.createGlyphVector(frc, this.font.getSize(), this.aci);
/*      */       
/*  206 */       if (this.gv != null) {
/*  207 */         this.isAltGlyph = true;
/*      */       }
/*      */     } 
/*  210 */     if (this.gv == null)
/*      */     {
/*      */       
/*  213 */       this.gv = this.font.createGlyphVector(frc, this.aci);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public GVTGlyphVector getGlyphVector() {
/*  219 */     return this.gv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point2D getOffset() {
/*  229 */     return this.offset;
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
/*      */   public void setScale(float xScale, float yScale, boolean adjSpacing) {
/*  245 */     if (this.vertical) { xScale = 1.0F; }
/*  246 */     else { yScale = 1.0F; }
/*      */     
/*  248 */     if (xScale != this.xScale || yScale != this.yScale || adjSpacing != this.adjSpacing) {
/*      */ 
/*      */       
/*  251 */       this.xScale = xScale;
/*  252 */       this.yScale = yScale;
/*  253 */       this.adjSpacing = adjSpacing;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  258 */       this.spacingApplied = false;
/*  259 */       this.glyphAdvances = null;
/*  260 */       this.pathApplied = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffset(Point2D offset) {
/*  271 */     if (offset.getX() != this.offset.getX() || offset.getY() != this.offset.getY()) {
/*      */       
/*  273 */       if (this.layoutApplied || this.spacingApplied) {
/*      */ 
/*      */         
/*  276 */         float dx = (float)(offset.getX() - this.offset.getX());
/*  277 */         float dy = (float)(offset.getY() - this.offset.getY());
/*  278 */         int numGlyphs = this.gv.getNumGlyphs();
/*      */         
/*  280 */         float[] gp = this.gv.getGlyphPositions(0, numGlyphs + 1, null);
/*  281 */         Point2D.Float pos = new Point2D.Float();
/*  282 */         for (int i = 0; i <= numGlyphs; i++) {
/*  283 */           pos.x = gp[2 * i] + dx;
/*  284 */           pos.y = gp[2 * i + 1] + dy;
/*  285 */           this.gv.setGlyphPosition(i, pos);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  292 */       this.offset = offset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  298 */       this.pathApplied = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public GVTGlyphMetrics getGlyphMetrics(int glyphIndex) {
/*  303 */     return this.gv.getGlyphMetrics(glyphIndex);
/*      */   }
/*      */   
/*      */   public GVTLineMetrics getLineMetrics() {
/*  307 */     return this.metrics;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isVertical() {
/*  314 */     return this.vertical;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnATextPath() {
/*  321 */     return (this.textPath != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGlyphCount() {
/*  329 */     return this.gv.getNumGlyphs();
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
/*      */   public int getCharacterCount(int startGlyphIndex, int endGlyphIndex) {
/*  343 */     return this.gv.getCharacterCount(startGlyphIndex, endGlyphIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLeftToRight() {
/*  350 */     this.aci.first();
/*  351 */     int bidiLevel = ((Integer)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.BIDI_LEVEL)).intValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     return ((bidiLevel & 0x1) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void syncLayout() {
/*  366 */     if (!this.pathApplied) {
/*  367 */       doPathLayout();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void draw(Graphics2D g2d) {
/*  377 */     syncLayout();
/*  378 */     this.gv.draw(g2d, this.aci);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point2D getAdvance2D() {
/*  386 */     adjustTextSpacing();
/*  387 */     return this.advance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape getOutline() {
/*  395 */     syncLayout();
/*      */     
/*  397 */     return this.gv.getOutline();
/*      */   }
/*      */   
/*      */   public float[] getGlyphAdvances() {
/*  401 */     if (this.glyphAdvances != null) {
/*  402 */       return this.glyphAdvances;
/*      */     }
/*  404 */     if (!this.spacingApplied)
/*      */     {
/*  406 */       adjustTextSpacing();
/*      */     }
/*  408 */     int numGlyphs = this.gv.getNumGlyphs();
/*  409 */     float[] glyphPos = this.gv.getGlyphPositions(0, numGlyphs + 1, null);
/*  410 */     this.glyphAdvances = new float[numGlyphs + 1];
/*  411 */     int off = 0;
/*  412 */     if (isVertical()) {
/*  413 */       off = 1;
/*      */     }
/*  415 */     float start = glyphPos[off];
/*  416 */     for (int i = 0; i < numGlyphs + 1; i++) {
/*  417 */       this.glyphAdvances[i] = glyphPos[2 * i + off] - start;
/*      */     }
/*  419 */     return this.glyphAdvances;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Shape getDecorationOutline(int decorationType) {
/*  430 */     syncLayout();
/*      */     
/*  432 */     Shape g = new GeneralPath();
/*  433 */     if ((decorationType & 0x1) != 0) {
/*  434 */       ((GeneralPath)g).append(getUnderlineShape(), false);
/*      */     }
/*  436 */     if ((decorationType & 0x2) != 0) {
/*  437 */       ((GeneralPath)g).append(getStrikethroughShape(), false);
/*      */     }
/*  439 */     if ((decorationType & 0x4) != 0) {
/*  440 */       ((GeneralPath)g).append(getOverlineShape(), false);
/*      */     }
/*  442 */     return g;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getBounds2D() {
/*  449 */     syncLayout();
/*  450 */     return this.gv.getBounds2D(this.aci);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rectangle2D getGeometricBounds() {
/*  458 */     syncLayout();
/*      */     
/*  460 */     Rectangle2D gvB = this.gv.getGeometricBounds();
/*  461 */     Rectangle2D decB = getDecorationOutline(7).getBounds2D();
/*  462 */     return gvB.createUnion(decB);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point2D getTextPathAdvance() {
/*  470 */     syncLayout();
/*  471 */     if (this.textPath != null) {
/*  472 */       return this.textPathAdvance;
/*      */     }
/*  474 */     return getAdvance2D();
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
/*      */   public int getGlyphIndex(int charIndex) {
/*  488 */     int numGlyphs = getGlyphCount();
/*  489 */     int j = 0;
/*  490 */     for (int i = 0; i < numGlyphs; i++) {
/*  491 */       int count = getCharacterCount(i, i);
/*  492 */       for (int n = 0; n < count; n++) {
/*  493 */         int glyphCharIndex = this.charMap[j++];
/*  494 */         if (charIndex == glyphCharIndex)
/*  495 */           return i; 
/*  496 */         if (j >= this.charMap.length)
/*  497 */           return -1; 
/*      */       } 
/*      */     } 
/*  500 */     return -1;
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
/*      */   public int getLastGlyphIndex(int charIndex) {
/*  512 */     int numGlyphs = getGlyphCount();
/*  513 */     int j = this.charMap.length - 1;
/*  514 */     for (int i = numGlyphs - 1; i >= 0; i--) {
/*  515 */       int count = getCharacterCount(i, i);
/*  516 */       for (int n = 0; n < count; n++) {
/*  517 */         int glyphCharIndex = this.charMap[j--];
/*  518 */         if (charIndex == glyphCharIndex) return i; 
/*  519 */         if (j < 0) return -1; 
/*      */       } 
/*      */     } 
/*  522 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getComputedOrientationAngle(int index) {
/*  532 */     if (isGlyphOrientationAuto()) {
/*  533 */       if (isVertical()) {
/*  534 */         char ch = this.aci.setIndex(index);
/*  535 */         if (isLatinChar(ch)) {
/*  536 */           return 90.0D;
/*      */         }
/*  538 */         return 0.0D;
/*      */       } 
/*  540 */       return 0.0D;
/*      */     } 
/*      */     
/*  543 */     return getGlyphOrientationAngle();
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
/*      */   public Shape getHighlightShape(int beginCharIndex, int endCharIndex) {
/*  558 */     syncLayout();
/*      */     
/*  560 */     if (beginCharIndex > endCharIndex) {
/*  561 */       int temp = beginCharIndex;
/*  562 */       beginCharIndex = endCharIndex;
/*  563 */       endCharIndex = temp;
/*      */     } 
/*  565 */     GeneralPath shape = null;
/*  566 */     int numGlyphs = getGlyphCount();
/*      */     
/*  568 */     Point2D.Float[] topPts = new Point2D.Float[2 * numGlyphs];
/*  569 */     Point2D.Float[] botPts = new Point2D.Float[2 * numGlyphs];
/*      */     
/*  571 */     int ptIdx = 0;
/*      */     
/*  573 */     int currentChar = 0;
/*  574 */     for (int i = 0; i < numGlyphs; i++) {
/*  575 */       int glyphCharIndex = this.charMap[currentChar];
/*  576 */       if (glyphCharIndex >= beginCharIndex && glyphCharIndex <= endCharIndex && this.gv.isGlyphVisible(i)) {
/*      */ 
/*      */ 
/*      */         
/*  580 */         Shape gbounds = this.gv.getGlyphLogicalBounds(i);
/*  581 */         if (gbounds != null) {
/*      */           
/*  583 */           if (shape == null) {
/*  584 */             shape = new GeneralPath();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  589 */           float[] pts = new float[6];
/*  590 */           int count = 0;
/*  591 */           int type = -1;
/*      */           
/*  593 */           PathIterator pi = gbounds.getPathIterator(null);
/*  594 */           Point2D.Float firstPt = null;
/*      */           
/*  596 */           while (!pi.isDone()) {
/*  597 */             type = pi.currentSegment(pts);
/*  598 */             if (type == 0 || type == 1) {
/*      */ 
/*      */               
/*  601 */               if (count > 4)
/*  602 */                 break;  if (count == 4) {
/*      */                 
/*  604 */                 if (firstPt == null || firstPt.x != pts[0] || firstPt.y != pts[1]) {
/*      */                   break;
/*      */                 }
/*      */               }
/*      */               else {
/*      */                 
/*  610 */                 Point2D.Float pt = new Point2D.Float(pts[0], pts[1]);
/*  611 */                 if (count == 0) firstPt = pt;
/*      */                 
/*  613 */                 switch (count) { case 0:
/*  614 */                     botPts[ptIdx] = pt; break;
/*  615 */                   case 1: topPts[ptIdx] = pt; break;
/*  616 */                   case 2: topPts[ptIdx + 1] = pt; break;
/*  617 */                   case 3: botPts[ptIdx + 1] = pt; break; }
/*      */               
/*      */               } 
/*  620 */             } else if (type == 4) {
/*      */               
/*  622 */               if (count < 4 || count > 5) {
/*      */                 break;
/*      */               }
/*      */             } else {
/*      */               break;
/*      */             } 
/*  628 */             count++;
/*  629 */             pi.next();
/*      */           } 
/*  631 */           if (pi.isDone()) {
/*      */             
/*  633 */             if (botPts[ptIdx] != null && ((topPts[ptIdx]).x != (topPts[ptIdx + 1]).x || (topPts[ptIdx]).y != (topPts[ptIdx + 1]).y))
/*      */             {
/*      */ 
/*      */               
/*  637 */               ptIdx += 2;
/*      */             }
/*      */           } else {
/*      */             
/*  641 */             addPtsToPath(shape, topPts, botPts, ptIdx);
/*  642 */             ptIdx = 0;
/*  643 */             shape.append(gbounds, false);
/*      */           } 
/*      */         } 
/*      */       } 
/*  647 */       currentChar += getCharacterCount(i, i);
/*  648 */       if (currentChar >= this.charMap.length)
/*  649 */         currentChar = this.charMap.length - 1; 
/*      */     } 
/*  651 */     addPtsToPath(shape, topPts, botPts, ptIdx);
/*      */     
/*  653 */     return shape;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean epsEQ(double a, double b) {
/*  659 */     return (a + 1.0E-5D > b && a - 1.0E-5D < b);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int makeConvexHull(Point2D.Float[] pts, int numPts) {
/*  665 */     for (int i = 1; i < numPts; i++) {
/*      */ 
/*      */       
/*  668 */       if ((pts[i]).x < (pts[i - 1]).x || ((pts[i]).x == (pts[i - 1]).x && (pts[i]).y < (pts[i - 1]).y)) {
/*      */         
/*  670 */         Point2D.Float tmp = pts[i];
/*  671 */         pts[i] = pts[i - 1];
/*  672 */         pts[i - 1] = tmp;
/*  673 */         i = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  678 */     Point2D.Float pt0 = pts[0];
/*  679 */     Point2D.Float pt1 = pts[numPts - 1];
/*  680 */     Point2D.Float dxdy = new Point2D.Float(pt1.x - pt0.x, pt1.y - pt0.y);
/*  681 */     float c = dxdy.y * pt0.x - dxdy.x * pt0.y;
/*      */     
/*  683 */     Point2D.Float[] topList = new Point2D.Float[numPts];
/*  684 */     Point2D.Float[] botList = new Point2D.Float[numPts];
/*  685 */     topList[0] = pts[0]; botList[0] = pts[0];
/*  686 */     int nTopPts = 1;
/*  687 */     int nBotPts = 1;
/*  688 */     for (int j = 1; j < numPts - 1; j++) {
/*  689 */       Point2D.Float float_ = pts[j];
/*  690 */       float soln = dxdy.x * float_.y - dxdy.y * float_.x + c;
/*  691 */       if (soln < 0.0F) {
/*      */         
/*  693 */         while (nBotPts >= 2) {
/*  694 */           pt0 = botList[nBotPts - 2];
/*  695 */           pt1 = botList[nBotPts - 1];
/*  696 */           float dx = pt1.x - pt0.x;
/*  697 */           float dy = pt1.y - pt0.y;
/*  698 */           float c0 = dy * pt0.x - dx * pt0.y;
/*  699 */           soln = dx * float_.y - dy * float_.x + c0;
/*  700 */           if (soln > 1.0E-5D)
/*      */             break; 
/*  702 */           if (soln > -1.0E-5D) {
/*      */             
/*  704 */             if (pt1.y < float_.y) float_ = pt1; 
/*  705 */             nBotPts--;
/*      */             
/*      */             break;
/*      */           } 
/*  709 */           nBotPts--;
/*      */         } 
/*  711 */         botList[nBotPts++] = float_;
/*      */       } else {
/*      */         
/*  714 */         while (nTopPts >= 2) {
/*  715 */           pt0 = topList[nTopPts - 2];
/*  716 */           pt1 = topList[nTopPts - 1];
/*  717 */           float dx = pt1.x - pt0.x;
/*  718 */           float dy = pt1.y - pt0.y;
/*  719 */           float c0 = dy * pt0.x - dx * pt0.y;
/*  720 */           soln = dx * float_.y - dy * float_.x + c0;
/*  721 */           if (soln < -1.0E-5D)
/*      */             break; 
/*  723 */           if (soln < 1.0E-5D) {
/*      */             
/*  725 */             if (pt1.y > float_.y) float_ = pt1; 
/*  726 */             nTopPts--;
/*      */             
/*      */             break;
/*      */           } 
/*  730 */           nTopPts--;
/*      */         } 
/*  732 */         topList[nTopPts++] = float_;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  737 */     Point2D.Float pt = pts[numPts - 1];
/*  738 */     while (nBotPts >= 2) {
/*  739 */       pt0 = botList[nBotPts - 2];
/*  740 */       pt1 = botList[nBotPts - 1];
/*  741 */       float dx = pt1.x - pt0.x;
/*  742 */       float dy = pt1.y - pt0.y;
/*  743 */       float c0 = dy * pt0.x - dx * pt0.y;
/*  744 */       float soln = dx * pt.y - dy * pt.x + c0;
/*  745 */       if (soln > 1.0E-5D) {
/*      */         break;
/*      */       }
/*  748 */       if (soln > -1.0E-5D) {
/*      */         
/*  750 */         if (pt1.y >= pt.y) nBotPts--;
/*      */         
/*      */         break;
/*      */       } 
/*  754 */       nBotPts--;
/*      */     } 
/*      */     
/*  757 */     while (nTopPts >= 2) {
/*  758 */       pt0 = topList[nTopPts - 2];
/*  759 */       pt1 = topList[nTopPts - 1];
/*  760 */       float dx = pt1.x - pt0.x;
/*  761 */       float dy = pt1.y - pt0.y;
/*  762 */       float c0 = dy * pt0.x - dx * pt0.y;
/*  763 */       float soln = dx * pt.y - dy * pt.x + c0;
/*  764 */       if (soln < -1.0E-5D) {
/*      */         break;
/*      */       }
/*  767 */       if (soln < 1.0E-5D) {
/*      */         
/*  769 */         if (pt1.y <= pt.y) nTopPts--;
/*      */         
/*      */         break;
/*      */       } 
/*  773 */       nTopPts--;
/*      */     } 
/*      */     
/*  776 */     System.arraycopy(topList, 0, pts, 0, nTopPts);
/*  777 */     int k = nTopPts;
/*      */ 
/*      */     
/*  780 */     pts[k++] = pts[numPts - 1];
/*      */ 
/*      */     
/*  783 */     for (int n = nBotPts - 1; n > 0; n--, k++) {
/*  784 */       pts[k] = botList[n];
/*      */     }
/*  786 */     return k;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addPtsToPath(GeneralPath shape, Point2D.Float[] topPts, Point2D.Float[] botPts, int numPts) {
/*  793 */     if (numPts < 2)
/*  794 */       return;  if (numPts == 2) {
/*  795 */       shape.moveTo((topPts[0]).x, (topPts[0]).y);
/*  796 */       shape.lineTo((topPts[1]).x, (topPts[1]).y);
/*  797 */       shape.lineTo((botPts[1]).x, (botPts[1]).y);
/*  798 */       shape.lineTo((botPts[0]).x, (botPts[0]).y);
/*  799 */       shape.lineTo((topPts[0]).x, (topPts[0]).y);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  811 */     Point2D.Float[] boxes = new Point2D.Float[8];
/*  812 */     Point2D.Float[] chull = new Point2D.Float[8];
/*  813 */     boxes[4] = topPts[0];
/*  814 */     boxes[5] = topPts[1];
/*  815 */     boxes[6] = botPts[1];
/*  816 */     boxes[7] = botPts[0];
/*  817 */     Area[] areas = new Area[numPts / 2];
/*  818 */     int nAreas = 0;
/*  819 */     for (int i = 2; i < numPts; i += 2) {
/*  820 */       boxes[0] = boxes[4];
/*  821 */       boxes[1] = boxes[5];
/*  822 */       boxes[2] = boxes[6];
/*  823 */       boxes[3] = boxes[7];
/*  824 */       boxes[4] = topPts[i];
/*  825 */       boxes[5] = topPts[i + 1];
/*  826 */       boxes[6] = botPts[i + 1];
/*  827 */       boxes[7] = botPts[i];
/*      */ 
/*      */       
/*  830 */       float delta = (boxes[2]).x - (boxes[0]).x;
/*  831 */       float dist = delta * delta;
/*  832 */       delta = (boxes[2]).y - (boxes[0]).y;
/*  833 */       dist += delta * delta;
/*  834 */       float sz = (float)Math.sqrt(dist);
/*      */       
/*  836 */       delta = (boxes[6]).x - (boxes[4]).x;
/*  837 */       dist = delta * delta;
/*  838 */       delta = (boxes[6]).y - (boxes[4]).y;
/*  839 */       dist += delta * delta;
/*  840 */       sz += (float)Math.sqrt(dist);
/*      */       
/*  842 */       delta = ((boxes[0]).x + (boxes[1]).x + (boxes[2]).x + (boxes[3]).x - (boxes[4]).x + (boxes[5]).x + (boxes[6]).x + (boxes[7]).x) / 4.0F;
/*      */       
/*  844 */       dist = delta * delta;
/*  845 */       delta = ((boxes[0]).y + (boxes[1]).y + (boxes[2]).y + (boxes[3]).y - (boxes[4]).y + (boxes[5]).y + (boxes[6]).y + (boxes[7]).y) / 4.0F;
/*      */       
/*  847 */       dist += delta * delta;
/*  848 */       dist = (float)Math.sqrt(dist);
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
/*  859 */       GeneralPath gp = new GeneralPath();
/*  860 */       if (dist < sz) {
/*      */         
/*  862 */         System.arraycopy(boxes, 0, chull, 0, 8);
/*  863 */         int npts = makeConvexHull(chull, 8);
/*  864 */         gp.moveTo((chull[0]).x, (chull[0]).y);
/*  865 */         for (int n = 1; n < npts; n++)
/*  866 */           gp.lineTo((chull[n]).x, (chull[n]).y); 
/*  867 */         gp.closePath();
/*      */       } else {
/*      */         
/*  870 */         mergeAreas(shape, areas, nAreas);
/*  871 */         nAreas = 0;
/*      */ 
/*      */         
/*  874 */         if (i == 2) {
/*  875 */           gp.moveTo((boxes[0]).x, (boxes[0]).y);
/*  876 */           gp.lineTo((boxes[1]).x, (boxes[1]).y);
/*  877 */           gp.lineTo((boxes[2]).x, (boxes[2]).y);
/*  878 */           gp.lineTo((boxes[3]).x, (boxes[3]).y);
/*  879 */           gp.closePath();
/*  880 */           shape.append(gp, false);
/*  881 */           gp.reset();
/*      */         } 
/*  883 */         gp.moveTo((boxes[4]).x, (boxes[4]).y);
/*  884 */         gp.lineTo((boxes[5]).x, (boxes[5]).y);
/*  885 */         gp.lineTo((boxes[6]).x, (boxes[6]).y);
/*  886 */         gp.lineTo((boxes[7]).x, (boxes[7]).y);
/*  887 */         gp.closePath();
/*      */       } 
/*  889 */       areas[nAreas++] = new Area(gp);
/*      */     } 
/*      */     
/*  892 */     mergeAreas(shape, areas, nAreas);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void mergeAreas(GeneralPath shape, Area[] shapes, int nShapes) {
/*  901 */     while (nShapes > 1) {
/*  902 */       int n = 0;
/*  903 */       for (int i = 1; i < nShapes; i += 2) {
/*  904 */         shapes[i - 1].add(shapes[i]);
/*  905 */         shapes[n++] = shapes[i - 1];
/*  906 */         shapes[i] = null;
/*      */       } 
/*      */ 
/*      */       
/*  910 */       if ((nShapes & 0x1) == 1)
/*  911 */         shapes[n - 1].add(shapes[nShapes - 1]); 
/*  912 */       nShapes /= 2;
/*      */     } 
/*  914 */     if (nShapes == 1) {
/*  915 */       shape.append(shapes[0], false);
/*      */     }
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
/*      */   public TextHit hitTestChar(float x, float y) {
/*  929 */     syncLayout();
/*      */     
/*  931 */     TextHit textHit = null;
/*      */     
/*  933 */     int currentChar = 0;
/*  934 */     for (int i = 0; i < this.gv.getNumGlyphs(); i++) {
/*  935 */       Shape gbounds = this.gv.getGlyphLogicalBounds(i);
/*  936 */       if (gbounds != null) {
/*  937 */         Rectangle2D gbounds2d = gbounds.getBounds2D();
/*  938 */         if (gbounds.contains(x, y)) {
/*  939 */           boolean isRightHalf = (x > gbounds2d.getX() + gbounds2d.getWidth() / 2.0D);
/*      */           
/*  941 */           boolean isLeadingEdge = !isRightHalf;
/*  942 */           int charIndex = this.charMap[currentChar];
/*  943 */           textHit = new TextHit(charIndex, isLeadingEdge);
/*  944 */           return textHit;
/*      */         } 
/*      */       } 
/*  947 */       currentChar += getCharacterCount(i, i);
/*  948 */       if (currentChar >= this.charMap.length)
/*  949 */         currentChar = this.charMap.length - 1; 
/*      */     } 
/*  951 */     return textHit;
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
/*      */   protected GVTFont getFont() {
/*  964 */     this.aci.first();
/*  965 */     GVTFont gvtFont = (GVTFont)this.aci.getAttribute(GVT_FONT);
/*      */     
/*  967 */     if (gvtFont != null) {
/*  968 */       return gvtFont;
/*      */     }
/*      */     
/*  971 */     return (GVTFont)new AWTGVTFont(this.aci.getAttributes());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Shape getOverlineShape() {
/*  978 */     double y = this.metrics.getOverlineOffset();
/*  979 */     float overlineThickness = this.metrics.getOverlineThickness();
/*      */ 
/*      */ 
/*      */     
/*  983 */     y += overlineThickness;
/*      */ 
/*      */     
/*  986 */     this.aci.first();
/*  987 */     Float dy = (Float)this.aci.getAttribute(DY);
/*  988 */     if (dy != null) {
/*  989 */       y += dy.floatValue();
/*      */     }
/*  991 */     Stroke overlineStroke = new BasicStroke(overlineThickness);
/*      */     
/*  993 */     Rectangle2D logicalBounds = this.gv.getLogicalBounds();
/*      */     
/*  995 */     return overlineStroke.createStrokedShape(new Line2D.Double(logicalBounds.getMinX() + overlineThickness / 2.0D, this.offset.getY() + y, logicalBounds.getMaxX() - overlineThickness / 2.0D, this.offset.getY() + y));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Shape getUnderlineShape() {
/* 1006 */     double y = this.metrics.getUnderlineOffset();
/* 1007 */     float underlineThickness = this.metrics.getUnderlineThickness();
/*      */ 
/*      */ 
/*      */     
/* 1011 */     y += underlineThickness * 1.5D;
/*      */     
/* 1013 */     BasicStroke underlineStroke = new BasicStroke(underlineThickness);
/*      */ 
/*      */ 
/*      */     
/* 1017 */     this.aci.first();
/* 1018 */     Float dy = (Float)this.aci.getAttribute(DY);
/* 1019 */     if (dy != null) {
/* 1020 */       y += dy.floatValue();
/*      */     }
/* 1022 */     Rectangle2D logicalBounds = this.gv.getLogicalBounds();
/*      */     
/* 1024 */     return underlineStroke.createStrokedShape(new Line2D.Double(logicalBounds.getMinX() + underlineThickness / 2.0D, this.offset.getY() + y, logicalBounds.getMaxX() - underlineThickness / 2.0D, this.offset.getY() + y));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Shape getStrikethroughShape() {
/* 1034 */     double y = this.metrics.getStrikethroughOffset();
/* 1035 */     float strikethroughThickness = this.metrics.getStrikethroughThickness();
/*      */     
/* 1037 */     Stroke strikethroughStroke = new BasicStroke(strikethroughThickness);
/*      */ 
/*      */ 
/*      */     
/* 1041 */     this.aci.first();
/* 1042 */     Float dy = (Float)this.aci.getAttribute(DY);
/* 1043 */     if (dy != null) {
/* 1044 */       y += dy.floatValue();
/*      */     }
/* 1046 */     Rectangle2D logicalBounds = this.gv.getLogicalBounds();
/* 1047 */     return strikethroughStroke.createStrokedShape(new Line2D.Double(logicalBounds.getMinX() + strikethroughThickness / 2.0D, this.offset.getY() + y, logicalBounds.getMaxX() - strikethroughThickness / 2.0D, this.offset.getY() + y));
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
/*      */   protected void doExplicitGlyphLayout() {
/* 1061 */     this.gv.performDefaultLayout();
/*      */     
/* 1063 */     float baselineAscent = this.vertical ? (float)this.gv.getLogicalBounds().getWidth() : (this.metrics.getAscent() + Math.abs(this.metrics.getDescent()));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1068 */     int numGlyphs = this.gv.getNumGlyphs();
/*      */     
/* 1070 */     float[] gp = this.gv.getGlyphPositions(0, numGlyphs + 1, null);
/* 1071 */     float verticalFirstOffset = 0.0F;
/* 1072 */     float horizontalFirstOffset = 0.0F;
/*      */     
/* 1074 */     boolean glyphOrientationAuto = isGlyphOrientationAuto();
/* 1075 */     int glyphOrientationAngle = 0;
/* 1076 */     if (!glyphOrientationAuto) {
/* 1077 */       glyphOrientationAngle = getGlyphOrientationAngle();
/*      */     }
/* 1079 */     int i = 0;
/* 1080 */     int aciStart = this.aci.getBeginIndex();
/* 1081 */     int aciIndex = 0;
/* 1082 */     char ch = this.aci.first();
/* 1083 */     int runLimit = aciIndex + aciStart;
/*      */     
/* 1085 */     Float x = null, y = null, dx = null, dy = null, rotation = null;
/* 1086 */     Object baseline = null;
/*      */     
/* 1088 */     float shift_x_pos = 0.0F;
/* 1089 */     float shift_y_pos = 0.0F;
/* 1090 */     float curr_x_pos = (float)this.offset.getX();
/* 1091 */     float curr_y_pos = (float)this.offset.getY();
/*      */     
/* 1093 */     Point2D.Float pos = new Point2D.Float();
/* 1094 */     boolean hasArabicTransparent = false;
/*      */     
/* 1096 */     while (i < numGlyphs) {
/* 1097 */       if (aciIndex + aciStart >= runLimit) {
/* 1098 */         runLimit = this.aci.getRunLimit(runAtts);
/* 1099 */         x = (Float)this.aci.getAttribute(X);
/* 1100 */         y = (Float)this.aci.getAttribute(Y);
/* 1101 */         dx = (Float)this.aci.getAttribute(DX);
/* 1102 */         dy = (Float)this.aci.getAttribute(DY);
/* 1103 */         rotation = (Float)this.aci.getAttribute(ROTATION);
/* 1104 */         baseline = this.aci.getAttribute(BASELINE_SHIFT);
/*      */       } 
/*      */       
/* 1107 */       GVTGlyphMetrics gm = this.gv.getGlyphMetrics(i);
/*      */       
/* 1109 */       if (i == 0) {
/* 1110 */         if (isVertical()) {
/* 1111 */           if (glyphOrientationAuto) {
/* 1112 */             if (isLatinChar(ch)) {
/*      */               
/* 1114 */               verticalFirstOffset = 0.0F;
/*      */             } else {
/*      */               
/* 1117 */               float advY = gm.getVerticalAdvance();
/* 1118 */               float asc = this.metrics.getAscent();
/* 1119 */               float dsc = this.metrics.getDescent();
/* 1120 */               verticalFirstOffset = asc + (advY - asc + dsc) / 2.0F;
/*      */             }
/*      */           
/* 1123 */           } else if (glyphOrientationAngle == 0) {
/* 1124 */             float advY = gm.getVerticalAdvance();
/* 1125 */             float asc = this.metrics.getAscent();
/* 1126 */             float dsc = this.metrics.getDescent();
/* 1127 */             verticalFirstOffset = asc + (advY - asc + dsc) / 2.0F;
/*      */           } else {
/*      */             
/* 1130 */             verticalFirstOffset = 0.0F;
/*      */           }
/*      */         
/*      */         }
/* 1134 */         else if (glyphOrientationAngle == 270) {
/* 1135 */           horizontalFirstOffset = (float)gm.getBounds2D().getHeight();
/*      */         }
/*      */         else {
/*      */           
/* 1139 */           horizontalFirstOffset = 0.0F;
/*      */         }
/*      */       
/*      */       }
/* 1143 */       else if (glyphOrientationAuto && verticalFirstOffset == 0.0F && !isLatinChar(ch)) {
/*      */         
/* 1145 */         float advY = gm.getVerticalAdvance();
/* 1146 */         float asc = this.metrics.getAscent();
/* 1147 */         float dsc = this.metrics.getDescent();
/* 1148 */         verticalFirstOffset = asc + (advY - asc + dsc) / 2.0F;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1154 */       float ox = 0.0F;
/* 1155 */       float oy = 0.0F;
/* 1156 */       float glyphOrientationRotation = 0.0F;
/* 1157 */       float glyphRotation = 0.0F;
/*      */       
/* 1159 */       if (ch != Character.MAX_VALUE) {
/* 1160 */         if (this.vertical) {
/* 1161 */           if (glyphOrientationAuto) {
/* 1162 */             if (isLatinChar(ch)) {
/*      */ 
/*      */               
/* 1165 */               glyphOrientationRotation = 1.5707964F;
/*      */             } else {
/* 1167 */               glyphOrientationRotation = 0.0F;
/*      */             } 
/*      */           } else {
/* 1170 */             glyphOrientationRotation = (float)Math.toRadians(glyphOrientationAngle);
/*      */           } 
/* 1172 */           if (this.textPath != null)
/*      */           {
/* 1174 */             x = null;
/*      */           }
/*      */         } else {
/* 1177 */           glyphOrientationRotation = (float)Math.toRadians(glyphOrientationAngle);
/* 1178 */           if (this.textPath != null)
/*      */           {
/* 1180 */             y = null;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1185 */         if (rotation == null || rotation.isNaN()) {
/* 1186 */           glyphRotation = glyphOrientationRotation;
/*      */         } else {
/* 1188 */           glyphRotation = rotation.floatValue() + glyphOrientationRotation;
/*      */         } 
/*      */ 
/*      */         
/* 1192 */         if (x != null && !x.isNaN()) {
/* 1193 */           if (i == 0)
/* 1194 */             shift_x_pos = (float)(x.floatValue() - this.offset.getX()); 
/* 1195 */           curr_x_pos = x.floatValue() - shift_x_pos;
/*      */         } 
/* 1197 */         if (dx != null && !dx.isNaN()) {
/* 1198 */           curr_x_pos += dx.floatValue();
/*      */         }
/*      */         
/* 1201 */         if (y != null && !y.isNaN()) {
/* 1202 */           if (i == 0)
/* 1203 */             shift_y_pos = (float)(y.floatValue() - this.offset.getY()); 
/* 1204 */           curr_y_pos = y.floatValue() - shift_y_pos;
/*      */         } 
/* 1206 */         if (dy != null && !dy.isNaN()) {
/* 1207 */           curr_y_pos += dy.floatValue();
/* 1208 */         } else if (i > 0) {
/* 1209 */           curr_y_pos += gp[i * 2 + 1] - gp[i * 2 - 1];
/*      */         } 
/*      */         
/* 1212 */         float baselineAdjust = 0.0F;
/* 1213 */         if (baseline != null) {
/* 1214 */           if (baseline instanceof Integer) {
/* 1215 */             if (baseline == TextAttribute.SUPERSCRIPT_SUPER) {
/* 1216 */               baselineAdjust = baselineAscent * 0.5F;
/* 1217 */             } else if (baseline == TextAttribute.SUPERSCRIPT_SUB) {
/* 1218 */               baselineAdjust = -baselineAscent * 0.5F;
/*      */             } 
/* 1220 */           } else if (baseline instanceof Float) {
/* 1221 */             baselineAdjust = ((Float)baseline).floatValue();
/*      */           } 
/* 1223 */           if (this.vertical) {
/* 1224 */             ox = baselineAdjust;
/*      */           } else {
/* 1226 */             oy = -baselineAdjust;
/*      */           } 
/*      */         } 
/*      */         
/* 1230 */         if (this.vertical) {
/*      */           
/* 1232 */           oy += verticalFirstOffset;
/*      */           
/* 1234 */           if (glyphOrientationAuto) {
/* 1235 */             if (isLatinChar(ch)) {
/* 1236 */               ox += this.metrics.getStrikethroughOffset();
/*      */             } else {
/* 1238 */               Rectangle2D glyphBounds = this.gv.getGlyphVisualBounds(i).getBounds2D();
/*      */               
/* 1240 */               ox -= (float)(glyphBounds.getMaxX() - gp[2 * i] - glyphBounds.getWidth() / 2.0D);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1245 */             Rectangle2D glyphBounds = this.gv.getGlyphVisualBounds(i).getBounds2D();
/*      */             
/* 1247 */             if (glyphOrientationAngle == 0) {
/* 1248 */               ox -= (float)(glyphBounds.getMaxX() - gp[2 * i] - glyphBounds.getWidth() / 2.0D);
/*      */             }
/* 1250 */             else if (glyphOrientationAngle == 180) {
/* 1251 */               ox += (float)(glyphBounds.getMaxX() - gp[2 * i] - glyphBounds.getWidth() / 2.0D);
/*      */             }
/* 1253 */             else if (glyphOrientationAngle == 90) {
/* 1254 */               ox += this.metrics.getStrikethroughOffset();
/*      */             } else {
/* 1256 */               ox -= this.metrics.getStrikethroughOffset();
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1260 */           ox += horizontalFirstOffset;
/* 1261 */           if (glyphOrientationAngle == 90) {
/* 1262 */             oy -= gm.getHorizontalAdvance();
/* 1263 */           } else if (glyphOrientationAngle == 180) {
/* 1264 */             oy -= this.metrics.getAscent();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1270 */       pos.x = curr_x_pos + ox;
/* 1271 */       pos.y = curr_y_pos + oy;
/* 1272 */       this.gv.setGlyphPosition(i, pos);
/*      */ 
/*      */       
/* 1275 */       if (ArabicTextHandler.arabicCharTransparent(ch)) {
/* 1276 */         hasArabicTransparent = true;
/*      */       
/*      */       }
/* 1279 */       else if (this.vertical) {
/* 1280 */         float advanceY = 0.0F;
/* 1281 */         if (glyphOrientationAuto) {
/* 1282 */           if (isLatinChar(ch)) {
/* 1283 */             advanceY = gm.getHorizontalAdvance();
/*      */           } else {
/* 1285 */             advanceY = gm.getVerticalAdvance();
/*      */           }
/*      */         
/* 1288 */         } else if (glyphOrientationAngle == 0 || glyphOrientationAngle == 180) {
/*      */           
/* 1290 */           advanceY = gm.getVerticalAdvance();
/* 1291 */         } else if (glyphOrientationAngle == 90) {
/* 1292 */           advanceY = gm.getHorizontalAdvance();
/*      */         } else {
/* 1294 */           advanceY = gm.getHorizontalAdvance();
/*      */ 
/*      */           
/* 1297 */           this.gv.setGlyphTransform(i, AffineTransform.getTranslateInstance(0.0D, advanceY));
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1302 */         curr_y_pos += advanceY;
/*      */       } else {
/* 1304 */         float advanceX = 0.0F;
/* 1305 */         if (glyphOrientationAngle == 0) {
/* 1306 */           advanceX = gm.getHorizontalAdvance();
/* 1307 */         } else if (glyphOrientationAngle == 180) {
/* 1308 */           advanceX = gm.getHorizontalAdvance();
/*      */ 
/*      */           
/* 1311 */           this.gv.setGlyphTransform(i, AffineTransform.getTranslateInstance(advanceX, 0.0D));
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1316 */           advanceX = gm.getVerticalAdvance();
/*      */         } 
/* 1318 */         curr_x_pos += advanceX;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1323 */       if (!epsEQ(glyphRotation, 0.0D)) {
/* 1324 */         AffineTransform rotAt, glyphTransform = this.gv.getGlyphTransform(i);
/* 1325 */         if (glyphTransform == null) {
/* 1326 */           glyphTransform = new AffineTransform();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1331 */         if (epsEQ(glyphRotation, 1.5707963267948966D)) {
/* 1332 */           rotAt = new AffineTransform(0.0F, 1.0F, -1.0F, 0.0F, 0.0F, 0.0F);
/* 1333 */         } else if (epsEQ(glyphRotation, Math.PI)) {
/* 1334 */           rotAt = new AffineTransform(-1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F);
/* 1335 */         } else if (epsEQ(glyphRotation, 4.71238898038469D)) {
/* 1336 */           rotAt = new AffineTransform(0.0F, -1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
/*      */         } else {
/* 1338 */           rotAt = AffineTransform.getRotateInstance(glyphRotation);
/*      */         } 
/* 1340 */         glyphTransform.concatenate(rotAt);
/* 1341 */         this.gv.setGlyphTransform(i, glyphTransform);
/*      */       } 
/*      */       
/* 1344 */       aciIndex += this.gv.getCharacterCount(i, i);
/* 1345 */       if (aciIndex >= this.charMap.length)
/* 1346 */         aciIndex = this.charMap.length - 1; 
/* 1347 */       ch = this.aci.setIndex(aciIndex + aciStart);
/* 1348 */       i++;
/*      */     } 
/*      */     
/* 1351 */     pos.x = curr_x_pos;
/* 1352 */     pos.y = curr_y_pos;
/* 1353 */     this.gv.setGlyphPosition(i, pos);
/*      */     
/* 1355 */     this.advance = new Point2D.Float((float)(curr_x_pos - this.offset.getX()), (float)(curr_y_pos - this.offset.getY()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1361 */     if (hasArabicTransparent) {
/* 1362 */       ch = this.aci.first();
/* 1363 */       aciIndex = 0;
/* 1364 */       i = 0;
/* 1365 */       int transparentStart = -1;
/* 1366 */       while (i < numGlyphs) {
/* 1367 */         if (ArabicTextHandler.arabicCharTransparent(ch)) {
/* 1368 */           if (transparentStart == -1) transparentStart = i;
/*      */         
/* 1370 */         } else if (transparentStart != -1) {
/* 1371 */           Point2D loc = this.gv.getGlyphPosition(i);
/* 1372 */           GVTGlyphMetrics gm = this.gv.getGlyphMetrics(i);
/* 1373 */           int tyS = 0, txS = 0;
/* 1374 */           float advX = 0.0F, advY = 0.0F;
/* 1375 */           if (this.vertical) {
/* 1376 */             if (glyphOrientationAuto || glyphOrientationAngle == 90) {
/*      */               
/* 1378 */               advY = gm.getHorizontalAdvance();
/* 1379 */             } else if (glyphOrientationAngle == 270) {
/* 1380 */               advY = 0.0F;
/* 1381 */             } else if (glyphOrientationAngle == 0) {
/* 1382 */               advX = gm.getHorizontalAdvance();
/*      */             } else {
/* 1384 */               advX = -gm.getHorizontalAdvance();
/*      */             } 
/* 1386 */           } else if (glyphOrientationAngle == 0) {
/* 1387 */             advX = gm.getHorizontalAdvance();
/* 1388 */           } else if (glyphOrientationAngle == 90) {
/* 1389 */             advY = gm.getHorizontalAdvance();
/* 1390 */           } else if (glyphOrientationAngle == 180) {
/* 1391 */             advX = 0.0F;
/*      */           } else {
/* 1393 */             advY = -gm.getHorizontalAdvance();
/*      */           } 
/* 1395 */           float baseX = (float)(loc.getX() + advX);
/* 1396 */           float baseY = (float)(loc.getY() + advY);
/* 1397 */           for (int j = transparentStart; j < i; j++) {
/* 1398 */             Point2D locT = this.gv.getGlyphPosition(j);
/* 1399 */             GVTGlyphMetrics gmT = this.gv.getGlyphMetrics(j);
/* 1400 */             float locX = (float)locT.getX();
/* 1401 */             float locY = (float)locT.getY();
/* 1402 */             float tx = 0.0F, ty = 0.0F;
/* 1403 */             float advT = gmT.getHorizontalAdvance();
/* 1404 */             if (this.vertical) {
/* 1405 */               if (glyphOrientationAuto || glyphOrientationAngle == 90) {
/*      */                 
/* 1407 */                 locY = baseY - advT;
/* 1408 */               } else if (glyphOrientationAngle == 270) {
/* 1409 */                 locY = baseY + advT;
/* 1410 */               } else if (glyphOrientationAngle == 0) {
/* 1411 */                 locX = baseX - advT;
/*      */               } else {
/* 1413 */                 locX = baseX + advT;
/*      */               } 
/* 1415 */             } else if (glyphOrientationAngle == 0) {
/* 1416 */               locX = baseX - advT;
/* 1417 */             } else if (glyphOrientationAngle == 90) {
/* 1418 */               locY = baseY - advT;
/* 1419 */             } else if (glyphOrientationAngle == 180) {
/* 1420 */               locX = baseX + advT;
/*      */             } else {
/* 1422 */               locY = baseY + advT;
/*      */             } 
/*      */             
/* 1425 */             locT = new Point2D.Double(locX, locY);
/* 1426 */             this.gv.setGlyphPosition(j, locT);
/* 1427 */             if (txS != 0 || tyS != 0) {
/*      */               
/* 1429 */               AffineTransform at = AffineTransform.getTranslateInstance(tx, ty);
/*      */               
/* 1431 */               at.concatenate(this.gv.getGlyphTransform(i));
/* 1432 */               this.gv.setGlyphTransform(i, at);
/*      */             } 
/*      */           } 
/* 1435 */           transparentStart = -1;
/*      */         } 
/*      */         
/* 1438 */         aciIndex += this.gv.getCharacterCount(i, i);
/* 1439 */         if (aciIndex >= this.charMap.length)
/* 1440 */           aciIndex = this.charMap.length - 1; 
/* 1441 */         ch = this.aci.setIndex(aciIndex + aciStart);
/* 1442 */         i++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1448 */     this.layoutApplied = true;
/* 1449 */     this.spacingApplied = false;
/* 1450 */     this.glyphAdvances = null;
/* 1451 */     this.pathApplied = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void adjustTextSpacing() {
/* 1459 */     if (this.spacingApplied) {
/*      */       return;
/*      */     }
/*      */     
/* 1463 */     if (!this.layoutApplied)
/*      */     {
/* 1465 */       doExplicitGlyphLayout();
/*      */     }
/* 1467 */     this.aci.first();
/* 1468 */     Boolean customSpacing = (Boolean)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.CUSTOM_SPACING);
/*      */     
/* 1470 */     if (customSpacing != null && customSpacing.booleanValue()) {
/* 1471 */       this.advance = doSpacing((Float)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.KERNING), (Float)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.LETTER_SPACING), (Float)this.aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.WORD_SPACING));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1479 */       this.layoutApplied = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     applyStretchTransform(!this.adjSpacing);
/*      */     
/* 1486 */     this.spacingApplied = true;
/* 1487 */     this.pathApplied = false;
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
/*      */   protected Point2D doSpacing(Float kern, Float letterSpacing, Float wordSpacing) {
/* 1501 */     boolean autoKern = true;
/* 1502 */     boolean doWordSpacing = false;
/* 1503 */     boolean doLetterSpacing = false;
/* 1504 */     float kernVal = 0.0F;
/* 1505 */     float letterSpacingVal = 0.0F;
/*      */     
/* 1507 */     if (kern != null && !kern.isNaN()) {
/* 1508 */       kernVal = kern.floatValue();
/* 1509 */       autoKern = false;
/*      */     } 
/* 1511 */     if (letterSpacing != null && !letterSpacing.isNaN()) {
/* 1512 */       letterSpacingVal = letterSpacing.floatValue();
/* 1513 */       doLetterSpacing = true;
/*      */     } 
/* 1515 */     if (wordSpacing != null && !wordSpacing.isNaN()) {
/* 1516 */       doWordSpacing = true;
/*      */     }
/*      */     
/* 1519 */     int numGlyphs = this.gv.getNumGlyphs();
/*      */     
/* 1521 */     float dx = 0.0F;
/* 1522 */     float dy = 0.0F;
/* 1523 */     Point2D[] newPositions = new Point2D[numGlyphs + 1];
/* 1524 */     Point2D prevPos = this.gv.getGlyphPosition(0);
/* 1525 */     int prevCode = this.gv.getGlyphCode(0);
/* 1526 */     float x = (float)prevPos.getX();
/* 1527 */     float y = (float)prevPos.getY();
/*      */     
/* 1529 */     Point2D lastCharAdvance = new Point2D.Double(this.advance.getX() - this.gv.getGlyphPosition(numGlyphs - 1).getX() - x, this.advance.getY() - this.gv.getGlyphPosition(numGlyphs - 1).getY() - y);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1534 */       GVTFont font = this.gv.getFont();
/*      */       
/* 1536 */       if (numGlyphs > 1 && (doLetterSpacing || !autoKern)) {
/* 1537 */         int i; for (i = 1; i <= numGlyphs; i++) {
/* 1538 */           Point2D gpos = this.gv.getGlyphPosition(i);
/*      */           
/* 1540 */           int currCode = (i == numGlyphs) ? -1 : this.gv.getGlyphCode(i);
/* 1541 */           dx = (float)gpos.getX() - (float)prevPos.getX();
/* 1542 */           dy = (float)gpos.getY() - (float)prevPos.getY();
/* 1543 */           if (autoKern) {
/* 1544 */             if (this.vertical) { dy += letterSpacingVal; }
/* 1545 */             else { dx += letterSpacingVal; }
/*      */ 
/*      */           
/*      */           }
/* 1549 */           else if (this.vertical) {
/* 1550 */             float vKern = 0.0F;
/* 1551 */             if (currCode != -1)
/* 1552 */               vKern = font.getVKern(prevCode, currCode); 
/* 1553 */             dy += kernVal - vKern + letterSpacingVal;
/*      */           } else {
/* 1555 */             float hKern = 0.0F;
/* 1556 */             if (currCode != -1)
/* 1557 */               hKern = font.getHKern(prevCode, currCode); 
/* 1558 */             dx += kernVal - hKern + letterSpacingVal;
/*      */           } 
/*      */           
/* 1561 */           x += dx;
/* 1562 */           y += dy;
/* 1563 */           newPositions[i] = new Point2D.Float(x, y);
/* 1564 */           prevPos = gpos;
/* 1565 */           prevCode = currCode;
/*      */         } 
/*      */         
/* 1568 */         for (i = 1; i <= numGlyphs; i++) {
/* 1569 */           if (newPositions[i] != null) {
/* 1570 */             this.gv.setGlyphPosition(i, newPositions[i]);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1576 */       if (this.vertical) {
/* 1577 */         lastCharAdvance.setLocation(lastCharAdvance.getX(), lastCharAdvance.getY() + kernVal + letterSpacingVal);
/*      */       }
/*      */       else {
/*      */         
/* 1581 */         lastCharAdvance.setLocation(lastCharAdvance.getX() + kernVal + letterSpacingVal, lastCharAdvance.getY());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1587 */       dx = 0.0F;
/* 1588 */       dy = 0.0F;
/* 1589 */       prevPos = this.gv.getGlyphPosition(0);
/* 1590 */       x = (float)prevPos.getX();
/* 1591 */       y = (float)prevPos.getY();
/*      */       
/* 1593 */       if (numGlyphs > 1 && doWordSpacing) {
/* 1594 */         for (int i = 1; i < numGlyphs; i++) {
/* 1595 */           Point2D gpos = this.gv.getGlyphPosition(i);
/* 1596 */           dx = (float)gpos.getX() - (float)prevPos.getX();
/* 1597 */           dy = (float)gpos.getY() - (float)prevPos.getY();
/* 1598 */           boolean inWS = false;
/*      */           
/* 1600 */           int beginWS = i;
/* 1601 */           int endWS = i;
/* 1602 */           GVTGlyphMetrics gm = this.gv.getGlyphMetrics(i);
/*      */ 
/*      */           
/* 1605 */           while (gm.getBounds2D().getWidth() < 0.01D || gm.isWhitespace()) {
/* 1606 */             if (!inWS) inWS = true; 
/* 1607 */             if (i == numGlyphs - 1) {
/*      */               break;
/*      */             }
/*      */             
/* 1611 */             i++;
/* 1612 */             endWS++;
/* 1613 */             gpos = this.gv.getGlyphPosition(i);
/* 1614 */             gm = this.gv.getGlyphMetrics(i);
/*      */           } 
/*      */           
/* 1617 */           if (inWS) {
/* 1618 */             int nWS = endWS - beginWS;
/* 1619 */             float px = (float)prevPos.getX();
/* 1620 */             float py = (float)prevPos.getY();
/* 1621 */             dx = (float)(gpos.getX() - px) / (nWS + 1);
/* 1622 */             dy = (float)(gpos.getY() - py) / (nWS + 1);
/* 1623 */             if (this.vertical) {
/* 1624 */               dy += wordSpacing.floatValue() / (nWS + 1);
/*      */             } else {
/* 1626 */               dx += wordSpacing.floatValue() / (nWS + 1);
/*      */             } 
/* 1628 */             for (int k = beginWS; k <= endWS; k++) {
/* 1629 */               x += dx;
/* 1630 */               y += dy;
/* 1631 */               newPositions[k] = new Point2D.Float(x, y);
/*      */             } 
/*      */           } else {
/* 1634 */             dx = (float)(gpos.getX() - prevPos.getX());
/* 1635 */             dy = (float)(gpos.getY() - prevPos.getY());
/* 1636 */             x += dx;
/* 1637 */             y += dy;
/* 1638 */             newPositions[i] = new Point2D.Float(x, y);
/*      */           } 
/* 1640 */           prevPos = gpos;
/*      */         } 
/* 1642 */         Point2D gPos = this.gv.getGlyphPosition(numGlyphs);
/* 1643 */         x += (float)(gPos.getX() - prevPos.getX());
/* 1644 */         y += (float)(gPos.getY() - prevPos.getY());
/* 1645 */         newPositions[numGlyphs] = new Point2D.Float(x, y);
/*      */         
/* 1647 */         for (int j = 1; j <= numGlyphs; j++) {
/* 1648 */           if (newPositions[j] != null) {
/* 1649 */             this.gv.setGlyphPosition(j, newPositions[j]);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/* 1654 */     } catch (Exception e) {
/* 1655 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */     
/* 1659 */     double advX = this.gv.getGlyphPosition(numGlyphs - 1).getX() - this.gv.getGlyphPosition(0).getX();
/*      */     
/* 1661 */     double advY = this.gv.getGlyphPosition(numGlyphs - 1).getY() - this.gv.getGlyphPosition(0).getY();
/*      */     
/* 1663 */     Point2D newAdvance = new Point2D.Double(advX + lastCharAdvance.getX(), advY + lastCharAdvance.getY());
/*      */     
/* 1665 */     return newAdvance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyStretchTransform(boolean stretchGlyphs) {
/* 1675 */     if (this.xScale == 1.0F && this.yScale == 1.0F) {
/*      */       return;
/*      */     }
/* 1678 */     AffineTransform scaleAT = AffineTransform.getScaleInstance(this.xScale, this.yScale);
/*      */ 
/*      */     
/* 1681 */     int numGlyphs = this.gv.getNumGlyphs();
/* 1682 */     float[] gp = this.gv.getGlyphPositions(0, numGlyphs + 1, null);
/*      */     
/* 1684 */     float initX = gp[0];
/* 1685 */     float initY = gp[1];
/* 1686 */     Point2D.Float pos = new Point2D.Float();
/* 1687 */     for (int i = 0; i <= numGlyphs; i++) {
/* 1688 */       float dx = gp[2 * i] - initX;
/* 1689 */       float dy = gp[2 * i + 1] - initY;
/* 1690 */       pos.x = initX + dx * this.xScale;
/* 1691 */       pos.y = initY + dy * this.yScale;
/* 1692 */       this.gv.setGlyphPosition(i, pos);
/*      */       
/* 1694 */       if (stretchGlyphs && i != numGlyphs) {
/*      */         
/* 1696 */         AffineTransform glyphTransform = this.gv.getGlyphTransform(i);
/* 1697 */         if (glyphTransform != null) {
/* 1698 */           glyphTransform.preConcatenate(scaleAT);
/* 1699 */           this.gv.setGlyphTransform(i, glyphTransform);
/*      */         } else {
/* 1701 */           this.gv.setGlyphTransform(i, scaleAT);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1706 */     this.advance = new Point2D.Float((float)(this.advance.getX() * this.xScale), (float)(this.advance.getY() * this.yScale));
/*      */ 
/*      */     
/* 1709 */     this.layoutApplied = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doPathLayout() {
/*      */     float glyphsLength, currentPosition;
/* 1717 */     if (this.pathApplied) {
/*      */       return;
/*      */     }
/* 1720 */     if (!this.spacingApplied)
/*      */     {
/* 1722 */       adjustTextSpacing();
/*      */     }
/* 1724 */     getGlyphAdvances();
/*      */ 
/*      */     
/* 1727 */     if (this.textPath == null) {
/*      */       
/* 1729 */       this.pathApplied = true;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1734 */     boolean horizontal = !isVertical();
/*      */     
/* 1736 */     boolean glyphOrientationAuto = isGlyphOrientationAuto();
/* 1737 */     int glyphOrientationAngle = 0;
/* 1738 */     if (!glyphOrientationAuto) {
/* 1739 */       glyphOrientationAngle = getGlyphOrientationAngle();
/*      */     }
/*      */     
/* 1742 */     float pathLength = this.textPath.lengthOfPath();
/* 1743 */     float startOffset = this.textPath.getStartOffset();
/* 1744 */     int numGlyphs = this.gv.getNumGlyphs();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1749 */     for (int i = 0; i < numGlyphs; i++) {
/* 1750 */       this.gv.setGlyphVisible(i, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1756 */     if (horizontal) {
/* 1757 */       glyphsLength = (float)this.gv.getLogicalBounds().getWidth();
/*      */     } else {
/* 1759 */       glyphsLength = (float)this.gv.getLogicalBounds().getHeight();
/*      */     } 
/*      */ 
/*      */     
/* 1763 */     if (pathLength == 0.0F || glyphsLength == 0.0F) {
/*      */       
/* 1765 */       this.pathApplied = true;
/* 1766 */       this.textPathAdvance = this.advance;
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 1774 */     Point2D firstGlyphPosition = this.gv.getGlyphPosition(0);
/* 1775 */     float glyphOffset = 0.0F;
/*      */     
/* 1777 */     if (horizontal) {
/* 1778 */       glyphOffset = (float)firstGlyphPosition.getY();
/* 1779 */       currentPosition = (float)(firstGlyphPosition.getX() + startOffset);
/*      */     } else {
/* 1781 */       glyphOffset = (float)firstGlyphPosition.getX();
/* 1782 */       currentPosition = (float)(firstGlyphPosition.getY() + startOffset);
/*      */     } 
/*      */     
/* 1785 */     char ch = this.aci.first();
/* 1786 */     int start = this.aci.getBeginIndex();
/* 1787 */     int currentChar = 0;
/* 1788 */     int lastGlyphDrawn = -1;
/* 1789 */     float lastGlyphAdvance = 0.0F;
/*      */     
/* 1791 */     for (int j = 0; j < numGlyphs; j++) {
/*      */       float charMidPos;
/* 1793 */       Point2D currentGlyphPos = this.gv.getGlyphPosition(j);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1798 */       float glyphAdvance = 0.0F;
/* 1799 */       float nextGlyphOffset = 0.0F;
/* 1800 */       Point2D nextGlyphPosition = this.gv.getGlyphPosition(j + 1);
/* 1801 */       if (horizontal) {
/* 1802 */         glyphAdvance = (float)(nextGlyphPosition.getX() - currentGlyphPos.getX());
/*      */         
/* 1804 */         nextGlyphOffset = (float)(nextGlyphPosition.getY() - currentGlyphPos.getY());
/*      */       } else {
/*      */         
/* 1807 */         glyphAdvance = (float)(nextGlyphPosition.getY() - currentGlyphPos.getY());
/*      */         
/* 1809 */         nextGlyphOffset = (float)(nextGlyphPosition.getX() - currentGlyphPos.getX());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1814 */       Rectangle2D glyphBounds = this.gv.getGlyphOutline(j).getBounds2D();
/* 1815 */       float glyphWidth = (float)glyphBounds.getWidth();
/* 1816 */       float glyphHeight = (float)glyphBounds.getHeight();
/* 1817 */       float glyphMidX = 0.0F;
/* 1818 */       if (glyphWidth > 0.0F) {
/* 1819 */         glyphMidX = (float)(glyphBounds.getX() + (glyphWidth / 2.0F));
/* 1820 */         glyphMidX -= (float)currentGlyphPos.getX();
/*      */       } 
/*      */       
/* 1823 */       float glyphMidY = 0.0F;
/* 1824 */       if (glyphHeight > 0.0F) {
/* 1825 */         glyphMidY = (float)(glyphBounds.getY() + (glyphHeight / 2.0F));
/* 1826 */         glyphMidY -= (float)currentGlyphPos.getY();
/*      */       } 
/*      */ 
/*      */       
/* 1830 */       if (horizontal) {
/* 1831 */         charMidPos = currentPosition + glyphMidX;
/*      */       } else {
/* 1833 */         charMidPos = currentPosition + glyphMidY;
/*      */       } 
/*      */ 
/*      */       
/* 1837 */       Point2D charMidPoint = this.textPath.pointAtLength(charMidPos);
/*      */ 
/*      */       
/* 1840 */       if (charMidPoint != null) {
/*      */ 
/*      */         
/* 1843 */         float angle = this.textPath.angleAtLength(charMidPos);
/*      */ 
/*      */         
/* 1846 */         AffineTransform glyphPathTransform = new AffineTransform();
/*      */ 
/*      */         
/* 1849 */         if (horizontal) {
/* 1850 */           glyphPathTransform.rotate(angle);
/*      */         } else {
/* 1852 */           glyphPathTransform.rotate(angle - 1.5707963267948966D);
/*      */         } 
/*      */ 
/*      */         
/* 1856 */         if (horizontal) {
/* 1857 */           glyphPathTransform.translate(0.0D, glyphOffset);
/*      */         } else {
/* 1859 */           glyphPathTransform.translate(glyphOffset, 0.0D);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1864 */         if (horizontal) {
/* 1865 */           glyphPathTransform.translate(-glyphMidX, 0.0D);
/*      */         } else {
/* 1867 */           glyphPathTransform.translate(0.0D, -glyphMidY);
/*      */         } 
/*      */ 
/*      */         
/* 1871 */         AffineTransform glyphTransform = this.gv.getGlyphTransform(j);
/* 1872 */         if (glyphTransform != null) {
/* 1873 */           glyphPathTransform.concatenate(glyphTransform);
/*      */         }
/*      */         
/* 1876 */         this.gv.setGlyphTransform(j, glyphPathTransform);
/* 1877 */         this.gv.setGlyphPosition(j, charMidPoint);
/*      */ 
/*      */         
/* 1880 */         lastGlyphDrawn = j;
/* 1881 */         lastGlyphAdvance = glyphAdvance;
/*      */       }
/*      */       else {
/*      */         
/* 1885 */         this.gv.setGlyphVisible(j, false);
/*      */       } 
/* 1887 */       currentPosition += glyphAdvance;
/* 1888 */       glyphOffset += nextGlyphOffset;
/* 1889 */       currentChar += this.gv.getCharacterCount(j, j);
/* 1890 */       if (currentChar >= this.charMap.length)
/* 1891 */         currentChar = this.charMap.length - 1; 
/* 1892 */       ch = this.aci.setIndex(currentChar + start);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1898 */     if (lastGlyphDrawn > -1) {
/* 1899 */       Point2D lastGlyphPos = this.gv.getGlyphPosition(lastGlyphDrawn);
/* 1900 */       if (horizontal) {
/* 1901 */         this.textPathAdvance = new Point2D.Double(lastGlyphPos.getX() + lastGlyphAdvance, lastGlyphPos.getY());
/*      */       }
/*      */       else {
/*      */         
/* 1905 */         this.textPathAdvance = new Point2D.Double(lastGlyphPos.getX(), lastGlyphPos.getY() + lastGlyphAdvance);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1910 */       this.textPathAdvance = new Point2D.Double(0.0D, 0.0D);
/*      */     } 
/*      */ 
/*      */     
/* 1914 */     this.layoutApplied = false;
/*      */     
/* 1916 */     this.spacingApplied = false;
/* 1917 */     this.pathApplied = true;
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
/*      */   protected boolean isLatinChar(char c) {
/* 1930 */     if (c < 'ÿ' && Character.isLetterOrDigit(c))
/*      */     {
/* 1932 */       return true;
/*      */     }
/*      */     
/* 1935 */     Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
/*      */     
/* 1937 */     if (block == Character.UnicodeBlock.BASIC_LATIN || block == Character.UnicodeBlock.LATIN_1_SUPPLEMENT || block == Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL || block == Character.UnicodeBlock.LATIN_EXTENDED_A || block == Character.UnicodeBlock.LATIN_EXTENDED_B || block == Character.UnicodeBlock.ARABIC || block == Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A || block == Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1945 */       return true;
/*      */     }
/* 1947 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isGlyphOrientationAuto() {
/* 1954 */     if (!isVertical()) return false; 
/* 1955 */     this.aci.first();
/* 1956 */     Integer vOrient = (Integer)this.aci.getAttribute(VERTICAL_ORIENTATION);
/* 1957 */     if (vOrient != null) {
/* 1958 */       return (vOrient == ORIENTATION_AUTO);
/*      */     }
/* 1960 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getGlyphOrientationAngle() {
/*      */     Float angle;
/* 1969 */     int glyphOrientationAngle = 0;
/*      */     
/* 1971 */     this.aci.first();
/*      */ 
/*      */     
/* 1974 */     if (isVertical()) {
/* 1975 */       angle = (Float)this.aci.getAttribute(VERTICAL_ORIENTATION_ANGLE);
/*      */     } else {
/* 1977 */       angle = (Float)this.aci.getAttribute(HORIZONTAL_ORIENTATION_ANGLE);
/*      */     } 
/*      */     
/* 1980 */     if (angle != null) {
/* 1981 */       glyphOrientationAngle = (int)angle.floatValue();
/*      */     }
/*      */ 
/*      */     
/* 1985 */     if (glyphOrientationAngle != 0 || glyphOrientationAngle != 90 || glyphOrientationAngle != 180 || glyphOrientationAngle != 270) {
/*      */ 
/*      */       
/* 1988 */       while (glyphOrientationAngle < 0) {
/* 1989 */         glyphOrientationAngle += 360;
/*      */       }
/*      */       
/* 1992 */       while (glyphOrientationAngle >= 360) {
/* 1993 */         glyphOrientationAngle -= 360;
/*      */       }
/*      */       
/* 1996 */       if (glyphOrientationAngle <= 45 || glyphOrientationAngle > 315) {
/*      */         
/* 1998 */         glyphOrientationAngle = 0;
/* 1999 */       } else if (glyphOrientationAngle > 45 && glyphOrientationAngle <= 135) {
/*      */         
/* 2001 */         glyphOrientationAngle = 90;
/* 2002 */       } else if (glyphOrientationAngle > 135 && glyphOrientationAngle <= 225) {
/*      */         
/* 2004 */         glyphOrientationAngle = 180;
/*      */       } else {
/* 2006 */         glyphOrientationAngle = 270;
/*      */       } 
/*      */     } 
/* 2009 */     return glyphOrientationAngle;
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
/*      */   public boolean hasCharacterIndex(int index) {
/* 2021 */     for (int aCharMap : this.charMap) {
/* 2022 */       if (index == aCharMap)
/* 2023 */         return true; 
/*      */     } 
/* 2025 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAltGlyph() {
/* 2033 */     return this.isAltGlyph;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isReversed() {
/* 2038 */     return this.gv.isReversed();
/*      */   }
/*      */ 
/*      */   
/*      */   public void maybeReverse(boolean mirror) {
/* 2043 */     this.gv.maybeReverse(mirror);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/GlyphLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */