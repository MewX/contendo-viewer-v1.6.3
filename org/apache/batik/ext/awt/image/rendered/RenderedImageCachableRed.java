/*     */ package org.apache.batik.ext.awt.image.rendered;
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
/*     */ public class RenderedImageCachableRed
/*     */   implements CachableRed
/*     */ {
/*     */   private RenderedImage src;
/*     */   
/*     */   public static CachableRed wrap(RenderedImage ri) {
/*  44 */     if (ri instanceof CachableRed)
/*  45 */       return (CachableRed)ri; 
/*  46 */     if (ri instanceof BufferedImage)
/*  47 */       return new BufferedImageCachableRed((BufferedImage)ri); 
/*  48 */     return new RenderedImageCachableRed(ri);
/*     */   }
/*     */ 
/*     */   
/*  52 */   private Vector srcs = new Vector(0);
/*     */   
/*     */   public RenderedImageCachableRed(RenderedImage src) {
/*  55 */     if (src == null) {
/*  56 */       throw new IllegalArgumentException();
/*     */     }
/*  58 */     this.src = src;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/*  62 */     return this.srcs;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  66 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinX() {
/*  73 */     return this.src.getMinX();
/*     */   }
/*     */   public int getMinY() {
/*  76 */     return this.src.getMinY();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  80 */     return this.src.getWidth();
/*     */   }
/*     */   public int getHeight() {
/*  83 */     return this.src.getHeight();
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/*  87 */     return this.src.getColorModel();
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/*  91 */     return this.src.getSampleModel();
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/*  95 */     return this.src.getMinTileX();
/*     */   }
/*     */   public int getMinTileY() {
/*  98 */     return this.src.getMinTileY();
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/* 102 */     return this.src.getNumXTiles();
/*     */   }
/*     */   public int getNumYTiles() {
/* 105 */     return this.src.getNumYTiles();
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 109 */     return this.src.getTileGridXOffset();
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 113 */     return this.src.getTileGridYOffset();
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 117 */     return this.src.getTileWidth();
/*     */   }
/*     */   public int getTileHeight() {
/* 120 */     return this.src.getTileHeight();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 124 */     return this.src.getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 128 */     return this.src.getPropertyNames();
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 132 */     return this.src.getTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster raster) {
/* 136 */     return this.src.copyData(raster);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 140 */     return this.src.getData();
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 144 */     return this.src.getData(rect);
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle outputRgn) {
/* 148 */     throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle inputRgn) {
/* 153 */     throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/RenderedImageCachableRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */