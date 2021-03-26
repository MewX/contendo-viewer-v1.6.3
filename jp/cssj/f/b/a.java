/*     */ package jp.cssj.f.b;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.f.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class a
/*     */   implements a
/*     */ {
/*     */   static {
/*  22 */     j = Logger.getLogger(a.class.getName());
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
/*  34 */   protected RandomAccessFile a = null;
/*     */   
/*  36 */   protected File b = null;
/*     */   
/*  38 */   protected List<a> c = null;
/*     */   
/*  40 */   protected a d = null; protected a e = null;
/*     */   
/*  42 */   protected long f = 0L; protected long g = 0L; private static final Logger j;
/*     */   private static final int k = 8192;
/*  44 */   protected int h = 0; private final int l;
/*     */   private final int m;
/*  46 */   private byte[] o = null;
/*     */   private final int n;
/*     */   
/*  49 */   protected class a { public a a = null; public a b = null;
/*     */     
/*     */     private final int d;
/*     */     
/*  53 */     private int e = 0;
/*     */     
/*  55 */     private byte[] f = null;
/*     */     
/*     */     private c g;
/*     */     
/*  59 */     private int h = 0;
/*     */     
/*     */     public a(a this$0, int id) {
/*  62 */       this.d = id;
/*     */     }
/*     */     
/*     */     public int a() {
/*  66 */       return this.d;
/*     */     }
/*     */     
/*     */     public int b() {
/*  70 */       return this.e;
/*     */     }
/*     */     
/*     */     public void a(byte[] buff, int pos, int len) throws IOException {
/*  74 */       if (this.g == null && this.e + len < a.a(this.c) && this.c.g + 
/*  75 */         a.a(this.c) <= a.b(this.c)) {
/*  76 */         if (this.f == null) {
/*  77 */           this.f = new byte[a.a(this.c)];
/*  78 */           this.c.g += a.a(this.c);
/*     */         } 
/*  80 */         System.arraycopy(buff, pos, this.f, this.e, len);
/*     */       } else {
/*  82 */         if (this.f != null) {
/*  83 */           b(this.f, 0, this.e);
/*  84 */           this.c.g -= a.a(this.c);
/*  85 */           this.f = null;
/*     */         } 
/*  87 */         b(buff, pos, len);
/*     */       } 
/*  89 */       this.e += len;
/*     */     }
/*     */     
/*     */     private void b(byte[] buff, int off, int len) throws IOException {
/*  93 */       if (this.g == null) {
/*  94 */         this.g = new c(10);
/*  95 */         this.g.a(this.c.h++);
/*     */       } 
/*  97 */       while (len > 0) {
/*  98 */         if (this.h == 8192) {
/*  99 */           this.g.a(this.c.h++);
/* 100 */           this.h = 0;
/*     */         } 
/* 102 */         int seg = this.g.b(this.g.b() - 1);
/* 103 */         int wlen = Math.min(len, 8192 - this.h);
/* 104 */         long wpos = seg * 8192L + this.h;
/* 105 */         this.c.a.seek(wpos);
/* 106 */         this.c.a.write(buff, off, wlen);
/* 107 */         this.h += wlen;
/* 108 */         off += wlen;
/* 109 */         len -= wlen;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void c() throws IOException {
/* 114 */       if (this.f != null) {
/* 115 */         if (this.e >= a.c(this.c)) {
/* 116 */           b(this.f, 0, this.e);
/* 117 */           this.c.g -= a.a(this.c);
/* 118 */           this.f = null;
/* 119 */         } else if (this.e < this.f.length) {
/* 120 */           byte[] temp = new byte[this.e];
/* 121 */           System.arraycopy(this.f, 0, temp, 0, temp.length);
/* 122 */           this.c.g -= (this.f.length - this.e);
/* 123 */           this.f = temp;
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     public void a(OutputStream out) throws IOException {
/* 129 */       if (this.g == null) {
/* 130 */         if (this.f != null) {
/* 131 */           out.write(this.f, 0, this.e);
/*     */         }
/*     */       } else {
/* 134 */         if (a.d(this.c) == null) {
/* 135 */           a.a(this.c, new byte[8192]);
/*     */         }
/* 137 */         byte[] buff = a.d(this.c);
/* 138 */         for (int i = 0; i < this.g.b() - 1; i++) {
/* 139 */           int j = this.g.b(i);
/* 140 */           long l = j * 8192L;
/* 141 */           this.c.a.seek(l);
/* 142 */           this.c.a.readFully(buff);
/* 143 */           out.write(buff);
/*     */         } 
/* 145 */         int seg = this.g.b(this.g.b() - 1);
/* 146 */         long rpos = seg * 8192L;
/* 147 */         this.c.a.seek(rpos);
/* 148 */         this.c.a.readFully(buff, 0, this.h);
/* 149 */         out.write(buff, 0, this.h);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void d() {
/* 154 */       this.f = null;
/*     */     } }
/*     */ 
/*     */   
/*     */   public a(int fragmentBufferSize, int totalBufferSize, int threshold) {
/* 159 */     this.l = fragmentBufferSize;
/* 160 */     this.m = totalBufferSize;
/* 161 */     this.n = threshold;
/*     */   }
/*     */   
/*     */   public a() {
/* 165 */     this(8192, 2097152, 1024);
/*     */   }
/*     */   
/*     */   protected int f() throws IOException {
/* 169 */     if (this.c == null) {
/* 170 */       this.c = new ArrayList<>();
/* 171 */       this.b = File.createTempFile("cssj-rsr-", ".frgs");
/* 172 */       this.b.deleteOnExit();
/* 173 */       this.a = new RandomAccessFile(this.b, "rw");
/*     */     } 
/* 175 */     return this.c.size();
/*     */   }
/*     */   
/*     */   protected a c(int id) throws IOException {
/* 179 */     return this.c.get(id);
/*     */   }
/*     */   
/*     */   protected void a(int id, a frg) {
/* 183 */     if (!i && id != this.c.size()) throw new AssertionError(); 
/* 184 */     this.c.add(frg);
/*     */   }
/*     */   
/*     */   public a.a d() {
/* 188 */     long[] idToPosition = new long[this.c.size()];
/* 189 */     long position = 0L;
/* 190 */     a frg = this.d;
/* 191 */     while (frg != null) {
/*     */       
/* 193 */       idToPosition[frg.a()] = position;
/* 194 */       position += frg.b();
/* 195 */       frg = frg.b;
/*     */     } 
/* 197 */     return new a.a(this, idToPosition) {
/*     */         public long a(int id) {
/* 199 */           long position = this.a[id];
/* 200 */           return position;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   public void b() throws IOException {
/* 210 */     int id = f();
/* 211 */     a bk = new a(this, id);
/* 212 */     if (this.d == null) {
/* 213 */       this.d = bk;
/*     */     } else {
/* 215 */       this.e.b = bk;
/* 216 */       bk.a = this.e;
/*     */     } 
/* 218 */     a(id, bk);
/* 219 */     this.e = bk;
/*     */   }
/*     */   
/*     */   public void a(int anchorId) throws IOException {
/* 223 */     int id = f();
/* 224 */     a anchor = c(anchorId);
/* 225 */     a bk = new a(this, id);
/* 226 */     a(id, bk);
/* 227 */     bk.a = anchor.a;
/* 228 */     bk.b = anchor;
/* 229 */     anchor.a.b = bk;
/* 230 */     anchor.a = bk;
/* 231 */     if (this.d == anchor) {
/* 232 */       this.d = bk;
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(int id, byte[] b, int off, int len) throws IOException {
/* 237 */     a frg = c(id);
/* 238 */     frg.a(b, off, len);
/* 239 */     this.f += len;
/*     */   }
/*     */   
/*     */   public void b(int id) throws IOException {
/* 243 */     a frg = c(id);
/* 244 */     frg.c();
/*     */   }
/*     */   
/*     */   protected void a(OutputStream out) throws IOException {
/* 248 */     if (this.d == null) {
/*     */       
/* 250 */       h();
/*     */       return;
/*     */     } 
/* 253 */     if (j.isLoggable(Level.FINE)) {
/* 254 */       int total = this.c.size();
/* 255 */       int onMemory = 0;
/* 256 */       for (int i = 0; i < total; i++) {
/* 257 */         a f = this.c.get(i);
/* 258 */         if (a.a(f) == null) {
/* 259 */           onMemory++;
/*     */         }
/*     */       } 
/* 262 */       j.fine(total + "個のフラグメントが生成されました。");
/* 263 */       j.fine("うち" + onMemory + "個がオンメモリーで、" + (total - onMemory) + "個がディスク上にあります。");
/*     */     } 
/*     */     
/* 266 */     a bk = this.d;
/* 267 */     while (bk != null) {
/* 268 */       bk.a(out);
/* 269 */       bk.d();
/* 270 */       bk = bk.b;
/*     */     } 
/* 272 */     h();
/*     */   }
/*     */   
/*     */   public long g() {
/* 276 */     return this.f;
/*     */   }
/*     */   
/*     */   private void h() {
/* 280 */     if (j.isLoggable(Level.FINE)) {
/* 281 */       j.fine("リソースを片付けます。");
/*     */     }
/* 283 */     if (this.a != null) {
/*     */       try {
/* 285 */         this.a.close();
/* 286 */       } catch (Exception e) {
/* 287 */         j.log(Level.FINE, "一時ファイルをクローズできませんでした。", e);
/*     */       } 
/* 289 */       this.a = null;
/*     */     } 
/* 291 */     if (this.b != null) {
/*     */       try {
/* 293 */         this.b.delete();
/* 294 */       } catch (Exception e) {
/* 295 */         j.log(Level.FINE, "一時ファイルを削除できませんでした。", e);
/*     */       } 
/* 297 */       this.b = null;
/*     */     } 
/* 299 */     this.d = null;
/* 300 */     this.e = null;
/* 301 */     this.c = null;
/* 302 */     this.f = 0L;
/* 303 */     this.g = 0L;
/* 304 */     this.h = 0;
/*     */   }
/*     */   
/*     */   public void e() {
/* 308 */     h();
/*     */   }
/*     */   
/*     */   protected void finalize() throws IOException {
/* 312 */     if (this.d != null || this.a != null || this.b != null)
/* 313 */       e(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */