/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.c.e;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.b.a.a;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class g
/*     */   extends a
/*     */ {
/*     */   protected boolean n = false;
/*     */   
/*     */   public g(i params) {
/*  24 */     super(params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected g(i params, m size, m minSize, b frame, g container) {
/*  29 */     super(params, size, minSize, frame, container);
/*     */   }
/*     */   
/*     */   public abstract e u();
/*     */   
/*     */   public final boolean e_() {
/*  35 */     return this.n;
/*     */   }
/*     */   
/*     */   public final boolean g() {
/*  39 */     return ((u()).d != null);
/*     */   }
/*     */   
/*     */   public void a(d layoutStack, double minLineAxis, double maxLineAxis, boolean table) {
/*     */     c containerBox;
/*  44 */     if (b_().a() == 4) {
/*  45 */       if (table) {
/*     */         
/*  47 */         a builder = (a)layoutStack;
/*  48 */         containerBox = (builder.a(builder.g() - 2)).a;
/*     */       } else {
/*     */         
/*  51 */         containerBox = layoutStack.k();
/*     */       } 
/*     */     } else {
/*  54 */       containerBox = layoutStack.k();
/*     */     } 
/*  56 */     i cParams = containerBox.c_();
/*  57 */     double lineSize = containerBox.h();
/*  58 */     if (e.a(this.a.D)) {
/*     */       
/*  60 */       this
/*     */         
/*  62 */         .n = (this.a.X.a() == 1 || (this.a.X.a() == 2 && !table && (b_().a() == 5 || containerBox.e_())));
/*     */     } else {
/*     */       
/*  65 */       this
/*     */         
/*  67 */         .n = (this.a.X.b() == 1 || (this.a.X.b() == 2 && !table && (b_().a() == 5 || containerBox.e_())));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     e.b(this.e.c, this.e.a.e, lineSize);
/*     */ 
/*     */ 
/*     */     
/*  77 */     e.a(this.e.b, this.e.a.b, lineSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     if (e.a(this.a.D)) {
/*     */       double minWidth, maxWidth;
/*  85 */       c fixedWidthBox = layoutStack.p();
/*  86 */       if (fixedWidthBox == null) {
/*  87 */         fixedWidthBox = containerBox;
/*     */       }
/*  89 */       double cHeight = table ? containerBox.t() : layoutStack.o();
/*  90 */       double cWidth = fixedWidthBox.t();
/*  91 */       this.g = e.b(this.c, cHeight);
/*  92 */       if (e.a(this.g)) {
/*  93 */         this.g = maxLineAxis;
/*     */       }
/*  95 */       else if (this.a.aa == 2) {
/*  96 */         this.g -= this.e.g();
/*     */       } 
/*     */       
/*  99 */       if (this.c.b() == 3 && containerBox
/*     */         
/* 101 */         .d() != 2) {
/*     */         double limitHeight;
/* 103 */         if (e.a(cParams.D) || containerBox.e_()) {
/* 104 */           limitHeight = cHeight - this.e.e();
/*     */         } else {
/*     */           
/* 107 */           limitHeight = layoutStack.o() - this.e.e();
/*     */         } 
/* 109 */         this.g = Math.max(minLineAxis, Math.min(limitHeight, this.g));
/*     */       } 
/* 111 */       double maxHeight = e.b(this.a.Z, cHeight);
/* 112 */       if (!e.a(maxHeight) && this.g > maxHeight) {
/* 113 */         this.g = maxHeight;
/*     */       }
/* 115 */       double minHeight = e.b(this.d, cHeight);
/* 116 */       if (this.g < minHeight) {
/* 117 */         this.g = minHeight;
/*     */       }
/*     */ 
/*     */       
/* 121 */       switch (this.d.a()) {
/*     */         case 2:
/* 123 */           if (!table && e_()) {
/* 124 */             double d1 = this.d.c() * cWidth;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 128 */           minWidth = 0.0D;
/*     */           break;
/*     */         case 1:
/* 131 */           minWidth = this.d.c();
/*     */           break;
/*     */         default:
/* 134 */           throw new IllegalStateException();
/*     */       } 
/*     */       
/* 137 */       switch (this.a.Z.a()) {
/*     */         case 2:
/* 139 */           if (!table && e_()) {
/* 140 */             double d1 = this.a.Z.c() * cWidth;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 144 */           maxWidth = Double.MAX_VALUE;
/*     */           break;
/*     */         case 1:
/* 147 */           maxWidth = this.a.Z.c();
/*     */           break;
/*     */         default:
/* 150 */           throw new IllegalStateException();
/*     */       } 
/* 152 */       switch (this.c.a()) {
/*     */         case 2:
/* 154 */           if (!table && e_()) {
/* 155 */             this.f = this.c.c() * cWidth;
/* 156 */             this.f = Math.max(this.f, minWidth);
/* 157 */             this.f = Math.min(this.f, maxWidth);
/* 158 */             if (this.a.aa == 2) {
/* 159 */               this.f -= m().h();
/*     */             }
/* 161 */             minWidth = maxWidth = this.f;
/*     */           } 
/*     */         
/*     */         case 3:
/* 165 */           if (!table) {
/* 166 */             this.f = 0.0D;
/*     */           }
/*     */           break;
/*     */         case 1:
/* 170 */           this.f = this.c.c();
/* 171 */           this.f = Math.max(this.f, minWidth);
/* 172 */           this.f = Math.min(this.f, maxWidth);
/* 173 */           if (this.a.aa == 2) {
/* 174 */             this.f -= m().h();
/*     */           }
/* 176 */           minWidth = maxWidth = this.f;
/*     */         
/*     */         default:
/* 179 */           throw new IllegalStateException();
/*     */       } 
/* 181 */       this.h = minWidth;
/* 182 */       this.i = maxWidth;
/*     */     } else {
/*     */       double minHeight, maxHeight;
/* 185 */       c fixedHeightBox = layoutStack.q();
/* 186 */       if (fixedHeightBox == null) {
/* 187 */         fixedHeightBox = containerBox;
/*     */       }
/* 189 */       double cHeight = fixedHeightBox.t();
/* 190 */       double cWidth = table ? containerBox.s() : layoutStack.n();
/* 191 */       this.f = e.a(this.c, cWidth);
/* 192 */       if (e.a(this.f)) {
/* 193 */         this.f = maxLineAxis;
/*     */       }
/* 195 */       else if (this.a.aa == 2) {
/* 196 */         this.f -= this.e.h();
/*     */       } 
/*     */       
/* 199 */       if (this.c.a() == 3 && containerBox
/*     */         
/* 201 */         .d() != 2) {
/*     */         double limitWidth;
/* 203 */         if (!e.a(cParams.D) || containerBox.e_()) {
/* 204 */           limitWidth = cWidth - this.e.f();
/*     */         } else {
/*     */           
/* 207 */           limitWidth = layoutStack.n() - this.e.f();
/*     */         } 
/* 209 */         this.f = Math.max(minLineAxis, Math.min(limitWidth, this.f));
/*     */       } 
/* 211 */       double maxWidth = e.a(this.a.Z, cWidth);
/* 212 */       if (!e.a(maxWidth) && this.f > maxWidth) {
/* 213 */         this.f = maxWidth;
/*     */       }
/* 215 */       double minWidth = e.a(this.d, cWidth);
/* 216 */       if (this.f < minWidth) {
/* 217 */         this.f = minWidth;
/*     */       }
/*     */ 
/*     */       
/* 221 */       switch (this.d.b()) {
/*     */         case 2:
/* 223 */           if (!table && e_()) {
/* 224 */             double d1 = this.d.d() * cHeight;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 228 */           minHeight = 0.0D;
/*     */           break;
/*     */         case 1:
/* 231 */           minHeight = this.d.d();
/*     */           break;
/*     */         default:
/* 234 */           throw new IllegalStateException();
/*     */       } 
/*     */       
/* 237 */       switch (this.a.Z.b()) {
/*     */         case 2:
/* 239 */           if (!table && e_()) {
/* 240 */             double d1 = this.a.Z.d() * cHeight;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 244 */           maxHeight = Double.MAX_VALUE;
/*     */           break;
/*     */         case 1:
/* 247 */           maxHeight = this.a.Z.d();
/*     */           break;
/*     */         default:
/* 250 */           throw new IllegalStateException();
/*     */       } 
/* 252 */       switch (this.c.b()) {
/*     */         case 2:
/* 254 */           if (!table && e_()) {
/* 255 */             this.g = this.c.d() * cHeight;
/* 256 */             this.g = Math.max(this.g, minHeight);
/* 257 */             this.g = Math.min(this.g, maxHeight);
/* 258 */             if (this.a.aa == 2) {
/* 259 */               this.g -= m().g();
/*     */             }
/* 261 */             minHeight = this.g;
/* 262 */             maxHeight = this.g;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 266 */           this.g = 0.0D;
/*     */           break;
/*     */         case 1:
/* 269 */           this.g = this.c.d();
/* 270 */           this.g = Math.max(this.g, minHeight);
/* 271 */           this.g = Math.min(this.g, maxHeight);
/* 272 */           if (this.a.aa == 2) {
/* 273 */             this.g -= m().g();
/*     */           }
/* 275 */           minHeight = this.g;
/* 276 */           maxHeight = this.g;
/*     */           break;
/*     */         default:
/* 279 */           throw new IllegalStateException();
/*     */       } 
/* 281 */       this.h = minHeight;
/* 282 */       this.i = maxHeight;
/*     */     } 
/*     */     
/* 285 */     if (!o && e.a(this.f)) throw new AssertionError(); 
/* 286 */     if (!o && e.a(this.g)) throw new AssertionError();
/*     */   
/*     */   }
/*     */   
/*     */   public void a(m containerBox) {
/* 291 */     e pos = u();
/* 292 */     if (pos.d != null) {
/*     */ 
/*     */ 
/*     */       
/* 296 */       this.j = e.a(pos.d, containerBox);
/* 297 */       this.k = e.b(pos.d, containerBox);
/*     */     } 
/* 299 */     super.a(containerBox);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */