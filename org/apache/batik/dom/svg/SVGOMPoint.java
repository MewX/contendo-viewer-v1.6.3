/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGPoint;
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
/*     */ public class SVGOMPoint
/*     */   implements SVGPoint
/*     */ {
/*     */   protected float x;
/*     */   protected float y;
/*     */   
/*     */   public SVGOMPoint() {}
/*     */   
/*     */   public SVGOMPoint(float x, float y) {
/*  54 */     this.x = x;
/*  55 */     this.y = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/*  62 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(float x) throws DOMException {
/*  69 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/*  76 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(float y) throws DOMException {
/*  83 */     this.y = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint matrixTransform(SVGMatrix matrix) {
/*  90 */     return matrixTransform(this, matrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGPoint matrixTransform(SVGPoint point, SVGMatrix matrix) {
/*  98 */     float newX = matrix.getA() * point.getX() + matrix.getC() * point.getY() + matrix.getE();
/*     */     
/* 100 */     float newY = matrix.getB() * point.getX() + matrix.getD() * point.getY() + matrix.getF();
/*     */     
/* 102 */     return new SVGOMPoint(newX, newY);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */