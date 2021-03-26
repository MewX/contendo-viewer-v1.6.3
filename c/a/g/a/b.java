/*     */ package c.a.g.a;
/*     */ 
/*     */ import c.a.b.a;
/*     */ import c.a.e.c.a;
/*     */ import c.a.j.b.a;
/*     */ import c.a.j.b.g;
/*     */ import c.a.j.b.h;
/*     */ import c.a.j.b.i;
/*     */ import c.a.j.b.o;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class b
/*     */   extends h
/*     */   implements a
/*     */ {
/*     */   public static final char a = 'Q';
/*  86 */   private static final String[][] g = (String[][])null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected a b;
/*     */ 
/*     */   
/*  93 */   protected int[] c = null;
/*     */ 
/*     */   
/*  96 */   protected int[] d = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c.a.e.b h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private o i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(a src, int[] utrb, a decSpec) {
/* 117 */     super((g)src);
/* 118 */     if (utrb.length != src.c()) {
/* 119 */       throw new IllegalArgumentException();
/*     */     }
/* 121 */     this.b = src;
/* 122 */     this.d = utrb;
/* 123 */     this.h = decSpec.k;
/* 124 */     this.i = decSpec.f;
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
/*     */   public int i(int c) {
/* 152 */     return this.c[c];
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
/*     */   public i f(int t, int c) {
/* 172 */     return this.b.f(t, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 180 */     return this.b.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 188 */     return this.b.b();
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
/*     */   public static String[][] g() {
/* 206 */     return g;
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
/*     */   public void c(int x, int y) {
/* 222 */     this.b.c(x, y);
/* 223 */     this.e = e();
/*     */ 
/*     */     
/* 226 */     int cttype = 0;
/* 227 */     if (((Integer)this.h.f(this.e)).intValue() == 0) {
/* 228 */       cttype = 0;
/*     */     } else {
/* 230 */       int nc = (this.b.c() > 3) ? 3 : this.b.c();
/* 231 */       int rev = 0;
/* 232 */       for (int c = 0; c < nc; c++)
/* 233 */         rev += this.i.h(this.e, c) ? 1 : 0; 
/* 234 */       if (rev == 3) {
/*     */         
/* 236 */         cttype = 1;
/*     */       }
/* 238 */       else if (rev == 0) {
/*     */         
/* 240 */         cttype = 2;
/*     */       }
/*     */       else {
/*     */         
/* 244 */         throw new IllegalArgumentException("Wavelet transformation and component transformation not coherent in tile" + this.e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     switch (cttype) {
/*     */       case 0:
/* 254 */         this.c = this.d;
/*     */         return;
/*     */       case 1:
/* 257 */         this
/* 258 */           .c = a.a(this.d, 1, null);
/*     */         return;
/*     */       case 2:
/* 261 */         this
/* 262 */           .c = a.a(this.d, 2, null);
/*     */         return;
/*     */     } 
/* 265 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation for tile: " + this.e);
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
/*     */   public void d() {
/* 281 */     this.b.d();
/* 282 */     this.e = e();
/*     */ 
/*     */     
/* 285 */     int cttype = ((Integer)this.h.f(this.e)).intValue();
/* 286 */     switch (cttype) {
/*     */       case 0:
/* 288 */         this.c = this.d;
/*     */         return;
/*     */       case 1:
/* 291 */         this
/* 292 */           .c = a.a(this.d, 1, null);
/*     */         return;
/*     */       case 2:
/* 295 */         this
/* 296 */           .c = a.a(this.d, 2, null);
/*     */         return;
/*     */     } 
/* 299 */     throw new IllegalArgumentException("Non JPEG 2000 part I component transformation for tile: " + this.e);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/g/a/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */