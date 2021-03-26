/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.LongCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractLongCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  32 */     return getLongCollection().add(((Number)element).longValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  36 */     return getLongCollection().addAll(CollectionLongCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  40 */     getLongCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getLongCollection().contains(((Number)element).longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  49 */     return getLongCollection().containsAll(CollectionLongCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getLongCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getLongCollection().isEmpty();
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
/*  68 */     return LongIteratorIterator.wrap(getLongCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  72 */     return getLongCollection().removeElement(((Number)element).longValue());
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  76 */     return getLongCollection().removeAll(CollectionLongCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  80 */     return getLongCollection().retainAll(CollectionLongCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getLongCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  88 */     long[] a = getLongCollection().toArray();
/*  89 */     Object[] A = new Object[a.length];
/*  90 */     for (int i = 0; i < a.length; i++) {
/*  91 */       A[i] = new Long(a[i]);
/*     */     }
/*  93 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/*  97 */     long[] a = getLongCollection().toArray();
/*  98 */     if (A.length < a.length) {
/*  99 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 101 */     for (int i = 0; i < a.length; i++) {
/* 102 */       A[i] = new Long(a[i]);
/*     */     }
/* 104 */     if (A.length > a.length) {
/* 105 */       A[a.length] = null;
/*     */     }
/*     */     
/* 108 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract LongCollection getLongCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractLongCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */