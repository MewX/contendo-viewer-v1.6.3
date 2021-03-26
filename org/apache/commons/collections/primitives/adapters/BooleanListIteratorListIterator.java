/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.primitives.BooleanListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BooleanListIteratorListIterator
/*    */   implements ListIterator
/*    */ {
/*    */   private BooleanListIterator _iterator;
/*    */   
/*    */   public static ListIterator wrap(BooleanListIterator iterator) {
/* 49 */     return (null == iterator) ? null : new BooleanListIteratorListIterator(iterator);
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
/*    */   public BooleanListIteratorListIterator(BooleanListIterator iterator) {
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
/*    */     return new Boolean(this._iterator.next());
/*    */   }
/*    */   
/*    */   public Object previous() {
/*    */     return new Boolean(this._iterator.previous());
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/*    */     this._iterator.add(((Boolean)obj).booleanValue());
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/*    */     this._iterator.set(((Boolean)obj).booleanValue());
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/BooleanListIteratorListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */