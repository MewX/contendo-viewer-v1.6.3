/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections.keyvalue.TiedMapEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingletonMap
/*     */   implements Serializable, Cloneable, BoundedMap, KeyValue, OrderedMap
/*     */ {
/*     */   private static final long serialVersionUID = -8931271118676803261L;
/*     */   private final Object key;
/*     */   private Object value;
/*     */   
/*     */   public SingletonMap() {
/*  77 */     this.key = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SingletonMap(Object key, Object value) {
/*  88 */     this.key = key;
/*  89 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SingletonMap(KeyValue keyValue) {
/*  99 */     this.key = keyValue.getKey();
/* 100 */     this.value = keyValue.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SingletonMap(Map.Entry entry) {
/* 110 */     this.key = entry.getKey();
/* 111 */     this.value = entry.getValue();
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
/*     */   public SingletonMap(Map map) {
/* 123 */     if (map.size() != 1) {
/* 124 */       throw new IllegalArgumentException("The map size must be 1");
/*     */     }
/* 126 */     Map.Entry entry = map.entrySet().iterator().next();
/* 127 */     this.key = entry.getKey();
/* 128 */     this.value = entry.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getKey() {
/* 139 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 148 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object setValue(Object value) {
/* 158 */     Object old = this.value;
/* 159 */     this.value = value;
/* 160 */     return old;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxSize() {
/* 180 */     return 1;
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
/*     */   public Object get(Object key) {
/* 192 */     if (isEqualKey(key)) {
/* 193 */       return this.value;
/*     */     }
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 204 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 224 */     return isEqualKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 234 */     return isEqualValue(value);
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
/* 250 */     if (isEqualKey(key)) {
/* 251 */       return setValue(value);
/*     */     }
/* 253 */     throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
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
/*     */   public void putAll(Map map) {
/*     */     Map.Entry entry;
/* 268 */     switch (map.size()) {
/*     */       case 0:
/*     */         return;
/*     */       
/*     */       case 1:
/* 273 */         entry = map.entrySet().iterator().next();
/* 274 */         put(entry.getKey(), entry.getValue());
/*     */         return;
/*     */     } 
/*     */     
/* 278 */     throw new IllegalArgumentException("The map size must be 0 or 1");
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
/* 290 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 297 */     throw new UnsupportedOperationException();
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
/*     */   public Set entrySet() {
/* 309 */     TiedMapEntry tiedMapEntry = new TiedMapEntry((Map)this, getKey());
/* 310 */     return Collections.singleton(tiedMapEntry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set keySet() {
/* 321 */     return Collections.singleton(this.key);
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
/* 332 */     return new SingletonValues(this);
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
/*     */   public MapIterator mapIterator() {
/* 348 */     return (MapIterator)new SingletonMapIterator(this);
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
/* 362 */     return new SingletonMapIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 371 */     return getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object lastKey() {
/* 380 */     return getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object nextKey(Object key) {
/* 390 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object previousKey(Object key) {
/* 400 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEqualKey(Object key) {
/* 411 */     return (key == null) ? ((getKey() == null)) : key.equals(getKey());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEqualValue(Object value) {
/* 421 */     return (value == null) ? ((getValue() == null)) : value.equals(getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   static class SingletonMapIterator
/*     */     implements OrderedMapIterator, ResettableIterator
/*     */   {
/*     */     private final SingletonMap parent;
/*     */     
/*     */     private boolean hasNext = true;
/*     */     
/*     */     private boolean canGetSet = false;
/*     */     
/*     */     SingletonMapIterator(SingletonMap parent) {
/* 435 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 439 */       return this.hasNext;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 443 */       if (!this.hasNext) {
/* 444 */         throw new NoSuchElementException("No next() entry in the iteration");
/*     */       }
/* 446 */       this.hasNext = false;
/* 447 */       this.canGetSet = true;
/* 448 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 452 */       return !this.hasNext;
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 456 */       if (this.hasNext == true) {
/* 457 */         throw new NoSuchElementException("No previous() entry in the iteration");
/*     */       }
/* 459 */       this.hasNext = true;
/* 460 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 464 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 468 */       if (!this.canGetSet) {
/* 469 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 471 */       return this.parent.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 475 */       if (!this.canGetSet) {
/* 476 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 478 */       return this.parent.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 482 */       if (!this.canGetSet) {
/* 483 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 485 */       return this.parent.setValue(value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 489 */       this.hasNext = true;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 493 */       if (this.hasNext) {
/* 494 */         return "Iterator[]";
/*     */       }
/* 496 */       return "Iterator[" + getKey() + "=" + getValue() + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class SingletonValues
/*     */     extends AbstractSet
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = -3689524741863047872L;
/*     */     
/*     */     private final SingletonMap parent;
/*     */ 
/*     */     
/*     */     SingletonValues(SingletonMap parent) {
/* 511 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 515 */       return 1;
/*     */     }
/*     */     public boolean isEmpty() {
/* 518 */       return false;
/*     */     }
/*     */     public boolean contains(Object object) {
/* 521 */       return this.parent.containsValue(object);
/*     */     }
/*     */     public void clear() {
/* 524 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public Iterator iterator() {
/* 527 */       return (Iterator)new SingletonIterator(this.parent.getValue(), false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 539 */       SingletonMap cloned = (SingletonMap)super.clone();
/* 540 */       return cloned;
/* 541 */     } catch (CloneNotSupportedException ex) {
/* 542 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 553 */     if (obj == this) {
/* 554 */       return true;
/*     */     }
/* 556 */     if (!(obj instanceof Map)) {
/* 557 */       return false;
/*     */     }
/* 559 */     Map other = (Map)obj;
/* 560 */     if (other.size() != 1) {
/* 561 */       return false;
/*     */     }
/* 563 */     Map.Entry entry = other.entrySet().iterator().next();
/* 564 */     return (isEqualKey(entry.getKey()) && isEqualValue(entry.getValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 573 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 583 */     return (new StringBuffer(128)).append('{').append((getKey() == this) ? "(this Map)" : getKey()).append('=').append((getValue() == this) ? "(this Map)" : getValue()).append('}').toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/SingletonMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */