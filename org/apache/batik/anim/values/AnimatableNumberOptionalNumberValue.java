/*     */ package org.apache.batik.anim.values;
/*     */ 
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimatableNumberOptionalNumberValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float number;
/*     */   protected boolean hasOptionalNumber;
/*     */   protected float optionalNumber;
/*     */   
/*     */   protected AnimatableNumberOptionalNumberValue(AnimationTarget target) {
/*  50 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOptionalNumberValue(AnimationTarget target, float n) {
/*  58 */     super(target);
/*  59 */     this.number = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOptionalNumberValue(AnimationTarget target, float n, float on) {
/*  67 */     super(target);
/*  68 */     this.number = n;
/*  69 */     this.optionalNumber = on;
/*  70 */     this.hasOptionalNumber = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableNumberOptionalNumberValue res;
/*     */     float newNumber;
/*     */     float newOptionalNumber;
/*     */     boolean newHasOptionalNumber;
/*  83 */     if (result == null) {
/*  84 */       res = new AnimatableNumberOptionalNumberValue(this.target);
/*     */     } else {
/*  86 */       res = (AnimatableNumberOptionalNumberValue)result;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (to != null && interpolation >= 0.5D) {
/*  93 */       AnimatableNumberOptionalNumberValue toValue = (AnimatableNumberOptionalNumberValue)to;
/*     */       
/*  95 */       newNumber = toValue.number;
/*  96 */       newOptionalNumber = toValue.optionalNumber;
/*  97 */       newHasOptionalNumber = toValue.hasOptionalNumber;
/*     */     } else {
/*  99 */       newNumber = this.number;
/* 100 */       newOptionalNumber = this.optionalNumber;
/* 101 */       newHasOptionalNumber = this.hasOptionalNumber;
/*     */     } 
/*     */     
/* 104 */     if (res.number != newNumber || res.hasOptionalNumber != newHasOptionalNumber || res.optionalNumber != newOptionalNumber) {
/*     */ 
/*     */       
/* 107 */       res.number = this.number;
/* 108 */       res.optionalNumber = this.optionalNumber;
/* 109 */       res.hasOptionalNumber = this.hasOptionalNumber;
/* 110 */       res.hasChanged = true;
/*     */     } 
/* 112 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getNumber() {
/* 119 */     return this.number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasOptionalNumber() {
/* 126 */     return this.hasOptionalNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOptionalNumber() {
/* 133 */     return this.optionalNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 149 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 156 */     if (this.hasOptionalNumber) {
/* 157 */       return new AnimatableNumberOptionalNumberValue(this.target, 0.0F, 0.0F);
/*     */     }
/* 159 */     return new AnimatableNumberOptionalNumberValue(this.target, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 166 */     StringBuffer sb = new StringBuffer();
/* 167 */     sb.append(formatNumber(this.number));
/* 168 */     if (this.hasOptionalNumber) {
/* 169 */       sb.append(' ');
/* 170 */       sb.append(formatNumber(this.optionalNumber));
/*     */     } 
/* 172 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableNumberOptionalNumberValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */