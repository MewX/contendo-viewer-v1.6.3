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
/*     */ public class AnimatableIntegerValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected int value;
/*     */   
/*     */   protected AnimatableIntegerValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableIntegerValue(AnimationTarget target, int v) {
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
/*     */     AnimatableIntegerValue res;
/*  60 */     if (result == null) {
/*  61 */       res = new AnimatableIntegerValue(this.target);
/*     */     } else {
/*  63 */       res = (AnimatableIntegerValue)result;
/*     */     } 
/*     */     
/*  66 */     int v = this.value;
/*  67 */     if (to != null) {
/*  68 */       AnimatableIntegerValue toInteger = (AnimatableIntegerValue)to;
/*  69 */       v = (int)(v + this.value + interpolation * (toInteger.getValue() - this.value));
/*     */     } 
/*  71 */     if (accumulation != null) {
/*  72 */       AnimatableIntegerValue accInteger = (AnimatableIntegerValue)accumulation;
/*     */       
/*  74 */       v += multiplier * accInteger.getValue();
/*     */     } 
/*     */     
/*  77 */     if (res.value != v) {
/*  78 */       res.value = v;
/*  79 */       res.hasChanged = true;
/*     */     } 
/*  81 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/*  88 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 104 */     AnimatableIntegerValue o = (AnimatableIntegerValue)other;
/* 105 */     return Math.abs(this.value - o.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 112 */     return new AnimatableIntegerValue(this.target, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 119 */     return Integer.toString(this.value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableIntegerValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */