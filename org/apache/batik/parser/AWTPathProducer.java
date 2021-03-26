/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AWTPathProducer
/*     */   implements PathHandler, ShapeProducer
/*     */ {
/*     */   protected ExtendedGeneralPath path;
/*     */   protected float currentX;
/*     */   protected float currentY;
/*     */   protected float xCenter;
/*     */   protected float yCenter;
/*     */   protected int windingRule;
/*     */   
/*     */   public static Shape createShape(Reader r, int wr) throws IOException, ParseException {
/*  75 */     PathParser p = new PathParser();
/*  76 */     AWTPathProducer ph = new AWTPathProducer();
/*     */     
/*  78 */     ph.setWindingRule(wr);
/*  79 */     p.setPathHandler(ph);
/*  80 */     p.parse(r);
/*     */     
/*  82 */     return ph.getShape();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWindingRule(int i) {
/*  89 */     this.windingRule = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWindingRule() {
/*  96 */     return this.windingRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 105 */     return (Shape)this.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPath() throws ParseException {
/* 112 */     this.currentX = 0.0F;
/* 113 */     this.currentY = 0.0F;
/* 114 */     this.xCenter = 0.0F;
/* 115 */     this.yCenter = 0.0F;
/* 116 */     this.path = new ExtendedGeneralPath(this.windingRule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPath() throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void movetoRel(float x, float y) throws ParseException {
/* 129 */     this.path.moveTo(this.xCenter = this.currentX += x, this.yCenter = this.currentY += y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void movetoAbs(float x, float y) throws ParseException {
/* 136 */     this.path.moveTo(this.xCenter = this.currentX = x, this.yCenter = this.currentY = y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closePath() throws ParseException {
/* 143 */     this.path.closePath();
/* 144 */     Point2D pt = this.path.getCurrentPoint();
/* 145 */     this.currentX = (float)pt.getX();
/* 146 */     this.currentY = (float)pt.getY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoRel(float x, float y) throws ParseException {
/* 153 */     this.path.lineTo(this.xCenter = this.currentX += x, this.yCenter = this.currentY += y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoAbs(float x, float y) throws ParseException {
/* 160 */     this.path.lineTo(this.xCenter = this.currentX = x, this.yCenter = this.currentY = y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoHorizontalRel(float x) throws ParseException {
/* 167 */     this.path.lineTo(this.xCenter = this.currentX += x, this.yCenter = this.currentY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoHorizontalAbs(float x) throws ParseException {
/* 174 */     this.path.lineTo(this.xCenter = this.currentX = x, this.yCenter = this.currentY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoVerticalRel(float y) throws ParseException {
/* 181 */     this.path.lineTo(this.xCenter = this.currentX, this.yCenter = this.currentY += y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoVerticalAbs(float y) throws ParseException {
/* 188 */     this.path.lineTo(this.xCenter = this.currentX, this.yCenter = this.currentY = y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicRel(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 198 */     this.path.curveTo(this.currentX + x1, this.currentY + y1, this.xCenter = this.currentX + x2, this.yCenter = this.currentY + y2, this.currentX += x, this.currentY += y);
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
/*     */   public void curvetoCubicAbs(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 210 */     this.path.curveTo(x1, y1, this.xCenter = x2, this.yCenter = y2, this.currentX = x, this.currentY = y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicSmoothRel(float x2, float y2, float x, float y) throws ParseException {
/* 220 */     this.path.curveTo(this.currentX * 2.0F - this.xCenter, this.currentY * 2.0F - this.yCenter, this.xCenter = this.currentX + x2, this.yCenter = this.currentY + y2, this.currentX += x, this.currentY += y);
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
/*     */   public void curvetoCubicSmoothAbs(float x2, float y2, float x, float y) throws ParseException {
/* 234 */     this.path.curveTo(this.currentX * 2.0F - this.xCenter, this.currentY * 2.0F - this.yCenter, this.xCenter = x2, this.yCenter = y2, this.currentX = x, this.currentY = y);
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
/*     */   public void curvetoQuadraticRel(float x1, float y1, float x, float y) throws ParseException {
/* 248 */     this.path.quadTo(this.xCenter = this.currentX + x1, this.yCenter = this.currentY + y1, this.currentX += x, this.currentY += y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticAbs(float x1, float y1, float x, float y) throws ParseException {
/* 258 */     this.path.quadTo(this.xCenter = x1, this.yCenter = y1, this.currentX = x, this.currentY = y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticSmoothRel(float x, float y) throws ParseException {
/* 266 */     this.path.quadTo(this.xCenter = this.currentX * 2.0F - this.xCenter, this.yCenter = this.currentY * 2.0F - this.yCenter, this.currentX += x, this.currentY += y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticSmoothAbs(float x, float y) throws ParseException {
/* 277 */     this.path.quadTo(this.xCenter = this.currentX * 2.0F - this.xCenter, this.yCenter = this.currentY * 2.0F - this.yCenter, this.currentX = x, this.currentY = y);
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
/*     */   public void arcRel(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 291 */     this.path.arcTo(rx, ry, xAxisRotation, largeArcFlag, sweepFlag, this.xCenter = this.currentX += x, this.yCenter = this.currentY += y);
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
/*     */   public void arcAbs(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 303 */     this.path.arcTo(rx, ry, xAxisRotation, largeArcFlag, sweepFlag, this.xCenter = this.currentX = x, this.yCenter = this.currentY = y);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AWTPathProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */