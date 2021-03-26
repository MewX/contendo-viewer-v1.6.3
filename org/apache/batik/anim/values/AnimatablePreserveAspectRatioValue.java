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
/*     */ public class AnimatablePreserveAspectRatioValue
/*     */   extends AnimatableValue
/*     */ {
/*  37 */   protected static final String[] ALIGN_VALUES = new String[] { null, "none", "xMinYMin", "xMidYMin", "xMaxYMin", "xMinYMid", "xMidYMid", "xMaxYMid", "xMinYMax", "xMidYMax", "xMaxYMax" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected static final String[] MEET_OR_SLICE_VALUES = new String[] { null, "meet", "slice" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short align;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short meetOrSlice;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatablePreserveAspectRatioValue(AnimationTarget target) {
/*  74 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatablePreserveAspectRatioValue(AnimationTarget target, short align, short meetOrSlice) {
/*  82 */     super(target);
/*  83 */     this.align = align;
/*  84 */     this.meetOrSlice = meetOrSlice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatablePreserveAspectRatioValue res;
/*     */     short newAlign;
/*     */     short newMeetOrSlice;
/*  96 */     if (result == null) {
/*  97 */       res = new AnimatablePreserveAspectRatioValue(this.target);
/*     */     } else {
/*  99 */       res = (AnimatablePreserveAspectRatioValue)result;
/*     */     } 
/*     */ 
/*     */     
/* 103 */     if (to != null && interpolation >= 0.5D) {
/* 104 */       AnimatablePreserveAspectRatioValue toValue = (AnimatablePreserveAspectRatioValue)to;
/*     */       
/* 106 */       newAlign = toValue.align;
/* 107 */       newMeetOrSlice = toValue.meetOrSlice;
/*     */     } else {
/* 109 */       newAlign = this.align;
/* 110 */       newMeetOrSlice = this.meetOrSlice;
/*     */     } 
/*     */     
/* 113 */     if (res.align != newAlign || res.meetOrSlice != newMeetOrSlice) {
/* 114 */       res.align = this.align;
/* 115 */       res.meetOrSlice = this.meetOrSlice;
/* 116 */       res.hasChanged = true;
/*     */     } 
/* 118 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAlign() {
/* 125 */     return this.align;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMeetOrSlice() {
/* 132 */     return this.meetOrSlice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 148 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 155 */     return new AnimatablePreserveAspectRatioValue(this.target, (short)1, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 164 */     if (this.align < 1 || this.align > 10) {
/* 165 */       return null;
/*     */     }
/* 167 */     String value = ALIGN_VALUES[this.align];
/* 168 */     if (this.align == 1) {
/* 169 */       return value;
/*     */     }
/* 171 */     if (this.meetOrSlice < 1 || this.meetOrSlice > 2) {
/* 172 */       return null;
/*     */     }
/* 174 */     return value + ' ' + MEET_OR_SLICE_VALUES[this.meetOrSlice];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatablePreserveAspectRatioValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */