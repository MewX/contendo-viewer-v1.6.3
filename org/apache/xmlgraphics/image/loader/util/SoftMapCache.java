/*     */ package org.apache.xmlgraphics.image.loader.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoftMapCache
/*     */ {
/*  38 */   private static Log log = LogFactory.getLog(SoftMapCache.class);
/*     */   
/*     */   private Map map;
/*  41 */   private ReferenceQueue refQueue = new ReferenceQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoftMapCache(boolean synched) {
/*  48 */     this.map = new HashMap<Object, Object>();
/*  49 */     if (synched) {
/*  50 */       this.map = Collections.synchronizedMap(this.map);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/*  61 */     Reference ref = (Reference)this.map.get(key);
/*  62 */     return getReference(key, ref);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/*  73 */     Reference ref = (Reference)this.map.remove(key);
/*  74 */     return getReference(key, ref);
/*     */   }
/*     */   
/*     */   private Object getReference(Object key, Reference ref) {
/*  78 */     Object value = null;
/*  79 */     if (ref != null) {
/*  80 */       value = ref.get();
/*  81 */       if (value == null) {
/*     */         
/*  83 */         if (log.isTraceEnabled()) {
/*  84 */           log.trace("Image has been collected: " + key);
/*     */         }
/*  86 */         checkReferenceQueue();
/*     */       } 
/*     */     } 
/*  89 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(Object key, Object value) {
/*  98 */     this.map.put(key, wrapInReference(value, key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 105 */     this.map.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doHouseKeeping() {
/* 112 */     checkReferenceQueue();
/*     */   }
/*     */   
/*     */   private Reference wrapInReference(Object obj, Object key) {
/* 116 */     return new SoftReferenceWithKey(obj, key, this.refQueue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkReferenceQueue() {
/*     */     SoftReferenceWithKey ref;
/* 125 */     while ((ref = (SoftReferenceWithKey)this.refQueue.poll()) != null) {
/* 126 */       if (log.isTraceEnabled()) {
/* 127 */         log.trace("Removing ImageInfo from ref queue: " + ref.getKey());
/*     */       }
/* 129 */       this.map.remove(ref.getKey());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/SoftMapCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */