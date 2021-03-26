/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
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
/*     */ abstract class Patch
/*     */ {
/*     */   protected Point2D[][] controlPoints;
/*     */   protected float[][] cornerColor;
/*     */   protected int[] level;
/*     */   protected List<ShadedTriangle> listOfTriangles;
/*     */   
/*     */   Patch(Point2D[] ctl, float[][] color) {
/*  50 */     this.cornerColor = (float[][])color.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Point2D[] getFlag1Edge();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Point2D[] getFlag2Edge();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Point2D[] getFlag3Edge();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[][] getFlag1Color() {
/*  81 */     int numberOfColorComponents = (this.cornerColor[0]).length;
/*  82 */     float[][] implicitCornerColor = new float[2][numberOfColorComponents];
/*  83 */     for (int i = 0; i < numberOfColorComponents; i++) {
/*     */       
/*  85 */       implicitCornerColor[0][i] = this.cornerColor[1][i];
/*  86 */       implicitCornerColor[1][i] = this.cornerColor[2][i];
/*     */     } 
/*  88 */     return implicitCornerColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[][] getFlag2Color() {
/*  98 */     int numberOfColorComponents = (this.cornerColor[0]).length;
/*  99 */     float[][] implicitCornerColor = new float[2][numberOfColorComponents];
/* 100 */     for (int i = 0; i < numberOfColorComponents; i++) {
/*     */       
/* 102 */       implicitCornerColor[0][i] = this.cornerColor[2][i];
/* 103 */       implicitCornerColor[1][i] = this.cornerColor[3][i];
/*     */     } 
/* 105 */     return implicitCornerColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[][] getFlag3Color() {
/* 115 */     int numberOfColorComponents = (this.cornerColor[0]).length;
/* 116 */     float[][] implicitCornerColor = new float[2][numberOfColorComponents];
/* 117 */     for (int i = 0; i < numberOfColorComponents; i++) {
/*     */       
/* 119 */       implicitCornerColor[0][i] = this.cornerColor[3][i];
/* 120 */       implicitCornerColor[1][i] = this.cornerColor[0][i];
/*     */     } 
/* 122 */     return implicitCornerColor;
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
/*     */   protected double getLen(Point2D ps, Point2D pe) {
/* 134 */     double x = pe.getX() - ps.getX();
/* 135 */     double y = pe.getY() - ps.getY();
/* 136 */     return Math.sqrt(x * x + y * y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEdgeALine(Point2D[] ctl) {
/* 147 */     double ctl1 = Math.abs(edgeEquationValue(ctl[1], ctl[0], ctl[3]));
/* 148 */     double ctl2 = Math.abs(edgeEquationValue(ctl[2], ctl[0], ctl[3]));
/* 149 */     double x = Math.abs(ctl[0].getX() - ctl[3].getX());
/* 150 */     double y = Math.abs(ctl[0].getY() - ctl[3].getY());
/* 151 */     return ((ctl1 <= x && ctl2 <= x) || (ctl1 <= y && ctl2 <= y));
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
/*     */   protected double edgeEquationValue(Point2D p, Point2D p1, Point2D p2) {
/* 166 */     return (p2.getY() - p1.getY()) * (p.getX() - p1.getX()) - (p2.getX() - p1.getX()) * (p.getY() - p1.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<ShadedTriangle> getShadedTriangles(CoordinateColorPair[][] patchCC) {
/* 177 */     List<ShadedTriangle> list = new ArrayList<ShadedTriangle>();
/* 178 */     int szV = patchCC.length;
/* 179 */     int szU = (patchCC[0]).length;
/* 180 */     for (int i = 1; i < szV; i++) {
/*     */       
/* 182 */       for (int j = 1; j < szU; j++) {
/*     */         
/* 184 */         Point2D p0 = (patchCC[i - 1][j - 1]).coordinate, p1 = (patchCC[i - 1][j]).coordinate, p2 = (patchCC[i][j]).coordinate;
/* 185 */         Point2D p3 = (patchCC[i][j - 1]).coordinate;
/* 186 */         boolean ll = true;
/* 187 */         if (overlaps(p0, p1) || overlaps(p0, p3)) {
/*     */           
/* 189 */           ll = false;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 194 */           Point2D[] llCorner = { p0, p1, p3 };
/*     */ 
/*     */ 
/*     */           
/* 198 */           float[][] llColor = { (patchCC[i - 1][j - 1]).color, (patchCC[i - 1][j]).color, (patchCC[i][j - 1]).color };
/*     */ 
/*     */ 
/*     */           
/* 202 */           ShadedTriangle tmpll = new ShadedTriangle(llCorner, llColor);
/* 203 */           list.add(tmpll);
/*     */         } 
/* 205 */         if (!ll || (!overlaps(p2, p1) && !overlaps(p2, p3))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 211 */           Point2D[] urCorner = { p3, p1, p2 };
/*     */ 
/*     */ 
/*     */           
/* 215 */           float[][] urColor = { (patchCC[i][j - 1]).color, (patchCC[i - 1][j]).color, (patchCC[i][j]).color };
/*     */ 
/*     */ 
/*     */           
/* 219 */           ShadedTriangle tmpur = new ShadedTriangle(urCorner, urColor);
/* 220 */           list.add(tmpur);
/*     */         } 
/*     */       } 
/*     */     } 
/* 224 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean overlaps(Point2D p0, Point2D p1) {
/* 230 */     return (Math.abs(p0.getX() - p1.getX()) < 0.001D && Math.abs(p0.getY() - p1.getY()) < 0.001D);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Patch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */