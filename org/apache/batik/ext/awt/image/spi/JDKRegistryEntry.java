/*     */ package org.apache.batik.ext.awt.image.spi;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.renderable.DeferRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.RedRable;
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
/*     */ public class JDKRegistryEntry
/*     */   extends AbstractRegistryEntry
/*     */   implements URLRegistryEntry
/*     */ {
/*     */   public static final float PRIORITY = 1000000.0F;
/*     */   
/*     */   public JDKRegistryEntry() {
/*  57 */     super("JDK", 1000000.0F, new String[0], new String[] { "image/gif" });
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
/*     */   public boolean isCompatibleURL(ParsedURL purl) {
/*     */     try {
/*  74 */       new URL(purl.toString());
/*  75 */     } catch (MalformedURLException mue) {
/*     */       
/*  77 */       return false;
/*     */     } 
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter handleURL(ParsedURL purl, boolean needRawData) {
/*     */     final URL url;
/*     */     final String errCode;
/*     */     final Object[] errParam;
/*     */     try {
/*  94 */       url = new URL(purl.toString());
/*  95 */     } catch (MalformedURLException mue) {
/*  96 */       return null;
/*     */     } 
/*     */     
/*  99 */     final DeferRable dr = new DeferRable();
/*     */ 
/*     */     
/* 102 */     if (purl != null) {
/* 103 */       errCode = "url.format.unreadable";
/* 104 */       errParam = new Object[] { "JDK", url };
/*     */     } else {
/* 106 */       errCode = "stream.format.unreadable";
/* 107 */       errParam = new Object[] { "JDK" };
/*     */     } 
/*     */     
/* 110 */     Thread t = new Thread() {
/*     */         public void run() {
/* 112 */           Filter filt = null;
/*     */           try {
/* 114 */             Toolkit tk = Toolkit.getDefaultToolkit();
/* 115 */             Image img = tk.createImage(url);
/*     */             
/* 117 */             if (img != null) {
/* 118 */               RenderedImage ri = JDKRegistryEntry.this.loadImage(img, dr);
/* 119 */               if (ri != null) {
/* 120 */                 RedRable redRable = new RedRable(GraphicsUtil.wrap(ri));
/*     */               }
/*     */             } 
/* 123 */           } catch (ThreadDeath td) {
/* 124 */             filt = ImageTagRegistry.getBrokenLinkImage(JDKRegistryEntry.this, errCode, errParam);
/*     */             
/* 126 */             dr.setSource(filt);
/* 127 */             throw td;
/* 128 */           } catch (Throwable throwable) {}
/* 129 */           if (filt == null) {
/* 130 */             filt = ImageTagRegistry.getBrokenLinkImage(JDKRegistryEntry.this, errCode, errParam);
/*     */           }
/*     */           
/* 133 */           dr.setSource(filt);
/*     */         }
/*     */       };
/* 136 */     t.start();
/* 137 */     return (Filter)dr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage loadImage(Image img, DeferRable dr) {
/* 144 */     if (img instanceof RenderedImage) {
/* 145 */       return (RenderedImage)img;
/*     */     }
/* 147 */     MyImgObs observer = new MyImgObs();
/* 148 */     Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, observer);
/* 149 */     observer.waitTilWidthHeightDone();
/* 150 */     if (observer.imageError)
/* 151 */       return null; 
/* 152 */     int width = observer.width;
/* 153 */     int height = observer.height;
/* 154 */     dr.setBounds(new Rectangle2D.Double(0.0D, 0.0D, width, height));
/*     */ 
/*     */     
/* 157 */     BufferedImage bi = new BufferedImage(width, height, 2);
/*     */     
/* 159 */     Graphics2D g2d = bi.createGraphics();
/*     */ 
/*     */     
/* 162 */     observer.waitTilImageDone();
/* 163 */     if (observer.imageError)
/* 164 */       return null; 
/* 165 */     dr.setProperties(new HashMap<Object, Object>());
/*     */     
/* 167 */     g2d.drawImage(img, 0, 0, (ImageObserver)null);
/* 168 */     g2d.dispose();
/*     */     
/* 170 */     return bi;
/*     */   }
/*     */   
/*     */   public static class MyImgObs
/*     */     implements ImageObserver {
/*     */     boolean widthDone = false;
/*     */     boolean heightDone = false;
/*     */     boolean imageDone = false;
/* 178 */     int width = -1;
/* 179 */     int height = -1;
/*     */     
/*     */     boolean imageError = false;
/* 182 */     int IMG_BITS = 224;
/*     */     
/*     */     public void clear() {
/* 185 */       this.width = -1;
/* 186 */       this.height = -1;
/* 187 */       this.widthDone = false;
/* 188 */       this.heightDone = false;
/* 189 */       this.imageDone = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
/* 194 */       synchronized (this) {
/* 195 */         boolean notify = false;
/*     */         
/* 197 */         if ((infoflags & 0x1) != 0) this.width = width; 
/* 198 */         if ((infoflags & 0x2) != 0) this.height = height;
/*     */         
/* 200 */         if ((infoflags & 0x20) != 0) {
/* 201 */           this.width = width;
/* 202 */           this.height = height;
/*     */         } 
/*     */         
/* 205 */         if ((infoflags & this.IMG_BITS) != 0) {
/* 206 */           if (!this.widthDone || !this.heightDone || !this.imageDone) {
/* 207 */             this.widthDone = true;
/* 208 */             this.heightDone = true;
/* 209 */             this.imageDone = true;
/* 210 */             notify = true;
/*     */           } 
/* 212 */           if ((infoflags & 0x40) != 0) {
/* 213 */             this.imageError = true;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 218 */         if (!this.widthDone && this.width != -1) {
/* 219 */           notify = true;
/* 220 */           this.widthDone = true;
/*     */         } 
/* 222 */         if (!this.heightDone && this.height != -1) {
/* 223 */           notify = true;
/* 224 */           this.heightDone = true;
/*     */         } 
/*     */         
/* 227 */         if (notify)
/* 228 */           notifyAll(); 
/*     */       } 
/* 230 */       return true;
/*     */     }
/*     */     
/*     */     public synchronized void waitTilWidthHeightDone() {
/* 234 */       while (!this.widthDone || !this.heightDone) {
/*     */         
/*     */         try {
/* 237 */           wait();
/*     */         }
/* 239 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void waitTilWidthDone() {
/* 245 */       while (!this.widthDone) {
/*     */         
/*     */         try {
/* 248 */           wait();
/*     */         }
/* 250 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void waitTilHeightDone() {
/* 256 */       while (!this.heightDone) {
/*     */         
/*     */         try {
/* 259 */           wait();
/*     */         }
/* 261 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void waitTilImageDone() {
/* 268 */       while (!this.imageDone) {
/*     */         
/*     */         try {
/* 271 */           wait();
/*     */         }
/* 273 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/JDKRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */