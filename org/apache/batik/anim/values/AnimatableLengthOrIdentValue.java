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
/*     */ public class AnimatableLengthOrIdentValue
/*     */   extends AnimatableLengthValue
/*     */ {
/*     */   protected boolean isIdent;
/*     */   protected String ident;
/*     */   
/*     */   protected AnimatableLengthOrIdentValue(AnimationTarget target) {
/*  47 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableLengthOrIdentValue(AnimationTarget target, short type, float v, short pcInterp) {
/*  55 */     super(target, type, v, pcInterp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableLengthOrIdentValue(AnimationTarget target, String ident) {
/*  62 */     super(target);
/*  63 */     this.ident = ident;
/*  64 */     this.isIdent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIdent() {
/*  71 */     return this.isIdent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdent() {
/*  78 */     return this.ident;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/*  94 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 101 */     return new AnimatableLengthOrIdentValue(this.target, (short)1, 0.0F, this.percentageInterpretation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 110 */     if (this.isIdent) {
/* 111 */       return this.ident;
/*     */     }
/* 113 */     return super.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatableLengthOrIdentValue res;
/* 124 */     if (result == null) {
/* 125 */       res = new AnimatableLengthOrIdentValue(this.target);
/*     */     } else {
/* 127 */       res = (AnimatableLengthOrIdentValue)result;
/*     */     } 
/*     */     
/* 130 */     if (to == null) {
/* 131 */       if (this.isIdent) {
/* 132 */         res.hasChanged = (!res.isIdent || !res.ident.equals(this.ident));
/* 133 */         res.ident = this.ident;
/* 134 */         res.isIdent = true;
/*     */       } else {
/* 136 */         short oldLengthType = res.lengthType;
/* 137 */         float oldLengthValue = res.lengthValue;
/* 138 */         short oldPercentageInterpretation = res.percentageInterpretation;
/* 139 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */         
/* 141 */         if (res.lengthType != oldLengthType || res.lengthValue != oldLengthValue || res.percentageInterpretation != oldPercentageInterpretation)
/*     */         {
/*     */ 
/*     */           
/* 145 */           res.hasChanged = true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 149 */       AnimatableLengthOrIdentValue toValue = (AnimatableLengthOrIdentValue)to;
/*     */       
/* 151 */       if (this.isIdent || toValue.isIdent) {
/* 152 */         if (interpolation >= 0.5D) {
/* 153 */           if (res.isIdent != toValue.isIdent || res.lengthType != toValue.lengthType || res.lengthValue != toValue.lengthValue || (res.isIdent && toValue.isIdent && !toValue.ident.equals(this.ident)))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 158 */             res.isIdent = toValue.isIdent;
/* 159 */             res.ident = toValue.ident;
/* 160 */             res.lengthType = toValue.lengthType;
/* 161 */             res.lengthValue = toValue.lengthValue;
/* 162 */             res.hasChanged = true;
/*     */           }
/*     */         
/* 165 */         } else if (res.isIdent != this.isIdent || res.lengthType != this.lengthType || res.lengthValue != this.lengthValue || (res.isIdent && this.isIdent && !res.ident.equals(this.ident))) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 170 */           res.isIdent = this.isIdent;
/* 171 */           res.ident = this.ident;
/* 172 */           res.ident = this.ident;
/* 173 */           res.lengthType = this.lengthType;
/* 174 */           res.hasChanged = true;
/*     */         } 
/*     */       } else {
/*     */         
/* 178 */         super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 183 */     return res;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableLengthOrIdentValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */