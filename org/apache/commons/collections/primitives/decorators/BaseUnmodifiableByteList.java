/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ByteCollection;
/*    */ import org.apache.commons.collections.primitives.ByteIterator;
/*    */ import org.apache.commons.collections.primitives.ByteList;
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
/*    */ abstract class BaseUnmodifiableByteList
/*    */   extends BaseProxyByteList
/*    */ {
/*    */   public final void add(int index, byte element) {
/* 33 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, ByteCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final byte removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final byte set(int index, byte element) {
/* 45 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(byte element) {
/* 49 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(ByteCollection c) {
/* 53 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(ByteCollection c) {
/* 61 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(byte element) {
/* 65 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(ByteCollection c) {
/* 69 */     throw new UnsupportedOperationException("This ByteList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final ByteList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableByteList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final ByteIterator iterator() {
/* 77 */     return UnmodifiableByteIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public ByteListIterator listIterator() {
/* 81 */     return UnmodifiableByteListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public ByteListIterator listIterator(int index) {
/* 85 */     return UnmodifiableByteListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */