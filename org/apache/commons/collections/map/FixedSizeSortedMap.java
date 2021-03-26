/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedSizeSortedMap
/*     */   extends AbstractSortedMapDecorator
/*     */   implements Serializable, SortedMap, BoundedMap
/*     */ {
/*     */   private static final long serialVersionUID = 3126019624511683653L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map) {
/*  67 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FixedSizeSortedMap(SortedMap map) {
/*  78 */     super(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SortedMap getSortedMap() {
/*  87 */     return (SortedMap)this.map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  95 */     out.defaultWriteObject();
/*  96 */     out.writeObject(this.map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 103 */     in.defaultReadObject();
/* 104 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 109 */     if (!this.map.containsKey(key)) {
/* 110 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */     }
/* 112 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 116 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 117 */       if (!mapToCopy.containsKey(it.next())) {
/* 118 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */       }
/*     */     } 
/* 121 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 129 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 133 */     Set set = this.map.entrySet();
/* 134 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 138 */     Set set = this.map.keySet();
/* 139 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 143 */     Collection coll = this.map.values();
/* 144 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 149 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 150 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 154 */     SortedMap map = getSortedMap().headMap(toKey);
/* 155 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 159 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 160 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 164 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 168 */     return size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/FixedSizeSortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */