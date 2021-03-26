/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.QuadCurve2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cubic
/*     */   extends AbstractSegment
/*     */ {
/*     */   public Point2D.Double p1;
/*     */   public Point2D.Double p2;
/*     */   public Point2D.Double p3;
/*     */   public Point2D.Double p4;
/*     */   
/*     */   public Cubic() {
/*  35 */     this.p1 = new Point2D.Double();
/*  36 */     this.p2 = new Point2D.Double();
/*  37 */     this.p3 = new Point2D.Double();
/*  38 */     this.p4 = new Point2D.Double();
/*     */   }
/*     */   
/*     */   public Cubic(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
/*  42 */     this.p1 = new Point2D.Double(x1, y1);
/*  43 */     this.p2 = new Point2D.Double(x2, y2);
/*  44 */     this.p3 = new Point2D.Double(x3, y3);
/*  45 */     this.p4 = new Point2D.Double(x4, y4);
/*     */   }
/*     */ 
/*     */   
/*     */   public Cubic(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4) {
/*  50 */     this.p1 = p1;
/*  51 */     this.p2 = p2;
/*  52 */     this.p3 = p3;
/*  53 */     this.p4 = p4;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  57 */     return new Cubic(new Point2D.Double(this.p1.x, this.p1.y), new Point2D.Double(this.p2.x, this.p2.y), new Point2D.Double(this.p3.x, this.p3.y), new Point2D.Double(this.p4.x, this.p4.y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Segment reverse() {
/*  64 */     return new Cubic(new Point2D.Double(this.p4.x, this.p4.y), new Point2D.Double(this.p3.x, this.p3.y), new Point2D.Double(this.p2.x, this.p2.y), new Point2D.Double(this.p1.x, this.p1.y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getMinMax(double p1, double p2, double p3, double p4, double[] minMax) {
/*  73 */     if (p4 > p1) {
/*  74 */       minMax[0] = p1; minMax[1] = p4;
/*     */     } else {
/*  76 */       minMax[0] = p4; minMax[1] = p1;
/*     */     } 
/*     */     
/*  79 */     double c0 = 3.0D * (p2 - p1);
/*  80 */     double c1 = 6.0D * (p3 - p2);
/*  81 */     double c2 = 3.0D * (p4 - p3);
/*  82 */     double[] eqn = { c0, c1 - 2.0D * c0, c2 - c1 + c0 };
/*  83 */     int roots = QuadCurve2D.solveQuadratic(eqn);
/*  84 */     for (int r = 0; r < roots; r++) {
/*  85 */       double tv = eqn[r];
/*  86 */       if (tv > 0.0D && tv < 1.0D) {
/*  87 */         tv = (1.0D - tv) * (1.0D - tv) * (1.0D - tv) * p1 + 3.0D * tv * (1.0D - tv) * (1.0D - tv) * p2 + 3.0D * tv * tv * (1.0D - tv) * p3 + tv * tv * tv * p4;
/*     */ 
/*     */ 
/*     */         
/*  91 */         if (tv < minMax[0]) { minMax[0] = tv; }
/*  92 */         else if (tv > minMax[1]) { minMax[1] = tv; }
/*     */       
/*     */       } 
/*     */     }  } public double minX() {
/*  96 */     double[] minMax = { 0.0D, 0.0D };
/*  97 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, this.p4.x, minMax);
/*  98 */     return minMax[0];
/*     */   }
/*     */   public double maxX() {
/* 101 */     double[] minMax = { 0.0D, 0.0D };
/* 102 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, this.p4.x, minMax);
/* 103 */     return minMax[1];
/*     */   }
/*     */   public double minY() {
/* 106 */     double[] minMax = { 0.0D, 0.0D };
/* 107 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, this.p4.y, minMax);
/* 108 */     return minMax[0];
/*     */   }
/*     */   public double maxY() {
/* 111 */     double[] minMax = { 0.0D, 0.0D };
/* 112 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, this.p4.y, minMax);
/* 113 */     return minMax[1];
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 117 */     double[] minMaxX = { 0.0D, 0.0D };
/* 118 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, this.p4.x, minMaxX);
/* 119 */     double[] minMaxY = { 0.0D, 0.0D };
/* 120 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, this.p4.y, minMaxY);
/*     */     
/* 122 */     return new Rectangle2D.Double(minMaxX[0], minMaxY[0], minMaxX[1] - minMaxX[0], minMaxY[1] - minMaxY[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int findRoots(double y, double[] roots) {
/* 128 */     double[] eqn = { this.p1.y - y, 3.0D * (this.p2.y - this.p1.y), 3.0D * (this.p1.y - 2.0D * this.p2.y + this.p3.y), 3.0D * this.p2.y - this.p1.y + this.p4.y - 3.0D * this.p3.y };
/*     */     
/* 130 */     return CubicCurve2D.solveCubic(eqn, roots);
/*     */   }
/*     */ 
/*     */   
/*     */   public Point2D.Double evalDt(double t) {
/* 135 */     double x = 3.0D * ((this.p2.x - this.p1.x) * (1.0D - t) * (1.0D - t) + 2.0D * (this.p3.x - this.p2.x) * (1.0D - t) * t + (this.p4.x - this.p3.x) * t * t);
/*     */ 
/*     */     
/* 138 */     double y = 3.0D * ((this.p2.y - this.p1.y) * (1.0D - t) * (1.0D - t) + 2.0D * (this.p3.y - this.p2.y) * (1.0D - t) * t + (this.p4.y - this.p3.y) * t * t);
/*     */ 
/*     */     
/* 141 */     return new Point2D.Double(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public Point2D.Double eval(double t) {
/* 146 */     double x = (1.0D - t) * (1.0D - t) * (1.0D - t) * this.p1.x + 3.0D * (t * (1.0D - t) * (1.0D - t) * this.p2.x + t * t * (1.0D - t) * this.p3.x) + t * t * t * this.p4.x;
/*     */ 
/*     */ 
/*     */     
/* 150 */     double y = (1.0D - t) * (1.0D - t) * (1.0D - t) * this.p1.y + 3.0D * (t * (1.0D - t) * (1.0D - t) * this.p2.y + t * t * (1.0D - t) * this.p3.y) + t * t * t * this.p4.y;
/*     */ 
/*     */ 
/*     */     
/* 154 */     return new Point2D.Double(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Segment s0, Segment s1) {
/* 164 */     Cubic c0 = null, c1 = null;
/* 165 */     if (s0 instanceof Cubic) c0 = (Cubic)s0; 
/* 166 */     if (s1 instanceof Cubic) c1 = (Cubic)s1; 
/* 167 */     subdivide(c0, c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(double t, Segment s0, Segment s1) {
/* 176 */     Cubic c0 = null, c1 = null;
/* 177 */     if (s0 instanceof Cubic) c0 = (Cubic)s0; 
/* 178 */     if (s1 instanceof Cubic) c1 = (Cubic)s1; 
/* 179 */     subdivide(t, c0, c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Cubic c0, Cubic c1) {
/* 189 */     if (c0 == null && c1 == null)
/*     */       return; 
/* 191 */     double npX = (this.p1.x + 3.0D * (this.p2.x + this.p3.x) + this.p4.x) * 0.125D;
/* 192 */     double npY = (this.p1.y + 3.0D * (this.p2.y + this.p3.y) + this.p4.y) * 0.125D;
/*     */     
/* 194 */     double npdx = (this.p2.x - this.p1.x + 2.0D * (this.p3.x - this.p2.x) + this.p4.x - this.p3.x) * 0.125D;
/* 195 */     double npdy = (this.p2.y - this.p1.y + 2.0D * (this.p3.y - this.p2.y) + this.p4.y - this.p3.y) * 0.125D;
/*     */     
/* 197 */     if (c0 != null) {
/* 198 */       c0.p1.x = this.p1.x;
/* 199 */       c0.p1.y = this.p1.y;
/* 200 */       c0.p2.x = (this.p2.x + this.p1.x) * 0.5D;
/* 201 */       c0.p2.y = (this.p2.y + this.p1.y) * 0.5D;
/*     */       
/* 203 */       c0.p3.x = npX - npdx;
/* 204 */       c0.p3.y = npY - npdy;
/* 205 */       c0.p4.x = npX;
/* 206 */       c0.p4.y = npY;
/*     */     } 
/*     */     
/* 209 */     if (c1 != null) {
/* 210 */       c1.p1.x = npX;
/* 211 */       c1.p1.y = npY;
/* 212 */       c1.p2.x = npX + npdx;
/* 213 */       c1.p2.y = npY + npdy;
/*     */       
/* 215 */       c1.p3.x = (this.p4.x + this.p3.x) * 0.5D;
/* 216 */       c1.p3.y = (this.p4.y + this.p3.y) * 0.5D;
/* 217 */       c1.p4.x = this.p4.x;
/* 218 */       c1.p4.y = this.p4.y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(double t, Cubic c0, Cubic c1) {
/* 228 */     if (c0 == null && c1 == null)
/*     */       return; 
/* 230 */     Point2D.Double np = eval(t);
/* 231 */     Point2D.Double npd = evalDt(t);
/*     */     
/* 233 */     if (c0 != null) {
/* 234 */       c0.p1.x = this.p1.x;
/* 235 */       c0.p1.y = this.p1.y;
/* 236 */       this.p1.x += (this.p2.x - this.p1.x) * t;
/* 237 */       this.p1.y += (this.p2.y - this.p1.y) * t;
/*     */       
/* 239 */       np.x -= npd.x * t / 3.0D;
/* 240 */       np.y -= npd.y * t / 3.0D;
/* 241 */       c0.p4.x = np.x;
/* 242 */       c0.p4.y = np.y;
/*     */     } 
/*     */     
/* 245 */     if (c1 != null) {
/* 246 */       c1.p1.x = np.x;
/* 247 */       c1.p1.y = np.y;
/* 248 */       np.x += npd.x * (1.0D - t) / 3.0D;
/* 249 */       np.y += npd.y * (1.0D - t) / 3.0D;
/*     */       
/* 251 */       this.p4.x += (this.p3.x - this.p4.x) * (1.0D - t);
/* 252 */       this.p4.y += (this.p3.y - this.p4.y) * (1.0D - t);
/* 253 */       c1.p4.x = this.p4.x;
/* 254 */       c1.p4.y = this.p4.y;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Segment getSegment(double t0, double t1) {
/* 259 */     double dt = t1 - t0;
/* 260 */     Point2D.Double np1 = eval(t0);
/* 261 */     Point2D.Double dp1 = evalDt(t0);
/* 262 */     Point2D.Double np2 = new Point2D.Double(np1.x + dt * dp1.x / 3.0D, np1.y + dt * dp1.y / 3.0D);
/*     */ 
/*     */     
/* 265 */     Point2D.Double np4 = eval(t1);
/* 266 */     Point2D.Double dp4 = evalDt(t1);
/*     */     
/* 268 */     Point2D.Double np3 = new Point2D.Double(np4.x - dt * dp4.x / 3.0D, np4.y - dt * dp4.y / 3.0D);
/*     */     
/* 270 */     return new Cubic(np1, np2, np3, np4);
/*     */   }
/*     */   
/* 273 */   private static int count = 0;
/*     */ 
/*     */   
/*     */   protected double subLength(double leftLegLen, double rightLegLen, double maxErr) {
/* 277 */     count++;
/*     */     
/* 279 */     double cldx = this.p3.x - this.p2.x;
/* 280 */     double cldy = this.p3.y - this.p2.y;
/* 281 */     double crossLegLen = Math.sqrt(cldx * cldx + cldy * cldy);
/*     */     
/* 283 */     double cdx = this.p4.x - this.p1.x;
/* 284 */     double cdy = this.p4.y - this.p1.y;
/* 285 */     double cordLen = Math.sqrt(cdx * cdx + cdy * cdy);
/*     */     
/* 287 */     double hullLen = leftLegLen + rightLegLen + crossLegLen;
/* 288 */     if (hullLen < maxErr) return (hullLen + cordLen) / 2.0D;
/*     */     
/* 290 */     double err = hullLen - cordLen;
/* 291 */     if (err < maxErr) {
/* 292 */       return (hullLen + cordLen) / 2.0D;
/*     */     }
/* 294 */     Cubic c = new Cubic();
/* 295 */     double npX = (this.p1.x + 3.0D * (this.p2.x + this.p3.x) + this.p4.x) * 0.125D;
/* 296 */     double npY = (this.p1.y + 3.0D * (this.p2.y + this.p3.y) + this.p4.y) * 0.125D;
/*     */     
/* 298 */     double npdx = (cldx + cdx) * 0.125D;
/* 299 */     double npdy = (cldy + cdy) * 0.125D;
/*     */     
/* 301 */     c.p1.x = this.p1.x;
/* 302 */     c.p1.y = this.p1.y;
/* 303 */     c.p2.x = (this.p2.x + this.p1.x) * 0.5D;
/* 304 */     c.p2.y = (this.p2.y + this.p1.y) * 0.5D;
/*     */     
/* 306 */     c.p3.x = npX - npdx;
/* 307 */     c.p3.y = npY - npdy;
/* 308 */     c.p4.x = npX;
/* 309 */     c.p4.y = npY;
/*     */     
/* 311 */     double midLen = Math.sqrt(npdx * npdx + npdy * npdy);
/* 312 */     double len = c.subLength(leftLegLen / 2.0D, midLen, maxErr / 2.0D);
/*     */     
/* 314 */     c.p1.x = npX;
/* 315 */     c.p1.y = npY;
/* 316 */     c.p2.x = npX + npdx;
/* 317 */     c.p2.y = npY + npdy;
/*     */     
/* 319 */     c.p3.x = (this.p4.x + this.p3.x) * 0.5D;
/* 320 */     c.p3.y = (this.p4.y + this.p3.y) * 0.5D;
/* 321 */     c.p4.x = this.p4.x;
/* 322 */     c.p4.y = this.p4.y;
/*     */     
/* 324 */     len += c.subLength(midLen, rightLegLen / 2.0D, maxErr / 2.0D);
/* 325 */     return len;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 329 */     return getLength(1.0E-6D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLength(double maxErr) {
/* 334 */     double dx = this.p2.x - this.p1.x;
/* 335 */     double dy = this.p2.y - this.p1.y;
/* 336 */     double leftLegLen = Math.sqrt(dx * dx + dy * dy);
/* 337 */     dx = this.p4.x - this.p3.x;
/* 338 */     dy = this.p4.y - this.p3.y;
/* 339 */     double rightLegLen = Math.sqrt(dx * dx + dy * dy);
/* 340 */     dx = this.p3.x - this.p2.x;
/* 341 */     dy = this.p3.y - this.p2.y;
/* 342 */     double crossLegLen = Math.sqrt(dx * dx + dy * dy);
/*     */     
/* 344 */     double eps = maxErr * (leftLegLen + rightLegLen + crossLegLen);
/*     */     
/* 346 */     return subLength(leftLegLen, rightLegLen, eps);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 350 */     return "M" + this.p1.x + ',' + this.p1.y + 'C' + this.p2.x + ',' + this.p2.y + ' ' + this.p3.x + ',' + this.p3.y + ' ' + this.p4.x + ',' + this.p4.y;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Cubic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */