/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.CharCollection;
/*    */ import org.apache.commons.collections.primitives.CharIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class BaseProxyCharCollection
/*    */   implements CharCollection
/*    */ {
/*    */   protected abstract CharCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(char element) {
/* 35 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(CharCollection c) {
/* 39 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 43 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(char element) {
/* 47 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(CharCollection c) {
/* 51 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 55 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public CharIterator iterator() {
/* 59 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(CharCollection c) {
/* 63 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(char element) {
/* 67 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(CharCollection c) {
/* 71 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 75 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public char[] toArray() {
/* 79 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public char[] toArray(char[] a) {
/* 83 */     return getProxiedCollection().toArray(a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 89 */     return getProxiedCollection().equals(obj);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 93 */     return getProxiedCollection().hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 97 */     return getProxiedCollection().toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyCharCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */