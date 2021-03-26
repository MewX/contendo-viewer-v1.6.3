/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.SortedBidiMap;
/*     */ import org.apache.commons.collections.map.AbstractMapDecorator;
/*     */ import org.apache.commons.collections.map.AbstractSortedMapDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualTreeBidiMap
/*     */   extends AbstractDualBidiMap
/*     */   implements Serializable, SortedBidiMap
/*     */ {
/*     */   private static final long serialVersionUID = 721969328361809L;
/*     */   protected final Comparator comparator;
/*     */   
/*     */   public DualTreeBidiMap() {
/*  69 */     super(new TreeMap(), new TreeMap());
/*  70 */     this.comparator = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DualTreeBidiMap(Map map) {
/*  80 */     super(new TreeMap(), new TreeMap());
/*  81 */     putAll(map);
/*  82 */     this.comparator = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DualTreeBidiMap(Comparator comparator) {
/*  91 */     super(new TreeMap(comparator), new TreeMap(comparator));
/*  92 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DualTreeBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/* 103 */     super(normalMap, reverseMap, inverseBidiMap);
/* 104 */     this.comparator = ((SortedMap)normalMap).comparator();
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
/*     */   protected BidiMap createBidiMap(Map normalMap, Map reverseMap, BidiMap inverseMap) {
/* 116 */     return new DualTreeBidiMap(normalMap, reverseMap, inverseMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparator comparator() {
/* 121 */     return ((SortedMap)this.maps[0]).comparator();
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 125 */     return ((SortedMap)this.maps[0]).firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 129 */     return ((SortedMap)this.maps[0]).lastKey();
/*     */   }
/*     */   
/*     */   public Object nextKey(Object key) {
/* 133 */     if (isEmpty()) {
/* 134 */       return null;
/*     */     }
/* 136 */     if (this.maps[0] instanceof OrderedMap) {
/* 137 */       return ((OrderedMap)this.maps[0]).nextKey(key);
/*     */     }
/* 139 */     SortedMap sm = (SortedMap)this.maps[0];
/* 140 */     Iterator it = sm.tailMap(key).keySet().iterator();
/* 141 */     it.next();
/* 142 */     if (it.hasNext()) {
/* 143 */       return it.next();
/*     */     }
/* 145 */     return null;
/*     */   }
/*     */   
/*     */   public Object previousKey(Object key) {
/* 149 */     if (isEmpty()) {
/* 150 */       return null;
/*     */     }
/* 152 */     if (this.maps[0] instanceof OrderedMap) {
/* 153 */       return ((OrderedMap)this.maps[0]).previousKey(key);
/*     */     }
/* 155 */     SortedMap sm = (SortedMap)this.maps[0];
/* 156 */     SortedMap hm = sm.headMap(key);
/* 157 */     if (hm.isEmpty()) {
/* 158 */       return null;
/*     */     }
/* 160 */     return hm.lastKey();
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
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 173 */     return new BidiOrderedMapIterator(this);
/*     */   }
/*     */   
/*     */   public SortedBidiMap inverseSortedBidiMap() {
/* 177 */     return (SortedBidiMap)inverseBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 181 */     return (OrderedBidiMap)inverseBidiMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 186 */     SortedMap sub = ((SortedMap)this.maps[0]).headMap(toKey);
/* 187 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 191 */     SortedMap sub = ((SortedMap)this.maps[0]).tailMap(fromKey);
/* 192 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 196 */     SortedMap sub = ((SortedMap)this.maps[0]).subMap(fromKey, toKey);
/* 197 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ViewMap
/*     */     extends AbstractSortedMapDecorator
/*     */   {
/*     */     final DualTreeBidiMap bidi;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ViewMap(DualTreeBidiMap bidi, SortedMap sm) {
/* 217 */       super((SortedMap)bidi.createBidiMap(sm, bidi.maps[1], bidi.inverseBidiMap));
/* 218 */       this.bidi = (DualTreeBidiMap)((AbstractMapDecorator)this).map;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 223 */       return this.bidi.maps[0].containsValue(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 228 */       for (Iterator it = keySet().iterator(); it.hasNext(); ) {
/* 229 */         it.next();
/* 230 */         it.remove();
/*     */       } 
/*     */     }
/*     */     
/*     */     public SortedMap headMap(Object toKey) {
/* 235 */       return (SortedMap)new ViewMap(this.bidi, super.headMap(toKey));
/*     */     }
/*     */     
/*     */     public SortedMap tailMap(Object fromKey) {
/* 239 */       return (SortedMap)new ViewMap(this.bidi, super.tailMap(fromKey));
/*     */     }
/*     */     
/*     */     public SortedMap subMap(Object fromKey, Object toKey) {
/* 243 */       return (SortedMap)new ViewMap(this.bidi, super.subMap(fromKey, toKey));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class BidiOrderedMapIterator
/*     */     implements OrderedMapIterator, ResettableIterator
/*     */   {
/*     */     protected final AbstractDualBidiMap parent;
/*     */ 
/*     */     
/*     */     protected ListIterator iterator;
/*     */ 
/*     */     
/* 258 */     private Map.Entry last = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected BidiOrderedMapIterator(AbstractDualBidiMap parent) {
/* 266 */       this.parent = parent;
/* 267 */       this.iterator = (new ArrayList(parent.entrySet())).listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 271 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 275 */       this.last = this.iterator.next();
/* 276 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 280 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 284 */       this.last = this.iterator.previous();
/* 285 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 289 */       this.iterator.remove();
/* 290 */       this.parent.remove(this.last.getKey());
/* 291 */       this.last = null;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 295 */       if (this.last == null) {
/* 296 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */       }
/* 298 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 302 */       if (this.last == null) {
/* 303 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */       }
/* 305 */       return this.last.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 309 */       if (this.last == null) {
/* 310 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */       }
/* 312 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != this.last.getKey())
/*     */       {
/* 314 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 316 */       return this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 320 */       this.iterator = (new ArrayList(this.parent.entrySet())).listIterator();
/* 321 */       this.last = null;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 325 */       if (this.last != null) {
/* 326 */         return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 328 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 336 */     out.defaultWriteObject();
/* 337 */     out.writeObject(this.maps[0]);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 341 */     in.defaultReadObject();
/* 342 */     this.maps[0] = new TreeMap(this.comparator);
/* 343 */     this.maps[1] = new TreeMap(this.comparator);
/* 344 */     Map map = (Map)in.readObject();
/* 345 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/DualTreeBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */