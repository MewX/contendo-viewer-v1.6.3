/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntCollection;
/*    */ import org.apache.commons.collections.primitives.IntList;
/*    */ import org.apache.commons.collections.primitives.IntListIterator;
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
/*    */ abstract class BaseProxyIntList
/*    */   extends BaseProxyIntCollection
/*    */   implements IntList
/*    */ {
/*    */   protected abstract IntList getProxiedList();
/*    */   
/*    */   protected final IntCollection getProxiedCollection() {
/* 33 */     return (IntCollection)getProxiedList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int index, int element) {
/* 40 */     getProxiedList().add(index, element);
/*    */   }
/*    */   
/*    */   public boolean addAll(int index, IntCollection collection) {
/* 44 */     return getProxiedList().addAll(index, collection);
/*    */   }
/*    */   
/*    */   public int get(int index) {
/* 48 */     return getProxiedList().get(index);
/*    */   }
/*    */   
/*    */   public int indexOf(int element) {
/* 52 */     return getProxiedList().indexOf(element);
/*    */   }
/*    */   
/*    */   public int lastIndexOf(int element) {
/* 56 */     return getProxiedList().lastIndexOf(element);
/*    */   }
/*    */   
/*    */   public IntListIterator listIterator() {
/* 60 */     return getProxiedList().listIterator();
/*    */   }
/*    */   
/*    */   public IntListIterator listIterator(int index) {
/* 64 */     return getProxiedList().listIterator(index);
/*    */   }
/*    */   
/*    */   public int removeElementAt(int index) {
/* 68 */     return getProxiedList().removeElementAt(index);
/*    */   }
/*    */   
/*    */   public int set(int index, int element) {
/* 72 */     return getProxiedList().set(index, element);
/*    */   }
/*    */   
/*    */   public IntList subList(int fromIndex, int toIndex) {
/* 76 */     return getProxiedList().subList(fromIndex, toIndex);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyIntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */