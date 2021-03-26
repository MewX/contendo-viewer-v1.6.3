/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections.Unmodifiable;
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
/*     */ public final class UnmodifiableSortedMap
/*     */   extends AbstractSortedMapDecorator
/*     */   implements Serializable, Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = 5805344239827376360L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map) {
/*  56 */     if (map instanceof Unmodifiable) {
/*  57 */       return map;
/*     */     }
/*  59 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableSortedMap(SortedMap map) {
/*  70 */     super(map);
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
/*  82 */     out.defaultWriteObject();
/*  83 */     out.writeObject(this.map);
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
/*  95 */     in.defaultReadObject();
/*  96 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 117 */     Set set = super.entrySet();
/* 118 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 122 */     Set set = super.keySet();
/* 123 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 127 */     Collection coll = super.values();
/* 128 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object firstKey() {
/* 133 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 137 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 141 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 145 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 146 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 150 */     SortedMap map = getSortedMap().headMap(toKey);
/* 151 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 155 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 156 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/UnmodifiableSortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */