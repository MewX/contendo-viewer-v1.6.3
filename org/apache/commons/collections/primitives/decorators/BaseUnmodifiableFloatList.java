/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatCollection;
/*    */ import org.apache.commons.collections.primitives.FloatIterator;
/*    */ import org.apache.commons.collections.primitives.FloatList;
/*    */ import org.apache.commons.collections.primitives.FloatListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class BaseUnmodifiableFloatList
/*    */   extends BaseProxyFloatList
/*    */ {
/*    */   public final void add(int index, float element) {
/* 33 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, FloatCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final float removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final float set(int index, float element) {
/* 45 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(float element) {
/* 49 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(FloatCollection c) {
/* 53 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(FloatCollection c) {
/* 61 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(float element) {
/* 65 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(FloatCollection c) {
/* 69 */     throw new UnsupportedOperationException("This FloatList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final FloatList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableFloatList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final FloatIterator iterator() {
/* 77 */     return UnmodifiableFloatIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public FloatListIterator listIterator() {
/* 81 */     return UnmodifiableFloatListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public FloatListIterator listIterator(int index) {
/* 85 */     return UnmodifiableFloatListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableFloatList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */