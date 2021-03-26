/*     */ package net.zamasoft.a.a;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import net.zamasoft.a.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */ {
/*     */   public static final int a = 1;
/*     */   public static final int b = 3;
/*     */   public static final int c = 4;
/*     */   public static final int d = 5;
/*     */   public static final int e = 6;
/*     */   public static final int f = 7;
/*     */   public static final int g = 8;
/*     */   public static final int h = 10;
/*     */   public static final int i = 11;
/*     */   public static final int j = 12;
/*     */   public static final int k = 14;
/*     */   public static final int l = 16;
/*     */   public static final int m = 18;
/*     */   public static final int n = 19;
/*     */   public static final int o = 20;
/*     */   public static final int p = 21;
/*     */   public static final int q = 22;
/*     */   public static final int r = 23;
/*     */   public static final int s = 24;
/*     */   public static final int t = 25;
/*     */   public static final int u = 26;
/*     */   public static final int v = 27;
/*     */   public static final int w = 28;
/*     */   public static final int x = 29;
/*     */   public static final int y = 30;
/*     */   public static final int z = 31;
/*     */   public static final int A = 3075;
/*     */   public static final int B = 3076;
/*     */   public static final int C = 3077;
/*     */   public static final int D = 3080;
/*     */   public static final int E = 3081;
/*     */   public static final int F = 3082;
/*     */   public static final int G = 3083;
/*     */   public static final int H = 3084;
/*     */   public static final int I = 3085;
/*     */   public static final int J = 3086;
/*     */   public static final int K = 3087;
/*     */   public static final int L = 3090;
/*     */   public static final int M = 3092;
/*     */   public static final int N = 3093;
/*     */   public static final int O = 3094;
/*     */   public static final int P = 3095;
/*     */   public static final int Q = 3096;
/*     */   public static final int R = 3098;
/*     */   public static final int S = 3099;
/*     */   public static final int T = 3100;
/*     */   public static final int U = 3101;
/*     */   public static final int V = 3102;
/*     */   public static final int W = 3106;
/*     */   public static final int X = 3107;
/*     */   public static final int Y = 3108;
/*     */   public static final int Z = 3109;
/*     */   public static final byte aa = 1;
/*     */   public static final byte ab = 2;
/*     */   private static final boolean ac = false;
/*     */   private final RandomAccessFile ad;
/* 123 */   private final e ae = new e();
/*     */   
/* 125 */   private final int[] af = new int[10];
/*     */   
/* 127 */   private int ag = 0;
/*     */   
/* 129 */   private int ah = -1;
/*     */   
/*     */   public d(RandomAccessFile raf) {
/* 132 */     this.ad = raf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int ix, int offset, short upm, int[] globalSubrOffsets, int[] localSubrOffsets) {
/* 140 */     ByteArrayOutputStream buff = new ByteArrayOutputStream();
/* 141 */     GeneralPath path = new GeneralPath();
/* 142 */     path.moveTo(0.0F, 0.0F);
/* 143 */     synchronized (this.ad) {
/*     */       try {
/* 145 */         this.ad.seek(offset);
/* 146 */         int cx = 0, cy = 0;
/*     */         
/* 148 */         int hintCount = 0;
/* 149 */         boolean closed = true;
/* 150 */         this.ae.b();
/* 151 */         this.ag = 0; int op;
/* 152 */         while ((op = a()) != 14) {
/* 153 */           int i; float x1; int maskBytes; int six; float f1; float y1; int j; float f2; float x2; float f3; float y2; float f4; float x3; float f5; float y3; float f6; float x4; float y4; float x5; float y5; float x6; float y6; switch (op) {
/*     */             case 21:
/* 155 */               if (!closed) {
/* 156 */                 path.closePath();
/*     */ 
/*     */ 
/*     */                 
/* 160 */                 closed = true;
/*     */               } 
/* 162 */               cx += this.ae.b(this.ae.c() - 2);
/* 163 */               cy += -this.ae.b(this.ae.c() - 1);
/* 164 */               path.moveTo(cx, cy);
/*     */ 
/*     */ 
/*     */               
/* 168 */               this.ae.a(buff, op, 2);
/* 169 */               this.ae.b();
/*     */               continue;
/*     */             
/*     */             case 22:
/* 173 */               if (!closed) {
/* 174 */                 path.closePath();
/*     */ 
/*     */ 
/*     */                 
/* 178 */                 closed = true;
/*     */               } 
/* 180 */               cx += this.ae.b(this.ae.c() - 1);
/* 181 */               path.moveTo(cx, cy);
/*     */ 
/*     */ 
/*     */               
/* 185 */               this.ae.a(buff, op, 1);
/* 186 */               this.ae.b();
/*     */               continue;
/*     */             
/*     */             case 4:
/* 190 */               if (!closed) {
/* 191 */                 path.closePath();
/*     */ 
/*     */ 
/*     */                 
/* 195 */                 closed = true;
/*     */               } 
/* 197 */               cy += -this.ae.b(this.ae.c() - 1);
/* 198 */               path.moveTo(cx, cy);
/*     */ 
/*     */ 
/*     */               
/* 202 */               this.ae.a(buff, op, 1);
/* 203 */               this.ae.b();
/*     */               continue;
/*     */             
/*     */             case 5:
/* 207 */               if (this.ae.c() < 2 || this.ae.c() % 2 == 1) {
/* 208 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 210 */               for (i = 0; i < this.ae.c() - 1; i += 2) {
/* 211 */                 cx += this.ae.b(i);
/* 212 */                 cy += -this.ae.b(i + 1);
/* 213 */                 path.lineTo(cx, cy);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 218 */               this.ae.a(buff, op, this.ae.c());
/* 219 */               this.ae.b();
/* 220 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 6:
/* 224 */               if (this.ae.c() < 1) {
/* 225 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 227 */               for (i = 0; i < this.ae.c(); i++) {
/* 228 */                 if (i % 2 == 0) {
/* 229 */                   cx += this.ae.b(i);
/*     */                 } else {
/* 231 */                   cy += -this.ae.b(i);
/*     */                 } 
/* 233 */                 path.lineTo(cx, cy);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 238 */               this.ae.a(buff, op, this.ae.c());
/* 239 */               this.ae.b();
/* 240 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 7:
/* 244 */               if (this.ae.c() < 1) {
/* 245 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 247 */               for (i = 0; i < this.ae.c(); i++) {
/* 248 */                 if (i % 2 == 0) {
/* 249 */                   cy += -this.ae.b(i);
/*     */                 } else {
/* 251 */                   cx += this.ae.b(i);
/*     */                 } 
/* 253 */                 path.lineTo(cx, cy);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 258 */               this.ae.a(buff, op, this.ae.c());
/* 259 */               this.ae.b();
/* 260 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 8:
/* 264 */               if (this.ae.c() < 1 || this.ae.c() % 6 != 0) {
/* 265 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 267 */               for (i = 0; i < this.ae.c() - 5; i += 6) {
/* 268 */                 float f7 = (cx += this.ae.b(i));
/* 269 */                 float f8 = (cy += -this.ae.b(i + 1));
/* 270 */                 float f9 = (cx += this.ae.b(i + 2));
/* 271 */                 float f10 = (cy += -this.ae.b(i + 3));
/* 272 */                 float f11 = (cx += this.ae.b(i + 4));
/* 273 */                 float f12 = (cy += -this.ae.b(i + 5));
/* 274 */                 path.curveTo(f7, f8, f9, f10, f11, f12);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 280 */               this.ae.a(buff, op, this.ae.c());
/* 281 */               this.ae.b();
/* 282 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 27:
/* 286 */               if (this.ae.c() < 2 || (this.ae
/* 287 */                 .c() % 4 != 0 && (this.ae.c() - 1) % 4 != 0)) {
/* 288 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 290 */               i = 0;
/* 291 */               if (this.ae.c() % 2 == 1) {
/* 292 */                 cy += -this.ae.b(i);
/* 293 */                 i++;
/*     */               } 
/* 295 */               for (; i < this.ae.c() - 3; i += 4) {
/* 296 */                 float f7 = (cx += this.ae.b(i));
/* 297 */                 float f8 = cy;
/* 298 */                 float f9 = (cx += this.ae.b(i + 1));
/* 299 */                 float f10 = (cy += -this.ae.b(i + 2));
/* 300 */                 float f11 = (cx += this.ae.b(i + 3));
/* 301 */                 float f12 = cy;
/* 302 */                 path.curveTo(f7, f8, f9, f10, f11, f12);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 308 */               this.ae.a(buff, op, this.ae.c());
/* 309 */               this.ae.b();
/* 310 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 31:
/* 314 */               if (this.ae.c() < 1 || this.ae.c() % 4 > 1) {
/* 315 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 317 */               for (i = 0; i < this.ae.c() - 3; i += 4) {
/*     */                 float f7, f8, f9, f10, f11, f12;
/* 319 */                 if (i / 4 % 2 == 1) {
/* 320 */                   f7 = cx;
/* 321 */                   f8 = (cy += -this.ae.b(i));
/* 322 */                   f9 = (cx += this.ae.b(i + 1));
/* 323 */                   f10 = (cy += -this.ae.b(i + 2));
/* 324 */                   f11 = (cx += this.ae.b(i + 3));
/* 325 */                   if (i + 4 == this.ae.c() - 1) {
/* 326 */                     f12 = (cy += -this.ae.b(i + 4));
/*     */                   } else {
/* 328 */                     f12 = cy;
/*     */                   } 
/*     */                 } else {
/* 331 */                   f7 = (cx += this.ae.b(i));
/* 332 */                   f8 = cy;
/* 333 */                   f9 = (cx += this.ae.b(i + 1));
/* 334 */                   f10 = (cy += -this.ae.b(i + 2));
/* 335 */                   f12 = (cy += -this.ae.b(i + 3));
/* 336 */                   if (i + 4 == this.ae.c() - 1) {
/* 337 */                     f11 = (cx += this.ae.b(i + 4));
/*     */                   } else {
/* 339 */                     f11 = cx;
/*     */                   } 
/*     */                 } 
/* 342 */                 path.curveTo(f7, f8, f9, f10, f11, f12);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 348 */               this.ae.a(buff, op, this.ae.c());
/* 349 */               this.ae.b();
/* 350 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 24:
/* 354 */               if (this.ae.c() < 1 || (this.ae.c() - 2) % 6 != 0) {
/* 355 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 357 */               for (i = 0; i < this.ae.c() - 7; i += 6) {
/* 358 */                 float f7 = (cx += this.ae.b(i));
/* 359 */                 float f8 = (cy += -this.ae.b(i + 1));
/* 360 */                 float f9 = (cx += this.ae.b(i + 2));
/* 361 */                 float f10 = (cy += -this.ae.b(i + 3));
/* 362 */                 float f11 = (cx += this.ae.b(i + 4));
/* 363 */                 float f12 = (cy += -this.ae.b(i + 5));
/* 364 */                 path.curveTo(f7, f8, f9, f10, f11, f12);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 370 */               cx += this.ae.b(this.ae.c() - 2);
/* 371 */               cy += -this.ae.b(this.ae.c() - 1);
/* 372 */               path.lineTo(cx, cy);
/*     */ 
/*     */ 
/*     */               
/* 376 */               this.ae.a(buff, op, this.ae.c());
/* 377 */               this.ae.b();
/* 378 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 25:
/* 382 */               if (this.ae.c() < 1 || (this.ae.c() - 6) % 2 != 0) {
/* 383 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 385 */               i = 0;
/* 386 */               for (; i < this.ae.c() - 6; i += 2) {
/* 387 */                 cx += this.ae.b(i);
/* 388 */                 cy += -this.ae.b(i + 1);
/* 389 */                 path.lineTo(cx, cy);
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 394 */               f1 = (cx += this.ae.b(i));
/* 395 */               f2 = (cy += -this.ae.b(i + 1));
/* 396 */               f3 = (cx += this.ae.b(i + 2));
/* 397 */               f4 = (cy += -this.ae.b(i + 3));
/* 398 */               f5 = (cx += this.ae.b(i + 4));
/* 399 */               f6 = (cy += -this.ae.b(i + 5));
/* 400 */               path.curveTo(f1, f2, f3, f4, f5, f6);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 405 */               this.ae.a(buff, op, this.ae.c());
/* 406 */               this.ae.b();
/* 407 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 30:
/* 411 */               if (this.ae.c() < 1 || this.ae.c() % 4 > 1) {
/* 412 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 414 */               for (i = 0; i < this.ae.c() - 3; i += 4) {
/*     */                 
/* 416 */                 if (i / 4 % 2 == 0) {
/* 417 */                   f1 = cx;
/* 418 */                   f2 = (cy += -this.ae.b(i));
/* 419 */                   f3 = (cx += this.ae.b(i + 1));
/* 420 */                   f4 = (cy += -this.ae.b(i + 2));
/* 421 */                   f5 = (cx += this.ae.b(i + 3));
/* 422 */                   if (i + 4 == this.ae.c() - 1) {
/* 423 */                     f6 = (cy += -this.ae.b(i + 4));
/*     */                   } else {
/* 425 */                     f6 = cy;
/*     */                   } 
/*     */                 } else {
/* 428 */                   f1 = (cx += this.ae.b(i));
/* 429 */                   f2 = cy;
/* 430 */                   f3 = (cx += this.ae.b(i + 1));
/* 431 */                   f4 = (cy += -this.ae.b(i + 2));
/* 432 */                   f6 = (cy += -this.ae.b(i + 3));
/* 433 */                   if (i + 4 == this.ae.c() - 1) {
/* 434 */                     f5 = (cx += this.ae.b(i + 4));
/*     */                   } else {
/* 436 */                     f5 = cx;
/*     */                   } 
/*     */                 } 
/* 439 */                 path.curveTo(f1, f2, f3, f4, f5, f6);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 445 */               this.ae.a(buff, op, this.ae.c());
/* 446 */               this.ae.b();
/* 447 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 26:
/* 451 */               if (this.ae.c() < 2 || (this.ae
/* 452 */                 .c() % 4 != 0 && (this.ae.c() - 1) % 4 != 0)) {
/* 453 */                 throw new ArrayIndexOutOfBoundsException(this.ae.c());
/*     */               }
/* 455 */               i = 0;
/* 456 */               if (this.ae.c() % 2 == 1) {
/* 457 */                 cx += this.ae.b(i);
/* 458 */                 i++;
/*     */               } 
/* 460 */               for (; i < this.ae.c() - 3; i += 4) {
/* 461 */                 f1 = cx;
/* 462 */                 f2 = (cy += -this.ae.b(i));
/* 463 */                 f3 = (cx += this.ae.b(i + 1));
/* 464 */                 f4 = (cy += -this.ae.b(i + 2));
/* 465 */                 f5 = cx;
/* 466 */                 f6 = (cy += -this.ae.b(i + 3));
/* 467 */                 path.curveTo(f1, f2, f3, f4, f5, f6);
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 473 */               this.ae.a(buff, op, this.ae.c());
/* 474 */               this.ae.b();
/* 475 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 3107:
/* 479 */               x1 = (cx += this.ae.b(0));
/* 480 */               y1 = (cy += -this.ae.b(1));
/* 481 */               x2 = (cx += this.ae.b(2));
/* 482 */               y2 = (cy += -this.ae.b(3));
/* 483 */               x3 = (cx += this.ae.b(4));
/* 484 */               y3 = (cy += -this.ae.b(5));
/* 485 */               x4 = (cx += this.ae.b(6));
/* 486 */               y4 = (cy += -this.ae.b(7));
/* 487 */               x5 = (cx += this.ae.b(8));
/* 488 */               y5 = (cy += -this.ae.b(9));
/* 489 */               x6 = (cx += this.ae.b(10));
/* 490 */               y6 = (cy += -this.ae.b(11));
/* 491 */               path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */ 
/*     */ 
/*     */               
/* 495 */               path.curveTo(x4, y4, x5, y5, x6, y6);
/*     */ 
/*     */ 
/*     */               
/* 499 */               this.ae.a(buff, op, this.ae.c());
/* 500 */               this.ae.b();
/* 501 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 3106:
/* 505 */               x1 = (cx += this.ae.b(0));
/* 506 */               y1 = cy;
/* 507 */               x2 = (cx += this.ae.b(1));
/* 508 */               y2 = (cy += -this.ae.b(2));
/* 509 */               x3 = (cx += this.ae.b(3));
/* 510 */               y3 = cy;
/* 511 */               x4 = (cx += this.ae.b(4));
/* 512 */               y4 = cy;
/* 513 */               x5 = (cx += this.ae.b(5));
/* 514 */               y5 = cy;
/* 515 */               x6 = (cx += this.ae.b(6));
/* 516 */               y6 = cy;
/* 517 */               path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */ 
/*     */ 
/*     */               
/* 521 */               path.curveTo(x4, y4, x5, y5, x6, y6);
/*     */ 
/*     */ 
/*     */               
/* 525 */               this.ae.a(buff, op, this.ae.c());
/* 526 */               this.ae.b();
/* 527 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 3108:
/* 531 */               x1 = (cx += this.ae.b(0));
/* 532 */               y1 = (cy += -this.ae.b(1));
/* 533 */               x2 = (cx += this.ae.b(2));
/* 534 */               y2 = (cy += -this.ae.b(3));
/* 535 */               x3 = (cx += this.ae.b(4));
/* 536 */               y3 = cy;
/* 537 */               x4 = (cx += this.ae.b(5));
/* 538 */               y4 = cy;
/* 539 */               x5 = (cx += this.ae.b(6));
/* 540 */               y5 = (cy += -this.ae.b(7));
/* 541 */               x6 = (cx += this.ae.b(8));
/* 542 */               y6 = cy;
/* 543 */               path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */ 
/*     */ 
/*     */               
/* 547 */               path.curveTo(x4, y4, x5, y5, x6, y6);
/*     */ 
/*     */ 
/*     */               
/* 551 */               this.ae.a(buff, op, this.ae.c());
/* 552 */               this.ae.b();
/* 553 */               closed = false;
/*     */               continue;
/*     */             
/*     */             case 3109:
/* 557 */               x1 = (cx += this.ae.b(0));
/* 558 */               y1 = (cy += -this.ae.b(1));
/* 559 */               x2 = (cx += this.ae.b(2));
/* 560 */               y2 = (cy += -this.ae.b(3));
/* 561 */               x3 = (cx += this.ae.b(4));
/* 562 */               y3 = (cy += -this.ae.b(5));
/* 563 */               x4 = (cx += this.ae.b(6));
/* 564 */               y4 = (cy += -this.ae.b(7));
/* 565 */               x5 = (cx += this.ae.b(8));
/* 566 */               y5 = (cy += -this.ae.b(9));
/* 567 */               x6 = (cx += this.ae.b(10));
/* 568 */               y6 = (cy += -this.ae.b(10));
/* 569 */               path.curveTo(x1, y1, x2, y2, x3, y3);
/*     */ 
/*     */ 
/*     */               
/* 573 */               path.curveTo(x4, y4, x5, y5, x6, y6);
/*     */ 
/*     */ 
/*     */               
/* 577 */               this.ae.a(buff, op, this.ae.c());
/* 578 */               this.ae.b();
/* 579 */               closed = false;
/*     */               continue;
/*     */ 
/*     */             
/*     */             case 1:
/*     */             case 3:
/*     */             case 18:
/*     */             case 23:
/* 587 */               hintCount += this.ae.c() / 2;
/*     */ 
/*     */ 
/*     */               
/* 591 */               this.ae.a(buff, op, this.ae.c() / 2 * 2);
/* 592 */               this.ae.b();
/*     */               continue;
/*     */ 
/*     */             
/*     */             case 19:
/*     */             case 20:
/* 598 */               hintCount += this.ae.c() / 2;
/*     */ 
/*     */ 
/*     */               
/* 602 */               this.ae.a(buff, op, this.ae.c() / 2 * 2);
/* 603 */               this.ae.b();
/* 604 */               maskBytes = (hintCount + 7) / 8;
/*     */ 
/*     */ 
/*     */               
/* 608 */               for (j = 0; j < maskBytes; j++) {
/* 609 */                 buff.write(this.ad.read());
/*     */               }
/*     */               continue;
/*     */ 
/*     */             
/*     */             case 10:
/* 615 */               six = this.ae.a();
/* 616 */               if (localSubrOffsets.length < 1240) {
/* 617 */                 six += 107;
/* 618 */               } else if (localSubrOffsets.length < 33900) {
/* 619 */                 six += 1131;
/*     */               } else {
/* 621 */                 six += 32768;
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 626 */               this.af[this.ag++] = (int)this.ad.getFilePointer();
/* 627 */               this.ad.seek(localSubrOffsets[six]);
/*     */               continue;
/*     */ 
/*     */             
/*     */             case 29:
/* 632 */               six = this.ae.a();
/* 633 */               if (globalSubrOffsets.length < 1240) {
/* 634 */                 six += 107;
/* 635 */               } else if (globalSubrOffsets.length < 33900) {
/* 636 */                 six += 1131;
/*     */               } else {
/* 638 */                 six += 32768;
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 643 */               this.af[this.ag++] = (int)this.ad.getFilePointer();
/* 644 */               this.ad.seek(globalSubrOffsets[six]);
/*     */               continue;
/*     */ 
/*     */             
/*     */             case 11:
/* 649 */               this.ad.seek(this.af[--this.ag]);
/*     */               continue;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 656 */           throw new UnsupportedOperationException(Integer.toHexString(op));
/*     */         } 
/*     */ 
/*     */         
/* 660 */         if (!closed) {
/* 661 */           path.closePath();
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 666 */       catch (IOException iOException) {
/* 667 */         throw new RuntimeException(iOException);
/*     */       } 
/*     */     } 
/*     */     
/* 671 */     buff.write(14);
/* 672 */     byte[] charString = buff.toByteArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 679 */     return new b(path, charString);
/*     */   }
/*     */   
/*     */   private int a() throws IOException {
/*     */     while (true) {
/* 684 */       byte type = b();
/* 685 */       switch (type) {
/*     */         case 1:
/* 687 */           return c();
/*     */         case 2:
/* 689 */           this.ae.a(d()); continue;
/*     */       }  break;
/*     */     } 
/* 692 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private byte b() throws IOException {
/* 698 */     this.ah = this.ad.read();
/* 699 */     if (this.ah <= 31 && this.ah != 28) {
/* 700 */       return 1;
/*     */     }
/* 702 */     return 2;
/*     */   }
/*     */   
/*     */   private int c() throws IOException {
/* 706 */     if (this.ah == -1 && 
/* 707 */       b() != 1) {
/* 708 */       throw new IOException("Operatorではありません。");
/*     */     }
/*     */     
/* 711 */     int b = this.ah;
/* 712 */     this.ah = -1;
/* 713 */     if (b == 12) {
/* 714 */       b <<= 8;
/* 715 */       int a = this.ad.read();
/* 716 */       b |= a;
/*     */     } 
/* 718 */     return b;
/*     */   }
/*     */   
/*     */   private int d() throws IOException {
/* 722 */     if (this.ah == -1 && 
/* 723 */       b() != 2) {
/* 724 */       throw new IOException("Integerではありません。");
/*     */     }
/*     */     
/* 727 */     int b0 = this.ah;
/* 728 */     this.ah = -1;
/* 729 */     if (b0 >= 32 && b0 <= 246) {
/* 730 */       return b0 - 139;
/*     */     }
/* 732 */     if (b0 >= 247 && b0 <= 250) {
/* 733 */       int b1 = this.ad.read();
/* 734 */       return (b0 - 247) * 256 + b1 + 108;
/*     */     } 
/* 736 */     if (b0 >= 251 && b0 <= 254) {
/* 737 */       int b1 = this.ad.read();
/* 738 */       return -(b0 - 251) * 256 - b1 - 108;
/*     */     } 
/* 740 */     if (b0 == 28) {
/* 741 */       int b1 = this.ad.read();
/* 742 */       int b2 = this.ad.read();
/* 743 */       return b1 << 8 | b2;
/*     */     } 
/* 745 */     if (b0 == 255) {
/* 746 */       int b1 = this.ad.read();
/* 747 */       int b2 = this.ad.read();
/* 748 */       int b3 = this.ad.read();
/* 749 */       int b4 = this.ad.read();
/* 750 */       return b1 << 24 | b2 << 16 | b3 << 8 | b4;
/*     */     } 
/* 752 */     throw new IOException("不正なIntegerです。:" + b0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */