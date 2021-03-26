/*     */ package org.apache.xmlgraphics.image.loader.pipeline;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageManager;
/*     */ import org.apache.xmlgraphics.image.loader.impl.CompositeImageLoader;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageConverter;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageImplRegistry;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageLoaderFactory;
/*     */ import org.apache.xmlgraphics.image.loader.util.Penalty;
/*     */ import org.apache.xmlgraphics.util.dijkstra.DefaultEdgeDirectory;
/*     */ import org.apache.xmlgraphics.util.dijkstra.DijkstraAlgorithm;
/*     */ import org.apache.xmlgraphics.util.dijkstra.EdgeDirectory;
/*     */ import org.apache.xmlgraphics.util.dijkstra.Vertex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PipelineFactory
/*     */ {
/*  53 */   protected static final Log log = LogFactory.getLog(PipelineFactory.class);
/*     */   
/*     */   private ImageManager manager;
/*     */   
/*  57 */   private int converterEdgeDirectoryVersion = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private DefaultEdgeDirectory converterEdgeDirectory;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PipelineFactory(ImageManager manager) {
/*  67 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   private DefaultEdgeDirectory getEdgeDirectory() {
/*  71 */     ImageImplRegistry registry = this.manager.getRegistry();
/*  72 */     if (registry.getImageConverterModifications() != this.converterEdgeDirectoryVersion) {
/*  73 */       Collection converters = registry.getImageConverters();
/*     */ 
/*     */       
/*  76 */       DefaultEdgeDirectory dir = new DefaultEdgeDirectory();
/*  77 */       Iterator<ImageConverter> iter = converters.iterator();
/*  78 */       while (iter.hasNext()) {
/*  79 */         ImageConverter converter = iter.next();
/*  80 */         Penalty penalty = Penalty.toPenalty(converter.getConversionPenalty());
/*  81 */         penalty = penalty.add(registry.getAdditionalPenalty(converter.getClass().getName()));
/*     */         
/*  83 */         dir.addEdge(new ImageConversionEdge(converter, penalty));
/*     */       } 
/*     */       
/*  86 */       this.converterEdgeDirectoryVersion = registry.getImageConverterModifications();
/*  87 */       this.converterEdgeDirectory = dir;
/*     */     } 
/*  89 */     return this.converterEdgeDirectory;
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
/*     */   public ImageProviderPipeline newImageConverterPipeline(Image originalImage, ImageFlavor targetFlavor) {
/* 102 */     DefaultEdgeDirectory dir = getEdgeDirectory();
/* 103 */     ImageRepresentation destination = new ImageRepresentation(targetFlavor);
/* 104 */     ImageProviderPipeline pipeline = findPipeline(dir, originalImage.getFlavor(), destination);
/* 105 */     return pipeline;
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
/*     */   public ImageProviderPipeline newImageConverterPipeline(ImageInfo imageInfo, ImageFlavor targetFlavor) {
/* 117 */     ImageProviderPipeline[] candidates = determineCandidatePipelines(imageInfo, targetFlavor);
/*     */ 
/*     */     
/* 120 */     if (candidates.length > 0) {
/* 121 */       Arrays.sort(candidates, new PipelineComparator());
/* 122 */       ImageProviderPipeline pipeline = candidates[0];
/* 123 */       if (pipeline != null && log.isDebugEnabled()) {
/* 124 */         log.debug("Pipeline: " + pipeline + " with penalty " + pipeline.getConversionPenalty());
/*     */       }
/*     */       
/* 127 */       return pipeline;
/*     */     } 
/* 129 */     return null;
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
/*     */   public ImageProviderPipeline[] determineCandidatePipelines(ImageInfo imageInfo, ImageFlavor targetFlavor) {
/* 142 */     String originalMime = imageInfo.getMimeType();
/* 143 */     ImageImplRegistry registry = this.manager.getRegistry();
/* 144 */     List<ImageProviderPipeline> candidates = new ArrayList();
/*     */ 
/*     */     
/* 147 */     DefaultEdgeDirectory dir = getEdgeDirectory();
/*     */     
/* 149 */     ImageLoaderFactory[] loaderFactories = registry.getImageLoaderFactories(imageInfo, targetFlavor);
/*     */     
/* 151 */     if (loaderFactories != null) {
/*     */       CompositeImageLoader compositeImageLoader;
/*     */       
/* 154 */       if (loaderFactories.length == 1) {
/* 155 */         ImageLoader loader = loaderFactories[0].newImageLoader(targetFlavor);
/*     */       } else {
/* 157 */         int count = loaderFactories.length;
/* 158 */         ImageLoader[] loaders = new ImageLoader[count];
/* 159 */         for (int i = 0; i < count; i++) {
/* 160 */           loaders[i] = loaderFactories[i].newImageLoader(targetFlavor);
/*     */         }
/* 162 */         compositeImageLoader = new CompositeImageLoader(loaders);
/*     */       } 
/* 164 */       ImageProviderPipeline pipeline = new ImageProviderPipeline(this.manager.getCache(), (ImageLoader)compositeImageLoader);
/* 165 */       candidates.add(pipeline);
/*     */     } else {
/*     */       
/* 168 */       if (log.isTraceEnabled()) {
/* 169 */         log.trace("No ImageLoaderFactory found that can load this format (" + targetFlavor + ") directly. Trying ImageConverters instead...");
/*     */       }
/*     */ 
/*     */       
/* 173 */       ImageRepresentation destination = new ImageRepresentation(targetFlavor);
/*     */ 
/*     */       
/* 176 */       loaderFactories = registry.getImageLoaderFactories(originalMime);
/* 177 */       if (loaderFactories != null)
/*     */       {
/*     */         
/* 180 */         for (int i = 0, ci = loaderFactories.length; i < ci; i++) {
/* 181 */           ImageLoaderFactory loaderFactory = loaderFactories[i];
/* 182 */           ImageFlavor[] flavors = loaderFactory.getSupportedFlavors(originalMime);
/* 183 */           for (int j = 0, cj = flavors.length; j < cj; j++) {
/* 184 */             ImageProviderPipeline pipeline = findPipeline(dir, flavors[j], destination);
/* 185 */             if (pipeline != null) {
/* 186 */               ImageLoader loader = loaderFactory.newImageLoader(flavors[j]);
/* 187 */               pipeline.setImageLoader(loader);
/* 188 */               candidates.add(pipeline);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 194 */     return candidates.<ImageProviderPipeline>toArray(new ImageProviderPipeline[candidates.size()]);
/*     */   }
/*     */   
/*     */   private static class PipelineComparator
/*     */     implements Serializable, Comparator {
/*     */     private static final long serialVersionUID = 1161513617996198090L;
/*     */     
/*     */     private PipelineComparator() {}
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 204 */       ImageProviderPipeline p1 = (ImageProviderPipeline)o1;
/* 205 */       ImageProviderPipeline p2 = (ImageProviderPipeline)o2;
/*     */       
/* 207 */       return p1.getConversionPenalty() - p2.getConversionPenalty();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ImageProviderPipeline findPipeline(DefaultEdgeDirectory dir, ImageFlavor originFlavor, ImageRepresentation destination) {
/* 214 */     DijkstraAlgorithm dijkstra = new DijkstraAlgorithm((EdgeDirectory)dir);
/*     */     
/* 216 */     ImageRepresentation origin = new ImageRepresentation(originFlavor);
/* 217 */     dijkstra.execute(origin, destination);
/* 218 */     if (log.isTraceEnabled()) {
/* 219 */       log.trace("Lowest penalty: " + dijkstra.getLowestPenalty(destination));
/*     */     }
/*     */     
/* 222 */     Vertex prev = destination;
/* 223 */     Vertex pred = dijkstra.getPredecessor(destination);
/* 224 */     if (pred == null) {
/* 225 */       if (log.isTraceEnabled()) {
/* 226 */         log.trace("No route found!");
/*     */       }
/* 228 */       return null;
/*     */     } 
/* 230 */     LinkedList<ImageConversionEdge> stops = new LinkedList();
/* 231 */     while ((pred = dijkstra.getPredecessor(prev)) != null) {
/* 232 */       ImageConversionEdge edge = (ImageConversionEdge)dir.getBestEdge(pred, prev);
/*     */       
/* 234 */       stops.addFirst(edge);
/* 235 */       prev = pred;
/*     */     } 
/* 237 */     ImageProviderPipeline pipeline = new ImageProviderPipeline(this.manager.getCache(), null);
/* 238 */     Iterator<ImageConversionEdge> iter = stops.iterator();
/* 239 */     while (iter.hasNext()) {
/* 240 */       ImageConversionEdge edge = iter.next();
/* 241 */       pipeline.addConverter(edge.getImageConverter());
/*     */     } 
/* 243 */     return pipeline;
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
/*     */   public ImageProviderPipeline[] determineCandidatePipelines(ImageInfo imageInfo, ImageFlavor[] flavors) {
/* 256 */     List<ImageProviderPipeline> candidates = new ArrayList();
/* 257 */     int count = flavors.length;
/* 258 */     for (int i = 0; i < count; i++) {
/*     */       
/* 260 */       ImageProviderPipeline pipeline = newImageConverterPipeline(imageInfo, flavors[i]);
/* 261 */       if (pipeline != null) {
/*     */ 
/*     */         
/* 264 */         Penalty p = pipeline.getConversionPenalty(this.manager.getRegistry());
/* 265 */         if (!p.isInfinitePenalty())
/* 266 */           candidates.add(pipeline); 
/*     */       } 
/*     */     } 
/* 269 */     return candidates.<ImageProviderPipeline>toArray(new ImageProviderPipeline[candidates.size()]);
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
/*     */   public ImageProviderPipeline[] determineCandidatePipelines(Image sourceImage, ImageFlavor[] flavors) {
/* 282 */     List<ImageProviderPipeline> candidates = new ArrayList();
/* 283 */     int count = flavors.length;
/* 284 */     for (int i = 0; i < count; i++) {
/*     */       
/* 286 */       ImageProviderPipeline pipeline = newImageConverterPipeline(sourceImage, flavors[i]);
/* 287 */       if (pipeline != null) {
/* 288 */         candidates.add(pipeline);
/*     */       }
/*     */     } 
/* 291 */     return candidates.<ImageProviderPipeline>toArray(new ImageProviderPipeline[candidates.size()]);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/pipeline/PipelineFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */