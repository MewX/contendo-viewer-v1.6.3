/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.geom.Point2D;
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
/*     */ 
/*     */ public class Linear
/*     */   implements Segment
/*     */ {
/*     */   public Point2D.Double p1;
/*     */   public Point2D.Double p2;
/*     */   
/*     */   public Linear() {
/*  33 */     this.p1 = new Point2D.Double();
/*  34 */     this.p2 = new Point2D.Double();
/*     */   }
/*     */ 
/*     */   
/*     */   public Linear(double x1, double y1, double x2, double y2) {
/*  39 */     this.p1 = new Point2D.Double(x1, y1);
/*  40 */     this.p2 = new Point2D.Double(x2, y2);
/*     */   }
/*     */   
/*     */   public Linear(Point2D.Double p1, Point2D.Double p2) {
/*  44 */     this.p1 = p1;
/*  45 */     this.p2 = p2;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  49 */     return new Linear(new Point2D.Double(this.p1.x, this.p1.y), new Point2D.Double(this.p2.x, this.p2.y));
/*     */   }
/*     */ 
/*     */   
/*     */   public Segment reverse() {
/*  54 */     return new Linear(new Point2D.Double(this.p2.x, this.p2.y), new Point2D.Double(this.p1.x, this.p1.y));
/*     */   }
/*     */ 
/*     */   
/*     */   public double minX() {
/*  59 */     if (this.p1.x < this.p2.x) return this.p1.x; 
/*  60 */     return this.p2.x;
/*     */   }
/*     */   public double maxX() {
/*  63 */     if (this.p1.x > this.p2.x) return this.p1.x; 
/*  64 */     return this.p2.x;
/*     */   }
/*     */   public double minY() {
/*  67 */     if (this.p1.y < this.p2.y) return this.p1.y; 
/*  68 */     return this.p2.y;
/*     */   }
/*     */   
/*  71 */   public double maxY() { if (this.p1.y > this.p2.y) return this.p2.y; 
/*  72 */     return this.p1.y; } public Rectangle2D getBounds2D() { double x;
/*     */     double y;
/*     */     double w;
/*     */     double h;
/*  76 */     if (this.p1.x < this.p2.x) {
/*  77 */       x = this.p1.x; w = this.p2.x - this.p1.x;
/*     */     } else {
/*  79 */       x = this.p2.x; w = this.p1.x - this.p2.x;
/*     */     } 
/*  81 */     if (this.p1.y < this.p2.y) {
/*  82 */       y = this.p1.y; h = this.p2.y - this.p1.y;
/*     */     } else {
/*  84 */       y = this.p2.y; h = this.p1.y - this.p2.y;
/*     */     } 
/*     */     
/*  87 */     return new Rectangle2D.Double(x, y, w, h); }
/*     */ 
/*     */   
/*     */   public Point2D.Double evalDt(double t) {
/*  91 */     double x = this.p2.x - this.p1.x;
/*  92 */     double y = this.p2.y - this.p1.y;
/*  93 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   public Point2D.Double eval(double t) {
/*  96 */     double x = this.p1.x + t * (this.p2.x - this.p1.x);
/*  97 */     double y = this.p1.y + t * (this.p2.y - this.p1.y);
/*  98 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public Segment.SplitResults split(double y) {
/* 102 */     if (y == this.p1.y || y == this.p2.y) return null; 
/* 103 */     if (y <= this.p1.y && y <= this.p2.y) return null; 
/* 104 */     if (y >= this.p1.y && y >= this.p2.y) return null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     double t = (y - this.p1.y) / (this.p2.y - this.p1.y);
/*     */     
/* 111 */     Segment[] t0 = { getSegment(0.0D, t) };
/* 112 */     Segment[] t1 = { getSegment(t, 1.0D) };
/*     */     
/* 114 */     if (this.p2.y < y)
/* 115 */       return new Segment.SplitResults(t0, t1); 
/* 116 */     return new Segment.SplitResults(t1, t0);
/*     */   }
/*     */   
/*     */   public Segment getSegment(double t0, double t1) {
/* 120 */     Point2D.Double np1 = eval(t0);
/* 121 */     Point2D.Double np2 = eval(t1);
/* 122 */     return new Linear(np1, np2);
/*     */   }
/*     */   public Segment splitBefore(double t) {
/* 125 */     return new Linear(this.p1, eval(t));
/*     */   }
/*     */   public Segment splitAfter(double t) {
/* 128 */     return new Linear(eval(t), this.p2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Segment s0, Segment s1) {
/* 138 */     Linear l0 = null, l1 = null;
/* 139 */     if (s0 instanceof Linear) l0 = (Linear)s0; 
/* 140 */     if (s1 instanceof Linear) l1 = (Linear)s1; 
/* 141 */     subdivide(l0, l1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(double t, Segment s0, Segment s1) {
/* 150 */     Linear l0 = null, l1 = null;
/* 151 */     if (s0 instanceof Linear) l0 = (Linear)s0; 
/* 152 */     if (s1 instanceof Linear) l1 = (Linear)s1; 
/* 153 */     subdivide(t, l0, l1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subdivide(Linear l0, Linear l1) {
/* 163 */     if (l0 == null && l1 == null)
/*     */       return; 
/* 165 */     double x = (this.p1.x + this.p2.x) * 0.5D;
/* 166 */     double y = (this.p1.y + this.p2.y) * 0.5D;
/*     */     
/* 168 */     if (l0 != null) {
/* 169 */       l0.p1.x = this.p1.x;
/* 170 */       l0.p1.y = this.p1.y;
/* 171 */       l0.p2.x = x;
/* 172 */       l0.p2.y = y;
/*     */     } 
/* 174 */     if (l1 != null) {
/* 175 */       l1.p1.x = x;
/* 176 */       l1.p1.y = y;
/* 177 */       l1.p2.x = this.p2.x;
/* 178 */       l1.p2.y = this.p2.y;
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
/*     */   public void subdivide(double t, Linear l0, Linear l1) {
/* 190 */     if (l0 == null && l1 == null)
/*     */       return; 
/* 192 */     double x = this.p1.x + t * (this.p2.x - this.p1.x);
/* 193 */     double y = this.p1.y + t * (this.p2.y - this.p1.y);
/*     */     
/* 195 */     if (l0 != null) {
/* 196 */       l0.p1.x = this.p1.x;
/* 197 */       l0.p1.y = this.p1.y;
/* 198 */       l0.p2.x = x;
/* 199 */       l0.p2.y = y;
/*     */     } 
/* 201 */     if (l1 != null) {
/* 202 */       l1.p1.x = x;
/* 203 */       l1.p1.y = y;
/* 204 */       l1.p2.x = this.p2.x;
/* 205 */       l1.p2.y = this.p2.y;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 210 */     double dx = this.p2.x - this.p1.x;
/* 211 */     double dy = this.p2.y - this.p1.y;
/* 212 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   public double getLength(double maxErr) {
/* 215 */     return getLength();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 219 */     return "M" + this.p1.x + ',' + this.p1.y + 'L' + this.p2.x + ',' + this.p2.y;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Linear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */