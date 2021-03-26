/*     */ package org.apache.batik.ext.awt.g2d;
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
/*     */ public abstract class TransformStackElement
/*     */   implements Cloneable
/*     */ {
/*     */   private TransformType type;
/*     */   private double[] transformParameters;
/*     */   
/*     */   protected TransformStackElement(TransformType type, double[] transformParameters) {
/*  50 */     this.type = type;
/*  51 */     this.transformParameters = transformParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  58 */     TransformStackElement newElement = null;
/*     */ 
/*     */     
/*     */     try {
/*  62 */       newElement = (TransformStackElement)super.clone();
/*  63 */     } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */ 
/*     */     
/*  66 */     double[] transformParameters = new double[this.transformParameters.length];
/*  67 */     System.arraycopy(this.transformParameters, 0, transformParameters, 0, transformParameters.length);
/*  68 */     newElement.transformParameters = transformParameters;
/*  69 */     return newElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TransformStackElement createTranslateElement(double tx, double ty) {
/*  78 */     return new TransformStackElement(TransformType.TRANSLATE, new double[] { tx, ty })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/*  81 */           return (parameters[0] == 0.0D && parameters[1] == 0.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static TransformStackElement createRotateElement(double theta) {
/*  87 */     return new TransformStackElement(TransformType.ROTATE, new double[] { theta })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/*  90 */           return (Math.cos(parameters[0]) == 1.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createScaleElement(double scaleX, double scaleY) {
/*  97 */     return new TransformStackElement(TransformType.SCALE, new double[] { scaleX, scaleY })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/* 100 */           return (parameters[0] == 1.0D && parameters[1] == 1.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createShearElement(double shearX, double shearY) {
/* 107 */     return new TransformStackElement(TransformType.SHEAR, new double[] { shearX, shearY })
/*     */       {
/*     */         boolean isIdentity(double[] parameters) {
/* 110 */           return (parameters[0] == 0.0D && parameters[1] == 0.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static TransformStackElement createGeneralTransformElement(AffineTransform txf) {
/* 117 */     double[] matrix = new double[6];
/* 118 */     txf.getMatrix(matrix);
/* 119 */     return new TransformStackElement(TransformType.GENERAL, matrix) {
/*     */         boolean isIdentity(double[] m) {
/* 121 */           return (m[0] == 1.0D && m[2] == 0.0D && m[4] == 0.0D && m[1] == 0.0D && m[3] == 1.0D && m[5] == 0.0D);
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
/* 137 */     return isIdentity(this.transformParameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getTransformParameters() {
/* 144 */     return this.transformParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformType getType() {
/* 151 */     return this.type;
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
/* 166 */     boolean canConcatenate = false;
/*     */     
/* 168 */     if (this.type.toInt() == stackElement.type.toInt())
/* 169 */     { canConcatenate = true;
/* 170 */       switch (this.type.toInt())
/*     */       { case 0:
/* 172 */           this.transformParameters[0] = this.transformParameters[0] + stackElement.transformParameters[0];
/* 173 */           this.transformParameters[1] = this.transformParameters[1] + stackElement.transformParameters[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 192 */           return canConcatenate;case 1: this.transformParameters[0] = this.transformParameters[0] + stackElement.transformParameters[0]; return canConcatenate;case 2: this.transformParameters[0] = this.transformParameters[0] * stackElement.transformParameters[0]; this.transformParameters[1] = this.transformParameters[1] * stackElement.transformParameters[1]; return canConcatenate;case 4: this.transformParameters = matrixMultiply(this.transformParameters, stackElement.transformParameters); return canConcatenate; }  canConcatenate = false; }  return canConcatenate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] matrixMultiply(double[] matrix1, double[] matrix2) {
/* 199 */     double[] product = new double[6];
/* 200 */     AffineTransform transform1 = new AffineTransform(matrix1);
/* 201 */     transform1.concatenate(new AffineTransform(matrix2));
/* 202 */     transform1.getMatrix(product);
/* 203 */     return product;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/g2d/TransformStackElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */