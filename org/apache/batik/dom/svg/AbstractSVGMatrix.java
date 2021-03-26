/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
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
/*     */ public abstract class AbstractSVGMatrix
/*     */   implements SVGMatrix
/*     */ {
/*  40 */   protected static final AffineTransform FLIP_X_TRANSFORM = new AffineTransform(-1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected static final AffineTransform FLIP_Y_TRANSFORM = new AffineTransform(1.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract AffineTransform getAffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getA() {
/*  58 */     return (float)getAffineTransform().getScaleX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setA(float a) throws DOMException {
/*  65 */     AffineTransform at = getAffineTransform();
/*  66 */     at.setTransform(a, at.getShearY(), at.getShearX(), at.getScaleY(), at.getTranslateX(), at.getTranslateY());
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
/*     */   public float getB() {
/*  78 */     return (float)getAffineTransform().getShearY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setB(float b) throws DOMException {
/*  85 */     AffineTransform at = getAffineTransform();
/*  86 */     at.setTransform(at.getScaleX(), b, at.getShearX(), at.getScaleY(), at.getTranslateX(), at.getTranslateY());
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
/*     */   public float getC() {
/*  98 */     return (float)getAffineTransform().getShearX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setC(float c) throws DOMException {
/* 105 */     AffineTransform at = getAffineTransform();
/* 106 */     at.setTransform(at.getScaleX(), at.getShearY(), c, at.getScaleY(), at.getTranslateX(), at.getTranslateY());
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
/*     */   public float getD() {
/* 118 */     return (float)getAffineTransform().getScaleY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setD(float d) throws DOMException {
/* 125 */     AffineTransform at = getAffineTransform();
/* 126 */     at.setTransform(at.getScaleX(), at.getShearY(), at.getShearX(), d, at.getTranslateX(), at.getTranslateY());
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
/*     */   public float getE() {
/* 138 */     return (float)getAffineTransform().getTranslateX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setE(float e) throws DOMException {
/* 145 */     AffineTransform at = getAffineTransform();
/* 146 */     at.setTransform(at.getScaleX(), at.getShearY(), at.getShearX(), at.getScaleY(), e, at.getTranslateY());
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
/*     */   public float getF() {
/* 158 */     return (float)getAffineTransform().getTranslateY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setF(float f) throws DOMException {
/* 165 */     AffineTransform at = getAffineTransform();
/* 166 */     at.setTransform(at.getScaleX(), at.getShearY(), at.getShearX(), at.getScaleY(), at.getTranslateX(), f);
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
/*     */   public SVGMatrix multiply(SVGMatrix secondMatrix) {
/* 178 */     AffineTransform at = new AffineTransform(secondMatrix.getA(), secondMatrix.getB(), secondMatrix.getC(), secondMatrix.getD(), secondMatrix.getE(), secondMatrix.getF());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 185 */     tr.concatenate(at);
/* 186 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix inverse() throws SVGException {
/*     */     try {
/* 194 */       return new SVGOMMatrix(getAffineTransform().createInverse());
/* 195 */     } catch (NoninvertibleTransformException e) {
/* 196 */       throw new SVGOMException((short)2, e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix translate(float x, float y) {
/* 205 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 206 */     tr.translate(x, y);
/* 207 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix scale(float scaleFactor) {
/* 214 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 215 */     tr.scale(scaleFactor, scaleFactor);
/* 216 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix scaleNonUniform(float scaleFactorX, float scaleFactorY) {
/* 223 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 224 */     tr.scale(scaleFactorX, scaleFactorY);
/* 225 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix rotate(float angle) {
/* 232 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 233 */     tr.rotate(Math.toRadians(angle));
/* 234 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix rotateFromVector(float x, float y) throws SVGException {
/* 241 */     if (x == 0.0F || y == 0.0F) {
/* 242 */       throw new SVGOMException((short)1, "");
/*     */     }
/* 244 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 245 */     tr.rotate(Math.atan2(y, x));
/* 246 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix flipX() {
/* 253 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 254 */     tr.concatenate(FLIP_X_TRANSFORM);
/* 255 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix flipY() {
/* 262 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 263 */     tr.concatenate(FLIP_Y_TRANSFORM);
/* 264 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix skewX(float angleDeg) {
/* 271 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 272 */     tr.concatenate(AffineTransform.getShearInstance(Math.tan(Math.toRadians(angleDeg)), 0.0D));
/*     */     
/* 274 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix skewY(float angleDeg) {
/* 281 */     AffineTransform tr = (AffineTransform)getAffineTransform().clone();
/* 282 */     tr.concatenate(AffineTransform.getShearInstance(0.0D, Math.tan(Math.toRadians(angleDeg))));
/*     */     
/* 284 */     return new SVGOMMatrix(tr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */