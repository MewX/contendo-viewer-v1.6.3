/*     */ package jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import jp.cssj.f.a.b;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.g.a.a;
/*     */ import jp.cssj.sakae.pdf.g.a.b;
/*     */ import jp.cssj.sakae.pdf.g.b.e;
/*     */ import jp.cssj.sakae.pdf.g.c.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class j
/*     */   extends c
/*     */ {
/*     */   private final n i;
/*     */   private int j;
/*  26 */   private int k = -1;
/*     */ 
/*     */   
/*  29 */   private int l = 0;
/*     */ 
/*     */   
/*  32 */   private j m = null;
/*     */ 
/*     */   
/*  35 */   private int n = 0;
/*     */ 
/*     */   
/*     */   private b o;
/*     */   
/*  40 */   private byte[] p = null;
/*     */ 
/*     */   
/*     */   public j(OutputStream out, n pdfWriter, int id, int nextId, b currentRef) throws IOException {
/*  44 */     super(out, pdfWriter.a().i());
/*  45 */     this.i = pdfWriter;
/*  46 */     this.j = id;
/*  47 */     this.k = nextId;
/*  48 */     this.o = currentRef;
/*     */   }
/*     */   
/*     */   protected byte[] b() {
/*  52 */     if (this.p == null) {
/*  53 */       this.p = new byte[8192];
/*     */     }
/*  55 */     return this.p;
/*     */   }
/*     */   
/*     */   protected j c() throws IOException {
/*  59 */     close();
/*  60 */     int id = this.i.e();
/*  61 */     if (this.k == -1) {
/*  62 */       this.i.g.b();
/*     */     } else {
/*  64 */       this.i.g.a(this.k);
/*     */     } 
/*  66 */     b b1 = new b(this.i.g, id);
/*  67 */     this.j = this.i.e();
/*  68 */     if (this.k == -1) {
/*  69 */       this.i.g.b();
/*     */     } else {
/*  71 */       this.i.g.a(this.k);
/*     */     } 
/*  73 */     j newFragOut = new j((OutputStream)b1, this.i, id, this.j, this.o);
/*  74 */     this.out = (OutputStream)new b(this.i.g, this.j);
/*  75 */     this.l = 0;
/*  76 */     return newFragOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(b ref) throws IOException {
/*  86 */     m();
/*  87 */     ((f)ref).a(this.j, e());
/*  88 */     a(ref.a);
/*  89 */     a(ref.b);
/*  90 */     b("obj");
/*  91 */     k();
/*  92 */     if (this.o != null) {
/*  93 */       throw new IllegalStateException("既にオブジェクトの中です:" + this.o);
/*     */     }
/*  95 */     this.o = ref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() throws IOException {
/* 104 */     f("endobj");
/* 105 */     if (this.o == null) {
/* 106 */       throw new IllegalStateException("既にオブジェクトの外です");
/*     */     }
/* 108 */     this.o = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream a(short mode) throws IOException {
/* 117 */     if (this.m != null) {
/* 118 */       throw new IllegalStateException("ストリームをネストすることはできません:" + this.m);
/*     */     }
/* 120 */     g();
/*     */     
/* 122 */     return b(mode);
/*     */   } public OutputStream b(short mode) throws IOException { a a2;
/*     */     b b1;
/*     */     DeflaterOutputStream deflaterOutputStream;
/*     */     a a1;
/* 127 */     if (this.m != null) {
/* 128 */       throw new IllegalStateException("ストリームをネストすることはできません:" + this.m);
/*     */     }
/*     */     
/* 131 */     switch (mode) {
/*     */       case 0:
/*     */         break;
/*     */       
/*     */       case 2:
/* 136 */         switch (this.i.h.b()) {
/*     */ 
/*     */           
/*     */           case 2:
/* 140 */             a("Filter");
/* 141 */             i();
/* 142 */             a("ASCII85Decode");
/* 143 */             a("FlateDecode");
/* 144 */             j();
/* 145 */             m();
/*     */             break;
/*     */           case 1:
/* 148 */             a("Filter");
/* 149 */             i();
/* 150 */             a("FlateDecode");
/* 151 */             j();
/* 152 */             m();
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case 1:
/* 158 */         switch (this.i.h.b()) {
/*     */           case 0:
/* 160 */             a("Filter");
/* 161 */             i();
/* 162 */             a("ASCIIHexDecode");
/* 163 */             j();
/* 164 */             m();
/*     */             break;
/*     */           case 2:
/* 167 */             a("Filter");
/* 168 */             i();
/* 169 */             a("ASCII85Decode");
/* 170 */             a("FlateDecode");
/* 171 */             j();
/* 172 */             m();
/*     */             break;
/*     */           case 1:
/* 175 */             a("Filter");
/* 176 */             i();
/* 177 */             a("FlateDecode");
/* 178 */             j();
/* 179 */             m();
/*     */             break;
/*     */         } 
/* 182 */         throw new IllegalStateException();
/*     */ 
/*     */       
/*     */       default:
/* 186 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 189 */     a("Length");
/* 190 */     write(32);
/* 191 */     this.m = c();
/* 192 */     k();
/* 193 */     h();
/* 194 */     f("stream");
/* 195 */     flush();
/* 196 */     this.n = e();
/*     */     
/* 198 */     OutputStream out = new FilterOutputStream(this, (OutputStream)this) {
/*     */         public void close() throws IOException {
/* 200 */           this.a.d();
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 205 */     if (this.i.j != null) {
/* 206 */       out = this.i.j.a(this.o).a(out);
/*     */     }
/*     */ 
/*     */     
/* 210 */     switch (mode)
/*     */     
/*     */     { 
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
/*     */       case 0:
/* 250 */         return out;
/*     */       case 2: switch (this.i.h.b()) { case 0: break;
/*     */           case 2: out = new DeflaterOutputStream((OutputStream)new a(out)); break;
/*     */           case 1: out = new DeflaterOutputStream(out); break;
/*     */           default: throw new IllegalArgumentException(); }  a2 = new a(out, b());
/*     */       case 1: switch (this.i.h.b()) { case 0: b1 = new b((OutputStream)a2); break;
/*     */           case 2: deflaterOutputStream = new DeflaterOutputStream((OutputStream)new a((OutputStream)b1)); break;
/*     */           case 1: deflaterOutputStream = new DeflaterOutputStream(deflaterOutputStream); break;
/*     */           default:
/* 259 */             throw new IllegalArgumentException(); }  a1 = new a(deflaterOutputStream, b()); }  throw new IllegalArgumentException(); } protected void d() throws IOException { this.m.a(e() - this.n);
/* 260 */     this.m.close();
/* 261 */     this.m = null;
/* 262 */     this.n = 0;
/* 263 */     k();
/* 264 */     f("endstream"); }
/*     */ 
/*     */   
/*     */   public void b(int i) throws IOException {
/* 268 */     if (this.i.j == null) {
/* 269 */       super.b(i);
/*     */       return;
/*     */     } 
/* 272 */     byte[] data = new byte[2];
/* 273 */     data[0] = (byte)(i >> 8 & 0xFF);
/* 274 */     data[1] = (byte)(i & 0xFF);
/* 275 */     b(data, 0, data.length);
/*     */   }
/*     */   
/*     */   public void a(int[] a, int off, int len) throws IOException {
/* 279 */     if (this.i.j == null) {
/* 280 */       super.a(a, off, len);
/*     */       return;
/*     */     } 
/* 283 */     byte[] data = new byte[len * 2];
/* 284 */     for (int i = 0; i < len; i++) {
/* 285 */       int k = a[i + off];
/* 286 */       data[i * 2] = (byte)(k >> 8 & 0xFF);
/* 287 */       data[i * 2 + 1] = (byte)(k & 0xFF);
/*     */     } 
/* 289 */     b(data, 0, data.length);
/*     */   }
/*     */   
/*     */   protected void b(byte[] data, int off, int len) throws IOException {
/* 293 */     e e = this.i.j.a(this.o);
/* 294 */     if (e.a()) {
/* 295 */       data = e.a(data, off, len);
/* 296 */       off = 0;
/* 297 */       len = data.length;
/*     */     } else {
/* 299 */       e.b(data, off, len);
/*     */     } 
/* 301 */     a(data, off, len);
/*     */   }
/*     */   
/*     */   public void c(String str) throws IOException {
/* 305 */     if (this.i.j == null) {
/* 306 */       super.c(str);
/*     */       return;
/*     */     } 
/* 309 */     byte[] data = str.getBytes("iso-8859-1");
/* 310 */     b(data, 0, data.length);
/*     */   }
/*     */   
/*     */   public void e(String text) throws IOException {
/* 314 */     if (this.i.j == null) {
/* 315 */       super.e(text);
/*     */       return;
/*     */     } 
/* 318 */     d(text);
/*     */   }
/*     */   
/*     */   public void d(String text) throws IOException {
/* 322 */     if (this.i.j == null) {
/* 323 */       super.d(text);
/*     */       return;
/*     */     } 
/* 326 */     byte[] data = new byte[text.length() * 2 + 2];
/* 327 */     data[0] = -2;
/* 328 */     data[1] = -1;
/* 329 */     for (int i = 0; i < text.length(); i++) {
/* 330 */       char c1 = text.charAt(i);
/* 331 */       data[i * 2 + 2] = (byte)(c1 >> 8 & 0xFF);
/* 332 */       data[i * 2 + 3] = (byte)(c1 & 0xFF);
/*     */     } 
/* 334 */     b(data, 0, data.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int e() {
/* 343 */     return this.l;
/*     */   }
/*     */   
/*     */   protected int n() {
/* 347 */     return this.j;
/*     */   }
/*     */   
/*     */   public void write(byte[] buff, int off, int len) throws IOException {
/* 351 */     super.write(buff, off, len);
/* 352 */     this.l += len;
/*     */   }
/*     */   
/*     */   public void write(byte[] buff) throws IOException {
/* 356 */     super.write(buff);
/* 357 */     this.l += buff.length;
/*     */   }
/*     */   
/*     */   public void write(int i) throws IOException {
/* 361 */     super.write(i);
/* 362 */     this.l++;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 366 */     if (this.m != null) {
/* 367 */       throw new IllegalStateException("ストリームが閉じられていません");
/*     */     }
/* 369 */     if (this.out == null) {
/* 370 */       throw new IllegalStateException("既に閉じられています");
/*     */     }
/* 372 */     super.close();
/* 373 */     this.out = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */