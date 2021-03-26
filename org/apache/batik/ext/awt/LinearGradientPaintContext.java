/*     */ package org.apache.batik.ext.awt;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.ColorModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LinearGradientPaintContext
/*     */   extends MultipleGradientPaintContext
/*     */ {
/*     */   private float dgdX;
/*     */   private float dgdY;
/*     */   private float gc;
/*     */   private float pixSz;
/*     */   private static final int DEFAULT_IMPL = 1;
/*     */   private static final int ANTI_ALIAS_IMPL = 3;
/*     */   private int fillMethod;
/*     */   
/*     */   public LinearGradientPaintContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform t, RenderingHints hints, Point2D dStart, Point2D dEnd, float[] fractions, Color[] colors, MultipleGradientPaint.CycleMethodEnum cycleMethod, MultipleGradientPaint.ColorSpaceEnum colorSpace) throws NoninvertibleTransformException {
/* 103 */     super(cm, deviceBounds, userBounds, t, hints, fractions, colors, cycleMethod, colorSpace);
/*     */ 
/*     */ 
/*     */     
/* 107 */     Point2D.Float start = new Point2D.Float((float)dStart.getX(), (float)dStart.getY());
/*     */     
/* 109 */     Point2D.Float end = new Point2D.Float((float)dEnd.getX(), (float)dEnd.getY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     float dx = end.x - start.x;
/* 122 */     float dy = end.y - start.y;
/* 123 */     float dSq = dx * dx + dy * dy;
/*     */ 
/*     */     
/* 126 */     float constX = dx / dSq;
/* 127 */     float constY = dy / dSq;
/*     */ 
/*     */     
/* 130 */     this.dgdX = this.a00 * constX + this.a10 * constY;
/*     */     
/* 132 */     this.dgdY = this.a01 * constX + this.a11 * constY;
/*     */     
/* 134 */     float dgdXAbs = Math.abs(this.dgdX);
/* 135 */     float dgdYAbs = Math.abs(this.dgdY);
/* 136 */     if (dgdXAbs > dgdYAbs) { this.pixSz = dgdXAbs; }
/* 137 */     else { this.pixSz = dgdYAbs; }
/*     */ 
/*     */     
/* 140 */     this.gc = (this.a02 - start.x) * constX + (this.a12 - start.y) * constY;
/*     */     
/* 142 */     Object colorRend = hints.get(RenderingHints.KEY_COLOR_RENDERING);
/* 143 */     Object rend = hints.get(RenderingHints.KEY_RENDERING);
/*     */     
/* 145 */     this.fillMethod = 1;
/*     */     
/* 147 */     if (cycleMethod == MultipleGradientPaint.REPEAT || this.hasDiscontinuity) {
/*     */       
/* 149 */       if (rend == RenderingHints.VALUE_RENDER_QUALITY) {
/* 150 */         this.fillMethod = 3;
/*     */       }
/* 152 */       if (colorRend == RenderingHints.VALUE_COLOR_RENDER_SPEED) {
/* 153 */         this.fillMethod = 1;
/* 154 */       } else if (colorRend == RenderingHints.VALUE_COLOR_RENDER_QUALITY) {
/* 155 */         this.fillMethod = 3;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillHardNoCycle(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 163 */     float initConst = this.dgdX * x + this.gc;
/*     */     
/* 165 */     for (int i = 0; i < h; i++) {
/*     */       
/* 167 */       float g = initConst + this.dgdY * (y + i);
/* 168 */       int rowLimit = off + w;
/*     */       
/* 170 */       if (this.dgdX == 0.0F) {
/*     */         int val;
/*     */         
/* 173 */         if (g <= 0.0F) {
/* 174 */           val = this.gradientUnderflow;
/* 175 */         } else if (g >= 1.0F) {
/* 176 */           val = this.gradientOverflow;
/*     */         } else {
/*     */           
/* 179 */           int gradIdx = 0;
/* 180 */           while (gradIdx < this.gradientsLength - 1 && 
/* 181 */             g >= this.fractions[gradIdx + 1])
/*     */           {
/* 183 */             gradIdx++;
/*     */           }
/* 185 */           float delta = g - this.fractions[gradIdx];
/* 186 */           float idx = delta * 255.0F / this.normalizedIntervals[gradIdx] + 0.5F;
/*     */           
/* 188 */           val = this.gradients[gradIdx][(int)idx];
/*     */         } 
/*     */         
/* 191 */         while (off < rowLimit) {
/* 192 */           pixels[off++] = val;
/*     */         }
/*     */       } else {
/*     */         int gradSteps, preGradSteps, preVal, postVal;
/*     */ 
/*     */ 
/*     */         
/*     */         float gradStepsF, preGradStepsF;
/*     */ 
/*     */         
/* 202 */         if (this.dgdX >= 0.0F) {
/* 203 */           gradStepsF = (1.0F - g) / this.dgdX;
/* 204 */           preGradStepsF = (float)Math.ceil(((0.0F - g) / this.dgdX));
/* 205 */           preVal = this.gradientUnderflow;
/* 206 */           postVal = this.gradientOverflow;
/*     */         } else {
/* 208 */           gradStepsF = (0.0F - g) / this.dgdX;
/* 209 */           preGradStepsF = (float)Math.ceil(((1.0F - g) / this.dgdX));
/* 210 */           preVal = this.gradientOverflow;
/* 211 */           postVal = this.gradientUnderflow;
/*     */         } 
/*     */         
/* 214 */         if (gradStepsF > w) { gradSteps = w; }
/* 215 */         else { gradSteps = (int)gradStepsF; }
/* 216 */          if (preGradStepsF > w) { preGradSteps = w; }
/* 217 */         else { preGradSteps = (int)preGradStepsF; }
/*     */         
/* 219 */         int gradLimit = off + gradSteps;
/* 220 */         if (preGradSteps > 0) {
/* 221 */           int preGradLimit = off + preGradSteps;
/*     */           
/* 223 */           while (off < preGradLimit) {
/* 224 */             pixels[off++] = preVal;
/*     */           }
/* 226 */           g += this.dgdX * preGradSteps;
/*     */         } 
/*     */         
/* 229 */         if (this.dgdX > 0.0F) {
/*     */           
/* 231 */           int gradIdx = 0;
/* 232 */           while (gradIdx < this.gradientsLength - 1 && 
/* 233 */             g >= this.fractions[gradIdx + 1])
/*     */           {
/* 235 */             gradIdx++;
/*     */           }
/*     */           
/* 238 */           while (off < gradLimit) {
/* 239 */             int steps; float delta = g - this.fractions[gradIdx];
/* 240 */             int[] grad = this.gradients[gradIdx];
/*     */             
/* 242 */             double stepsD = Math.ceil(((this.fractions[gradIdx + 1] - g) / this.dgdX));
/*     */ 
/*     */             
/* 245 */             if (stepsD > w) { steps = w; }
/* 246 */             else { steps = (int)stepsD; }
/* 247 */              int subGradLimit = off + steps;
/* 248 */             if (subGradLimit > gradLimit) {
/* 249 */               subGradLimit = gradLimit;
/*     */             }
/* 251 */             int idx = (int)(delta * 255.0F / this.normalizedIntervals[gradIdx] * 65536.0F) + 32768;
/*     */ 
/*     */             
/* 254 */             int step = (int)(this.dgdX * 255.0F / this.normalizedIntervals[gradIdx] * 65536.0F);
/*     */ 
/*     */             
/* 257 */             while (off < subGradLimit) {
/* 258 */               pixels[off++] = grad[idx >> 16];
/* 259 */               idx += step;
/*     */             } 
/* 261 */             g = (float)(g + this.dgdX * stepsD);
/* 262 */             gradIdx++;
/*     */           } 
/*     */         } else {
/*     */           
/* 266 */           int gradIdx = this.gradientsLength - 1;
/* 267 */           while (gradIdx > 0 && 
/* 268 */             g <= this.fractions[gradIdx])
/*     */           {
/* 270 */             gradIdx--;
/*     */           }
/*     */           
/* 273 */           while (off < gradLimit) {
/* 274 */             int steps; float delta = g - this.fractions[gradIdx];
/* 275 */             int[] grad = this.gradients[gradIdx];
/*     */             
/* 277 */             double stepsD = Math.ceil((delta / -this.dgdX));
/*     */             
/* 279 */             if (stepsD > w) { steps = w; }
/* 280 */             else { steps = (int)stepsD; }
/* 281 */              int subGradLimit = off + steps;
/* 282 */             if (subGradLimit > gradLimit) {
/* 283 */               subGradLimit = gradLimit;
/*     */             }
/* 285 */             int idx = (int)(delta * 255.0F / this.normalizedIntervals[gradIdx] * 65536.0F) + 32768;
/*     */ 
/*     */             
/* 288 */             int step = (int)(this.dgdX * 255.0F / this.normalizedIntervals[gradIdx] * 65536.0F);
/*     */ 
/*     */             
/* 291 */             while (off < subGradLimit) {
/* 292 */               pixels[off++] = grad[idx >> 16];
/* 293 */               idx += step;
/*     */             } 
/* 295 */             g = (float)(g + this.dgdX * stepsD);
/* 296 */             gradIdx--;
/*     */           } 
/*     */         } 
/*     */         
/* 300 */         while (off < rowLimit) {
/* 301 */           pixels[off++] = postVal;
/*     */         }
/*     */       } 
/* 304 */       off += adjust;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillSimpleNoCycle(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 311 */     float initConst = this.dgdX * x + this.gc;
/* 312 */     float step = this.dgdX * this.fastGradientArraySize;
/* 313 */     int fpStep = (int)(step * 65536.0F);
/*     */     
/* 315 */     int[] grad = this.gradient;
/*     */     
/* 317 */     for (int i = 0; i < h; i++) {
/*     */       
/* 319 */       float g = initConst + this.dgdY * (y + i);
/* 320 */       g *= this.fastGradientArraySize;
/* 321 */       g = (float)(g + 0.5D);
/*     */       
/* 323 */       int rowLimit = off + w;
/*     */       
/* 325 */       float check = this.dgdX * this.fastGradientArraySize * w;
/* 326 */       if (check < 0.0F) check = -check; 
/* 327 */       if (check < 0.3D) {
/*     */         int val;
/*     */         
/* 330 */         if (g <= 0.0F) {
/* 331 */           val = this.gradientUnderflow;
/* 332 */         } else if (g >= this.fastGradientArraySize) {
/* 333 */           val = this.gradientOverflow;
/*     */         } else {
/* 335 */           val = grad[(int)g];
/* 336 */         }  while (off < rowLimit) {
/* 337 */           pixels[off++] = val;
/*     */         }
/*     */       } else {
/*     */         int gradSteps, preGradSteps, preVal, postVal;
/*     */ 
/*     */ 
/*     */         
/* 344 */         if (this.dgdX > 0.0F) {
/* 345 */           gradSteps = (int)((this.fastGradientArraySize - g) / step);
/* 346 */           preGradSteps = (int)Math.ceil((0.0F - g / step));
/* 347 */           preVal = this.gradientUnderflow;
/* 348 */           postVal = this.gradientOverflow;
/*     */         } else {
/*     */           
/* 351 */           gradSteps = (int)((0.0F - g) / step);
/* 352 */           preGradSteps = (int)Math.ceil(((this.fastGradientArraySize - g) / step));
/*     */           
/* 354 */           preVal = this.gradientOverflow;
/* 355 */           postVal = this.gradientUnderflow;
/*     */         } 
/*     */         
/* 358 */         if (gradSteps > w)
/* 359 */           gradSteps = w; 
/* 360 */         int gradLimit = off + gradSteps;
/*     */         
/* 362 */         if (preGradSteps > 0) {
/* 363 */           if (preGradSteps > w)
/* 364 */             preGradSteps = w; 
/* 365 */           int preGradLimit = off + preGradSteps;
/*     */           
/* 367 */           while (off < preGradLimit) {
/* 368 */             pixels[off++] = preVal;
/*     */           }
/* 370 */           g += step * preGradSteps;
/*     */         } 
/*     */         
/* 373 */         int fpG = (int)(g * 65536.0F);
/* 374 */         while (off < gradLimit) {
/* 375 */           pixels[off++] = grad[fpG >> 16];
/* 376 */           fpG += fpStep;
/*     */         } 
/*     */         
/* 379 */         while (off < rowLimit) {
/* 380 */           pixels[off++] = postVal;
/*     */         }
/*     */       } 
/* 383 */       off += adjust;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillSimpleRepeat(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 390 */     float initConst = this.dgdX * x + this.gc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     float step = (this.dgdX - (int)this.dgdX) * this.fastGradientArraySize;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 401 */     if (step < 0.0F) {
/* 402 */       step += this.fastGradientArraySize;
/*     */     }
/* 404 */     int[] grad = this.gradient;
/*     */     
/* 406 */     for (int i = 0; i < h; i++) {
/*     */       
/* 408 */       float g = initConst + this.dgdY * (y + i);
/*     */ 
/*     */       
/* 411 */       g -= (int)g;
/*     */       
/* 413 */       if (g < 0.0F) {
/* 414 */         g++;
/*     */       }
/*     */       
/* 417 */       g *= this.fastGradientArraySize;
/* 418 */       g = (float)(g + 0.5D);
/* 419 */       int rowLimit = off + w;
/* 420 */       while (off < rowLimit) {
/* 421 */         int idx = (int)g;
/* 422 */         if (idx >= this.fastGradientArraySize) {
/* 423 */           g -= this.fastGradientArraySize;
/* 424 */           idx -= this.fastGradientArraySize;
/*     */         } 
/* 426 */         pixels[off++] = grad[idx];
/* 427 */         g += step;
/*     */       } 
/*     */       
/* 430 */       off += adjust;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fillSimpleReflect(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 436 */     float initConst = this.dgdX * x + this.gc;
/*     */     
/* 438 */     int[] grad = this.gradient;
/*     */     
/* 440 */     for (int i = 0; i < h; i++) {
/*     */       
/* 442 */       float g = initConst + this.dgdY * (y + i);
/*     */ 
/*     */       
/* 445 */       g -= (2 * (int)(g / 2.0F));
/*     */       
/* 447 */       float step = this.dgdX;
/*     */       
/* 449 */       if (g < 0.0F) {
/* 450 */         g = -g;
/* 451 */         step = -step;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 461 */       step -= 2.0F * (int)step / 2.0F;
/* 462 */       if (step < 0.0F)
/* 463 */         step = (float)(step + 2.0D); 
/* 464 */       int reflectMax = 2 * this.fastGradientArraySize;
/*     */ 
/*     */       
/* 467 */       g *= this.fastGradientArraySize;
/* 468 */       g = (float)(g + 0.5D);
/* 469 */       step *= this.fastGradientArraySize;
/* 470 */       int rowLimit = off + w;
/* 471 */       while (off < rowLimit) {
/* 472 */         int idx = (int)g;
/* 473 */         if (idx >= reflectMax) {
/* 474 */           g -= reflectMax;
/* 475 */           idx -= reflectMax;
/*     */         } 
/*     */         
/* 478 */         if (idx <= this.fastGradientArraySize) {
/* 479 */           pixels[off++] = grad[idx];
/*     */         } else {
/* 481 */           pixels[off++] = grad[reflectMax - idx];
/* 482 */         }  g += step;
/*     */       } 
/*     */       
/* 485 */       off += adjust;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 507 */     float initConst = this.dgdX * x + this.gc;
/*     */     
/* 509 */     if (this.fillMethod == 3) {
/*     */       
/* 511 */       for (int i = 0; i < h; i++) {
/* 512 */         float g = initConst + this.dgdY * (y + i);
/*     */         
/* 514 */         int rowLimit = off + w;
/* 515 */         while (off < rowLimit) {
/*     */           
/* 517 */           pixels[off++] = indexGradientAntiAlias(g, this.pixSz);
/* 518 */           g += this.dgdX;
/*     */         } 
/* 520 */         off += adjust;
/*     */       }
/*     */     
/* 523 */     } else if (!this.isSimpleLookup) {
/* 524 */       if (this.cycleMethod == MultipleGradientPaint.NO_CYCLE) {
/* 525 */         fillHardNoCycle(pixels, off, adjust, x, y, w, h);
/*     */       }
/*     */       else {
/*     */         
/* 529 */         for (int i = 0; i < h; i++) {
/* 530 */           float g = initConst + this.dgdY * (y + i);
/*     */           
/* 532 */           int rowLimit = off + w;
/* 533 */           while (off < rowLimit) {
/*     */             
/* 535 */             pixels[off++] = indexIntoGradientsArrays(g);
/* 536 */             g += this.dgdX;
/*     */           } 
/* 538 */           off += adjust;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 544 */     else if (this.cycleMethod == MultipleGradientPaint.NO_CYCLE) {
/* 545 */       fillSimpleNoCycle(pixels, off, adjust, x, y, w, h);
/* 546 */     } else if (this.cycleMethod == MultipleGradientPaint.REPEAT) {
/* 547 */       fillSimpleRepeat(pixels, off, adjust, x, y, w, h);
/*     */     } else {
/* 549 */       fillSimpleReflect(pixels, off, adjust, x, y, w, h);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/LinearGradientPaintContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */