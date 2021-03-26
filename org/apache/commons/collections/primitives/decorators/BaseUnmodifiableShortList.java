/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortCollection;
/*    */ import org.apache.commons.collections.primitives.ShortIterator;
/*    */ import org.apache.commons.collections.primitives.ShortList;
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
/*    */ abstract class BaseUnmodifiableShortList
/*    */   extends BaseProxyShortList
/*    */ {
/*    */   public final void add(int index, short element) {
/* 33 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, ShortCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final short removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final short set(int index, short element) {
/* 45 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(short element) {
/* 49 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(ShortCollection c) {
/* 53 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(ShortCollection c) {
/* 61 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(short element) {
/* 65 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(ShortCollection c) {
/* 69 */     throw new UnsupportedOperationException("This ShortList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final ShortList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableShortList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final ShortIterator iterator() {
/* 77 */     return UnmodifiableShortIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public ShortListIterator listIterator() {
/* 81 */     return UnmodifiableShortListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public ShortListIterator listIterator(int index) {
/* 85 */     return UnmodifiableShortListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */