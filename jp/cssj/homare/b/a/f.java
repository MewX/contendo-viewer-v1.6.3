/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a.l;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c.C;
/*     */ import jp.cssj.homare.b.a.c.e;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class f
/*     */   extends b
/*     */ {
/*  34 */   protected double c = 0.0D;
/*     */   
/*  36 */   protected double d = 0.0D;
/*     */   
/*  38 */   protected double e = 0.0D;
/*     */   
/*  40 */   protected double f = 0.0D; protected final C a;
/*     */   
/*     */   public f(C params) {
/*  43 */     this.a = params;
/*  44 */     this.b = new b(params.f);
/*     */   }
/*     */   protected final b b;
/*     */   public final byte a() {
/*  48 */     return 6;
/*     */   }
/*     */   
/*     */   public final y b() {
/*  52 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public final C d_() {
/*  56 */     return this.a;
/*     */   }
/*     */   
/*     */   public final b m() {
/*  60 */     return this.b;
/*     */   }
/*     */   
/*     */   public final double p() {
/*  64 */     return this.c + this.b.f();
/*     */   }
/*     */   
/*     */   public final double q() {
/*  68 */     return this.d + this.b.e();
/*     */   }
/*     */   
/*     */   public final double s() {
/*  72 */     return this.c;
/*     */   }
/*     */   
/*     */   public final double t() {
/*  76 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(double lineAxis) {
/*  83 */     e.b(this.b.c, this.b.a.e, lineAxis);
/*     */ 
/*     */ 
/*     */     
/*  87 */     e.a(this.b.b, this.b.a.b, lineAxis);
/*     */   }
/*     */   
/*     */   public final void a(double refWidth, double refHeight, double refMaxWidth, double refMaxHeight) {
/*  91 */     double width = e.a(this.a.b, refWidth);
/*  92 */     double height = e.b(this.a.b, refHeight);
/*     */     
/*  94 */     if (this.a.a instanceof l) {
/*  95 */       ((l)this.a.a).a(this, width, height);
/*     */     }
/*     */     
/*  98 */     if (e.a(width) && e.a(height)) {
/*     */       
/* 100 */       width = this.a.a.a();
/* 101 */       height = this.a.a.b();
/* 102 */     } else if (e.a(width)) {
/*     */       
/* 104 */       if (this.a.e == 2) {
/* 105 */         height -= this.b.g();
/*     */       }
/* 107 */       double intrinsicWidth = this.a.a.a();
/* 108 */       double intrinsicHeight = this.a.a.b();
/* 109 */       if (intrinsicHeight != 0.0D) {
/* 110 */         width = intrinsicWidth * height / intrinsicHeight;
/*     */       } else {
/*     */         
/* 113 */         width = 0.0D;
/*     */       } 
/* 115 */     } else if (e.a(height)) {
/*     */       
/* 117 */       if (this.a.e == 2) {
/* 118 */         width -= this.b.h();
/*     */       }
/* 120 */       double intrinsicHeight = this.a.a.b();
/* 121 */       double intrinsicWidth = this.a.a.a();
/* 122 */       if (intrinsicWidth != 0.0D) {
/* 123 */         height = intrinsicHeight * width / intrinsicWidth;
/*     */       } else {
/*     */         
/* 126 */         height = 0.0D;
/*     */       } 
/* 128 */     } else if (this.a.e == 2) {
/* 129 */       width -= this.b.h();
/* 130 */       height -= this.b.g();
/*     */     } 
/*     */     
/* 133 */     if (!g && e.a(width)) throw new AssertionError(); 
/* 134 */     if (!g && e.a(height)) throw new AssertionError();
/*     */ 
/*     */     
/* 137 */     double maxWidth = e.a(this.a.d, refMaxWidth);
/* 138 */     double minWidth = e.a(this.a.c, refWidth);
/* 139 */     double maxHeight = e.b(this.a.d, refMaxHeight);
/* 140 */     double minHeight = e.b(this.a.c, refHeight);
/* 141 */     if (e.a(maxWidth)) {
/* 142 */       maxWidth = Double.MAX_VALUE;
/* 143 */     } else if (this.a.e == 2) {
/* 144 */       maxWidth -= this.b.h();
/*     */     } 
/*     */     
/* 147 */     if (e.a(maxHeight)) {
/* 148 */       maxHeight = Double.MAX_VALUE;
/* 149 */     } else if (this.a.e == 2) {
/* 150 */       maxHeight -= this.b.g();
/*     */     } 
/* 152 */     maxWidth = Math.max(minWidth, maxWidth);
/* 153 */     maxHeight = Math.max(minHeight, maxHeight);
/*     */     
/* 155 */     if (width > maxWidth) {
/* 156 */       if (height > maxHeight) {
/* 157 */         if (maxWidth / width <= maxHeight / height) {
/*     */           
/* 159 */           height = Math.max(minHeight, maxWidth * height / width);
/* 160 */           width = maxWidth;
/*     */         } else {
/*     */           
/* 163 */           width = Math.max(minWidth, maxHeight * width / height);
/* 164 */           height = maxHeight;
/*     */         } 
/* 166 */       } else if (height < minHeight) {
/*     */         
/* 168 */         width = maxWidth;
/* 169 */         height = minWidth;
/*     */       } else {
/*     */         
/* 172 */         height = Math.max(maxWidth * height / width, minHeight);
/* 173 */         width = maxWidth;
/*     */       } 
/* 175 */     } else if (width < minWidth) {
/* 176 */       if (height < minHeight) {
/* 177 */         if (minWidth / width <= minHeight / height) {
/*     */           
/* 179 */           if (height != 0.0D) {
/* 180 */             width = Math.min(maxWidth, minHeight * width / height);
/*     */           } else {
/* 182 */             width = minWidth;
/*     */           } 
/* 184 */           height = minHeight;
/*     */         } else {
/*     */           
/* 187 */           if (width != 0.0D) {
/* 188 */             height = Math.min(maxHeight, minWidth * height / width);
/*     */           } else {
/* 190 */             height = minHeight;
/*     */           } 
/* 192 */           width = minWidth;
/*     */         } 
/* 194 */       } else if (height > maxHeight) {
/*     */         
/* 196 */         width = minWidth;
/* 197 */         height = maxHeight;
/*     */       } else {
/*     */         
/* 200 */         if (width != 0.0D) {
/* 201 */           height = Math.min(minWidth * height / width, maxHeight);
/*     */         } else {
/* 203 */           height = minHeight;
/*     */         } 
/* 205 */         width = minWidth;
/*     */       } 
/* 207 */     } else if (height > maxHeight) {
/*     */       
/* 209 */       width = Math.max(maxHeight * width / height, minWidth);
/* 210 */       height = maxHeight;
/* 211 */     } else if (height < minHeight) {
/*     */       
/* 213 */       if (height != 0.0D) {
/* 214 */         width = Math.min(minHeight * width / height, maxWidth);
/*     */       } else {
/* 216 */         width = minWidth;
/*     */       } 
/* 218 */       height = minHeight;
/*     */     } 
/* 220 */     this.c = width;
/* 221 */     this.d = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(m containerBox) {
/* 226 */     e pos = (e)b_();
/* 227 */     if (pos.d != null) {
/*     */ 
/*     */ 
/*     */       
/* 231 */       this.e = e.a(pos.d, containerBox);
/* 232 */       this.f = e.b(pos.d, containerBox);
/*     */     } 
/*     */     
/* 235 */     if (!g && e.a(this.c)) throw new AssertionError(); 
/* 236 */     if (!g && e.a(this.d)) throw new AssertionError(); 
/* 237 */     if (!g && e.a(this.e)) throw new AssertionError("Undefined offsetX"); 
/* 238 */     if (!g && e.a(this.f)) throw new AssertionError("Undefined offsetY"); 
/*     */   }
/*     */   
/*     */   protected static class a
/*     */     extends jp.cssj.homare.b.c.a {
/*     */     protected final b a;
/*     */     
/*     */     public a(n pageBox, Shape clip, float opacity, AffineTransform transform, b frame, b image, double width, double height) {
/* 246 */       super(pageBox, clip, opacity, transform, frame, width, height);
/* 247 */       this.a = image;
/*     */     }
/*     */     
/*     */     public void a(b gc, double x, double y) throws c {
/* 251 */       super.a(gc, x, y);
/* 252 */       x += this.b.b();
/* 253 */       y += this.b.a();
/* 254 */       double width = this.c - this.b.f();
/* 255 */       double height = this.d - this.b.e();
/* 256 */       if (width > 0.0D && height > 0.0D) {
/* 257 */         double imageWidth = this.a.a();
/* 258 */         double imageHeight = this.a.b();
/* 259 */         AffineTransform at = AffineTransform.getTranslateInstance(x, y);
/* 260 */         at.scale(width / imageWidth, height / imageHeight);
/* 261 */         gc.d();
/*     */         
/* 263 */         if (this.b.a.c.m()) {
/* 264 */           Shape shape = jp.cssj.homare.b.f.a.a.a(this.b.a.c, x, y, width, height);
/*     */           
/* 266 */           gc.a(shape);
/*     */         } 
/*     */         
/* 269 */         gc.a(at);
/* 270 */         gc.a(this.a);
/* 271 */         gc.e();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 277 */     String str = (d_()).a.c();
/* 278 */     if (str != null) {
/* 279 */       textBuff.append(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 285 */     if (this.a.an == 1) {
/* 286 */       g newDrawer = new g(this.a.am);
/* 287 */       drawer.a(newDrawer);
/* 288 */       drawer = newDrawer;
/*     */     } 
/*     */     
/* 291 */     x += this.e;
/* 292 */     y += this.f;
/*     */     
/* 294 */     transform = a(transform, x, y);
/*     */     
/* 296 */     visitor.a(transform, this, x, y);
/*     */     
/* 298 */     if (this.a.ao != 0.0F) {
/*     */       
/* 300 */       a a1 = new a(pageBox, clip, this.a.ao, transform, this.b, this.a.a, p(), q());
/* 301 */       drawer.a((jp.cssj.homare.b.c.f)a1, x, y);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */