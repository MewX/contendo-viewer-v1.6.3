/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.primitives.ByteListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ByteListIteratorListIterator
/*    */   implements ListIterator
/*    */ {
/*    */   private ByteListIterator _iterator;
/*    */   
/*    */   public static ListIterator wrap(ByteListIterator iterator) {
/* 49 */     return (null == iterator) ? null : new ByteListIteratorListIterator(iterator);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ByteListIteratorListIterator(ByteListIterator iterator) {
/* 97 */     this._iterator = null;
/*    */     this._iterator = iterator;
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/*    */     return this._iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/*    */     return this._iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     return this._iterator.hasNext();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/*    */     return this._iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object next() {
/*    */     return new Byte(this._iterator.next());
/*    */   }
/*    */   
/*    */   public Object previous() {
/*    */     return new Byte(this._iterator.previous());
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/*    */     this._iterator.add(((Number)obj).byteValue());
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/*    */     this._iterator.set(((Number)obj).byteValue());
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ByteListIteratorListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */