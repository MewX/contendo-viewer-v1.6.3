/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSegment
/*     */   implements Segment
/*     */ {
/*     */   static final double eps = 3.552713678800501E-15D;
/*     */   static final double tol = 1.4210854715202004E-14D;
/*     */   
/*     */   protected abstract int findRoots(double paramDouble, double[] paramArrayOfdouble);
/*     */   
/*     */   public Segment.SplitResults split(double y) {
/*     */     Segment[] below, above;
/*  34 */     double[] roots = { 0.0D, 0.0D, 0.0D };
/*  35 */     int numSol = findRoots(y, roots);
/*  36 */     if (numSol == 0) return null;
/*     */     
/*  38 */     Arrays.sort(roots, 0, numSol);
/*  39 */     double[] segs = new double[numSol + 2];
/*  40 */     int numSegments = 0;
/*  41 */     segs[numSegments++] = 0.0D;
/*  42 */     for (int i = 0; i < numSol; i++) {
/*  43 */       double r = roots[i];
/*  44 */       if (r > 0.0D) {
/*  45 */         if (r >= 1.0D)
/*  46 */           break;  if (segs[numSegments - 1] != r)
/*  47 */           segs[numSegments++] = r; 
/*     */       } 
/*  49 */     }  segs[numSegments++] = 1.0D;
/*     */     
/*  51 */     if (numSegments == 2) return null;
/*     */ 
/*     */ 
/*     */     
/*  55 */     Segment[] parts = new Segment[numSegments];
/*  56 */     double pT = 0.0D;
/*  57 */     int pIdx = 0;
/*  58 */     boolean firstAbove = false, prevAbove = false;
/*  59 */     for (int j = 1; j < numSegments; j++) {
/*     */       
/*  61 */       parts[pIdx] = getSegment(segs[j - 1], segs[j]);
/*  62 */       Point2D.Double pt = parts[pIdx].eval(0.5D);
/*     */       
/*  64 */       if (pIdx == 0) {
/*  65 */         pIdx++;
/*  66 */         firstAbove = prevAbove = (pt.y < y);
/*     */       } else {
/*     */         
/*  69 */         boolean bool = (pt.y < y);
/*  70 */         if (prevAbove == bool) {
/*     */           
/*  72 */           parts[pIdx - 1] = getSegment(pT, segs[j]);
/*     */         } else {
/*  74 */           pIdx++;
/*  75 */           pT = segs[j - 1];
/*  76 */           prevAbove = bool;
/*     */         } 
/*     */       } 
/*  79 */     }  if (pIdx == 1) return null;
/*     */     
/*  81 */     if (firstAbove) {
/*  82 */       above = new Segment[(pIdx + 1) / 2];
/*  83 */       below = new Segment[pIdx / 2];
/*     */     } else {
/*  85 */       above = new Segment[pIdx / 2];
/*  86 */       below = new Segment[(pIdx + 1) / 2];
/*     */     } 
/*  88 */     int ai = 0, bi = 0;
/*  89 */     for (int k = 0; k < pIdx; k++) {
/*  90 */       if (firstAbove) { above[ai++] = parts[k]; }
/*  91 */       else { below[bi++] = parts[k]; }
/*  92 */        firstAbove = !firstAbove;
/*     */     } 
/*  94 */     return new Segment.SplitResults(below, above);
/*     */   }
/*     */   
/*     */   public Segment splitBefore(double t) {
/*  98 */     return getSegment(0.0D, t);
/*     */   }
/*     */   
/*     */   public Segment splitAfter(double t) {
/* 102 */     return getSegment(t, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int solveLine(double a, double b, double[] roots) {
/* 111 */     if (a == 0.0D) {
/* 112 */       if (b != 0.0D)
/*     */       {
/* 114 */         return 0;
/*     */       }
/* 116 */       roots[0] = 0.0D;
/* 117 */       return 1;
/*     */     } 
/*     */     
/* 120 */     roots[0] = -b / a;
/* 121 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int solveQuad(double a, double b, double c, double[] roots) {
/* 127 */     if (a == 0.0D)
/*     */     {
/* 129 */       return solveLine(b, c, roots);
/*     */     }
/*     */     
/* 132 */     double det = b * b - 4.0D * a * c;
/*     */ 
/*     */     
/* 135 */     if (Math.abs(det) <= 1.4210854715202004E-14D * b * b) {
/*     */       
/* 137 */       roots[0] = -b / 2.0D * a;
/* 138 */       return 1;
/*     */     } 
/*     */     
/* 141 */     if (det < 0.0D) {
/* 142 */       return 0;
/*     */     }
/*     */     
/* 145 */     det = Math.sqrt(det);
/* 146 */     double w = -(b + matchSign(det, b));
/* 147 */     roots[0] = 2.0D * c / w;
/* 148 */     roots[1] = w / 2.0D * a;
/* 149 */     return 2;
/*     */   }
/*     */   
/*     */   public static double matchSign(double a, double b) {
/* 153 */     if (b < 0.0D) return (a < 0.0D) ? a : -a; 
/* 154 */     return (a > 0.0D) ? a : -a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int solveCubic(double a3, double a2, double a1, double a0, double[] roots) {
/* 165 */     double r, dRoots[] = { 0.0D, 0.0D };
/* 166 */     int dCnt = solveQuad(3.0D * a3, 2.0D * a2, a1, dRoots);
/* 167 */     double[] yVals = { 0.0D, 0.0D, 0.0D, 0.0D };
/* 168 */     double[] tVals = { 0.0D, 0.0D, 0.0D, 0.0D };
/* 169 */     int yCnt = 0;
/* 170 */     yVals[yCnt] = a0;
/* 171 */     tVals[yCnt++] = 0.0D;
/*     */     
/* 173 */     switch (dCnt) {
/*     */       case 1:
/* 175 */         r = dRoots[0];
/* 176 */         if (r > 0.0D && r < 1.0D) {
/* 177 */           yVals[yCnt] = ((a3 * r + a2) * r + a1) * r + a0;
/* 178 */           tVals[yCnt++] = r;
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 182 */         if (dRoots[0] > dRoots[1]) {
/* 183 */           double t = dRoots[0];
/* 184 */           dRoots[0] = dRoots[1];
/* 185 */           dRoots[1] = t;
/*     */         } 
/* 187 */         r = dRoots[0];
/* 188 */         if (r > 0.0D && r < 1.0D) {
/* 189 */           yVals[yCnt] = ((a3 * r + a2) * r + a1) * r + a0;
/* 190 */           tVals[yCnt++] = r;
/*     */         } 
/* 192 */         r = dRoots[1];
/* 193 */         if (r > 0.0D && r < 1.0D) {
/* 194 */           yVals[yCnt] = ((a3 * r + a2) * r + a1) * r + a0;
/* 195 */           tVals[yCnt++] = r;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 200 */     yVals[yCnt] = a3 + a2 + a1 + a0;
/* 201 */     tVals[yCnt++] = 1.0D;
/*     */     
/* 203 */     int ret = 0;
/* 204 */     for (int i = 0; i < yCnt - 1; i++) {
/* 205 */       double y0 = yVals[i], t0 = tVals[i];
/* 206 */       double y1 = yVals[i + 1], t1 = tVals[i + 1];
/* 207 */       if ((y0 >= 0.0D || y1 >= 0.0D) && (
/* 208 */         y0 <= 0.0D || y1 <= 0.0D)) {
/*     */         
/* 210 */         if (y0 > y1) {
/*     */           
/* 212 */           double t = y0; y0 = y1; y1 = t;
/* 213 */           t = t0; t0 = t1; t1 = t;
/*     */         } 
/*     */         
/* 216 */         if (-y0 < 1.4210854715202004E-14D * y1) { roots[ret++] = t0; }
/* 217 */         else if (y1 < -1.4210854715202004E-14D * y0) { roots[ret++] = t1; i++; }
/*     */         else
/* 219 */         { double epsZero = 1.4210854715202004E-14D * (y1 - y0);
/*     */           int cnt;
/* 221 */           for (cnt = 0; cnt < 20; cnt++) {
/* 222 */             double dt = t1 - t0;
/* 223 */             double dy = y1 - y0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 231 */             double t = t0 + (Math.abs(y0 / dy) * 99.0D + 0.5D) * dt / 100.0D;
/* 232 */             double v = ((a3 * t + a2) * t + a1) * t + a0;
/* 233 */             if (Math.abs(v) < epsZero) {
/* 234 */               roots[ret++] = t; break;
/*     */             } 
/* 236 */             if (v < 0.0D) { t0 = t; y0 = v; }
/* 237 */             else { t1 = t; y1 = v; }
/*     */           
/* 239 */           }  if (cnt == 20)
/* 240 */             roots[ret++] = (t0 + t1) / 2.0D;  } 
/*     */       } 
/* 242 */     }  return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/AbstractSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */