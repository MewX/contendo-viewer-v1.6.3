/*      */ package org.apache.batik.ext.awt.image.rendered;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class TurbulencePatternRed
/*      */   extends AbstractRed
/*      */ {
/*      */   private StitchInfo stitchInfo;
/*      */   
/*      */   static final class StitchInfo
/*      */   {
/*      */     int width;
/*      */     int height;
/*      */     int wrapX;
/*      */     int wrapY;
/*      */     
/*      */     StitchInfo() {}
/*      */     
/*      */     StitchInfo(StitchInfo stitchInfo) {
/*  104 */       this.width = stitchInfo.width;
/*  105 */       this.height = stitchInfo.height;
/*  106 */       this.wrapX = stitchInfo.wrapX;
/*  107 */       this.wrapY = stitchInfo.wrapY;
/*      */     }
/*      */     
/*      */     final void assign(StitchInfo stitchInfo) {
/*  111 */       this.width = stitchInfo.width;
/*  112 */       this.height = stitchInfo.height;
/*  113 */       this.wrapX = stitchInfo.wrapX;
/*  114 */       this.wrapY = stitchInfo.wrapY;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final void doubleFrequency() {
/*  129 */       this.width *= 2;
/*  130 */       this.height *= 2;
/*  131 */       this.wrapX *= 2;
/*  132 */       this.wrapY *= 2;
/*  133 */       this.wrapX = (int)(this.wrapX - 4096.0D);
/*  134 */       this.wrapY = (int)(this.wrapY - 4096.0D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   private static final AffineTransform IDENTITY = new AffineTransform();
/*      */ 
/*      */ 
/*      */   
/*      */   private double baseFrequencyX;
/*      */ 
/*      */ 
/*      */   
/*      */   private double baseFrequencyY;
/*      */ 
/*      */ 
/*      */   
/*      */   private int numOctaves;
/*      */ 
/*      */ 
/*      */   
/*      */   private int seed;
/*      */ 
/*      */ 
/*      */   
/*      */   private Rectangle2D tile;
/*      */ 
/*      */ 
/*      */   
/*      */   private AffineTransform txf;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isFractalNoise;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] channels;
/*      */ 
/*      */ 
/*      */   
/*      */   double[] tx;
/*      */ 
/*      */   
/*      */   double[] ty;
/*      */ 
/*      */   
/*      */   private static final int RAND_m = 2147483647;
/*      */ 
/*      */   
/*      */   private static final int RAND_a = 16807;
/*      */ 
/*      */   
/*      */   private static final int RAND_q = 127773;
/*      */ 
/*      */   
/*      */   private static final int RAND_r = 2836;
/*      */ 
/*      */   
/*      */   private static final int BSize = 256;
/*      */ 
/*      */   
/*      */   private static final int BM = 255;
/*      */ 
/*      */   
/*      */   private static final double PerlinN = 4096.0D;
/*      */ 
/*      */   
/*      */   private final int[] latticeSelector;
/*      */ 
/*      */   
/*      */   private final double[] gradient;
/*      */ 
/*      */ 
/*      */   
/*      */   public double getBaseFrequencyX() {
/*  217 */     return this.baseFrequencyX;
/*      */   }
/*      */   
/*      */   public double getBaseFrequencyY() {
/*  221 */     return this.baseFrequencyY;
/*      */   }
/*      */   
/*      */   public int getNumOctaves() {
/*  225 */     return this.numOctaves;
/*      */   }
/*      */   
/*      */   public int getSeed() {
/*  229 */     return this.seed;
/*      */   }
/*      */   
/*      */   public Rectangle2D getTile() {
/*  233 */     return (Rectangle2D)this.tile.clone();
/*      */   }
/*      */   
/*      */   public boolean isFractalNoise() {
/*  237 */     return this.isFractalNoise;
/*      */   }
/*      */   
/*      */   public boolean[] getChannels() {
/*  241 */     boolean[] channels = new boolean[4];
/*  242 */     for (int channel : this.channels) channels[channel] = true;
/*      */     
/*  244 */     return channels;
/*      */   }
/*      */   
/*      */   public final int setupSeed(int seed) {
/*  248 */     if (seed <= 0) seed = -(seed % 2147483646) + 1; 
/*  249 */     if (seed > 2147483646) seed = 2147483646; 
/*  250 */     return seed;
/*      */   }
/*      */   
/*      */   public final int random(int seed) {
/*  254 */     int result = 16807 * seed % 127773 - 2836 * seed / 127773;
/*  255 */     if (result <= 0) result += Integer.MAX_VALUE; 
/*  256 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initLattice(int seed) {
/*  262 */     seed = setupSeed(seed);
/*      */     int k;
/*  264 */     for (k = 0; k < 4; k++) {
/*  265 */       for (int m = 0; m < 256; m++) {
/*  266 */         double u = ((seed = random(seed)) % 512 - 256);
/*  267 */         double v = ((seed = random(seed)) % 512 - 256);
/*      */         
/*  269 */         double s = 1.0D / Math.sqrt(u * u + v * v);
/*  270 */         this.gradient[m * 8 + k * 2] = u * s;
/*  271 */         this.gradient[m * 8 + k * 2 + 1] = v * s;
/*      */       } 
/*      */     } 
/*      */     int i;
/*  275 */     for (i = 0; i < 256; i++) {
/*  276 */       this.latticeSelector[i] = i;
/*      */     }
/*  278 */     while (--i > 0) {
/*  279 */       k = this.latticeSelector[i];
/*  280 */       int m = (seed = random(seed)) % 256;
/*  281 */       this.latticeSelector[i] = this.latticeSelector[m];
/*  282 */       this.latticeSelector[m] = k;
/*      */ 
/*      */ 
/*      */       
/*  286 */       int s1 = i << 3;
/*  287 */       int s2 = m << 3;
/*  288 */       for (m = 0; m < 8; m++) {
/*  289 */         double s = this.gradient[s1 + m];
/*  290 */         this.gradient[s1 + m] = this.gradient[s2 + m];
/*  291 */         this.gradient[s2 + m] = s;
/*      */       } 
/*      */     } 
/*  294 */     this.latticeSelector[256] = this.latticeSelector[0];
/*  295 */     for (int j = 0; j < 8; j++) {
/*  296 */       this.gradient[2048 + j] = this.gradient[j];
/*      */     }
/*      */   }
/*      */   
/*      */   private static final double s_curve(double t) {
/*  301 */     return t * t * (3.0D - 2.0D * t);
/*      */   }
/*      */   
/*      */   private static final double lerp(double t, double a, double b) {
/*  305 */     return a + t * (b - a);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void noise2(double[] noise, double vec0, double vec1) {
/*  321 */     vec0 += 4096.0D;
/*  322 */     int b0 = (int)vec0 & 0xFF;
/*      */     
/*  324 */     int i = this.latticeSelector[b0];
/*  325 */     int j = this.latticeSelector[b0 + 1];
/*      */     
/*  327 */     double rx0 = vec0 - (int)vec0;
/*  328 */     double rx1 = rx0 - 1.0D;
/*  329 */     double sx = s_curve(rx0);
/*      */     
/*  331 */     vec1 += 4096.0D;
/*  332 */     b0 = (int)vec1;
/*      */ 
/*      */ 
/*      */     
/*  336 */     int b1 = (j + b0 & 0xFF) << 3;
/*  337 */     b0 = (i + b0 & 0xFF) << 3;
/*      */     
/*  339 */     double ry0 = vec1 - (int)vec1;
/*  340 */     double ry1 = ry0 - 1.0D;
/*  341 */     double sy = s_curve(ry0);
/*      */     
/*  343 */     switch (this.channels.length) {
/*      */       
/*      */       case 4:
/*  346 */         noise[3] = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 6] + ry0 * this.gradient[b0 + 7], rx1 * this.gradient[b1 + 6] + ry0 * this.gradient[b1 + 7]), lerp(sx, rx0 * this.gradient[b0 + 8 + 6] + ry1 * this.gradient[b0 + 8 + 7], rx1 * this.gradient[b1 + 8 + 6] + ry1 * this.gradient[b1 + 8 + 7]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  355 */         noise[2] = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 4] + ry0 * this.gradient[b0 + 5], rx1 * this.gradient[b1 + 4] + ry0 * this.gradient[b1 + 5]), lerp(sx, rx0 * this.gradient[b0 + 8 + 4] + ry1 * this.gradient[b0 + 8 + 5], rx1 * this.gradient[b1 + 8 + 4] + ry1 * this.gradient[b1 + 8 + 5]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  364 */         noise[1] = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 2] + ry0 * this.gradient[b0 + 3], rx1 * this.gradient[b1 + 2] + ry0 * this.gradient[b1 + 3]), lerp(sx, rx0 * this.gradient[b0 + 8 + 2] + ry1 * this.gradient[b0 + 8 + 3], rx1 * this.gradient[b1 + 8 + 2] + ry1 * this.gradient[b1 + 8 + 3]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  373 */         noise[0] = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 0] + ry0 * this.gradient[b0 + 1], rx1 * this.gradient[b1 + 0] + ry0 * this.gradient[b1 + 1]), lerp(sx, rx0 * this.gradient[b0 + 8 + 0] + ry1 * this.gradient[b0 + 8 + 1], rx1 * this.gradient[b1 + 8 + 0] + ry1 * this.gradient[b1 + 8 + 1]));
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void noise2Stitch(double[] noise, double vec0, double vec1, StitchInfo stitchInfo) {
/*  402 */     double t = vec0 + 4096.0D;
/*  403 */     int b0 = (int)t;
/*  404 */     int b1 = b0 + 1;
/*      */     
/*  406 */     if (b1 >= stitchInfo.wrapX) {
/*  407 */       if (b0 >= stitchInfo.wrapX) {
/*  408 */         b0 -= stitchInfo.width;
/*  409 */         b1 -= stitchInfo.width;
/*      */       } else {
/*  411 */         b1 -= stitchInfo.width;
/*      */       } 
/*      */     }
/*  414 */     int i = this.latticeSelector[b0 & 0xFF];
/*  415 */     int j = this.latticeSelector[b1 & 0xFF];
/*      */     
/*  417 */     double rx0 = t - (int)t;
/*  418 */     double rx1 = rx0 - 1.0D;
/*  419 */     double sx = s_curve(rx0);
/*      */     
/*  421 */     t = vec1 + 4096.0D;
/*  422 */     b0 = (int)t;
/*  423 */     b1 = b0 + 1;
/*      */     
/*  425 */     if (b1 >= stitchInfo.wrapY) {
/*  426 */       if (b0 >= stitchInfo.wrapY) {
/*  427 */         b0 -= stitchInfo.height;
/*  428 */         b1 -= stitchInfo.height;
/*      */       } else {
/*  430 */         b1 -= stitchInfo.height;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  437 */     int b00 = (i + b0 & 0xFF) << 3;
/*  438 */     int b10 = (j + b0 & 0xFF) << 3;
/*  439 */     int b01 = (i + b1 & 0xFF) << 3;
/*  440 */     int b11 = (j + b1 & 0xFF) << 3;
/*      */     
/*  442 */     double ry0 = t - (int)t;
/*  443 */     double ry1 = ry0 - 1.0D;
/*  444 */     double sy = s_curve(ry0);
/*      */     
/*  446 */     switch (this.channels.length) {
/*      */       
/*      */       case 4:
/*  449 */         noise[3] = lerp(sy, lerp(sx, rx0 * this.gradient[b00 + 6] + ry0 * this.gradient[b00 + 7], rx1 * this.gradient[b10 + 6] + ry0 * this.gradient[b10 + 7]), lerp(sx, rx0 * this.gradient[b01 + 6] + ry1 * this.gradient[b01 + 7], rx1 * this.gradient[b11 + 6] + ry1 * this.gradient[b11 + 7]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  458 */         noise[2] = lerp(sy, lerp(sx, rx0 * this.gradient[b00 + 4] + ry0 * this.gradient[b00 + 5], rx1 * this.gradient[b10 + 4] + ry0 * this.gradient[b10 + 5]), lerp(sx, rx0 * this.gradient[b01 + 4] + ry1 * this.gradient[b01 + 5], rx1 * this.gradient[b11 + 4] + ry1 * this.gradient[b11 + 5]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  467 */         noise[1] = lerp(sy, lerp(sx, rx0 * this.gradient[b00 + 2] + ry0 * this.gradient[b00 + 3], rx1 * this.gradient[b10 + 2] + ry0 * this.gradient[b10 + 3]), lerp(sx, rx0 * this.gradient[b01 + 2] + ry1 * this.gradient[b01 + 3], rx1 * this.gradient[b11 + 2] + ry1 * this.gradient[b11 + 3]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  476 */         noise[0] = lerp(sy, lerp(sx, rx0 * this.gradient[b00 + 0] + ry0 * this.gradient[b00 + 1], rx1 * this.gradient[b10 + 0] + ry0 * this.gradient[b10 + 1]), lerp(sx, rx0 * this.gradient[b01 + 0] + ry1 * this.gradient[b01 + 1], rx1 * this.gradient[b11 + 0] + ry1 * this.gradient[b11 + 1]));
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int turbulence_4(double pointX, double pointY, double[] fSum) {
/*      */     int j;
/*  500 */     double ratio = 255.0D;
/*      */ 
/*      */ 
/*      */     
/*  504 */     pointX *= this.baseFrequencyX;
/*  505 */     pointY *= this.baseFrequencyY;
/*  506 */     fSum[3] = 0.0D; fSum[2] = 0.0D; fSum[1] = 0.0D; fSum[0] = 0.0D;
/*      */     
/*  508 */     for (int nOctave = this.numOctaves; nOctave > 0; nOctave--) {
/*  509 */       double px = pointX + 4096.0D;
/*      */       
/*  511 */       int b0 = (int)px & 0xFF;
/*  512 */       int k = this.latticeSelector[b0];
/*  513 */       j = this.latticeSelector[b0 + 1];
/*      */       
/*  515 */       double rx0 = px - (int)px;
/*  516 */       double rx1 = rx0 - 1.0D;
/*  517 */       double sx = s_curve(rx0);
/*      */       
/*  519 */       double py = pointY + 4096.0D;
/*  520 */       b0 = (int)py & 0xFF;
/*  521 */       int b1 = b0 + 1 & 0xFF;
/*      */       
/*  523 */       b1 = (j + b0 & 0xFF) << 3;
/*  524 */       b0 = (k + b0 & 0xFF) << 3;
/*      */       
/*  526 */       double ry0 = py - (int)py;
/*  527 */       double ry1 = ry0 - 1.0D;
/*  528 */       double sy = s_curve(ry0);
/*      */       
/*  530 */       double n = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 0] + ry0 * this.gradient[b0 + 1], rx1 * this.gradient[b1 + 0] + ry0 * this.gradient[b1 + 1]), lerp(sx, rx0 * this.gradient[b0 + 8 + 0] + ry1 * this.gradient[b0 + 8 + 1], rx1 * this.gradient[b1 + 8 + 0] + ry1 * this.gradient[b1 + 8 + 1]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  538 */       if (n < 0.0D) { fSum[0] = fSum[0] - n * ratio; }
/*  539 */       else { fSum[0] = fSum[0] + n * ratio; }
/*      */       
/*  541 */       n = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 2] + ry0 * this.gradient[b0 + 3], rx1 * this.gradient[b1 + 2] + ry0 * this.gradient[b1 + 3]), lerp(sx, rx0 * this.gradient[b0 + 8 + 2] + ry1 * this.gradient[b0 + 8 + 3], rx1 * this.gradient[b1 + 8 + 2] + ry1 * this.gradient[b1 + 8 + 3]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  549 */       if (n < 0.0D) { fSum[1] = fSum[1] - n * ratio; }
/*  550 */       else { fSum[1] = fSum[1] + n * ratio; }
/*      */       
/*  552 */       n = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 4] + ry0 * this.gradient[b0 + 5], rx1 * this.gradient[b1 + 4] + ry0 * this.gradient[b1 + 5]), lerp(sx, rx0 * this.gradient[b0 + 8 + 4] + ry1 * this.gradient[b0 + 8 + 5], rx1 * this.gradient[b1 + 8 + 4] + ry1 * this.gradient[b1 + 8 + 5]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  560 */       if (n < 0.0D) { fSum[2] = fSum[2] - n * ratio; }
/*  561 */       else { fSum[2] = fSum[2] + n * ratio; }
/*      */       
/*  563 */       n = lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 6] + ry0 * this.gradient[b0 + 7], rx1 * this.gradient[b1 + 6] + ry0 * this.gradient[b1 + 7]), lerp(sx, rx0 * this.gradient[b0 + 8 + 6] + ry1 * this.gradient[b0 + 8 + 7], rx1 * this.gradient[b1 + 8 + 6] + ry1 * this.gradient[b1 + 8 + 7]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  570 */       if (n < 0.0D) { fSum[3] = fSum[3] - n * ratio; }
/*  571 */       else { fSum[3] = fSum[3] + n * ratio; }
/*      */       
/*  573 */       ratio *= 0.5D;
/*  574 */       pointX *= 2.0D;
/*  575 */       pointY *= 2.0D;
/*      */     } 
/*      */     
/*  578 */     int i = (int)fSum[0];
/*  579 */     if ((i & 0xFFFFFF00) == 0) { j = i << 16; }
/*  580 */     else { j = ((i & Integer.MIN_VALUE) != 0) ? 0 : 16711680; }
/*      */     
/*  582 */     i = (int)fSum[1];
/*  583 */     if ((i & 0xFFFFFF00) == 0) { j |= i << 8; }
/*  584 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : 65280; }
/*      */     
/*  586 */     i = (int)fSum[2];
/*  587 */     if ((i & 0xFFFFFF00) == 0) { j |= i; }
/*  588 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : 255; }
/*      */     
/*  590 */     i = (int)fSum[3];
/*  591 */     if ((i & 0xFFFFFF00) == 0) { j |= i << 24; }
/*  592 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : -16777216; }
/*  593 */      return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void turbulence(int[] rgb, double pointX, double pointY, double[] fSum, double[] noise) {
/*      */     int nOctave;
/*  612 */     fSum[3] = 0.0D; fSum[2] = 0.0D; fSum[1] = 0.0D; fSum[0] = 0.0D;
/*  613 */     double ratio = 255.0D;
/*  614 */     pointX *= this.baseFrequencyX;
/*  615 */     pointY *= this.baseFrequencyY;
/*  616 */     switch (this.channels.length) {
/*      */       case 4:
/*  618 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  619 */           noise2(noise, pointX, pointY);
/*      */           
/*  621 */           if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  622 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  623 */            if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  624 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  625 */            if (noise[2] < 0.0D) { fSum[2] = fSum[2] - noise[2] * ratio; }
/*  626 */           else { fSum[2] = fSum[2] + noise[2] * ratio; }
/*  627 */            if (noise[3] < 0.0D) { fSum[3] = fSum[3] - noise[3] * ratio; }
/*  628 */           else { fSum[3] = fSum[3] + noise[3] * ratio; }
/*  629 */            ratio *= 0.5D;
/*  630 */           pointX *= 2.0D;
/*  631 */           pointY *= 2.0D;
/*      */         } 
/*      */         
/*  634 */         rgb[0] = (int)fSum[0];
/*  635 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  636 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  637 */         rgb[1] = (int)fSum[1];
/*  638 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  639 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  640 */         rgb[2] = (int)fSum[2];
/*  641 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/*  642 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  643 */         rgb[3] = (int)fSum[3];
/*  644 */         if ((rgb[3] & 0xFFFFFF00) != 0)
/*  645 */           rgb[3] = ((rgb[3] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 3:
/*  648 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  649 */           noise2(noise, pointX, pointY);
/*      */           
/*  651 */           if (noise[2] < 0.0D) { fSum[2] = fSum[2] - noise[2] * ratio; }
/*  652 */           else { fSum[2] = fSum[2] + noise[2] * ratio; }
/*  653 */            if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  654 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  655 */            if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  656 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  657 */            ratio *= 0.5D;
/*  658 */           pointX *= 2.0D;
/*  659 */           pointY *= 2.0D;
/*      */         } 
/*  661 */         rgb[2] = (int)fSum[2];
/*  662 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/*  663 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  664 */         rgb[1] = (int)fSum[1];
/*  665 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  666 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  667 */         rgb[0] = (int)fSum[0];
/*  668 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  669 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 2:
/*  672 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  673 */           noise2(noise, pointX, pointY);
/*      */           
/*  675 */           if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  676 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  677 */            if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  678 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  679 */            ratio *= 0.5D;
/*  680 */           pointX *= 2.0D;
/*  681 */           pointY *= 2.0D;
/*      */         } 
/*      */         
/*  684 */         rgb[1] = (int)fSum[1];
/*  685 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  686 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  687 */         rgb[0] = (int)fSum[0];
/*  688 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  689 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 1:
/*  692 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  693 */           noise2(noise, pointX, pointY);
/*      */           
/*  695 */           if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  696 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  697 */            ratio *= 0.5D;
/*  698 */           pointX *= 2.0D;
/*  699 */           pointY *= 2.0D;
/*      */         } 
/*      */         
/*  702 */         rgb[0] = (int)fSum[0];
/*  703 */         if ((rgb[0] & 0xFFFFFF00) != 0) {
/*  704 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void turbulenceStitch(int[] rgb, double pointX, double pointY, double[] fSum, double[] noise, StitchInfo stitchInfo) {
/*      */     int nOctave;
/*  725 */     double ratio = 1.0D;
/*  726 */     pointX *= this.baseFrequencyX;
/*  727 */     pointY *= this.baseFrequencyY;
/*  728 */     fSum[3] = 0.0D; fSum[2] = 0.0D; fSum[1] = 0.0D; fSum[0] = 0.0D;
/*  729 */     switch (this.channels.length) {
/*      */       case 4:
/*  731 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  732 */           noise2Stitch(noise, pointX, pointY, stitchInfo);
/*      */           
/*  734 */           if (noise[3] < 0.0D) { fSum[3] = fSum[3] - noise[3] * ratio; }
/*  735 */           else { fSum[3] = fSum[3] + noise[3] * ratio; }
/*  736 */            if (noise[2] < 0.0D) { fSum[2] = fSum[2] - noise[2] * ratio; }
/*  737 */           else { fSum[2] = fSum[2] + noise[2] * ratio; }
/*  738 */            if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  739 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  740 */            if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  741 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  742 */            ratio *= 0.5D;
/*  743 */           pointX *= 2.0D;
/*  744 */           pointY *= 2.0D;
/*      */           
/*  746 */           stitchInfo.doubleFrequency();
/*      */         } 
/*  748 */         rgb[3] = (int)(fSum[3] * 255.0D);
/*  749 */         if ((rgb[3] & 0xFFFFFF00) != 0)
/*  750 */           rgb[3] = ((rgb[3] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  751 */         rgb[2] = (int)(fSum[2] * 255.0D);
/*  752 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/*  753 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  754 */         rgb[1] = (int)(fSum[1] * 255.0D);
/*  755 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  756 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  757 */         rgb[0] = (int)(fSum[0] * 255.0D);
/*  758 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  759 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 3:
/*  762 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  763 */           noise2Stitch(noise, pointX, pointY, stitchInfo);
/*  764 */           if (noise[2] < 0.0D) { fSum[2] = fSum[2] - noise[2] * ratio; }
/*  765 */           else { fSum[2] = fSum[2] + noise[2] * ratio; }
/*  766 */            if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  767 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  768 */            if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  769 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  770 */            ratio *= 0.5D;
/*  771 */           pointX *= 2.0D;
/*  772 */           pointY *= 2.0D;
/*      */           
/*  774 */           stitchInfo.doubleFrequency();
/*      */         } 
/*  776 */         rgb[2] = (int)(fSum[2] * 255.0D);
/*  777 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/*  778 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  779 */         rgb[1] = (int)(fSum[1] * 255.0D);
/*  780 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  781 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  782 */         rgb[0] = (int)(fSum[0] * 255.0D);
/*  783 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  784 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 2:
/*  787 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  788 */           noise2Stitch(noise, pointX, pointY, stitchInfo);
/*  789 */           if (noise[1] < 0.0D) { fSum[1] = fSum[1] - noise[1] * ratio; }
/*  790 */           else { fSum[1] = fSum[1] + noise[1] * ratio; }
/*  791 */            if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  792 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  793 */            ratio *= 0.5D;
/*  794 */           pointX *= 2.0D;
/*  795 */           pointY *= 2.0D;
/*      */           
/*  797 */           stitchInfo.doubleFrequency();
/*      */         } 
/*  799 */         rgb[1] = (int)(fSum[1] * 255.0D);
/*  800 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  801 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*  802 */         rgb[0] = (int)(fSum[0] * 255.0D);
/*  803 */         if ((rgb[0] & 0xFFFFFF00) != 0)
/*  804 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */         break;
/*      */       case 1:
/*  807 */         for (nOctave = 0; nOctave < this.numOctaves; nOctave++) {
/*  808 */           noise2Stitch(noise, pointX, pointY, stitchInfo);
/*  809 */           if (noise[0] < 0.0D) { fSum[0] = fSum[0] - noise[0] * ratio; }
/*  810 */           else { fSum[0] = fSum[0] + noise[0] * ratio; }
/*  811 */            ratio *= 0.5D;
/*  812 */           pointX *= 2.0D;
/*  813 */           pointY *= 2.0D;
/*      */           
/*  815 */           stitchInfo.doubleFrequency();
/*      */         } 
/*  817 */         rgb[0] = (int)(fSum[0] * 255.0D);
/*  818 */         if ((rgb[0] & 0xFFFFFF00) != 0) {
/*  819 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int turbulenceFractal_4(double pointX, double pointY, double[] fSum) {
/*      */     int j;
/*  837 */     double ratio = 127.5D;
/*      */     
/*  839 */     pointX *= this.baseFrequencyX;
/*  840 */     pointY *= this.baseFrequencyY;
/*  841 */     fSum[3] = 127.5D; fSum[2] = 127.5D; fSum[1] = 127.5D; fSum[0] = 127.5D;
/*      */     
/*  843 */     for (int nOctave = this.numOctaves; nOctave > 0; nOctave--) {
/*  844 */       double px = pointX + 4096.0D;
/*      */       
/*  846 */       int b0 = (int)px & 0xFF;
/*  847 */       int k = this.latticeSelector[b0];
/*  848 */       j = this.latticeSelector[b0 + 1];
/*      */       
/*  850 */       double rx0 = px - (int)px;
/*  851 */       double rx1 = rx0 - 1.0D;
/*  852 */       double sx = s_curve(rx0);
/*      */       
/*  854 */       double py = pointY + 4096.0D;
/*  855 */       b0 = (int)py & 0xFF;
/*  856 */       int b1 = b0 + 1 & 0xFF;
/*      */       
/*  858 */       b1 = (j + b0 & 0xFF) << 3;
/*  859 */       b0 = (k + b0 & 0xFF) << 3;
/*      */       
/*  861 */       double ry0 = py - (int)py;
/*  862 */       double ry1 = ry0 - 1.0D;
/*  863 */       double sy = s_curve(ry0);
/*      */       
/*  865 */       fSum[0] = fSum[0] + lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 0] + ry0 * this.gradient[b0 + 1], rx1 * this.gradient[b1 + 0] + ry0 * this.gradient[b1 + 1]), lerp(sx, rx0 * this.gradient[b0 + 8 + 0] + ry1 * this.gradient[b0 + 8 + 1], rx1 * this.gradient[b1 + 8 + 0] + ry1 * this.gradient[b1 + 8 + 1])) * ratio;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  873 */       fSum[1] = fSum[1] + lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 2] + ry0 * this.gradient[b0 + 3], rx1 * this.gradient[b1 + 2] + ry0 * this.gradient[b1 + 3]), lerp(sx, rx0 * this.gradient[b0 + 8 + 2] + ry1 * this.gradient[b0 + 8 + 3], rx1 * this.gradient[b1 + 8 + 2] + ry1 * this.gradient[b1 + 8 + 3])) * ratio;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  881 */       fSum[2] = fSum[2] + lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 4] + ry0 * this.gradient[b0 + 5], rx1 * this.gradient[b1 + 4] + ry0 * this.gradient[b1 + 5]), lerp(sx, rx0 * this.gradient[b0 + 8 + 4] + ry1 * this.gradient[b0 + 8 + 5], rx1 * this.gradient[b1 + 8 + 4] + ry1 * this.gradient[b1 + 8 + 5])) * ratio;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  889 */       fSum[3] = fSum[3] + lerp(sy, lerp(sx, rx0 * this.gradient[b0 + 6] + ry0 * this.gradient[b0 + 7], rx1 * this.gradient[b1 + 6] + ry0 * this.gradient[b1 + 7]), lerp(sx, rx0 * this.gradient[b0 + 8 + 6] + ry1 * this.gradient[b0 + 8 + 7], rx1 * this.gradient[b1 + 8 + 6] + ry1 * this.gradient[b1 + 8 + 7])) * ratio;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  897 */       ratio *= 0.5D;
/*  898 */       pointX *= 2.0D;
/*  899 */       pointY *= 2.0D;
/*      */     } 
/*      */     
/*  902 */     int i = (int)fSum[0];
/*  903 */     if ((i & 0xFFFFFF00) == 0) { j = i << 16; }
/*  904 */     else { j = ((i & Integer.MIN_VALUE) != 0) ? 0 : 16711680; }
/*      */     
/*  906 */     i = (int)fSum[1];
/*  907 */     if ((i & 0xFFFFFF00) == 0) { j |= i << 8; }
/*  908 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : 65280; }
/*      */     
/*  910 */     i = (int)fSum[2];
/*  911 */     if ((i & 0xFFFFFF00) == 0) { j |= i; }
/*  912 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : 255; }
/*      */     
/*  914 */     i = (int)fSum[3];
/*  915 */     if ((i & 0xFFFFFF00) == 0) { j |= i << 24; }
/*  916 */     else { j |= ((i & Integer.MIN_VALUE) != 0) ? 0 : -16777216; }
/*  917 */      return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void turbulenceFractal(int[] rgb, double pointX, double pointY, double[] fSum, double[] noise) {
/*  935 */     double ratio = 127.5D;
/*      */     
/*  937 */     fSum[3] = 127.5D; fSum[2] = 127.5D; fSum[1] = 127.5D; fSum[0] = 127.5D;
/*  938 */     pointX *= this.baseFrequencyX;
/*  939 */     pointY *= this.baseFrequencyY;
/*  940 */     for (int nOctave = this.numOctaves; nOctave > 0; nOctave--) {
/*  941 */       noise2(noise, pointX, pointY);
/*      */       
/*  943 */       switch (this.channels.length) {
/*      */         case 4:
/*  945 */           fSum[3] = fSum[3] + noise[3] * ratio;
/*      */         case 3:
/*  947 */           fSum[2] = fSum[2] + noise[2] * ratio;
/*      */         case 2:
/*  949 */           fSum[1] = fSum[1] + noise[1] * ratio;
/*      */         case 1:
/*  951 */           fSum[0] = fSum[0] + noise[0] * ratio;
/*      */           break;
/*      */       } 
/*  954 */       ratio *= 0.5D;
/*  955 */       pointX *= 2.0D;
/*  956 */       pointY *= 2.0D;
/*      */     } 
/*      */     
/*  959 */     switch (this.channels.length) {
/*      */       case 4:
/*  961 */         rgb[3] = (int)fSum[3];
/*  962 */         if ((rgb[3] & 0xFFFFFF00) != 0)
/*  963 */           rgb[3] = ((rgb[3] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 3:
/*  965 */         rgb[2] = (int)fSum[2];
/*  966 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/*  967 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 2:
/*  969 */         rgb[1] = (int)fSum[1];
/*  970 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/*  971 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 1:
/*  973 */         rgb[0] = (int)fSum[0];
/*  974 */         if ((rgb[0] & 0xFFFFFF00) != 0) {
/*  975 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void turbulenceFractalStitch(int[] rgb, double pointX, double pointY, double[] fSum, double[] noise, StitchInfo stitchInfo) {
/*  996 */     double ratio = 127.5D;
/*      */     
/*  998 */     fSum[3] = 127.5D; fSum[2] = 127.5D; fSum[1] = 127.5D; fSum[0] = 127.5D;
/*  999 */     pointX *= this.baseFrequencyX;
/* 1000 */     pointY *= this.baseFrequencyY;
/* 1001 */     for (int nOctave = this.numOctaves; nOctave > 0; nOctave--) {
/* 1002 */       noise2Stitch(noise, pointX, pointY, stitchInfo);
/*      */       
/* 1004 */       switch (this.channels.length) {
/*      */         case 4:
/* 1006 */           fSum[3] = fSum[3] + noise[3] * ratio;
/*      */         case 3:
/* 1008 */           fSum[2] = fSum[2] + noise[2] * ratio;
/*      */         case 2:
/* 1010 */           fSum[1] = fSum[1] + noise[1] * ratio;
/*      */         case 1:
/* 1012 */           fSum[0] = fSum[0] + noise[0] * ratio;
/*      */           break;
/*      */       } 
/* 1015 */       ratio *= 0.5D;
/* 1016 */       pointX *= 2.0D;
/* 1017 */       pointY *= 2.0D;
/* 1018 */       stitchInfo.doubleFrequency();
/*      */     } 
/*      */     
/* 1021 */     switch (this.channels.length) {
/*      */       case 4:
/* 1023 */         rgb[3] = (int)fSum[3];
/* 1024 */         if ((rgb[3] & 0xFFFFFF00) != 0)
/* 1025 */           rgb[3] = ((rgb[3] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 3:
/* 1027 */         rgb[2] = (int)fSum[2];
/* 1028 */         if ((rgb[2] & 0xFFFFFF00) != 0)
/* 1029 */           rgb[2] = ((rgb[2] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 2:
/* 1031 */         rgb[1] = (int)fSum[1];
/* 1032 */         if ((rgb[1] & 0xFFFFFF00) != 0)
/* 1033 */           rgb[1] = ((rgb[1] & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*      */       case 1:
/* 1035 */         rgb[0] = (int)fSum[0];
/* 1036 */         if ((rgb[0] & 0xFFFFFF00) != 0) {
/* 1037 */           rgb[0] = ((rgb[0] & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WritableRaster copyData(WritableRaster dest) {
/* 1049 */     if (dest == null) {
/* 1050 */       throw new IllegalArgumentException("Cannot generate a noise pattern into a null raster");
/*      */     }
/*      */ 
/*      */     
/* 1054 */     int w = dest.getWidth();
/* 1055 */     int h = dest.getHeight();
/*      */ 
/*      */     
/* 1058 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*      */     
/* 1060 */     int minX = dest.getMinX();
/* 1061 */     int minY = dest.getMinY();
/* 1062 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)dest.getSampleModel();
/* 1063 */     int dstOff = dstDB.getOffset() + sppsm.getOffset(minX - dest.getSampleModelTranslateX(), minY - dest.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */     
/* 1067 */     int[] destPixels = dstDB.getBankData()[0];
/* 1068 */     int dstAdjust = sppsm.getScanlineStride() - w;
/*      */ 
/*      */     
/* 1071 */     int dp = dstOff;
/* 1072 */     int[] rgb = new int[4];
/* 1073 */     double[] fSum = { 0.0D, 0.0D, 0.0D, 0.0D };
/* 1074 */     double[] noise = { 0.0D, 0.0D, 0.0D, 0.0D };
/*      */ 
/*      */     
/* 1077 */     double tx0 = this.tx[0];
/* 1078 */     double tx1 = this.tx[1];
/*      */ 
/*      */     
/* 1081 */     double ty0 = this.ty[0] - w * tx0;
/* 1082 */     double ty1 = this.ty[1] - w * tx1;
/*      */     
/* 1084 */     double[] p = { minX, minY };
/* 1085 */     this.txf.transform(p, 0, p, 0, 1);
/* 1086 */     double point_0 = p[0];
/* 1087 */     double point_1 = p[1];
/*      */     
/* 1089 */     if (this.isFractalNoise) {
/* 1090 */       if (this.stitchInfo == null) {
/* 1091 */         if (this.channels.length == 4) {
/* 1092 */           for (int i = 0; i < h; i++) {
/* 1093 */             for (int end = dp + w; dp < end; dp++) {
/* 1094 */               destPixels[dp] = turbulenceFractal_4(point_0, point_1, fSum);
/*      */               
/* 1096 */               point_0 += tx0;
/* 1097 */               point_1 += tx1;
/*      */             } 
/* 1099 */             point_0 += ty0;
/* 1100 */             point_1 += ty1;
/* 1101 */             dp += dstAdjust;
/*      */           } 
/*      */         } else {
/* 1104 */           for (int i = 0; i < h; i++) {
/* 1105 */             for (int end = dp + w; dp < end; dp++) {
/* 1106 */               turbulenceFractal(rgb, point_0, point_1, fSum, noise);
/*      */ 
/*      */               
/* 1109 */               destPixels[dp] = rgb[3] << 24 | rgb[0] << 16 | rgb[1] << 8 | rgb[2];
/*      */ 
/*      */ 
/*      */               
/* 1113 */               point_0 += tx0;
/* 1114 */               point_1 += tx1;
/*      */             } 
/* 1116 */             point_0 += ty0;
/* 1117 */             point_1 += ty1;
/* 1118 */             dp += dstAdjust;
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1123 */         StitchInfo si = new StitchInfo();
/* 1124 */         for (int i = 0; i < h; i++) {
/* 1125 */           for (int end = dp + w; dp < end; dp++) {
/* 1126 */             si.assign(this.stitchInfo);
/* 1127 */             turbulenceFractalStitch(rgb, point_0, point_1, fSum, noise, si);
/*      */ 
/*      */ 
/*      */             
/* 1131 */             destPixels[dp] = rgb[3] << 24 | rgb[0] << 16 | rgb[1] << 8 | rgb[2];
/*      */ 
/*      */ 
/*      */             
/* 1135 */             point_0 += tx0;
/* 1136 */             point_1 += tx1;
/*      */           } 
/* 1138 */           point_0 += ty0;
/* 1139 */           point_1 += ty1;
/* 1140 */           dp += dstAdjust;
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1145 */     } else if (this.stitchInfo == null) {
/* 1146 */       if (this.channels.length == 4) {
/* 1147 */         for (int i = 0; i < h; i++) {
/* 1148 */           for (int end = dp + w; dp < end; dp++) {
/* 1149 */             destPixels[dp] = turbulence_4(point_0, point_1, fSum);
/*      */ 
/*      */             
/* 1152 */             point_0 += tx0;
/* 1153 */             point_1 += tx1;
/*      */           } 
/* 1155 */           point_0 += ty0;
/* 1156 */           point_1 += ty1;
/* 1157 */           dp += dstAdjust;
/*      */         } 
/*      */       } else {
/* 1160 */         for (int i = 0; i < h; i++) {
/* 1161 */           for (int end = dp + w; dp < end; dp++) {
/* 1162 */             turbulence(rgb, point_0, point_1, fSum, noise);
/*      */ 
/*      */             
/* 1165 */             destPixels[dp] = rgb[3] << 24 | rgb[0] << 16 | rgb[1] << 8 | rgb[2];
/*      */ 
/*      */ 
/*      */             
/* 1169 */             point_0 += tx0;
/* 1170 */             point_1 += tx1;
/*      */           } 
/* 1172 */           point_0 += ty0;
/* 1173 */           point_1 += ty1;
/* 1174 */           dp += dstAdjust;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1179 */       StitchInfo si = new StitchInfo();
/* 1180 */       for (int i = 0; i < h; i++) {
/* 1181 */         for (int end = dp + w; dp < end; dp++) {
/* 1182 */           si.assign(this.stitchInfo);
/* 1183 */           turbulenceStitch(rgb, point_0, point_1, fSum, noise, si);
/*      */ 
/*      */ 
/*      */           
/* 1187 */           destPixels[dp] = rgb[3] << 24 | rgb[0] << 16 | rgb[1] << 8 | rgb[2];
/*      */ 
/*      */ 
/*      */           
/* 1191 */           point_0 += tx0;
/* 1192 */           point_1 += tx1;
/*      */         } 
/* 1194 */         point_0 += ty0;
/* 1195 */         point_1 += ty1;
/* 1196 */         dp += dstAdjust;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1201 */     return dest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TurbulencePatternRed(double baseFrequencyX, double baseFrequencyY, int numOctaves, int seed, boolean isFractalNoise, Rectangle2D tile, AffineTransform txf, Rectangle devRect, ColorSpace cs, boolean alpha) {
/*      */     ColorModel cm;
/*      */     this.stitchInfo = null;
/*      */     this.tx = new double[] { 1.0D, 0.0D };
/*      */     this.ty = new double[] { 0.0D, 1.0D };
/*      */     this.latticeSelector = new int[257];
/*      */     this.gradient = new double[2056];
/* 1230 */     this.baseFrequencyX = baseFrequencyX;
/* 1231 */     this.baseFrequencyY = baseFrequencyY;
/* 1232 */     this.seed = seed;
/* 1233 */     this.isFractalNoise = isFractalNoise;
/* 1234 */     this.tile = tile;
/* 1235 */     this.txf = txf;
/*      */     
/* 1237 */     if (this.txf == null) {
/* 1238 */       this.txf = IDENTITY;
/*      */     }
/* 1240 */     int nChannels = cs.getNumComponents();
/* 1241 */     if (alpha) nChannels++; 
/* 1242 */     this.channels = new int[nChannels];
/* 1243 */     for (int i = 0; i < this.channels.length; i++) {
/* 1244 */       this.channels[i] = i;
/*      */     }
/* 1246 */     txf.deltaTransform(this.tx, 0, this.tx, 0, 1);
/* 1247 */     txf.deltaTransform(this.ty, 0, this.ty, 0, 1);
/*      */     
/* 1249 */     double[] vecX = { 0.5D, 0.0D };
/* 1250 */     double[] vecY = { 0.0D, 0.5D };
/* 1251 */     txf.deltaTransform(vecX, 0, vecX, 0, 1);
/* 1252 */     txf.deltaTransform(vecY, 0, vecY, 0, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1267 */     double dx = Math.max(Math.abs(vecX[0]), Math.abs(vecY[0]));
/* 1268 */     int maxX = -((int)Math.round((Math.log(dx) + Math.log(baseFrequencyX)) / Math.log(2.0D)));
/*      */ 
/*      */     
/* 1271 */     double dy = Math.max(Math.abs(vecX[1]), Math.abs(vecY[1]));
/* 1272 */     int maxY = -((int)Math.round((Math.log(dy) + Math.log(baseFrequencyY)) / Math.log(2.0D)));
/*      */ 
/*      */     
/* 1275 */     this.numOctaves = (numOctaves > maxX) ? maxX : numOctaves;
/* 1276 */     this.numOctaves = (this.numOctaves > maxY) ? maxY : this.numOctaves;
/*      */     
/* 1278 */     if (this.numOctaves < 1 && numOctaves > 1) {
/* 1279 */       this.numOctaves = 1;
/*      */     }
/* 1281 */     if (this.numOctaves > 8)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1286 */       this.numOctaves = 8;
/*      */     }
/* 1288 */     if (tile != null) {
/*      */ 
/*      */ 
/*      */       
/* 1292 */       double lowFreq = Math.floor(tile.getWidth() * baseFrequencyX) / tile.getWidth();
/* 1293 */       double highFreq = Math.ceil(tile.getWidth() * baseFrequencyX) / tile.getWidth();
/* 1294 */       if (baseFrequencyX / lowFreq < highFreq / baseFrequencyX) {
/* 1295 */         this.baseFrequencyX = lowFreq;
/*      */       } else {
/* 1297 */         this.baseFrequencyX = highFreq;
/*      */       } 
/* 1299 */       lowFreq = Math.floor(tile.getHeight() * baseFrequencyY) / tile.getHeight();
/* 1300 */       highFreq = Math.ceil(tile.getHeight() * baseFrequencyY) / tile.getHeight();
/* 1301 */       if (baseFrequencyY / lowFreq < highFreq / baseFrequencyY) {
/* 1302 */         this.baseFrequencyY = lowFreq;
/*      */       } else {
/* 1304 */         this.baseFrequencyY = highFreq;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1310 */       this.stitchInfo = new StitchInfo();
/* 1311 */       this.stitchInfo.width = (int)(tile.getWidth() * this.baseFrequencyX);
/* 1312 */       this.stitchInfo.height = (int)(tile.getHeight() * this.baseFrequencyY);
/* 1313 */       this.stitchInfo.wrapX = (int)(tile.getX() * this.baseFrequencyX + 4096.0D + this.stitchInfo.width);
/*      */       
/* 1315 */       this.stitchInfo.wrapY = (int)(tile.getY() * this.baseFrequencyY + 4096.0D + this.stitchInfo.height);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1320 */       if (this.stitchInfo.width == 0) this.stitchInfo.width = 1; 
/* 1321 */       if (this.stitchInfo.height == 0) this.stitchInfo.height = 1;
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1329 */     initLattice(seed);
/*      */ 
/*      */     
/* 1332 */     if (alpha) {
/* 1333 */       cm = new DirectColorModel(cs, 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */     }
/*      */     else {
/*      */       
/* 1337 */       cm = new DirectColorModel(cs, 24, 16711680, 65280, 255, 0, false, 3);
/*      */     } 
/*      */ 
/*      */     
/* 1341 */     int tileSize = AbstractTiledRed.getDefaultTileSize();
/* 1342 */     init((CachableRed)null, devRect, cm, cm.createCompatibleSampleModel(tileSize, tileSize), 0, 0, (Map)null);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TurbulencePatternRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */