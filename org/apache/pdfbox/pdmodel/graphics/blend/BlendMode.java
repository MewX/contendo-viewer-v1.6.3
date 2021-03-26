/*     */ package org.apache.pdfbox.pdmodel.graphics.blend;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlendMode
/*     */ {
/*  33 */   public static final SeparableBlendMode NORMAL = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  38 */         return srcValue;
/*     */       }
/*     */     };
/*     */   
/*  42 */   public static final SeparableBlendMode COMPATIBLE = NORMAL;
/*     */   
/*  44 */   public static final SeparableBlendMode MULTIPLY = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  49 */         return srcValue * dstValue;
/*     */       }
/*     */     };
/*     */   
/*  53 */   public static final SeparableBlendMode SCREEN = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  58 */         return srcValue + dstValue - srcValue * dstValue;
/*     */       }
/*     */     };
/*     */   
/*  62 */   public static final SeparableBlendMode OVERLAY = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  67 */         return (dstValue <= 0.5D) ? (2.0F * dstValue * srcValue) : (2.0F * (srcValue + dstValue - srcValue * dstValue) - 1.0F);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*  72 */   public static final SeparableBlendMode DARKEN = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  77 */         return Math.min(srcValue, dstValue);
/*     */       }
/*     */     };
/*     */   
/*  81 */   public static final SeparableBlendMode LIGHTEN = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  86 */         return Math.max(srcValue, dstValue);
/*     */       }
/*     */     };
/*     */   
/*  90 */   public static final SeparableBlendMode COLOR_DODGE = new SeparableBlendMode()
/*     */     {
/*     */ 
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/*  96 */         if (dstValue == 0.0F)
/*     */         {
/*  98 */           return 0.0F;
/*     */         }
/* 100 */         if (dstValue >= 1.0F - srcValue)
/*     */         {
/* 102 */           return 1.0F;
/*     */         }
/* 104 */         return dstValue / (1.0F - srcValue);
/*     */       }
/*     */     };
/*     */   
/* 108 */   public static final SeparableBlendMode COLOR_BURN = new SeparableBlendMode()
/*     */     {
/*     */ 
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/* 114 */         if (dstValue == 1.0F)
/*     */         {
/* 116 */           return 1.0F;
/*     */         }
/* 118 */         if (1.0F - dstValue >= srcValue)
/*     */         {
/* 120 */           return 0.0F;
/*     */         }
/* 122 */         return 1.0F - (1.0F - dstValue) / srcValue;
/*     */       }
/*     */     };
/*     */   
/* 126 */   public static final SeparableBlendMode HARD_LIGHT = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/* 131 */         return (srcValue <= 0.5D) ? (2.0F * dstValue * srcValue) : (2.0F * (srcValue + dstValue - srcValue * dstValue) - 1.0F);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 136 */   public static final SeparableBlendMode SOFT_LIGHT = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/* 141 */         if (srcValue <= 0.5D)
/*     */         {
/* 143 */           return dstValue - (1.0F - 2.0F * srcValue) * dstValue * (1.0F - dstValue);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 148 */         float d = (dstValue <= 0.25D) ? (((16.0F * dstValue - 12.0F) * dstValue + 4.0F) * dstValue) : (float)Math.sqrt(dstValue);
/* 149 */         return dstValue + (2.0F * srcValue - 1.0F) * (d - dstValue);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 154 */   public static final SeparableBlendMode DIFFERENCE = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/* 159 */         return Math.abs(dstValue - srcValue);
/*     */       }
/*     */     };
/*     */   
/* 163 */   public static final SeparableBlendMode EXCLUSION = new SeparableBlendMode()
/*     */     {
/*     */       
/*     */       public float blendChannel(float srcValue, float dstValue)
/*     */       {
/* 168 */         return dstValue + srcValue - 2.0F * dstValue * srcValue;
/*     */       }
/*     */     };
/*     */   
/* 172 */   public static final NonSeparableBlendMode HUE = new NonSeparableBlendMode()
/*     */     {
/*     */       
/*     */       public void blend(float[] srcValues, float[] dstValues, float[] result)
/*     */       {
/* 177 */         float[] temp = new float[3];
/* 178 */         BlendMode.getSaturationRGB(dstValues, srcValues, temp);
/* 179 */         BlendMode.getLuminosityRGB(dstValues, temp, result);
/*     */       }
/*     */     };
/*     */   
/* 183 */   public static final NonSeparableBlendMode SATURATION = new NonSeparableBlendMode()
/*     */     {
/*     */       
/*     */       public void blend(float[] srcValues, float[] dstValues, float[] result)
/*     */       {
/* 188 */         BlendMode.getSaturationRGB(srcValues, dstValues, result);
/*     */       }
/*     */     };
/*     */   
/* 192 */   public static final NonSeparableBlendMode COLOR = new NonSeparableBlendMode()
/*     */     {
/*     */       
/*     */       public void blend(float[] srcValues, float[] dstValues, float[] result)
/*     */       {
/* 197 */         BlendMode.getLuminosityRGB(dstValues, srcValues, result);
/*     */       }
/*     */     };
/*     */   
/* 201 */   public static final NonSeparableBlendMode LUMINOSITY = new NonSeparableBlendMode()
/*     */     {
/*     */       
/*     */       public void blend(float[] srcValues, float[] dstValues, float[] result)
/*     */       {
/* 206 */         BlendMode.getLuminosityRGB(srcValues, dstValues, result);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 211 */   private static final Map<COSName, BlendMode> BLEND_MODES = createBlendModeMap();
/* 212 */   private static final Map<BlendMode, COSName> BLEND_MODE_NAMES = createBlendModeNamesMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlendMode getInstance(COSBase cosBlendMode) {
/* 226 */     BlendMode result = null;
/* 227 */     if (cosBlendMode instanceof COSName) {
/*     */       
/* 229 */       result = BLEND_MODES.get(cosBlendMode);
/*     */     }
/* 231 */     else if (cosBlendMode instanceof COSArray) {
/*     */       
/* 233 */       COSArray cosBlendModeArray = (COSArray)cosBlendMode;
/* 234 */       for (int i = 0; i < cosBlendModeArray.size(); i++) {
/*     */         
/* 236 */         result = BLEND_MODES.get(cosBlendModeArray.getObject(i));
/* 237 */         if (result != null) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 244 */     if (result != null)
/*     */     {
/* 246 */       return result;
/*     */     }
/* 248 */     return NORMAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static COSName getCOSName(BlendMode bm) {
/* 259 */     return BLEND_MODE_NAMES.get(bm);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int get255Value(float val) {
/* 264 */     return (int)Math.floor((val >= 1.0D) ? 255.0D : (val * 255.0D));
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
/*     */   private static void getSaturationRGB(float[] srcValues, float[] dstValues, float[] result) {
/* 279 */     int rd = get255Value(dstValues[0]);
/* 280 */     int gd = get255Value(dstValues[1]);
/* 281 */     int bd = get255Value(dstValues[2]);
/* 282 */     int rs = get255Value(srcValues[0]);
/* 283 */     int gs = get255Value(srcValues[1]);
/* 284 */     int bs = get255Value(srcValues[2]);
/*     */     
/* 286 */     int minb = Math.min(rd, Math.min(gd, bd));
/* 287 */     int maxb = Math.max(rd, Math.max(gd, bd));
/* 288 */     if (minb == maxb) {
/*     */ 
/*     */       
/* 291 */       result[0] = gd / 255.0F;
/* 292 */       result[1] = gd / 255.0F;
/* 293 */       result[2] = gd / 255.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 297 */     int mins = Math.min(rs, Math.min(gs, bs));
/* 298 */     int maxs = Math.max(rs, Math.max(gs, bs));
/*     */     
/* 300 */     int scale = (maxs - mins << 16) / (maxb - minb);
/* 301 */     int y = rd * 77 + gd * 151 + bd * 28 + 128 >> 8;
/* 302 */     int r = y + ((rd - y) * scale + 32768 >> 16);
/* 303 */     int g = y + ((gd - y) * scale + 32768 >> 16);
/* 304 */     int b = y + ((bd - y) * scale + 32768 >> 16);
/*     */     
/* 306 */     if (((r | g | b) & 0x100) == 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 313 */       int scalemin, scalemax, min = Math.min(r, Math.min(g, b));
/* 314 */       int max = Math.max(r, Math.max(g, b));
/*     */       
/* 316 */       if (min < 0) {
/*     */         
/* 318 */         scalemin = (y << 16) / (y - min);
/*     */       }
/*     */       else {
/*     */         
/* 322 */         scalemin = 65536;
/*     */       } 
/*     */       
/* 325 */       if (max > 255) {
/*     */         
/* 327 */         scalemax = (255 - y << 16) / (max - y);
/*     */       }
/*     */       else {
/*     */         
/* 331 */         scalemax = 65536;
/*     */       } 
/*     */       
/* 334 */       scale = Math.min(scalemin, scalemax);
/* 335 */       r = y + ((r - y) * scale + 32768 >> 16);
/* 336 */       g = y + ((g - y) * scale + 32768 >> 16);
/* 337 */       b = y + ((b - y) * scale + 32768 >> 16);
/*     */     } 
/* 339 */     result[0] = r / 255.0F;
/* 340 */     result[1] = g / 255.0F;
/* 341 */     result[2] = b / 255.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void getLuminosityRGB(float[] srcValues, float[] dstValues, float[] result) {
/* 352 */     int rd = get255Value(dstValues[0]);
/* 353 */     int gd = get255Value(dstValues[1]);
/* 354 */     int bd = get255Value(dstValues[2]);
/* 355 */     int rs = get255Value(srcValues[0]);
/* 356 */     int gs = get255Value(srcValues[1]);
/* 357 */     int bs = get255Value(srcValues[2]);
/* 358 */     int delta = (rs - rd) * 77 + (gs - gd) * 151 + (bs - bd) * 28 + 128 >> 8;
/* 359 */     int r = rd + delta;
/* 360 */     int g = gd + delta;
/* 361 */     int b = bd + delta;
/*     */     
/* 363 */     if (((r | g | b) & 0x100) == 256) {
/*     */       
/* 365 */       int scale, y = rs * 77 + gs * 151 + bs * 28 + 128 >> 8;
/* 366 */       if (delta > 0) {
/*     */ 
/*     */         
/* 369 */         int max = Math.max(r, Math.max(g, b));
/* 370 */         scale = (max == y) ? 0 : ((255 - y << 16) / (max - y));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 375 */         int min = Math.min(r, Math.min(g, b));
/* 376 */         scale = (y == min) ? 0 : ((y << 16) / (y - min));
/*     */       } 
/* 378 */       r = y + ((r - y) * scale + 32768 >> 16);
/* 379 */       g = y + ((g - y) * scale + 32768 >> 16);
/* 380 */       b = y + ((b - y) * scale + 32768 >> 16);
/*     */     } 
/* 382 */     result[0] = r / 255.0F;
/* 383 */     result[1] = g / 255.0F;
/* 384 */     result[2] = b / 255.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<COSName, BlendMode> createBlendModeMap() {
/* 389 */     Map<COSName, BlendMode> map = new HashMap<COSName, BlendMode>(13);
/* 390 */     map.put(COSName.NORMAL, NORMAL);
/*     */     
/* 392 */     map.put(COSName.COMPATIBLE, NORMAL);
/* 393 */     map.put(COSName.MULTIPLY, MULTIPLY);
/* 394 */     map.put(COSName.SCREEN, SCREEN);
/* 395 */     map.put(COSName.OVERLAY, OVERLAY);
/* 396 */     map.put(COSName.DARKEN, DARKEN);
/* 397 */     map.put(COSName.LIGHTEN, LIGHTEN);
/* 398 */     map.put(COSName.COLOR_DODGE, COLOR_DODGE);
/* 399 */     map.put(COSName.COLOR_BURN, COLOR_BURN);
/* 400 */     map.put(COSName.HARD_LIGHT, HARD_LIGHT);
/* 401 */     map.put(COSName.SOFT_LIGHT, SOFT_LIGHT);
/* 402 */     map.put(COSName.DIFFERENCE, DIFFERENCE);
/* 403 */     map.put(COSName.EXCLUSION, EXCLUSION);
/* 404 */     map.put(COSName.HUE, HUE);
/* 405 */     map.put(COSName.SATURATION, SATURATION);
/* 406 */     map.put(COSName.LUMINOSITY, LUMINOSITY);
/* 407 */     map.put(COSName.COLOR, COLOR);
/* 408 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<BlendMode, COSName> createBlendModeNamesMap() {
/* 413 */     Map<BlendMode, COSName> map = new HashMap<BlendMode, COSName>(13);
/* 414 */     map.put(NORMAL, COSName.NORMAL);
/*     */     
/* 416 */     map.put(COMPATIBLE, COSName.NORMAL);
/* 417 */     map.put(MULTIPLY, COSName.MULTIPLY);
/* 418 */     map.put(SCREEN, COSName.SCREEN);
/* 419 */     map.put(OVERLAY, COSName.OVERLAY);
/* 420 */     map.put(DARKEN, COSName.DARKEN);
/* 421 */     map.put(LIGHTEN, COSName.LIGHTEN);
/* 422 */     map.put(COLOR_DODGE, COSName.COLOR_DODGE);
/* 423 */     map.put(COLOR_BURN, COSName.COLOR_BURN);
/* 424 */     map.put(HARD_LIGHT, COSName.HARD_LIGHT);
/* 425 */     map.put(SOFT_LIGHT, COSName.SOFT_LIGHT);
/* 426 */     map.put(DIFFERENCE, COSName.DIFFERENCE);
/* 427 */     map.put(EXCLUSION, COSName.EXCLUSION);
/* 428 */     map.put(HUE, COSName.HUE);
/* 429 */     map.put(SATURATION, COSName.SATURATION);
/* 430 */     map.put(LUMINOSITY, COSName.LUMINOSITY);
/* 431 */     map.put(COLOR, COSName.COLOR);
/* 432 */     return map;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/blend/BlendMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */