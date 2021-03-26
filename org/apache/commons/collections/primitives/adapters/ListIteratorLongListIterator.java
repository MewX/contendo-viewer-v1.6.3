/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.primitives.LongListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListIteratorLongListIterator
/*    */   implements LongListIterator
/*    */ {
/*    */   private ListIterator _iterator;
/*    */   
/*    */   public static LongListIterator wrap(ListIterator iterator) {
/* 49 */     return (null == iterator) ? null : new ListIteratorLongListIterator(iterator);
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
/*    */   public ListIteratorLongListIterator(ListIterator iterator) {
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
/*    */   public long next() {
/*    */     return ((Number)this._iterator.next()).longValue();
/*    */   }
/*    */   
/*    */   public long previous() {
/*    */     return ((Number)this._iterator.previous()).longValue();
/*    */   }
/*    */   
/*    */   public void add(long element) {
/*    */     this._iterator.add(new Long(element));
/*    */   }
/*    */   
/*    */   public void set(long element) {
/*    */     this._iterator.set(new Long(element));
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ListIteratorLongListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */