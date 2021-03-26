/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public class FastHashMap
/*     */   extends HashMap
/*     */ {
/*  70 */   protected HashMap map = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean fast = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastHashMap() {
/*  85 */     this.map = new HashMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastHashMap(int capacity) {
/*  95 */     this.map = new HashMap(capacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastHashMap(int capacity, float factor) {
/* 106 */     this.map = new HashMap(capacity, factor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastHashMap(Map map) {
/* 116 */     this.map = new HashMap(map);
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
/*     */   public boolean getFast() {
/* 129 */     return this.fast;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFast(boolean fast) {
/* 138 */     this.fast = fast;
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
/*     */   public Object get(Object key) {
/* 157 */     if (this.fast) {
/* 158 */       return this.map.get(key);
/*     */     }
/* 160 */     synchronized (this.map) {
/* 161 */       return this.map.get(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 172 */     if (this.fast) {
/* 173 */       return this.map.size();
/*     */     }
/* 175 */     synchronized (this.map) {
/* 176 */       return this.map.size();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 187 */     if (this.fast) {
/* 188 */       return this.map.isEmpty();
/*     */     }
/* 190 */     synchronized (this.map) {
/* 191 */       return this.map.isEmpty();
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
/*     */   public boolean containsKey(Object key) {
/* 204 */     if (this.fast) {
/* 205 */       return this.map.containsKey(key);
/*     */     }
/* 207 */     synchronized (this.map) {
/* 208 */       return this.map.containsKey(key);
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
/*     */   public boolean containsValue(Object value) {
/* 221 */     if (this.fast) {
/* 222 */       return this.map.containsValue(value);
/*     */     }
/* 224 */     synchronized (this.map) {
/* 225 */       return this.map.containsValue(value);
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
/*     */   public Object put(Object key, Object value) {
/* 246 */     if (this.fast) {
/* 247 */       synchronized (this) {
/* 248 */         HashMap temp = (HashMap)this.map.clone();
/* 249 */         Object result = temp.put(key, value);
/* 250 */         this.map = temp;
/* 251 */         return result;
/*     */       } 
/*     */     }
/* 254 */     synchronized (this.map) {
/* 255 */       return this.map.put(key, value);
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
/*     */   public void putAll(Map in) {
/* 267 */     if (this.fast) {
/* 268 */       synchronized (this) {
/* 269 */         HashMap temp = (HashMap)this.map.clone();
/* 270 */         temp.putAll(in);
/* 271 */         this.map = temp;
/*     */       } 
/*     */     } else {
/* 274 */       synchronized (this.map) {
/* 275 */         this.map.putAll(in);
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
/*     */   public Object remove(Object key) {
/* 288 */     if (this.fast) {
/* 289 */       synchronized (this) {
/* 290 */         HashMap temp = (HashMap)this.map.clone();
/* 291 */         Object result = temp.remove(key);
/* 292 */         this.map = temp;
/* 293 */         return result;
/*     */       } 
/*     */     }
/* 296 */     synchronized (this.map) {
/* 297 */       return this.map.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 306 */     if (this.fast) {
/* 307 */       synchronized (this) {
/* 308 */         this.map = new HashMap();
/*     */       } 
/*     */     } else {
/* 311 */       synchronized (this.map) {
/* 312 */         this.map.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 331 */     if (o == this)
/* 332 */       return true; 
/* 333 */     if (!(o instanceof Map)) {
/* 334 */       return false;
/*     */     }
/* 336 */     Map mo = (Map)o;
/*     */ 
/*     */     
/* 339 */     if (this.fast) {
/* 340 */       if (mo.size() != this.map.size()) {
/* 341 */         return false;
/*     */       }
/* 343 */       Iterator i = this.map.entrySet().iterator();
/* 344 */       while (i.hasNext()) {
/* 345 */         Map.Entry e = i.next();
/* 346 */         Object key = e.getKey();
/* 347 */         Object value = e.getValue();
/* 348 */         if (value == null) {
/* 349 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 350 */             return false; 
/*     */           continue;
/*     */         } 
/* 353 */         if (!value.equals(mo.get(key))) {
/* 354 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 358 */       return true;
/*     */     } 
/*     */     
/* 361 */     synchronized (this.map) {
/* 362 */       if (mo.size() != this.map.size()) {
/* 363 */         return false;
/*     */       }
/* 365 */       Iterator i = this.map.entrySet().iterator();
/* 366 */       while (i.hasNext()) {
/* 367 */         Map.Entry e = i.next();
/* 368 */         Object key = e.getKey();
/* 369 */         Object value = e.getValue();
/* 370 */         if (value == null) {
/* 371 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 372 */             return false; 
/*     */           continue;
/*     */         } 
/* 375 */         if (!value.equals(mo.get(key))) {
/* 376 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 380 */       return true;
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
/*     */   public int hashCode() {
/* 393 */     if (this.fast) {
/* 394 */       int h = 0;
/* 395 */       Iterator i = this.map.entrySet().iterator();
/* 396 */       while (i.hasNext()) {
/* 397 */         h += i.next().hashCode();
/*     */       }
/* 399 */       return h;
/*     */     } 
/* 401 */     synchronized (this.map) {
/* 402 */       int h = 0;
/* 403 */       Iterator i = this.map.entrySet().iterator();
/* 404 */       while (i.hasNext()) {
/* 405 */         h += i.next().hashCode();
/*     */       }
/* 407 */       return h;
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
/*     */   public Object clone() {
/* 419 */     FastHashMap results = null;
/* 420 */     if (this.fast) {
/* 421 */       results = new FastHashMap(this.map);
/*     */     } else {
/* 423 */       synchronized (this.map) {
/* 424 */         results = new FastHashMap(this.map);
/*     */       } 
/*     */     } 
/* 427 */     results.setFast(getFast());
/* 428 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 439 */     return new EntrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 446 */     return new KeySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 453 */     return new Values();
/*     */   }
/*     */ 
/*     */   
/*     */   private abstract class CollectionView
/*     */     implements Collection
/*     */   {
/*     */     private final FastHashMap this$0;
/*     */ 
/*     */     
/*     */     public CollectionView(FastHashMap this$0) {
/* 464 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() {
/* 472 */       if (this.this$0.fast) {
/* 473 */         synchronized (this.this$0) {
/* 474 */           this.this$0.map = new HashMap();
/*     */         } 
/*     */       } else {
/* 477 */         synchronized (this.this$0.map) {
/* 478 */           get(this.this$0.map).clear();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 484 */       if (this.this$0.fast) {
/* 485 */         synchronized (this.this$0) {
/* 486 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 487 */           boolean r = get(temp).remove(o);
/* 488 */           this.this$0.map = temp;
/* 489 */           return r;
/*     */         } 
/*     */       }
/* 492 */       synchronized (this.this$0.map) {
/* 493 */         return get(this.this$0.map).remove(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean removeAll(Collection o) {
/* 499 */       if (this.this$0.fast) {
/* 500 */         synchronized (this.this$0) {
/* 501 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 502 */           boolean r = get(temp).removeAll(o);
/* 503 */           this.this$0.map = temp;
/* 504 */           return r;
/*     */         } 
/*     */       }
/* 507 */       synchronized (this.this$0.map) {
/* 508 */         return get(this.this$0.map).removeAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean retainAll(Collection o) {
/* 514 */       if (this.this$0.fast) {
/* 515 */         synchronized (this.this$0) {
/* 516 */           HashMap temp = (HashMap)this.this$0.map.clone();
/* 517 */           boolean r = get(temp).retainAll(o);
/* 518 */           this.this$0.map = temp;
/* 519 */           return r;
/*     */         } 
/*     */       }
/* 522 */       synchronized (this.this$0.map) {
/* 523 */         return get(this.this$0.map).retainAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 529 */       if (this.this$0.fast) {
/* 530 */         return get(this.this$0.map).size();
/*     */       }
/* 532 */       synchronized (this.this$0.map) {
/* 533 */         return get(this.this$0.map).size();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 540 */       if (this.this$0.fast) {
/* 541 */         return get(this.this$0.map).isEmpty();
/*     */       }
/* 543 */       synchronized (this.this$0.map) {
/* 544 */         return get(this.this$0.map).isEmpty();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean contains(Object o) {
/* 550 */       if (this.this$0.fast) {
/* 551 */         return get(this.this$0.map).contains(o);
/*     */       }
/* 553 */       synchronized (this.this$0.map) {
/* 554 */         return get(this.this$0.map).contains(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsAll(Collection o) {
/* 560 */       if (this.this$0.fast) {
/* 561 */         return get(this.this$0.map).containsAll(o);
/*     */       }
/* 563 */       synchronized (this.this$0.map) {
/* 564 */         return get(this.this$0.map).containsAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray(Object[] o) {
/* 570 */       if (this.this$0.fast) {
/* 571 */         return get(this.this$0.map).toArray(o);
/*     */       }
/* 573 */       synchronized (this.this$0.map) {
/* 574 */         return get(this.this$0.map).toArray(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray() {
/* 580 */       if (this.this$0.fast) {
/* 581 */         return get(this.this$0.map).toArray();
/*     */       }
/* 583 */       synchronized (this.this$0.map) {
/* 584 */         return get(this.this$0.map).toArray();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 591 */       if (o == this) return true; 
/* 592 */       if (this.this$0.fast) {
/* 593 */         return get(this.this$0.map).equals(o);
/*     */       }
/* 595 */       synchronized (this.this$0.map) {
/* 596 */         return get(this.this$0.map).equals(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 602 */       if (this.this$0.fast) {
/* 603 */         return get(this.this$0.map).hashCode();
/*     */       }
/* 605 */       synchronized (this.this$0.map) {
/* 606 */         return get(this.this$0.map).hashCode();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean add(Object o) {
/* 612 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection c) {
/* 616 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 620 */       return new CollectionViewIterator(this);
/*     */     }
/*     */     protected abstract Collection get(Map param1Map);
/*     */     protected abstract Object iteratorNext(Map.Entry param1Entry);
/*     */     
/*     */     private class CollectionViewIterator implements Iterator { private Map expected;
/*     */       private Map.Entry lastReturned;
/*     */       
/*     */       public CollectionViewIterator(FastHashMap.CollectionView this$1) {
/* 629 */         this.this$1 = this$1; this.lastReturned = null;
/* 630 */         this.expected = this$1.this$0.map;
/* 631 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       private Iterator iterator; private final FastHashMap.CollectionView this$1;
/*     */       public boolean hasNext() {
/* 635 */         if (this.expected != this.this$1.this$0.map) {
/* 636 */           throw new ConcurrentModificationException();
/*     */         }
/* 638 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 642 */         if (this.expected != this.this$1.this$0.map) {
/* 643 */           throw new ConcurrentModificationException();
/*     */         }
/* 645 */         this.lastReturned = this.iterator.next();
/* 646 */         return this.this$1.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 650 */         if (this.lastReturned == null) {
/* 651 */           throw new IllegalStateException();
/*     */         }
/* 653 */         if (this.this$1.this$0.fast) {
/* 654 */           synchronized (this.this$1.this$0) {
/* 655 */             if (this.expected != this.this$1.this$0.map) {
/* 656 */               throw new ConcurrentModificationException();
/*     */             }
/* 658 */             this.this$1.this$0.remove(this.lastReturned.getKey());
/* 659 */             this.lastReturned = null;
/* 660 */             this.expected = this.this$1.this$0.map;
/*     */           } 
/*     */         } else {
/* 663 */           this.iterator.remove();
/* 664 */           this.lastReturned = null;
/*     */         } 
/*     */       } }
/*     */   }
/*     */   
/*     */   private class KeySet extends CollectionView implements Set {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private KeySet(FastHashMap this$0) {
/* 673 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 676 */       return map.keySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 680 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends CollectionView {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private Values(FastHashMap this$0) {
/* 688 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 691 */       return map.values();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 695 */       return entry.getValue();
/*     */     } }
/*     */   
/*     */   private class EntrySet extends CollectionView implements Set {
/*     */     private final FastHashMap this$0;
/*     */     
/*     */     private EntrySet(FastHashMap this$0) {
/* 702 */       FastHashMap.this = FastHashMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 705 */       return map.entrySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 709 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/FastHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */