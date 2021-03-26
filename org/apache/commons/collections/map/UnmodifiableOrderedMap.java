/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableOrderedMapIterator;
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
/*     */ public final class UnmodifiableOrderedMap
/*     */   extends AbstractOrderedMapDecorator
/*     */   implements Serializable, Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = 8136428161720526266L;
/*     */   
/*     */   public static OrderedMap decorate(OrderedMap map) {
/*  59 */     if (map instanceof Unmodifiable) {
/*  60 */       return map;
/*     */     }
/*  62 */     return new UnmodifiableOrderedMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableOrderedMap(OrderedMap map) {
/*  73 */     super(map);
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
/*  85 */     out.defaultWriteObject();
/*  86 */     out.writeObject(this.map);
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
/*  98 */     in.defaultReadObject();
/*  99 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public MapIterator mapIterator() {
/* 104 */     MapIterator it = getOrderedMap().mapIterator();
/* 105 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 109 */     OrderedMapIterator it = getOrderedMap().orderedMapIterator();
/* 110 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 114 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 130 */     Set set = super.entrySet();
/* 131 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 135 */     Set set = super.keySet();
/* 136 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 140 */     Collection coll = super.values();
/* 141 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/UnmodifiableOrderedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */