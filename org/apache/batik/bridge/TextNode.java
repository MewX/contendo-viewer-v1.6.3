/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.gvt.AbstractGraphicsNode;
/*     */ import org.apache.batik.gvt.Selectable;
/*     */ import org.apache.batik.gvt.text.AttributedCharacterSpanIterator;
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
/*     */ public class TextNode
/*     */   extends AbstractGraphicsNode
/*     */   implements Selectable
/*     */ {
/*  46 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected Point2D location = new Point2D.Float(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributedCharacterIterator aci;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String text;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected Mark beginMark = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected Mark endMark = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List textRuns;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   protected TextPainter textPainter = StrokingTextPainter.getInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Rectangle2D geometryBounds;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Rectangle2D primitiveBounds;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Shape outline;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextPainter(TextPainter textPainter) {
/* 115 */     if (textPainter == null) {
/* 116 */       this.textPainter = StrokingTextPainter.getInstance();
/*     */     } else {
/* 118 */       this.textPainter = textPainter;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextPainter getTextPainter() {
/* 126 */     return this.textPainter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getTextRuns() {
/* 133 */     return this.textRuns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextRuns(List textRuns) {
/* 142 */     this.textRuns = textRuns;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 150 */     if (this.text != null) {
/* 151 */       return this.text;
/*     */     }
/* 153 */     if (this.aci == null) {
/* 154 */       this.text = "";
/*     */     } else {
/* 156 */       StringBuffer buf = new StringBuffer(this.aci.getEndIndex());
/* 157 */       char c = this.aci.first();
/* 158 */       for (; c != Character.MAX_VALUE; 
/* 159 */         c = this.aci.next()) {
/* 160 */         buf.append(c);
/*     */       }
/* 162 */       this.text = buf.toString();
/*     */     } 
/* 164 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(Point2D newLocation) {
/* 173 */     fireGraphicsNodeChangeStarted();
/* 174 */     invalidateGeometryCache();
/* 175 */     this.location = newLocation;
/* 176 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getLocation() {
/* 185 */     return this.location;
/*     */   }
/*     */ 
/*     */   
/*     */   public void swapTextPaintInfo(TextPaintInfo newInfo, TextPaintInfo oldInfo) {
/* 190 */     fireGraphicsNodeChangeStarted();
/* 191 */     invalidateGeometryCache();
/* 192 */     oldInfo.set(newInfo);
/* 193 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributedCharacterIterator(AttributedCharacterIterator newAci) {
/* 204 */     fireGraphicsNodeChangeStarted();
/* 205 */     invalidateGeometryCache();
/* 206 */     this.aci = newAci;
/* 207 */     this.text = null;
/* 208 */     this.textRuns = null;
/* 209 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedCharacterIterator getAttributedCharacterIterator() {
/* 218 */     return this.aci;
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
/*     */   protected void invalidateGeometryCache() {
/* 231 */     super.invalidateGeometryCache();
/* 232 */     this.primitiveBounds = null;
/* 233 */     this.geometryBounds = null;
/* 234 */     this.outline = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/* 241 */     if (this.primitiveBounds == null && 
/* 242 */       this.aci != null) {
/* 243 */       this.primitiveBounds = this.textPainter.getBounds2D(this);
/*     */     }
/*     */     
/* 246 */     return this.primitiveBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 256 */     if (this.geometryBounds == null && 
/* 257 */       this.aci != null) {
/* 258 */       this.geometryBounds = this.textPainter.getGeometryBounds(this);
/*     */     }
/*     */     
/* 261 */     return this.geometryBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds() {
/* 270 */     return getGeometryBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 277 */     if (this.outline == null && 
/* 278 */       this.aci != null) {
/* 279 */       this.outline = this.textPainter.getOutline(this);
/*     */     }
/*     */     
/* 282 */     return this.outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mark getMarkerForChar(int index, boolean beforeChar) {
/* 291 */     return this.textPainter.getMark(this, index, beforeChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelection(Mark begin, Mark end) {
/* 298 */     if (begin.getTextNode() != this || end.getTextNode() != this)
/*     */     {
/* 300 */       throw new RuntimeException("Markers not from this TextNode");
/*     */     }
/* 302 */     this.beginMark = begin;
/* 303 */     this.endMark = end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean selectAt(double x, double y) {
/* 312 */     this.beginMark = this.textPainter.selectAt(x, y, this);
/* 313 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean selectTo(double x, double y) {
/* 322 */     Mark tmpMark = this.textPainter.selectTo(x, y, this.beginMark);
/* 323 */     if (tmpMark == null)
/* 324 */       return false; 
/* 325 */     if (tmpMark != this.endMark) {
/* 326 */       this.endMark = tmpMark;
/* 327 */       return true;
/*     */     } 
/* 329 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean selectAll(double x, double y) {
/* 338 */     this.beginMark = this.textPainter.selectFirst(this);
/* 339 */     this.endMark = this.textPainter.selectLast(this);
/* 340 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSelection() {
/* 349 */     Object o = null;
/* 350 */     if (this.aci == null) return o;
/*     */     
/* 352 */     int[] ranges = this.textPainter.getSelected(this.beginMark, this.endMark);
/*     */ 
/*     */ 
/*     */     
/* 356 */     if (ranges != null && ranges.length > 1) {
/*     */       
/* 358 */       if (ranges[0] > ranges[1]) {
/* 359 */         int temp = ranges[1];
/* 360 */         ranges[1] = ranges[0];
/* 361 */         ranges[0] = temp;
/*     */       } 
/* 363 */       o = new AttributedCharacterSpanIterator(this.aci, ranges[0], ranges[1] + 1);
/*     */     } 
/*     */     
/* 366 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getHighlightShape() {
/* 375 */     Shape highlightShape = this.textPainter.getHighlightShape(this.beginMark, this.endMark);
/*     */     
/* 377 */     AffineTransform t = getGlobalTransform();
/* 378 */     highlightShape = t.createTransformedShape(highlightShape);
/* 379 */     return highlightShape;
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
/*     */   public void primitivePaint(Graphics2D g2d) {
/* 397 */     Shape clip = g2d.getClip();
/* 398 */     if (clip != null && !(clip instanceof GeneralPath)) {
/* 399 */       g2d.setClip(new GeneralPath(clip));
/*     */     }
/*     */     
/* 402 */     this.textPainter.paint(this, g2d);
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
/*     */   public boolean contains(Point2D p) {
/* 419 */     if (!super.contains(p)) {
/* 420 */       return false;
/*     */     }
/* 422 */     List list = getTextRuns();
/*     */     
/* 424 */     for (Object aList : list) {
/* 425 */       StrokingTextPainter.TextRun run = (StrokingTextPainter.TextRun)aList;
/*     */       
/* 427 */       TextSpanLayout layout = run.getLayout();
/* 428 */       float x = (float)p.getX();
/* 429 */       float y = (float)p.getY();
/* 430 */       TextHit textHit = layout.hitTestChar(x, y);
/* 431 */       if (textHit != null && contains(p, layout.getBounds2D())) {
/* 432 */         return true;
/*     */       }
/*     */     } 
/* 435 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean contains(Point2D p, Rectangle2D b) {
/* 439 */     if (b == null || !b.contains(p)) {
/* 440 */       return false;
/*     */     }
/* 442 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 447 */         return this.isVisible;
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/* 452 */         return true;
/*     */       case 8:
/* 454 */         return false;
/*     */     } 
/* 456 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Anchor
/*     */     implements Serializable
/*     */   {
/*     */     public static final int ANCHOR_START = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int ANCHOR_MIDDLE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int ANCHOR_END = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     public static final Anchor START = new Anchor(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     public static final Anchor MIDDLE = new Anchor(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 500 */     public static final Anchor END = new Anchor(2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int type;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Anchor(int type) {
/* 511 */       this.type = type;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getType() {
/* 518 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Object readResolve() throws ObjectStreamException {
/* 529 */       switch (this.type) {
/*     */         case 0:
/* 531 */           return START;
/*     */         case 1:
/* 533 */           return MIDDLE;
/*     */         case 2:
/* 535 */           return END;
/*     */       } 
/* 537 */       throw new RuntimeException("Unknown Anchor type");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */