/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.IntCollection;
/*     */ import org.apache.commons.collections.primitives.IntIterator;
/*     */ import org.apache.commons.collections.primitives.IntList;
/*     */ import org.apache.commons.collections.primitives.IntListIterator;
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
/*     */ abstract class AbstractListIntList
/*     */   extends AbstractCollectionIntCollection
/*     */   implements IntList
/*     */ {
/*     */   public void add(int index, int element) {
/*  35 */     getList().add(index, new Integer(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, IntCollection collection) {
/*  39 */     return getList().addAll(index, IntCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public int get(int index) {
/*  43 */     return ((Number)getList().get(index)).intValue();
/*     */   }
/*     */   
/*     */   public int indexOf(int element) {
/*  47 */     return getList().indexOf(new Integer(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(int element) {
/*  51 */     return getList().lastIndexOf(new Integer(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntListIterator listIterator() {
/*  62 */     return ListIteratorIntListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntListIterator listIterator(int index) {
/*  73 */     return ListIteratorIntListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public int removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).intValue();
/*     */   }
/*     */   
/*     */   public int set(int index, int element) {
/*  81 */     return ((Number)getList().set(index, new Integer(element))).intValue();
/*     */   }
/*     */   
/*     */   public IntList subList(int fromIndex, int toIndex) {
/*  85 */     return ListIntList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof IntList) {
/*  90 */       IntList that = (IntList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       IntIterator thisiter = iterator();
/*  97 */       IntIterator thatiter = that.iterator();
/*  98 */       while (thisiter.hasNext()) {
/*  99 */         if (thisiter.next() != thatiter.next()) {
/* 100 */           return false;
/*     */         }
/*     */       } 
/* 103 */       return true;
/*     */     } 
/*     */     
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 111 */     return getList().hashCode();
/*     */   }
/*     */   
/*     */   protected final Collection getCollection() {
/* 115 */     return getList();
/*     */   }
/*     */   
/*     */   protected abstract List getList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListIntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */