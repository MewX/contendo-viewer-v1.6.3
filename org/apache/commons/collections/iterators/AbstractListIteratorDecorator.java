/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractListIteratorDecorator
/*    */   implements ListIterator
/*    */ {
/*    */   protected final ListIterator iterator;
/*    */   
/*    */   public AbstractListIteratorDecorator(ListIterator iterator) {
/* 45 */     if (iterator == null) {
/* 46 */       throw new IllegalArgumentException("ListIterator must not be null");
/*    */     }
/* 48 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ListIterator getListIterator() {
/* 57 */     return this.iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 62 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 66 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 70 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 74 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 78 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 82 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 86 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 90 */     this.iterator.set(obj);
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 94 */     this.iterator.add(obj);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/AbstractListIteratorDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */