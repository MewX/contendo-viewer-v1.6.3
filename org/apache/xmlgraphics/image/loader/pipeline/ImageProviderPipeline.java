/*     */ package org.apache.xmlgraphics.image.loader.pipeline;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.cache.ImageCache;
/*     */ import org.apache.xmlgraphics.image.loader.impl.ImageRawStream;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageConverter;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageImplRegistry;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*     */ import org.apache.xmlgraphics.image.loader.util.Penalty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageProviderPipeline
/*     */ {
/*  53 */   protected static final Log log = LogFactory.getLog(ImageProviderPipeline.class);
/*     */   
/*     */   private ImageCache cache;
/*     */   private ImageLoader loader;
/*  57 */   private List converters = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageProviderPipeline(ImageCache cache, ImageLoader loader) {
/*  65 */     this.cache = cache;
/*  66 */     setImageLoader(loader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageProviderPipeline(ImageLoader loader) {
/*  74 */     this(null, loader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageProviderPipeline() {
/*  82 */     this(null, null);
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
/*     */   public Image execute(ImageInfo info, Map hints, ImageSessionContext context) throws ImageException, IOException {
/*  99 */     return execute(info, null, hints, context);
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
/*     */   public Image execute(ImageInfo info, Image originalImage, Map hints, ImageSessionContext context) throws ImageException, IOException {
/* 118 */     if (hints == null) {
/* 119 */       hints = Collections.EMPTY_MAP;
/*     */     }
/* 121 */     long start = System.currentTimeMillis();
/* 122 */     Image img = null;
/*     */ 
/*     */     
/* 125 */     Image lastCacheableImage = null;
/*     */     
/* 127 */     int converterCount = this.converters.size();
/* 128 */     int startingPoint = 0;
/* 129 */     if (this.cache != null) {
/* 130 */       for (int i = converterCount - 1; i >= 0; i--) {
/* 131 */         ImageConverter converter = getConverter(i);
/* 132 */         ImageFlavor flavor = converter.getTargetFlavor();
/* 133 */         img = this.cache.getImage(info, flavor);
/* 134 */         if (img != null) {
/* 135 */           startingPoint = i + 1;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 140 */       if (img == null && this.loader != null) {
/*     */         
/* 142 */         ImageFlavor flavor = this.loader.getTargetFlavor();
/* 143 */         img = this.cache.getImage(info, flavor);
/*     */       } 
/*     */     } 
/* 146 */     if (img == null && originalImage != null) {
/* 147 */       img = originalImage;
/*     */     }
/*     */     
/* 150 */     boolean entirelyInCache = true;
/*     */     
/* 152 */     if (img == null && this.loader != null) {
/*     */       
/* 154 */       img = this.loader.loadImage(info, hints, context);
/* 155 */       if (log.isTraceEnabled()) {
/* 156 */         long duration = System.currentTimeMillis() - start;
/* 157 */         log.trace("Image loading using " + this.loader + " took " + duration + " ms.");
/*     */       } 
/*     */ 
/*     */       
/* 161 */       entirelyInCache = false;
/* 162 */       if (img.isCacheable()) {
/* 163 */         lastCacheableImage = img;
/*     */       }
/*     */     } 
/* 166 */     if (img == null) {
/* 167 */       throw new ImageException("Pipeline fails. No ImageLoader and no original Image available.");
/*     */     }
/*     */ 
/*     */     
/* 171 */     if (converterCount > 0) {
/* 172 */       for (int i = startingPoint; i < converterCount; i++) {
/* 173 */         ImageConverter converter = getConverter(i);
/* 174 */         start = System.currentTimeMillis();
/* 175 */         img = converter.convert(img, hints);
/* 176 */         if (log.isTraceEnabled()) {
/* 177 */           long duration = System.currentTimeMillis() - start;
/* 178 */           log.trace("Image conversion using " + converter + " took " + duration + " ms.");
/*     */         } 
/*     */ 
/*     */         
/* 182 */         entirelyInCache = false;
/* 183 */         if (img.isCacheable()) {
/* 184 */           lastCacheableImage = img;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (this.cache != null && !entirelyInCache) {
/* 192 */       if (lastCacheableImage == null)
/*     */       {
/* 194 */         lastCacheableImage = forceCaching(img);
/*     */       }
/* 196 */       if (lastCacheableImage != null) {
/* 197 */         if (log.isTraceEnabled()) {
/* 198 */           log.trace("Caching image: " + lastCacheableImage);
/*     */         }
/* 200 */         this.cache.putImage(lastCacheableImage);
/*     */       } 
/*     */     } 
/* 203 */     return img;
/*     */   }
/*     */   
/*     */   private ImageConverter getConverter(int index) {
/* 207 */     return this.converters.get(index);
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
/*     */   protected Image forceCaching(Image img) throws IOException {
/* 221 */     if (img instanceof ImageRawStream) {
/* 222 */       ImageRawStream raw = (ImageRawStream)img;
/* 223 */       if (log.isDebugEnabled()) {
/* 224 */         log.debug("Image is made cacheable: " + img.getInfo());
/*     */       }
/*     */ 
/*     */       
/* 228 */       ByteArrayOutputStream baout = new ByteArrayOutputStream();
/* 229 */       InputStream in = raw.createInputStream();
/*     */       try {
/* 231 */         IOUtils.copy(in, baout);
/*     */       } finally {
/* 233 */         IOUtils.closeQuietly(in);
/*     */       } 
/* 235 */       byte[] data = baout.toByteArray();
/* 236 */       raw.setInputStreamFactory((ImageRawStream.InputStreamFactory)new ImageRawStream.ByteArrayStreamFactory(data));
/* 237 */       return (Image)raw;
/*     */     } 
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageLoader(ImageLoader imageLoader) {
/* 248 */     this.loader = imageLoader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addConverter(ImageConverter converter) {
/* 257 */     this.converters.add(converter);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 262 */     StringBuffer sb = new StringBuffer();
/* 263 */     sb.append("Loader: ").append(this.loader);
/* 264 */     if (this.converters.size() > 0) {
/* 265 */       sb.append(" Converters: ");
/* 266 */       sb.append(this.converters);
/*     */     } 
/* 268 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConversionPenalty() {
/* 277 */     return getConversionPenalty(null).getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Penalty getConversionPenalty(ImageImplRegistry registry) {
/* 287 */     Penalty penalty = Penalty.ZERO_PENALTY;
/* 288 */     if (this.loader != null) {
/* 289 */       penalty = penalty.add(this.loader.getUsagePenalty());
/* 290 */       if (registry != null) {
/* 291 */         penalty = penalty.add(registry.getAdditionalPenalty(this.loader.getClass().getName()));
/*     */       }
/*     */     } 
/*     */     
/* 295 */     Iterator<ImageConverter> iter = this.converters.iterator();
/* 296 */     while (iter.hasNext()) {
/* 297 */       ImageConverter converter = iter.next();
/* 298 */       penalty = penalty.add(converter.getConversionPenalty());
/* 299 */       if (registry != null) {
/* 300 */         penalty = penalty.add(registry.getAdditionalPenalty(converter.getClass().getName()));
/*     */       }
/*     */     } 
/*     */     
/* 304 */     return penalty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFlavor getTargetFlavor() {
/* 312 */     if (this.converters.size() > 0)
/* 313 */       return getConverter(this.converters.size() - 1).getTargetFlavor(); 
/* 314 */     if (this.loader != null) {
/* 315 */       return this.loader.getTargetFlavor();
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/pipeline/ImageProviderPipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */