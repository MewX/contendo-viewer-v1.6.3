/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.SortedBidiMap;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections.map.UnmodifiableEntrySet;
/*     */ import org.apache.commons.collections.map.UnmodifiableSortedMap;
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
/*     */ public final class UnmodifiableSortedBidiMap
/*     */   extends AbstractSortedBidiMapDecorator
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableSortedBidiMap inverse;
/*     */   
/*     */   public static SortedBidiMap decorate(SortedBidiMap map) {
/*  59 */     if (map instanceof Unmodifiable) {
/*  60 */       return map;
/*     */     }
/*  62 */     return new UnmodifiableSortedBidiMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableSortedBidiMap(SortedBidiMap map) {
/*  73 */     super(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  94 */     Set set = super.entrySet();
/*  95 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  99 */     Set set = super.keySet();
/* 100 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 104 */     Collection coll = super.values();
/* 105 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object removeValue(Object value) {
/* 110 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 114 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 118 */     return (BidiMap)inverseSortedBidiMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 123 */     OrderedMapIterator it = getSortedBidiMap().orderedMapIterator();
/* 124 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 128 */     return (OrderedBidiMap)inverseSortedBidiMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedBidiMap inverseSortedBidiMap() {
/* 133 */     if (this.inverse == null) {
/* 134 */       this.inverse = new UnmodifiableSortedBidiMap(getSortedBidiMap().inverseSortedBidiMap());
/* 135 */       this.inverse.inverse = this;
/*     */     } 
/* 137 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 141 */     SortedMap sm = getSortedBidiMap().subMap(fromKey, toKey);
/* 142 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 146 */     SortedMap sm = getSortedBidiMap().headMap(toKey);
/* 147 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 151 */     SortedMap sm = getSortedBidiMap().tailMap(fromKey);
/* 152 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/UnmodifiableSortedBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */