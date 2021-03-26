/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiHashMap
/*     */   extends HashMap
/*     */   implements MultiMap
/*     */ {
/*  67 */   private transient Collection values = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long serialVersionUID = 1943563828307035349L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiHashMap(int initialCapacity) {
/*  85 */     super(initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiHashMap(int initialCapacity, float loadFactor) {
/*  95 */     super(initialCapacity, loadFactor);
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
/*     */   public MultiHashMap(Map mapToCopy) {
/* 113 */     super((int)(mapToCopy.size() * 1.4F));
/* 114 */     if (mapToCopy instanceof MultiMap) {
/* 115 */       for (Iterator it = mapToCopy.entrySet().iterator(); it.hasNext(); ) {
/* 116 */         Map.Entry entry = it.next();
/* 117 */         Collection coll = (Collection)entry.getValue();
/* 118 */         Collection newColl = createCollection(coll);
/* 119 */         super.put(entry.getKey(), newColl);
/*     */       } 
/*     */     } else {
/* 122 */       putAll(mapToCopy);
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
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 134 */     s.defaultReadObject();
/*     */ 
/*     */     
/* 137 */     String version = "1.2";
/*     */     try {
/* 139 */       version = System.getProperty("java.version");
/* 140 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (version.startsWith("1.2") || version.startsWith("1.3")) {
/* 145 */       for (Iterator iterator = entrySet().iterator(); iterator.hasNext(); ) {
/* 146 */         Map.Entry entry = iterator.next();
/*     */         
/* 148 */         super.put(entry.getKey(), ((Collection)entry.getValue()).iterator().next());
/*     */       } 
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
/*     */   public int totalSize() {
/* 161 */     int total = 0;
/* 162 */     Collection values = super.values();
/* 163 */     for (Iterator it = values.iterator(); it.hasNext(); ) {
/* 164 */       Collection coll = (Collection)it.next();
/* 165 */       total += coll.size();
/*     */     } 
/* 167 */     return total;
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
/*     */   public Collection getCollection(Object key) {
/* 179 */     return (Collection)get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Object key) {
/* 190 */     Collection coll = getCollection(key);
/* 191 */     if (coll == null) {
/* 192 */       return 0;
/*     */     }
/* 194 */     return coll.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator(Object key) {
/* 205 */     Collection coll = getCollection(key);
/* 206 */     if (coll == null) {
/* 207 */       return EmptyIterator.INSTANCE;
/*     */     }
/* 209 */     return coll.iterator();
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
/*     */   public Object put(Object key, Object value) {
/* 225 */     Collection coll = getCollection(key);
/* 226 */     if (coll == null) {
/* 227 */       coll = createCollection(null);
/* 228 */       super.put(key, coll);
/*     */     } 
/* 230 */     boolean results = coll.add(value);
/* 231 */     return results ? value : null;
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
/*     */   public boolean putAll(Object key, Collection values) {
/* 243 */     if (values == null || values.size() == 0) {
/* 244 */       return false;
/*     */     }
/* 246 */     Collection coll = getCollection(key);
/* 247 */     if (coll == null) {
/* 248 */       coll = createCollection(values);
/* 249 */       if (coll.size() == 0) {
/* 250 */         return false;
/*     */       }
/* 252 */       super.put(key, coll);
/* 253 */       return true;
/*     */     } 
/* 255 */     return coll.addAll(values);
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
/*     */   public boolean containsValue(Object value) {
/* 268 */     Set pairs = entrySet();
/*     */     
/* 270 */     if (pairs == null) {
/* 271 */       return false;
/*     */     }
/* 273 */     Iterator pairsIterator = pairs.iterator();
/* 274 */     while (pairsIterator.hasNext()) {
/* 275 */       Map.Entry keyValuePair = pairsIterator.next();
/* 276 */       Collection coll = (Collection)keyValuePair.getValue();
/* 277 */       if (coll.contains(value)) {
/* 278 */         return true;
/*     */       }
/*     */     } 
/* 281 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object key, Object value) {
/* 292 */     Collection coll = getCollection(key);
/* 293 */     if (coll == null) {
/* 294 */       return false;
/*     */     }
/* 296 */     return coll.contains(value);
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
/*     */   public Object remove(Object key, Object item) {
/* 313 */     Collection valuesForKey = getCollection(key);
/* 314 */     if (valuesForKey == null) {
/* 315 */       return null;
/*     */     }
/* 317 */     valuesForKey.remove(item);
/*     */ 
/*     */ 
/*     */     
/* 321 */     if (valuesForKey.isEmpty()) {
/* 322 */       remove(key);
/*     */     }
/* 324 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 334 */     Set pairs = entrySet();
/* 335 */     Iterator pairsIterator = pairs.iterator();
/* 336 */     while (pairsIterator.hasNext()) {
/* 337 */       Map.Entry keyValuePair = pairsIterator.next();
/* 338 */       Collection coll = (Collection)keyValuePair.getValue();
/* 339 */       coll.clear();
/*     */     } 
/* 341 */     super.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 352 */     Collection vs = this.values;
/* 353 */     return (vs != null) ? vs : (this.values = new Values());
/*     */   }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final MultiHashMap this$0;
/*     */     
/*     */     private Values(MultiHashMap this$0) {
/* 360 */       MultiHashMap.this = MultiHashMap.this;
/*     */     }
/*     */     public Iterator iterator() {
/* 363 */       return new MultiHashMap.ValueIterator();
/*     */     }
/*     */     
/*     */     public int size() {
/* 367 */       int compt = 0;
/* 368 */       Iterator it = iterator();
/* 369 */       while (it.hasNext()) {
/* 370 */         it.next();
/* 371 */         compt++;
/*     */       } 
/* 373 */       return compt;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 377 */       MultiHashMap.this.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator
/*     */     implements Iterator
/*     */   {
/*     */     private Iterator backedIterator;
/*     */     private Iterator tempIterator;
/*     */     private final MultiHashMap this$0;
/*     */     
/*     */     private ValueIterator(MultiHashMap this$0) {
/* 389 */       MultiHashMap.this = MultiHashMap.this;
/* 390 */       this.backedIterator = MultiHashMap.this.values().iterator();
/*     */     }
/*     */     
/*     */     private boolean searchNextIterator() {
/* 394 */       while (this.tempIterator == null || !this.tempIterator.hasNext()) {
/* 395 */         if (!this.backedIterator.hasNext()) {
/* 396 */           return false;
/*     */         }
/* 398 */         this.tempIterator = ((Collection)this.backedIterator.next()).iterator();
/*     */       } 
/* 400 */       return true;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 404 */       return searchNextIterator();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 408 */       if (!searchNextIterator()) {
/* 409 */         throw new NoSuchElementException();
/*     */       }
/* 411 */       return this.tempIterator.next();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 415 */       if (this.tempIterator == null) {
/* 416 */         throw new IllegalStateException();
/*     */       }
/* 418 */       this.tempIterator.remove();
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
/*     */   public Object clone() {
/* 432 */     MultiHashMap cloned = (MultiHashMap)super.clone();
/*     */ 
/*     */     
/* 435 */     for (Iterator it = cloned.entrySet().iterator(); it.hasNext(); ) {
/* 436 */       Map.Entry entry = it.next();
/* 437 */       Collection coll = (Collection)entry.getValue();
/* 438 */       Collection newColl = createCollection(coll);
/* 439 */       entry.setValue(newColl);
/*     */     } 
/* 441 */     return cloned;
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
/*     */   protected Collection createCollection(Collection coll) {
/* 453 */     if (coll == null) {
/* 454 */       return new ArrayList();
/*     */     }
/* 456 */     return new ArrayList(coll);
/*     */   }
/*     */   
/*     */   public MultiHashMap() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/MultiHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */