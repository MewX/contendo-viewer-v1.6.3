/*     */ package jp.cssj.homare.css.f;
/*     */ 
/*     */ import jp.cssj.homare.css.e.f;
/*     */ import jp.cssj.homare.ua.m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class b
/*     */   extends a
/*     */ {
/*     */   private final m i;
/*     */   private short j;
/*     */   private double k;
/*     */   
/*     */   b(m ua, short unitType, double length) {
/*  18 */     switch (unitType) {
/*     */       case 18:
/*  20 */         a(length);
/*     */         break;
/*     */       
/*     */       case 19:
/*  24 */         b(length);
/*     */         break;
/*     */       
/*     */       case 20:
/*  28 */         c(length);
/*     */         break;
/*     */       
/*     */       case 21:
/*  32 */         d(length);
/*     */         break;
/*     */       
/*     */       case 22:
/*  36 */         e(length);
/*     */         break;
/*     */       
/*     */       case 17:
/*  40 */         f(length);
/*     */         break;
/*     */       
/*     */       default:
/*  44 */         throw new IllegalArgumentException();
/*     */     } 
/*  46 */     this.i = ua;
/*     */   }
/*     */   
/*     */   b(m ua, double length) {
/*  50 */     this(ua, (short)21, length);
/*     */   }
/*     */   
/*     */   public short b() {
/*  54 */     return this.j;
/*     */   }
/*     */   
/*     */   public double a(short unitType) {
/*  58 */     switch (unitType) {
/*     */       case 18:
/*  60 */         return f();
/*     */       
/*     */       case 19:
/*  63 */         return g();
/*     */       
/*     */       case 20:
/*  66 */         return h();
/*     */       
/*     */       case 21:
/*  69 */         return i();
/*     */       
/*     */       case 22:
/*  72 */         return j();
/*     */       
/*     */       case 17:
/*  75 */         return k();
/*     */     } 
/*     */     
/*  78 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double c() {
/*  88 */     return a((short)21);
/*     */   }
/*     */   
/*     */   protected void a(double inches) {
/*  92 */     this.k = inches;
/*  93 */     this.j = 18;
/*     */   }
/*     */   
/*     */   protected void b(double centimeters) {
/*  97 */     this.k = centimeters;
/*  98 */     this.j = 19;
/*     */   }
/*     */   
/*     */   protected void c(double millimeters) {
/* 102 */     this.k = millimeters;
/* 103 */     this.j = 20;
/*     */   }
/*     */   
/*     */   protected void d(double points) {
/* 107 */     this.k = points;
/* 108 */     this.j = 21;
/*     */   }
/*     */   
/*     */   protected void e(double picas) {
/* 112 */     this.k = picas;
/* 113 */     this.j = 22;
/*     */   }
/*     */   
/*     */   protected void f(double pixels) {
/* 117 */     this.k = pixels;
/* 118 */     this.j = 17;
/*     */   }
/*     */   
/*     */   protected double f() {
/* 122 */     return f.a(this.i, this.k, this.j, (short)18);
/*     */   }
/*     */   
/*     */   protected double g() {
/* 126 */     return f.a(this.i, this.k, this.j, (short)19);
/*     */   }
/*     */   
/*     */   protected double h() {
/* 130 */     return f.a(this.i, this.k, this.j, (short)20);
/*     */   }
/*     */   
/*     */   protected double i() {
/* 134 */     return f.a(this.i, this.k, this.j, (short)21);
/*     */   }
/*     */   
/*     */   protected double j() {
/* 138 */     return f.a(this.i, this.k, this.j, (short)22);
/*     */   }
/*     */   
/*     */   protected double k() {
/* 142 */     return f.a(this.i, this.k, this.j, (short)17);
/*     */   }
/*     */   
/*     */   public int a(a o) {
/* 146 */     a length = o;
/* 147 */     double d1 = a((short)20);
/* 148 */     double d2 = length.a((short)20);
/* 149 */     return (d1 == d2) ? 0 : ((d1 > d2) ? 1 : -1);
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 153 */     return (this.k < 0.0D);
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 157 */     return (this.k == 0.0D);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */