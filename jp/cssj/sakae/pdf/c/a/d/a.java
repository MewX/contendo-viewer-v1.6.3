/*     */ package jp.cssj.sakae.pdf.c.a.d;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.l;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.a.a;
/*     */ import jp.cssj.sakae.pdf.c.a.e;
/*     */ import jp.cssj.sakae.pdf.c.d;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ class a
/*     */   extends a
/*     */   implements l, d
/*     */ {
/*     */   private static final long h = 2L;
/*  34 */   protected final e d = new e(-32768);
/*     */   
/*  36 */   protected c e = new c(-1);
/*     */   
/*  38 */   protected final c f = new c();
/*     */   
/*  40 */   protected final List<Shape> g = new ArrayList<>();
/*     */   
/*  42 */   private final char[] i = new char[1];
/*     */   
/*  44 */   private final FontRenderContext j = new FontRenderContext(null, false, false);
/*     */   
/*     */   private final Font k;
/*  47 */   private final AffineTransform[] l = new AffineTransform[6];
/*     */   
/*     */   public a(b source, String name, b fontRef) {
/*  50 */     super((g)source, name, fontRef);
/*  51 */     this.k = new Font("sans-serif", 0, 400);
/*  52 */     float ascent = this.a.g();
/*  53 */     float descent = this.a.i();
/*  54 */     this.l[0] = AffineTransform.getTranslateInstance(140.0D, (-ascent + 440.0F));
/*  55 */     this.l[1] = AffineTransform.getTranslateInstance(550.0D, (-ascent + 440.0F));
/*  56 */     this.l[2] = AffineTransform.getTranslateInstance(140.0D, (descent - 160.0F));
/*  57 */     this.l[3] = AffineTransform.getTranslateInstance(550.0D, (descent - 160.0F));
/*  58 */     this.l[4] = AffineTransform.getTranslateInstance(140.0D, (descent - 160.0F + 400.0F));
/*  59 */     this.l[5] = AffineTransform.getTranslateInstance(550.0D, (descent - 160.0F + 400.0F));
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  63 */     int gid = this.e.b(i);
/*  64 */     if (gid == -1) {
/*  65 */       gid = this.g.size();
/*  66 */       this.f.a(gid, i);
/*  67 */       this.e.a(i, gid);
/*     */       
/*  69 */       short advance = b(gid);
/*  70 */       this.d.a(gid, advance);
/*  71 */       Shape shape = d(gid);
/*  72 */       this.g.add(shape);
/*     */     } 
/*  74 */     return gid;
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/*  78 */     int i = this.f.b(gid);
/*  79 */     switch (i) {
/*     */ 
/*     */       
/*     */       case 0:
/*     */       case 11:
/*     */       case 28:
/*     */       case 29:
/*     */       case 30:
/*     */       case 31:
/*     */       case 8203:
/*     */       case 8204:
/*     */       case 8205:
/*     */       case 8206:
/*     */       case 8207:
/*     */       case 8234:
/*     */       case 8235:
/*     */       case 8236:
/*     */       case 8237:
/*     */       case 8238:
/*     */       case 8288:
/*     */       case 65279:
/* 100 */         return 0;
/*     */       
/*     */       case 32:
/*     */       case 127:
/*     */       case 160:
/*     */       case 8232:
/*     */       case 8233:
/*     */       case 8239:
/* 108 */         return 500;
/*     */     } 
/* 110 */     return (short)((i <= 65535) ? 1000 : 1400);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/* 114 */     short s = b(gid);
/* 115 */     return (s == 1400) ? 1000 : s;
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, c {
/* 119 */     if (gc instanceof jp.cssj.sakae.pdf.d.a) {
/* 120 */       b.a(((jp.cssj.sakae.pdf.d.a)gc).b(), text, 
/* 121 */           (this.a.e() == 3));
/*     */     } else {
/* 123 */       jp.cssj.sakae.c.a.a.a.a(gc, (d)this, text);
/*     */     } 
/*     */   }
/*     */   public void a(c out, k xref) throws IOException {
/*     */     short[] w2;
/* 128 */     b source = (b)this.a;
/* 129 */     int[] unicodeArray = this.f.a();
/* 130 */     short[] w = this.d.a();
/*     */     
/* 132 */     if (this.a.e() == 3) {
/* 133 */       w2 = new short[0];
/*     */     } else {
/* 135 */       w2 = null;
/*     */     } 
/* 137 */     e.a(out, xref, source, this, this.c, w, w2, unicodeArray);
/*     */   }
/*     */   
/*     */   public b c() {
/* 141 */     b source = (b)this.a;
/* 142 */     return source.f();
/*     */   }
/*     */   
/*     */   public int g() {
/* 146 */     return this.g.size();
/*     */   }
/*     */   
/*     */   public int h() {
/* 150 */     return this.g.size();
/*     */   }
/*     */   
/*     */   public String e() {
/* 154 */     return "Identity";
/*     */   }
/*     */   
/*     */   public String d() {
/* 158 */     return "Adobe";
/*     */   }
/*     */   
/*     */   public Shape a_(int gid) {
/* 162 */     return this.g.get(gid);
/*     */   }
/*     */   
/*     */   public byte[] f(int i) {
/* 166 */     return null;
/*     */   }
/*     */   
/*     */   public int f() {
/* 170 */     return 0;
/*     */   }
/*     */   
/*     */   public String b() {
/* 174 */     return this.a.d();
/*     */   }
/*     */   public Shape d(int gid) {
/*     */     Shape shape;
/* 178 */     short advance = b(gid);
/* 179 */     int i = this.f.b(gid);
/*     */     
/* 181 */     if (advance >= 1000) {
/* 182 */       GeneralPath path = new GeneralPath();
/* 183 */       shape = path;
/*     */       
/* 185 */       String s = Integer.toHexString(i).toUpperCase();
/* 186 */       if (s.length() > 6) {
/* 187 */         s = s.substring(s.length() - 6);
/* 188 */       } else if (s.length() < 4) {
/* 189 */         s = "0000".substring(s.length()) + s;
/*     */       } 
/*     */       
/* 192 */       float yy = (s.length() <= 4) ? 0.0F : 400.0F;
/*     */       
/* 194 */       float ascent = this.a.g();
/* 195 */       float descent = this.a.i();
/* 196 */       path.moveTo(50.0F, -ascent + 50.0F);
/* 197 */       path.lineTo(950.0F, -ascent + 50.0F);
/* 198 */       path.lineTo(950.0F, descent - 50.0F + yy);
/* 199 */       path.lineTo(50.0F, descent - 50.0F + yy);
/* 200 */       path.closePath();
/* 201 */       path.moveTo(70.0F, -ascent + 70.0F);
/* 202 */       path.lineTo(70.0F, descent - 70.0F + yy);
/* 203 */       path.lineTo(930.0F, descent - 70.0F + yy);
/* 204 */       path.lineTo(930.0F, -ascent + 70.0F);
/* 205 */       path.closePath();
/*     */       
/* 207 */       for (int j = 0; j < s.length(); j++) {
/* 208 */         this.i[0] = s.charAt(j);
/* 209 */         GlyphVector gv = this.k.createGlyphVector(this.j, this.i);
/* 210 */         Shape gs = gv.getGlyphOutline(0);
/* 211 */         path.append(gs.getPathIterator(this.l[j]), false);
/*     */       } 
/*     */     } else {
/* 214 */       shape = null;
/*     */     } 
/* 216 */     return shape;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */