/*     */ package org.apache.xmlgraphics.java2d;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TransformStackElement
/*     */   implements Cloneable
/*     */ {
/*     */   private TransformType type;
/*     */   private double[] transformParameters;
/*     */   
/*     */   protected TransformStackElement(TransformType type, double[] transformParameters) {
/*  56 */     this.type = type;
/*  57 */     this.transformParameters = transformParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  64 */     TransformStackElement newElement = null;
/*     */ 
/*     */     
/*     */     try {
/*  68 */       newElement = (TransformStackElement)super.clone();
/*  69 */     } catch (CloneNotSupportedException ex) {
/*  70 */       throw new AssertionError();
/*     */     } 
/*     */ 
/*     */     
/*  74 */     double[] transformParameters = new double[this.transformParameters.length];
/*  75 */     System.arraycopy(this.transformParameters, 0, transformParameters, 0, transformParameters.length);
/*  76 */     newElement.transformParameters = transformParameters;
/*  77 */     return newElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TransformStackElement createTranslateElement(double tx, double ty) {
/*  86 */     return new TransformStackElement(TransformType.TRANSLATE, new double[] { tx, ty })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/*  89 */           return (parameters[0] == 0.0D && parameters[1] == 0.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static TransformStackElement createRotateElement(double theta) {
/*  95 */     return new TransformStackElement(TransformType.ROTATE, new double[] { theta })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/*  98 */           return (Math.cos(parameters[0]) == 1.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createScaleElement(double scaleX, double scaleY) {
/* 105 */     return new TransformStackElement(TransformType.SCALE, new double[] { scaleX, scaleY })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/* 108 */           return (parameters[0] == 1.0D && parameters[1] == 1.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createShearElement(double shearX, double shearY) {
/* 115 */     return new TransformStackElement(TransformType.SHEAR, new double[] { shearX, shearY })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/* 118 */           return (parameters[0] == 0.0D && parameters[1] == 0.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createGeneralTransformElement(AffineTransform txf) {
/* 125 */     double[] matrix = new double[6];
/* 126 */     txf.getMatrix(matrix);
/* 127 */     return new TransformStackElement(TransformType.GENERAL, matrix) {
/*     */         boolean isIdentity(double[] m) {
/* 129 */           return (m[0] == 1.0D && m[2] == 0.0D && m[4] == 0.0D && m[1] == 0.0D && m[3] == 1.0D && m[5] == 0.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean isIdentity(double[] paramArrayOfdouble);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIdentity() {
/* 145 */     return isIdentity(this.transformParameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getTransformParameters() {
/* 152 */     return this.transformParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformType getType() {
/* 159 */     return this.type;
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
/*     */   public boolean concatenate(TransformStackElement stackElement) {
/* 174 */     boolean canConcatenate = false;
/*     */     
/* 176 */     if (this.type.toInt() == stackElement.type.toInt())
/* 177 */     { canConcatenate = true;
/* 178 */       switch (this.type.toInt())
/*     */       { case 0:
/* 180 */           this.transformParameters[0] = this.transformParameters[0] + stackElement.transformParameters[0];
/* 181 */           this.transformParameters[1] = this.transformParameters[1] + stackElement.transformParameters[1];
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
/* 200 */           return canConcatenate;case 1: this.transformParameters[0] = this.transformParameters[0] + stackElement.transformParameters[0]; return canConcatenate;case 2: this.transformParameters[0] = this.transformParameters[0] * stackElement.transformParameters[0]; this.transformParameters[1] = this.transformParameters[1] * stackElement.transformParameters[1]; return canConcatenate;case 4: this.transformParameters = matrixMultiply(this.transformParameters, stackElement.transformParameters); return canConcatenate; }  canConcatenate = false; }  return canConcatenate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] matrixMultiply(double[] matrix1, double[] matrix2) {
/* 207 */     double[] product = new double[6];
/* 208 */     AffineTransform transform1 = new AffineTransform(matrix1);
/* 209 */     transform1.concatenate(new AffineTransform(matrix2));
/* 210 */     transform1.getMatrix(product);
/* 211 */     return product;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/TransformStackElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */