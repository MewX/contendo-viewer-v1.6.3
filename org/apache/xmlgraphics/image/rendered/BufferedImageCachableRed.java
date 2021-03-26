/*     */ package org.apache.xmlgraphics.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferedImageCachableRed
/*     */   extends AbstractRed
/*     */ {
/*     */   BufferedImage bi;
/*     */   
/*     */   public BufferedImageCachableRed(BufferedImage bi) {
/*  50 */     super((CachableRed)null, new Rectangle(bi.getMinX(), bi.getMinY(), bi.getWidth(), bi.getHeight()), bi.getColorModel(), bi.getSampleModel(), bi.getMinX(), bi.getMinY(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.bi = bi;
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImageCachableRed(BufferedImage bi, int xloc, int yloc) {
/*  61 */     super((CachableRed)null, new Rectangle(xloc, yloc, bi.getWidth(), bi.getHeight()), bi.getColorModel(), bi.getSampleModel(), xloc, yloc, (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.bi = bi;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  70 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBufferedImage() {
/*  80 */     return this.bi;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  84 */     return this.bi.getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  88 */     return this.bi.getPropertyNames();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/*  92 */     return this.bi.getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/*  96 */     Raster r = this.bi.getData();
/*  97 */     return r.createTranslatedChild(getMinX(), getMinY());
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 101 */     Rectangle r = (Rectangle)rect.clone();
/*     */     
/* 103 */     if (!r.intersects(getBounds())) {
/* 104 */       return null;
/*     */     }
/* 106 */     r = r.intersection(getBounds());
/* 107 */     r.translate(-getMinX(), -getMinY());
/*     */     
/* 109 */     Raster ret = this.bi.getData(r);
/* 110 */     return ret.createTranslatedChild(ret.getMinX() + getMinX(), ret.getMinY() + getMinY());
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 115 */     WritableRaster wr2 = wr.createWritableTranslatedChild(wr.getMinX() - getMinX(), wr.getMinY() - getMinY());
/*     */ 
/*     */ 
/*     */     
/* 119 */     GraphicsUtil.copyData(this.bi.getRaster(), wr2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/BufferedImageCachableRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */