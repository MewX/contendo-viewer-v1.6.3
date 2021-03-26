/*     */ package org.apache.batik.ext.awt.image.codec.png;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.renderable.DeferRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.RedRable;
/*     */ import org.apache.batik.ext.awt.image.rendered.Any2sRGBRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.FormatRed;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageTagRegistry;
/*     */ import org.apache.batik.ext.awt.image.spi.MagicNumberRegistryEntry;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNGRegistryEntry
/*     */   extends MagicNumberRegistryEntry
/*     */ {
/*  47 */   static final byte[] signature = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
/*     */   
/*     */   public PNGRegistryEntry() {
/*  50 */     super("PNG", "png", "image/png", 0, signature);
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
/*     */   public Filter handleStream(InputStream inIS, ParsedURL origURL, boolean needRawData) {
/*     */     final String errCode;
/*     */     final Object[] errParam;
/*  66 */     final DeferRable dr = new DeferRable();
/*  67 */     final InputStream is = inIS;
/*  68 */     final boolean raw = needRawData;
/*     */ 
/*     */     
/*  71 */     if (origURL != null) {
/*  72 */       errCode = "url.format.unreadable";
/*  73 */       errParam = new Object[] { "PNG", origURL };
/*     */     } else {
/*  75 */       errCode = "stream.format.unreadable";
/*  76 */       errParam = new Object[] { "PNG" };
/*     */     } 
/*     */     
/*  79 */     Thread t = new Thread() {
/*     */         public void run() {
/*     */           Filter filter;
/*     */           try {
/*  83 */             PNGDecodeParam param = new PNGDecodeParam();
/*  84 */             param.setExpandPalette(true);
/*     */             
/*  86 */             if (raw) {
/*  87 */               param.setPerformGammaCorrection(false);
/*     */             } else {
/*  89 */               param.setPerformGammaCorrection(true);
/*  90 */               param.setDisplayExponent(2.2F);
/*     */             } 
/*  92 */             PNGRed pNGRed = new PNGRed(is, param);
/*  93 */             dr.setBounds(new Rectangle2D.Double(0.0D, 0.0D, pNGRed.getWidth(), pNGRed.getHeight()));
/*     */ 
/*     */             
/*  96 */             Any2sRGBRed any2sRGBRed = new Any2sRGBRed((CachableRed)pNGRed);
/*  97 */             FormatRed formatRed = new FormatRed((CachableRed)any2sRGBRed, GraphicsUtil.sRGB_Unpre);
/*  98 */             WritableRaster wr = (WritableRaster)formatRed.getData();
/*  99 */             ColorModel cm = formatRed.getColorModel();
/*     */             
/* 101 */             BufferedImage image = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */             
/* 103 */             CachableRed cachableRed = GraphicsUtil.wrap(image);
/* 104 */             RedRable redRable = new RedRable(cachableRed);
/* 105 */           } catch (IOException ioe) {
/* 106 */             filter = ImageTagRegistry.getBrokenLinkImage(PNGRegistryEntry.this, errCode, errParam);
/*     */           }
/* 108 */           catch (ThreadDeath td) {
/* 109 */             filter = ImageTagRegistry.getBrokenLinkImage(PNGRegistryEntry.this, errCode, errParam);
/*     */             
/* 111 */             dr.setSource(filter);
/* 112 */             throw td;
/* 113 */           } catch (Throwable t) {
/* 114 */             filter = ImageTagRegistry.getBrokenLinkImage(PNGRegistryEntry.this, errCode, errParam);
/*     */           } 
/*     */ 
/*     */           
/* 118 */           dr.setSource(filter);
/*     */         }
/*     */       };
/* 121 */     t.start();
/* 122 */     return (Filter)dr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */