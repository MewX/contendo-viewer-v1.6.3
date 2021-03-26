/*     */ package c.a.g.b;
/*     */ 
/*     */ import c.a.g.a;
/*     */ import c.a.g.b;
/*     */ import c.a.j.a.g;
/*     */ import c.a.j.a.h;
/*     */ import c.a.j.a.i;
/*     */ import c.a.j.a.j;
/*     */ import c.a.j.a.o;
/*     */ import c.a.j.b;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends b
/*     */ {
/*     */   public static final int c = 11;
/*     */   public static final int d = 5;
/*     */   public static final int e = 2047;
/*     */   public static final int f = 31;
/* 111 */   private static double g = Math.log(2.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c.a.g.c h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private b i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private h m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(j src, J2KImageWriteParamJava wp) {
/* 144 */     super(src);
/* 145 */     this.h = wp.getQuantizationType();
/* 146 */     this.i = wp.getQuantizationStep();
/* 147 */     this.j = wp.getGuardBits();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c.a.g.c d() {
/* 156 */     return this.h;
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
/*     */   public int a(int t, int i) {
/* 170 */     return ((Integer)this.j.a(t, i)).intValue();
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
/*     */   public boolean d(int t, int i) {
/* 185 */     return this.h.f(t, i);
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
/*     */   public boolean b(int t, int i) {
/* 200 */     return this.h.e(t, i);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g a(int i, g cblk) {
/* 238 */     return b(i, cblk);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final g b(int i, g cblk) {
/*     */     i i1;
/*     */     g g1;
/*     */     int[] outarr;
/* 282 */     float[] infarr = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     int k = ((Integer)this.j.a(this.k, i)).intValue();
/*     */ 
/*     */     
/* 292 */     boolean intq = (this.b.a(this.k, i) == 3);
/*     */ 
/*     */     
/* 295 */     if (cblk == null) {
/* 296 */       i1 = new i();
/*     */     }
/*     */ 
/*     */     
/* 300 */     h infblk = this.m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     if (intq) {
/* 308 */       g1 = this.b.a(i, (g)i1);
/* 309 */       if (g1 == null) {
/* 310 */         return null;
/*     */       }
/*     */       
/* 313 */       outarr = (int[])g1.b();
/*     */     }
/*     */     else {
/*     */       
/* 317 */       infblk = (h)this.b.b(i, (g)infblk);
/* 318 */       if (infblk == null) {
/*     */ 
/*     */ 
/*     */         
/* 322 */         this.m.a(null);
/* 323 */         return null;
/*     */       } 
/* 325 */       this.m = infblk;
/* 326 */       infarr = (float[])infblk.b();
/*     */ 
/*     */       
/* 329 */       outarr = (int[])g1.b();
/* 330 */       if (outarr == null || outarr.length < infblk.f * infblk.g) {
/* 331 */         outarr = new int[infblk.f * infblk.g];
/* 332 */         g1.a(outarr);
/*     */       } 
/* 334 */       g1.d = infblk.d;
/* 335 */       g1.c = infblk.c;
/* 336 */       g1.e = infblk.e;
/* 337 */       g1.a = infblk.a;
/* 338 */       g1.b = infblk.b;
/* 339 */       g1.f = infblk.f;
/* 340 */       g1.g = infblk.g;
/* 341 */       g1.k = infblk.k;
/* 342 */       g1.h = 0;
/* 343 */       g1.i = g1.f;
/*     */     } 
/*     */ 
/*     */     
/* 347 */     int w = g1.f;
/* 348 */     int j = g1.g;
/* 349 */     o sb = g1.e;
/*     */     
/* 351 */     if (d(this.k, i)) {
/* 352 */       g1.j = k - 1 + this.b.getNomRangeBits(i) + sb.j;
/* 353 */       int shiftBits = 31 - g1.j;
/*     */ 
/*     */       
/* 356 */       g1.l = (1 << shiftBits);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 361 */       for (int m = w * j - 1; m >= 0; m--) {
/* 362 */         int tmp = outarr[m] << shiftBits;
/* 363 */         outarr[m] = (tmp < 0) ? (Integer.MIN_VALUE | -tmp) : tmp;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 368 */       float baseStep = ((Float)this.i.a(this.k, i)).floatValue();
/*     */ 
/*     */       
/* 371 */       if (b(this.k, i)) {
/* 372 */         g1
/* 373 */           .j = k - 1 + sb.g - (int)Math.floor(Math.log(baseStep) / c.g);
/* 374 */         stepUDR = baseStep / (1 << sb.g);
/*     */       } else {
/*     */         
/* 377 */         g1.j = k - 1 - (int)Math.floor(Math.log((baseStep / sb.A * (1 << sb.j))) / c.g);
/*     */ 
/*     */         
/* 380 */         stepUDR = baseStep / sb.A * (1 << sb.j);
/*     */       } 
/* 382 */       int shiftBits = 31 - g1.j;
/*     */ 
/*     */       
/* 385 */       float stepUDR = b(a(stepUDR));
/* 386 */       float invstep = 1.0F / (float)(1L << this.b.getNomRangeBits(i) + sb.j) * stepUDR;
/*     */ 
/*     */       
/* 389 */       invstep *= (1 << shiftBits - this.b.b(i));
/*     */ 
/*     */       
/* 392 */       g1.l = invstep;
/* 393 */       g1.m = ((float)(1L << this.b.getNomRangeBits(i) + sb.j) * stepUDR);
/*     */ 
/*     */       
/* 396 */       if (intq) {
/*     */ 
/*     */ 
/*     */         
/* 400 */         for (int m = w * j - 1; m >= 0; m--) {
/* 401 */           int tmp = (int)(outarr[m] * invstep);
/* 402 */           outarr[m] = (tmp < 0) ? (Integer.MIN_VALUE | -tmp) : tmp;
/*     */         } 
/*     */       } else {
/*     */         
/* 406 */         int n = w * j - 1, m = infblk.h + (j - 1) * infblk.i + w - 1; int jmin;
/* 407 */         for (jmin = w * (j - 1); n >= 0; jmin -= w) {
/* 408 */           for (; n >= jmin; m--, n--) {
/* 409 */             int tmp = (int)(infarr[m] * invstep);
/* 410 */             outarr[n] = (tmp < 0) ? (Integer.MIN_VALUE | -tmp) : tmp;
/*     */           } 
/*     */           
/* 413 */           m -= infblk.i - w;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 418 */     return g1;
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
/*     */   protected void a(o sb, int i) {
/* 436 */     if (sb.B > 0.0F)
/*     */       return; 
/* 438 */     if (!sb.e) {
/* 439 */       if (d(this.k, i)) {
/* 440 */         sb.B = (float)Math.pow(2.0D, -(this.b.getNomRangeBits(i) << 1)) * sb.A * sb.A;
/*     */       }
/*     */       else {
/*     */         
/* 444 */         float baseStep = ((Float)this.i.a(this.k, i)).floatValue();
/* 445 */         if (b(this.k, i)) {
/* 446 */           sb
/* 447 */             .B = baseStep * baseStep * (float)Math.pow(2.0D, (sb.j - sb.g << 1)) * sb.A * sb.A;
/*     */         }
/*     */         else {
/*     */           
/* 451 */           sb.B = baseStep * baseStep;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 456 */       a((o)sb.b(), i);
/* 457 */       a((o)sb.c(), i);
/* 458 */       a((o)sb.d(), i);
/* 459 */       a((o)sb.e(), i);
/* 460 */       sb.B = 1.0F;
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
/*     */   public static int a(float step) {
/* 476 */     int exp = (int)Math.ceil(-Math.log(step) / g);
/* 477 */     if (exp > 31)
/*     */     {
/*     */       
/* 480 */       return 63488;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 485 */     return exp << 11 | (int)((-step * (-1 << exp) - 1.0F) * 2048.0F + 0.5F);
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
/*     */   private static float b(int ems) {
/* 503 */     return (-1.0F - (ems & 0x7FF) / 2048.0F) / (-1 << (ems >> 11 & 0x1F));
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
/*     */   public int a(int i) {
/* 518 */     o o = e(this.k, i);
/* 519 */     if (d(this.k, i)) {
/* 520 */       return a((b)o, i);
/*     */     }
/*     */     
/* 523 */     if (b(this.k, i)) {
/* 524 */       return a((b)o, this.k, i);
/*     */     }
/*     */     
/* 527 */     return b((b)o, this.k, i);
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
/*     */   private int a(b sb, int i) {
/* 544 */     int max = 0;
/* 545 */     int g = ((Integer)this.j.a(this.k, i)).intValue();
/*     */     
/* 547 */     if (!sb.e) {
/* 548 */       return g - 1 + this.b.getNomRangeBits(i) + sb.j;
/*     */     }
/* 550 */     max = a(sb.b(), i);
/* 551 */     int tmp = a(sb.d(), i);
/* 552 */     if (tmp > max)
/* 553 */       max = tmp; 
/* 554 */     tmp = a(sb.c(), i);
/* 555 */     if (tmp > max)
/* 556 */       max = tmp; 
/* 557 */     tmp = a(sb.e(), i);
/* 558 */     if (tmp > max) {
/* 559 */       max = tmp;
/*     */     }
/* 561 */     return max;
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
/*     */   private int a(b sb, int t, int i) {
/* 577 */     int max = 0;
/* 578 */     int g = ((Integer)this.j.a(t, i)).intValue();
/*     */     
/* 580 */     if (!sb.e) {
/* 581 */       float baseStep = ((Float)this.i.a(t, i)).floatValue();
/* 582 */       return g - 1 + sb.g - (int)Math.floor(Math.log(baseStep) / c.g);
/*     */     } 
/*     */     
/* 585 */     max = a(sb.b(), t, i);
/* 586 */     int tmp = a(sb.d(), t, i);
/* 587 */     if (tmp > max)
/* 588 */       max = tmp; 
/* 589 */     tmp = a(sb.c(), t, i);
/* 590 */     if (tmp > max)
/* 591 */       max = tmp; 
/* 592 */     tmp = a(sb.e(), t, i);
/* 593 */     if (tmp > max) {
/* 594 */       max = tmp;
/*     */     }
/* 596 */     return max;
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
/*     */   private int b(b sb, int t, int i) {
/* 613 */     int max = 0;
/* 614 */     int g = ((Integer)this.j.a(t, i)).intValue();
/*     */     
/* 616 */     if (!sb.e) {
/* 617 */       float baseStep = ((Float)this.i.a(t, i)).floatValue();
/*     */       
/* 619 */       return g - 1 - (int)Math.floor(Math.log((baseStep / ((o)sb).A * (1 << sb.j))) / c.g);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 624 */     max = b(sb.b(), t, i);
/* 625 */     int tmp = b(sb.d(), t, i);
/* 626 */     if (tmp > max)
/* 627 */       max = tmp; 
/* 628 */     tmp = b(sb.c(), t, i);
/* 629 */     if (tmp > max)
/* 630 */       max = tmp; 
/* 631 */     tmp = b(sb.e(), t, i);
/* 632 */     if (tmp > max) {
/* 633 */       max = tmp;
/*     */     }
/* 635 */     return max;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/b/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */