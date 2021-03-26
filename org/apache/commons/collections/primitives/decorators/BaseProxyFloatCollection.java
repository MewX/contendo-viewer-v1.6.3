/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatCollection;
/*    */ import org.apache.commons.collections.primitives.FloatIterator;
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
/*    */ abstract class BaseProxyFloatCollection
/*    */   implements FloatCollection
/*    */ {
/*    */   protected abstract FloatCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(float element) {
/* 35 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(FloatCollection c) {
/* 39 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 43 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(float element) {
/* 47 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(FloatCollection c) {
/* 51 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 55 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public FloatIterator iterator() {
/* 59 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(FloatCollection c) {
/* 63 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(float element) {
/* 67 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(FloatCollection c) {
/* 71 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 75 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public float[] toArray() {
/* 79 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public float[] toArray(float[] a) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyFloatCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */