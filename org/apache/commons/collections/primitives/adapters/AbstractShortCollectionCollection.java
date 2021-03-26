/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.ShortCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractShortCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  32 */     return getShortCollection().add(((Number)element).shortValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  36 */     return getShortCollection().addAll(CollectionShortCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  40 */     getShortCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getShortCollection().contains(((Number)element).shortValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  49 */     return getShortCollection().containsAll(CollectionShortCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getShortCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getShortCollection().isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  68 */     return ShortIteratorIterator.wrap(getShortCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  72 */     return getShortCollection().removeElement(((Number)element).shortValue());
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  76 */     return getShortCollection().removeAll(CollectionShortCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  80 */     return getShortCollection().retainAll(CollectionShortCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getShortCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  88 */     short[] a = getShortCollection().toArray();
/*  89 */     Object[] A = new Object[a.length];
/*  90 */     for (int i = 0; i < a.length; i++) {
/*  91 */       A[i] = new Short(a[i]);
/*     */     }
/*  93 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/*  97 */     short[] a = getShortCollection().toArray();
/*  98 */     if (A.length < a.length) {
/*  99 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 101 */     for (int i = 0; i < a.length; i++) {
/* 102 */       A[i] = new Short(a[i]);
/*     */     }
/* 104 */     if (A.length > a.length) {
/* 105 */       A[a.length] = null;
/*     */     }
/*     */     
/* 108 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract ShortCollection getShortCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractShortCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */