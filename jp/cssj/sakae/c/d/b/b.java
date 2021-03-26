/*     */ package jp.cssj.sakae.c.d.b;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.b.a.a;
/*     */ import jp.cssj.sakae.c.d.b.a.c;
/*     */ import jp.cssj.sakae.c.d.c;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.c.d.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements e
/*     */ {
/*  29 */   private byte j = 1;
/*     */ 
/*     */   
/*  32 */   private double k = 1.2D;
/*     */ 
/*     */   
/*  35 */   private double l = Double.MAX_VALUE;
/*     */ 
/*     */   
/*  38 */   private double m = Double.MAX_VALUE;
/*     */ 
/*     */   
/*  41 */   private double n = 0.0D;
/*     */ 
/*     */   
/*  44 */   private double o = 0.0D;
/*     */ 
/*     */   
/*  47 */   private double p = 0.0D;
/*     */   static {
/*  49 */     a = 0;
/*  50 */     b = 1;
/*     */ 
/*     */ 
/*     */     
/*  54 */     c = 0;
/*  55 */     d = 1;
/*  56 */     e = 2;
/*  57 */     f = 3;
/*     */   }
/*  59 */   private byte q = a; private byte r = c;
/*     */ 
/*     */ 
/*     */   
/*  63 */   private int u = 1;
/*     */   
/*  65 */   private double v = 0.0D;
/*     */   
/*  67 */   private double w = 0.0D; private double x = 0.0D;
/*     */   
/*  69 */   private int y = 0;
/*     */   
/*  71 */   private List<Object> z = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  75 */   private double B = 0.0D;
/*     */   
/*  77 */   private i C = null;
/*     */   
/*  79 */   private List<c> D = new ArrayList<>();
/*     */   
/*  81 */   private double E = 0.0D;
/*     */   
/*  83 */   private double F = 0.0D;
/*     */   
/*  85 */   private int G = 0;
/*     */   
/*  87 */   private int H = 0;
/*     */   
/*     */   private boolean I = false;
/*     */   
/*  91 */   private double J = 0.0D; private static final double h = 24.0D; private jp.cssj.sakae.c.b i; public static byte a; public static byte b; public static byte c;
/*     */   
/*     */   public jp.cssj.sakae.c.b c() {
/*  94 */     return this.i;
/*     */   }
/*     */   public static byte d; public static byte e; public static byte f; private double s; private double t; private c[] A;
/*     */   public void a(jp.cssj.sakae.c.b gc) {
/*  98 */     this.i = gc;
/*     */   }
/*     */   
/*     */   public byte d() {
/* 102 */     return this.j;
/*     */   }
/*     */   
/*     */   public void a(byte direction) {
/* 106 */     this.j = direction;
/*     */   }
/*     */   
/*     */   public byte e() {
/* 110 */     return this.r;
/*     */   }
/*     */   
/*     */   public void b(byte align) {
/* 114 */     this.r = align;
/*     */   }
/*     */   
/*     */   public double f() {
/* 118 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(double lineHeight) {
/* 122 */     this.k = lineHeight;
/*     */   }
/*     */   
/*     */   public double g() {
/* 126 */     return this.E;
/*     */   }
/*     */   
/*     */   public void b(double letterSpacing) {
/* 130 */     this.E = letterSpacing;
/*     */   }
/*     */   
/*     */   public double h() {
/* 134 */     return this.m;
/*     */   }
/*     */   
/*     */   public void c(double pageAdvance) {
/* 138 */     this.m = pageAdvance;
/*     */   }
/*     */   
/*     */   public int i() {
/* 142 */     return this.u;
/*     */   }
/*     */   
/*     */   public void a(int columnCount) {
/* 146 */     this.u = columnCount;
/*     */   }
/*     */   
/*     */   public double j() {
/* 150 */     return this.v;
/*     */   }
/*     */   
/*     */   public void d(double columnGap) {
/* 154 */     this.v = columnGap;
/*     */   }
/*     */   
/*     */   public void e(double lineAdvance) {
/* 158 */     this.l = lineAdvance;
/*     */   }
/*     */   
/*     */   public double k() {
/* 162 */     return this.n;
/*     */   }
/*     */   
/*     */   public double l() {
/* 166 */     return this.o;
/*     */   }
/*     */   
/*     */   public double m() {
/* 170 */     return this.p;
/*     */   }
/*     */   
/*     */   private double r() {
/* 174 */     double maxAdvance = (this.l - this.v * (this.u - 1)) / this.u;
/* 175 */     if (this.q == b && 
/* 176 */       this.w < this.t) {
/* 177 */       maxAdvance -= this.s;
/*     */     }
/*     */     
/* 180 */     return maxAdvance;
/*     */   }
/*     */   
/*     */   public boolean n() {
/* 184 */     return this.I;
/*     */   }
/*     */   
/*     */   public void a(boolean justifyPage) {
/* 188 */     this.I = justifyPage;
/*     */   }
/*     */   
/*     */   public double o() {
/* 192 */     return this.J;
/*     */   }
/*     */   
/*     */   public void f(double fontSize) {
/* 196 */     this.J = fontSize;
/*     */   }
/*     */   
/*     */   public void a(byte position, double width, double height) {
/* 200 */     this.q = position;
/* 201 */     this.s = width;
/* 202 */     this.t = height;
/*     */   }
/*     */   
/*     */   private void b(boolean last) {
/*     */     double advance;
/* 207 */     if (last) {
/* 208 */       int elementCount = this.D.size();
/* 209 */       if (this.C != null) {
/* 210 */         elementCount++;
/*     */       }
/* 212 */       this.A = new c[elementCount];
/* 213 */       for (int k = 0; k < this.D.size(); k++) {
/* 214 */         this.A[k] = this.D.get(k);
/*     */       }
/* 216 */       if (this.C != null) {
/* 217 */         this.C.n();
/* 218 */         this.A[elementCount - 1] = (c)this.C;
/* 219 */         this.C = null;
/*     */       } 
/* 221 */       advance = this.F;
/* 222 */       this.D.clear();
/*     */     } else {
/* 224 */       advance = 0.0D;
/* 225 */       int count = this.D.size() - this.G;
/* 226 */       int elementCount = count;
/* 227 */       if (this.C != null) {
/* 228 */         if (this.C.l() <= this.H) {
/* 229 */           if (this.G > 0) {
/* 230 */             elementCount++;
/* 231 */             count++;
/*     */           } 
/*     */         } else {
/* 234 */           elementCount++;
/*     */         } 
/*     */       }
/* 237 */       this.A = new c[elementCount];
/* 238 */       Iterator<c> iterator = this.D.iterator();
/* 239 */       for (int k = 0; k < count; k++) {
/* 240 */         c c1 = iterator.next();
/* 241 */         this.A[k] = c1;
/* 242 */         advance += c1.c();
/* 243 */         iterator.remove();
/*     */       } 
/* 245 */       if (this.C != null && this.C.l() > this.H) {
/* 246 */         int pos = this.C.l() - this.H;
/* 247 */         h h = this.C.a(pos);
/* 248 */         this.A[elementCount - 1] = (c)h;
/* 249 */         advance += h.c();
/*     */       } 
/*     */ 
/*     */       
/* 253 */       if (this.r == f) {
/*     */         
/* 255 */         int glyphCount = 0;
/* 256 */         for (int m = 0; m < this.A.length; m++) {
/* 257 */           c c1 = this.A[m];
/* 258 */           if (c1.f_() == 1) {
/* 259 */             glyphCount += ((h)c1).l();
/*     */           }
/*     */         } 
/* 262 */         if (glyphCount >= 2) {
/* 263 */           double letterSpacing = (r() - advance) / (glyphCount - 1);
/* 264 */           for (int n = 0; n < this.A.length; n++) {
/* 265 */             c c1 = this.A[n];
/* 266 */             if (c1.f_() == 1) {
/* 267 */               i t = (i)c1;
/* 268 */               t.a(t.m() + letterSpacing);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 274 */     this.F -= advance;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     double maxAscent = 0.0D, maxDescent = 0.0D;
/* 280 */     for (int j = 0; j < this.A.length; j++) {
/* 281 */       c c1 = this.A[j];
/* 282 */       if (c1.f_() == 1) {
/* 283 */         h text = (h)c1;
/* 284 */         maxAscent = Math.max(maxAscent, text.f());
/* 285 */         maxDescent = Math.max(maxDescent, text.g());
/*     */       } else {
/* 287 */         a control = (a)c1;
/* 288 */         maxAscent = Math.max(maxAscent, control.f());
/* 289 */         maxDescent = Math.max(maxDescent, control.g());
/*     */       } 
/*     */     } 
/* 292 */     if (this.J != 0.0D) {
/* 293 */       maxDescent = this.J - maxAscent;
/*     */     }
/*     */ 
/*     */     
/* 297 */     double lineMargin = (maxAscent + maxDescent) * (this.k - 1.0D) / 2.0D;
/* 298 */     double pageAdvance1 = maxAscent + lineMargin;
/* 299 */     double pageAdvance2 = maxDescent + lineMargin + this.B;
/*     */     
/* 301 */     if (a(this.w + pageAdvance1 + pageAdvance2, this.m) > 0) {
/*     */       
/* 303 */       t();
/*     */ 
/*     */       
/* 306 */       this.y++;
/* 307 */       if (this.y >= this.u) {
/* 308 */         p();
/*     */       } else {
/* 310 */         this.w = 0.0D;
/* 311 */         this.x += r() + this.v;
/*     */       } 
/* 313 */       this.z = new ArrayList();
/* 314 */       pageAdvance1 = maxAscent;
/*     */     } 
/*     */ 
/*     */     
/* 318 */     this.z.add(this.A);
/* 319 */     this.z.add(Boolean.valueOf(last));
/*     */ 
/*     */     
/* 322 */     this.w += pageAdvance1 + pageAdvance2;
/*     */   }
/*     */   
/*     */   public static int a(double a, double d1) {
/* 326 */     double diff = a - d1;
/* 327 */     if (diff < 0.1D && diff > -0.1D) {
/* 328 */       return 0;
/*     */     }
/* 330 */     return (a < d1) ? -1 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(c[] elements, boolean last) {
/* 335 */     double lineAxis, pageAxis, maxAscent = 0.0D, maxDescent = 0.0D;
/* 336 */     for (int j = 0; j < elements.length; j++) {
/* 337 */       c c1 = elements[j];
/* 338 */       if (c1.f_() == 1) {
/* 339 */         h text = (h)c1;
/* 340 */         maxAscent = Math.max(maxAscent, text.f());
/* 341 */         maxDescent = Math.max(maxDescent, text.g());
/*     */       } else {
/* 343 */         a control = (a)c1;
/* 344 */         maxAscent = Math.max(maxAscent, control.f());
/* 345 */         maxDescent = Math.max(maxDescent, control.g());
/*     */       } 
/*     */     } 
/* 348 */     if (this.J != 0.0D) {
/* 349 */       maxDescent = this.J - maxAscent;
/*     */     }
/*     */ 
/*     */     
/* 353 */     double lineMargin = (maxAscent + maxDescent) * (this.k - 1.0D) / 2.0D;
/* 354 */     double pageAdvance1 = maxAscent + lineMargin;
/* 355 */     double pageAdvance2 = maxDescent + lineMargin + this.B;
/* 356 */     this.w += pageAdvance1;
/*     */ 
/*     */ 
/*     */     
/* 360 */     switch (this.j) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 364 */         lineAxis = this.x;
/* 365 */         pageAxis = this.w;
/*     */         break;
/*     */       
/*     */       case 3:
/* 369 */         lineAxis = this.x;
/* 370 */         pageAxis = -this.w;
/*     */         break;
/*     */       default:
/* 373 */         throw new IllegalStateException();
/*     */     } 
/* 375 */     if (this.r == d || this.r == b.e) {
/* 376 */       double advance = 0.0D;
/* 377 */       for (int m = 0; m < elements.length; m++) {
/* 378 */         c c1 = elements[m];
/* 379 */         advance += c1.c();
/*     */       } 
/* 381 */       if (this.r == d) {
/* 382 */         lineAxis += r() - advance;
/*     */       } else {
/* 384 */         lineAxis += (r() - advance) / 2.0D;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 389 */     for (int k = 0; k < elements.length; k++) {
/* 390 */       c c1 = elements[k];
/* 391 */       if (this.i != null && c1.f_() == 1) {
/* 392 */         h text = (h)c1;
/* 393 */         switch (this.j) {
/*     */           
/*     */           case 1:
/*     */           case 2:
/* 397 */             this.i.a(text, lineAxis, pageAxis);
/*     */             break;
/*     */           
/*     */           case 3:
/* 401 */             this.i.a(text, pageAxis, lineAxis);
/*     */             break;
/*     */           default:
/* 404 */             throw new IllegalArgumentException();
/*     */         } 
/*     */       } 
/* 407 */       lineAxis += c1.c();
/*     */     } 
/*     */     
/* 410 */     this.w += pageAdvance2;
/* 411 */     this.n = Math.max(this.n, this.o = lineAxis);
/* 412 */     this.p = Math.max(this.p, this.w);
/*     */   }
/*     */   
/*     */   protected void p() throws c {
/* 416 */     this.w = this.x = 0.0D;
/* 417 */     this.y = 0;
/*     */   }
/*     */   
/*     */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/* 421 */     s();
/* 422 */     this.C = new i(charOffset, fontStyle, fontMetrics);
/* 423 */     this.C.a(this.E);
/*     */   }
/*     */   
/*     */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/* 427 */     this.F += this.C.a(ch, coff, clen, gid);
/* 428 */     this.F += this.E;
/* 429 */     this.H++;
/*     */   }
/*     */   
/*     */   public void a() {
/* 433 */     if (!g && this.C.l() <= 0) throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   private void s() {
/* 437 */     if (this.C != null) {
/* 438 */       this.C.n();
/* 439 */       this.D.add(this.C);
/* 440 */       this.G++;
/* 441 */       this.H = 0;
/* 442 */       this.C = null;
/*     */     } 
/*     */   }
/*     */   public void a(g quad) {
/*     */     c tab;
/* 447 */     a control = (a)quad;
/* 448 */     switch (control.e()) {
/*     */       case '\n':
/* 450 */         b(true);
/* 451 */         this.G = 0;
/* 452 */         this.H = 0;
/*     */         break;
/*     */ 
/*     */       
/*     */       case '\t':
/* 457 */         tab = (c)control;
/* 458 */         tab.a = 24.0D - this.F % 24.0D;
/* 459 */         if (this.F + tab.a > r()) {
/* 460 */           b(false);
/* 461 */           tab.a = 24.0D;
/*     */         } 
/*     */         break;
/*     */     } 
/* 465 */     s();
/* 466 */     this.D.add(quad);
/* 467 */     this.G++;
/* 468 */     this.F += quad.c();
/*     */   }
/*     */   
/*     */   public void b() {
/* 472 */     if (this.F > r()) {
/* 473 */       b(false);
/*     */     }
/* 475 */     this.G = 0;
/* 476 */     this.H = 0;
/*     */   }
/*     */   
/*     */   private void t() {
/* 480 */     if (this.I && this.u > 1)
/*     */     {
/* 482 */       this.B = (this.m - this.w) / (this.z.size() / 2 - 1);
/*     */     }
/*     */     
/* 485 */     List<Object> list = this.z;
/* 486 */     this.z = null;
/* 487 */     this.w = 0.0D;
/* 488 */     for (int j = 0; j < list.size(); j++) {
/* 489 */       c[] elements = (c[])list.get(j);
/* 490 */       Boolean last = (Boolean)list.get(++j);
/* 491 */       a(elements, last.booleanValue());
/*     */     } 
/* 493 */     this.B = 0.0D;
/*     */   }
/*     */   
/*     */   public void q() {
/* 497 */     b(true);
/* 498 */     t();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */