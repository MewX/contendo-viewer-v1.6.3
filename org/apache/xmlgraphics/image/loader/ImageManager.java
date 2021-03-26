/*     */ package org.apache.xmlgraphics.image.loader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.cache.ImageCache;
/*     */ import org.apache.xmlgraphics.image.loader.pipeline.ImageProviderPipeline;
/*     */ import org.apache.xmlgraphics.image.loader.pipeline.PipelineFactory;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageImplRegistry;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImagePreloader;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.image.loader.util.Penalty;
/*     */ import org.apache.xmlgraphics.io.XmlSourceUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageManager
/*     */ {
/*  46 */   protected static final Log log = LogFactory.getLog(ImageManager.class);
/*     */ 
/*     */   
/*     */   private ImageImplRegistry registry;
/*     */ 
/*     */   
/*     */   private ImageContext imageContext;
/*     */ 
/*     */   
/*  55 */   private ImageCache cache = new ImageCache();
/*     */   
/*  57 */   private PipelineFactory pipelineFactory = new PipelineFactory(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageManager(ImageContext context) {
/*  64 */     this(ImageImplRegistry.getDefaultInstance(), context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageManager(ImageImplRegistry registry, ImageContext context) {
/*  73 */     this.registry = registry;
/*  74 */     this.imageContext = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageImplRegistry getRegistry() {
/*  82 */     return this.registry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageContext getImageContext() {
/*  90 */     return this.imageContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageCache getCache() {
/*  98 */     return this.cache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PipelineFactory getPipelineFactory() {
/* 106 */     return this.pipelineFactory;
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
/*     */   public ImageInfo getImageInfo(String uri, ImageSessionContext session) throws ImageException, IOException {
/* 122 */     if (getCache() != null) {
/* 123 */       return getCache().needImageInfo(uri, session, this);
/*     */     }
/* 125 */     return preloadImage(uri, session);
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
/*     */   public ImageInfo preloadImage(String uri, ImageSessionContext session) throws ImageException, IOException {
/* 148 */     Source src = session.needSource(uri);
/* 149 */     ImageInfo info = preloadImage(uri, src);
/* 150 */     session.returnSource(uri, src);
/* 151 */     return info;
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
/*     */   public ImageInfo preloadImage(String uri, Source src) throws ImageException, IOException {
/* 173 */     Iterator<ImagePreloader> iter = this.registry.getPreloaderIterator();
/* 174 */     while (iter.hasNext()) {
/* 175 */       ImagePreloader preloader = iter.next();
/* 176 */       ImageInfo info = preloader.preloadImage(uri, src, this.imageContext);
/* 177 */       if (info != null) {
/* 178 */         return info;
/*     */       }
/*     */     } 
/* 181 */     throw new ImageException("The file format is not supported. No ImagePreloader found for " + uri);
/*     */   }
/*     */ 
/*     */   
/*     */   private Map prepareHints(Map<?, ?> hints, ImageSessionContext sessionContext) {
/* 186 */     Map<Object, Object> newHints = new HashMap<Object, Object>();
/* 187 */     if (hints != null) {
/* 188 */       newHints.putAll(hints);
/*     */     }
/* 190 */     if (!newHints.containsKey(ImageProcessingHints.IMAGE_SESSION_CONTEXT) && sessionContext != null)
/*     */     {
/* 192 */       newHints.put(ImageProcessingHints.IMAGE_SESSION_CONTEXT, sessionContext);
/*     */     }
/*     */     
/* 195 */     if (!newHints.containsKey(ImageProcessingHints.IMAGE_MANAGER)) {
/* 196 */       newHints.put(ImageProcessingHints.IMAGE_MANAGER, this);
/*     */     }
/* 198 */     return newHints;
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
/*     */   public Image getImage(ImageInfo info, ImageFlavor flavor, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 222 */     hints = prepareHints(hints, session);
/*     */     
/* 224 */     Image img = null;
/* 225 */     ImageProviderPipeline pipeline = getPipelineFactory().newImageConverterPipeline(info, flavor);
/*     */     
/* 227 */     if (pipeline != null) {
/* 228 */       img = pipeline.execute(info, hints, session);
/*     */     }
/* 230 */     if (img == null) {
/* 231 */       throw new ImageException("Cannot load image (no suitable loader/converter combination available) for " + info);
/*     */     }
/*     */ 
/*     */     
/* 235 */     XmlSourceUtil.closeQuietly(session.getSource(info.getOriginalURI()));
/* 236 */     return img;
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
/*     */   public Image getImage(ImageInfo info, ImageFlavor[] flavors, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 261 */     hints = prepareHints(hints, session);
/*     */     
/* 263 */     Image img = null;
/* 264 */     ImageProviderPipeline[] candidates = getPipelineFactory().determineCandidatePipelines(info, flavors);
/*     */     
/* 266 */     ImageProviderPipeline pipeline = choosePipeline(candidates);
/*     */     
/* 268 */     if (pipeline != null) {
/* 269 */       img = pipeline.execute(info, hints, session);
/*     */     }
/* 271 */     if (img == null) {
/* 272 */       throw new ImageException("Cannot load image (no suitable loader/converter combination available) for " + info);
/*     */     }
/*     */ 
/*     */     
/* 276 */     XmlSourceUtil.closeQuietly(session.getSource(info.getOriginalURI()));
/* 277 */     return img;
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
/*     */   public Image getImage(ImageInfo info, ImageFlavor flavor, ImageSessionContext session) throws ImageException, IOException {
/* 295 */     return getImage(info, flavor, ImageUtil.getDefaultHints(session), session);
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
/*     */   public Image getImage(ImageInfo info, ImageFlavor[] flavors, ImageSessionContext session) throws ImageException, IOException {
/* 313 */     return getImage(info, flavors, ImageUtil.getDefaultHints(session), session);
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
/*     */   public void closeImage(String uri, ImageSessionContext session) {
/* 325 */     XmlSourceUtil.closeQuietly(session.getSource(uri));
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
/*     */   public Image convertImage(Image image, ImageFlavor[] flavors, Map hints) throws ImageException, IOException {
/* 347 */     hints = prepareHints(hints, null);
/* 348 */     ImageInfo info = image.getInfo();
/*     */     
/* 350 */     Image img = null;
/* 351 */     int count = flavors.length;
/* 352 */     for (int i = 0; i < count; i++) {
/* 353 */       if (image.getFlavor().equals(flavors[i]))
/*     */       {
/* 355 */         return image;
/*     */       }
/*     */     } 
/* 358 */     ImageProviderPipeline[] candidates = getPipelineFactory().determineCandidatePipelines(image, flavors);
/*     */     
/* 360 */     ImageProviderPipeline pipeline = choosePipeline(candidates);
/*     */     
/* 362 */     if (pipeline != null) {
/* 363 */       img = pipeline.execute(info, image, hints, null);
/*     */     }
/* 365 */     if (img == null) {
/* 366 */       throw new ImageException("Cannot convert image " + image + " (no suitable converter combination available)");
/*     */     }
/*     */ 
/*     */     
/* 370 */     return img;
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
/*     */   public Image convertImage(Image image, ImageFlavor[] flavors) throws ImageException, IOException {
/* 386 */     return convertImage(image, flavors, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageProviderPipeline choosePipeline(ImageProviderPipeline[] candidates) {
/* 395 */     ImageProviderPipeline pipeline = null;
/* 396 */     int minPenalty = Integer.MAX_VALUE;
/* 397 */     int count = candidates.length;
/* 398 */     if (log.isTraceEnabled()) {
/* 399 */       log.trace("Candidate Pipelines:");
/* 400 */       for (int j = 0; j < count; j++) {
/* 401 */         if (candidates[j] != null)
/*     */         {
/*     */           
/* 404 */           log.trace("  " + j + ": " + candidates[j].getConversionPenalty(getRegistry()) + " for " + candidates[j]);
/*     */         }
/*     */       } 
/*     */     } 
/* 408 */     for (int i = count - 1; i >= 0; i--) {
/* 409 */       if (candidates[i] != null) {
/*     */ 
/*     */         
/* 412 */         Penalty penalty = candidates[i].getConversionPenalty(getRegistry());
/* 413 */         if (!penalty.isInfinitePenalty())
/*     */         {
/*     */           
/* 416 */           if (penalty.getValue() <= minPenalty) {
/* 417 */             pipeline = candidates[i];
/* 418 */             minPenalty = penalty.getValue();
/*     */           }  } 
/*     */       } 
/* 421 */     }  if (log.isDebugEnabled()) {
/* 422 */       log.debug("Chosen pipeline: " + pipeline);
/*     */     }
/* 424 */     return pipeline;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */