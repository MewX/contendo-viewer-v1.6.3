/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.TileRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.rendered.TileCacheRed;
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
/*     */ public class PatternPaintContext
/*     */   implements PaintContext
/*     */ {
/*     */   private ColorModel rasterCM;
/*     */   private WritableRaster raster;
/*     */   private RenderedImage tiled;
/*     */   protected AffineTransform usr2dev;
/*     */   
/*     */   public AffineTransform getUsr2Dev() {
/*  66 */     return this.usr2dev;
/*     */   }
/*  68 */   private static Rectangle EVERYTHING = new Rectangle(-536870912, -536870912, 1073741823, 1073741823);
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
/*     */   public PatternPaintContext(ColorModel destCM, AffineTransform usr2dev, RenderingHints hints, Filter tile, Rectangle2D patternRegion, boolean overflow) {
/*  87 */     if (usr2dev == null) {
/*  88 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  91 */     if (hints == null) {
/*  92 */       hints = new RenderingHints(null);
/*     */     }
/*     */     
/*  95 */     if (tile == null) {
/*  96 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  99 */     this.usr2dev = usr2dev;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     TileRable8Bit tileRable8Bit = new TileRable8Bit(tile, EVERYTHING, patternRegion, overflow);
/*     */ 
/*     */ 
/*     */     
/* 108 */     ColorSpace destCS = destCM.getColorSpace();
/* 109 */     if (destCS == ColorSpace.getInstance(1000)) {
/* 110 */       tileRable8Bit.setColorSpaceLinear(false);
/* 111 */     } else if (destCS == ColorSpace.getInstance(1004)) {
/* 112 */       tileRable8Bit.setColorSpaceLinear(true);
/*     */     } 
/* 114 */     RenderContext rc = new RenderContext(usr2dev, EVERYTHING, hints);
/* 115 */     this.tiled = tileRable8Bit.createRendering(rc);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (this.tiled != null) {
/* 121 */       Rectangle2D devRgn = usr2dev.createTransformedShape(patternRegion).getBounds();
/*     */       
/* 123 */       if (devRgn.getWidth() > 128.0D || devRgn.getHeight() > 128.0D)
/*     */       {
/* 125 */         this.tiled = (RenderedImage)new TileCacheRed(GraphicsUtil.wrap(this.tiled), 256, 64);
/*     */       }
/*     */     } else {
/* 128 */       this.rasterCM = ColorModel.getRGBdefault();
/*     */       
/* 130 */       WritableRaster wr = this.rasterCM.createCompatibleWritableRaster(32, 32);
/* 131 */       this.tiled = (RenderedImage)GraphicsUtil.wrap(new BufferedImage(this.rasterCM, wr, false, null));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 136 */     this.rasterCM = this.tiled.getColorModel();
/* 137 */     if (this.rasterCM.hasAlpha())
/* 138 */       if (destCM.hasAlpha()) {
/* 139 */         this.rasterCM = GraphicsUtil.coerceColorModel(this.rasterCM, destCM.isAlphaPremultiplied());
/*     */       } else {
/*     */         
/* 142 */         this.rasterCM = GraphicsUtil.coerceColorModel(this.rasterCM, false);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 147 */     this.raster = null;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 151 */     return this.rasterCM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getRaster(int x, int y, int width, int height) {
/* 158 */     if (this.raster == null || this.raster.getWidth() < width || this.raster.getHeight() < height)
/*     */     {
/*     */       
/* 161 */       this.raster = this.rasterCM.createCompatibleWritableRaster(width, height);
/*     */     }
/*     */     
/* 164 */     WritableRaster wr = this.raster.createWritableChild(0, 0, width, height, x, y, (int[])null);
/*     */ 
/*     */     
/* 167 */     this.tiled.copyData(wr);
/* 168 */     GraphicsUtil.coerceData(wr, this.tiled.getColorModel(), this.rasterCM.isAlphaPremultiplied());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     if (this.raster.getWidth() == width && this.raster.getHeight() == height)
/*     */     {
/* 177 */       return this.raster;
/*     */     }
/* 179 */     return wr.createTranslatedChild(0, 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/PatternPaintContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */