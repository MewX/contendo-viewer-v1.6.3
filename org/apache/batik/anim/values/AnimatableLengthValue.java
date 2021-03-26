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
/*     */ public class AnimatableLengthValue
/*     */   extends AnimatableValue
/*     */ {
/*  36 */   protected static final String[] UNITS = new String[] { "", "%", "em", "ex", "px", "cm", "mm", "in", "pt", "pc" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short lengthType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float lengthValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short percentageInterpretation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableLengthValue(AnimationTarget target) {
/*  61 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableLengthValue(AnimationTarget target, short type, float v, short pcInterp) {
/*  69 */     super(target);
/*  70 */     this.lengthType = type;
/*  71 */     this.lengthValue = v;
/*  72 */     this.percentageInterpretation = pcInterp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableLengthValue res;
/*  84 */     if (result == null) {
/*  85 */       res = new AnimatableLengthValue(this.target);
/*     */     } else {
/*  87 */       res = (AnimatableLengthValue)result;
/*     */     } 
/*     */     
/*  90 */     short oldLengthType = res.lengthType;
/*  91 */     float oldLengthValue = res.lengthValue;
/*  92 */     short oldPercentageInterpretation = res.percentageInterpretation;
/*     */     
/*  94 */     res.lengthType = this.lengthType;
/*  95 */     res.lengthValue = this.lengthValue;
/*  96 */     res.percentageInterpretation = this.percentageInterpretation;
/*     */     
/*  98 */     if (to != null) {
/*  99 */       float toValue; AnimatableLengthValue toLength = (AnimatableLengthValue)to;
/*     */       
/* 101 */       if (!compatibleTypes(res.lengthType, res.percentageInterpretation, toLength.lengthType, toLength.percentageInterpretation)) {
/*     */ 
/*     */         
/* 104 */         res.lengthValue = this.target.svgToUserSpace(res.lengthValue, res.lengthType, res.percentageInterpretation);
/*     */ 
/*     */         
/* 107 */         res.lengthType = 1;
/* 108 */         toValue = toLength.target.svgToUserSpace(toLength.lengthValue, toLength.lengthType, toLength.percentageInterpretation);
/*     */       }
/*     */       else {
/*     */         
/* 112 */         toValue = toLength.lengthValue;
/*     */       } 
/* 114 */       res.lengthValue += interpolation * (toValue - res.lengthValue);
/*     */     } 
/*     */     
/* 117 */     if (accumulation != null) {
/* 118 */       float accValue; AnimatableLengthValue accLength = (AnimatableLengthValue)accumulation;
/*     */       
/* 120 */       if (!compatibleTypes(res.lengthType, res.percentageInterpretation, accLength.lengthType, accLength.percentageInterpretation)) {
/*     */ 
/*     */ 
/*     */         
/* 124 */         res.lengthValue = this.target.svgToUserSpace(res.lengthValue, res.lengthType, res.percentageInterpretation);
/*     */ 
/*     */         
/* 127 */         res.lengthType = 1;
/* 128 */         accValue = accLength.target.svgToUserSpace(accLength.lengthValue, accLength.lengthType, accLength.percentageInterpretation);
/*     */       }
/*     */       else {
/*     */         
/* 132 */         accValue = accLength.lengthValue;
/*     */       } 
/* 134 */       res.lengthValue += multiplier * accValue;
/*     */     } 
/*     */     
/* 137 */     if (oldPercentageInterpretation != res.percentageInterpretation || oldLengthType != res.lengthType || oldLengthValue != res.lengthValue)
/*     */     {
/*     */       
/* 140 */       res.hasChanged = true;
/*     */     }
/* 142 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean compatibleTypes(short t1, short pi1, short t2, short pi2) {
/* 154 */     return ((t1 == t2 && (t1 != 2 || pi1 == pi2)) || (t1 == 1 && t2 == 5) || (t1 == 5 && t2 == 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLengthType() {
/* 166 */     return this.lengthType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLengthValue() {
/* 173 */     return this.lengthValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 189 */     AnimatableLengthValue o = (AnimatableLengthValue)other;
/* 190 */     float v1 = this.target.svgToUserSpace(this.lengthValue, this.lengthType, this.percentageInterpretation);
/*     */     
/* 192 */     float v2 = this.target.svgToUserSpace(o.lengthValue, o.lengthType, o.percentageInterpretation);
/*     */     
/* 194 */     return Math.abs(v1 - v2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 201 */     return new AnimatableLengthValue(this.target, (short)1, 0.0F, this.percentageInterpretation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 212 */     return formatNumber(this.lengthValue) + UNITS[this.lengthType - 1];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableLengthValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */