/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.IterableMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.EntrySetMapIterator;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
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
/*     */ public final class UnmodifiableMap
/*     */   extends AbstractMapDecorator
/*     */   implements Serializable, IterableMap, Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = 2737023427269031941L;
/*     */   
/*     */   public static Map decorate(Map map) {
/*  58 */     if (map instanceof Unmodifiable) {
/*  59 */       return map;
/*     */     }
/*  61 */     return new UnmodifiableMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableMap(Map map) {
/*  72 */     super(map);
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
/*  84 */     out.defaultWriteObject();
/*  85 */     out.writeObject(this.map);
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
/*  97 */     in.defaultReadObject();
/*  98 */     this.map = (Map)in.readObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 103 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 119 */     if (this.map instanceof IterableMap) {
/* 120 */       MapIterator it = ((IterableMap)this.map).mapIterator();
/* 121 */       return UnmodifiableMapIterator.decorate(it);
/*     */     } 
/* 123 */     EntrySetMapIterator entrySetMapIterator = new EntrySetMapIterator(this.map);
/* 124 */     return UnmodifiableMapIterator.decorate((MapIterator)entrySetMapIterator);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/* 129 */     Set set = super.entrySet();
/* 130 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 134 */     Set set = super.keySet();
/* 135 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 139 */     Collection coll = super.values();
/* 140 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/UnmodifiableMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */