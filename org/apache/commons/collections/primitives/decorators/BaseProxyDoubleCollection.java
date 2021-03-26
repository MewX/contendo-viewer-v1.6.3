/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleCollection;
/*    */ import org.apache.commons.collections.primitives.DoubleIterator;
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
/*    */ abstract class BaseProxyDoubleCollection
/*    */   implements DoubleCollection
/*    */ {
/*    */   protected abstract DoubleCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(double element) {
/* 35 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(DoubleCollection c) {
/* 39 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 43 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(double element) {
/* 47 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(DoubleCollection c) {
/* 51 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 55 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public DoubleIterator iterator() {
/* 59 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(DoubleCollection c) {
/* 63 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(double element) {
/* 67 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(DoubleCollection c) {
/* 71 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 75 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public double[] toArray() {
/* 79 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public double[] toArray(double[] a) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyDoubleCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */