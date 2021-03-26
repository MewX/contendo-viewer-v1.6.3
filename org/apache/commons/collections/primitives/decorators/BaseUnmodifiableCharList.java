/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.CharCollection;
/*    */ import org.apache.commons.collections.primitives.CharIterator;
/*    */ import org.apache.commons.collections.primitives.CharList;
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
/*    */ abstract class BaseUnmodifiableCharList
/*    */   extends BaseProxyCharList
/*    */ {
/*    */   public final void add(int index, char element) {
/* 33 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(int index, CharCollection collection) {
/* 37 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final char removeElementAt(int index) {
/* 41 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final char set(int index, char element) {
/* 45 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean add(char element) {
/* 49 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean addAll(CharCollection c) {
/* 53 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 57 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeAll(CharCollection c) {
/* 61 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean removeElement(char element) {
/* 65 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final boolean retainAll(CharCollection c) {
/* 69 */     throw new UnsupportedOperationException("This CharList is not modifiable.");
/*    */   }
/*    */   
/*    */   public final CharList subList(int fromIndex, int toIndex) {
/* 73 */     return UnmodifiableCharList.wrap(getProxiedList().subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   public final CharIterator iterator() {
/* 77 */     return UnmodifiableCharIterator.wrap(getProxiedList().iterator());
/*    */   }
/*    */   
/*    */   public CharListIterator listIterator() {
/* 81 */     return UnmodifiableCharListIterator.wrap(getProxiedList().listIterator());
/*    */   }
/*    */   
/*    */   public CharListIterator listIterator(int index) {
/* 85 */     return UnmodifiableCharListIterator.wrap(getProxiedList().listIterator(index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseUnmodifiableCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */