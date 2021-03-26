/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StaticBucketMap
/*     */   implements Map
/*     */ {
/*     */   private static final int DEFAULT_BUCKETS = 255;
/*     */   private Node[] buckets;
/*     */   private Lock[] locks;
/*     */   
/*     */   public StaticBucketMap() {
/* 115 */     this(255);
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
/*     */   public StaticBucketMap(int numBuckets) {
/* 129 */     int size = Math.max(17, numBuckets);
/*     */ 
/*     */     
/* 132 */     if (size % 2 == 0) {
/* 133 */       size--;
/*     */     }
/*     */     
/* 136 */     this.buckets = new Node[size];
/* 137 */     this.locks = new Lock[size];
/*     */     
/* 139 */     for (int i = 0; i < size; i++) {
/* 140 */       this.locks[i] = new Lock();
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
/*     */   private final int getHash(Object key) {
/* 159 */     if (key == null) {
/* 160 */       return 0;
/*     */     }
/* 162 */     int hash = key.hashCode();
/* 163 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 164 */     hash ^= hash >>> 10;
/* 165 */     hash += hash << 3;
/* 166 */     hash ^= hash >>> 6;
/* 167 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 168 */     hash ^= hash >>> 16;
/* 169 */     hash %= this.buckets.length;
/* 170 */     return (hash < 0) ? (hash * -1) : hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 180 */     int cnt = 0;
/*     */     
/* 182 */     for (int i = 0; i < this.buckets.length; i++) {
/* 183 */       cnt += (this.locks[i]).size;
/*     */     }
/* 185 */     return cnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 194 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 204 */     int hash = getHash(key);
/*     */     
/* 206 */     synchronized (this.locks[hash]) {
/* 207 */       Node n = this.buckets[hash];
/*     */       
/* 209 */       while (n != null) {
/* 210 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 211 */           return n.value;
/*     */         }
/*     */         
/* 214 */         n = n.next;
/*     */       } 
/*     */     } 
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 227 */     int hash = getHash(key);
/*     */     
/* 229 */     synchronized (this.locks[hash]) {
/* 230 */       Node n = this.buckets[hash];
/*     */       
/* 232 */       while (n != null) {
/* 233 */         if (n.key == null || (n.key != null && n.key.equals(key))) {
/* 234 */           return true;
/*     */         }
/*     */         
/* 237 */         n = n.next;
/*     */       } 
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 250 */     for (int i = 0; i < this.buckets.length; i++) {
/* 251 */       synchronized (this.locks[i]) {
/* 252 */         Node n = this.buckets[i];
/*     */         
/* 254 */         while (n != null) {
/* 255 */           if (n.value == value || (n.value != null && n.value.equals(value))) {
/* 256 */             return true;
/*     */           }
/*     */           
/* 259 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 263 */     return false;
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
/*     */   public Object put(Object key, Object value) {
/* 275 */     int hash = getHash(key);
/*     */     
/* 277 */     synchronized (this.locks[hash]) {
/* 278 */       Node n = this.buckets[hash];
/*     */       
/* 280 */       if (n == null) {
/* 281 */         n = new Node();
/* 282 */         n.key = key;
/* 283 */         n.value = value;
/* 284 */         this.buckets[hash] = n;
/* 285 */         (this.locks[hash]).size++;
/* 286 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       for (Node next = n; next != null; next = next.next) {
/* 293 */         n = next;
/*     */         
/* 295 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/* 296 */           Object returnVal = n.value;
/* 297 */           n.value = value;
/* 298 */           return returnVal;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 304 */       Node newNode = new Node();
/* 305 */       newNode.key = key;
/* 306 */       newNode.value = value;
/* 307 */       n.next = newNode;
/* 308 */       (this.locks[hash]).size++;
/*     */     } 
/* 310 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 320 */     int hash = getHash(key);
/*     */     
/* 322 */     synchronized (this.locks[hash]) {
/* 323 */       Node n = this.buckets[hash];
/* 324 */       Node prev = null;
/*     */       
/* 326 */       while (n != null) {
/* 327 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/*     */           
/* 329 */           if (null == prev) {
/*     */             
/* 331 */             this.buckets[hash] = n.next;
/*     */           } else {
/*     */             
/* 334 */             prev.next = n.next;
/*     */           } 
/* 336 */           (this.locks[hash]).size--;
/* 337 */           return n.value;
/*     */         } 
/*     */         
/* 340 */         prev = n;
/* 341 */         n = n.next;
/*     */       } 
/*     */     } 
/* 344 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 354 */     return new KeySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 363 */     return new Values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 372 */     return new EntrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map map) {
/* 383 */     Iterator i = map.keySet().iterator();
/*     */     
/* 385 */     while (i.hasNext()) {
/* 386 */       Object key = i.next();
/* 387 */       put(key, map.get(key));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 395 */     for (int i = 0; i < this.buckets.length; i++) {
/* 396 */       Lock lock = this.locks[i];
/* 397 */       synchronized (lock) {
/* 398 */         this.buckets[i] = null;
/* 399 */         lock.size = 0;
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
/*     */   public boolean equals(Object obj) {
/* 411 */     if (obj == this) {
/* 412 */       return true;
/*     */     }
/* 414 */     if (!(obj instanceof Map)) {
/* 415 */       return false;
/*     */     }
/* 417 */     Map other = (Map)obj;
/* 418 */     return entrySet().equals(other.entrySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 427 */     int hashCode = 0;
/*     */     
/* 429 */     for (int i = 0; i < this.buckets.length; i++) {
/* 430 */       synchronized (this.locks[i]) {
/* 431 */         Node n = this.buckets[i];
/*     */         
/* 433 */         while (n != null) {
/* 434 */           hashCode += n.hashCode();
/* 435 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 439 */     return hashCode;
/*     */   }
/*     */   
/*     */   private static final class Node
/*     */     implements Map.Entry, KeyValue
/*     */   {
/*     */     protected Object key;
/*     */     protected Object value;
/*     */     protected Node next;
/*     */     
/*     */     private Node() {}
/*     */     
/*     */     public Object getKey() {
/* 452 */       return this.key;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 456 */       return this.value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 460 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 465 */       if (obj == this) {
/* 466 */         return true;
/*     */       }
/* 468 */       if (!(obj instanceof Map.Entry)) {
/* 469 */         return false;
/*     */       }
/*     */       
/* 472 */       Map.Entry e2 = (Map.Entry)obj;
/* 473 */       return (((this.key == null) ? (e2.getKey() == null) : this.key.equals(e2.getKey())) && ((this.value == null) ? (e2.getValue() == null) : this.value.equals(e2.getValue())));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setValue(Object obj) {
/* 479 */       Object retVal = this.value;
/* 480 */       this.value = obj;
/* 481 */       return retVal;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Lock {
/*     */     public int size;
/*     */     
/*     */     private Lock() {}
/*     */   }
/*     */   
/*     */   private class EntryIterator implements Iterator { private ArrayList current;
/*     */     private int bucket;
/*     */     
/*     */     private EntryIterator(StaticBucketMap this$0) {
/* 495 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */       
/* 497 */       this.current = new ArrayList();
/*     */     }
/*     */     private Map.Entry last;
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     public boolean hasNext() {
/* 503 */       if (this.current.size() > 0) return true; 
/* 504 */       while (this.bucket < StaticBucketMap.this.buckets.length) {
/* 505 */         synchronized (StaticBucketMap.this.locks[this.bucket]) {
/* 506 */           StaticBucketMap.Node n = StaticBucketMap.this.buckets[this.bucket];
/* 507 */           while (n != null) {
/* 508 */             this.current.add(n);
/* 509 */             n = n.next;
/*     */           } 
/* 511 */           this.bucket++;
/* 512 */           if (this.current.size() > 0) return true; 
/*     */         } 
/*     */       } 
/* 515 */       return false;
/*     */     }
/*     */     
/*     */     protected Map.Entry nextEntry() {
/* 519 */       if (!hasNext()) throw new NoSuchElementException(); 
/* 520 */       this.last = this.current.remove(this.current.size() - 1);
/* 521 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 525 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 529 */       if (this.last == null) throw new IllegalStateException(); 
/* 530 */       StaticBucketMap.this.remove(this.last.getKey());
/* 531 */       this.last = null;
/*     */     } }
/*     */   
/*     */   private class ValueIterator extends EntryIterator {
/*     */     private ValueIterator(StaticBucketMap this$0) {
/* 536 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     } private final StaticBucketMap this$0;
/*     */     public Object next() {
/* 539 */       return nextEntry().getValue();
/*     */     } }
/*     */   private class KeyIterator extends EntryIterator { private final StaticBucketMap this$0;
/*     */     
/*     */     private KeyIterator(StaticBucketMap this$0) {
/* 544 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public Object next() {
/* 547 */       return nextEntry().getKey();
/*     */     } }
/*     */   private class EntrySet extends AbstractSet { private final StaticBucketMap this$0;
/*     */     
/*     */     private EntrySet(StaticBucketMap this$0) {
/* 552 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 555 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 559 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 563 */       return new StaticBucketMap.EntryIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 567 */       Map.Entry entry = (Map.Entry)obj;
/* 568 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 569 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 570 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 571 */           if (n.equals(entry)) return true; 
/*     */         } 
/*     */       } 
/* 574 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 578 */       if (!(obj instanceof Map.Entry)) {
/* 579 */         return false;
/*     */       }
/* 581 */       Map.Entry entry = (Map.Entry)obj;
/* 582 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 583 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 584 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 585 */           if (n.equals(entry)) {
/* 586 */             StaticBucketMap.this.remove(n.getKey());
/* 587 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 591 */       return false;
/*     */     } }
/*     */   
/*     */   private class KeySet extends AbstractSet { private final StaticBucketMap this$0;
/*     */     
/*     */     private KeySet(StaticBucketMap this$0) {
/* 597 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 600 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 604 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 608 */       return new StaticBucketMap.KeyIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 612 */       return StaticBucketMap.this.containsKey(obj);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 616 */       int hash = StaticBucketMap.this.getHash(obj);
/* 617 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 618 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 619 */           Object k = n.getKey();
/* 620 */           if (k == obj || (k != null && k.equals(obj))) {
/* 621 */             StaticBucketMap.this.remove(k);
/* 622 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 626 */       return false;
/*     */     } }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private Values(StaticBucketMap this$0) {
/* 633 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 636 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 640 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 644 */       return new StaticBucketMap.ValueIterator();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void atomic(Runnable r) {
/* 685 */     if (r == null) throw new NullPointerException(); 
/* 686 */     atomic(r, 0);
/*     */   }
/*     */   
/*     */   private void atomic(Runnable r, int bucket) {
/* 690 */     if (bucket >= this.buckets.length) {
/* 691 */       r.run();
/*     */       return;
/*     */     } 
/* 694 */     synchronized (this.locks[bucket]) {
/* 695 */       atomic(r, bucket + 1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/StaticBucketMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */