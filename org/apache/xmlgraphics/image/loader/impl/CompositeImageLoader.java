/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeImageLoader
/*     */   extends AbstractImageLoader
/*     */ {
/*  42 */   protected static final Log log = LogFactory.getLog(CompositeImageLoader.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private ImageLoader[] loaders;
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeImageLoader(ImageLoader[] loaders) {
/*  51 */     if (loaders == null || loaders.length == 0) {
/*  52 */       throw new IllegalArgumentException("Must at least pass one ImageLoader as parameter");
/*     */     }
/*  54 */     for (int i = 1, c = loaders.length; i < c; i++) {
/*  55 */       if (!loaders[0].getTargetFlavor().equals(loaders[i].getTargetFlavor())) {
/*  56 */         throw new IllegalArgumentException("All ImageLoaders must produce the same target flavor");
/*     */       }
/*     */     } 
/*     */     
/*  60 */     this.loaders = loaders;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageFlavor getTargetFlavor() {
/*  65 */     return this.loaders[0].getTargetFlavor();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUsagePenalty() {
/*  70 */     int maxPenalty = 0;
/*  71 */     for (int i = 1, c = this.loaders.length; i < c; i++) {
/*  72 */       maxPenalty = Math.max(maxPenalty, this.loaders[i].getUsagePenalty());
/*     */     }
/*  74 */     return maxPenalty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/*  80 */     ImageException firstException = null;
/*  81 */     for (int i = 0, c = this.loaders.length; i < c; i++) {
/*  82 */       ImageLoader loader = this.loaders[i];
/*     */       try {
/*  84 */         Image img = loader.loadImage(info, hints, session);
/*  85 */         if (img != null && firstException != null) {
/*  86 */           log.debug("First ImageLoader failed (" + firstException.getMessage() + "). Fallback was successful.");
/*     */         }
/*     */         
/*  89 */         return img;
/*  90 */       } catch (ImageException ie) {
/*  91 */         if (firstException == null) {
/*  92 */           firstException = ie;
/*     */         }
/*     */       } 
/*     */     } 
/*  96 */     throw firstException;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     StringBuffer sb = new StringBuffer("[");
/* 102 */     for (int i = 0; i < this.loaders.length; i++) {
/* 103 */       if (i > 0) {
/* 104 */         sb.append(",");
/*     */       }
/* 106 */       sb.append(this.loaders[i].toString());
/*     */     } 
/* 108 */     sb.append("]");
/* 109 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/CompositeImageLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */