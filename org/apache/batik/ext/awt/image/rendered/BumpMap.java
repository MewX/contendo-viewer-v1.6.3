/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BumpMap
/*     */ {
/*     */   private RenderedImage texture;
/*     */   private double surfaceScale;
/*     */   private double surfaceScaleX;
/*     */   private double surfaceScaleY;
/*     */   private double scaleX;
/*     */   private double scaleY;
/*     */   
/*     */   public BumpMap(RenderedImage texture, double surfaceScale, double scaleX, double scaleY) {
/*  58 */     this.texture = texture;
/*  59 */     this.surfaceScaleX = surfaceScale * scaleX;
/*  60 */     this.surfaceScaleY = surfaceScale * scaleY;
/*  61 */     this.surfaceScale = surfaceScale;
/*  62 */     this.scaleX = scaleX;
/*  63 */     this.scaleY = scaleY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSurfaceScale() {
/*  70 */     return this.surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][][] getNormalArray(int x, int y, int w, int h) {
/*  81 */     double[][][] N = new double[h][w][4];
/*     */     
/*  83 */     Rectangle srcRect = new Rectangle(x - 1, y - 1, w + 2, h + 2);
/*  84 */     Rectangle srcBound = new Rectangle(this.texture.getMinX(), this.texture.getMinY(), this.texture.getWidth(), this.texture.getHeight());
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (!srcRect.intersects(srcBound)) {
/*  89 */       return N;
/*     */     }
/*  91 */     srcRect = srcRect.intersection(srcBound);
/*  92 */     Raster r = this.texture.getData(srcRect);
/*     */     
/*  94 */     srcRect = r.getBounds();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     DataBufferInt db = (DataBufferInt)r.getDataBuffer();
/*     */ 
/*     */     
/* 104 */     int[] pixels = db.getBankData()[0];
/*     */ 
/*     */     
/* 107 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)r.getSampleModel();
/*     */ 
/*     */     
/* 110 */     int scanStride = sppsm.getScanlineStride();
/* 111 */     int scanStridePP = scanStride + 1;
/* 112 */     int scanStrideMM = scanStride - 1;
/* 113 */     double prpc = 0.0D, prcc = 0.0D, prnc = 0.0D;
/* 114 */     double crpc = 0.0D, crcc = 0.0D, crnc = 0.0D;
/* 115 */     double nrpc = 0.0D, nrcc = 0.0D, nrnc = 0.0D;
/*     */ 
/*     */     
/* 118 */     double quarterSurfaceScaleX = this.surfaceScaleX / 4.0D;
/* 119 */     double quarterSurfaceScaleY = this.surfaceScaleY / 4.0D;
/* 120 */     double halfSurfaceScaleX = this.surfaceScaleX / 2.0D;
/* 121 */     double halfSurfaceScaleY = this.surfaceScaleY / 2.0D;
/* 122 */     double thirdSurfaceScaleX = this.surfaceScaleX / 3.0D;
/* 123 */     double thirdSurfaceScaleY = this.surfaceScaleY / 3.0D;
/* 124 */     double twoThirdSurfaceScaleX = this.surfaceScaleX * 2.0D / 3.0D;
/* 125 */     double twoThirdSurfaceScaleY = this.surfaceScaleY * 2.0D / 3.0D;
/*     */     
/* 127 */     double pixelScale = 0.00392156862745098D;
/*     */     
/* 129 */     if (w <= 0) {
/* 130 */       return N;
/*     */     }
/* 132 */     if (h <= 0) {
/* 133 */       return N;
/*     */     }
/* 135 */     int xEnd = Math.min(srcRect.x + srcRect.width - 1, x + w);
/* 136 */     int yEnd = Math.min(srcRect.y + srcRect.height - 1, y + h);
/* 137 */     int offset = db.getOffset() + sppsm.getOffset(srcRect.x - r.getSampleModelTranslateX(), srcRect.y - r.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     int yloc = y;
/* 143 */     if (yloc < srcRect.y) {
/* 144 */       yloc = srcRect.y;
/*     */     }
/*     */ 
/*     */     
/* 148 */     if (yloc == srcRect.y) {
/* 149 */       if (yloc == yEnd) {
/*     */         
/* 151 */         double[][] arrayOfDouble = N[yloc - y];
/* 152 */         int i = x;
/* 153 */         if (i < srcRect.x)
/* 154 */           i = srcRect.x; 
/* 155 */         int j = offset + i - srcRect.x + scanStride * (yloc - srcRect.y);
/*     */ 
/*     */         
/* 158 */         crcc = (pixels[j] >>> 24) * 0.00392156862745098D;
/*     */         
/* 160 */         if (i != srcRect.x) {
/* 161 */           crpc = (pixels[j - 1] >>> 24) * 0.00392156862745098D;
/*     */         }
/* 163 */         else if (i < xEnd) {
/*     */           
/* 165 */           crnc = (pixels[j + 1] >>> 24) * 0.00392156862745098D;
/*     */           
/* 167 */           double[] n = arrayOfDouble[i - x];
/*     */           
/* 169 */           n[0] = 2.0D * this.surfaceScaleX * (crcc - crnc);
/* 170 */           double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + 1.0D);
/* 171 */           n[0] = n[0] * invNorm;
/* 172 */           n[1] = 0.0D;
/* 173 */           n[2] = invNorm;
/* 174 */           n[3] = crcc * this.surfaceScale;
/* 175 */           j++;
/* 176 */           i++;
/* 177 */           crpc = crcc;
/* 178 */           crcc = crnc;
/*     */         } else {
/*     */           
/* 181 */           crpc = crcc;
/*     */         } 
/*     */         
/* 184 */         for (; i < xEnd; i++) {
/*     */           
/* 186 */           crnc = (pixels[j + 1] >>> 24) * 0.00392156862745098D;
/* 187 */           double[] n = arrayOfDouble[i - x];
/*     */           
/* 189 */           n[0] = this.surfaceScaleX * (crpc - crnc);
/* 190 */           double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + 1.0D);
/* 191 */           n[0] = n[0] * invNorm;
/* 192 */           n[1] = 0.0D;
/* 193 */           n[2] = invNorm;
/* 194 */           n[3] = crcc * this.surfaceScale;
/* 195 */           j++;
/* 196 */           crpc = crcc;
/* 197 */           crcc = crnc;
/*     */         } 
/*     */         
/* 200 */         if (i < x + w && i == srcRect.x + srcRect.width - 1) {
/*     */ 
/*     */           
/* 203 */           double[] n = arrayOfDouble[i - x];
/*     */           
/* 205 */           n[0] = 2.0D * this.surfaceScaleX * (crpc - crcc);
/* 206 */           double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 207 */           n[0] = n[0] * invNorm;
/* 208 */           n[1] = n[1] * invNorm;
/* 209 */           n[2] = invNorm;
/* 210 */           n[3] = crcc * this.surfaceScale;
/*     */         } 
/* 212 */         return N;
/*     */       } 
/*     */       
/* 215 */       double[][] NRow = N[yloc - y];
/* 216 */       int p = offset + scanStride * (yloc - srcRect.y);
/* 217 */       int xloc = x;
/* 218 */       if (xloc < srcRect.x)
/* 219 */         xloc = srcRect.x; 
/* 220 */       p += xloc - srcRect.x;
/*     */       
/* 222 */       crcc = (pixels[p] >>> 24) * 0.00392156862745098D;
/* 223 */       nrcc = (pixels[p + scanStride] >>> 24) * 0.00392156862745098D;
/*     */       
/* 225 */       if (xloc != srcRect.x) {
/* 226 */         crpc = (pixels[p - 1] >>> 24) * 0.00392156862745098D;
/* 227 */         nrpc = (pixels[p + scanStrideMM] >>> 24) * 0.00392156862745098D;
/*     */       }
/* 229 */       else if (xloc < xEnd) {
/*     */         
/* 231 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 232 */         nrnc = (pixels[p + scanStridePP] >>> 24) * 0.00392156862745098D;
/*     */         
/* 234 */         double[] n = NRow[xloc - x];
/*     */         
/* 236 */         n[0] = -twoThirdSurfaceScaleX * (2.0D * crnc + nrnc - 2.0D * crcc - nrcc);
/*     */         
/* 238 */         n[1] = -twoThirdSurfaceScaleY * (2.0D * nrcc + nrnc - 2.0D * crcc - crnc);
/*     */         
/* 240 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 241 */         n[0] = n[0] * invNorm;
/* 242 */         n[1] = n[1] * invNorm;
/* 243 */         n[2] = invNorm;
/* 244 */         n[3] = crcc * this.surfaceScale;
/* 245 */         p++;
/* 246 */         xloc++;
/* 247 */         crpc = crcc;
/* 248 */         nrpc = nrcc;
/* 249 */         crcc = crnc;
/* 250 */         nrcc = nrnc;
/*     */       } else {
/*     */         
/* 253 */         crpc = crcc;
/* 254 */         nrpc = nrcc;
/*     */       } 
/*     */       
/* 257 */       for (; xloc < xEnd; xloc++) {
/*     */         
/* 259 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 260 */         nrnc = (pixels[p + scanStridePP] >>> 24) * 0.00392156862745098D;
/*     */         
/* 262 */         double[] n = NRow[xloc - x];
/*     */         
/* 264 */         n[0] = -thirdSurfaceScaleX * (2.0D * crnc + nrnc - 2.0D * crpc + nrpc);
/*     */         
/* 266 */         n[1] = -halfSurfaceScaleY * (nrpc + 2.0D * nrcc + nrnc - crpc + 2.0D * crcc + crnc);
/*     */ 
/*     */         
/* 269 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 270 */         n[0] = n[0] * invNorm;
/* 271 */         n[1] = n[1] * invNorm;
/* 272 */         n[2] = invNorm;
/* 273 */         n[3] = crcc * this.surfaceScale;
/* 274 */         p++;
/* 275 */         crpc = crcc;
/* 276 */         nrpc = nrcc;
/* 277 */         crcc = crnc;
/* 278 */         nrcc = nrnc;
/*     */       } 
/*     */       
/* 281 */       if (xloc < x + w && xloc == srcRect.x + srcRect.width - 1) {
/*     */ 
/*     */         
/* 284 */         double[] n = NRow[xloc - x];
/*     */         
/* 286 */         n[0] = -twoThirdSurfaceScaleX * (2.0D * crcc + nrcc - 2.0D * crpc + nrpc);
/*     */         
/* 288 */         n[1] = -twoThirdSurfaceScaleY * (2.0D * nrcc + nrpc - 2.0D * crcc + crpc);
/*     */ 
/*     */         
/* 291 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 292 */         n[0] = n[0] * invNorm;
/* 293 */         n[1] = n[1] * invNorm;
/* 294 */         n[2] = invNorm;
/* 295 */         n[3] = crcc * this.surfaceScale;
/*     */       } 
/* 297 */       yloc++;
/*     */     } 
/*     */     
/* 300 */     for (; yloc < yEnd; yloc++) {
/* 301 */       double[][] NRow = N[yloc - y];
/* 302 */       int p = offset + scanStride * (yloc - srcRect.y);
/*     */       
/* 304 */       int xloc = x;
/* 305 */       if (xloc < srcRect.x) {
/* 306 */         xloc = srcRect.x;
/*     */       }
/* 308 */       p += xloc - srcRect.x;
/*     */       
/* 310 */       prcc = (pixels[p - scanStride] >>> 24) * 0.00392156862745098D;
/* 311 */       crcc = (pixels[p] >>> 24) * 0.00392156862745098D;
/* 312 */       nrcc = (pixels[p + scanStride] >>> 24) * 0.00392156862745098D;
/*     */       
/* 314 */       if (xloc != srcRect.x) {
/* 315 */         prpc = (pixels[p - scanStridePP] >>> 24) * 0.00392156862745098D;
/* 316 */         crpc = (pixels[p - 1] >>> 24) * 0.00392156862745098D;
/* 317 */         nrpc = (pixels[p + scanStrideMM] >>> 24) * 0.00392156862745098D;
/*     */       }
/* 319 */       else if (xloc < xEnd) {
/*     */         
/* 321 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 322 */         prnc = (pixels[p - scanStrideMM] >>> 24) * 0.00392156862745098D;
/* 323 */         nrnc = (pixels[p + scanStridePP] >>> 24) * 0.00392156862745098D;
/*     */         
/* 325 */         double[] n = NRow[xloc - x];
/*     */         
/* 327 */         n[0] = -halfSurfaceScaleX * (prnc + 2.0D * crnc + nrnc - prcc + 2.0D * crcc + nrcc);
/*     */         
/* 329 */         n[1] = -thirdSurfaceScaleY * (2.0D * prcc + prnc - 2.0D * crcc + crnc);
/*     */ 
/*     */         
/* 332 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 333 */         n[0] = n[0] * invNorm;
/* 334 */         n[1] = n[1] * invNorm;
/* 335 */         n[2] = invNorm;
/* 336 */         n[3] = crcc * this.surfaceScale;
/*     */         
/* 338 */         p++;
/* 339 */         xloc++;
/*     */         
/* 341 */         prpc = prcc;
/* 342 */         crpc = crcc;
/* 343 */         nrpc = nrcc;
/* 344 */         prcc = prnc;
/* 345 */         crcc = crnc;
/* 346 */         nrcc = nrnc;
/*     */       } else {
/*     */         
/* 349 */         prpc = prcc;
/* 350 */         crpc = crcc;
/* 351 */         nrpc = nrcc;
/*     */       } 
/*     */       
/* 354 */       for (; xloc < xEnd; xloc++) {
/*     */         
/* 356 */         prnc = (pixels[p - scanStrideMM] >>> 24) * 0.00392156862745098D;
/* 357 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 358 */         nrnc = (pixels[p + scanStridePP] >>> 24) * 0.00392156862745098D;
/*     */         
/* 360 */         double[] n = NRow[xloc - x];
/*     */         
/* 362 */         n[0] = -quarterSurfaceScaleX * (prnc + 2.0D * crnc + nrnc - prpc + 2.0D * crpc + nrpc);
/*     */         
/* 364 */         n[1] = -quarterSurfaceScaleY * (nrpc + 2.0D * nrcc + nrnc - prpc + 2.0D * prcc + prnc);
/*     */ 
/*     */         
/* 367 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 368 */         n[0] = n[0] * invNorm;
/* 369 */         n[1] = n[1] * invNorm;
/* 370 */         n[2] = invNorm;
/* 371 */         n[3] = crcc * this.surfaceScale;
/*     */         
/* 373 */         p++;
/* 374 */         prpc = prcc;
/* 375 */         crpc = crcc;
/* 376 */         nrpc = nrcc;
/* 377 */         prcc = prnc;
/* 378 */         crcc = crnc;
/* 379 */         nrcc = nrnc;
/*     */       } 
/*     */       
/* 382 */       if (xloc < x + w && xloc == srcRect.x + srcRect.width - 1) {
/*     */ 
/*     */         
/* 385 */         double[] n = NRow[xloc - x];
/*     */         
/* 387 */         n[0] = -halfSurfaceScaleX * (prcc + 2.0D * crcc + nrcc - prpc + 2.0D * crpc + nrpc);
/*     */         
/* 389 */         n[1] = -thirdSurfaceScaleY * (nrpc + 2.0D * nrcc - prpc + 2.0D * prcc);
/*     */ 
/*     */         
/* 392 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 393 */         n[0] = n[0] * invNorm;
/* 394 */         n[1] = n[1] * invNorm;
/* 395 */         n[2] = invNorm;
/* 396 */         n[3] = crcc * this.surfaceScale;
/*     */       } 
/*     */     } 
/*     */     
/* 400 */     if (yloc < y + h && yloc == srcRect.y + srcRect.height - 1) {
/*     */       
/* 402 */       double[][] NRow = N[yloc - y];
/* 403 */       int p = offset + scanStride * (yloc - srcRect.y);
/* 404 */       int xloc = x;
/* 405 */       if (xloc < srcRect.x) {
/* 406 */         xloc = srcRect.x;
/*     */       }
/* 408 */       p += xloc - srcRect.x;
/*     */       
/* 410 */       crcc = (pixels[p] >>> 24) * 0.00392156862745098D;
/* 411 */       prcc = (pixels[p - scanStride] >>> 24) * 0.00392156862745098D;
/*     */       
/* 413 */       if (xloc != srcRect.x) {
/* 414 */         prpc = (pixels[p - scanStridePP] >>> 24) * 0.00392156862745098D;
/* 415 */         crpc = (pixels[p - 1] >>> 24) * 0.00392156862745098D;
/*     */       }
/* 417 */       else if (xloc < xEnd) {
/*     */         
/* 419 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 420 */         prnc = (pixels[p - scanStrideMM] >>> 24) * 0.00392156862745098D;
/*     */         
/* 422 */         double[] n = NRow[xloc - x];
/*     */         
/* 424 */         n[0] = -twoThirdSurfaceScaleX * (2.0D * crnc + prnc - 2.0D * crcc - prcc);
/* 425 */         n[1] = -twoThirdSurfaceScaleY * (2.0D * crcc + crnc - 2.0D * prcc - prnc);
/* 426 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 427 */         n[0] = n[0] * invNorm;
/* 428 */         n[1] = n[1] * invNorm;
/* 429 */         n[2] = invNorm;
/* 430 */         n[3] = crcc * this.surfaceScale;
/*     */         
/* 432 */         p++;
/* 433 */         xloc++;
/* 434 */         crpc = crcc;
/* 435 */         prpc = prcc;
/* 436 */         crcc = crnc;
/* 437 */         prcc = prnc;
/*     */       } else {
/*     */         
/* 440 */         crpc = crcc;
/* 441 */         prpc = prcc;
/*     */       } 
/*     */       
/* 444 */       for (; xloc < xEnd; xloc++) {
/*     */         
/* 446 */         crnc = (pixels[p + 1] >>> 24) * 0.00392156862745098D;
/* 447 */         prnc = (pixels[p - scanStrideMM] >>> 24) * 0.00392156862745098D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 453 */         double[] n = NRow[xloc - x];
/*     */         
/* 455 */         n[0] = -thirdSurfaceScaleX * (2.0D * crnc + prnc - 2.0D * crpc + prpc);
/*     */         
/* 457 */         n[1] = -halfSurfaceScaleY * (crpc + 2.0D * crcc + crnc - prpc + 2.0D * prcc + prnc);
/*     */ 
/*     */         
/* 460 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 461 */         n[0] = n[0] * invNorm;
/* 462 */         n[1] = n[1] * invNorm;
/* 463 */         n[2] = invNorm;
/* 464 */         n[3] = crcc * this.surfaceScale;
/*     */         
/* 466 */         p++;
/* 467 */         crpc = crcc;
/* 468 */         prpc = prcc;
/* 469 */         crcc = crnc;
/* 470 */         prcc = prnc;
/*     */       } 
/*     */       
/* 473 */       if (xloc < x + w && xloc == srcRect.x + srcRect.width - 1) {
/*     */ 
/*     */         
/* 476 */         double[] n = NRow[xloc - x];
/*     */         
/* 478 */         n[0] = -twoThirdSurfaceScaleX * (2.0D * crcc + prcc - 2.0D * crpc + prpc);
/*     */         
/* 480 */         n[1] = -twoThirdSurfaceScaleY * (2.0D * crcc + crpc - 2.0D * prcc + prpc);
/*     */ 
/*     */         
/* 483 */         double invNorm = 1.0D / Math.sqrt(n[0] * n[0] + n[1] * n[1] + 1.0D);
/* 484 */         n[0] = n[0] * invNorm;
/* 485 */         n[1] = n[1] * invNorm;
/* 486 */         n[2] = invNorm;
/* 487 */         n[3] = crcc * this.surfaceScale;
/*     */       } 
/*     */     } 
/* 490 */     return N;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/BumpMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */