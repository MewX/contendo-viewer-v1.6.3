/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.BooleanCollection;
/*     */ import org.apache.commons.collections.primitives.BooleanIterator;
/*     */ import org.apache.commons.collections.primitives.BooleanList;
/*     */ import org.apache.commons.collections.primitives.BooleanListIterator;
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
/*     */ abstract class AbstractListBooleanList
/*     */   extends AbstractCollectionBooleanCollection
/*     */   implements BooleanList
/*     */ {
/*     */   public void add(int index, boolean element) {
/*  35 */     getList().add(index, new Boolean(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, BooleanCollection collection) {
/*  39 */     return getList().addAll(index, BooleanCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public boolean get(int index) {
/*  43 */     return ((Boolean)getList().get(index)).booleanValue();
/*     */   }
/*     */   
/*     */   public int indexOf(boolean element) {
/*  47 */     return getList().indexOf(new Boolean(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(boolean element) {
/*  51 */     return getList().lastIndexOf(new Boolean(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanListIterator listIterator() {
/*  62 */     return ListIteratorBooleanListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanListIterator listIterator(int index) {
/*  73 */     return ListIteratorBooleanListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public boolean removeElementAt(int index) {
/*  77 */     return ((Boolean)getList().remove(index)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean set(int index, boolean element) {
/*  81 */     return ((Boolean)getList().set(index, new Boolean(element))).booleanValue();
/*     */   }
/*     */   
/*     */   public BooleanList subList(int fromIndex, int toIndex) {
/*  85 */     return ListBooleanList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof BooleanList) {
/*  90 */       BooleanList that = (BooleanList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       BooleanIterator thisiter = iterator();
/*  97 */       BooleanIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListBooleanList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */