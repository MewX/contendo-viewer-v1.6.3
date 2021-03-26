/*     */ package org.apache.fontbox.util;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoundingBox
/*     */ {
/*     */   private float lowerLeftX;
/*     */   private float lowerLeftY;
/*     */   private float upperRightX;
/*     */   private float upperRightY;
/*     */   
/*     */   public BoundingBox() {}
/*     */   
/*     */   public BoundingBox(float minX, float minY, float maxX, float maxY) {
/*  52 */     this.lowerLeftX = minX;
/*  53 */     this.lowerLeftY = minY;
/*  54 */     this.upperRightX = maxX;
/*  55 */     this.upperRightY = maxY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox(List<Number> numbers) {
/*  65 */     this.lowerLeftX = ((Number)numbers.get(0)).floatValue();
/*  66 */     this.lowerLeftY = ((Number)numbers.get(1)).floatValue();
/*  67 */     this.upperRightX = ((Number)numbers.get(2)).floatValue();
/*  68 */     this.upperRightY = ((Number)numbers.get(3)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLowerLeftX() {
/*  78 */     return this.lowerLeftX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowerLeftX(float lowerLeftXValue) {
/*  88 */     this.lowerLeftX = lowerLeftXValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLowerLeftY() {
/*  98 */     return this.lowerLeftY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowerLeftY(float lowerLeftYValue) {
/* 108 */     this.lowerLeftY = lowerLeftYValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUpperRightX() {
/* 118 */     return this.upperRightX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperRightX(float upperRightXValue) {
/* 128 */     this.upperRightX = upperRightXValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUpperRightY() {
/* 138 */     return this.upperRightY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperRightY(float upperRightYValue) {
/* 148 */     this.upperRightY = upperRightYValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 159 */     return getUpperRightX() - getLowerLeftX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 170 */     return getUpperRightY() - getLowerLeftY();
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
/*     */   public boolean contains(float x, float y) {
/* 183 */     return (x >= this.lowerLeftX && x <= this.upperRightX && y >= this.lowerLeftY && y <= this.upperRightY);
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
/*     */   public String toString() {
/* 195 */     return "[" + getLowerLeftX() + "," + getLowerLeftY() + "," + 
/* 196 */       getUpperRightX() + "," + getUpperRightY() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/util/BoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */