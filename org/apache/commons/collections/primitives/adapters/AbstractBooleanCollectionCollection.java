/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.primitives.BooleanCollection;
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
/*     */ abstract class AbstractBooleanCollectionCollection
/*     */   implements Collection
/*     */ {
/*     */   public boolean add(Object element) {
/*  31 */     return getBooleanCollection().add(((Boolean)element).booleanValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  35 */     return getBooleanCollection().addAll(CollectionBooleanCollection.wrap(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  40 */     getBooleanCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/*  44 */     return getBooleanCollection().contains(((Boolean)element).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  50 */     return getBooleanCollection().containsAll(CollectionBooleanCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  54 */     return getBooleanCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  58 */     return getBooleanCollection().isEmpty();
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
/*  69 */     return BooleanIteratorIterator.wrap(getBooleanCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/*  73 */     return getBooleanCollection().removeElement(((Boolean)element).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  78 */     return getBooleanCollection().removeAll(CollectionBooleanCollection.wrap(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  83 */     return getBooleanCollection().retainAll(CollectionBooleanCollection.wrap(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  88 */     return getBooleanCollection().size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  92 */     boolean[] a = getBooleanCollection().toArray();
/*  93 */     Object[] A = new Object[a.length];
/*  94 */     for (int i = 0; i < a.length; i++) {
/*  95 */       A[i] = new Boolean(a[i]);
/*     */     }
/*  97 */     return A;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] A) {
/* 101 */     boolean[] a = getBooleanCollection().toArray();
/* 102 */     if (A.length < a.length) {
/* 103 */       A = (Object[])Array.newInstance(A.getClass().getComponentType(), a.length);
/*     */     }
/* 105 */     for (int i = 0; i < a.length; i++) {
/* 106 */       A[i] = new Boolean(a[i]);
/*     */     }
/* 108 */     if (A.length > a.length) {
/* 109 */       A[a.length] = null;
/*     */     }
/*     */     
/* 112 */     return A;
/*     */   }
/*     */   
/*     */   protected abstract BooleanCollection getBooleanCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractBooleanCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */