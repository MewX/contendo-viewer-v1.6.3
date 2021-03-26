/*      */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*      */ 
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
/*      */ class CloudyBorder
/*      */ {
/*      */   private static final double ANGLE_180_DEG = 3.141592653589793D;
/*      */   private static final double ANGLE_90_DEG = 1.5707963267948966D;
/*   42 */   private static final double ANGLE_34_DEG = Math.toRadians(34.0D);
/*   43 */   private static final double ANGLE_30_DEG = Math.toRadians(30.0D);
/*   44 */   private static final double ANGLE_12_DEG = Math.toRadians(12.0D);
/*      */ 
/*      */   
/*      */   private final PDAppearanceContentStream output;
/*      */   
/*      */   private final PDRectangle annotRect;
/*      */   
/*      */   private final double intensity;
/*      */   
/*      */   private final double lineWidth;
/*      */   
/*      */   private PDRectangle rectWithDiff;
/*      */   
/*      */   private boolean outputStarted = false;
/*      */   
/*      */   private double bboxMinX;
/*      */   
/*      */   private double bboxMinY;
/*      */   
/*      */   private double bboxMaxX;
/*      */   
/*      */   private double bboxMaxY;
/*      */ 
/*      */   
/*      */   CloudyBorder(PDAppearanceContentStream stream, double intensity, double lineWidth, PDRectangle rect) {
/*   69 */     this.output = stream;
/*   70 */     this.intensity = intensity;
/*   71 */     this.lineWidth = lineWidth;
/*   72 */     this.annotRect = rect;
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
/*      */   void createCloudyRectangle(PDRectangle rd) throws IOException {
/*   88 */     this.rectWithDiff = applyRectDiff(rd, this.lineWidth / 2.0D);
/*   89 */     double left = this.rectWithDiff.getLowerLeftX();
/*   90 */     double bottom = this.rectWithDiff.getLowerLeftY();
/*   91 */     double right = this.rectWithDiff.getUpperRightX();
/*   92 */     double top = this.rectWithDiff.getUpperRightY();
/*      */     
/*   94 */     cloudyRectangleImpl(left, bottom, right, top, false);
/*   95 */     finish();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createCloudyPolygon(float[][] path) throws IOException {
/*  106 */     int n = path.length;
/*  107 */     Point2D.Double[] polygon = new Point2D.Double[n];
/*      */     
/*  109 */     for (int i = 0; i < n; i++) {
/*      */       
/*  111 */       float[] array = path[i];
/*  112 */       if (array.length == 2) {
/*      */         
/*  114 */         polygon[i] = new Point2D.Double(array[0], array[1]);
/*      */       }
/*  116 */       else if (array.length == 6) {
/*      */ 
/*      */         
/*  119 */         polygon[i] = new Point2D.Double(array[4], array[5]);
/*      */       } 
/*      */     } 
/*      */     
/*  123 */     cloudyPolygonImpl(polygon, false);
/*  124 */     finish();
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
/*      */   void createCloudyEllipse(PDRectangle rd) throws IOException {
/*  137 */     this.rectWithDiff = applyRectDiff(rd, 0.0D);
/*  138 */     double left = this.rectWithDiff.getLowerLeftX();
/*  139 */     double bottom = this.rectWithDiff.getLowerLeftY();
/*  140 */     double right = this.rectWithDiff.getUpperRightX();
/*  141 */     double top = this.rectWithDiff.getUpperRightY();
/*      */     
/*  143 */     cloudyEllipseImpl(left, bottom, right, top);
/*  144 */     finish();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   PDRectangle getBBox() {
/*  155 */     return getRectangle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   PDRectangle getRectangle() {
/*  166 */     return new PDRectangle((float)this.bboxMinX, (float)this.bboxMinY, (float)(this.bboxMaxX - this.bboxMinX), (float)(this.bboxMaxY - this.bboxMinY));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   AffineTransform getMatrix() {
/*  177 */     return AffineTransform.getTranslateInstance(-this.bboxMinX, -this.bboxMinY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   PDRectangle getRectDifference() {
/*  187 */     if (this.annotRect == null) {
/*      */       
/*  189 */       float d = (float)this.lineWidth / 2.0F;
/*  190 */       return new PDRectangle(d, d, (float)this.lineWidth, (float)this.lineWidth);
/*      */     } 
/*      */     
/*  193 */     PDRectangle re = (this.rectWithDiff != null) ? this.rectWithDiff : this.annotRect;
/*      */     
/*  195 */     float left = re.getLowerLeftX() - (float)this.bboxMinX;
/*  196 */     float bottom = re.getLowerLeftY() - (float)this.bboxMinY;
/*  197 */     float right = (float)this.bboxMaxX - re.getUpperRightX();
/*  198 */     float top = (float)this.bboxMaxY - re.getUpperRightY();
/*      */     
/*  200 */     return new PDRectangle(left, bottom, right - left, top - bottom);
/*      */   }
/*      */ 
/*      */   
/*      */   private static double cosine(double dx, double hypot) {
/*  205 */     if (Double.compare(hypot, 0.0D) == 0)
/*      */     {
/*  207 */       return 0.0D;
/*      */     }
/*  209 */     return dx / hypot;
/*      */   }
/*      */ 
/*      */   
/*      */   private static double sine(double dy, double hypot) {
/*  214 */     if (Double.compare(hypot, 0.0D) == 0)
/*      */     {
/*  216 */       return 0.0D;
/*      */     }
/*  218 */     return dy / hypot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cloudyRectangleImpl(double left, double bottom, double right, double top, boolean isEllipse) throws IOException {
/*      */     Point2D.Double[] polygon;
/*  228 */     double w = right - left;
/*  229 */     double h = top - bottom;
/*      */     
/*  231 */     if (this.intensity <= 0.0D) {
/*      */       
/*  233 */       this.output.addRect((float)left, (float)bottom, (float)w, (float)h);
/*  234 */       this.bboxMinX = left;
/*  235 */       this.bboxMinY = bottom;
/*  236 */       this.bboxMaxX = right;
/*  237 */       this.bboxMaxY = top;
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  244 */     if (w < 1.0D) {
/*      */       
/*  246 */       polygon = new Point2D.Double[] { new Point2D.Double(left, bottom), new Point2D.Double(left, top), new Point2D.Double(left, bottom) };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  252 */     else if (h < 1.0D) {
/*      */       
/*  254 */       polygon = new Point2D.Double[] { new Point2D.Double(left, bottom), new Point2D.Double(right, bottom), new Point2D.Double(left, bottom) };
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  262 */       polygon = new Point2D.Double[] { new Point2D.Double(left, bottom), new Point2D.Double(right, bottom), new Point2D.Double(right, top), new Point2D.Double(left, top), new Point2D.Double(left, bottom) };
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  270 */     cloudyPolygonImpl(polygon, isEllipse);
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
/*      */   private void cloudyPolygonImpl(Point2D.Double[] vertices, boolean isEllipse) throws IOException {
/*  282 */     Point2D.Double[] polygon = removeZeroLengthSegments(vertices);
/*  283 */     getPositivePolygon(polygon);
/*  284 */     int numPoints = polygon.length;
/*      */     
/*  286 */     if (numPoints < 2) {
/*      */       return;
/*      */     }
/*      */     
/*  290 */     if (this.intensity <= 0.0D) {
/*      */       
/*  292 */       moveTo(polygon[0]);
/*  293 */       for (int i = 1; i < numPoints; i++)
/*      */       {
/*  295 */         lineTo(polygon[i]);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  300 */     double cloudRadius = isEllipse ? getEllipseCloudRadius() : getPolygonCloudRadius();
/*      */     
/*  302 */     if (cloudRadius < 0.5D)
/*      */     {
/*  304 */       cloudRadius = 0.5D;
/*      */     }
/*      */     
/*  307 */     double k = Math.cos(ANGLE_34_DEG);
/*  308 */     double advIntermDefault = 2.0D * k * cloudRadius;
/*  309 */     double advCornerDefault = k * cloudRadius;
/*  310 */     double[] array = new double[2];
/*  311 */     double anglePrev = 0.0D;
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
/*  322 */     int n0 = computeParamsPolygon(advIntermDefault, advCornerDefault, k, cloudRadius, polygon[numPoints - 2]
/*  323 */         .distance(polygon[0]), array);
/*  324 */     double alphaPrev = (n0 == 0) ? array[0] : ANGLE_34_DEG;
/*      */     
/*  326 */     for (int j = 0; j + 1 < numPoints; j++) {
/*      */       
/*  328 */       Point2D.Double pt = polygon[j];
/*  329 */       Point2D.Double ptNext = polygon[j + 1];
/*  330 */       double length = pt.distance(ptNext);
/*  331 */       if (Double.compare(length, 0.0D) == 0) {
/*      */         
/*  333 */         alphaPrev = ANGLE_34_DEG;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  338 */         int n = computeParamsPolygon(advIntermDefault, advCornerDefault, k, cloudRadius, length, array);
/*      */         
/*  340 */         if (n < 0) {
/*      */           
/*  342 */           if (!this.outputStarted)
/*      */           {
/*  344 */             moveTo(pt);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  349 */           double alpha = array[0];
/*  350 */           double dx = array[1];
/*      */           
/*  352 */           double angleCur = Math.atan2(ptNext.y - pt.y, ptNext.x - pt.x);
/*  353 */           if (j == 0) {
/*      */             
/*  355 */             Point2D.Double ptPrev = polygon[numPoints - 2];
/*  356 */             anglePrev = Math.atan2(pt.y - ptPrev.y, pt.x - ptPrev.x);
/*      */           } 
/*      */           
/*  359 */           double cos = cosine(ptNext.x - pt.x, length);
/*  360 */           double sin = sine(ptNext.y - pt.y, length);
/*  361 */           double x = pt.x;
/*  362 */           double y = pt.y;
/*      */           
/*  364 */           addCornerCurl(anglePrev, angleCur, cloudRadius, pt.x, pt.y, alpha, alphaPrev, !this.outputStarted);
/*      */ 
/*      */           
/*  367 */           double adv = 2.0D * k * cloudRadius + 2.0D * dx;
/*  368 */           x += adv * cos;
/*  369 */           y += adv * sin;
/*      */ 
/*      */           
/*  372 */           int numInterm = n;
/*  373 */           if (n >= 1) {
/*      */             
/*  375 */             addFirstIntermediateCurl(angleCur, cloudRadius, alpha, x, y);
/*  376 */             x += advIntermDefault * cos;
/*  377 */             y += advIntermDefault * sin;
/*  378 */             numInterm = n - 1;
/*      */           } 
/*      */ 
/*      */           
/*  382 */           Point2D.Double[] template = getIntermediateCurlTemplate(angleCur, cloudRadius);
/*  383 */           for (int i = 0; i < numInterm; i++) {
/*      */             
/*  385 */             outputCurlTemplate(template, x, y);
/*  386 */             x += advIntermDefault * cos;
/*  387 */             y += advIntermDefault * sin;
/*      */           } 
/*      */           
/*  390 */           anglePrev = angleCur;
/*  391 */           alphaPrev = (n == 0) ? alpha : ANGLE_34_DEG;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int computeParamsPolygon(double advInterm, double advCorner, double k, double r, double length, double[] array) {
/*  401 */     if (Double.compare(length, 0.0D) == 0) {
/*      */       
/*  403 */       array[0] = ANGLE_34_DEG;
/*  404 */       array[1] = 0.0D;
/*  405 */       return -1;
/*      */     } 
/*      */ 
/*      */     
/*  409 */     int n = (int)Math.ceil((length - 2.0D * advCorner) / advInterm);
/*      */ 
/*      */     
/*  412 */     double e = length - 2.0D * advCorner + n * advInterm;
/*      */     
/*  414 */     double dx = e / 2.0D;
/*      */ 
/*      */     
/*  417 */     double arg = (k * r + dx) / r;
/*  418 */     double alpha = (arg < -1.0D || arg > 1.0D) ? 0.0D : Math.acos(arg);
/*      */     
/*  420 */     array[0] = alpha;
/*  421 */     array[1] = dx;
/*  422 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addCornerCurl(double anglePrev, double angleCur, double radius, double cx, double cy, double alpha, double alphaPrev, boolean addMoveTo) throws IOException {
/*  432 */     double a = anglePrev + Math.PI + alphaPrev;
/*  433 */     double b = anglePrev + Math.PI + alphaPrev - Math.toRadians(22.0D);
/*  434 */     getArcSegment(a, b, cx, cy, radius, radius, null, addMoveTo);
/*      */     
/*  436 */     a = b;
/*  437 */     b = angleCur - alpha;
/*  438 */     getArc(a, b, radius, radius, cx, cy, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addFirstIntermediateCurl(double angleCur, double r, double alpha, double cx, double cy) throws IOException {
/*  447 */     double a = angleCur + Math.PI;
/*      */     
/*  449 */     getArcSegment(a + alpha, a + alpha - ANGLE_30_DEG, cx, cy, r, r, null, false);
/*  450 */     getArcSegment(a + alpha - ANGLE_30_DEG, a + 1.5707963267948966D, cx, cy, r, r, null, false);
/*  451 */     getArcSegment(a + 1.5707963267948966D, a + Math.PI - ANGLE_34_DEG, cx, cy, r, r, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Point2D.Double[] getIntermediateCurlTemplate(double angleCur, double r) throws IOException {
/*  461 */     ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
/*  462 */     double a = angleCur + Math.PI;
/*      */     
/*  464 */     getArcSegment(a + ANGLE_34_DEG, a + ANGLE_12_DEG, 0.0D, 0.0D, r, r, points, false);
/*  465 */     getArcSegment(a + ANGLE_12_DEG, a + 1.5707963267948966D, 0.0D, 0.0D, r, r, points, false);
/*  466 */     getArcSegment(a + 1.5707963267948966D, a + Math.PI - ANGLE_34_DEG, 0.0D, 0.0D, r, r, points, false);
/*      */ 
/*      */     
/*  469 */     return points.<Point2D.Double>toArray(new Point2D.Double[points.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void outputCurlTemplate(Point2D.Double[] template, double x, double y) throws IOException {
/*  478 */     int n = template.length;
/*  479 */     int i = 0;
/*      */     
/*  481 */     if (n % 3 == 1) {
/*      */       
/*  483 */       Point2D.Double a = template[0];
/*  484 */       moveTo(a.x + x, a.y + y);
/*  485 */       i++;
/*      */     } 
/*  487 */     for (; i + 2 < n; i += 3) {
/*      */       
/*  489 */       Point2D.Double a = template[i];
/*  490 */       Point2D.Double b = template[i + 1];
/*  491 */       Point2D.Double c = template[i + 2];
/*  492 */       curveTo(a.x + x, a.y + y, b.x + x, b.y + y, c.x + x, c.y + y);
/*      */     } 
/*      */   }
/*      */   
/*      */   private PDRectangle applyRectDiff(PDRectangle rd, double min) {
/*      */     double rdLeft, rdBottom, rdRight, rdTop;
/*  498 */     float rectLeft = this.annotRect.getLowerLeftX();
/*  499 */     float rectBottom = this.annotRect.getLowerLeftY();
/*  500 */     float rectRight = this.annotRect.getUpperRightX();
/*  501 */     float rectTop = this.annotRect.getUpperRightY();
/*      */ 
/*      */     
/*  504 */     rectLeft = Math.min(rectLeft, rectRight);
/*  505 */     rectBottom = Math.min(rectBottom, rectTop);
/*  506 */     rectRight = Math.max(rectLeft, rectRight);
/*  507 */     rectTop = Math.max(rectBottom, rectTop);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     if (rd != null) {
/*      */       
/*  516 */       rdLeft = Math.max(rd.getLowerLeftX(), min);
/*  517 */       rdBottom = Math.max(rd.getLowerLeftY(), min);
/*  518 */       rdRight = Math.max(rd.getUpperRightX(), min);
/*  519 */       rdTop = Math.max(rd.getUpperRightY(), min);
/*      */     }
/*      */     else {
/*      */       
/*  523 */       rdLeft = min;
/*  524 */       rdBottom = min;
/*  525 */       rdRight = min;
/*  526 */       rdTop = min;
/*      */     } 
/*      */     
/*  529 */     rectLeft = (float)(rectLeft + rdLeft);
/*  530 */     rectBottom = (float)(rectBottom + rdBottom);
/*  531 */     rectRight = (float)(rectRight - rdRight);
/*  532 */     rectTop = (float)(rectTop - rdTop);
/*      */     
/*  534 */     return new PDRectangle(rectLeft, rectBottom, rectRight - rectLeft, rectTop - rectBottom);
/*      */   }
/*      */ 
/*      */   
/*      */   private void reversePolygon(Point2D.Double[] points) {
/*  539 */     int len = points.length;
/*  540 */     int n = len / 2;
/*  541 */     for (int i = 0; i < n; i++) {
/*      */       
/*  543 */       int j = len - i - 1;
/*  544 */       Point2D.Double pi = points[i];
/*  545 */       Point2D.Double pj = points[j];
/*  546 */       points[i] = pj;
/*  547 */       points[j] = pi;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getPositivePolygon(Point2D.Double[] points) {
/*  558 */     if (getPolygonDirection(points) < 0.0D)
/*      */     {
/*  560 */       reversePolygon(points);
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
/*      */   
/*      */   private double getPolygonDirection(Point2D.Double[] points) {
/*  575 */     double a = 0.0D;
/*  576 */     int len = points.length;
/*  577 */     for (int i = 0; i < len; i++) {
/*      */       
/*  579 */       int j = (i + 1) % len;
/*  580 */       a += (points[i]).x * (points[j]).y - (points[i]).y * (points[j]).x;
/*      */     } 
/*  582 */     return a;
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
/*      */   private void getArc(double startAng, double endAng, double rx, double ry, double cx, double cy, ArrayList<Point2D.Double> out, boolean addMoveTo) throws IOException {
/*  595 */     double angleIncr = 1.5707963267948966D;
/*  596 */     double startx = rx * Math.cos(startAng) + cx;
/*  597 */     double starty = ry * Math.sin(startAng) + cy;
/*      */     
/*  599 */     double angleTodo = endAng - startAng;
/*  600 */     while (angleTodo < 0.0D)
/*      */     {
/*  602 */       angleTodo += 6.283185307179586D;
/*      */     }
/*  604 */     double sweep = angleTodo;
/*  605 */     double angleDone = 0.0D;
/*      */     
/*  607 */     if (addMoveTo)
/*      */     {
/*  609 */       if (out != null) {
/*      */         
/*  611 */         out.add(new Point2D.Double(startx, starty));
/*      */       }
/*      */       else {
/*      */         
/*  615 */         moveTo(startx, starty);
/*      */       } 
/*      */     }
/*      */     
/*  619 */     while (angleTodo > 1.5707963267948966D) {
/*      */       
/*  621 */       getArcSegment(startAng + angleDone, startAng + angleDone + 1.5707963267948966D, cx, cy, rx, ry, out, false);
/*      */       
/*  623 */       angleDone += 1.5707963267948966D;
/*  624 */       angleTodo -= 1.5707963267948966D;
/*      */     } 
/*      */     
/*  627 */     if (angleTodo > 0.0D)
/*      */     {
/*  629 */       getArcSegment(startAng + angleDone, startAng + sweep, cx, cy, rx, ry, out, false);
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
/*      */   
/*      */   private void getArcSegment(double startAng, double endAng, double cx, double cy, double rx, double ry, ArrayList<Point2D.Double> out, boolean addMoveTo) throws IOException {
/*  644 */     double cosA = Math.cos(startAng);
/*  645 */     double sinA = Math.sin(startAng);
/*  646 */     double cosB = Math.cos(endAng);
/*  647 */     double sinB = Math.sin(endAng);
/*  648 */     double denom = Math.sin((endAng - startAng) / 2.0D);
/*  649 */     if (Double.compare(denom, 0.0D) == 0) {
/*      */ 
/*      */ 
/*      */       
/*  653 */       if (addMoveTo) {
/*      */         
/*  655 */         double xs = cx + rx * cosA;
/*  656 */         double ys = cy + ry * sinA;
/*  657 */         if (out != null) {
/*      */           
/*  659 */           out.add(new Point2D.Double(xs, ys));
/*      */         }
/*      */         else {
/*      */           
/*  663 */           moveTo(xs, ys);
/*      */         } 
/*      */       } 
/*      */       return;
/*      */     } 
/*  668 */     double bcp = 1.333333333D * (1.0D - Math.cos((endAng - startAng) / 2.0D)) / denom;
/*  669 */     double p1x = cx + rx * (cosA - bcp * sinA);
/*  670 */     double p1y = cy + ry * (sinA + bcp * cosA);
/*  671 */     double p2x = cx + rx * (cosB + bcp * sinB);
/*  672 */     double p2y = cy + ry * (sinB - bcp * cosB);
/*  673 */     double p3x = cx + rx * cosB;
/*  674 */     double p3y = cy + ry * sinB;
/*      */     
/*  676 */     if (addMoveTo) {
/*      */       
/*  678 */       double xs = cx + rx * cosA;
/*  679 */       double ys = cy + ry * sinA;
/*  680 */       if (out != null) {
/*      */         
/*  682 */         out.add(new Point2D.Double(xs, ys));
/*      */       }
/*      */       else {
/*      */         
/*  686 */         moveTo(xs, ys);
/*      */       } 
/*      */     } 
/*      */     
/*  690 */     if (out != null) {
/*      */       
/*  692 */       out.add(new Point2D.Double(p1x, p1y));
/*  693 */       out.add(new Point2D.Double(p2x, p2y));
/*  694 */       out.add(new Point2D.Double(p3x, p3y));
/*      */     }
/*      */     else {
/*      */       
/*  698 */       curveTo(p1x, p1y, p2x, p2y, p3x, p3y);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Point2D.Double[] flattenEllipse(double left, double bottom, double right, double top) {
/*  708 */     Ellipse2D.Double ellipse = new Ellipse2D.Double(left, bottom, right - left, top - bottom);
/*  709 */     double flatness = 0.5D;
/*  710 */     PathIterator iterator = ellipse.getPathIterator(null, 0.5D);
/*  711 */     double[] coords = new double[6];
/*  712 */     ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
/*      */     
/*  714 */     while (!iterator.isDone()) {
/*      */       
/*  716 */       switch (iterator.currentSegment(coords)) {
/*      */         
/*      */         case 0:
/*      */         case 1:
/*  720 */           points.add(new Point2D.Double(coords[0], coords[1]));
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  727 */       iterator.next();
/*      */     } 
/*      */     
/*  730 */     int size = points.size();
/*  731 */     double closeTestLimit = 0.05D;
/*      */     
/*  733 */     if (size >= 2 && ((Point2D.Double)points.get(size - 1)).distance(points.get(0)) > 0.05D)
/*      */     {
/*  735 */       points.add(points.get(points.size() - 1));
/*      */     }
/*  737 */     return points.<Point2D.Double>toArray(new Point2D.Double[points.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cloudyEllipseImpl(double leftOrig, double bottomOrig, double rightOrig, double topOrig) throws IOException {
/*  746 */     if (this.intensity <= 0.0D) {
/*      */       
/*  748 */       drawBasicEllipse(leftOrig, bottomOrig, rightOrig, topOrig);
/*      */       
/*      */       return;
/*      */     } 
/*  752 */     double left = leftOrig;
/*  753 */     double bottom = bottomOrig;
/*  754 */     double right = rightOrig;
/*  755 */     double top = topOrig;
/*  756 */     double width = right - left;
/*  757 */     double height = top - bottom;
/*  758 */     double cloudRadius = getEllipseCloudRadius();
/*      */ 
/*      */     
/*  761 */     double threshold1 = 0.5D * cloudRadius;
/*  762 */     if (width < threshold1 && height < threshold1) {
/*      */       
/*  764 */       drawBasicEllipse(left, bottom, right, top);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  770 */     double threshold2 = 5.0D;
/*  771 */     if ((width < 5.0D && height > 20.0D) || (width > 20.0D && height < 5.0D)) {
/*      */       
/*  773 */       cloudyRectangleImpl(left, bottom, right, top, true);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  779 */     double radiusAdj = Math.sin(ANGLE_12_DEG) * cloudRadius - 1.5D;
/*  780 */     if (width > 2.0D * radiusAdj) {
/*      */       
/*  782 */       left += radiusAdj;
/*  783 */       right -= radiusAdj;
/*      */     }
/*      */     else {
/*      */       
/*  787 */       double mid = (left + right) / 2.0D;
/*  788 */       left = mid - 0.1D;
/*  789 */       right = mid + 0.1D;
/*      */     } 
/*  791 */     if (height > 2.0D * radiusAdj) {
/*      */       
/*  793 */       top -= radiusAdj;
/*  794 */       bottom += radiusAdj;
/*      */     }
/*      */     else {
/*      */       
/*  798 */       double mid = (top + bottom) / 2.0D;
/*  799 */       top = mid + 0.1D;
/*  800 */       bottom = mid - 0.1D;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  808 */     Point2D.Double[] flatPolygon = flattenEllipse(left, bottom, right, top);
/*  809 */     int numPoints = flatPolygon.length;
/*  810 */     if (numPoints < 2) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  815 */     double totLen = 0.0D;
/*  816 */     for (int i = 1; i < numPoints; i++) {
/*  817 */       totLen += flatPolygon[i - 1].distance(flatPolygon[i]);
/*      */     }
/*      */     
/*  820 */     double k = Math.cos(ANGLE_34_DEG);
/*  821 */     double curlAdvance = 2.0D * k * cloudRadius;
/*  822 */     int n = (int)Math.ceil(totLen / curlAdvance);
/*  823 */     if (n < 2) {
/*      */       
/*  825 */       drawBasicEllipse(leftOrig, bottomOrig, rightOrig, topOrig);
/*      */       
/*      */       return;
/*      */     } 
/*  829 */     curlAdvance = totLen / n;
/*  830 */     cloudRadius = curlAdvance / 2.0D * k;
/*      */     
/*  832 */     if (cloudRadius < 0.5D) {
/*      */       
/*  834 */       cloudRadius = 0.5D;
/*  835 */       curlAdvance = 2.0D * k * cloudRadius;
/*      */     }
/*  837 */     else if (cloudRadius < 3.0D) {
/*      */ 
/*      */ 
/*      */       
/*  841 */       drawBasicEllipse(leftOrig, bottomOrig, rightOrig, topOrig);
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  849 */     int centerPointsLength = n;
/*  850 */     Point2D.Double[] centerPoints = new Point2D.Double[centerPointsLength];
/*  851 */     int centerPointsIndex = 0;
/*  852 */     double lengthRemain = 0.0D;
/*  853 */     double comparisonToler = this.lineWidth * 0.1D;
/*      */     
/*  855 */     for (int j = 0; j + 1 < numPoints; j++) {
/*      */       
/*  857 */       Point2D.Double p1 = flatPolygon[j];
/*  858 */       Point2D.Double p2 = flatPolygon[j + 1];
/*  859 */       double dx = p2.x - p1.x;
/*  860 */       double dy = p2.y - p1.y;
/*  861 */       double length = p1.distance(p2);
/*  862 */       if (Double.compare(length, 0.0D) != 0) {
/*      */ 
/*      */ 
/*      */         
/*  866 */         double lengthTodo = length + lengthRemain;
/*  867 */         if (lengthTodo >= curlAdvance - comparisonToler || j == numPoints - 2) {
/*      */           
/*  869 */           double cos = cosine(dx, length);
/*  870 */           double sin = sine(dy, length);
/*  871 */           double d = curlAdvance - lengthRemain;
/*      */           
/*      */           do {
/*  874 */             double x = p1.x + d * cos;
/*  875 */             double y = p1.y + d * sin;
/*  876 */             if (centerPointsIndex < centerPointsLength)
/*      */             {
/*  878 */               centerPoints[centerPointsIndex++] = new Point2D.Double(x, y);
/*      */             }
/*  880 */             lengthTodo -= curlAdvance;
/*  881 */             d += curlAdvance;
/*      */           }
/*  883 */           while (lengthTodo >= curlAdvance - comparisonToler);
/*      */           
/*  885 */           lengthRemain = lengthTodo;
/*  886 */           if (lengthRemain < 0.0D)
/*      */           {
/*  888 */             lengthRemain = 0.0D;
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  893 */           lengthRemain += length;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     numPoints = centerPointsIndex;
/*  906 */     double anglePrev = 0.0D;
/*  907 */     double alphaPrev = 0.0D;
/*      */     
/*  909 */     for (int m = 0; m < numPoints; m++) {
/*      */       
/*  911 */       int idxNext = m + 1;
/*  912 */       if (m + 1 >= numPoints)
/*      */       {
/*  914 */         idxNext = 0;
/*      */       }
/*  916 */       Point2D.Double pt = centerPoints[m];
/*  917 */       Point2D.Double ptNext = centerPoints[idxNext];
/*      */       
/*  919 */       if (m == 0) {
/*      */         
/*  921 */         Point2D.Double ptPrev = centerPoints[numPoints - 1];
/*  922 */         anglePrev = Math.atan2(pt.y - ptPrev.y, pt.x - ptPrev.x);
/*  923 */         alphaPrev = computeParamsEllipse(ptPrev, pt, cloudRadius, curlAdvance);
/*      */       } 
/*      */       
/*  926 */       double angleCur = Math.atan2(ptNext.y - pt.y, ptNext.x - pt.x);
/*  927 */       double alpha = computeParamsEllipse(pt, ptNext, cloudRadius, curlAdvance);
/*      */       
/*  929 */       addCornerCurl(anglePrev, angleCur, cloudRadius, pt.x, pt.y, alpha, alphaPrev, !this.outputStarted);
/*      */ 
/*      */       
/*  932 */       anglePrev = angleCur;
/*  933 */       alphaPrev = alpha;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double computeParamsEllipse(Point2D.Double pt, Point2D.Double ptNext, double r, double curlAdv) {
/*  943 */     double length = pt.distance(ptNext);
/*  944 */     if (Double.compare(length, 0.0D) == 0)
/*      */     {
/*  946 */       return ANGLE_34_DEG;
/*      */     }
/*      */     
/*  949 */     double e = length - curlAdv;
/*  950 */     double arg = (curlAdv / 2.0D + e / 2.0D) / r;
/*  951 */     return (arg < -1.0D || arg > 1.0D) ? 0.0D : Math.acos(arg);
/*      */   }
/*      */ 
/*      */   
/*      */   private Point2D.Double[] removeZeroLengthSegments(Point2D.Double[] polygon) {
/*  956 */     int np = polygon.length;
/*  957 */     if (np <= 2)
/*      */     {
/*  959 */       return polygon;
/*      */     }
/*      */     
/*  962 */     double toler = 0.5D;
/*  963 */     int npNew = np;
/*  964 */     Point2D.Double ptPrev = polygon[0];
/*      */ 
/*      */     
/*  967 */     for (int i = 1; i < np; i++) {
/*      */       
/*  969 */       Point2D.Double pt = polygon[i];
/*  970 */       if (Math.abs(pt.x - ptPrev.x) < 0.5D && Math.abs(pt.y - ptPrev.y) < 0.5D) {
/*      */         
/*  972 */         polygon[i] = null;
/*  973 */         npNew--;
/*      */       } 
/*  975 */       ptPrev = pt;
/*      */     } 
/*      */     
/*  978 */     if (npNew == np)
/*      */     {
/*  980 */       return polygon;
/*      */     }
/*      */     
/*  983 */     Point2D.Double[] polygonNew = new Point2D.Double[npNew];
/*  984 */     int j = 0;
/*  985 */     for (int k = 0; k < np; k++) {
/*      */       
/*  987 */       Point2D.Double pt = polygon[k];
/*  988 */       if (pt != null)
/*      */       {
/*  990 */         polygonNew[j++] = pt;
/*      */       }
/*      */     } 
/*      */     
/*  994 */     return polygonNew;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawBasicEllipse(double left, double bottom, double right, double top) throws IOException {
/* 1003 */     double rx = Math.abs(right - left) / 2.0D;
/* 1004 */     double ry = Math.abs(top - bottom) / 2.0D;
/* 1005 */     double cx = (left + right) / 2.0D;
/* 1006 */     double cy = (bottom + top) / 2.0D;
/* 1007 */     getArc(0.0D, 6.283185307179586D, rx, ry, cx, cy, null, true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void beginOutput(double x, double y) throws IOException {
/* 1012 */     this.bboxMinX = x;
/* 1013 */     this.bboxMinY = y;
/* 1014 */     this.bboxMaxX = x;
/* 1015 */     this.bboxMaxY = y;
/* 1016 */     this.outputStarted = true;
/*      */     
/* 1018 */     this.output.setLineJoinStyle(2);
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateBBox(double x, double y) {
/* 1023 */     this.bboxMinX = Math.min(this.bboxMinX, x);
/* 1024 */     this.bboxMinY = Math.min(this.bboxMinY, y);
/* 1025 */     this.bboxMaxX = Math.max(this.bboxMaxX, x);
/* 1026 */     this.bboxMaxY = Math.max(this.bboxMaxY, y);
/*      */   }
/*      */ 
/*      */   
/*      */   private void moveTo(Point2D.Double p) throws IOException {
/* 1031 */     moveTo(p.x, p.y);
/*      */   }
/*      */ 
/*      */   
/*      */   private void moveTo(double x, double y) throws IOException {
/* 1036 */     if (this.outputStarted) {
/*      */       
/* 1038 */       updateBBox(x, y);
/*      */     }
/*      */     else {
/*      */       
/* 1042 */       beginOutput(x, y);
/*      */     } 
/*      */     
/* 1045 */     this.output.moveTo((float)x, (float)y);
/*      */   }
/*      */ 
/*      */   
/*      */   private void lineTo(Point2D.Double p) throws IOException {
/* 1050 */     lineTo(p.x, p.y);
/*      */   }
/*      */ 
/*      */   
/*      */   private void lineTo(double x, double y) throws IOException {
/* 1055 */     if (this.outputStarted) {
/*      */       
/* 1057 */       updateBBox(x, y);
/*      */     }
/*      */     else {
/*      */       
/* 1061 */       beginOutput(x, y);
/*      */     } 
/*      */     
/* 1064 */     this.output.lineTo((float)x, (float)y);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void curveTo(double ax, double ay, double bx, double by, double cx, double cy) throws IOException {
/* 1070 */     updateBBox(ax, ay);
/* 1071 */     updateBBox(bx, by);
/* 1072 */     updateBBox(cx, cy);
/* 1073 */     this.output.curveTo((float)ax, (float)ay, (float)bx, (float)by, (float)cx, (float)cy);
/*      */   }
/*      */ 
/*      */   
/*      */   private void finish() throws IOException {
/* 1078 */     if (this.outputStarted)
/*      */     {
/* 1080 */       this.output.closePath();
/*      */     }
/*      */     
/* 1083 */     if (this.lineWidth > 0.0D) {
/*      */       
/* 1085 */       double d = this.lineWidth / 2.0D;
/* 1086 */       this.bboxMinX -= d;
/* 1087 */       this.bboxMinY -= d;
/* 1088 */       this.bboxMaxX += d;
/* 1089 */       this.bboxMaxY += d;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double getEllipseCloudRadius() {
/* 1097 */     return 4.75D * this.intensity + 0.5D * this.lineWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private double getPolygonCloudRadius() {
/* 1103 */     return 4.0D * this.intensity + 0.5D * this.lineWidth;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/CloudyBorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */