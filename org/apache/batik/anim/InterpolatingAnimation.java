/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.ext.awt.geom.Cubic;
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
/*     */ public abstract class InterpolatingAnimation
/*     */   extends AbstractAnimation
/*     */ {
/*     */   protected int calcMode;
/*     */   protected float[] keyTimes;
/*     */   protected float[] keySplines;
/*     */   protected Cubic[] keySplineCubics;
/*     */   protected boolean additive;
/*     */   protected boolean cumulative;
/*     */   
/*     */   public InterpolatingAnimation(TimedElement timedElement, AnimatableElement animatableElement, int calcMode, float[] keyTimes, float[] keySplines, boolean additive, boolean cumulative) {
/*  78 */     super(timedElement, animatableElement);
/*  79 */     this.calcMode = calcMode;
/*  80 */     this.keyTimes = keyTimes;
/*  81 */     this.keySplines = keySplines;
/*  82 */     this.additive = additive;
/*  83 */     this.cumulative = cumulative;
/*     */     
/*  85 */     if (calcMode == 3) {
/*  86 */       if (keySplines == null || keySplines.length % 4 != 0) {
/*  87 */         throw timedElement.createException("attribute.malformed", new Object[] { null, "keySplines" });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  92 */       this.keySplineCubics = new Cubic[keySplines.length / 4];
/*  93 */       for (int i = 0; i < keySplines.length / 4; i++) {
/*  94 */         this.keySplineCubics[i] = new Cubic(0.0D, 0.0D, keySplines[i * 4], keySplines[i * 4 + 1], keySplines[i * 4 + 2], keySplines[i * 4 + 3], 1.0D, 1.0D);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (keyTimes != null) {
/* 104 */       boolean invalidKeyTimes = false;
/* 105 */       if (((calcMode == 1 || calcMode == 3 || calcMode == 2) && (keyTimes.length < 2 || keyTimes[0] != 0.0F || keyTimes[keyTimes.length - 1] != 1.0F)) || (calcMode == 0 && (keyTimes.length == 0 || keyTimes[0] != 0.0F)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         invalidKeyTimes = true;
/*     */       }
/* 114 */       if (!invalidKeyTimes) {
/* 115 */         for (int i = 1; i < keyTimes.length; i++) {
/* 116 */           if (keyTimes[i] < 0.0F || keyTimes[1] > 1.0F || keyTimes[i] < keyTimes[i - 1]) {
/*     */             
/* 118 */             invalidKeyTimes = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 123 */       if (invalidKeyTimes) {
/* 124 */         throw timedElement.createException("attribute.malformed", new Object[] { null, "keyTimes" });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean willReplace() {
/* 137 */     return !this.additive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sampledLastValue(int repeatIteration) {
/* 144 */     sampledAtUnitTime(1.0F, repeatIteration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sampledAt(float simpleTime, float simpleDur, int repeatIteration) {
/*     */     float unitTime;
/* 153 */     if (simpleDur == Float.POSITIVE_INFINITY) {
/* 154 */       unitTime = 0.0F;
/*     */     } else {
/* 156 */       unitTime = simpleTime / simpleDur;
/*     */     } 
/* 158 */     sampledAtUnitTime(unitTime, repeatIteration);
/*     */   }
/*     */   
/*     */   protected abstract void sampledAtUnitTime(float paramFloat, int paramInt);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/InterpolatingAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */