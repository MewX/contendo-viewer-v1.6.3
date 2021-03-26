/*     */ package org.apache.xmlgraphics.java2d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  52 */   public static final TransformType TRANSLATE = new TransformType(0, "translate");
/*  53 */   public static final TransformType ROTATE = new TransformType(1, "rotate");
/*  54 */   public static final TransformType SCALE = new TransformType(2, "scale");
/*  55 */   public static final TransformType SHEAR = new TransformType(3, "shear");
/*  56 */   public static final TransformType GENERAL = new TransformType(4, "general");
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
/*  67 */     this.desc = desc;
/*  68 */     this.val = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  75 */     return this.desc;
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
/*  89 */     return this.val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readResolve() {
/*  98 */     switch (this.val) {
/*     */       case 0:
/* 100 */         return TRANSLATE;
/*     */       case 1:
/* 102 */         return ROTATE;
/*     */       case 2:
/* 104 */         return SCALE;
/*     */       case 3:
/* 106 */         return SHEAR;
/*     */       case 4:
/* 108 */         return GENERAL;
/*     */     } 
/* 110 */     throw new RuntimeException("Unknown TransformType value");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/TransformType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */