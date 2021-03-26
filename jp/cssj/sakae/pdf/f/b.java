/*     */ package jp.cssj.sakae.pdf.f;
/*     */ 
/*     */ import jp.cssj.sakae.a.h;
/*     */ import jp.cssj.sakae.pdf.a.a;
/*     */ import jp.cssj.sakae.pdf.c.a;
/*     */ import jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*     */   private h t;
/*  12 */   private int u = 1400;
/*     */ 
/*     */   
/*     */   public static final int a = 1200;
/*     */   
/*     */   public static final int b = 1300;
/*     */   
/*     */   public static final int c = 1400;
/*     */   
/*     */   public static final int d = 1412;
/*     */   
/*     */   public static final int e = 1421;
/*     */   
/*     */   public static final int f = 1500;
/*     */   
/*     */   public static final int g = 1600;
/*     */   
/*     */   public static final int h = 1700;
/*     */   
/*  31 */   private short v = 1;
/*     */ 
/*     */   
/*     */   public static final short i = 0;
/*     */   
/*     */   public static final short j = 1;
/*     */   
/*     */   public static final short k = 2;
/*     */   
/*  40 */   private short w = 0;
/*     */ 
/*     */   
/*     */   public static final short l = 0;
/*     */   
/*     */   public static final short m = 1;
/*     */   
/*  47 */   private short x = 0;
/*     */   
/*     */   public static final short n = 0;
/*     */   
/*     */   public static final short o = 1;
/*     */   
/*     */   public static final short p = 2;
/*     */   
/*  55 */   private int y = 200;
/*     */ 
/*     */ 
/*     */   
/*  59 */   private String z = "UTF-8";
/*     */   
/*     */   private boolean A = false;
/*     */   
/*  63 */   private a B = null;
/*     */   
/*  65 */   private short C = 0;
/*     */   
/*     */   public static final short q = 0;
/*     */   
/*     */   public static final short r = 1;
/*     */   
/*     */   public static final short s = 2;
/*     */   
/*  73 */   private int D = 0; private int E = 0;
/*     */   
/*  75 */   private byte[] F = null;
/*     */   
/*  77 */   private e G = new e();
/*     */   
/*  79 */   private j H = new j();
/*     */   
/*  81 */   private a I = null;
/*     */   
/*     */   public synchronized h a() {
/*  84 */     if (this.t == null) {
/*  85 */       this.t = a.a();
/*     */     }
/*  87 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(h fsm) {
/*  96 */     this.t = fsm;
/*     */   }
/*     */   
/*     */   public short b() {
/* 100 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(short compression) {
/* 109 */     this.v = compression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(short jpegImage) {
/* 118 */     this.w = jpegImage;
/*     */   }
/*     */   
/*     */   public short c() {
/* 122 */     return this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(short imageCompression) {
/* 131 */     this.x = imageCompression;
/*     */   }
/*     */   
/*     */   public short d() {
/* 135 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int imageCompressionLossless) {
/* 144 */     this.y = imageCompressionLossless;
/*     */   }
/*     */   
/*     */   public int e() {
/* 148 */     return this.y;
/*     */   }
/*     */   
/*     */   public int f() {
/* 152 */     return this.D;
/*     */   }
/*     */   
/*     */   public void b(int maxImageWidth) {
/* 156 */     this.D = maxImageWidth;
/*     */   }
/*     */   
/*     */   public int g() {
/* 160 */     return this.E;
/*     */   }
/*     */   
/*     */   public void c(int maxImageHeight) {
/* 164 */     this.E = maxImageHeight;
/*     */   }
/*     */   
/*     */   public int h() {
/* 168 */     return this.u;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(int version) {
/* 177 */     this.u = version;
/*     */   }
/*     */   
/*     */   public String i() {
/* 181 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String platformEncoding) {
/* 190 */     this.z = platformEncoding;
/*     */   }
/*     */   
/*     */   public boolean j() {
/* 194 */     return this.A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean bookmarks) {
/* 203 */     this.A = bookmarks;
/*     */   }
/*     */   
/*     */   public a k() {
/* 207 */     return this.B;
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
/*     */   public void a(a encription) {
/* 219 */     this.B = encription;
/*     */   }
/*     */   
/*     */   public short l() {
/* 223 */     return this.C;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(short colorMode) {
/* 232 */     this.C = colorMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] fileId) {
/* 241 */     if (fileId != null && fileId.length != 16) {
/* 242 */       throw new IllegalArgumentException("ファイルIDは16バイトのバイト列でなければなりません");
/*     */     }
/* 244 */     this.F = fileId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] m() {
/* 253 */     return this.F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(e metaInfo) {
/* 262 */     this.G = metaInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e n() {
/* 271 */     return this.G;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(a openAction) {
/* 280 */     if (openAction != null) {
/* 281 */       openAction.a(this);
/*     */     }
/* 283 */     this.I = openAction;
/*     */   }
/*     */   
/*     */   public a o() {
/* 287 */     return this.I;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(j viewerPreferences) {
/* 296 */     this.H = viewerPreferences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j p() {
/* 305 */     return this.H;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */