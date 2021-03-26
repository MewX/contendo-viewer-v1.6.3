/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongCollection;
/*    */ import org.apache.commons.collections.primitives.LongIterator;
/*    */ import org.apache.commons.collections.primitives.LongList;
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
/*    */ abstract class BaseUnmodifiableLongList
/*    */   extends BaseProxyLongList
/*    */ {
/*    */   public final void add(int index, long element) {
/* 33 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, LongCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final long removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final long set(int index, long element) {
/* 45 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(long element) {
/* 49 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(LongCollection c) {
/* 53 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(LongCollection c) {
/* 61 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(long element) {
/* 65 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(LongCollection c) {
/* 69 */     throw new UnsupportedOperationException("This LongList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final LongList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableLongList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final LongIterator iterator() {
/* 77 */     return UnmodifiableLongIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public LongListIterator listIterator() {
/* 81 */     return UnmodifiableLongListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public LongListIterator listIterator(int index) {
/* 85 */     return UnmodifiableLongListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableLongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */