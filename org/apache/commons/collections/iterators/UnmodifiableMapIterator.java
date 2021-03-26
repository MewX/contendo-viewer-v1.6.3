/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.MapIterator;
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
/*    */ public final class UnmodifiableMapIterator
/*    */   implements MapIterator, Unmodifiable
/*    */ {
/*    */   private MapIterator iterator;
/*    */   
/*    */   public static MapIterator decorate(MapIterator iterator) {
/* 42 */     if (iterator == null) {
/* 43 */       throw new IllegalArgumentException("MapIterator must not be null");
/*    */     }
/* 45 */     if (iterator instanceof Unmodifiable) {
/* 46 */       return iterator;
/*    */     }
/* 48 */     return new UnmodifiableMapIterator(iterator);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableMapIterator(MapIterator iterator) {
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
/*    */   public Object getKey() {
/* 72 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 76 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 80 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 84 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/UnmodifiableMapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */