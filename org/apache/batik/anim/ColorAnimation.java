/*    */ package org.apache.batik.anim;
/*    */ 
/*    */ import org.apache.batik.anim.dom.AnimatableElement;
/*    */ import org.apache.batik.anim.timing.TimedElement;
/*    */ import org.apache.batik.anim.values.AnimatableValue;
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
/*    */ public class ColorAnimation
/*    */   extends SimpleAnimation
/*    */ {
/*    */   public ColorAnimation(TimedElement timedElement, AnimatableElement animatableElement, int calcMode, float[] keyTimes, float[] keySplines, boolean additive, boolean cumulative, AnimatableValue[] values, AnimatableValue from, AnimatableValue to, AnimatableValue by) {
/* 47 */     super(timedElement, animatableElement, calcMode, keyTimes, keySplines, additive, cumulative, values, from, to, by);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/ColorAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */