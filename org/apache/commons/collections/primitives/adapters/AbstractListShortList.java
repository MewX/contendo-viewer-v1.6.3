/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.ShortCollection;
/*     */ import org.apache.commons.collections.primitives.ShortIterator;
/*     */ import org.apache.commons.collections.primitives.ShortList;
/*     */ import org.apache.commons.collections.primitives.ShortListIterator;
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
/*     */ abstract class AbstractListShortList
/*     */   extends AbstractCollectionShortCollection
/*     */   implements ShortList
/*     */ {
/*     */   public void add(int index, short element) {
/*  35 */     getList().add(index, new Short(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, ShortCollection collection) {
/*  39 */     return getList().addAll(index, ShortCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public short get(int index) {
/*  43 */     return ((Number)getList().get(index)).shortValue();
/*     */   }
/*     */   
/*     */   public int indexOf(short element) {
/*  47 */     return getList().indexOf(new Short(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(short element) {
/*  51 */     return getList().lastIndexOf(new Short(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShortListIterator listIterator() {
/*  62 */     return ListIteratorShortListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShortListIterator listIterator(int index) {
/*  73 */     return ListIteratorShortListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public short removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).shortValue();
/*     */   }
/*     */   
/*     */   public short set(int index, short element) {
/*  81 */     return ((Number)getList().set(index, new Short(element))).shortValue();
/*     */   }
/*     */   
/*     */   public ShortList subList(int fromIndex, int toIndex) {
/*  85 */     return ListShortList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof ShortList) {
/*  90 */       ShortList that = (ShortList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       ShortIterator thisiter = iterator();
/*  97 */       ShortIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */