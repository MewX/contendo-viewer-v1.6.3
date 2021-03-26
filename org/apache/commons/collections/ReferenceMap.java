/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReferenceMap
/*     */   extends AbstractMap
/*     */ {
/*     */   private static final long serialVersionUID = -3370601314380922368L;
/*     */   public static final int HARD = 0;
/*     */   public static final int SOFT = 1;
/*     */   public static final int WEAK = 2;
/*     */   private int keyType;
/*     */   private int valueType;
/*     */   private float loadFactor;
/*     */   private boolean purgeValues = false;
/* 152 */   private transient ReferenceQueue queue = new ReferenceQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient Entry[] table;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient int size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient int threshold;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile transient int modCount;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient Set keySet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient Set entrySet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private transient Collection values;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReferenceMap() {
/* 203 */     this(0, 1);
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
/*     */   public ReferenceMap(int keyType, int valueType, boolean purgeValues) {
/* 218 */     this(keyType, valueType);
/* 219 */     this.purgeValues = purgeValues;
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
/*     */   public ReferenceMap(int keyType, int valueType) {
/* 232 */     this(keyType, valueType, 16, 0.75F);
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
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 255 */     this(keyType, valueType, capacity, loadFactor);
/* 256 */     this.purgeValues = purgeValues;
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
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor) {
/* 274 */     verify("keyType", keyType);
/* 275 */     verify("valueType", valueType);
/*     */     
/* 277 */     if (capacity <= 0) {
/* 278 */       throw new IllegalArgumentException("capacity must be positive");
/*     */     }
/* 280 */     if (loadFactor <= 0.0F || loadFactor >= 1.0F) {
/* 281 */       throw new IllegalArgumentException("Load factor must be greater than 0 and less than 1.");
/*     */     }
/*     */     
/* 284 */     this.keyType = keyType;
/* 285 */     this.valueType = valueType;
/*     */     
/* 287 */     int v = 1;
/* 288 */     for (; v < capacity; v *= 2);
/*     */     
/* 290 */     this.table = new Entry[v];
/* 291 */     this.loadFactor = loadFactor;
/* 292 */     this.threshold = (int)(v * loadFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void verify(String name, int type) {
/* 298 */     if (type < 0 || type > 2) {
/* 299 */       throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK.");
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
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 312 */     out.defaultWriteObject();
/* 313 */     out.writeInt(this.table.length);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     for (Iterator iter = entrySet().iterator(); iter.hasNext(); ) {
/* 319 */       Map.Entry entry = iter.next();
/* 320 */       out.writeObject(entry.getKey());
/* 321 */       out.writeObject(entry.getValue());
/*     */     } 
/* 323 */     out.writeObject(null);
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
/*     */   private void readObject(ObjectInputStream inp) throws IOException, ClassNotFoundException {
/* 335 */     inp.defaultReadObject();
/* 336 */     this.table = new Entry[inp.readInt()];
/* 337 */     this.threshold = (int)(this.table.length * this.loadFactor);
/* 338 */     this.queue = new ReferenceQueue();
/* 339 */     Object key = inp.readObject();
/* 340 */     while (key != null) {
/* 341 */       Object value = inp.readObject();
/* 342 */       put(key, value);
/* 343 */       key = inp.readObject();
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
/*     */   private Object toReference(int type, Object referent, int hash) {
/* 360 */     switch (type) { case 0:
/* 361 */         return referent;
/* 362 */       case 1: return new SoftRef(hash, referent, this.queue);
/* 363 */       case 2: return new WeakRef(hash, referent, this.queue); }
/* 364 */      throw new Error();
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
/*     */   private Entry getEntry(Object key) {
/* 377 */     if (key == null) return null; 
/* 378 */     int hash = key.hashCode();
/* 379 */     int index = indexFor(hash);
/* 380 */     for (Entry entry = this.table[index]; entry != null; entry = entry.next) {
/* 381 */       if (entry.hash == hash && key.equals(entry.getKey())) {
/* 382 */         return entry;
/*     */       }
/*     */     } 
/* 385 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int indexFor(int hash) {
/* 395 */     hash += hash << 15 ^ 0xFFFFFFFF;
/* 396 */     hash ^= hash >>> 10;
/* 397 */     hash += hash << 3;
/* 398 */     hash ^= hash >>> 6;
/* 399 */     hash += hash << 11 ^ 0xFFFFFFFF;
/* 400 */     hash ^= hash >>> 16;
/* 401 */     return hash & this.table.length - 1;
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
/*     */   private void resize() {
/* 413 */     Entry[] old = this.table;
/* 414 */     this.table = new Entry[old.length * 2];
/*     */     
/* 416 */     for (int i = 0; i < old.length; i++) {
/* 417 */       Entry next = old[i];
/* 418 */       while (next != null) {
/* 419 */         Entry entry = next;
/* 420 */         next = next.next;
/* 421 */         int index = indexFor(entry.hash);
/* 422 */         entry.next = this.table[index];
/* 423 */         this.table[index] = entry;
/*     */       } 
/* 425 */       old[i] = null;
/*     */     } 
/* 427 */     this.threshold = (int)(this.table.length * this.loadFactor);
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
/*     */   private void purge() {
/* 445 */     Reference ref = this.queue.poll();
/* 446 */     while (ref != null) {
/* 447 */       purge(ref);
/* 448 */       ref = this.queue.poll();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void purge(Reference ref) {
/* 457 */     int hash = ref.hashCode();
/* 458 */     int index = indexFor(hash);
/* 459 */     Entry previous = null;
/* 460 */     Entry entry = this.table[index];
/* 461 */     while (entry != null) {
/* 462 */       if (entry.purge(ref)) {
/* 463 */         if (previous == null) { this.table[index] = entry.next; }
/* 464 */         else { previous.next = entry.next; }
/* 465 */          this.size--;
/*     */         return;
/*     */       } 
/* 468 */       previous = entry;
/* 469 */       entry = entry.next;
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
/*     */   public int size() {
/* 481 */     purge();
/* 482 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 492 */     purge();
/* 493 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 503 */     purge();
/* 504 */     Entry entry = getEntry(key);
/* 505 */     if (entry == null) return false; 
/* 506 */     return (entry.getValue() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 517 */     purge();
/* 518 */     Entry entry = getEntry(key);
/* 519 */     if (entry == null) return null; 
/* 520 */     return entry.getValue();
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
/* 536 */     if (key == null) throw new NullPointerException("null keys not allowed"); 
/* 537 */     if (value == null) throw new NullPointerException("null values not allowed");
/*     */     
/* 539 */     purge();
/* 540 */     if (this.size + 1 > this.threshold) resize();
/*     */     
/* 542 */     int hash = key.hashCode();
/* 543 */     int index = indexFor(hash);
/* 544 */     Entry entry = this.table[index];
/* 545 */     while (entry != null) {
/* 546 */       if (hash == entry.hash && key.equals(entry.getKey())) {
/* 547 */         Object result = entry.getValue();
/* 548 */         entry.setValue(value);
/* 549 */         return result;
/*     */       } 
/* 551 */       entry = entry.next;
/*     */     } 
/* 553 */     this.size++;
/* 554 */     this.modCount++;
/* 555 */     key = toReference(this.keyType, key, hash);
/* 556 */     value = toReference(this.valueType, value, hash);
/* 557 */     this.table[index] = new Entry(this, key, hash, value, this.table[index]);
/* 558 */     return null;
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
/*     */   public Object remove(Object key) {
/* 570 */     if (key == null) return null; 
/* 571 */     purge();
/* 572 */     int hash = key.hashCode();
/* 573 */     int index = indexFor(hash);
/* 574 */     Entry previous = null;
/* 575 */     Entry entry = this.table[index];
/* 576 */     while (entry != null) {
/* 577 */       if (hash == entry.hash && key.equals(entry.getKey())) {
/* 578 */         if (previous == null) { this.table[index] = entry.next; }
/* 579 */         else { previous.next = entry.next; }
/* 580 */          this.size--;
/* 581 */         this.modCount++;
/* 582 */         return entry.getValue();
/*     */       } 
/* 584 */       previous = entry;
/* 585 */       entry = entry.next;
/*     */     } 
/* 587 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 595 */     Arrays.fill((Object[])this.table, (Object)null);
/* 596 */     this.size = 0;
/* 597 */     while (this.queue.poll() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 607 */     if (this.entrySet != null) {
/* 608 */       return this.entrySet;
/*     */     }
/* 610 */     this.entrySet = new AbstractSet(this) {
/*     */         public int size() {
/* 612 */           return this.this$0.size();
/*     */         }
/*     */         private final ReferenceMap this$0;
/*     */         public void clear() {
/* 616 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 620 */           if (o == null) return false; 
/* 621 */           if (!(o instanceof Map.Entry)) return false; 
/* 622 */           Map.Entry e = (Map.Entry)o;
/* 623 */           ReferenceMap.Entry e2 = this.this$0.getEntry(e.getKey());
/* 624 */           return (e2 != null && e.equals(e2));
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 628 */           boolean r = contains(o);
/* 629 */           if (r) {
/* 630 */             Map.Entry e = (Map.Entry)o;
/* 631 */             this.this$0.remove(e.getKey());
/*     */           } 
/* 633 */           return r;
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 637 */           return new ReferenceMap.EntryIterator(this.this$0);
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 641 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] arr) {
/* 645 */           ArrayList list = new ArrayList();
/* 646 */           Iterator iterator = iterator();
/* 647 */           while (iterator.hasNext()) {
/* 648 */             ReferenceMap.Entry e = iterator.next();
/* 649 */             list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
/*     */           } 
/* 651 */           return list.toArray(arr);
/*     */         }
/*     */       };
/* 654 */     return this.entrySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 664 */     if (this.keySet != null) return this.keySet; 
/* 665 */     this.keySet = new AbstractSet(this) { private final ReferenceMap this$0;
/*     */         public int size() {
/* 667 */           return this.this$0.size();
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 671 */           return new ReferenceMap.KeyIterator();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 675 */           return this.this$0.containsKey(o);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean remove(Object o) {
/* 680 */           Object r = this.this$0.remove(o);
/* 681 */           return (r != null);
/*     */         }
/*     */         
/*     */         public void clear() {
/* 685 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 689 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] array) {
/* 693 */           Collection c = new ArrayList(size());
/* 694 */           for (Iterator it = iterator(); it.hasNext();) {
/* 695 */             c.add(it.next());
/*     */           }
/* 697 */           return c.toArray(array);
/*     */         } }
/*     */       ;
/* 700 */     return this.keySet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection values() {
/* 710 */     if (this.values != null) return this.values; 
/* 711 */     this.values = new AbstractCollection(this) { private final ReferenceMap this$0;
/*     */         public int size() {
/* 713 */           return this.this$0.size();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 717 */           this.this$0.clear();
/*     */         }
/*     */         
/*     */         public Iterator iterator() {
/* 721 */           return new ReferenceMap.ValueIterator();
/*     */         }
/*     */         
/*     */         public Object[] toArray() {
/* 725 */           return toArray(new Object[0]);
/*     */         }
/*     */         
/*     */         public Object[] toArray(Object[] array) {
/* 729 */           Collection c = new ArrayList(size());
/* 730 */           for (Iterator it = iterator(); it.hasNext();) {
/* 731 */             c.add(it.next());
/*     */           }
/* 733 */           return c.toArray(array);
/*     */         } }
/*     */       ;
/* 736 */     return this.values;
/*     */   }
/*     */ 
/*     */   
/*     */   private class Entry
/*     */     implements Map.Entry, KeyValue
/*     */   {
/*     */     Object key;
/*     */     Object value;
/*     */     int hash;
/*     */     Entry next;
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     public Entry(ReferenceMap this$0, Object key, int hash, Object value, Entry next) {
/* 750 */       this.this$0 = this$0;
/* 751 */       this.key = key;
/* 752 */       this.hash = hash;
/* 753 */       this.value = value;
/* 754 */       this.next = next;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getKey() {
/* 759 */       return (this.this$0.keyType > 0) ? ((Reference)this.key).get() : this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getValue() {
/* 764 */       return (this.this$0.valueType > 0) ? ((Reference)this.value).get() : this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object setValue(Object object) {
/* 769 */       Object old = getValue();
/* 770 */       if (this.this$0.valueType > 0) ((Reference)this.value).clear(); 
/* 771 */       this.value = this.this$0.toReference(this.this$0.valueType, object, this.hash);
/* 772 */       return old;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 777 */       if (o == null) return false; 
/* 778 */       if (o == this) return true; 
/* 779 */       if (!(o instanceof Map.Entry)) return false;
/*     */       
/* 781 */       Map.Entry entry = (Map.Entry)o;
/* 782 */       Object key = entry.getKey();
/* 783 */       Object value = entry.getValue();
/* 784 */       if (key == null || value == null) return false; 
/* 785 */       return (key.equals(getKey()) && value.equals(getValue()));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 790 */       Object v = getValue();
/* 791 */       return this.hash ^ ((v == null) ? 0 : v.hashCode());
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 796 */       return getKey() + "=" + getValue();
/*     */     }
/*     */ 
/*     */     
/*     */     boolean purge(Reference ref) {
/* 801 */       boolean r = (this.this$0.keyType > 0 && this.key == ref);
/* 802 */       r = (r || (this.this$0.valueType > 0 && this.value == ref));
/* 803 */       if (r) {
/* 804 */         if (this.this$0.keyType > 0) ((Reference)this.key).clear(); 
/* 805 */         if (this.this$0.valueType > 0) {
/* 806 */           ((Reference)this.value).clear();
/* 807 */         } else if (this.this$0.purgeValues) {
/* 808 */           this.value = null;
/*     */         } 
/*     */       } 
/* 811 */       return r;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class EntryIterator
/*     */     implements Iterator
/*     */   {
/*     */     int index;
/*     */     
/*     */     ReferenceMap.Entry entry;
/*     */     ReferenceMap.Entry previous;
/*     */     Object nextKey;
/*     */     Object nextValue;
/*     */     Object currentKey;
/*     */     Object currentValue;
/*     */     int expectedModCount;
/*     */     private final ReferenceMap this$0;
/*     */     
/*     */     public EntryIterator(ReferenceMap this$0) {
/* 831 */       this.this$0 = this$0;
/* 832 */       this.index = (this$0.size() != 0) ? this$0.table.length : 0;
/*     */ 
/*     */       
/* 835 */       this.expectedModCount = this$0.modCount;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 840 */       checkMod();
/* 841 */       while (nextNull()) {
/* 842 */         ReferenceMap.Entry e = this.entry;
/* 843 */         int i = this.index;
/* 844 */         while (e == null && i > 0) {
/* 845 */           i--;
/* 846 */           e = this.this$0.table[i];
/*     */         } 
/* 848 */         this.entry = e;
/* 849 */         this.index = i;
/* 850 */         if (e == null) {
/* 851 */           this.currentKey = null;
/* 852 */           this.currentValue = null;
/* 853 */           return false;
/*     */         } 
/* 855 */         this.nextKey = e.getKey();
/* 856 */         this.nextValue = e.getValue();
/* 857 */         if (nextNull()) this.entry = this.entry.next; 
/*     */       } 
/* 859 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     private void checkMod() {
/* 864 */       if (this.this$0.modCount != this.expectedModCount) {
/* 865 */         throw new ConcurrentModificationException();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean nextNull() {
/* 871 */       return (this.nextKey == null || this.nextValue == null);
/*     */     }
/*     */     
/*     */     protected ReferenceMap.Entry nextEntry() {
/* 875 */       checkMod();
/* 876 */       if (nextNull() && !hasNext()) throw new NoSuchElementException(); 
/* 877 */       this.previous = this.entry;
/* 878 */       this.entry = this.entry.next;
/* 879 */       this.currentKey = this.nextKey;
/* 880 */       this.currentValue = this.nextValue;
/* 881 */       this.nextKey = null;
/* 882 */       this.nextValue = null;
/* 883 */       return this.previous;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object next() {
/* 888 */       return nextEntry();
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 893 */       checkMod();
/* 894 */       if (this.previous == null) throw new IllegalStateException(); 
/* 895 */       this.this$0.remove(this.currentKey);
/* 896 */       this.previous = null;
/* 897 */       this.currentKey = null;
/* 898 */       this.currentValue = null;
/* 899 */       this.expectedModCount = this.this$0.modCount;
/*     */     } }
/*     */   
/*     */   private class ValueIterator extends EntryIterator { private final ReferenceMap this$0;
/*     */     
/*     */     private ValueIterator(ReferenceMap this$0) {
/* 905 */       ReferenceMap.this = ReferenceMap.this;
/*     */     } public Object next() {
/* 907 */       return nextEntry().getValue();
/*     */     } }
/*     */   
/*     */   private class KeyIterator extends EntryIterator {
/*     */     private KeyIterator(ReferenceMap this$0) {
/* 912 */       ReferenceMap.this = ReferenceMap.this;
/*     */     } public Object next() {
/* 914 */       return nextEntry().getKey();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private final ReferenceMap this$0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class SoftRef
/*     */     extends SoftReference
/*     */   {
/*     */     private int hash;
/*     */ 
/*     */     
/*     */     public SoftRef(int hash, Object r, ReferenceQueue q) {
/* 930 */       super((T)r, q);
/* 931 */       this.hash = hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 936 */       return this.hash;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class WeakRef
/*     */     extends WeakReference
/*     */   {
/*     */     private int hash;
/*     */     
/*     */     public WeakRef(int hash, Object r, ReferenceQueue q) {
/* 946 */       super((T)r, q);
/* 947 */       this.hash = hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 952 */       return this.hash;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ReferenceMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */