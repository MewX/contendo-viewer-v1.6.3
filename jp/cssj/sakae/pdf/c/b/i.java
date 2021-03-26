/*     */ package jp.cssj.sakae.pdf.c.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.c.e;
/*     */ import jp.cssj.sakae.pdf.d;
/*     */ import jp.cssj.sakae.pdf.d.a;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class i
/*     */   implements e
/*     */ {
/*     */   private static final long a = 0L;
/*     */   private final c b;
/*     */   private final String c;
/*     */   private final String d;
/*     */   private final b e;
/*     */   
/*     */   i(c source, String name, String encoding, b fontRef) {
/*  33 */     this.b = source;
/*  34 */     this.c = name;
/*  35 */     this.d = encoding;
/*  36 */     this.e = fontRef;
/*     */   }
/*     */   
/*     */   public g a() {
/*  40 */     return (g)this.b;
/*     */   }
/*     */   
/*     */   public int a(int j) {
/*  44 */     int gid = this.b.c(j);
/*  45 */     return gid;
/*     */   }
/*     */   
/*     */   public short a(int scid, int cid) {
/*  49 */     return this.b.a(scid, cid);
/*     */   }
/*     */   
/*     */   public int b(int scid, int cid) {
/*  53 */     return this.b.b(scid, cid);
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/*  57 */     return this.b.b(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/*  61 */     return b(gid);
/*     */   }
/*     */   
/*     */   public String i() {
/*  65 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, c {
/*  69 */     if (gc instanceof a) {
/*     */       
/*  71 */       d out = ((a)gc).b();
/*  72 */       int glen = text.l();
/*  73 */       int[] gids = text.j();
/*  74 */       double[] xadvances = text.a(false);
/*  75 */       double size = text.d().e();
/*  76 */       out.i();
/*  77 */       int pgid = 0;
/*  78 */       StringBuffer buff = new StringBuffer();
/*  79 */       for (int j = 0; j < glen; j++) {
/*  80 */         int gid = gids[j];
/*  81 */         short kerning = this.b.a(gid, pgid);
/*  82 */         if (xadvances != null) {
/*  83 */           if (j == 0) {
/*  84 */             double xadvance = xadvances[j];
/*  85 */             if (xadvance != 0.0D) {
/*  86 */               out.a(-xadvance * 1000.0D / size);
/*     */             }
/*     */           } else {
/*  89 */             kerning = (short)(int)(kerning + xadvances[j] * 1000.0D / size);
/*     */           } 
/*     */         }
/*  92 */         if (kerning != 0) {
/*  93 */           out.c(buff.toString());
/*  94 */           buff.delete(0, buff.length());
/*  95 */           out.a(-kerning);
/*     */         } 
/*  97 */         buff.append((char)gid);
/*  98 */         pgid = gid;
/*     */       } 
/* 100 */       out.c(buff.toString());
/* 101 */       out.j();
/* 102 */       out.b("TJ");
/*     */     } else {
/* 104 */       b.a(gc, (g)this.b, this.b.q(), text);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(c out, k xref) throws IOException {
/* 109 */     out.a(this.e);
/* 110 */     out.g();
/* 111 */     out.a("Type");
/* 112 */     out.a("Font");
/* 113 */     out.k();
/* 114 */     out.a("Subtype");
/* 115 */     out.a("Type1");
/* 116 */     out.k();
/* 117 */     if (this.d != null) {
/* 118 */       out.a("Encoding");
/* 119 */       out.a(this.d);
/* 120 */       out.k();
/*     */     } 
/* 122 */     out.a("Name");
/* 123 */     out.a(this.c);
/* 124 */     out.k();
/* 125 */     out.a("BaseFont");
/* 126 */     out.a(this.b.d());
/* 127 */     out.k();
/* 128 */     out.h();
/* 129 */     out.a();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 133 */     return super.toString() + ":[PDFName=" + i() + " source=" + a() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */