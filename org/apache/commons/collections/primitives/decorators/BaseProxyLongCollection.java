/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongCollection;
/*    */ import org.apache.commons.collections.primitives.LongIterator;
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
/*    */ abstract class BaseProxyLongCollection
/*    */   implements LongCollection
/*    */ {
/*    */   protected abstract LongCollection getProxiedCollection();
/*    */   
/*    */   public boolean add(long element) {
/* 35 */     return getProxiedCollection().add(element);
/*    */   }
/*    */   
/*    */   public boolean addAll(LongCollection c) {
/* 39 */     return getProxiedCollection().addAll(c);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 43 */     getProxiedCollection().clear();
/*    */   }
/*    */   
/*    */   public boolean contains(long element) {
/* 47 */     return getProxiedCollection().contains(element);
/*    */   }
/*    */   
/*    */   public boolean containsAll(LongCollection c) {
/* 51 */     return getProxiedCollection().containsAll(c);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 55 */     return getProxiedCollection().isEmpty();
/*    */   }
/*    */   
/*    */   public LongIterator iterator() {
/* 59 */     return getProxiedCollection().iterator();
/*    */   }
/*    */   
/*    */   public boolean removeAll(LongCollection c) {
/* 63 */     return getProxiedCollection().removeAll(c);
/*    */   }
/*    */   
/*    */   public boolean removeElement(long element) {
/* 67 */     return getProxiedCollection().removeElement(element);
/*    */   }
/*    */   
/*    */   public boolean retainAll(LongCollection c) {
/* 71 */     return getProxiedCollection().retainAll(c);
/*    */   }
/*    */   
/*    */   public int size() {
/* 75 */     return getProxiedCollection().size();
/*    */   }
/*    */   
/*    */   public long[] toArray() {
/* 79 */     return getProxiedCollection().toArray();
/*    */   }
/*    */   
/*    */   public long[] toArray(long[] a) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyLongCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */