/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.Predicate;
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
/*    */ public class PredicatedSortedBag
/*    */   extends PredicatedBag
/*    */   implements SortedBag
/*    */ {
/*    */   private static final long serialVersionUID = 3448581314086406616L;
/*    */   
/*    */   public static SortedBag decorate(SortedBag bag, Predicate predicate) {
/* 61 */     return new PredicatedSortedBag(bag, predicate);
/*    */   }
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
/*    */   protected PredicatedSortedBag(SortedBag bag, Predicate predicate) {
/* 77 */     super((Bag)bag, predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedBag getSortedBag() {
/* 86 */     return (SortedBag)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object first() {
/* 91 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 95 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 99 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/PredicatedSortedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */