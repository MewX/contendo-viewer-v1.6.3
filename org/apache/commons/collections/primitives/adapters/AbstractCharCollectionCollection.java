/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.CharCollection;
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
/*     */ abstract class AbstractCharCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  32 */     return getCharCollection().add(((Character)element).charValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  36 */     return getCharCollection().addAll(CollectionCharCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  40 */     getCharCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getCharCollection().contains(((Character)element).charValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  49 */     return getCharCollection().containsAll(CollectionCharCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getCharCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getCharCollection().isEmpty();
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
/*  68 */     return CharIteratorIterator.wrap(getCharCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  72 */     return getCharCollection().removeElement(((Character)element).charValue());
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  76 */     return getCharCollection().removeAll(CollectionCharCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  80 */     return getCharCollection().retainAll(CollectionCharCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCharCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  88 */     char[] a = getCharCollection().toArray();
/*  89 */     Object[] A = new Object[a.length];
/*  90 */     for (int i = 0; i < a.length; i++) {
/*  91 */       A[i] = new Character(a[i]);
/*     */     }
/*  93 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/*  97 */     char[] a = getCharCollection().toArray();
/*  98 */     if (A.length < a.length) {
/*  99 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 101 */     for (int i = 0; i < a.length; i++) {
/* 102 */       A[i] = new Character(a[i]);
/*     */     }
/* 104 */     if (A.length > a.length) {
/* 105 */       A[a.length] = null;
/*     */     }
/*     */     
/* 108 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract CharCollection getCharCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCharCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */