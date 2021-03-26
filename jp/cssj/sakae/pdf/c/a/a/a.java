/*     */ package jp.cssj.sakae.pdf.c.a.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.b.a;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.a.e;
/*     */ import jp.cssj.sakae.pdf.c.d;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ import net.zamasoft.a.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ class a
/*     */   extends a
/*     */   implements d
/*     */ {
/*     */   private static final long n = 0L;
/*     */   protected final b g;
/*     */   protected final String h;
/*  30 */   protected final e i = new e(-32768); protected final e j = new e(-32768);
/*     */   
/*  32 */   protected c k = new c(-1);
/*     */   
/*  34 */   protected c l = new c();
/*     */   
/*  36 */   protected int m = 1;
/*     */   
/*     */   protected a(b source, String name, b fontRef) {
/*  39 */     super(source);
/*  40 */     this.g = fontRef;
/*  41 */     this.h = name;
/*  42 */     this.i.a(0, e(0));
/*  43 */     this.j.a(0, b_(0));
/*     */   }
/*     */   
/*     */   public String i() {
/*  47 */     return this.h;
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  51 */     b source = (b)a();
/*  52 */     int agid = source.r().a(i);
/*  53 */     if (agid == 0) {
/*  54 */       return 0;
/*     */     }
/*  56 */     int gid = this.k.b(i);
/*  57 */     if (gid == -1) {
/*  58 */       gid = this.m;
/*  59 */       this.k.a(i, gid);
/*  60 */       this.l.a(gid, i);
/*     */       
/*  62 */       if (this.d != null) {
/*  63 */         agid = this.d.a(agid);
/*     */       }
/*  65 */       this.m++;
/*  66 */       this.i.a(gid, e(agid));
/*  67 */       this.j.a(gid, b_(agid));
/*     */     } 
/*  69 */     return gid;
/*     */   }
/*     */   
/*     */   protected int g(int gid) {
/*  73 */     return this.l.b(gid);
/*     */   }
/*     */   
/*     */   protected int h(int gid) {
/*  77 */     int bgid, cid = this.l.b(gid);
/*  78 */     int agid = this.c.r().a(cid);
/*     */     
/*  80 */     if (this.d != null) {
/*  81 */       bgid = this.d.a(agid);
/*     */     } else {
/*  83 */       bgid = agid;
/*     */     } 
/*  85 */     return bgid;
/*     */   }
/*     */   
/*     */   public Shape d(int gid) {
/*  89 */     b glyph = this.c.o().b(h(gid));
/*  90 */     if (glyph == null) {
/*  91 */       return null;
/*     */     }
/*  93 */     Shape shape = glyph.a();
/*  94 */     if (shape == null) {
/*  95 */       return null;
/*     */     }
/*  97 */     shape = a(shape, gid);
/*  98 */     return shape;
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/* 102 */     if (g_()) {
/* 103 */       return this.j.a(gid);
/*     */     }
/* 105 */     return this.i.a(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/* 109 */     return this.i.a(gid);
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, c {
/* 113 */     if (gc instanceof jp.cssj.sakae.pdf.d.a) {
/* 114 */       b.a(((jp.cssj.sakae.pdf.d.a)gc).b(), text, (this.d != null));
/*     */     } else {
/* 116 */       jp.cssj.sakae.c.a.a.a.a(gc, (d)this, text);
/*     */     } 
/*     */   }
/*     */   public void a(c out, k xref) throws IOException {
/*     */     short[] w2;
/* 121 */     b source = (b)a();
/* 122 */     int[] unicodea = this.l.a();
/* 123 */     short[] w = this.i.a();
/*     */     
/* 125 */     if (this.d != null) {
/* 126 */       w2 = this.j.a();
/*     */     } else {
/* 128 */       w2 = null;
/*     */     } 
/*     */     
/* 131 */     e.a(out, xref, source, this, this.g, w, w2, unicodea);
/* 132 */     this.l = null;
/*     */   }
/*     */   
/*     */   public b c() {
/* 136 */     b source = (b)a();
/* 137 */     return source.f();
/*     */   }
/*     */   
/*     */   public int g() {
/* 141 */     return this.m;
/*     */   }
/*     */   
/*     */   public int h() {
/* 145 */     return this.m;
/*     */   }
/*     */   
/*     */   public String e() {
/* 149 */     return "Identity";
/*     */   }
/*     */   
/*     */   public String d() {
/* 153 */     return "Adobe";
/*     */   }
/*     */   
/*     */   public Shape a_(int gid) {
/* 157 */     return d(gid);
/*     */   }
/*     */   
/*     */   public byte[] f(int gid) {
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int f() {
/* 171 */     return 0;
/*     */   }
/*     */   
/*     */   public String b() {
/* 175 */     b metaFont = (b)a();
/* 176 */     return metaFont.d();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */