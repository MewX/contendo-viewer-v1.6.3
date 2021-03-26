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
/*     */ public class AnimatableNumberOrIdentValue
/*     */   extends AnimatableNumberValue
/*     */ {
/*     */   protected boolean isIdent;
/*     */   protected String ident;
/*     */   protected boolean numericIdent;
/*     */   
/*     */   protected AnimatableNumberOrIdentValue(AnimationTarget target) {
/*  51 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOrIdentValue(AnimationTarget target, float v, boolean numericIdent) {
/*  59 */     super(target, v);
/*  60 */     this.numericIdent = numericIdent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableNumberOrIdentValue(AnimationTarget target, String ident) {
/*  67 */     super(target);
/*  68 */     this.ident = ident;
/*  69 */     this.isIdent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/*  85 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/*  92 */     return new AnimatableNumberOrIdentValue(this.target, 0.0F, this.numericIdent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  99 */     if (this.isIdent) {
/* 100 */       return this.ident;
/*     */     }
/* 102 */     if (this.numericIdent) {
/* 103 */       return Integer.toString((int)this.value);
/*     */     }
/* 105 */     return super.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableNumberOrIdentValue res;
/* 116 */     if (result == null) {
/* 117 */       res = new AnimatableNumberOrIdentValue(this.target);
/*     */     } else {
/* 119 */       res = (AnimatableNumberOrIdentValue)result;
/*     */     } 
/*     */     
/* 122 */     if (to == null) {
/* 123 */       if (this.isIdent) {
/* 124 */         res.hasChanged = (!res.isIdent || !res.ident.equals(this.ident));
/* 125 */         res.ident = this.ident;
/* 126 */         res.isIdent = true;
/* 127 */       } else if (this.numericIdent) {
/* 128 */         res.hasChanged = (res.value != this.value || res.isIdent);
/* 129 */         res.value = this.value;
/* 130 */         res.isIdent = false;
/* 131 */         res.hasChanged = true;
/* 132 */         res.numericIdent = true;
/*     */       } else {
/* 134 */         float oldValue = res.value;
/* 135 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */         
/* 137 */         res.numericIdent = false;
/* 138 */         if (res.value != oldValue) {
/* 139 */           res.hasChanged = true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 143 */       AnimatableNumberOrIdentValue toValue = (AnimatableNumberOrIdentValue)to;
/*     */       
/* 145 */       if (this.isIdent || toValue.isIdent || this.numericIdent) {
/* 146 */         if (interpolation >= 0.5D) {
/* 147 */           if (res.isIdent != toValue.isIdent || res.value != toValue.value || (res.isIdent && toValue.isIdent && !toValue.ident.equals(this.ident)))
/*     */           {
/*     */ 
/*     */             
/* 151 */             res.isIdent = toValue.isIdent;
/* 152 */             res.ident = toValue.ident;
/* 153 */             res.value = toValue.value;
/* 154 */             res.numericIdent = toValue.numericIdent;
/* 155 */             res.hasChanged = true;
/*     */           }
/*     */         
/* 158 */         } else if (res.isIdent != this.isIdent || res.value != this.value || (res.isIdent && this.isIdent && !res.ident.equals(this.ident))) {
/*     */ 
/*     */ 
/*     */           
/* 162 */           res.isIdent = this.isIdent;
/* 163 */           res.ident = this.ident;
/* 164 */           res.value = this.value;
/* 165 */           res.numericIdent = this.numericIdent;
/* 166 */           res.hasChanged = true;
/*     */         } 
/*     */       } else {
/*     */         
/* 170 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */         
/* 172 */         res.numericIdent = false;
/*     */       } 
/*     */     } 
/* 175 */     return res;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableNumberOrIdentValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */