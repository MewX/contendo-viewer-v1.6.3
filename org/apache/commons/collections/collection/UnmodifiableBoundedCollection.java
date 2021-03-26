/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableBoundedCollection
/*     */   extends AbstractSerializableCollectionDecorator
/*     */   implements BoundedCollection
/*     */ {
/*     */   private static final long serialVersionUID = -7112672385450340330L;
/*     */   
/*     */   public static BoundedCollection decorate(BoundedCollection coll) {
/*  56 */     return new UnmodifiableBoundedCollection(coll);
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
/*     */   public static BoundedCollection decorateUsing(Collection coll) {
/*  70 */     if (coll == null) {
/*  71 */       throw new IllegalArgumentException("The collection must not be null");
/*     */     }
/*     */ 
/*     */     
/*  75 */     for (int i = 0; i < 1000 && 
/*  76 */       !(coll instanceof BoundedCollection); i++) {
/*     */       
/*  78 */       if (coll instanceof AbstractCollectionDecorator) {
/*  79 */         coll = ((AbstractCollectionDecorator)coll).collection;
/*  80 */       } else if (coll instanceof SynchronizedCollection) {
/*  81 */         coll = ((SynchronizedCollection)coll).collection;
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     if (!(coll instanceof BoundedCollection)) {
/*  88 */       throw new IllegalArgumentException("The collection is not a bounded collection");
/*     */     }
/*  90 */     return new UnmodifiableBoundedCollection((BoundedCollection)coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableBoundedCollection(BoundedCollection coll) {
/* 100 */     super((Collection)coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 105 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 129 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 134 */     return ((BoundedCollection)this.collection).isFull();
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 138 */     return ((BoundedCollection)this.collection).maxSize();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/UnmodifiableBoundedCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */