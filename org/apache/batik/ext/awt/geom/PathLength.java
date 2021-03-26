/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.FlatteningPathIterator;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathLength
/*     */ {
/*     */   protected Shape path;
/*     */   protected List segments;
/*     */   protected int[] segmentIndexes;
/*     */   protected float pathLength;
/*     */   protected boolean initialised;
/*     */   
/*     */   public PathLength(Shape path) {
/*  81 */     setPath(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getPath() {
/*  89 */     return this.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPath(Shape v) {
/*  97 */     this.path = v;
/*  98 */     this.initialised = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float lengthOfPath() {
/* 106 */     if (!this.initialised) {
/* 107 */       initialise();
/*     */     }
/* 109 */     return this.pathLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialise() {
/* 116 */     this.pathLength = 0.0F;
/*     */     
/* 118 */     PathIterator pi = this.path.getPathIterator(new AffineTransform());
/* 119 */     SingleSegmentPathIterator sspi = new SingleSegmentPathIterator();
/* 120 */     this.segments = new ArrayList(20);
/* 121 */     List<Integer> indexes = new ArrayList(20);
/* 122 */     int index = 0;
/* 123 */     int origIndex = -1;
/* 124 */     float lastMoveX = 0.0F;
/* 125 */     float lastMoveY = 0.0F;
/* 126 */     float currentX = 0.0F;
/* 127 */     float currentY = 0.0F;
/* 128 */     float[] seg = new float[6];
/*     */ 
/*     */     
/* 131 */     this.segments.add(new PathSegment(0, 0.0F, 0.0F, 0.0F, origIndex));
/*     */ 
/*     */     
/* 134 */     while (!pi.isDone()) {
/* 135 */       origIndex++;
/* 136 */       indexes.add(Integer.valueOf(index));
/* 137 */       int segType = pi.currentSegment(seg);
/* 138 */       switch (segType) {
/*     */         case 0:
/* 140 */           this.segments.add(new PathSegment(segType, seg[0], seg[1], this.pathLength, origIndex));
/*     */           
/* 142 */           currentX = seg[0];
/* 143 */           currentY = seg[1];
/* 144 */           lastMoveX = currentX;
/* 145 */           lastMoveY = currentY;
/* 146 */           index++;
/* 147 */           pi.next();
/*     */           continue;
/*     */         case 1:
/* 150 */           this.pathLength = (float)(this.pathLength + Point2D.distance(currentX, currentY, seg[0], seg[1]));
/*     */           
/* 152 */           this.segments.add(new PathSegment(segType, seg[0], seg[1], this.pathLength, origIndex));
/*     */           
/* 154 */           currentX = seg[0];
/* 155 */           currentY = seg[1];
/* 156 */           index++;
/* 157 */           pi.next();
/*     */           continue;
/*     */         case 4:
/* 160 */           this.pathLength = (float)(this.pathLength + Point2D.distance(currentX, currentY, lastMoveX, lastMoveY));
/*     */           
/* 162 */           this.segments.add(new PathSegment(1, lastMoveX, lastMoveY, this.pathLength, origIndex));
/*     */ 
/*     */           
/* 165 */           currentX = lastMoveX;
/* 166 */           currentY = lastMoveY;
/* 167 */           index++;
/* 168 */           pi.next();
/*     */           continue;
/*     */       } 
/* 171 */       sspi.setPathIterator(pi, currentX, currentY);
/* 172 */       FlatteningPathIterator fpi = new FlatteningPathIterator(sspi, 0.009999999776482582D);
/*     */       
/* 174 */       while (!fpi.isDone()) {
/* 175 */         segType = fpi.currentSegment(seg);
/* 176 */         if (segType == 1) {
/* 177 */           this.pathLength = (float)(this.pathLength + Point2D.distance(currentX, currentY, seg[0], seg[1]));
/*     */           
/* 179 */           this.segments.add(new PathSegment(segType, seg[0], seg[1], this.pathLength, origIndex));
/*     */ 
/*     */           
/* 182 */           currentX = seg[0];
/* 183 */           currentY = seg[1];
/* 184 */           index++;
/*     */         } 
/* 186 */         fpi.next();
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     this.segmentIndexes = new int[indexes.size()];
/* 191 */     for (int i = 0; i < this.segmentIndexes.length; i++) {
/* 192 */       this.segmentIndexes[i] = ((Integer)indexes.get(i)).intValue();
/*     */     }
/* 194 */     this.initialised = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSegments() {
/* 201 */     if (!this.initialised) {
/* 202 */       initialise();
/*     */     }
/* 204 */     return this.segmentIndexes.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLengthAtSegment(int index) {
/* 212 */     if (!this.initialised) {
/* 213 */       initialise();
/*     */     }
/* 215 */     if (index <= 0) {
/* 216 */       return 0.0F;
/*     */     }
/* 218 */     if (index >= this.segmentIndexes.length) {
/* 219 */       return this.pathLength;
/*     */     }
/* 221 */     PathSegment seg = this.segments.get(this.segmentIndexes[index]);
/* 222 */     return seg.getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int segmentAtLength(float length) {
/* 229 */     int upperIndex = findUpperIndex(length);
/* 230 */     if (upperIndex == -1)
/*     */     {
/* 232 */       return -1;
/*     */     }
/*     */     
/* 235 */     if (upperIndex == 0) {
/*     */       
/* 237 */       PathSegment upper = this.segments.get(upperIndex);
/* 238 */       return upper.getIndex();
/*     */     } 
/*     */     
/* 241 */     PathSegment lower = this.segments.get(upperIndex - 1);
/* 242 */     return lower.getIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D pointAtLength(int index, float proportion) {
/*     */     float end;
/* 250 */     if (!this.initialised) {
/* 251 */       initialise();
/*     */     }
/* 253 */     if (index < 0 || index >= this.segmentIndexes.length) {
/* 254 */       return null;
/*     */     }
/* 256 */     PathSegment seg = this.segments.get(this.segmentIndexes[index]);
/* 257 */     float start = seg.getLength();
/*     */     
/* 259 */     if (index == this.segmentIndexes.length - 1) {
/* 260 */       end = this.pathLength;
/*     */     } else {
/* 262 */       seg = this.segments.get(this.segmentIndexes[index + 1]);
/* 263 */       end = seg.getLength();
/*     */     } 
/* 265 */     return pointAtLength(start + (end - start) * proportion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D pointAtLength(float length) {
/* 274 */     int upperIndex = findUpperIndex(length);
/* 275 */     if (upperIndex == -1)
/*     */     {
/* 277 */       return null;
/*     */     }
/*     */     
/* 280 */     PathSegment upper = this.segments.get(upperIndex);
/*     */     
/* 282 */     if (upperIndex == 0)
/*     */     {
/* 284 */       return new Point2D.Float(upper.getX(), upper.getY());
/*     */     }
/*     */     
/* 287 */     PathSegment lower = this.segments.get(upperIndex - 1);
/*     */ 
/*     */     
/* 290 */     float offset = length - lower.getLength();
/*     */ 
/*     */     
/* 293 */     double theta = Math.atan2((upper.getY() - lower.getY()), (upper.getX() - lower.getX()));
/*     */ 
/*     */     
/* 296 */     float xPoint = (float)(lower.getX() + offset * Math.cos(theta));
/* 297 */     float yPoint = (float)(lower.getY() + offset * Math.sin(theta));
/*     */     
/* 299 */     return new Point2D.Float(xPoint, yPoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float angleAtLength(int index, float proportion) {
/*     */     float end;
/* 310 */     if (!this.initialised) {
/* 311 */       initialise();
/*     */     }
/* 313 */     if (index < 0 || index >= this.segmentIndexes.length) {
/* 314 */       return 0.0F;
/*     */     }
/* 316 */     PathSegment seg = this.segments.get(this.segmentIndexes[index]);
/* 317 */     float start = seg.getLength();
/*     */     
/* 319 */     if (index == this.segmentIndexes.length - 1) {
/* 320 */       end = this.pathLength;
/*     */     } else {
/* 322 */       seg = this.segments.get(this.segmentIndexes[index + 1]);
/* 323 */       end = seg.getLength();
/*     */     } 
/* 325 */     return angleAtLength(start + (end - start) * proportion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float angleAtLength(float length) {
/* 335 */     int upperIndex = findUpperIndex(length);
/* 336 */     if (upperIndex == -1)
/*     */     {
/* 338 */       return 0.0F;
/*     */     }
/*     */     
/* 341 */     PathSegment upper = this.segments.get(upperIndex);
/*     */     
/* 343 */     if (upperIndex == 0)
/*     */     {
/*     */       
/* 346 */       upperIndex = 1;
/*     */     }
/*     */     
/* 349 */     PathSegment lower = this.segments.get(upperIndex - 1);
/*     */ 
/*     */     
/* 352 */     return (float)Math.atan2((upper.getY() - lower.getY()), (upper.getX() - lower.getX()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int findUpperIndex(float length) {
/* 363 */     if (!this.initialised) {
/* 364 */       initialise();
/*     */     }
/*     */     
/* 367 */     if (length < 0.0F || length > this.pathLength)
/*     */     {
/* 369 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 373 */     int lb = 0;
/* 374 */     int ub = this.segments.size() - 1;
/* 375 */     while (lb != ub) {
/* 376 */       int curr = lb + ub >> 1;
/* 377 */       PathSegment ps = this.segments.get(curr);
/* 378 */       if (ps.getLength() >= length) {
/* 379 */         ub = curr; continue;
/*     */       } 
/* 381 */       lb = curr + 1;
/*     */     } 
/*     */     
/*     */     while (true) {
/* 385 */       PathSegment ps = this.segments.get(ub);
/* 386 */       if (ps.getSegType() != 0 || ub == this.segments.size() - 1) {
/*     */         break;
/*     */       }
/*     */       
/* 390 */       ub++;
/*     */     } 
/*     */     
/* 393 */     int upperIndex = -1;
/* 394 */     int currentIndex = 0;
/* 395 */     int numSegments = this.segments.size();
/* 396 */     while (upperIndex <= 0 && currentIndex < numSegments) {
/* 397 */       PathSegment ps = this.segments.get(currentIndex);
/* 398 */       if (ps.getLength() >= length && ps.getSegType() != 0)
/*     */       {
/* 400 */         upperIndex = currentIndex;
/*     */       }
/* 402 */       currentIndex++;
/*     */     } 
/* 404 */     return upperIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SingleSegmentPathIterator
/*     */     implements PathIterator
/*     */   {
/*     */     protected PathIterator it;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean done;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean moveDone;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected double x;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected double y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setPathIterator(PathIterator it, double x, double y) {
/* 443 */       this.it = it;
/* 444 */       this.x = x;
/* 445 */       this.y = y;
/* 446 */       this.done = false;
/* 447 */       this.moveDone = false;
/*     */     }
/*     */     
/*     */     public int currentSegment(double[] coords) {
/* 451 */       int type = this.it.currentSegment(coords);
/* 452 */       if (!this.moveDone) {
/* 453 */         coords[0] = this.x;
/* 454 */         coords[1] = this.y;
/* 455 */         return 0;
/*     */       } 
/* 457 */       return type;
/*     */     }
/*     */     
/*     */     public int currentSegment(float[] coords) {
/* 461 */       int type = this.it.currentSegment(coords);
/* 462 */       if (!this.moveDone) {
/* 463 */         coords[0] = (float)this.x;
/* 464 */         coords[1] = (float)this.y;
/* 465 */         return 0;
/*     */       } 
/* 467 */       return type;
/*     */     }
/*     */     
/*     */     public int getWindingRule() {
/* 471 */       return this.it.getWindingRule();
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 475 */       return (this.done || this.it.isDone());
/*     */     }
/*     */     
/*     */     public void next() {
/* 479 */       if (!this.done) {
/* 480 */         if (!this.moveDone) {
/* 481 */           this.moveDone = true;
/*     */         } else {
/* 483 */           this.it.next();
/* 484 */           this.done = true;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class PathSegment
/*     */   {
/*     */     protected final int segType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected float x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected float y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected float length;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     PathSegment(int segType, float x, float y, float len, int idx) {
/* 534 */       this.segType = segType;
/* 535 */       this.x = x;
/* 536 */       this.y = y;
/* 537 */       this.length = len;
/* 538 */       this.index = idx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSegType() {
/* 545 */       return this.segType;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getX() {
/* 552 */       return this.x;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setX(float v) {
/* 559 */       this.x = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getY() {
/* 566 */       return this.y;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setY(float v) {
/* 573 */       this.y = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getLength() {
/* 580 */       return this.length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLength(float v) {
/* 587 */       this.length = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getIndex() {
/* 594 */       return this.index;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setIndex(int v) {
/* 601 */       this.index = v;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/PathLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */