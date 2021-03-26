/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatCollection;
/*    */ import org.apache.commons.collections.primitives.FloatList;
/*    */ import org.apache.commons.collections.primitives.FloatListIterator;
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
/*    */ abstract class BaseProxyFloatList
/*    */   extends BaseProxyFloatCollection
/*    */   implements FloatList
/*    */ {
/*    */   protected abstract FloatList getProxiedList();
/*    */   
/*    */   protected final FloatCollection getProxiedCollection() {
/* 33 */     return (FloatCollection)getProxiedList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int index, float element) {
/* 40 */     getProxiedList().add(index, element);
/*    */   }
/*    */   
/*    */   public boolean addAll(int index, FloatCollection collection) {
/* 44 */     return getProxiedList().addAll(index, collection);
/*    */   }
/*    */   
/*    */   public float get(int index) {
/* 48 */     return getProxiedList().get(index);
/*    */   }
/*    */   
/*    */   public int indexOf(float element) {
/* 52 */     return getProxiedList().indexOf(element);
/*    */   }
/*    */   
/*    */   public int lastIndexOf(float element) {
/* 56 */     return getProxiedList().lastIndexOf(element);
/*    */   }
/*    */   
/*    */   public FloatListIterator listIterator() {
/* 60 */     return getProxiedList().listIterator();
/*    */   }
/*    */   
/*    */   public FloatListIterator listIterator(int index) {
/* 64 */     return getProxiedList().listIterator(index);
/*    */   }
/*    */   
/*    */   public float removeElementAt(int index) {
/* 68 */     return getProxiedList().removeElementAt(index);
/*    */   }
/*    */   
/*    */   public float set(int index, float element) {
/* 72 */     return getProxiedList().set(index, element);
/*    */   }
/*    */   
/*    */   public FloatList subList(int fromIndex, int toIndex) {
/* 76 */     return getProxiedList().subList(fromIndex, toIndex);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/BaseProxyFloatList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */