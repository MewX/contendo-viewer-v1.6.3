/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
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
/*     */ public class SimpleAnimation
/*     */   extends InterpolatingAnimation
/*     */ {
/*     */   protected AnimatableValue[] values;
/*     */   protected AnimatableValue from;
/*     */   protected AnimatableValue to;
/*     */   protected AnimatableValue by;
/*     */   
/*     */   public SimpleAnimation(TimedElement timedElement, AnimatableElement animatableElement, int calcMode, float[] keyTimes, float[] keySplines, boolean additive, boolean cumulative, AnimatableValue[] values, AnimatableValue from, AnimatableValue to, AnimatableValue by) {
/*  71 */     super(timedElement, animatableElement, calcMode, keyTimes, keySplines, additive, cumulative);
/*     */     
/*  73 */     this.from = from;
/*  74 */     this.to = to;
/*  75 */     this.by = by;
/*     */     
/*  77 */     if (values == null) {
/*  78 */       if (from != null) {
/*  79 */         values = new AnimatableValue[2];
/*  80 */         values[0] = from;
/*  81 */         if (to != null) {
/*  82 */           values[1] = to;
/*  83 */         } else if (by != null) {
/*  84 */           values[1] = from.interpolate(null, null, 0.0F, by, 1);
/*     */         } else {
/*  86 */           throw timedElement.createException("values.to.by.missing", new Object[] { null });
/*     */         }
/*     */       
/*     */       }
/*  90 */       else if (to != null) {
/*  91 */         values = new AnimatableValue[2];
/*  92 */         values[0] = animatableElement.getUnderlyingValue();
/*  93 */         values[1] = to;
/*  94 */         this.cumulative = false;
/*  95 */         this.toAnimation = true;
/*  96 */       } else if (by != null) {
/*  97 */         this.additive = true;
/*  98 */         values = new AnimatableValue[2];
/*  99 */         values[0] = by.getZeroValue();
/* 100 */         values[1] = by;
/*     */       } else {
/* 102 */         throw timedElement.createException("values.to.by.missing", new Object[] { null });
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 107 */     this.values = values;
/*     */     
/* 109 */     if (this.keyTimes != null && calcMode != 2) {
/* 110 */       if (this.keyTimes.length != values.length) {
/* 111 */         throw timedElement.createException("attribute.malformed", new Object[] { null, "keyTimes" });
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 117 */     else if (calcMode == 1 || calcMode == 3 || (calcMode == 2 && !values[0].canPace())) {
/*     */       
/* 119 */       int count = (values.length == 1) ? 2 : values.length;
/* 120 */       this.keyTimes = new float[count];
/* 121 */       for (int i = 0; i < count; i++) {
/* 122 */         this.keyTimes[i] = i / (count - 1);
/*     */       }
/* 124 */     } else if (calcMode == 0) {
/* 125 */       int count = values.length;
/* 126 */       this.keyTimes = new float[count];
/* 127 */       for (int i = 0; i < count; i++) {
/* 128 */         this.keyTimes[i] = i / count;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 133 */       int count = values.length;
/* 134 */       float[] cumulativeDistances = new float[count];
/* 135 */       cumulativeDistances[0] = 0.0F;
/* 136 */       for (int i = 1; i < count; i++) {
/* 137 */         cumulativeDistances[i] = cumulativeDistances[i - 1] + values[i - 1].distanceTo(values[i]);
/*     */       }
/*     */       
/* 140 */       float totalLength = cumulativeDistances[count - 1];
/* 141 */       this.keyTimes = new float[count];
/* 142 */       this.keyTimes[0] = 0.0F;
/* 143 */       for (int j = 1; j < count - 1; j++) {
/* 144 */         this.keyTimes[j] = cumulativeDistances[j] / totalLength;
/*     */       }
/* 146 */       this.keyTimes[count - 1] = 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 150 */     if (calcMode == 3 && keySplines.length != (this.keyTimes.length - 1) * 4)
/*     */     {
/* 152 */       throw timedElement.createException("attribute.malformed", new Object[] { null, "keySplines" });
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
/*     */   protected void sampledAtUnitTime(float unitTime, int repeatIteration) {
/*     */     AnimatableValue value, accumulation, nextValue;
/* 165 */     float interpolation = 0.0F;
/* 166 */     if (unitTime != 1.0F) {
/* 167 */       int keyTimeIndex = 0;
/*     */       
/* 169 */       while (keyTimeIndex < this.keyTimes.length - 1 && unitTime >= this.keyTimes[keyTimeIndex + 1]) {
/* 170 */         keyTimeIndex++;
/*     */       }
/* 172 */       value = this.values[keyTimeIndex];
/* 173 */       if (this.calcMode == 1 || this.calcMode == 2 || this.calcMode == 3) {
/*     */ 
/*     */         
/* 176 */         nextValue = this.values[keyTimeIndex + 1];
/* 177 */         interpolation = (unitTime - this.keyTimes[keyTimeIndex]) / (this.keyTimes[keyTimeIndex + 1] - this.keyTimes[keyTimeIndex]);
/*     */         
/* 179 */         if (this.calcMode == 3 && unitTime != 0.0F) {
/*     */           Point2D.Double p;
/*     */           
/* 182 */           Cubic c = this.keySplineCubics[keyTimeIndex];
/* 183 */           float tolerance = 0.001F;
/* 184 */           float min = 0.0F;
/* 185 */           float max = 1.0F;
/*     */           
/*     */           while (true) {
/* 188 */             float t = (min + max) / 2.0F;
/* 189 */             p = c.eval(t);
/* 190 */             double x = p.getX();
/* 191 */             if (Math.abs(x - interpolation) < tolerance) {
/*     */               break;
/*     */             }
/* 194 */             if (x < interpolation) {
/* 195 */               min = t; continue;
/*     */             } 
/* 197 */             max = t;
/*     */           } 
/*     */           
/* 200 */           interpolation = (float)p.getY();
/*     */         } 
/*     */       } else {
/* 203 */         nextValue = null;
/*     */       } 
/*     */     } else {
/* 206 */       value = this.values[this.values.length - 1];
/* 207 */       nextValue = null;
/*     */     } 
/* 209 */     if (this.cumulative) {
/* 210 */       accumulation = this.values[this.values.length - 1];
/*     */     } else {
/* 212 */       accumulation = null;
/*     */     } 
/*     */     
/* 215 */     this.value = value.interpolate(this.value, nextValue, interpolation, accumulation, repeatIteration);
/*     */     
/* 217 */     if (this.value.hasChanged())
/* 218 */       markDirty(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/SimpleAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */