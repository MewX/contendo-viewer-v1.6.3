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
/*     */ class TensorPatch
/*     */   extends Patch
/*     */ {
/*     */   protected TensorPatch(Point2D[] tcp, float[][] color) {
/*  37 */     super(tcp, color);
/*  38 */     this.controlPoints = reshapeControlPoints(tcp);
/*  39 */     this.level = calcLevel();
/*  40 */     this.listOfTriangles = getTriangles();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Point2D[][] reshapeControlPoints(Point2D[] tcp) {
/*  49 */     Point2D[][] square = new Point2D[4][4]; int i;
/*  50 */     for (i = 0; i <= 3; i++) {
/*     */       
/*  52 */       square[0][i] = tcp[i];
/*  53 */       square[3][i] = tcp[9 - i];
/*     */     } 
/*  55 */     for (i = 1; i <= 2; i++) {
/*     */       
/*  57 */       square[i][0] = tcp[12 - i];
/*  58 */       square[i][2] = tcp[12 + i];
/*  59 */       square[i][3] = tcp[3 + i];
/*     */     } 
/*  61 */     square[1][1] = tcp[12];
/*  62 */     square[2][1] = tcp[15];
/*  63 */     return square;
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
/*  74 */     Point2D[] ctlC1 = new Point2D[4];
/*  75 */     Point2D[] ctlC2 = new Point2D[4];
/*  76 */     for (int j = 0; j < 4; j++) {
/*     */       
/*  78 */       ctlC1[j] = this.controlPoints[j][0];
/*  79 */       ctlC2[j] = this.controlPoints[j][3];
/*     */     } 
/*     */     
/*  82 */     if (isEdgeALine(ctlC1) && isEdgeALine(ctlC2))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       if (!isOnSameSideCC(this.controlPoints[1][1]) && !isOnSameSideCC(this.controlPoints[1][2]) && 
/*  90 */         !isOnSameSideCC(this.controlPoints[2][1]) && !isOnSameSideCC(this.controlPoints[2][2])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  97 */         double lc1 = getLen(ctlC1[0], ctlC1[3]), lc2 = getLen(ctlC2[0], ctlC2[3]);
/*  98 */         if (lc1 <= 800.0D && lc2 <= 800.0D)
/*     */         {
/*     */ 
/*     */           
/* 102 */           if (lc1 > 400.0D || lc2 > 400.0D) {
/*     */             
/* 104 */             l[0] = 3;
/*     */           }
/* 106 */           else if (lc1 > 200.0D || lc2 > 200.0D) {
/*     */             
/* 108 */             l[0] = 2;
/*     */           }
/*     */           else {
/*     */             
/* 112 */             l[0] = 1;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 118 */     if (isEdgeALine(this.controlPoints[0]) && isEdgeALine(this.controlPoints[3]))
/*     */     {
/* 120 */       if (!isOnSameSideDD(this.controlPoints[1][1]) && !isOnSameSideDD(this.controlPoints[1][2]) && 
/* 121 */         !isOnSameSideDD(this.controlPoints[2][1]) && !isOnSameSideDD(this.controlPoints[2][2])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         double ld1 = getLen(this.controlPoints[0][0], this.controlPoints[0][3]);
/* 128 */         double ld2 = getLen(this.controlPoints[3][0], this.controlPoints[3][3]);
/* 129 */         if (ld1 <= 800.0D && ld2 <= 800.0D)
/*     */         {
/*     */ 
/*     */           
/* 133 */           if (ld1 > 400.0D || ld2 > 400.0D) {
/*     */             
/* 135 */             l[1] = 3;
/*     */           }
/* 137 */           else if (ld1 > 200.0D || ld2 > 200.0D) {
/*     */             
/* 139 */             l[1] = 2;
/*     */           }
/*     */           else {
/*     */             
/* 143 */             l[1] = 1;
/*     */           }  } 
/*     */       } 
/*     */     }
/* 147 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isOnSameSideCC(Point2D p) {
/* 154 */     double cc = edgeEquationValue(p, this.controlPoints[0][0], this.controlPoints[3][0]) * edgeEquationValue(p, this.controlPoints[0][3], this.controlPoints[3][3]);
/* 155 */     return (cc > 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isOnSameSideDD(Point2D p) {
/* 162 */     double dd = edgeEquationValue(p, this.controlPoints[0][0], this.controlPoints[0][3]) * edgeEquationValue(p, this.controlPoints[3][0], this.controlPoints[3][3]);
/* 163 */     return (dd > 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ShadedTriangle> getTriangles() {
/* 169 */     CoordinateColorPair[][] patchCC = getPatchCoordinatesColor();
/* 170 */     return getShadedTriangles(patchCC);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag1Edge() {
/* 176 */     Point2D[] implicitEdge = new Point2D[4];
/* 177 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 179 */       implicitEdge[i] = this.controlPoints[i][3];
/*     */     }
/* 181 */     return implicitEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag2Edge() {
/* 187 */     Point2D[] implicitEdge = new Point2D[4];
/* 188 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 190 */       implicitEdge[i] = this.controlPoints[3][3 - i];
/*     */     }
/* 192 */     return implicitEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Point2D[] getFlag3Edge() {
/* 198 */     Point2D[] implicitEdge = new Point2D[4];
/* 199 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 201 */       implicitEdge[i] = this.controlPoints[3 - i][0];
/*     */     }
/* 203 */     return implicitEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CoordinateColorPair[][] getPatchCoordinatesColor() {
/* 214 */     int numberOfColorComponents = (this.cornerColor[0]).length;
/* 215 */     double[][] bernsteinPolyU = getBernsteinPolynomials(this.level[0]);
/* 216 */     int szU = (bernsteinPolyU[0]).length;
/* 217 */     double[][] bernsteinPolyV = getBernsteinPolynomials(this.level[1]);
/* 218 */     int szV = (bernsteinPolyV[0]).length;
/* 219 */     CoordinateColorPair[][] patchCC = new CoordinateColorPair[szV][szU];
/*     */     
/* 221 */     double stepU = 1.0D / (szU - 1);
/* 222 */     double stepV = 1.0D / (szV - 1);
/* 223 */     double v = -stepV;
/* 224 */     for (int k = 0; k < szV; k++) {
/*     */ 
/*     */       
/* 227 */       v += stepV;
/* 228 */       double u = -stepU;
/* 229 */       for (int l = 0; l < szU; l++) {
/*     */         
/* 231 */         double tmpx = 0.0D;
/* 232 */         double tmpy = 0.0D;
/*     */         
/* 234 */         for (int i = 0; i < 4; i++) {
/*     */           
/* 236 */           for (int j = 0; j < 4; j++) {
/*     */             
/* 238 */             tmpx += this.controlPoints[i][j].getX() * bernsteinPolyU[i][l] * bernsteinPolyV[j][k];
/* 239 */             tmpy += this.controlPoints[i][j].getY() * bernsteinPolyU[i][l] * bernsteinPolyV[j][k];
/*     */           } 
/*     */         } 
/* 242 */         Point2D tmpC = new Point2D.Double(tmpx, tmpy);
/*     */         
/* 244 */         u += stepU;
/* 245 */         float[] paramSC = new float[numberOfColorComponents];
/* 246 */         for (int ci = 0; ci < numberOfColorComponents; ci++)
/*     */         {
/* 248 */           paramSC[ci] = (float)((1.0D - v) * ((1.0D - u) * this.cornerColor[0][ci] + u * this.cornerColor[3][ci]) + v * ((1.0D - u) * this.cornerColor[1][ci] + u * this.cornerColor[2][ci]));
/*     */         }
/*     */         
/* 251 */         patchCC[k][l] = new CoordinateColorPair(tmpC, paramSC);
/*     */       } 
/*     */     } 
/* 254 */     return patchCC;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double[][] getBernsteinPolynomials(int lvl) {
/* 260 */     int sz = (1 << lvl) + 1;
/* 261 */     double[][] poly = new double[4][sz];
/* 262 */     double step = 1.0D / (sz - 1);
/* 263 */     double t = -step;
/* 264 */     for (int i = 0; i < sz; i++) {
/*     */       
/* 266 */       t += step;
/* 267 */       poly[0][i] = (1.0D - t) * (1.0D - t) * (1.0D - t);
/* 268 */       poly[1][i] = 3.0D * t * (1.0D - t) * (1.0D - t);
/* 269 */       poly[2][i] = 3.0D * t * t * (1.0D - t);
/* 270 */       poly[3][i] = t * t * t;
/*     */     } 
/* 272 */     return poly;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/TensorPatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */