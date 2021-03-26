/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.DoubleCollection;
/*     */ import org.apache.commons.collections.primitives.DoubleIterator;
/*     */ import org.apache.commons.collections.primitives.DoubleList;
/*     */ import org.apache.commons.collections.primitives.DoubleListIterator;
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
/*     */ abstract class AbstractListDoubleList
/*     */   extends AbstractCollectionDoubleCollection
/*     */   implements DoubleList
/*     */ {
/*     */   public void add(int index, double element) {
/*  35 */     getList().add(index, new Double(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, DoubleCollection collection) {
/*  39 */     return getList().addAll(index, DoubleCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public double get(int index) {
/*  43 */     return ((Number)getList().get(index)).doubleValue();
/*     */   }
/*     */   
/*     */   public int indexOf(double element) {
/*  47 */     return getList().indexOf(new Double(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(double element) {
/*  51 */     return getList().lastIndexOf(new Double(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleListIterator listIterator() {
/*  62 */     return ListIteratorDoubleListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleListIterator listIterator(int index) {
/*  73 */     return ListIteratorDoubleListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public double removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).doubleValue();
/*     */   }
/*     */   
/*     */   public double set(int index, double element) {
/*  81 */     return ((Number)getList().set(index, new Double(element))).doubleValue();
/*     */   }
/*     */   
/*     */   public DoubleList subList(int fromIndex, int toIndex) {
/*  85 */     return ListDoubleList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof DoubleList) {
/*  90 */       DoubleList that = (DoubleList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       DoubleIterator thisiter = iterator();
/*  97 */       DoubleIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListDoubleList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */