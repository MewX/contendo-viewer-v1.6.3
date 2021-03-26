/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractKeyValue;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListOrderedMap
/*     */   extends AbstractMapDecorator
/*     */   implements Serializable, OrderedMap
/*     */ {
/*     */   private static final long serialVersionUID = 2728177751851003750L;
/*  71 */   protected final List insertOrder = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static OrderedMap decorate(Map map) {
/*  82 */     return new ListOrderedMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListOrderedMap() {
/*  93 */     this(new HashMap());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ListOrderedMap(Map map) {
/* 103 */     super(map);
/* 104 */     this.insertOrder.addAll(getMap().keySet());
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
/* 116 */     out.defaultWriteObject();
/* 117 */     out.writeObject(this.map);
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 129 */     in.defaultReadObject();
/* 130 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MapIterator mapIterator() {
/* 136 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 140 */     return new ListOrderedMapIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 150 */     if (size() == 0) {
/* 151 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 153 */     return this.insertOrder.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object lastKey() {
/* 163 */     if (size() == 0) {
/* 164 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 166 */     return this.insertOrder.get(size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object nextKey(Object key) {
/* 177 */     int index = this.insertOrder.indexOf(key);
/* 178 */     if (index >= 0 && index < size() - 1) {
/* 179 */       return this.insertOrder.get(index + 1);
/*     */     }
/* 181 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object previousKey(Object key) {
/* 192 */     int index = this.insertOrder.indexOf(key);
/* 193 */     if (index > 0) {
/* 194 */       return this.insertOrder.get(index - 1);
/*     */     }
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 201 */     if (getMap().containsKey(key))
/*     */     {
/* 203 */       return getMap().put(key, value);
/*     */     }
/*     */     
/* 206 */     Object result = getMap().put(key, value);
/* 207 */     this.insertOrder.add(key);
/* 208 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAll(Map map) {
/* 213 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 214 */       Map.Entry entry = it.next();
/* 215 */       put(entry.getKey(), entry.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 220 */     Object result = getMap().remove(key);
/* 221 */     this.insertOrder.remove(key);
/* 222 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 226 */     getMap().clear();
/* 227 */     this.insertOrder.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 232 */     return new KeySetView(this);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 236 */     return new ValuesView(this);
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 240 */     return new EntrySetView(this, this.insertOrder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 250 */     if (isEmpty()) {
/* 251 */       return "{}";
/*     */     }
/* 253 */     StringBuffer buf = new StringBuffer();
/* 254 */     buf.append('{');
/* 255 */     boolean first = true;
/* 256 */     Iterator it = entrySet().iterator();
/* 257 */     while (it.hasNext()) {
/* 258 */       Map.Entry entry = it.next();
/* 259 */       Object key = entry.getKey();
/* 260 */       Object value = entry.getValue();
/* 261 */       if (first) {
/* 262 */         first = false;
/*     */       } else {
/* 264 */         buf.append(", ");
/*     */       } 
/* 266 */       buf.append((key == this) ? "(this Map)" : key);
/* 267 */       buf.append('=');
/* 268 */       buf.append((value == this) ? "(this Map)" : value);
/*     */     } 
/* 270 */     buf.append('}');
/* 271 */     return buf.toString();
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
/*     */   public Object get(int index) {
/* 283 */     return this.insertOrder.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(int index) {
/* 294 */     return get(this.insertOrder.get(index));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object key) {
/* 304 */     return this.insertOrder.indexOf(key);
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
/*     */   public Object remove(int index) {
/* 316 */     return remove(get(index));
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
/*     */   public List asList() {
/* 335 */     return UnmodifiableList.decorate(this.insertOrder);
/*     */   }
/*     */   
/*     */   static class ValuesView
/*     */     extends AbstractCollection
/*     */   {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     ValuesView(ListOrderedMap parent) {
/* 344 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 348 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 352 */       return this.parent.containsValue(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 356 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 360 */       return (Iterator)new Object(this, this.parent.entrySet().iterator());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class KeySetView
/*     */     extends AbstractSet
/*     */   {
/*     */     private final ListOrderedMap parent;
/*     */ 
/*     */ 
/*     */     
/*     */     KeySetView(ListOrderedMap parent) {
/* 374 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 378 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 382 */       return this.parent.containsKey(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 386 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 390 */       return (Iterator)new Object(this, this.parent.entrySet().iterator());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class EntrySetView
/*     */     extends AbstractSet
/*     */   {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     private final List insertOrder;
/*     */     
/*     */     private Set entrySet;
/*     */ 
/*     */     
/*     */     public EntrySetView(ListOrderedMap parent, List insertOrder) {
/* 406 */       this.parent = parent;
/* 407 */       this.insertOrder = insertOrder;
/*     */     }
/*     */     
/*     */     private Set getEntrySet() {
/* 411 */       if (this.entrySet == null) {
/* 412 */         this.entrySet = this.parent.getMap().entrySet();
/*     */       }
/* 414 */       return this.entrySet;
/*     */     }
/*     */     
/*     */     public int size() {
/* 418 */       return this.parent.size();
/*     */     }
/*     */     public boolean isEmpty() {
/* 421 */       return this.parent.isEmpty();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 425 */       return getEntrySet().contains(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection coll) {
/* 429 */       return getEntrySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 433 */       if (!(obj instanceof Map.Entry)) {
/* 434 */         return false;
/*     */       }
/* 436 */       if (getEntrySet().contains(obj)) {
/* 437 */         Object key = ((Map.Entry)obj).getKey();
/* 438 */         this.parent.remove(key);
/* 439 */         return true;
/*     */       } 
/* 441 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 445 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 449 */       if (obj == this) {
/* 450 */         return true;
/*     */       }
/* 452 */       return getEntrySet().equals(obj);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 456 */       return getEntrySet().hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 460 */       return getEntrySet().toString();
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 464 */       return (Iterator)new ListOrderedMap.ListOrderedIterator(this.parent, this.insertOrder);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedIterator
/*     */     extends AbstractIteratorDecorator {
/*     */     private final ListOrderedMap parent;
/* 471 */     private Object last = null;
/*     */     
/*     */     ListOrderedIterator(ListOrderedMap parent, List insertOrder) {
/* 474 */       super(insertOrder.iterator());
/* 475 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 479 */       this.last = super.next();
/* 480 */       return new ListOrderedMap.ListOrderedMapEntry(this.parent, this.last);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 484 */       super.remove();
/* 485 */       this.parent.getMap().remove(this.last);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapEntry
/*     */     extends AbstractMapEntry {
/*     */     private final ListOrderedMap parent;
/*     */     
/*     */     ListOrderedMapEntry(ListOrderedMap parent, Object key) {
/* 494 */       super(key, null);
/* 495 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 499 */       return this.parent.get(((AbstractKeyValue)this).key);
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 503 */       return this.parent.getMap().put(((AbstractKeyValue)this).key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapIterator
/*     */     implements OrderedMapIterator, ResettableIterator {
/*     */     private final ListOrderedMap parent;
/*     */     private ListIterator iterator;
/* 511 */     private Object last = null;
/*     */     
/*     */     private boolean readable = false;
/*     */     
/*     */     ListOrderedMapIterator(ListOrderedMap parent) {
/* 516 */       this.parent = parent;
/* 517 */       this.iterator = parent.insertOrder.listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 521 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 525 */       this.last = this.iterator.next();
/* 526 */       this.readable = true;
/* 527 */       return this.last;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 531 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 535 */       this.last = this.iterator.previous();
/* 536 */       this.readable = true;
/* 537 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 541 */       if (!this.readable) {
/* 542 */         throw new IllegalStateException("remove() can only be called once after next()");
/*     */       }
/* 544 */       this.iterator.remove();
/* 545 */       this.parent.map.remove(this.last);
/* 546 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 550 */       if (!this.readable) {
/* 551 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 553 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 557 */       if (!this.readable) {
/* 558 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 560 */       return this.parent.get(this.last);
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 564 */       if (!this.readable) {
/* 565 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 567 */       return this.parent.map.put(this.last, value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 571 */       this.iterator = this.parent.insertOrder.listIterator();
/* 572 */       this.last = null;
/* 573 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 577 */       if (this.readable == true) {
/* 578 */         return "Iterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 580 */       return "Iterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/ListOrderedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */