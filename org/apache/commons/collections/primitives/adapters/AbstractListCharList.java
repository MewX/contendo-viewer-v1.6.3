/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.CharCollection;
/*     */ import org.apache.commons.collections.primitives.CharIterator;
/*     */ import org.apache.commons.collections.primitives.CharList;
/*     */ import org.apache.commons.collections.primitives.CharListIterator;
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
/*     */ abstract class AbstractListCharList
/*     */   extends AbstractCollectionCharCollection
/*     */   implements CharList
/*     */ {
/*     */   public void add(int index, char element) {
/*  35 */     getList().add(index, new Character(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, CharCollection collection) {
/*  39 */     return getList().addAll(index, CharCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public char get(int index) {
/*  43 */     return ((Character)getList().get(index)).charValue();
/*     */   }
/*     */   
/*     */   public int indexOf(char element) {
/*  47 */     return getList().indexOf(new Character(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(char element) {
/*  51 */     return getList().lastIndexOf(new Character(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharListIterator listIterator() {
/*  62 */     return ListIteratorCharListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharListIterator listIterator(int index) {
/*  73 */     return ListIteratorCharListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public char removeElementAt(int index) {
/*  77 */     return ((Character)getList().remove(index)).charValue();
/*     */   }
/*     */   
/*     */   public char set(int index, char element) {
/*  81 */     return ((Character)getList().set(index, new Character(element))).charValue();
/*     */   }
/*     */   
/*     */   public CharList subList(int fromIndex, int toIndex) {
/*  85 */     return ListCharList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof CharList) {
/*  90 */       CharList that = (CharList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       CharIterator thisiter = iterator();
/*  97 */       CharIterator thatiter = that.iterator();
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */