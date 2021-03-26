/*    */ package org.apache.commons.collections.map;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
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
/*    */ 
/*    */ public abstract class AbstractSortedMapDecorator
/*    */   extends AbstractMapDecorator
/*    */   implements SortedMap
/*    */ {
/*    */   protected AbstractSortedMapDecorator() {}
/*    */   
/*    */   public AbstractSortedMapDecorator(SortedMap map) {
/* 56 */     super(map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedMap getSortedMap() {
/* 65 */     return (SortedMap)this.map;
/*    */   }
/*    */ 
/*    */   
/*    */   public Comparator comparator() {
/* 70 */     return getSortedMap().comparator();
/*    */   }
/*    */   
/*    */   public Object firstKey() {
/* 74 */     return getSortedMap().firstKey();
/*    */   }
/*    */   
/*    */   public SortedMap headMap(Object toKey) {
/* 78 */     return getSortedMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public Object lastKey() {
/* 82 */     return getSortedMap().lastKey();
/*    */   }
/*    */   
/*    */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 86 */     return getSortedMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap tailMap(Object fromKey) {
/* 90 */     return getSortedMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractSortedMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */