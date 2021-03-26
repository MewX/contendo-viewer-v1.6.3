/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoftReferenceCache
/*     */ {
/*  54 */   protected final Map map = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   private final boolean synchronous;
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoftReferenceCache() {
/*  62 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoftReferenceCache(boolean synchronous) {
/*  71 */     this.synchronous = synchronous;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void flush() {
/*  80 */     this.map.clear();
/*  81 */     notifyAll();
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
/*     */   protected final synchronized boolean isPresentImpl(Object key) {
/*  93 */     if (!this.map.containsKey(key)) {
/*  94 */       return false;
/*     */     }
/*  96 */     Object o = this.map.get(key);
/*  97 */     if (o == null)
/*     */     {
/*  99 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 103 */     SoftReference sr = (SoftReference)o;
/* 104 */     o = sr.get();
/* 105 */     if (o != null) {
/* 106 */       return true;
/*     */     }
/*     */     
/* 109 */     clearImpl(key);
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final synchronized boolean isDoneImpl(Object key) {
/* 120 */     Object o = this.map.get(key);
/* 121 */     if (o == null) return false; 
/* 122 */     SoftReference sr = (SoftReference)o;
/* 123 */     o = sr.get();
/* 124 */     if (o != null) {
/* 125 */       return true;
/*     */     }
/*     */     
/* 128 */     clearImpl(key);
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final synchronized Object requestImpl(Object key) {
/* 138 */     if (this.map.containsKey(key)) {
/*     */       
/* 140 */       Object o = this.map.get(key);
/* 141 */       while (o == null) {
/* 142 */         if (this.synchronous) {
/* 143 */           return null;
/*     */         }
/*     */         
/*     */         try {
/* 147 */           wait();
/*     */         }
/* 149 */         catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */         
/* 153 */         if (!this.map.containsKey(key)) {
/*     */           break;
/*     */         }
/*     */         
/* 157 */         o = this.map.get(key);
/*     */       } 
/* 159 */       if (o != null) {
/* 160 */         SoftReference sr = (SoftReference)o;
/* 161 */         o = sr.get();
/* 162 */         if (o != null) {
/* 163 */           return o;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     this.map.put(key, null);
/* 169 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final synchronized void clearImpl(Object key) {
/* 178 */     this.map.remove(key);
/* 179 */     notifyAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final synchronized void putImpl(Object key, Object object) {
/* 190 */     if (this.map.containsKey(key)) {
/* 191 */       SoftReference ref = new SoftRefKey(object, key);
/* 192 */       this.map.put(key, ref);
/* 193 */       notifyAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   class SoftRefKey extends CleanerThread.SoftReferenceCleared { Object key;
/*     */     
/*     */     public SoftRefKey(Object o, Object key) {
/* 200 */       super(o);
/* 201 */       this.key = key;
/*     */     }
/*     */     
/*     */     public void cleared() {
/* 205 */       SoftReferenceCache cache = SoftReferenceCache.this;
/* 206 */       if (cache == null)
/* 207 */         return;  synchronized (cache) {
/* 208 */         if (!cache.map.containsKey(this.key))
/*     */           return; 
/* 210 */         Object o = cache.map.remove(this.key);
/* 211 */         if (this == o) {
/*     */ 
/*     */           
/* 214 */           cache.notifyAll();
/*     */         }
/*     */         else {
/*     */           
/* 218 */           cache.map.put(this.key, o);
/*     */         } 
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/SoftReferenceCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */