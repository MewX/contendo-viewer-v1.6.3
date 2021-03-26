/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import org.apache.batik.ext.awt.image.rendered.AbstractRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HistogramRed
/*     */   extends AbstractRed
/*     */ {
/*     */   boolean[] computed;
/*  37 */   int tallied = 0;
/*     */   
/*  39 */   int[] bins = new int[256];
/*     */   
/*     */   public HistogramRed(CachableRed src) {
/*  42 */     super(src, null);
/*     */     
/*  44 */     int tiles = getNumXTiles() * getNumYTiles();
/*  45 */     this.computed = new boolean[tiles];
/*     */   }
/*     */   
/*     */   public void tallyTile(Raster r) {
/*  49 */     int minX = r.getMinX();
/*  50 */     int minY = r.getMinY();
/*  51 */     int w = r.getWidth();
/*  52 */     int h = r.getHeight();
/*     */     
/*  54 */     int[] samples = null;
/*     */     
/*  56 */     for (int y = minY; y < minY + h; y++) {
/*  57 */       samples = r.getPixels(minX, y, w, 1, samples);
/*  58 */       for (int x = 0; x < 3 * w; x++) {
/*     */         
/*  60 */         int val = samples[x++] * 5;
/*  61 */         val += samples[x++] * 9;
/*  62 */         val += samples[x++] * 2;
/*  63 */         this.bins[val >> 4] = this.bins[val >> 4] + 1;
/*     */       } 
/*     */     } 
/*  66 */     this.tallied++;
/*     */   }
/*     */   
/*     */   public int[] getHistogram() {
/*  70 */     if (this.tallied == this.computed.length) {
/*  71 */       return this.bins;
/*     */     }
/*  73 */     CachableRed src = getSources().get(0);
/*  74 */     int yt0 = src.getMinTileY();
/*     */     
/*  76 */     int xtiles = src.getNumXTiles();
/*  77 */     int xt0 = src.getMinTileX();
/*     */     
/*  79 */     for (int y = 0; y < src.getNumYTiles(); y++) {
/*  80 */       for (int x = 0; x < xtiles; x++) {
/*  81 */         int idx = x + xt0 + y * xtiles;
/*  82 */         if (!this.computed[idx]) {
/*     */           
/*  84 */           Raster r = src.getTile(x + xt0, y + yt0);
/*  85 */           tallyTile(r);
/*  86 */           this.computed[idx] = true;
/*     */         } 
/*     */       } 
/*  89 */     }  return this.bins;
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*  93 */     copyToRaster(wr);
/*  94 */     return wr;
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/*  98 */     int yt = tileY - getMinTileY();
/*  99 */     int xt = tileX - getMinTileX();
/*     */     
/* 101 */     CachableRed src = getSources().get(0);
/* 102 */     Raster r = src.getTile(tileX, tileY);
/*     */     
/* 104 */     int idx = xt + yt * getNumXTiles();
/*     */     
/* 106 */     if (this.computed[idx]) {
/* 107 */       return r;
/*     */     }
/* 109 */     tallyTile(r);
/* 110 */     this.computed[idx] = true;
/* 111 */     return r;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/HistogramRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */