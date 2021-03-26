/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Point;
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
/*     */ class Line
/*     */ {
/*     */   private final Point point0;
/*     */   private final Point point1;
/*     */   private final float[] color0;
/*     */   private final float[] color1;
/*     */   protected final Set<Point> linePoints;
/*     */   
/*     */   Line(Point p0, Point p1, float[] c0, float[] c1) {
/*  47 */     this.point0 = p0;
/*  48 */     this.point1 = p1;
/*  49 */     this.color0 = (float[])c0.clone();
/*  50 */     this.color1 = (float[])c1.clone();
/*  51 */     this.linePoints = calcLine(this.point0.x, this.point0.y, this.point1.x, this.point1.y);
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
/*     */   private Set<Point> calcLine(int x0, int y0, int x1, int y1) {
/*  68 */     Set<Point> points = new HashSet<Point>(3);
/*  69 */     int dx = Math.abs(x1 - x0);
/*  70 */     int dy = Math.abs(y1 - y0);
/*  71 */     int sx = (x0 < x1) ? 1 : -1;
/*  72 */     int sy = (y0 < y1) ? 1 : -1;
/*  73 */     int err = dx - dy;
/*     */     
/*     */     while (true) {
/*  76 */       points.add(new IntPoint(x0, y0));
/*  77 */       if (x0 == x1 && y0 == y1) {
/*     */         break;
/*     */       }
/*     */       
/*  81 */       int e2 = 2 * err;
/*  82 */       if (e2 > -dy) {
/*     */         
/*  84 */         err -= dy;
/*  85 */         x0 += sx;
/*     */       } 
/*  87 */       if (e2 < dx) {
/*     */         
/*  89 */         err += dx;
/*  90 */         y0 += sy;
/*     */       } 
/*     */     } 
/*  93 */     return points;
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
/*     */   protected float[] calcColor(Point p) {
/* 105 */     int numberOfColorComponents = this.color0.length;
/* 106 */     float[] pc = new float[numberOfColorComponents];
/* 107 */     if (this.point0.x == this.point1.x && this.point0.y == this.point1.y)
/*     */     {
/* 109 */       return this.color0;
/*     */     }
/* 111 */     if (this.point0.x == this.point1.x) {
/*     */       
/* 113 */       float l = (this.point1.y - this.point0.y);
/* 114 */       for (int i = 0; i < numberOfColorComponents; i++)
/*     */       {
/* 116 */         pc[i] = this.color0[i] * (this.point1.y - p.y) / l + this.color1[i] * (p.y - this.point0.y) / l;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 122 */       float l = (this.point1.x - this.point0.x);
/* 123 */       for (int i = 0; i < numberOfColorComponents; i++)
/*     */       {
/* 125 */         pc[i] = this.color0[i] * (this.point1.x - p.x) / l + this.color1[i] * (p.x - this.point0.x) / l;
/*     */       }
/*     */     } 
/*     */     
/* 129 */     return pc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */