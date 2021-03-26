/*     */ package jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class h
/*     */ {
/*     */   private final p a;
/*     */   private final j b;
/*     */   private final j c;
/*  17 */   private g d = null; private g e = null; private g f = null;
/*     */   
/*  19 */   private int g = 0;
/*     */   
/*     */   public h(n pdfWriter) throws IOException {
/*  22 */     this.a = pdfWriter.i;
/*  23 */     this.b = pdfWriter.k.c();
/*  24 */     this.c = pdfWriter.l;
/*     */   }
/*     */   
/*     */   public void a(b pageRef, String title, double t, double x, double y) {
/*  28 */     jp.cssj.sakae.pdf.h.a dest = new jp.cssj.sakae.pdf.h.a(pageRef, x, t - y, 0.0D);
/*  29 */     g outline = new g(title, dest);
/*     */     
/*  31 */     if (this.f == null) {
/*  32 */       if (this.d == null) {
/*  33 */         this.d = outline;
/*     */       } else {
/*  35 */         this.e.f = outline;
/*  36 */         outline.e = this.e;
/*     */       } 
/*  38 */       this.e = outline;
/*  39 */       this.g++;
/*     */     } else {
/*  41 */       if (this.f.g == null) {
/*  42 */         this.f.g = outline;
/*     */       } else {
/*  44 */         this.f.h.f = outline;
/*  45 */         outline.e = this.f.h;
/*     */       } 
/*  47 */       this.f.h = outline;
/*  48 */       this.f.i++;
/*     */     } 
/*     */     
/*  51 */     outline.d = this.f;
/*  52 */     this.f = outline;
/*     */   }
/*     */   
/*     */   public void a() {
/*  56 */     this.f = this.f.d;
/*     */   }
/*     */   
/*     */   public void b() throws IOException {
/*  60 */     if (this.d == null) {
/*  61 */       this.b.close();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  68 */     g outline = this.d;
/*     */     while (true) {
/*  70 */       if (outline.a()) {
/*  71 */         if (outline.e != null) {
/*  72 */           outline.e.f = outline.f;
/*     */         }
/*  74 */         if (outline.f != null) {
/*  75 */           outline.f.e = outline.e;
/*     */         }
/*  77 */         if (outline.d != null) {
/*  78 */           if (outline.d.g == outline) {
/*  79 */             outline.d.g = outline.f;
/*     */           }
/*  81 */           outline.d.i--;
/*     */         } else {
/*  83 */           if (this.d == outline) {
/*  84 */             this.d = outline.f;
/*     */           }
/*  86 */           this.g--;
/*     */         } 
/*     */       } else {
/*  89 */         outline.c = this.a.a();
/*  90 */         if (outline.d != null) {
/*  91 */           outline.d.h = outline;
/*     */         } else {
/*  93 */           this.e = outline;
/*     */         } 
/*     */       } 
/*  96 */       if (outline.g != null) {
/*  97 */         outline = outline.g; continue;
/*     */       } 
/*  99 */       while (outline.f == null) {
/* 100 */         outline = outline.d;
/* 101 */         if (outline == null) {
/*     */           break;
/*     */         }
/*     */       } 
/* 105 */       outline = outline.f;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (this.d == null) {
/* 111 */       this.b.close();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 116 */     this.c.a("Outlines");
/* 117 */     b outlineRef = this.a.a();
/* 118 */     this.c.b(outlineRef);
/* 119 */     this.c.k();
/*     */     
/* 121 */     this.c.a("PageMode");
/* 122 */     this.c.a("UseOutlines");
/* 123 */     this.c.k();
/*     */ 
/*     */     
/* 126 */     this.b.a(outlineRef);
/* 127 */     this.b.g();
/* 128 */     this.b.a("Count");
/* 129 */     this.b.a(this.g);
/* 130 */     this.b.k();
/*     */     
/* 132 */     this.b.a("First");
/* 133 */     this.b.b(this.d.c);
/* 134 */     this.b.k();
/*     */     
/* 136 */     this.b.a("Last");
/* 137 */     this.b.b(this.e.c);
/* 138 */     this.b.k();
/*     */     
/* 140 */     this.b.h();
/* 141 */     this.b.a();
/*     */ 
/*     */ 
/*     */     
/* 145 */     g g1 = this.d;
/*     */     while (true) {
/* 147 */       this.b.a(g1.c);
/* 148 */       this.b.g();
/*     */       
/* 150 */       this.b.a("Parent");
/* 151 */       if (g1.d == null) {
/* 152 */         this.b.b(outlineRef);
/*     */       } else {
/* 154 */         this.b.b(g1.d.c);
/*     */       } 
/* 156 */       this.b.k();
/*     */       
/* 158 */       this.b.a("Dest");
/* 159 */       this.b.a(g1.b);
/* 160 */       this.b.k();
/*     */       
/* 162 */       this.b.a("Title");
/* 163 */       if (g1.a == null) {
/* 164 */         this.b.e("");
/*     */       } else {
/* 166 */         this.b.e(g1.a);
/*     */       } 
/* 168 */       this.b.k();
/*     */       
/* 170 */       if (g1.e != null) {
/* 171 */         this.b.a("Prev");
/* 172 */         this.b.b(g1.e.c);
/* 173 */         this.b.k();
/*     */       } 
/*     */       
/* 176 */       if (g1.f != null) {
/* 177 */         this.b.a("Next");
/* 178 */         this.b.b(g1.f.c);
/* 179 */         this.b.k();
/*     */       } 
/*     */       
/* 182 */       if (g1.i > 0) {
/* 183 */         this.b.a("First");
/* 184 */         this.b.b(g1.g.c);
/* 185 */         this.b.k();
/*     */         
/* 187 */         this.b.a("Last");
/* 188 */         this.b.b(g1.h.c);
/* 189 */         this.b.k();
/*     */         
/* 191 */         this.b.a("Count");
/* 192 */         this.b.a(-g1.i);
/* 193 */         this.b.k();
/*     */       } 
/*     */       
/* 196 */       this.b.h();
/* 197 */       this.b.a();
/*     */       
/* 199 */       if (g1.g != null) {
/* 200 */         g1 = g1.g; continue;
/*     */       } 
/* 202 */       while (g1.f == null) {
/* 203 */         g1 = g1.d;
/* 204 */         if (g1 == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 212 */           this.b.close();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       g1 = g1.f;
/*     */     } 
/*     */     // Byte code: goto -> 770
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */