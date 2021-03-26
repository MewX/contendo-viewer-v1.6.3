/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortCollection;
/*    */ import org.apache.commons.collections.primitives.ShortIterator;
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
/*    */ abstract class BaseProxyShortCollection
/*    */   implements ShortCollection
/*    */ {
/*    */   protected abstract ShortCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(short element) {
/* 35 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(ShortCollection c) {
/* 39 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 43 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(short element) {
/* 47 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(ShortCollection c) {
/* 51 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 55 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public ShortIterator iterator() {
/* 59 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(ShortCollection c) {
/* 63 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(short element) {
/* 67 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(ShortCollection c) {
/* 71 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 75 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public short[] toArray() {
/* 79 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public short[] toArray(short[] a) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyShortCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */