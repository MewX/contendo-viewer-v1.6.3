/*     */ package net.a.a.g;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class e
/*     */   implements d
/*     */ {
/*     */   private f a;
/*     */   private float b;
/*     */   private float c;
/*     */   private float d;
/*     */   private float e;
/*     */   private float f;
/*     */   private float g;
/*     */   private float h;
/*     */   private float i;
/*     */   private float j;
/*     */   private float k;
/*     */   private float l;
/*     */   private float m;
/*  55 */   private float n = -1.0F;
/*     */   
/*  57 */   private float o = -1.0F;
/*     */   
/*  59 */   private float p = -1.0F;
/*     */ 
/*     */   
/*     */   private final List<b> q;
/*     */ 
/*     */ 
/*     */   
/*     */   public e() {
/*  67 */     this.a = f.a;
/*  68 */     this.q = new ArrayList<b>();
/*     */   }
/*     */ 
/*     */   
/*     */   public f a() {
/*  73 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(f paramf) {
/*  78 */     this.a = paramf;
/*     */   }
/*     */ 
/*     */   
/*     */   public float b(f paramf) {
/*  83 */     if (f.b.equals(paramf)) {
/*  84 */       return this.b;
/*     */     }
/*  86 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float c(f paramf) {
/*  92 */     if (f.b.equals(paramf)) {
/*  93 */       return this.d;
/*     */     }
/*  95 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float e(f paramf) {
/* 101 */     if (f.b.equals(paramf)) {
/* 102 */       return this.f;
/*     */     }
/* 104 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float f(f paramf) {
/* 110 */     if (f.b.equals(paramf)) {
/* 111 */       return this.j;
/*     */     }
/* 113 */     return this.k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float g(f paramf) {
/* 119 */     if (f.b.equals(paramf)) {
/* 120 */       return this.l;
/*     */     }
/* 122 */     return this.m;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float d(f paramf) {
/* 128 */     if (f.b.equals(paramf)) {
/* 129 */       return this.h;
/*     */     }
/* 131 */     return this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2, f paramf) {
/* 137 */     this.k = paramFloat1;
/* 138 */     this.m = paramFloat2;
/* 139 */     if (f.b.equals(paramf)) {
/* 140 */       this.j = paramFloat1;
/* 141 */       this.l = paramFloat2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(float paramFloat, f paramf) {
/* 149 */     this.c = paramFloat;
/* 150 */     if (f.b.equals(paramf)) {
/* 151 */       this.b = paramFloat;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(float paramFloat, f paramf) {
/* 158 */     this.e = paramFloat;
/* 159 */     if (f.b.equals(paramf)) {
/* 160 */       this.d = paramFloat;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat, f paramf) {
/* 167 */     this.g = paramFloat;
/* 168 */     if (f.b.equals(paramf)) {
/* 169 */       this.f = paramFloat;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(float paramFloat, f paramf) {
/* 175 */     this.i = paramFloat;
/* 176 */     if (f.b.equals(paramf)) {
/* 177 */       this.h = paramFloat;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(b paramb) {
/* 183 */     this.q.clear();
/* 184 */     this.q.add(paramb);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<b> e() {
/* 189 */     return this.q;
/*     */   }
/*     */ 
/*     */   
/*     */   public float b() {
/* 194 */     return this.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float paramFloat) {
/* 199 */     this.p = paramFloat;
/*     */   }
/*     */ 
/*     */   
/*     */   public float d() {
/* 204 */     if (this.n < 0.0F) {
/* 205 */       return this.b;
/*     */     }
/* 207 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float c() {
/* 213 */     if (this.o < 0.0F) {
/* 214 */       return this.d;
/*     */     }
/* 216 */     return this.o;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(float paramFloat) {
/* 222 */     this.n = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(float paramFloat) {
/* 228 */     this.o = paramFloat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(float paramFloat, f paramf) {
/* 233 */     this.m += paramFloat;
/* 234 */     if (f.b.equals(paramf))
/* 235 */       this.l += paramFloat; 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */