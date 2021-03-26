/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleCollection;
/*    */ import org.apache.commons.collections.primitives.DoubleIterator;
/*    */ import org.apache.commons.collections.primitives.DoubleList;
/*    */ import org.apache.commons.collections.primitives.DoubleListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class BaseUnmodifiableDoubleList
/*    */   extends BaseProxyDoubleList
/*    */ {
/*    */   public final void add(int index, double element) {
/* 33 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, DoubleCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final double removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final double set(int index, double element) {
/* 45 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(double element) {
/* 49 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(DoubleCollection c) {
/* 53 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(DoubleCollection c) {
/* 61 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(double element) {
/* 65 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(DoubleCollection c) {
/* 69 */     throw new UnsupportedOperationException("This DoubleList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final DoubleList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableDoubleList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final DoubleIterator iterator() {
/* 77 */     return UnmodifiableDoubleIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public DoubleListIterator listIterator() {
/* 81 */     return UnmodifiableDoubleListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public DoubleListIterator listIterator(int index) {
/* 85 */     return UnmodifiableDoubleListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableDoubleList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */