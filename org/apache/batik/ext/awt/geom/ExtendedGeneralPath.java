/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedGeneralPath
/*     */   implements Cloneable, ExtendedShape
/*     */ {
/*     */   protected GeneralPath path;
/*  49 */   int numVals = 0;
/*  50 */   int numSeg = 0;
/*  51 */   float[] values = null;
/*  52 */   int[] types = null;
/*     */   
/*     */   float mx;
/*     */   float my;
/*     */   float cx;
/*     */   float cy;
/*     */   
/*     */   public ExtendedGeneralPath() {
/*  60 */     this.path = new GeneralPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedGeneralPath(int rule) {
/*  69 */     this.path = new GeneralPath(rule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedGeneralPath(int rule, int initialCapacity) {
/*  78 */     this.path = new GeneralPath(rule, initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedGeneralPath(Shape s) {
/*  86 */     this();
/*  87 */     append(s, false);
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
/*     */   public synchronized void arcTo(float rx, float ry, float angle, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
/* 120 */     if (rx == 0.0F || ry == 0.0F) {
/* 121 */       lineTo(x, y);
/*     */       
/*     */       return;
/*     */     } 
/* 125 */     checkMoveTo();
/*     */ 
/*     */     
/* 128 */     double x0 = this.cx;
/* 129 */     double y0 = this.cy;
/* 130 */     if (x0 == x && y0 == y) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 136 */     Arc2D arc = computeArc(x0, y0, rx, ry, angle, largeArcFlag, sweepFlag, x, y);
/*     */     
/* 138 */     if (arc == null)
/*     */       return; 
/* 140 */     AffineTransform t = AffineTransform.getRotateInstance(Math.toRadians(angle), arc.getCenterX(), arc.getCenterY());
/*     */     
/* 142 */     Shape s = t.createTransformedShape(arc);
/* 143 */     this.path.append(s, true);
/*     */     
/* 145 */     makeRoom(7);
/* 146 */     this.types[this.numSeg++] = 4321;
/* 147 */     this.values[this.numVals++] = rx;
/* 148 */     this.values[this.numVals++] = ry;
/* 149 */     this.values[this.numVals++] = angle;
/* 150 */     this.values[this.numVals++] = largeArcFlag ? 1.0F : 0.0F;
/* 151 */     this.values[this.numVals++] = sweepFlag ? 1.0F : 0.0F;
/* 152 */     this.cx = this.values[this.numVals++] = x;
/* 153 */     this.cy = this.values[this.numVals++] = y;
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
/*     */   public static Arc2D computeArc(double x0, double y0, double rx, double ry, double angle, boolean largeArcFlag, boolean sweepFlag, double x, double y) {
/* 176 */     double dx2 = (x0 - x) / 2.0D;
/* 177 */     double dy2 = (y0 - y) / 2.0D;
/*     */     
/* 179 */     angle = Math.toRadians(angle % 360.0D);
/* 180 */     double cosAngle = Math.cos(angle);
/* 181 */     double sinAngle = Math.sin(angle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     double x1 = cosAngle * dx2 + sinAngle * dy2;
/* 187 */     double y1 = -sinAngle * dx2 + cosAngle * dy2;
/*     */     
/* 189 */     rx = Math.abs(rx);
/* 190 */     ry = Math.abs(ry);
/* 191 */     double Prx = rx * rx;
/* 192 */     double Pry = ry * ry;
/* 193 */     double Px1 = x1 * x1;
/* 194 */     double Py1 = y1 * y1;
/*     */     
/* 196 */     double radiiCheck = Px1 / Prx + Py1 / Pry;
/* 197 */     if (radiiCheck > 0.99999D) {
/* 198 */       double radiiScale = Math.sqrt(radiiCheck) * 1.00001D;
/* 199 */       rx = radiiScale * rx;
/* 200 */       ry = radiiScale * ry;
/* 201 */       Prx = rx * rx;
/* 202 */       Pry = ry * ry;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     double sign = (largeArcFlag == sweepFlag) ? -1.0D : 1.0D;
/* 209 */     double sq = (Prx * Pry - Prx * Py1 - Pry * Px1) / (Prx * Py1 + Pry * Px1);
/* 210 */     sq = (sq < 0.0D) ? 0.0D : sq;
/* 211 */     double coef = sign * Math.sqrt(sq);
/* 212 */     double cx1 = coef * rx * y1 / ry;
/* 213 */     double cy1 = coef * -(ry * x1 / rx);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     double sx2 = (x0 + x) / 2.0D;
/* 219 */     double sy2 = (y0 + y) / 2.0D;
/* 220 */     double cx = sx2 + cosAngle * cx1 - sinAngle * cy1;
/* 221 */     double cy = sy2 + sinAngle * cx1 + cosAngle * cy1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     double ux = (x1 - cx1) / rx;
/* 227 */     double uy = (y1 - cy1) / ry;
/* 228 */     double vx = (-x1 - cx1) / rx;
/* 229 */     double vy = (-y1 - cy1) / ry;
/*     */ 
/*     */     
/* 232 */     double n = Math.sqrt(ux * ux + uy * uy);
/* 233 */     double p = ux;
/* 234 */     sign = (uy < 0.0D) ? -1.0D : 1.0D;
/* 235 */     double angleStart = Math.toDegrees(sign * Math.acos(p / n));
/*     */ 
/*     */     
/* 238 */     n = Math.sqrt((ux * ux + uy * uy) * (vx * vx + vy * vy));
/* 239 */     p = ux * vx + uy * vy;
/* 240 */     sign = (ux * vy - uy * vx < 0.0D) ? -1.0D : 1.0D;
/* 241 */     double angleExtent = Math.toDegrees(sign * Math.acos(p / n));
/* 242 */     if (!sweepFlag && angleExtent > 0.0D) {
/* 243 */       angleExtent -= 360.0D;
/* 244 */     } else if (sweepFlag && angleExtent < 0.0D) {
/* 245 */       angleExtent += 360.0D;
/*     */     } 
/* 247 */     angleExtent %= 360.0D;
/* 248 */     angleStart %= 360.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     Arc2D.Double arc = new Arc2D.Double();
/* 254 */     arc.x = cx - rx;
/* 255 */     arc.y = cy - ry;
/* 256 */     arc.width = rx * 2.0D;
/* 257 */     arc.height = ry * 2.0D;
/* 258 */     arc.start = -angleStart;
/* 259 */     arc.extent = -angleExtent;
/*     */     
/* 261 */     return arc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void moveTo(float x, float y) {
/* 269 */     makeRoom(2);
/* 270 */     this.types[this.numSeg++] = 0;
/* 271 */     this.values[this.numVals++] = x; this.cx = this.mx = x;
/* 272 */     this.values[this.numVals++] = y; this.cy = this.my = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void lineTo(float x, float y) {
/* 280 */     checkMoveTo();
/* 281 */     this.path.lineTo(x, y);
/*     */     
/* 283 */     makeRoom(2);
/* 284 */     this.types[this.numSeg++] = 1;
/* 285 */     this.cx = this.values[this.numVals++] = x;
/* 286 */     this.cy = this.values[this.numVals++] = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void quadTo(float x1, float y1, float x2, float y2) {
/* 293 */     checkMoveTo();
/* 294 */     this.path.quadTo(x1, y1, x2, y2);
/*     */     
/* 296 */     makeRoom(4);
/* 297 */     this.types[this.numSeg++] = 2;
/* 298 */     this.values[this.numVals++] = x1;
/* 299 */     this.values[this.numVals++] = y1;
/* 300 */     this.cx = this.values[this.numVals++] = x2;
/* 301 */     this.cy = this.values[this.numVals++] = y2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) {
/* 310 */     checkMoveTo();
/* 311 */     this.path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */     
/* 313 */     makeRoom(6);
/* 314 */     this.types[this.numSeg++] = 3;
/* 315 */     this.values[this.numVals++] = x1;
/* 316 */     this.values[this.numVals++] = y1;
/* 317 */     this.values[this.numVals++] = x2;
/* 318 */     this.values[this.numVals++] = y2;
/* 319 */     this.cx = this.values[this.numVals++] = x3;
/* 320 */     this.cy = this.values[this.numVals++] = y3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void closePath() {
/* 328 */     if (this.numSeg != 0 && this.types[this.numSeg - 1] == 4) {
/*     */       return;
/*     */     }
/*     */     
/* 332 */     if (this.numSeg != 0 && this.types[this.numSeg - 1] != 0) {
/* 333 */       this.path.closePath();
/*     */     }
/* 335 */     makeRoom(0);
/* 336 */     this.types[this.numSeg++] = 4;
/* 337 */     this.cx = this.mx;
/* 338 */     this.cy = this.my;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkMoveTo() {
/* 346 */     if (this.numSeg == 0)
/*     */       return; 
/* 348 */     switch (this.types[this.numSeg - 1]) {
/*     */       
/*     */       case 0:
/* 351 */         this.path.moveTo(this.values[this.numVals - 2], this.values[this.numVals - 1]);
/*     */         break;
/*     */       
/*     */       case 4:
/* 355 */         if (this.numSeg == 1)
/* 356 */           return;  if (this.types[this.numSeg - 2] == 0) {
/* 357 */           this.path.moveTo(this.values[this.numVals - 2], this.values[this.numVals - 1]);
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Shape s, boolean connect) {
/* 369 */     append(s.getPathIterator(new AffineTransform()), connect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(PathIterator pi, boolean connect) {
/* 376 */     double[] vals = new double[6];
/*     */     
/* 378 */     while (!pi.isDone()) {
/* 379 */       Arrays.fill(vals, 0.0D);
/* 380 */       int type = pi.currentSegment(vals);
/* 381 */       pi.next();
/* 382 */       if (connect && this.numVals != 0) {
/* 383 */         if (type == 0) {
/* 384 */           double x = vals[0];
/* 385 */           double y = vals[1];
/* 386 */           if (x != this.cx || y != this.cy) {
/*     */ 
/*     */             
/* 389 */             type = 1;
/*     */           } else {
/*     */             
/* 392 */             if (pi.isDone())
/* 393 */               break;  type = pi.currentSegment(vals);
/* 394 */             pi.next();
/*     */           } 
/*     */         } 
/* 397 */         connect = false;
/*     */       } 
/*     */       
/* 400 */       switch (type) { case 4:
/* 401 */           closePath();
/*     */         case 0:
/* 403 */           moveTo((float)vals[0], (float)vals[1]);
/*     */         case 1:
/* 405 */           lineTo((float)vals[0], (float)vals[1]);
/*     */         case 2:
/* 407 */           quadTo((float)vals[0], (float)vals[1], (float)vals[2], (float)vals[3]);
/*     */         
/*     */         case 3:
/* 410 */           curveTo((float)vals[0], (float)vals[1], (float)vals[2], (float)vals[3], (float)vals[4], (float)vals[5]); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(ExtendedPathIterator epi, boolean connect) {
/* 421 */     float[] vals = new float[7];
/* 422 */     while (!epi.isDone()) {
/* 423 */       Arrays.fill(vals, 0.0F);
/* 424 */       int type = epi.currentSegment(vals);
/* 425 */       epi.next();
/* 426 */       if (connect && this.numVals != 0) {
/* 427 */         if (type == 0) {
/* 428 */           float x = vals[0];
/* 429 */           float y = vals[1];
/* 430 */           if (x != this.cx || y != this.cy) {
/*     */ 
/*     */             
/* 433 */             type = 1;
/*     */           } else {
/*     */             
/* 436 */             if (epi.isDone())
/* 437 */               break;  type = epi.currentSegment(vals);
/* 438 */             epi.next();
/*     */           } 
/*     */         } 
/* 441 */         connect = false;
/*     */       } 
/*     */       
/* 444 */       switch (type) { case 4:
/* 445 */           closePath();
/*     */         case 0:
/* 447 */           moveTo(vals[0], vals[1]);
/*     */         case 1:
/* 449 */           lineTo(vals[0], vals[1]);
/*     */         case 2:
/* 451 */           quadTo(vals[0], vals[1], vals[2], vals[3]);
/*     */         
/*     */         case 3:
/* 454 */           curveTo(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
/*     */ 
/*     */         
/*     */         case 4321:
/* 458 */           arcTo(vals[0], vals[1], vals[2], (vals[3] != 0.0F), (vals[4] != 0.0F), vals[5], vals[6]); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getWindingRule() {
/* 469 */     return this.path.getWindingRule();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWindingRule(int rule) {
/* 476 */     this.path.setWindingRule(rule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Point2D getCurrentPoint() {
/* 483 */     if (this.numVals == 0) return null; 
/* 484 */     return new Point2D.Double(this.cx, this.cy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() {
/* 491 */     this.path.reset();
/*     */     
/* 493 */     this.numSeg = 0;
/* 494 */     this.numVals = 0;
/* 495 */     this.values = null;
/* 496 */     this.types = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void transform(AffineTransform at) {
/* 503 */     if (at.getType() != 0) {
/* 504 */       throw new IllegalArgumentException("ExtendedGeneralPaths can not be transformed");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Shape createTransformedShape(AffineTransform at) {
/* 512 */     return this.path.createTransformedShape(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Rectangle getBounds() {
/* 519 */     return this.path.getBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Rectangle2D getBounds2D() {
/* 526 */     return this.path.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 533 */     return this.path.contains(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 540 */     return this.path.contains(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 547 */     return this.path.contains(x, y, w, h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 554 */     return this.path.contains(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 561 */     return this.path.intersects(x, y, w, h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 568 */     return this.path.intersects(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 575 */     return this.path.getPathIterator(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 582 */     return this.path.getPathIterator(at, flatness);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedPathIterator getExtendedPathIterator() {
/* 589 */     return new EPI();
/*     */   }
/*     */   
/*     */   class EPI implements ExtendedPathIterator {
/* 593 */     int segNum = 0;
/* 594 */     int valsIdx = 0;
/*     */     
/*     */     public int currentSegment() {
/* 597 */       return ExtendedGeneralPath.this.types[this.segNum];
/*     */     }
/*     */     
/*     */     public int currentSegment(double[] coords) {
/* 601 */       int ret = ExtendedGeneralPath.this.types[this.segNum];
/* 602 */       switch (ret) {
/*     */         
/*     */         case 0:
/*     */         case 1:
/* 606 */           coords[0] = ExtendedGeneralPath.this.values[this.valsIdx];
/* 607 */           coords[1] = ExtendedGeneralPath.this.values[this.valsIdx + 1];
/*     */           break;
/*     */         case 2:
/* 610 */           coords[0] = ExtendedGeneralPath.this.values[this.valsIdx];
/* 611 */           coords[1] = ExtendedGeneralPath.this.values[this.valsIdx + 1];
/* 612 */           coords[2] = ExtendedGeneralPath.this.values[this.valsIdx + 2];
/* 613 */           coords[3] = ExtendedGeneralPath.this.values[this.valsIdx + 3];
/*     */           break;
/*     */         case 3:
/* 616 */           coords[0] = ExtendedGeneralPath.this.values[this.valsIdx];
/* 617 */           coords[1] = ExtendedGeneralPath.this.values[this.valsIdx + 1];
/* 618 */           coords[2] = ExtendedGeneralPath.this.values[this.valsIdx + 2];
/* 619 */           coords[3] = ExtendedGeneralPath.this.values[this.valsIdx + 3];
/* 620 */           coords[4] = ExtendedGeneralPath.this.values[this.valsIdx + 4];
/* 621 */           coords[5] = ExtendedGeneralPath.this.values[this.valsIdx + 5];
/*     */           break;
/*     */         case 4321:
/* 624 */           coords[0] = ExtendedGeneralPath.this.values[this.valsIdx];
/* 625 */           coords[1] = ExtendedGeneralPath.this.values[this.valsIdx + 1];
/* 626 */           coords[2] = ExtendedGeneralPath.this.values[this.valsIdx + 2];
/* 627 */           coords[3] = ExtendedGeneralPath.this.values[this.valsIdx + 3];
/* 628 */           coords[4] = ExtendedGeneralPath.this.values[this.valsIdx + 4];
/* 629 */           coords[5] = ExtendedGeneralPath.this.values[this.valsIdx + 5];
/* 630 */           coords[6] = ExtendedGeneralPath.this.values[this.valsIdx + 6];
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 636 */       return ret;
/*     */     }
/*     */     
/*     */     public int currentSegment(float[] coords) {
/* 640 */       int ret = ExtendedGeneralPath.this.types[this.segNum];
/* 641 */       switch (ret) {
/*     */         
/*     */         case 0:
/*     */         case 1:
/* 645 */           coords[0] = ExtendedGeneralPath.this.values[this.valsIdx];
/* 646 */           coords[1] = ExtendedGeneralPath.this.values[this.valsIdx + 1];
/*     */           break;
/*     */         case 2:
/* 649 */           System.arraycopy(ExtendedGeneralPath.this.values, this.valsIdx, coords, 0, 4);
/*     */           break;
/*     */         case 3:
/* 652 */           System.arraycopy(ExtendedGeneralPath.this.values, this.valsIdx, coords, 0, 6);
/*     */           break;
/*     */         case 4321:
/* 655 */           System.arraycopy(ExtendedGeneralPath.this.values, this.valsIdx, coords, 0, 7);
/*     */           break;
/*     */       } 
/* 658 */       return ret;
/*     */     }
/*     */     
/*     */     public int getWindingRule() {
/* 662 */       return ExtendedGeneralPath.this.path.getWindingRule();
/*     */     }
/*     */     public boolean isDone() {
/* 665 */       return (this.segNum == ExtendedGeneralPath.this.numSeg);
/*     */     }
/*     */     public void next() {
/* 668 */       int type = ExtendedGeneralPath.this.types[this.segNum++];
/* 669 */       switch (type) {
/*     */         case 0:
/*     */         case 1:
/* 672 */           this.valsIdx += 2; break;
/* 673 */         case 2: this.valsIdx += 4; break;
/* 674 */         case 3: this.valsIdx += 6; break;
/* 675 */         case 4321: this.valsIdx += 7;
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 685 */       ExtendedGeneralPath result = (ExtendedGeneralPath)super.clone();
/* 686 */       result.path = (GeneralPath)this.path.clone();
/*     */       
/* 688 */       if (this.values != null) {
/* 689 */         result.values = new float[this.values.length];
/* 690 */         System.arraycopy(this.values, 0, result.values, 0, this.values.length);
/*     */       } 
/* 692 */       result.numVals = this.numVals;
/*     */       
/* 694 */       if (this.types != null) {
/* 695 */         result.types = new int[this.types.length];
/* 696 */         System.arraycopy(this.types, 0, result.types, 0, this.types.length);
/*     */       } 
/* 698 */       result.numSeg = this.numSeg;
/*     */       
/* 700 */       return result;
/* 701 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/* 702 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void makeRoom(int numValues) {
/* 713 */     if (this.values == null) {
/* 714 */       this.values = new float[2 * numValues];
/* 715 */       this.types = new int[2];
/* 716 */       this.numVals = 0;
/* 717 */       this.numSeg = 0;
/*     */       
/*     */       return;
/*     */     } 
/* 721 */     int newSize = this.numVals + numValues;
/* 722 */     if (newSize > this.values.length) {
/* 723 */       int nlen = this.values.length * 2;
/* 724 */       if (nlen < newSize) {
/* 725 */         nlen = newSize;
/*     */       }
/* 727 */       float[] nvals = new float[nlen];
/* 728 */       System.arraycopy(this.values, 0, nvals, 0, this.numVals);
/* 729 */       this.values = nvals;
/*     */     } 
/*     */     
/* 732 */     if (this.numSeg == this.types.length) {
/* 733 */       int[] ntypes = new int[this.types.length * 2];
/* 734 */       System.arraycopy(this.types, 0, ntypes, 0, this.types.length);
/* 735 */       this.types = ntypes;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/ExtendedGeneralPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */