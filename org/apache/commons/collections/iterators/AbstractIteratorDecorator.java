/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ public class AbstractIteratorDecorator
/*    */   implements Iterator
/*    */ {
/*    */   protected final Iterator iterator;
/*    */   
/*    */   public AbstractIteratorDecorator(Iterator iterator) {
/* 45 */     if (iterator == null) {
/* 46 */       throw new IllegalArgumentException("Iterator must not be null");
/*    */     }
/* 48 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Iterator getIterator() {
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
/*    */   public void remove() {
/* 70 */     this.iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/AbstractIteratorDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */