/*      */ package org.apache.commons.collections.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.IterableMap;
/*      */ import org.apache.commons.collections.KeyValue;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyMapIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AbstractHashedMap
/*      */   implements IterableMap
/*      */ {
/*      */   protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
/*      */   protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
/*      */   protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
/*      */   protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
/*      */   protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
/*      */   protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
/*      */   protected static final int DEFAULT_CAPACITY = 16;
/*      */   protected static final int DEFAULT_THRESHOLD = 12;
/*      */   protected static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   protected static final int MAXIMUM_CAPACITY = 1073741824;
/*   73 */   protected static final Object NULL = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient float loadFactor;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient int size;
/*      */ 
/*      */   
/*      */   protected transient HashEntry[] data;
/*      */ 
/*      */   
/*      */   protected transient int threshold;
/*      */ 
/*      */   
/*      */   protected transient int modCount;
/*      */ 
/*      */   
/*      */   protected transient EntrySet entrySet;
/*      */ 
/*      */   
/*      */   protected transient KeySet keySet;
/*      */ 
/*      */   
/*      */   protected transient Values values;
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractHashedMap() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor, int threshold) {
/*  108 */     this.loadFactor = loadFactor;
/*  109 */     this.data = new HashEntry[initialCapacity];
/*  110 */     this.threshold = threshold;
/*  111 */     init();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity) {
/*  122 */     this(initialCapacity, 0.75F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor) {
/*  136 */     if (initialCapacity < 1) {
/*  137 */       throw new IllegalArgumentException("Initial capacity must be greater than 0");
/*      */     }
/*  139 */     if (loadFactor <= 0.0F || Float.isNaN(loadFactor)) {
/*  140 */       throw new IllegalArgumentException("Load factor must be greater than 0");
/*      */     }
/*  142 */     this.loadFactor = loadFactor;
/*  143 */     this.threshold = calculateThreshold(initialCapacity, loadFactor);
/*  144 */     initialCapacity = calculateNewCapacity(initialCapacity);
/*  145 */     this.data = new HashEntry[initialCapacity];
/*  146 */     init();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected AbstractHashedMap(Map map) {
/*  156 */     this(Math.max(2 * map.size(), 16), 0.75F);
/*  157 */     putAll(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void init() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  174 */     key = convertKey(key);
/*  175 */     int hashCode = hash(key);
/*  176 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  177 */     while (entry != null) {
/*  178 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  179 */         return entry.getValue();
/*      */       }
/*  181 */       entry = entry.next;
/*      */     } 
/*  183 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  192 */     return this.size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  201 */     return (this.size == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  212 */     key = convertKey(key);
/*  213 */     int hashCode = hash(key);
/*  214 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  215 */     while (entry != null) {
/*  216 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  217 */         return true;
/*      */       }
/*  219 */       entry = entry.next;
/*      */     } 
/*  221 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  231 */     if (value == null) {
/*  232 */       for (int i = 0, isize = this.data.length; i < isize; i++) {
/*  233 */         HashEntry entry = this.data[i];
/*  234 */         while (entry != null) {
/*  235 */           if (entry.getValue() == null) {
/*  236 */             return true;
/*      */           }
/*  238 */           entry = entry.next;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  242 */       for (int i = 0, isize = this.data.length; i < isize; i++) {
/*  243 */         HashEntry entry = this.data[i];
/*  244 */         while (entry != null) {
/*  245 */           if (isEqualValue(value, entry.getValue())) {
/*  246 */             return true;
/*      */           }
/*  248 */           entry = entry.next;
/*      */         } 
/*      */       } 
/*      */     } 
/*  252 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  264 */     key = convertKey(key);
/*  265 */     int hashCode = hash(key);
/*  266 */     int index = hashIndex(hashCode, this.data.length);
/*  267 */     HashEntry entry = this.data[index];
/*  268 */     while (entry != null) {
/*  269 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  270 */         Object oldValue = entry.getValue();
/*  271 */         updateEntry(entry, value);
/*  272 */         return oldValue;
/*      */       } 
/*  274 */       entry = entry.next;
/*      */     } 
/*      */     
/*  277 */     addMapping(index, hashCode, key, value);
/*  278 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map map) {
/*  291 */     int mapSize = map.size();
/*  292 */     if (mapSize == 0) {
/*      */       return;
/*      */     }
/*  295 */     int newSize = (int)((this.size + mapSize) / this.loadFactor + 1.0F);
/*  296 */     ensureCapacity(calculateNewCapacity(newSize));
/*  297 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/*  298 */       Map.Entry entry = it.next();
/*  299 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/*  310 */     key = convertKey(key);
/*  311 */     int hashCode = hash(key);
/*  312 */     int index = hashIndex(hashCode, this.data.length);
/*  313 */     HashEntry entry = this.data[index];
/*  314 */     HashEntry previous = null;
/*  315 */     while (entry != null) {
/*  316 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  317 */         Object oldValue = entry.getValue();
/*  318 */         removeMapping(entry, index, previous);
/*  319 */         return oldValue;
/*      */       } 
/*  321 */       previous = entry;
/*  322 */       entry = entry.next;
/*      */     } 
/*  324 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  332 */     this.modCount++;
/*  333 */     HashEntry[] data = this.data;
/*  334 */     for (int i = data.length - 1; i >= 0; i--) {
/*  335 */       data[i] = null;
/*      */     }
/*  337 */     this.size = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object convertKey(Object key) {
/*  353 */     return (key == null) ? NULL : key;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int hash(Object key) {
/*  366 */     int h = key.hashCode();
/*  367 */     h += h << 9 ^ 0xFFFFFFFF;
/*  368 */     h ^= h >>> 14;
/*  369 */     h += h << 4;
/*  370 */     h ^= h >>> 10;
/*  371 */     return h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEqualKey(Object key1, Object key2) {
/*  384 */     return (key1 == key2 || key1.equals(key2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEqualValue(Object value1, Object value2) {
/*  397 */     return (value1 == value2 || value1.equals(value2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int hashIndex(int hashCode, int dataSize) {
/*  410 */     return hashCode & dataSize - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected HashEntry getEntry(Object key) {
/*  425 */     key = convertKey(key);
/*  426 */     int hashCode = hash(key);
/*  427 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  428 */     while (entry != null) {
/*  429 */       if (entry.hashCode == hashCode && isEqualKey(key, entry.key)) {
/*  430 */         return entry;
/*      */       }
/*  432 */       entry = entry.next;
/*      */     } 
/*  434 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateEntry(HashEntry entry, Object newValue) {
/*  448 */     entry.setValue(newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reuseEntry(HashEntry entry, int hashIndex, int hashCode, Object key, Object value) {
/*  464 */     entry.next = this.data[hashIndex];
/*  465 */     entry.hashCode = hashCode;
/*  466 */     entry.key = key;
/*  467 */     entry.value = value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
/*  485 */     this.modCount++;
/*  486 */     HashEntry entry = createEntry(this.data[hashIndex], hashCode, key, value);
/*  487 */     addEntry(entry, hashIndex);
/*  488 */     this.size++;
/*  489 */     checkCapacity();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected HashEntry createEntry(HashEntry next, int hashCode, Object key, Object value) {
/*  506 */     return new HashEntry(next, hashCode, key, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addEntry(HashEntry entry, int hashIndex) {
/*  519 */     this.data[hashIndex] = entry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeMapping(HashEntry entry, int hashIndex, HashEntry previous) {
/*  535 */     this.modCount++;
/*  536 */     removeEntry(entry, hashIndex, previous);
/*  537 */     this.size--;
/*  538 */     destroyEntry(entry);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeEntry(HashEntry entry, int hashIndex, HashEntry previous) {
/*  553 */     if (previous == null) {
/*  554 */       this.data[hashIndex] = entry.next;
/*      */     } else {
/*  556 */       previous.next = entry.next;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void destroyEntry(HashEntry entry) {
/*  569 */     entry.next = null;
/*  570 */     entry.key = null;
/*  571 */     entry.value = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkCapacity() {
/*  581 */     if (this.size >= this.threshold) {
/*  582 */       int newCapacity = this.data.length * 2;
/*  583 */       if (newCapacity <= 1073741824) {
/*  584 */         ensureCapacity(newCapacity);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureCapacity(int newCapacity) {
/*  595 */     int oldCapacity = this.data.length;
/*  596 */     if (newCapacity <= oldCapacity) {
/*      */       return;
/*      */     }
/*  599 */     if (this.size == 0) {
/*  600 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  601 */       this.data = new HashEntry[newCapacity];
/*      */     } else {
/*  603 */       HashEntry[] oldEntries = this.data;
/*  604 */       HashEntry[] newEntries = new HashEntry[newCapacity];
/*      */       
/*  606 */       this.modCount++;
/*  607 */       for (int i = oldCapacity - 1; i >= 0; i--) {
/*  608 */         HashEntry entry = oldEntries[i];
/*  609 */         if (entry != null) {
/*  610 */           oldEntries[i] = null;
/*      */           do {
/*  612 */             HashEntry next = entry.next;
/*  613 */             int index = hashIndex(entry.hashCode, newCapacity);
/*  614 */             entry.next = newEntries[index];
/*  615 */             newEntries[index] = entry;
/*  616 */             entry = next;
/*  617 */           } while (entry != null);
/*      */         } 
/*      */       } 
/*  620 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  621 */       this.data = newEntries;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateNewCapacity(int proposedCapacity) {
/*  633 */     int newCapacity = 1;
/*  634 */     if (proposedCapacity > 1073741824) {
/*  635 */       newCapacity = 1073741824;
/*      */     } else {
/*  637 */       while (newCapacity < proposedCapacity) {
/*  638 */         newCapacity <<= 1;
/*      */       }
/*  640 */       if (newCapacity > 1073741824) {
/*  641 */         newCapacity = 1073741824;
/*      */       }
/*      */     } 
/*  644 */     return newCapacity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateThreshold(int newCapacity, float factor) {
/*  656 */     return (int)(newCapacity * factor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected HashEntry entryNext(HashEntry entry) {
/*  670 */     return entry.next;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int entryHashCode(HashEntry entry) {
/*  683 */     return entry.hashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object entryKey(HashEntry entry) {
/*  696 */     return entry.key;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object entryValue(HashEntry entry) {
/*  709 */     return entry.value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MapIterator mapIterator() {
/*  725 */     if (this.size == 0) {
/*  726 */       return EmptyMapIterator.INSTANCE;
/*      */     }
/*  728 */     return new HashMapIterator(this);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class HashMapIterator
/*      */     extends HashIterator
/*      */     implements MapIterator
/*      */   {
/*      */     protected HashMapIterator(AbstractHashedMap parent) {
/*  737 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  741 */       return nextEntry().getKey();
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  745 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  746 */       if (current == null) {
/*  747 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  749 */       return current.getKey();
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  753 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  754 */       if (current == null) {
/*  755 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  757 */       return current.getValue();
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  761 */       AbstractHashedMap.HashEntry current = currentEntry();
/*  762 */       if (current == null) {
/*  763 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  765 */       return current.setValue(value);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set entrySet() {
/*  778 */     if (this.entrySet == null) {
/*  779 */       this.entrySet = new EntrySet(this);
/*      */     }
/*  781 */     return this.entrySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Iterator createEntrySetIterator() {
/*  791 */     if (size() == 0) {
/*  792 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  794 */     return new EntrySetIterator(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class EntrySet
/*      */     extends AbstractSet
/*      */   {
/*      */     protected final AbstractHashedMap parent;
/*      */ 
/*      */     
/*      */     protected EntrySet(AbstractHashedMap parent) {
/*  806 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  810 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  814 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object entry) {
/*  818 */       if (entry instanceof Map.Entry) {
/*  819 */         Map.Entry e = (Map.Entry)entry;
/*  820 */         Map.Entry match = this.parent.getEntry(e.getKey());
/*  821 */         return (match != null && match.equals(e));
/*      */       } 
/*  823 */       return false;
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  827 */       if (!(obj instanceof Map.Entry)) {
/*  828 */         return false;
/*      */       }
/*  830 */       if (!contains(obj)) {
/*  831 */         return false;
/*      */       }
/*  833 */       Map.Entry entry = (Map.Entry)obj;
/*  834 */       Object key = entry.getKey();
/*  835 */       this.parent.remove(key);
/*  836 */       return true;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  840 */       return this.parent.createEntrySetIterator();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class EntrySetIterator
/*      */     extends HashIterator
/*      */   {
/*      */     protected EntrySetIterator(AbstractHashedMap parent) {
/*  850 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  854 */       return nextEntry();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set keySet() {
/*  867 */     if (this.keySet == null) {
/*  868 */       this.keySet = new KeySet(this);
/*      */     }
/*  870 */     return this.keySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Iterator createKeySetIterator() {
/*  880 */     if (size() == 0) {
/*  881 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  883 */     return new KeySetIterator(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class KeySet
/*      */     extends AbstractSet
/*      */   {
/*      */     protected final AbstractHashedMap parent;
/*      */ 
/*      */     
/*      */     protected KeySet(AbstractHashedMap parent) {
/*  895 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  899 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  903 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  907 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  911 */       boolean result = this.parent.containsKey(key);
/*  912 */       this.parent.remove(key);
/*  913 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  917 */       return this.parent.createKeySetIterator();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class KeySetIterator
/*      */     extends EntrySetIterator
/*      */   {
/*      */     protected KeySetIterator(AbstractHashedMap parent) {
/*  927 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  931 */       return nextEntry().getKey();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection values() {
/*  944 */     if (this.values == null) {
/*  945 */       this.values = new Values(this);
/*      */     }
/*  947 */     return this.values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Iterator createValuesIterator() {
/*  957 */     if (size() == 0) {
/*  958 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  960 */     return new ValuesIterator(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class Values
/*      */     extends AbstractCollection
/*      */   {
/*      */     protected final AbstractHashedMap parent;
/*      */ 
/*      */     
/*      */     protected Values(AbstractHashedMap parent) {
/*  972 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  976 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  980 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  984 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  988 */       return this.parent.createValuesIterator();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ValuesIterator
/*      */     extends HashIterator
/*      */   {
/*      */     protected ValuesIterator(AbstractHashedMap parent) {
/*  998 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1002 */       return nextEntry().getValue();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class HashEntry
/*      */     implements Map.Entry, KeyValue
/*      */   {
/*      */     protected HashEntry next;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int hashCode;
/*      */ 
/*      */     
/*      */     protected Object key;
/*      */ 
/*      */     
/*      */     protected Object value;
/*      */ 
/*      */ 
/*      */     
/*      */     protected HashEntry(HashEntry next, int hashCode, Object key, Object value) {
/* 1027 */       this.next = next;
/* 1028 */       this.hashCode = hashCode;
/* 1029 */       this.key = key;
/* 1030 */       this.value = value;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1034 */       return (this.key == AbstractHashedMap.NULL) ? null : this.key;
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1038 */       return this.value;
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/* 1042 */       Object old = this.value;
/* 1043 */       this.value = value;
/* 1044 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1048 */       if (obj == this) {
/* 1049 */         return true;
/*      */       }
/* 1051 */       if (!(obj instanceof Map.Entry)) {
/* 1052 */         return false;
/*      */       }
/* 1054 */       Map.Entry other = (Map.Entry)obj;
/* 1055 */       return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1061 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1066 */       return getKey() + '=' + getValue();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static abstract class HashIterator
/*      */     implements Iterator
/*      */   {
/*      */     protected final AbstractHashedMap parent;
/*      */ 
/*      */     
/*      */     protected int hashIndex;
/*      */     
/*      */     protected AbstractHashedMap.HashEntry last;
/*      */     
/*      */     protected AbstractHashedMap.HashEntry next;
/*      */     
/*      */     protected int expectedModCount;
/*      */ 
/*      */     
/*      */     protected HashIterator(AbstractHashedMap parent) {
/* 1088 */       this.parent = parent;
/* 1089 */       AbstractHashedMap.HashEntry[] data = parent.data;
/* 1090 */       int i = data.length;
/* 1091 */       AbstractHashedMap.HashEntry next = null;
/* 1092 */       while (i > 0 && next == null) {
/* 1093 */         next = data[--i];
/*      */       }
/* 1095 */       this.next = next;
/* 1096 */       this.hashIndex = i;
/* 1097 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1101 */       return (this.next != null);
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry nextEntry() {
/* 1105 */       if (this.parent.modCount != this.expectedModCount) {
/* 1106 */         throw new ConcurrentModificationException();
/*      */       }
/* 1108 */       AbstractHashedMap.HashEntry newCurrent = this.next;
/* 1109 */       if (newCurrent == null) {
/* 1110 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/* 1112 */       AbstractHashedMap.HashEntry[] data = this.parent.data;
/* 1113 */       int i = this.hashIndex;
/* 1114 */       AbstractHashedMap.HashEntry n = newCurrent.next;
/* 1115 */       while (n == null && i > 0) {
/* 1116 */         n = data[--i];
/*      */       }
/* 1118 */       this.next = n;
/* 1119 */       this.hashIndex = i;
/* 1120 */       this.last = newCurrent;
/* 1121 */       return newCurrent;
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry currentEntry() {
/* 1125 */       return this.last;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1129 */       if (this.last == null) {
/* 1130 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/* 1132 */       if (this.parent.modCount != this.expectedModCount) {
/* 1133 */         throw new ConcurrentModificationException();
/*      */       }
/* 1135 */       this.parent.remove(this.last.getKey());
/* 1136 */       this.last = null;
/* 1137 */       this.expectedModCount = this.parent.modCount;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1141 */       if (this.last != null) {
/* 1142 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
/*      */       }
/* 1144 */       return "Iterator[]";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract Object next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 1170 */     out.writeFloat(this.loadFactor);
/* 1171 */     out.writeInt(this.data.length);
/* 1172 */     out.writeInt(this.size);
/* 1173 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/* 1174 */       out.writeObject(it.next());
/* 1175 */       out.writeObject(it.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1198 */     this.loadFactor = in.readFloat();
/* 1199 */     int capacity = in.readInt();
/* 1200 */     int size = in.readInt();
/* 1201 */     init();
/* 1202 */     this.data = new HashEntry[capacity];
/* 1203 */     for (int i = 0; i < size; i++) {
/* 1204 */       Object key = in.readObject();
/* 1205 */       Object value = in.readObject();
/* 1206 */       put(key, value);
/*      */     } 
/* 1208 */     this.threshold = calculateThreshold(this.data.length, this.loadFactor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object clone() {
/*      */     try {
/* 1222 */       AbstractHashedMap cloned = (AbstractHashedMap)super.clone();
/* 1223 */       cloned.data = new HashEntry[this.data.length];
/* 1224 */       cloned.entrySet = null;
/* 1225 */       cloned.keySet = null;
/* 1226 */       cloned.values = null;
/* 1227 */       cloned.modCount = 0;
/* 1228 */       cloned.size = 0;
/* 1229 */       cloned.init();
/* 1230 */       cloned.putAll((Map)this);
/* 1231 */       return cloned;
/*      */     }
/* 1233 */     catch (CloneNotSupportedException ex) {
/* 1234 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1245 */     if (obj == this) {
/* 1246 */       return true;
/*      */     }
/* 1248 */     if (!(obj instanceof Map)) {
/* 1249 */       return false;
/*      */     }
/* 1251 */     Map map = (Map)obj;
/* 1252 */     if (map.size() != size()) {
/* 1253 */       return false;
/*      */     }
/* 1255 */     MapIterator it = mapIterator();
/*      */     try {
/* 1257 */       while (it.hasNext()) {
/* 1258 */         Object key = it.next();
/* 1259 */         Object value = it.getValue();
/* 1260 */         if (value == null) {
/* 1261 */           if (map.get(key) != null || !map.containsKey(key))
/* 1262 */             return false; 
/*      */           continue;
/*      */         } 
/* 1265 */         if (!value.equals(map.get(key))) {
/* 1266 */           return false;
/*      */         }
/*      */       }
/*      */     
/* 1270 */     } catch (ClassCastException ignored) {
/* 1271 */       return false;
/* 1272 */     } catch (NullPointerException ignored) {
/* 1273 */       return false;
/*      */     } 
/* 1275 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1284 */     int total = 0;
/* 1285 */     Iterator it = createEntrySetIterator();
/* 1286 */     while (it.hasNext()) {
/* 1287 */       total += it.next().hashCode();
/*      */     }
/* 1289 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1298 */     if (size() == 0) {
/* 1299 */       return "{}";
/*      */     }
/* 1301 */     StringBuffer buf = new StringBuffer(32 * size());
/* 1302 */     buf.append('{');
/*      */     
/* 1304 */     MapIterator it = mapIterator();
/* 1305 */     boolean hasNext = it.hasNext();
/* 1306 */     while (hasNext) {
/* 1307 */       Object key = it.next();
/* 1308 */       Object value = it.getValue();
/* 1309 */       buf.append((key == this) ? "(this Map)" : key).append('=').append((value == this) ? "(this Map)" : value);
/*      */ 
/*      */ 
/*      */       
/* 1313 */       hasNext = it.hasNext();
/* 1314 */       if (hasNext) {
/* 1315 */         buf.append(',').append(' ');
/*      */       }
/*      */     } 
/*      */     
/* 1319 */     buf.append('}');
/* 1320 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractHashedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */