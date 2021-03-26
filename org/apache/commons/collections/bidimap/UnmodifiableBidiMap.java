/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableMapIterator;
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
/*     */ public final class UnmodifiableBidiMap
/*     */   extends AbstractBidiMapDecorator
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableBidiMap inverse;
/*     */   
/*     */   public static BidiMap decorate(BidiMap map) {
/*  54 */     if (map instanceof Unmodifiable) {
/*  55 */       return map;
/*     */     }
/*  57 */     return new UnmodifiableBidiMap(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableBidiMap(BidiMap map) {
/*  68 */     super(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  73 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  89 */     Set set = super.entrySet();
/*  90 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  94 */     Set set = super.keySet();
/*  95 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection values() {
/*  99 */     Collection coll = super.values();
/* 100 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object removeValue(Object value) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 109 */     MapIterator it = getBidiMap().mapIterator();
/* 110 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public BidiMap inverseBidiMap() {
/* 114 */     if (this.inverse == null) {
/* 115 */       this.inverse = new UnmodifiableBidiMap(getBidiMap().inverseBidiMap());
/* 116 */       this.inverse.inverse = this;
/*     */     } 
/* 118 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/UnmodifiableBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */