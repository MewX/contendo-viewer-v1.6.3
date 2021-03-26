/*    */ package org.apache.commons.collections.bidimap;
/*    */ 
/*    */ import org.apache.commons.collections.BidiMap;
/*    */ import org.apache.commons.collections.OrderedBidiMap;
/*    */ import org.apache.commons.collections.OrderedMapIterator;
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
/*    */ public abstract class AbstractOrderedBidiMapDecorator
/*    */   extends AbstractBidiMapDecorator
/*    */   implements OrderedBidiMap
/*    */ {
/*    */   protected AbstractOrderedBidiMapDecorator(OrderedBidiMap map) {
/* 48 */     super((BidiMap)map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected OrderedBidiMap getOrderedBidiMap() {
/* 57 */     return (OrderedBidiMap)this.map;
/*    */   }
/*    */ 
/*    */   
/*    */   public OrderedMapIterator orderedMapIterator() {
/* 62 */     return getOrderedBidiMap().orderedMapIterator();
/*    */   }
/*    */   
/*    */   public Object firstKey() {
/* 66 */     return getOrderedBidiMap().firstKey();
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 70 */     return getOrderedBidiMap().lastKey();
/*    */   }
/*    */   
/*    */   public Object nextKey(Object key) {
/* 74 */     return getOrderedBidiMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public Object previousKey(Object key) {
/* 78 */     return getOrderedBidiMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 82 */     return getOrderedBidiMap().inverseOrderedBidiMap();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/AbstractOrderedBidiMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */