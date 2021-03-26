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
/*     */ public class AnimatableNumberListValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float[] numbers;
/*     */   
/*     */   protected AnimatableNumberListValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberListValue(AnimationTarget target, float[] numbers) {
/*  47 */     super(target);
/*  48 */     this.numbers = numbers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     float[] baseValues;
/*  60 */     AnimatableNumberListValue res, toNumList = (AnimatableNumberListValue)to;
/*  61 */     AnimatableNumberListValue accNumList = (AnimatableNumberListValue)accumulation;
/*     */ 
/*     */     
/*  64 */     boolean hasTo = (to != null);
/*  65 */     boolean hasAcc = (accumulation != null);
/*  66 */     boolean canInterpolate = ((!hasTo || toNumList.numbers.length == this.numbers.length) && (!hasAcc || accNumList.numbers.length == this.numbers.length));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     if (!canInterpolate && hasTo && interpolation >= 0.5D) {
/*  72 */       baseValues = toNumList.numbers;
/*     */     } else {
/*  74 */       baseValues = this.numbers;
/*     */     } 
/*  76 */     int len = baseValues.length;
/*     */ 
/*     */     
/*  79 */     if (result == null) {
/*  80 */       res = new AnimatableNumberListValue(this.target);
/*  81 */       res.numbers = new float[len];
/*     */     } else {
/*  83 */       res = (AnimatableNumberListValue)result;
/*  84 */       if (res.numbers == null || res.numbers.length != len) {
/*  85 */         res.numbers = new float[len];
/*     */       }
/*     */     } 
/*     */     
/*  89 */     for (int i = 0; i < len; i++) {
/*  90 */       float newValue = baseValues[i];
/*  91 */       if (canInterpolate) {
/*  92 */         if (hasTo) {
/*  93 */           newValue += interpolation * (toNumList.numbers[i] - newValue);
/*     */         }
/*  95 */         if (hasAcc) {
/*  96 */           newValue += multiplier * accNumList.numbers[i];
/*     */         }
/*     */       } 
/*  99 */       if (res.numbers[i] != newValue) {
/* 100 */         res.numbers[i] = newValue;
/* 101 */         res.hasChanged = true;
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getNumbers() {
/* 112 */     return this.numbers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 128 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 135 */     float[] ns = new float[this.numbers.length];
/* 136 */     return new AnimatableNumberListValue(this.target, ns);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 143 */     StringBuffer sb = new StringBuffer();
/* 144 */     sb.append(this.numbers[0]);
/* 145 */     for (int i = 1; i < this.numbers.length; i++) {
/* 146 */       sb.append(' ');
/* 147 */       sb.append(this.numbers[i]);
/*     */     } 
/* 149 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableNumberListValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */