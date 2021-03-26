/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
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
/*    */ public final class UnmodifiableOrderedMapIterator
/*    */   implements OrderedMapIterator, Unmodifiable
/*    */ {
/*    */   private OrderedMapIterator iterator;
/*    */   
/*    */   public static OrderedMapIterator decorate(OrderedMapIterator iterator) {
/* 42 */     if (iterator == null) {
/* 43 */       throw new IllegalArgumentException("OrderedMapIterator must not be null");
/*    */     }
/* 45 */     if (iterator instanceof Unmodifiable) {
/* 46 */       return iterator;
/*    */     }
/* 48 */     return new UnmodifiableOrderedMapIterator(iterator);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableOrderedMapIterator(OrderedMapIterator iterator) {
/* 59 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 64 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 68 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 72 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 76 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 80 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 84 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 88 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 92 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/UnmodifiableOrderedMapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */