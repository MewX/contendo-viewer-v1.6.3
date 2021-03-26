/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntCollection;
/*    */ import org.apache.commons.collections.primitives.IntIterator;
/*    */ import org.apache.commons.collections.primitives.IntList;
/*    */ import org.apache.commons.collections.primitives.IntListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class BaseUnmodifiableIntList
/*    */   extends BaseProxyIntList
/*    */ {
/*    */   public final void add(int index, int element) {
/* 33 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, IntCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final int removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final int set(int index, int element) {
/* 45 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(int element) {
/* 49 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(IntCollection c) {
/* 53 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(IntCollection c) {
/* 61 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(int element) {
/* 65 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(IntCollection c) {
/* 69 */     throw new UnsupportedOperationException("This IntList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final IntList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableIntList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final IntIterator iterator() {
/* 77 */     return UnmodifiableIntIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public IntListIterator listIterator() {
/* 81 */     return UnmodifiableIntListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public IntListIterator listIterator(int index) {
/* 85 */     return UnmodifiableIntListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableIntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */