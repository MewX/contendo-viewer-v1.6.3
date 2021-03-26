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
/*    */ public class SetAnimation
/*    */   extends AbstractAnimation
/*    */ {
/*    */   protected AnimatableValue to;
/*    */   
/*    */   public SetAnimation(TimedElement timedElement, AnimatableElement animatableElement, AnimatableValue to) {
/* 44 */     super(timedElement, animatableElement);
/* 45 */     this.to = to;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void sampledAt(float simpleTime, float simpleDur, int repeatIteration) {
/* 53 */     if (this.value == null) {
/* 54 */       this.value = this.to;
/* 55 */       markDirty();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void sampledLastValue(int repeatIteration) {
/* 63 */     if (this.value == null) {
/* 64 */       this.value = this.to;
/* 65 */       markDirty();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/SetAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */