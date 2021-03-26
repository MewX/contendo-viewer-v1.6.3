/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.SortedSet;
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
/*    */ public abstract class AbstractSortedSetDecorator
/*    */   extends AbstractSetDecorator
/*    */   implements SortedSet
/*    */ {
/*    */   protected AbstractSortedSetDecorator() {}
/*    */   
/*    */   protected AbstractSortedSetDecorator(Set set) {
/* 49 */     super(set);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedSet getSortedSet() {
/* 58 */     return (SortedSet)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public SortedSet subSet(Object fromElement, Object toElement) {
/* 63 */     return getSortedSet().subSet(fromElement, toElement);
/*    */   }
/*    */   
/*    */   public SortedSet headSet(Object toElement) {
/* 67 */     return getSortedSet().headSet(toElement);
/*    */   }
/*    */   
/*    */   public SortedSet tailSet(Object fromElement) {
/* 71 */     return getSortedSet().tailSet(fromElement);
/*    */   }
/*    */   
/*    */   public Object first() {
/* 75 */     return getSortedSet().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 79 */     return getSortedSet().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 83 */     return getSortedSet().comparator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/AbstractSortedSetDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */