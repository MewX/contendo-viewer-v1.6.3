/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.primitives.CharCollection;
/*     */ import org.apache.commons.collections.primitives.CharList;
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
/*     */ abstract class AbstractCharListList
/*     */   extends AbstractCharCollectionCollection
/*     */   implements List
/*     */ {
/*     */   public void add(int index, Object element) {
/*  34 */     getCharList().add(index, ((Character)element).charValue());
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  38 */     return getCharList().addAll(index, CollectionCharCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  42 */     return new Character(getCharList().get(index));
/*     */   }
/*     */   
/*     */   public int indexOf(Object element) {
/*  46 */     return getCharList().indexOf(((Character)element).charValue());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object element) {
/*  50 */     return getCharList().lastIndexOf(((Character)element).charValue());
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
/*  61 */     return CharListIteratorListIterator.wrap(getCharList().listIterator());
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
/*  72 */     return CharListIteratorListIterator.wrap(getCharList().listIterator(index));
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  76 */     return new Character(getCharList().removeElementAt(index));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/*  80 */     return new Character(getCharList().set(index, ((Character)element).charValue()));
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  84 */     return CharListList.wrap(getCharList().subList(fromIndex, toIndex));
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
/* 112 */     return getCharList().hashCode();
/*     */   }
/*     */   
/*     */   protected final CharCollection getCharCollection() {
/* 116 */     return (CharCollection)getCharList();
/*     */   }
/*     */   
/*     */   protected abstract CharList getCharList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCharListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */