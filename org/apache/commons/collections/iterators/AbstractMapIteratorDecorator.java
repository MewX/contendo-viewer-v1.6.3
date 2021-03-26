/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.MapIterator;
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
/*    */ public class AbstractMapIteratorDecorator
/*    */   implements MapIterator
/*    */ {
/*    */   protected final MapIterator iterator;
/*    */   
/*    */   public AbstractMapIteratorDecorator(MapIterator iterator) {
/* 44 */     if (iterator == null) {
/* 45 */       throw new IllegalArgumentException("MapIterator must not be null");
/*    */     }
/* 47 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected MapIterator getMapIterator() {
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
/*    */   public void remove() {
/* 69 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 73 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 77 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object obj) {
/* 81 */     return this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/AbstractMapIteratorDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */