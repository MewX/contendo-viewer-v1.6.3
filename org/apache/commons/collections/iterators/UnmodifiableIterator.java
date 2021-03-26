/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableIterator
/*    */   implements Iterator, Unmodifiable
/*    */ {
/*    */   private Iterator iterator;
/*    */   
/*    */   public static Iterator decorate(Iterator iterator) {
/* 45 */     if (iterator == null) {
/* 46 */       throw new IllegalArgumentException("Iterator must not be null");
/*    */     }
/* 48 */     if (iterator instanceof Unmodifiable) {
/* 49 */       return iterator;
/*    */     }
/* 51 */     return new UnmodifiableIterator(iterator);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableIterator(Iterator iterator) {
/* 62 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 67 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 71 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 75 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/UnmodifiableIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */