/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
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
/*    */ public class AbstractOrderedMapIteratorDecorator
/*    */   implements OrderedMapIterator
/*    */ {
/*    */   protected final OrderedMapIterator iterator;
/*    */   
/*    */   public AbstractOrderedMapIteratorDecorator(OrderedMapIterator iterator) {
/* 44 */     if (iterator == null) {
/* 45 */       throw new IllegalArgumentException("OrderedMapIterator must not be null");
/*    */     }
/* 47 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected OrderedMapIterator getOrderedMapIterator() {
/* 56 */     return this.iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 61 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 65 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 69 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 73 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 77 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 81 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 85 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object obj) {
/* 89 */     return this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/AbstractOrderedMapIteratorDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */