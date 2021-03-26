/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.collections.collection.CompositeCollection;
/*     */ import org.apache.commons.collections.set.CompositeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeMap
/*     */   implements Map
/*     */ {
/*     */   private Map[] composite;
/*     */   private MapMutator mutator;
/*     */   
/*     */   public CompositeMap() {
/*  51 */     this(new Map[0], (MapMutator)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeMap(Map one, Map two) {
/*  62 */     this(new Map[] { one, two }, (MapMutator)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeMap(Map one, Map two, MapMutator mutator) {
/*  73 */     this(new Map[] { one, two }, mutator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeMap(Map[] composite) {
/*  84 */     this(composite, (MapMutator)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeMap(Map[] composite, MapMutator mutator) {
/*  95 */     this.mutator = mutator;
/*  96 */     this.composite = new Map[0];
/*  97 */     for (int i = composite.length - 1; i >= 0; i--) {
/*  98 */       addComposited(composite[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMutator(MapMutator mutator) {
/* 109 */     this.mutator = mutator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addComposited(Map map) throws IllegalArgumentException {
/* 120 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 121 */       Collection intersect = CollectionUtils.intersection(this.composite[i].keySet(), map.keySet());
/* 122 */       if (intersect.size() != 0) {
/* 123 */         if (this.mutator == null) {
/* 124 */           throw new IllegalArgumentException("Key collision adding Map to CompositeMap");
/*     */         }
/*     */         
/* 127 */         this.mutator.resolveCollision(this, this.composite[i], map, intersect);
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     Map[] temp = new Map[this.composite.length + 1];
/* 132 */     System.arraycopy(this.composite, 0, temp, 0, this.composite.length);
/* 133 */     temp[temp.length - 1] = map;
/* 134 */     this.composite = temp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Map removeComposited(Map map) {
/* 144 */     int size = this.composite.length;
/* 145 */     for (int i = 0; i < size; i++) {
/* 146 */       if (this.composite[i].equals(map)) {
/* 147 */         Map[] temp = new Map[size - 1];
/* 148 */         System.arraycopy(this.composite, 0, temp, 0, i);
/* 149 */         System.arraycopy(this.composite, i + 1, temp, i, size - i - 1);
/* 150 */         this.composite = temp;
/* 151 */         return map;
/*     */       } 
/*     */     } 
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 164 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 165 */       this.composite[i].clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 186 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 187 */       if (this.composite[i].containsKey(key)) {
/* 188 */         return true;
/*     */       }
/*     */     } 
/* 191 */     return false;
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
/*     */   public boolean containsValue(Object value) {
/* 211 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 212 */       if (this.composite[i].containsValue(value)) {
/* 213 */         return true;
/*     */       }
/*     */     } 
/* 216 */     return false;
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
/*     */   public Set entrySet() {
/* 237 */     CompositeSet entries = new CompositeSet();
/* 238 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 239 */       entries.addComposited(this.composite[i].entrySet());
/*     */     }
/* 241 */     return (Set)entries;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 269 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 270 */       if (this.composite[i].containsKey(key)) {
/* 271 */         return this.composite[i].get(key);
/*     */       }
/*     */     } 
/* 274 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 283 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 284 */       if (!this.composite[i].isEmpty()) {
/* 285 */         return false;
/*     */       }
/*     */     } 
/* 288 */     return true;
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
/*     */   public Set keySet() {
/* 307 */     CompositeSet keys = new CompositeSet();
/* 308 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 309 */       keys.addComposited(this.composite[i].keySet());
/*     */     }
/* 311 */     return (Set)keys;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 340 */     if (this.mutator == null) {
/* 341 */       throw new UnsupportedOperationException("No mutator specified");
/*     */     }
/* 343 */     return this.mutator.put(this, this.composite, key, value);
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
/*     */   
/*     */   public void putAll(Map map) {
/* 369 */     if (this.mutator == null) {
/* 370 */       throw new UnsupportedOperationException("No mutator specified");
/*     */     }
/* 372 */     this.mutator.putAll(this, this.composite, map);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 401 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 402 */       if (this.composite[i].containsKey(key)) {
/* 403 */         return this.composite[i].remove(key);
/*     */       }
/*     */     } 
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 417 */     int size = 0;
/* 418 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 419 */       size += this.composite[i].size();
/*     */     }
/* 421 */     return size;
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
/*     */   public Collection values() {
/* 438 */     CompositeCollection keys = new CompositeCollection();
/* 439 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 440 */       keys.addComposited(this.composite[i].values());
/*     */     }
/* 442 */     return (Collection)keys;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 452 */     if (obj instanceof Map) {
/* 453 */       Map map = (Map)obj;
/* 454 */       return entrySet().equals(map.entrySet());
/*     */     } 
/* 456 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 463 */     int code = 0;
/* 464 */     for (Iterator i = entrySet().iterator(); i.hasNext();) {
/* 465 */       code += i.next().hashCode();
/*     */     }
/* 467 */     return code;
/*     */   }
/*     */   
/*     */   public static interface MapMutator {
/*     */     void resolveCollision(CompositeMap param1CompositeMap, Map param1Map1, Map param1Map2, Collection param1Collection);
/*     */     
/*     */     Object put(CompositeMap param1CompositeMap, Map[] param1ArrayOfMap, Object param1Object1, Object param1Object2);
/*     */     
/*     */     void putAll(CompositeMap param1CompositeMap, Map[] param1ArrayOfMap, Map param1Map);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/CompositeMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */