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
/*     */ public class AnimatableStringValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected String string;
/*     */   
/*     */   protected AnimatableStringValue(AnimationTarget target) {
/*  40 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableStringValue(AnimationTarget target, String s) {
/*  47 */     super(target);
/*  48 */     this.string = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableStringValue res;
/*     */     String newString;
/*  60 */     if (result == null) {
/*  61 */       res = new AnimatableStringValue(this.target);
/*     */     } else {
/*  63 */       res = (AnimatableStringValue)result;
/*     */     } 
/*     */ 
/*     */     
/*  67 */     if (to != null && interpolation >= 0.5D) {
/*  68 */       AnimatableStringValue toValue = (AnimatableStringValue)to;
/*     */       
/*  70 */       newString = toValue.string;
/*     */     } else {
/*  72 */       newString = this.string;
/*     */     } 
/*     */     
/*  75 */     if (res.string == null || !res.string.equals(newString)) {
/*  76 */       res.string = newString;
/*  77 */       res.hasChanged = true;
/*     */     } 
/*  79 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() {
/*  86 */     return this.string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 102 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 109 */     return new AnimatableStringValue(this.target, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 116 */     return this.string;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableStringValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */