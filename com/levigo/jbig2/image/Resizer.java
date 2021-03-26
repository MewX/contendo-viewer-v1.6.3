/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.util.Utils;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Resizer
/*     */ {
/*     */   private static final double EPSILON = 1.0E-7D;
/*     */   
/*     */   static final class Mapping
/*     */   {
/*     */     final double scale;
/*  33 */     final double offset = 0.5D;
/*     */     
/*     */     private final double a0;
/*     */     private final double b0;
/*     */     
/*     */     Mapping(double param1Double1, double param1Double2, double param1Double3, double param1Double4) {
/*  39 */       this.a0 = param1Double1;
/*  40 */       this.b0 = param1Double3;
/*  41 */       this.scale = param1Double4 / param1Double2;
/*     */       
/*  43 */       if (this.scale <= 0.0D)
/*  44 */         throw new IllegalArgumentException("Negative scales are not allowed"); 
/*     */     }
/*     */     
/*     */     Mapping(double param1Double) {
/*  48 */       this.scale = param1Double;
/*  49 */       this.a0 = this.b0 = 0.0D;
/*     */     }
/*     */     
/*     */     double mapPixelCenter(int param1Int) {
/*  53 */       return (param1Int + 0.5D - this.b0) / this.scale + this.a0;
/*     */     }
/*     */     
/*     */     double dstToSrc(double param1Double) {
/*  57 */       return (param1Double - this.b0) / this.scale + this.a0;
/*     */     }
/*     */     
/*     */     double srcToDst(double param1Double) {
/*  61 */       return (param1Double - this.a0) * this.scale + this.b0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private enum Order
/*     */   {
/*  69 */     AUTO, XY, YX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private int weightBits = 14;
/*     */   
/*  78 */   private int weightOne = 1 << this.weightBits;
/*     */ 
/*     */   
/*  81 */   private int[] bitsPerChannel = new int[] { 8, 8, 8 };
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final int[] NO_SHIFT = new int[16];
/*     */   
/*  87 */   private int[] finalShift = new int[] { 2 * this.weightBits - this.bitsPerChannel[0], 2 * this.weightBits - this.bitsPerChannel[1], 2 * this.weightBits - this.bitsPerChannel[2] };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean debug = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isInteger(double paramDouble) {
/*  98 */     return (Math.abs(paramDouble - Math.floor(paramDouble + 0.5D)) < 1.0E-7D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean coerce = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   private final Order order = Order.AUTO;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean trimZeros = true;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Mapping mappingX;
/*     */ 
/*     */   
/*     */   private final Mapping mappingY;
/*     */ 
/*     */ 
/*     */   
/*     */   public Resizer(double paramDouble) {
/* 129 */     this(paramDouble, paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resizer(double paramDouble1, double paramDouble2) {
/* 139 */     this.mappingX = new Mapping(paramDouble1);
/* 140 */     this.mappingY = new Mapping(paramDouble2);
/*     */   }
/*     */   
/*     */   private Weighttab[] createXWeights(Rectangle paramRectangle1, Rectangle paramRectangle2, ParameterizedFilter paramParameterizedFilter) {
/* 144 */     int i = paramRectangle1.x;
/* 145 */     int j = paramRectangle1.x + paramRectangle1.width;
/*     */     
/* 147 */     int k = paramRectangle2.x;
/* 148 */     int m = paramRectangle2.x + paramRectangle2.width;
/*     */     
/* 150 */     Weighttab[] arrayOfWeighttab = new Weighttab[paramRectangle2.width];
/* 151 */     for (int n = k; n < m; n++) {
/* 152 */       double d = this.mappingX.mapPixelCenter(n);
/* 153 */       arrayOfWeighttab[n - k] = new Weighttab(paramParameterizedFilter, this.weightOne, d, i, j - 1, true);
/*     */     } 
/*     */     
/* 156 */     return arrayOfWeighttab;
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
/*     */   private ParameterizedFilter simplifyFilter(ParameterizedFilter paramParameterizedFilter, double paramDouble1, double paramDouble2) {
/* 173 */     if (paramParameterizedFilter.support <= 0.5D || (paramParameterizedFilter.filter.cardinal && isInteger(1.0D / paramParameterizedFilter.scale) && isInteger(1.0D / paramDouble1 * paramParameterizedFilter.scale) && isInteger((paramDouble2 / paramDouble1 - 0.5D) / paramParameterizedFilter.scale)))
/*     */     {
/*     */       
/* 176 */       return new ParameterizedFilter(new Filter.Point(), 1.0D, 0.5D, 1);
/*     */     }
/* 178 */     return paramParameterizedFilter;
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
/*     */   private void resizeXfirst(Object paramObject1, Rectangle paramRectangle1, Object paramObject2, Rectangle paramRectangle2, ParameterizedFilter paramParameterizedFilter1, ParameterizedFilter paramParameterizedFilter2) {
/* 192 */     Scanline scanline1 = createScanline(paramObject1, paramObject2, paramRectangle1.width);
/*     */ 
/*     */     
/* 195 */     Scanline scanline2 = createScanline(paramObject1, paramObject2, paramRectangle2.width);
/*     */ 
/*     */     
/* 198 */     Weighttab[] arrayOfWeighttab = createXWeights(paramRectangle1, paramRectangle2, paramParameterizedFilter1);
/*     */ 
/*     */     
/* 201 */     int i = paramParameterizedFilter2.width + 2;
/* 202 */     Scanline[] arrayOfScanline = new Scanline[i]; int j;
/* 203 */     for (j = 0; j < i; j++) {
/* 204 */       arrayOfScanline[j] = createScanline(paramObject1, paramObject2, paramRectangle2.width);
/* 205 */       (arrayOfScanline[j]).y = -1;
/*     */     } 
/*     */ 
/*     */     
/* 209 */     j = paramRectangle1.y;
/* 210 */     int k = paramRectangle1.y + paramRectangle1.height;
/* 211 */     int m = paramRectangle2.y;
/* 212 */     int n = paramRectangle2.y + paramRectangle2.height;
/*     */     
/* 214 */     int i1 = -1;
/*     */ 
/*     */     
/* 217 */     for (int i2 = m; i2 < n; i2++) {
/*     */       
/* 219 */       Weighttab weighttab = new Weighttab(paramParameterizedFilter2, this.weightOne, this.mappingY.mapPixelCenter(i2), j, k - 1, true);
/*     */       
/* 221 */       scanline2.clear();
/*     */ 
/*     */       
/* 224 */       for (int i3 = weighttab.i0; i3 <= weighttab.i1; i3++) {
/* 225 */         Scanline scanline = arrayOfScanline[i3 % i];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 230 */         if (scanline.y != i3) {
/*     */           
/* 232 */           scanline.y = i3;
/*     */           
/* 234 */           if (j + i3 <= i1) {
/* 235 */             throw new AssertionError("Backtracking from line " + i1 + " to " + (j + i3));
/*     */           }
/* 237 */           scanline1.fetch(paramRectangle1.x, j + i3);
/*     */           
/* 239 */           i1 = j + i3;
/*     */ 
/*     */           
/* 242 */           scanline1.filter(NO_SHIFT, this.bitsPerChannel, arrayOfWeighttab, scanline);
/*     */         } 
/*     */ 
/*     */         
/* 246 */         scanline.accumulate(weighttab.weights[i3 - weighttab.i0], scanline2);
/*     */       } 
/*     */       
/* 249 */       scanline2.shift(this.finalShift);
/* 250 */       scanline2.store(paramRectangle2.x, i2);
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
/*     */   private void resizeYfirst(Object paramObject1, Rectangle paramRectangle1, Object paramObject2, Rectangle paramRectangle2, ParameterizedFilter paramParameterizedFilter1, ParameterizedFilter paramParameterizedFilter2) {
/* 262 */     Scanline scanline1 = createScanline(paramObject1, paramObject2, paramRectangle2.width);
/*     */ 
/*     */     
/* 265 */     Scanline scanline2 = createScanline(paramObject1, paramObject2, paramRectangle1.width);
/*     */ 
/*     */     
/* 268 */     Weighttab[] arrayOfWeighttab = createXWeights(paramRectangle1, paramRectangle2, paramParameterizedFilter1);
/*     */ 
/*     */     
/* 271 */     int i = paramParameterizedFilter2.width + 2;
/* 272 */     Scanline[] arrayOfScanline = new Scanline[i]; int j;
/* 273 */     for (j = 0; j < i; j++) {
/* 274 */       arrayOfScanline[j] = createScanline(paramObject1, paramObject2, paramRectangle1.width);
/*     */ 
/*     */       
/* 277 */       (arrayOfScanline[j]).y = -1;
/*     */     } 
/*     */ 
/*     */     
/* 281 */     j = paramRectangle1.y;
/* 282 */     int k = paramRectangle1.y + paramRectangle1.height;
/* 283 */     int m = paramRectangle2.y;
/* 284 */     int n = paramRectangle2.y + paramRectangle2.height;
/*     */ 
/*     */     
/* 287 */     int i1 = -1;
/*     */ 
/*     */     
/* 290 */     for (int i2 = m; i2 < n; i2++) {
/*     */ 
/*     */       
/* 293 */       Weighttab weighttab = new Weighttab(paramParameterizedFilter2, this.weightOne, this.mappingY.mapPixelCenter(i2), j, k - 1, true);
/*     */       
/* 295 */       scanline2.clear();
/*     */ 
/*     */       
/* 298 */       for (int i3 = weighttab.i0; i3 <= weighttab.i1; i3++) {
/* 299 */         Scanline scanline = arrayOfScanline[i3 % i];
/* 300 */         if (scanline.y != i3) {
/*     */           
/* 302 */           scanline.y = i3;
/*     */           
/* 304 */           if (j + i3 <= i1) {
/* 305 */             throw new AssertionError("Backtracking from line " + i1 + " to " + (j + i3));
/*     */           }
/* 307 */           scanline.fetch(paramRectangle1.x, j + i3);
/*     */           
/* 309 */           i1 = j + i3;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 316 */         scanline.accumulate(weighttab.weights[i3 - weighttab.i0], scanline2);
/*     */       } 
/*     */ 
/*     */       
/* 320 */       scanline2.filter(this.bitsPerChannel, this.finalShift, arrayOfWeighttab, scanline1);
/*     */ 
/*     */       
/* 323 */       scanline1.store(paramRectangle2.x, i2);
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
/*     */   public void resize(Object paramObject1, Rectangle paramRectangle1, Object paramObject2, Rectangle paramRectangle2, Filter paramFilter1, Filter paramFilter2) {
/* 343 */     ParameterizedFilter parameterizedFilter1 = new ParameterizedFilter(paramFilter1, this.mappingX.scale);
/* 344 */     ParameterizedFilter parameterizedFilter2 = new ParameterizedFilter(paramFilter2, this.mappingY.scale);
/*     */ 
/*     */     
/* 347 */     Rectangle rectangle = new Rectangle();
/* 348 */     int i = Utils.ceil(this.mappingX.srcToDst(paramRectangle1.x - parameterizedFilter1.support) + 1.0E-7D);
/* 349 */     int j = Utils.ceil(this.mappingY.srcToDst(paramRectangle1.y - parameterizedFilter2.support) + 1.0E-7D);
/* 350 */     int k = Utils.floor(this.mappingX.srcToDst((paramRectangle1.x + paramRectangle1.width) + parameterizedFilter1.support) - 1.0E-7D);
/*     */     
/* 352 */     int m = Utils.floor(this.mappingY.srcToDst((paramRectangle1.y + paramRectangle1.height) + parameterizedFilter2.support) - 1.0E-7D);
/*     */     
/* 354 */     rectangle.setFrameFromDiagonal(i, j, k, m);
/*     */     
/* 356 */     if (paramRectangle2.x < rectangle.x || paramRectangle2.getMaxX() > rectangle.getMaxX() || paramRectangle2.y < rectangle.y || paramRectangle2.getMaxY() > rectangle.getMaxY())
/*     */     {
/*     */       
/* 359 */       paramRectangle2 = paramRectangle2.intersection(rectangle);
/*     */     }
/*     */     
/* 362 */     if (paramRectangle1.isEmpty() || paramRectangle2.width <= 0 || paramRectangle2.height <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 367 */     this.mappingX.getClass(); parameterizedFilter1 = simplifyFilter(parameterizedFilter1, this.mappingX.scale, 0.5D);
/* 368 */     this.mappingY.getClass(); parameterizedFilter2 = simplifyFilter(parameterizedFilter2, this.mappingY.scale, 0.5D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 374 */     boolean bool = (this.order != Order.AUTO) ? ((this.order == Order.XY) ? true : false) : ((paramRectangle2.width * (paramRectangle1.height * parameterizedFilter1.width + paramRectangle2.height * parameterizedFilter2.width) < paramRectangle2.height * (paramRectangle2.width * parameterizedFilter1.width + paramRectangle1.width * parameterizedFilter2.width)) ? true : false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 381 */     if (bool) {
/* 382 */       resizeXfirst(paramObject1, paramRectangle1, paramObject2, paramRectangle2, parameterizedFilter1, parameterizedFilter2);
/*     */     } else {
/* 384 */       resizeYfirst(paramObject1, paramRectangle1, paramObject2, paramRectangle2, parameterizedFilter1, parameterizedFilter2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Scanline createScanline(Object paramObject1, Object paramObject2, int paramInt) {
/* 389 */     if (paramObject1 == null) {
/* 390 */       throw new IllegalArgumentException("src must not be null");
/*     */     }
/* 392 */     if (!(paramObject1 instanceof Bitmap)) {
/* 393 */       throw new IllegalArgumentException("src must be from type " + Bitmap.class.getName());
/*     */     }
/* 395 */     if (paramObject2 == null) {
/* 396 */       throw new IllegalArgumentException("dst must not be null");
/*     */     }
/* 398 */     if (!(paramObject2 instanceof WritableRaster)) {
/* 399 */       throw new IllegalArgumentException("dst must be from type " + WritableRaster.class.getName());
/*     */     }
/* 401 */     return new BitmapScanline((Bitmap)paramObject1, (WritableRaster)paramObject2, paramInt);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/Resizer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */