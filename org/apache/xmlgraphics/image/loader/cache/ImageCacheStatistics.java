/*     */ package org.apache.xmlgraphics.image.loader.cache;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageCacheStatistics
/*     */   implements ImageCacheListener
/*     */ {
/*     */   private int invalidHits;
/*     */   private int imageInfoCacheHits;
/*     */   private int imageInfoCacheMisses;
/*     */   private int imageCacheHits;
/*     */   private int imageCacheMisses;
/*     */   private Map imageCacheHitMap;
/*     */   private Map imageCacheMissMap;
/*     */   
/*     */   public ImageCacheStatistics(boolean detailed) {
/*  43 */     if (detailed) {
/*  44 */       this.imageCacheHitMap = new HashMap<Object, Object>();
/*  45 */       this.imageCacheMissMap = new HashMap<Object, Object>();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  53 */     this.imageInfoCacheHits = 0;
/*  54 */     this.imageInfoCacheMisses = 0;
/*  55 */     this.invalidHits = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidHit(String uri) {
/*  60 */     this.invalidHits++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cacheHitImageInfo(String uri) {
/*  65 */     this.imageInfoCacheHits++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void cacheMissImageInfo(String uri) {
/*  70 */     this.imageInfoCacheMisses++;
/*     */   }
/*     */   
/*     */   private void increaseEntry(Map<Object, Integer> map, Object key) {
/*  74 */     Integer v = (Integer)map.get(key);
/*  75 */     if (v == null) {
/*  76 */       v = Integer.valueOf(1);
/*     */     } else {
/*  78 */       Integer integer1 = v, integer2 = v = Integer.valueOf(v.intValue() + 1);
/*     */     } 
/*  80 */     map.put(key, v);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cacheHitImage(ImageKey key) {
/*  85 */     this.imageCacheHits++;
/*  86 */     if (this.imageCacheHitMap != null) {
/*  87 */       increaseEntry(this.imageCacheHitMap, key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void cacheMissImage(ImageKey key) {
/*  93 */     this.imageCacheMisses++;
/*  94 */     if (this.imageCacheMissMap != null) {
/*  95 */       increaseEntry(this.imageCacheMissMap, key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInvalidHits() {
/* 104 */     return this.invalidHits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageInfoCacheHits() {
/* 112 */     return this.imageInfoCacheHits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageInfoCacheMisses() {
/* 120 */     return this.imageInfoCacheMisses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageCacheHits() {
/* 128 */     return this.imageCacheHits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageCacheMisses() {
/* 136 */     return this.imageCacheMisses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getImageCacheHitMap() {
/* 144 */     return Collections.unmodifiableMap(this.imageCacheHitMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getImageCacheMissMap() {
/* 152 */     return Collections.unmodifiableMap(this.imageCacheMissMap);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/ImageCacheStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */