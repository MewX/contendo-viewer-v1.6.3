/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.LongCollection;
/*     */ import org.apache.commons.collections.primitives.LongList;
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
/*     */ abstract class AbstractLongListList
/*     */   extends AbstractLongCollectionCollection
/*     */   implements List
/*     */ {
/*     */   public void add(int index, Object element) {
/*  34 */     getLongList().add(index, ((Number)element).longValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  38 */     return getLongList().addAll(index, CollectionLongCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  42 */     return new Long(getLongList().get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object element) {
/*  46 */     return getLongList().indexOf(((Number)element).longValue());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object element) {
/*  50 */     return getLongList().lastIndexOf(((Number)element).longValue());
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
/*  61 */     return LongListIteratorListIterator.wrap(getLongList().listIterator());
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
/*  72 */     return LongListIteratorListIterator.wrap(getLongList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  76 */     return new Long(getLongList().removeElementAt(index));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/*  80 */     return new Long(getLongList().set(index, ((Number)element).longValue()));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  84 */     return LongListList.wrap(getLongList().subList(fromIndex, toIndex));
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
/* 112 */     return getLongList().hashCode();
/*     */   }
/*     */   
/*     */   protected final LongCollection getLongCollection() {
/* 116 */     return (LongCollection)getLongList();
/*     */   }
/*     */   
/*     */   protected abstract LongList getLongList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractLongListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */