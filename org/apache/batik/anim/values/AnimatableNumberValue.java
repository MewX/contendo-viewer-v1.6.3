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
/*     */ public class AnimatableNumberValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float value;
/*     */   
/*     */   protected AnimatableNumberValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberValue(AnimationTarget target, float v) {
/*  47 */     super(target);
/*  48 */     this.value = v;
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
/*     */     AnimatableNumberValue res;
/*  60 */     if (result == null) {
/*  61 */       res = new AnimatableNumberValue(this.target);
/*     */     } else {
/*  63 */       res = (AnimatableNumberValue)result;
/*     */     } 
/*     */     
/*  66 */     float v = this.value;
/*  67 */     if (to != null) {
/*  68 */       AnimatableNumberValue toNumber = (AnimatableNumberValue)to;
/*  69 */       v += interpolation * (toNumber.value - this.value);
/*     */     } 
/*  71 */     if (accumulation != null) {
/*  72 */       AnimatableNumberValue accNumber = (AnimatableNumberValue)accumulation;
/*  73 */       v += multiplier * accNumber.value;
/*     */     } 
/*     */     
/*  76 */     if (res.value != v) {
/*  77 */       res.value = v;
/*  78 */       res.hasChanged = true;
/*     */     } 
/*  80 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValue() {
/*  87 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 103 */     AnimatableNumberValue o = (AnimatableNumberValue)other;
/* 104 */     return Math.abs(this.value - o.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 111 */     return new AnimatableNumberValue(this.target, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 118 */     return formatNumber(this.value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableNumberValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */