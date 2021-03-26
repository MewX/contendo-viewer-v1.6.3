/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.primitives.ByteIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IteratorByteIterator
/*    */   implements ByteIterator
/*    */ {
/*    */   private Iterator _iterator;
/*    */   
/*    */   public static ByteIterator wrap(Iterator iterator) {
/* 51 */     return (null == iterator) ? null : new IteratorByteIterator(iterator);
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
/*    */   public IteratorByteIterator(Iterator iterator) {
/* 75 */     this._iterator = null;
/*    */     this._iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     return this._iterator.hasNext();
/*    */   }
/*    */   
/*    */   public byte next() {
/*    */     return ((Number)this._iterator.next()).byteValue();
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/IteratorByteIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */