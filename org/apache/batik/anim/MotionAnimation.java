/*     */ package org.apache.batik.anim;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.values.AnimatableAngleValue;
/*     */ import org.apache.batik.anim.values.AnimatableMotionPointValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.ext.awt.geom.Cubic;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
/*     */ import org.apache.batik.ext.awt.geom.ExtendedPathIterator;
/*     */ import org.apache.batik.ext.awt.geom.PathLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MotionAnimation
/*     */   extends InterpolatingAnimation
/*     */ {
/*     */   protected ExtendedGeneralPath path;
/*     */   protected PathLength pathLength;
/*     */   protected float[] keyPoints;
/*     */   protected boolean rotateAuto;
/*     */   protected boolean rotateAutoReverse;
/*     */   protected float rotateAngle;
/*     */   
/*     */   public MotionAnimation(TimedElement timedElement, AnimatableElement animatableElement, int calcMode, float[] keyTimes, float[] keySplines, boolean additive, boolean cumulative, AnimatableValue[] values, AnimatableValue from, AnimatableValue to, AnimatableValue by, ExtendedGeneralPath path, float[] keyPoints, boolean rotateAuto, boolean rotateAutoReverse, float rotateAngle, short rotateAngleUnit) {
/*  94 */     super(timedElement, animatableElement, calcMode, keyTimes, keySplines, additive, cumulative);
/*     */     
/*  96 */     this.rotateAuto = rotateAuto;
/*  97 */     this.rotateAutoReverse = rotateAutoReverse;
/*  98 */     this.rotateAngle = AnimatableAngleValue.rad(rotateAngle, rotateAngleUnit);
/*     */     
/* 100 */     if (path == null) {
/* 101 */       path = new ExtendedGeneralPath();
/* 102 */       if (values == null || values.length == 0) {
/* 103 */         if (from != null) {
/* 104 */           AnimatableMotionPointValue fromPt = (AnimatableMotionPointValue)from;
/* 105 */           float x = fromPt.getX();
/* 106 */           float y = fromPt.getY();
/* 107 */           path.moveTo(x, y);
/* 108 */           if (to != null) {
/* 109 */             AnimatableMotionPointValue toPt = (AnimatableMotionPointValue)to;
/* 110 */             path.lineTo(toPt.getX(), toPt.getY());
/* 111 */           } else if (by != null) {
/* 112 */             AnimatableMotionPointValue byPt = (AnimatableMotionPointValue)by;
/* 113 */             path.lineTo(x + byPt.getX(), y + byPt.getY());
/*     */           } else {
/* 115 */             throw timedElement.createException("values.to.by.path.missing", new Object[] { null });
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 120 */         else if (to != null) {
/* 121 */           AnimatableMotionPointValue unPt = (AnimatableMotionPointValue)animatableElement.getUnderlyingValue();
/*     */           
/* 123 */           AnimatableMotionPointValue toPt = (AnimatableMotionPointValue)to;
/* 124 */           path.moveTo(unPt.getX(), unPt.getY());
/* 125 */           path.lineTo(toPt.getX(), toPt.getY());
/* 126 */           this.cumulative = false;
/* 127 */         } else if (by != null) {
/* 128 */           AnimatableMotionPointValue byPt = (AnimatableMotionPointValue)by;
/* 129 */           path.moveTo(0.0F, 0.0F);
/* 130 */           path.lineTo(byPt.getX(), byPt.getY());
/* 131 */           this.additive = true;
/*     */         } else {
/* 133 */           throw timedElement.createException("values.to.by.path.missing", new Object[] { null });
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 139 */         AnimatableMotionPointValue pt = (AnimatableMotionPointValue)values[0];
/* 140 */         path.moveTo(pt.getX(), pt.getY());
/* 141 */         for (int i = 1; i < values.length; i++) {
/* 142 */           pt = (AnimatableMotionPointValue)values[i];
/* 143 */           path.lineTo(pt.getX(), pt.getY());
/*     */         } 
/*     */       } 
/*     */     } 
/* 147 */     this.path = path;
/* 148 */     this.pathLength = new PathLength((Shape)path);
/* 149 */     int segments = 0;
/* 150 */     ExtendedPathIterator epi = path.getExtendedPathIterator();
/* 151 */     while (!epi.isDone()) {
/* 152 */       int type = epi.currentSegment();
/* 153 */       if (type != 0) {
/* 154 */         segments++;
/*     */       }
/* 156 */       epi.next();
/*     */     } 
/*     */     
/* 159 */     int count = (keyPoints == null) ? (segments + 1) : keyPoints.length;
/* 160 */     float totalLength = this.pathLength.lengthOfPath();
/* 161 */     if (this.keyTimes != null && calcMode != 2) {
/* 162 */       if (this.keyTimes.length != count) {
/* 163 */         throw timedElement.createException("attribute.malformed", new Object[] { null, "keyTimes" });
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 169 */     else if (calcMode == 1 || calcMode == 3) {
/* 170 */       this.keyTimes = new float[count];
/* 171 */       for (int i = 0; i < count; i++) {
/* 172 */         this.keyTimes[i] = i / (count - 1);
/*     */       }
/* 174 */     } else if (calcMode == 0) {
/* 175 */       this.keyTimes = new float[count];
/* 176 */       for (int i = 0; i < count; i++) {
/* 177 */         this.keyTimes[i] = i / count;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 182 */       epi = path.getExtendedPathIterator();
/* 183 */       this.keyTimes = new float[count];
/* 184 */       int j = 0;
/* 185 */       for (int i = 0; i < count - 1; i++) {
/* 186 */         while (epi.currentSegment() == 0) {
/*     */           
/* 188 */           j++;
/* 189 */           epi.next();
/*     */         } 
/* 191 */         this.keyTimes[i] = this.pathLength.getLengthAtSegment(j) / totalLength;
/*     */         
/* 193 */         j++;
/* 194 */         epi.next();
/*     */       } 
/* 196 */       this.keyTimes[count - 1] = 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (keyPoints != null) {
/* 201 */       if (keyPoints.length != this.keyTimes.length) {
/* 202 */         throw timedElement.createException("attribute.malformed", new Object[] { null, "keyPoints" });
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 208 */       epi = path.getExtendedPathIterator();
/* 209 */       keyPoints = new float[count];
/* 210 */       int j = 0;
/* 211 */       for (int i = 0; i < count - 1; i++) {
/* 212 */         while (epi.currentSegment() == 0) {
/*     */           
/* 214 */           j++;
/* 215 */           epi.next();
/*     */         } 
/* 217 */         keyPoints[i] = this.pathLength.getLengthAtSegment(j) / totalLength;
/* 218 */         j++;
/* 219 */         epi.next();
/*     */       } 
/* 221 */       keyPoints[count - 1] = 1.0F;
/*     */     } 
/* 223 */     this.keyPoints = keyPoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void sampledAtUnitTime(float unitTime, int repeatIteration) {
/*     */     AnimatableMotionPointValue animatableMotionPointValue;
/*     */     AnimatableValue accumulation;
/* 232 */     float interpolation = 0.0F;
/* 233 */     if (unitTime != 1.0F) {
/* 234 */       float ang; int keyTimeIndex = 0;
/*     */       
/* 236 */       while (keyTimeIndex < this.keyTimes.length - 1 && unitTime >= this.keyTimes[keyTimeIndex + 1]) {
/* 237 */         keyTimeIndex++;
/*     */       }
/* 239 */       if (keyTimeIndex == this.keyTimes.length - 1 && this.calcMode == 0) {
/* 240 */         keyTimeIndex = this.keyTimes.length - 2;
/* 241 */         interpolation = 1.0F;
/*     */       }
/* 243 */       else if (this.calcMode == 1 || this.calcMode == 2 || this.calcMode == 3) {
/*     */         
/* 245 */         if (unitTime == 0.0F) {
/* 246 */           interpolation = 0.0F;
/*     */         } else {
/* 248 */           interpolation = (unitTime - this.keyTimes[keyTimeIndex]) / (this.keyTimes[keyTimeIndex + 1] - this.keyTimes[keyTimeIndex]);
/*     */         } 
/*     */         
/* 251 */         if (this.calcMode == 3 && unitTime != 0.0F) {
/*     */           Point2D.Double double_;
/*     */           
/* 254 */           Cubic c = this.keySplineCubics[keyTimeIndex];
/* 255 */           float tolerance = 0.001F;
/* 256 */           float min = 0.0F;
/* 257 */           float max = 1.0F;
/*     */           
/*     */           while (true) {
/* 260 */             float t = (min + max) / 2.0F;
/* 261 */             double_ = c.eval(t);
/* 262 */             double x = double_.getX();
/* 263 */             if (Math.abs(x - interpolation) < tolerance) {
/*     */               break;
/*     */             }
/* 266 */             if (x < interpolation) {
/* 267 */               min = t; continue;
/*     */             } 
/* 269 */             max = t;
/*     */           } 
/*     */           
/* 272 */           interpolation = (float)double_.getY();
/*     */         } 
/*     */       } 
/*     */       
/* 276 */       float point = this.keyPoints[keyTimeIndex];
/* 277 */       if (interpolation != 0.0F) {
/* 278 */         point += interpolation * (this.keyPoints[keyTimeIndex + 1] - this.keyPoints[keyTimeIndex]);
/*     */       }
/*     */       
/* 281 */       point *= this.pathLength.lengthOfPath();
/* 282 */       Point2D p = this.pathLength.pointAtLength(point);
/*     */       
/* 284 */       if (this.rotateAuto) {
/* 285 */         ang = this.pathLength.angleAtLength(point);
/* 286 */         if (this.rotateAutoReverse) {
/* 287 */           ang = (float)(ang + Math.PI);
/*     */         }
/*     */       } else {
/* 290 */         ang = this.rotateAngle;
/*     */       } 
/* 292 */       animatableMotionPointValue = new AnimatableMotionPointValue(null, (float)p.getX(), (float)p.getY(), ang);
/*     */     } else {
/*     */       float ang;
/* 295 */       Point2D p = this.pathLength.pointAtLength(this.pathLength.lengthOfPath());
/*     */       
/* 297 */       if (this.rotateAuto) {
/* 298 */         ang = this.pathLength.angleAtLength(this.pathLength.lengthOfPath());
/* 299 */         if (this.rotateAutoReverse) {
/* 300 */           ang = (float)(ang + Math.PI);
/*     */         }
/*     */       } else {
/* 303 */         ang = this.rotateAngle;
/*     */       } 
/* 305 */       animatableMotionPointValue = new AnimatableMotionPointValue(null, (float)p.getX(), (float)p.getY(), ang);
/*     */     } 
/*     */     
/* 308 */     if (this.cumulative) {
/* 309 */       float ang; Point2D p = this.pathLength.pointAtLength(this.pathLength.lengthOfPath());
/*     */       
/* 311 */       if (this.rotateAuto) {
/* 312 */         ang = this.pathLength.angleAtLength(this.pathLength.lengthOfPath());
/* 313 */         if (this.rotateAutoReverse) {
/* 314 */           ang = (float)(ang + Math.PI);
/*     */         }
/*     */       } else {
/* 317 */         ang = this.rotateAngle;
/*     */       } 
/* 319 */       AnimatableMotionPointValue animatableMotionPointValue1 = new AnimatableMotionPointValue(null, (float)p.getX(), (float)p.getY(), ang);
/*     */     } else {
/*     */       
/* 322 */       accumulation = null;
/*     */     } 
/*     */     
/* 325 */     this.value = animatableMotionPointValue.interpolate(this.value, null, interpolation, accumulation, repeatIteration);
/*     */     
/* 327 */     if (this.value.hasChanged())
/* 328 */       markDirty(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/MotionAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */