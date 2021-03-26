/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections.map.UnmodifiableEntrySet;
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
/*     */ public final class UnmodifiableOrderedBidiMap
/*     */   extends AbstractOrderedBidiMapDecorator
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableOrderedBidiMap inverse;
/*     */   
/*     */   public static OrderedBidiMap decorate(OrderedBidiMap map) {
/*  56 */     if (map instanceof Unmodifiable) {
/*  57 */       return map;
/*     */     }
/*  59 */     return new UnmodifiableOrderedBidiMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableOrderedBidiMap(OrderedBidiMap map) {
/*  70 */     super(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  75 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  87 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  91 */     Set set = super.entrySet();
/*  92 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  96 */     Set set = super.keySet();
/*  97 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 101 */     Collection coll = super.values();
/* 102 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object removeValue(Object value) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 111 */     return (MapIterator)orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 115 */     return (BidiMap)inverseOrderedBidiMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 120 */     OrderedMapIterator it = getOrderedBidiMap().orderedMapIterator();
/* 121 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 125 */     if (this.inverse == null) {
/* 126 */       this.inverse = new UnmodifiableOrderedBidiMap(getOrderedBidiMap().inverseOrderedBidiMap());
/* 127 */       this.inverse.inverse = this;
/*     */     } 
/* 129 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/UnmodifiableOrderedBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */