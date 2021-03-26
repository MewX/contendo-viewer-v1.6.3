/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.PathIterator;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
/*     */ import org.apache.batik.parser.DefaultPathHandler;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.apache.batik.parser.PathParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGNormPathSegList
/*     */   extends AbstractSVGPathSegList
/*     */ {
/*     */   protected void doParse(String value, ListHandler handler) throws ParseException {
/*  55 */     PathParser pathParser = new PathParser();
/*     */     
/*  57 */     NormalizedPathSegListBuilder builder = new NormalizedPathSegListBuilder(handler);
/*     */     
/*  59 */     pathParser.setPathHandler((PathHandler)builder);
/*  60 */     pathParser.parse(value);
/*     */   }
/*     */   
/*     */   protected class NormalizedPathSegListBuilder
/*     */     extends DefaultPathHandler {
/*     */     protected ListHandler listHandler;
/*     */     protected AbstractSVGNormPathSegList.SVGPathSegGenericItem lastAbs;
/*     */     
/*     */     public NormalizedPathSegListBuilder(ListHandler listHandler) {
/*  69 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void startPath() throws ParseException {
/*  75 */       this.listHandler.startList();
/*  76 */       this.lastAbs = new AbstractSVGNormPathSegList.SVGPathSegGenericItem((short)2, "M", 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endPath() throws ParseException {
/*  84 */       this.listHandler.endList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void movetoRel(float x, float y) throws ParseException {
/*  91 */       movetoAbs(this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void movetoAbs(float x, float y) throws ParseException {
/*  98 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem(AbstractSVGNormPathSegList.this, (short)2, "M", x, y));
/*     */ 
/*     */       
/* 101 */       this.lastAbs.setX(x);
/* 102 */       this.lastAbs.setY(y);
/* 103 */       this.lastAbs.setPathSegType((short)2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void closePath() throws ParseException {
/* 110 */       this.listHandler.item(new SVGPathSegItem((short)1, "z"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoRel(float x, float y) throws ParseException {
/* 118 */       linetoAbs(this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoAbs(float x, float y) throws ParseException {
/* 125 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem(AbstractSVGNormPathSegList.this, (short)4, "L", x, y));
/*     */ 
/*     */       
/* 128 */       this.lastAbs.setX(x);
/* 129 */       this.lastAbs.setY(y);
/* 130 */       this.lastAbs.setPathSegType((short)4);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoHorizontalRel(float x) throws ParseException {
/* 137 */       linetoAbs(this.lastAbs.getX() + x, this.lastAbs.getY());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoHorizontalAbs(float x) throws ParseException {
/* 144 */       linetoAbs(x, this.lastAbs.getY());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoVerticalRel(float y) throws ParseException {
/* 151 */       linetoAbs(this.lastAbs.getX(), this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoVerticalAbs(float y) throws ParseException {
/* 158 */       linetoAbs(this.lastAbs.getX(), y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicRel(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 168 */       curvetoCubicAbs(this.lastAbs.getX() + x1, this.lastAbs.getY() + y1, this.lastAbs.getX() + x2, this.lastAbs.getY() + y2, this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicAbs(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 180 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem(AbstractSVGNormPathSegList.this, (short)6, "C", x1, y1, x2, y2, x, y));
/*     */ 
/*     */       
/* 183 */       this.lastAbs.setValue(x1, y1, x2, y2, x, y);
/* 184 */       this.lastAbs.setPathSegType((short)6);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicSmoothRel(float x2, float y2, float x, float y) throws ParseException {
/* 193 */       curvetoCubicSmoothAbs(this.lastAbs.getX() + x2, this.lastAbs.getY() + y2, this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicSmoothAbs(float x2, float y2, float x, float y) throws ParseException {
/* 204 */       if (this.lastAbs.getPathSegType() == 6) {
/* 205 */         curvetoCubicAbs(this.lastAbs.getX() + this.lastAbs.getX() - this.lastAbs.getX2(), this.lastAbs.getY() + this.lastAbs.getY() - this.lastAbs.getY2(), x2, y2, x, y);
/*     */       }
/*     */       else {
/*     */         
/* 209 */         curvetoCubicAbs(this.lastAbs.getX(), this.lastAbs.getY(), x2, y2, x, y);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticRel(float x1, float y1, float x, float y) throws ParseException {
/* 219 */       curvetoQuadraticAbs(this.lastAbs.getX() + x1, this.lastAbs.getY() + y1, this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticAbs(float x1, float y1, float x, float y) throws ParseException {
/* 229 */       curvetoCubicAbs(this.lastAbs.getX() + 2.0F * (x1 - this.lastAbs.getX()) / 3.0F, this.lastAbs.getY() + 2.0F * (y1 - this.lastAbs.getY()) / 3.0F, x + 2.0F * (x1 - x) / 3.0F, y + 2.0F * (y1 - y) / 3.0F, x, y);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       this.lastAbs.setX1(x1);
/* 235 */       this.lastAbs.setY1(y1);
/* 236 */       this.lastAbs.setPathSegType((short)8);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticSmoothRel(float x, float y) throws ParseException {
/* 244 */       curvetoQuadraticSmoothAbs(this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticSmoothAbs(float x, float y) throws ParseException {
/* 252 */       if (this.lastAbs.getPathSegType() == 8) {
/* 253 */         curvetoQuadraticAbs(this.lastAbs.getX() + this.lastAbs.getX() - this.lastAbs.getX1(), this.lastAbs.getY() + this.lastAbs.getY() - this.lastAbs.getY1(), x, y);
/*     */       }
/*     */       else {
/*     */         
/* 257 */         curvetoQuadraticAbs(this.lastAbs.getX(), this.lastAbs.getY(), x, y);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void arcRel(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 270 */       arcAbs(rx, ry, xAxisRotation, largeArcFlag, sweepFlag, this.lastAbs.getX() + x, this.lastAbs.getY() + y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void arcAbs(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 283 */       if (rx == 0.0F || ry == 0.0F) {
/* 284 */         linetoAbs(x, y);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 289 */       double x0 = this.lastAbs.getX();
/* 290 */       double y0 = this.lastAbs.getY();
/* 291 */       if (x0 == x && y0 == y) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 297 */       Arc2D arc = ExtendedGeneralPath.computeArc(x0, y0, rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y);
/*     */       
/* 299 */       if (arc == null)
/*     */         return; 
/* 301 */       AffineTransform t = AffineTransform.getRotateInstance(Math.toRadians(xAxisRotation), arc.getCenterX(), arc.getCenterY());
/*     */       
/* 303 */       Shape s = t.createTransformedShape(arc);
/*     */       
/* 305 */       PathIterator pi = s.getPathIterator(new AffineTransform());
/* 306 */       float[] d = { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F };
/* 307 */       int i = -1;
/*     */       
/* 309 */       while (!pi.isDone()) {
/* 310 */         i = pi.currentSegment(d);
/*     */         
/* 312 */         switch (i) {
/*     */           case 3:
/* 314 */             curvetoCubicAbs(d[0], d[1], d[2], d[3], d[4], d[5]);
/*     */             break;
/*     */         } 
/* 317 */         pi.next();
/*     */       } 
/* 319 */       this.lastAbs.setPathSegType((short)10);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class SVGPathSegGenericItem
/*     */     extends SVGPathSegItem
/*     */   {
/*     */     public SVGPathSegGenericItem(short type, String letter, float x1, float y1, float x2, float y2, float x, float y) {
/* 328 */       super(type, letter);
/* 329 */       setX1(x2);
/* 330 */       setY1(y2);
/* 331 */       setX2(x2);
/* 332 */       setY2(y2);
/* 333 */       setX(x);
/* 334 */       setY(y);
/*     */     }
/*     */     
/*     */     public void setValue(float x1, float y1, float x2, float y2, float x, float y) {
/* 338 */       setX1(x2);
/* 339 */       setY1(y2);
/* 340 */       setX2(x2);
/* 341 */       setY2(y2);
/* 342 */       setX(x);
/* 343 */       setY(y);
/*     */     }
/*     */     
/*     */     public void setValue(float x, float y) {
/* 347 */       setX(x);
/* 348 */       setY(y);
/*     */     }
/*     */     
/*     */     public void setPathSegType(short type) {
/* 352 */       this.type = type;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGNormPathSegList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */