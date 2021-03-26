/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.values.AnimatableTransformListValue;
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
/*     */ public class TransformAnimation
/*     */   extends SimpleAnimation
/*     */ {
/*     */   protected short type;
/*     */   protected float[] keyTimes2;
/*     */   protected float[] keyTimes3;
/*     */   
/*     */   public TransformAnimation(TimedElement timedElement, AnimatableElement animatableElement, int calcMode, float[] keyTimes, float[] keySplines, boolean additive, boolean cumulative, AnimatableValue[] values, AnimatableValue from, AnimatableValue to, AnimatableValue by, short type) {
/*  71 */     super(timedElement, animatableElement, (calcMode == 2) ? 1 : calcMode, (calcMode == 2) ? null : keyTimes, keySplines, additive, cumulative, values, from, to, by);
/*     */ 
/*     */ 
/*     */     
/*  75 */     this.calcMode = calcMode;
/*  76 */     this.type = type;
/*     */     
/*  78 */     if (calcMode != 2) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  84 */     int count = this.values.length;
/*     */     
/*  86 */     float[] cumulativeDistances2 = null;
/*  87 */     float[] cumulativeDistances3 = null;
/*  88 */     switch (type) {
/*     */       case 4:
/*  90 */         cumulativeDistances3 = new float[count];
/*  91 */         cumulativeDistances3[0] = 0.0F;
/*     */       
/*     */       case 2:
/*     */       case 3:
/*  95 */         cumulativeDistances2 = new float[count];
/*  96 */         cumulativeDistances2[0] = 0.0F;
/*     */         break;
/*     */     } 
/*  99 */     float[] cumulativeDistances1 = new float[count];
/* 100 */     cumulativeDistances1[0] = 0.0F;
/*     */ 
/*     */     
/* 103 */     for (int i = 1; i < this.values.length; i++) {
/* 104 */       switch (type) {
/*     */         case 4:
/* 106 */           cumulativeDistances3[i] = cumulativeDistances3[i - 1] + ((AnimatableTransformListValue)this.values[i - 1]).distanceTo3(this.values[i]);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/*     */         case 3:
/* 113 */           cumulativeDistances2[i] = cumulativeDistances2[i - 1] + ((AnimatableTransformListValue)this.values[i - 1]).distanceTo2(this.values[i]);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 119 */       cumulativeDistances1[i] = cumulativeDistances1[i - 1] + ((AnimatableTransformListValue)this.values[i - 1]).distanceTo1(this.values[i]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     switch (type) {
/*     */       case 4:
/* 128 */         totalLength = cumulativeDistances3[count - 1];
/* 129 */         this.keyTimes3 = new float[count];
/* 130 */         this.keyTimes3[0] = 0.0F;
/* 131 */         for (j = 1; j < count - 1; j++) {
/* 132 */           this.keyTimes3[j] = cumulativeDistances3[j] / totalLength;
/*     */         }
/* 134 */         this.keyTimes3[count - 1] = 1.0F;
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 138 */         totalLength = cumulativeDistances2[count - 1];
/* 139 */         this.keyTimes2 = new float[count];
/* 140 */         this.keyTimes2[0] = 0.0F;
/* 141 */         for (j = 1; j < count - 1; j++) {
/* 142 */           this.keyTimes2[j] = cumulativeDistances2[j] / totalLength;
/*     */         }
/* 144 */         this.keyTimes2[count - 1] = 1.0F;
/*     */         break;
/*     */     } 
/* 147 */     float totalLength = cumulativeDistances1[count - 1];
/* 148 */     this.keyTimes = new float[count];
/* 149 */     this.keyTimes[0] = 0.0F;
/* 150 */     for (int j = 1; j < count - 1; j++) {
/* 151 */       this.keyTimes[j] = cumulativeDistances1[j] / totalLength;
/*     */     }
/* 153 */     this.keyTimes[count - 1] = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sampledAtUnitTime(float unitTime, int repeatIteration) {
/*     */     AnimatableTransformListValue value1, value2, nextValue1, nextValue2, accumulation;
/* 164 */     if (this.calcMode != 2 || this.type == 5 || this.type == 6) {
/*     */ 
/*     */       
/* 167 */       super.sampledAtUnitTime(unitTime, repeatIteration);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 172 */     AnimatableTransformListValue value3 = null;
/* 173 */     AnimatableTransformListValue nextValue3 = null;
/* 174 */     float interpolation1 = 0.0F, interpolation2 = 0.0F, interpolation3 = 0.0F;
/* 175 */     if (unitTime != 1.0F) {
/* 176 */       switch (this.type) {
/*     */         case 4:
/* 178 */           keyTimeIndex = 0;
/*     */           
/* 180 */           while (keyTimeIndex < this.keyTimes3.length - 1 && unitTime >= this.keyTimes3[keyTimeIndex + 1]) {
/* 181 */             keyTimeIndex++;
/*     */           }
/* 183 */           value3 = (AnimatableTransformListValue)this.values[keyTimeIndex];
/*     */           
/* 185 */           nextValue3 = (AnimatableTransformListValue)this.values[keyTimeIndex + 1];
/*     */           
/* 187 */           interpolation3 = (unitTime - this.keyTimes3[keyTimeIndex]) / (this.keyTimes3[keyTimeIndex + 1] - this.keyTimes3[keyTimeIndex]);
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 192 */       int keyTimeIndex = 0;
/*     */       
/* 194 */       while (keyTimeIndex < this.keyTimes2.length - 1 && unitTime >= this.keyTimes2[keyTimeIndex + 1]) {
/* 195 */         keyTimeIndex++;
/*     */       }
/* 197 */       value2 = (AnimatableTransformListValue)this.values[keyTimeIndex];
/*     */       
/* 199 */       nextValue2 = (AnimatableTransformListValue)this.values[keyTimeIndex + 1];
/*     */       
/* 201 */       interpolation2 = (unitTime - this.keyTimes2[keyTimeIndex]) / (this.keyTimes2[keyTimeIndex + 1] - this.keyTimes2[keyTimeIndex]);
/*     */ 
/*     */ 
/*     */       
/* 205 */       keyTimeIndex = 0;
/*     */       
/* 207 */       while (keyTimeIndex < this.keyTimes.length - 1 && unitTime >= this.keyTimes[keyTimeIndex + 1]) {
/* 208 */         keyTimeIndex++;
/*     */       }
/* 210 */       value1 = (AnimatableTransformListValue)this.values[keyTimeIndex];
/*     */       
/* 212 */       nextValue1 = (AnimatableTransformListValue)this.values[keyTimeIndex + 1];
/*     */       
/* 214 */       interpolation1 = (unitTime - this.keyTimes[keyTimeIndex]) / (this.keyTimes[keyTimeIndex + 1] - this.keyTimes[keyTimeIndex]);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 219 */       value1 = value2 = value3 = (AnimatableTransformListValue)this.values[this.values.length - 1];
/*     */       
/* 221 */       nextValue1 = nextValue2 = nextValue3 = null;
/* 222 */       interpolation1 = interpolation2 = interpolation3 = 1.0F;
/*     */     } 
/* 224 */     if (this.cumulative) {
/* 225 */       accumulation = (AnimatableTransformListValue)this.values[this.values.length - 1];
/*     */     } else {
/*     */       
/* 228 */       accumulation = null;
/*     */     } 
/*     */     
/* 231 */     switch (this.type) {
/*     */       case 4:
/* 233 */         this.value = (AnimatableValue)AnimatableTransformListValue.interpolate((AnimatableTransformListValue)this.value, value1, value2, value3, nextValue1, nextValue2, nextValue3, interpolation1, interpolation2, interpolation3, accumulation, repeatIteration);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 240 */         this.value = (AnimatableValue)AnimatableTransformListValue.interpolate((AnimatableTransformListValue)this.value, value1, value2, nextValue1, nextValue2, interpolation1, interpolation2, accumulation, repeatIteration);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     if (this.value.hasChanged())
/* 248 */       markDirty(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/TransformAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */