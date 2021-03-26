/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.FloatCollection;
/*     */ import org.apache.commons.collections.primitives.FloatIterator;
/*     */ import org.apache.commons.collections.primitives.FloatList;
/*     */ import org.apache.commons.collections.primitives.FloatListIterator;
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
/*     */ abstract class AbstractListFloatList
/*     */   extends AbstractCollectionFloatCollection
/*     */   implements FloatList
/*     */ {
/*     */   public void add(int index, float element) {
/*  35 */     getList().add(index, new Float(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, FloatCollection collection) {
/*  39 */     return getList().addAll(index, FloatCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public float get(int index) {
/*  43 */     return ((Number)getList().get(index)).floatValue();
/*     */   }
/*     */   
/*     */   public int indexOf(float element) {
/*  47 */     return getList().indexOf(new Float(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(float element) {
/*  51 */     return getList().lastIndexOf(new Float(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatListIterator listIterator() {
/*  62 */     return ListIteratorFloatListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FloatListIterator listIterator(int index) {
/*  73 */     return ListIteratorFloatListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public float removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).floatValue();
/*     */   }
/*     */   
/*     */   public float set(int index, float element) {
/*  81 */     return ((Number)getList().set(index, new Float(element))).floatValue();
/*     */   }
/*     */   
/*     */   public FloatList subList(int fromIndex, int toIndex) {
/*  85 */     return ListFloatList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof FloatList) {
/*  90 */       FloatList that = (FloatList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       FloatIterator thisiter = iterator();
/*  97 */       FloatIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListFloatList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */