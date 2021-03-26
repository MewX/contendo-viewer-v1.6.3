/*     */ package org.apache.xmlgraphics.image.loader.cache;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.Image;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageManager;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.util.SoftMapCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageCache
/*     */ {
/*  59 */   protected static final Log log = LogFactory.getLog(ImageCache.class);
/*     */ 
/*     */   
/*  62 */   private Map invalidURIs = Collections.synchronizedMap(new HashMap<Object, Object>());
/*     */   
/*     */   private ExpirationPolicy invalidURIExpirationPolicy;
/*     */   
/*  66 */   private SoftMapCache imageInfos = new SoftMapCache(true);
/*  67 */   private SoftMapCache images = new SoftMapCache(true);
/*     */   
/*     */   private ImageCacheListener cacheListener;
/*     */   
/*     */   private TimeStampProvider timeStampProvider;
/*     */   
/*     */   private long lastHouseKeeping;
/*     */   private static final long ONE_HOUR = 3600000L;
/*     */   
/*     */   public ImageCache() {
/*  77 */     this(new TimeStampProvider(), new DefaultExpirationPolicy());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageCache(TimeStampProvider timeStampProvider, ExpirationPolicy invalidURIExpirationPolicy) {
/*  87 */     this.timeStampProvider = timeStampProvider;
/*  88 */     this.invalidURIExpirationPolicy = invalidURIExpirationPolicy;
/*  89 */     this.lastHouseKeeping = this.timeStampProvider.getTimeStamp();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCacheListener(ImageCacheListener listener) {
/*  97 */     this.cacheListener = listener;
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
/*     */   public ImageInfo needImageInfo(String uri, ImageSessionContext session, ImageManager manager) throws ImageException, IOException {
/* 115 */     if (isInvalidURI(uri)) {
/* 116 */       throw new FileNotFoundException("Image not found: " + uri);
/*     */     }
/* 118 */     String lockURI = uri.intern();
/* 119 */     synchronized (lockURI) {
/* 120 */       ImageInfo info = getImageInfo(uri);
/* 121 */       if (info == null) {
/*     */         try {
/* 123 */           Source src = session.needSource(uri);
/* 124 */           if (src == null) {
/* 125 */             registerInvalidURI(uri);
/* 126 */             throw new FileNotFoundException("Image not found: " + uri);
/*     */           } 
/* 128 */           info = manager.preloadImage(uri, src);
/* 129 */           session.returnSource(uri, src);
/* 130 */         } catch (IOException ioe) {
/* 131 */           registerInvalidURI(uri);
/* 132 */           throw ioe;
/* 133 */         } catch (ImageException e) {
/* 134 */           registerInvalidURI(uri);
/* 135 */           throw e;
/*     */         } 
/* 137 */         if (info.getOriginalImage() == null || info.getOriginalImage().isCacheable()) {
/* 138 */           putImageInfo(info);
/*     */         }
/*     */       } 
/* 141 */       return info;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvalidURI(String uri) {
/* 151 */     boolean expired = removeInvalidURIIfExpired(uri);
/* 152 */     if (expired) {
/* 153 */       return false;
/*     */     }
/* 155 */     if (this.cacheListener != null) {
/* 156 */       this.cacheListener.invalidHit(uri);
/*     */     }
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean removeInvalidURIIfExpired(String uri) {
/* 163 */     Long timestamp = (Long)this.invalidURIs.get(uri);
/* 164 */     boolean expired = (timestamp == null || this.invalidURIExpirationPolicy.isExpired(this.timeStampProvider, timestamp.longValue()));
/*     */ 
/*     */     
/* 167 */     if (expired) {
/* 168 */       this.invalidURIs.remove(uri);
/*     */     }
/* 170 */     return expired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageInfo getImageInfo(String uri) {
/* 179 */     ImageInfo info = (ImageInfo)this.imageInfos.get(uri);
/* 180 */     if (this.cacheListener != null) {
/* 181 */       if (info != null) {
/* 182 */         this.cacheListener.cacheHitImageInfo(uri);
/*     */       }
/* 184 */       else if (!isInvalidURI(uri)) {
/* 185 */         this.cacheListener.cacheMissImageInfo(uri);
/*     */       } 
/*     */     }
/*     */     
/* 189 */     return info;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void putImageInfo(ImageInfo info) {
/* 198 */     this.imageInfos.put(info.getOriginalURI(), info);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void registerInvalidURI(String uri) {
/* 208 */     this.invalidURIs.put(uri, Long.valueOf(this.timeStampProvider.getTimeStamp()));
/*     */     
/* 210 */     considerHouseKeeping();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getImage(ImageInfo info, ImageFlavor flavor) {
/* 220 */     return getImage(info.getOriginalURI(), flavor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getImage(String uri, ImageFlavor flavor) {
/* 230 */     if (uri == null || "".equals(uri)) {
/* 231 */       return null;
/*     */     }
/* 233 */     ImageKey key = new ImageKey(uri, flavor);
/* 234 */     Image img = (Image)this.images.get(key);
/* 235 */     if (this.cacheListener != null) {
/* 236 */       if (img != null) {
/* 237 */         this.cacheListener.cacheHitImage(key);
/*     */       } else {
/* 239 */         this.cacheListener.cacheMissImage(key);
/*     */       } 
/*     */     }
/* 242 */     return img;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putImage(Image img) {
/* 250 */     String originalURI = img.getInfo().getOriginalURI();
/* 251 */     if (originalURI == null || "".equals(originalURI)) {
/*     */       return;
/*     */     }
/*     */     
/* 255 */     if (!img.isCacheable()) {
/* 256 */       throw new IllegalArgumentException("Image is not cacheable! (Flavor: " + img.getFlavor() + ")");
/*     */     }
/*     */     
/* 259 */     ImageKey key = new ImageKey(originalURI, img.getFlavor());
/* 260 */     this.images.put(key, img);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCache() {
/* 267 */     this.invalidURIs.clear();
/* 268 */     this.imageInfos.clear();
/* 269 */     this.images.clear();
/* 270 */     doHouseKeeping();
/*     */   }
/*     */   
/*     */   private void considerHouseKeeping() {
/* 274 */     long ts = this.timeStampProvider.getTimeStamp();
/* 275 */     if (this.lastHouseKeeping + 3600000L > ts) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       this.lastHouseKeeping = ts;
/* 281 */       doHouseKeeping();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doHouseKeeping() {
/* 289 */     this.imageInfos.doHouseKeeping();
/* 290 */     this.images.doHouseKeeping();
/* 291 */     doInvalidURIHouseKeeping();
/*     */   }
/*     */   
/*     */   private void doInvalidURIHouseKeeping() {
/* 295 */     Set currentEntries = new HashSet(this.invalidURIs.keySet());
/* 296 */     Iterator<String> iter = currentEntries.iterator();
/* 297 */     while (iter.hasNext()) {
/* 298 */       String key = iter.next();
/* 299 */       removeInvalidURIIfExpired(key);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/ImageCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */