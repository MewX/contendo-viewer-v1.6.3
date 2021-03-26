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
/*     */ public class AnimatableAngleValue
/*     */   extends AnimatableNumberValue
/*     */ {
/*  36 */   protected static final String[] UNITS = new String[] { "", "", "deg", "rad", "grad" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short unit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableAngleValue(AnimationTarget target) {
/*  49 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableAngleValue(AnimationTarget target, float v, short unit) {
/*  56 */     super(target, v);
/*  57 */     this.unit = unit;
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
/*     */     AnimatableAngleValue res;
/*  69 */     if (result == null) {
/*  70 */       res = new AnimatableAngleValue(this.target);
/*     */     } else {
/*  72 */       res = (AnimatableAngleValue)result;
/*     */     } 
/*     */     
/*  75 */     float v = this.value;
/*  76 */     short u = this.unit;
/*  77 */     if (to != null) {
/*  78 */       AnimatableAngleValue toAngle = (AnimatableAngleValue)to;
/*  79 */       if (toAngle.unit != u) {
/*  80 */         v = rad(v, u);
/*  81 */         v += interpolation * (rad(toAngle.value, toAngle.unit) - v);
/*  82 */         u = 3;
/*     */       } else {
/*  84 */         v += interpolation * (toAngle.value - v);
/*     */       } 
/*     */     } 
/*  87 */     if (accumulation != null) {
/*  88 */       AnimatableAngleValue accAngle = (AnimatableAngleValue)accumulation;
/*  89 */       if (accAngle.unit != u) {
/*  90 */         v += multiplier * rad(accAngle.value, accAngle.unit);
/*  91 */         u = 3;
/*     */       } else {
/*  93 */         v += multiplier * accAngle.value;
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     if (res.value != v || res.unit != u) {
/*  98 */       res.value = v;
/*  99 */       res.unit = u;
/* 100 */       res.hasChanged = true;
/*     */     } 
/* 102 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getUnit() {
/* 109 */     return this.unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 117 */     AnimatableAngleValue o = (AnimatableAngleValue)other;
/* 118 */     return Math.abs(rad(this.value, this.unit) - rad(o.value, o.unit));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 125 */     return new AnimatableAngleValue(this.target, 0.0F, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 133 */     return super.getCssText() + UNITS[this.unit];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rad(float v, short unit) {
/* 140 */     switch (unit) {
/*     */       case 3:
/* 142 */         return v;
/*     */       case 4:
/* 144 */         return 3.1415927F * v / 200.0F;
/*     */     } 
/* 146 */     return 3.1415927F * v / 180.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableAngleValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */