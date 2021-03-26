/*     */ package c.a.e;
/*     */ 
/*     */ import c.a.e;
/*     */ import c.a.i.c;
/*     */ import java.awt.Point;
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
/*     */ public class j
/*     */   extends g
/*     */   implements a
/*     */ {
/*  76 */   private a a = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private int b;
/*     */ 
/*     */ 
/*     */   
/*     */   private int c;
/*     */ 
/*     */ 
/*     */   
/*     */   private int d;
/*     */ 
/*     */ 
/*     */   
/*     */   private int e;
/*     */ 
/*     */   
/*     */   private int f;
/*     */ 
/*     */   
/*     */   private int g;
/*     */ 
/*     */   
/*     */   private int h;
/*     */ 
/*     */   
/*     */   private int i;
/*     */ 
/*     */   
/* 107 */   private int[] j = null;
/*     */ 
/*     */   
/* 110 */   private int[] m = null;
/*     */ 
/*     */ 
/*     */   
/* 114 */   private int[] n = null;
/*     */ 
/*     */ 
/*     */   
/* 118 */   private int[] o = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int p;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int r;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int s;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j(a src, int ax, int ay, int px, int py, int nw, int nh) {
/* 164 */     super(src);
/*     */ 
/*     */     
/* 167 */     this.a = src;
/* 168 */     this.b = ax;
/* 169 */     this.c = ay;
/* 170 */     this.d = px;
/* 171 */     this.e = py;
/* 172 */     this.f = nw;
/* 173 */     this.g = nh;
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
/* 188 */     if (this.b < 0 || this.c < 0 || this.d < 0 || this.e < 0 || this.f < 0 || this.g < 0 || this.d > this.b || this.e > this.c)
/*     */     {
/* 190 */       throw new IllegalArgumentException("Invalid image origin, tiling origin or nominal tile size");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (this.f == 0) this.f = this.b + src.getImgWidth() - this.d; 
/* 198 */     if (this.g == 0) this.g = this.c + src.getImgHeight() - this.e;
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (this.b - this.d >= this.f) {
/* 203 */       this.d += (this.b - this.d) / this.f * this.f;
/*     */     }
/* 205 */     if (this.c - this.e >= this.g) {
/* 206 */       this.e += (this.c - this.e) / this.g * this.g;
/*     */     }
/* 208 */     if (this.b - this.d >= this.f || this.c - this.e >= this.g) {
/* 209 */       c.b()
/* 210 */         .printmsg(1, "Automatically adjusted tiling origin to equivalent one (" + this.d + "," + this.e + ") so that " + "first tile overlaps the image");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     this.h = (int)Math.ceil((this.b + src.getImgWidth() - this.d) / this.f);
/* 218 */     this.i = (int)Math.ceil((this.c + src.getImgHeight() - this.e) / this.g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getTileWidth() {
/* 228 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getTileHeight() {
/* 238 */     return this.s;
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
/*     */   public final int getTileCompWidth(int t, int c) {
/* 251 */     if (t != getTileIdx()) {
/* 252 */       throw new Error("Asking the width of a tile-component which is not in the current tile (call setTile() or nextTile() methods before).");
/*     */     }
/*     */ 
/*     */     
/* 256 */     return this.j[c];
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
/*     */   public final int getTileCompHeight(int t, int c) {
/* 269 */     if (t != getTileIdx()) {
/* 270 */       throw new Error("Asking the width of a tile-component which is not in the current tile (call setTile() or nextTile() methods before).");
/*     */     }
/*     */ 
/*     */     
/* 274 */     return this.m[c];
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
/*     */   public int getFixedPoint(int c) {
/* 292 */     return this.a.getFixedPoint(c);
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
/*     */   public final c getInternCompData(c blk, int i) {
/* 336 */     if (blk.e < 0 || blk.f < 0 || blk.g > this.j[i] || blk.h > this.m[i]) {
/* 337 */       throw new IllegalArgumentException("Block is outside the tile");
/*     */     }
/*     */     
/* 340 */     int incx = (int)Math.ceil(this.b / this.a.getCompSubsX(i));
/* 341 */     int incy = (int)Math.ceil(this.c / this.a.getCompSubsY(i));
/* 342 */     blk.e -= incx;
/* 343 */     blk.f -= incy;
/* 344 */     blk = this.a.getInternCompData(blk, i);
/*     */     
/* 346 */     blk.e += incx;
/* 347 */     blk.f += incy;
/* 348 */     return blk;
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
/*     */   public final c getCompData(c blk, int i) {
/* 391 */     if (blk.e < 0 || blk.f < 0 || blk.g > this.j[i] || blk.h > this.m[i]) {
/* 392 */       throw new IllegalArgumentException("Block is outside the tile");
/*     */     }
/*     */     
/* 395 */     int incx = (int)Math.ceil(this.b / this.a.getCompSubsX(i));
/* 396 */     int incy = (int)Math.ceil(this.c / this.a.getCompSubsY(i));
/* 397 */     blk.e -= incx;
/* 398 */     blk.f -= incy;
/* 399 */     blk = this.a.getCompData(blk, i);
/*     */     
/* 401 */     blk.e += incx;
/* 402 */     blk.f += incy;
/* 403 */     return blk;
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
/*     */   public final void setTile(int x, int y) {
/* 416 */     this.a.setTile(x, y);
/*     */ 
/*     */     
/* 419 */     if (x < 0 || y < 0 || x >= this.h || y >= this.i) {
/* 420 */       throw new IllegalArgumentException("Tile's indexes out of bounds");
/*     */     }
/*     */ 
/*     */     
/* 424 */     this.p = x;
/* 425 */     this.q = y;
/*     */     
/* 427 */     int tx0 = (x != 0) ? (this.d + x * this.f) : this.b;
/* 428 */     int ty0 = (y != 0) ? (this.e + y * this.g) : this.c;
/*     */     
/* 430 */     int tx1 = (x != this.h - 1) ? (this.d + (x + 1) * this.f) : (this.b + this.a.getImgWidth());
/*     */     
/* 432 */     int ty1 = (y != this.i - 1) ? (this.e + (y + 1) * this.g) : (this.c + this.a.getImgHeight());
/*     */     
/* 434 */     this.r = tx1 - tx0;
/* 435 */     this.s = ty1 - ty0;
/*     */     
/* 437 */     int nc = this.a.getNumComps();
/* 438 */     if (this.j == null) this.j = new int[nc]; 
/* 439 */     if (this.m == null) this.m = new int[nc]; 
/* 440 */     if (this.n == null) this.n = new int[nc]; 
/* 441 */     if (this.o == null) this.o = new int[nc]; 
/* 442 */     for (int i = 0; i < nc; i++) {
/* 443 */       this.n[i] = (int)Math.ceil(tx0 / this.a.getCompSubsX(i));
/* 444 */       this.o[i] = (int)Math.ceil(ty0 / this.a.getCompSubsY(i));
/* 445 */       this.j[i] = (int)Math.ceil(tx1 / this.a.getCompSubsX(i)) - this.n[i];
/*     */       
/* 447 */       this.m[i] = (int)Math.ceil(ty1 / this.a.getCompSubsY(i)) - this.o[i];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextTile() {
/* 458 */     if (this.p == this.h - 1 && this.q == this.i - 1)
/* 459 */       throw new e(); 
/* 460 */     if (this.p < this.h - 1) {
/* 461 */       setTile(this.p + 1, this.q);
/*     */     } else {
/* 463 */       setTile(0, this.q + 1);
/*     */     } 
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
/*     */   public final Point getTile(Point co) {
/* 476 */     if (co != null) {
/* 477 */       co.x = this.p;
/* 478 */       co.y = this.q;
/* 479 */       return co;
/*     */     } 
/* 481 */     return new Point(this.p, this.q);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getTileIdx() {
/* 492 */     return this.q * this.h + this.p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getCompULX(int c) {
/* 502 */     return this.n[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getCompULY(int c) {
/* 512 */     return this.o[c];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULX() {
/* 517 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULY() {
/* 522 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getImgULX() {
/* 533 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getImgULY() {
/* 544 */     return this.c;
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
/*     */   public final Point getNumTiles(Point co) {
/* 557 */     if (co != null) {
/* 558 */       co.x = this.h;
/* 559 */       co.y = this.i;
/* 560 */       return co;
/*     */     } 
/* 562 */     return new Point(this.h, this.i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNumTiles() {
/* 572 */     return this.h * this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNomTileWidth() {
/* 581 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNomTileHeight() {
/* 590 */     return this.g;
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
/*     */   public final Point a(Point co) {
/* 606 */     if (co != null) {
/* 607 */       co.x = this.d;
/* 608 */       co.y = this.e;
/* 609 */       return co;
/*     */     } 
/* 611 */     return new Point(this.d, this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 621 */     return "Tiler: source= " + this.a + "\n" + 
/* 622 */       getNumTiles() + " tile(s), nominal width=" + this.f + ", nominal height=" + this.g;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/j.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */