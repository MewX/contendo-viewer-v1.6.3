/*     */ package c.a.j.a;
/*     */ 
/*     */ import c.a.j.b;
/*     */ import c.a.j.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class o
/*     */   extends b
/*     */ {
/*  74 */   public o t = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o u;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o v;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o w;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a y;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a z;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public float A = -1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float B;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public o(int w, int h, int ulcx, int ulcy, int lvls, e[] hfilters, e[] vfilters) {
/* 177 */     super(w, h, ulcx, ulcy, lvls, hfilters, vfilters);
/*     */     
/* 179 */     k();
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
/*     */   public b a() {
/* 192 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b() {
/* 203 */     return this.u;
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
/*     */   public b c() {
/* 215 */     return this.v;
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
/*     */   public b d() {
/* 227 */     return this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b e() {
/* 238 */     return this.x;
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
/*     */   protected b a(e hfilter, e vfilter) {
/* 264 */     if (this.e) {
/* 265 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 269 */     this.e = true;
/* 270 */     this.y = (a)hfilter;
/* 271 */     this.z = (a)vfilter;
/*     */ 
/*     */     
/* 274 */     this.u = new o();
/* 275 */     this.w = new o();
/* 276 */     this.v = new o();
/* 277 */     this.x = new o();
/*     */ 
/*     */     
/* 280 */     this.u.t = this;
/* 281 */     this.v.t = this;
/* 282 */     this.w.t = this;
/* 283 */     this.x.t = this;
/*     */ 
/*     */     
/* 286 */     f();
/*     */ 
/*     */     
/* 289 */     return this.u;
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
/*     */   private void a(float[][] wfs) {
/* 314 */     if (this.A < 0.0F) {
/*     */       
/* 316 */       if (this.e)
/*     */       {
/* 318 */         if (this.u.A < 0.0F) {
/* 319 */           this.u.a(wfs);
/* 320 */           wfs[0] = this.y
/* 321 */             .a(wfs[0], null);
/* 322 */           wfs[1] = this.z
/* 323 */             .a(wfs[1], null);
/*     */         }
/* 325 */         else if (this.v.A < 0.0F) {
/* 326 */           this.v.a(wfs);
/* 327 */           wfs[0] = this.y
/* 328 */             .b(wfs[0], null);
/* 329 */           wfs[1] = this.z
/* 330 */             .a(wfs[1], null);
/*     */         }
/* 332 */         else if (this.w.A < 0.0F) {
/* 333 */           this.w.a(wfs);
/* 334 */           wfs[0] = this.y
/* 335 */             .a(wfs[0], null);
/* 336 */           wfs[1] = this.z
/* 337 */             .b(wfs[1], null);
/*     */         }
/* 339 */         else if (this.x.A < 0.0F) {
/* 340 */           this.x.a(wfs);
/* 341 */           wfs[0] = this.y
/* 342 */             .b(wfs[0], null);
/* 343 */           wfs[1] = this.z
/* 344 */             .b(wfs[1], null);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 350 */           throw new Error("You have found a bug in JJ2000!");
/*     */         }
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 356 */         wfs[0] = new float[1];
/* 357 */         wfs[0][0] = 1.0F;
/* 358 */         wfs[1] = new float[1];
/* 359 */         wfs[1][0] = 1.0F;
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 366 */       throw new Error("You have found a bug in JJ2000!");
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
/*     */   private void a(float l2n) {
/* 386 */     if (this.A < 0.0F) {
/*     */       
/* 388 */       if (this.e) {
/*     */         
/* 390 */         if (this.u.A < 0.0F) {
/* 391 */           this.u.a(l2n);
/*     */         }
/* 393 */         else if (this.v.A < 0.0F) {
/* 394 */           this.v.a(l2n);
/*     */         }
/* 396 */         else if (this.w.A < 0.0F) {
/* 397 */           this.w.a(l2n);
/*     */         }
/* 399 */         else if (this.x.A < 0.0F) {
/* 400 */           this.x.a(l2n);
/*     */           
/* 402 */           if (this.x.A >= 0.0F) {
/* 403 */             this.A = 0.0F;
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 410 */           throw new Error("You have found a bug in JJ2000!");
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 415 */         this.A = l2n;
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 422 */       throw new Error("You have found a bug in JJ2000!");
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
/*     */   private void k() {
/* 436 */     float[][] wfs = new float[2][];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 442 */     while (this.A < 0.0F) {
/* 443 */       a(wfs);
/*     */ 
/*     */       
/* 446 */       double acc = 0.0D; int i;
/* 447 */       for (i = (wfs[0]).length - 1; i >= 0; i--) {
/* 448 */         acc += (wfs[0][i] * wfs[0][i]);
/*     */       }
/* 450 */       float l2n = (float)Math.sqrt(acc);
/*     */       
/* 452 */       acc = 0.0D;
/* 453 */       for (i = (wfs[1]).length - 1; i >= 0; i--) {
/* 454 */         acc += (wfs[1][i] * wfs[1][i]);
/*     */       }
/* 456 */       l2n *= (float)Math.sqrt(acc);
/*     */       
/* 458 */       wfs[0] = null;
/* 459 */       wfs[1] = null;
/*     */       
/* 461 */       a(l2n);
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
/*     */   public e i() {
/* 474 */     return this.y;
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
/*     */   public e j() {
/* 486 */     return this.y;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/o.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */