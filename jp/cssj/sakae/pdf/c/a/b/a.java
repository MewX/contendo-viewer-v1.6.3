/*     */ package jp.cssj.sakae.pdf.c.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.a.b.a;
/*     */ import jp.cssj.sakae.a.b.c;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.a.e;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.c.e;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ import net.zamasoft.a.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ class a
/*     */   extends a
/*     */   implements e
/*     */ {
/*     */   private static final long l = 0L;
/*     */   protected final b g;
/*     */   protected final String h;
/*  30 */   protected final e i = new e(-32768);
/*     */   
/*     */   protected final e j;
/*     */   
/*  34 */   protected final c k = new c();
/*     */   
/*     */   protected a(b metaFont, String name, b fontRef) {
/*  37 */     super(metaFont);
/*  38 */     this.g = fontRef;
/*  39 */     this.h = name;
/*  40 */     if (g_()) {
/*  41 */       this.j = new e(-32768);
/*     */     } else {
/*  43 */       this.j = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String i() {
/*  48 */     return this.h;
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  52 */     b source = (b)a();
/*  53 */     int gid = source.r().a(i);
/*  54 */     if (this.d != null) {
/*  55 */       gid = this.d.a(gid);
/*     */     }
/*  57 */     return gid;
/*     */   }
/*     */   
/*     */   protected int g(int gid) {
/*  61 */     return this.k.b(gid);
/*     */   }
/*     */   
/*     */   public Shape d(int gid) {
/*  65 */     c source = (c)a();
/*  66 */     b glyph = source.o().b(gid);
/*  67 */     if (glyph == null) {
/*  68 */       return null;
/*     */     }
/*  70 */     Shape shape = glyph.a();
/*  71 */     if (shape == null) {
/*  72 */       return null;
/*     */     }
/*  74 */     shape = a(shape, gid);
/*  75 */     return shape;
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/*  79 */     if (g_()) {
/*  80 */       return b_(gid);
/*     */     }
/*  82 */     return e(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/*  86 */     return e(gid);
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, c {
/*  90 */     if (gc instanceof jp.cssj.sakae.pdf.d.a) {
/*  91 */       b.a(((jp.cssj.sakae.pdf.d.a)gc).b(), text, g_());
/*     */     } else {
/*  93 */       jp.cssj.sakae.c.a.a.a.a(gc, (d)this, text);
/*     */     } 
/*     */     
/*  96 */     int glen = text.l();
/*  97 */     int[] gids = text.j();
/*  98 */     char[] chars = text.h();
/*  99 */     for (int i = 0; i < glen; i++) {
/* 100 */       this.i.a(gids[i], e(gids[i]));
/* 101 */       if (g_()) {
/* 102 */         this.j.a(gids[i], b_(gids[i]));
/*     */       }
/* 104 */       this.k.a(gids[i], chars[i]);
/*     */     } 
/*     */   }
/*     */   public void a(c out, k xref) throws IOException {
/*     */     short[] w2;
/* 109 */     b source = (b)a();
/* 110 */     short[] w = this.i.a();
/*     */     
/* 112 */     if (g_()) {
/* 113 */       w2 = this.j.a();
/*     */     } else {
/* 115 */       w2 = null;
/*     */     } 
/* 117 */     e.a(out, xref, source, this.g, w, w2, this.k.a());
/*     */   }
/*     */   
/*     */   public short a(int scid, int cid) {
/* 121 */     return 0;
/*     */   }
/*     */   
/*     */   public int b(int scid, int cid) {
/* 125 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */