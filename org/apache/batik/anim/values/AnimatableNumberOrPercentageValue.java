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
/*     */ public class AnimatableNumberOrPercentageValue
/*     */   extends AnimatableNumberValue
/*     */ {
/*     */   protected boolean isPercentage;
/*     */   
/*     */   protected AnimatableNumberOrPercentageValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOrPercentageValue(AnimationTarget target, float n) {
/*  47 */     super(target, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOrPercentageValue(AnimationTarget target, float n, boolean isPercentage) {
/*  56 */     super(target, n);
/*  57 */     this.isPercentage = isPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableNumberOrPercentageValue res;
/*     */     float newValue;
/*     */     boolean newIsPercentage;
/*  69 */     if (result == null) {
/*  70 */       res = new AnimatableNumberOrPercentageValue(this.target);
/*     */     } else {
/*  72 */       res = (AnimatableNumberOrPercentageValue)result;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     AnimatableNumberOrPercentageValue toValue = (AnimatableNumberOrPercentageValue)to;
/*     */     
/*  80 */     AnimatableNumberOrPercentageValue accValue = (AnimatableNumberOrPercentageValue)accumulation;
/*     */ 
/*     */     
/*  83 */     if (to != null) {
/*  84 */       if (toValue.isPercentage == this.isPercentage) {
/*  85 */         newValue = this.value + interpolation * (toValue.value - this.value);
/*  86 */         newIsPercentage = this.isPercentage;
/*     */       }
/*  88 */       else if (interpolation >= 0.5D) {
/*  89 */         newValue = toValue.value;
/*  90 */         newIsPercentage = toValue.isPercentage;
/*     */       } else {
/*  92 */         newValue = this.value;
/*  93 */         newIsPercentage = this.isPercentage;
/*     */       } 
/*     */     } else {
/*     */       
/*  97 */       newValue = this.value;
/*  98 */       newIsPercentage = this.isPercentage;
/*     */     } 
/*     */     
/* 101 */     if (accumulation != null && accValue.isPercentage == newIsPercentage) {
/* 102 */       newValue += multiplier * accValue.value;
/*     */     }
/*     */     
/* 105 */     if (res.value != newValue || res.isPercentage != newIsPercentage) {
/*     */       
/* 107 */       res.value = newValue;
/* 108 */       res.isPercentage = newIsPercentage;
/* 109 */       res.hasChanged = true;
/*     */     } 
/* 111 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPercentage() {
/* 118 */     return this.isPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 134 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 141 */     return new AnimatableNumberOrPercentageValue(this.target, 0.0F, this.isPercentage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 148 */     StringBuffer sb = new StringBuffer();
/* 149 */     sb.append(formatNumber(this.value));
/* 150 */     if (this.isPercentage) {
/* 151 */       sb.append('%');
/*     */     }
/* 153 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableNumberOrPercentageValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */