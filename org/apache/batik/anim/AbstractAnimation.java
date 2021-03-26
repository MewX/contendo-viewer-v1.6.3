/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
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
/*     */ public abstract class AbstractAnimation
/*     */ {
/*     */   public static final short CALC_MODE_DISCRETE = 0;
/*     */   public static final short CALC_MODE_LINEAR = 1;
/*     */   public static final short CALC_MODE_PACED = 2;
/*     */   public static final short CALC_MODE_SPLINE = 3;
/*     */   protected TimedElement timedElement;
/*     */   protected AnimatableElement animatableElement;
/*     */   protected AbstractAnimation lowerAnimation;
/*     */   protected AbstractAnimation higherAnimation;
/*     */   protected boolean isDirty;
/*     */   protected boolean isActive;
/*     */   protected boolean isFrozen;
/*     */   protected float beginTime;
/*     */   protected AnimatableValue value;
/*     */   protected AnimatableValue composedValue;
/*     */   protected boolean usesUnderlyingValue;
/*     */   protected boolean toAnimation;
/*     */   
/*     */   protected AbstractAnimation(TimedElement timedElement, AnimatableElement animatableElement) {
/* 109 */     this.timedElement = timedElement;
/* 110 */     this.animatableElement = animatableElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimedElement getTimedElement() {
/* 117 */     return this.timedElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getValue() {
/* 124 */     if (!this.isActive && !this.isFrozen) {
/* 125 */       return null;
/*     */     }
/* 127 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getComposedValue() {
/* 136 */     if (!this.isActive && !this.isFrozen) {
/* 137 */       return null;
/*     */     }
/* 139 */     if (this.isDirty) {
/*     */ 
/*     */       
/* 142 */       AnimatableValue lowerValue = null;
/* 143 */       if (!willReplace())
/*     */       {
/* 145 */         if (this.lowerAnimation == null) {
/* 146 */           lowerValue = this.animatableElement.getUnderlyingValue();
/* 147 */           this.usesUnderlyingValue = true;
/*     */         } else {
/* 149 */           lowerValue = this.lowerAnimation.getComposedValue();
/* 150 */           this.usesUnderlyingValue = false;
/*     */         } 
/*     */       }
/*     */       
/* 154 */       this.composedValue = this.value.interpolate(this.composedValue, null, 0.0F, lowerValue, 1);
/*     */ 
/*     */       
/* 157 */       this.isDirty = false;
/*     */     } 
/* 159 */     return this.composedValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 167 */     return this.timedElement.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean usesUnderlyingValue() {
/* 174 */     return (this.usesUnderlyingValue || this.toAnimation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean willReplace() {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void markDirty() {
/* 190 */     this.isDirty = true;
/* 191 */     if (this.higherAnimation != null && !this.higherAnimation.willReplace() && !this.higherAnimation.isDirty)
/*     */     {
/*     */       
/* 194 */       this.higherAnimation.markDirty();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void sampledLastValue(int repeatIteration) {}
/*     */   
/*     */   protected abstract void sampledAt(float paramFloat1, float paramFloat2, int paramInt);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/AbstractAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */