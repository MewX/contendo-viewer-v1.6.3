/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.LongCollection;
/*     */ import org.apache.commons.collections.primitives.LongIterator;
/*     */ import org.apache.commons.collections.primitives.LongList;
/*     */ import org.apache.commons.collections.primitives.LongListIterator;
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
/*     */ abstract class AbstractListLongList
/*     */   extends AbstractCollectionLongCollection
/*     */   implements LongList
/*     */ {
/*     */   public void add(int index, long element) {
/*  35 */     getList().add(index, new Long(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, LongCollection collection) {
/*  39 */     return getList().addAll(index, LongCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public long get(int index) {
/*  43 */     return ((Number)getList().get(index)).longValue();
/*     */   }
/*     */   
/*     */   public int indexOf(long element) {
/*  47 */     return getList().indexOf(new Long(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(long element) {
/*  51 */     return getList().lastIndexOf(new Long(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LongListIterator listIterator() {
/*  62 */     return ListIteratorLongListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LongListIterator listIterator(int index) {
/*  73 */     return ListIteratorLongListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public long removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).longValue();
/*     */   }
/*     */   
/*     */   public long set(int index, long element) {
/*  81 */     return ((Number)getList().set(index, new Long(element))).longValue();
/*     */   }
/*     */   
/*     */   public LongList subList(int fromIndex, int toIndex) {
/*  85 */     return ListLongList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof LongList) {
/*  90 */       LongList that = (LongList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       LongIterator thisiter = iterator();
/*  97 */       LongIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListLongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */