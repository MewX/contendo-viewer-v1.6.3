/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDualBidiMap
/*     */   implements BidiMap
/*     */ {
/*  50 */   protected final transient Map[] maps = new Map[2];
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected transient BidiMap inverseBidiMap = null;
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected transient Set keySet = null;
/*     */ 
/*     */ 
/*     */   
/*  62 */   protected transient Collection values = null;
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected transient Set entrySet = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractDualBidiMap() {
/*  77 */     this.maps[0] = createMap();
/*  78 */     this.maps[1] = createMap();
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
/*     */   protected AbstractDualBidiMap(Map normalMap, Map reverseMap) {
/*  96 */     this.maps[0] = normalMap;
/*  97 */     this.maps[1] = reverseMap;
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
/*     */   protected AbstractDualBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/* 110 */     this.maps[0] = normalMap;
/* 111 */     this.maps[1] = reverseMap;
/* 112 */     this.inverseBidiMap = inverseBidiMap;
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
/*     */   protected Map createMap() {
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract BidiMap createBidiMap(Map paramMap1, Map paramMap2, BidiMap paramBidiMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 142 */     return this.maps[0].get(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 146 */     return this.maps[0].size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 150 */     return this.maps[0].isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 154 */     return this.maps[0].containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 158 */     return this.maps[0].equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 162 */     return this.maps[0].hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 166 */     return this.maps[0].toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 172 */     if (this.maps[0].containsKey(key)) {
/* 173 */       this.maps[1].remove(this.maps[0].get(key));
/*     */     }
/* 175 */     if (this.maps[1].containsKey(value)) {
/* 176 */       this.maps[0].remove(this.maps[1].get(value));
/*     */     }
/* 178 */     Object obj = this.maps[0].put(key, value);
/* 179 */     this.maps[1].put(value, key);
/* 180 */     return obj;
/*     */   }
/*     */   
/*     */   public void putAll(Map map) {
/* 184 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 185 */       Map.Entry entry = it.next();
/* 186 */       put(entry.getKey(), entry.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 191 */     Object value = null;
/* 192 */     if (this.maps[0].containsKey(key)) {
/* 193 */       value = this.maps[0].remove(key);
/* 194 */       this.maps[1].remove(value);
/*     */     } 
/* 196 */     return value;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 200 */     this.maps[0].clear();
/* 201 */     this.maps[1].clear();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 205 */     return this.maps[1].containsKey(value);
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
/*     */   public MapIterator mapIterator() {
/* 222 */     return new BidiMapIterator(this);
/*     */   }
/*     */   
/*     */   public Object getKey(Object value) {
/* 226 */     return this.maps[1].get(value);
/*     */   }
/*     */   
/*     */   public Object removeValue(Object value) {
/* 230 */     Object key = null;
/* 231 */     if (this.maps[1].containsKey(value)) {
/* 232 */       key = this.maps[1].remove(value);
/* 233 */       this.maps[0].remove(key);
/*     */     } 
/* 235 */     return key;
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 239 */     if (this.inverseBidiMap == null) {
/* 240 */       this.inverseBidiMap = createBidiMap(this.maps[1], this.maps[0], this);
/*     */     }
/* 242 */     return this.inverseBidiMap;
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
/*     */   public Set keySet() {
/* 255 */     if (this.keySet == null) {
/* 256 */       this.keySet = new KeySet(this);
/*     */     }
/* 258 */     return this.keySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createKeySetIterator(Iterator iterator) {
/* 269 */     return (Iterator)new KeySetIterator(iterator, this);
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
/* 280 */     if (this.values == null) {
/* 281 */       this.values = new Values(this);
/*     */     }
/* 283 */     return this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createValuesIterator(Iterator iterator) {
/* 294 */     return (Iterator)new ValuesIterator(iterator, this);
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
/*     */   public Set entrySet() {
/* 309 */     if (this.entrySet == null) {
/* 310 */       this.entrySet = new EntrySet(this);
/*     */     }
/* 312 */     return this.entrySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createEntrySetIterator(Iterator iterator) {
/* 323 */     return (Iterator)new EntrySetIterator(iterator, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static abstract class View
/*     */     extends AbstractCollectionDecorator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected View(Collection coll, AbstractDualBidiMap parent) {
/* 342 */       super(coll);
/* 343 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection coll) {
/* 347 */       if (this.parent.isEmpty() || coll.isEmpty()) {
/* 348 */         return false;
/*     */       }
/* 350 */       boolean modified = false;
/* 351 */       Iterator it = iterator();
/* 352 */       while (it.hasNext()) {
/* 353 */         if (coll.contains(it.next())) {
/* 354 */           it.remove();
/* 355 */           modified = true;
/*     */         } 
/*     */       } 
/* 358 */       return modified;
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection coll) {
/* 362 */       if (this.parent.isEmpty()) {
/* 363 */         return false;
/*     */       }
/* 365 */       if (coll.isEmpty()) {
/* 366 */         this.parent.clear();
/* 367 */         return true;
/*     */       } 
/* 369 */       boolean modified = false;
/* 370 */       Iterator it = iterator();
/* 371 */       while (it.hasNext()) {
/* 372 */         if (!coll.contains(it.next())) {
/* 373 */           it.remove();
/* 374 */           modified = true;
/*     */         } 
/*     */       } 
/* 377 */       return modified;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 381 */       this.parent.clear();
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
/*     */   protected static class KeySet
/*     */     extends View
/*     */     implements Set
/*     */   {
/*     */     protected KeySet(AbstractDualBidiMap parent) {
/* 397 */       super(parent.maps[0].keySet(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 401 */       return this.parent.createKeySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 405 */       return this.parent.maps[0].containsKey(key);
/*     */     }
/*     */     
/*     */     public boolean remove(Object key) {
/* 409 */       if (this.parent.maps[0].containsKey(key)) {
/* 410 */         Object value = this.parent.maps[0].remove(key);
/* 411 */         this.parent.maps[1].remove(value);
/* 412 */         return true;
/*     */       } 
/* 414 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class KeySetIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */     
/* 426 */     protected Object lastKey = null;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canRemove = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected KeySetIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 436 */       super(iterator);
/* 437 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 441 */       this.lastKey = super.next();
/* 442 */       this.canRemove = true;
/* 443 */       return this.lastKey;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 447 */       if (!this.canRemove) {
/* 448 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/* 450 */       Object value = this.parent.maps[0].get(this.lastKey);
/* 451 */       super.remove();
/* 452 */       this.parent.maps[1].remove(value);
/* 453 */       this.lastKey = null;
/* 454 */       this.canRemove = false;
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
/*     */   protected static class Values
/*     */     extends View
/*     */     implements Set
/*     */   {
/*     */     protected Values(AbstractDualBidiMap parent) {
/* 470 */       super(parent.maps[0].values(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 474 */       return this.parent.createValuesIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 478 */       return this.parent.maps[1].containsKey(value);
/*     */     }
/*     */     
/*     */     public boolean remove(Object value) {
/* 482 */       if (this.parent.maps[1].containsKey(value)) {
/* 483 */         Object key = this.parent.maps[1].remove(value);
/* 484 */         this.parent.maps[0].remove(key);
/* 485 */         return true;
/*     */       } 
/* 487 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ValuesIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */     
/* 499 */     protected Object lastValue = null;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canRemove = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ValuesIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 509 */       super(iterator);
/* 510 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 514 */       this.lastValue = super.next();
/* 515 */       this.canRemove = true;
/* 516 */       return this.lastValue;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 520 */       if (!this.canRemove) {
/* 521 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/* 523 */       super.remove();
/* 524 */       this.parent.maps[1].remove(this.lastValue);
/* 525 */       this.lastValue = null;
/* 526 */       this.canRemove = false;
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
/*     */   protected static class EntrySet
/*     */     extends View
/*     */     implements Set
/*     */   {
/*     */     protected EntrySet(AbstractDualBidiMap parent) {
/* 542 */       super(parent.maps[0].entrySet(), parent);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 546 */       return this.parent.createEntrySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 550 */       if (!(obj instanceof Map.Entry)) {
/* 551 */         return false;
/*     */       }
/* 553 */       Map.Entry entry = (Map.Entry)obj;
/* 554 */       Object key = entry.getKey();
/* 555 */       if (this.parent.containsKey(key)) {
/* 556 */         Object value = this.parent.maps[0].get(key);
/* 557 */         if ((value == null) ? (entry.getValue() == null) : value.equals(entry.getValue())) {
/* 558 */           this.parent.maps[0].remove(key);
/* 559 */           this.parent.maps[1].remove(value);
/* 560 */           return true;
/*     */         } 
/*     */       } 
/* 563 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class EntrySetIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */     
/* 575 */     protected Map.Entry last = null;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canRemove = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected EntrySetIterator(Iterator iterator, AbstractDualBidiMap parent) {
/* 585 */       super(iterator);
/* 586 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 590 */       this.last = (Map.Entry)new AbstractDualBidiMap.MapEntry((Map.Entry)super.next(), this.parent);
/* 591 */       this.canRemove = true;
/* 592 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 596 */       if (!this.canRemove) {
/* 597 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/*     */       
/* 600 */       Object value = this.last.getValue();
/* 601 */       super.remove();
/* 602 */       this.parent.maps[1].remove(value);
/* 603 */       this.last = null;
/* 604 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MapEntry
/*     */     extends AbstractMapEntryDecorator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected MapEntry(Map.Entry entry, AbstractDualBidiMap parent) {
/* 622 */       super(entry);
/* 623 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 627 */       Object key = getKey();
/* 628 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != key)
/*     */       {
/* 630 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 632 */       this.parent.put(key, value);
/* 633 */       Object oldValue = super.setValue(value);
/* 634 */       return oldValue;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class BidiMapIterator
/*     */     implements MapIterator, ResettableIterator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */     
/*     */     protected Iterator iterator;
/*     */     
/* 648 */     protected Map.Entry last = null;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canRemove = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected BidiMapIterator(AbstractDualBidiMap parent) {
/* 658 */       this.parent = parent;
/* 659 */       this.iterator = parent.maps[0].entrySet().iterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 663 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 667 */       this.last = this.iterator.next();
/* 668 */       this.canRemove = true;
/* 669 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 673 */       if (!this.canRemove) {
/* 674 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/*     */       
/* 677 */       Object value = this.last.getValue();
/* 678 */       this.iterator.remove();
/* 679 */       this.parent.maps[1].remove(value);
/* 680 */       this.last = null;
/* 681 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 685 */       if (this.last == null) {
/* 686 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */       }
/* 688 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 692 */       if (this.last == null) {
/* 693 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */       }
/* 695 */       return this.last.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 699 */       if (this.last == null) {
/* 700 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */       }
/* 702 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != this.last.getKey())
/*     */       {
/* 704 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 706 */       return this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 710 */       this.iterator = this.parent.maps[0].entrySet().iterator();
/* 711 */       this.last = null;
/* 712 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 716 */       if (this.last != null) {
/* 717 */         return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 719 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/AbstractDualBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */