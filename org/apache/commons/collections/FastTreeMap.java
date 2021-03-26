/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastTreeMap
/*     */   extends TreeMap
/*     */ {
/*  72 */   protected TreeMap map = null;
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
/*     */   
/*     */   public FastTreeMap() {
/*  88 */     this.map = new TreeMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastTreeMap(Comparator comparator) {
/*  98 */     this.map = new TreeMap(comparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastTreeMap(Map map) {
/* 109 */     this.map = new TreeMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastTreeMap(SortedMap map) {
/* 120 */     this.map = new TreeMap(map);
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
/* 133 */     return this.fast;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFast(boolean fast) {
/* 142 */     this.fast = fast;
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
/* 161 */     if (this.fast) {
/* 162 */       return this.map.get(key);
/*     */     }
/* 164 */     synchronized (this.map) {
/* 165 */       return this.map.get(key);
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
/* 176 */     if (this.fast) {
/* 177 */       return this.map.size();
/*     */     }
/* 179 */     synchronized (this.map) {
/* 180 */       return this.map.size();
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
/* 191 */     if (this.fast) {
/* 192 */       return this.map.isEmpty();
/*     */     }
/* 194 */     synchronized (this.map) {
/* 195 */       return this.map.isEmpty();
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
/* 208 */     if (this.fast) {
/* 209 */       return this.map.containsKey(key);
/*     */     }
/* 211 */     synchronized (this.map) {
/* 212 */       return this.map.containsKey(key);
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
/* 225 */     if (this.fast) {
/* 226 */       return this.map.containsValue(value);
/*     */     }
/* 228 */     synchronized (this.map) {
/* 229 */       return this.map.containsValue(value);
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
/*     */   public Comparator comparator() {
/* 241 */     if (this.fast) {
/* 242 */       return this.map.comparator();
/*     */     }
/* 244 */     synchronized (this.map) {
/* 245 */       return this.map.comparator();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 256 */     if (this.fast) {
/* 257 */       return this.map.firstKey();
/*     */     }
/* 259 */     synchronized (this.map) {
/* 260 */       return this.map.firstKey();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object lastKey() {
/* 271 */     if (this.fast) {
/* 272 */       return this.map.lastKey();
/*     */     }
/* 274 */     synchronized (this.map) {
/* 275 */       return this.map.lastKey();
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
/*     */   public Object put(Object key, Object value) {
/* 297 */     if (this.fast) {
/* 298 */       synchronized (this) {
/* 299 */         TreeMap temp = (TreeMap)this.map.clone();
/* 300 */         Object result = temp.put(key, value);
/* 301 */         this.map = temp;
/* 302 */         return result;
/*     */       } 
/*     */     }
/* 305 */     synchronized (this.map) {
/* 306 */       return this.map.put(key, value);
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
/* 318 */     if (this.fast) {
/* 319 */       synchronized (this) {
/* 320 */         TreeMap temp = (TreeMap)this.map.clone();
/* 321 */         temp.putAll(in);
/* 322 */         this.map = temp;
/*     */       } 
/*     */     } else {
/* 325 */       synchronized (this.map) {
/* 326 */         this.map.putAll(in);
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
/* 339 */     if (this.fast) {
/* 340 */       synchronized (this) {
/* 341 */         TreeMap temp = (TreeMap)this.map.clone();
/* 342 */         Object result = temp.remove(key);
/* 343 */         this.map = temp;
/* 344 */         return result;
/*     */       } 
/*     */     }
/* 347 */     synchronized (this.map) {
/* 348 */       return this.map.remove(key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 357 */     if (this.fast) {
/* 358 */       synchronized (this) {
/* 359 */         this.map = new TreeMap();
/*     */       } 
/*     */     } else {
/* 362 */       synchronized (this.map) {
/* 363 */         this.map.clear();
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
/*     */   
/*     */   public boolean equals(Object o) {
/* 383 */     if (o == this)
/* 384 */       return true; 
/* 385 */     if (!(o instanceof Map)) {
/* 386 */       return false;
/*     */     }
/* 388 */     Map mo = (Map)o;
/*     */ 
/*     */     
/* 391 */     if (this.fast) {
/* 392 */       if (mo.size() != this.map.size()) {
/* 393 */         return false;
/*     */       }
/* 395 */       Iterator i = this.map.entrySet().iterator();
/* 396 */       while (i.hasNext()) {
/* 397 */         Map.Entry e = i.next();
/* 398 */         Object key = e.getKey();
/* 399 */         Object value = e.getValue();
/* 400 */         if (value == null) {
/* 401 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 402 */             return false; 
/*     */           continue;
/*     */         } 
/* 405 */         if (!value.equals(mo.get(key))) {
/* 406 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 410 */       return true;
/*     */     } 
/* 412 */     synchronized (this.map) {
/* 413 */       if (mo.size() != this.map.size()) {
/* 414 */         return false;
/*     */       }
/* 416 */       Iterator i = this.map.entrySet().iterator();
/* 417 */       while (i.hasNext()) {
/* 418 */         Map.Entry e = i.next();
/* 419 */         Object key = e.getKey();
/* 420 */         Object value = e.getValue();
/* 421 */         if (value == null) {
/* 422 */           if (mo.get(key) != null || !mo.containsKey(key))
/* 423 */             return false; 
/*     */           continue;
/*     */         } 
/* 426 */         if (!value.equals(mo.get(key))) {
/* 427 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 431 */       return true;
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
/* 444 */     if (this.fast) {
/* 445 */       int h = 0;
/* 446 */       Iterator i = this.map.entrySet().iterator();
/* 447 */       while (i.hasNext()) {
/* 448 */         h += i.next().hashCode();
/*     */       }
/* 450 */       return h;
/*     */     } 
/* 452 */     synchronized (this.map) {
/* 453 */       int h = 0;
/* 454 */       Iterator i = this.map.entrySet().iterator();
/* 455 */       while (i.hasNext()) {
/* 456 */         h += i.next().hashCode();
/*     */       }
/* 458 */       return h;
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
/* 470 */     FastTreeMap results = null;
/* 471 */     if (this.fast) {
/* 472 */       results = new FastTreeMap(this.map);
/*     */     } else {
/* 474 */       synchronized (this.map) {
/* 475 */         results = new FastTreeMap(this.map);
/*     */       } 
/*     */     } 
/* 478 */     results.setFast(getFast());
/* 479 */     return results;
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
/*     */   public SortedMap headMap(Object key) {
/* 494 */     if (this.fast) {
/* 495 */       return this.map.headMap(key);
/*     */     }
/* 497 */     synchronized (this.map) {
/* 498 */       return this.map.headMap(key);
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
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 512 */     if (this.fast) {
/* 513 */       return this.map.subMap(fromKey, toKey);
/*     */     }
/* 515 */     synchronized (this.map) {
/* 516 */       return this.map.subMap(fromKey, toKey);
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
/*     */   public SortedMap tailMap(Object key) {
/* 529 */     if (this.fast) {
/* 530 */       return this.map.tailMap(key);
/*     */     }
/* 532 */     synchronized (this.map) {
/* 533 */       return this.map.tailMap(key);
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
/*     */   public Set entrySet() {
/* 547 */     return new EntrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 554 */     return new KeySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 561 */     return new Values();
/*     */   }
/*     */ 
/*     */   
/*     */   private abstract class CollectionView
/*     */     implements Collection
/*     */   {
/*     */     private final FastTreeMap this$0;
/*     */ 
/*     */     
/*     */     public CollectionView(FastTreeMap this$0) {
/* 572 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() {
/* 580 */       if (this.this$0.fast) {
/* 581 */         synchronized (this.this$0) {
/* 582 */           this.this$0.map = new TreeMap();
/*     */         } 
/*     */       } else {
/* 585 */         synchronized (this.this$0.map) {
/* 586 */           get(this.this$0.map).clear();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 592 */       if (this.this$0.fast) {
/* 593 */         synchronized (this.this$0) {
/* 594 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 595 */           boolean r = get(temp).remove(o);
/* 596 */           this.this$0.map = temp;
/* 597 */           return r;
/*     */         } 
/*     */       }
/* 600 */       synchronized (this.this$0.map) {
/* 601 */         return get(this.this$0.map).remove(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean removeAll(Collection o) {
/* 607 */       if (this.this$0.fast) {
/* 608 */         synchronized (this.this$0) {
/* 609 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 610 */           boolean r = get(temp).removeAll(o);
/* 611 */           this.this$0.map = temp;
/* 612 */           return r;
/*     */         } 
/*     */       }
/* 615 */       synchronized (this.this$0.map) {
/* 616 */         return get(this.this$0.map).removeAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean retainAll(Collection o) {
/* 622 */       if (this.this$0.fast) {
/* 623 */         synchronized (this.this$0) {
/* 624 */           TreeMap temp = (TreeMap)this.this$0.map.clone();
/* 625 */           boolean r = get(temp).retainAll(o);
/* 626 */           this.this$0.map = temp;
/* 627 */           return r;
/*     */         } 
/*     */       }
/* 630 */       synchronized (this.this$0.map) {
/* 631 */         return get(this.this$0.map).retainAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 637 */       if (this.this$0.fast) {
/* 638 */         return get(this.this$0.map).size();
/*     */       }
/* 640 */       synchronized (this.this$0.map) {
/* 641 */         return get(this.this$0.map).size();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 648 */       if (this.this$0.fast) {
/* 649 */         return get(this.this$0.map).isEmpty();
/*     */       }
/* 651 */       synchronized (this.this$0.map) {
/* 652 */         return get(this.this$0.map).isEmpty();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean contains(Object o) {
/* 658 */       if (this.this$0.fast) {
/* 659 */         return get(this.this$0.map).contains(o);
/*     */       }
/* 661 */       synchronized (this.this$0.map) {
/* 662 */         return get(this.this$0.map).contains(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsAll(Collection o) {
/* 668 */       if (this.this$0.fast) {
/* 669 */         return get(this.this$0.map).containsAll(o);
/*     */       }
/* 671 */       synchronized (this.this$0.map) {
/* 672 */         return get(this.this$0.map).containsAll(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray(Object[] o) {
/* 678 */       if (this.this$0.fast) {
/* 679 */         return get(this.this$0.map).toArray(o);
/*     */       }
/* 681 */       synchronized (this.this$0.map) {
/* 682 */         return get(this.this$0.map).toArray(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray() {
/* 688 */       if (this.this$0.fast) {
/* 689 */         return get(this.this$0.map).toArray();
/*     */       }
/* 691 */       synchronized (this.this$0.map) {
/* 692 */         return get(this.this$0.map).toArray();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 699 */       if (o == this) return true; 
/* 700 */       if (this.this$0.fast) {
/* 701 */         return get(this.this$0.map).equals(o);
/*     */       }
/* 703 */       synchronized (this.this$0.map) {
/* 704 */         return get(this.this$0.map).equals(o);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 710 */       if (this.this$0.fast) {
/* 711 */         return get(this.this$0.map).hashCode();
/*     */       }
/* 713 */       synchronized (this.this$0.map) {
/* 714 */         return get(this.this$0.map).hashCode();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean add(Object o) {
/* 720 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection c) {
/* 724 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 728 */       return new CollectionViewIterator(this);
/*     */     }
/*     */     protected abstract Collection get(Map param1Map);
/*     */     protected abstract Object iteratorNext(Map.Entry param1Entry);
/*     */     
/*     */     private class CollectionViewIterator implements Iterator { private Map expected;
/*     */       private Map.Entry lastReturned;
/*     */       
/*     */       public CollectionViewIterator(FastTreeMap.CollectionView this$1) {
/* 737 */         this.this$1 = this$1; this.lastReturned = null;
/* 738 */         this.expected = this$1.this$0.map;
/* 739 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       private Iterator iterator; private final FastTreeMap.CollectionView this$1;
/*     */       public boolean hasNext() {
/* 743 */         if (this.expected != this.this$1.this$0.map) {
/* 744 */           throw new ConcurrentModificationException();
/*     */         }
/* 746 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public Object next() {
/* 750 */         if (this.expected != this.this$1.this$0.map) {
/* 751 */           throw new ConcurrentModificationException();
/*     */         }
/* 753 */         this.lastReturned = this.iterator.next();
/* 754 */         return this.this$1.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 758 */         if (this.lastReturned == null) {
/* 759 */           throw new IllegalStateException();
/*     */         }
/* 761 */         if (this.this$1.this$0.fast) {
/* 762 */           synchronized (this.this$1.this$0) {
/* 763 */             if (this.expected != this.this$1.this$0.map) {
/* 764 */               throw new ConcurrentModificationException();
/*     */             }
/* 766 */             this.this$1.this$0.remove(this.lastReturned.getKey());
/* 767 */             this.lastReturned = null;
/* 768 */             this.expected = this.this$1.this$0.map;
/*     */           } 
/*     */         } else {
/* 771 */           this.iterator.remove();
/* 772 */           this.lastReturned = null;
/*     */         } 
/*     */       } }
/*     */   }
/*     */   
/*     */   private class KeySet extends CollectionView implements Set {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private KeySet(FastTreeMap this$0) {
/* 781 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 784 */       return map.keySet();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 788 */       return entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends CollectionView {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private Values(FastTreeMap this$0) {
/* 796 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 799 */       return map.values();
/*     */     }
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 803 */       return entry.getValue();
/*     */     } }
/*     */   
/*     */   private class EntrySet extends CollectionView implements Set {
/*     */     private final FastTreeMap this$0;
/*     */     
/*     */     private EntrySet(FastTreeMap this$0) {
/* 810 */       FastTreeMap.this = FastTreeMap.this;
/*     */     }
/*     */     protected Collection get(Map map) {
/* 813 */       return map.entrySet();
/*     */     }
/*     */ 
/*     */     
/*     */     protected Object iteratorNext(Map.Entry entry) {
/* 818 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/FastTreeMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */