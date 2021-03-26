/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TranslateRed
/*     */   extends AbstractRed
/*     */ {
/*     */   protected int deltaX;
/*     */   protected int deltaY;
/*     */   
/*     */   public TranslateRed(CachableRed cr, int xloc, int yloc) {
/*  45 */     super(cr, new Rectangle(xloc, yloc, cr.getWidth(), cr.getHeight()), cr.getColorModel(), cr.getSampleModel(), cr.getTileGridXOffset() + xloc - cr.getMinX(), cr.getTileGridYOffset() + yloc - cr.getMinY(), (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     this.deltaX = xloc - cr.getMinX();
/*  52 */     this.deltaY = yloc - cr.getMinY();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeltaX() {
/*  58 */     return this.deltaX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDeltaY() {
/*  63 */     return this.deltaY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CachableRed getSource() {
/*  69 */     return getSources().get(0);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  73 */     return getSource().getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  77 */     return getSource().getPropertyNames();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/*  81 */     Raster r = getSource().getTile(tileX, tileY);
/*     */     
/*  83 */     return r.createTranslatedChild(r.getMinX() + this.deltaX, r.getMinY() + this.deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster getData() {
/*  88 */     Raster r = getSource().getData();
/*  89 */     return r.createTranslatedChild(r.getMinX() + this.deltaX, r.getMinY() + this.deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/*  94 */     Rectangle r = (Rectangle)rect.clone();
/*  95 */     r.translate(-this.deltaX, -this.deltaY);
/*  96 */     Raster ret = getSource().getData(r);
/*  97 */     return ret.createTranslatedChild(ret.getMinX() + this.deltaX, ret.getMinY() + this.deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 102 */     WritableRaster wr2 = wr.createWritableTranslatedChild(wr.getMinX() - this.deltaX, wr.getMinY() - this.deltaY);
/*     */ 
/*     */     
/* 105 */     getSource().copyData(wr2);
/*     */     
/* 107 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TranslateRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */