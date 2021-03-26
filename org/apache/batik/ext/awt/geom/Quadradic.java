/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
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
/*     */ 
/*     */ public class Quadradic
/*     */   extends AbstractSegment
/*     */ {
/*     */   public Point2D.Double p1;
/*     */   public Point2D.Double p2;
/*     */   public Point2D.Double p3;
/*     */   
/*     */   public Quadradic() {
/*  34 */     this.p1 = new Point2D.Double();
/*  35 */     this.p2 = new Point2D.Double();
/*  36 */     this.p3 = new Point2D.Double();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quadradic(double x1, double y1, double x2, double y2, double x3, double y3) {
/*  42 */     this.p1 = new Point2D.Double(x1, y1);
/*  43 */     this.p2 = new Point2D.Double(x2, y2);
/*  44 */     this.p3 = new Point2D.Double(x3, y3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quadradic(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
/*  50 */     this.p1 = p1;
/*  51 */     this.p2 = p2;
/*  52 */     this.p3 = p3;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  56 */     return new Quadradic(new Point2D.Double(this.p1.x, this.p1.y), new Point2D.Double(this.p2.x, this.p2.y), new Point2D.Double(this.p3.x, this.p3.y));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Segment reverse() {
/*  62 */     return new Quadradic(new Point2D.Double(this.p3.x, this.p3.y), new Point2D.Double(this.p2.x, this.p2.y), new Point2D.Double(this.p1.x, this.p1.y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getMinMax(double p1, double p2, double p3, double[] minMax) {
/*  69 */     if (p3 > p1) {
/*  70 */       minMax[0] = p1; minMax[1] = p3;
/*     */     } else {
/*  72 */       minMax[0] = p3; minMax[1] = p1;
/*     */     } 
/*     */     
/*  75 */     double a = p1 - 2.0D * p2 + p3;
/*  76 */     double b = p3 - p2;
/*     */     
/*  78 */     if (a == 0.0D)
/*     */       return; 
/*  80 */     double tv = b / a;
/*  81 */     if (tv <= 0.0D || tv >= 1.0D)
/*     */       return; 
/*  83 */     tv = ((p1 - 2.0D * p2 + p3) * tv + 2.0D * (p2 - p1)) * tv + p1;
/*  84 */     if (tv < minMax[0]) { minMax[0] = tv; }
/*  85 */     else if (tv > minMax[1]) { minMax[1] = tv; }
/*     */   
/*     */   }
/*     */   public double minX() {
/*  89 */     double[] minMax = { 0.0D, 0.0D };
/*  90 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, minMax);
/*  91 */     return minMax[0];
/*     */   }
/*     */   public double maxX() {
/*  94 */     double[] minMax = { 0.0D, 0.0D };
/*  95 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, minMax);
/*  96 */     return minMax[1];
/*     */   }
/*     */   public double minY() {
/*  99 */     double[] minMax = { 0.0D, 0.0D };
/* 100 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, minMax);
/* 101 */     return minMax[0];
/*     */   }
/*     */   public double maxY() {
/* 104 */     double[] minMax = { 0.0D, 0.0D };
/* 105 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, minMax);
/* 106 */     return minMax[1];
/*     */   }
/*     */   public Rectangle2D getBounds2D() {
/* 109 */     double[] minMaxX = { 0.0D, 0.0D };
/* 110 */     getMinMax(this.p1.x, this.p2.x, this.p3.x, minMaxX);
/* 111 */     double[] minMaxY = { 0.0D, 0.0D };
/* 112 */     getMinMax(this.p1.y, this.p2.y, this.p3.y, minMaxY);
/*     */     
/* 114 */     return new Rectangle2D.Double(minMaxX[0], minMaxY[0], minMaxX[1] - minMaxX[0], minMaxY[1] - minMaxY[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int findRoots(double y, double[] roots) {
/* 120 */     double[] eqn = { this.p1.y - y, 2.0D * (this.p2.y - this.p1.y), this.p1.y - 2.0D * this.p2.y + this.p3.y };
/* 121 */     return QuadCurve2D.solveQuadratic(eqn, roots);
/*     */   }
/*     */ 
/*     */   
/*     */   public Point2D.Double evalDt(double t) {
/* 126 */     double x = 2.0D * (this.p1.x - 2.0D * this.p2.x + this.p3.x) * t + 2.0D * (this.p2.x - this.p1.x);
/* 127 */     double y = 2.0D * (this.p1.y - 2.0D * this.p2.y + this.p3.y) * t + 2.0D * (this.p2.y - this.p1.y);
/* 128 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   public Point2D.Double eval(double t) {
/* 131 */     double x = ((this.p1.x - 2.0D * this.p2.x + this.p3.x) * t + 2.0D * (this.p2.x - this.p1.x)) * t + this.p1.x;
/* 132 */     double y = ((this.p1.y - 2.0D * this.p2.y + this.p3.y) * t + 2.0D * (this.p2.y - this.p1.y)) * t + this.p1.y;
/* 133 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public Segment getSegment(double t0, double t1) {
/* 137 */     double dt = t1 - t0;
/* 138 */     Point2D.Double np1 = eval(t0);
/* 139 */     Point2D.Double dp1 = evalDt(t0);
/*     */     
/* 141 */     Point2D.Double np2 = new Point2D.Double(np1.x + 0.5D * dt * dp1.x, np1.y + 0.5D * dt * dp1.y);
/*     */ 
/*     */     
/* 144 */     Point2D.Double np3 = eval(t1);
/* 145 */     return new Quadradic(np1, np2, np3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Quadradic q0, Quadradic q1) {
/* 155 */     if (q0 == null && q1 == null)
/*     */       return; 
/* 157 */     double x = (this.p1.x - 2.0D * this.p2.x + this.p3.x) * 0.25D + this.p2.x - this.p1.x + this.p1.x;
/* 158 */     double y = (this.p1.y - 2.0D * this.p2.y + this.p3.y) * 0.25D + this.p2.y - this.p1.y + this.p1.y;
/*     */     
/* 160 */     double dx = (this.p1.x - 2.0D * this.p2.x + this.p3.x) * 0.25D + (this.p2.x - this.p1.x) * 0.5D;
/* 161 */     double dy = (this.p1.y - 2.0D * this.p2.y + this.p3.y) * 0.25D + (this.p2.y - this.p1.y) * 0.5D;
/*     */     
/* 163 */     if (q0 != null) {
/* 164 */       q0.p1.x = this.p1.x;
/* 165 */       q0.p1.y = this.p1.y;
/* 166 */       q0.p2.x = x - dx;
/* 167 */       q0.p2.y = y - dy;
/* 168 */       q0.p3.x = x;
/* 169 */       q0.p3.y = y;
/*     */     } 
/*     */     
/* 172 */     if (q1 != null) {
/* 173 */       q1.p1.x = x;
/* 174 */       q1.p1.y = y;
/* 175 */       q1.p2.x = x + dx;
/* 176 */       q1.p2.y = y + dy;
/* 177 */       q1.p3.x = this.p3.x;
/* 178 */       q1.p3.y = this.p3.y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(double t, Quadradic q0, Quadradic q1) {
/* 188 */     Point2D.Double np = eval(t);
/* 189 */     Point2D.Double npd = evalDt(t);
/*     */     
/* 191 */     if (q0 != null) {
/* 192 */       q0.p1.x = this.p1.x;
/* 193 */       q0.p1.y = this.p1.y;
/* 194 */       np.x -= npd.x * t * 0.5D;
/* 195 */       np.y -= npd.y * t * 0.5D;
/* 196 */       q0.p3.x = np.x;
/* 197 */       q0.p3.y = np.y;
/*     */     } 
/*     */     
/* 200 */     if (q1 != null) {
/* 201 */       q1.p1.x = np.x;
/* 202 */       q1.p1.y = np.y;
/* 203 */       np.x += npd.x * (1.0D - t) * 0.5D;
/* 204 */       np.y += npd.y * (1.0D - t) * 0.5D;
/* 205 */       q1.p3.x = this.p3.x;
/* 206 */       q1.p3.y = this.p3.y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Segment s0, Segment s1) {
/* 217 */     Quadradic q0 = null, q1 = null;
/* 218 */     if (s0 instanceof Quadradic) q0 = (Quadradic)s0; 
/* 219 */     if (s1 instanceof Quadradic) q1 = (Quadradic)s1; 
/* 220 */     subdivide(q0, q1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(double t, Segment s0, Segment s1) {
/* 230 */     Quadradic q0 = null, q1 = null;
/* 231 */     if (s0 instanceof Quadradic) q0 = (Quadradic)s0; 
/* 232 */     if (s1 instanceof Quadradic) q1 = (Quadradic)s1; 
/* 233 */     subdivide(t, q0, q1);
/*     */   }
/*     */   
/* 236 */   static int count = 0;
/*     */   
/*     */   protected double subLength(double leftLegLen, double rightLegLen, double maxErr) {
/* 239 */     count++;
/*     */     
/* 241 */     double dx = this.p3.x - this.p1.x;
/* 242 */     double dy = this.p3.y - this.p1.y;
/* 243 */     double cordLen = Math.sqrt(dx * dx + dy * dy);
/*     */     
/* 245 */     double hullLen = leftLegLen + rightLegLen;
/* 246 */     if (hullLen < maxErr) return (hullLen + cordLen) * 0.5D;
/*     */     
/* 248 */     double err = hullLen - cordLen;
/* 249 */     if (err < maxErr) {
/* 250 */       return (hullLen + cordLen) * 0.5D;
/*     */     }
/* 252 */     Quadradic q = new Quadradic();
/* 253 */     double x = (this.p1.x + 2.0D * this.p2.x + this.p3.x) * 0.25D;
/* 254 */     double y = (this.p1.y + 2.0D * this.p2.y + this.p3.y) * 0.25D;
/*     */     
/* 256 */     dx = 0.25D * dx;
/* 257 */     dy = 0.25D * dy;
/*     */     
/* 259 */     q.p1.x = this.p1.x;
/* 260 */     q.p1.y = this.p1.y;
/* 261 */     q.p2.x = x - dx;
/* 262 */     q.p2.y = y - dy;
/* 263 */     q.p3.x = x;
/* 264 */     q.p3.y = y;
/*     */     
/* 266 */     double midLen = 0.25D * cordLen;
/* 267 */     double len = q.subLength(leftLegLen * 0.5D, midLen, maxErr * 0.5D);
/*     */     
/* 269 */     q.p1.x = x;
/* 270 */     q.p1.y = y;
/* 271 */     q.p2.x = x + dx;
/* 272 */     q.p2.y = y + dy;
/* 273 */     q.p3.x = this.p3.x;
/* 274 */     q.p3.y = this.p3.y;
/*     */     
/* 276 */     len += q.subLength(midLen, rightLegLen * 0.5D, maxErr * 0.5D);
/* 277 */     return len;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 281 */     return getLength(1.0E-6D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLength(double maxErr) {
/* 286 */     double dx = this.p2.x - this.p1.x;
/* 287 */     double dy = this.p2.y - this.p1.y;
/* 288 */     double leftLegLen = Math.sqrt(dx * dx + dy * dy);
/* 289 */     dx = this.p3.x - this.p2.x;
/* 290 */     dy = this.p3.y - this.p2.y;
/* 291 */     double rightLegLen = Math.sqrt(dx * dx + dy * dy);
/*     */     
/* 293 */     double eps = maxErr * (leftLegLen + rightLegLen);
/*     */     
/* 295 */     return subLength(leftLegLen, rightLegLen, eps);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 299 */     return "M" + this.p1.x + ',' + this.p1.y + 'Q' + this.p2.x + ',' + this.p2.y + ' ' + this.p3.x + ',' + this.p3.y;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Quadradic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */