/*     */ package jp.cssj.homare.impl.ua;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import jp.cssj.homare.b.d.a;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.c.c;
/*     */ 
/*     */ public class e
/*     */   extends a {
/*     */   protected b a;
/*     */   protected double b;
/*     */   protected double c;
/*     */   
/*     */   public e(m ua) {
/*  21 */     super(ua);
/*     */   }
/*     */   public b a() throws c {
/*     */     double actualPaperWidth, actualPaperHeight, maxWidth, maxHeight, hscale, vscale;
/*  25 */     this.e++;
/*     */     
/*  27 */     switch (this.i) {
/*     */       case 1:
/*  29 */         actualPaperWidth = this.t;
/*  30 */         actualPaperHeight = this.u;
/*  31 */         this.a = this.d.a(actualPaperWidth, actualPaperHeight);
/*  32 */         this.a.d();
/*     */         break;
/*     */       case 2:
/*  35 */         this.a = this.d.a(this.t, this.u);
/*  36 */         this.a.d();
/*  37 */         if (((this.t > this.u) ? true : false) != ((this.r > this.s) ? true : false)) {
/*  38 */           actualPaperWidth = this.u;
/*  39 */           actualPaperHeight = this.t;
/*  40 */           AffineTransform affineTransform = AffineTransform.getRotateInstance(-1.5707963267948966D);
/*  41 */           affineTransform.translate(-this.u, 0.0D);
/*  42 */           this.a.a(affineTransform); break;
/*     */         } 
/*  44 */         actualPaperWidth = this.t;
/*  45 */         actualPaperHeight = this.u;
/*     */         break;
/*     */       
/*     */       case 3:
/*  49 */         if (((this.t > this.u) ? true : false) != ((this.r > this.s) ? true : false)) {
/*  50 */           actualPaperWidth = this.u;
/*  51 */           actualPaperHeight = this.t;
/*     */         } else {
/*  53 */           actualPaperWidth = this.t;
/*  54 */           actualPaperHeight = this.u;
/*     */         } 
/*  56 */         this.a = this.d.a(actualPaperWidth, actualPaperHeight);
/*  57 */         this.a.d();
/*     */         break;
/*     */       default:
/*  60 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/*  63 */     switch (this.h) {
/*     */       case 1:
/*  65 */         this.b = this.r;
/*  66 */         this.c = this.s;
/*     */         break;
/*     */       case 2:
/*  69 */         this.b = actualPaperWidth - this.n - this.m;
/*  70 */         this.c = actualPaperHeight - this.l - this.o;
/*     */         break;
/*     */       case 3:
/*  73 */         this.b = this.r;
/*  74 */         this.c = this.s;
/*  75 */         maxWidth = actualPaperWidth - this.n - this.m;
/*  76 */         maxHeight = actualPaperHeight - this.l - this.o;
/*  77 */         if (this.b != maxWidth) {
/*  78 */           this.c = this.c * maxWidth / this.b;
/*  79 */           this.b = maxWidth;
/*     */         } 
/*  81 */         if (this.c > maxHeight) {
/*  82 */           this.b = this.b * maxHeight / this.c;
/*  83 */           this.c = maxHeight;
/*     */         } 
/*     */         break;
/*     */       
/*     */       default:
/*  88 */         throw new IllegalStateException();
/*     */     } 
/*  90 */     AffineTransform at = AffineTransform.getTranslateInstance((actualPaperWidth - this.b - this.n - this.m) / 2.0D, (actualPaperHeight - this.c - this.l - this.o) / 2.0D);
/*     */ 
/*     */     
/*  93 */     this.a.a(at);
/*     */ 
/*     */     
/*  96 */     c();
/*  97 */     a(this.n, 0.0D);
/*     */ 
/*     */     
/* 100 */     if (this.n != 0.0D || this.l != 0.0D) {
/* 101 */       this.a.a(AffineTransform.getTranslateInstance(this.n, this.l));
/*     */     }
/*     */ 
/*     */     
/* 105 */     double bgX = -this.p;
/* 106 */     double bgY = -this.p;
/* 107 */     double bgW = this.r + this.p * 2.0D;
/* 108 */     double bgH = this.s + this.p * 2.0D;
/*     */     
/* 110 */     switch (this.h) {
/*     */       
/*     */       case 1:
/* 113 */         if (this.w) {
/* 114 */           this.a.a(new Rectangle2D.Double(bgX, bgY, bgW, bgH));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 149 */         return this.a;case 2: case 3: if (this.r != 0.0D) { hscale = this.b / this.r; } else { hscale = 0.0D; }  if (this.s != 0.0D) { vscale = this.c / this.s; } else { vscale = 0.0D; }  if (this.w) this.a.a(new Rectangle2D.Double(bgX, bgY, bgW * hscale, bgH * vscale));  if (hscale != 0.0D && vscale != 0.0D) this.a.a(AffineTransform.getScaleInstance(hscale, vscale));  return this.a;
/*     */     } 
/*     */     throw new IllegalArgumentException();
/*     */   } public void b() throws c {
/* 153 */     this.a.e();
/*     */     try {
/* 155 */       this.d.a(this.a);
/* 156 */     } catch (IOException iOException) {
/* 157 */       throw new c(iOException);
/*     */     } finally {
/* 159 */       this.a = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void a(double x, double y) throws c {
/* 165 */     if (this.v == null) {
/*     */       return;
/*     */     }
/* 168 */     double paperWidth = this.b + this.n + this.m;
/*     */     
/* 170 */     String text = this.v.format(new Object[] { String.valueOf(this.e) });
/* 171 */     double fontSize = this.l / 6.0D;
/* 172 */     y = y + this.l - this.p - fontSize;
/* 173 */     double width = paperWidth / 2.0D - this.n;
/* 174 */     jp.cssj.homare.b.f.e.a(this.a, (g)this.d.j(), fontSize, text, x, y, width);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void c() throws c {
/* 179 */     if (this.j) {
/* 180 */       d();
/*     */     }
/* 182 */     if (this.k) {
/* 183 */       e();
/*     */     }
/*     */ 
/*     */     
/* 187 */     if (this.q > 0.0D) {
/* 188 */       double middle = this.b / 2.0D + this.n;
/* 189 */       double bouter = this.c + this.l + this.p;
/* 190 */       double touter = this.l - this.p;
/* 191 */       double bottom = this.c + this.l + this.o;
/* 192 */       double lbc = middle - this.q / 2.0D;
/* 193 */       double rbc = middle + this.q / 2.0D;
/*     */       
/* 195 */       this.a.c(new Line2D.Double(lbc, 0.0D, lbc, touter));
/* 196 */       this.a.c(new Line2D.Double(rbc, 0.0D, rbc, touter));
/* 197 */       this.a.c(new Line2D.Double(lbc, bottom, lbc, bouter));
/* 198 */       this.a.c(new Line2D.Double(rbc, bottom, rbc, bouter));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void d() throws c {
/* 204 */     this.a.a(c.b);
/* 205 */     this.a.a(0.30000001192092896D);
/* 206 */     this.a.a(b.t);
/*     */     
/* 208 */     double louter = this.n - this.p;
/* 209 */     double touter = this.l - this.p;
/* 210 */     double router = this.b + this.n + this.p;
/* 211 */     double rinner = this.b + this.n;
/* 212 */     double right = this.b + this.n + this.m;
/* 213 */     double bouter = this.c + this.l + this.p;
/* 214 */     double binner = this.c + this.l;
/* 215 */     double bottom = this.c + this.l + this.o;
/*     */ 
/*     */     
/* 218 */     this.a.c(new Line2D.Double(0.0D, touter, louter, touter));
/* 219 */     this.a.c(new Line2D.Double(0.0D, this.l, louter, this.l));
/* 220 */     this.a.c(new Line2D.Double(louter, 0.0D, louter, touter));
/* 221 */     this.a.c(new Line2D.Double(this.n, 0.0D, this.n, touter));
/*     */ 
/*     */     
/* 224 */     this.a.c(new Line2D.Double(router, touter, right, touter));
/* 225 */     this.a.c(new Line2D.Double(router, this.l, right, this.l));
/* 226 */     this.a.c(new Line2D.Double(router, 0.0D, router, touter));
/* 227 */     this.a.c(new Line2D.Double(rinner, 0.0D, rinner, touter));
/*     */ 
/*     */     
/* 230 */     this.a.c(new Line2D.Double(0.0D, bouter, louter, bouter));
/* 231 */     this.a.c(new Line2D.Double(0.0D, binner, louter, binner));
/* 232 */     this.a.c(new Line2D.Double(louter, bottom, louter, bouter));
/* 233 */     this.a.c(new Line2D.Double(this.n, bottom, this.n, bouter));
/*     */ 
/*     */     
/* 236 */     this.a.c(new Line2D.Double(router, bouter, right, bouter));
/* 237 */     this.a.c(new Line2D.Double(router, binner, right, binner));
/* 238 */     this.a.c(new Line2D.Double(router, bottom, router, bouter));
/* 239 */     this.a.c(new Line2D.Double(rinner, bottom, rinner, bouter));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void e() throws c {
/* 245 */     this.a.a(c.b);
/* 246 */     this.a.a(0.30000001192092896D);
/* 247 */     this.a.a(b.t);
/*     */ 
/*     */     
/* 250 */     double middle = this.b / 2.0D + this.n;
/* 251 */     double d2 = middle - (this.n + this.m) / 2.0D;
/* 252 */     double rmiddle = middle + (this.n + this.m) / 2.0D;
/* 253 */     double d3 = this.l - this.p * 2.0D;
/* 254 */     double touter = this.l - this.p;
/* 255 */     double d5 = this.c + this.l + this.p * 2.0D;
/* 256 */     double bouter = this.c + this.l + this.p;
/* 257 */     double bottom = this.c + this.l + this.o;
/*     */ 
/*     */     
/* 260 */     this.a.c(new Line2D.Double(d2, d3, rmiddle, d3));
/* 261 */     this.a.c(new Line2D.Double(middle, 0.0D, middle, touter));
/*     */ 
/*     */     
/* 264 */     this.a.c(new Line2D.Double(d2, d5, rmiddle, d5));
/* 265 */     this.a.c(new Line2D.Double(middle, bottom, middle, bouter));
/*     */ 
/*     */ 
/*     */     
/* 269 */     double lmiddle = this.n - this.p * 2.0D;
/* 270 */     double d1 = this.c / 2.0D + this.l;
/* 271 */     double tmiddle = d1 - (this.l + this.o) / 2.0D;
/* 272 */     double bmiddle = d1 + (this.l + this.o) / 2.0D;
/* 273 */     double louter = this.n - this.p;
/* 274 */     double d4 = this.b + this.n + this.p * 2.0D;
/* 275 */     double router = this.b + this.n + this.p;
/* 276 */     double right = this.b + this.n + this.m;
/*     */ 
/*     */     
/* 279 */     this.a.c(new Line2D.Double(lmiddle, tmiddle, lmiddle, bmiddle));
/* 280 */     this.a.c(new Line2D.Double(0.0D, d1, louter, d1));
/*     */ 
/*     */     
/* 283 */     this.a.c(new Line2D.Double(d4, tmiddle, d4, bmiddle));
/* 284 */     this.a.c(new Line2D.Double(right, d1, router, d1));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */