/*     */ package org.apache.xmlgraphics.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Vector;
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
/*     */ public class RenderedImageCachableRed
/*     */   implements CachableRed
/*     */ {
/*     */   private RenderedImage src;
/*     */   
/*     */   public static CachableRed wrap(RenderedImage ri) {
/*  48 */     if (ri instanceof CachableRed) {
/*  49 */       return (CachableRed)ri;
/*     */     }
/*  51 */     if (ri instanceof BufferedImage) {
/*  52 */       return new BufferedImageCachableRed((BufferedImage)ri);
/*     */     }
/*  54 */     return new RenderedImageCachableRed(ri);
/*     */   }
/*     */ 
/*     */   
/*  58 */   private Vector srcs = new Vector(0);
/*     */   
/*     */   public RenderedImageCachableRed(RenderedImage src) {
/*  61 */     if (src == null) {
/*  62 */       throw new NullPointerException();
/*     */     }
/*  64 */     this.src = src;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/*  68 */     return this.srcs;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  72 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinX() {
/*  79 */     return this.src.getMinX();
/*     */   }
/*     */   public int getMinY() {
/*  82 */     return this.src.getMinY();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  86 */     return this.src.getWidth();
/*     */   }
/*     */   public int getHeight() {
/*  89 */     return this.src.getHeight();
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/*  93 */     return this.src.getColorModel();
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/*  97 */     return this.src.getSampleModel();
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/* 101 */     return this.src.getMinTileX();
/*     */   }
/*     */   public int getMinTileY() {
/* 104 */     return this.src.getMinTileY();
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/* 108 */     return this.src.getNumXTiles();
/*     */   }
/*     */   public int getNumYTiles() {
/* 111 */     return this.src.getNumYTiles();
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 115 */     return this.src.getTileGridXOffset();
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 119 */     return this.src.getTileGridYOffset();
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 123 */     return this.src.getTileWidth();
/*     */   }
/*     */   public int getTileHeight() {
/* 126 */     return this.src.getTileHeight();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 130 */     return this.src.getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 134 */     return this.src.getPropertyNames();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 138 */     return this.src.getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster raster) {
/* 142 */     return this.src.copyData(raster);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 146 */     return this.src.getData();
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 150 */     return this.src.getData(rect);
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle outputRgn) {
/* 154 */     throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle inputRgn) {
/* 159 */     throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/RenderedImageCachableRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */