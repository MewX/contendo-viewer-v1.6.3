/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import c.a.a.a.d;
/*     */ import com.github.jaiimageio.impl.common.SimpleRenderedImage;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.stream.ImageInputStream;
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
/*     */ public class J2KRenderedImage
/*     */   extends SimpleRenderedImage
/*     */ {
/*     */   private Raster currentTile;
/*     */   private Point currentTileGrid;
/*     */   private J2KReadState readState;
/*     */   
/*     */   public J2KRenderedImage(ImageInputStream iis, J2KImageReadParamJava param, J2KMetadata metadata, J2KImageReader reader) throws IOException {
/*  69 */     this(new J2KReadState(iis, param, metadata, reader));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public J2KRenderedImage(ImageInputStream iis, J2KImageReadParamJava param, J2KImageReader reader) throws IOException {
/*  75 */     this(new J2KReadState(iis, param, reader));
/*     */   }
/*     */   
/*     */   public J2KRenderedImage(J2KReadState readState) {
/*  79 */     this.readState = readState;
/*     */     
/*  81 */     d hd = readState.getHeader();
/*     */ 
/*     */     
/*  84 */     Rectangle destinationRegion = readState.getDestinationRegion();
/*  85 */     this.width = destinationRegion.width;
/*  86 */     this.height = destinationRegion.height;
/*  87 */     this.minX = destinationRegion.x;
/*  88 */     this.minY = destinationRegion.y;
/*     */     
/*  90 */     Rectangle tile0Rect = readState.getTile0Rect();
/*  91 */     this.tileWidth = tile0Rect.width;
/*  92 */     this.tileHeight = tile0Rect.height;
/*  93 */     this.tileGridXOffset = tile0Rect.x;
/*  94 */     this.tileGridYOffset = tile0Rect.y;
/*     */     
/*  96 */     this.sampleModel = readState.getSampleModel();
/*  97 */     this.colorModel = readState.getColorModel();
/*     */   }
/*     */   
/*     */   public synchronized Raster getTile(int tileX, int tileY) {
/* 101 */     if (this.currentTile != null && this.currentTileGrid.x == tileX && this.currentTileGrid.y == tileY)
/*     */     {
/*     */       
/* 104 */       return this.currentTile;
/*     */     }
/* 106 */     if (tileX >= getNumXTiles() || tileY >= getNumYTiles()) {
/* 107 */       throw new IllegalArgumentException(I18N.getString("J2KReadState1"));
/*     */     }
/*     */     try {
/* 110 */       int x = tileXToX(tileX);
/* 111 */       int y = tileYToY(tileY);
/* 112 */       this
/* 113 */         .currentTile = Raster.createWritableRaster(this.sampleModel, new Point(x, y));
/*     */       
/* 115 */       this.currentTile = this.readState.getTile(tileX, tileY, (WritableRaster)this.currentTile);
/*     */     
/*     */     }
/* 118 */     catch (IOException e) {
/* 119 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 122 */     if (this.currentTileGrid == null) {
/* 123 */       this.currentTileGrid = new Point(tileX, tileY);
/*     */     } else {
/* 125 */       this.currentTileGrid.x = tileX;
/* 126 */       this.currentTileGrid.y = tileY;
/*     */     } 
/*     */     
/* 129 */     return this.currentTile;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KRenderedImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */