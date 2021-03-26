/*     */ package jp.cssj.sakae.pdf;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import jp.cssj.sakae.c.c.a;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.e.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class d
/*     */   extends h
/*     */ {
/*     */   protected final double a;
/*     */   protected final double b;
/*     */   protected final j c;
/*     */   
/*     */   public d(j pdfWriter, OutputStream out, double width, double height) throws IOException {
/*  23 */     super(out, pdfWriter.a().i());
/*  24 */     this.a = width;
/*  25 */     this.b = height;
/*  26 */     this.c = pdfWriter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j d() {
/*  35 */     return this.c;
/*     */   }
/*     */   
/*     */   public double a() {
/*  39 */     return this.a;
/*     */   }
/*     */   
/*     */   public double b() {
/*  43 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void a(String paramString1, String paramString2) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(double x, double y) throws IOException {
/*  56 */     a(x);
/*  57 */     a(this.b - y);
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
/*     */   public void a(double x1, double y1, double x2, double y2) throws IOException {
/*  70 */     i();
/*  71 */     a(x1, y2);
/*  72 */     a(x2, y1);
/*  73 */     j();
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
/*     */   
/*     */   public void b(double x, double y, double width, double height) throws IOException {
/*  87 */     a(x, y + height);
/*  88 */     a(width);
/*  89 */     a(height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(AffineTransform at) throws IOException {
/*  99 */     AffineTransform tpdf = new AffineTransform(1.0D, 0.0D, 0.0D, -1.0D, 0.0D, this.b);
/* 100 */     AffineTransform iat = new AffineTransform(at);
/* 101 */     iat.preConcatenate(tpdf);
/* 102 */     iat.concatenate(tpdf);
/* 103 */     a(iat.getScaleX());
/* 104 */     a(iat.getShearY());
/* 105 */     a(iat.getShearX());
/* 106 */     a(iat.getScaleY());
/* 107 */     a(iat.getTranslateX());
/* 108 */     a(iat.getTranslateY());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(b color) throws IOException {
/*     */     a a;
/*     */     float c;
/*     */     float m;
/*     */     float y;
/*     */     float k;
/* 118 */     if (d().a().l() == 1) {
/* 119 */       if (color.c() != 3) {
/* 120 */         color = b.a(color);
/*     */       }
/* 122 */     } else if (d().a().l() == 2 && 
/* 123 */       color.c() != 2) {
/* 124 */       a = b.b(color);
/*     */     } 
/*     */     
/* 127 */     switch (a.c()) {
/*     */       case 3:
/* 129 */         a(a.a(0));
/* 130 */         b("g");
/*     */         return;
/*     */       case 1:
/*     */       case 4:
/* 134 */         a(a.a(0));
/* 135 */         a(a.a(1));
/* 136 */         a(a.a(2));
/* 137 */         b("rg");
/*     */         return;
/*     */       case 2:
/* 140 */         c = a.a(0);
/* 141 */         m = a.a(1);
/* 142 */         y = a.a(2);
/* 143 */         k = a.a(3);
/* 144 */         a(c);
/* 145 */         a(m);
/* 146 */         a(y);
/* 147 */         a(k);
/* 148 */         b("k");
/*     */         return;
/*     */     } 
/* 151 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(b color) throws IOException {
/*     */     a a;
/*     */     float c;
/*     */     float m;
/*     */     float y;
/*     */     float k;
/* 162 */     if (d().a().l() == 1) {
/*     */       
/* 164 */       if (color.c() != 3) {
/* 165 */         color = b.a(color);
/*     */       }
/* 167 */     } else if (d().a().l() == 2) {
/*     */       
/* 169 */       if (color.c() != 2) {
/* 170 */         a = b.b(color);
/*     */       }
/*     */     } 
/* 173 */     switch (a.c()) {
/*     */       case 3:
/* 175 */         a(a.a(0));
/* 176 */         b("G");
/*     */         return;
/*     */       case 1:
/*     */       case 4:
/* 180 */         a(a.a(0));
/* 181 */         a(a.a(1));
/* 182 */         a(a.a(2));
/* 183 */         b("RG");
/*     */         return;
/*     */       case 2:
/* 186 */         c = a.a(0);
/* 187 */         m = a.a(1);
/* 188 */         y = a.a(2);
/* 189 */         k = a.a(3);
/* 190 */         a(c);
/* 191 */         a(m);
/* 192 */         a(y);
/* 193 */         a(k);
/* 194 */         b("K");
/*     */         return;
/*     */     } 
/* 197 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */