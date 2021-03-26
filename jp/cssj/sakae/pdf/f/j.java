/*     */ package jp.cssj.sakae.pdf.f;
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
/*     */ public class j
/*     */ {
/*     */   private boolean r = false;
/*     */   private boolean s = false;
/*     */   private boolean t = false;
/*     */   private boolean u = false;
/*     */   private boolean v = false;
/*     */   private boolean w = false;
/*     */   public static final byte a = 1;
/*     */   public static final byte b = 2;
/*     */   public static final byte c = 3;
/*     */   public static final byte d = 4;
/*  40 */   private byte x = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte e = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte f = 2;
/*     */ 
/*     */ 
/*     */   
/*  52 */   private byte y = 1;
/*     */   
/*     */   public static final byte g = 1;
/*     */   
/*     */   public static final byte h = 2;
/*     */   
/*     */   public static final byte i = 3;
/*     */   public static final byte j = 4;
/*     */   public static final byte k = 5;
/*  61 */   private byte z = 2;
/*  62 */   private byte A = 2;
/*  63 */   private byte B = 2;
/*  64 */   private byte C = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte l = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte m = 2;
/*     */ 
/*     */ 
/*     */   
/*  76 */   private byte D = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte n = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte o = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte p = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte q = 4;
/*     */ 
/*     */ 
/*     */   
/*  96 */   private byte E = 1;
/*     */ 
/*     */   
/*     */   private boolean F;
/*     */ 
/*     */   
/* 102 */   private int[] G = null;
/*     */ 
/*     */   
/* 105 */   private int H = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte a() {
/* 113 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte direction) {
/* 122 */     this.y = direction;
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 126 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean hideToolbar) {
/* 136 */     this.r = hideToolbar;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 140 */     return this.s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(boolean hideMenubar) {
/* 149 */     this.s = hideMenubar;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 153 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(boolean hideWindowUI) {
/* 162 */     this.t = hideWindowUI;
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 166 */     return this.u;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(boolean fitWindow) {
/* 175 */     this.u = fitWindow;
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 179 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(boolean centerWindow) {
/* 188 */     this.v = centerWindow;
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 192 */     return this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(boolean displayDocTitle) {
/* 201 */     this.w = displayDocTitle;
/*     */   }
/*     */   
/*     */   public byte h() {
/* 205 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(byte nonFullScreenPageMode) {
/* 215 */     this.x = nonFullScreenPageMode;
/*     */   }
/*     */   
/*     */   public byte i() {
/* 219 */     return this.z;
/*     */   }
/*     */   
/*     */   public void c(byte viewArea) {
/* 223 */     this.z = viewArea;
/*     */   }
/*     */   
/*     */   public byte j() {
/* 227 */     return this.A;
/*     */   }
/*     */   
/*     */   public void d(byte viewClip) {
/* 231 */     this.A = viewClip;
/*     */   }
/*     */   
/*     */   public byte k() {
/* 235 */     return this.B;
/*     */   }
/*     */   
/*     */   public void e(byte printArea) {
/* 239 */     this.B = printArea;
/*     */   }
/*     */   
/*     */   public byte l() {
/* 243 */     return this.C;
/*     */   }
/*     */   
/*     */   public void f(byte printClip) {
/* 247 */     this.C = printClip;
/*     */   }
/*     */   
/*     */   public byte m() {
/* 251 */     return this.D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(byte printScaling) {
/* 260 */     this.D = printScaling;
/*     */   }
/*     */   
/*     */   public byte n() {
/* 264 */     return this.E;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void h(byte duplex) {
/* 273 */     this.E = duplex;
/*     */   }
/*     */   
/*     */   public boolean o() {
/* 277 */     return this.F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(boolean pickTrayByPDFSize) {
/* 284 */     this.F = pickTrayByPDFSize;
/*     */   }
/*     */   
/*     */   public int[] p() {
/* 288 */     return this.G;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int[] printPageRange) {
/* 297 */     this.G = printPageRange;
/*     */   }
/*     */   
/*     */   public int q() {
/* 301 */     return this.H;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int numCopies) {
/* 310 */     if (numCopies != 0 && (numCopies < 2 || numCopies > 5)) {
/* 311 */       throw new IllegalArgumentException();
/*     */     }
/* 313 */     this.H = numCopies;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */