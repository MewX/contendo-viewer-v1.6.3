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
/*     */ public class AnimatableAngleOrIdentValue
/*     */   extends AnimatableAngleValue
/*     */ {
/*     */   protected boolean isIdent;
/*     */   protected String ident;
/*     */   
/*     */   protected AnimatableAngleOrIdentValue(AnimationTarget target) {
/*  47 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableAngleOrIdentValue(AnimationTarget target, float v, short unit) {
/*  54 */     super(target, v, unit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableAngleOrIdentValue(AnimationTarget target, String ident) {
/*  61 */     super(target);
/*  62 */     this.ident = ident;
/*  63 */     this.isIdent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIdent() {
/*  70 */     return this.isIdent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdent() {
/*  77 */     return this.ident;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/*  93 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 100 */     return new AnimatableAngleOrIdentValue(this.target, 0.0F, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 108 */     if (this.isIdent) {
/* 109 */       return this.ident;
/*     */     }
/* 111 */     return super.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableAngleOrIdentValue res;
/* 122 */     if (result == null) {
/* 123 */       res = new AnimatableAngleOrIdentValue(this.target);
/*     */     } else {
/* 125 */       res = (AnimatableAngleOrIdentValue)result;
/*     */     } 
/*     */     
/* 128 */     if (to == null) {
/* 129 */       if (this.isIdent) {
/* 130 */         res.hasChanged = (!res.isIdent || !res.ident.equals(this.ident));
/* 131 */         res.ident = this.ident;
/* 132 */         res.isIdent = true;
/*     */       } else {
/* 134 */         short oldUnit = res.unit;
/* 135 */         float oldValue = res.value;
/* 136 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */         
/* 138 */         if (res.unit != oldUnit || res.value != oldValue) {
/* 139 */           res.hasChanged = true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 143 */       AnimatableAngleOrIdentValue toValue = (AnimatableAngleOrIdentValue)to;
/*     */       
/* 145 */       if (this.isIdent || toValue.isIdent) {
/* 146 */         if (interpolation >= 0.5D) {
/* 147 */           if (res.isIdent != toValue.isIdent || res.unit != toValue.unit || res.value != toValue.value || (res.isIdent && toValue.isIdent && !toValue.ident.equals(this.ident)))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 152 */             res.isIdent = toValue.isIdent;
/* 153 */             res.ident = toValue.ident;
/* 154 */             res.unit = toValue.unit;
/* 155 */             res.value = toValue.value;
/* 156 */             res.hasChanged = true;
/*     */           }
/*     */         
/* 159 */         } else if (res.isIdent != this.isIdent || res.unit != this.unit || res.value != this.value || (res.isIdent && this.isIdent && !res.ident.equals(this.ident))) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 164 */           res.isIdent = this.isIdent;
/* 165 */           res.ident = this.ident;
/* 166 */           res.unit = this.unit;
/* 167 */           res.value = this.value;
/* 168 */           res.hasChanged = true;
/*     */         } 
/*     */       } else {
/*     */         
/* 172 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 177 */     return res;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableAngleOrIdentValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */