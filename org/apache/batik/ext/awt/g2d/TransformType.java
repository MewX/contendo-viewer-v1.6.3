/*     */ package org.apache.batik.ext.awt.g2d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TransformType
/*     */ {
/*     */   public static final int TRANSFORM_TRANSLATE = 0;
/*     */   public static final int TRANSFORM_ROTATE = 1;
/*     */   public static final int TRANSFORM_SCALE = 2;
/*     */   public static final int TRANSFORM_SHEAR = 3;
/*     */   public static final int TRANSFORM_GENERAL = 4;
/*     */   public static final String TRANSLATE_STRING = "translate";
/*     */   public static final String ROTATE_STRING = "rotate";
/*     */   public static final String SCALE_STRING = "scale";
/*     */   public static final String SHEAR_STRING = "shear";
/*     */   public static final String GENERAL_STRING = "general";
/*  50 */   public static final TransformType TRANSLATE = new TransformType(0, "translate");
/*  51 */   public static final TransformType ROTATE = new TransformType(1, "rotate");
/*  52 */   public static final TransformType SCALE = new TransformType(2, "scale");
/*  53 */   public static final TransformType SHEAR = new TransformType(3, "shear");
/*  54 */   public static final TransformType GENERAL = new TransformType(4, "general");
/*     */ 
/*     */   
/*     */   private String desc;
/*     */ 
/*     */   
/*     */   private int val;
/*     */ 
/*     */ 
/*     */   
/*     */   private TransformType(int val, String desc) {
/*  65 */     this.desc = desc;
/*  66 */     this.val = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  73 */     return this.desc;
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
/*     */   public int toInt() {
/*  87 */     return this.val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readResolve() {
/*  96 */     switch (this.val) {
/*     */       case 0:
/*  98 */         return TRANSLATE;
/*     */       case 1:
/* 100 */         return ROTATE;
/*     */       case 2:
/* 102 */         return SCALE;
/*     */       case 3:
/* 104 */         return SHEAR;
/*     */       case 4:
/* 106 */         return GENERAL;
/*     */     } 
/* 108 */     throw new RuntimeException("Unknown TransformType value:" + this.val);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/g2d/TransformType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */