/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractLinkedMap
/*     */   extends AbstractHashedMap
/*     */   implements OrderedMap
/*     */ {
/*     */   protected transient LinkEntry header;
/*     */   
/*     */   protected AbstractLinkedMap() {}
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor, int threshold) {
/*  85 */     super(initialCapacity, loadFactor, threshold);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity) {
/*  95 */     super(initialCapacity);
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
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor) {
/* 108 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractLinkedMap(Map map) {
/* 118 */     super(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/* 125 */     this.header = new LinkEntry(null, -1, null, null);
/* 126 */     this.header.before = this.header.after = this.header;
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
/* 138 */     if (value == null) {
/* 139 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 140 */         if (entry.getValue() == null) {
/* 141 */           return true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 145 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 146 */         if (isEqualValue(value, entry.getValue())) {
/* 147 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 160 */     super.clear();
/* 161 */     this.header.before = this.header.after = this.header;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 171 */     if (this.size == 0) {
/* 172 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 174 */     return this.header.after.getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object lastKey() {
/* 183 */     if (this.size == 0) {
/* 184 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 186 */     return this.header.before.getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object nextKey(Object key) {
/* 196 */     LinkEntry entry = (LinkEntry)getEntry(key);
/* 197 */     return (entry == null || entry.after == this.header) ? null : entry.after.getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object previousKey(Object key) {
/* 207 */     LinkEntry entry = (LinkEntry)getEntry(key);
/* 208 */     return (entry == null || entry.before == this.header) ? null : entry.before.getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LinkEntry getEntry(int index) {
/*     */     LinkEntry entry;
/* 220 */     if (index < 0) {
/* 221 */       throw new IndexOutOfBoundsException("Index " + index + " is less than zero");
/*     */     }
/* 223 */     if (index >= this.size) {
/* 224 */       throw new IndexOutOfBoundsException("Index " + index + " is invalid for size " + this.size);
/*     */     }
/*     */     
/* 227 */     if (index < this.size / 2) {
/*     */       
/* 229 */       entry = this.header.after;
/* 230 */       for (int currentIndex = 0; currentIndex < index; currentIndex++) {
/* 231 */         entry = entry.after;
/*     */       }
/*     */     } else {
/*     */       
/* 235 */       entry = this.header;
/* 236 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--) {
/* 237 */         entry = entry.before;
/*     */       }
/*     */     } 
/* 240 */     return entry;
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
/*     */   protected void addEntry(AbstractHashedMap.HashEntry entry, int hashIndex) {
/* 253 */     LinkEntry link = (LinkEntry)entry;
/* 254 */     link.after = this.header;
/* 255 */     link.before = this.header.before;
/* 256 */     this.header.before.after = link;
/* 257 */     this.header.before = link;
/* 258 */     this.data[hashIndex] = entry;
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
/*     */   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 273 */     return new LinkEntry(next, hashCode, key, value);
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
/*     */   protected void removeEntry(AbstractHashedMap.HashEntry entry, int hashIndex, AbstractHashedMap.HashEntry previous) {
/* 287 */     LinkEntry link = (LinkEntry)entry;
/* 288 */     link.before.after = link.after;
/* 289 */     link.after.before = link.before;
/* 290 */     link.after = null;
/* 291 */     link.before = null;
/* 292 */     super.removeEntry(entry, hashIndex, previous);
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
/*     */   protected LinkEntry entryBefore(LinkEntry entry) {
/* 306 */     return entry.before;
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
/*     */   protected LinkEntry entryAfter(LinkEntry entry) {
/* 319 */     return entry.after;
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
/*     */   public MapIterator mapIterator() {
/* 334 */     if (this.size == 0) {
/* 335 */       return (MapIterator)EmptyOrderedMapIterator.INSTANCE;
/*     */     }
/* 337 */     return (MapIterator)new LinkMapIterator(this);
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
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 351 */     if (this.size == 0) {
/* 352 */       return EmptyOrderedMapIterator.INSTANCE;
/*     */     }
/* 354 */     return new LinkMapIterator(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class LinkMapIterator
/*     */     extends LinkIterator
/*     */     implements OrderedMapIterator
/*     */   {
/*     */     protected LinkMapIterator(AbstractLinkedMap parent) {
/* 363 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 367 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 371 */       return previousEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 375 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 376 */       if (current == null) {
/* 377 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 379 */       return current.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 383 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 384 */       if (current == null) {
/* 385 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 387 */       return current.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 391 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 392 */       if (current == null) {
/* 393 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 395 */       return current.setValue(value);
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
/*     */   protected Iterator createEntrySetIterator() {
/* 407 */     if (size() == 0) {
/* 408 */       return (Iterator)EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 410 */     return (Iterator)new EntrySetIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class EntrySetIterator
/*     */     extends LinkIterator
/*     */   {
/*     */     protected EntrySetIterator(AbstractLinkedMap parent) {
/* 419 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 423 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 427 */       return previousEntry();
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
/*     */   protected Iterator createKeySetIterator() {
/* 439 */     if (size() == 0) {
/* 440 */       return (Iterator)EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 442 */     return (Iterator)new KeySetIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class KeySetIterator
/*     */     extends EntrySetIterator
/*     */   {
/*     */     protected KeySetIterator(AbstractLinkedMap parent) {
/* 451 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 455 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 459 */       return previousEntry().getKey();
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
/*     */   protected Iterator createValuesIterator() {
/* 471 */     if (size() == 0) {
/* 472 */       return (Iterator)EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 474 */     return (Iterator)new ValuesIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ValuesIterator
/*     */     extends LinkIterator
/*     */   {
/*     */     protected ValuesIterator(AbstractLinkedMap parent) {
/* 483 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 487 */       return nextEntry().getValue();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 491 */       return previousEntry().getValue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class LinkEntry
/*     */     extends AbstractHashedMap.HashEntry
/*     */   {
/*     */     protected LinkEntry before;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected LinkEntry after;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected LinkEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 519 */       super(next, hashCode, key, value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static abstract class LinkIterator
/*     */     implements OrderedIterator, ResettableIterator
/*     */   {
/*     */     protected final AbstractLinkedMap parent;
/*     */ 
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry last;
/*     */ 
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry next;
/*     */     
/*     */     protected int expectedModCount;
/*     */ 
/*     */     
/*     */     protected LinkIterator(AbstractLinkedMap parent) {
/* 540 */       this.parent = parent;
/* 541 */       this.next = parent.header.after;
/* 542 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 546 */       return (this.next != this.parent.header);
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 550 */       return (this.next.before != this.parent.header);
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry nextEntry() {
/* 554 */       if (this.parent.modCount != this.expectedModCount) {
/* 555 */         throw new ConcurrentModificationException();
/*     */       }
/* 557 */       if (this.next == this.parent.header) {
/* 558 */         throw new NoSuchElementException("No next() entry in the iteration");
/*     */       }
/* 560 */       this.last = this.next;
/* 561 */       this.next = this.next.after;
/* 562 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry previousEntry() {
/* 566 */       if (this.parent.modCount != this.expectedModCount) {
/* 567 */         throw new ConcurrentModificationException();
/*     */       }
/* 569 */       AbstractLinkedMap.LinkEntry previous = this.next.before;
/* 570 */       if (previous == this.parent.header) {
/* 571 */         throw new NoSuchElementException("No previous() entry in the iteration");
/*     */       }
/* 573 */       this.next = previous;
/* 574 */       this.last = previous;
/* 575 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry currentEntry() {
/* 579 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 583 */       if (this.last == null) {
/* 584 */         throw new IllegalStateException("remove() can only be called once after next()");
/*     */       }
/* 586 */       if (this.parent.modCount != this.expectedModCount) {
/* 587 */         throw new ConcurrentModificationException();
/*     */       }
/* 589 */       this.parent.remove(this.last.getKey());
/* 590 */       this.last = null;
/* 591 */       this.expectedModCount = this.parent.modCount;
/*     */     }
/*     */     
/*     */     public void reset() {
/* 595 */       this.last = null;
/* 596 */       this.next = this.parent.header.after;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 600 */       if (this.last != null) {
/* 601 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
/*     */       }
/* 603 */       return "Iterator[]";
/*     */     }
/*     */     
/*     */     public abstract Object previous();
/*     */     
/*     */     public abstract Object next();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractLinkedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */