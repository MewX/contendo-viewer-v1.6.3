/*     */ package jp.cssj.homare.b.e;
/*     */ 
/*     */ import jp.cssj.homare.b.a.b.r;
/*     */ import jp.cssj.homare.b.a.b.w;
/*     */ import jp.cssj.homare.b.a.c.j;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ public class c
/*     */ {
/*     */   private static final j[][] b;
/*     */   private static final double[] c;
/*     */   private double[] d;
/*     */   
/*     */   static {
/*  15 */     b = new j[0][];
/*  16 */     c = new double[0];
/*     */   }
/*     */ 
/*     */   
/*     */   private double[] e;
/*     */   
/*     */   private j[][] f;
/*     */   
/*     */   private j[][] g;
/*     */   
/*     */   private double[] h;
/*     */   
/*     */   private j[][] i;
/*     */   
/*     */   private j[][] j;
/*     */   
/*     */   private double[] k;
/*     */   private j[][] l;
/*     */   private j[][] m;
/*     */   
/*     */   public static j a(j prev, j next) {
/*  37 */     if (prev == null) {
/*  38 */       return next;
/*     */     }
/*  40 */     if (prev.a(next) > 0) {
/*  41 */       return next;
/*     */     }
/*  43 */     return prev;
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
/*     */   
/*     */   public c(int columnCount, int headerCount, int bodyCount, int footerCount) {
/*  57 */     this.d = new double[columnCount];
/*  58 */     this.e = new double[headerCount];
/*  59 */     this.g = new j[headerCount][columnCount + 1];
/*  60 */     this.f = new j[columnCount][headerCount + 1];
/*  61 */     this.h = new double[bodyCount];
/*  62 */     this.j = new j[bodyCount][columnCount + 1];
/*  63 */     this.i = new j[columnCount][bodyCount + 1];
/*  64 */     this.k = new double[footerCount];
/*  65 */     this.m = new j[footerCount][columnCount + 1];
/*  66 */     this.l = new j[columnCount][footerCount + 1];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(double[] columnWidths, double[] headerRowHeights, j[][] headerVborders, j[][] headerHborders, double[] bodyRowHeights, j[][] bodyVborders, j[][] bodyHborders, double[] footerRowHeights, j[][] footerVborders, j[][] footerHborders) {
/*  86 */     this.d = (columnWidths == null) ? c : columnWidths;
/*  87 */     this.e = (headerRowHeights == null) ? c : headerRowHeights;
/*  88 */     this.g = (headerVborders == null) ? b : headerVborders;
/*  89 */     this.f = (headerHborders == null) ? b : headerHborders;
/*  90 */     this.h = (bodyRowHeights == null) ? c : bodyRowHeights;
/*  91 */     this.j = (bodyVborders == null) ? b : bodyVborders;
/*  92 */     this.i = (bodyHborders == null) ? b : bodyHborders;
/*  93 */     this.k = (footerRowHeights == null) ? c : footerRowHeights;
/*  94 */     this.m = (footerVborders == null) ? b : footerVborders;
/*  95 */     this.l = (footerHborders == null) ? b : footerHborders;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 104 */     return this.d.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int col, double size) {
/* 114 */     if (!a && e.a(size)) throw new AssertionError(); 
/* 115 */     this.d[col] = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double a(int col) {
/* 125 */     return this.d[col];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 134 */     return this.e.length + this.h.length + this.k.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int row, double rowSize) {
/* 144 */     if (row < this.e.length) {
/* 145 */       this.e[row] = rowSize;
/*     */       return;
/*     */     } 
/* 148 */     row -= this.e.length;
/* 149 */     if (row < this.h.length) {
/* 150 */       this.h[row] = rowSize;
/*     */       return;
/*     */     } 
/* 153 */     row -= this.h.length;
/* 154 */     this.k[row] = rowSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double b(int row) {
/* 164 */     if (row < this.e.length) {
/* 165 */       return this.e[row];
/*     */     }
/* 167 */     row -= this.e.length;
/* 168 */     if (row < this.h.length) {
/* 169 */       return this.h[row];
/*     */     }
/* 171 */     row -= this.h.length;
/* 172 */     return this.k[row];
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
/*     */   public void a(int col, int index, boolean bottom, j border) {
/* 184 */     if (index < this.e.length) {
/* 185 */       this.f[col][index] = a(this.f[col][index], border);
/*     */       return;
/*     */     } 
/* 188 */     if (index == this.e.length) {
/* 189 */       if (bottom) {
/* 190 */         this.f[col][index] = a(this.f[col][index], border);
/*     */       } else {
/* 192 */         this.i[col][0] = a(this.i[col][0], border);
/*     */       } 
/*     */       return;
/*     */     } 
/* 196 */     index -= this.e.length;
/* 197 */     if (index < this.h.length) {
/* 198 */       this.i[col][index] = a(this.i[col][index], border);
/*     */       return;
/*     */     } 
/* 201 */     if (index == this.h.length) {
/* 202 */       if (bottom) {
/* 203 */         this.i[col][index] = a(this.i[col][index], border);
/*     */       } else {
/* 205 */         this.l[col][0] = a(this.l[col][0], border);
/*     */       } 
/*     */       return;
/*     */     } 
/* 209 */     index -= this.h.length;
/* 210 */     this.l[col][index] = a(this.l[col][index], border);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j a(int col, int index) {
/* 221 */     if (index < this.e.length) {
/* 222 */       return this.f[col][index];
/*     */     }
/* 224 */     if (index == this.e.length) {
/* 225 */       j border = null;
/* 226 */       if (this.f.length > 0 && (this.f[col]).length > index) {
/* 227 */         border = a(border, this.f[col][index]);
/*     */       }
/* 229 */       if (this.i.length > 0 && (this.i[col]).length > 0) {
/* 230 */         border = a(border, this.i[col][0]);
/*     */       }
/* 232 */       return border;
/*     */     } 
/* 234 */     index -= this.e.length;
/* 235 */     if (index < this.h.length) {
/* 236 */       return this.i[col][index];
/*     */     }
/* 238 */     if (index == this.h.length) {
/* 239 */       j border = null;
/* 240 */       if (this.l.length > 0 && (this.l[col]).length > 0) {
/* 241 */         border = a(border, this.l[col][0]);
/*     */       }
/* 243 */       if (this.i.length > 0 && (this.i[col]).length > index) {
/* 244 */         border = a(border, this.i[col][index]);
/*     */       }
/* 246 */       return border;
/*     */     } 
/* 248 */     index -= this.h.length;
/* 249 */     return this.l[col][index];
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
/*     */   public void a(int row, int index, j border) {
/* 261 */     if (row < this.e.length) {
/* 262 */       this.g[row][index] = a(this.g[row][index], border);
/*     */       return;
/*     */     } 
/* 265 */     row -= this.e.length;
/* 266 */     if (row < this.h.length) {
/* 267 */       this.j[row][index] = a(this.j[row][index], border);
/*     */       return;
/*     */     } 
/* 270 */     row -= this.h.length;
/* 271 */     this.m[row][index] = a(this.m[row][index], border);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j b(int row, int index) {
/* 282 */     if (row < this.e.length) {
/* 283 */       return this.g[row][index];
/*     */     }
/* 285 */     row -= this.e.length;
/* 286 */     if (row < this.h.length) {
/* 287 */       return this.j[row][index];
/*     */     }
/* 289 */     row -= this.h.length;
/* 290 */     return this.m[row][index];
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
/*     */   public c a(r prevTable, r nextTable, int origBodyRowCount) {
/* 303 */     int prevBodyGroupCount = prevTable.m();
/* 304 */     int prevBodyRowCount = 0;
/* 305 */     for (int i = 0; i < prevBodyGroupCount; i++) {
/* 306 */       prevBodyRowCount += prevTable.a(i).h();
/*     */     }
/*     */ 
/*     */     
/* 310 */     int nextBodyGroupCount = nextTable.m();
/* 311 */     int nextBodyRowCount = 0;
/* 312 */     for (int k = 0; k < nextBodyGroupCount; k++) {
/* 313 */       nextBodyRowCount += nextTable.a(k).h();
/*     */     }
/*     */ 
/*     */     
/* 317 */     j[][] hborders = this.i;
/* 318 */     this.i = new j[this.d.length][prevBodyRowCount + 1];
/* 319 */     for (int m = 0; m < this.d.length; m++) {
/* 320 */       System.arraycopy(hborders[m], 0, this.i[m], 0, prevBodyRowCount + 1);
/*     */     }
/* 322 */     j[][] nextHBorders = new j[this.d.length][nextBodyRowCount + 1]; int n;
/* 323 */     for (n = 0; n < this.d.length; n++) {
/* 324 */       System.arraycopy(hborders[n], this.j.length - nextBodyRowCount, nextHBorders[n], 0, nextBodyRowCount + 1);
/*     */     }
/*     */     
/* 327 */     if (origBodyRowCount != prevBodyRowCount + nextBodyRowCount) {
/* 328 */       for (n = 0; n < this.d.length; n++) {
/* 329 */         this.i[n][(this.i[n]).length - 1] = null;
/* 330 */         nextHBorders[n][0] = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 335 */     j[][] vborders = this.j;
/* 336 */     this.j = new j[prevBodyRowCount][];
/* 337 */     System.arraycopy(vborders, 0, this.j, 0, prevBodyRowCount);
/* 338 */     j[][] nextVBorders = new j[nextBodyRowCount][];
/* 339 */     System.arraycopy(vborders, vborders.length - nextBodyRowCount, nextVBorders, 0, nextBodyRowCount);
/*     */ 
/*     */     
/* 342 */     double[] nextRowSizes = new double[nextBodyRowCount];
/* 343 */     System.arraycopy(this.h, this.h.length - nextBodyRowCount, nextRowSizes, 0, nextBodyRowCount);
/*     */ 
/*     */     
/* 346 */     c nextBorders = new c(this.d, this.e, this.g, this.f, nextRowSizes, nextVBorders, nextHBorders, this.k, this.m, this.l);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     double[] rowSizes = this.h;
/* 352 */     this.h = new double[prevBodyRowCount];
/* 353 */     System.arraycopy(rowSizes, 0, this.h, 0, prevBodyRowCount);
/* 354 */     if (prevBodyGroupCount > 0) {
/* 355 */       w bodyGroup = prevTable.a(prevBodyGroupCount - 1);
/* 356 */       int rowCount = bodyGroup.h();
/* 357 */       if (rowCount > 0) {
/* 358 */         this.h[this.h.length - 1] = bodyGroup.a(rowCount - 1).f();
/*     */       }
/*     */     } 
/* 361 */     nextBorders.h = new double[nextBodyRowCount];
/* 362 */     System.arraycopy(rowSizes, rowSizes.length - nextBodyRowCount, nextBorders.h, 0, nextBodyRowCount);
/* 363 */     if (nextBodyGroupCount > 0) {
/* 364 */       w bodyGroup = nextTable.a(0);
/* 365 */       int rowCount = bodyGroup.h();
/* 366 */       if (rowCount > 0) {
/* 367 */         nextBorders.h[0] = bodyGroup.a(0).f();
/*     */       }
/*     */     } 
/*     */     
/* 371 */     return nextBorders;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/e/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */