/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.primitives.ShortIterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShortIteratorIterator
/*    */   implements Iterator
/*    */ {
/*    */   private ShortIterator _iterator;
/*    */   
/*    */   public static Iterator wrap(ShortIterator iterator) {
/* 49 */     return (null == iterator) ? null : new ShortIteratorIterator(iterator);
/*    */   }
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
/*    */   public ShortIteratorIterator(ShortIterator iterator) {
/* 73 */     this._iterator = null;
/*    */     this._iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     return this._iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/*    */     return new Short(this._iterator.next());
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ShortIteratorIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */