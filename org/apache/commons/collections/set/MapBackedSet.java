/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MapBackedSet
/*     */   implements Serializable, Set
/*     */ {
/*     */   private static final long serialVersionUID = 6723912213766056587L;
/*     */   protected final Map map;
/*     */   protected final Object dummyValue;
/*     */   
/*     */   public static Set decorate(Map map) {
/*  56 */     return decorate(map, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set decorate(Map map, Object dummyValue) {
/*  67 */     if (map == null) {
/*  68 */       throw new IllegalArgumentException("The map must not be null");
/*     */     }
/*  70 */     return new MapBackedSet(map, dummyValue);
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
/*     */   private MapBackedSet(Map map, Object dummyValue) {
/*  83 */     this.map = map;
/*  84 */     this.dummyValue = dummyValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  89 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  93 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  97 */     return this.map.keySet().iterator();
/*     */   }
/*     */   
/*     */   public boolean contains(Object obj) {
/* 101 */     return this.map.containsKey(obj);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 105 */     return this.map.keySet().containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 109 */     int size = this.map.size();
/* 110 */     this.map.put(obj, this.dummyValue);
/* 111 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 115 */     int size = this.map.size();
/* 116 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 117 */       Object obj = it.next();
/* 118 */       this.map.put(obj, this.dummyValue);
/*     */     } 
/* 120 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 124 */     int size = this.map.size();
/* 125 */     this.map.remove(obj);
/* 126 */     return (this.map.size() != size);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 130 */     return this.map.keySet().removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 134 */     return this.map.keySet().retainAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 138 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 142 */     return this.map.keySet().toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 146 */     return this.map.keySet().toArray(array);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 150 */     return this.map.keySet().equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 154 */     return this.map.keySet().hashCode();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/MapBackedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */