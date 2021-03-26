/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntCollection;
/*    */ import org.apache.commons.collections.primitives.IntIterator;
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
/*    */ abstract class BaseProxyIntCollection
/*    */   implements IntCollection
/*    */ {
/*    */   protected abstract IntCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(int element) {
/* 37 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(IntCollection c) {
/* 41 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 45 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(int element) {
/* 49 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(IntCollection c) {
/* 53 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 57 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public IntIterator iterator() {
/* 61 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(IntCollection c) {
/* 65 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(int element) {
/* 69 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(IntCollection c) {
/* 73 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 77 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public int[] toArray() {
/* 81 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public int[] toArray(int[] a) {
/* 85 */     return getProxiedCollection().toArray(a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 91 */     return getProxiedCollection().equals(obj);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 95 */     return getProxiedCollection().hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 99 */     return getProxiedCollection().toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyIntCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */