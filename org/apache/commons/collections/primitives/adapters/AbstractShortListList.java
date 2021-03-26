/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.ShortCollection;
/*     */ import org.apache.commons.collections.primitives.ShortList;
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
/*     */ abstract class AbstractShortListList
/*     */   extends AbstractShortCollectionCollection
/*     */   implements List
/*     */ {
/*     */   public void add(int index, Object element) {
/*  34 */     getShortList().add(index, ((Number)element).shortValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  38 */     return getShortList().addAll(index, CollectionShortCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  42 */     return new Short(getShortList().get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object element) {
/*  46 */     return getShortList().indexOf(((Number)element).shortValue());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object element) {
/*  50 */     return getShortList().lastIndexOf(((Number)element).shortValue());
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
/*  61 */     return ShortListIteratorListIterator.wrap(getShortList().listIterator());
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
/*  72 */     return ShortListIteratorListIterator.wrap(getShortList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  76 */     return new Short(getShortList().removeElementAt(index));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/*  80 */     return new Short(getShortList().set(index, ((Number)element).shortValue()));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  84 */     return ShortListList.wrap(getShortList().subList(fromIndex, toIndex));
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
/* 112 */     return getShortList().hashCode();
/*     */   }
/*     */   
/*     */   protected final ShortCollection getShortCollection() {
/* 116 */     return (ShortCollection)getShortList();
/*     */   }
/*     */   
/*     */   protected abstract ShortList getShortList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractShortListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */