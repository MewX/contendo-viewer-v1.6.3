/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.ByteCollection;
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
/*     */ abstract class AbstractByteCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  32 */     return getByteCollection().add(((Number)element).byteValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  36 */     return getByteCollection().addAll(CollectionByteCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  40 */     getByteCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getByteCollection().contains(((Number)element).byteValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  49 */     return getByteCollection().containsAll(CollectionByteCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getByteCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getByteCollection().isEmpty();
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
/*  68 */     return ByteIteratorIterator.wrap(getByteCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  72 */     return getByteCollection().removeElement(((Number)element).byteValue());
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  76 */     return getByteCollection().removeAll(CollectionByteCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  80 */     return getByteCollection().retainAll(CollectionByteCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getByteCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  88 */     byte[] a = getByteCollection().toArray();
/*  89 */     Object[] A = new Object[a.length];
/*  90 */     for (int i = 0; i < a.length; i++) {
/*  91 */       A[i] = new Byte(a[i]);
/*     */     }
/*  93 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/*  97 */     byte[] a = getByteCollection().toArray();
/*  98 */     if (A.length < a.length) {
/*  99 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 101 */     for (int i = 0; i < a.length; i++) {
/* 102 */       A[i] = new Byte(a[i]);
/*     */     }
/* 104 */     if (A.length > a.length) {
/* 105 */       A[a.length] = null;
/*     */     }
/*     */     
/* 108 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract ByteCollection getByteCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractByteCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */