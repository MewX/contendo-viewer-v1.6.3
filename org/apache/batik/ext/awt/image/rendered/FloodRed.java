/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FloodRed
/*     */   extends AbstractRed
/*     */ {
/*     */   private WritableRaster raster;
/*     */   
/*     */   public FloodRed(Rectangle bounds) {
/*  55 */     this(bounds, new Color(0, 0, 0, 0));
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
/*     */   public FloodRed(Rectangle bounds, Paint paint) {
/*  69 */     ColorModel cm = GraphicsUtil.sRGB_Unpre;
/*     */     
/*  71 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */     
/*  73 */     int tw = bounds.width;
/*  74 */     if (tw > defSz) tw = defSz; 
/*  75 */     int th = bounds.height;
/*  76 */     if (th > defSz) th = defSz;
/*     */ 
/*     */     
/*  79 */     SampleModel sm = cm.createCompatibleSampleModel(tw, th);
/*     */ 
/*     */     
/*  82 */     init((CachableRed)null, bounds, cm, sm, 0, 0, (Map)null);
/*     */     
/*  84 */     this.raster = Raster.createWritableRaster(sm, new Point(0, 0));
/*  85 */     BufferedImage offScreen = new BufferedImage(cm, this.raster, cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */     
/*  89 */     Graphics2D g = GraphicsUtil.createGraphics(offScreen);
/*  90 */     g.setPaint(paint);
/*  91 */     g.fillRect(0, 0, bounds.width, bounds.height);
/*  92 */     g.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getTile(int x, int y) {
/*  98 */     int tx = this.tileGridXOff + x * this.tileWidth;
/*  99 */     int ty = this.tileGridYOff + y * this.tileHeight;
/* 100 */     return this.raster.createTranslatedChild(tx, ty);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 104 */     int tx0 = getXTile(wr.getMinX());
/* 105 */     int ty0 = getYTile(wr.getMinY());
/* 106 */     int tx1 = getXTile(wr.getMinX() + wr.getWidth() - 1);
/* 107 */     int ty1 = getYTile(wr.getMinY() + wr.getHeight() - 1);
/*     */     
/* 109 */     boolean is_INT_PACK = GraphicsUtil.is_INT_PACK_Data(getSampleModel(), false);
/*     */ 
/*     */     
/* 112 */     for (int y = ty0; y <= ty1; y++) {
/* 113 */       for (int x = tx0; x <= tx1; x++) {
/* 114 */         Raster r = getTile(x, y);
/* 115 */         if (is_INT_PACK) {
/* 116 */           GraphicsUtil.copyData_INT_PACK(r, wr);
/*     */         } else {
/* 118 */           GraphicsUtil.copyData_FALLBACK(r, wr);
/*     */         } 
/*     */       } 
/* 121 */     }  return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/FloodRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */