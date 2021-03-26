/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
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
/*     */ public class ProfileRed
/*     */   extends AbstractRed
/*     */ {
/*  48 */   private static final ColorSpace sRGBCS = ColorSpace.getInstance(1000);
/*     */   
/*  50 */   private static final ColorModel sRGBCM = new DirectColorModel(sRGBCS, 32, 16711680, 65280, 255, -16777216, false, 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ICCColorSpaceWithIntent colorSpace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProfileRed(CachableRed src, ICCColorSpaceWithIntent colorSpace) {
/*  70 */     this.colorSpace = colorSpace;
/*     */     
/*  72 */     init(src, src.getBounds(), sRGBCM, sRGBCM.createCompatibleSampleModel(src.getWidth(), src.getHeight()), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CachableRed getSource() {
/*  80 */     return getSources().get(0);
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
/*     */   public WritableRaster copyData(WritableRaster argbWR) {
/*     */     try {
/* 113 */       RenderedImage img = getSource();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       ColorModel imgCM = img.getColorModel();
/* 120 */       ColorSpace imgCS = imgCM.getColorSpace();
/* 121 */       int nImageComponents = imgCS.getNumComponents();
/* 122 */       int nProfileComponents = this.colorSpace.getNumComponents();
/* 123 */       if (nImageComponents != nProfileComponents) {
/*     */ 
/*     */         
/* 126 */         System.err.println("Input image and associated color profile have mismatching number of color components: conversion is not possible");
/*     */         
/* 128 */         return argbWR;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       int w = argbWR.getWidth();
/* 135 */       int h = argbWR.getHeight();
/* 136 */       int minX = argbWR.getMinX();
/* 137 */       int minY = argbWR.getMinY();
/* 138 */       WritableRaster srcWR = imgCM.createCompatibleWritableRaster(w, h);
/*     */       
/* 140 */       srcWR = srcWR.createWritableTranslatedChild(minX, minY);
/* 141 */       img.copyData(srcWR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 147 */       if (!(imgCM instanceof ComponentColorModel) || !(img.getSampleModel() instanceof java.awt.image.BandedSampleModel) || (imgCM.hasAlpha() && imgCM.isAlphaPremultiplied())) {
/*     */ 
/*     */         
/* 150 */         ComponentColorModel imgCompCM = new ComponentColorModel(imgCS, imgCM.getComponentSize(), imgCM.hasAlpha(), false, imgCM.getTransparency(), 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 159 */         WritableRaster writableRaster = Raster.createBandedRaster(0, argbWR.getWidth(), argbWR.getHeight(), imgCompCM.getNumComponents(), new Point(0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 165 */         BufferedImage imgComp = new BufferedImage(imgCompCM, writableRaster, imgCompCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */         
/* 168 */         BufferedImage srcImg = new BufferedImage(imgCM, srcWR.createWritableTranslatedChild(0, 0), imgCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */         
/* 172 */         Graphics2D graphics2D = imgComp.createGraphics();
/* 173 */         graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
/*     */         
/* 175 */         graphics2D.drawImage(srcImg, 0, 0, (ImageObserver)null);
/* 176 */         img = imgComp;
/* 177 */         imgCM = imgCompCM;
/* 178 */         srcWR = writableRaster.createWritableTranslatedChild(minX, minY);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       ComponentColorModel newCM = new ComponentColorModel((ColorSpace)this.colorSpace, imgCM.getComponentSize(), false, false, 1, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 196 */       DataBufferByte data = (DataBufferByte)srcWR.getDataBuffer();
/* 197 */       srcWR = Raster.createBandedRaster(data, argbWR.getWidth(), argbWR.getHeight(), argbWR.getWidth(), new int[] { 0, 1, 2 }, new int[] { 0, 0, 0 }, new Point(0, 0));
/*     */ 
/*     */ 
/*     */       
/* 201 */       BufferedImage newImg = new BufferedImage(newCM, srcWR, newCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 207 */       ComponentColorModel sRGBCompCM = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8 }, false, false, 1, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 215 */       WritableRaster wr = Raster.createBandedRaster(0, argbWR.getWidth(), argbWR.getHeight(), sRGBCompCM.getNumComponents(), new Point(0, 0));
/*     */ 
/*     */ 
/*     */       
/* 219 */       BufferedImage sRGBImage = new BufferedImage(sRGBCompCM, wr, false, null);
/*     */       
/* 221 */       ColorConvertOp colorConvertOp = new ColorConvertOp(null);
/* 222 */       colorConvertOp.filter(newImg, sRGBImage);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if (imgCM.hasAlpha()) {
/* 228 */         DataBufferByte rgbData = (DataBufferByte)wr.getDataBuffer();
/* 229 */         byte[][] imgBanks = data.getBankData();
/* 230 */         byte[][] rgbBanks = rgbData.getBankData();
/*     */         
/* 232 */         byte[][] argbBanks = { rgbBanks[0], rgbBanks[1], rgbBanks[2], imgBanks[3] };
/*     */         
/* 234 */         DataBufferByte argbData = new DataBufferByte(argbBanks, (imgBanks[0]).length);
/* 235 */         srcWR = Raster.createBandedRaster(argbData, argbWR.getWidth(), argbWR.getHeight(), argbWR.getWidth(), new int[] { 0, 1, 2, 3 }, new int[] { 0, 0, 0, 0 }, new Point(0, 0));
/*     */ 
/*     */ 
/*     */         
/* 239 */         sRGBCompCM = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 246 */         sRGBImage = new BufferedImage(sRGBCompCM, srcWR, false, null);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       BufferedImage result = new BufferedImage(sRGBCM, argbWR.createWritableTranslatedChild(0, 0), false, null);
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
/* 268 */       Graphics2D g = result.createGraphics();
/* 269 */       g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
/*     */       
/* 271 */       g.drawImage(sRGBImage, 0, 0, (ImageObserver)null);
/* 272 */       g.dispose();
/*     */       
/* 274 */       return argbWR;
/* 275 */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/* 277 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/ProfileRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */