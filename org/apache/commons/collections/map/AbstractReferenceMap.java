/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.keyvalue.DefaultMapEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractReferenceMap
/*     */   extends AbstractHashedMap
/*     */ {
/*     */   public static final int HARD = 0;
/*     */   public static final int SOFT = 1;
/*     */   public static final int WEAK = 2;
/*     */   protected int keyType;
/*     */   protected int valueType;
/*     */   protected boolean purgeValues;
/*     */   private transient ReferenceQueue queue;
/*     */   
/*     */   protected AbstractReferenceMap() {}
/*     */   
/*     */   protected AbstractReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 142 */     super(capacity, loadFactor);
/* 143 */     verify("keyType", keyType);
/* 144 */     verify("valueType", valueType);
/* 145 */     this.keyType = keyType;
/* 146 */     this.valueType = valueType;
/* 147 */     this.purgeValues = purgeValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/* 154 */     this.queue = new ReferenceQueue();
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
/*     */   private static void verify(String name, int type) {
/* 166 */     if (type < 0 || type > 2) {
/* 167 */       throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK.");
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
/* 178 */     purgeBeforeRead();
/* 179 */     return super.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 188 */     purgeBeforeRead();
/* 189 */     return super.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 199 */     purgeBeforeRead();
/* 200 */     Map.Entry entry = getEntry(key);
/* 201 */     if (entry == null) {
/* 202 */       return false;
/*     */     }
/* 204 */     return (entry.getValue() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 214 */     purgeBeforeRead();
/* 215 */     if (value == null) {
/* 216 */       return false;
/*     */     }
/* 218 */     return super.containsValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 228 */     purgeBeforeRead();
/* 229 */     Map.Entry entry = getEntry(key);
/* 230 */     if (entry == null) {
/* 231 */       return null;
/*     */     }
/* 233 */     return entry.getValue();
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
/*     */   public Object put(Object key, Object value) {
/* 247 */     if (key == null) {
/* 248 */       throw new NullPointerException("null keys not allowed");
/*     */     }
/* 250 */     if (value == null) {
/* 251 */       throw new NullPointerException("null values not allowed");
/*     */     }
/*     */     
/* 254 */     purgeBeforeWrite();
/* 255 */     return super.put(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 265 */     if (key == null) {
/* 266 */       return null;
/*     */     }
/* 268 */     purgeBeforeWrite();
/* 269 */     return super.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 276 */     super.clear();
/* 277 */     while (this.queue.poll() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapIterator mapIterator() {
/* 288 */     return new ReferenceMapIterator(this);
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
/* 299 */     if (this.entrySet == null) {
/* 300 */       this.entrySet = new ReferenceEntrySet(this);
/*     */     }
/* 302 */     return this.entrySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 311 */     if (this.keySet == null) {
/* 312 */       this.keySet = new ReferenceKeySet(this);
/*     */     }
/* 314 */     return this.keySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 323 */     if (this.values == null) {
/* 324 */       this.values = new ReferenceValues(this);
/*     */     }
/* 326 */     return this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void purgeBeforeRead() {
/* 336 */     purge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void purgeBeforeWrite() {
/* 345 */     purge();
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
/*     */   protected void purge() {
/* 357 */     Reference ref = this.queue.poll();
/* 358 */     while (ref != null) {
/* 359 */       purge(ref);
/* 360 */       ref = this.queue.poll();
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
/*     */   protected void purge(Reference ref) {
/* 373 */     int hash = ref.hashCode();
/* 374 */     int index = hashIndex(hash, this.data.length);
/* 375 */     AbstractHashedMap.HashEntry previous = null;
/* 376 */     AbstractHashedMap.HashEntry entry = this.data[index];
/* 377 */     while (entry != null) {
/* 378 */       if (((ReferenceEntry)entry).purge(ref)) {
/* 379 */         if (previous == null) {
/* 380 */           this.data[index] = entry.next;
/*     */         } else {
/* 382 */           previous.next = entry.next;
/*     */         } 
/* 384 */         this.size--;
/*     */         return;
/*     */       } 
/* 387 */       previous = entry;
/* 388 */       entry = entry.next;
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
/*     */   protected AbstractHashedMap.HashEntry getEntry(Object key) {
/* 401 */     if (key == null) {
/* 402 */       return null;
/*     */     }
/* 404 */     return super.getEntry(key);
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
/*     */   protected int hashEntry(Object key, Object value) {
/* 417 */     return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
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
/*     */   protected boolean isEqualKey(Object key1, Object key2) {
/* 432 */     key2 = (this.keyType > 0) ? ((Reference)key2).get() : key2;
/* 433 */     return (key1 == key2 || key1.equals(key2));
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
/*     */   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 446 */     return new ReferenceEntry(this, next, hashCode, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createEntrySetIterator() {
/* 455 */     return new ReferenceEntrySetIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createKeySetIterator() {
/* 464 */     return new ReferenceKeySetIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createValuesIterator() {
/* 473 */     return new ReferenceValuesIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ReferenceEntrySet
/*     */     extends AbstractHashedMap.EntrySet
/*     */   {
/*     */     protected ReferenceEntrySet(AbstractHashedMap parent) {
/* 483 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 487 */       return toArray(new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 492 */       ArrayList list = new ArrayList();
/* 493 */       Iterator iterator = iterator();
/* 494 */       while (iterator.hasNext()) {
/* 495 */         Map.Entry e = iterator.next();
/* 496 */         list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
/*     */       } 
/* 498 */       return list.toArray(arr);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ReferenceKeySet
/*     */     extends AbstractHashedMap.KeySet
/*     */   {
/*     */     protected ReferenceKeySet(AbstractHashedMap parent) {
/* 509 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 513 */       return toArray(new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 518 */       List list = new ArrayList(this.parent.size());
/* 519 */       for (Iterator it = iterator(); it.hasNext();) {
/* 520 */         list.add(it.next());
/*     */       }
/* 522 */       return list.toArray(arr);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ReferenceValues
/*     */     extends AbstractHashedMap.Values
/*     */   {
/*     */     protected ReferenceValues(AbstractHashedMap parent) {
/* 533 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 537 */       return toArray(new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object[] toArray(Object[] arr) {
/* 542 */       List list = new ArrayList(this.parent.size());
/* 543 */       for (Iterator it = iterator(); it.hasNext();) {
/* 544 */         list.add(it.next());
/*     */       }
/* 546 */       return list.toArray(arr);
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
/*     */   protected static class ReferenceEntry
/*     */     extends AbstractHashedMap.HashEntry
/*     */   {
/*     */     protected final AbstractReferenceMap parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ReferenceEntry(AbstractReferenceMap parent, AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 573 */       super(next, hashCode, null, null);
/* 574 */       this.parent = parent;
/* 575 */       this.key = toReference(parent.keyType, key, hashCode);
/* 576 */       this.value = toReference(parent.valueType, value, hashCode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getKey() {
/* 586 */       return (this.parent.keyType > 0) ? ((Reference)this.key).get() : this.key;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getValue() {
/* 596 */       return (this.parent.valueType > 0) ? ((Reference)this.value).get() : this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object setValue(Object obj) {
/* 606 */       Object old = getValue();
/* 607 */       if (this.parent.valueType > 0) {
/* 608 */         ((Reference)this.value).clear();
/*     */       }
/* 610 */       this.value = toReference(this.parent.valueType, obj, this.hashCode);
/* 611 */       return old;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 624 */       if (obj == this) {
/* 625 */         return true;
/*     */       }
/* 627 */       if (!(obj instanceof Map.Entry)) {
/* 628 */         return false;
/*     */       }
/*     */       
/* 631 */       Map.Entry entry = (Map.Entry)obj;
/* 632 */       Object entryKey = entry.getKey();
/* 633 */       Object entryValue = entry.getValue();
/* 634 */       if (entryKey == null || entryValue == null) {
/* 635 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 639 */       return (this.parent.isEqualKey(entryKey, this.key) && this.parent.isEqualValue(entryValue, getValue()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 651 */       return this.parent.hashEntry(getKey(), getValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object toReference(int type, Object referent, int hash) {
/* 665 */       switch (type) { case 0:
/* 666 */           return referent;
/* 667 */         case 1: return new AbstractReferenceMap.SoftRef(hash, referent, this.parent.queue);
/* 668 */         case 2: return new AbstractReferenceMap.WeakRef(hash, referent, this.parent.queue); }
/* 669 */        throw new Error();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean purge(Reference ref) {
/* 679 */       boolean r = (this.parent.keyType > 0 && this.key == ref);
/* 680 */       r = (r || (this.parent.valueType > 0 && this.value == ref));
/* 681 */       if (r) {
/* 682 */         if (this.parent.keyType > 0) {
/* 683 */           ((Reference)this.key).clear();
/*     */         }
/* 685 */         if (this.parent.valueType > 0) {
/* 686 */           ((Reference)this.value).clear();
/* 687 */         } else if (this.parent.purgeValues) {
/* 688 */           this.value = null;
/*     */         } 
/*     */       } 
/* 691 */       return r;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ReferenceEntry next() {
/* 700 */       return (ReferenceEntry)this.next;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class ReferenceEntrySetIterator
/*     */     implements Iterator
/*     */   {
/*     */     final AbstractReferenceMap parent;
/*     */     
/*     */     int index;
/*     */     
/*     */     AbstractReferenceMap.ReferenceEntry entry;
/*     */     
/*     */     AbstractReferenceMap.ReferenceEntry previous;
/*     */     
/*     */     Object nextKey;
/*     */     
/*     */     Object nextValue;
/*     */     
/*     */     Object currentKey;
/*     */     
/*     */     Object currentValue;
/*     */     
/*     */     int expectedModCount;
/*     */     
/*     */     public ReferenceEntrySetIterator(AbstractReferenceMap parent) {
/* 727 */       this.parent = parent;
/* 728 */       this.index = (parent.size() != 0) ? parent.data.length : 0;
/*     */ 
/*     */       
/* 731 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 735 */       checkMod();
/* 736 */       while (nextNull()) {
/* 737 */         AbstractReferenceMap.ReferenceEntry e = this.entry;
/* 738 */         int i = this.index;
/* 739 */         while (e == null && i > 0) {
/* 740 */           i--;
/* 741 */           e = (AbstractReferenceMap.ReferenceEntry)this.parent.data[i];
/*     */         } 
/* 743 */         this.entry = e;
/* 744 */         this.index = i;
/* 745 */         if (e == null) {
/* 746 */           this.currentKey = null;
/* 747 */           this.currentValue = null;
/* 748 */           return false;
/*     */         } 
/* 750 */         this.nextKey = e.getKey();
/* 751 */         this.nextValue = e.getValue();
/* 752 */         if (nextNull()) {
/* 753 */           this.entry = this.entry.next();
/*     */         }
/*     */       } 
/* 756 */       return true;
/*     */     }
/*     */     
/*     */     private void checkMod() {
/* 760 */       if (this.parent.modCount != this.expectedModCount) {
/* 761 */         throw new ConcurrentModificationException();
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean nextNull() {
/* 766 */       return (this.nextKey == null || this.nextValue == null);
/*     */     }
/*     */     
/*     */     protected AbstractReferenceMap.ReferenceEntry nextEntry() {
/* 770 */       checkMod();
/* 771 */       if (nextNull() && !hasNext()) {
/* 772 */         throw new NoSuchElementException();
/*     */       }
/* 774 */       this.previous = this.entry;
/* 775 */       this.entry = this.entry.next();
/* 776 */       this.currentKey = this.nextKey;
/* 777 */       this.currentValue = this.nextValue;
/* 778 */       this.nextKey = null;
/* 779 */       this.nextValue = null;
/* 780 */       return this.previous;
/*     */     }
/*     */     
/*     */     protected AbstractReferenceMap.ReferenceEntry currentEntry() {
/* 784 */       checkMod();
/* 785 */       return this.previous;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 789 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 793 */       checkMod();
/* 794 */       if (this.previous == null) {
/* 795 */         throw new IllegalStateException();
/*     */       }
/* 797 */       this.parent.remove(this.currentKey);
/* 798 */       this.previous = null;
/* 799 */       this.currentKey = null;
/* 800 */       this.currentValue = null;
/* 801 */       this.expectedModCount = this.parent.modCount;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class ReferenceKeySetIterator
/*     */     extends ReferenceEntrySetIterator
/*     */   {
/*     */     ReferenceKeySetIterator(AbstractReferenceMap parent) {
/* 811 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 815 */       return nextEntry().getKey();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class ReferenceValuesIterator
/*     */     extends ReferenceEntrySetIterator
/*     */   {
/*     */     ReferenceValuesIterator(AbstractReferenceMap parent) {
/* 825 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 829 */       return nextEntry().getValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class ReferenceMapIterator
/*     */     extends ReferenceEntrySetIterator
/*     */     implements MapIterator
/*     */   {
/*     */     protected ReferenceMapIterator(AbstractReferenceMap parent) {
/* 839 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 843 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 847 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 848 */       if (current == null) {
/* 849 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 851 */       return current.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 855 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 856 */       if (current == null) {
/* 857 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 859 */       return current.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 863 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 864 */       if (current == null) {
/* 865 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 867 */       return current.setValue(value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class SoftRef
/*     */     extends SoftReference
/*     */   {
/*     */     private int hash;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SoftRef(int hash, Object r, ReferenceQueue q) {
/* 884 */       super((T)r, q);
/* 885 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 889 */       return this.hash;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class WeakRef
/*     */     extends WeakReference
/*     */   {
/*     */     private int hash;
/*     */ 
/*     */     
/*     */     public WeakRef(int hash, Object r, ReferenceQueue q) {
/* 901 */       super((T)r, q);
/* 902 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 906 */       return this.hash;
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
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 930 */     out.writeInt(this.keyType);
/* 931 */     out.writeInt(this.valueType);
/* 932 */     out.writeBoolean(this.purgeValues);
/* 933 */     out.writeFloat(this.loadFactor);
/* 934 */     out.writeInt(this.data.length);
/* 935 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/* 936 */       out.writeObject(it.next());
/* 937 */       out.writeObject(it.getValue());
/*     */     } 
/* 939 */     out.writeObject(null);
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
/*     */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 961 */     this.keyType = in.readInt();
/* 962 */     this.valueType = in.readInt();
/* 963 */     this.purgeValues = in.readBoolean();
/* 964 */     this.loadFactor = in.readFloat();
/* 965 */     int capacity = in.readInt();
/* 966 */     init();
/* 967 */     this.data = new AbstractHashedMap.HashEntry[capacity];
/*     */     while (true) {
/* 969 */       Object key = in.readObject();
/* 970 */       if (key == null) {
/*     */         break;
/*     */       }
/* 973 */       Object value = in.readObject();
/* 974 */       put(key, value);
/*     */     } 
/* 976 */     this.threshold = calculateThreshold(this.data.length, this.loadFactor);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractReferenceMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */