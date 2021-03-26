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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimatableLengthListValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected short[] lengthTypes;
/*     */   protected float[] lengthValues;
/*     */   protected short percentageInterpretation;
/*     */   
/*     */   protected AnimatableLengthListValue(AnimationTarget target) {
/*  54 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableLengthListValue(AnimationTarget target, short[] types, float[] values, short pcInterp) {
/*  62 */     super(target);
/*  63 */     this.lengthTypes = types;
/*  64 */     this.lengthValues = values;
/*  65 */     this.percentageInterpretation = pcInterp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     short[] baseLengthTypes;
/*     */     float[] baseLengthValues;
/*  76 */     AnimatableLengthListValue res, toLengthList = (AnimatableLengthListValue)to;
/*  77 */     AnimatableLengthListValue accLengthList = (AnimatableLengthListValue)accumulation;
/*     */ 
/*     */     
/*  80 */     boolean hasTo = (to != null);
/*  81 */     boolean hasAcc = (accumulation != null);
/*  82 */     boolean canInterpolate = ((!hasTo || toLengthList.lengthTypes.length == this.lengthTypes.length) && (!hasAcc || accLengthList.lengthTypes.length == this.lengthTypes.length));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (!canInterpolate && hasTo && interpolation >= 0.5D) {
/*  89 */       baseLengthTypes = toLengthList.lengthTypes;
/*  90 */       baseLengthValues = toLengthList.lengthValues;
/*     */     } else {
/*  92 */       baseLengthTypes = this.lengthTypes;
/*  93 */       baseLengthValues = this.lengthValues;
/*     */     } 
/*  95 */     int len = baseLengthTypes.length;
/*     */ 
/*     */     
/*  98 */     if (result == null) {
/*  99 */       res = new AnimatableLengthListValue(this.target);
/* 100 */       res.lengthTypes = new short[len];
/* 101 */       res.lengthValues = new float[len];
/*     */     } else {
/* 103 */       res = (AnimatableLengthListValue)result;
/* 104 */       if (res.lengthTypes == null || res.lengthTypes.length != len) {
/* 105 */         res.lengthTypes = new short[len];
/* 106 */         res.lengthValues = new float[len];
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     res.hasChanged = (this.percentageInterpretation != res.percentageInterpretation);
/*     */     
/* 112 */     res.percentageInterpretation = this.percentageInterpretation;
/*     */     
/* 114 */     for (int i = 0; i < len; i++) {
/* 115 */       float toV = 0.0F, accV = 0.0F;
/* 116 */       short newLengthType = baseLengthTypes[i];
/* 117 */       float newLengthValue = baseLengthValues[i];
/* 118 */       if (canInterpolate) {
/* 119 */         if ((hasTo && !AnimatableLengthValue.compatibleTypes(newLengthType, this.percentageInterpretation, toLengthList.lengthTypes[i], toLengthList.percentageInterpretation)) || (hasAcc && !AnimatableLengthValue.compatibleTypes(newLengthType, this.percentageInterpretation, accLengthList.lengthTypes[i], accLengthList.percentageInterpretation))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 129 */           newLengthValue = this.target.svgToUserSpace(newLengthValue, newLengthType, this.percentageInterpretation);
/*     */ 
/*     */           
/* 132 */           newLengthType = 1;
/* 133 */           if (hasTo) {
/* 134 */             toV = to.target.svgToUserSpace(toLengthList.lengthValues[i], toLengthList.lengthTypes[i], toLengthList.percentageInterpretation);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 139 */           if (hasAcc) {
/* 140 */             accV = accumulation.target.svgToUserSpace(accLengthList.lengthValues[i], accLengthList.lengthTypes[i], accLengthList.percentageInterpretation);
/*     */           
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 146 */           if (hasTo) {
/* 147 */             toV = toLengthList.lengthValues[i];
/*     */           }
/* 149 */           if (hasAcc) {
/* 150 */             accV = accLengthList.lengthValues[i];
/*     */           }
/*     */         } 
/* 153 */         newLengthValue += interpolation * (toV - newLengthValue) + multiplier * accV;
/*     */       } 
/*     */ 
/*     */       
/* 157 */       if (!res.hasChanged) {
/* 158 */         res.hasChanged = (newLengthType != res.lengthTypes[i] || newLengthValue != res.lengthValues[i]);
/*     */       }
/*     */       
/* 161 */       res.lengthTypes[i] = newLengthType;
/* 162 */       res.lengthValues[i] = newLengthValue;
/*     */     } 
/*     */     
/* 165 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] getLengthTypes() {
/* 172 */     return this.lengthTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getLengthValues() {
/* 179 */     return this.lengthValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 195 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 202 */     float[] vs = new float[this.lengthValues.length];
/* 203 */     return new AnimatableLengthListValue(this.target, this.lengthTypes, vs, this.percentageInterpretation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 212 */     StringBuffer sb = new StringBuffer();
/* 213 */     if (this.lengthValues.length > 0) {
/* 214 */       sb.append(formatNumber(this.lengthValues[0]));
/* 215 */       sb.append(AnimatableLengthValue.UNITS[this.lengthTypes[0] - 1]);
/*     */     } 
/* 217 */     for (int i = 1; i < this.lengthValues.length; i++) {
/* 218 */       sb.append(',');
/* 219 */       sb.append(formatNumber(this.lengthValues[i]));
/* 220 */       sb.append(AnimatableLengthValue.UNITS[this.lengthTypes[i] - 1]);
/*     */     } 
/* 222 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableLengthListValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */