/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.a.f;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.a.i;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.b.a.a;
/*     */ import jp.cssj.homare.b.b.a.e;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class c
/*     */   extends b
/*     */   implements m, o, p
/*     */ {
/*  35 */   protected double f = 0.0D;
/*  36 */   protected double g = 0.0D;
/*  37 */   protected double h = 0.0D, i = Double.MAX_VALUE;
/*  38 */   protected double j = 0.0D; protected double k = 0.0D; protected m c; protected m d;
/*     */   protected b e;
/*     */   protected g l;
/*     */   
/*     */   protected c(m size, m minSize, g container) {
/*  43 */     if (!m && size == null) throw new AssertionError(); 
/*  44 */     this.c = size;
/*  45 */     this.d = minSize;
/*  46 */     this.l = container;
/*  47 */     container.a(this);
/*     */   }
/*     */   
/*     */   public final g e() {
/*  51 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract i c_();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(double newSize) {
/*  68 */     i params = c_();
/*  69 */     if (e.a(params.D)) {
/*     */       
/*  71 */       if (newSize <= this.f) {
/*     */         return;
/*     */       }
/*  74 */       this.f = Math.max(this.h, newSize);
/*  75 */       this.f = Math.min(this.i, this.f);
/*     */     } else {
/*     */       
/*  78 */       if (newSize <= this.g) {
/*     */         return;
/*     */       }
/*  81 */       this.g = Math.max(this.h, newSize);
/*  82 */       this.g = Math.min(this.i, this.g);
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
/*     */   public abstract boolean e_();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean g();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void a(n paramn, g paramg, Shape paramShape, AffineTransform paramAffineTransform, double paramDouble1, double paramDouble2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double h() {
/* 121 */     i params = c_();
/* 122 */     double lineSize = e.a(this);
/* 123 */     int columnCount = i();
/* 124 */     if (columnCount >= 2)
/*     */     {
/* 126 */       lineSize = (lineSize + params.ac.e) / columnCount - params.ac.e;
/*     */     }
/* 128 */     return lineSize;
/*     */   }
/*     */   
/*     */   public int i() {
/* 132 */     return 1;
/*     */   }
/*     */   
/*     */   public final boolean j() {
/* 136 */     int columnCount = i();
/* 137 */     if (columnCount < 2) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (e_()) {
/* 141 */       return true;
/*     */     }
/* 143 */     return (k() < columnCount);
/*     */   }
/*     */   
/*     */   protected int k() {
/* 147 */     if (this.l.a() == 1) {
/* 148 */       return 1;
/*     */     }
/* 150 */     f columns = (f)this.l;
/* 151 */     return columns.b();
/*     */   }
/*     */   
/*     */   public final boolean l() {
/* 155 */     if (i() <= 1) {
/* 156 */       return false;
/*     */     }
/* 158 */     i params = c_();
/* 159 */     if (e.a(params.D)) {
/* 160 */       if (this.c.a() == 3) {
/* 161 */         return false;
/*     */       }
/*     */     }
/* 164 */     else if (this.c.b() == 3) {
/* 165 */       return false;
/*     */     } 
/*     */     
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public final void a(a builder) {
/* 172 */     g oldCont = this.l;
/*     */     
/* 174 */     int acc = k();
/* 175 */     double pageSize = oldCont.h();
/*     */ 
/*     */     
/* 178 */     if (acc >= 2) {
/* 179 */       pageSize += this.f * (acc - 1);
/*     */     }
/* 181 */     pageSize /= i();
/* 182 */     pageSize = oldCont.a(pageSize);
/* 183 */     this.i = this.f = pageSize;
/*     */ 
/*     */     
/* 186 */     if (acc >= 2) {
/* 187 */       pageSize += this.g * (acc - 1);
/*     */     }
/* 189 */     pageSize /= i();
/* 190 */     pageSize = oldCont.a(pageSize);
/* 191 */     this.i = this.g = pageSize;
/*     */ 
/*     */     
/* 194 */     this.l = (g)new i();
/* 195 */     this.l.a(this);
/*     */     
/* 197 */     e columnBuilder = new e((d)builder, this);
/* 198 */     oldCont.a((a)columnBuilder, 0, true);
/*     */   }
/*     */   
/*     */   public final b m() {
/* 202 */     return this.e;
/*     */   }
/*     */   
/*     */   public final double n() {
/* 206 */     return this.l.f();
/*     */   }
/*     */   
/*     */   public final double o() {
/* 210 */     return this.l.g();
/*     */   }
/*     */   
/*     */   public final double p() {
/* 214 */     return this.f + this.e.f();
/*     */   }
/*     */   
/*     */   public final double q() {
/* 218 */     return this.g + this.e.e();
/*     */   }
/*     */   
/*     */   public final double r() {
/*     */     double textIndent;
/* 223 */     i params = c_();
/* 224 */     switch (params.h.a()) {
/*     */       case 1:
/* 226 */         textIndent = params.h.b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 234 */         return textIndent;case 2: textIndent = params.h.b() * h(); return textIndent;
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   } public final double s() {
/* 238 */     return this.f;
/*     */   }
/*     */   
/*     */   public final double t() {
/* 242 */     return this.g;
/*     */   }
/*     */   
/*     */   public final void a(l box, double pageAxis) {
/* 246 */     this.l.a(box, pageAxis);
/*     */   }
/*     */   
/*     */   public final void a(i box, double staticX, double staticY) {
/* 250 */     this.l.a(box, staticX, staticY);
/*     */   }
/*     */   
/*     */   public void a(k box, double lineAxis, double pageAxis) {
/* 254 */     this.l.a(box, lineAxis, pageAxis);
/*     */   }
/*     */   
/*     */   public void a(m containerBox) {
/* 258 */     this.l.a(containerBox);
/*     */   }
/*     */   
/*     */   protected final Shape a(Shape clip, double x, double y) {
/* 262 */     if ((c_()).ab != 1) {
/* 263 */       return clip;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 268 */     Rectangle2D.Double newClip = new Rectangle2D.Double(x + (this.e.a.c.d()).m + this.e.b.d, y + (this.e.a.c.a()).m + this.e.b.a, this.f + this.e.c.a(), this.g + this.e.c.b());
/* 269 */     if (clip == null) {
/* 270 */       return newClip;
/*     */     }
/* 272 */     return newClip.createIntersection((Rectangle2D)clip);
/*     */   }
/*     */   
/*     */   protected abstract c a(g paramg, double paramDouble, byte paramByte);
/*     */   
/*     */   public g a(double pageLimit, d mode, byte flags) {
/*     */     f columns;
/* 279 */     g newContainer = this.l.a(pageLimit, mode, flags);
/*     */     
/* 281 */     if (newContainer == null) {
/* 282 */       return null;
/*     */     }
/*     */     
/* 285 */     if (this.l.a() == 2) {
/* 286 */       columns = (f)this.l;
/* 287 */       if (newContainer == columns.c()) {
/* 288 */         return null;
/*     */       }
/*     */     } else {
/* 291 */       if (newContainer == this.l) {
/* 292 */         return null;
/*     */       }
/* 294 */       this.l = (g)(columns = new f((i)this.l));
/* 295 */       columns.a(this);
/*     */     } 
/* 297 */     if (e.a((c_()).D)) {
/* 298 */       this.f = pageLimit;
/*     */     } else {
/* 300 */       this.g = pageLimit;
/*     */     } 
/* 302 */     columns.k();
/* 303 */     return newContainer;
/*     */   }
/*     */   
/*     */   public p b(double pageLimit, d mode, byte flags) {
/* 307 */     if (e.a((c_()).D)) {
/* 308 */       pageLimit -= this.e.d();
/*     */     } else {
/* 310 */       pageLimit -= this.e.a();
/*     */     } 
/* 312 */     byte xflags = flags;
/* 313 */     if ((flags & 0x10) != 0 && i() > 1) {
/* 314 */       xflags = (byte)(xflags ^ 0x10);
/*     */     }
/* 316 */     g nextContainer = this.l.a(pageLimit, mode, xflags);
/* 317 */     if (nextContainer == null) {
/* 318 */       return null;
/*     */     }
/* 320 */     if (nextContainer == this.l) {
/* 321 */       return this;
/*     */     }
/* 323 */     return a(nextContainer, pageLimit, flags);
/*     */   }
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 327 */     this.l.a(textBuff);
/*     */   }
/*     */   
/*     */   public void a(a builder, int depth) {
/* 331 */     this.l.a(builder, depth, false);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */