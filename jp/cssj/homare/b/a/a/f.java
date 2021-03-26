/*     */ package jp.cssj.homare.b.a.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.k;
/*     */ import jp.cssj.homare.b.a.l;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.c.b;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ 
/*     */ 
/*     */ public class f
/*     */   implements g
/*     */ {
/*     */   protected class a
/*     */     extends b
/*     */   {
/*     */     protected final double a;
/*     */     protected final double b;
/*     */     
/*     */     public a(f this$0, n pageBox, Shape clip, float opacity, AffineTransform transform, double x, double y) {
/*  31 */       super(pageBox, clip, opacity, transform);
/*  32 */       this.a = x;
/*  33 */       this.b = y;
/*     */     }
/*     */     
/*     */     public void a(b gc, double x, double y) throws c {
/*  37 */       i params = this.c.b.c_();
/*  38 */       double columnSize = this.c.b.h() + params.ac.e;
/*  39 */       if (e.a(params.D)) {
/*  40 */         for (int i = 1; i < this.c.b(); i++) {
/*  41 */           y += columnSize;
/*  42 */           jp.cssj.homare.b.f.a.a.e(gc, params.ac.f, x, y - params.ac.e / 2.0D, this.c.b
/*  43 */               .s());
/*     */         } 
/*     */       } else {
/*  46 */         for (int i = 1; i < this.c.b(); i++) {
/*  47 */           x += columnSize;
/*  48 */           jp.cssj.homare.b.f.a.a.f(gc, params.ac.f, x - params.ac.e / 2.0D, y, this.c.b
/*  49 */               .t());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*  55 */   protected final List<g> a = new ArrayList<>();
/*     */   
/*     */   protected c b;
/*     */   
/*     */   public f(i container) {
/*  60 */     this.a.add(container);
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  64 */     return 2;
/*     */   }
/*     */   
/*     */   public int b() {
/*  68 */     return this.a.size();
/*     */   }
/*     */   
/*     */   public void a(c box) {
/*  72 */     this.b = box;
/*     */   }
/*     */   
/*     */   public final i c() {
/*  76 */     return (i)this.a.get(this.a.size() - 1);
/*     */   }
/*     */   
/*     */   public void a(l box, double pageAxis) {
/*  80 */     c().a(box, pageAxis);
/*     */   }
/*     */   
/*     */   public void a(k box, double lineAxis, double pageAxis) {
/*  84 */     c().a(box, lineAxis, pageAxis);
/*     */   }
/*     */   
/*     */   public void a(i box, double staticX, double staticY) {
/*  88 */     c().a(box, staticX, staticY);
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public boolean e() {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public double f() {
/* 100 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double g() {
/* 104 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double h() {
/* 108 */     return c().h();
/*     */   }
/*     */   
/*     */   public double a(double pageAxis) {
/* 112 */     i first = (i)this.a.get(0);
/* 113 */     return first.a(pageAxis);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/* 118 */     i params = this.b.c_();
/* 119 */     double columnSize = this.b.h() + params.ac.e;
/* 120 */     if (params.ac.f.a()) {
/* 121 */       a a = new a(this, pageBox, clip, params.ao, transform, x, y);
/* 122 */       drawer.a((jp.cssj.homare.b.c.f)a, x, y);
/*     */     } 
/* 124 */     if (e.a((this.b.c_()).D)) {
/* 125 */       for (int i = 0; i < this.a.size(); i++) {
/* 126 */         if (i >= 1) {
/* 127 */           y += columnSize;
/*     */         }
/* 129 */         i container = (i)this.a.get(i);
/* 130 */         container.a(pageBox, drawer, clip, transform, x, y);
/*     */       } 
/*     */     } else {
/* 133 */       for (int i = 0; i < this.a.size(); i++) {
/* 134 */         if (i >= 1) {
/* 135 */           x += columnSize;
/*     */         }
/* 137 */         i container = (i)this.a.get(i);
/* 138 */         container.a(pageBox, drawer, clip, transform, x, y);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 145 */     i params = this.b.c_();
/* 146 */     double columnSize = this.b.h() + params.ac.e;
/* 147 */     if (e.a((this.b.c_()).D)) {
/* 148 */       for (int i = 0; i < this.a.size(); i++) {
/* 149 */         if (i >= 1) {
/* 150 */           y += columnSize;
/*     */         }
/* 152 */         i container = (i)this.a.get(i);
/* 153 */         container.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } else {
/* 156 */       for (int i = 0; i < this.a.size(); i++) {
/* 157 */         if (i >= 1) {
/* 158 */           x += columnSize;
/*     */         }
/* 160 */         i container = (i)this.a.get(i);
/* 161 */         container.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 168 */     i params = this.b.c_();
/* 169 */     double columnSize = this.b.h() + params.ac.e;
/* 170 */     if (e.a((this.b.c_()).D)) {
/* 171 */       for (int i = 0; i < this.a.size(); i++) {
/* 172 */         if (i >= 1) {
/* 173 */           y += columnSize;
/*     */         }
/* 175 */         i container = (i)this.a.get(i);
/* 176 */         container.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } else {
/* 179 */       for (int i = 0; i < this.a.size(); i++) {
/* 180 */         if (i >= 1) {
/* 181 */           x += columnSize;
/*     */         }
/* 183 */         i container = (i)this.a.get(i);
/* 184 */         container.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 191 */     i params = this.b.c_();
/* 192 */     double columnSize = this.b.h() + params.ac.e;
/* 193 */     if (e.a((this.b.c_()).D)) {
/* 194 */       for (int i = 0; i < this.a.size(); i++) {
/* 195 */         if (i >= 1) {
/* 196 */           y += columnSize;
/*     */         }
/* 198 */         i container = (i)this.a.get(i);
/* 199 */         container.c(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } else {
/* 202 */       for (int i = 0; i < this.a.size(); i++) {
/* 203 */         if (i >= 1) {
/* 204 */           x += columnSize;
/*     */         }
/* 206 */         i container = (i)this.a.get(i);
/* 207 */         container.c(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(m containerBox) {
/* 213 */     for (int i = 0; i < this.a.size(); i++) {
/* 214 */       i container = (i)this.a.get(i);
/* 215 */       container.a(containerBox);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(StringBuffer textBuff) {
/* 220 */     for (int i = 0; i < this.a.size(); i++) {
/* 221 */       i container = (i)this.a.get(i);
/* 222 */       container.a(textBuff);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 227 */     for (int i = 0; i < this.a.size(); i++) {
/* 228 */       i container = (i)this.a.get(i);
/* 229 */       if (container.i()) {
/* 230 */         return true;
/*     */       }
/*     */     } 
/* 233 */     return false;
/*     */   }
/*     */   
/*     */   public boolean j() {
/* 237 */     for (int i = 0; i < this.a.size(); i++) {
/* 238 */       i container = (i)this.a.get(i);
/* 239 */       if (container.j()) {
/* 240 */         return true;
/*     */       }
/*     */     } 
/* 243 */     return false;
/*     */   }
/*     */   
/*     */   public g a(double pageLimit, d mode, byte flags) {
/* 247 */     return c().a(pageLimit, mode, flags);
/*     */   }
/*     */   
/*     */   public g a(g nextBox, double pageLimit, byte flags) {
/* 251 */     return nextBox;
/*     */   }
/*     */   
/*     */   public h a(double pageLimit, byte flags) {
/* 255 */     return null;
/*     */   }
/*     */   
/*     */   public void k() {
/* 259 */     g container = new i();
/* 260 */     container.a(this.b);
/* 261 */     this.a.add(container);
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.homare.b.b.a.a builder, int depth, boolean restyleAbsolutes) {
/* 265 */     for (int i = 0; i < this.a.size(); i++) {
/* 266 */       i container = (i)this.a.get(i);
/* 267 */       container.a(builder, depth, restyleAbsolutes);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */