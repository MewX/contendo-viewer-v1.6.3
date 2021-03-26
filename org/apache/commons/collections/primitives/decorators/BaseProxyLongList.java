/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongCollection;
/*    */ import org.apache.commons.collections.primitives.LongList;
/*    */ import org.apache.commons.collections.primitives.LongListIterator;
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
/*    */ abstract class BaseProxyLongList
/*    */   extends BaseProxyLongCollection
/*    */   implements LongList
/*    */ {
/*    */   protected abstract LongList getProxiedList();
/*    */   
/*    */   protected final LongCollection getProxiedCollection() {
/* 33 */     return (LongCollection)getProxiedList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int index, long element) {
/* 40 */     getProxiedList().add(index, element);
/*    */   }
/*    */   
/*    */   public boolean addAll(int index, LongCollection collection) {
/* 44 */     return getProxiedList().addAll(index, collection);
/*    */   }
/*    */   
/*    */   public long get(int index) {
/* 48 */     return getProxiedList().get(index);
/*    */   }
/*    */   
/*    */   public int indexOf(long element) {
/* 52 */     return getProxiedList().indexOf(element);
/*    */   }
/*    */   
/*    */   public int lastIndexOf(long element) {
/* 56 */     return getProxiedList().lastIndexOf(element);
/*    */   }
/*    */   
/*    */   public LongListIterator listIterator() {
/* 60 */     return getProxiedList().listIterator();
/*    */   }
/*    */   
/*    */   public LongListIterator listIterator(int index) {
/* 64 */     return getProxiedList().listIterator(index);
/*    */   }
/*    */   
/*    */   public long removeElementAt(int index) {
/* 68 */     return getProxiedList().removeElementAt(index);
/*    */   }
/*    */   
/*    */   public long set(int index, long element) {
/* 72 */     return getProxiedList().set(index, element);
/*    */   }
/*    */   
/*    */   public LongList subList(int fromIndex, int toIndex) {
/* 76 */     return getProxiedList().subList(fromIndex, toIndex);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyLongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */