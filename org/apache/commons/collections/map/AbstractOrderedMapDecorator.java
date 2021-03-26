/*    */ package org.apache.commons.collections.map;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ import org.apache.commons.collections.OrderedMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractOrderedMapDecorator
/*    */   extends AbstractMapDecorator
/*    */   implements OrderedMap
/*    */ {
/*    */   protected AbstractOrderedMapDecorator() {}
/*    */   
/*    */   public AbstractOrderedMapDecorator(OrderedMap map) {
/* 57 */     super((Map)map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected OrderedMap getOrderedMap() {
/* 66 */     return (OrderedMap)this.map;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object firstKey() {
/* 71 */     return getOrderedMap().firstKey();
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 75 */     return getOrderedMap().lastKey();
/*    */   }
/*    */   
/*    */   public Object nextKey(Object key) {
/* 79 */     return getOrderedMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public Object previousKey(Object key) {
/* 83 */     return getOrderedMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public MapIterator mapIterator() {
/* 87 */     return getOrderedMap().mapIterator();
/*    */   }
/*    */   
/*    */   public OrderedMapIterator orderedMapIterator() {
/* 91 */     return getOrderedMap().orderedMapIterator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractOrderedMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */