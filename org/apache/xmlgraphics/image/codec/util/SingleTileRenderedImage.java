/*    */ package org.apache.xmlgraphics.image.codec.util;
/*    */ 
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.Raster;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleTileRenderedImage
/*    */   extends SimpleRenderedImage
/*    */ {
/*    */   Raster ras;
/*    */   
/*    */   public SingleTileRenderedImage(Raster ras, ColorModel colorModel) {
/* 46 */     this.ras = ras;
/*    */     
/* 48 */     this.tileGridXOffset = this.minX = ras.getMinX();
/* 49 */     this.tileGridYOffset = this.minY = ras.getMinY();
/* 50 */     this.tileWidth = this.width = ras.getWidth();
/* 51 */     this.tileHeight = this.height = ras.getHeight();
/* 52 */     this.sampleModel = ras.getSampleModel();
/* 53 */     this.colorModel = colorModel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Raster getTile(int tileX, int tileY) {
/* 60 */     if (tileX != 0 || tileY != 0) {
/* 61 */       throw new IllegalArgumentException(PropertyUtil.getString("SingleTileRenderedImage0"));
/*    */     }
/* 63 */     return this.ras;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/SingleTileRenderedImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */