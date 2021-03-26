/*     */ package c.a.g.a;
/*     */ 
/*     */ import c.a.b.a;
/*     */ import c.a.e.c;
/*     */ import c.a.e.e;
/*     */ import c.a.g.a;
/*     */ import c.a.g.b;
/*     */ import c.a.g.c;
/*     */ import c.a.j.b.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends b
/*     */ {
/*     */   private c g;
/*     */   private b h;
/*     */   private a i;
/*     */   private e j;
/*     */   private e k;
/*     */   private int l;
/*     */   
/*     */   public d(a src, int[] utrb, a decSpec) {
/* 130 */     super(src, utrb, decSpec);
/*     */     
/* 132 */     if (utrb.length != src.c()) {
/* 133 */       throw new IllegalArgumentException("Invalid rb argument");
/*     */     }
/* 135 */     this.h = decSpec.d;
/* 136 */     this.g = decSpec.c;
/* 137 */     this.i = decSpec.e;
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
/*     */   public int j(int i) {
/* 159 */     return 0;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public final c a(int j, int m, int n, i sb, c cblk) {
/* 203 */     return b(j, m, n, sb, cblk);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final c b(int j, int m, int n, i sb, c cblk) {
/*     */     c.a.e.d d1;
/* 257 */     boolean reversible = this.g.f(this.e, j);
/* 258 */     boolean derived = this.g.e(this.e, j);
/*     */     
/* 260 */     e params = (e)this.h.a(this.e, j);
/* 261 */     int G = ((Integer)this.i.a(this.e, j)).intValue();
/*     */     
/* 263 */     this.l = cblk.a();
/*     */     
/* 265 */     if (reversible && this.l != 3) {
/* 266 */       throw new IllegalArgumentException("Reversible quantizations must use int data");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 271 */     int[] outiarr = null;
/* 272 */     float[] outfarr = null;
/* 273 */     int[] inarr = null;
/*     */ 
/*     */     
/* 276 */     switch (this.l) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 281 */         cblk = this.b.a(j, m, n, sb, cblk);
/*     */         
/* 283 */         outiarr = (int[])cblk.b();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 288 */         this.k = (e)this.b.b(j, m, n, sb, (c)this.k);
/* 289 */         inarr = this.k.c();
/* 290 */         if (cblk == null) {
/* 291 */           d1 = new c.a.e.d();
/*     */         }
/*     */         
/* 294 */         ((c)d1).e = this.k.e;
/* 295 */         ((c)d1).f = this.k.f;
/* 296 */         ((c)d1).g = this.k.g;
/* 297 */         ((c)d1).h = this.k.h;
/* 298 */         ((c)d1).i = 0;
/* 299 */         ((c)d1).j = ((c)d1).g;
/* 300 */         ((c)d1).k = this.k.k;
/*     */         
/* 302 */         outfarr = (float[])d1.b();
/* 303 */         if (outfarr == null || outfarr.length < ((c)d1).g * ((c)d1).h) {
/* 304 */           outfarr = new float[((c)d1).g * ((c)d1).h];
/* 305 */           d1.a(outfarr);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 310 */     int magBits = sb.A;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     if (reversible) {
/* 316 */       int shiftBits = 31 - magBits;
/*     */ 
/*     */ 
/*     */       
/* 320 */       for (int k = outiarr.length - 1; k >= 0; k--) {
/* 321 */         int temp = outiarr[k];
/* 322 */         outiarr[k] = (temp >= 0) ? (temp >> shiftBits) : -((temp & Integer.MAX_VALUE) >> shiftBits);
/*     */       } 
/*     */     } else {
/*     */       int i1, jmin, k; float step;
/*     */       int w, h;
/* 327 */       if (derived) {
/*     */         
/* 329 */         int mrl = (this.b.f(e(), j)).h;
/* 330 */         step = params.b[0][0] * (float)(1L << this.c[j] + sb.j + mrl - sb.g);
/*     */       }
/*     */       else {
/*     */         
/* 334 */         step = params.b[sb.h][sb.k] * (float)(1L << this.c[j] + sb.j);
/*     */       } 
/*     */       
/* 337 */       int shiftBits = 31 - magBits;
/*     */ 
/*     */       
/* 340 */       step /= (1 << shiftBits);
/*     */       
/* 342 */       switch (this.l) {
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 347 */           for (i1 = outiarr.length - 1; i1 >= 0; i1--) {
/* 348 */             int temp = outiarr[i1];
/* 349 */             outiarr[i1] = (int)(((temp >= 0) ? temp : -(temp & Integer.MAX_VALUE)) * step);
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 356 */           w = ((c)d1).g;
/* 357 */           h = ((c)d1).h;
/* 358 */           i1 = w * h - 1; k = this.k.i + (h - 1) * this.k.j + w - 1;
/* 359 */           for (jmin = w * (h - 1); i1 >= 0; jmin -= w) {
/* 360 */             for (; i1 >= jmin; k--, i1--) {
/* 361 */               int temp = inarr[k];
/* 362 */               outfarr[i1] = ((temp >= 0) ? temp : -(temp & Integer.MAX_VALUE)) * step;
/*     */             } 
/*     */ 
/*     */             
/* 366 */             k -= this.k.j - w;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 372 */     return (c)d1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */