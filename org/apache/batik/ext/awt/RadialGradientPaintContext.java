/*     */ package org.apache.batik.ext.awt;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class RadialGradientPaintContext
/*     */   extends MultipleGradientPaintContext
/*     */ {
/*     */   private boolean isSimpleFocus = false;
/*     */   private boolean isNonCyclic = false;
/*     */   private float radius;
/*     */   private float centerX;
/*     */   private float centerY;
/*     */   private float focusX;
/*     */   private float focusY;
/*     */   private float radiusSq;
/*     */   private float constA;
/*     */   private float constB;
/*     */   private float trivial;
/*     */   private static final int FIXED_POINT_IMPL = 1;
/*     */   private static final int DEFAULT_IMPL = 2;
/*     */   private static final int ANTI_ALIAS_IMPL = 3;
/*     */   private int fillMethod;
/*     */   private static final float SCALEBACK = 0.999F;
/*     */   private float invSqStepFloat;
/*     */   private static final int MAX_PRECISION = 256;
/*     */   private int[] sqrtLutFixed;
/*     */   
/*     */   protected void fillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/*     */     switch (this.fillMethod) {
/*     */       case 1:
/*     */         fixedPointSimplestCaseNonCyclicFillRaster(pixels, off, adjust, x, y, w, h);
/*     */         return;
/*     */       case 3:
/*     */         antiAliasFillRaster(pixels, off, adjust, x, y, w, h);
/*     */         return;
/*     */     } 
/*     */     cyclicCircularGradientFillRaster(pixels, off, adjust, x, y, w, h);
/*     */   }
/*     */   
/*     */   public RadialGradientPaintContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform t, RenderingHints hints, float cx, float cy, float r, float fx, float fy, float[] fractions, Color[] colors, MultipleGradientPaint.CycleMethodEnum cycleMethod, MultipleGradientPaint.ColorSpaceEnum colorSpace) throws NoninvertibleTransformException {
/* 137 */     super(cm, deviceBounds, userBounds, t, hints, fractions, colors, cycleMethod, colorSpace);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 352 */     this.sqrtLutFixed = new int[256]; this.centerX = cx; this.centerY = cy; this.focusX = fx; this.focusY = fy; this.radius = r; this.isSimpleFocus = (this.focusX == this.centerX && this.focusY == this.centerY); this.isNonCyclic = (cycleMethod == RadialGradientPaint.NO_CYCLE); this.radiusSq = this.radius * this.radius; float dX = this.focusX - this.centerX; float dY = this.focusY - this.centerY; double dist = Math.sqrt((dX * dX + dY * dY)); if (dist > (this.radius * 0.999F)) {
/*     */       double angle = Math.atan2(dY, dX); this.focusX = (float)((0.999F * this.radius) * Math.cos(angle)) + this.centerX; this.focusY = (float)((0.999F * this.radius) * Math.sin(angle)) + this.centerY;
/*     */     }  dX = this.focusX - this.centerX; this.trivial = (float)Math.sqrt((this.radiusSq - dX * dX)); this.constA = this.a02 - this.centerX; this.constB = this.a12 - this.centerY; Object colorRend = hints.get(RenderingHints.KEY_COLOR_RENDERING); Object rend = hints.get(RenderingHints.KEY_RENDERING); this.fillMethod = 0; if (rend == RenderingHints.VALUE_RENDER_QUALITY || colorRend == RenderingHints.VALUE_COLOR_RENDER_QUALITY)
/*     */       this.fillMethod = 3;  if (rend == RenderingHints.VALUE_RENDER_SPEED || colorRend == RenderingHints.VALUE_COLOR_RENDER_SPEED)
/*     */       this.fillMethod = 2;  if (this.fillMethod == 0)
/*     */       this.fillMethod = 2;  if (this.fillMethod == 2 && this.isSimpleFocus && this.isNonCyclic && this.isSimpleLookup) {
/*     */       calculateFixedPointSqrtLookupTable(); this.fillMethod = 1;
/* 359 */     }  } private void calculateFixedPointSqrtLookupTable() { float sqStepFloat = (this.fastGradientArraySize * this.fastGradientArraySize) / 254.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     int[] workTbl = this.sqrtLutFixed;
/*     */     int i;
/* 367 */     for (i = 0; i < 255; i++) {
/* 368 */       workTbl[i] = (int)Math.sqrt((i * sqStepFloat));
/*     */     }
/* 370 */     workTbl[i] = workTbl[i - 1];
/* 371 */     this.invSqStepFloat = 1.0F / sqStepFloat; } private void fixedPointSimplestCaseNonCyclicFillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h) { float iSq = 0.0F; float indexFactor = this.fastGradientArraySize / this.radius; float constX = this.a00 * x + this.a01 * y + this.constA; float constY = this.a10 * x + this.a11 * y + this.constB; float deltaX = indexFactor * this.a00; float deltaY = indexFactor * this.a10; int fixedArraySizeSq = this.fastGradientArraySize * this.fastGradientArraySize; int indexer = off; float temp = deltaX * deltaX + deltaY * deltaY; float gDeltaDelta = temp * 2.0F; if (temp > fixedArraySizeSq) {
/*     */       int val = this.gradientOverflow; for (int i = 0; i < h; i++) {
/*     */         for (int end = indexer + w; indexer < end; indexer++)
/*     */           pixels[indexer] = val;  indexer += adjust;
/*     */       }  return;
/*     */     } 
/*     */     for (int j = 0; j < h; j++) {
/*     */       float dX = indexFactor * (this.a01 * j + constX);
/*     */       float dY = indexFactor * (this.a11 * j + constY);
/*     */       float g = dY * dY + dX * dX;
/*     */       float gDelta = (deltaY * dY + deltaX * dX) * 2.0F + temp;
/*     */       for (int end = indexer + w; indexer < end; indexer++) {
/*     */         if (g >= fixedArraySizeSq) {
/*     */           pixels[indexer] = this.gradientOverflow;
/*     */         } else {
/*     */           iSq = g * this.invSqStepFloat;
/*     */           int iSqInt = (int)iSq;
/*     */           iSq -= iSqInt;
/*     */           int gIndex = this.sqrtLutFixed[iSqInt];
/*     */           gIndex += (int)(iSq * (this.sqrtLutFixed[iSqInt + 1] - gIndex));
/*     */           pixels[indexer] = this.gradient[gIndex];
/*     */         } 
/*     */         g += gDelta;
/*     */         gDelta += gDeltaDelta;
/*     */       } 
/*     */       indexer += adjust;
/*     */     }  }
/* 398 */   private void cyclicCircularGradientFillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h) { double constC = (-this.radiusSq + this.centerX * this.centerX + this.centerY * this.centerY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 407 */     float constX = this.a00 * x + this.a01 * y + this.a02;
/* 408 */     float constY = this.a10 * x + this.a11 * y + this.a12;
/* 409 */     float precalc2 = 2.0F * this.centerY;
/* 410 */     float precalc3 = -2.0F * this.centerX;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 419 */     int indexer = off;
/*     */     
/* 421 */     int pixInc = w + adjust;
/*     */     
/* 423 */     for (int j = 0; j < h; j++) {
/*     */       
/* 425 */       float X = this.a01 * j + constX;
/* 426 */       float Y = this.a11 * j + constY;
/*     */ 
/*     */       
/* 429 */       for (int i = 0; i < w; i++) {
/*     */         double solutionX, solutionY;
/*     */         
/* 432 */         if (X - this.focusX > -1.0E-6F && X - this.focusX < 1.0E-6F) {
/*     */           
/* 434 */           solutionX = this.focusX;
/*     */           
/* 436 */           solutionY = this.centerY;
/*     */           
/* 438 */           solutionY += (Y > this.focusY) ? this.trivial : -this.trivial;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 444 */           double slope = ((Y - this.focusY) / (X - this.focusX));
/*     */           
/* 446 */           double yintcpt = Y - slope * X;
/*     */ 
/*     */ 
/*     */           
/* 450 */           double A = slope * slope + 1.0D;
/*     */           
/* 452 */           double B = precalc3 + -2.0D * slope * (this.centerY - yintcpt);
/*     */           
/* 454 */           double C = constC + yintcpt * (yintcpt - precalc2);
/*     */           
/* 456 */           float det = (float)Math.sqrt(B * B - 4.0D * A * C);
/*     */           
/* 458 */           solutionX = -B;
/*     */ 
/*     */ 
/*     */           
/* 462 */           solutionX += (X < this.focusX) ? -det : det;
/*     */           
/* 464 */           solutionX /= 2.0D * A;
/*     */           
/* 466 */           solutionY = slope * solutionX + yintcpt;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 474 */         float deltaXSq = (float)solutionX - this.focusX;
/* 475 */         deltaXSq *= deltaXSq;
/*     */         
/* 477 */         float deltaYSq = (float)solutionY - this.focusY;
/* 478 */         deltaYSq *= deltaYSq;
/*     */         
/* 480 */         float intersectToFocusSq = deltaXSq + deltaYSq;
/*     */         
/* 482 */         deltaXSq = X - this.focusX;
/* 483 */         deltaXSq *= deltaXSq;
/*     */         
/* 485 */         deltaYSq = Y - this.focusY;
/* 486 */         deltaYSq *= deltaYSq;
/*     */         
/* 488 */         float currentToFocusSq = deltaXSq + deltaYSq;
/*     */ 
/*     */ 
/*     */         
/* 492 */         float g = (float)Math.sqrt((currentToFocusSq / intersectToFocusSq));
/*     */ 
/*     */         
/* 495 */         pixels[indexer + i] = indexIntoGradientsArrays(g);
/*     */         
/* 497 */         X += this.a00;
/* 498 */         Y += this.a10;
/*     */       } 
/* 500 */       indexer += pixInc;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void antiAliasFillRaster(int[] pixels, int off, int adjust, int x, int y, int w, int h) {
/* 529 */     double constC = (-this.radiusSq + this.centerX * this.centerX + this.centerY * this.centerY);
/*     */ 
/*     */     
/* 532 */     float precalc2 = 2.0F * this.centerY;
/* 533 */     float precalc3 = -2.0F * this.centerX;
/*     */ 
/*     */     
/* 536 */     float constX = this.a00 * (x - 0.5F) + this.a01 * (y + 0.5F) + this.a02;
/* 537 */     float constY = this.a10 * (x - 0.5F) + this.a11 * (y + 0.5F) + this.a12;
/*     */ 
/*     */ 
/*     */     
/* 541 */     int indexer = off - 1;
/*     */     
/* 543 */     double[] prevGs = new double[w + 1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 551 */     float X = constX - this.a01;
/* 552 */     float Y = constY - this.a11;
/*     */     
/*     */     int i;
/* 555 */     for (i = 0; i <= w; i++) {
/* 556 */       double solutionX, solutionY; float dx = X - this.focusX;
/*     */ 
/*     */       
/* 559 */       if (dx > -1.0E-6F && dx < 1.0E-6F) {
/*     */         
/* 561 */         solutionX = this.focusX;
/* 562 */         solutionY = this.centerY;
/* 563 */         solutionY += (Y > this.focusY) ? this.trivial : -this.trivial;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 574 */         double slope = ((Y - this.focusY) / (X - this.focusX));
/*     */         
/* 576 */         double yintcpt = Y - slope * X;
/*     */ 
/*     */ 
/*     */         
/* 580 */         double A = slope * slope + 1.0D;
/*     */         
/* 582 */         double B = precalc3 + -2.0D * slope * (this.centerY - yintcpt);
/*     */         
/* 584 */         double C = constC + yintcpt * (yintcpt - precalc2);
/*     */         
/* 586 */         double det = Math.sqrt(B * B - 4.0D * A * C);
/*     */         
/* 588 */         solutionX = -B;
/*     */ 
/*     */ 
/*     */         
/* 592 */         solutionX += (X < this.focusX) ? -det : det;
/*     */         
/* 594 */         solutionX /= 2.0D * A;
/*     */         
/* 596 */         solutionY = slope * solutionX + yintcpt;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 603 */       double deltaXSq = solutionX - this.focusX;
/* 604 */       deltaXSq *= deltaXSq;
/*     */       
/* 606 */       double deltaYSq = solutionY - this.focusY;
/* 607 */       deltaYSq *= deltaYSq;
/*     */       
/* 609 */       double intersectToFocusSq = deltaXSq + deltaYSq;
/*     */       
/* 611 */       deltaXSq = (X - this.focusX);
/* 612 */       deltaXSq *= deltaXSq;
/*     */       
/* 614 */       deltaYSq = (Y - this.focusY);
/* 615 */       deltaYSq *= deltaYSq;
/*     */       
/* 617 */       double currentToFocusSq = deltaXSq + deltaYSq;
/*     */ 
/*     */ 
/*     */       
/* 621 */       prevGs[i] = Math.sqrt(currentToFocusSq / intersectToFocusSq);
/*     */       
/* 623 */       X += this.a00;
/* 624 */       Y += this.a10;
/*     */     } 
/*     */     
/* 627 */     for (int j = 0; j < h; j++) {
/*     */       double solutionX, solutionY;
/*     */       
/* 630 */       X = this.a01 * j + constX;
/* 631 */       Y = this.a11 * j + constY;
/*     */       
/* 633 */       double g10 = prevGs[0];
/*     */       
/* 635 */       float dx = X - this.focusX;
/*     */       
/* 637 */       if (dx > -1.0E-6F && dx < 1.0E-6F) {
/*     */         
/* 639 */         solutionX = this.focusX;
/* 640 */         solutionY = this.centerY;
/* 641 */         solutionY += (Y > this.focusY) ? this.trivial : -this.trivial;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 652 */         double slope = ((Y - this.focusY) / (X - this.focusX));
/*     */         
/* 654 */         double yintcpt = Y - slope * X;
/*     */ 
/*     */ 
/*     */         
/* 658 */         double A = slope * slope + 1.0D;
/*     */         
/* 660 */         double B = precalc3 + -2.0D * slope * (this.centerY - yintcpt);
/*     */         
/* 662 */         double C = constC + yintcpt * (yintcpt - precalc2);
/*     */         
/* 664 */         double det = Math.sqrt(B * B - 4.0D * A * C);
/*     */         
/* 666 */         solutionX = -B;
/*     */ 
/*     */ 
/*     */         
/* 670 */         solutionX += (X < this.focusX) ? -det : det;
/*     */         
/* 672 */         solutionX /= 2.0D * A;
/*     */         
/* 674 */         solutionY = slope * solutionX + yintcpt;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 681 */       double deltaXSq = solutionX - this.focusX;
/* 682 */       deltaXSq *= deltaXSq;
/*     */       
/* 684 */       double deltaYSq = solutionY - this.focusY;
/* 685 */       deltaYSq *= deltaYSq;
/*     */       
/* 687 */       double intersectToFocusSq = deltaXSq + deltaYSq;
/*     */       
/* 689 */       deltaXSq = (X - this.focusX);
/* 690 */       deltaXSq *= deltaXSq;
/*     */       
/* 692 */       deltaYSq = (Y - this.focusY);
/* 693 */       deltaYSq *= deltaYSq;
/*     */       
/* 695 */       double currentToFocusSq = deltaXSq + deltaYSq;
/* 696 */       double g11 = Math.sqrt(currentToFocusSq / intersectToFocusSq);
/* 697 */       prevGs[0] = g11;
/*     */       
/* 699 */       X += this.a00;
/* 700 */       Y += this.a10;
/*     */ 
/*     */       
/* 703 */       for (i = 1; i <= w; i++) {
/* 704 */         double g00 = g10;
/* 705 */         double g01 = g11;
/* 706 */         g10 = prevGs[i];
/*     */         
/* 708 */         dx = X - this.focusX;
/*     */         
/* 710 */         if (dx > -1.0E-6F && dx < 1.0E-6F) {
/*     */           
/* 712 */           solutionX = this.focusX;
/* 713 */           solutionY = this.centerY;
/* 714 */           solutionY += (Y > this.focusY) ? this.trivial : -this.trivial;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 725 */           double slope = ((Y - this.focusY) / (X - this.focusX));
/*     */           
/* 727 */           double yintcpt = Y - slope * X;
/*     */ 
/*     */ 
/*     */           
/* 731 */           double A = slope * slope + 1.0D;
/*     */           
/* 733 */           double B = precalc3 + -2.0D * slope * (this.centerY - yintcpt);
/*     */           
/* 735 */           double C = constC + yintcpt * (yintcpt - precalc2);
/*     */           
/* 737 */           double det = Math.sqrt(B * B - 4.0D * A * C);
/*     */           
/* 739 */           solutionX = -B;
/*     */ 
/*     */ 
/*     */           
/* 743 */           solutionX += (X < this.focusX) ? -det : det;
/*     */           
/* 745 */           solutionX /= 2.0D * A;
/*     */           
/* 747 */           solutionY = slope * solutionX + yintcpt;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 754 */         deltaXSq = solutionX - this.focusX;
/* 755 */         deltaXSq *= deltaXSq;
/*     */         
/* 757 */         deltaYSq = solutionY - this.focusY;
/* 758 */         deltaYSq *= deltaYSq;
/*     */         
/* 760 */         intersectToFocusSq = deltaXSq + deltaYSq;
/*     */         
/* 762 */         deltaXSq = (X - this.focusX);
/* 763 */         deltaXSq *= deltaXSq;
/*     */         
/* 765 */         deltaYSq = (Y - this.focusY);
/* 766 */         deltaYSq *= deltaYSq;
/*     */         
/* 768 */         currentToFocusSq = deltaXSq + deltaYSq;
/* 769 */         g11 = Math.sqrt(currentToFocusSq / intersectToFocusSq);
/* 770 */         prevGs[i] = g11;
/*     */ 
/*     */         
/* 773 */         pixels[indexer + i] = indexGradientAntiAlias((float)((g00 + g01 + g10 + g11) / 4.0D), (float)Math.max(Math.abs(g11 - g00), Math.abs(g10 - g01)));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 778 */         X += this.a00;
/* 779 */         Y += this.a10;
/*     */       } 
/* 781 */       indexer += w + adjust;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/RadialGradientPaintContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */