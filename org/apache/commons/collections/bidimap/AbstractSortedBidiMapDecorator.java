/*    */ package org.apache.commons.collections.bidimap;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ import org.apache.commons.collections.OrderedBidiMap;
/*    */ import org.apache.commons.collections.SortedBidiMap;
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
/*    */ public abstract class AbstractSortedBidiMapDecorator
/*    */   extends AbstractOrderedBidiMapDecorator
/*    */   implements SortedBidiMap
/*    */ {
/*    */   public AbstractSortedBidiMapDecorator(SortedBidiMap map) {
/* 50 */     super((OrderedBidiMap)map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedBidiMap getSortedBidiMap() {
/* 59 */     return (SortedBidiMap)this.map;
/*    */   }
/*    */ 
/*    */   
/*    */   public SortedBidiMap inverseSortedBidiMap() {
/* 64 */     return getSortedBidiMap().inverseSortedBidiMap();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 68 */     return getSortedBidiMap().comparator();
/*    */   }
/*    */   
/*    */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 72 */     return getSortedBidiMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap headMap(Object toKey) {
/* 76 */     return getSortedBidiMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public SortedMap tailMap(Object fromKey) {
/* 80 */     return getSortedBidiMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/AbstractSortedBidiMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */