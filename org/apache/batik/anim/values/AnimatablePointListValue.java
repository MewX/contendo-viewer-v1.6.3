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
/*    */ public class AnimatablePointListValue
/*    */   extends AnimatableNumberListValue
/*    */ {
/*    */   protected AnimatablePointListValue(AnimationTarget target) {
/* 35 */     super(target);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatablePointListValue(AnimationTarget target, float[] numbers) {
/* 42 */     super(target, numbers);
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
/* 54 */       result = new AnimatablePointListValue(this.target);
/*    */     }
/* 56 */     return super.interpolate(result, to, interpolation, accumulation, multiplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPace() {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float distanceTo(AnimatableValue other) {
/* 73 */     return 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatableValue getZeroValue() {
/* 80 */     float[] ns = new float[this.numbers.length];
/* 81 */     return new AnimatablePointListValue(this.target, ns);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatablePointListValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */