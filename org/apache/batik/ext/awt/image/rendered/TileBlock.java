/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileBlock
/*     */ {
/*     */   int occX;
/*     */   int occY;
/*     */   int occW;
/*     */   int occH;
/*     */   int xOff;
/*     */   int yOff;
/*     */   int w;
/*     */   int h;
/*     */   int benefit;
/*     */   boolean[] occupied;
/*     */   
/*     */   TileBlock(int occX, int occY, int occW, int occH, boolean[] occupied, int xOff, int yOff, int w, int h) {
/*  51 */     this.occX = occX;
/*  52 */     this.occY = occY;
/*  53 */     this.occW = occW;
/*  54 */     this.occH = occH;
/*  55 */     this.xOff = xOff;
/*  56 */     this.yOff = yOff;
/*  57 */     this.w = w;
/*  58 */     this.h = h;
/*  59 */     this.occupied = occupied;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     for (int y = 0; y < h; y++) {
/*  65 */       for (int x = 0; x < w; x++) {
/*  66 */         if (!occupied[x + xOff + occW * (y + yOff)]) {
/*  67 */           this.benefit++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  76 */     String ret = "";
/*  77 */     for (int y = 0; y < this.occH; y++) {
/*  78 */       for (int x = 0; x < this.occW + 1; x++) {
/*  79 */         if (x == this.xOff || x == this.xOff + this.w) {
/*  80 */           if (y == this.yOff || y == this.yOff + this.h - 1) {
/*  81 */             ret = ret + "+";
/*  82 */           } else if (y > this.yOff && y < this.yOff + this.h - 1) {
/*  83 */             ret = ret + "|";
/*     */           } else {
/*  85 */             ret = ret + " ";
/*     */           } 
/*  87 */         } else if (y == this.yOff && x > this.xOff && x < this.xOff + this.w) {
/*  88 */           ret = ret + "-";
/*  89 */         } else if (y == this.yOff + this.h - 1 && x > this.xOff && x < this.xOff + this.w) {
/*  90 */           ret = ret + "_";
/*     */         } else {
/*  92 */           ret = ret + " ";
/*     */         } 
/*  94 */         if (x != this.occW)
/*     */         {
/*     */           
/*  97 */           if (this.occupied[x + y * this.occW]) {
/*  98 */             ret = ret + "*";
/*     */           } else {
/* 100 */             ret = ret + ".";
/*     */           }  } 
/* 102 */       }  ret = ret + "\n";
/*     */     } 
/* 104 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getXLoc() {
/* 110 */     return this.occX + this.xOff;
/*     */   }
/*     */ 
/*     */   
/*     */   int getYLoc() {
/* 115 */     return this.occY + this.yOff;
/*     */   }
/*     */ 
/*     */   
/*     */   int getWidth() {
/* 120 */     return this.w;
/*     */   }
/*     */ 
/*     */   
/*     */   int getHeight() {
/* 125 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   int getBenefit() {
/* 130 */     return this.benefit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getWork() {
/* 136 */     return this.w * this.h + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int getWork(TileBlock[] blocks) {
/* 142 */     int ret = 0;
/* 143 */     for (TileBlock block : blocks) ret += block.getWork(); 
/* 144 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TileBlock[] getBestSplit() {
/* 153 */     if (simplify()) {
/* 154 */       return null;
/*     */     }
/*     */     
/* 157 */     if (this.benefit == this.w * this.h) {
/* 158 */       return new TileBlock[] { this };
/*     */     }
/* 160 */     return splitOneGo();
/*     */   }
/*     */   
/*     */   public TileBlock[] splitOneGo() {
/* 164 */     boolean[] filled = (boolean[])this.occupied.clone();
/* 165 */     List<TileBlock> items = new ArrayList();
/* 166 */     for (int y = this.yOff; y < this.yOff + this.h; y++) {
/* 167 */       for (int x = this.xOff; x < this.xOff + this.w; x++) {
/* 168 */         if (!filled[x + y * this.occW]) {
/*     */ 
/*     */           
/* 171 */           int cw = this.xOff + this.w - x;
/* 172 */           for (int cx = x; cx < x + cw; cx++) {
/* 173 */             if (filled[cx + y * this.occW]) {
/* 174 */               cw = cx - x;
/*     */             } else {
/* 176 */               filled[cx + y * this.occW] = true;
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 181 */           int ch = 1;
/* 182 */           for (int cy = y + 1; cy < this.yOff + this.h; cy++) {
/* 183 */             int i = x;
/* 184 */             for (; i < x + cw && 
/* 185 */               !filled[i + cy * this.occW]; i++);
/*     */ 
/*     */ 
/*     */             
/* 189 */             if (i != x + cw) {
/*     */               break;
/*     */             }
/*     */             
/* 193 */             for (i = x; i < x + cw; i++)
/* 194 */               filled[i + cy * this.occW] = true; 
/* 195 */             ch++;
/*     */           } 
/* 197 */           items.add(new TileBlock(this.occX, this.occY, this.occW, this.occH, this.occupied, x, y, cw, ch));
/*     */           
/* 199 */           x += cw - 1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 203 */     TileBlock[] ret = new TileBlock[items.size()];
/* 204 */     items.toArray(ret);
/*     */     
/* 206 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean simplify() {
/* 211 */     boolean[] workOccupied = this.occupied;
/*     */     int y;
/* 213 */     for (y = 0; y < this.h; y++) {
/*     */       int i;
/* 215 */       for (i = 0; i < this.w && 
/* 216 */         workOccupied[i + this.xOff + this.occW * (y + this.yOff)]; i++);
/*     */       
/* 218 */       if (i != this.w) {
/*     */         break;
/*     */       }
/* 221 */       this.yOff++;
/* 222 */       y--;
/* 223 */       this.h--;
/*     */     } 
/*     */ 
/*     */     
/* 227 */     if (this.h == 0) return true;
/*     */ 
/*     */ 
/*     */     
/* 231 */     for (y = this.h - 1; y >= 0; y--) {
/*     */       int i;
/* 233 */       for (i = 0; i < this.w && 
/* 234 */         workOccupied[i + this.xOff + this.occW * (y + this.yOff)]; i++);
/*     */       
/* 236 */       if (i != this.w) {
/*     */         break;
/*     */       }
/* 239 */       this.h--;
/*     */     } 
/*     */     int x;
/* 242 */     for (x = 0; x < this.w; x++) {
/*     */       int i;
/* 244 */       for (i = 0; i < this.h && 
/* 245 */         workOccupied[x + this.xOff + this.occW * (i + this.yOff)]; i++);
/*     */       
/* 247 */       if (i != this.h) {
/*     */         break;
/*     */       }
/* 250 */       this.xOff++;
/* 251 */       x--;
/* 252 */       this.w--;
/*     */     } 
/*     */     
/* 255 */     for (x = this.w - 1; x >= 0; x--) {
/*     */       int i;
/* 257 */       for (i = 0; i < this.h && 
/* 258 */         workOccupied[x + this.xOff + this.occW * (i + this.yOff)]; i++);
/*     */       
/* 260 */       if (i != this.h) {
/*     */         break;
/*     */       }
/* 263 */       this.w--;
/*     */     } 
/*     */     
/* 266 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */