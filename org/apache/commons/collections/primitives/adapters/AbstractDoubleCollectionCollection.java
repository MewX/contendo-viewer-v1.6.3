/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.DoubleCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractDoubleCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  32 */     return getDoubleCollection().add(((Number)element).doubleValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  36 */     return getDoubleCollection().addAll(CollectionDoubleCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  40 */     getDoubleCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getDoubleCollection().contains(((Number)element).doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  49 */     return getDoubleCollection().containsAll(CollectionDoubleCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getDoubleCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getDoubleCollection().isEmpty();
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
/*  68 */     return DoubleIteratorIterator.wrap(getDoubleCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  72 */     return getDoubleCollection().removeElement(((Number)element).doubleValue());
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  76 */     return getDoubleCollection().removeAll(CollectionDoubleCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  80 */     return getDoubleCollection().retainAll(CollectionDoubleCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getDoubleCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  88 */     double[] a = getDoubleCollection().toArray();
/*  89 */     Object[] A = new Object[a.length];
/*  90 */     for (int i = 0; i < a.length; i++) {
/*  91 */       A[i] = new Double(a[i]);
/*     */     }
/*  93 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/*  97 */     double[] a = getDoubleCollection().toArray();
/*  98 */     if (A.length < a.length) {
/*  99 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 101 */     for (int i = 0; i < a.length; i++) {
/* 102 */       A[i] = new Double(a[i]);
/*     */     }
/* 104 */     if (A.length > a.length) {
/* 105 */       A[a.length] = null;
/*     */     }
/*     */     
/* 108 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract DoubleCollection getDoubleCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractDoubleCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */