/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CubicBezierCurve
/*     */ {
/*     */   private final Point2D[] controlPoints;
/*     */   private final int level;
/*     */   private final Point2D[] curve;
/*     */   
/*     */   CubicBezierCurve(Point2D[] ctrlPnts, int l) {
/*  42 */     this.controlPoints = (Point2D[])ctrlPnts.clone();
/*  43 */     this.level = l;
/*  44 */     this.curve = getPoints(this.level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getLevel() {
/*  54 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Point2D[] getPoints(int l) {
/*  60 */     if (l < 0)
/*     */     {
/*  62 */       l = 0;
/*     */     }
/*  64 */     int sz = (1 << l) + 1;
/*  65 */     Point2D[] res = new Point2D[sz];
/*  66 */     double step = 1.0D / (sz - 1);
/*  67 */     double t = -step;
/*  68 */     for (int i = 0; i < sz; i++) {
/*     */       
/*  70 */       t += step;
/*     */ 
/*     */ 
/*     */       
/*  74 */       double tmpX = (1.0D - t) * (1.0D - t) * (1.0D - t) * this.controlPoints[0].getX() + 3.0D * t * (1.0D - t) * (1.0D - t) * this.controlPoints[1].getX() + 3.0D * t * t * (1.0D - t) * this.controlPoints[2].getX() + t * t * t * this.controlPoints[3].getX();
/*     */ 
/*     */ 
/*     */       
/*  78 */       double tmpY = (1.0D - t) * (1.0D - t) * (1.0D - t) * this.controlPoints[0].getY() + 3.0D * t * (1.0D - t) * (1.0D - t) * this.controlPoints[1].getY() + 3.0D * t * t * (1.0D - t) * this.controlPoints[2].getY() + t * t * t * this.controlPoints[3].getY();
/*  79 */       res[i] = new Point2D.Double(tmpX, tmpY);
/*     */     } 
/*  81 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Point2D[] getCubicBezierCurve() {
/*  91 */     return this.curve;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  97 */     StringBuilder sb = new StringBuilder();
/*  98 */     for (Point2D p : this.controlPoints) {
/*     */       
/* 100 */       if (sb.length() > 0)
/*     */       {
/* 102 */         sb.append(' ');
/*     */       }
/* 104 */       sb.append(p);
/*     */     } 
/* 106 */     return "Cubic Bezier curve{control points p0, p1, p2, p3: " + sb + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/CubicBezierCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */