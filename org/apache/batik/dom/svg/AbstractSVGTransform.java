/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGTransform;
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
/*     */ public abstract class AbstractSVGTransform
/*     */   implements SVGTransform
/*     */ {
/*  38 */   protected short type = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform affineTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float angle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract SVGMatrix createMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(short type) {
/*  75 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/*  83 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/*  91 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void assign(AbstractSVGTransform t) {
/*  98 */     this.type = t.type;
/*  99 */     this.affineTransform = t.affineTransform;
/* 100 */     this.angle = t.angle;
/* 101 */     this.x = t.x;
/* 102 */     this.y = t.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getType() {
/* 109 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getMatrix() {
/* 116 */     return createMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAngle() {
/* 123 */     return this.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatrix(SVGMatrix matrix) {
/* 130 */     this.type = 1;
/* 131 */     this.affineTransform = new AffineTransform(matrix.getA(), matrix.getB(), matrix.getC(), matrix.getD(), matrix.getE(), matrix.getF());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranslate(float tx, float ty) {
/* 140 */     this.type = 2;
/* 141 */     this.affineTransform = AffineTransform.getTranslateInstance(tx, ty);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScale(float sx, float sy) {
/* 148 */     this.type = 3;
/* 149 */     this.affineTransform = AffineTransform.getScaleInstance(sx, sy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotate(float angle, float cx, float cy) {
/* 156 */     this.type = 4;
/* 157 */     this.affineTransform = AffineTransform.getRotateInstance(Math.toRadians(angle), cx, cy);
/*     */     
/* 159 */     this.angle = angle;
/* 160 */     this.x = cx;
/* 161 */     this.y = cy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkewX(float angle) {
/* 168 */     this.type = 5;
/* 169 */     this.affineTransform = AffineTransform.getShearInstance(Math.tan(Math.toRadians(angle)), 0.0D);
/*     */ 
/*     */     
/* 172 */     this.angle = angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkewY(float angle) {
/* 179 */     this.type = 6;
/* 180 */     this.affineTransform = AffineTransform.getShearInstance(0.0D, Math.tan(Math.toRadians(angle)));
/*     */ 
/*     */     
/* 183 */     this.angle = angle;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */