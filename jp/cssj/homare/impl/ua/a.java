/*     */ package jp.cssj.homare.impl.ua;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import jp.cssj.c.b;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.homare.css.e.c;
/*     */ import jp.cssj.homare.css.e.d;
/*     */ import jp.cssj.homare.css.e.f;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.b.d;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.css.f.w;
/*     */ import jp.cssj.homare.css.f.x;
/*     */ import jp.cssj.homare.ua.ImageLoader;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.b;
/*     */ import jp.cssj.homare.ua.f;
/*     */ import jp.cssj.homare.ua.i;
/*     */ import jp.cssj.homare.ua.l;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
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
/*     */ public abstract class a
/*     */   implements m
/*     */ {
/*  48 */   private l c = new l();
/*     */   
/*  50 */   private i t = new i();
/*     */   
/*  52 */   private f u = new f();
/*     */   
/*  54 */   private Map<String, String> v = null;
/*     */   
/*  56 */   private byte w = 0;
/*     */   
/*     */   private Locale x;
/*     */   
/*  60 */   private String[] y = null;
/*     */   
/*     */   private double z;
/*     */   
/*     */   private E A;
/*     */   
/*     */   private jp.cssj.homare.css.f.a[] B;
/*     */   
/*     */   private n C;
/*     */   
/*     */   private n D;
/*     */   
/*  72 */   private x E = null;
/*     */   
/*     */   private jp.cssj.homare.css.f.a F;
/*     */   
/*     */   private double G;
/*     */   
/*     */   private E H;
/*     */   
/*  80 */   private ad I = (ad)H.a;
/*     */   
/*  82 */   private double J = -1.0D; private double K = -1.0D;
/*     */   
/*  84 */   private static final AffineTransform L = new AffineTransform();
/*     */   
/*  86 */   private AffineTransform M = null;
/*     */   
/*  88 */   private jp.cssj.b.b.a N = null;
/*     */   
/*     */   private c O;
/*     */   
/*     */   private e P;
/*     */   
/*  94 */   private d Q = null;
/*     */ 
/*     */ 
/*     */   
/*  98 */   private byte R = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected double a;
/*     */ 
/*     */ 
/*     */   
/*     */   protected double b;
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] S;
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] T;
/*     */ 
/*     */   
/*     */   private String[] U;
/*     */ 
/*     */ 
/*     */   
/*     */   public l a() {
/* 122 */     return this.c;
/*     */   }
/*     */   
/*     */   public i b() {
/* 126 */     return this.t;
/*     */   }
/*     */   
/*     */   public f c() {
/* 130 */     return this.u;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final String a(String name) {
/* 136 */     if (this.v == null) {
/* 137 */       return null;
/*     */     }
/* 139 */     String value = this.v.get(name);
/* 140 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(String name, String value) {
/* 145 */     if (this.v == null) {
/* 146 */       if (value == null || value.length() == 0) {
/*     */         return;
/*     */       }
/* 149 */       this.v = new HashMap<>();
/*     */     } 
/* 151 */     if (value == null || value.length() == 0) {
/* 152 */       this.v.remove(name);
/*     */     } else {
/* 154 */       this.v.put(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(Map<String, String> props) {
/* 159 */     this.v = null;
/* 160 */     for (Iterator<Map.Entry<String, String>> iterator = props.entrySet().iterator(); iterator.hasNext(); ) {
/* 161 */       Map.Entry<String, String> entry = iterator.next();
/* 162 */       a(entry.getKey(), entry.getValue());
/*     */     } 
/*     */ 
/*     */     
/* 166 */     if (this.v != null) {
/* 167 */       for (int j = 0;; j++) {
/* 168 */         String prefix = "output.meta." + j + ".";
/* 169 */         String name = this.v.get(prefix + "name");
/* 170 */         if (name == null) {
/*     */           break;
/*     */         }
/* 173 */         String value = this.v.get(prefix + "value");
/* 174 */         b(name, value);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(byte mode) {
/* 181 */     if (this.w != mode) {
/* 182 */       a((short)4097);
/*     */     }
/* 184 */     this.w = mode;
/*     */   }
/*     */   
/*     */   protected void b(byte mode) {
/* 188 */     if (this.w == mode || this.w == 2) {
/* 189 */       a((short)4097);
/* 190 */       throw new jp.cssj.homare.ua.a(this.w);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Locale d() {
/* 195 */     return this.x;
/*     */   }
/*     */   
/*     */   public boolean b(String mediaTypes) {
/* 199 */     if (mediaTypes == null || mediaTypes.length() == 0) {
/* 200 */       return true;
/*     */     }
/* 202 */     if (this.y == null) {
/*     */       
/* 204 */       String media = B.Q.a(this);
/* 205 */       this.y = media.split("[\\s]+");
/*     */     } 
/* 207 */     for (int j = 0; j < this.y.length; j++) {
/* 208 */       if (mediaTypes.indexOf(this.y[j]) != -1) {
/* 209 */         return true;
/*     */       }
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */   
/*     */   public double e() {
/* 216 */     return this.z;
/*     */   }
/*     */   
/*     */   public E f() {
/* 220 */     return this.A;
/*     */   }
/*     */   
/*     */   public jp.cssj.homare.css.f.a c(byte type) {
/* 224 */     return this.B[type - 1];
/*     */   }
/*     */   
/*     */   public n g() {
/* 228 */     return this.C;
/*     */   }
/*     */   
/*     */   public n h() {
/* 232 */     return this.D;
/*     */   }
/*     */   
/*     */   public x i() {
/* 236 */     if (this.E == null) {
/* 237 */       String str = B.E.a(this);
/* 238 */       this.E = d.c(str);
/*     */     } 
/* 240 */     return this.E;
/*     */   }
/*     */   
/*     */   public d j() {
/* 244 */     if (this.Q == null) {
/* 245 */       String s = B.X.a(this);
/* 246 */       int pdfVersion = B.ae.a(this);
/* 247 */       if (pdfVersion == 7 || pdfVersion == 8) {
/* 248 */         this.Q = d.b(s);
/* 249 */         if (this.Q == null) {
/* 250 */           this.Q = d.e;
/*     */         }
/*     */       } else {
/* 253 */         this.Q = d.a(s);
/* 254 */         if (this.Q == null) {
/* 255 */           a((short)10244, B.X.a, s);
/* 256 */           this.Q = d.a;
/*     */         } 
/*     */       } 
/*     */     } 
/* 260 */     return this.Q;
/*     */   }
/*     */   
/*     */   public final double d(byte absoluteFontSize) {
/* 264 */     double size = this.F.c();
/* 265 */     switch (absoluteFontSize)
/*     */     { case 1:
/* 267 */         size = size * 3.0D / 5.0D;
/*     */       
/*     */       case 2:
/* 270 */         size = size * 3.0D / 4.0D;
/*     */       
/*     */       case 3:
/* 273 */         size = size * 8.0D / 9.0D;
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
/*     */       case 4:
/* 289 */         return size * k();
/*     */       case 5: size = size * 6.0D / 5.0D;
/*     */       case 6: size = size * 3.0D / 2.0D;
/*     */       case 7:
/* 293 */         size *= 2.0D; }  throw new IllegalArgumentException(); } public double k() { if (this.K == -1.0D) {
/* 294 */       this.K = B.F.a(this);
/*     */     }
/* 296 */     return this.K; }
/*     */ 
/*     */   
/*     */   public double a(double fontSize) {
/* 300 */     return fontSize * this.G;
/*     */   }
/*     */   
/*     */   public double b(double fontSize) {
/* 304 */     return fontSize / this.G;
/*     */   }
/*     */   
/*     */   public E l() {
/* 308 */     return this.H;
/*     */   }
/*     */   
/*     */   public ad m() {
/* 312 */     return this.I;
/*     */   }
/*     */   
/*     */   public double n() {
/* 316 */     if (this.J == -1.0D) {
/* 317 */       this.J = B.T.a(this);
/*     */     }
/* 319 */     return this.J;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(E defaultMarkerOffset) {
/* 327 */     this.A = defaultMarkerOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Locale locale) {
/* 335 */     this.x = locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(double normalLineHeight) {
/* 343 */     this.z = normalLineHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(jp.cssj.homare.css.f.a[] borderTable) {
/* 351 */     if (borderTable.length != 3) {
/* 352 */       throw new IllegalArgumentException();
/*     */     }
/* 354 */     this.B = borderTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(n defaultColor) {
/* 362 */     this.C = defaultColor;
/*     */   }
/*     */   
/*     */   public void b(n matColor) {
/* 366 */     this.D = matColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(double fontScaleRatio) {
/* 374 */     this.G = fontScaleRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(jp.cssj.homare.css.f.a mediumFontSize) {
/* 382 */     this.F = mediumFontSize;
/*     */   }
/*     */   
/*     */   public void b(E minSize) {
/* 386 */     this.H = minSize;
/*     */   }
/*     */   
/*     */   public void a(ad maxSize) {
/* 390 */     this.I = maxSize;
/*     */   }
/*     */   
/*     */   protected AffineTransform o() {
/* 394 */     if (this.M == null) {
/* 395 */       double scale = f.a(this, 1.0D, (short)17, (short)21);
/* 396 */       if (scale == 0.0D) {
/* 397 */         this.M = L;
/*     */       } else {
/* 399 */         this.M = AffineTransform.getScaleInstance(scale, scale);
/*     */       } 
/*     */     } 
/* 402 */     return this.M;
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.b.b.a messageHandler) {
/* 406 */     this.N = messageHandler;
/*     */   }
/*     */   
/*     */   public final void a(short code, String[] args) {
/* 410 */     if (this.N == null) {
/*     */       return;
/*     */     }
/* 413 */     this.N.a(code, args, null);
/*     */   }
/*     */   
/*     */   public final void a(short code) {
/* 417 */     a(code, (String[])null);
/*     */   }
/*     */   
/* 420 */   public a() { this.S = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 430 */     this.T = null;
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
/* 441 */     this.U = null; a(Locale.getDefault()); c(1.2D); a((E)w.a(1.0D)); b((E)jp.cssj.homare.css.f.a.a); a((ad)jp.cssj.homare.css.f.a.a(this, 14400.0D, (short)21)); a(new jp.cssj.homare.css.f.a[] { jp.cssj.homare.css.f.a.a(this, 1.0D), jp.cssj.homare.css.f.a.a(this, 2.0D), jp.cssj.homare.css.f.a.a(this, 3.0D) }); d(1.2D); a(jp.cssj.homare.css.f.a.a(this, 12.0D)); a(c.b); b(c.o); }
/*     */   public final void a(short code, String arg0) { if (this.S == null)
/*     */       this.S = new String[1];  this.S[0] = arg0;
/* 444 */     a(code, this.S); } public final void a(short code, String arg0, String arg1, String arg2) { if (this.U == null) {
/* 445 */       this.U = new String[3];
/*     */     }
/* 447 */     this.U[0] = arg0;
/* 448 */     this.U[1] = arg1;
/* 449 */     this.U[2] = arg2;
/* 450 */     a(code, this.U); }
/*     */   public final void a(short code, String arg0, String arg1) { if (this.T == null)
/*     */       this.T = new String[2];  this.T[0] = arg0;
/*     */     this.T[1] = arg1;
/* 454 */     a(code, this.T); } public void a(c resolver) { this.O = resolver; }
/*     */ 
/*     */   
/*     */   public c p() {
/* 458 */     return this.O;
/*     */   }
/*     */   
/*     */   public b b(URI uri) throws IOException {
/*     */     try {
/* 463 */       return this.O.b(uri);
/* 464 */     } catch (SecurityException securityException) {
/* 465 */       a((short)10260, uri.toString());
/* 466 */       IOException ioe = new IOException(securityException.getMessage());
/* 467 */       ioe.initCause(securityException);
/* 468 */       throw ioe;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(b source) {
/* 473 */     this.O.a(source);
/*     */   }
/*     */   
/*     */   public void a(e fontManager) {
/* 477 */     this.P = fontManager;
/*     */   }
/*     */   
/*     */   public e q() {
/* 481 */     return this.P;
/*     */   }
/*     */   
/*     */   protected b b(b source) throws IOException {
/* 485 */     ImageLoader loader = (ImageLoader)b.a().a(ImageLoader.class, source);
/* 486 */     b image = loader.loadImage(this, source);
/* 487 */     return image;
/*     */   }
/*     */   public b c(b source) throws IOException {
/*     */     jp.cssj.sakae.c.b.a.a a1;
/* 491 */     b image = b(source);
/* 492 */     AffineTransform pixelToUnit = o();
/* 493 */     if (!pixelToUnit.isIdentity()) {
/* 494 */       a1 = new jp.cssj.sakae.c.b.a.a(image, this.M);
/*     */     }
/* 496 */     return (b)a1;
/*     */   }
/*     */   
/*     */   public void e(byte boundSide) {
/* 500 */     this.R = boundSide;
/*     */   }
/*     */   
/*     */   public byte r() {
/* 504 */     return this.R;
/*     */   }
/*     */   
/*     */   public final b a(double pageWidth, double pageHeight) {
/* 508 */     this.a = pageWidth;
/* 509 */     this.b = pageHeight;
/* 510 */     return s();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(b gc) throws IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void t() throws b, IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(byte mode) {
/* 524 */     this.K = -1.0D;
/* 525 */     this.J = -1.0D;
/* 526 */     this.M = null;
/* 527 */     if (mode != 0) {
/* 528 */       int pages = b().c();
/* 529 */       this.t = new i();
/* 530 */       a().c().a();
/*     */       
/* 532 */       b().a(0, true).a("pages", pages);
/*     */     } 
/* 534 */     this.u = new f();
/*     */   }
/*     */   
/*     */   public boolean u() {
/* 538 */     return false;
/*     */   }
/*     */   
/*     */   public void v() {}
/*     */   
/*     */   protected abstract b s();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */