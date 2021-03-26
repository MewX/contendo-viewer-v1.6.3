/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.list.UnmodifiableList;
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
/*      */ public class SequencedHashMap
/*      */   implements Externalizable, Cloneable, Map
/*      */ {
/*      */   private Entry sentinel;
/*      */   private HashMap entries;
/*      */   
/*      */   private static class Entry
/*      */     implements Map.Entry, KeyValue
/*      */   {
/*      */     private final Object key;
/*      */     private Object value;
/*   83 */     Entry next = null;
/*   84 */     Entry prev = null;
/*      */     
/*      */     public Entry(Object key, Object value) {
/*   87 */       this.key = key;
/*   88 */       this.value = value;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getKey() {
/*   93 */       return this.key;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue() {
/*   98 */       return this.value;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object setValue(Object value) {
/*  103 */       Object oldValue = this.value;
/*  104 */       this.value = value;
/*  105 */       return oldValue;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  110 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  114 */       if (obj == null)
/*  115 */         return false; 
/*  116 */       if (obj == this)
/*  117 */         return true; 
/*  118 */       if (!(obj instanceof Map.Entry)) {
/*  119 */         return false;
/*      */       }
/*  121 */       Map.Entry other = (Map.Entry)obj;
/*      */ 
/*      */       
/*  124 */       if ((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) if ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue()));  return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  129 */       return "[" + getKey() + "=" + getValue() + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final Entry createSentinel() {
/*  139 */     Entry s = new Entry(null, null);
/*  140 */     s.prev = s;
/*  141 */     s.next = s;
/*  142 */     return s;
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
/*  161 */   private transient long modCount = 0L; private static final int KEY = 0;
/*      */   private static final int VALUE = 1;
/*      */   private static final int ENTRY = 2;
/*      */   private static final int REMOVED_MASK = -2147483648;
/*      */   private static final long serialVersionUID = 3380552487888102930L;
/*      */   
/*      */   public SequencedHashMap() {
/*  168 */     this.sentinel = createSentinel();
/*  169 */     this.entries = new HashMap();
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
/*      */   public SequencedHashMap(int initialSize) {
/*  181 */     this.sentinel = createSentinel();
/*  182 */     this.entries = new HashMap(initialSize);
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
/*      */   public SequencedHashMap(int initialSize, float loadFactor) {
/*  196 */     this.sentinel = createSentinel();
/*  197 */     this.entries = new HashMap(initialSize, loadFactor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SequencedHashMap(Map m) {
/*  206 */     this();
/*  207 */     putAll(m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeEntry(Entry entry) {
/*  215 */     entry.next.prev = entry.prev;
/*  216 */     entry.prev.next = entry.next;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void insertEntry(Entry entry) {
/*  224 */     entry.next = this.sentinel;
/*  225 */     entry.prev = this.sentinel.prev;
/*  226 */     this.sentinel.prev.next = entry;
/*  227 */     this.sentinel.prev = entry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  237 */     return this.entries.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  246 */     return (this.sentinel.next == this.sentinel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  254 */     return this.entries.containsKey(key);
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
/*      */   public boolean containsValue(Object value) {
/*  269 */     if (value == null) {
/*  270 */       for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  271 */         if (pos.getValue() == null)
/*  272 */           return true; 
/*      */       } 
/*      */     } else {
/*  275 */       for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  276 */         if (value.equals(pos.getValue()))
/*  277 */           return true; 
/*      */       } 
/*      */     } 
/*  280 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object o) {
/*  288 */     Entry entry = (Entry)this.entries.get(o);
/*  289 */     if (entry == null) {
/*  290 */       return null;
/*      */     }
/*  292 */     return entry.getValue();
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
/*      */   public Map.Entry getFirst() {
/*  309 */     return isEmpty() ? null : this.sentinel.next;
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
/*      */   public Object getFirstKey() {
/*  329 */     return this.sentinel.next.getKey();
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
/*      */   public Object getFirstValue() {
/*  349 */     return this.sentinel.next.getValue();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map.Entry getLast() {
/*  376 */     return isEmpty() ? null : this.sentinel.prev;
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
/*      */   public Object getLastKey() {
/*  396 */     return this.sentinel.prev.getKey();
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
/*      */   public Object getLastValue() {
/*  416 */     return this.sentinel.prev.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  423 */     this.modCount++;
/*      */     
/*  425 */     Object oldValue = null;
/*      */ 
/*      */     
/*  428 */     Entry e = (Entry)this.entries.get(key);
/*      */ 
/*      */     
/*  431 */     if (e != null) {
/*      */       
/*  433 */       removeEntry(e);
/*      */ 
/*      */       
/*  436 */       oldValue = e.setValue(value);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  445 */       e = new Entry(key, value);
/*  446 */       this.entries.put(key, e);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  451 */     insertEntry(e);
/*      */     
/*  453 */     return oldValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/*  460 */     Entry e = removeImpl(key);
/*  461 */     return (e == null) ? null : e.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Entry removeImpl(Object key) {
/*  469 */     Entry e = (Entry)this.entries.remove(key);
/*  470 */     if (e == null)
/*  471 */       return null; 
/*  472 */     this.modCount++;
/*  473 */     removeEntry(e);
/*  474 */     return e;
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
/*      */   public void putAll(Map t) {
/*  488 */     Iterator iter = t.entrySet().iterator();
/*  489 */     while (iter.hasNext()) {
/*  490 */       Map.Entry entry = iter.next();
/*  491 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  499 */     this.modCount++;
/*      */ 
/*      */     
/*  502 */     this.entries.clear();
/*      */ 
/*      */     
/*  505 */     this.sentinel.next = this.sentinel;
/*  506 */     this.sentinel.prev = this.sentinel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  513 */     if (obj == null)
/*  514 */       return false; 
/*  515 */     if (obj == this) {
/*  516 */       return true;
/*      */     }
/*  518 */     if (!(obj instanceof Map)) {
/*  519 */       return false;
/*      */     }
/*  521 */     return entrySet().equals(((Map)obj).entrySet());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  528 */     return entrySet().hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  539 */     StringBuffer buf = new StringBuffer();
/*  540 */     buf.append('[');
/*  541 */     for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/*  542 */       buf.append(pos.getKey());
/*  543 */       buf.append('=');
/*  544 */       buf.append(pos.getValue());
/*  545 */       if (pos.next != this.sentinel) {
/*  546 */         buf.append(',');
/*      */       }
/*      */     } 
/*  549 */     buf.append(']');
/*      */     
/*  551 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set keySet() {
/*  558 */     return new AbstractSet(this) {
/*      */         private final SequencedHashMap this$0;
/*      */         
/*      */         public Iterator iterator() {
/*  562 */           return new SequencedHashMap.OrderedIterator(this.this$0, 0);
/*      */         }
/*      */         public boolean remove(Object o) {
/*  565 */           SequencedHashMap.Entry e = this.this$0.removeImpl(o);
/*  566 */           return (e != null);
/*      */         }
/*      */ 
/*      */         
/*      */         public void clear() {
/*  571 */           this.this$0.clear();
/*      */         }
/*      */         public int size() {
/*  574 */           return this.this$0.size();
/*      */         }
/*      */         public boolean isEmpty() {
/*  577 */           return this.this$0.isEmpty();
/*      */         }
/*      */         public boolean contains(Object o) {
/*  580 */           return this.this$0.containsKey(o);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection values() {
/*  590 */     return new AbstractCollection(this) { private final SequencedHashMap this$0;
/*      */         
/*      */         public Iterator iterator() {
/*  593 */           return new SequencedHashMap.OrderedIterator(this.this$0, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean remove(Object value) {
/*  599 */           if (value == null) {
/*  600 */             for (SequencedHashMap.Entry pos = this.this$0.sentinel.next; pos != this.this$0.sentinel; pos = pos.next) {
/*  601 */               if (pos.getValue() == null) {
/*  602 */                 this.this$0.removeImpl(pos.getKey());
/*  603 */                 return true;
/*      */               } 
/*      */             } 
/*      */           } else {
/*  607 */             for (SequencedHashMap.Entry pos = this.this$0.sentinel.next; pos != this.this$0.sentinel; pos = pos.next) {
/*  608 */               if (value.equals(pos.getValue())) {
/*  609 */                 this.this$0.removeImpl(pos.getKey());
/*  610 */                 return true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/*  615 */           return false;
/*      */         }
/*      */ 
/*      */         
/*      */         public void clear() {
/*  620 */           this.this$0.clear();
/*      */         }
/*      */         public int size() {
/*  623 */           return this.this$0.size();
/*      */         }
/*      */         public boolean isEmpty() {
/*  626 */           return this.this$0.isEmpty();
/*      */         }
/*      */         public boolean contains(Object o) {
/*  629 */           return this.this$0.containsValue(o);
/*      */         } }
/*      */       ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set entrySet() {
/*  638 */     return new AbstractSet(this) { private final SequencedHashMap this$0;
/*      */         
/*      */         private SequencedHashMap.Entry findEntry(Object o) {
/*  641 */           if (o == null)
/*  642 */             return null; 
/*  643 */           if (!(o instanceof Map.Entry)) {
/*  644 */             return null;
/*      */           }
/*  646 */           Map.Entry e = (Map.Entry)o;
/*  647 */           SequencedHashMap.Entry entry = (SequencedHashMap.Entry)this.this$0.entries.get(e.getKey());
/*  648 */           if (entry != null && entry.equals(e)) {
/*  649 */             return entry;
/*      */           }
/*  651 */           return null;
/*      */         }
/*      */ 
/*      */         
/*      */         public Iterator iterator() {
/*  656 */           return new SequencedHashMap.OrderedIterator(this.this$0, 2);
/*      */         }
/*      */         public boolean remove(Object o) {
/*  659 */           SequencedHashMap.Entry e = findEntry(o);
/*  660 */           if (e == null) {
/*  661 */             return false;
/*      */           }
/*  663 */           return (this.this$0.removeImpl(e.getKey()) != null);
/*      */         }
/*      */ 
/*      */         
/*      */         public void clear() {
/*  668 */           this.this$0.clear();
/*      */         }
/*      */         public int size() {
/*  671 */           return this.this$0.size();
/*      */         }
/*      */         public boolean isEmpty() {
/*  674 */           return this.this$0.isEmpty();
/*      */         }
/*      */         public boolean contains(Object o) {
/*  677 */           return (findEntry(o) != null);
/*      */         } }
/*      */       ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class OrderedIterator
/*      */     implements Iterator
/*      */   {
/*      */     private int returnType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private SequencedHashMap.Entry pos;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private transient long expectedModCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final SequencedHashMap this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public OrderedIterator(SequencedHashMap this$0, int returnType) {
/*  719 */       this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */       
/*      */       this.pos = this.this$0.sentinel;
/*      */ 
/*      */ 
/*      */       
/*      */       this.expectedModCount = this.this$0.modCount;
/*      */ 
/*      */       
/*  730 */       this.returnType = returnType | Integer.MIN_VALUE;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/*  741 */       return (this.pos.next != this.this$0.sentinel);
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
/*      */     
/*      */     public Object next() {
/*  756 */       if (this.this$0.modCount != this.expectedModCount) {
/*  757 */         throw new ConcurrentModificationException();
/*      */       }
/*  759 */       if (this.pos.next == this.this$0.sentinel) {
/*  760 */         throw new NoSuchElementException();
/*      */       }
/*      */ 
/*      */       
/*  764 */       this.returnType &= Integer.MAX_VALUE;
/*      */       
/*  766 */       this.pos = this.pos.next;
/*  767 */       switch (this.returnType) {
/*      */         case 0:
/*  769 */           return this.pos.getKey();
/*      */         case 1:
/*  771 */           return this.pos.getValue();
/*      */         case 2:
/*  773 */           return this.pos;
/*      */       } 
/*      */       
/*  776 */       throw new Error("bad iterator type: " + this.returnType);
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
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {
/*  793 */       if ((this.returnType & Integer.MIN_VALUE) != 0) {
/*  794 */         throw new IllegalStateException("remove() must follow next()");
/*      */       }
/*  796 */       if (this.this$0.modCount != this.expectedModCount) {
/*  797 */         throw new ConcurrentModificationException();
/*      */       }
/*      */       
/*  800 */       this.this$0.removeImpl(this.pos.getKey());
/*      */ 
/*      */       
/*  803 */       this.expectedModCount++;
/*      */ 
/*      */       
/*  806 */       this.returnType |= Integer.MIN_VALUE;
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
/*      */   public Object clone() throws CloneNotSupportedException {
/*  828 */     SequencedHashMap map = (SequencedHashMap)super.clone();
/*      */ 
/*      */     
/*  831 */     map.sentinel = createSentinel();
/*      */ 
/*      */ 
/*      */     
/*  835 */     map.entries = new HashMap();
/*      */ 
/*      */     
/*  838 */     map.putAll(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  848 */     return map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map.Entry getEntry(int index) {
/*  858 */     Entry pos = this.sentinel;
/*      */     
/*  860 */     if (index < 0) {
/*  861 */       throw new ArrayIndexOutOfBoundsException(index + " < 0");
/*      */     }
/*      */ 
/*      */     
/*  865 */     int i = -1;
/*  866 */     while (i < index - 1 && pos.next != this.sentinel) {
/*  867 */       i++;
/*  868 */       pos = pos.next;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  873 */     if (pos.next == this.sentinel) {
/*  874 */       throw new ArrayIndexOutOfBoundsException(index + " >= " + (i + 1));
/*      */     }
/*      */     
/*  877 */     return pos.next;
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
/*      */   public Object get(int index) {
/*  889 */     return getEntry(index).getKey();
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
/*      */   public Object getValue(int index) {
/*  901 */     return getEntry(index).getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOf(Object key) {
/*  911 */     Entry e = (Entry)this.entries.get(key);
/*  912 */     if (e == null) {
/*  913 */       return -1;
/*      */     }
/*  915 */     int pos = 0;
/*  916 */     while (e.prev != this.sentinel) {
/*  917 */       pos++;
/*  918 */       e = e.prev;
/*      */     } 
/*  920 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator iterator() {
/*  929 */     return keySet().iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int lastIndexOf(Object key) {
/*  940 */     return indexOf(key);
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
/*      */   public List sequence() {
/*  958 */     List l = new ArrayList(size());
/*  959 */     Iterator iter = keySet().iterator();
/*  960 */     while (iter.hasNext()) {
/*  961 */       l.add(iter.next());
/*      */     }
/*      */     
/*  964 */     return UnmodifiableList.decorate(l);
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
/*      */   public Object remove(int index) {
/*  978 */     return remove(get(index));
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
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*  991 */     int size = in.readInt();
/*  992 */     for (int i = 0; i < size; i++) {
/*  993 */       Object key = in.readObject();
/*  994 */       Object value = in.readObject();
/*  995 */       put(key, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/* 1006 */     out.writeInt(size());
/* 1007 */     for (Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
/* 1008 */       out.writeObject(pos.getKey());
/* 1009 */       out.writeObject(pos.getValue());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/SequencedHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */