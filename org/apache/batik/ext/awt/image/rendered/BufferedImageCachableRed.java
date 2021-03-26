/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
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
/*     */ public class BufferedImageCachableRed
/*     */   extends AbstractRed
/*     */ {
/*     */   BufferedImage bi;
/*     */   
/*     */   public BufferedImageCachableRed(BufferedImage bi) {
/*  44 */     super((CachableRed)null, new Rectangle(bi.getMinX(), bi.getMinY(), bi.getWidth(), bi.getHeight()), bi.getColorModel(), bi.getSampleModel(), bi.getMinX(), bi.getMinY(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.bi = bi;
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImageCachableRed(BufferedImage bi, int xloc, int yloc) {
/*  55 */     super((CachableRed)null, new Rectangle(xloc, yloc, bi.getWidth(), bi.getHeight()), bi.getColorModel(), bi.getSampleModel(), xloc, yloc, (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.bi = bi;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  64 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBufferedImage() {
/*  74 */     return this.bi;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  78 */     return this.bi.getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  82 */     return this.bi.getPropertyNames();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/*  86 */     return this.bi.getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/*  90 */     Raster r = this.bi.getData();
/*  91 */     return r.createTranslatedChild(getMinX(), getMinY());
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/*  95 */     Rectangle r = (Rectangle)rect.clone();
/*     */     
/*  97 */     if (!r.intersects(getBounds()))
/*  98 */       return null; 
/*  99 */     r = r.intersection(getBounds());
/* 100 */     r.translate(-getMinX(), -getMinY());
/*     */     
/* 102 */     Raster ret = this.bi.getData(r);
/* 103 */     return ret.createTranslatedChild(ret.getMinX() + getMinX(), ret.getMinY() + getMinY());
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 108 */     WritableRaster wr2 = wr.createWritableTranslatedChild(wr.getMinX() - getMinX(), wr.getMinY() - getMinY());
/*     */ 
/*     */ 
/*     */     
/* 112 */     GraphicsUtil.copyData(this.bi.getRaster(), wr2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/BufferedImageCachableRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */