/*     */ package org.apache.batik.ext.awt.image.codec.imageio;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractImageIORegistryEntry
/*     */   extends MagicNumberRegistryEntry
/*     */ {
/*     */   public AbstractImageIORegistryEntry(String name, String[] exts, String[] mimeTypes, MagicNumberRegistryEntry.MagicNumber[] magicNumbers) {
/*  64 */     super(name, 1100.0F, exts, mimeTypes, magicNumbers);
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
/*     */   public AbstractImageIORegistryEntry(String name, String ext, String mimeType, int offset, byte[] magicNumber) {
/*  79 */     super(name, 1100.0F, ext, mimeType, offset, magicNumber);
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
/*  95 */     final DeferRable dr = new DeferRable();
/*  96 */     final InputStream is = inIS;
/*     */ 
/*     */     
/*  99 */     if (origURL != null) {
/* 100 */       errCode = "url.format.unreadable";
/* 101 */       errParam = new Object[] { getFormatName(), origURL };
/*     */     } else {
/* 103 */       errCode = "stream.format.unreadable";
/* 104 */       errParam = new Object[] { getFormatName() };
/*     */     } 
/*     */     
/* 107 */     Thread t = new Thread()
/*     */       {
/*     */         public void run() {
/*     */           Filter filter;
/*     */           try {
/* 112 */             Iterator<ImageReader> iter = ImageIO.getImageReadersByMIMEType(AbstractImageIORegistryEntry.this.getMimeTypes().get(0).toString());
/*     */             
/* 114 */             if (!iter.hasNext()) {
/* 115 */               throw new UnsupportedOperationException("No image reader for " + AbstractImageIORegistryEntry.this.getFormatName() + " available!");
/*     */             }
/*     */ 
/*     */             
/* 119 */             ImageReader reader = iter.next();
/* 120 */             ImageInputStream imageIn = ImageIO.createImageInputStream(is);
/* 121 */             reader.setInput(imageIn, true);
/*     */             
/* 123 */             int imageIndex = 0;
/* 124 */             dr.setBounds(new Rectangle2D.Double(0.0D, 0.0D, reader.getWidth(imageIndex), reader.getHeight(imageIndex)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 132 */             BufferedImage bi = reader.read(imageIndex);
/* 133 */             CachableRed cr = GraphicsUtil.wrap(bi);
/* 134 */             Any2sRGBRed any2sRGBRed = new Any2sRGBRed(cr);
/* 135 */             FormatRed formatRed = new FormatRed((CachableRed)any2sRGBRed, GraphicsUtil.sRGB_Unpre);
/* 136 */             WritableRaster wr = (WritableRaster)formatRed.getData();
/* 137 */             ColorModel cm = formatRed.getColorModel();
/* 138 */             BufferedImage image = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */             
/* 140 */             CachableRed cachableRed1 = GraphicsUtil.wrap(image);
/* 141 */             RedRable redRable = new RedRable(cachableRed1);
/* 142 */           } catch (IOException ioe) {
/*     */             
/* 144 */             filter = ImageTagRegistry.getBrokenLinkImage(AbstractImageIORegistryEntry.this, errCode, errParam);
/*     */           
/*     */           }
/* 147 */           catch (ThreadDeath td) {
/* 148 */             filter = ImageTagRegistry.getBrokenLinkImage(AbstractImageIORegistryEntry.this, errCode, errParam);
/*     */ 
/*     */             
/* 151 */             dr.setSource(filter);
/* 152 */             throw td;
/* 153 */           } catch (Throwable t) {
/* 154 */             filter = ImageTagRegistry.getBrokenLinkImage(AbstractImageIORegistryEntry.this, errCode, errParam);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 159 */           dr.setSource(filter);
/*     */         }
/*     */       };
/* 162 */     t.start();
/* 163 */     return (Filter)dr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/AbstractImageIORegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */