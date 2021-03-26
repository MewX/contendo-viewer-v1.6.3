/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   private Node[] m_buckets;
/*     */   private Lock[] m_locks;
/*     */   
/*     */   public StaticBucketMap() {
/* 112 */     this(255);
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
/*     */   public StaticBucketMap(int numBuckets) {
/* 127 */     int size = Math.max(17, numBuckets);
/*     */ 
/*     */     
/* 130 */     if (size % 2 == 0)
/*     */     {
/* 132 */       size--;
/*     */     }
/*     */     
/* 135 */     this.m_buckets = new Node[size];
/* 136 */     this.m_locks = new Lock[size];
/*     */     
/* 138 */     for (int i = 0; i < size; i++)
/*     */     {
/* 140 */       this.m_locks[i] = new Lock();
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
/* 159 */     if (key == null) return 0; 
/* 160 */     int hash = key.hashCode();
/* 161 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 162 */     hash ^= hash >>> 10;
/* 163 */     hash += hash << 3;
/* 164 */     hash ^= hash >>> 6;
/* 165 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 166 */     hash ^= hash >>> 16;
/* 167 */     hash %= this.m_buckets.length;
/* 168 */     return (hash < 0) ? (hash * -1) : hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 176 */     return new KeySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 184 */     int cnt = 0;
/*     */     
/* 186 */     for (int i = 0; i < this.m_buckets.length; i++)
/*     */     {
/* 188 */       cnt += (this.m_locks[i]).size;
/*     */     }
/*     */     
/* 191 */     return cnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 199 */     int hash = getHash(key);
/*     */     
/* 201 */     synchronized (this.m_locks[hash]) {
/*     */       
/* 203 */       Node n = this.m_buckets[hash];
/*     */       
/* 205 */       if (n == null) {
/*     */         
/* 207 */         n = new Node();
/* 208 */         n.key = key;
/* 209 */         n.value = value;
/* 210 */         this.m_buckets[hash] = n;
/* 211 */         (this.m_locks[hash]).size++;
/* 212 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 218 */       for (Node next = n; next != null; next = next.next) {
/*     */         
/* 220 */         n = next;
/*     */         
/* 222 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/*     */           
/* 224 */           Object returnVal = n.value;
/* 225 */           n.value = value;
/* 226 */           return returnVal;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 232 */       Node newNode = new Node();
/* 233 */       newNode.key = key;
/* 234 */       newNode.value = value;
/* 235 */       n.next = newNode;
/* 236 */       (this.m_locks[hash]).size++;
/*     */     } 
/*     */     
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 247 */     int hash = getHash(key);
/*     */     
/* 249 */     synchronized (this.m_locks[hash]) {
/*     */       
/* 251 */       Node n = this.m_buckets[hash];
/*     */       
/* 253 */       while (n != null) {
/*     */         
/* 255 */         if (n.key == key || (n.key != null && n.key.equals(key)))
/*     */         {
/* 257 */           return n.value;
/*     */         }
/*     */         
/* 260 */         n = n.next;
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 272 */     int hash = getHash(key);
/*     */     
/* 274 */     synchronized (this.m_locks[hash]) {
/*     */       
/* 276 */       Node n = this.m_buckets[hash];
/*     */       
/* 278 */       while (n != null) {
/*     */         
/* 280 */         if (n.key == null || (n.key != null && n.key.equals(key)))
/*     */         {
/* 282 */           return true;
/*     */         }
/*     */         
/* 285 */         n = n.next;
/*     */       } 
/*     */     } 
/*     */     
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 297 */     for (int i = 0; i < this.m_buckets.length; i++) {
/*     */       
/* 299 */       synchronized (this.m_locks[i]) {
/*     */         
/* 301 */         Node n = this.m_buckets[i];
/*     */         
/* 303 */         while (n != null) {
/*     */           
/* 305 */           if (n.value == value || (n.value != null && n.value.equals(value)))
/*     */           {
/*     */             
/* 308 */             return true;
/*     */           }
/*     */           
/* 311 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 316 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 324 */     return new Values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 332 */     return new EntrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map other) {
/* 340 */     Iterator i = other.keySet().iterator();
/*     */     
/* 342 */     while (i.hasNext()) {
/*     */       
/* 344 */       Object key = i.next();
/* 345 */       put(key, other.get(key));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 354 */     int hash = getHash(key);
/*     */     
/* 356 */     synchronized (this.m_locks[hash]) {
/*     */       
/* 358 */       Node n = this.m_buckets[hash];
/* 359 */       Node prev = null;
/*     */       
/* 361 */       while (n != null) {
/*     */         
/* 363 */         if (n.key == key || (n.key != null && n.key.equals(key))) {
/*     */ 
/*     */           
/* 366 */           if (null == prev) {
/*     */ 
/*     */             
/* 369 */             this.m_buckets[hash] = n.next;
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 374 */             prev.next = n.next;
/*     */           } 
/* 376 */           (this.m_locks[hash]).size--;
/* 377 */           return n.value;
/*     */         } 
/*     */         
/* 380 */         prev = n;
/* 381 */         n = n.next;
/*     */       } 
/*     */     } 
/*     */     
/* 385 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isEmpty() {
/* 393 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clear() {
/* 401 */     for (int i = 0; i < this.m_buckets.length; i++) {
/*     */       
/* 403 */       Lock lock = this.m_locks[i];
/* 404 */       synchronized (lock) {
/* 405 */         this.m_buckets[i] = null;
/* 406 */         lock.size = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean equals(Object obj) {
/* 416 */     if (obj == null) return false; 
/* 417 */     if (obj == this) return true;
/*     */     
/* 419 */     if (!(obj instanceof Map)) return false;
/*     */     
/* 421 */     Map other = (Map)obj;
/*     */     
/* 423 */     return entrySet().equals(other.entrySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int hashCode() {
/* 431 */     int hashCode = 0;
/*     */     
/* 433 */     for (int i = 0; i < this.m_buckets.length; i++) {
/*     */       
/* 435 */       synchronized (this.m_locks[i]) {
/*     */         
/* 437 */         Node n = this.m_buckets[i];
/*     */         
/* 439 */         while (n != null) {
/*     */           
/* 441 */           hashCode += n.hashCode();
/* 442 */           n = n.next;
/*     */         } 
/*     */       } 
/*     */     } 
/* 446 */     return hashCode;
/*     */   }
/*     */ 
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
/* 460 */       return this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getValue() {
/* 465 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 470 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 475 */       if (o == null) return false; 
/* 476 */       if (o == this) return true;
/*     */       
/* 478 */       if (!(o instanceof Map.Entry)) {
/* 479 */         return false;
/*     */       }
/* 481 */       Map.Entry e2 = (Map.Entry)o;
/*     */       
/* 483 */       if ((this.key == null) ? (e2.getKey() == null) : this.key.equals(e2.getKey())) if ((this.value == null) ? (e2.getValue() == null) : this.value.equals(e2.getValue()));  return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setValue(Object val) {
/* 491 */       Object retVal = this.value;
/* 492 */       this.value = val;
/* 493 */       return retVal;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Lock { public int size;
/*     */     
/*     */     private Lock() {} }
/*     */   
/*     */   private class EntryIterator implements Iterator { private ArrayList current;
/*     */     
/*     */     private EntryIterator(StaticBucketMap this$0) {
/* 504 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */       
/* 506 */       this.current = new ArrayList();
/*     */     }
/*     */     private int bucket; private Map.Entry last;
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     public boolean hasNext() {
/* 512 */       if (this.current.size() > 0) return true; 
/* 513 */       while (this.bucket < StaticBucketMap.this.m_buckets.length) {
/* 514 */         synchronized (StaticBucketMap.this.m_locks[this.bucket]) {
/* 515 */           StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[this.bucket];
/* 516 */           while (n != null) {
/* 517 */             this.current.add(n);
/* 518 */             n = n.next;
/*     */           } 
/* 520 */           this.bucket++;
/* 521 */           if (this.current.size() > 0) return true; 
/*     */         } 
/*     */       } 
/* 524 */       return false;
/*     */     }
/*     */     
/*     */     protected Map.Entry nextEntry() {
/* 528 */       if (!hasNext()) throw new NoSuchElementException(); 
/* 529 */       this.last = this.current.remove(this.current.size() - 1);
/* 530 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 534 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 538 */       if (this.last == null) throw new IllegalStateException(); 
/* 539 */       StaticBucketMap.this.remove(this.last.getKey());
/* 540 */       this.last = null;
/*     */     } }
/*     */   private class ValueIterator extends EntryIterator { private final StaticBucketMap this$0;
/*     */     
/*     */     private ValueIterator(StaticBucketMap this$0) {
/* 545 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public Object next() {
/* 548 */       return nextEntry().getValue();
/*     */     } }
/*     */   private class KeyIterator extends EntryIterator { private final StaticBucketMap this$0;
/*     */     
/*     */     private KeyIterator(StaticBucketMap this$0) {
/* 553 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public Object next() {
/* 556 */       return nextEntry().getKey();
/*     */     } }
/*     */   private class EntrySet extends AbstractSet { private final StaticBucketMap this$0;
/*     */     
/*     */     private EntrySet(StaticBucketMap this$0) {
/* 561 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 564 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 568 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 572 */       return new StaticBucketMap.EntryIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 576 */       Map.Entry entry = (Map.Entry)o;
/* 577 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 578 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 579 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 580 */           if (n.equals(entry)) return true; 
/*     */         } 
/*     */       } 
/* 583 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 587 */       if (!(obj instanceof Map.Entry)) {
/* 588 */         return false;
/*     */       }
/* 590 */       Map.Entry entry = (Map.Entry)obj;
/* 591 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 592 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 593 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 594 */           if (n.equals(entry)) {
/* 595 */             StaticBucketMap.this.remove(n.getKey());
/* 596 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 600 */       return false;
/*     */     } }
/*     */   
/*     */   private class KeySet extends AbstractSet { private final StaticBucketMap this$0;
/*     */     
/*     */     private KeySet(StaticBucketMap this$0) {
/* 606 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 609 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 613 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 617 */       return new StaticBucketMap.KeyIterator();
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 621 */       return StaticBucketMap.this.containsKey(o);
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 625 */       int hash = StaticBucketMap.this.getHash(o);
/* 626 */       synchronized (StaticBucketMap.this.m_locks[hash]) {
/* 627 */         for (StaticBucketMap.Node n = StaticBucketMap.this.m_buckets[hash]; n != null; n = n.next) {
/* 628 */           Object k = n.getKey();
/* 629 */           if (k == o || (k != null && k.equals(o))) {
/* 630 */             StaticBucketMap.this.remove(k);
/* 631 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 635 */       return false;
/*     */     } }
/*     */   
/*     */   private class Values extends AbstractCollection {
/*     */     private final StaticBucketMap this$0;
/*     */     
/*     */     private Values(StaticBucketMap this$0) {
/* 642 */       StaticBucketMap.this = StaticBucketMap.this;
/*     */     }
/*     */     public int size() {
/* 645 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 649 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 653 */       return new StaticBucketMap.ValueIterator();
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
/* 694 */     if (r == null) throw new NullPointerException(); 
/* 695 */     atomic(r, 0);
/*     */   }
/*     */   
/*     */   private void atomic(Runnable r, int bucket) {
/* 699 */     if (bucket >= this.m_buckets.length) {
/* 700 */       r.run();
/*     */       return;
/*     */     } 
/* 703 */     synchronized (this.m_locks[bucket]) {
/* 704 */       atomic(r, bucket + 1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/StaticBucketMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */