/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import jp.cssj.homare.b.a.c.C;
/*     */ import jp.cssj.homare.b.a.c.a;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.e.a;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends f
/*     */   implements i
/*     */ {
/*     */   protected final a h;
/*     */   
/*     */   public b(C params, a pos) {
/*  23 */     super(params);
/*  24 */     this.h = pos;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  28 */     return (z)this.h;
/*     */   }
/*     */   
/*     */   public final a c() {
/*  32 */     return this.h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(m containerBox) {
/*  39 */     double cWidth = containerBox.s() + (containerBox.m()).c.a();
/*  40 */     double cHeight = containerBox.t() + (containerBox.m()).c.b();
/*     */     
/*  42 */     a(cWidth, cHeight, cWidth, cHeight);
/*  43 */     t margin = this.b.a.b;
/*  44 */     a amargin = this.b.b;
/*     */ 
/*     */     
/*  47 */     double left = e.b(this.h.a, cWidth);
/*  48 */     double right = e.c(this.h.a, cWidth);
/*  49 */     double marginLeft = (margin.d() == 3) ? 1.722773839210782E308D : amargin.d;
/*  50 */     double marginRight = (margin.b() == 3) ? 1.722773839210782E308D : amargin.b;
/*  51 */     if (!e.a(left) && !e.a(right)) {
/*  52 */       if (e.a(marginLeft) && e.a(marginRight)) {
/*  53 */         marginLeft = marginRight = (cWidth - left - right - this.c - this.b.f()) / 2.0D;
/*     */       }
/*  55 */       if (e.a(marginLeft) && !e.a(marginRight)) {
/*  56 */         marginLeft = cWidth - left - right - this.c - this.b.f();
/*     */       }
/*  58 */       if (!e.a(marginLeft) && e.a(marginRight)) {
/*  59 */         marginRight = cWidth - left - right - this.c - this.b.f();
/*     */       } else {
/*     */         
/*  62 */         right = 0.0D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  67 */       if (e.a(marginLeft)) {
/*  68 */         marginLeft = 0.0D;
/*     */       }
/*  70 */       if (e.a(marginRight)) {
/*  71 */         marginRight = 0.0D;
/*     */       }
/*     */       
/*  74 */       left = right = 0.0D;
/*  75 */       if (e.a(right)) {
/*  76 */         right = cWidth - left - this.c - this.b.f();
/*     */       } else {
/*  78 */         left = cWidth - right - this.c - this.b.f();
/*     */       } 
/*     */     } 
/*  81 */     this.e = left;
/*  82 */     this.b.b.d = marginLeft;
/*  83 */     this.b.b.b = marginRight;
/*  84 */     if (!i && e.a(marginRight)) throw new AssertionError(); 
/*  85 */     if (!i && e.a(marginLeft)) throw new AssertionError();
/*     */     
/*  87 */     double top = e.a(this.h.a, cHeight);
/*  88 */     double bottom = e.d(this.h.a, cHeight);
/*  89 */     double marginTop = (margin.a() == 3) ? 1.722773839210782E308D : amargin.a;
/*  90 */     double marginBottom = (margin.c() == 3) ? 1.722773839210782E308D : amargin.c;
/*  91 */     if (!e.a(top) && !e.a(bottom)) {
/*  92 */       if (e.a(marginTop) && e.a(marginBottom)) {
/*  93 */         marginTop = marginBottom = (cHeight - top - bottom - this.d - this.b.e()) / 2.0D;
/*     */       }
/*  95 */       if (e.a(marginTop) && !e.a(marginBottom)) {
/*  96 */         marginTop = cHeight - top - bottom - this.d - this.b.e();
/*     */       }
/*  98 */       if (!e.a(marginTop) && e.a(marginBottom)) {
/*  99 */         marginBottom = cHeight - top - bottom - this.d - this.b.e();
/*     */       } else {
/*     */         
/* 102 */         bottom = 0.0D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 107 */       if (e.a(marginTop)) {
/* 108 */         marginTop = 0.0D;
/*     */       }
/* 110 */       if (e.a(marginBottom)) {
/* 111 */         marginBottom = 0.0D;
/*     */       }
/*     */       
/* 114 */       top = bottom = 0.0D;
/* 115 */       if (e.a(top)) {
/* 116 */         top = cHeight - bottom - this.d - marginTop - this.b.e();
/*     */       } else {
/* 118 */         bottom = cHeight - top - this.d - marginTop - this.b.e();
/*     */       } 
/*     */     } 
/* 121 */     this.f = top;
/* 122 */     this.b.b.a = marginTop;
/* 123 */     this.b.b.c = marginBottom;
/* 124 */     if (!i && e.a(marginTop)) throw new AssertionError(); 
/* 125 */     if (!i && e.a(marginBottom)) throw new AssertionError();
/*     */     
/* 127 */     if (!i && e.a(this.c)) throw new AssertionError(); 
/* 128 */     if (!i && e.a(this.d)) throw new AssertionError(); 
/* 129 */     if (!i && e.a(this.e)) throw new AssertionError("Undefined offsetX"); 
/* 130 */     if (!i && e.a(this.f)) throw new AssertionError("Undefined offsetY"); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */