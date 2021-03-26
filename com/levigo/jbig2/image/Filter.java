/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class Filter
/*     */ {
/*     */   final boolean cardinal;
/*     */   double support;
/*     */   double blur;
/*     */   
/*     */   public static String nameByType(FilterType paramFilterType) {
/*  30 */     if (paramFilterType == null)
/*  31 */       throw new IllegalArgumentException("type must not be null"); 
/*  32 */     return paramFilterType.name();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FilterType typeByName(String paramString) {
/*  42 */     if (paramString == null)
/*  43 */       throw new IllegalArgumentException("name must not be null"); 
/*  44 */     return FilterType.valueOf(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Filter byType(FilterType paramFilterType) {
/*  54 */     switch (paramFilterType) {
/*     */       case Bessel:
/*  56 */         return new Bessel();
/*     */       case Blackman:
/*  58 */         return new Blackman();
/*     */       case Box:
/*  60 */         return new Box();
/*     */       case Catrom:
/*  62 */         return new Catrom();
/*     */       case Cubic:
/*  64 */         return new Cubic();
/*     */       case Gaussian:
/*  66 */         return new Gaussian();
/*     */       case Hamming:
/*  68 */         return new Hamming();
/*     */       case Hanning:
/*  70 */         return new Hanning();
/*     */       case Hermite:
/*  72 */         return new Hermite();
/*     */       case Lanczos:
/*  74 */         return new Lanczos();
/*     */       case Mitchell:
/*  76 */         return new Mitchell();
/*     */       case Point:
/*  78 */         return new Point();
/*     */       case Quadratic:
/*  80 */         return new Quadratic();
/*     */       case Sinc:
/*  82 */         return new Sinc();
/*     */       case Triangle:
/*  84 */         return new Triangle();
/*     */     } 
/*  86 */     throw new IllegalArgumentException("No filter for given type.");
/*     */   }
/*     */   
/*     */   public static final class Bessel extends Filter {
/*     */     public Bessel() {
/*  91 */       super(false, 3.2383D, 1.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private double J1(double param1Double) {
/*  99 */       double[] arrayOfDouble1 = { 5.811993540016061E20D, -6.672106568924916E19D, 2.3164335806340024E18D, -3.588817569910106E16D, 2.9087952638347756E14D, -1.3229834803321265E12D, 3.4132341823017006E9D, -4695753.530642996D, 2701.1227108923235D };
/*     */ 
/*     */ 
/*     */       
/* 103 */       double[] arrayOfDouble2 = { 1.1623987080032122E21D, 1.185770712190321E19D, 6.0920613989175216E16D, 2.0816612213076075E14D, 5.2437102621676495E11D, 1.013863514358674E9D, 1501793.5949985855D, 1606.9315734814877D, 1.0D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       double d1 = arrayOfDouble1[8];
/* 110 */       double d2 = arrayOfDouble2[8];
/* 111 */       for (byte b = 7; b >= 0; b--) {
/* 112 */         d1 = d1 * param1Double * param1Double + arrayOfDouble1[b];
/* 113 */         d2 = d2 * param1Double * param1Double + arrayOfDouble2[b];
/*     */       } 
/* 115 */       return d1 / d2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private double P1(double param1Double) {
/* 123 */       double[] arrayOfDouble1 = { 35224.66491336798D, 62758.84524716128D, 31353.963110915956D, 4985.4832060594335D, 211.15291828539623D, 1.2571716929145342D };
/*     */ 
/*     */       
/* 126 */       double[] arrayOfDouble2 = { 35224.66491336798D, 62694.34695935605D, 31240.406381904104D, 4930.396490181089D, 203.07751891347593D, 1.0D };
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       double d1 = arrayOfDouble1[5];
/* 132 */       double d2 = arrayOfDouble2[5];
/* 133 */       for (byte b = 4; b >= 0; b--) {
/* 134 */         d1 = d1 * 8.0D / param1Double * 8.0D / param1Double + arrayOfDouble1[b];
/* 135 */         d2 = d2 * 8.0D / param1Double * 8.0D / param1Double + arrayOfDouble2[b];
/*     */       } 
/* 137 */       return d1 / d2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private double Q1(double param1Double) {
/* 145 */       double[] arrayOfDouble1 = { 351.17519143035526D, 721.0391804904475D, 425.98730116544425D, 83.18989576738508D, 4.568171629551227D, 0.03532840052740124D };
/*     */ 
/*     */       
/* 148 */       double[] arrayOfDouble2 = { 7491.737417180912D, 15414.177339265098D, 9152.231701516992D, 1811.1867005523513D, 103.81875854621337D, 1.0D };
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       double d1 = arrayOfDouble1[5];
/* 154 */       double d2 = arrayOfDouble2[5];
/* 155 */       for (byte b = 4; b >= 0; b--) {
/* 156 */         d1 = d1 * 8.0D / param1Double * 8.0D / param1Double + arrayOfDouble1[b];
/* 157 */         d2 = d2 * 8.0D / param1Double * 8.0D / param1Double + arrayOfDouble2[b];
/*     */       } 
/* 159 */       return d1 / d2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private double BesselOrderOne(double param1Double) {
/* 165 */       if (param1Double == 0.0D)
/* 166 */         return 0.0D; 
/* 167 */       double d1 = param1Double;
/* 168 */       if (param1Double < 0.0D)
/* 169 */         param1Double = -param1Double; 
/* 170 */       if (param1Double < 8.0D)
/* 171 */         return d1 * J1(param1Double); 
/* 172 */       double d2 = Math.sqrt(2.0D / Math.PI * param1Double) * (P1(param1Double) * 1.0D / Math.sqrt(2.0D) * (Math.sin(param1Double) - Math.cos(param1Double)) - 8.0D / param1Double * Q1(param1Double) * -1.0D / Math.sqrt(2.0D) * (Math.sin(param1Double) + Math.cos(param1Double)));
/*     */ 
/*     */       
/* 175 */       if (d1 < 0.0D)
/* 176 */         d2 = -d2; 
/* 177 */       return d2;
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 182 */       if (param1Double == 0.0D)
/* 183 */         return 0.7853981633974483D; 
/* 184 */       return BesselOrderOne(Math.PI * param1Double) / 2.0D * param1Double;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Blackman
/*     */     extends Filter {
/*     */     public double f(double param1Double) {
/* 191 */       return 0.42D + 0.5D * Math.cos(Math.PI * param1Double) + 0.08D * Math.cos(6.283185307179586D * param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Box extends Filter {
/*     */     public Box() {
/* 197 */       super(true, 0.5D, 1.0D);
/*     */     }
/*     */     
/*     */     public Box(double param1Double) {
/* 201 */       super(true, param1Double, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 206 */       if (param1Double >= -0.5D && param1Double < 0.5D)
/* 207 */         return 1.0D; 
/* 208 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Point extends Box {
/*     */     public Point() {
/* 214 */       super(0.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public double fWindowed(double param1Double) {
/* 220 */       return f(param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Catrom extends Filter {
/*     */     public Catrom() {
/* 226 */       super(true, 2.0D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 231 */       if (param1Double < 0.0D)
/* 232 */         param1Double = -param1Double; 
/* 233 */       if (param1Double < 1.0D)
/* 234 */         return 0.5D * (2.0D + param1Double * param1Double * (-5.0D + param1Double * 3.0D)); 
/* 235 */       if (param1Double < 2.0D)
/* 236 */         return 0.5D * (4.0D + param1Double * (-8.0D + param1Double * (5.0D - param1Double))); 
/* 237 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Cubic extends Filter {
/*     */     public Cubic() {
/* 243 */       super(false, 2.0D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 248 */       if (param1Double < 0.0D)
/* 249 */         param1Double = -param1Double; 
/* 250 */       if (param1Double < 1.0D)
/* 251 */         return 0.5D * param1Double * param1Double * param1Double - param1Double * param1Double + 0.6666666666666666D; 
/* 252 */       if (param1Double < 2.0D) {
/* 253 */         param1Double = 2.0D - param1Double;
/* 254 */         return 0.16666666666666666D * param1Double * param1Double * param1Double;
/*     */       } 
/* 256 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Gaussian extends Filter {
/*     */     public Gaussian() {
/* 262 */       super(false, 1.25D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 267 */       return Math.exp(-2.0D * param1Double * param1Double) * Math.sqrt(0.6366197723675814D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Hamming
/*     */     extends Filter {
/*     */     public double f(double param1Double) {
/* 274 */       return 0.54D + 0.46D * Math.cos(Math.PI * param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Hanning
/*     */     extends Filter {
/*     */     public double f(double param1Double) {
/* 281 */       return 0.5D + 0.5D * Math.cos(Math.PI * param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Hermite
/*     */     extends Filter {
/*     */     public double f(double param1Double) {
/* 288 */       if (param1Double < 0.0D) {
/* 289 */         param1Double = -param1Double;
/*     */       }
/*     */       
/* 292 */       if (param1Double < 1.0D) {
/* 293 */         return (2.0D * param1Double - 3.0D) * param1Double * param1Double + 1.0D;
/*     */       }
/* 295 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Lanczos extends Filter {
/*     */     public Lanczos() {
/* 301 */       super(true, 3.0D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 306 */       if (param1Double < 0.0D)
/* 307 */         param1Double = -param1Double; 
/* 308 */       if (param1Double < 3.0D)
/* 309 */         return (float)(sinc(param1Double) * sinc(param1Double / 3.0D)); 
/* 310 */       return 0.0D;
/*     */     }
/*     */     
/*     */     private double sinc(double param1Double) {
/* 314 */       if (param1Double != 0.0D) {
/* 315 */         param1Double *= Math.PI;
/* 316 */         return Math.sin(param1Double) / param1Double;
/*     */       } 
/* 318 */       return 1.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Mitchell
/*     */     extends Filter
/*     */   {
/*     */     public Mitchell() {
/* 326 */       super(false, 2.0D, 1.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 333 */       double d1 = 0.3333333333333333D;
/* 334 */       double d2 = 0.3333333333333333D;
/* 335 */       if (param1Double < 0.0D)
/* 336 */         param1Double = -param1Double; 
/* 337 */       if (param1Double < 1.0D) {
/* 338 */         param1Double = (12.0D - 9.0D * d1 - 6.0D * d2) * param1Double * param1Double * param1Double + (-18.0D + 12.0D * d1 + 6.0D * d2) * param1Double * param1Double + 6.0D - 2.0D * d1;
/* 339 */         return param1Double / 6.0D;
/*     */       } 
/* 341 */       if (param1Double < 2.0D) {
/* 342 */         param1Double = (-1.0D * d1 - 6.0D * d2) * param1Double * param1Double * param1Double + (6.0D * d1 + 30.0D * d2) * param1Double * param1Double + (-12.0D * d1 - 48.0D * d2) * param1Double + 8.0D * d1 + 24.0D * d2;
/*     */         
/* 344 */         return param1Double / 6.0D;
/*     */       } 
/* 346 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Quadratic extends Filter {
/*     */     public Quadratic() {
/* 352 */       super(false, 1.5D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 357 */       if (param1Double < 0.0D)
/* 358 */         param1Double = -param1Double; 
/* 359 */       if (param1Double < 0.5D)
/* 360 */         return 0.75D - param1Double * param1Double; 
/* 361 */       if (param1Double < 1.5D) {
/* 362 */         param1Double -= 1.5D;
/* 363 */         return 0.5D * param1Double * param1Double;
/*     */       } 
/* 365 */       return 0.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Sinc extends Filter {
/*     */     public Sinc() {
/* 371 */       super(true, 4.0D, 1.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public double f(double param1Double) {
/* 376 */       param1Double *= Math.PI;
/* 377 */       if (param1Double != 0.0D)
/* 378 */         return Math.sin(param1Double) / param1Double; 
/* 379 */       return 1.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Triangle
/*     */     extends Filter {
/*     */     public double f(double param1Double) {
/* 386 */       if (param1Double < 0.0D)
/* 387 */         param1Double = -param1Double; 
/* 388 */       if (param1Double < 1.0D)
/* 389 */         return 1.0D - param1Double; 
/* 390 */       return 0.0D;
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
/*     */   protected Filter() {
/* 406 */     this(true, 1.0D, 1.0D);
/*     */   }
/*     */   
/*     */   protected Filter(boolean paramBoolean, double paramDouble1, double paramDouble2) {
/* 410 */     this.cardinal = paramBoolean;
/* 411 */     this.support = paramDouble1;
/* 412 */     this.blur = paramDouble2;
/*     */   }
/*     */   
/*     */   public double fWindowed(double paramDouble) {
/* 416 */     return (paramDouble < -this.support || paramDouble > this.support) ? 0.0D : f(paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double f(double paramDouble);
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 427 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSupport() {
/* 434 */     return this.support;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupport(double paramDouble) {
/* 441 */     this.support = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBlur() {
/* 448 */     return this.blur;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlur(double paramDouble) {
/* 455 */     this.blur = paramDouble;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/Filter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */