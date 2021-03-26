/*     */ package jp.cssj.sakae.pdf.c.a.c;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.c.a.a;
/*     */ import jp.cssj.sakae.pdf.c.a.e;
/*     */ import jp.cssj.sakae.pdf.c.a.f;
/*     */ import jp.cssj.sakae.pdf.c.a.l;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.d;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ class a extends a {
/*     */   private static final long e = 1L;
/*     */   private final f f;
/*     */   private transient CharsetEncoder g;
/*     */   private transient CharBuffer h;
/*     */   private static final int i = 512;
/*     */   private transient ByteBuffer j;
/*     */   
/*  31 */   a(b source, String name, b fontRef, f cmap) { super((g)source, name, fontRef);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     this.g = null;
/*     */     
/*  38 */     this.h = null;
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.j = null;
/*     */     if (!d && cmap == null)
/*     */       throw new AssertionError(); 
/*  45 */     this.f = cmap; } public void a(b gc, h text) throws IOException, c { if (!d && text.i() <= 0) throw new AssertionError(); 
/*  46 */     if (gc instanceof jp.cssj.sakae.pdf.d.a) {
/*  47 */       d out = ((jp.cssj.sakae.pdf.d.a)gc).b();
/*     */       
/*  49 */       char[] ch = text.h();
/*  50 */       int clen = text.i();
/*  51 */       double[] xadvances = text.a(false);
/*  52 */       if (xadvances == null) {
/*  53 */         a(out, ch, 0, clen);
/*  54 */         out.b("Tj");
/*     */       } else {
/*  56 */         out.i();
/*  57 */         byte[] clens = text.k();
/*  58 */         int glen = text.l();
/*  59 */         int len = 0;
/*  60 */         int off = 0;
/*  61 */         double size = text.d().e();
/*  62 */         for (int i = 0; i < glen; i++) {
/*  63 */           double xadvance = xadvances[i];
/*  64 */           if (xadvance != 0.0D) {
/*     */             
/*  66 */             if (this.a.e() == 3) {
/*  67 */               xadvance = -xadvance;
/*     */             }
/*  69 */             if (len > 0) {
/*  70 */               a(out, ch, off, len);
/*  71 */               off += len;
/*  72 */               len = 0;
/*     */             } 
/*  74 */             out.a(-xadvance * 1000.0D / size);
/*     */           } 
/*  76 */           len += clens[i];
/*     */         } 
/*  78 */         if (len > 0) {
/*  79 */           a(out, ch, off, len);
/*     */         }
/*  81 */         out.j();
/*  82 */         out.b("TJ");
/*     */       } 
/*     */     } else {
/*  85 */       b source = (b)a();
/*  86 */       b.a(gc, (g)source, source.r(), text);
/*     */     }  }
/*     */ 
/*     */   
/*     */   private void a(d out, char[] ch, int off, int len) throws IOException {
/*  91 */     if (this.g == null) {
/*  92 */       this.g = this.f.a().a().newEncoder();
/*  93 */       this.h = CharBuffer.allocate(512);
/*     */     } 
/*  95 */     this.g.reset();
/*  96 */     int buffLen = (int)Math.ceil((len * this.g.maxBytesPerChar()));
/*  97 */     if (this.j == null || this.j.capacity() < buffLen) {
/*  98 */       this.j = ByteBuffer.allocate(buffLen);
/*     */     }
/* 100 */     while (len > 0) {
/* 101 */       this.h.clear();
/* 102 */       int llen = Math.min(len, 512);
/* 103 */       for (int i = 0; i < llen; i++) {
/* 104 */         char c = ch[i + off];
/*     */         
/* 106 */         c = (c == ' ') ? ' ' : c;
/*     */         
/* 108 */         if (!this.f.a().b(c)) {
/* 109 */           c = '?';
/*     */         }
/* 111 */         this.h.put(c);
/*     */       } 
/* 113 */       len -= llen;
/* 114 */       off += llen;
/* 115 */       this.h.flip();
/* 116 */       this.g.encode(this.h, this.j, (len == 0));
/*     */     } 
/* 118 */     this.g.flush(this.j);
/* 119 */     this.j.flip();
/* 120 */     out.a(this.j.array(), this.j.arrayOffset(), this.j.limit());
/* 121 */     this.j.clear();
/*     */   }
/*     */   
/*     */   public void a(c out, k xref) throws IOException {
/* 125 */     b source = (b)this.a;
/*     */ 
/*     */     
/* 128 */     out.a(this.c);
/* 129 */     out.g();
/* 130 */     out.a("Type");
/* 131 */     out.a("Font");
/* 132 */     out.k();
/* 133 */     out.a("Subtype");
/* 134 */     out.a("Type0");
/* 135 */     out.k();
/* 136 */     out.a("BaseFont");
/* 137 */     out.a(source.d());
/* 138 */     out.k();
/* 139 */     out.a("DescendantFonts");
/* 140 */     out.i();
/* 141 */     b xfontRef = xref.a();
/* 142 */     out.b(xfontRef);
/* 143 */     out.j();
/* 144 */     out.k();
/* 145 */     out.a("Encoding");
/* 146 */     out.a(b());
/* 147 */     out.h();
/* 148 */     out.a();
/*     */ 
/*     */     
/* 151 */     out.a(xfontRef);
/* 152 */     out.g();
/* 153 */     out.a("Type");
/* 154 */     out.a("Font");
/* 155 */     out.k();
/* 156 */     out.a("Subtype");
/* 157 */     out.a("CIDFontType2");
/* 158 */     out.k();
/* 159 */     out.a("BaseFont");
/* 160 */     out.a(source.d());
/* 161 */     out.k();
/* 162 */     out.a("FontDescriptor");
/* 163 */     b fontDescRef = xref.a();
/* 164 */     out.b(fontDescRef);
/* 165 */     out.k();
/* 166 */     out.a("CIDSystemInfo");
/* 167 */     out.g();
/* 168 */     out.a("Registry");
/* 169 */     out.c(d());
/* 170 */     out.a("Ordering");
/* 171 */     out.c(c());
/* 172 */     out.a("Supplement");
/* 173 */     out.a(e());
/* 174 */     out.k();
/* 175 */     out.a("CIDToGIDMap");
/* 176 */     out.a("Identity");
/* 177 */     out.k();
/* 178 */     out.h();
/*     */ 
/*     */     
/* 181 */     e.a((h)out, source.q());
/*     */     
/* 183 */     out.h();
/* 184 */     out.a();
/*     */ 
/*     */     
/* 187 */     out.a(fontDescRef);
/* 188 */     out.g();
/* 189 */     out.a("Type");
/* 190 */     out.a("FontDescriptor");
/* 191 */     out.k();
/* 192 */     out.a("FontName");
/* 193 */     out.a(source.d());
/* 194 */     out.k();
/* 195 */     e.a((h)out, source);
/* 196 */     out.a("FontBBox");
/* 197 */     b bbox = source.f();
/* 198 */     out.i();
/* 199 */     out.a(bbox.a);
/* 200 */     out.a(bbox.b);
/* 201 */     out.a(bbox.c);
/* 202 */     out.a(bbox.d);
/* 203 */     out.j();
/* 204 */     out.k();
/* 205 */     out.a("StemV");
/* 206 */     out.a(92);
/* 207 */     out.k();
/* 208 */     out.a("ItalicAngle");
/* 209 */     out.a(0);
/* 210 */     out.k();
/* 211 */     out.a("CapHeight");
/* 212 */     out.a(source.h());
/* 213 */     out.k();
/* 214 */     out.a("XHeight");
/* 215 */     out.a(source.l());
/* 216 */     out.k();
/* 217 */     out.a("Ascent");
/* 218 */     out.a(source.g());
/* 219 */     out.k();
/* 220 */     out.a("Descent");
/* 221 */     out.a(-source.i());
/* 222 */     out.k();
/*     */     
/* 224 */     out.h();
/* 225 */     out.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int c) {
/* 230 */     int gid = this.f.a().a(c);
/* 231 */     return gid;
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/* 235 */     if (this.a.e() == 3) {
/* 236 */       return 1000;
/*     */     }
/* 238 */     return c(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/* 242 */     b source = (b)this.a;
/* 243 */     l wa = source.q();
/* 244 */     short w = wa.a(gid);
/* 245 */     return w;
/*     */   }
/*     */   
/*     */   String b() {
/* 249 */     return this.f.b();
/*     */   }
/*     */   
/*     */   String c() {
/* 253 */     return this.f.d();
/*     */   }
/*     */   
/*     */   String d() {
/* 257 */     return this.f.c();
/*     */   }
/*     */   
/*     */   int e() {
/* 261 */     return this.f.e();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */