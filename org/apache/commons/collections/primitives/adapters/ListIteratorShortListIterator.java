/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.primitives.ShortListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListIteratorShortListIterator
/*    */   implements ShortListIterator
/*    */ {
/*    */   private ListIterator _iterator;
/*    */   
/*    */   public static ShortListIterator wrap(ListIterator iterator) {
/* 49 */     return (null == iterator) ? null : new ListIteratorShortListIterator(iterator);
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
/*    */   public ListIteratorShortListIterator(ListIterator iterator) {
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
/*    */   public short next() {
/*    */     return ((Number)this._iterator.next()).shortValue();
/*    */   }
/*    */   
/*    */   public short previous() {
/*    */     return ((Number)this._iterator.previous()).shortValue();
/*    */   }
/*    */   
/*    */   public void add(short element) {
/*    */     this._iterator.add(new Short(element));
/*    */   }
/*    */   
/*    */   public void set(short element) {
/*    */     this._iterator.set(new Short(element));
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ListIteratorShortListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */