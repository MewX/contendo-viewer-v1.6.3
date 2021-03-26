/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedPathIterator;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedShape;
/*     */ import org.apache.batik.ext.awt.geom.ShapeExtender;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarkerShapePainter
/*     */   implements ShapePainter
/*     */ {
/*     */   protected ExtendedShape extShape;
/*     */   protected Marker startMarker;
/*     */   protected Marker middleMarker;
/*     */   protected Marker endMarker;
/*     */   private ProxyGraphicsNode startMarkerProxy;
/*     */   private ProxyGraphicsNode[] middleMarkerProxies;
/*     */   private ProxyGraphicsNode endMarkerProxy;
/*     */   private CompositeGraphicsNode markerGroup;
/*     */   private Rectangle2D dPrimitiveBounds;
/*     */   private Rectangle2D dGeometryBounds;
/*     */   
/*     */   public MarkerShapePainter(Shape shape) {
/* 102 */     if (shape == null) {
/* 103 */       throw new IllegalArgumentException();
/*     */     }
/* 105 */     if (shape instanceof ExtendedShape) {
/* 106 */       this.extShape = (ExtendedShape)shape;
/*     */     } else {
/* 108 */       this.extShape = (ExtendedShape)new ShapeExtender(shape);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics2D g2d) {
/* 118 */     if (this.markerGroup == null) {
/* 119 */       buildMarkerGroup();
/*     */     }
/* 121 */     if (this.markerGroup.getChildren().size() > 0) {
/* 122 */       this.markerGroup.paint(g2d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getPaintedArea() {
/* 130 */     if (this.markerGroup == null) {
/* 131 */       buildMarkerGroup();
/*     */     }
/* 133 */     return this.markerGroup.getOutline();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPaintedBounds2D() {
/* 140 */     if (this.markerGroup == null) {
/* 141 */       buildMarkerGroup();
/*     */     }
/* 143 */     return this.markerGroup.getPrimitiveBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inPaintedArea(Point2D pt) {
/* 150 */     if (this.markerGroup == null) {
/* 151 */       buildMarkerGroup();
/*     */     }
/* 153 */     GraphicsNode gn = this.markerGroup.nodeHitAt(pt);
/* 154 */     return (gn != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getSensitiveArea() {
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds2D() {
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inSensitiveArea(Point2D pt) {
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShape(Shape shape) {
/* 183 */     if (shape == null) {
/* 184 */       throw new IllegalArgumentException();
/*     */     }
/* 186 */     if (shape instanceof ExtendedShape) {
/* 187 */       this.extShape = (ExtendedShape)shape;
/*     */     } else {
/* 189 */       this.extShape = (ExtendedShape)new ShapeExtender(shape);
/*     */     } 
/*     */     
/* 192 */     this.startMarkerProxy = null;
/* 193 */     this.middleMarkerProxies = null;
/* 194 */     this.endMarkerProxy = null;
/* 195 */     this.markerGroup = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedShape getExtShape() {
/* 204 */     return this.extShape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 213 */     return (Shape)this.extShape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getStartMarker() {
/* 221 */     return this.startMarker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartMarker(Marker startMarker) {
/* 231 */     this.startMarker = startMarker;
/* 232 */     this.startMarkerProxy = null;
/* 233 */     this.markerGroup = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getMiddleMarker() {
/* 241 */     return this.middleMarker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMiddleMarker(Marker middleMarker) {
/* 251 */     this.middleMarker = middleMarker;
/* 252 */     this.middleMarkerProxies = null;
/* 253 */     this.markerGroup = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getEndMarker() {
/* 261 */     return this.endMarker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndMarker(Marker endMarker) {
/* 271 */     this.endMarker = endMarker;
/* 272 */     this.endMarkerProxy = null;
/* 273 */     this.markerGroup = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buildMarkerGroup() {
/* 284 */     if (this.startMarker != null && this.startMarkerProxy == null) {
/* 285 */       this.startMarkerProxy = buildStartMarkerProxy();
/*     */     }
/*     */     
/* 288 */     if (this.middleMarker != null && this.middleMarkerProxies == null) {
/* 289 */       this.middleMarkerProxies = buildMiddleMarkerProxies();
/*     */     }
/*     */     
/* 292 */     if (this.endMarker != null && this.endMarkerProxy == null) {
/* 293 */       this.endMarkerProxy = buildEndMarkerProxy();
/*     */     }
/*     */     
/* 296 */     CompositeGraphicsNode group = new CompositeGraphicsNode();
/* 297 */     List<ProxyGraphicsNode> children = group.getChildren();
/* 298 */     if (this.startMarkerProxy != null) {
/* 299 */       children.add(this.startMarkerProxy);
/*     */     }
/*     */     
/* 302 */     if (this.middleMarkerProxies != null) {
/* 303 */       for (ProxyGraphicsNode middleMarkerProxy : this.middleMarkerProxies) {
/* 304 */         children.add(middleMarkerProxy);
/*     */       }
/*     */     }
/*     */     
/* 308 */     if (this.endMarkerProxy != null) {
/* 309 */       children.add(this.endMarkerProxy);
/*     */     }
/*     */     
/* 312 */     this.markerGroup = group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProxyGraphicsNode buildStartMarkerProxy() {
/* 321 */     ExtendedPathIterator iter = getExtShape().getExtendedPathIterator();
/*     */ 
/*     */     
/* 324 */     double[] coords = new double[7];
/* 325 */     int segType = 0;
/*     */     
/* 327 */     if (iter.isDone()) {
/* 328 */       return null;
/*     */     }
/*     */     
/* 331 */     segType = iter.currentSegment(coords);
/* 332 */     if (segType != 0) {
/* 333 */       return null;
/*     */     }
/* 335 */     iter.next();
/*     */     
/* 337 */     Point2D markerPosition = new Point2D.Double(coords[0], coords[1]);
/*     */ 
/*     */ 
/*     */     
/* 341 */     double rotation = this.startMarker.getOrient();
/* 342 */     if (Double.isNaN(rotation) && 
/* 343 */       !iter.isDone()) {
/* 344 */       double[] next = new double[7];
/* 345 */       int nextSegType = 0;
/* 346 */       nextSegType = iter.currentSegment(next);
/* 347 */       if (nextSegType == 4) {
/* 348 */         nextSegType = 1;
/* 349 */         next[0] = coords[0];
/* 350 */         next[1] = coords[1];
/*     */       } 
/* 352 */       rotation = computeRotation(null, 0, coords, segType, next, nextSegType);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 360 */     AffineTransform markerTxf = computeMarkerTransform(this.startMarker, markerPosition, rotation);
/*     */ 
/*     */ 
/*     */     
/* 364 */     ProxyGraphicsNode gn = new ProxyGraphicsNode();
/*     */     
/* 366 */     gn.setSource(this.startMarker.getMarkerNode());
/* 367 */     gn.setTransform(markerTxf);
/*     */     
/* 369 */     return gn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProxyGraphicsNode buildEndMarkerProxy() {
/* 378 */     ExtendedPathIterator iter = getExtShape().getExtendedPathIterator();
/*     */     
/* 380 */     int nPoints = 0;
/*     */ 
/*     */ 
/*     */     
/* 384 */     if (iter.isDone()) {
/* 385 */       return null;
/*     */     }
/*     */     
/* 388 */     double[] coords = new double[7];
/* 389 */     double[] moveTo = new double[2];
/* 390 */     int segType = 0;
/* 391 */     segType = iter.currentSegment(coords);
/* 392 */     if (segType != 0) {
/* 393 */       return null;
/*     */     }
/* 395 */     nPoints++;
/* 396 */     moveTo[0] = coords[0];
/* 397 */     moveTo[1] = coords[1];
/*     */     
/* 399 */     iter.next();
/*     */ 
/*     */     
/* 402 */     double[] lastButOne = new double[7];
/* 403 */     double[] last = { coords[0], coords[1], coords[2], coords[3], coords[4], coords[5], coords[6] };
/*     */     
/* 405 */     double[] tmp = null;
/* 406 */     int lastSegType = segType;
/* 407 */     int lastButOneSegType = 0;
/*     */     
/* 409 */     while (!iter.isDone()) {
/* 410 */       tmp = lastButOne;
/* 411 */       lastButOne = last;
/* 412 */       last = tmp;
/* 413 */       lastButOneSegType = lastSegType;
/*     */       
/* 415 */       lastSegType = iter.currentSegment(last);
/*     */       
/* 417 */       if (lastSegType == 0) {
/* 418 */         moveTo[0] = last[0];
/* 419 */         moveTo[1] = last[1];
/* 420 */       } else if (lastSegType == 4) {
/* 421 */         lastSegType = 1;
/* 422 */         last[0] = moveTo[0];
/* 423 */         last[1] = moveTo[1];
/*     */       } 
/*     */       
/* 426 */       iter.next();
/* 427 */       nPoints++;
/*     */     } 
/*     */     
/* 430 */     if (nPoints < 2) {
/* 431 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 435 */     Point2D markerPosition = getSegmentTerminatingPoint(last, lastSegType);
/*     */ 
/*     */ 
/*     */     
/* 439 */     double rotation = this.endMarker.getOrient();
/* 440 */     if (Double.isNaN(rotation)) {
/* 441 */       rotation = computeRotation(lastButOne, lastButOneSegType, last, lastSegType, null, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 448 */     AffineTransform markerTxf = computeMarkerTransform(this.endMarker, markerPosition, rotation);
/*     */ 
/*     */ 
/*     */     
/* 452 */     ProxyGraphicsNode gn = new ProxyGraphicsNode();
/*     */     
/* 454 */     gn.setSource(this.endMarker.getMarkerNode());
/* 455 */     gn.setTransform(markerTxf);
/*     */     
/* 457 */     return gn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProxyGraphicsNode[] buildMiddleMarkerProxies() {
/* 466 */     ExtendedPathIterator iter = getExtShape().getExtendedPathIterator();
/*     */     
/* 468 */     double[] prev = new double[7];
/* 469 */     double[] curr = new double[7];
/* 470 */     double[] next = new double[7], tmp = null;
/* 471 */     int prevSegType = 0, currSegType = 0, nextSegType = 0;
/*     */ 
/*     */     
/* 474 */     if (iter.isDone()) {
/* 475 */       return null;
/*     */     }
/*     */     
/* 478 */     prevSegType = iter.currentSegment(prev);
/* 479 */     double[] moveTo = new double[2];
/*     */     
/* 481 */     if (prevSegType != 0) {
/* 482 */       return null;
/*     */     }
/*     */     
/* 485 */     moveTo[0] = prev[0];
/* 486 */     moveTo[1] = prev[1];
/* 487 */     iter.next();
/*     */     
/* 489 */     if (iter.isDone()) {
/* 490 */       return null;
/*     */     }
/*     */     
/* 493 */     currSegType = iter.currentSegment(curr);
/*     */     
/* 495 */     if (currSegType == 0) {
/* 496 */       moveTo[0] = curr[0];
/* 497 */       moveTo[1] = curr[1];
/* 498 */     } else if (currSegType == 4) {
/* 499 */       currSegType = 1;
/* 500 */       curr[0] = moveTo[0];
/* 501 */       curr[1] = moveTo[1];
/*     */     } 
/*     */     
/* 504 */     iter.next();
/*     */     
/* 506 */     List<ProxyGraphicsNode> proxies = new ArrayList();
/* 507 */     while (!iter.isDone()) {
/* 508 */       nextSegType = iter.currentSegment(next);
/*     */       
/* 510 */       if (nextSegType == 0) {
/* 511 */         moveTo[0] = next[0];
/* 512 */         moveTo[1] = next[1];
/* 513 */       } else if (nextSegType == 4) {
/* 514 */         nextSegType = 1;
/* 515 */         next[0] = moveTo[0];
/* 516 */         next[1] = moveTo[1];
/*     */       } 
/*     */       
/* 519 */       proxies.add(createMiddleMarker(prev, prevSegType, curr, currSegType, next, nextSegType));
/*     */ 
/*     */ 
/*     */       
/* 523 */       tmp = prev;
/* 524 */       prev = curr;
/* 525 */       prevSegType = currSegType;
/* 526 */       curr = next;
/* 527 */       currSegType = nextSegType;
/* 528 */       next = tmp;
/*     */       
/* 530 */       iter.next();
/*     */     } 
/*     */     
/* 533 */     ProxyGraphicsNode[] gn = new ProxyGraphicsNode[proxies.size()];
/* 534 */     proxies.toArray(gn);
/*     */     
/* 536 */     return gn;
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
/*     */   private ProxyGraphicsNode createMiddleMarker(double[] prev, int prevSegType, double[] curr, int currSegType, double[] next, int nextSegType) {
/* 548 */     Point2D markerPosition = getSegmentTerminatingPoint(curr, currSegType);
/*     */ 
/*     */ 
/*     */     
/* 552 */     double rotation = this.middleMarker.getOrient();
/* 553 */     if (Double.isNaN(rotation)) {
/* 554 */       rotation = computeRotation(prev, prevSegType, curr, currSegType, next, nextSegType);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     AffineTransform markerTxf = computeMarkerTransform(this.middleMarker, markerPosition, rotation);
/*     */ 
/*     */ 
/*     */     
/* 564 */     ProxyGraphicsNode gn = new ProxyGraphicsNode();
/*     */     
/* 566 */     gn.setSource(this.middleMarker.getMarkerNode());
/* 567 */     gn.setTransform(markerTxf);
/*     */     
/* 569 */     return gn;
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
/*     */   private double computeRotation(double[] prev, int prevSegType, double[] curr, int currSegType, double[] next, int nextSegType) {
/* 581 */     double[] inSlope = computeInSlope(prev, prevSegType, curr, currSegType);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     double[] outSlope = computeOutSlope(curr, currSegType, next, nextSegType);
/*     */ 
/*     */     
/* 589 */     if (inSlope == null) {
/* 590 */       inSlope = outSlope;
/*     */     }
/*     */     
/* 593 */     if (outSlope == null) {
/* 594 */       outSlope = inSlope;
/*     */     }
/*     */     
/* 597 */     if (inSlope == null) {
/* 598 */       return 0.0D;
/*     */     }
/*     */     
/* 601 */     double dx = inSlope[0] + outSlope[0];
/* 602 */     double dy = inSlope[1] + outSlope[1];
/*     */     
/* 604 */     if (dx == 0.0D && dy == 0.0D)
/*     */     {
/*     */       
/* 607 */       return Math.toDegrees(Math.atan2(inSlope[1], inSlope[0])) + 90.0D;
/*     */     }
/* 609 */     return Math.toDegrees(Math.atan2(dy, dx));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] computeInSlope(double[] prev, int prevSegType, double[] curr, int currSegType) {
/*     */     Point2D prevEndPoint;
/*     */     boolean large, goLeft;
/*     */     Arc2D arc;
/*     */     double theta;
/* 620 */     Point2D currEndPoint = getSegmentTerminatingPoint(curr, currSegType);
/*     */     
/* 622 */     double dx = 0.0D;
/* 623 */     double dy = 0.0D;
/*     */     
/* 625 */     switch (currSegType) {
/*     */ 
/*     */       
/*     */       case 1:
/* 629 */         prevEndPoint = getSegmentTerminatingPoint(prev, prevSegType);
/*     */         
/* 631 */         dx = currEndPoint.getX() - prevEndPoint.getX();
/* 632 */         dy = currEndPoint.getY() - prevEndPoint.getY();
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 639 */         dx = currEndPoint.getX() - curr[0];
/* 640 */         dy = currEndPoint.getY() - curr[1];
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 646 */         dx = currEndPoint.getX() - curr[2];
/* 647 */         dy = currEndPoint.getY() - curr[3];
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4321:
/* 652 */         prevEndPoint = getSegmentTerminatingPoint(prev, prevSegType);
/*     */         
/* 654 */         large = (curr[3] != 0.0D);
/* 655 */         goLeft = (curr[4] != 0.0D);
/* 656 */         arc = ExtendedGeneralPath.computeArc(prevEndPoint.getX(), prevEndPoint.getY(), curr[0], curr[1], curr[2], large, goLeft, curr[5], curr[6]);
/*     */ 
/*     */ 
/*     */         
/* 660 */         theta = arc.getAngleStart() + arc.getAngleExtent();
/* 661 */         theta = Math.toRadians(theta);
/* 662 */         dx = -arc.getWidth() / 2.0D * Math.sin(theta);
/* 663 */         dy = arc.getHeight() / 2.0D * Math.cos(theta);
/*     */ 
/*     */ 
/*     */         
/* 667 */         if (curr[2] != 0.0D) {
/* 668 */           double ang = Math.toRadians(-curr[2]);
/* 669 */           double sinA = Math.sin(ang);
/* 670 */           double cosA = Math.cos(ang);
/* 671 */           double tdx = dx * cosA - dy * sinA;
/* 672 */           double tdy = dx * sinA + dy * cosA;
/* 673 */           dx = tdx;
/* 674 */           dy = tdy;
/*     */         } 
/*     */ 
/*     */         
/* 678 */         if (goLeft) {
/* 679 */           dx = -dx; break;
/*     */         } 
/* 681 */         dy = -dy;
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 689 */         throw new RuntimeException("should not have SEG_CLOSE here");
/*     */ 
/*     */       
/*     */       default:
/* 693 */         return null;
/*     */     } 
/*     */     
/* 696 */     if (dx == 0.0D && dy == 0.0D) {
/* 697 */       return null;
/*     */     }
/*     */     
/* 700 */     return normalize(new double[] { dx, dy });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] computeOutSlope(double[] curr, int currSegType, double[] next, int nextSegType) {
/*     */     boolean large, goLeft;
/*     */     Arc2D arc;
/*     */     double theta;
/* 709 */     Point2D currEndPoint = getSegmentTerminatingPoint(curr, currSegType);
/*     */     
/* 711 */     double dx = 0.0D, dy = 0.0D;
/*     */     
/* 713 */     switch (nextSegType) {
/*     */       case 4:
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 724 */         dx = next[0] - currEndPoint.getX();
/* 725 */         dy = next[1] - currEndPoint.getY();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4321:
/* 730 */         large = (next[3] != 0.0D);
/* 731 */         goLeft = (next[4] != 0.0D);
/* 732 */         arc = ExtendedGeneralPath.computeArc(currEndPoint.getX(), currEndPoint.getY(), next[0], next[1], next[2], large, goLeft, next[5], next[6]);
/*     */ 
/*     */ 
/*     */         
/* 736 */         theta = arc.getAngleStart();
/* 737 */         theta = Math.toRadians(theta);
/* 738 */         dx = -arc.getWidth() / 2.0D * Math.sin(theta);
/* 739 */         dy = arc.getHeight() / 2.0D * Math.cos(theta);
/*     */ 
/*     */         
/* 742 */         if (next[2] != 0.0D) {
/* 743 */           double ang = Math.toRadians(-next[2]);
/* 744 */           double sinA = Math.sin(ang);
/* 745 */           double cosA = Math.cos(ang);
/* 746 */           double tdx = dx * cosA - dy * sinA;
/* 747 */           double tdy = dx * sinA + dy * cosA;
/* 748 */           dx = tdx;
/* 749 */           dy = tdy;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 754 */         if (goLeft) {
/* 755 */           dx = -dx; break;
/*     */         } 
/* 757 */         dy = -dy;
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 766 */         return null;
/*     */     } 
/*     */     
/* 769 */     if (dx == 0.0D && dy == 0.0D) {
/* 770 */       return null;
/*     */     }
/*     */     
/* 773 */     return normalize(new double[] { dx, dy });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] normalize(double[] v) {
/* 780 */     double n = Math.sqrt(v[0] * v[0] + v[1] * v[1]);
/* 781 */     v[0] = v[0] / n;
/* 782 */     v[1] = v[1] / n;
/* 783 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AffineTransform computeMarkerTransform(Marker marker, Point2D markerPosition, double rotation) {
/* 793 */     Point2D ref = marker.getRef();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 799 */     AffineTransform txf = new AffineTransform();
/*     */     
/* 801 */     txf.translate(markerPosition.getX() - ref.getX(), markerPosition.getY() - ref.getY());
/*     */ 
/*     */     
/* 804 */     if (!Double.isNaN(rotation)) {
/* 805 */       txf.rotate(Math.toRadians(rotation), ref.getX(), ref.getY());
/*     */     }
/*     */     
/* 808 */     return txf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D getSegmentTerminatingPoint(double[] coords, int segType) {
/* 815 */     switch (segType) {
/*     */       case 3:
/* 817 */         return new Point2D.Double(coords[4], coords[5]);
/*     */       case 1:
/* 819 */         return new Point2D.Double(coords[0], coords[1]);
/*     */       case 0:
/* 821 */         return new Point2D.Double(coords[0], coords[1]);
/*     */       case 2:
/* 823 */         return new Point2D.Double(coords[2], coords[3]);
/*     */       case 4321:
/* 825 */         return new Point2D.Double(coords[5], coords[6]);
/*     */     } 
/*     */     
/* 828 */     throw new RuntimeException("invalid segmentType:" + segType);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/MarkerShapePainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */