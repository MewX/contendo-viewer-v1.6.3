/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.a.i;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c.B;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.c.f;
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
/*     */ public abstract class a
/*     */   extends c
/*     */ {
/*     */   private static final boolean n = false;
/*     */   protected final i a;
/*     */   
/*     */   public a(i params) {
/*  35 */     super((params.b() == 5) ? m.e : params.X, 
/*  36 */         (params.b() == 5) ? m.d : params.Y, (g)new i()); B frame;
/*  37 */     this.a = params;
/*     */     
/*  39 */     if (params.b() == 5) {
/*  40 */       frame = B.a;
/*     */     } else {
/*  42 */       frame = params.S;
/*     */     } 
/*  44 */     this.e = new b(frame);
/*  45 */     if (!b && this.a.C == null) throw new AssertionError();
/*     */   
/*     */   }
/*     */   
/*     */   protected a(i params, m size, m minSize, b frame, g container) {
/*  50 */     super(size, minSize, container);
/*  51 */     this.a = params;
/*  52 */     this.e = frame;
/*  53 */     if (!b && this.a.C == null) throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   public byte a() {
/*  57 */     return 5;
/*     */   }
/*     */   
/*     */   public y b() {
/*  61 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public i c_() {
/*  65 */     return this.a;
/*     */   }
/*     */   public void a(c containerBox) {
/*     */     double maxWidth, minWidth, maxHeight, minHeight;
/*  69 */     i containerParams = containerBox.c_();
/*  70 */     double lineSize = containerBox.h();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     e.b(this.e.c, this.e.a.e, lineSize);
/*     */ 
/*     */ 
/*     */     
/*  79 */     e.a(this.e.b, this.e.a.b, lineSize);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     switch (containerParams.D) {
/*     */       
/*     */       case 1:
/*  87 */         this.f = e.a(this.c, lineSize);
/*  88 */         if (e.a(this.f)) {
/*  89 */           this.f = 0.0D;
/*     */         }
/*  91 */         maxWidth = e.a(this.a.Z, lineSize);
/*  92 */         if (!e.a(maxWidth)) {
/*  93 */           this.f = Math.min(this.f, maxWidth);
/*     */         }
/*  95 */         minWidth = e.a(this.d, lineSize);
/*  96 */         this.f = Math.max(this.f, minWidth);
/*     */         break;
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 101 */         this.g = e.b(this.c, lineSize);
/* 102 */         if (e.a(this.g)) {
/* 103 */           this.g = 0.0D;
/*     */         }
/* 105 */         maxHeight = e.a(this.a.Z, lineSize);
/* 106 */         if (!e.a(maxHeight)) {
/* 107 */           this.g = Math.min(this.g, maxHeight);
/*     */         }
/* 109 */         minHeight = e.a(this.d, lineSize);
/* 110 */         this.g = Math.max(this.g, minHeight);
/*     */         break;
/*     */       default:
/* 113 */         throw new IllegalStateException();
/*     */     } 
/* 115 */     if (!b && e.a(this.f)) throw new AssertionError(); 
/* 116 */     if (!b && e.a(this.g)) throw new AssertionError();
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/* 122 */     x += this.j;
/* 123 */     y += this.k;
/*     */     
/* 125 */     transform = a(transform, x, y);
/*     */     
/* 127 */     if (this.a.ao != 0.0F && this.e.i()) {
/*     */       
/* 129 */       jp.cssj.homare.b.c.a a1 = new jp.cssj.homare.b.c.a(pageBox, clip, this.a.ao, transform, this.e, p(), q());
/* 130 */       drawer.a((f)a1, x, y);
/*     */     } 
/*     */     
/* 133 */     clip = a(clip, x, y);
/*     */     
/* 135 */     x += this.e.b();
/* 136 */     y += this.e.a();
/* 137 */     this.l.a(pageBox, drawer, clip, transform, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 142 */     x += this.j;
/* 143 */     y += this.k;
/* 144 */     if (!b && e.a(x)) throw new AssertionError(); 
/* 145 */     if (!b && e.a(y)) throw new AssertionError();
/*     */     
/* 147 */     transform = a(transform, x, y);
/*     */     
/* 149 */     visitor.a(transform, this, x, y);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     clip = a(clip, x, y);
/*     */     
/* 158 */     x += this.e.b();
/* 159 */     y += this.e.a();
/* 160 */     if (!b && e.a(x)) throw new AssertionError(); 
/* 161 */     if (!b && e.a(y)) throw new AssertionError();
/*     */     
/* 163 */     boolean contextBox = g();
/* 164 */     if (contextBox) {
/* 165 */       contextX = x - this.e.c.d;
/* 166 */       contextY = y - this.e.c.a;
/*     */     } 
/* 168 */     this.l.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/* 169 */     this.l.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/* 170 */     if (!contextBox) {
/* 171 */       clip = null;
/*     */     }
/* 173 */     this.l.c(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */   protected abstract a a(m paramm1, m paramm2, b paramb, g paramg);
/*     */   
/*     */   protected final c a(g container, double pageLimit, byte flags) {
/*     */     b prevFrame;
/*     */     a nextBlock;
/* 180 */     if (pageLimit < 0.0D) {
/* 181 */       pageLimit = 0.0D;
/*     */     }
/*     */ 
/*     */     
/* 185 */     if (e.a(this.a.D)) {
/*     */       b nextFrame; m nextSize, nextMinSize;
/* 187 */       if ((flags & 0x10) != 0) {
/*     */         
/* 189 */         prevFrame = nextFrame = this.e;
/* 190 */         pageLimit = Math.max(pageLimit, this.l.h());
/*     */       } else {
/* 192 */         prevFrame = this.e.a(true, true, true, false);
/* 193 */         nextFrame = this.e.a(true, false, true, true);
/*     */       } 
/*     */       
/* 196 */       if (e_()) {
/*     */         
/* 198 */         double width = Math.max(0.0D, this.f - pageLimit);
/* 199 */         nextSize = m.a(width, this.c.d(), (byte)1, this.c
/* 200 */             .b());
/*     */       } else {
/* 202 */         nextSize = this.c;
/*     */       } 
/*     */       
/* 205 */       if (this.d.a() != 3) {
/*     */         
/* 207 */         double width = Math.max(0.0D, Math.min(this.d.c(), this.f) - pageLimit);
/* 208 */         nextMinSize = m.a(width, this.d.d(), (byte)1, this.d
/* 209 */             .b());
/*     */       } else {
/* 211 */         nextMinSize = this.d;
/*     */       } 
/* 213 */       nextBlock = a(nextSize, nextMinSize, nextFrame, container);
/* 214 */       nextBlock.g = this.g;
/* 215 */       this.f = pageLimit;
/*     */     } else {
/*     */       b nextFrame; m nextSize, nextMinSize;
/* 218 */       if ((flags & 0x10) != 0) {
/*     */         
/* 220 */         prevFrame = nextFrame = this.e;
/* 221 */         pageLimit = Math.max(pageLimit, this.l.h());
/*     */       } else {
/* 223 */         prevFrame = this.e.a(true, true, false, true);
/* 224 */         nextFrame = this.e.a(false, true, true, true);
/*     */       } 
/*     */ 
/*     */       
/* 228 */       if (e_()) {
/*     */         
/* 230 */         double height = Math.max(0.0D, this.g - pageLimit);
/* 231 */         nextSize = m.a(this.c.c(), height, this.c.a(), (byte)1);
/*     */       } else {
/*     */         
/* 234 */         nextSize = this.c;
/*     */       } 
/* 236 */       if (this.d.b() != 3) {
/*     */         
/* 238 */         double height = Math.max(0.0D, Math.min(this.d.d(), this.g) - pageLimit);
/* 239 */         nextMinSize = m.a(this.d.c(), height, this.d.a(), (byte)1);
/*     */       } else {
/*     */         
/* 242 */         nextMinSize = this.d;
/*     */       } 
/* 244 */       nextBlock = a(nextSize, nextMinSize, nextFrame, container);
/* 245 */       nextBlock.f = this.f;
/* 246 */       this.g = pageLimit;
/*     */     } 
/* 248 */     this.e = prevFrame;
/* 249 */     return nextBlock;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */