/*    */ package org.apache.batik.ext.awt.image.rendered;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
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
/*    */ public class TileCache
/*    */ {
/* 28 */   private static LRUCache cache = new LRUCache(50);
/*    */   public static void setSize(int sz) {
/* 30 */     cache.setSize(sz);
/*    */   }
/*    */   
/*    */   public static TileStore getTileGrid(int minTileX, int minTileY, int xSz, int ySz, TileGenerator src) {
/* 34 */     return new TileGrid(minTileX, minTileY, xSz, ySz, src, cache);
/*    */   }
/*    */ 
/*    */   
/*    */   public static TileStore getTileGrid(RenderedImage img, TileGenerator src) {
/* 39 */     return new TileGrid(img.getMinTileX(), img.getMinTileY(), img.getNumXTiles(), img.getNumYTiles(), src, cache);
/*    */   }
/*    */ 
/*    */   
/*    */   public static TileStore getTileMap(TileGenerator src) {
/* 44 */     return new TileMap(src, cache);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */