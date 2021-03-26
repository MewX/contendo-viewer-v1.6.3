/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShadedTriangle
/*     */ {
/*     */   protected final Point2D[] corner;
/*     */   protected final float[][] color;
/*     */   private final double area;
/*     */   private final int degree;
/*     */   private final Line line;
/*     */   private final double v0;
/*     */   private final double v1;
/*     */   private final double v2;
/*     */   
/*     */   ShadedTriangle(Point2D[] p, float[][] c) {
/*  60 */     this.corner = (Point2D[])p.clone();
/*  61 */     this.color = (float[][])c.clone();
/*  62 */     this.area = getArea(p[0], p[1], p[2]);
/*  63 */     this.degree = calcDeg(p);
/*     */     
/*  65 */     if (this.degree == 2) {
/*     */       
/*  67 */       if (overlaps(this.corner[1], this.corner[2]) && !overlaps(this.corner[0], this.corner[2]))
/*     */       {
/*     */         
/*  70 */         Point p0 = new Point((int)Math.round(this.corner[0].getX()), (int)Math.round(this.corner[0].getY()));
/*     */         
/*  72 */         Point p1 = new Point((int)Math.round(this.corner[2].getX()), (int)Math.round(this.corner[2].getY()));
/*  73 */         this.line = new Line(p0, p1, this.color[0], this.color[2]);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*  78 */         Point p0 = new Point((int)Math.round(this.corner[1].getX()), (int)Math.round(this.corner[1].getY()));
/*     */         
/*  80 */         Point p1 = new Point((int)Math.round(this.corner[2].getX()), (int)Math.round(this.corner[2].getY()));
/*  81 */         this.line = new Line(p0, p1, this.color[1], this.color[2]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  86 */       this.line = null;
/*     */     } 
/*     */     
/*  89 */     this.v0 = edgeEquationValue(p[0], p[1], p[2]);
/*  90 */     this.v1 = edgeEquationValue(p[1], p[2], p[0]);
/*  91 */     this.v2 = edgeEquationValue(p[2], p[0], p[1]);
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
/*     */   private int calcDeg(Point2D[] p) {
/* 103 */     Set<Point> set = new HashSet<Point>();
/* 104 */     for (Point2D itp : p) {
/*     */       
/* 106 */       Point np = new Point((int)Math.round(itp.getX() * 1000.0D), (int)Math.round(itp.getY() * 1000.0D));
/* 107 */       set.add(np);
/*     */     } 
/* 109 */     return set.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDeg() {
/* 114 */     return this.degree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getBoundary() {
/* 124 */     int[] boundary = new int[4];
/* 125 */     int x0 = (int)Math.round(this.corner[0].getX());
/* 126 */     int x1 = (int)Math.round(this.corner[1].getX());
/* 127 */     int x2 = (int)Math.round(this.corner[2].getX());
/* 128 */     int y0 = (int)Math.round(this.corner[0].getY());
/* 129 */     int y1 = (int)Math.round(this.corner[1].getY());
/* 130 */     int y2 = (int)Math.round(this.corner[2].getY());
/*     */     
/* 132 */     boundary[0] = Math.min(Math.min(x0, x1), x2);
/* 133 */     boundary[1] = Math.max(Math.max(x0, x1), x2);
/* 134 */     boundary[2] = Math.min(Math.min(y0, y1), y2);
/* 135 */     boundary[3] = Math.max(Math.max(y0, y1), y2);
/*     */     
/* 137 */     return boundary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Line getLine() {
/* 147 */     return this.line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 158 */     if (this.degree == 1)
/*     */     {
/* 160 */       return overlaps(this.corner[0], p) | overlaps(this.corner[1], p) | overlaps(this.corner[2], p);
/*     */     }
/* 162 */     if (this.degree == 2) {
/*     */       
/* 164 */       Point tp = new Point((int)Math.round(p.getX()), (int)Math.round(p.getY()));
/* 165 */       return this.line.linePoints.contains(tp);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     double pv0 = edgeEquationValue(p, this.corner[1], this.corner[2]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     if (pv0 * this.v0 < 0.0D)
/*     */     {
/* 179 */       return false;
/*     */     }
/* 181 */     double pv1 = edgeEquationValue(p, this.corner[2], this.corner[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (pv1 * this.v1 < 0.0D)
/*     */     {
/* 188 */       return false;
/*     */     }
/* 190 */     double pv2 = edgeEquationValue(p, this.corner[0], this.corner[1]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     return (pv2 * this.v2 >= 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean overlaps(Point2D p0, Point2D p1) {
/* 205 */     return (Math.abs(p0.getX() - p1.getX()) < 0.001D && Math.abs(p0.getY() - p1.getY()) < 0.001D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double edgeEquationValue(Point2D p, Point2D p1, Point2D p2) {
/* 215 */     return (p2.getY() - p1.getY()) * (p.getX() - p1.getX()) - (p2
/* 216 */       .getX() - p1.getX()) * (p.getY() - p1.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getArea(Point2D a, Point2D b, Point2D c) {
/* 222 */     return Math.abs((c.getX() - b.getX()) * (c.getY() - a.getY()) - (c
/* 223 */         .getX() - a.getX()) * (c.getY() - b.getY())) / 2.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] calcColor(Point2D p) {
/*     */     int i;
/*     */     Point tp;
/* 234 */     int numberOfColorComponents = (this.color[0]).length;
/* 235 */     float[] pCol = new float[numberOfColorComponents];
/*     */     
/* 237 */     switch (this.degree)
/*     */     
/*     */     { case 1:
/* 240 */         for (i = 0; i < numberOfColorComponents; i++)
/*     */         {
/*     */           
/* 243 */           pCol[i] = (this.color[0][i] + this.color[1][i] + this.color[2][i]) / 3.0F;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 261 */         return pCol;case 2: tp = new Point((int)Math.round(p.getX()), (int)Math.round(p.getY())); return this.line.calcColor(tp); }  float aw = (float)(getArea(p, this.corner[1], this.corner[2]) / this.area); float bw = (float)(getArea(p, this.corner[2], this.corner[0]) / this.area); float cw = (float)(getArea(p, this.corner[0], this.corner[1]) / this.area); for (int j = 0; j < numberOfColorComponents; j++) pCol[j] = this.color[0][j] * aw + this.color[1][j] * bw + this.color[2][j] * cw;  return pCol;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 267 */     return this.corner[0] + " " + this.corner[1] + " " + this.corner[2];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/ShadedTriangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */