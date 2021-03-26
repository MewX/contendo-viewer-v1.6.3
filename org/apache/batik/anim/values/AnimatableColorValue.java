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
/*     */ public class AnimatableColorValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected float red;
/*     */   protected float green;
/*     */   protected float blue;
/*     */   
/*     */   protected AnimatableColorValue(AnimationTarget target) {
/*  50 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableColorValue(AnimationTarget target, float r, float g, float b) {
/*  58 */     super(target);
/*  59 */     this.red = r;
/*  60 */     this.green = g;
/*  61 */     this.blue = b;
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
/*     */     AnimatableColorValue res;
/*  73 */     if (result == null) {
/*  74 */       res = new AnimatableColorValue(this.target);
/*     */     } else {
/*  76 */       res = (AnimatableColorValue)result;
/*     */     } 
/*     */     
/*  79 */     float oldRed = res.red;
/*  80 */     float oldGreen = res.green;
/*  81 */     float oldBlue = res.blue;
/*     */     
/*  83 */     res.red = this.red;
/*  84 */     res.green = this.green;
/*  85 */     res.blue = this.blue;
/*     */     
/*  87 */     AnimatableColorValue toColor = (AnimatableColorValue)to;
/*  88 */     AnimatableColorValue accColor = (AnimatableColorValue)accumulation;
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (to != null) {
/*  93 */       res.red += interpolation * (toColor.red - res.red);
/*  94 */       res.green += interpolation * (toColor.green - res.green);
/*  95 */       res.blue += interpolation * (toColor.blue - res.blue);
/*     */     } 
/*     */     
/*  98 */     if (accumulation != null) {
/*  99 */       res.red += multiplier * accColor.red;
/* 100 */       res.green += multiplier * accColor.green;
/* 101 */       res.blue += multiplier * accColor.blue;
/*     */     } 
/*     */     
/* 104 */     if (res.red != oldRed || res.green != oldGreen || res.blue != oldBlue) {
/* 105 */       res.hasChanged = true;
/*     */     }
/* 107 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 123 */     AnimatableColorValue o = (AnimatableColorValue)other;
/* 124 */     float dr = this.red - o.red;
/* 125 */     float dg = this.green - o.green;
/* 126 */     float db = this.blue - o.blue;
/* 127 */     return (float)Math.sqrt((dr * dr + dg * dg + db * db));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 134 */     return new AnimatableColorValue(this.target, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 141 */     return "rgb(" + Math.round(this.red * 255.0F) + ',' + Math.round(this.green * 255.0F) + ',' + Math.round(this.blue * 255.0F) + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableColorValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */