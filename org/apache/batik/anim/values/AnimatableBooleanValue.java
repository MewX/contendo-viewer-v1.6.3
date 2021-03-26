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
/*     */ public class AnimatableBooleanValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected boolean value;
/*     */   
/*     */   protected AnimatableBooleanValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableBooleanValue(AnimationTarget target, boolean b) {
/*  47 */     super(target);
/*  48 */     this.value = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableBooleanValue res;
/*     */     boolean newValue;
/*  60 */     if (result == null) {
/*  61 */       res = new AnimatableBooleanValue(this.target);
/*     */     } else {
/*  63 */       res = (AnimatableBooleanValue)result;
/*     */     } 
/*     */ 
/*     */     
/*  67 */     if (to != null && interpolation >= 0.5D) {
/*  68 */       AnimatableBooleanValue toValue = (AnimatableBooleanValue)to;
/*  69 */       newValue = toValue.value;
/*     */     } else {
/*  71 */       newValue = this.value;
/*     */     } 
/*     */     
/*  74 */     if (res.value != newValue) {
/*  75 */       res.value = newValue;
/*  76 */       res.hasChanged = true;
/*     */     } 
/*  78 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getValue() {
/*  85 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 101 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 108 */     return new AnimatableBooleanValue(this.target, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 115 */     return this.value ? "true" : "false";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableBooleanValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */