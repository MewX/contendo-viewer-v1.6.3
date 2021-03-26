/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ByteCollection;
/*    */ import org.apache.commons.collections.primitives.ByteList;
/*    */ import org.apache.commons.collections.primitives.ByteListIterator;
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
/*    */ abstract class BaseProxyByteList
/*    */   extends BaseProxyByteCollection
/*    */   implements ByteList
/*    */ {
/*    */   protected abstract ByteList getProxiedList();
/*    */   
/*    */   protected final ByteCollection getProxiedCollection() {
/* 33 */     return (ByteCollection)getProxiedList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int index, byte element) {
/* 40 */     getProxiedList().add(index, element);
/*    */   }
/*    */   
/*    */   public boolean addAll(int index, ByteCollection collection) {
/* 44 */     return getProxiedList().addAll(index, collection);
/*    */   }
/*    */   
/*    */   public byte get(int index) {
/* 48 */     return getProxiedList().get(index);
/*    */   }
/*    */   
/*    */   public int indexOf(byte element) {
/* 52 */     return getProxiedList().indexOf(element);
/*    */   }
/*    */   
/*    */   public int lastIndexOf(byte element) {
/* 56 */     return getProxiedList().lastIndexOf(element);
/*    */   }
/*    */   
/*    */   public ByteListIterator listIterator() {
/* 60 */     return getProxiedList().listIterator();
/*    */   }
/*    */   
/*    */   public ByteListIterator listIterator(int index) {
/* 64 */     return getProxiedList().listIterator(index);
/*    */   }
/*    */   
/*    */   public byte removeElementAt(int index) {
/* 68 */     return getProxiedList().removeElementAt(index);
/*    */   }
/*    */   
/*    */   public byte set(int index, byte element) {
/* 72 */     return getProxiedList().set(index, element);
/*    */   }
/*    */   
/*    */   public ByteList subList(int fromIndex, int toIndex) {
/* 76 */     return getProxiedList().subList(fromIndex, toIndex);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */