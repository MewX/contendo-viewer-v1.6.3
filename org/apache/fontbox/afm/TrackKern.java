/*     */ package org.apache.fontbox.afm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TrackKern
/*     */ {
/*     */   private int degree;
/*     */   private float minPointSize;
/*     */   private float minKern;
/*     */   private float maxPointSize;
/*     */   private float maxKern;
/*     */   
/*     */   public int getDegree() {
/*  37 */     return this.degree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDegree(int degreeValue) {
/*  45 */     this.degree = degreeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxKern() {
/*  53 */     return this.maxKern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxKern(float maxKernValue) {
/*  61 */     this.maxKern = maxKernValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxPointSize() {
/*  69 */     return this.maxPointSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxPointSize(float maxPointSizeValue) {
/*  77 */     this.maxPointSize = maxPointSizeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinKern() {
/*  85 */     return this.minKern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinKern(float minKernValue) {
/*  93 */     this.minKern = minKernValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinPointSize() {
/* 101 */     return this.minPointSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinPointSize(float minPointSizeValue) {
/* 109 */     this.minPointSize = minPointSizeValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/TrackKern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */