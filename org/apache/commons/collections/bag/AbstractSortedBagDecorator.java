/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.SortedBag;
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
/*    */ public abstract class AbstractSortedBagDecorator
/*    */   extends AbstractBagDecorator
/*    */   implements SortedBag
/*    */ {
/*    */   protected AbstractSortedBagDecorator() {}
/*    */   
/*    */   protected AbstractSortedBagDecorator(SortedBag bag) {
/* 50 */     super((Bag)bag);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedBag getSortedBag() {
/* 59 */     return (SortedBag)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object first() {
/* 64 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 68 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 72 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/AbstractSortedBagDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */