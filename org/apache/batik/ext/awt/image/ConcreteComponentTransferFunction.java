/*     */ package org.apache.batik.ext.awt.image;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConcreteComponentTransferFunction
/*     */   implements ComponentTransferFunction
/*     */ {
/*     */   private int type;
/*     */   private float slope;
/*     */   private float[] tableValues;
/*     */   private float intercept;
/*     */   private float amplitude;
/*     */   private float exponent;
/*     */   private float offset;
/*     */   
/*     */   public static ComponentTransferFunction getIdentityTransfer() {
/*  51 */     ConcreteComponentTransferFunction f = new ConcreteComponentTransferFunction();
/*  52 */     f.type = 0;
/*  53 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentTransferFunction getTableTransfer(float[] tableValues) {
/*  61 */     ConcreteComponentTransferFunction f = new ConcreteComponentTransferFunction();
/*  62 */     f.type = 1;
/*     */     
/*  64 */     if (tableValues == null) {
/*  65 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  68 */     if (tableValues.length < 2) {
/*  69 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  72 */     f.tableValues = new float[tableValues.length];
/*  73 */     System.arraycopy(tableValues, 0, f.tableValues, 0, tableValues.length);
/*     */ 
/*     */ 
/*     */     
/*  77 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentTransferFunction getDiscreteTransfer(float[] tableValues) {
/*  85 */     ConcreteComponentTransferFunction f = new ConcreteComponentTransferFunction();
/*  86 */     f.type = 2;
/*     */     
/*  88 */     if (tableValues == null) {
/*  89 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  92 */     if (tableValues.length < 2) {
/*  93 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  96 */     f.tableValues = new float[tableValues.length];
/*  97 */     System.arraycopy(tableValues, 0, f.tableValues, 0, tableValues.length);
/*     */ 
/*     */ 
/*     */     
/* 101 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentTransferFunction getLinearTransfer(float slope, float intercept) {
/* 109 */     ConcreteComponentTransferFunction f = new ConcreteComponentTransferFunction();
/* 110 */     f.type = 3;
/* 111 */     f.slope = slope;
/* 112 */     f.intercept = intercept;
/*     */     
/* 114 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentTransferFunction getGammaTransfer(float amplitude, float exponent, float offset) {
/* 124 */     ConcreteComponentTransferFunction f = new ConcreteComponentTransferFunction();
/* 125 */     f.type = 4;
/* 126 */     f.amplitude = amplitude;
/* 127 */     f.exponent = exponent;
/* 128 */     f.offset = offset;
/*     */     
/* 130 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 137 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSlope() {
/* 144 */     return this.slope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getTableValues() {
/* 151 */     return this.tableValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getIntercept() {
/* 158 */     return this.intercept;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAmplitude() {
/* 165 */     return this.amplitude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getExponent() {
/* 172 */     return this.exponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOffset() {
/* 179 */     return this.offset;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/ConcreteComponentTransferFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */