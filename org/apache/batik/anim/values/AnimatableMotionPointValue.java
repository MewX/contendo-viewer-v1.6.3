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
/*     */ public class AnimatableMotionPointValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float angle;
/*     */   
/*     */   protected AnimatableMotionPointValue(AnimationTarget target) {
/*  50 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableMotionPointValue(AnimationTarget target, float x, float y, float angle) {
/*  58 */     super(target);
/*  59 */     this.x = x;
/*  60 */     this.y = y;
/*  61 */     this.angle = angle;
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
/*     */     AnimatableMotionPointValue res;
/*  73 */     if (result == null) {
/*  74 */       res = new AnimatableMotionPointValue(this.target);
/*     */     } else {
/*  76 */       res = (AnimatableMotionPointValue)result;
/*     */     } 
/*     */     
/*  79 */     float newX = this.x, newY = this.y, newAngle = this.angle;
/*  80 */     int angleCount = 1;
/*     */     
/*  82 */     if (to != null) {
/*  83 */       AnimatableMotionPointValue toValue = (AnimatableMotionPointValue)to;
/*     */       
/*  85 */       newX += interpolation * (toValue.x - this.x);
/*  86 */       newY += interpolation * (toValue.y - this.y);
/*  87 */       newAngle += toValue.angle;
/*  88 */       angleCount++;
/*     */     } 
/*  90 */     if (accumulation != null && multiplier != 0) {
/*  91 */       AnimatableMotionPointValue accValue = (AnimatableMotionPointValue)accumulation;
/*     */       
/*  93 */       newX += multiplier * accValue.x;
/*  94 */       newY += multiplier * accValue.y;
/*  95 */       newAngle += accValue.angle;
/*  96 */       angleCount++;
/*     */     } 
/*  98 */     newAngle /= angleCount;
/*     */     
/* 100 */     if (res.x != newX || res.y != newY || res.angle != newAngle) {
/* 101 */       res.x = newX;
/* 102 */       res.y = newY;
/* 103 */       res.angle = newAngle;
/* 104 */       res.hasChanged = true;
/*     */     } 
/* 106 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/* 113 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 120 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAngle() {
/* 127 */     return this.angle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 143 */     AnimatableMotionPointValue o = (AnimatableMotionPointValue)other;
/* 144 */     float dx = this.x - o.x;
/* 145 */     float dy = this.y - o.y;
/* 146 */     return (float)Math.sqrt((dx * dx + dy * dy));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 153 */     return new AnimatableMotionPointValue(this.target, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 160 */     StringBuffer sb = new StringBuffer();
/* 161 */     sb.append(formatNumber(this.x));
/* 162 */     sb.append(',');
/* 163 */     sb.append(formatNumber(this.y));
/* 164 */     sb.append(',');
/* 165 */     sb.append(formatNumber(this.angle));
/* 166 */     sb.append("rad");
/* 167 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableMotionPointValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */