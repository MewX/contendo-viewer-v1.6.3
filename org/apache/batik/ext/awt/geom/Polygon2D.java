/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
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
/*     */ public class Polygon2D
/*     */   implements Shape, Serializable, Cloneable
/*     */ {
/*     */   public int npoints;
/*     */   public float[] xpoints;
/*     */   public float[] ypoints;
/*     */   protected Rectangle2D bounds;
/*     */   private GeneralPath path;
/*     */   private GeneralPath closedPath;
/*     */   
/*     */   public Polygon2D() {
/*  74 */     this.xpoints = new float[4];
/*  75 */     this.ypoints = new float[4];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Polygon2D(Rectangle2D rec) {
/*  85 */     if (rec == null) {
/*  86 */       throw new IndexOutOfBoundsException("null Rectangle");
/*     */     }
/*  88 */     this.npoints = 4;
/*  89 */     this.xpoints = new float[4];
/*  90 */     this.ypoints = new float[4];
/*  91 */     this.xpoints[0] = (float)rec.getMinX();
/*  92 */     this.ypoints[0] = (float)rec.getMinY();
/*  93 */     this.xpoints[1] = (float)rec.getMaxX();
/*  94 */     this.ypoints[1] = (float)rec.getMinY();
/*  95 */     this.xpoints[2] = (float)rec.getMaxX();
/*  96 */     this.ypoints[2] = (float)rec.getMaxY();
/*  97 */     this.xpoints[3] = (float)rec.getMinX();
/*  98 */     this.ypoints[3] = (float)rec.getMaxY();
/*  99 */     calculatePath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Polygon2D(Polygon pol) {
/* 109 */     if (pol == null) {
/* 110 */       throw new IndexOutOfBoundsException("null Polygon");
/*     */     }
/* 112 */     this.npoints = pol.npoints;
/* 113 */     this.xpoints = new float[pol.npoints];
/* 114 */     this.ypoints = new float[pol.npoints];
/* 115 */     for (int i = 0; i < pol.npoints; i++) {
/* 116 */       this.xpoints[i] = pol.xpoints[i];
/* 117 */       this.ypoints[i] = pol.ypoints[i];
/*     */     } 
/* 119 */     calculatePath();
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
/*     */   public Polygon2D(float[] xpoints, float[] ypoints, int npoints) {
/* 137 */     if (npoints > xpoints.length || npoints > ypoints.length) {
/* 138 */       throw new IndexOutOfBoundsException("npoints > xpoints.length || npoints > ypoints.length");
/*     */     }
/* 140 */     this.npoints = npoints;
/* 141 */     this.xpoints = new float[npoints];
/* 142 */     this.ypoints = new float[npoints];
/* 143 */     System.arraycopy(xpoints, 0, this.xpoints, 0, npoints);
/* 144 */     System.arraycopy(ypoints, 0, this.ypoints, 0, npoints);
/* 145 */     calculatePath();
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
/*     */   public Polygon2D(int[] xpoints, int[] ypoints, int npoints) {
/* 163 */     if (npoints > xpoints.length || npoints > ypoints.length) {
/* 164 */       throw new IndexOutOfBoundsException("npoints > xpoints.length || npoints > ypoints.length");
/*     */     }
/* 166 */     this.npoints = npoints;
/* 167 */     this.xpoints = new float[npoints];
/* 168 */     this.ypoints = new float[npoints];
/* 169 */     for (int i = 0; i < npoints; i++) {
/* 170 */       this.xpoints[i] = xpoints[i];
/* 171 */       this.ypoints[i] = ypoints[i];
/*     */     } 
/* 173 */     calculatePath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 180 */     this.npoints = 0;
/* 181 */     this.bounds = null;
/* 182 */     this.path = new GeneralPath();
/* 183 */     this.closedPath = null;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 187 */     Polygon2D pol = new Polygon2D();
/* 188 */     for (int i = 0; i < this.npoints; i++) {
/* 189 */       pol.addPoint(this.xpoints[i], this.ypoints[i]);
/*     */     }
/* 191 */     return pol;
/*     */   }
/*     */   
/*     */   private void calculatePath() {
/* 195 */     this.path = new GeneralPath();
/* 196 */     this.path.moveTo(this.xpoints[0], this.ypoints[0]);
/* 197 */     for (int i = 1; i < this.npoints; i++) {
/* 198 */       this.path.lineTo(this.xpoints[i], this.ypoints[i]);
/*     */     }
/* 200 */     this.bounds = this.path.getBounds2D();
/* 201 */     this.closedPath = null;
/*     */   }
/*     */   
/*     */   private void updatePath(float x, float y) {
/* 205 */     this.closedPath = null;
/* 206 */     if (this.path == null) {
/* 207 */       this.path = new GeneralPath(0);
/* 208 */       this.path.moveTo(x, y);
/* 209 */       this.bounds = new Rectangle2D.Float(x, y, 0.0F, 0.0F);
/*     */     } else {
/* 211 */       this.path.lineTo(x, y);
/* 212 */       float _xmax = (float)this.bounds.getMaxX();
/* 213 */       float _ymax = (float)this.bounds.getMaxY();
/* 214 */       float _xmin = (float)this.bounds.getMinX();
/* 215 */       float _ymin = (float)this.bounds.getMinY();
/* 216 */       if (x < _xmin) { _xmin = x; }
/* 217 */       else if (x > _xmax) { _xmax = x; }
/* 218 */        if (y < _ymin) { _ymin = y; }
/* 219 */       else if (y > _ymax) { _ymax = y; }
/* 220 */        this.bounds = new Rectangle2D.Float(_xmin, _ymin, _xmax - _xmin, _ymax - _ymin);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Polyline2D getPolyline2D() {
/* 228 */     Polyline2D pol = new Polyline2D(this.xpoints, this.ypoints, this.npoints);
/*     */     
/* 230 */     pol.addPoint(this.xpoints[0], this.ypoints[0]);
/*     */     
/* 232 */     return pol;
/*     */   }
/*     */   
/*     */   public Polygon getPolygon() {
/* 236 */     int[] _xpoints = new int[this.npoints];
/* 237 */     int[] _ypoints = new int[this.npoints];
/* 238 */     for (int i = 0; i < this.npoints; i++) {
/* 239 */       _xpoints[i] = (int)this.xpoints[i];
/* 240 */       _ypoints[i] = (int)this.ypoints[i];
/*     */     } 
/*     */     
/* 243 */     return new Polygon(_xpoints, _ypoints, this.npoints);
/*     */   }
/*     */   
/*     */   public void addPoint(Point2D p) {
/* 247 */     addPoint((float)p.getX(), (float)p.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPoint(float x, float y) {
/* 256 */     if (this.npoints == this.xpoints.length) {
/*     */ 
/*     */       
/* 259 */       float[] tmp = new float[this.npoints * 2];
/* 260 */       System.arraycopy(this.xpoints, 0, tmp, 0, this.npoints);
/* 261 */       this.xpoints = tmp;
/*     */       
/* 263 */       tmp = new float[this.npoints * 2];
/* 264 */       System.arraycopy(this.ypoints, 0, tmp, 0, this.npoints);
/* 265 */       this.ypoints = tmp;
/*     */     } 
/* 267 */     this.xpoints[this.npoints] = x;
/* 268 */     this.ypoints[this.npoints] = y;
/* 269 */     this.npoints++;
/* 270 */     updatePath(x, y);
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
/*     */   public boolean contains(Point p) {
/* 282 */     return contains(p.x, p.y);
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
/*     */   public boolean contains(int x, int y) {
/* 296 */     return contains(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 305 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 309 */     if (this.bounds == null) return null; 
/* 310 */     return this.bounds.getBounds();
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
/*     */   public boolean contains(double x, double y) {
/* 323 */     if (this.npoints <= 2 || !this.bounds.contains(x, y)) {
/* 324 */       return false;
/*     */     }
/* 326 */     updateComputingPath();
/*     */     
/* 328 */     return this.closedPath.contains(x, y);
/*     */   }
/*     */   
/*     */   private void updateComputingPath() {
/* 332 */     if (this.npoints >= 1 && 
/* 333 */       this.closedPath == null) {
/* 334 */       this.closedPath = (GeneralPath)this.path.clone();
/* 335 */       this.closedPath.closePath();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 350 */     return contains(p.getX(), p.getY());
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
/* 369 */     if (this.npoints <= 0 || !this.bounds.intersects(x, y, w, h)) {
/* 370 */       return false;
/*     */     }
/* 372 */     updateComputingPath();
/* 373 */     return this.closedPath.intersects(x, y, w, h);
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
/* 386 */     return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
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
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 403 */     if (this.npoints <= 0 || !this.bounds.intersects(x, y, w, h)) {
/* 404 */       return false;
/*     */     }
/*     */     
/* 407 */     updateComputingPath();
/* 408 */     return this.closedPath.contains(x, y, w, h);
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
/*     */   public boolean contains(Rectangle2D r) {
/* 421 */     return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
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
/* 437 */     updateComputingPath();
/* 438 */     if (this.closedPath == null) return null; 
/* 439 */     return this.closedPath.getPathIterator(at);
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
/* 461 */     return getPathIterator(at);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Polygon2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */