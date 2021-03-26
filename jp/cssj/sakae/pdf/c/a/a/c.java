/*     */ package jp.cssj.sakae.pdf.c.a.a;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.l;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c.a.a;
/*     */ import jp.cssj.sakae.pdf.c.a.b;
/*     */ import jp.cssj.sakae.pdf.c.a.e;
/*     */ import jp.cssj.sakae.pdf.c.d;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.d.a;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ class c
/*     */   extends a
/*     */   implements l, d
/*     */ {
/*     */   private static final long h = 0L;
/*  29 */   protected final e d = new e(-32768);
/*     */   
/*  31 */   protected jp.cssj.sakae.e.c e = new jp.cssj.sakae.e.c(-1);
/*     */   
/*  33 */   protected jp.cssj.sakae.e.c f = new jp.cssj.sakae.e.c();
/*     */   
/*  35 */   protected final List<Shape> g = new ArrayList<>();
/*     */   private final char[] i;
/*     */   
/*  38 */   public c(d source, String name, b fontRef) { super((g)source, name, fontRef);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     this.i = new char[1]; int[] cida = { source.q().getMissingGlyphCode() };
/*     */     GlyphVector gv = source.q().createGlyphVector(source.r(), cida);
/*     */     this.d.a(0, (short)(int)gv.getGlyphMetrics(0).getAdvance());
/*     */     this.g.add(gv.getGlyphOutline(0)); } public int a(int i) { int gid;
/*  50 */     d metaFont = (d)this.a;
/*  51 */     if (metaFont.q().canDisplay((char)i)) {
/*  52 */       gid = this.e.b(i);
/*  53 */       if (gid == -1) {
/*  54 */         this.i[0] = (char)i;
/*  55 */         gid = this.g.size();
/*  56 */         this.e.a(i, gid);
/*  57 */         this.f.a(gid, i);
/*  58 */         GlyphVector gv = metaFont.q().createGlyphVector(metaFont.r(), this.i);
/*  59 */         short advance = (short)(int)gv.getGlyphMetrics(0).getAdvance();
/*  60 */         Shape shape = gv.getGlyphOutline(0);
/*  61 */         this.g.add(shape);
/*     */         
/*  63 */         this.d.a(gid, advance);
/*     */       } 
/*     */     } else {
/*  66 */       gid = 0;
/*     */     } 
/*  68 */     return gid; }
/*     */ 
/*     */   
/*     */   public short b(int gid) {
/*  72 */     return this.d.a(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/*  76 */     return b(gid);
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, jp.cssj.sakae.c.c {
/*  80 */     if (gc instanceof a) {
/*  81 */       b.a(((a)gc).b(), text, false);
/*     */     } else {
/*  83 */       d source = (d)a();
/*  84 */       b.a(gc, (g)source, source.q(), text);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.sakae.pdf.c out, k xref) throws IOException {
/*  89 */     d metaFont = (d)this.a;
/*  90 */     int[] unicodea = this.f.a();
/*  91 */     this.f = null;
/*  92 */     e.a(out, xref, (b)metaFont, this, this.c, this.d.a(), null, unicodea);
/*     */   }
/*     */   
/*     */   public b c() {
/*  96 */     d metaFont = (d)this.a;
/*  97 */     return metaFont.f();
/*     */   }
/*     */   
/*     */   public int g() {
/* 101 */     return this.g.size();
/*     */   }
/*     */   
/*     */   public int h() {
/* 105 */     return this.g.size();
/*     */   }
/*     */   
/*     */   public String e() {
/* 109 */     return "Identity";
/*     */   }
/*     */   
/*     */   public String d() {
/* 113 */     return "Adobe";
/*     */   }
/*     */   
/*     */   public Shape a_(int gid) {
/* 117 */     return this.g.get(gid);
/*     */   }
/*     */   
/*     */   public byte[] f(int i) {
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public Shape d(int gid) {
/* 125 */     return a_(gid);
/*     */   }
/*     */   
/*     */   public int f() {
/* 129 */     return 0;
/*     */   }
/*     */   
/*     */   public String b() {
/* 133 */     d metaFont = (d)this.a;
/* 134 */     Font awtFont = metaFont.q();
/* 135 */     return awtFont.getPSName();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */