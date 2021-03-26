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
/*     */ public class AnimatableRectValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float width;
/*     */   protected float height;
/*     */   
/*     */   protected AnimatableRectValue(AnimationTarget target) {
/*  55 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableRectValue(AnimationTarget target, float x, float y, float w, float h) {
/*  63 */     super(target);
/*  64 */     this.x = x;
/*  65 */     this.y = y;
/*  66 */     this.width = w;
/*  67 */     this.height = h;
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
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableRectValue res;
/*  80 */     if (result == null) {
/*  81 */       res = new AnimatableRectValue(this.target);
/*     */     } else {
/*  83 */       res = (AnimatableRectValue)result;
/*     */     } 
/*     */     
/*  86 */     float newX = this.x, newY = this.y, newWidth = this.width, newHeight = this.height;
/*  87 */     if (to != null) {
/*  88 */       AnimatableRectValue toValue = (AnimatableRectValue)to;
/*  89 */       newX += interpolation * (toValue.x - this.x);
/*  90 */       newY += interpolation * (toValue.y - this.y);
/*  91 */       newWidth += interpolation * (toValue.width - this.width);
/*  92 */       newHeight += interpolation * (toValue.height - this.height);
/*     */     } 
/*  94 */     if (accumulation != null && multiplier != 0) {
/*  95 */       AnimatableRectValue accValue = (AnimatableRectValue)accumulation;
/*  96 */       newX += multiplier * accValue.x;
/*  97 */       newY += multiplier * accValue.y;
/*  98 */       newWidth += multiplier * accValue.width;
/*  99 */       newHeight += multiplier * accValue.height;
/*     */     } 
/* 101 */     if (res.x != newX || res.y != newY || res.width != newWidth || res.height != newHeight) {
/*     */       
/* 103 */       res.x = newX;
/* 104 */       res.y = newY;
/* 105 */       res.width = newWidth;
/* 106 */       res.height = newHeight;
/* 107 */       res.hasChanged = true;
/*     */     } 
/* 109 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/* 116 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 123 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 130 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 137 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 153 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 160 */     return new AnimatableRectValue(this.target, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 167 */     StringBuffer sb = new StringBuffer();
/* 168 */     sb.append(this.x);
/* 169 */     sb.append(',');
/* 170 */     sb.append(this.y);
/* 171 */     sb.append(',');
/* 172 */     sb.append(this.width);
/* 173 */     sb.append(',');
/* 174 */     sb.append(this.height);
/* 175 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableRectValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */