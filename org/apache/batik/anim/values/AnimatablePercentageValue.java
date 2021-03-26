/*    */ package org.apache.batik.anim.values;
/*    */ 
/*    */ import org.apache.batik.anim.dom.AnimationTarget;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimatablePercentageValue
/*    */   extends AnimatableNumberValue
/*    */ {
/*    */   protected AnimatablePercentageValue(AnimationTarget target) {
/* 35 */     super(target);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatablePercentageValue(AnimationTarget target, float v) {
/* 42 */     super(target, v);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/* 53 */     if (result == null) {
/* 54 */       result = new AnimatablePercentageValue(this.target);
/*    */     }
/* 56 */     return super.interpolate(result, to, interpolation, accumulation, multiplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatableValue getZeroValue() {
/* 64 */     return new AnimatablePercentageValue(this.target, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 71 */     return super.getCssText() + "%";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatablePercentageValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */