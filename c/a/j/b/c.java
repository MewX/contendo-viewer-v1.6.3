/*     */ package c.a.j.b;
/*     */ 
/*     */ import c.a.b.a;
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
/*     */ public abstract class c
/*     */   implements b
/*     */ {
/*     */   protected a c;
/*     */   protected g d;
/*     */   protected int e;
/*     */   protected int f;
/*     */   
/*     */   protected c(g src, a decSpec) {
/*  98 */     this.d = src;
/*  99 */     this.c = decSpec;
/* 100 */     this.f = decSpec.g.b();
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
/*     */   public void b(int rl) {
/* 124 */     if (rl < 0) {
/* 125 */       throw new IllegalArgumentException("Resolution level index cannot be negative.");
/*     */     }
/*     */     
/* 128 */     this.e = rl;
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
/*     */   public int getTileWidth() {
/* 144 */     int tIdx = getTileIdx();
/* 145 */     int rl = 10000;
/*     */     
/* 147 */     int nc = this.d.c();
/* 148 */     for (int i = 0; i < nc; i++) {
/* 149 */       int mrl = (this.d.f(tIdx, i)).h;
/* 150 */       if (mrl < rl) rl = mrl; 
/*     */     } 
/* 152 */     return this.d.c(rl);
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
/*     */   public int getTileHeight() {
/* 169 */     int tIdx = getTileIdx();
/* 170 */     int rl = 10000;
/*     */     
/* 172 */     int nc = this.d.c();
/* 173 */     for (int i = 0; i < nc; i++) {
/* 174 */       int mrl = (this.d.f(tIdx, i)).h;
/* 175 */       if (mrl < rl) rl = mrl; 
/*     */     } 
/* 177 */     return this.d.d(rl);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNomTileWidth() {
/* 182 */     return this.d.o();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNomTileHeight() {
/* 187 */     return this.d.p();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgWidth() {
/* 198 */     return this.d.e(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgHeight() {
/* 209 */     return this.d.f(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumComps() {
/* 218 */     return this.d.c();
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
/*     */   public int getCompSubsX(int i) {
/* 235 */     return this.d.a(i);
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
/*     */   public int getCompSubsY(int i) {
/* 252 */     return this.d.b(i);
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
/*     */   public int getTileCompWidth(int t, int i) {
/* 267 */     int rl = (this.d.f(t, i)).h;
/* 268 */     return this.d.a(t, i, rl);
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
/*     */   public int getTileCompHeight(int t, int i) {
/* 287 */     int rl = (this.d.f(t, i)).h;
/* 288 */     return this.d.b(t, i, rl);
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
/*     */   public int getCompImgWidth(int i) {
/* 303 */     int rl = this.c.g.b(i);
/* 304 */     return this.d.a(i, rl);
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
/*     */   public int getCompImgHeight(int i) {
/* 322 */     int rl = this.c.g.b(i);
/* 323 */     return this.d.b(i, rl);
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
/*     */   public void setTile(int x, int y) {
/* 338 */     this.d.c(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextTile() {
/* 349 */     this.d.d();
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
/*     */   public Point getTile(Point co) {
/* 364 */     return this.d.a(co);
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
/*     */   public int getTileIdx() {
/* 376 */     return this.d.e();
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
/*     */   public int getCompULX(int i) {
/* 388 */     int tIdx = getTileIdx();
/* 389 */     int rl = (this.d.f(tIdx, i)).h;
/* 390 */     return this.d.d(i, rl);
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
/*     */   public int getCompULY(int i) {
/* 402 */     int tIdx = getTileIdx();
/* 403 */     int rl = (this.d.f(tIdx, i)).h;
/* 404 */     return this.d.e(i, rl);
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
/*     */   public int getImgULX() {
/* 418 */     return this.d.g(this.e);
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
/*     */   public int getImgULY() {
/* 432 */     return this.d.h(this.e);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULX() {
/* 437 */     return this.d.m();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULY() {
/* 442 */     return this.d.n();
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
/*     */   public Point getNumTiles(Point co) {
/* 457 */     return this.d.b(co);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumTiles() {
/* 468 */     return this.d.f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public i a(int t, int i) {
/* 479 */     return this.d.f(t, i);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */