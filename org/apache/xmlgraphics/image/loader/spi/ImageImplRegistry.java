/*     */ package org.apache.xmlgraphics.image.loader.spi;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.util.Penalty;
/*     */ import org.apache.xmlgraphics.util.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageImplRegistry
/*     */ {
/*  45 */   protected static final Log log = LogFactory.getLog(ImageImplRegistry.class);
/*     */ 
/*     */   
/*     */   public static final int INFINITE_PENALTY = 2147483647;
/*     */ 
/*     */   
/*  51 */   private List preloaders = new ArrayList();
/*     */   
/*     */   private int lastPreloaderIdentifier;
/*     */   
/*     */   private int lastPreloaderSort;
/*     */   
/*  57 */   private Map loaders = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*  61 */   private List converters = new ArrayList();
/*     */ 
/*     */   
/*     */   private int converterModifications;
/*     */ 
/*     */   
/*  67 */   private Map additionalPenalties = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static ImageImplRegistry defaultInstance = new ImageImplRegistry();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageImplRegistry(boolean discover) {
/*  78 */     if (discover) {
/*  79 */       discoverClasspathImplementations();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageImplRegistry() {
/*  87 */     this(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageImplRegistry getDefaultInstance() {
/*  95 */     return defaultInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discoverClasspathImplementations() {
/* 103 */     Iterator<ImagePreloader> iter = Service.providers(ImagePreloader.class);
/* 104 */     while (iter.hasNext()) {
/* 105 */       registerPreloader(iter.next());
/*     */     }
/*     */ 
/*     */     
/* 109 */     iter = Service.providers(ImageLoaderFactory.class);
/* 110 */     while (iter.hasNext()) {
/* 111 */       registerLoaderFactory((ImageLoaderFactory)iter.next());
/*     */     }
/*     */ 
/*     */     
/* 115 */     iter = Service.providers(ImageConverter.class);
/* 116 */     while (iter.hasNext()) {
/* 117 */       registerConverter((ImageConverter)iter.next());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerPreloader(ImagePreloader preloader) {
/* 126 */     if (log.isDebugEnabled()) {
/* 127 */       log.debug("Registered " + preloader.getClass().getName() + " with priority " + preloader.getPriority());
/*     */     }
/*     */     
/* 130 */     this.preloaders.add(newPreloaderHolder(preloader));
/*     */   }
/*     */   
/*     */   private synchronized PreloaderHolder newPreloaderHolder(ImagePreloader preloader) {
/* 134 */     PreloaderHolder holder = new PreloaderHolder();
/* 135 */     holder.preloader = preloader;
/* 136 */     holder.identifier = ++this.lastPreloaderIdentifier;
/* 137 */     return holder;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class PreloaderHolder
/*     */   {
/*     */     private ImagePreloader preloader;
/*     */     
/*     */     public String toString() {
/* 146 */       return this.preloader + " " + this.identifier;
/*     */     }
/*     */     private int identifier;
/*     */     private PreloaderHolder() {} }
/*     */   private synchronized void sortPreloaders() {
/* 151 */     if (this.lastPreloaderIdentifier != this.lastPreloaderSort) {
/* 152 */       Collections.sort(this.preloaders, new Comparator()
/*     */           {
/*     */             public int compare(Object o1, Object o2) {
/* 155 */               ImageImplRegistry.PreloaderHolder h1 = (ImageImplRegistry.PreloaderHolder)o1;
/* 156 */               long p1 = h1.preloader.getPriority();
/* 157 */               p1 += ImageImplRegistry.this.getAdditionalPenalty(h1.preloader.getClass().getName()).getValue();
/*     */               
/* 159 */               ImageImplRegistry.PreloaderHolder h2 = (ImageImplRegistry.PreloaderHolder)o2;
/* 160 */               int p2 = h2.preloader.getPriority();
/* 161 */               p2 += ImageImplRegistry.this.getAdditionalPenalty(h2.preloader.getClass().getName()).getValue();
/*     */               
/* 163 */               int diff = Penalty.truncate(p1 - p2);
/* 164 */               if (diff != 0) {
/* 165 */                 return diff;
/*     */               }
/* 167 */               diff = h1.identifier - h2.identifier;
/* 168 */               return diff;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 173 */       this.lastPreloaderSort = this.lastPreloaderIdentifier;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerLoaderFactory(ImageLoaderFactory loaderFactory) {
/* 182 */     if (!loaderFactory.isAvailable()) {
/* 183 */       if (log.isDebugEnabled()) {
/* 184 */         log.debug("ImageLoaderFactory reports not available: " + loaderFactory.getClass().getName());
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 189 */     String[] mimes = loaderFactory.getSupportedMIMETypes();
/* 190 */     for (int i = 0, ci = mimes.length; i < ci; i++) {
/* 191 */       String mime = mimes[i];
/*     */       
/* 193 */       synchronized (this.loaders) {
/* 194 */         Map<Object, Object> flavorMap = (Map)this.loaders.get(mime);
/* 195 */         if (flavorMap == null) {
/* 196 */           flavorMap = new HashMap<Object, Object>();
/* 197 */           this.loaders.put(mime, flavorMap);
/*     */         } 
/*     */         
/* 200 */         ImageFlavor[] flavors = loaderFactory.getSupportedFlavors(mime);
/* 201 */         for (int j = 0, cj = flavors.length; j < cj; j++) {
/* 202 */           ImageFlavor flavor = flavors[j];
/*     */           
/* 204 */           List<ImageLoaderFactory> factoryList = (List)flavorMap.get(flavor);
/* 205 */           if (factoryList == null) {
/* 206 */             factoryList = new ArrayList();
/* 207 */             flavorMap.put(flavor, factoryList);
/*     */           } 
/* 209 */           factoryList.add(loaderFactory);
/*     */           
/* 211 */           if (log.isDebugEnabled()) {
/* 212 */             log.debug("Registered " + loaderFactory.getClass().getName() + ": MIME = " + mime + ", Flavor = " + flavor);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getImageConverters() {
/* 225 */     return Collections.unmodifiableList(this.converters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageConverterModifications() {
/* 234 */     return this.converterModifications;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerConverter(ImageConverter converter) {
/* 242 */     this.converters.add(converter);
/* 243 */     this.converterModifications++;
/* 244 */     if (log.isDebugEnabled()) {
/* 245 */       log.debug("Registered: " + converter.getClass().getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getPreloaderIterator() {
/* 254 */     sortPreloaders();
/* 255 */     Iterator iter = this.preloaders.iterator();
/*     */     
/* 257 */     MyIterator i = new MyIterator();
/* 258 */     i.iter = iter;
/* 259 */     return i;
/*     */   }
/*     */   
/*     */   static class MyIterator implements Iterator { Iterator iter;
/*     */     
/*     */     public boolean hasNext() {
/* 265 */       return this.iter.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 269 */       Object obj = this.iter.next();
/* 270 */       if (obj != null) {
/* 271 */         return ((ImageImplRegistry.PreloaderHolder)obj).preloader;
/*     */       }
/* 273 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 278 */       this.iter.remove();
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageLoaderFactory getImageLoaderFactory(ImageInfo imageInfo, ImageFlavor flavor) {
/* 291 */     String mime = imageInfo.getMimeType();
/* 292 */     Map flavorMap = (Map)this.loaders.get(mime);
/* 293 */     if (flavorMap != null) {
/* 294 */       List factoryList = (List)flavorMap.get(flavor);
/* 295 */       if (factoryList != null && factoryList.size() > 0) {
/* 296 */         Iterator<ImageLoaderFactory> iter = factoryList.iterator();
/* 297 */         int bestPenalty = Integer.MAX_VALUE;
/* 298 */         ImageLoaderFactory bestFactory = null;
/* 299 */         while (iter.hasNext()) {
/* 300 */           ImageLoaderFactory factory = iter.next();
/* 301 */           if (!factory.isSupported(imageInfo)) {
/*     */             continue;
/*     */           }
/* 304 */           ImageLoader loader = factory.newImageLoader(flavor);
/* 305 */           int penalty = loader.getUsagePenalty();
/* 306 */           if (penalty < bestPenalty) {
/* 307 */             bestPenalty = penalty;
/* 308 */             bestFactory = factory;
/*     */           } 
/*     */         } 
/* 311 */         return bestFactory;
/*     */       } 
/*     */     } 
/* 314 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageLoaderFactory[] getImageLoaderFactories(ImageInfo imageInfo, ImageFlavor flavor) {
/* 325 */     String mime = imageInfo.getMimeType();
/* 326 */     Collection<ImageLoaderFactory> matches = new TreeSet(new ImageLoaderFactoryComparator(flavor));
/* 327 */     Map flavorMap = (Map)this.loaders.get(mime);
/* 328 */     if (flavorMap != null) {
/* 329 */       for (Object i : flavorMap.entrySet()) {
/* 330 */         Map.Entry e = (Map.Entry)i;
/* 331 */         ImageFlavor checkFlavor = (ImageFlavor)e.getKey();
/* 332 */         if (checkFlavor.isCompatible(flavor)) {
/* 333 */           List factoryList = (List)e.getValue();
/* 334 */           if (factoryList != null && factoryList.size() > 0) {
/* 335 */             Iterator<ImageLoaderFactory> factoryIter = factoryList.iterator();
/* 336 */             while (factoryIter.hasNext()) {
/* 337 */               ImageLoaderFactory factory = factoryIter.next();
/* 338 */               if (factory.isSupported(imageInfo)) {
/* 339 */                 matches.add(factory);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 346 */     if (matches.size() == 0) {
/* 347 */       return null;
/*     */     }
/* 349 */     return matches.<ImageLoaderFactory>toArray(new ImageLoaderFactory[matches.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   private class ImageLoaderFactoryComparator
/*     */     implements Comparator
/*     */   {
/*     */     private ImageFlavor targetFlavor;
/*     */     
/*     */     public ImageLoaderFactoryComparator(ImageFlavor targetFlavor) {
/* 359 */       this.targetFlavor = targetFlavor;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 363 */       ImageLoaderFactory f1 = (ImageLoaderFactory)o1;
/* 364 */       ImageLoader l1 = f1.newImageLoader(this.targetFlavor);
/* 365 */       long p1 = l1.getUsagePenalty();
/* 366 */       p1 += ImageImplRegistry.this.getAdditionalPenalty(l1.getClass().getName()).getValue();
/*     */       
/* 368 */       ImageLoaderFactory f2 = (ImageLoaderFactory)o2;
/* 369 */       ImageLoader l2 = f2.newImageLoader(this.targetFlavor);
/*     */       
/* 371 */       long p2 = ImageImplRegistry.this.getAdditionalPenalty(l2.getClass().getName()).getValue();
/*     */ 
/*     */       
/* 374 */       return Penalty.truncate(p1 - p2);
/*     */     }
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
/*     */   public ImageLoaderFactory[] getImageLoaderFactories(String mime) {
/* 387 */     Map flavorMap = (Map)this.loaders.get(mime);
/* 388 */     if (flavorMap != null) {
/* 389 */       Set factories = new HashSet();
/* 390 */       Iterator<List> iter = flavorMap.values().iterator();
/* 391 */       while (iter.hasNext()) {
/* 392 */         List factoryList = iter.next();
/* 393 */         factories.addAll(factoryList);
/*     */       } 
/* 395 */       int factoryCount = factories.size();
/* 396 */       if (factoryCount > 0) {
/* 397 */         return (ImageLoaderFactory[])factories.toArray((Object[])new ImageLoaderFactory[factoryCount]);
/*     */       }
/*     */     } 
/*     */     
/* 401 */     return new ImageLoaderFactory[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdditionalPenalty(String className, Penalty penalty) {
/* 412 */     if (penalty != null) {
/* 413 */       this.additionalPenalties.put(className, penalty);
/*     */     } else {
/* 415 */       this.additionalPenalties.remove(className);
/*     */     } 
/* 417 */     this.lastPreloaderSort = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Penalty getAdditionalPenalty(String className) {
/* 427 */     Penalty p = (Penalty)this.additionalPenalties.get(className);
/* 428 */     return (p != null) ? p : Penalty.ZERO_PENALTY;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/spi/ImageImplRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */