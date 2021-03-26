/*    */ package org.apache.batik.ext.awt.image.codec.util;
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
/*    */ public class SingleTileRenderedImage
/*    */   extends SimpleRenderedImage
/*    */ {
/*    */   Raster ras;
/*    */   
/*    */   public SingleTileRenderedImage(Raster ras, ColorModel colorModel) {
/* 44 */     this.ras = ras;
/*    */     
/* 46 */     this.tileGridXOffset = this.minX = ras.getMinX();
/* 47 */     this.tileGridYOffset = this.minY = ras.getMinY();
/* 48 */     this.tileWidth = this.width = ras.getWidth();
/* 49 */     this.tileHeight = this.height = ras.getHeight();
/* 50 */     this.sampleModel = ras.getSampleModel();
/* 51 */     this.colorModel = colorModel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Raster getTile(int tileX, int tileY) {
/* 58 */     if (tileX != 0 || tileY != 0) {
/* 59 */       throw new IllegalArgumentException(PropertyUtil.getString("SingleTileRenderedImage0"));
/*    */     }
/* 61 */     return this.ras;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/SingleTileRenderedImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */