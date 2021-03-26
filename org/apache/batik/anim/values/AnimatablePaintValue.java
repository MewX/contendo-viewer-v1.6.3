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
/*     */ public class AnimatablePaintValue
/*     */   extends AnimatableColorValue
/*     */ {
/*     */   public static final int PAINT_NONE = 0;
/*     */   public static final int PAINT_CURRENT_COLOR = 1;
/*     */   public static final int PAINT_COLOR = 2;
/*     */   public static final int PAINT_URI = 3;
/*     */   public static final int PAINT_URI_NONE = 4;
/*     */   public static final int PAINT_URI_CURRENT_COLOR = 5;
/*     */   public static final int PAINT_URI_COLOR = 6;
/*     */   public static final int PAINT_INHERIT = 7;
/*     */   protected int paintType;
/*     */   protected String uri;
/*     */   
/*     */   protected AnimatablePaintValue(AnimationTarget target) {
/*  55 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatablePaintValue(AnimationTarget target, float r, float g, float b) {
/*  63 */     super(target, r, g, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createNonePaintValue(AnimationTarget target) {
/*  71 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/*  72 */     v.paintType = 0;
/*  73 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createCurrentColorPaintValue(AnimationTarget target) {
/*  81 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/*  82 */     v.paintType = 1;
/*  83 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createColorPaintValue(AnimationTarget target, float r, float g, float b) {
/*  91 */     AnimatablePaintValue v = new AnimatablePaintValue(target, r, g, b);
/*  92 */     v.paintType = 2;
/*  93 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createURIPaintValue(AnimationTarget target, String uri) {
/* 101 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/* 102 */     v.uri = uri;
/* 103 */     v.paintType = 3;
/* 104 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createURINonePaintValue(AnimationTarget target, String uri) {
/* 113 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/* 114 */     v.uri = uri;
/* 115 */     v.paintType = 4;
/* 116 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createURICurrentColorPaintValue(AnimationTarget target, String uri) {
/* 125 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/* 126 */     v.uri = uri;
/* 127 */     v.paintType = 5;
/* 128 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createURIColorPaintValue(AnimationTarget target, String uri, float r, float g, float b) {
/* 137 */     AnimatablePaintValue v = new AnimatablePaintValue(target, r, g, b);
/* 138 */     v.uri = uri;
/* 139 */     v.paintType = 6;
/* 140 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnimatablePaintValue createInheritPaintValue(AnimationTarget target) {
/* 148 */     AnimatablePaintValue v = new AnimatablePaintValue(target);
/* 149 */     v.paintType = 7;
/* 150 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*     */     AnimatablePaintValue res;
/*     */     int newPaintType;
/*     */     String newURI;
/*     */     float newRed;
/*     */     float newGreen;
/*     */     float newBlue;
/* 162 */     if (result == null) {
/* 163 */       res = new AnimatablePaintValue(this.target);
/*     */     } else {
/* 165 */       res = (AnimatablePaintValue)result;
/*     */     } 
/*     */     
/* 168 */     if (this.paintType == 2) {
/* 169 */       boolean canInterpolate = true;
/* 170 */       if (to != null) {
/* 171 */         AnimatablePaintValue toPaint = (AnimatablePaintValue)to;
/* 172 */         canInterpolate = (toPaint.paintType == 2);
/*     */       } 
/* 174 */       if (accumulation != null) {
/* 175 */         AnimatablePaintValue accPaint = (AnimatablePaintValue)accumulation;
/*     */         
/* 177 */         canInterpolate = (canInterpolate && accPaint.paintType == 2);
/*     */       } 
/*     */       
/* 180 */       if (canInterpolate) {
/* 181 */         res.paintType = 2;
/* 182 */         return super.interpolate(res, to, interpolation, accumulation, multiplier);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (to != null && interpolation >= 0.5D) {
/* 192 */       AnimatablePaintValue toValue = (AnimatablePaintValue)to;
/* 193 */       newPaintType = toValue.paintType;
/* 194 */       newURI = toValue.uri;
/* 195 */       newRed = toValue.red;
/* 196 */       newGreen = toValue.green;
/* 197 */       newBlue = toValue.blue;
/*     */     } else {
/* 199 */       newPaintType = this.paintType;
/* 200 */       newURI = this.uri;
/* 201 */       newRed = this.red;
/* 202 */       newGreen = this.green;
/* 203 */       newBlue = this.blue;
/*     */     } 
/*     */     
/* 206 */     if (res.paintType != newPaintType || res.uri == null || !res.uri.equals(newURI) || res.red != newRed || res.green != newGreen || res.blue != newBlue) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       res.paintType = newPaintType;
/* 213 */       res.uri = newURI;
/* 214 */       res.red = newRed;
/* 215 */       res.green = newGreen;
/* 216 */       res.blue = newBlue;
/* 217 */       res.hasChanged = true;
/*     */     } 
/*     */     
/* 220 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPaintType() {
/* 227 */     return this.paintType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURI() {
/* 234 */     return this.uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 250 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 257 */     return createColorPaintValue(this.target, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/* 264 */     switch (this.paintType) {
/*     */       case 0:
/* 266 */         return "none";
/*     */       case 1:
/* 268 */         return "currentColor";
/*     */       case 2:
/* 270 */         return super.getCssText();
/*     */       case 3:
/* 272 */         return "url(" + this.uri + ")";
/*     */       case 4:
/* 274 */         return "url(" + this.uri + ") none";
/*     */       case 5:
/* 276 */         return "url(" + this.uri + ") currentColor";
/*     */       case 6:
/* 278 */         return "url(" + this.uri + ") " + super.getCssText();
/*     */     } 
/* 280 */     return "inherit";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatablePaintValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */