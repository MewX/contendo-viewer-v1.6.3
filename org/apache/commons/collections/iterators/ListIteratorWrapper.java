/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListIteratorWrapper
/*     */   implements ListIterator
/*     */ {
/*     */   private final Iterator iterator;
/*  38 */   private final LinkedList list = new LinkedList();
/*     */ 
/*     */   
/*  41 */   private int currentIndex = 0;
/*     */ 
/*     */   
/*  44 */   private int wrappedIteratorIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIteratorWrapper(Iterator iterator) {
/*  61 */     if (iterator == null) {
/*  62 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/*  64 */     this.iterator = iterator;
/*     */   }
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
/*     */   public void add(Object o) throws UnsupportedOperationException {
/*  77 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/*  87 */     if (this.currentIndex == this.wrappedIteratorIndex) {
/*  88 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPrevious() {
/* 100 */     if (this.currentIndex == 0) {
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() throws NoSuchElementException {
/* 114 */     if (this.currentIndex < this.wrappedIteratorIndex) {
/* 115 */       this.currentIndex++;
/* 116 */       return this.list.get(this.currentIndex - 1);
/*     */     } 
/*     */     
/* 119 */     Object retval = this.iterator.next();
/* 120 */     this.list.add(retval);
/* 121 */     this.currentIndex++;
/* 122 */     this.wrappedIteratorIndex++;
/* 123 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextIndex() {
/* 132 */     return this.currentIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object previous() throws NoSuchElementException {
/* 142 */     if (this.currentIndex == 0) {
/* 143 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 146 */     this.currentIndex--;
/* 147 */     return this.list.get(this.currentIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int previousIndex() {
/* 156 */     return this.currentIndex - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() throws UnsupportedOperationException {
/* 165 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Object o) throws UnsupportedOperationException {
/* 175 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/ListIteratorWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */