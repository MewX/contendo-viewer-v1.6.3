/*     */ package org.apache.batik.anim.values;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Locale;
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
/*     */ public abstract class AnimatableValue
/*     */ {
/*  38 */   protected static DecimalFormat decimalFormat = new DecimalFormat("0.0###########################################################", new DecimalFormatSymbols(Locale.ENGLISH));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimationTarget target;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasChanged = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue(AnimationTarget target) {
/*  58 */     this.target = target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatNumber(float f) {
/*  65 */     return decimalFormat.format(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract AnimatableValue interpolate(AnimatableValue paramAnimatableValue1, AnimatableValue paramAnimatableValue2, float paramFloat, AnimatableValue paramAnimatableValue3, int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canPace();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float distanceTo(AnimatableValue paramAnimatableValue);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract AnimatableValue getZeroValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChanged() {
/* 114 */     boolean ret = this.hasChanged;
/* 115 */     this.hasChanged = false;
/* 116 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 124 */     return getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 132 */     return getClass().getName() + "[" + toStringRep() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */