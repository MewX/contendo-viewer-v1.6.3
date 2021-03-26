/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.a.i;
/*     */ import jp.cssj.homare.b.a.c.B;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.x;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.k;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.ua.m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class n
/*     */   extends a
/*     */ {
/*     */   protected final m n;
/*     */   protected List<a> o;
/*     */   protected List<a> I;
/*     */   protected List<i> J;
/*     */   protected double K;
/*     */   protected double L;
/*     */   
/*     */   protected static class a
/*     */   {
/*     */     public final i a;
/*     */     public final double b;
/*     */     public final double c;
/*     */     
/*     */     public a(i box, double x, double y) {
/*  50 */       this.a = box;
/*  51 */       this.b = x;
/*  52 */       this.c = y;
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
/*     */   public n(i params, m ua) {
/*  71 */     this(params, ua, (g)new i());
/*     */   }
/*     */   
/*     */   public n(i params, m ua, g container) {
/*  75 */     super(params, params.X, params.Y, new b(params.S), container); double lineWidth, top, right, bottom, left; this.o = null; this.I = null; this.J = null; this.K = 0.0D; this.L = 0.0D;
/*  76 */     if (!M && this.c.a() == 2) throw new AssertionError(); 
/*  77 */     if (!M && this.c.b() == 2) throw new AssertionError();
/*     */     
/*  79 */     this.n = ua;
/*     */ 
/*     */     
/*  82 */     switch (params.D) {
/*     */       
/*     */       case 1:
/*  85 */         if (!M && this.c.a() != 1) throw new AssertionError(); 
/*  86 */         lineWidth = this.c.c();
/*     */         break;
/*     */       
/*     */       case 2:
/*     */       case 3:
/*  91 */         if (!M && this.c.b() != 1) throw new AssertionError(); 
/*  92 */         lineWidth = this.c.d();
/*     */         break;
/*     */       default:
/*  95 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/*  98 */     B frame = this.e.a;
/*     */     
/* 100 */     t insets = frame.b;
/*     */     
/* 102 */     switch (insets.a()) {
/*     */       case 1:
/* 104 */         top = insets.f();
/*     */         break;
/*     */       case 2:
/* 107 */         top = insets.f() * lineWidth;
/*     */         break;
/*     */       case 3:
/* 110 */         top = 0.0D;
/*     */         break;
/*     */       default:
/* 113 */         throw new IllegalStateException();
/*     */     } 
/* 115 */     switch (insets.c()) {
/*     */       case 1:
/* 117 */         bottom = insets.h();
/*     */         break;
/*     */       case 2:
/* 120 */         bottom = insets.h() * lineWidth;
/*     */         break;
/*     */       case 3:
/* 123 */         bottom = 0.0D;
/*     */         break;
/*     */       default:
/* 126 */         throw new IllegalStateException();
/*     */     } 
/* 128 */     switch (insets.d()) {
/*     */       case 1:
/* 130 */         left = insets.i();
/*     */         break;
/*     */       case 2:
/* 133 */         left = insets.i() * lineWidth;
/*     */         break;
/*     */       case 3:
/* 136 */         left = 0.0D;
/*     */         break;
/*     */       default:
/* 139 */         throw new IllegalStateException();
/*     */     } 
/* 141 */     switch (insets.b()) {
/*     */       case 1:
/* 143 */         right = insets.g();
/*     */         break;
/*     */       case 2:
/* 146 */         right = insets.g() * lineWidth;
/*     */         break;
/*     */       case 3:
/* 149 */         right = 0.0D;
/*     */         break;
/*     */       default:
/* 152 */         throw new IllegalStateException();
/*     */     } 
/* 154 */     this.e.b.a = top;
/* 155 */     this.e.b.b = right;
/* 156 */     this.e.b.c = bottom;
/* 157 */     this.e.b.d = left;
/* 158 */     if (this.c.a() == 1) {
/* 159 */       this.K = this.f = this.c.c() - this.e.f();
/*     */     }
/* 161 */     if (this.c.b() == 1) {
/* 162 */       this.L = this.g = this.c.d() - this.e.e();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final byte a() {
/* 168 */     return 1;
/*     */   }
/*     */   
/*     */   public final z b_() {
/* 172 */     return (z)x.a;
/*     */   }
/*     */   
/*     */   public final m u() {
/* 176 */     return this.n;
/*     */   }
/*     */   
/*     */   public final boolean e_() {
/* 180 */     return false;
/*     */   }
/*     */   
/*     */   public final void a(k box, double lineAxis, double pageAxis) {
/* 184 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public final void a(double newSize) {
/* 188 */     if (!M && e.a(newSize)) throw new AssertionError(); 
/* 189 */     i params = c_();
/* 190 */     switch (params.D) {
/*     */       
/*     */       case 1:
/* 193 */         this.L = Math.max(this.L, newSize);
/* 194 */         if (this.c.b() != 3 || newSize <= this.g) {
/*     */           return;
/*     */         }
/* 197 */         this.g = Math.max(this.h, newSize);
/* 198 */         this.g = Math.min(this.i, this.g);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 204 */         this.K = Math.max(this.K, newSize);
/* 205 */         if (this.c.a() != 3 || newSize <= this.f) {
/*     */           return;
/*     */         }
/* 208 */         this.f = Math.max(this.h, newSize);
/* 209 */         this.f = Math.min(this.i, this.f);
/*     */         return;
/*     */     } 
/*     */     
/* 213 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public double v() {
/* 218 */     return this.K + this.e.f();
/*     */   }
/*     */   
/*     */   public double w() {
/* 222 */     return this.L + this.e.e();
/*     */   }
/*     */   
/*     */   public final void a(g drawer, jp.cssj.homare.b.g.a visitor, i box, double x, double y) {
/* 226 */     jp.cssj.homare.b.a.c.a pos = box.c();
/* 227 */     if (pos.a.d() != 3 || pos.a.b() != 3) {
/* 228 */       x = 0.0D;
/*     */     }
/* 230 */     if (pos.a.a() != 3 || pos.a.c() != 3) {
/* 231 */       y = 0.0D;
/*     */     }
/* 233 */     box.a((m)this);
/* 234 */     a fixed = new a(box, x, y);
/* 235 */     if (this.I == null) {
/* 236 */       this.I = new ArrayList<>();
/*     */     }
/* 238 */     this.I.add(fixed);
/*     */     
/* 240 */     x = this.j + this.e.b() - this.e.b.d;
/* 241 */     y = this.k + this.e.a() - this.e.b.a;
/* 242 */     fixed.a.a(this, drawer, visitor, null, new AffineTransform(), x, y, fixed.b, fixed.c);
/*     */   }
/*     */   
/*     */   public final void a(i box) {
/* 246 */     if (this.J == null) {
/* 247 */       this.J = new ArrayList<>();
/*     */     }
/* 249 */     this.J.add(box);
/*     */   }
/*     */   
/*     */   public final boolean g() {
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public final p b(double pageLimit, d mode, byte flags) {
/* 257 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final a a(m nextSize, m nextMinSize, b nextFrame, g container) {
/* 262 */     return new n(this.a, this.n, container);
/*     */   }
/*     */   
/*     */   public final void a(g drawer, jp.cssj.homare.b.g.a visitor) {
/* 266 */     double x = -this.e.b.d;
/* 267 */     double y = -this.e.b.a;
/* 268 */     a(this, drawer, null, new AffineTransform(), x, y);
/* 269 */     a(this, drawer, visitor, null, new AffineTransform(), x, y, x, y);
/*     */   }
/*     */   
/*     */   public final void b(g drawer, jp.cssj.homare.b.g.a visitor) {
/* 273 */     double x = this.j + this.e.b() - this.e.b.d;
/* 274 */     double y = this.k + this.e.a() - this.e.b.a;
/* 275 */     if (this.o != null) {
/* 276 */       for (int i = 0; i < this.o.size(); i++) {
/* 277 */         a c = this.o.get(i);
/* 278 */         c.a.a(this, drawer, visitor, null, new AffineTransform(), x, y, c.b, c.c);
/*     */       } 
/*     */     }
/* 281 */     if (this.I != null && !this.I.isEmpty()) {
/* 282 */       if (this.o == null) {
/* 283 */         this.o = new ArrayList<>();
/*     */       }
/* 285 */       this.o.addAll(this.I);
/* 286 */       this.I.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void c(g drawer, jp.cssj.homare.b.g.a visitor) {
/* 291 */     if (this.J == null) {
/*     */       return;
/*     */     }
/* 294 */     double x = this.j + this.e.b() - this.e.b.d;
/* 295 */     double y = this.k + this.e.a() - this.e.b.a;
/* 296 */     for (int i = 0; i < this.J.size(); i++) {
/* 297 */       j box = (j)this.J.get(i);
/* 298 */       box.a(this, drawer, visitor, null, new AffineTransform(), x, y, x, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(jp.cssj.homare.b.b.a.a builder, int depth) {
/* 303 */     if (this.o == null) {
/*     */       return;
/*     */     }
/* 306 */     for (int i = 0; i < this.o.size(); i++) {
/* 307 */       a fixed = this.o.get(i);
/* 308 */       builder.a((j)fixed.a);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */