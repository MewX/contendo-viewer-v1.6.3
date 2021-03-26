/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageProcessingHints;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*     */ import org.apache.xmlgraphics.java2d.color.DeviceCMYKColorSpace;
/*     */ import org.apache.xmlgraphics.util.UnitConv;
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
/*     */ public class ImageConverterG2D2Bitmap
/*     */   extends AbstractImageConverter
/*     */ {
/*     */   public Image convert(Image src, Map hints) {
/*  53 */     checkSourceFlavor(src);
/*  54 */     assert src instanceof ImageGraphics2D;
/*  55 */     ImageGraphics2D g2dImage = (ImageGraphics2D)src;
/*     */     
/*  57 */     Object formatIntent = hints.get(ImageProcessingHints.BITMAP_TYPE_INTENT);
/*  58 */     int bitsPerPixel = 24;
/*  59 */     if ("gray".equals(formatIntent)) {
/*  60 */       bitsPerPixel = 8;
/*  61 */     } else if ("mono".equals(formatIntent)) {
/*  62 */       bitsPerPixel = 1;
/*     */     } 
/*     */     
/*  65 */     Object transparencyIntent = hints.get(ImageProcessingHints.TRANSPARENCY_INTENT);
/*  66 */     boolean withAlpha = true;
/*  67 */     if ("ignore".equals(transparencyIntent)) {
/*  68 */       withAlpha = false;
/*     */     }
/*     */     
/*  71 */     int resolution = 300;
/*  72 */     Number res = (Number)hints.get(ImageProcessingHints.TARGET_RESOLUTION);
/*  73 */     if (res != null) {
/*  74 */       resolution = res.intValue();
/*     */     }
/*  76 */     boolean cmyk = Boolean.TRUE.equals(hints.get("CMYK"));
/*     */     
/*  78 */     BufferedImage bi = paintToBufferedImage(g2dImage, bitsPerPixel, withAlpha, resolution, cmyk);
/*     */     
/*  80 */     ImageBuffered bufImage = new ImageBuffered(src.getInfo(), bi, null);
/*  81 */     return bufImage;
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
/*     */   protected BufferedImage paintToBufferedImage(ImageGraphics2D g2dImage, int bitsPerPixel, boolean withAlpha, int resolution, boolean cmyk) {
/*     */     BufferedImage bi;
/*  94 */     ImageSize size = g2dImage.getSize();
/*     */     
/*  96 */     RenderingHints additionalHints = null;
/*  97 */     int bmw = (int)Math.ceil(UnitConv.mpt2px(size.getWidthMpt(), resolution));
/*  98 */     int bmh = (int)Math.ceil(UnitConv.mpt2px(size.getHeightMpt(), resolution));
/*     */     
/* 100 */     switch (bitsPerPixel) {
/*     */       case 1:
/* 102 */         bi = new BufferedImage(bmw, bmh, 12);
/* 103 */         withAlpha = false;
/*     */         
/* 105 */         additionalHints = new RenderingHints(null);
/*     */         
/* 107 */         additionalHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
/*     */         break;
/*     */       
/*     */       case 8:
/* 111 */         if (withAlpha) {
/* 112 */           bi = createGrayBufferedImageWithAlpha(bmw, bmh); break;
/*     */         } 
/* 114 */         bi = new BufferedImage(bmw, bmh, 10);
/*     */         break;
/*     */       
/*     */       default:
/* 118 */         if (withAlpha) {
/* 119 */           bi = new BufferedImage(bmw, bmh, 2); break;
/*     */         } 
/* 121 */         if (cmyk) {
/* 122 */           ComponentColorModel ccm = new ComponentColorModel((ColorSpace)new DeviceCMYKColorSpace(), false, false, 1, 0);
/*     */           
/* 124 */           int[] bands = { 0, 1, 2, 3 };
/* 125 */           PixelInterleavedSampleModel sm = new PixelInterleavedSampleModel(0, bmw, bmh, 4, bmw * 4, bands);
/*     */           
/* 127 */           WritableRaster raster = WritableRaster.createWritableRaster(sm, new Point(0, 0));
/* 128 */           bi = new BufferedImage(ccm, raster, false, null); break;
/*     */         } 
/* 130 */         bi = new BufferedImage(bmw, bmh, 1);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 135 */     Graphics2D g2d = bi.createGraphics();
/*     */     try {
/* 137 */       g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/*     */       
/* 139 */       setRenderingHintsForBufferedImage(g2d);
/* 140 */       if (additionalHints != null) {
/* 141 */         g2d.addRenderingHints(additionalHints);
/*     */       }
/*     */       
/* 144 */       g2d.setBackground(Color.white);
/* 145 */       g2d.setColor(Color.black);
/* 146 */       if (!withAlpha) {
/* 147 */         g2d.clearRect(0, 0, bmw, bmh);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       double sx = bmw / size.getWidthMpt();
/* 157 */       double sy = bmh / size.getHeightMpt();
/* 158 */       g2d.scale(sx, sy);
/*     */ 
/*     */       
/* 161 */       Rectangle2D area = new Rectangle2D.Double(0.0D, 0.0D, size.getWidthMpt(), size.getHeightMpt());
/*     */       
/* 163 */       g2dImage.getGraphics2DImagePainter().paint(g2d, area);
/*     */     } finally {
/* 165 */       g2d.dispose();
/*     */     } 
/* 167 */     return bi;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BufferedImage createGrayBufferedImageWithAlpha(int width, int height) {
/* 172 */     boolean alphaPremultiplied = true;
/* 173 */     int bands = 2;
/* 174 */     int[] bits = new int[bands];
/* 175 */     for (int i = 0; i < bands; i++) {
/* 176 */       bits[i] = 8;
/*     */     }
/* 178 */     ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(1003), bits, true, alphaPremultiplied, 3, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     WritableRaster wr = Raster.createInterleavedRaster(0, width, height, bands, new Point(0, 0));
/*     */ 
/*     */ 
/*     */     
/* 188 */     BufferedImage bi = new BufferedImage(cm, wr, alphaPremultiplied, null);
/* 189 */     return bi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setRenderingHintsForBufferedImage(Graphics2D g2d) {
/* 198 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */     
/* 200 */     g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFlavor getSourceFlavor() {
/* 206 */     return ImageFlavor.GRAPHICS2D;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageFlavor getTargetFlavor() {
/* 211 */     return ImageFlavor.BUFFERED_IMAGE;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageConverterG2D2Bitmap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */