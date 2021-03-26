/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.DoubleCollection;
/*     */ import org.apache.commons.collections.primitives.DoubleList;
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
/*     */ abstract class AbstractDoubleListList
/*     */   extends AbstractDoubleCollectionCollection
/*     */   implements List
/*     */ {
/*     */   public void add(int index, Object element) {
/*  34 */     getDoubleList().add(index, ((Number)element).doubleValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  38 */     return getDoubleList().addAll(index, CollectionDoubleCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  42 */     return new Double(getDoubleList().get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object element) {
/*  46 */     return getDoubleList().indexOf(((Number)element).doubleValue());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object element) {
/*  50 */     return getDoubleList().lastIndexOf(((Number)element).doubleValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator listIterator() {
/*  61 */     return DoubleListIteratorListIterator.wrap(getDoubleList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator listIterator(int index) {
/*  72 */     return DoubleListIteratorListIterator.wrap(getDoubleList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  76 */     return new Double(getDoubleList().removeElementAt(index));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/*  80 */     return new Double(getDoubleList().set(index, ((Number)element).doubleValue()));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  84 */     return DoubleListList.wrap(getDoubleList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  88 */     if (obj instanceof List) {
/*  89 */       List that = (List)obj;
/*  90 */       if (this == that)
/*  91 */         return true; 
/*  92 */       if (size() != that.size()) {
/*  93 */         return false;
/*     */       }
/*  95 */       Iterator thisiter = iterator();
/*  96 */       Iterator thatiter = that.iterator();
/*  97 */       while (thisiter.hasNext()) {
/*  98 */         Object thiselt = thisiter.next();
/*  99 */         Object thatelt = thatiter.next();
/* 100 */         if ((null == thiselt) ? (null != thatelt) : !thiselt.equals(thatelt)) {
/* 101 */           return false;
/*     */         }
/*     */       } 
/* 104 */       return true;
/*     */     } 
/*     */     
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     return getDoubleList().hashCode();
/*     */   }
/*     */   
/*     */   protected final DoubleCollection getDoubleCollection() {
/* 116 */     return (DoubleCollection)getDoubleList();
/*     */   }
/*     */   
/*     */   protected abstract DoubleList getDoubleList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractDoubleListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */