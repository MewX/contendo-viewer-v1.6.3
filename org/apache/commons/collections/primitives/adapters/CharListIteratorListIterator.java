/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.primitives.CharListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CharListIteratorListIterator
/*    */   implements ListIterator
/*    */ {
/*    */   private CharListIterator _iterator;
/*    */   
/*    */   public static ListIterator wrap(CharListIterator iterator) {
/* 49 */     return (null == iterator) ? null : new CharListIteratorListIterator(iterator);
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
/*    */   public CharListIteratorListIterator(CharListIterator iterator) {
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
/*    */     return new Character(this._iterator.next());
/*    */   }
/*    */   
/*    */   public Object previous() {
/*    */     return new Character(this._iterator.previous());
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/*    */     this._iterator.add(((Character)obj).charValue());
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/*    */     this._iterator.set(((Character)obj).charValue());
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     this._iterator.remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/CharListIteratorListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */