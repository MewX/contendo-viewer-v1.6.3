/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
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
/*    */ 
/*    */ public final class UnmodifiableListIterator
/*    */   implements ListIterator, Unmodifiable
/*    */ {
/*    */   private ListIterator iterator;
/*    */   
/*    */   public static ListIterator decorate(ListIterator iterator) {
/* 43 */     if (iterator == null) {
/* 44 */       throw new IllegalArgumentException("ListIterator must not be null");
/*    */     }
/* 46 */     if (iterator instanceof Unmodifiable) {
/* 47 */       return iterator;
/*    */     }
/* 49 */     return new UnmodifiableListIterator(iterator);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableListIterator(ListIterator iterator) {
/* 60 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 65 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 69 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 73 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 77 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 81 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 85 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 89 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 93 */     throw new UnsupportedOperationException("set() is not supported");
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 97 */     throw new UnsupportedOperationException("add() is not supported");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/UnmodifiableListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */