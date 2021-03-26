/*     */ package c.a.e;
/*     */ 
/*     */ import c.a.e;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class i
/*     */   implements a
/*     */ {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private a[] d;
/*     */   private int[] e;
/*     */   private int[] f;
/*     */   private int[] g;
/*     */   
/*     */   public i(a[] imD, int[] cIdx) {
/* 121 */     this.d = imD;
/* 122 */     this.e = cIdx;
/* 123 */     if (this.d.length != this.e.length) {
/* 124 */       throw new IllegalArgumentException("imD and cIdx must have the same length");
/*     */     }
/*     */     
/* 127 */     this.c = imD.length;
/*     */     
/* 129 */     this.f = new int[this.c];
/* 130 */     this.g = new int[this.c];
/*     */     
/*     */     int j;
/*     */     
/* 134 */     for (j = 0; j < this.c; j++) {
/* 135 */       if (imD[j].getNumTiles() != 1 || imD[j]
/* 136 */         .getCompULX(cIdx[j]) != 0 || imD[j]
/* 137 */         .getCompULY(cIdx[j]) != 0) {
/* 138 */         throw new IllegalArgumentException("All input components must, not use tiles and must have the origin at the canvas origin");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     int maxW = 0;
/* 159 */     int maxH = 0;
/* 160 */     for (j = 0; j < this.c; j++) {
/* 161 */       if (imD[j].getCompImgWidth(cIdx[j]) > maxW)
/* 162 */         maxW = imD[j].getCompImgWidth(cIdx[j]); 
/* 163 */       if (imD[j].getCompImgHeight(cIdx[j]) > maxH) {
/* 164 */         maxH = imD[j].getCompImgHeight(cIdx[j]);
/*     */       }
/*     */     } 
/* 167 */     this.a = maxW;
/* 168 */     this.b = maxH;
/*     */ 
/*     */ 
/*     */     
/* 172 */     for (j = 0; j < this.c; j++) {
/*     */ 
/*     */       
/* 175 */       this.f[j] = (maxW + imD[j].getCompImgWidth(cIdx[j]) - 1) / imD[j]
/* 176 */         .getCompImgWidth(cIdx[j]);
/* 177 */       this.g[j] = (maxH + imD[j].getCompImgHeight(cIdx[j]) - 1) / imD[j]
/* 178 */         .getCompImgHeight(cIdx[j]);
/* 179 */       if ((maxW + this.f[j] - 1) / this.f[j] != imD[j]
/* 180 */         .getCompImgWidth(cIdx[j]) || (maxH + this.g[j] - 1) / this.g[j] != imD[j]
/*     */         
/* 182 */         .getCompImgHeight(cIdx[j])) {
/* 183 */         throw new Error("Can not compute component subsampling factors: strange subsampling.");
/*     */       }
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
/*     */   public int getTileWidth() {
/* 196 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileHeight() {
/* 206 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNomTileWidth() {
/* 211 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNomTileHeight() {
/* 216 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgWidth() {
/* 226 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgHeight() {
/* 236 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumComps() {
/* 245 */     return this.c;
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
/*     */   public int getCompSubsX(int c) {
/* 261 */     return this.f[c];
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
/*     */   public int getCompSubsY(int c) {
/* 277 */     return this.g[c];
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
/*     */   public int getTileCompWidth(int t, int c) {
/* 291 */     return this.d[c].getTileCompWidth(t, this.e[c]);
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
/*     */   public int getTileCompHeight(int t, int c) {
/* 305 */     return this.d[c].getTileCompHeight(t, this.e[c]);
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
/*     */   public int getCompImgWidth(int c) {
/* 318 */     return this.d[c].getCompImgWidth(this.e[c]);
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
/*     */   public int getCompImgHeight(int n) {
/* 333 */     return this.d[n].getCompImgHeight(this.e[n]);
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
/*     */   public int getNomRangeBits(int c) {
/* 351 */     return this.d[c].getNomRangeBits(this.e[c]);
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
/* 369 */     return this.d[c].getFixedPoint(this.e[c]);
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
/*     */   public c getInternCompData(c blk, int j) {
/* 410 */     return this.d[j].getInternCompData(blk, this.e[j]);
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
/*     */   public c getCompData(c blk, int j) {
/* 451 */     return this.d[j].getCompData(blk, this.e[j]);
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
/*     */   public void setTile(int x, int y) {
/* 464 */     if (x != 0 || y != 0) {
/* 465 */       throw new IllegalArgumentException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextTile() {
/* 476 */     throw new e();
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
/*     */   public Point getTile(Point co) {
/* 489 */     if (co != null) {
/* 490 */       co.x = 0;
/* 491 */       co.y = 0;
/* 492 */       return co;
/*     */     } 
/*     */     
/* 495 */     return new Point(0, 0);
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
/* 507 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompULX(int c) {
/* 517 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompULY(int c) {
/* 527 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULX() {
/* 532 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTilePartULY() {
/* 537 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgULX() {
/* 548 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImgULY() {
/* 559 */     return 0;
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
/* 574 */     if (co != null) {
/* 575 */       co.x = 1;
/* 576 */       co.y = 1;
/* 577 */       return co;
/*     */     } 
/*     */     
/* 580 */     return new Point(1, 1);
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
/* 591 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 602 */     String string = "ImgDataJoiner: WxH = " + this.a + "x" + this.b;
/* 603 */     for (int j = 0; j < this.c; j++) {
/* 604 */       string = string + "\n- Component " + j + " " + this.d[j];
/*     */     }
/* 606 */     return string;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/i.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */