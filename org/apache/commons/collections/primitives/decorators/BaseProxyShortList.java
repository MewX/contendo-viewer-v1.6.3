/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortCollection;
/*    */ import org.apache.commons.collections.primitives.ShortList;
/*    */ import org.apache.commons.collections.primitives.ShortListIterator;
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
/*    */ abstract class BaseProxyShortList
/*    */   extends BaseProxyShortCollection
/*    */   implements ShortList
/*    */ {
/*    */   protected abstract ShortList getProxiedList();
/*    */   
/*    */   protected final ShortCollection getProxiedCollection() {
/* 33 */     return (ShortCollection)getProxiedList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int index, short element) {
/* 40 */     getProxiedList().add(index, element);
/*    */   }
/*    */   
/*    */   public boolean addAll(int index, ShortCollection collection) {
/* 44 */     return getProxiedList().addAll(index, collection);
/*    */   }
/*    */   
/*    */   public short get(int index) {
/* 48 */     return getProxiedList().get(index);
/*    */   }
/*    */   
/*    */   public int indexOf(short element) {
/* 52 */     return getProxiedList().indexOf(element);
/*    */   }
/*    */   
/*    */   public int lastIndexOf(short element) {
/* 56 */     return getProxiedList().lastIndexOf(element);
/*    */   }
/*    */   
/*    */   public ShortListIterator listIterator() {
/* 60 */     return getProxiedList().listIterator();
/*    */   }
/*    */   
/*    */   public ShortListIterator listIterator(int index) {
/* 64 */     return getProxiedList().listIterator(index);
/*    */   }
/*    */   
/*    */   public short removeElementAt(int index) {
/* 68 */     return getProxiedList().removeElementAt(index);
/*    */   }
/*    */   
/*    */   public short set(int index, short element) {
/* 72 */     return getProxiedList().set(index, element);
/*    */   }
/*    */   
/*    */   public ShortList subList(int fromIndex, int toIndex) {
/* 76 */     return getProxiedList().subList(fromIndex, toIndex);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */