/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.IntCollection;
/*     */ import org.apache.commons.collections.primitives.IntList;
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
/*     */ abstract class AbstractIntListList
/*     */   extends AbstractIntCollectionCollection
/*     */   implements List
/*     */ {
/*     */   public void add(int index, Object element) {
/*  34 */     getIntList().add(index, ((Number)element).intValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  38 */     return getIntList().addAll(index, CollectionIntCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  42 */     return new Integer(getIntList().get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object element) {
/*  46 */     return getIntList().indexOf(((Number)element).intValue());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object element) {
/*  50 */     return getIntList().lastIndexOf(((Number)element).intValue());
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
/*  61 */     return IntListIteratorListIterator.wrap(getIntList().listIterator());
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
/*  72 */     return IntListIteratorListIterator.wrap(getIntList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  76 */     return new Integer(getIntList().removeElementAt(index));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/*  80 */     return new Integer(getIntList().set(index, ((Number)element).intValue()));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  84 */     return IntListList.wrap(getIntList().subList(fromIndex, toIndex));
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
/* 112 */     return getIntList().hashCode();
/*     */   }
/*     */   
/*     */   protected final IntCollection getIntCollection() {
/* 116 */     return (IntCollection)getIntList();
/*     */   }
/*     */   
/*     */   protected abstract IntList getIntList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractIntListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */