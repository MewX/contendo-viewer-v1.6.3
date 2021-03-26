/*     */ package org.apache.pdfbox.util;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Arrays;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
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
/*     */ public final class Matrix
/*     */   implements Cloneable
/*     */ {
/*  35 */   static final float[] DEFAULT_SINGLE = new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final float[] single;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix() {
/*  49 */     this.single = new float[DEFAULT_SINGLE.length];
/*  50 */     System.arraycopy(DEFAULT_SINGLE, 0, this.single, 0, DEFAULT_SINGLE.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix(COSArray array) {
/*  60 */     this.single = new float[DEFAULT_SINGLE.length];
/*  61 */     this.single[0] = ((COSNumber)array.getObject(0)).floatValue();
/*  62 */     this.single[1] = ((COSNumber)array.getObject(1)).floatValue();
/*  63 */     this.single[3] = ((COSNumber)array.getObject(2)).floatValue();
/*  64 */     this.single[4] = ((COSNumber)array.getObject(3)).floatValue();
/*  65 */     this.single[6] = ((COSNumber)array.getObject(4)).floatValue();
/*  66 */     this.single[7] = ((COSNumber)array.getObject(5)).floatValue();
/*  67 */     this.single[8] = 1.0F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix(float a, float b, float c, float d, float e, float f) {
/*  89 */     this.single = new float[DEFAULT_SINGLE.length];
/*  90 */     this.single[0] = a;
/*  91 */     this.single[1] = b;
/*  92 */     this.single[3] = c;
/*  93 */     this.single[4] = d;
/*  94 */     this.single[6] = e;
/*  95 */     this.single[7] = f;
/*  96 */     this.single[8] = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix(AffineTransform at) {
/* 105 */     this.single = new float[DEFAULT_SINGLE.length];
/* 106 */     System.arraycopy(DEFAULT_SINGLE, 0, this.single, 0, DEFAULT_SINGLE.length);
/* 107 */     this.single[0] = (float)at.getScaleX();
/* 108 */     this.single[1] = (float)at.getShearY();
/* 109 */     this.single[3] = (float)at.getShearX();
/* 110 */     this.single[4] = (float)at.getScaleY();
/* 111 */     this.single[6] = (float)at.getTranslateX();
/* 112 */     this.single[7] = (float)at.getTranslateY();
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
/*     */   public static Matrix createMatrix(COSBase base) {
/* 126 */     if (!(base instanceof COSArray))
/*     */     {
/* 128 */       return new Matrix();
/*     */     }
/* 130 */     COSArray array = (COSArray)base;
/* 131 */     if (array.size() < 6)
/*     */     {
/* 133 */       return new Matrix();
/*     */     }
/* 135 */     for (int i = 0; i < 6; i++) {
/*     */       
/* 137 */       if (!(array.getObject(i) instanceof COSNumber))
/*     */       {
/* 139 */         return new Matrix();
/*     */       }
/*     */     } 
/* 142 */     return new Matrix(array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void reset() {
/* 154 */     System.arraycopy(DEFAULT_SINGLE, 0, this.single, 0, DEFAULT_SINGLE.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform createAffineTransform() {
/* 164 */     return new AffineTransform(this.single[0], this.single[1], this.single[3], this.single[4], this.single[6], this.single[7]);
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
/*     */   @Deprecated
/*     */   public void setFromAffineTransform(AffineTransform af) {
/* 179 */     this.single[0] = (float)af.getScaleX();
/* 180 */     this.single[1] = (float)af.getShearY();
/* 181 */     this.single[3] = (float)af.getShearX();
/* 182 */     this.single[4] = (float)af.getScaleY();
/* 183 */     this.single[6] = (float)af.getTranslateX();
/* 184 */     this.single[7] = (float)af.getTranslateY();
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
/*     */   public float getValue(int row, int column) {
/* 197 */     return this.single[row * 3 + column];
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
/*     */   public void setValue(int row, int column, float value) {
/* 209 */     this.single[row * 3 + column] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[][] getValues() {
/* 219 */     float[][] retval = new float[3][3];
/* 220 */     retval[0][0] = this.single[0];
/* 221 */     retval[0][1] = this.single[1];
/* 222 */     retval[0][2] = this.single[2];
/* 223 */     retval[1][0] = this.single[3];
/* 224 */     retval[1][1] = this.single[4];
/* 225 */     retval[1][2] = this.single[5];
/* 226 */     retval[2][0] = this.single[6];
/* 227 */     retval[2][1] = this.single[7];
/* 228 */     retval[2][2] = this.single[8];
/* 229 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public double[][] getValuesAsDouble() {
/* 241 */     double[][] retval = new double[3][3];
/* 242 */     retval[0][0] = this.single[0];
/* 243 */     retval[0][1] = this.single[1];
/* 244 */     retval[0][2] = this.single[2];
/* 245 */     retval[1][0] = this.single[3];
/* 246 */     retval[1][1] = this.single[4];
/* 247 */     retval[1][2] = this.single[5];
/* 248 */     retval[2][0] = this.single[6];
/* 249 */     retval[2][1] = this.single[7];
/* 250 */     retval[2][2] = this.single[8];
/* 251 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void concatenate(Matrix matrix) {
/* 261 */     matrix.multiply(this, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(Vector vector) {
/* 271 */     Matrix m = getTranslateInstance(vector.getX(), vector.getY());
/* 272 */     concatenate(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(float tx, float ty) {
/* 283 */     Matrix m = getTranslateInstance(tx, ty);
/* 284 */     concatenate(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(float sx, float sy) {
/* 295 */     Matrix m = getScaleInstance(sx, sy);
/* 296 */     concatenate(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(double theta) {
/* 306 */     Matrix m = getRotateInstance(theta, 0.0F, 0.0F);
/* 307 */     concatenate(m);
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
/*     */   public Matrix multiply(Matrix b) {
/* 319 */     return multiply(b, new Matrix());
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
/*     */   
/*     */   public Matrix multiply(Matrix other, Matrix result) {
/* 337 */     if (result == null)
/*     */     {
/* 339 */       result = new Matrix();
/*     */     }
/*     */     
/* 342 */     if (other != null && other.single != null) {
/*     */ 
/*     */       
/* 345 */       float[] thisOperand = this.single;
/* 346 */       float[] otherOperand = other.single;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 356 */       if (this == result) {
/*     */         
/* 358 */         float[] thisOrigVals = new float[this.single.length];
/* 359 */         System.arraycopy(this.single, 0, thisOrigVals, 0, this.single.length);
/*     */         
/* 361 */         thisOperand = thisOrigVals;
/*     */       } 
/* 363 */       if (other == result) {
/*     */         
/* 365 */         float[] otherOrigVals = new float[other.single.length];
/* 366 */         System.arraycopy(other.single, 0, otherOrigVals, 0, other.single.length);
/*     */         
/* 368 */         otherOperand = otherOrigVals;
/*     */       } 
/*     */       
/* 371 */       result.single[0] = thisOperand[0] * otherOperand[0] + thisOperand[1] * otherOperand[3] + thisOperand[2] * otherOperand[6];
/*     */ 
/*     */       
/* 374 */       result.single[1] = thisOperand[0] * otherOperand[1] + thisOperand[1] * otherOperand[4] + thisOperand[2] * otherOperand[7];
/*     */ 
/*     */       
/* 377 */       result.single[2] = thisOperand[0] * otherOperand[2] + thisOperand[1] * otherOperand[5] + thisOperand[2] * otherOperand[8];
/*     */ 
/*     */       
/* 380 */       result.single[3] = thisOperand[3] * otherOperand[0] + thisOperand[4] * otherOperand[3] + thisOperand[5] * otherOperand[6];
/*     */ 
/*     */       
/* 383 */       result.single[4] = thisOperand[3] * otherOperand[1] + thisOperand[4] * otherOperand[4] + thisOperand[5] * otherOperand[7];
/*     */ 
/*     */       
/* 386 */       result.single[5] = thisOperand[3] * otherOperand[2] + thisOperand[4] * otherOperand[5] + thisOperand[5] * otherOperand[8];
/*     */ 
/*     */       
/* 389 */       result.single[6] = thisOperand[6] * otherOperand[0] + thisOperand[7] * otherOperand[3] + thisOperand[8] * otherOperand[6];
/*     */ 
/*     */       
/* 392 */       result.single[7] = thisOperand[6] * otherOperand[1] + thisOperand[7] * otherOperand[4] + thisOperand[8] * otherOperand[7];
/*     */ 
/*     */       
/* 395 */       result.single[8] = thisOperand[6] * otherOperand[2] + thisOperand[7] * otherOperand[5] + thisOperand[8] * otherOperand[8];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 400 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void transform(Point2D point) {
/* 410 */     float x = (float)point.getX();
/* 411 */     float y = (float)point.getY();
/* 412 */     float a = this.single[0];
/* 413 */     float b = this.single[1];
/* 414 */     float c = this.single[3];
/* 415 */     float d = this.single[4];
/* 416 */     float e = this.single[6];
/* 417 */     float f = this.single[7];
/* 418 */     point.setLocation((x * a + y * c + e), (x * b + y * d + f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D.Float transformPoint(float x, float y) {
/* 429 */     float a = this.single[0];
/* 430 */     float b = this.single[1];
/* 431 */     float c = this.single[3];
/* 432 */     float d = this.single[4];
/* 433 */     float e = this.single[6];
/* 434 */     float f = this.single[7];
/* 435 */     return new Point2D.Float(x * a + y * c + e, x * b + y * d + f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector transform(Vector vector) {
/* 445 */     float a = this.single[0];
/* 446 */     float b = this.single[1];
/* 447 */     float c = this.single[3];
/* 448 */     float d = this.single[4];
/* 449 */     float e = this.single[6];
/* 450 */     float f = this.single[7];
/* 451 */     float x = vector.getX();
/* 452 */     float y = vector.getY();
/* 453 */     return new Vector(x * a + y * c + e, x * b + y * d + f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Matrix extractScaling() {
/* 465 */     Matrix matrix = new Matrix();
/* 466 */     matrix.single[0] = this.single[0];
/* 467 */     matrix.single[4] = this.single[4];
/* 468 */     return matrix;
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
/*     */   public static Matrix getScaleInstance(float sx, float sy) {
/* 480 */     Matrix matrix = new Matrix();
/* 481 */     matrix.single[0] = sx;
/* 482 */     matrix.single[4] = sy;
/* 483 */     return matrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Matrix extractTranslating() {
/* 495 */     Matrix matrix = new Matrix();
/* 496 */     matrix.single[6] = this.single[6];
/* 497 */     matrix.single[7] = this.single[7];
/* 498 */     return matrix;
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
/*     */   @Deprecated
/*     */   public static Matrix getTranslatingInstance(float tx, float ty) {
/* 512 */     return getTranslateInstance(tx, ty);
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
/*     */   public static Matrix getTranslateInstance(float tx, float ty) {
/* 524 */     Matrix matrix = new Matrix();
/* 525 */     matrix.single[6] = tx;
/* 526 */     matrix.single[7] = ty;
/* 527 */     return matrix;
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
/*     */   public static Matrix getRotateInstance(double theta, float tx, float ty) {
/* 540 */     float cosTheta = (float)Math.cos(theta);
/* 541 */     float sinTheta = (float)Math.sin(theta);
/*     */     
/* 543 */     Matrix matrix = new Matrix();
/* 544 */     matrix.single[0] = cosTheta;
/* 545 */     matrix.single[1] = sinTheta;
/* 546 */     matrix.single[3] = -sinTheta;
/* 547 */     matrix.single[4] = cosTheta;
/* 548 */     matrix.single[6] = tx;
/* 549 */     matrix.single[7] = ty;
/* 550 */     return matrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Matrix concatenate(Matrix a, Matrix b) {
/* 561 */     Matrix copy = a.clone();
/* 562 */     copy.concatenate(b);
/* 563 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix clone() {
/* 573 */     Matrix clone = new Matrix();
/* 574 */     System.arraycopy(this.single, 0, clone.single, 0, 9);
/* 575 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScalingFactorX() {
/* 585 */     float xScale = this.single[0];
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
/* 604 */     if (this.single[1] != 0.0F || this.single[3] != 0.0F)
/*     */     {
/* 606 */       xScale = (float)Math.sqrt(Math.pow(this.single[0], 2.0D) + 
/* 607 */           Math.pow(this.single[1], 2.0D));
/*     */     }
/* 609 */     return xScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScalingFactorY() {
/* 619 */     float yScale = this.single[4];
/* 620 */     if (this.single[1] != 0.0F || this.single[3] != 0.0F)
/*     */     {
/* 622 */       yScale = (float)Math.sqrt(Math.pow(this.single[3], 2.0D) + 
/* 623 */           Math.pow(this.single[4], 2.0D));
/*     */     }
/* 625 */     return yScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScaleX() {
/* 635 */     return this.single[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShearY() {
/* 643 */     return this.single[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShearX() {
/* 651 */     return this.single[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getScaleY() {
/* 661 */     return this.single[4];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTranslateX() {
/* 669 */     return this.single[6];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTranslateY() {
/* 677 */     return this.single[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public float getXPosition() {
/* 689 */     return this.single[6];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public float getYPosition() {
/* 701 */     return this.single[7];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray toCOSArray() {
/* 709 */     COSArray array = new COSArray();
/* 710 */     array.add((COSBase)new COSFloat(this.single[0]));
/* 711 */     array.add((COSBase)new COSFloat(this.single[1]));
/* 712 */     array.add((COSBase)new COSFloat(this.single[3]));
/* 713 */     array.add((COSBase)new COSFloat(this.single[4]));
/* 714 */     array.add((COSBase)new COSFloat(this.single[6]));
/* 715 */     array.add((COSBase)new COSFloat(this.single[7]));
/* 716 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 722 */     String sb = "[" + this.single[0] + "," + this.single[1] + "," + this.single[3] + "," + this.single[4] + "," + this.single[6] + "," + this.single[7] + "]";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 729 */     return sb;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 735 */     return Arrays.hashCode(this.single);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 741 */     if (this == obj)
/*     */     {
/* 743 */       return true;
/*     */     }
/* 745 */     if (obj == null)
/*     */     {
/* 747 */       return false;
/*     */     }
/* 749 */     if (getClass() != obj.getClass())
/*     */     {
/* 751 */       return false;
/*     */     }
/* 753 */     return Arrays.equals(this.single, ((Matrix)obj).single);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/Matrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */