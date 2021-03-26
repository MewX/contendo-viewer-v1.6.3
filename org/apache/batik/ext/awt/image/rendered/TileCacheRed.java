/*    */ package org.apache.batik.ext.awt.image.rendered;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileCacheRed
/*    */   extends AbstractTiledRed
/*    */ {
/*    */   public TileCacheRed(CachableRed cr) {
/* 40 */     super(cr, (Map)null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileCacheRed(CachableRed cr, int tileWidth, int tileHeight) {
/* 49 */     ColorModel cm = cr.getColorModel();
/* 50 */     Rectangle bounds = cr.getBounds();
/* 51 */     if (tileWidth > bounds.width) tileWidth = bounds.width; 
/* 52 */     if (tileHeight > bounds.height) tileHeight = bounds.height; 
/* 53 */     SampleModel sm = cm.createCompatibleSampleModel(tileWidth, tileHeight);
/* 54 */     init(cr, bounds, cm, sm, cr.getTileGridXOffset(), cr.getTileGridYOffset(), (Map)null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void genRect(WritableRaster wr) {
/* 61 */     CachableRed src = getSources().get(0);
/*    */     
/* 63 */     src.copyData(wr);
/*    */   }
/*    */   
/*    */   public void flushCache(Rectangle rect) {
/* 67 */     int tx0 = getXTile(rect.x);
/* 68 */     int ty0 = getYTile(rect.y);
/* 69 */     int tx1 = getXTile(rect.x + rect.width - 1);
/* 70 */     int ty1 = getYTile(rect.y + rect.height - 1);
/*    */     
/* 72 */     if (tx0 < this.minTileX) tx0 = this.minTileX; 
/* 73 */     if (ty0 < this.minTileY) ty0 = this.minTileY;
/*    */     
/* 75 */     if (tx1 >= this.minTileX + this.numXTiles) tx1 = this.minTileX + this.numXTiles - 1; 
/* 76 */     if (ty1 >= this.minTileY + this.numYTiles) ty1 = this.minTileY + this.numYTiles - 1;
/*    */     
/* 78 */     if (tx1 < tx0 || ty1 < ty0) {
/*    */       return;
/*    */     }
/* 81 */     TileStore store = getTileStore();
/* 82 */     for (int y = ty0; y <= ty1; y++) {
/* 83 */       for (int x = tx0; x <= tx1; x++)
/* 84 */         store.setTile(x, y, null); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileCacheRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */