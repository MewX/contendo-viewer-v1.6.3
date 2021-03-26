/*     */ package c.a.b;
/*     */ 
/*     */ import c.a.c.c;
/*     */ import c.a.d;
/*     */ import c.a.e.b;
/*     */ import c.a.g.b;
/*     */ import c.a.g.c;
/*     */ import c.a.j.b.o;
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
/*     */ public class a
/*     */   implements Cloneable
/*     */ {
/*     */   public d a;
/*     */   public c.a.h.a b;
/*     */   public c c;
/*     */   public b d;
/*     */   public c.a.g.a e;
/*     */   public o f;
/*     */   public c.a.a g;
/*     */   public c.a.a h;
/*     */   public c.a.a i;
/*     */   public d j;
/*     */   public b k;
/*     */   public d l;
/*     */   public d m;
/*     */   public c n;
/*     */   public d o;
/*     */   public d p;
/*     */   public c.a.c.a q;
/*     */   public d r;
/*     */   
/*     */   public a a() {
/*     */     a decSpec2;
/*     */     try {
/* 128 */       decSpec2 = (a)clone();
/* 129 */     } catch (CloneNotSupportedException e) {
/* 130 */       throw new Error("Cannot clone the DecoderSpecs instance");
/*     */     } 
/*     */     
/* 133 */     decSpec2.c = (c)this.c.c();
/* 134 */     decSpec2.d = (b)this.d.c();
/* 135 */     decSpec2.e = (c.a.g.a)this.e.c();
/*     */     
/* 137 */     decSpec2.f = (o)this.f.c();
/* 138 */     decSpec2.g = (c.a.a)this.g.c();
/*     */     
/* 140 */     decSpec2.k = (b)this.k.c();
/*     */     
/* 142 */     if (this.b != null) {
/* 143 */       decSpec2.b = (c.a.h.a)this.b.c();
/*     */     }
/* 145 */     return decSpec2;
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
/*     */   public a(int nt, int nc) {
/* 158 */     this.c = new c(nt, nc, (byte)2);
/* 159 */     this.d = new b(nt, nc, (byte)2);
/* 160 */     this.e = new c.a.g.a(nt, nc, (byte)2);
/*     */ 
/*     */     
/* 163 */     this.f = new o(nt, nc, (byte)2);
/* 164 */     this.g = new c.a.a(nt, nc, (byte)2);
/*     */ 
/*     */     
/* 167 */     this.k = new b(nt, nc, (byte)2);
/*     */ 
/*     */     
/* 170 */     this.j = new d(nt, nc, (byte)2);
/* 171 */     this.m = new d(nt, nc, (byte)2);
/* 172 */     this.q = new c.a.c.a(nt, nc, (byte)2);
/*     */ 
/*     */     
/* 175 */     this.n = new c(nt, nc, (byte)2, this.g);
/*     */ 
/*     */     
/* 178 */     this.h = new c.a.a(nt, nc, (byte)1);
/* 179 */     this.i = new c.a.a(nt, nc, (byte)1);
/* 180 */     this.l = new d(nt, nc, (byte)1);
/* 181 */     this.o = new d(nt, nc, (byte)1);
/* 182 */     this.p = new d(nt, nc, (byte)1);
/* 183 */     this.r = new d(nt, nc, (byte)1);
/* 184 */     this.a = new d(nt, nc, (byte)1);
/* 185 */     this.r.a(new Boolean(false));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/b/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */