/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class SynchronizedCollection
/*     */   implements Serializable, Collection
/*     */ {
/*     */   private static final long serialVersionUID = 2412805092710877986L;
/*     */   protected final Collection collection;
/*     */   protected final Object lock;
/*     */   
/*     */   public static Collection decorate(Collection coll) {
/*  59 */     return new SynchronizedCollection(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedCollection(Collection collection) {
/*  70 */     if (collection == null) {
/*  71 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  73 */     this.collection = collection;
/*  74 */     this.lock = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SynchronizedCollection(Collection collection, Object lock) {
/*  85 */     if (collection == null) {
/*  86 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  88 */     this.collection = collection;
/*  89 */     this.lock = lock;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/*  94 */     synchronized (this.lock) {
/*  95 */       return this.collection.add(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 100 */     synchronized (this.lock) {
/* 101 */       return this.collection.addAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 106 */     synchronized (this.lock) {
/* 107 */       this.collection.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 112 */     synchronized (this.lock) {
/* 113 */       return this.collection.contains(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 118 */     synchronized (this.lock) {
/* 119 */       return this.collection.containsAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 124 */     synchronized (this.lock) {
/* 125 */       return this.collection.isEmpty();
/*     */     } 
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
/*     */   
/*     */   public Iterator iterator() {
/* 140 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 144 */     synchronized (this.lock) {
/* 145 */       return this.collection.toArray();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] object) {
/* 150 */     synchronized (this.lock) {
/* 151 */       return this.collection.toArray(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 156 */     synchronized (this.lock) {
/* 157 */       return this.collection.remove(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 162 */     synchronized (this.lock) {
/* 163 */       return this.collection.removeAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 168 */     synchronized (this.lock) {
/* 169 */       return this.collection.retainAll(coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int size() {
/* 174 */     synchronized (this.lock) {
/* 175 */       return this.collection.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 180 */     synchronized (this.lock) {
/* 181 */       if (object == this) {
/* 182 */         return true;
/*     */       }
/* 184 */       return this.collection.equals(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 189 */     synchronized (this.lock) {
/* 190 */       return this.collection.hashCode();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 195 */     synchronized (this.lock) {
/* 196 */       return this.collection.toString();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/SynchronizedCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */