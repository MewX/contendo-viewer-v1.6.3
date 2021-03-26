/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.geom.Point2D;
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
/*     */ class CoonsPatch
/*     */   extends Patch
/*     */ {
/*     */   protected CoonsPatch(Point2D[] points, float[][] color) {
/*  37 */     super(points, color);
/*  38 */     this.controlPoints = reshapeControlPoints(points);
/*  39 */     this.level = calcLevel();
/*  40 */     this.listOfTriangles = getTriangles();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Point2D[][] reshapeControlPoints(Point2D[] points) {
/*  46 */     Point2D[][] fourRows = new Point2D[4][4];
/*  47 */     (new Point2D[4])[0] = points[0]; (new Point2D[4])[1] = points[1]; (new Point2D[4])[2] = points[2]; (new Point2D[4])[3] = points[3]; fourRows[2] = new Point2D[4];
/*     */ 
/*     */ 
/*     */     
/*  51 */     (new Point2D[4])[0] = points[3]; (new Point2D[4])[1] = points[4]; (new Point2D[4])[2] = points[5]; (new Point2D[4])[3] = points[6]; fourRows[1] = new Point2D[4];
/*     */ 
/*     */ 
/*     */     
/*  55 */     (new Point2D[4])[0] = points[9]; (new Point2D[4])[1] = points[8]; (new Point2D[4])[2] = points[7]; (new Point2D[4])[3] = points[6]; fourRows[3] = new Point2D[4];
/*     */ 
/*     */ 
/*     */     
/*  59 */     (new Point2D[4])[0] = points[0]; (new Point2D[4])[1] = points[11]; (new Point2D[4])[2] = points[10]; (new Point2D[4])[3] = points[9]; fourRows[0] = new Point2D[4];
/*     */ 
/*     */ 
/*     */     
/*  63 */     return fourRows;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] calcLevel() {
/*  69 */     int[] l = { 4, 4 };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     if (isEdgeALine(this.controlPoints[0]) && isEdgeALine(this.controlPoints[1])) {
/*     */       
/*  76 */       double lc1 = getLen(this.controlPoints[0][0], this.controlPoints[0][3]);
/*  77 */       double lc2 = getLen(this.controlPoints[1][0], this.controlPoints[1][3]);
/*     */       
/*  79 */       if (lc1 <= 800.0D && lc2 <= 800.0D)
/*     */       {
/*     */ 
/*     */         
/*  83 */         if (lc1 > 400.0D || lc2 > 400.0D) {
/*     */           
/*  85 */           l[0] = 3;
/*     */         }
/*  87 */         else if (lc1 > 200.0D || lc2 > 200.0D) {
/*     */           
/*  89 */           l[0] = 2;
/*     */         }
/*     */         else {
/*     */           
/*  93 */           l[0] = 1;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  98 */     if (isEdgeALine(this.controlPoints[2]) && isEdgeALine(this.controlPoints[3])) {
/*     */       
/* 100 */       double ld1 = getLen(this.controlPoints[2][0], this.controlPoints[2][3]);
/* 101 */       double ld2 = getLen(this.controlPoints[3][0], this.controlPoints[3][3]);
/* 102 */       if (ld1 <= 800.0D && ld2 <= 800.0D)
/*     */       {
/*     */ 
/*     */         
/* 106 */         if (ld1 > 400.0D || ld2 > 400.0D) {
/*     */           
/* 108 */           l[1] = 3;
/*     */         }
/* 110 */         else if (ld1 > 200.0D || ld2 > 200.0D) {
/*     */           
/* 112 */           l[1] = 2;
/*     */         }
/*     */         else {
/*     */           
/* 116 */           l[1] = 1;
/*     */         }  } 
/*     */     } 
/* 119 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ShadedTriangle> getTriangles() {
/* 126 */     CubicBezierCurve eC1 = new CubicBezierCurve(this.controlPoints[0], this.level[0]);
/* 127 */     CubicBezierCurve eC2 = new CubicBezierCurve(this.controlPoints[1], this.level[0]);
/* 128 */     CubicBezierCurve eD1 = new CubicBezierCurve(this.controlPoints[2], this.level[1]);
/* 129 */     CubicBezierCurve eD2 = new CubicBezierCurve(this.controlPoints[3], this.level[1]);
/* 130 */     CoordinateColorPair[][] patchCC = getPatchCoordinatesColor(eC1, eC2, eD1, eD2);
/* 131 */     return getShadedTriangles(patchCC);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag1Edge() {
/* 137 */     return (Point2D[])this.controlPoints[1].clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag2Edge() {
/* 143 */     Point2D[] implicitEdge = new Point2D[4];
/* 144 */     implicitEdge[0] = this.controlPoints[3][3];
/* 145 */     implicitEdge[1] = this.controlPoints[3][2];
/* 146 */     implicitEdge[2] = this.controlPoints[3][1];
/* 147 */     implicitEdge[3] = this.controlPoints[3][0];
/* 148 */     return implicitEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag3Edge() {
/* 154 */     Point2D[] implicitEdge = new Point2D[4];
/* 155 */     implicitEdge[0] = this.controlPoints[0][3];
/* 156 */     implicitEdge[1] = this.controlPoints[0][2];
/* 157 */     implicitEdge[2] = this.controlPoints[0][1];
/* 158 */     implicitEdge[3] = this.controlPoints[0][0];
/* 159 */     return implicitEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CoordinateColorPair[][] getPatchCoordinatesColor(CubicBezierCurve c1, CubicBezierCurve c2, CubicBezierCurve d1, CubicBezierCurve d2) {
/* 169 */     Point2D[] curveC1 = c1.getCubicBezierCurve();
/* 170 */     Point2D[] curveC2 = c2.getCubicBezierCurve();
/* 171 */     Point2D[] curveD1 = d1.getCubicBezierCurve();
/* 172 */     Point2D[] curveD2 = d2.getCubicBezierCurve();
/*     */     
/* 174 */     int numberOfColorComponents = (this.cornerColor[0]).length;
/* 175 */     int szV = curveD1.length;
/* 176 */     int szU = curveC1.length;
/*     */     
/* 178 */     CoordinateColorPair[][] patchCC = new CoordinateColorPair[szV][szU];
/*     */     
/* 180 */     double stepV = 1.0D / (szV - 1);
/* 181 */     double stepU = 1.0D / (szU - 1);
/* 182 */     double v = -stepV;
/* 183 */     for (int i = 0; i < szV; i++) {
/*     */ 
/*     */       
/* 186 */       v += stepV;
/* 187 */       double u = -stepU;
/* 188 */       for (int j = 0; j < szU; j++) {
/*     */         
/* 190 */         u += stepU;
/* 191 */         double scx = (1.0D - v) * curveC1[j].getX() + v * curveC2[j].getX();
/* 192 */         double scy = (1.0D - v) * curveC1[j].getY() + v * curveC2[j].getY();
/* 193 */         double sdx = (1.0D - u) * curveD1[i].getX() + u * curveD2[i].getX();
/* 194 */         double sdy = (1.0D - u) * curveD1[i].getY() + u * curveD2[i].getY();
/*     */         
/* 196 */         double sbx = (1.0D - v) * ((1.0D - u) * this.controlPoints[0][0].getX() + u * this.controlPoints[0][3].getX()) + v * ((1.0D - u) * this.controlPoints[1][0].getX() + u * this.controlPoints[1][3].getX());
/*     */         
/* 198 */         double sby = (1.0D - v) * ((1.0D - u) * this.controlPoints[0][0].getY() + u * this.controlPoints[0][3].getY()) + v * ((1.0D - u) * this.controlPoints[1][0].getY() + u * this.controlPoints[1][3].getY());
/*     */         
/* 200 */         double sx = scx + sdx - sbx;
/* 201 */         double sy = scy + sdy - sby;
/*     */ 
/*     */         
/* 204 */         Point2D tmpC = new Point2D.Double(sx, sy);
/*     */         
/* 206 */         float[] paramSC = new float[numberOfColorComponents];
/* 207 */         for (int ci = 0; ci < numberOfColorComponents; ci++)
/*     */         {
/* 209 */           paramSC[ci] = (float)((1.0D - v) * ((1.0D - u) * this.cornerColor[0][ci] + u * this.cornerColor[3][ci]) + v * ((1.0D - u) * this.cornerColor[1][ci] + u * this.cornerColor[2][ci]));
/*     */         }
/*     */         
/* 212 */         patchCC[i][j] = new CoordinateColorPair(tmpC, paramSC);
/*     */       } 
/*     */     } 
/* 215 */     return patchCC;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/CoonsPatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */