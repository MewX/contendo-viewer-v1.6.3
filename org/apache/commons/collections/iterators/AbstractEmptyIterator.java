/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class AbstractEmptyIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 42 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 50 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 54 */     return 0;
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 58 */     return -1;
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 62 */     throw new UnsupportedOperationException("add() not supported for empty Iterator");
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 66 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 70 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 74 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 78 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 82 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void reset() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/AbstractEmptyIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */