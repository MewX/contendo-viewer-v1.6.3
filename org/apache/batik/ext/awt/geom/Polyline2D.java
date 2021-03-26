/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Polyline2D
/*     */   implements Shape, Serializable, Cloneable
/*     */ {
/*     */   private static final float ASSUME_ZERO = 0.001F;
/*     */   public int npoints;
/*     */   public float[] xpoints;
/*     */   public float[] ypoints;
/*     */   protected Rectangle2D bounds;
/*     */   private GeneralPath path;
/*     */   private GeneralPath closedPath;
/*     */   
/*     */   public Polyline2D() {
/*  77 */     this.xpoints = new float[4];
/*  78 */     this.ypoints = new float[4];
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
/*     */   public Polyline2D(float[] xpoints, float[] ypoints, int npoints) {
/*  97 */     if (npoints > xpoints.length || npoints > ypoints.length) {
/*  98 */       throw new IndexOutOfBoundsException("npoints > xpoints.length || npoints > ypoints.length");
/*     */     }
/* 100 */     this.npoints = npoints;
/* 101 */     this.xpoints = new float[npoints + 1];
/* 102 */     this.ypoints = new float[npoints + 1];
/* 103 */     System.arraycopy(xpoints, 0, this.xpoints, 0, npoints);
/* 104 */     System.arraycopy(ypoints, 0, this.ypoints, 0, npoints);
/* 105 */     calculatePath();
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
/*     */   public Polyline2D(int[] xpoints, int[] ypoints, int npoints) {
/* 123 */     if (npoints > xpoints.length || npoints > ypoints.length) {
/* 124 */       throw new IndexOutOfBoundsException("npoints > xpoints.length || npoints > ypoints.length");
/*     */     }
/* 126 */     this.npoints = npoints;
/* 127 */     this.xpoints = new float[npoints];
/* 128 */     this.ypoints = new float[npoints];
/* 129 */     for (int i = 0; i < npoints; i++) {
/* 130 */       this.xpoints[i] = xpoints[i];
/* 131 */       this.ypoints[i] = ypoints[i];
/*     */     } 
/* 133 */     calculatePath();
/*     */   }
/*     */   
/*     */   public Polyline2D(Line2D line) {
/* 137 */     this.npoints = 2;
/* 138 */     this.xpoints = new float[2];
/* 139 */     this.ypoints = new float[2];
/* 140 */     this.xpoints[0] = (float)line.getX1();
/* 141 */     this.xpoints[1] = (float)line.getX2();
/* 142 */     this.ypoints[0] = (float)line.getY1();
/* 143 */     this.ypoints[1] = (float)line.getY2();
/* 144 */     calculatePath();
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
/*     */   public void reset() {
/* 163 */     this.npoints = 0;
/* 164 */     this.bounds = null;
/* 165 */     this.path = new GeneralPath();
/* 166 */     this.closedPath = null;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 170 */     Polyline2D pol = new Polyline2D();
/* 171 */     for (int i = 0; i < this.npoints; i++) {
/* 172 */       pol.addPoint(this.xpoints[i], this.ypoints[i]);
/*     */     }
/* 174 */     return pol;
/*     */   }
/*     */   
/*     */   private void calculatePath() {
/* 178 */     this.path = new GeneralPath();
/* 179 */     this.path.moveTo(this.xpoints[0], this.ypoints[0]);
/* 180 */     for (int i = 1; i < this.npoints; i++) {
/* 181 */       this.path.lineTo(this.xpoints[i], this.ypoints[i]);
/*     */     }
/* 183 */     this.bounds = this.path.getBounds2D();
/* 184 */     this.closedPath = null;
/*     */   }
/*     */   
/*     */   private void updatePath(float x, float y) {
/* 188 */     this.closedPath = null;
/* 189 */     if (this.path == null) {
/* 190 */       this.path = new GeneralPath(0);
/* 191 */       this.path.moveTo(x, y);
/* 192 */       this.bounds = new Rectangle2D.Float(x, y, 0.0F, 0.0F);
/*     */     } else {
/* 194 */       this.path.lineTo(x, y);
/* 195 */       float _xmax = (float)this.bounds.getMaxX();
/* 196 */       float _ymax = (float)this.bounds.getMaxY();
/* 197 */       float _xmin = (float)this.bounds.getMinX();
/* 198 */       float _ymin = (float)this.bounds.getMinY();
/* 199 */       if (x < _xmin) { _xmin = x; }
/* 200 */       else if (x > _xmax) { _xmax = x; }
/* 201 */        if (y < _ymin) { _ymin = y; }
/* 202 */       else if (y > _ymax) { _ymax = y; }
/* 203 */        this.bounds = new Rectangle2D.Float(_xmin, _ymin, _xmax - _xmin, _ymax - _ymin);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPoint(Point2D p) {
/* 208 */     addPoint((float)p.getX(), (float)p.getY());
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
/*     */   public void addPoint(float x, float y) {
/* 224 */     if (this.npoints == this.xpoints.length) {
/*     */ 
/*     */       
/* 227 */       float[] tmp = new float[this.npoints * 2];
/* 228 */       System.arraycopy(this.xpoints, 0, tmp, 0, this.npoints);
/* 229 */       this.xpoints = tmp;
/*     */       
/* 231 */       tmp = new float[this.npoints * 2];
/* 232 */       System.arraycopy(this.ypoints, 0, tmp, 0, this.npoints);
/* 233 */       this.ypoints = tmp;
/*     */     } 
/* 235 */     this.xpoints[this.npoints] = x;
/* 236 */     this.ypoints[this.npoints] = y;
/* 237 */     this.npoints++;
/* 238 */     updatePath(x, y);
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
/*     */   public Rectangle getBounds() {
/* 250 */     if (this.bounds == null) return null; 
/* 251 */     return this.bounds.getBounds();
/*     */   }
/*     */   
/*     */   private void updateComputingPath() {
/* 255 */     if (this.npoints >= 1 && 
/* 256 */       this.closedPath == null) {
/* 257 */       this.closedPath = (GeneralPath)this.path.clone();
/* 258 */       this.closedPath.closePath();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point p) {
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(int x, int y) {
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 299 */     return this.bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 309 */     return false;
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
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 328 */     if (this.npoints <= 0 || !this.bounds.intersects(x, y, w, h)) {
/* 329 */       return false;
/*     */     }
/* 331 */     updateComputingPath();
/* 332 */     return this.closedPath.intersects(x, y, w, h);
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
/*     */   public boolean intersects(Rectangle2D r) {
/* 345 */     return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 355 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 365 */     return false;
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
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 381 */     if (this.path == null) return null; 
/* 382 */     return this.path.getPathIterator(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Polygon2D getPolygon2D() {
/* 391 */     Polygon2D pol = new Polygon2D();
/* 392 */     for (int i = 0; i < this.npoints - 1; i++) {
/* 393 */       pol.addPoint(this.xpoints[i], this.ypoints[i]);
/*     */     }
/* 395 */     Point2D.Double p0 = new Point2D.Double(this.xpoints[0], this.ypoints[0]);
/*     */     
/* 397 */     Point2D.Double p1 = new Point2D.Double(this.xpoints[this.npoints - 1], this.ypoints[this.npoints - 1]);
/*     */ 
/*     */     
/* 400 */     if (p0.distance(p1) > 0.0010000000474974513D) {
/* 401 */       pol.addPoint(this.xpoints[this.npoints - 1], this.ypoints[this.npoints - 1]);
/*     */     }
/* 403 */     return pol;
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
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 425 */     return this.path.getPathIterator(at);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Polyline2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */