/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractCollectionDecorator
/*     */   implements Collection
/*     */ {
/*     */   protected Collection collection;
/*     */   
/*     */   protected AbstractCollectionDecorator() {}
/*     */   
/*     */   protected AbstractCollectionDecorator(Collection coll) {
/*  62 */     if (coll == null) {
/*  63 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  65 */     this.collection = coll;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Collection getCollection() {
/*  74 */     return this.collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/*  79 */     return this.collection.add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  83 */     return this.collection.addAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  87 */     this.collection.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/*  91 */     return this.collection.contains(object);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  95 */     return this.collection.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  99 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 103 */     return this.collection.remove(object);
/*     */   }
/*     */   
/*     */   public int size() {
/* 107 */     return this.collection.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 111 */     return this.collection.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] object) {
/* 115 */     return this.collection.toArray(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 119 */     return this.collection.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 123 */     return this.collection.removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 127 */     return this.collection.retainAll(coll);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 131 */     if (object == this) {
/* 132 */       return true;
/*     */     }
/* 134 */     return this.collection.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 138 */     return this.collection.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 142 */     return this.collection.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/AbstractCollectionDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */